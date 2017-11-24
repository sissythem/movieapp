# Movie App

## Introduction 

The purpose of this project is to create an application showing movies and TV shows to users, giving them the possibility to create reviews, to make favorites etc. The movies and shows presented in our application are extracted from the MovieDB API. In more detail, scheduled jobs are programmed in order to get upcoming and now playing movies as well as on the air and airing today shows from the API. Although this information is updated periodically, getting the latest movies and TV programs and removing stale information, the old films and shows are kept in our database under separate tables, in order to give to the user the possibility to find old films and shows.

Before proceeding with implementation details, it is useful to mention shortly here the requirements of our application, so as to give the reader a general idea of the functionality of this application. First of all, as already mentioned, scheduled jobs are required so as to get new movies/shows and update our database. In addition, this information should be presented to the users as categories, for example list of upcoming movies, giving the possibility of reviewing a film/show and creating list of favorites. Users can register in our application, navigate to search for movies, add comments/ratings and make favorites. Of course it is mandatory to ensure that user is authenticated before performing above actions.

## MySQL
In order to manage all the data about movies, shows and users we created tables to save their specific information and the relations between them. First of all, we created a MySQL database with MySQL Workbench 6.3 CE and we configured our schema named "gntmoviedb". Regarding to our database, the tables created are listed below:

* **Users:** Information about users, such as username, password etc
* **Movies:** Keeps information about new and old films
* **Shows:** Same logic with movies
* **Images:** All images from shows and movies are stored in this table
* **Movie_Favorites & Show_Favorites:** User's favorite films/shows
* **Movie_Reviews & Show_Reviews:** User's comments and ratings on films/shows
* **Genres:** Storage of all genres in the MovieDB API
* **Movie_Genres & Show_Genres:** Relationship between films/shows and genres (eg movie having a list of genres, list of movies of a specific genre)
* **Movie_Images & Show_Images:** Expresses the relationship between images and films/shows
* **Upcoming_Movies:** After every scheduled job, only upcoming movies are stored in this table. Same logic is followed for the tables **Now_Playing_Movies, On_The_Air_Shows and Air2day_Shows** as well.	

We will now explain some tables in more detail. We will describe below only the movie related tables, however the same logic can be applied to the relevant show tables.

In order to be able to keep all films stored in MySQL database, regardless if they are new or not, but also to keep the database scalable, we have decided to create separate tables for the categories upcoming and now playing movies, populated by our scheduler. This decision has been made due to the deletions required in those tables since they should be up to date so as to present correct information to users. Therefore, all new movies retrieved by the MovieDB API are stored to those tables but also to the movies table (by checking first for duplicates). In this way when an item is deleted from respective category, it is still kept in our database.

One other table that worths to be mentioned is the movie_genres. By examining the API calls results, we came to the conclusion that we needed a new table in order to keep stored the relation between movies and genres, since a movie can have more than one genre and of course a genre is related to many movies.

Finally, in order to avoid multiple Java classes for images, we decided to create a general table images, so as to keep photos from both movies and shows, and to store their association.

## Wildfly 9.0.2

In order to run the server properly and be able to use the .bat (or .sh) files under path wildfly-9.0.2.Final/bin a Java JDK must be set in system variables as JAVA_HOME = C:\java\jdk1.8.0_### and then add to the Path variable %JAVA_HOME%\bin. Moreover, in order to access the server's management page, a new management user must added to the server users by running the file: wildfly-9.0.2.Final\bin\add-user.bat

Wildfly does not have the MySQL Connector and EclipseLink library, so we decided to add them as modules. When adding a module to wildfly a module.xml file is required for the server to be able to load the the module. Below all server configurations are explicitly listed.

### MySQL Connector

The .jar(*mysql-connector-java-5.1.44-bin.jar*) of the MySQL driver should be added under path: wildfly-9.0.2.Final\modules\system\layers\base\com\mysql\main. Moreover, the *module.xml* file should be edited as follows:

    ```
      <?xml version="1.0" encoding="UTF-8"?>
      <module xmlns="urn:jboss:module:1.3" name="com.mysql">
        <resources>
          <resource-root path="mysql-connector-java-5.1.44-bin.jar"/>
        </resources>
        <dependencies>
          <module name="javax.api"/>
          <module name="javax.transaction.api"/>
        </dependencies>
      </module>
    ```
    
