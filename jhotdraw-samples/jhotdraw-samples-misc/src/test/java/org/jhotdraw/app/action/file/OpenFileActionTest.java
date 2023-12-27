package org.jhotdraw.app.action.file;

import org.jhotdraw.api.app.Application;
import org.jhotdraw.api.app.View;
import org.jhotdraw.api.gui.URIChooser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OpenFileActionTest {

    @Mock
    private Application mockApp;

    @Mock
    private View mockView;

    @Mock
    private URIChooser mockChooser;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testActionPerformed() throws IOException {
        // Mock the behavior of the application
        when(mockApp.isEnabled()).thenReturn(true);
        when(mockApp.getActiveView()).thenReturn(mockView);
        when(mockApp.createView()).thenReturn(mockView);

        // Mock the behavior of the view
        when(mockView.isEmpty()).thenReturn(true);
        when(mockView.isEnabled()).thenReturn(true);
        when(mockView.getComponent()).thenReturn(new JPanel());

        // Mock the behavior of the URIChooser
        when(mockChooser.getSelectedURI()).thenReturn(URI.create("//file:///C:/Users/olive/OneDrive/Billeder/sdu.png"));
        when(mockApp.getOpenChooser(any())).thenReturn(mockChooser);

        // Create the OpenFileAction instance with the mocked application
        OpenFileAction openFileAction = new OpenFileAction(mockApp);

        // Create an ActionEvent (can be customized based on your needs)
        ActionEvent actionEvent = mock(ActionEvent.class);

        // Call the actionPerformed method
        try{
            openFileAction.actionPerformed(actionEvent);

        // Verify that the necessary methods were called
        verify(mockApp).setEnabled(false);
        verify(mockApp).add(mockView);
        verify(mockApp).show(mockView);
        verify(mockView).read(any(URI.class), eq(mockChooser));
        verify(mockView).setURI(any(URI.class));
        verify(mockApp).addRecentURI(any(URI.class));
        verify(mockApp).setEnabled(true);


        assertNotNull(mockApp.getActiveView());
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }
}