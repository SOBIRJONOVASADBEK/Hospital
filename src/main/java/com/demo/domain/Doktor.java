package com.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Set;

@Entity
@Data
@Table(name = "doktor")
public class Doktor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String FIO;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "doktor_profession",
            joinColumns = {@JoinColumn(name = "doktor_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "profession_name", referencedColumnName = "name")}
    )
    private Set<Profession> professions;

    private String bio;
    @Lob
    private Blob photo;

    @OneToOne
    private Contacts contacts;

    private String address;

    private Double price;



}