### EclipseLink

Apart from MySQL driver, EclipseLink library should also be added as module to the server. This is performed by pasting the *eclipselink-2.7.0.jar* file under the path *wildfly-9.0.2.Final\modules\system\layers\base\org\eclipse\persistence\main*. In addition, the *module.xml* file should be modified:
    
    ```
	    <?xml version="1.0" encoding="UTF-8"?>
		<!-- Represents the EclipseLink module  -->
		<module xmlns="urn:jboss:module:1.3" name="org.eclipse.persistence">
		    <resources>
		        <resource-root path="jipijapa-eclipselink-1.0.1.Final.jar"/>
		        <resource-root path="eclipselink-2.7.0.jar">
		        <filter>
		                <exclude path="javax/**" />
		        </filter>
				</resource-root>
		    </resources>

		    <dependencies>
		        <module name="asm.asm"/>
		        <module name="javax.api"/>
		        <module name="javax.annotation.api"/>
		        <module name="javax.enterprise.api"/>
		        <module name="javax.persistence.api"/>
		        <module name="javax.transaction.api"/>
		        <module name="javax.validation.api"/>
		        <module name="javax.xml.bind.api"/>
		        <module name="org.antlr"/>
		        <module name="org.apache.commons.collections"/>
		        <module name="org.dom4j"/>
		        <module name="org.javassist"/>
		        <module name="org.jboss.as.jpa.spi"/>
		        <module name="org.jboss.logging"/>
		        <module name="org.jboss.vfs"/>
				<module name="javax.ws.rs.api"/> 
		    </dependencies>
		</module>
	```

### Configuration

The last configuration that should be performed is to add the database url connection in the *standalone.xml*, which can be found under path: *wildfly-9.0.2.Final/standalone/configuration/standalone.xml*. Therefore, a new datasource should be added alongside the relevant credentials and the MySQL driver as we show in below code:

  ```
    <subsystem xmlns="urn:jboss:domain:datasources:3.0">
      <datasources>
        <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="false" use-java-context="true">
          <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>
          <driver>h2</driver>
          <security>
          <user-name>sa</user-name>
          <password>sa</password>
          </security>
          </datasource>
          <datasource jta="true" jndi-name="java:/jdbc/GntMoviesDS" pool-name="GntMoviesDS" enabled="true" use-ccm="true">
          <connection-url>jdbc:mysql://localhost:3306/gntmoviedb</connection-url>
          <driver>mysql</driver>
          <security>
          <user-name>username</user-name>
          <password>password</password>
          </security>
          </datasource>
        <drivers>
          <driver name="h2" module="com.h2database.h2">
          <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
          </driver>
          <driver name="mysql" module="com.mysql">
          <driver-class>com.mysql.jdbc.Driver</driver-class>
          </driver>
        </drivers>
      </datasources>
     </subsystem>
  ```

The default datasource ExampleDS can be removed or disabled (enabled=false). When this is done the default datasource in the server must also be changed to an existing datasource.

From:

```
<default-bindings context-service="java:jboss/ee/concurrency/context/default" datasource="java:jboss/datasources/ExampleDS" managed-executor-service="java:jboss/ee/concurrency/executor/default" managed-scheduled-executor-service="java:jboss/ee/concurrency/scheduler/default" managed-thread-factory="java:jboss/ee/concurrency/factory/default"/>
```

To:

```
<default-bindings context-service="java:jboss/ee/concurrency/context/default" datasource="java:/jdbc/GntMoviesDS" managed-executor-service="java:jboss/ee/concurrency/executor/default" managed-scheduled-executor-service="java:jboss/ee/concurrency/scheduler/default" managed-thread-factory="java:jboss/ee/concurrency/factory/default"/>
```

