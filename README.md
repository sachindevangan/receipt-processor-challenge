# Receipt Processor Challenge - Fetch Rewards

This project is a RESTful API built with Spring Boot that processes receipts and calculates reward points based on specific business rules.

---

## Features

- Submit receipts and receive a unique ID.
- Retrieve calculated reward points using the receipt ID.
- Scoring based on:
  - Retailer name characters
  - Total amount
  - Item descriptions
  - Purchase date/time
  - And more...

---

##  Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+

###  Build the Project

```bash
mvn clean install
```
### Run the Application
```bash
mvn spring-boot:run
```

The server will start on:
http://localhost:8080

### API Endpoints
1. Process a Receipt
   
  POST /receipts/process

Request Body:
```json
{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },
    {
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    }
  ],
  "total": "35.35"
}
```

Response:
```json
{
  "id": "2e4fbc4d-d5f3-429b-852b-5353f106b7c7"
}
```

2. Get Points for a Receipt
   
  GET /receipts/{id}/points

Response:
```json
{
  "points": 28
}
```
### Scoring Rules Implemented

| Rule  | Description                                                                 |
|-------|-----------------------------------------------------------------------------|
|  1  | Alphanumeric characters in retailer name = 1 point each                     |
|  2  | Total is a round dollar amount = +50 pts                                     |
|  3  | Total ends in `.00` = +25 pts                                                |
|  4  | Every 2 items = +5 pts                                                       |
|  5  | Item description length divisible by 3 → round up 20% of price and add      |
|  6  | Purchase day is odd = +6 pts                                                 |
|  7  | Time between 2–4 PM = +10 pts                                                |


