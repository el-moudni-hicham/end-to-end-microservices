package dev.moudni.customerservice.web;

import dev.moudni.customerservice.dtos.CustomerDTO;
import dev.moudni.customerservice.entites.Customer;
import dev.moudni.customerservice.exeptions.CustomerNotFoundException;
import dev.moudni.customerservice.exeptions.ErrorMessage;
import dev.moudni.customerservice.repository.CustomerRepository;
import dev.moudni.customerservice.service.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import java.security.Principal;
import java.util.List;

@RestController @AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
    CustomerServiceImpl customerService;

    @GetMapping("/customers")
    //@PreAuthorize("hasRole('USER')")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.findCustomers();
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/customers/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id){
        try {
            CustomerDTO customerById = customerService.findCustomerById(id);
            return ResponseEntity.ok(customerById);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.internalServerError().body(new ErrorMessage(e.getMessage()));
        }
    }


    /*@GetMapping("/auth")
    public Principal authentication(Principal principal){
        return principal;
    }*/

}
