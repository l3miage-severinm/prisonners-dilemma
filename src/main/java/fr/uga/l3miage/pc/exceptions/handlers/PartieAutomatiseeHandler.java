package fr.uga.l3miage.pc.exceptions.handlers;

import fr.uga.l3miage.pc.exceptions.rest.PartieAutomatiseeRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PartieAutomatiseeHandler {

    @ExceptionHandler(PartieAutomatiseeRestException.class)
    public ResponseEntity<String> handle(PartieAutomatiseeRestException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
