# Bug Tracker Backend

## Build the Application

Build and run all tests with

`./gradlew build`

Just run all tests with

`./gradlew test`

## Run the Application

Start the application locally with

`./gradlew bootRun`

The backend API will be available at [http://localhost:8080/api/v1/tickets](http://localhost:8080/api/v1/tickets).\
The application is automatically initialized with some sample data defined in [data.sql](src%2Fmain%2Fresources%2Fdata.sql).\
You can find the H2 console at [http://localhost:8080/h2-console](http://localhost:8080/h2-console). The JDBC URL will
be printed in the log during startup.