
package com.consumer.kafka.consumer;

import com.consumer.kafka.contactVM.Contact;
import com.consumer.kafka.repository.ContactRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class KafkaConsumer {

    @Autowired
    private ContactRepo repo; //to write to the Database
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "writeIntoTopic", groupId = "myGroup") //the group id visible in application.properties too.
    public void consume(String message) throws JsonProcessingException {
        LOGGER.info(String.format("Consumed message: %s", message));
        ObjectMapper mapper = new ObjectMapper();
        try {

            // covert JSON to Java object
            Contact ct = mapper.readValue(message, Contact.class);
            repo.save(ct);
            // output: Person{name='mkyong', age=20}
            System.out.println(ct);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        /*
        ObjectMapper objectMapper = new ObjectMapper(); //Consumed data has been converted into the object, it was in the form of JSON
        List<Contact> cts = Arrays.asList(objectMapper.readValue(message, Contact[].class));
        for (Contact ct : cts)
        {
            System.out.println(ct.toString());
            //rather than saving it in here I will implement a Scala file for data manipulation
            repo.save(ct);
        }
        */
        //saveAll
    }
}
