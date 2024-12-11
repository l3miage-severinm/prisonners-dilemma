package fr.uga.l3miage.pc.exceptions.handlers;

import fr.uga.l3miage.pc.exceptions.rest.StrategyAdaptingRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StrategyAdaptingHandler {
    @ExceptionHandler(StrategyAdaptingRestException.class)
    public ResponseEntity<String> handle(StrategyAdaptingRestException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
