# Pancake-shop

This is a Spring Boot project that contains the API for managing a pancake shop.
The application allows to create/read/update/delete an ingredient, pancake (list of ingredients) and order (list of pancakes).
The database is set up inside a docker container. 

## How to test the application:
1) run "docker-compose up" in the root directory of the project to start the docker container and the postgres database
2) run the PancakeShopApplication
3) import the "Pancake_Shop.postman_collection.json" file into Postman and try out the various methods
