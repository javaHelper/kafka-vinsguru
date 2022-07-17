 # Choreography Saga Pattern With Spring Boot — Microservice Design Patterns
 
https://vinsguru.medium.com/choreography-saga-pattern-with-spring-boot-microservice-design-patterns-fb35a1802bee
 
Over the years, Microservices have become very popular. Microservices are distributed systems. They are smaller, modular, easy to deploy and scale etc. Developing a single microservice application might be interesting! But handling a business transaction which spans across multiple Microservices is not fun! In order to complete an application workflow / a task, multiple Microservices might have to work together.

Lets see how difficult it could be in dealing with transactions / data consistency in the distributed systems in this article.

# A Simple Transaction:
Let’s assume that our business rule says, when an user places an order, order will be fulfilled if the product’s price is within the user’s credit limit/balance. Otherwise it will not be fulfilled. It looks really simple.

This is very easy to implement in a monolith application. The entire workflow can be considered as 1 single transaction. It is easy to commit / rollback when everything is in a single DB. With distributed systems with multiple databases, It is going to be very complex! Lets look at our architecture first to see how to implement this.

We have an order-service with its own database which is responsible for order management. Similarly we also have payment-service which is responsible for managing payments. So the order-service receives the request and checks with the payment-service if the user has the balance. If the payment-service responds OK, order-service completes the order and payment-service deducts the payment. Otherwise, the order-service cancels the order. For a very simple business requirement, here we have to send multiple requests across the network.
