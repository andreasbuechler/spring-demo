# spring-boot-vaadin-navigator-2
This example shows the usage of the `SpringNavigator` which should be used
when using Vaadin Spring. The class `MyNavigator` extends from `SpringNavigator`
and is autowired into the MainView.
`MyNavigator` fetches all the views from the Spring application context. Each
view only needs to be annotated with `@SpringView` to be detected by Vaadin Spring.

## run this app
To run this app execute `mvn clean spring-boot:run` and navigate to
http://localhost:8080
