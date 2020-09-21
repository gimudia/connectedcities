# Connected Cities - Find if there is a route between two cities  

## Project Summary

This project is a Spring boot Rest API written to find if two cities are connected. List of roads are available in the file(city.txt). The file contains a list of city pairs (one pair per line, comma separated), which indicates that there is a road between those cities.

## Prerequisites
	* Java 8 or above
	* Maven

## File Data-
Test file city.txt can find into resources folder. Modify the location in in the properties file, to build and test the code.

## Installation & Run

To install and run go to the directory in which you want to install the project. clone the github project via URL
git clone https://github.com/gimudia/connectedcities.git

do a maven clean install to build the source code
`mvn clean install`

The UNIT test can be executed by
`mvn test`

Run the application using maven Spring Boot plugin
`mvn spring-boot:run `

## Testing Endpoints

This Rest API via swagger can be used. Postman or browser can used as well.

* http://localhost:8080/swagger-ui.html#/
* http://localhost:8080/connected?destination=Boston&origin=Newark
* http://localhost:8080/swagger-ui.html#/connected-cities-controller/isRouteAvailableUsingGET

Note- City names are case insensitive and white spaces will be automatically removed.
