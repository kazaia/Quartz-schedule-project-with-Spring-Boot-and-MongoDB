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

![](https://github.com/kazaia/Quartz-schedule-project-with-Spring-Boot-and-MongoDB/blob/master/Images/Q1.png)

Create Quartz.properties under src/main/resources/quartz.properties and add the following configuration:

![](https://github.com/kazaia/Quartz-schedule-project-with-Spring-Boot-and-MongoDB/blob/master/Images/Q2.png)

Then we create a Mongo database called quartz_demo, as following:

![](https://github.com/kazaia/Quartz-schedule-project-with-Spring-Boot-and-MongoDB/blob/master/Images/Q3.png)

## Creating a REST API to schedule Email Jobs dynamically in Quartz: 

We create the DTO classes (Data transfer object), that contains : email, body, subject, timeZone, dateTime … etc. 
We use proper annotations to avoid empty or null data: @NotEmpty/ @NotNull
Then we define the controller that schedule email jobs in Quartz.  Spring Boot has built-in support for Quartz. It automatically creates a Quartz Scheduler bean with the configuration that we supplied in the application.properties file. That’s why we could directly inject the Scheduler in the controller.
In the /scheduleEmail API, we first validate the request body. Then, build a JobDetail instance with a JobDataMap that contains the recipient email, subject, and body. The JobDetail that we create is of type EmailJob. Next, we Build a Trigger instance that defines when the Job should be executed.
Finally, we schedule the Job using scheduler.scheduleJob() API.

## Creating the Quartz Job to send emails:

Let’s now define the Job that sends the actual emails. Spring Boot provides a wrapper around Quartz Scheduler’s Job interface called QuartzJobBean. This allows to create Quartz Jobs as Spring beans where you can autowire other beans.
Let’s create our EmailJob by extending QuartzJobBean.

## MongoDB collections: 

When you now run the Spring Boot project with a running MongoDB instance, you will see the following collections created:
	quartz_calendars
	quartz_jobs
	quartz_locks
	quartz_schedulers
	quartz_triggers

![](https://github.com/kazaia/Quartz-schedule-project-with-Spring-Boot-and-MongoDB/blob/master/Images/Q4.png)

## Testing the REST API: 

![](https://github.com/kazaia/Quartz-schedule-project-with-Spring-Boot-and-MongoDB/blob/master/Images/Q5.png)

No success, if the dateTime is before current time: 


![](https://github.com/kazaia/Quartz-schedule-project-with-Spring-Boot-and-MongoDB/blob/master/Images/Q6.png)

The mail is successfully scheduled when tested with valid data: 

![](https://github.com/kazaia/Quartz-schedule-project-with-Spring-Boot-and-MongoDB/blob/master/Images/Q7.png)
