package com.travellers;

import com.travellers.jpa.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CustomersRepository extends JpaRepository<Customers,Long> {

    // it works if it matches the game field name
    List<Customers> findByName(String name);
    // No prepared statements needed
    @Query("SELECT g FROM Customers g WHERE g.email like :email")
    List<Customers> findByEmail(@Param("email") String email);

    //find all customers by geography
    @Query(value = "SELECT * FROM customers Inner Join Address on gamers_id = address_id where address_id = :address_id", nativeQuery = true)
    Collection<Customers> findAllGamersByGeography(@Param("address_id") long address_id);
}
