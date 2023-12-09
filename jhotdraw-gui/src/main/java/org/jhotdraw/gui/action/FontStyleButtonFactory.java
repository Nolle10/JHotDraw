package org.jhotdraw.gui.action;

import org.jhotdraw.api.app.Disposable;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.action.AttributeToggler;
import org.jhotdraw.util.ActionUtil;
import org.jhotdraw.util.ResourceBundleUtil;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import static org.jhotdraw.draw.AttributeKeys.*;



public class FontStyleButtonFactory {
    private FontStyleButtonFactory() {
        throw new IllegalStateException("");
    }

    public enum FontStyle {
        BOLD,
        ITALIC,
        UNDERLINE,
        STRIKETHROUGH
    }

    public static JButton createFontStyleButton(DrawingEditor editor, FontStyle style) {
        return createFontStyleButton(editor,
                ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels"), style);
    }

    public static JButton createFontStyleButton(DrawingEditor editor,
                                                    ResourceBundleUtil labels, FontStyle style) {
        return createFontStyleButton(editor,
                labels, new LinkedList<>(), style);
    }

    public static JButton createFontStyleButton(DrawingEditor editor,
                                                    ResourceBundleUtil labels, java.util.List<Disposable> dsp, FontStyle style) {
        JButton btn;
        btn = new JButton();
        switch (style) {
            case BOLD:
                configureFontStyleBoldButton(btn, editor, labels);
                break;
            case ITALIC:
                configureFontStyleItalicButton(btn, editor, labels);
                break;
            case UNDERLINE:
                configureFontStyleUnderlineButton(btn, editor, labels);
                break;
            case STRIKETHROUGH:
                configureFontStyleStrikethroughButton(btn, editor, labels);
                break;
        }
        return btn;
    }

    private static void configureFontStyleBoldButton(JButton btn, DrawingEditor editor, ResourceBundleUtil labels) {
        labels.configureToolBarButton(btn, "attribute.fontStyle.bold");
        btn.setFocusable(false);
        AbstractAction a = new AttributeToggler<>(editor,
                FONT_BOLD, Boolean.TRUE, Boolean.FALSE,
                new StyledEditorKit.BoldAction());
        a.putValue(ActionUtil.UNDO_PRESENTATION_NAME_KEY, labels.getString("attribute.fontStyle.bold.text"));
        btn.addActionListener(a);
    }

    private static void configureFontStyleItalicButton(JButton btn, DrawingEditor editor, ResourceBundleUtil labels) {
        labels.configureToolBarButton(btn, "attribute.fontStyle.italic");
        btn.setFocusable(false);
        AbstractAction a = new AttributeToggler<>(editor,
                FONT_ITALIC, Boolean.TRUE, Boolean.FALSE,
                new StyledEditorKit.ItalicAction());
        a.putValue(ActionUtil.UNDO_PRESENTATION_NAME_KEY, labels.getString("attribute.fontStyle.italic.text"));
        btn.addActionListener(a);
    }

    private static void configureFontStyleUnderlineButton(JButton btn, DrawingEditor editor, ResourceBundleUtil labels) {
        labels.configureToolBarButton(btn, "attribute.fontStyle.underline");
        btn.setFocusable(false);
        AbstractAction a = new AttributeToggler<>(editor,
                FONT_UNDERLINE, Boolean.TRUE, Boolean.FALSE,
                new StyledEditorKit.UnderlineAction());
        a.putValue(ActionUtil.UNDO_PRESENTATION_NAME_KEY, labels.getString("attribute.fontStyle.underline.text"));
        btn.addActionListener(a);
    }

    private static void configureFontStyleStrikethroughButton(JButton btn, DrawingEditor editor, ResourceBundleUtil labels) {
        labels.configureToolBarButton(btn, "attribute.fontStyle.strikethrough");
        btn.setFocusable(false);
        AbstractAction a = new AttributeToggler<>(editor,
                FONT_STRIKETHROUGH, Boolean.TRUE, Boolean.FALSE,
                new StyledEditorKit.StyledTextAction("font-strikethrough") {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JEditorPane editor = getEditor(e);
                        if (editor != null) {
                            StyledEditorKit kit = getStyledEditorKit(editor);
                            MutableAttributeSet attr = kit.getInputAttributes();
                            boolean strikethrough = !StyleConstants.isStrikeThrough(attr);
                            SimpleAttributeSet sas = new SimpleAttributeSet();
                            StyleConstants.setStrikeThrough(sas, strikethrough);
                            setCharacterAttributes(editor, sas, false);
                        }
                    }
                });
        a.putValue(ActionUtil.UNDO_PRESENTATION_NAME_KEY, labels.getString("attribute.fontStyle.strikethrough.text"));
        btn.addActionListener(a);
    }
}
