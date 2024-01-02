package org.jhotdraw.draw.action.arrange;

import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.action.AbstractSelectedAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public interface ArrangeService extends ActionListener {

    @Override
    void actionPerformed(ActionEvent e);
    String getID();
    AbstractSelectedAction createWithEditor(DrawingEditor editor);
}
