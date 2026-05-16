package org.skypro.skyshop.exception;

public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException(String massage) {
        super(massage);
    }
}
