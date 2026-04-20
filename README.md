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

# Part 1

### Question 1: In your report, explain the default lifecycle of a JAX-RS Resource class. Is a new instance instantiated for every incoming request, or does the runtime treat it as asingleton? Elaborate on how this architectural decision impacts the way you manage and synchronize your in memory data structures $(maps/lists)$ to prevent data loss or race conditions.

**Answer:** 
By default, JAX-RS creates a new instance of a resource class and treat for every incoming HTTP request and destroyed once after the request finishes. This is known as per request scope. This means that any instance variables declared inside a resource class are reset with each request and cannot be used to store persistent data. 
This is why the DataStore class uses static fields for its HashMaps. Static fields belong to the class itself rather than to any instance, meaning they persist in memory regardless of how many resource class instances are created or destroyed. Without static fields, data added in one request would be lost by the next request since a fresh instance would be created.
In a production system, a database would handle persistence. In the coursewor it has used static HashMaps in a dedicated DataStore class provide a simple and effective solution that survives across multiple requests.

---

## Question 2: Why is the provision of ”Hypermedia” (links and navigation within responses) considered a hallmark of advanced RESTful design (HATEOAS)? How does this approach benefit client developers compared to static documentation?

**Answer:** 
HATEOAS (Hypermedia as the Engine of Application State) is considered a hallmark of advanced REST design because it makes APIs self describing. Clients don’t just get raw data, instead they also get links and navigation instructions that tell them what they can do next, similar to how a browser follows links in HTML
For example, the Discovery endpoint in this API returns links to /api/v1/rooms and /api/v1/sensors directly in the response. A client can dynamically discover these endpoints without consulting external documentation.
Compared to static documentation, HATEOAS benefits client developers because:

i. If URLs change, clients automatically get the updated links without needing documentation updates

ii. New endpoints can be discovered at runtime rather than hardcoded

iii. It reduces tight coupling between client and server — the client follows links rather than constructing URLs manually

---

# PART 2

## Question 1:  Is the DELETE operation idempotent in your implementation? Provide a detailed justification by describing what happens if a client mistakenly sends the exact same DELETE request for a room multiple times.

**Answer:**  
Yes, the DELETE operation is idempotent in standard HTTP implementations, including what would be expected in my context as an AI discussing API behaviors. Idempotence requires that multiple identical requests produce the same effect on the server state, regardless of varying response codes.

Server State Outcome
When a client sends the same DELETE request for a room multiple times:

First request: Room exists, so it's deleted, returning 204 No Content.

Subsequent requests: Room no longer exists, so server returns 404 Not Found, with no further changes.

The server's state remains identical after the first deletion— the room is gone—fulfilling idempotence as defined by RFC 7231, which emphasizes consistent side effects on the resource, not response codes.

Response Code Difference
Different status codes (204 vs. 404) do not violate idempotence, as standards like MDN and Stack Overflow clarify that only the backend state matters; clients retrying safely achieve the same result. This aligns with REST best practices where DELETE is explicitly idempotent.