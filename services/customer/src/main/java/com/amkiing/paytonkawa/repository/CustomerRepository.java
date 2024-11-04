package com.amkiing.paytonkawa.repository;

import com.amkiing.paytonkawa.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
