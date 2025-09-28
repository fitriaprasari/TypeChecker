package org.example.typchecker.semantic;

public class TypeError extends RuntimeException {
    public TypeError(String message) {
        super(message);
    }
}