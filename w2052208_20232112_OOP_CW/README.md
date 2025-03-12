README for Ticket Simulation System

Introduction

The Ticket Simulation System is a web-based application designed to simulate a real-time ticketing environment. It provides features to configure ticket parameters, manage tickets dynamically, and track actions performed by vendors and customers through an interactive user interface. The system is implemented using React.js for the frontend and Spring Boot for the backend.

Setup Instructions

Prerequisites
Ensure the following software is installed on your system:
•	Java: Version 17 or higher
•	Maven: Version 3.6 or higher
•	Node.js: Version 18 or higher
•	npm: Version 8 or higher (comes with Node.js)
•	Git: Version control for cloning the repository

Frontend Setup (React.js)
open cmd
Navigate to the frontend directory:
cd "Your frontend path"
Install dependencies:
npm install
Start the frontend development server:
npm start

The frontend application will run at http://localhost:3000


Backend Setup (Spring Boot)
open folder in intellij IDE 
Run the app


Usage Instructions

Configuring and Starting the System

Open the application in your web browser at http://localhost:3000.
Navigate to the Configuration Form section:
Enter parameters such as:
  •    Total Tickets: The maximum number of tickets available.
  •    Ticket Release Rate: The rate at which tickets are added by vendors.
  •    Customer Retrieval Rate: The rate at which customers can buy tickets.
  •    Max Ticket Capacity: The maximum tickets allowed in the system at any point.
  •    Number of Customers: Number of active customers in the simulation.
  •    Number of Vendors: Number of active vendors in the simulation.
       
       Click Update Configuration to save the settings.
       Click Get Configurations to get the configurations which previously saved

Use the Control Panel to manage the simulation:
 •  Click Start to initiate the ticketing simulation.
 •  Click Stop to halt the simulation.

Explanation of UI Controls
  •	Configuration Form:
     o	Update and retrieve configuration settings using provided fields and buttons.
  •	Ticket Display:
     o	View the current number of tickets available in real-time.
  •	Control Panel:
     o	Start or stop the simulation as needed.
  •	Log Display:
     o	Monitor real-time logs for actions like ticket retrieval or addition, along with the actor    and ticket number.

Notes
   •Backend endpoints are pre-configured for the frontend. Ensure both frontend and backend servers are running simultaneously.
   •For troubleshooting, check the browser console and backend logs for detailed error messages.




