package fr.uga.l3miage.pc.web.exceptions.rest;

public class PartieInexistanteRestException extends RuntimeException {

    public PartieInexistanteRestException(String message) {
        super(message);
    }
}
