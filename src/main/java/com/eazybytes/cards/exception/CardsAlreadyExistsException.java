package com.eazybytes.cards.exception;

public class CardsAlreadyExistsException extends RuntimeException {
    public CardsAlreadyExistsException(String message) {
        super(message);
    }
}
