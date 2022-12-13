package org.jhotdraw.draw.deletion;

import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;

import java.util.List;

public class DeletionUtils {

    /**
     * Will attempt to delete figures of a DrawiingView. If successfull, will add the deletion to the undo-queue.
     * @param drawingView drawing view to delete the figures from
     * @param figures The figures to delete
     * @return true if deletion was successfull, otherwise false.
     */
    public static boolean tryUndoableDeleteFigures(DrawingView drawingView, List<Figure> figures){
        Drawing drawing = drawingView.getDrawing();
        int[] deletedIndicies = getFigureZIndicies(drawing, figures);

        boolean deletionFailed = !tryDeleteFigures(drawing, figures);
        if(deletionFailed){
            return false;
        }

        UndoableDeletion event = new UndoableDeletion(drawingView, figures, deletedIndicies);

        drawing.fireUndoableEditHappened(event);

        return true;
    }

    /**
     * Will attempt to delete figures from any drawing
     * @param drawing The drawing to delete the figures from
     * @param figures The figures to delete
     * @return true if deletion was successfull, otherwise false.
     */
    public static boolean tryDeleteFigures(Drawing drawing, List<Figure> figures){
        if(figures.isEmpty()){
            return false;
        }

        boolean anyFigureIsNonRemoveable = figures.stream().anyMatch(x->!x.isRemovable());
        if(anyFigureIsNonRemoveable){
            return false;
        }

        drawing.removeAll(figures);
        return true;
    }

    private static int[] getFigureZIndicies(Drawing drawing, List<Figure> figures){
        final int[] deletedFigureIndices = new int[figures.size()];
        for (int i = 0; i
                < deletedFigureIndices.length; i++) {
            deletedFigureIndices[i] = drawing.indexOf(figures.get(i));
        }
        return deletedFigureIndices;
    }
}
