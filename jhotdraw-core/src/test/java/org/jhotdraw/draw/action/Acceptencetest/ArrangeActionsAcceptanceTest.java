package org.jhotdraw.draw.action.Acceptencetest;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

public class ArrangeActionsAcceptanceTest extends ScenarioTest<GivenSomeState, WhenSomeAction, ThenSomeOutcome> {

    @Test
    public void sendingFigureToBack() {
        given().I_have_three_figures_in_my_drawing();
        when().I_select_one_figure().and().I_choose_the_send_to_back_option();
        then().the_selected_figure_should_be_positioned_behind_the_other_figures();
    }

    @Test
    public void bringingFigureToFront() {
        given().I_have_three_figures_in_my_drawing();
        when().I_select_one_figure().and().I_choose_the_bring_to_front_option();
        then().the_selected_figure_should_be_positioned_in_front_of_the_other_figures();
    }
}
