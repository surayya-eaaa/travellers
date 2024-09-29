## How to start backend in Docker

 * cd travellers/Deployment
 * docker build -t bestseller-rest .
 * docker run -p 8080:8080 --name travellers travellers-rest
 * http://localhost:8080/Travellers

## How to start frontend

 * cd /travellers/frontend
 * docker build -t travellers-frontend .
 * docker run -p 8081:8080 --name travellers-frontend travellers-frontend
 * start vue.js application in webbrowser on http://127.0.0.1:8081/

## Api simple documentation and test
Swagger for testing the REST endpoint and interface documentation
http://localhost:8080/swagger-ui/index.html


## REST Api for Travellers resourse
Access Travellers resourse
http://localhost:8080/Customers

## Test Cases
 * Try CRUD operations
 * Try for Travellers writing email without @
 * Try keeping Travellers name empty

# Also add basic authentication for the API
# Deploy to Azure



