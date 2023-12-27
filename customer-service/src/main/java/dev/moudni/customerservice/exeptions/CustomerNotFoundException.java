package dev.moudni.customerservice.exeptions;

public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException(String message){
        super(message);
    }
}
