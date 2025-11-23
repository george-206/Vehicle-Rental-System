# Vehicle-Rental-System
A simple Java console-based application that allows admins to manage vehicles and customers to rent them.
The program supports file handling, status management, rental receipts, and basic validation
# Overview
This program is built entirely with core Java, without frameworks, focusing on:
OOP principles
File read/write handling
Menu-driven logic
Date parsing using LocalDate
Basic validation and error checking
The goal is to simulate a simple real-life rental workflow between an Admin and a Customer

# Features

## Admin Features
Add a new vehicle
View all available vehicles
View vehicles currently in maintenance
Change vehicle status (available / maintenance)

## Customer Features
Search for vehicle by model
Request to rent a vehicle
Calculate rental charge
Generate a rental receipt (ID, dates, cost)

## Data Storage
The system reads all vehicles from:  Vehicles.txt
Each line represents one vehicle in this format:  vehicleID,makeModel,type,status,dailyRate

## How to Run
Follow these steps to compile and run the application from your command line:
Save Files: Ensure all Java classes (Program.java, Vehicle.java, Admin.java, Customer.java, Rental.java) are saved in the same directory.

Create Data File: In that same directory, create an empty text file named Vehicles.txt.

Compile: Open your terminal or command prompt and run the following command:  
###### javac Program.java Vehicle.java Admin.java Customer.java Rental.java
Run: Execute the compiled program:
###### java Program
Start Interaction: The system will greet you and prompt you to log in as an Admin (1) or a User (2).
