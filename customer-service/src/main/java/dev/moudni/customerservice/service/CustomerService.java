package dev.moudni.customerservice.service;

import dev.moudni.customerservice.dtos.CustomerDTO;
import dev.moudni.customerservice.entites.Customer;
import dev.moudni.customerservice.exeptions.CustomerNotFoundException;
import dev.moudni.customerservice.exeptions.EmailAlreadyUsedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customer) throws EmailAlreadyUsedException;
    void deleteCustomer(CustomerDTO customer) throws CustomerNotFoundException;
    CustomerDTO updateCustomer(CustomerDTO customer) throws  CustomerNotFoundException, EmailAlreadyUsedException;
    List<CustomerDTO> findCustomers();
    CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException;

}
