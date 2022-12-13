package org.jhotdraw.draw.deletion;

import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.util.ResourceBundleUtil;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.util.List;

public class UndoableDeletion extends AbstractUndoableEdit {
    private final DrawingView drawingView;
    private final List<Figure> deletedFigures;
    private final int[] deletedFigureIndicies;

    public UndoableDeletion(DrawingView drawingView, List<Figure> deletedFigures, int[] deletedFigureIndicies){
        this.drawingView = drawingView;
        this.deletedFigures = deletedFigures;
        this.deletedFigureIndicies = deletedFigureIndicies;
    }

    @Override
    public String getPresentationName() {
        ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");
        return labels.getString("edit.delete.text");
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        drawingView.clearSelection();
        Drawing d = drawingView.getDrawing();
        for (int i = 0; i
                < deletedFigureIndicies.length; i++) {
            d.add(deletedFigureIndicies[i], deletedFigures.get(i));
        }
        drawingView.addToSelection(deletedFigures);
    }

    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        for (int i = 0; i
                < deletedFigureIndicies.length; i++) {
            drawingView.getDrawing().remove(deletedFigures.get(i));
        }
    }
}
