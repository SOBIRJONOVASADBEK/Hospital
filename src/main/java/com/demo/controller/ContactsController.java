package com.demo.controller;

import com.demo.domain.Contacts;
import com.demo.response.ContactsResponse;
import com.demo.service.ContactsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.demo.response.ContactsResponse.mapToContacts;

@RestController
@RequestMapping("/contacts")
public class ContactsController {
    private final ContactsService contactsService;

    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @PostMapping("/add/new-Contact")
    public ResponseEntity addContact(@RequestBody ContactsResponse contactsResponse){
        return ResponseEntity.ok(contactsService.save(contactsResponse));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@RequestParam Long id){
        contactsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateContacts(@RequestParam Long id,
                                         @RequestParam String email,
                                         @RequestParam String phoneNumber,
                                         @RequestParam String telegramLink){
        Contacts contacts=contactsService.updateContacts(id,email,phoneNumber,telegramLink);
        ContactsResponse contactsResponse=mapToContacts(contacts);
        return ResponseEntity.ok(contactsResponse);
    }


}
