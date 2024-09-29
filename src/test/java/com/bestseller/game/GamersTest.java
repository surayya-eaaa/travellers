package com.bestseller.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.travellers.CustomersRepository;
import com.travellers.Levels;
import com.travellers.jpa.Address;
import com.travellers.jpa.Customers;
import com.travellers.jpa.Trips;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GamersTest {
    @Autowired
    private CustomersRepository gamerRepository;

    @Test
    public void testFindById() {
        Customers gamer = getGamer();
        gamerRepository.save(gamer);
        Customers result = gamerRepository.findById(gamer.getId()).get();
        assertEquals(gamer.getId(), result.getId());
    }
    @Test
    public void testFindAll() {
        Customers gamer = getGamer();
        gamerRepository.save(gamer);
        List<Customers> result = new ArrayList<>();
        gamerRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 4);
    }
    private Customers getGamer() {
        Customers customers = new Customers();
        customers.setId(1L);
        customers.setName("nickname");
        customers.setEmail("email@email.com");
        Address address = new Address();
        address.setStreet("street");
        address.setCountry("country");

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
        return customers;
    }
}