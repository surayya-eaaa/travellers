package com.travellers;

import com.travellers.jpa.Address;
import com.travellers.jpa.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {
    List<Customers> findByCountry(String country);

}