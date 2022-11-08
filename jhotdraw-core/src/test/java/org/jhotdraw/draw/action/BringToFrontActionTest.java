package org.jhotdraw.draw.action;

import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.QuadTreeDrawing;
import org.jhotdraw.draw.figure.Figure;
import org.junit.Test;

import java.awt.geom.Rectangle2D;
import java.util.*;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class BringToFrontActionTest {

    @Test
    public void figure_should_be_brought_to_front() {
        // Arrange
        DrawingView mockView = mock(DrawingView.class);
        Drawing drawing = new QuadTreeDrawing();

        ArrayList<Figure> figures = new ArrayList<>();
        Figure figure1 = mock(Figure.class);
        Figure figure2 = mock(Figure.class);
        when(figure1.getDrawingArea()).thenReturn(new Rectangle2D.Double());
        when(figure2.getDrawingArea()).thenReturn(new Rectangle2D.Double());
        figures.add(figure1);
        figures.add(figure2);
        drawing.addAll(figures);
        when(mockView.getDrawing()).thenReturn(drawing);

        // Act
        BringToFrontAction.bringToFront(mockView, Collections.singletonList(figure2));
        List<Figure> returnedFigures = drawing.getFiguresFrontToBack();

        // Assert
        assertEquals(returnedFigures.get(0), figure2);
    }
}