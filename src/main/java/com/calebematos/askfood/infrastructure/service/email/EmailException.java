package com.calebematos.askfood.infrastructure.service.email;

public class EmailException extends RuntimeException {

    private static final long serialVersionUID = 1L;

     private EmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public static EmailException of(String message, Throwable cause) {
         return new EmailException(message, cause);
    }
}
