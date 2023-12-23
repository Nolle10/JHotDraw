package org.jhotdraw.draw.action.Acceptencetest;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.DefaultDrawing;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.EllipseFigure;
import org.jhotdraw.draw.figure.Figure;

public class GivenSomeState extends Stage<GivenSomeState> {

    Figure figure1 = new EllipseFigure();
    Figure figure2 = new EllipseFigure();
    Figure figure3 = new EllipseFigure();
    Drawing drawing = new DefaultDrawing();

    @ProvidedScenarioState
    DrawingView view = new DefaultDrawingView();

    public GivenSomeState I_have_tree_figures_in_my_drawing() {
        drawing.add(figure3);
        drawing.add(figure2);
        drawing.add(figure1);
        view.setDrawing(drawing);
        return this;
    }
}
