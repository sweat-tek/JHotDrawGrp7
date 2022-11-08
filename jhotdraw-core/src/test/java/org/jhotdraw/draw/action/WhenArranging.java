package org.jhotdraw.draw.action;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.QuadTreeDrawing;
import org.jhotdraw.draw.figure.Figure;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class WhenArranging extends Stage<WhenArranging> {
    @ExpectedScenarioState
    List<Figure> figures;

    @ProvidedScenarioState
    List<Figure> sortedFigures;

    public WhenArranging sending_figure_to_back() {
        DrawingView mockView = mock(DrawingView.class);
        Drawing drawing = new QuadTreeDrawing();
        drawing.addAll(figures);
        org.mockito.Mockito.when(mockView.getDrawing()).thenReturn(drawing);

        SendToBackAction.sendToBack(mockView, Collections.singletonList(figures.get(0)));

        sortedFigures = drawing.getFiguresFrontToBack();
        return self();
    }

    public WhenArranging bringing_figure_to_front() {
        DrawingView mockView = mock(DrawingView.class);
        Drawing drawing = new QuadTreeDrawing();
        drawing.addAll(figures);
        org.mockito.Mockito.when(mockView.getDrawing()).thenReturn(drawing);

        BringToFrontAction.bringToFront(mockView, Collections.singletonList(figures.get(1)));

        sortedFigures = drawing.getFiguresFrontToBack();
        return self();
    }
}
