# Orchestration Saga Pattern With Spring Boot

# Overview:
In this tutorial, I would like to show you a simple implementation of Orchestration Saga Pattern with Spring Boot.

Over the years, Microservices have become very popular. Microservices are distributed systems. They are smaller, modular, easy to deploy and scale etc. Developing a single Microservice application might be interesting! But handling a business transaction which spans across multiple Microservices is not fun!  In order to complete an application workflow / a task, multiple Microservices might have to work together.

Let’s see how difficult it could be in dealing with transactions / data consistency in the distributed systems in this article & how Orchestration Saga Pattern could help us.

# A Simple Transaction:
Let’s assume that our business rule says, when a user places an order, order will be fulfilled if the product’s price is within the user’s credit limit/balance & the inventory is available for the product. Otherwise it will not be fulfilled. It looks really simple. This is very easy to implement in a monolith application. The entire workflow can be considered as 1 single transaction. It is easy to commit / rollback when everything is in a single DB. With distributed systems with multiple databases, It is going to be very complex! Let’s look at our architecture first to see how to implement this.

<img width="665" alt="Screenshot 2022-07-17 at 11 23 47 AM" src="https://user-images.githubusercontent.com/54174687/179385893-c063e762-7c55-4829-98ea-eb7c70f44f10.png">

We have below Microservices with its own DB.

- order-service
- payment-service
- inventory-service

When the order-service receives the request for the new order, It has to check with the payment-service & inventory-service. We deduct the payment, inventory and fulfill the order finally! What will happen if we deducted payment but if inventory is not available? How to roll back? It is difficult when multiple databases are involved.

<img width="715" alt="Screenshot 2022-07-17 at 11 28 26 AM" src="https://user-images.githubusercontent.com/54174687/179386013-37b4b67c-d090-4251-8f64-f75ed61c8b30.png">

# Saga Pattern:
Each business transaction which spans multiple microservices are split into micro-service specific local transactions and they are executed in a sequence to complete the business workflow. It is called Saga. It can be implemented in 2 ways.

- Choreography approach
- Orchestration approach

In this article, we will be discussing the Orchestration based saga. For more information on Choreography based saga, check here.

# Orchestration Saga Pattern:
In this pattern, we will have an orchestrator, a separate service, which will be coordinating all the transactions among all the Microservices. If things are fine, it makes the order-request as complete, otherwise marks that as cancelled.

Let’s see how we could implement this. Our sample architecture will be more or less like this.!

- In this demo, communication between orchestrator and other services would be a simple HTTP in a non-blocking asynchronous way to make this stateless.
- We can also use Kafka topics for this communication. For that we have to use scatter/gather pattern which is more of a stateful style.

<img width="1007" alt="Screenshot 2022-07-17 at 11 35 01 AM" src="https://user-images.githubusercontent.com/54174687/179386178-2f2309d3-dd58-4284-878c-2da5d9d295f2.png">

<img width="902" alt="Screenshot 2022-07-17 at 11 35 52 AM" src="https://user-images.githubusercontent.com/54174687/179386206-3ee3cc64-d8e1-4e7f-94cb-67fea56bd065.png">

GET -> `http://localhost:8080/order/all`

Response:

```json
[
    {
        "orderId": "bbf16ed3-67c3-447d-9adf-1b80d1de79fa",
        "userId": 1,
        "productId": 3,
        "amount": 300.0,
        "status": "ORDER_COMPLETED"
    },
    {
        "orderId": "0ada825e-3e2b-49cd-ba5a-88e442cc4d57",
        "userId": 1,
        "productId": 3,
        "amount": 300.0,
        "status": "ORDER_COMPLETED"
    },
    {
        "orderId": "c89ee2e9-82ba-4532-9b5d-cd94693c30ca",
        "userId": 1,
        "productId": 3,
        "amount": 300.0,
        "status": "ORDER_COMPLETED"
    },
    {
        "orderId": "ea9f9638-5a6d-4f8a-823f-15ce84fa1ec4",
        "userId": 1,
        "productId": 3,
        "amount": 300.0,
        "status": "ORDER_CANCELLED"
    }
]
```

- order-updated topic

<img width="1201" alt="Screenshot 2022-07-17 at 11 37 37 AM" src="https://user-images.githubusercontent.com/54174687/179386256-cf10fc18-68a5-4931-91d7-862b0399b2d5.png">

- Order Created topic

<img width="1199" alt="Screenshot 2022-07-17 at 11 38 32 AM" src="https://user-images.githubusercontent.com/54174687/179386281-0664926a-4378-4467-9444-4b7c2d450174.png">




