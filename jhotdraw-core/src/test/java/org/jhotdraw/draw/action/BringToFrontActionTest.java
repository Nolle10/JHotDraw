package org.jhotdraw.draw.action;

import org.jhotdraw.draw.DefaultDrawing;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.action.arrange.BringToFrontAction;
import org.jhotdraw.draw.figure.EllipseFigure;
import org.jhotdraw.draw.figure.Figure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BringToFrontActionTest {
    private DrawingView view;
    private DrawingView view1;
    private DrawingView view2;

    private Drawing drawing;
    private Drawing drawing1;
    private Drawing drawing2;

    private Figure figure;
    private Figure figure1;
    private Figure figure2;
    private Figure figure3;
    private Figure figure4;
    private Figure figure5;
    private Figure figure6;
    private Figure figure7;

    @BeforeEach
    void setUp() {
        view = mock(DrawingView.class);
        drawing = new DefaultDrawing();
        figure = new EllipseFigure();
        drawing.add(figure);
        when(view.getDrawing()).thenReturn(drawing);

        //other drawing
        view1 = mock(DrawingView.class);
        drawing1 = new DefaultDrawing();
        figure1 = new EllipseFigure();
        figure2 = new EllipseFigure();
        figure3 = new EllipseFigure();
        drawing1.add(figure3);
        drawing1.add(figure2);
        drawing1.add(figure1);
        when(view1.getDrawing()).thenReturn(drawing1);

        //other drawing
        view2 = mock(DrawingView.class);
        drawing2 = new DefaultDrawing();
        figure4 = new EllipseFigure();
        figure5 = new EllipseFigure();
        figure6 = new EllipseFigure();
        figure7 = new EllipseFigure();
        drawing2.add(figure7);
        drawing2.add(figure6);
        drawing2.add(figure5);
        drawing2.add(figure4);
        when(view2.getDrawing()).thenReturn(drawing2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void actionPerformed() {
    }

    @Test
    void bringToFront() {
    }

    @Test
    void oneFigure(){
        Set<Figure> selectedFigure = new LinkedHashSet<>();
        selectedFigure.add(figure);
        when(view.getSelectedFigures()).thenReturn(selectedFigure);

        testBringToFront(view, drawing, selectedFigure, figure, figure);
    }

    @Test
    void alreadyArranged(){
        Set<Figure> selectedFigure1 = new LinkedHashSet<>();
        selectedFigure1.add(figure1);
        when(view1.getSelectedFigures()).thenReturn(selectedFigure1);

        testBringToFront(view1, drawing1, selectedFigure1, figure1, figure1);
    }

    @Test
    void oneFigureSelected(){
        Set<Figure>selectedFigure2 = new LinkedHashSet<>();
        selectedFigure2.add(figure2);
        when(view1.getSelectedFigures()).thenReturn(selectedFigure2);

        testBringToFront(view1, drawing1, selectedFigure2, figure1, figure2);
    }

    @Test
    void allFiguresSelected(){
        Set<Figure> selectedFigures = new LinkedHashSet<>();
        selectedFigures.add(figure3);
        selectedFigures.add(figure2);
        selectedFigures.add(figure1);
        when(view1.getSelectedFigures()).thenReturn(selectedFigures);

        testBringToFront(view1, drawing1, selectedFigures, figure1, figure1);
    }

    @Test
    void multipleFiguresSelected(){
        Set<Figure> multipleSelectedFigures = new LinkedHashSet<>();
        multipleSelectedFigures.add(figure5);
        multipleSelectedFigures.add(figure6);
        when(view2.getSelectedFigures()).thenReturn(multipleSelectedFigures);

        BringToFrontAction.bringToFront(view2, multipleSelectedFigures);

        List<Figure> figuresFromTheDrawing = new ArrayList<>(drawing2.getFiguresFrontToBack());
        int indexForFigure = figuresFromTheDrawing.indexOf(multipleSelectedFigures.iterator().next());

        //shows that figure 5 and 6 now are in the front after the action
        for (Figure f : multipleSelectedFigures) {
            assertSame(f, figuresFromTheDrawing.get(indexForFigure));
            indexForFigure++;
        }

    }

    @Test
    void getID() {
    }

    private void testBringToFront(DrawingView view, Drawing drawing, Set<Figure> selectedFigures, Figure beforeArrange, Figure afterArrange){
        Figure frontFigure = drawing.getFiguresFrontToBack().get(0);
        assertSame(beforeArrange, frontFigure);

        BringToFrontAction.bringToFront(view, selectedFigures);

        Figure frontFigureNew = drawing.getFiguresFrontToBack().get(0);
        assertSame(afterArrange, frontFigureNew);
    }
}