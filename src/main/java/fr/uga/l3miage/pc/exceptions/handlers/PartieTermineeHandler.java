package fr.uga.l3miage.pc.exceptions.handlers;

import fr.uga.l3miage.pc.exceptions.rest.PartieTermineeRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PartieTermineeHandler {
    @ExceptionHandler(PartieTermineeRestException.class)
    public ResponseEntity<String> handle(PartieTermineeRestException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
