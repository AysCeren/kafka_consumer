
package com.consumer.kafka.consumer;
//imports
import com.consumer.kafka.contactVM.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class JsonKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "test", groupId = "myGroup")
    public void Consume(Contact contact){
        LOGGER.info(String.format("Consumed Contact %s", contact.toString()));
        System.out.println("Consumed Contact " + contact.toString());
    }
}
