package org.jhotdraw.draw.undo;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.jhotdraw.undo.UndoRedoManager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ThenStuff extends Stage<ThenStuff> {
    @ScenarioState
    UndoRedoManager manager;

    public ThenStuff cannotUndo() {
        assertFalse(manager.canUndo());
        return this;
    }

    public ThenStuff cannotRedo() {
        assertFalse(manager.canRedo());
        return this;
    }

    public ThenStuff canUndo() {
        assertTrue(manager.canUndo());
        return this;
    }

    public ThenStuff canRedo() {
        assertTrue(manager.canRedo());
        return this;
    }

    public ThenStuff undoIsPossible() {
        assertTrue(manager.canUndo());
        return this;
    }

}
