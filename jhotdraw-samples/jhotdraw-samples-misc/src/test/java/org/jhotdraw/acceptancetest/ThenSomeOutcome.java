package org.jhotdraw.acceptancetest;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.api.app.View;
import org.jhotdraw.samples.svg.SVGView;
import org.junit.Assert;

public class ThenSomeOutcome extends Stage<ThenSomeOutcome> {

    @ExpectedScenarioState
    View view;
    View newView;


    public ThenSomeOutcome the_selected_png_is_shown(){

        newView = new SVGView();
        newView.setTitle("Hello World! This is the view!");

        view = new SVGView();
        view.setTitle("Hello World! This is the view!");

        System.out.println(view.getTitle());
        Assert.assertEquals(newView.getTitle(), view.getTitle());

        return this;
    }
}
