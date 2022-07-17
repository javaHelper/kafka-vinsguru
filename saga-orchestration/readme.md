 # Choreography Saga Pattern With Spring Boot — Microservice Design Patterns
 
https://vinsguru.medium.com/choreography-saga-pattern-with-spring-boot-microservice-design-patterns-fb35a1802bee
 
Over the years, Microservices have become very popular. Microservices are distributed systems. They are smaller, modular, easy to deploy and scale etc. Developing a single microservice application might be interesting! But handling a business transaction which spans across multiple Microservices is not fun! In order to complete an application workflow / a task, multiple Microservices might have to work together.

Lets see how difficult it could be in dealing with transactions / data consistency in the distributed systems in this article.

# A Simple Transaction:
Let’s assume that our business rule says, when an user places an order, order will be fulfilled if the product’s price is within the user’s credit limit/balance. Otherwise it will not be fulfilled. It looks really simple.

This is very easy to implement in a monolith application. The entire workflow can be considered as 1 single transaction. It is easy to commit / rollback when everything is in a single DB. With distributed systems with multiple databases, It is going to be very complex! Lets look at our architecture first to see how to implement this.

We have an order-service with its own database which is responsible for order management. Similarly we also have payment-service which is responsible for managing payments. So the order-service receives the request and checks with the payment-service if the user has the balance. If the payment-service responds OK, order-service completes the order and payment-service deducts the payment. Otherwise, the order-service cancels the order. For a very simple business requirement, here we have to send multiple requests across the network.

<img width="427" alt="Screenshot 2022-07-17 at 11 09 32 AM" src="https://user-images.githubusercontent.com/54174687/179385483-d649e136-9bfb-4598-9f1c-65189d1e783d.png">


What if we also need to check with inventory-service for the availability of inventory before making the order as complete! Now you see the problem?

In the traditional system design approach, order-service simply sends a HTTP request to get the information about the user’s credit balance. The problem with this approach is order-service assumes that payment-service will be up and running always. Any network issue or performance issue at the payment-service will be propagated to the order-service. It could lead to poor user-experience & we also might lose revenue. Let’s see how we could handle transactions in the distributed systems with loose coupling by using a pattern called Saga Pattern with Event Sourcing approach.

# Saga Pattern:
Each business transaction which spans multiple Microservices are split into Microservice specific local transactions and they are executed in a sequence to complete the business workflow. It is called Saga. It can be implemented in 2 ways.

- Choreography approach
- Orchestration approach

In this article, we will be discussing the choreography based approach by using event-sourcing. For orchestration based Saga, check here.
