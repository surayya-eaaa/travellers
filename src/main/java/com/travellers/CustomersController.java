package com.travellers;

//import com.bestseller.game.exceptions.GamersNotFoundException;

import com.travellers.jpa.Address;
import com.travellers.jpa.Customers;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomersController {

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private AddressRepository addressRepository;
   
    // Find
    @GetMapping("/Customers")
    @CrossOrigin
    List<Customers> findAllGamers() {
        return customersRepository.findAll();
    }

    @GetMapping("/Customers/Address")
    List<Address> findAllAddress() {
        return addressRepository.findAll();
    }

    @GetMapping("/Customers/Address/{id}")
    Collection<Customers> findGamersByGeograpgy(@PathVariable Long id) {
        return customersRepository.findAllGamersByGeography(id);
    }

    // Save
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            path = "/Customers",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    Customers newGamer(@RequestBody @Valid Customers customers) {
        return customersRepository.save(customers);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            path = "/Customers/Address",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    Address newAddress(@RequestBody @Valid Address address) {
        return addressRepository.save(address);
    }


    // Find
    @GetMapping("/Customers/{id}")
    Customers findOne(@PathVariable Long id) throws CustomersNotFoundException {
        return customersRepository.findById(id)
                .orElseThrow(() -> new CustomersNotFoundException(id));
    }

    // Save or update
    @PutMapping("/Customers/{id}")
    Customers saveOrUpdate(@RequestBody Customers newKonto, @PathVariable Long id) {

        return customersRepository.findById(id)
                .map(x -> {
                    x.setName(newKonto.getName());
                    x.setEmail(newKonto.getEmail());
                    return customersRepository.save(x);
                })
                .orElseGet(() -> {
                    newKonto.setId(id);
                    return customersRepository.save(newKonto);
                });
    }

    @DeleteMapping("/Customers/{id}")
    @CrossOrigin
    void deleteGamers(@PathVariable Long id) {
        customersRepository.deleteById(id);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }






}