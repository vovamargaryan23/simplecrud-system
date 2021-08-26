package com.simplecrud.simplecrud.controller;

import com.simplecrud.simplecrud.model.Contact;
import com.simplecrud.simplecrud.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public List getContacts(){
        return contactService.findAll();
    }

    @GetMapping("/contacts/{contact}")
        public Contact getContact(@PathVariable("contact") String email){
            if(contactService.existsContactByEmail(email)){
                return contactService.getContactByEmail(email);
        }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Email not found!");
    }

    @PostMapping("/contacts")
    public void addContact(@RequestBody Contact newContact){
        if(contactService.existsContactByEmail(newContact.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This Email is already in Database");
        }
        contactService.save(newContact);
    }

    @DeleteMapping("/contacts/{contactEmail}")
        public void deleteContact(@PathVariable("contactEmail") String email){
            if(!(contactService.existsContactByEmail(email))){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found");
            }
            contactService.deleteByEmail(email);
        }

    @PatchMapping(value = "/contacts/{contactEmail}", consumes = "application/json")
    public void updateContact(@PathVariable String contactEmail, @RequestBody Contact contact){
        if(!(contactService.existsContactByEmail(contactEmail))){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Email not found!");
        }
        contactService.update(contactEmail, contact.getEmail());
    }
}
