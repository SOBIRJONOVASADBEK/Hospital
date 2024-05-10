package com.demo.service;

import com.demo.domain.Contacts;
import com.demo.domain.Doktor;
import com.demo.exception.InternalServerException;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.ContactsRepository;
import com.demo.response.ContactsResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.demo.response.ContactsResponse.mapToContacts;

@Service
public class ContactsService {
    private final ContactsRepository contactsRepository;

    public ContactsService(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

   public Contacts save(ContactsResponse contactsResponse){
        Contacts contacts=mapToContacts(contactsResponse);
        return contactsRepository.save(contacts);
   }
    public Contacts updateContacts(Long id, String email,String phoneNumber,String telegramLink) {
        Contacts contacts=contactsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Contacts not found"));
        if (email!=null) contacts.setEmail(email);
        if (phoneNumber!=null) contacts.setPhoneNumber(phoneNumber);
        if (telegramLink!=null) contacts.setTelegramLink(telegramLink);
        return contactsRepository.save(contacts);
    }
    public void delete(Long contactsId) {
        Optional<Contacts> theContact=contactsRepository.findById(contactsId);
        if (theContact.isEmpty()){
           contactsRepository.deleteById(contactsId);
        }
    }


}
