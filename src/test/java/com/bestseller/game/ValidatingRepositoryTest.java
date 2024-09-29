package com.bestseller.game;


import com.travellers.Levels;
import com.travellers.ValidatingRepository;
import com.travellers.jpa.Address;
import com.travellers.jpa.Customers;
import com.travellers.jpa.Trips;
import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ValidatingRepositoryTest {

    @Autowired
    private ValidatingRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void whenInputIsInvalid_thenThrowsException() {
        Customers customers = new Customers();
        customers.setName("foo");
        customers.setEmail("foocom.com");
        Address address = new Address();
        address.setStreet("s");
        address.setCountry("c");
        customers.setAddress(address);

        Set<Trips> games = new HashSet<Trips>();

        Trips g = new Trips();
        g.setName("Kirby");
        g.setLevel(Levels.PRO);

        Trips g1 = new Trips();
        g1.setName("Mario");
        g1.setLevel(Levels.NOOB);

        Trips g2 = new Trips();
        g2.setName("Minecraft");
        g2.setLevel(Levels.INVINCIBLE);

        games.add(g);
        games.add(g1);
        games.add(g2);

        customers.setTrips(games);


        assertThrows(ConstraintViolationException.class, () -> {
            repository.save(customers);
            entityManager.flush();
        });

    }
    @Test
    void findAll() {
        Iterable<Customers> all = repository.findAll();
        Assertions.assertTrue(all.spliterator().getExactSizeIfKnown() > 0);
    }

}

