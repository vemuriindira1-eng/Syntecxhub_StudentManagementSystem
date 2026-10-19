# Student Management System

## Internship Details
- **Company:** Syntecxhub
- **Domain:** Java Programming
- **Project:** Student Management System
- **Language:** Java

## Project Description
The Student Management System is a console-based Java application used to manage student information. It allows users to add, view, search, update, and delete student records.

The application uses object-oriented programming concepts, an ArrayList for managing records, and file handling to store student information permanently.

## Features
- Add new student records
- View all student records
- Search students by ID
- Update student details
- Delete student records
- Validate student input
- Save records in a text file
- Load saved records when the program starts
- Menu-driven console interface

## Technologies Used
- Java
- ArrayList
- Classes and Objects
- Encapsulation
- File Handling
- Exception Handling
- Scanner for user input

## Project Structure
```text
Syntecxhub_StudentManagementSystem/
├── Student.java
├── StudentManagementSystem.java
├── README.md
└── data/
    └── students.txt
```

**Note:** `students.txt` is created automatically when student records are saved.

## Requirements
- Java Development Kit (JDK 11 or later)
- Command Prompt or VS Code terminal

## How to Run

1. Open the project folder in your terminal.
2. Compile the Java files:

   ```bash
   javac Student.java StudentManagementSystem.java
   ```

3. Run the application:

   ```bash
   java StudentManagementSystem
   ```

4. Select an option from the displayed menu.

## Data Storage
Student records are stored in `data/students.txt`. The application loads existing records when it starts and updates the file after adding, updating, or deleting records.

## Learning Outcomes
- Understanding Java classes and objects
- Using ArrayList to manage records
- Implementing CRUD operations
- Applying input validation
- Working with text files for persistent storage

## Author
Vemuri Indira

## Acknowledgment
This project is developed as part of the Syntecxhub Java Programming Internship.
