package org.jhotdraw.samples.svg.jgivenstages;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.QuadTreeDrawing;
import org.jhotdraw.samples.svg.figures.SVGEllipseFigure;

public class GivenSomething extends Stage<GivenSomething> {

    @ProvidedScenarioState
    Drawing drawing;

    public GivenSomething anEmptyDrawing() {
        this.drawing = new QuadTreeDrawing();
        return this;
    }

    public GivenSomething aDrawing() {
        this.drawing = new QuadTreeDrawing();
        return this;
    }

    public GivenSomething anEllipse() {
        this.drawing.add(new SVGEllipseFigure());
        return this;
    }
}
