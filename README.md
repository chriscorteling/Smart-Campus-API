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
By default, JAX-RS creates a new instance of a resource class for every incoming HTTP request and destroyed once after the request finishes. This is known as per request scope. This means that any instance variables declared inside a resource class are reset with each request and cannot be used to store persistent data. 


This is why the DataStore class uses static fields for its HashMaps. Static fields belong to the class itself rather than to any instance, meaning they persist in memory regardless of how many resource class instances are created or destroyed. Without static fields, data added in one request would be lost by the next request since a fresh instance would be created.


In a production system, a database would handle persistence. In the coursework it has used static HashMaps in a dedicated DataStore class provide a simple and effective solution that survives across multiple requests.

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

## Question 1: When returning a list of rooms, what are the implications of returning only IDs versus returning the full room objects? Consider network bandwidth and client side processing.

**Answer:**
When returning only IDs, the response payload is minimal and uses very little network bandwidth. However, the client would need to make a separate GET request for each individual room ID to retrieve the full details. This is known as the N+1 problem, if there are 100 rooms, the client needs 101 requests total which creates significant network overhead and slows down the application.


Returning full room objects produces a larger response payload but the client receives all the data it needs in a single call with no additional requests required. This implementation returns full room objects because the Room data structure is small and lightweight, meaning the bandwidth cost is minimal. The benefit of having complete data available immediately far outweighs the slight increase in response size, resulting in better overall performance and a simpler client implementation.


---

## Question 2: Is the DELETE operation idempotent in your implementation? Provide a detailed justification by describing what happens if a client mistakenly sends the exact same DELETE request for a room multiple times.

**Answer:**  
Yes,

The DELETE operation is idempotent in this implementation. Idempotence requires that multiple identical requests produce the same effect on the server state, regardless of varying response codes.
When a client sends the same DELETE request for a room multiple times, the following occurs. On the first request, the room exists and is successfully deleted, returning 204 No Content. On all subsequent requests, the room no longer exists in the DataStore, so the server returns 404 Not Found with no further changes made to the system. The server state remains identical after the first deletion the room is gone, which fulfils the definition of idempotence as defined by RFC 7231. This standard emphasises that idempotence refers to consistent side effects on the resource, not identical response codes. Different status codes (204 vs 404) do not violate idempotence since only the backend state matters. Clients can safely retry the same DELETE request and achieve the same outcome.

---

# PART 3

## Question 1: We explicitly use the @Consumes (MediaType.APPLICATION_JSON) annotation on the POST method. Explain the technical consequences if a client attempts to send data in a different format, such as text/plain or application/xml. How does JAX-RS handle this mismatch?

**Answer:**
When a client sends a request with a Content-Type header of text/plain or application/xml to an endpoint annotated with @Consumes(MediaType.APPLICATION_JSON), JAX-RS automatically intercepts the request before it even reaches the resource method. JAX-RS checks the Content-Type header of every incoming request and compares it against what the endpoint declares it can consume.


If there is a mismatch, JAX-RS immediately rejects the request and returns an HTTP 415 Unsupported Media Type response. The resource method code never executes Jersey handles the rejection entirely at the framework level before any application logic runs.


This is beneficial because it acts as an automatic validation layer. Developers do not need to manually check the content type inside every method JAX-RS enforces it automatically. It also protects the application from receiving unexpected data formats that the Jackson deserializer would not be able to parse into the expected Java object, preventing potential NullPointerExceptions or malformed data from entering the system.


---

## Question 2: You implemented this filtering using @QueryParam. Contrast this with an alternative design where the type is part of the URL path (e.g., /api/vl/sensors/type/CO2). Why is the query parameter approach generally considered superior for filtering and searching collections?

**Answer:**
Query parameters and path parameters serve fundamentally different purposes in REST design. Path segments like /sensors/type/Temperature are used to identify a specific resource they suggest that type is a distinct resource that lives at that location, which is semantically incorrect. A sensor type is not a resource, it is a filter criterion applied to a collection.


Query parameters like "?type=Temperature" are specifically designed for filtering, searching, and modifying how a collection is returned. They are optional by nature, if the parameter is not provided, the endpoint simply returns all sensors. This is exactly what this implementation does GET /sensors returns all sensors while GET /sensors?type=Temperature returns only Temperature sensors. With a path based approach, two completely separate endpoint methods would be needed to handle both cases.


Additionally query parameters are more flexible and scalable. Multiple filters can be combined easily such as "?type=Temperature&status=ACTIVE" without changing the URL structure. With path based filtering, adding multiple filters would require increasingly complex and deeply nested URL paths that become difficult to read and maintain. Query parameters therefore provide a cleaner, more RESTful and more extensible solution for collection filtering.


