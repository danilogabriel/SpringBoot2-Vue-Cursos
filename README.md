# Simple App Using VueJS as Front End and Spring Boot 2 as the Server and to provide REST Service

## What is the Use of This Repo
This App is a Simple VueJS and Springboot 2 App which uses

## What Does this Application do

The Application displays a list of training courses, and on clicking on "detalles" it displays more details about the course

## Prerequites to Run the Application

### Install NodeJS 

Refer https://nodejs.org/en/ to install NodeJS

### Install Vue Cli 

Install Vue Cli Node Package Globally using the following Command.

```bash
npm install -g @vue/cli
```

### Install maven

Install Maven and Ensure IDE is pointing to Right Maven folder

Also Ensure maven is set as a path variable to that maven commands can be run easily

Refer https://maven.apache.org/ for maven installation

## Steps to Run the Application

Clone the repo into local

Open the client folder and install the npm packages using the following commands

```bash
cd client
npm install
```

Go Back to the Parent Project Folder and build the package using the following commands

```bash
cd ..
mvn clean package   ( or  mvn clean package -DskipTests=true )
```

Go to server folder and start the Application using the following commands

```bash
cd server
mvn spring-boot:run
```

The Application runs on **localhost:8080** and the application runs in a embedded container in local

## Folder Structure

**client** : This has the Client Code implemented using Vue JS

**server** : This has the Springboot code

**pom.xml** : This is multimodule pom. This pom in turn executes the pom within the client and the server folders

## Application Design

### VueJS

#### Http Library (https://www.npmjs.com/package/axios)

**axios** library is used to make http calls

#### Routing

### Springboot 2



## References

**Springboot** : Refer to https://spring.io/guides/gs/rest-service/ to build REST service using Springboot

**VueJS** : https://vuejs.org/v2/guide/

**vue cli** : https://cli.vuejs.org/guide/

**axios** : https://www.npmjs.com/package/axios

**vue bootstrap** : https://bootstrap-vue.js.org/docs

**vue router** : https://router.vuejs.org/guide/
