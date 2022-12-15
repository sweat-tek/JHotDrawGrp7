package org.jhotdraw.draw.event;

import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.figure.Figure;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class CreateEdit extends AbstractUndoableEdit {
    private static final long serialVersionUID = 1L;
    private String presentationName;
    private Figure addedFigure;
    private Drawing addedDrawing;

    public CreateEdit(String presentationName, Figure addedFigure, Drawing addedDrawing) {
        this.presentationName = presentationName;
        this.addedFigure = addedFigure;
        this.addedDrawing = addedDrawing;
    }

    @Override
    public String getPresentationName() {
        return presentationName;
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        addedDrawing.remove(addedFigure);
    }

    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        addedDrawing.add(addedFigure);
    }
}
