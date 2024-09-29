package com.travellers;

import com.travellers.jpa.Customers;
import org.springframework.data.repository.CrudRepository;

public interface ValidatingRepository extends CrudRepository<Customers, Long> {}
