package com.simplecrud.simplecrud.service;

import com.simplecrud.simplecrud.model.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    void save(Contact contact);
    void update(String email, String newEmail);
    Contact getContactByEmail(String email);
    boolean existsContactByEmail(String email);
    void deleteByEmail(String email);
}
