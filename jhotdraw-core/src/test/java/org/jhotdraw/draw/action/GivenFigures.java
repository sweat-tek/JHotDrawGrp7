package org.jhotdraw.draw.action;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.figure.Figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.mock;

public class GivenFigures extends Stage<GivenFigures> {
    @ProvidedScenarioState
    List<Figure> figures = new ArrayList<>();

    public GivenFigures multiple_figures() {
        Figure figure1 = mock(Figure.class);
        Figure figure2 = mock(Figure.class);
        figures.add(figure1);
        figures.add(figure2);
        return self();
    }

    public GivenFigures multiple_specific_figures(Collection<Figure> figureCollection){
        figures.addAll(figureCollection);
        return self();
    }
}