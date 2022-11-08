package org.jhotdraw.draw.tool;

import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.figure.Figure;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CreationToolTest {
    @Test
    public void testThatTemplateIsCloned() {
        // Arrange
        Figure template = mock(Figure.class);
        Figure clone = mock(Figure.class);
        when(template.clone()).thenReturn(clone);
        CreationTool tool = new CreationTool(template);
        DrawingEditor editor = mock(DrawingEditor.class);
        tool.activate(editor);

        // Act
        Figure created = tool.createFigure();

        // Assert
        assertEquals(clone, created);
    }
}
