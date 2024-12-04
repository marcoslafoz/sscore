# SScore

**SScore** is the Backoffice application for the **Student Space** system, designed to efficiently manage student-related operations and academic data. This project is built using **Spring Boot** and employs **GraphQL** for efficient data queries and management.

## Technologies Used

- **Spring Boot**: A framework for building modern, robust Java applications.
- **GraphQL**: A query language for APIs, allowing efficient retrieval of specific data.
- **PostgreSQL**: A relational database management system used as the primary data store.

## Prerequisites

Before running the project, make sure you have the following installed:

- **PostgreSQL**: To manage the application's database.
- **Java SDK 21**: Required for running the Java-based development environment.

## Initial Setup

1. Clone the project repository:
   ```bash
   git clone <repository-URL>
   ```
2. Ensure PostgreSQL is running and the necessary database has been created.

## Useful Commands

### Run the Application
To start the application using Maven and Spring Boot, run:

```bash
mvn spring-boot:run
```

### Create the Database
Execute the SQL script to set up the database in PostgreSQL:

```bash
psql -U <your-username> -d studentspacedb -f postgres/studentspacedb.psql
```

*Note: Replace `<your-username>` with your database username.*

## Documentation & Support

For more details about the architecture or to get support, check the documentation in the `docs/` folder or open an issue in the repository.
