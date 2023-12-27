package org.jhotdraw.acceptancetest;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.api.app.View;
import org.jhotdraw.samples.svg.SVGView;
import org.junit.Assert;
import org.junit.Assert.*;

public class ThenSomeOutcome extends Stage<ThenSomeOutcome> {

    @ExpectedScenarioState
    View view;

    View newView;

    View falseView;

    public ThenSomeOutcome the_selected_png_is_shown(){

        newView = new SVGView();
        newView.setTitle("Hello World! This is the view!");

        falseView = new SVGView();
        falseView.setTitle("This is a wrong view!");

        Assert.assertEquals(newView.getTitle(), view.getTitle());
        Assert.assertNotEquals(falseView.getTitle(), view.getTitle());

        return this;
    }
}
