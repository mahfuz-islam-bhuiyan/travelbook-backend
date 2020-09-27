# travelbook-backend

###To Run the project

# Prerequisites
- JDK 8 
- Mysql db 
- node (>= v10)

# DB

- Mysql db needs to run the application. 
- Run the `db-script/db_script.sql` against a mysql db server.

# Run Back-end application

- Unzip `travelbook-backend-master.zip` and `cd` into the project directory and bring up a terminal window.

- `cd /src/main/resources` then open the `application.properties` and update following properties.

    spring.datasource.url= jdbc:mysql://localhost:3306/mbs?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true
    
    spring.datasource.username=<db_username>
    
    spring.datasource.password=<db_password>

    If the mysql server hosted remotely then we need to replace the value of `spring.datasource.url`'s  `localhost` to remotely hosted mysql IP and Port.

- `cd` back to the project's root directory.
- `mvn spring-boot:run`
- Spring backend should be running at `http://localhost:8080/`

