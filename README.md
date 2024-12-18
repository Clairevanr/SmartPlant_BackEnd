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
So there will be issues building it on a UNIX like machine
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
