package fr.uga.l3miage.pc.web.exceptions.handlers;

import fr.uga.l3miage.pc.web.exceptions.rest.PartieInexistanteRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PartieInexistanteHandler {
    @ExceptionHandler(PartieInexistanteRestException.class)
    public ResponseEntity<String> handle(PartieInexistanteRestException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
