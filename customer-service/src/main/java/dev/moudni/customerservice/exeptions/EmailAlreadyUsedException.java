package dev.moudni.customerservice.exeptions;

public class EmailAlreadyUsedException extends Exception{
    public EmailAlreadyUsedException(String message){
        super(message);
    }
}
