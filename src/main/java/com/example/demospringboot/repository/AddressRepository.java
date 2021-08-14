package com.example.demospringboot.repository;

import com.example.demospringboot.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List <Address> findAllByDeletedIsFalse();

    List <Address> getAllByDeletedIsFalse();

    Address findByIdAndDeletedIsFalse(Long id);
}
