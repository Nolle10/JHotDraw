package org.jhotdraw.acceptancetest;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import java.awt.event.ActionEvent;

public class GivenSomeState extends Stage<GivenSomeState> {

    @ProvidedScenarioState
    ActionEvent evt;

    public GivenSomeState I_have_opened_the_uri_chooser() {

        evt = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Open...");

        return this;

    }
}
