# Connected Cities - Find the route of two cities are connected  
1. Project Details-
This project is Spring boot Rest API written to find the if two cities routes are connected. List of roads is available in a file(city.txt). The file contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a
road between those cities.
2. Prerequisites
	Java 8 or above
	Maven
3. File Data-
Test file city.txt can find into resources folder.
4.Installation & Run
To install and run goto the directory in which you want to install the project. clone the github project via URL
git clone https://github.com/gimudia/connectedcities.git

do a maven clean install to build the source code
mvn clean install

The UNIT test can be executed by
mvn test

Run the application using maven Spring Boot plugin
mvn spring-boot:run 

5. Testing Endpoints
This Rest API can tested using swagger or other rest api testing clients like postman etc. below are the endpoints
Access service - http://localhost:8088/swagger-ui.html#/
http://localhost:8088/connected?destination=Boston&origin=Newark
http://localhost:8088/swagger-ui.html#/connected-cities-controller/isRouteAvailableUsingGET

Note- Cities names are case insensitive and white spaces will be automatic removed.



