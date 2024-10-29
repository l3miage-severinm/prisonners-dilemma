package fr.uga.l3miage.pc.exceptions.handlers;

import fr.uga.l3miage.pc.exceptions.rest.PartieNbToursIncorrectRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PartieNbToursIncorrectHandler {
    @ExceptionHandler(PartieNbToursIncorrectRestException.class)
    public ResponseEntity<String> handle(PartieNbToursIncorrectRestException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
