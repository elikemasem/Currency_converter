package com.dexwin.currencyconverter.DTO;

import java.time.LocalDateTime;


public class ResponseEntity {
    private int statusCode;
    private String message;
    private Object body;
    private LocalDateTime timestamp;
}

