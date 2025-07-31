package org.tuyetdang.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.tuyetdang.Entity.Customer;
import org.tuyetdang.Repository.CustomerRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    CustomerRepository customerRepository;

    public void addCustomer(Customer customer) {
        customerRepository.createCustomer(customer);
    }

}