## EAR Project

### Create EAR Project

In this section, the hole procedure in creating the project is presented, however it is important to firstly present our project's structure. There is an external project containing our three sub-projects: the EAR, EJB and Web projects. The way of creating an EAR Project in Eclipse, which will automatically create the ejb and web projects as well, is to go to the add new project choice in the menu, select Maven project and from the archetypes to choose the below(wildfly-javaee7-webapp-ear-blank-archetype):

![Maven archetype for EAR Project](/images/earProjectCreation.JPG)

### Replace Web Project

In this way three projects will be created: an EAR, an EJB and a WEB. Since Vaadin will be used in the UI, web project should be deleted and a new Maven project should be created by selecting Vaadin archetype as per below screenshot:

![Maven archetype for Vaadin Project](/images/vaadinProject.JPG)

This project should be added it to the EAR, by going to the EAR's Deployment Descriptor and by right clicking on Modules and selecting to add another project. Therefore now we have the external project which contains the EAR, EJB and Vaadin projects.

### Configurations in EAR

Since both archetypes use Java7, we can change Java version from project's facets (Java is already selected and we only change the version from 1.7 to 1.8). In addition, at the path *src\main\application\META-INF* an XML file is created, which is named by the project's name followed by "-ds", in our case *GntMovies-ds.xml* where the credentials of the database should be changed.

Finally, in the pom.xml file of the external project it is necessary to also change the Java version from 1.7 to 1.8 as shown in below image:

![Change Java version to pom.xml - External Project](/images/pomJavaVersion.JPG)

## EJB Project

### JPA

To begin with, since our project uses EclipseLink 2.5x library, it is mandatory to removed the Hibernate dependency from project's pom.xml file, otherwise runtime errors will occur. Moreover, in persistence.xml file, all entities should be listed. After having created our EJB project, we have configured it to a JPA Project and established the connection between the project and the schema using the database name and password. Then, by using the JPA tools that now appear in project's menu, we were able to create our entities from the tables of MySQL database.

While the automatic entity creation is very useful, we changed several things afterwards. First of all,  we specified an association one to one between shows and air today shows/on the air shows and between movie and upcoming movie/now playing movie respectively, so as to avoid duplicates. In addition, a ManyToMany relationship is established between genres and movies/shows entities, making unnecessary to create entities for tables movie_genres and show_genres since items are matched automatically in those tables. Finally, movie_images and show_images were not created as entities as well and the relationships with images and movies/shows is also automatically stored in the database. 

In summary, although the entities creation was made automatically in Eclipse, the JPA relations should be given extra care, since they are not always produced correctly. After having studied on TutorialsPoint site all the JPA relations, we performed some changes to the produced relationships, for example the @ManyToMany relationship between movies and genres.

Some examples of the JPA Relationship Annotations:

```
	@OneToOne
	@JoinColumn(name="showId")
	private Show show;

	@OneToMany(mappedBy = "show", fetch = FetchType.LAZY)
	private List<ShowImage> showImages;
	\end{lstlisting}
	\begin{lstlisting}
	@ManyToOne
	@JoinColumn(name="show_id")
	private Show show;


	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name = "show_genres", 
		joinColumns = {@JoinColumn(name = "showId", referencedColumnName = "id") }, 
		inverseJoinColumns = {@JoinColumn(name = "genreId", referencedColumnName = "id") }
	)
	private Set<Genre> genres;

	@ManyToMany(fetch=FetchType.LAZY)
	private List<Show> shows;	
```

### DAO

These classes can be found in the package com.gnt.movies.dao, and there is an interface and an implementation for each entity mentioned in the previous section. In the interfaces, all the functions that we have implemented in the files DaoImpl are included , so as to be able to create different implementations if needed in the future. In the implementation files the functions implemented, are related to simple actions on our entities, such as add an object, update an item or delete it in the database, as well as find an item based on different criteria each time. Since these functions are not pure business logic, but simple queries in the database, we have decided to include them in Dao classes and leave all business logic to be implemented in our beans. 

