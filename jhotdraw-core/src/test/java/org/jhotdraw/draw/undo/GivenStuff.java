package org.jhotdraw.draw.undo;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.undo.UndoRedoManager;
import org.mockito.Mockito;

import javax.swing.undo.UndoableEdit;

import static org.mockito.Mockito.mock;

public class GivenStuff extends Stage<GivenStuff> {
    @ProvidedScenarioState
    UndoRedoManager manager;


    // Given an undo manager with undoable event
    public GivenStuff anUndoableEdit() {
        this.manager = new UndoRedoManager();
        UndoableEdit edit = mock(UndoableEdit.class);
        Mockito.when(edit.isSignificant()).thenReturn(true);
        Mockito.when(edit.canUndo()).thenReturn(true);
        Mockito.when(edit.canRedo()).thenReturn(true);
        manager.addEdit(edit);
        return this;
    }


    // Given undo manager with undoable event that has been undone
    public GivenStuff aRedoableEdit() {
        this.manager = new UndoRedoManager();
        UndoableEdit edit = mock(UndoableEdit.class);
        Mockito.when(edit.isSignificant()).thenReturn(true);
        Mockito.when(edit.canUndo()).thenReturn(true);
        Mockito.when(edit.canRedo()).thenReturn(true);
        manager.addEdit(edit);
        manager.undo();
        return this;
    }

    public GivenStuff undoRedoManager() {

        this.manager = new UndoRedoManager();
        return this;
    }



}
