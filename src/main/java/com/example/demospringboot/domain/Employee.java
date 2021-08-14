package com.example.demospringboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter @Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private String email;

    @JsonIgnore
    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "address_fk")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Address address;

}
