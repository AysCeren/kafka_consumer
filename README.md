# Kafka Consumer Application
Kafka consumer consumes the data from Kafka Topics and writes them into database.
![image](https://github.com/user-attachments/assets/3df15f96-aa29-477a-8fad-96de8833178d)
This is where integrating Kafka with a database like PostgreSQL can be valuable. You can use Kafka to ingest and stream data, and then use PostgreSQL as a foundation for building apps and websites that use that data. This allows you to leverage the strengths of both technologies: Kafka for handling high volumes of streaming data and PostgreSQL for storing and querying structured data.
![image](https://github.com/user-attachments/assets/832f54d2-4bd1-4900-8c5a-cdb092bca9c6)


> In this example: pgAdmin is used, it is a web-based database management tool for PostgreSQL.
> Check to learn what is kafka: https://github.com/AysCeren/kafka_producer

## Kafka Consumer Application Initiation:
+ You may prefer to work with initalizer like spring.io
![image](https://github.com/user-attachments/assets/ee14c177-4afd-4c29-bf2a-815d57968570)

 After the program is ready, start with application.properties (under resources)
 1. It is important to work with StringDeserializer, if you want to consume your data as object.
 2. group-id should be determined, because kafka consumer is a multi consumer system, we need a UNIQUE subscriber/consumer id.
         - Postgres Connection:
         - 1. spring.datasource.url=jdbc:postgresql://localhost:5432/test give port for your pgAdmin
         - 2. Check the feautures such as name, username, password

  ![image](https://github.com/user-attachments/assets/3b726192-10ef-4355-8cbe-10b1634138a0)

```
# application.properties
      spring.application.name=kafka-consumer
      server.port=9090
      spring.kafka.consumer.bootstrap-servers=localhost:9092
      spring.kafka.consumer.group-id="myGroup"
      spring.kafka.consumer.auto-offset-reset= earliest
      spring.datasource.url=jdbc:postgresql://localhost:5432/test
      spring.datasource.name=demo
      spring.datasource.username=postgres
      spring.datasource.password=postgres
```


## Repository Logic:

JPA repository helps to store data in RDBMS, pgAdmin in this example. 
We should create a ContactRepo interface, which extends JPARepository and JPARepository extends CRUDRepository. CRUDRepository provides us with save() method to write data into database. So, I have created a repository package and open a new interface which is called ContactRepo and extends JPARepository.

## Contact DTO:
The project has a Contact class, which has attributes corresponding to kafka's topic. There is an important 3 configuration
```
@Data
@Entity //it will be an entity
@Table(name = "contacts") // VERY IMPORTANT

```

These 'Annotations', make our class ready to be converted into a table, and the name of the table will be proper to our database. 
> Id will be unique, that's why we are annoting as '@Id', so in the table it will be a Primary Key. 'GeneratedValue provide auto-incremented id, so user will not enter an ID, and ID won't be duplicated.
```
@Id //determines the primary key for the contacts table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //very important! this code saved the program from duplicate key error
    private int id; //which is id
    @Setter
    @Getter
    private String firstname;
    @Setter
    @Getter
    private String lastname;
    @Setter
    @Getter
      .
      .
      .
```

## Kafka Consumer Class:

@KafkaListener will continiously listen the kafka topics whether an event has occured or there is a change. 

```
 @Autowired
    private ContactRepo repo; //to write to the Database
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
```
####### repo is ready to save data to database.

```
    @KafkaListener(topics = "writeIntoTopic", groupId = "myGroup") //the group id visible in application.properties too.
    public void consume(String message) throws JsonProcessingException {
        LOGGER.info(String.format("Consumed message: %s", message));
        ObjectMapper mapper = new ObjectMapper();
        try {
            // convert JSON to Java object
            Contact ct = mapper.readValue(message, Contact.class);
            repo.save(ct);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

```

**Kafka Listener will read data from the topic, called as writeIntoTopic, spark wrote the processed data to this topic.
JSON data is consumed as String, it is converted into object type in following lines with the usage of 'ObjectMapper' class. After ct object is mapped, repo will save it into postgreSQL DataBase.

You should consider:
> Start your kafka servers
> After you had a connection to the database, you should start your database

## What about PostgreSQL table content:
The 'Contacts' table have been created, proper to the entities. 
```
    create table contacts
    (
    	id SERIAL PRIMARY KEY,
    	firstname varchar(255),
    	lastname varchar(255),
    	address varchar(255),
    	phone varchar(255)
     loginDate Date,
     birthDay Date,
     age int
    )
```

Last Look:
![image](https://github.com/user-attachments/assets/896150a2-93d9-44c1-aa58-34feb59fc6b0)


