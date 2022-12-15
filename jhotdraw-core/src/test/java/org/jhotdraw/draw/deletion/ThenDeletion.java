package org.jhotdraw.draw.deletion;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.figure.Figure;

import java.util.List;

import static org.junit.Assert.*;


public class ThenDeletion extends Stage<ThenDeletion> {
    @ScenarioState
    DefaultDrawingView view;

    public ThenDeletion thereIsNoFiguresOnTheDrawing() {
        List<Figure> figures = view.getDrawing().getFiguresFrontToBack();
        assertTrue(figures.isEmpty());
        return self();
    }

    public ThenDeletion theDrawingContainsThisExactAmountOfFigures(int expectedAmount) {
        List<Figure> figures = view.getDrawing().getFiguresFrontToBack();
        assertEquals(figures.size(), expectedAmount);
        return self();
    }

    public ThenDeletion thereIsAnyAmountOfFiguresOnTheDrawing() {
        List<Figure> figures = view.getDrawing().getFiguresFrontToBack();
        assertFalse(figures.isEmpty());
        return self();
    }
}
