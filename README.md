# DroneServiceDeliviring
## Table of contents
* [General info](#general-info)
* [PreRequisites](#prerequisites)
* [Technologies](#technologies)
* [Setup](#setup)
* [Assumptions](#assumptions)

## General info
```
This project is for new technology that is destined to be a disruptive force in the field of transportation 
which is the drone.Just as the mobile phone allowed developing countries to leapfrog older technologies for 
personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.
Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult
access.This one will be used to transport medication.
The service should allow:
- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone; 
- checking available drones for loading;
- check drone battery level for a given drone;
- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is **below 25%**;
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.
```

## PreRequisites
```
Installation of Java Sdk preferbly Java 11 to run the project
Install Maven in your machine to use the Maven Commands to run the application
```
## Technologies
```
This Project is created with:
* Java 11
* Maven
* Tomcat Server
* SpringBoot Framework with Spring Data JPA
* H2 Database(in memory)
* REST API
```
	
## Setup
```
To run this project locally, clone it from GitHub on (https://github.com/ahmedyusef91/drones-service-delivering)

To run the Project
$ mvn spring-boot:run

To clean the project
$ mvn clean

To test the project tests scenarios
$ mvn test

To create JarFile /Build the project
$ mvn clean install   or mvn package
```

## Assumptions
```
The following assumptions were made made during development
* The relationship between drone and medication is a one-to-many relationship
* The drones will have unique serial numbers.
* The drones data initialized by data.sql file
* The drone that will be Loaded is one with >= 25% battery Level and also is on IDLE State
* No drone will be loaded with medication that is greater than its maximum weight it can carry.
```

