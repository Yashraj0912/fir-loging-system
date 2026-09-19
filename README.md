# FIR Logging System

A **Java-based FIR (First Information Report) Logging System** that uses **JDBC and MySQL** to store and retrieve victim, criminal, and case information, while using **Java I/O Streams** to create and read FIR reports in `.txt` format.

The project provides a simple case-management workflow where a case can be registered using victim and criminal details, an FIR report can be created and stored as a text file, and the stored information can later be retrieved using the FIR ID.

## Features

* Register a new FIR/case
* Store victim information in MySQL
* Store criminal information in MySQL
* Store case information and status in MySQL
* Generate a FIR/Case ID from victim and criminal identifiers
* Create and store FIR reports in `.txt` format
* Read and display previously written FIR reports
* Retrieve victim details using the FIR ID
* Retrieve criminal details using the FIR ID
* JDBC-based MySQL database connectivity
* Uses `PreparedStatement` for parameterized SQL queries
* Console-based interaction with Java
* Swing/AWT-based interface for FIR report interaction
* Java I/O Streams for FIR text-file operations

## Technologies Used

| Technology       | Purpose                                            |
| ---------------- | -------------------------------------------------- |
| Java             | Core application development                       |
| JDBC             | Database connectivity                              |
| MySQL            | Storing victim, criminal, case and FIR information |
| Swing            | GUI components                                     |
| AWT              | GUI/event handling                                 |
| Java I/O Streams | Creating and reading FIR `.txt` files              |
| IntelliJ IDEA    | Development environment                            |

## Project Workflow

```text
                 +----------------------+
                 |    Register a Case   |
                 +----------+-----------+
                            |
                            v
                 +----------------------+
                 |   Enter Victim Data  |
                 +----------+-----------+
                            |
                            v
                 +----------------------+
                 | Enter Criminal Data  |
                 +----------+-----------+
                            |
                            v
                 +----------------------+
                 | Generate Case/FIR ID |
                 +----------+-----------+
                            |
                            v
                 +----------------------+
                 | Store Data in MySQL  |
                 +----------+-----------+
                            |
                            v
                 +----------------------+
                 | Create FIR Report    |
                 |      (.txt file)     |
                 +----------+-----------+
                            |
                            v
                 +----------------------+
                 | Retrieve Using FIR ID|
                 +----------+-----------+
                            |
              +-------------+-------------+
              |             |             |
              v             v             v
        Victim Details  Criminal Details  FIR Report
                                             |
                                             v
                                      File Viewer
```

## Database Structure

The application uses MySQL to maintain information about victims, criminals, cases, and FIR records.

### Victim

The `victim` table stores information such as:

* Aadhaar/identifier
* Name
* Age
* Date of birth

Example structure:

```sql
victim
-------------------------
adhaar
name
age
dob
```

### Criminal

The `criminal` table stores:

* Aadhaar/identifier
* Name
* Age
* Date of birth
* Verdict

Example structure:

```sql
criminal
-------------------------
adhaar
name
age
dob
verdict
```

The current implementation initially stores the verdict as:

```text
pending
```

### Case

The `caseT` table stores case-level information:

```sql
caseT
-------------------------
caseID
statusC
```

New cases are initially assigned the status:

```text
running
```

### FIR

The `Fir` table connects the FIR with the victim, criminal and case:

```sql
Fir
-------------------------
FirID
victimID
criminalID
caseID
FirReport
```

`FirReport` stores the identifier used to locate the corresponding FIR text report.

## FIR Registration

The `registerACase` class handles the registration process.

The application first collects victim information:

```text
Victim Name
Victim ID
Victim Age
```

It then collects criminal information:

```text
Criminal Name
Criminal ID
Criminal Age
```

After collecting the information, the application generates a case/FIR identifier using the first characters/digits of the victim and criminal identifiers.

The generated identifier is then used while creating the case and FIR records.

## JDBC and MySQL

The project uses JDBC to communicate with MySQL.

Database operations are performed using `PreparedStatement`, for example:

```java
String strVictim =
    "INSERT INTO victim(adhaar,name,age,dob) VALUES (?,?,?,?)";

PreparedStatement statment = c.prepareStatement(strVictim);
```

This allows values to be supplied separately from the SQL query.

The application performs database operations for:

* Victim insertion
* Criminal insertion
* Case insertion
* FIR insertion
* Victim retrieval
* Criminal retrieval
* FIR lookup

## Retrieving a Case

