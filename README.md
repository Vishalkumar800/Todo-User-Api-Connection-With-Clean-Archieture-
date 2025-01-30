Node.js API with Android Integration
This project demonstrates a Node.js backend API integrated with an Android application using Retrofit, Hilt, and Jetpack Compose. The backend is built with Express.js, and the Android app communicates with it to perform CRUD operations on user data.

***Features
Backend (Node.js):
Create a new user with name, email, and username.
Fetch all users from the database.
Basic validation for empty fields and duplicate emails.

Android App:
Built with Jetpack Compose for modern UI.
Uses Retrofit for API calls.
Implements Hilt for dependency injection.
Follows MVVM architecture for clean and maintainable code.
Handles API responses using Kotlin Coroutines.

=============================

Technologies Used : === 

Backend:
Node.js, 
Express.js,
MongoDB

=========================
Android:
Kotlin ,
Jetpack Compose ,
Retrofit ,
Hilt (Dependency Injection) , 
Coroutines , 
ViewModel ,
Prerequisites ,

========Before running the project, ensure you have the following installed:=========

Node.js and npm (for the backend)

Android Studio (for the Android app)

MongoDB (or any database you're using)

Postman (for testing the API)
=============================================================

Project Structure==============

Backend (Node.js)

backend/
├── models/           # Database models (e.g., userModel.js)

├── routes/           # API routes (e.g., userRoutes.js)

├── app.js            # Express app setup

├── server.js         # Server entry point

└── .env              # Environment variables

=========================

Android App

android/

├── app/
│   ├── src/main/java/com/rach/firstnodejs/

│   │   ├── api/          # Retrofit API service and data sources

│   │   ├── di/           # Hilt dependency injection modules

│   │   ├── repository/   # Repository layer

│   │   ├── ui/           # Jetpack Compose UI components

│   │   ├── viewmodel/    # ViewModel for business logic

│   │   └── model/        # Data classes (e.g., User.kt)

│   └── build.gradle      # App-level dependencies

└── build.gradle          # Project-level settings


