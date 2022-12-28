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

    private final DrawingView mockView = mock(DrawingView.class);
    private final Drawing drawing = new QuadTreeDrawing();

    public WhenArranging sending_figure_to_back(Figure figure) {
        drawing.addAll(figures);
        org.mockito.Mockito.when(mockView.getDrawing()).thenReturn(drawing);

        SendToBackAction.sendToBack(mockView, Collections.singletonList(figure));

        sortedFigures = drawing.getFiguresFrontToBack();
        return self();
    }

    public WhenArranging bringing_figure_to_front(Figure figure) {
        drawing.addAll(figures);
        org.mockito.Mockito.when(mockView.getDrawing()).thenReturn(drawing);

        BringToFrontAction.bringToFront(mockView, Collections.singletonList(figure));

        sortedFigures = drawing.getFiguresFrontToBack();
        return self();
    }
}
