package com.demo.response;

import com.demo.domain.Contacts;
import com.demo.repository.ContactsRepository;
import lombok.Data;

@Data
public class ContactsResponse {
    private Long id;

    private String phoneNumber;

    private String email;

    private String telegramLink;


    public static Contacts mapToContacts(ContactsResponse contactsResponse){
        Contacts contacts=new Contacts();
        contacts.setEmail(contactsResponse.getEmail());
        contacts.setPhoneNumber(contactsResponse.getPhoneNumber());
        contacts.setTelegramLink(contactsResponse.getTelegramLink());
        return contacts;
    }
    public static ContactsResponse mapToContacts(Contacts contactsResponse){
        ContactsResponse contacts=new ContactsResponse();
        contacts.setEmail(contactsResponse.getEmail());
        contacts.setPhoneNumber(contactsResponse.getPhoneNumber());
        contacts.setTelegramLink(contactsResponse.getTelegramLink());
        return contacts;
    }
}
