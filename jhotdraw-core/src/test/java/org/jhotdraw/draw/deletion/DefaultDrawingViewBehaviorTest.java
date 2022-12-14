package org.jhotdraw.draw.deletion;

import com.tngtech.jgiven.testng.ScenarioTest;
import org.testng.annotations.Test;

public class DefaultDrawingViewBehaviorTest extends ScenarioTest<GivenDeletion, WhenDeletion, ThenDeletion> {

    @Test
    public void deletingFiguresFromDrawing() {
        given().aDrawingWithAFigure();
        when().aSingleFigureIsSelected()
                .and().deleteIsPressed();
        then().thereIsNoFiguresOnTheDrawing();
    }

    @Test
    public void deletingMultipleFiguresFromDrawing() {
        given().aDrawingWithWithThisAmountOfFigures(10);
        when().allFiguresAreSelected()
                .and().deleteIsPressed();
        then().thereIsNoFiguresOnTheDrawing();
    }

    @Test
    public void cannotDeleteNonRemoveableFigure() {
        given().anEmptyDrawing();
        when().aNonRemoveableFigureIsAdded()
                .and().aSingleFigureIsSelected()
                        .and().deleteIsPressed();
        then().theDrawingContainsThisExactAmountOfFigures(1);
    }

    @Test
    public void nothingHappens() {
        given().anEmptyDrawing();
        when().nothingHappens();
        then().thereIsNoFiguresOnTheDrawing();
    }

    @Test
    public void figureAddedNotDeleted(){
        given().anEmptyDrawing();
        when().aFigureIsAdded();
        then().thereIsAnyAmountOfFiguresOnTheDrawing();
    }

    @Test
    public void aSingleFigureDeleted(){
        given().aDrawingWithWithThisAmountOfFigures(10);
        when().aSingleFigureIsSelected()
                .and().deleteIsPressed();
        then().theDrawingContainsThisExactAmountOfFigures(9);
    }
}
