package com.example.demospringboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "addresses")
@Getter @Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String country;

    private String city;

    private String zipCode;

    @JsonIgnore
    @Column(name = "is_deleted")
    private boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Employee> employees;

}
