package com.buechler.spring.vaadin.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.buechler.spring.vaadin.MainUI;
import com.buechler.spring.vaadin.MyNavigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = View2.NAME, ui = MainUI.class)
public class View2 extends VerticalLayout implements View {

  public static final String NAME = "view-name-2";

  @Autowired
  private MyNavigator navigator;

  public View2() {

    addComponent(new Label("This is view " + NAME));

    /*
     * We use our own injected navigator. Another possible way would be:
     * UI.getCurrent().getNavigator().navigateTo(View1.NAME)
     */
    addComponent(new Button("Go to view 1", e -> navigator.navigateTo(View1.NAME)));

  }

  @Override
  public void enter(ViewChangeEvent event) {}

}
