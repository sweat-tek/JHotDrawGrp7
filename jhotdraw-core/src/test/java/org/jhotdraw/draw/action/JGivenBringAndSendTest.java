package org.jhotdraw.draw.action;

import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.figure.RectangleFigure;
import org.junit.Test;
import com.tngtech.jgiven.junit.ScenarioTest;
import java.util.Arrays;


public class JGivenBringAndSendTest extends ScenarioTest<GivenFigures, WhenArranging, ThenFigure>{

    @Test
    public void figure_should_be_brought_to_front(){
        // Arrange
        Figure figure1 = new RectangleFigure();
        Figure figure2 = new RectangleFigure();
        given().multiple_specific_figures(Arrays.asList(figure1, figure2));

        // Act
        when().bringing_figure_to_front(figure2);

        // Assert
        then().should_be_in_front(figure2);
    }

    @Test
    public void specific_figure_should_be_brought_to_front(){
        // Arrange
        Figure figure1 = new RectangleFigure();
        Figure figure2 = new RectangleFigure();
        Figure figure3 = new RectangleFigure();
        given().multiple_specific_figures(Arrays.asList(figure1, figure2, figure3));

        // Act
        when().bringing_figure_to_front(figure2);

        // Assert
        then().should_be_in_front(figure2);
    }

    @Test
    public void figure_should_be_sent_to_back(){
        // Arrange
        Figure figure1 = new RectangleFigure();
        Figure figure2 = new RectangleFigure();
        given().multiple_specific_figures(Arrays.asList(figure1, figure2));

        // Act
        when().sending_figure_to_back(figure1);

        // Assert
        then().should_be_in_back(figure1);
    }

    @Test
    public void specific_figure_should_be_sent_to_back(){
        // Arrange
        Figure figure1 = new RectangleFigure();
        Figure figure2 = new RectangleFigure();
        Figure figure3 = new RectangleFigure();
        given().multiple_specific_figures(Arrays.asList(figure1, figure2, figure3));

        // Act
        when().sending_figure_to_back(figure2);

        // Assert
        then().should_be_in_back(figure2);
    }
}