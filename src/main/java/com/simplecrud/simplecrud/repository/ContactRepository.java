package com.simplecrud.simplecrud.repository;

import com.simplecrud.simplecrud.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
    Contact findByEmail(String email);
    boolean existsContactByEmail(String email);
}
