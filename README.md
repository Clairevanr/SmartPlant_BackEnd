## The Backend was let in a separated Git Repository to be able to deploy it on CleverCloud with the configurations we studied in class.
# Smart Plant

You are tired of seing all of your plants dies from your lack of consistency when watering them? 

With SmartPlants, sensors allows you to control the soil's humidity to tell you exactly when you need to water each of your plants !

This part is dedicated to the backend app of the SmartPlant Project.

# Requirements :
-JDK 21 at least (to use gradle)  
-Gradle (You can use the Gradle Wrapper coming with the project)

# Powered by :
-Gradle
-Junit
-Spring
-H2


/!\ CAUTION
The project was hosted on Windows to help dealing with some errors (as suggested by the instructor).
So there might be issues building it on a UNIX like machine
Try this command if available :

    dos2unix gradlew

# How to build the project :

## Clone the Repository: Clone the project to your local machine:
    git clone https://github.com/Clairevanr/SmartPlant.git 
#
    cd Automacorp_save

## Build the Project: Run the following command:
    ./gradlew build


## Run tests:
    ./gradlew test

## Package the project as a JAR:
    ./gradlew jar

## Clean the build directory:
    ./gradlew clean


When running the application, you can find it on :  http://localhost:8080

# Features

The model relies on: 

- PlantEntity describing the plants of the user.

- PlantType describing for each type of plant the recommendations when it comes to
humidity and temperature. Those presented here were for test purpose and may not be accurate.
Plus every plant from the same type won't always require the same amounts,but I chose this model to 
simplify.

- SensorEntity describing the sensors attached to the plants, their types and values.

- SensorType (enum) describing the type of the sensor.

All of this with their DAO, DTO, Mappers, etc... Plus the Unit tests related

We also implemented different functionalities such as :

-   Listing all the plants, sensors, plant types.
- Creating new instances of each
- Updating them
- Deleting them
- Researching them by id (Plants, Sensors) or names (PlantTypes)


All of these were tested through unit tests and Swagger UI.
The application is deployed on Clevercloud at the address :

https://app-3861dd22-bcbc-49fb-a17d-9e71a5501d1b.cleverapps.io/

(This is the address designed by default by CleverCloud for test purpose)

https://smartplant.cleverapps.io/

(This is the chosen domain name)

There are some issues with the backend such as the link between entities. We managed to compensate it in the FrontEnd but failed when it came to Android app. The issues are more detailed in the related README.


# Additional informations

The project is set up for Dockerisation (that was for an assignment in Majeur) and got Github workflows (for the same reason)
. Which is not relevant for you but had to be maintained.












