# spring-boot-vaadin-navigator-1
This example shows the usage of the `SpringViewProvider` which fetches all the
views from the Spring application context. Each view only needs to be
annotated with `@SpringView` to be detected by Vaadin Spring.

Spring-Boot is going to show a WARN message in the console because the standard
Navigator is used instead of the `SpringNavigator`.

## run this app
To run this app execute `mvn clean spring-boot:run` and navigate to
http://localhost:8080
