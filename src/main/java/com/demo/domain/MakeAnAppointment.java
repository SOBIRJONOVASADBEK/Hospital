package com.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "profession")
public class MakeAnAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String complaint;
}
