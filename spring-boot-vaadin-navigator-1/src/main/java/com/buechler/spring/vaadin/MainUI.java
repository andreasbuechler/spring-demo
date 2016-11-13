package com.buechler.spring.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.buechler.spring.vaadin.views.View1;
import com.buechler.spring.vaadin.views.View2;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
@Title("Vaadin Spring Navigator Example 1")
public class MainUI extends UI {

  @Autowired
  private SpringViewProvider viewProvider;

  @Override
  protected void init(VaadinRequest request) {

    Button btnView1 = new Button("View 1", e -> getNavigator().navigateTo(View1.NAME));
    Button btnView2 = new Button("View 2", e -> getNavigator().navigateTo(View2.NAME));
    HorizontalLayout hlBtns = new HorizontalLayout(btnView1, btnView2);

    Panel mainContent = new Panel();

    VerticalLayout mainLayout = new VerticalLayout(hlBtns, mainContent);
    mainLayout.setExpandRatio(mainContent, 1);

    Navigator navigator = new Navigator(this, mainContent);
    navigator.addProvider(viewProvider);
    setNavigator(navigator);
    navigator.navigateTo(View1.NAME);

    setContent(mainLayout);

  }

}
