This is a microservice project for sole purpose only.

Order in which you should be running the project.
1. ServiceRegistry(@Port 8761)
2. RatingMicroservice(@port 8083)
3. HotelMicroservice(@Port 8082)
4. UserMicroservice(@Port 8081)
5. ApiGateway(@Port 8084)

Api Gateway is the centralized service for you to access all the other api. E.g. with "http://localhost:8084/users" you can retrieve all the user and "http://localhost:8084/hotels" can retrieve all the hotels. 

