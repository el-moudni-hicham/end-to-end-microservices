package dev.moudni.customerservice.mapper;

import dev.moudni.customerservice.dtos.CustomerDTO;
import dev.moudni.customerservice.entites.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);
    CustomerDTO toCustomerDTO(Customer customer);
    Customer toCustomer(CustomerDTO customerDTO);
}
