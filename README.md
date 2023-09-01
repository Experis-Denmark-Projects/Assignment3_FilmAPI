## Assignment 3 - FilmAPI

## Description
This project serves the purpose of demonstrating a simple Java Spring Web application with data stored in a postgres database regarding movies, characters in movies and movie franchises.
For this project the two movie franchises "Marvel Cinematic Universe" and "DC Extended Universe" were used as context data.
Therefore, by reaching certain endpooints in the web application the user is able to get information from the database. 
The instruction section explains how to interact with the web application.  

## Project Structure 
This section presents the overall structure of the project. The web application uses rest controllers to receive HTTP requests from a user.
Therefore, the directory of the project has a seperate controller for characters, movies and franchises. 
The controllers follows a CRUD structure which means that the controller has endpoints for creating, reading, updating and deleting entities. 
Each of these controllers also have a number of dependencies.
These dependencies are used to invoke service methods which handles the business logic of the application. 
The service methods are invoked inside of the methods in the controllers. These methods are all annotated with different endpoint mappings.
This is how endpoints retrieves different HTTP responses. 

When a method in from the service is invoked it uses its repository dependency to access information from the postgres database.
Then the information from the database is retrived as an instance of its respective model class. For example, when accessing a character with a specific id in the databse
it is retrieved as a class model called "Character". However, this model is then converted to a "Data Transfer Object" (DTO) which is used in some of the HTTP responses. 
This conversion from a model to its respective DTO model class is handled through a seperate interface or abstract class which is annotated as a "Mapper".
Then in some methods the DTO is sent in the HTTP response. 

To summarize, the web application functions by a user sending a HTTP request which is received by a controller that then
uses a service which uses a repository to get access the database and creates a model instance corresponding to the data in the database
which the service returns to the controller and lastly the mapper converts the model to a DTO 
which may be used in the HTTP response message sent back to the user.

## Project Setup
To follow these instructions PGAdmin must be installed.
1. Clone the repository from the main branch.
2. Open the project in a Java editor.

## Run Instrictions - Spring Boot Application
This project uses Swagger/openAPI to interact with the Spring Web Application. 
To run the program follow the instructions below:
1. Navigate to the file called "FilmApiApplication" in the directory: "src/main/java/experis.filmapi/" then right click and press run.
2. Then open a browser and copy thus url:"http://localhost:8080/swagger-ui/index.html#/" into the browser.
3. Select a method to execute

## Authors
Name: Sigurd Schelde Andersen
E-Mail: sigurdschelde@gmail.com

Name: Alexander Jonstrup
E-Mail: Alexander.Jonstrup@outlook.com
