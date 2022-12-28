package org.jhotdraw.draw.undo;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

public class UndoAcceptanceTest extends ScenarioTest<GivenStuff, WhenStuff, ThenStuff> {
    @Test
    public void editIsPresent() {
        given().undoRedoManager();
        when().addEdit();
        then().undoIsPossible();
    }

    @Test
    public void undoingRemovesUndo() {
        given().anUndoableEdit();
        when().undo();
        then().cannotUndo();
    }

    @Test
    public void undoingAddsRedo() {
        given().anUndoableEdit();
        when().undo();
        then().canRedo();
    }

    @Test
    public void redoRemovesRedo() {
        given().aRedoableEdit();
        when().redo();
        then().cannotRedo();
    }

    @Test
    public void redoingAddsUndo() {
        given().aRedoableEdit();
        when().redo();
        then().canUndo();
    }
}
