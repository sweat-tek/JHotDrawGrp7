package org.jhotdraw.draw.action;

import org.jhotdraw.draw.figure.Figure;
import org.junit.Test;

import static org.mockito.Mockito.mock;

import com.tngtech.jgiven.junit.ScenarioTest;

import java.awt.geom.Rectangle2D;
import java.util.Arrays;


public class JGivenBringAndSendTest extends ScenarioTest<GivenFigures, WhenArranging, ThenFigure>{

    @Test
    public void figure_should_be_brought_to_front(){
        // Arrange
        Figure figure1 = mock(Figure.class);
        Figure figure2 = mock(Figure.class);
        org.mockito.Mockito.when(figure1.getDrawingArea()).thenReturn(new Rectangle2D.Double());
        org.mockito.Mockito.when(figure2.getDrawingArea()).thenReturn(new Rectangle2D.Double());
        given().multiple_specific_figures(Arrays.asList(figure1, figure2));

        // Act
        when().bringing_figure_to_front();

        // Assert
        then().should_be_in_front(figure2);
        then().should_be_in_back(figure1);
    }

    @Test
    public void specific_figure_should_be_brought_to_front(){
        // Arrange
        Figure figure1 = mock(Figure.class);
        Figure figure2 = mock(Figure.class);
        Figure figure3 = mock(Figure.class);
        org.mockito.Mockito.when(figure1.getDrawingArea()).thenReturn(new Rectangle2D.Double());
        org.mockito.Mockito.when(figure2.getDrawingArea()).thenReturn(new Rectangle2D.Double());
        org.mockito.Mockito.when(figure3.getDrawingArea()).thenReturn(new Rectangle2D.Double());
        given().multiple_specific_figures(Arrays.asList(figure1, figure2, figure3));

        // Act
        when().bringing_figure_to_front();

        // Assert
        then().should_be_in_front(figure2);
    }

    @Test
    public void figure_should_be_sent_to_back(){
        // Arrange
        Figure figure1 = mock(Figure.class);
        Figure figure2 = mock(Figure.class);
        org.mockito.Mockito.when(figure1.getDrawingArea()).thenReturn(new Rectangle2D.Double());
        org.mockito.Mockito.when(figure2.getDrawingArea()).thenReturn(new Rectangle2D.Double());
        given().multiple_specific_figures(Arrays.asList(figure1, figure2));

        // Act
        when().sending_figure_to_back();

        // Assert
        then().should_be_in_back(figure1);
        then().should_be_in_front(figure2);
    }

    @Test
    public void specific_figure_should_be_sent_to_back(){
        // Arrange
        Figure figure1 = mock(Figure.class);
        Figure figure2 = mock(Figure.class);
        Figure figure3 = mock(Figure.class);
        org.mockito.Mockito.when(figure1.getDrawingArea()).thenReturn(new Rectangle2D.Double());
        org.mockito.Mockito.when(figure2.getDrawingArea()).thenReturn(new Rectangle2D.Double());
        org.mockito.Mockito.when(figure3.getDrawingArea()).thenReturn(new Rectangle2D.Double());
        given().multiple_specific_figures(Arrays.asList(figure1, figure2, figure3));

        // Act
        when().sending_specific_figure_to_back(figure2);

        // Assert
        then().should_be_in_back(figure2);
    }
}