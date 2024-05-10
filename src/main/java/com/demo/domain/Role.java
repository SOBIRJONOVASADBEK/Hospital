package com.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Data
@Table(name = "role")
public class Role implements Serializable {
    @Id
    private String name;

}
