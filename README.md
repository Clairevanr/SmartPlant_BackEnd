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

(It is supposed to be available at this address but is not for some reason,
it takes to much time to launch and end up failing)


# Additional informations

The project is set up for Dockerisation (that was for an assignment in Majeur) and got Github workflows (for the same reason)
. Which is not relevant for you but had to be maintained.












# SmartPlant - README.md

## Introduction

SmartPlant is a plant management application that allows you to monitor and care for your indoor plants. The application uses sensors to measure temperature and humidity, sending this data to a backend system, which stores plant information and sensor readings in a database.

### Key Features:
- **Frontend**: User interface to manage plants.
- **Backend**: Server that receives sensor data and stores plant information.
- **Embedded**: Sensors that track environmental data and send it to the backend via Wi-Fi.

## Requirements

### Software Requirements:
1. **Python 3**: Ensure Python 3 is installed on your system.
2. **SASS**: Used to compile SCSS files into CSS.
    - Install SASS using the following command:
      npm install -g sass
3. **A Web Browser**: To access the web interface.

### Hardware Requirements:
- **Temperature and Humidity Sensors**: These sensors need to be connected to Wi-Fi to send data to the backend.
- **Local Server**: A device such as a Raspberry Pi or your computer to run the backend server.

## Installation

### Step 1: Clone the repository

First, clone the repository to your local machine:

git clone https://github.com/username/smartplant.git
cd smartplant

### Step 2: Set up the environment

Make sure Python 3 and SASS are installed on your system as outlined in the requirements section.

### Step 3: Run the backend server

The backend is a Python HTTP server that listens for incoming data from the sensors and stores it.

To start the server, run the following command:

./run_server.py

This will start the server on `http://localhost:8000`. You can change the port by specifying it when running the command:

./run_server.py 8080

### Step 4: Compile SCSS to CSS

To compile the SCSS files into CSS, use the provided script:

./compile_css.sh

This will continuously monitor changes to SCSS files and compile them into CSS automatically.

### Step 5: Access the Web Application

Once the server is running and the styles are compiled, you can access the application at:

http://localhost:8000/index.html

The application consists of several pages:
- **Home Page**: `index.html`
- **About Page**: `about.html`
- **Add Plant Page**: `addplant.html`
- **Plant List Page**: `listplants.html`

## How to Use

### Add a Plant

1. Go to the **Add Plant** page (`addplant.html`).
2. Fill in the plant name and select its type from the dropdown menu.
3. Submit the form, and the plant will be added to the backend.

### View and Manage Plants

- On the **Plant List** page (`listplants.html`), you can view all the plants you have added.
- You can click on a plant to view more details and delete it from the backend if needed.

### Sensor Data

The backend receives data from the sensors and stores it for each plant. Each plant has its own temperature and humidity readings, which can be displayed or used for alerts.

## File Structure

smartplant/
├── assets/
│   ├── img/              # Images for the website
│   └── stylesheets/      # Compiled CSS
├── integration/          # SCSS and CSS source files
│   ├── style.scss        # SCSS source
│   └── output.css        # Compiled CSS
├── assets/js/            # JavaScript for frontend functionality
│   ├── list-plant.js     # Script for plant list interactions
│   └── plantdisplay.js   # Script for displaying plant details
├── run_server.py         # Python server to handle backend requests
├── compile_css.sh        # Script to compile SCSS into CSS
└── README.md             # Project documentation

## Conclusion

SmartPlant is an easy-to-use application for managing indoor plants using real-time data from sensors. Whether you're a plant enthusiast or just looking to keep track of your plants, this application will help you maintain the optimal conditions for your plants.

For any questions or contributions, feel free to open an issue or submit a pull request.

Made by SmartPlant Team.
