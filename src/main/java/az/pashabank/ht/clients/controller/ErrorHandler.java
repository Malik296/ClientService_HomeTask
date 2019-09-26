package az.pashabank.ht.clients.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public String handle(MethodArgumentNotValidException ex){
        logger.error("Format Error: {}", ex.getMessage());
        return "Format Error";
    }


    @ExceptionHandler(NullPointerException.class)
    public String handle(NullPointerException ex){
        logger.error("Format Error: {}", ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(NumberFormatException.class)
    public String handle(NumberFormatException ex){
        logger.error("Format Error: {}", ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(RuntimeException.class)
    public String handle(RuntimeException ex){
        logger.error("Format Error: {}", ex.getMessage());
        return "Illegal Id";
    }
}