### DTO

Some of our entities have many fields and collections and it is inefficient to transfer these big objects to the UI, therefore we decided to create some smaller classes related to users, movies and shows, containing only some necessary fields. For example, if we want to show a list with some new movies to the user, there is no need to have movie objects with all their fields and collections and to send them to the UI. Instead, we will send only the important information such as the movie title, the rating, the main image etc. These three classes are included in the package com.gnt.movies.dto.

### EJB
These classes can be found in the com.gnt.movies.beans package which contains all our beans and therefore all the business logic of our application. Below we present this logic in more detail.

* **UserBean:** What worths to be mentioned here is the functions related to user registration and login. While the logic here is pretty simple, we should mention that we have implemented different login functions based on different cases. The first time the users enter the application, they should provide their username and password. The validation of these credentials is implemented with the function with signature boolean loginUser(String username, String password). If user leaves the application, but the session is still valid and he returns on time, the credentials won't be asked for second time. Instead the relevant token will be checked and user will be transfered to the home page of the application. Finally, the registration function checks separately the username and email provided by the user and if they are not found in our database, we register the user and redirect him to the login page before entering the application.

* **GenreBean:** When our scheduled job starts (SchedulerBean, update() function), the first thing we do is to update our genres table in case the MovieDB API has new kinds of movies or shows. Therefore, two calls are made to the API, one for movie and one for show genres. All items retrieved are saved in a HashSet and using the genreBean we call the addGenres function which will check if a genre already exists before adding it in the database, in order to avoid duplicates.

* **MovieBean & ShowBean:** Each time we add a new movie retrieved by the MovieDB API, it is necessary to check if the movie already exists in our database. Therefore, the function getMovie is call, which either creates a new movie or returns the existing one. The addition of the new movie is implemented by the addNewMovie function, which takes and upcoming or now playing movie from the API, makes use of some details providing (createMovieFromAPI function) but also makes a new call to add all missing details and updates the movie object (updateMovieWithDetails). Finally, the movie is added alongside with all the related images and genres (relationship between movies and genres is stored in the movie_genres table as already mentioned). Since the same logic is followed in the ShowBean as well, we will not make an extensive report for this class.

* **UpcomingMovieBean, NowPlayingMovieBean, OnTheAirShowBean & Air2dayShowBean:** Since the logic is pretty much the same for all those beans, we will refer only to UpcomingMovieBean class. In the SchedulerBean we call the checkUpcomingMovie function in order to find out if the specific upcoming movie is already stored in our database. If it concerns a completely new movie we first create a new UpcomingMovie object, then we call the getMovie function described in detail in the above bullet and then we create a correlation between the movie and the upcoming movie objects. Finally, the latter will be also stored in the database.

The remaining bean classes (related to our entities) implement the requirements related to user's favorite movies and shows as well as the relevant reviews. The business logic here is quite simple, since it requires some queries in the database to get for example a list with the favorite movies of a user.

### SchedulerBean

In order to fetch data from the MovieDB Api we created a Scheduler in the beans section to update periodically the genres, the upcoming and now playing movies, the TV airing today and on the air shows once per day so the user will be able to see the latest changes about shows and movies. When the scheduler is triggered, the movies and shows beans check in parallel if some of the new data already exists in the database and add only the non existing. The old now playing movies, air today shows etc are removed from the tables but not from the Movies and Shows tables where we keep both new and the old ones.

The above business logic is implemented by using a stateless bean and the annotation @Schedule before the function which implements the required scheduled jobs. Moreover, the data processing is quite complicate therefore for each item we  start a separate transaction by using the annotation @TransactionAttribute. 

### API Calls- Timer

As already mentioned, one important requirement of our application is to populate our tables with movies and shows from the MovieDB API. In order to retrieve these data, necessary classes for the deserialization were created (or existing entities were used, such as movies, shows, genres etc). The classes performing the calls to the API are in the package com.gnt.movies.utilities while the classes for the deserialization of JSON objects are under package com.gnt.movies.theMovieDB (while our entities can be found in com.gnt.movies.entities as already mentioned in the JPA section).

