package com.springboot.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.employee.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
