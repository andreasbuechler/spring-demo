package com.buechler.spring.data.vaadin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;

import com.buechler.spring.data.entities.Person;
import com.buechler.spring.data.repositories.PersonRepository;
import com.buechler.spring.data.vaadin.views.PersonEditView;
import com.buechler.spring.data.vaadin.views.PersonSearchView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
@Title("Vaadin Spring Data Example")
public class MainUI extends UI {

  @Autowired
  private SpringNavigator navigator;

  @Autowired
  private PersonRepository personRepository;

  @Override
  protected void init(VaadinRequest request) {

    HorizontalLayout hlNavigation = new HorizontalLayout();
    hlNavigation.setSpacing(true);
    hlNavigation.addComponent(createNavButton("Search Persons", PersonSearchView.NAME));
    hlNavigation.addComponent(createNavButton("Edit Persons", PersonEditView.NAME));
    hlNavigation.addComponent(new Button("Add 10 Persons", this::addPersons));
    hlNavigation.addComponent(new Button("Remove all Persons", this::removePersons));

    Panel mainContent = new Panel();
    mainContent.setSizeFull();

    VerticalLayout mainLayout = new VerticalLayout(hlNavigation, mainContent);
    mainLayout.setSizeFull();
    mainLayout.setMargin(true);
    mainLayout.setSpacing(true);
    mainLayout.setExpandRatio(mainContent, 1);

    navigator.init(this, mainContent);
    navigator.navigateTo(PersonSearchView.NAME);

    setContent(mainLayout);

  }

  private Button createNavButton(String caption, String viewName) {
    return new Button(caption, e -> navigator.navigateTo(viewName));
  }

  private void addPersons(ClickEvent event) {

    List<Person> persons = new ArrayList<>();
    persons.add(new Person("Shirley", "Myers"));
    persons.add(new Person("Annie", "Cruz"));
    persons.add(new Person("Terry", "Alvarez"));
    persons.add(new Person("Wanda", "Murphy"));
    persons.add(new Person("Janet", "Richards"));
    persons.add(new Person("Linda", "Wilson"));
    persons.add(new Person("Cynthia", "Ray"));
    persons.add(new Person("Beverly", "Gutierrez"));
    persons.add(new Person("Louis", "Nelson"));
    persons.add(new Person("Linda", "Myers"));

    try {
      personRepository.save(persons);
    } catch (JpaSystemException e) {
      e.printStackTrace();
      Notification.show("Could not save entities!", Type.ERROR_MESSAGE);
    }

  }

  private void removePersons(ClickEvent event) {
    try {
      personRepository.deleteAll();
    } catch (JpaSystemException e) {
      e.printStackTrace();
      Notification.show("Could not delete all entities!", Type.ERROR_MESSAGE);
    }
  }

}
