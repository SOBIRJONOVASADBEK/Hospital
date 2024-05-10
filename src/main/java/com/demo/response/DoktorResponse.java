package com.demo.response;

import com.demo.domain.Contacts;
import com.demo.domain.Profession;
import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Set;
@Data
public class DoktorResponse {
    private Long Id;
    private String FIO;

    private Set<Profession> professions;

    private String bio;

    private String photo;

    private Contacts contacts;

    private String address;
    private Double price;


    public DoktorResponse(Long FIO, String bio, String photoBytes, Contacts contacts, Set<Profession> professions,String address,Double price) {
        this.FIO = String.valueOf(FIO);
        this.bio = bio;
        this.photo =photoBytes != null ? Base64.encodeBase64String(photoBytes.getBytes()):null;
        this.contacts=contacts;
        this.address=address;
        this.price=price;
    }

    public DoktorResponse(Long id, String FIO, Set<Profession> professions, String bio,  byte[] photoBytes,String address,Contacts contacts,double price) {
        Id = id;
        this.FIO = FIO;
        this.professions = professions;
        this.bio = bio;
        this.photo =photoBytes != null ? Base64.encodeBase64String(photoBytes):null;
        this.address=address;
        this.contacts=contacts;
        this.price=price;
    }
}
