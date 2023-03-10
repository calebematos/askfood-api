package com.calebematos.askfood.infrastructure.service.report;

public class ReportException extends RuntimeException {

    private static final long serialVersionUID = 1L;

     private ReportException(String message, Throwable cause) {
        super(message, cause);
    }

    public static RuntimeException of(String message, Throwable cause) {
         return new ReportException(message, cause);
    }
}
