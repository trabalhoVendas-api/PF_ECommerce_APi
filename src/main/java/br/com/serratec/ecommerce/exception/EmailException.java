package br.com.serratec.ecommerce.exception;

public class EmailException extends RuntimeException {
    public EmailException(String message) {
        super(message);
    }
}