package org.jhotdraw.samples.svg.jgivenstages;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.samples.svg.figures.SVGEllipseFigure;

import java.awt.geom.Point2D;
import java.util.List;

import static org.junit.Assert.*;

public class WhenSomething extends Stage<WhenSomething> {

    @ScenarioState
    Drawing drawing;

    public WhenSomething anEllipseIsDrawn() {
        assertNotNull(drawing);
        drawing.add(new SVGEllipseFigure());
        return this;
    }

    public WhenSomething theEllipseIsResized() {
        assertNotNull(drawing);
        List<Figure> figures = drawing.getFiguresFrontToBack();
        assertEquals(figures.size(), 1);
        Figure figure = figures.get(0);
        assertTrue(figure instanceof SVGEllipseFigure);
        SVGEllipseFigure ellipse = (SVGEllipseFigure) figure;
        ellipse.setBounds(new Point2D.Double(1, 2), new Point2D.Double(4, 6));
        return this;
    }
}
