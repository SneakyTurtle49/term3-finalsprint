# term3-finalsprint
Overview

Welcome to the Gym Management System! This is a console-based Java application that allows users to manage a gym, its members, trainers, and workout classes. The system supports three roles:

Admin: Oversees everything. Can view/delete users and see revenue.

Trainer: Creates and manages workout classes.

Member: Views classes and purchases memberships.



How It Works

Users start by registering or logging in.

Based on your role, you see different options:

Admins manage users and see reports.

Trainers create/update/delete classes.

Members view classes and buy memberships.

All actions are saved to the database.



Class & Functionality Breakdown

User, Admin, Trainer, Member

Base user types. Admin, Trainer, and Member extend User.

UserService

Handles registration, login, hashing passwords, and user-related operations.

Membership, MembershipService, MembershipDAO

Manages membership creation, viewing user expenses, and calculating revenue.

WorkoutClass, WorkoutClassService, WorkoutClassDAO

Trainers manage classes. Members view them.

DatabaseConnection

Utility class for connecting to the PostgreSQL database.



How To Start & Use

Install Java (JDK 17+ recommended).

Clone the project from GitHub (see below).

Run the SQL script to set up the database (scripts.sql).

Run GymApp.java from your IDE or using:

mvn compile exec:java -Dexec.mainClass="org.keyin.GymApp"

  "mvn compile exec:java -Dexec.mainClass="org.keyin.GymApp""

Use the console menus to register, log in, and interact.

