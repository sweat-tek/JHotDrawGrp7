package org.jhotdraw.draw.deletion;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.QuadTreeDrawing;

public class GivenDeletion extends Stage<GivenDeletion> {

    @ProvidedScenarioState
    DefaultDrawingView view;

    public GivenDeletion anEmptyDrawing(){
        view = new DefaultDrawingView();
        view.setDrawing(new QuadTreeDrawing());
        return self();
    }

    public GivenDeletion aDrawingWithAFigure(){
        anEmptyDrawing();
        view.getDrawing().add(FigureMockFactory.createRemoveableFigure());
        return self();
    }

    public GivenDeletion aDrawingWithWithThisAmountOfFigures(int amount){
        anEmptyDrawing();
        for (int i = 0; i < amount; i++) {
            view.getDrawing().add(FigureMockFactory.createRemoveableFigure());
        }
        return self();
    }
}
