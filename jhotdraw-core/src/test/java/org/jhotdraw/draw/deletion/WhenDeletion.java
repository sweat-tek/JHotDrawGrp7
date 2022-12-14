package org.jhotdraw.draw.deletion;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.jhotdraw.draw.DefaultDrawingView;

public class WhenDeletion extends Stage<WhenDeletion> {

    @ScenarioState
    DefaultDrawingView view;

    public WhenDeletion nothingHappens(){
        return self();
    }

    public WhenDeletion aFigureIsAdded(){
        view.getDrawing().add(FigureMockFactory.createRemoveableFigure());
        return self();
    }

    public WhenDeletion aNonRemoveableFigureIsAdded(){
        view.getDrawing().add(FigureMockFactory.createNonRemoveableFigure());
        return self();
    }

    public WhenDeletion allFiguresAreSelected(){
        view.clearSelection();
        view.addToSelection(view.getDrawing().getFiguresFrontToBack());
        return self();
    }

    public WhenDeletion aSingleFigureIsSelected(){
        view.clearSelection();
        view.addToSelection(view.getDrawing().getFiguresFrontToBack().get(0));
        return self();
    }

    public WhenDeletion deleteIsPressed(){
        view.delete();
        return self();
    }
}
