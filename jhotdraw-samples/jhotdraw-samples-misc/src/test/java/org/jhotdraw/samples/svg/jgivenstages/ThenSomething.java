package org.jhotdraw.samples.svg.jgivenstages;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.samples.svg.figures.SVGEllipseFigure;

import java.awt.geom.Rectangle2D;
import java.util.List;

import static org.junit.Assert.*;

public class ThenSomething extends Stage<ThenSomething> {

    @ScenarioState
    Drawing drawing;

    public ThenSomething theDrawingContainsAnEllipse() {
        List<Figure> figures = drawing.getFiguresFrontToBack();
        assertTrue(figures.stream().anyMatch(figure -> figure instanceof SVGEllipseFigure));
        return this;
    }

    public ThenSomething theEllipseHasTheCorrectSize() {
        assertNotNull(drawing);
        List<Figure> figures = drawing.getFiguresFrontToBack();
        assertEquals(figures.size(), 1);
        Figure figure = figures.get(0);
        assertTrue(figure instanceof SVGEllipseFigure);
        SVGEllipseFigure ellipse = (SVGEllipseFigure) figure;
        Rectangle2D bounds = ellipse.getBounds();
        assertEquals(new Rectangle2D.Double(1, 2, 3, 4), bounds);
        return this;
    }
}
