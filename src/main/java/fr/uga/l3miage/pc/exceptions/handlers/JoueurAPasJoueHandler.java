package fr.uga.l3miage.pc.exceptions.handlers;

import fr.uga.l3miage.pc.exceptions.rest.JoueurAPasJoueRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JoueurAPasJoueHandler {
    @ExceptionHandler(JoueurAPasJoueRestException.class)
    public ResponseEntity<String> handle(JoueurAPasJoueRestException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
