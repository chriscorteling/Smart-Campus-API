# Smart Campus API

## 📌 Overview

The Smart Campus API is a RESTful web service developed using Java and Maven.
It simulates a smart campus environment by managing resources such as rooms, sensors, and sensor readings.

The API allows clients to:

* Discover available endpoints
* Retrieve room and sensor data
* Submit and view sensor readings

This project demonstrates the use of REST principles, HTTP methods, and structured API design.

---

## ⚙️ Technologies Used

* Java
* Maven
* Jakarta REST (JAX-RS)
* Apache Tomcat (for deployment)

---

## 🚀 How to Build and Run

### 1. Clone the repository

```bash
git clone https://github.com/chriscorteling/Smart-Campus-API.git
cd Smart-Campus-API
```

### 2. Build the project

```bash
mvn clean package
```

### 3. Deploy the application

* Copy the generated `.war` file from:

  ```
  target/Smart-Campus-API-1.0-SNAPSHOT.war
  ```
* Place it inside the `webapps` folder of Apache Tomcat
* Start Tomcat server

### 4. Access the API

```
http://localhost:8080/Smart-Campus-API/api/
```

---

## 📡 API Endpoints

### 🔹 1. Discover API

```bash
curl -X GET http://localhost:8080/Smart-Campus-API/api/discovery
```

---

### 🔹 2. Get all rooms

```bash
curl -X GET http://localhost:8080/Smart-Campus-API/api/rooms
```

---

### 🔹 3. Get all sensors

```bash
curl -X GET http://localhost:8080/Smart-Campus-API/api/sensors
```

---

### 🔹 4. Get sensor readings

```bash
curl -X GET http://localhost:8080/Smart-Campus-API/api/readings
```

---

### 🔹 5. Add a new sensor reading

```bash
curl -X POST http://localhost:8080/Smart-Campus-API/api/readings \
-H "Content-Type: application/json" \
-d '{"sensorId":1,"value":25.5}'
```

---

## 🧪 Testing

You can test endpoints using:

* curl (command line)
* Postman
* Browser (for GET requests)

---

## 📂 Project Structure

```
Smart-Campus-API/
├── src/
│   ├── main/
│   └── test/
├── pom.xml
├── .gitignore
├── README.md
```

---

## ❓ Coursework Questions (Summary)

### 1. What is REST?

REST (Representational State Transfer) is an architectural style for designing web services.
It uses HTTP methods (GET, POST, PUT, DELETE) and is stateless.

---

### 2. Difference between GET and POST

* GET → Retrieve data from the server
* POST → Send data to the server to create/update resources

---

### 3. Why use Maven?

Maven is used for:

* Managing dependencies
* Building the project
* Standardizing project structure

---

### 4. What is a WAR file?

A WAR (Web Application Archive) file is a packaged Java web application used for deployment on servers like Tomcat.

---

### 5. What is JSON?

JSON (JavaScript Object Notation) is a lightweight format used to exchange data between client and server.

---

## 👤 Author

Chris Corteling - w2120078/ 20240644
University of Westminster – Computer Science

---
