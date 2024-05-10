package com.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "contacts")
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;

    private String email;

    private String telegramLink;
}