The `getDetails` class allows a user to retrieve information using the FIR ID.

When an FIR ID is supplied, the application queries the FIR table:

```sql
SELECT * FROM Fir WHERE FirID = ?
```

The retrieved record provides the identifiers required to locate:

* Victim information
* Criminal information
* FIR report

The user can then select:

```text
1 → View Victim Details
2 → View Criminal Details
3 → View FIR Report
4 → Exit
```

## FIR Text Files

The FIR report is maintained as a text file.

Java I/O functionality is used to write FIR information into `.txt` files and subsequently read the stored report.

The FIR report can be accessed using its associated FIR/Report ID.

The project also contains a `FileViewer` component that is used to open/display the stored FIR report.

## Swing and AWT

The project uses Java **Swing and AWT** for user-interface functionality associated with the FIR report.

These technologies provide the components and event-driven functionality required to interact with and display FIR information.

## Project Structure

A typical project structure is:

```text
FIR-loging-system/
│
├── src/
│   ├── registerACase.java
│   ├── getDetails.java
│   ├── writeFir.java
│   ├── FileViewer.java
│   ├── ...
│   │
│   └── firs/
│       ├── FIR_REPORT_1.txt
│       ├── FIR_REPORT_2.txt
│       └── ...
│
├── pom.xml / project configuration
└── README.md
```

> The exact structure may vary depending on the current project files.

## Requirements

Before running the project, install:

* Java JDK
* MySQL Server
* MySQL Connector/J
* IntelliJ IDEA or another Java IDE

Recommended:

```text
Java 17+
MySQL 8+
```

## Setup

### 1. Clone the Repository

```bash
git clone https://github.com/Yashraj0912/fir-loging-system.git
```

```bash
cd fir-loging-system
```

### 2. Create the MySQL Database

Create a database in MySQL:

```sql
CREATE DATABASE fir_system;
```

Select it:

```sql
USE fir_system;
```

Create the required tables according to the SQL schema used by the project.

### 3. Configure Database Connection

Update the database connection details in the Java database connection class.

Typical configuration:

```java
String url = "jdbc:mysql://localhost:3306/fir_system";
String username = "root";
String password = "YOUR_PASSWORD";
```

Replace the username, password, database name, and port according to your local MySQL configuration.

### 4. Configure FIR File Location

The current implementation uses a local Windows path when opening FIR files:

```java

```

For another computer, this path should be changed to the location of the project's `firs` directory.

## How It Works

### Registering a Case

```text
Start Application
       ↓
Enter Victim Details
       ↓
Enter Criminal Details
       ↓
Generate Case/FIR ID
       ↓
Insert Victim into MySQL
       ↓
Insert Criminal into MySQL
       ↓
Insert Case into MySQL
       ↓
Insert FIR into MySQL
       ↓
Write FIR Report
```

### Viewing an Existing Case

```text
Enter FIR ID
      ↓
Search FIR table
      ↓
Retrieve Victim ID
Retrieve Criminal ID
Retrieve FIR Report ID
      ↓
Choose required information
      ↓
View Victim / Criminal / FIR Report
```

## Current Implementation Notes

The current version is a working learning project demonstrating the integration of:

* Java
* JDBC
* MySQL
* Swing/AWT
* Java I/O
* SQL CRUD operations
* File-based FIR reports

Some parts are currently simplified. For example, the date-of-birth values in the current registration implementation are placeholder values rather than being collected from the user.

## Future Improvements

Possible improvements include:

* Add proper date-of-birth input and validation
* Add login/authentication for authorized users
* Add search by FIR ID, victim, or criminal
* Add update and delete operations
* Add stronger input validation
* Use database transactions for registering related records
* Replace hardcoded file paths with configurable paths
* Improve the Swing/AWT interface
* Add centralized exception handling and logging
* Add proper database foreign-key relationships
* Add automated tests
* Containerize the application using Docker
* Add CI/CD using GitHub Actions
* Add role-based access for different users

## Security Considerations

This project is intended primarily as a learning project.

For a production system, sensitive personal information such as identification numbers and criminal records should be protected using appropriate:

* Authentication
* Authorization
* Encryption
* Access controls
* Secure database configuration
* Audit logging
* Data privacy practices

## Author

**Yash Raj Singh Yadav**

B.Tech Computer Science & Engineering
Bhopal, Madhya Pradesh, India

GitHub:
https://github.com/Yashraj0912

---

## License

This project is intended for educational and portfolio purposes.
