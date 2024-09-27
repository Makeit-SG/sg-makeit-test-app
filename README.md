# Project Title

A web application built with Spring Boot and Flutter Web, with PostgreSQL as the database.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Setup](#setup)
- [Running the Application](#running-the-application)
- [Screenshots](#screenshots)

## Introduction

This project is a full-stack web application that leverages a Spring Boot backend, a Flutter Web frontend, and a PostgreSQL database. The application is designed to manage products, including viewing, adding, and editing product information.

## Features

- User Authentication (JWT based)
- Product Management (CRUD operations)
- Role-Based Access Control (RBAC)
- Responsive UI built with Flutter Web
- RESTful API backend using Spring Boot
- PostgreSQL database integration

## Tech Stack

**Backend:**
- Spring Boot
- Java
- PostgreSQL

**Frontend:**
- Flutter Web
- Dart

**Database:**
- PostgreSQL

## Setup

### Prerequisites

- Java 8 or later
- Flutter SDK
- PostgreSQL
- Git

### Backend Setup

1. Clone the repository:
    ```bash
    git clone (https://github.com/github-ankit/sg-makeit-test)
    ```
2. Navigate to the backend directory:
    ```bash
    cd demo/backend
    ```
3. Install the required dependencies and build the project:
    ```bash
    ./mvnw clean install
    ```
4. Update the `application.properties` with your PostgreSQL credentials:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/sample_db
    spring.datasource.username=postgres
    spring.datasource.password=admin
    ```
5. Run the Spring Boot application:
    ```bash
    ./mvnw spring-boot:run
    ```

### Frontend Setup

1. Navigate to the frontend directory:
    ```bash
    cd ../frontend
    ```
2. Install Flutter dependencies:
    ```bash
    flutter pub get
    ```
3. Run the Flutter Web application:
    ```bash
    flutter run -d chrome
    ```

## Running the Application

To run the application, ensure that the backend server is up and running, and then start the frontend Flutter Web application. Access the application via `http://localhost:8081` in your web browser.

To build the project :
- Android
   ```bash
    flutter build apk
    ```
- iOS
   ```bash
    flutter build ipa
    ```

## Screenshots

![Login Page](https://github.com/github-ankit/sg-makeit-test/blob/main/login.png)
*Description: Login page for user authentication.*

![Product List](https://github.com/github-ankit/sg-makeit-test/blob/main/product_list.png)
*Description: Product list view.*

![Product Details](https://github.com/github-ankit/sg-makeit-test/blob/main/product_details.png)
*Description: Product details view.*

![Add Product PAge](https://github.com/github-ankit/sg-makeit-test/blob/main/add_product.png)
*Description: Add product to add new products view.*


