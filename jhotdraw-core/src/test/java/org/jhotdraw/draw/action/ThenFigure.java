package org.jhotdraw.draw.action;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.figure.Figure;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ThenFigure extends Stage<ThenFigure> {
    @ExpectedScenarioState
    List<Figure> sortedFigures;

    public ThenFigure should_be_in_front(Figure figure) {
        Figure frontFigure = sortedFigures.get(0);
        assertEquals(figure, frontFigure);
        return self();
    }

    public ThenFigure should_be_in_back(Figure figure){
        Figure backFigure = sortedFigures.get(sortedFigures.size()-1);
        assertEquals(figure, backFigure);
        return self();
    }
}
