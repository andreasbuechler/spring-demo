package com.buechler.spring.data.vaadin.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.buechler.spring.data.entities.Person;
import com.buechler.spring.data.repositories.PersonRepository;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = PersonSearchView.NAME)
public class PersonSearchView extends VerticalLayout implements View {

  public static final String NAME = "person-search";

  private Grid grid;
  private BeanItemContainer<Person> container;

  @Autowired
  private PersonRepository personRepository;

  public PersonSearchView() {
    createView();
  }

  private void createView() {

    setSizeFull();
    setMargin(true);
    setSpacing(true);

    createControlFields();

    grid = new Grid("Persons", container = new BeanItemContainer<>(Person.class));
    grid.setSizeFull();
    grid.setColumns("firstName", "lastName");
    addComponent(grid);
    setExpandRatio(grid, 1);

  }

  private void createControlFields() {

    HorizontalLayout hl = new HorizontalLayout();
    hl.setSpacing(true);

    hl.addComponent(new Button("Load all", e -> setPersons(personRepository.findAll())));
    hl.addComponent(new Button("Load all order by first name asc",
        e -> setPersons(personRepository.findAllByOrderByFirstNameAsc())));
    hl.addComponent(new Button("Load first 2 order by last name asc",
        e -> setPersons(personRepository.findFirst2ByOrderByLastNameAsc())));

    TextField findByLastName = new TextField("Find by containing lastname order by lastname ASC");
    findByLastName.setNullRepresentation("");
    findByLastName.addTextChangeListener(e -> setPersons(
        personRepository.findByLastNameContainingIgnoreCaseOrderByLastNameAsc(e.getText())));
    hl.addComponent(findByLastName);

    TextField findByFirstName =
        new TextField("Find by firstname (exact match) order by firstname ASC");
    findByFirstName.setNullRepresentation("");
    findByFirstName.addValueChangeListener(
        e -> setPersons(personRepository.findByFirstNameOrderByFirstNameAsc(e.getProperty()
                                                                             .getValue()
                                                                             .toString())));
    hl.addComponent(findByFirstName);

    hl.forEach(e -> hl.setComponentAlignment(e, Alignment.BOTTOM_CENTER));

    addComponent(hl);

  }

  public void setPersons(List<Person> persons) {
    container.removeAllItems();
    container.addAll(persons);
  }

  @Override
  public void enter(ViewChangeEvent event) {}

}
