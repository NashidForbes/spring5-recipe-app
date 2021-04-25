package guru.springframework.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;

// a global handler for bind exceptions
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    // need to declare @ResponseStatus as MVC conventional exception handling takes
    // precedence
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public String handleNumberFormat(Exception exception, Model model){
        log.error("Handling Binding Exception");
        log.error("exception: " + exception.getMessage());

        model.addAttribute("exception", exception);

        return "404error";
    }
}
