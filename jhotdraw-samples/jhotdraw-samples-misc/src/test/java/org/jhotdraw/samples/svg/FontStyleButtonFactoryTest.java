package org.jhotdraw.samples.svg;

import org.jhotdraw.draw.*;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.gui.action.FontStyleButtonFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;

import static org.jhotdraw.draw.AttributeKeys.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class FontStyleButtonFactoryTest {

    @Test
    public void fontStyleButtonAction() {
        Map<FontStyleButtonFactory.FontStyle, AttributeKey<Boolean>> parameters = new HashMap<>();
        parameters.put(FontStyleButtonFactory.FontStyle.BOLD, FONT_BOLD);
        parameters.put(FontStyleButtonFactory.FontStyle.ITALIC, FONT_ITALIC);
        parameters.put(FontStyleButtonFactory.FontStyle.UNDERLINE, FONT_UNDERLINE);
        parameters.put(FontStyleButtonFactory.FontStyle.STRIKETHROUGH, FONT_STRIKETHROUGH);

        for (Map.Entry<FontStyleButtonFactory.FontStyle, AttributeKey<Boolean>> entry: parameters.entrySet()) {
            // Mock everything
            DrawingEditor editor = Mockito.mock(DefaultDrawingEditor.class);
            DrawingView view = Mockito.mock(DefaultDrawingView.class);
            Drawing drawing = Mockito.mock(DefaultDrawing.class);
            Figure figure = Mockito.mock(Figure.class);

            // Create a button
            JButton button = FontStyleButtonFactory.createFontStyleButton(editor, entry.getKey());

            // Inject mocks in all getters
            when(editor.getActiveView()).thenReturn(view);
            when(view.getDrawing()).thenReturn(drawing);
            Set<Figure> returnSet = new HashSet<>();
            returnSet.add(figure);
            when(view.getSelectedFigures()).thenReturn(returnSet);

            // Press the button
            button.doClick();

            // Assert that the attribute key was retrieved by the attribute toggle
            verify(figure, times(1)).get(entry.getValue());
        }
    }

    @Test
    public void fontStyleButtonTooltip() {
        Map<FontStyleButtonFactory.FontStyle, String> labels = new HashMap<>();
        labels.put(FontStyleButtonFactory.FontStyle.BOLD, "Bold");
        labels.put(FontStyleButtonFactory.FontStyle.ITALIC, "Italic");
        labels.put(FontStyleButtonFactory.FontStyle.UNDERLINE, "Underline");
        labels.put(FontStyleButtonFactory.FontStyle.STRIKETHROUGH, "Strikethrough");

        for (Map.Entry<FontStyleButtonFactory.FontStyle, String> entry: labels.entrySet()) {
            DrawingEditor editor = new DefaultDrawingEditor();
            JButton button = FontStyleButtonFactory.createFontStyleButton(editor, entry.getKey());
            assertEquals(entry.getValue(), button.getToolTipText());
        }
    }
}
