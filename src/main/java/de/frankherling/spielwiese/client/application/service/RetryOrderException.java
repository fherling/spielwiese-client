package de.frankherling.spielwiese.client.application.service;

public class RetryOrderException extends RuntimeException {
    public RetryOrderException(String message) {
        super(message);
    }
}
