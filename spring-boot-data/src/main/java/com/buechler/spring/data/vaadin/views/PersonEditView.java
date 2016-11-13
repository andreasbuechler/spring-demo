package com.buechler.spring.data.vaadin.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;

import com.buechler.spring.data.entities.Person;
import com.buechler.spring.data.repositories.PersonRepository;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent;

@SpringView(name = PersonEditView.NAME)
public class PersonEditView extends VerticalLayout implements View {

  public static final String NAME = "person-edit";

  private PersonRepository personRepository;

  private Grid grid;
  private BeanItemContainer<Person> container;

  private TextField firstName;
  private TextField lastName;
  private BeanFieldGroup<Person> fieldGroup = new BeanFieldGroup<>(Person.class);

  @Autowired
  public PersonEditView(PersonRepository personRepository) {
    this.personRepository = personRepository;
    createView();
  }

  private void createView() {

    setSizeFull();
    setMargin(true);
    setSpacing(true);

    createHeaderButtons();
    createGrid();
    createDetailsPanel();

    container.addAll(personRepository.findAll());

  }

  private void createHeaderButtons() {
    Button btnAdd = new Button("Add new Person", this::addNewClicked);
    addComponent(btnAdd);
    setComponentAlignment(btnAdd, Alignment.MIDDLE_RIGHT);
  }

  private void createGrid() {
    GeneratedPropertyContainer gpc =
        new GeneratedPropertyContainer(container = new BeanItemContainer<>(Person.class));
    gpc.addGeneratedProperty("delete", new PropertyValueGenerator<String>() {

      @Override
      public String getValue(Item item, Object itemId, Object propertyId) {
        return "Delete";
      }

      @Override
      public Class<String> getType() {
        return String.class;
      }
    });
    grid = new Grid("Persons in Database", gpc);
    grid.setSizeFull();
    grid.setColumns("firstName", "lastName", "delete");
    grid.getColumn("delete")
        .setRenderer(new ButtonRenderer(this::deleteClicked));
    grid.addItemClickListener(e -> fieldGroup.setItemDataSource(e.getItem()));
    addComponent(grid);
    setExpandRatio(grid, 1);
  }

  private void createDetailsPanel() {
    firstName = new TextField("First Name");
    firstName.setNullRepresentation("");
    lastName = new TextField("Last Name");
    lastName.setNullRepresentation("");
    fieldGroup.bindMemberFields(this);

    Button btnSave = new Button("Save", this::saveClicked);

    addComponent(new Panel("Person Details", new FormLayout(firstName, lastName, btnSave)));
  }

  private void addNewClicked(ClickEvent event) {
    fieldGroup.setItemDataSource(new Person());
  }

  private void saveClicked(ClickEvent event) {
    try {
      fieldGroup.commit();
      Person p = fieldGroup.getItemDataSource()
                           .getBean();
      boolean personIsNew = p.isNew();
      try {
        Person persistedPerson = personRepository.save(p);
        if (personIsNew) {
          container.addBean(persistedPerson);
          grid.select(persistedPerson);
          grid.scrollTo(persistedPerson);
        }
      } catch (JpaSystemException e) {
        e.printStackTrace();
        Notification.show("Could not save entity!", Type.ERROR_MESSAGE);
      }
    } catch (CommitException e) {
      e.printStackTrace();
      Notification.show("Not all fields are valid.", Type.ERROR_MESSAGE);
    }
  }

  private void deleteClicked(RendererClickEvent event) {
    try {
      personRepository.delete((Person) event.getItemId());
      container.removeItem(event.getItemId());
    } catch (JpaSystemException e) {
      e.printStackTrace();
      Notification.show("Entity could not be deleted!", Type.ERROR_MESSAGE);
    }
  }

  @Override
  public void enter(ViewChangeEvent event) {}

}
