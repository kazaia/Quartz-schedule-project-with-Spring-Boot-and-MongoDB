# Quartz-schedule-project-with-Spring-Boot-and-MongoDB

## Quartz

Quartz is a job scheduling library that can be integrated into a wide variety of Java applications. Quartz is generally used for enterprise class applications to support process workflow, system management actions and to provide timely services within the applications.
Quartz Jobs can be persisted into a database, or a cache, or in-memory. In this project we will use MongoDB to persist all the jobs and other job-related data.

## Tools and prerequisites:

Before we begin, open a terminal and run the following commands to ensure that you have valid versions of Java and Maven installed:

![](https://github.com/kazaia/Quartz-schedule-project-with-Spring-Boot-and-MongoDB/blob/master/Images/01.png)

![](https://github.com/kazaia/Quartz-schedule-project-with-Spring-Boot-and-MongoDB/blob/master/Images/02.png)


This project is using MongoDB as the internal persistent store. We install and use MongoDB as a job store and Robo 3T as client. 
To test the REST API, we install Postman.

## Creating the Spring Boot application 

Spring Boot offers several conveniences for working with the Quartz scheduler, including the spring-boot-starter-quartz “Starter”. 
We Bootstrap a Spring Boot application by using Spring Initializer. To do this, we will have to visit the Spring Initializer at: https://start.spring.io/ and choose your Build, Spring Boot Version and platform. Also, we need to provide a Group, Artifact and required dependencies to run the application.
In our case: 
•	Enter quartz-demo in the Artifact field.
•	Add Web, JPA, MongoDB, Quartz, and Mail in the dependencies section.
•	Click Generate Project to generate and download the project.

## Project dependencies: 

For maven artifacts, add the following repository definition to your pom.xml:

<repositories>
    <repository>
        <id>michaelklishin</id>
        <url>https://dl.bintray.com/michaelklishin/maven/</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.novemberain</groupId>
    <artifactId>quartz-mongodb</artifactId>
    <version>2.1.0</version>
</dependency>


For Quartz, add this dependency: 

<dependencies>
	<dependency>
		<groupId>org.quartz-scheduler</groupId>
	        <artifactId>quartz</artifactId>
		<version>2.3.0</version>
	</dependency>


## Configuring MongoDB database, Quartz Scheduler, and Mail Sender

Let’s configure Quartz Scheduler, MongoDB database, and Spring Mail. MongoDB database will be used for storing Quartz Jobs, and Spring Mail will be used to send emails.
Open src/main/resources/application.yml file and add the following properties:


