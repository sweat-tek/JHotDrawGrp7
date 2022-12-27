package org.jhotdraw.samples.svg.figures;

import org.junit.Test;

import java.awt.geom.AffineTransform;
import static org.jhotdraw.draw.AttributeKeys.TRANSFORM;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SVGEllipseFigureTest {
    @Test
    public void testDefaultTransformNull() {
        // Arrange/Act
        SVGEllipseFigure figure = new SVGEllipseFigure(1, 1, 10, 10);
        AffineTransform prevTransform = figure.get(TRANSFORM);

        // Assert
        assertNull(prevTransform);
    }

    @Test
    public void testTransformCanBeApplied() {
        // Arrange
        SVGEllipseFigure figure = new SVGEllipseFigure();

        // Act
        AffineTransform transform = new AffineTransform(1, 2, 1, 2, 1, 2);
        figure.transform(transform);

        // Assert
        assertEquals(transform, figure.get(TRANSFORM));
    }

    @Test
    public void testTransformsCanBeStacked() {
        // Arrange
        SVGEllipseFigure figure = new SVGEllipseFigure();
        AffineTransform transform2 = new AffineTransform(2, 1, 2, 1, 2, 1);
        AffineTransform transform = new AffineTransform(1, 2, 1, 2, 1, 2);

        // Act
        figure.transform(transform);
        figure.transform(transform2);

        // Assert
        // This should be the resulting transform when applying first transform1 and then transform2
        AffineTransform expected = new AffineTransform(6, 3, 6, 3, 8, 4);
        assertEquals(expected, figure.get(TRANSFORM));
    }
}
