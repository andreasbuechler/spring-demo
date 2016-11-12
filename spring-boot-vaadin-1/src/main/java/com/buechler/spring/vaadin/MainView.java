package com.buechler.spring.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI // deploy it to the root
@Theme("valo")
@Title("Vaadin with spring")
public class MainView extends UI {

  @Override
  protected void init(VaadinRequest request) {

    VerticalLayout mainLayout = new VerticalLayout();
    mainLayout.setMargin(true);
    mainLayout.setSpacing(true);

    mainLayout.addComponent(
        new Button("Click me", e -> mainLayout.addComponent(new Label("Button clicked"))));

    setContent(mainLayout);

  }

}
