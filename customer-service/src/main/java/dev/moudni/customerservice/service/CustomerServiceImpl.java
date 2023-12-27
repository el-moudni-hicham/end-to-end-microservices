package dev.moudni.customerservice.service;

import dev.moudni.customerservice.dtos.CustomerDTO;
import dev.moudni.customerservice.entites.Customer;
import dev.moudni.customerservice.exeptions.CustomerNotFoundException;
import dev.moudni.customerservice.exeptions.EmailAlreadyUsedException;
import dev.moudni.customerservice.mapper.CustomerMapper;
import dev.moudni.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO request) throws EmailAlreadyUsedException {
        if(customerRepository.checkIfEmailExists(request.getEmail()))
            throw new EmailAlreadyUsedException("Email Already Used : " + request.getEmail());
        Customer c = CustomerMapper.MAPPER.toCustomer(request);
        Customer savedCustomer = customerRepository.save(c);
        CustomerDTO customerDTO = CustomerMapper.MAPPER.toCustomerDTO(savedCustomer);
        return customerDTO;
    }

    @Override
    public void deleteCustomer(CustomerDTO customer) throws CustomerNotFoundException {

    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customer) throws CustomerNotFoundException, EmailAlreadyUsedException {
        return null;
    }

    @Override
    public List<CustomerDTO> findCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customersDTO = new ArrayList<>();
        customers.forEach(customer -> {
            CustomerDTO customerDTO = CustomerMapper.MAPPER.toCustomerDTO(customer);
            customersDTO.add(customerDTO);
        });
        return customersDTO;
    }

    @Override
    public CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null)
            throw new CustomerNotFoundException(String.format("Customer with id %s Not Found", id));
        return CustomerMapper.MAPPER.toCustomerDTO(customer);
    }
}
