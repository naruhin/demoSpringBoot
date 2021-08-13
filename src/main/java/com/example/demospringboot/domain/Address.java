package com.example.demospringboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "addresses")
@Getter @Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String country;

    private String city;

    private String zipCode;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Employee> employees;

    public Address(String country, String city, String zipCode) {
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
    }
}
