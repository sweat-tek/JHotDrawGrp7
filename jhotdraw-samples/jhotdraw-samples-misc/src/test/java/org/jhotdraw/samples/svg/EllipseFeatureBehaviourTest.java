package org.jhotdraw.samples.svg;

import org.jhotdraw.samples.svg.jgivenstages.GivenSomething;
import org.jhotdraw.samples.svg.jgivenstages.ThenSomething;
import org.jhotdraw.samples.svg.jgivenstages.WhenSomething;
import org.junit.Test;
import com.tngtech.jgiven.junit.ScenarioTest;

public class EllipseFeatureBehaviourTest extends ScenarioTest<GivenSomething, WhenSomething, ThenSomething> {

    @Test
    public void drawEllipse() {
        given().anEmptyDrawing();
        when().anEllipseIsDrawn();
        then().theDrawingContainsAnEllipse();
    }

    @Test
    public void resizeEllipse() {
        given().aDrawing().
                and().anEllipse();
        when().theEllipseIsResized();
        then().theEllipseHasTheCorrectSize();
    }
}
