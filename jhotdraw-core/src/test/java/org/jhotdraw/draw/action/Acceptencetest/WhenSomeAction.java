package org.jhotdraw.draw.action.Acceptencetest;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.action.arrange.BringToFrontAction;
import org.jhotdraw.draw.action.arrange.SendToBackAction;
import org.jhotdraw.draw.figure.Figure;
import java.util.LinkedHashSet;
import java.util.Set;

public class WhenSomeAction extends Stage<WhenSomeAction> {

    @ExpectedScenarioState
    DrawingView view;

    @ExpectedScenarioState
    Set<Figure> selectedFigure2;



    public Stage<WhenSomeAction> I_select_one_figure() {
        selectedFigure2 = new LinkedHashSet<>();
        selectedFigure2.add(view.getDrawing().getFiguresFrontToBack().get(1));
        return this;
    }

    public Stage<WhenSomeAction> I_choose_the_send_to_back_option() {
        SendToBackAction.sendToBack(view, selectedFigure2);
        return this;
    }

    public Stage<WhenSomeAction> I_choose_the_bring_to_front_option() {
        BringToFrontAction.bringToFront(view, selectedFigure2);
        return this;
    }
}