package org.jhotdraw.draw.undo;

import org.jhotdraw.draw.figure.TextHolderFigure;
import org.jhotdraw.draw.tool.TextAreaCreationTool;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class TextAreaCreationToolTest {
    @Test
    public void testThatUndoableEditIsMade() {
        TextHolderFigure editedFigure = mock(TextHolderFigure.class);
        TextHolderFigure prototype = mock(TextHolderFigure.class);
        String oldText = "old";
        String newText = "new";
        TextAreaCreationTool tool = new TextAreaCreationTool(prototype);
        assertNotNull(tool.createUndoableEndEdit(editedFigure, oldText, newText));
    }
}
