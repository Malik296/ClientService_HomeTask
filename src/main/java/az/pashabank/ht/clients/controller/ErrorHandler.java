package az.pashabank.ht.clients.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public String handle(MethodArgumentNotValidException ex){
        System.out.println(ex.getMessage());
        return "Error var";
    }


    @ExceptionHandler(NullPointerException.class)
    public String handle(NullPointerException ex){
        System.out.println(ex.getMessage());
        return "Error var";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String handle(NumberFormatException ex){
        System.out.println(ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(RuntimeException.class)
    public String handle(RuntimeException ex){
        System.out.println(ex.getMessage());
        return "Illegal Id";
    }
}
