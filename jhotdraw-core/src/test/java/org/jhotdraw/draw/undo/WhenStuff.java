package org.jhotdraw.draw.undo;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.jhotdraw.undo.UndoRedoManager;
import org.mockito.Mockito;

import javax.swing.undo.UndoableEdit;

import static org.mockito.Mockito.mock;

public class WhenStuff extends Stage<WhenStuff> {
    @ScenarioState
    UndoRedoManager manager;

    public WhenStuff undo() {
        manager.undo();
        return this;
    }

    public WhenStuff redo() {
        manager.redo();
        return this;
    }

    public WhenStuff addEdit() {
        UndoableEdit edit = mock(UndoableEdit.class);
        Mockito.when(edit.isSignificant()).thenReturn(true);
        Mockito.when(edit.canUndo()).thenReturn(true);
        manager.addEdit(edit);
        return this;
    }
}
