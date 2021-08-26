package com.simplecrud.simplecrud.service;

import com.simplecrud.simplecrud.model.Contact;
import com.simplecrud.simplecrud.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> findAll(){
        return (List<Contact>) contactRepository.findAll();
    }
    @Override
    public void save(Contact contact){
        contactRepository.save(contact);
    }

    @Override
    public void update(String email, String newEmail){
        Contact oldContact = contactRepository.findByEmail(email);
        oldContact.setEmail(newEmail);
        contactRepository.save(oldContact);
        contactRepository.delete(contactRepository.findByEmail(email));
    }

    @Override
    public Contact getContactByEmail(String email){
        return contactRepository.findByEmail(email);
    }

    @Override
    public boolean existsContactByEmail(String email) {
        return contactRepository.existsContactByEmail(email);
    }

    @Override
    public void deleteByEmail(String email) {
        contactRepository.delete(contactRepository.findByEmail(email));
    }
}
