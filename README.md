# Task Management Application Documentation

## Overview

This application provides a REST API for managing tasks and task lists. It allows you to create, retrieve, update, and delete tasks and task lists.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- An IDE like IntelliJ IDEA or Eclipse (optional)

### Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/dcmariano/desafio/desafio.git
   cd desafio
   ```

2. **Build the Application**

   Using Maven:
   ```bash
   mvn clean install
   ```

3. **Run the Application**

   Using Maven:
   ```bash
   mvn spring-boot:run
   ```

   The application will start on `http://localhost:8080` by default.

## API Endpoints

### Task Endpoints

1. **Create a Task**

    - **Endpoint:** `POST /tasks`
    - **Request Body:**
      ```json
      {
        "title": "Task Title",
        "description": "Task Description",
        "status": "PENDING",
        "isHighlighted": false,
        "taskList": null
      }
      ```
    - **Response:**
      ```json
      {
        "id": 1,
        "title": "Task Title",
        "description": "Task Description",
        "status": "PENDING",
        "isHighlighted": false,
        "taskList": null
      }
      ```

2. **Get a Task**

    - **Endpoint:** `GET /tasks/{id}`
    - **Response:**
      ```json
      {
        "id": 1,
        "title": "Task Title",
        "description": "Task Description",
        "status": "PENDING",
        "isHighlighted": false,
        "taskList": null
      }
      ```

3. **Delete a Task**

    - **Endpoint:** `DELETE /tasks/{id}`
    - **Response:** `204 No Content`

4. **Get Tasks by List**

    - **Endpoint:** `GET /tasks/list/{taskListId}`
    - **Response:**
      ```json
      [
        {
          "id": 1,
          "title": "Task Title",
          "description": "Task Description",
          "status": "PENDING",
          "isHighlighted": false,
          "taskList": null
        }
      ]
      ```

5. **Update Task Status**

    - **Endpoint:** `PATCH /tasks/{id}/status`
    - **Request Parameters:**
        - `newStatus`: The new status for the task (e.g., `COMPLETED`)
    - **Response:**
      ```json
      {
        "id": 1,
        "title": "Task Title",
        "description": "Task Description",
        "status": "COMPLETED",
        "isHighlighted": false,
        "taskList": null
      }
      ```

6. **Update a Task**

    - **Endpoint:** `PUT /tasks/{id}`
    - **Request Body:**
      ```json
      {
        "title": "Updated Task Title",
        "description": "Updated Task Description",
        "status": "COMPLETED",
        "isHighlighted": true,
        "taskList": null
      }
      ```
    - **Response:**
      ```json
      {
        "id": 1,
        "title": "Updated Task Title",
        "description": "Updated Task Description",
        "status": "COMPLETED",
        "isHighlighted": true,
        "taskList": null
      }
      ```

### Task List Endpoints

1. **Create a Task List**

    - **Endpoint:** `POST /task-lists`
    - **Request Body:**
      ```json
      {
        "name": "Task List Name"
      }
      ```
    - **Response:**
      ```json
      {
        "id": 1,
        "name": "Task List Name"
      }
      ```

2. **Get a Task List**

    - **Endpoint:** `GET /task-lists/{id}`
    - **Response:**
      ```json
      {
        "id": 1,
        "name": "Task List Name"
      }
      ```

3. **Delete a Task List**

    - **Endpoint:** `DELETE /task-lists/{id}`
    - **Response:** `204 No Content`

## Error Handling

- **404 Not Found:** When the requested resource is not found.
- **400 Bad Request:** When the request body is invalid.
- **500 Internal Server Error:** When there is an unexpected server error.

## Testing

### Running Tests

You can run the tests using Maven:

- Maven:
  ```bash
  mvn test
  ```
