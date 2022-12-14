package org.jhotdraw.draw.deletion;

import org.jhotdraw.draw.figure.Figure;

import java.awt.geom.Rectangle2D;

import static org.mockito.Mockito.*;

public class FigureMockFactory {
    public static Figure createRemoveableFigure(){
        Figure figure = baseFigure();

        when(figure.isRemovable()).thenReturn(true);
        return figure;
    }

    public static Figure createNonRemoveableFigure(){
        Figure figure = baseFigure();
        when(figure.isRemovable()).thenReturn(false);
        return figure;
    }

    private static Figure baseFigure(){
        Figure figure = mock(Figure.class);
        when(figure.getBounds()).thenReturn(new Rectangle2D.Double(0, 0, 10, 10));
        when(figure.getDrawingArea()).thenReturn(new Rectangle2D.Double(0, 0, 10, 10));
        when(figure.getDrawingArea(anyDouble())).thenReturn(new Rectangle2D.Double(0, 0, 20, 20));

        return figure;
    }
}
