package org.jhotdraw.draw.action;

import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.util.LinkedList;

public class UndoableBringToFront extends AbstractUndoableArrange {
    public UndoableBringToFront(String ID, DrawingView view, LinkedList<Figure> figures) {
        super(ID, view, figures);
    }
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        BringToFrontAction.bringToFront(view, figures);
    }
    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        SendToBackAction.sendToBack(view, figures);
    }
}