---

# PART 4

## Question 1: Discuss the architectural benefits of the Sub-Resource Locator pattern. How does delegating logic to separate classes help manage complexity in large APIs compared to defining every nested path (e.g., sensors/{id}/readings/{rid}) in one massive controller class?

**Answer:**
The Sub-Resource Locator pattern is an architectural approach where a resource class delegates responsibility for a nested path to a separate dedicated class. In this implementation, SensorResource does not handle reading operations directly instead the getReadingsResource() method simply returns a new instance of SensorReadingResource, passing the sensorId as context. JAX-RS then routes all reading related requests to that dedicated class.


The primary architectural benefit is separation of concerns. Each resource class has a single, clearly defined responsibility SensorResource manages sensors and SensorReadingResource manages readings. This makes the codebase significantly easier to read, maintain and debug. If a bug exists in the readings logic, a developer knows exactly which class to look at.
In contrast, defining every nested path in one massive controller class would result in a single class handling rooms, sensors, readings and all their sub paths. As the API grows this class becomes increasingly difficult to manage. Adding new features risks breaking existing functionality and the class becomes too large to navigate efficiently.


The pattern also improves testability since each class can be tested independently. It also makes the code more scalable, new sub-resources can be added by simply creating a new class and adding a locator method, without touching existing resource classes. This aligns with the Open/Closed Principle the system is open for extension but closed for modification.

---

# PART 5

## Question 1: Why is HTTP 422 often considered more semantically accurate than a standard 404 when the issue is a missing reference inside a valid JSON payload?

**Answer:**
404 Not Found is designed to indicate that the requested resource URL does not exist on the server. It is a response about the endpoint itself, the path being requested could not be found. Using 404 when a roomId reference inside a JSON body does not exist is semantically misleading because the endpoint “/api/v1/sensors” does exist and was found successfully.


HTTP 422 Unprocessable Entity is far more semantically accurate in this scenario. It communicates that the server understood the request, the endpoint exists, the JSON was valid and well formed, but the server was unable to process the contained instructions due to a semantic error. In this case the semantic error is that the roomId field inside the JSON body references a room that does not exist in the system.


The distinction is important for client developers. A 404 response would suggest the client made a wrong URL which would lead them to check their endpoint path. A 422 response correctly directs the client to examine the content of their request body and fix the invalid reference. This makes the API more self documenting and easier to debug. It also aligns with REST best practices where status codes should accurately reflect the nature of the error rather than just indicating something went wrong.

---

## Question 2: From a cybersecurity standpoint, explain the risks associated with exposing internal Java stack traces to external API consumers. What specific information could an attacker gather from such a trace?

**Answer:**
Exposing Java stack traces to external users is a serious security risk because they reveal sensitive internal details about how the application is built.


An attacker can gather several types of useful information from a stack trace. They can see the exact file paths and package names of the code, revealing how the project is structured. They can identify which third party libraries and frameworks are being used along with their exact version numbers, for example seeing Jersey 2.39.1 or Tomcat 9.0 allows an attacker to search online for known security vulnerabilities in those specific versions and exploit them. They can also see internal method names and line numbers which gives them a detailed map of the application logic, making it easier to craft targeted attacks.


This is why the GlobalExceptionMapper in this implementation intercepts all unexpected errors and returns only a clean, generic message to the client saying "An unexpected error occurred." The real error details are logged privately on the server using logger.log, where only developers can see them. The attacker gets nothing useful.

---

## Question 3: Why is it advantageous to use JAX-RS filters for cross-cutting concerns like logging rather than manually inserting Logger.info() statements inside every single resource method?

**Answer**
A cross-cutting concern is a piece of functionality that applies to every part of the application regardless of the specific business logic. Logging every request and response is a perfect example, it needs to happen for every single endpoint without exception.


If logging was added manually inside every resource method, the same logging code would need to be copy and pasted into every single method across every resource class. This violates the DRY principle ( Don't Repeat Yourself). If the logging format ever needed to change, every single method would need to be updated individually which is time consuming and error prone. It is also easy to forget to add logging to a new method, resulting in inconsistent coverage.
JAX-RS filters solve this problem elegantly. The LoggingFilter class in this implementation implements both ContainerRequestFilter and ContainerResponseFilter, which means it automatically intercepts every single incoming request and outgoing response without any changes needed to the resource classes.


The @Provider annotation tells JAX-RS to automatically register the filter so it applies globally across the entire API. This keeps the resource classes clean and focused purely on their business logic while the filter handles the cross-cutting concern of logging separately. This approach is more maintainable, consistent and follows good software design principles.

