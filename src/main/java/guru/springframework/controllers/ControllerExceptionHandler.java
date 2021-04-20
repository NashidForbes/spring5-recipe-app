package guru.springframework.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    // need to declare @ResponseStatus as MVC conventional exception handling takes
    // precedence
/*    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception){
        log.error("Number format exception");
        log.error("exception: " + exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("400error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }*/
}
