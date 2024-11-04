package com.amkiing.paytonkawa.service;

import com.amkiing.paytonkawa.customer.Customer;
import com.amkiing.paytonkawa.dto.CustomerMapper;
import com.amkiing.paytonkawa.dto.CustomerRequest;
import com.amkiing.paytonkawa.dto.CustomerResponse;
import com.amkiing.paytonkawa.exception.CustomerNotFoundException;
import com.amkiing.paytonkawa.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    public List<CustomerResponse> findAllCustomers(){
        return customerRepository.findAll().stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public String createCustomer(CustomerRequest request){
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id()).
                orElseThrow(() -> new CustomerNotFoundException(
                    String.format("Cannot update customer: No customer with this ID = %s", request.id())
                ));
        mergerCustomer(customer, request);
        customerRepository.save(customer);

    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank((request.firstname()))){
            customer.setFirstname(request.firstname());
        }
        if (StringUtils.isNotBlank((request.lastname()))){
            customer.setLastname(request.lastname());
        }
        if (StringUtils.isNotBlank((request.email()))){
            customer.setEmail(request.email());
        }
        if (request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public Boolean existById(String customerId) {
        return customerRepository.findById(customerId).isPresent();

    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId).
               map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(("No customer found with this ID")));

    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
