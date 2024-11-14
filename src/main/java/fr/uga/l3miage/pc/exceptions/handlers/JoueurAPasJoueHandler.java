package fr.uga.l3miage.pc.exceptions.handlers;

import fr.uga.l3miage.pc.exceptions.rest.JoueurADejaJoueRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JoueurAPasJoueHandler {
    @ExceptionHandler(JoueurADejaJoueRestException.class)
    public ResponseEntity<String> handle(JoueurADejaJoueRestException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