The MovieDB API applies a limitation of 40 requests every 10 seconds per IP Address either by being burstable to 40 in a single second, or as an average of 4 requests/second. The timer will reset 10 seconds from your first request within the current 10 second "bucket". This means that if you trigger the limit you will have to wait up to 9 seconds before the timer resets but depending where you are within the 10 second window, it could be the very next second.

In order to prevent triggering the APIs limit we created a ConcurrentHashMap to act as a bucket of the current temporal active requests. After sending a request to the API we put to the bucket an entry with its unique key. Each entry has its own timer and after 10 seconds it will remove itself from the bucket. All this implementation can be found in the ApiClient and ApiEntry classes. 

In the ApiCalls class in com.gnt.movies.utilities package there are the methods that make the calls to the MovieDB Api in order to retrieve the genres, latest upcoming and now playing movies, on the air and airing today shows. To increase performance, the calls run in parallel as new threads. The  results are returned as HashSets in order to avoid duplicates due to inconsistencies of the API from call to call.

### Converters

While trying to serialize/deserialize objects to/from the database, we faced several issues of type mismatch between Java and SQL. One example is related to date types. In Java, instead of using Date type, we prefered the LocalDate, however JPA 2.1 was released before Java 8 and does not support LocalDate and LocalDateTime Classes. Apart from Date, boolean fields in Java should be first transformed in Byte type, before getting stored in the database.

These issues can be overcome by the use of Converters, by changing for example LocalDate to Date and LocalDateTime to Timestamp (similarly for boolean/byte) . The converter classes should be added to the persistence.xml alongside with the entity classes in order the JPA to be able to make the conversion. 

## EJB Project Testing

In this section, we will refer to all our tests, both unit and integration, as well as the configurations made related to Arquillian. After studying the book\cite{ejb3}, we came to the conclusion that it is important to set our integration test in a way that the additions/deletions/updates in the database will be simulated by using server's database and project's one.

### JUnit tests
To begin with, under the path src/test of the EJB project, we have created all our packages related to testing. The package for our JUnit tests is the com.gnt.test.junit. Some small tests are created there, in order to examine the way calls are executing and how beans behave.

### Arquillian

The package containing the code for integration test is com.gnt.test.arquillian. Before explaining our code, we think that it is worth mentioning the configurations we made so as to use server's database for testing instead of ours.

When EJB Project was created, automatically under test/resources path some files were created, necessary for the integration test with arquillian. But before proceeding with these files, we have to configure the pom.xml file with all the required dependences as well as with adding a profile of the server in order to be used for testing. Below we include our pom.xml, mentioning only the test scope dependences and providing the profiles used for saving space:

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<parent>
		<artifactId>GntMovies</artifactId>
		<groupId>com.gnt.movies</groupId>
		<version>1</version>
	</parent>

	<artifactId>GntMovies-ejb</artifactId>
	<packaging>ejb</packaging>

	<name>GntMovies: EJB Module</name>

	<url>http://wildfly.org</url>
	<licenses>
		<license>
			<name>Apache License, Version 2.0</name>
			<distribution>repo</distribution>
			<url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
		</license>
	</licenses>

	<dependencies>
		<!-- Test scope dependencies -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.12</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-all</artifactId>
			<version>1.10.19</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.jboss.arquillian.junit</groupId>
			<artifactId>arquillian-junit-container</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.jboss.arquillian.protocol</groupId>
			<artifactId>arquillian-protocol-servlet</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.jboss</groupId>
			<artifactId>jboss-vfs</artifactId>
			<version>3.1.0.Final</version>
			<scope>provided</scope>
		</dependency>
	</dependencies>

	<build>
		<finalName>${project.artifactId}</finalName>
		<plugins>
			<plugin>
				<artifactId>maven-ejb-plugin</artifactId>
				<version>${version.ejb.plugin}</version>
				<configuration>
					<!-- Tell Maven we are using EJB 3.1 -->
					<ejbVersion>3.1</ejbVersion>
				</configuration>
			</plugin>
		</plugins>
	</build>

	<profiles>
		<profile>
			<!-- The default profile skips all tests, though you can tune it to run 
				just unit tests based on a custom pattern -->
			<!-- Seperate profiles are provided for running all tests, including Arquillian 
				tests that execute in the specified container -->
			<id>default</id>
			<activation>
				<activeByDefault>true</activeByDefault>
			</activation>
			<build>
				<plugins>
					<plugin>
						<artifactId>maven-surefire-plugin</artifactId>
						<version>${version.surefire.plugin}</version>
						<configuration>
							<skip>true</skip>
						</configuration>
					</plugin>
				</plugins>
			</build>
		</profile>
		<profile>
			<id>widlfly-remote</id>
			<activation>
				<activeByDefault>true</activeByDefault>
			</activation>
			<dependencies>
				<dependency>
					<groupId>org.wildfly</groupId>
					<artifactId>wildfly-arquillian-container-remote</artifactId>
					<scope>test</scope>
					<exclusions>
						<exclusion>
							<groupId>sun.jdk</groupId>
							<artifactId>jconsole</artifactId>
						</exclusion>
					</exclusions>
				</dependency>
			</dependencies>
		</profile>
	</profiles>
