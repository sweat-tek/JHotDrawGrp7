package org.jhotdraw.draw.deletion;

import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.swing.undo.UndoableEdit;
import java.util.ArrayList;
import java.util.List;

import static com.tngtech.jgiven.impl.util.AssertionUtil.*;
import static org.mockito.Mockito.*;

public class DeletionUtilsTest {

    @Test
    public void tryUndoableDeleteFigures_baseline_shouldDelete() {
        // Arrange
        List<Figure> figures = new ArrayList<>();
        Figure removeableFigure1 = mock(Figure.class);
        when(removeableFigure1.isRemovable()).thenReturn(true);
        figures.add(removeableFigure1);

        DrawingView view = mock(DrawingView.class);
        Drawing drawing = mock(Drawing.class);
        when(view.getDrawing()).thenReturn(drawing);

        // Act
        boolean successfullDeletion = DeletionUtils.tryUndoableDeleteFigures(view, figures);

        // Assert
        assertTrue(successfullDeletion, "there is not reason the figure could not be deleted");
        verify(drawing).removeAll(figures);
        verify(drawing).fireUndoableEditHappened(any());
    }

    @Test
    public void tryUndoableDeleteFigures_simulateUndoRedo_shouldCallMockAppropriately() {
        // Arrange
        List<Figure> figures = new ArrayList<>();
        Figure removeableFigure1 = mock(Figure.class);
        when(removeableFigure1.isRemovable()).thenReturn(true);
        figures.add(removeableFigure1);

        DrawingView view = mock(DrawingView.class);
        Drawing drawing = mock(Drawing.class);
        when(drawing.indexOf(any())).thenReturn(0);
        when(view.getDrawing()).thenReturn(drawing);

        ArgumentCaptor<UndoableEdit> undoableEditArgumentCaptor = ArgumentCaptor.forClass(UndoableEdit.class);

        DeletionUtils.tryUndoableDeleteFigures(view, figures);

        // Act & Assert
        verify(drawing).fireUndoableEditHappened(undoableEditArgumentCaptor.capture());
        UndoableEdit edit = undoableEditArgumentCaptor.getValue();

        verify(drawing, never()).add(anyInt(), eq(removeableFigure1));
        edit.undo();
        verify(drawing).add(anyInt(), eq(removeableFigure1));

        int currentDrawingRemoveInvocationCount = (int) mockingDetails(drawing).getInvocations()
                .stream().filter(x -> x.getMethod().getName().equalsIgnoreCase("remove")).count();

        edit.redo();
        verify(drawing, times(currentDrawingRemoveInvocationCount + 1)).remove(eq(removeableFigure1));
    }

    @Test
    public void tryUndoableDeleteFigures_emptyFigureList_shouldNotDeleteAndNoEvent() {
        // Arrange
        List<Figure> figures = new ArrayList<>();

        DrawingView view = mock(DrawingView.class);
        Drawing drawing = mock(Drawing.class);
        when(view.getDrawing()).thenReturn(drawing);

        // Act
        boolean successfullDeletion = DeletionUtils.tryUndoableDeleteFigures(view, figures);

        // Assert
        assertFalse(successfullDeletion, "as the list is empty, there should be nothing to delete, thus deletion failed");
        verify(drawing, never()).removeAll(figures);
        verify(drawing, never()).fireUndoableEditHappened(any());
    }

    @Test
    public void tryDeleteFigures_baseline_shouldDelete() {
        // Arrange
        List<Figure> figures = new ArrayList<>();
        Figure removeableFigure1 = mock(Figure.class);
        when(removeableFigure1.isRemovable()).thenReturn(true);

        Figure removeableFigure2 = mock(Figure.class);
        when(removeableFigure2.isRemovable()).thenReturn(true);

        figures.add(removeableFigure1);
        figures.add(removeableFigure2);

        Drawing drawing = mock(Drawing.class);

        // Act
        boolean successfullDeletion = DeletionUtils.tryDeleteFigures(drawing, figures);

        // Assert
        assertTrue(successfullDeletion, "there is no reason these figures could not be removed");
        verify(drawing).removeAll(figures);
    }

    @Test
    public void tryDeleteFigures_emptyFigureList_shouldNotDelete() {
        // Arrange
        List<Figure> figures = new ArrayList<>();
        Drawing drawing = mock(Drawing.class);

        // Act
        boolean successfullDeletion = DeletionUtils.tryDeleteFigures(drawing, figures);

        // Assert
        assertFalse(successfullDeletion, "as the list is empty, there should be nothing to delete, thus deletion failed");
        verify(drawing, never()).removeAll(any());
    }

    @Test
    public void tryDeleteFigures_containsUnremoveableFigures_shouldNotDelete() {
        // Arrange
        List<Figure> figures = new ArrayList<>();
        Figure nonRemoveableFigure = mock(Figure.class);
        when(nonRemoveableFigure.isRemovable()).thenReturn(false);

        Figure removeableFigure = mock(Figure.class);
        when(removeableFigure.isRemovable()).thenReturn(true);

        figures.add(nonRemoveableFigure);
        figures.add(removeableFigure);

        Drawing drawing = mock(Drawing.class);

        // Act
        boolean successfullDeletion = DeletionUtils.tryDeleteFigures(drawing, figures);

        // Assert
        assertFalse(successfullDeletion, "as one of the items is non-removeable, deletion should not occur");
        verify(drawing, never()).removeAll(any());
    }
}