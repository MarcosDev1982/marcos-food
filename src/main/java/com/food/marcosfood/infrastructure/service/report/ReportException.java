package com.food.marcosfood.infrastructure.service.report;


public class ReportException extends RuntimeException {


    private static final long serialVersionUID = 6673122303121117669L;

    public ReportException(String message) {
        super(message);
    }

    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }
}

