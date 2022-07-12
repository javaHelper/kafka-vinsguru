# Saga Choreography

POST ->

```sh
curl --location --request POST 'http://localhost:8080/order/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userId" : 1,
    "productId" : 3
}'
```

<img width="899" alt="Screenshot 2022-07-12 at 10 57 29 AM" src="https://user-images.githubusercontent.com/54174687/178415306-4bd20dc9-b313-49e1-a931-81d188a8dc03.png">

GET -> `http://localhost:8080/order/all`

```sh
[
    {
        "id": "1e55ba48-f092-4f89-8586-f93dbc749029",
        "userId": 1,
        "productId": 3,
        "price": 300,
        "orderStatus": "ORDER_COMPLETED",
        "paymentStatus": "RESERVED",
        "inventoryStatus": "RESERVED",
        "version": 2
    },
    {
        "id": "99512d48-9268-4cec-8331-d1fcdc4f9025",
        "userId": 1,
        "productId": 3,
        "price": 300,
        "orderStatus": "ORDER_COMPLETED",
        "paymentStatus": "RESERVED",
        "inventoryStatus": "RESERVED",
        "version": 2
    },
    {
        "id": "267ce06f-2352-4c0f-bf4a-c70ed53f5ab3",
        "userId": 1,
        "productId": 3,
        "price": 300,
        "orderStatus": "ORDER_COMPLETED",
        "paymentStatus": "RESERVED",
        "inventoryStatus": "RESERVED",
        "version": 2
    },
    {
        "id": "6d1b65e4-fdf3-4122-9abf-59e7f7204102",
        "userId": 1,
        "productId": 3,
        "price": 300,
        "orderStatus": "ORDER_CANCELLED",
        "paymentStatus": "REJECTED",
        "inventoryStatus": "RESERVED",
        "version": 2
    }
]
```

<img width="1189" alt="Screenshot 2022-07-12 at 10 58 39 AM" src="https://user-images.githubusercontent.com/54174687/178415477-684c9d4f-d691-4ada-a1d4-e4b255ce7cee.png">
