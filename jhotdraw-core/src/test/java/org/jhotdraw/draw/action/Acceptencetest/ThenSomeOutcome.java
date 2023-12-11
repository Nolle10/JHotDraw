package org.jhotdraw.draw.action.Acceptencetest;

import com.tngtech.jgiven.Stage;
import static org.junit.Assert.assertSame;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;
import java.util.Set;

public class ThenSomeOutcome extends Stage<ThenSomeOutcome> {

    @ExpectedScenarioState
    DrawingView view;

    @ProvidedScenarioState
    Set<Figure> selectedFigure2;

    public void the_selected_figure_should_be_positioned_behind_the_other_figures() {
        Drawing drawing = view.getDrawing();
        Figure actualBackFigure = drawing.getFiguresFrontToBack().get(drawing.getFiguresFrontToBack().size() - 1);
        Figure expectedBackFigure = selectedFigure2.iterator().next();
        assertSame(expectedBackFigure, actualBackFigure);
    }

    public void the_selected_figure_should_be_positioned_in_front_of_the_other_figures() {
        Drawing drawing = view.getDrawing();
        Figure actualFrontFigure = drawing.getFiguresFrontToBack().get(0);
        Figure expectedFrontFigure = selectedFigure2.iterator().next();
        assertSame(expectedFrontFigure, actualFrontFigure);
    }
}
