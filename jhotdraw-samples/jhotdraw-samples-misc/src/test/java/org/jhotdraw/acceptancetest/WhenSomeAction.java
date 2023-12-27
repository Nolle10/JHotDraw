package org.jhotdraw.acceptancetest;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.api.app.View;
import org.jhotdraw.app.action.file.OpenFileAction;
import org.jhotdraw.samples.svg.SVGView;

import java.awt.event.ActionEvent;

public class WhenSomeAction extends Stage<WhenSomeAction> {

    @ExpectedScenarioState
    ActionEvent event;

    @ProvidedScenarioState
    View view;

    OpenFileAction openFileAction;

    public Stage<WhenSomeAction> I_open_a_png(){

        event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Open...");

        try{
            openFileAction.actionPerformed(event);
            view = new SVGView();
            view.setTitle("Hello World! This is the view!");
            return this;
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }

        return this;
    }
}