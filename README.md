# Education organizations REST server 


## Technologies used in the project

* JDK 1.8 (must be pre-installed)
* Gradle for build automation.
* Spring Data JPA - for persistence
* Spring Boot 
* Spring Security 
* Spring MVC
* Rest Assured Mock MVC - for testing
* H2 - In-memory database for data storage
* Lombok - for replacing bolierplate code such as geters/setters to single annotation


## Data storage
On application start tables are created in H2 in-memory database from *shema.sql* and data loaded to the tables from *data.sql*
So, we can fetch organizations from out the box

## Security 
Current application uses Basic authentification for securing REST endpoint.
Available user roles are below:

* READ_ONLY - allows access only GET methods endpoints
* MODIFICATION - allows access POST, PATCH endpoints as well
* ADMIN - allows access DELETE endpoints as well

## Unit testing
RestAssuredMVC - is used for unit testing of points below:
* check that only granted users have access to REST - endpoints
* verify that exception mapping works correctly
* check that data was not modified

## Application execution
Currently application is packaged as .war according to task description
But commenting *apply plugin: 'war'* line in *build.gradle* file allows to run the application using embedded tomcat
  






 


  
 


