package com.consumer.kafka.repository;

import com.consumer.kafka.contactVM.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ContactRepo extends JpaRepository<Contact, Integer> {
}
