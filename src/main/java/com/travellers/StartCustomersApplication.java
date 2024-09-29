package com.travellers;

import com.travellers.jpa.Address;
import com.travellers.jpa.Customers;
import com.travellers.jpa.Trips;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class StartCustomersApplication {
	private static final Logger log = LoggerFactory.getLogger(StartCustomersApplication.class);

	public static void main(String[] args) {
        SpringApplication.run(StartCustomersApplication.class, args);
    }

	// init bean to insert into h2 database.
    @Bean
    public CommandLineRunner initDatabase(CustomersRepository repository) {
        return args -> {
          	for (long i = 1; i < 6; i ++) {
                Customers customers = new Customers();
                customers.setId(i);
                customers.setName("nickname" + i);
                customers.setEmail("email@email" + i + ".com");
                Address address = new Address();
                address.setId(i);
                address.setStreet("street" + i);
                address.setCountry("country" + i);

                customers.setAddress(address);
                Set<Trips> games = new HashSet<Trips>();

                Trips g = new Trips();
                g.setName("Greece, Athenes");
                g.setLevel(Levels.PRO);

                Trips g1 = new Trips();
                g1.setName("France, Paris");
                g1.setLevel(Levels.NOOB);

                Trips g2 = new Trips();
                g2.setName("USA, Seatle");
                g2.setLevel(Levels.INVINCIBLE);

                games.add(g);
                games.add(g1);
                games.add(g2);

                customers.setTrips(games);

                repository.save(customers);
    		}





        log.info("findAll(), expect 5 customers");
        log.info("-------------------------------");
        for (Customers customers : repository.findAll()) {
            log.info(customers.toString());
        }
        log.info("\n");

        // find game by ID
        Optional<Customers> gamer = repository.findById(1L);
            gamer.ifPresent(obj -> {
            log.info("Customers found with findById(1L):");
            log.info("--------------------------------");
            log.info(obj.toString());
            log.info("\n");
        });

        // find game by title
        log.info("Customers found with findByName('Customers G')");
        log.info("--------------------------------------------");
        repository.findByName("Customers G").forEach(b -> {
            log.info(b.toString());
            log.info("\n");
        });


        repository.deleteById(3L);
        log.info("Customers delete where ID = 2L");
        log.info("--------------------------------------------");
        // find all games
        log.info("findAll() again, expect 3 customers");
        log.info("-------------------------------");
        for (Customers game : repository.findAll()) {
            log.info(game.toString());
        }
        log.info("\n");
        };
    }
}
