package com.travellers;

import com.travellers.jpa.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CustomersService {
    @Autowired
    private final CustomersRepository repository;

    public CustomersService(CustomersRepository repository) {
        this.repository = repository;
    }

    public List<Customers> findAll() {
            return repository.findAll();
        }

        public Optional<Customers> findById(Long id) {
            return repository.findById(id);
        }

        public Customers save(Customers game) {
            return repository.save(game);
        }

        public void deleteById(Long id) {
            repository.deleteById(id);
        }
    }

