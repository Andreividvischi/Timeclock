# Timeclock
This is the backend service for request below.

**Please check TODO comments.**

**EndPoints available for Demo:**
- http://localhost:8080/user/list
- http://localhost:8080/reports/getAllLogs
- http://localhost:8080/log/login/3
- http://localhost:8080/log/logout/3
- Errors are reported as HTTP_STATUS and logged in the console
- Few unit tests done

**Build and run instruction:**
- Used **jdk-11.0.10**
- **mvn clean install** on project
- "C:\Program Files\Java\jdk-11.0.10\bin\java" -jar target/timeclock-0.0.1-SNAPSHOT.jar

**Request**:
Build a “Timeclock” app in a lean and relatively quick version.

This app will include the following:
- Assuming there are 3 employees, registered by email, they can report entrance, report exit. 
- Also, there will be a page on which we can generate a report 
- Implement by a Java sprint boot or .NET core microservice. Use any relational database you choose.
- Create a simple frontend, with any of the options (single page application): React/ Angular / Vue.Js.
- The outcome should be a GitHub repo link and include a DB snapshot.
- *Bonus: make sure that one person can not report at the same time.

**My first analysis:**

  Before starting to create the application, is important to understand the vision of client and some usage aspects like who will use it, devices, internal/external, how many users, concurrency, performance aspects, etc.; After knowing these aspects, we can choose the appropriate architecture.

As an example, for this application that I suppose the scope of it is for internal usage, we could use a server-client architecture because we have a limited number of users and is no sense to use micro services architecture in this case as is expensive (development/infrastructure/monitoring tools) and we do not need to scale. Concurrency in this case could be assured by DB directly. 

Tacking in consideration your domain and Luxon application that handle an account balance, is verry important that the financial transactions to be done in order and to have the possibility to scale and use of multiple client applications. A micro service architecture is a good fit in this case and concurrency can be handled by using, as an example, a queue for communication between micro- services (all transactions are sent to the same queue (partition) and the plus/minus operation will be done in the right order to the account amount). 


Going back to this exercise, if I would have to create an **MVP will lock like this**:
-	User will log in in the application (basic authentication with encrypted password).
-	User will see his own log in/out records for a period (predefined or date pick).
-	User can log in/out for each day.
-	An Admin user will have the possibility to generate weekly/monthly report per user or for all, make correction if needed.

**For NVP 1,2…:**
-	Integrate with financial application.
-	Integrate with company user domains (for authentication)
-	Add possibility for adding different holidays.
-	Add approval flows.
-	Integrate with issue trackers like JIRA
-	Archive records.
-	Etc.

**From technical point of view, for this exercise:**
1.	I would create 2 services in the beginning (one for FE and one for BE). For BE, I will generate a starter project from start.spring.io.
2.	Create entities (@Entity - Hibernate) for:
-	users(id - PK, email - UK, first name NN, last name NN, pass NN – encoded)
-	user_logs(user_id – PK/FK, date – PK, login, logout) – concurrency can be treated with this PK
-	roles (id - PK, role_name) 
-	user_roles(user_id – PK/FK, role_id - FK)
3.	Create DAO to extends CrudRepository
4.	Create services for login/addLogs/generateReport
5.	Create Rest endpoints (that will call the services created):
-	POST login – get user info as response.
-	GET logs – get user logs.
-	POST addLogs – merge operation should be applied (insert/update or none) – response will say what was done.
-	GET report.
6.	Create spring security configuration. 
7.	Create unit tests.
8.	For FE, tacking in consideration that I have a little bit experience with ReactJS, I will start from: https://github.com/xvicmanx/react-crud-table project and extend.
