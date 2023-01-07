package org.jhotdraw.draw.action;

import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.util.ResourceBundleUtil;

import javax.swing.undo.AbstractUndoableEdit;
import java.util.LinkedList;

public abstract class AbstractUndoableArrange extends AbstractUndoableEdit {
    private final String ID;
    final protected DrawingView view;
    final protected LinkedList<Figure> figures;
    public AbstractUndoableArrange(String ID, DrawingView view, LinkedList<Figure> figures) {
        this.ID = ID;
        this.view = view;
        this.figures = figures;
    }
    @Override
    public String getPresentationName() {
        ResourceBundleUtil labels
                = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");
        return labels.getTextProperty(ID);
    }
}
