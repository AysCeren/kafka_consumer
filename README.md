# Kafka Consumer Application
Kafka consumer consumes the data from Kafka Topics and writes them into database.
![image](https://github.com/user-attachments/assets/3df15f96-aa29-477a-8fad-96de8833178d)
This is where integrating Kafka with a database like PostgreSQL can be valuable. You can use Kafka to ingest and stream data, and then use PostgreSQL as a foundation for building apps and websites that use that data. This allows you to leverage the strengths of both technologies: Kafka for handling high volumes of streaming data and PostgreSQL for storing and querying structured data.
![image](https://github.com/user-attachments/assets/832f54d2-4bd1-4900-8c5a-cdb092bca9c6)


> In this example: pgAdmin is used, it is a web-based database management tool for PostgreSQL.
> Check to learn what is kafka: https://github.com/AysCeren/kafka_producer

## Kafka Consumer Application Initiation:
+ You may prefer to work with initalizer like spring.io
+ ![image](https://github.com/user-attachments/assets/ee14c177-4afd-4c29-bf2a-815d57968570)
+ After the program is ready, start with application.properties (under resources)
      - 1. It is important to work with StringDeserializer, if you want to consume your data as object.
      - 2. group-id should be determined, because kafka consumer is a multi consumer system, we need a UNIQUE subscriber/consumer id.
         - Postgres Connection:
         - 1. spring.datasource.url=jdbc:postgresql://localhost:5432/test give port for your pgAdmin
         - 2. Check the feautures such as name, username, password

  ![image](https://github.com/user-attachments/assets/3b726192-10ef-4355-8cbe-10b1634138a0)

## Repository Logic:

JPA repository helps to store data in RDBMS, pgAdmin in this example. 
We should create a ContactRepo interface, which extends JPARepository and JPARepository extends CRUDRepository. CRUDRepository provides us with save() method to write data into database.
