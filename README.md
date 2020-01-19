# The  Recipe Store

This was my personal solo project assigned by QA Consulting due on the 20th of January.

## Index

[Requirements](#requirements)

[Solution](#solution)

[Architecture](#architecture)

[Testing](#testing)
* [Surefire Report](#surefire)

[Deployment](#deployment)
* [Technology used](#technology)
    
[Front End Design](#frontend)
* [Initial Wireframe Design](#wireframe)
* [Final Design](#final)

[Future Possibilities](#future)

[Authors](#author)

<a name="requirements"></a>
## Requirements

To create an OOP-based application with utilisation of supporting tools, methodologies and technologies that encapsulate all core modules covered during training. This includes a Trello board, at least 2 tables in a relational database, a functional backend, an integrated RESTful API and a CI/CD solution to deploy in a non-local environment. This must be controlled via source control and tested to at least 80% coverage.

<a name="solution"></a>
### Solution

I decided to create a recipe store application where you can save your recipes with a list of ingredients, personal categories, a rating and a few other details. You can add and remove categories and ingredients from already saved recipes as well as edit any saved field.
The recipe has a many to many relationship with both ingredients and categories, this is functional and can be seen through the front end.

<a name="architecture"></a>
## Architecture

<img align="left" width ="300" height = "300" src="http://https://github.com/Alex-creating/Project-1-Recipe-Store-/tree/devBranch/Documents/database.png">

The diagram above shows both my initial and final structure for the tables in my database. I managed to stick to my initial design and keep all aspects of the recipes, ingredients and categories. The recipe table is the main focus of this project so the ingredients and categories were designed to fit around the requirements of the recipe.

<img align="left" width ="300" height = "300" src="http://https://github.com/Alex-creating/Project-1-Recipe-Store-/tree/devBranch/Documents/Structure.png">

The above diagram shows the flow of the program, without specific classes. The database used was a H2 database, which was suitable due to its speed of starting up and shutting down. The RESTful API was coded with Java using the Eclipse IDE along with Spring Tool Suite 4. The front end contained HTML, CSS and JavaScript and was coded using Visual Studio Code.

<a name="testing"></a>
## Testing

Testing of this program included using JUnit, Selenium and Mockito, along with SonarQube to help idenfity and remove code smells to aid refactoring.

<a name="surefire"></a>
### Surefire Report

[Surefire Report](http://https://github.com/Alex-creating/Project-1-Recipe-Store-/tree/devBranch/Documents/surefire-report.html)

Test coverage from JUnit testing on Eclipse is 85%, SonarQube line coverage is at 82%, with 0 bugs, vunerabilities or code smells.

<a name="deployment"></a>
## Deployment 

I used Jenkins to build, test and deploy my project. Upon every push to my Git repository Jenkins would automatically begin this process and update my project. The application has been deployed using an Amazon Web Service (AWS) virtual machine (VM).


![CI](https://i.imgur.com/dANrjMY.png)

<a name="technology"></a>
### Technology used

H2 Database - Database
Java - Source Code
Jenkins - CI Server
Maven - Dependency Management
JUnit, Selenium, SonarQube - Testing
Surefire - Test Report
GitHub - Version Control System
Trello - Project Tracking
Amazon Web Service - Live Environment

<a name="frontend"></a>
## Front End Design

<a name="wireframe"></a>
### Initial Wireframe Design

Below shows the initial design of the front page.

<img align="left" width ="700" height = "300" src="http://https://github.com/Alex-creating/Project-1-Recipe-Store-/tree/devBranch/Documents/Wireframe.png">

<a name="final"></a>
### FInal Design

Below shows the final result of the front page.

<img align="left" width ="700" height = "300" src="http://https://github.com/Alex-creating/Project-1-Recipe-Store-/tree/devBranch/Documents/FrontPage.png">

<a name="future"></a>
## Future Possibilities

I would like to add several new features to make the application more versatile. A login feature would expand the amount of people who could use the application and open up the possibility of downloading other users recipes and share your own.
I would also like an option to upload an image alongside your recipe to make the application more personal for a user.
Finally, the addition of a shopping list feature would make the app more useful in everyday life. Selecting recipes and collecting their ingredients into a shopping list is something I would like to implement in the future.

<a name="author"></a>
## Authors
Alexander Russo



