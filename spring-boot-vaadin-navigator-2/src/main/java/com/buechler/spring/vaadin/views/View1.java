package com.buechler.spring.vaadin.views;

import com.buechler.spring.vaadin.MainUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = View1.NAME, ui = MainUI.class)
public class View1 extends VerticalLayout implements View {

  public static final String NAME = "view-name-1";

  public View1() {
    addComponent(new Label("This is view " + NAME));
  }

  @Override
  public void enter(ViewChangeEvent event) {}

}