</project>

```

We have used the profile wildfly-remote for the integration test. This choice has to be mentioned in arquillian.xml file which is presented below:

![The arquillian.xml file](/images/arquillian.JPG)

As already mentioned, we didn't want to use the project's database for testing, therefore it is required to add two xml files in order to use server's database. Below these files are presented, the test-ds.xml and test-persistence.xml. Please note that these files should be used in the integration test at the @Deployment function, as we will mention in a while.

![The test-ds.xml file](/images/test-ds.JPG)

![The test-persistence.xml file](/images/test-persistence.JPG)

After having mentioned all the configurations required for the tests, we will now explain the MyDeployment.java file and as an example one test class, the MovieTester.java, since the same logic is followed to the remaining files as well. First of all, in the first file we have two static functions creating a war and a jar file respectively, using the above xml files (highlighted on the image) as presented below:

![The MyDeployment.java file](/images/deployment.JPG)

In order to test the API calls we have to explicitly refer the three required libraries in the static WebArchive getWar method.

We will now explain the code in the file MovieTester.java. To begin with the class for the integration test needs the @RunWith(Arquillian.class) above the class name. Moreover, it is compulsory to have the createDeployment function with the annotation @Deployment. In this function we create the war file by using the MyDeployment class, before continuing with the code. Then, we initialize what we need with the @Before annotation and finally we write our test code in the functions with the @Test annotation.

## RESTful Services

Before starting the description of the Vaadin Project, it is important to mention that in our web project we have also included the Restful Services, used by the Android Application (under the package com.gnt.movies.GntMovies_web.rest). First of all, we have added this facet to the project (under project facets) but please note that the rest version should be changed to 2.0. 

Under the rest utils package, we have implemented the authentication requirement in the AuthenticationToken.java. In this class, we have four functions performing all the logic related to the JWT. To begin with, the issueToken function takes as parameter the username of the user that has requested to sign in our application and taking into account the current time, issues and returns a new token to the user, starting his session in the application. Moreover, the is isTokenValid function calls the validateToken() in order to check a given token by performing checks for three use cases: token is null, token is not signed or token is expired(by using the isExpired function).

Under the rest package, there are the rest services implementations where the client through the basic HTTP operations can perform CRUD functionality from different platforms. For example, in the MovieRest class the client has the ability to get movie details by title or id, add or remove a movie from favorites and add or remove a movie review. For these operations the user's authentication token is required in order to have access to the functionality we mentioned before. The same logic is followed for the remaining rest services classes where the user can get the list with the upcoming movies, the airing today shows, the genres etc. UserRest class deserves a special mention, because it contains the register, login and login with token functionality. When the user logs in gets a token for authentication which can be used later for login without having to write username and password again only if the token is still valid. 

## Vaadin Project

## Android Project
