package com.panamby.backlogmanager.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;
    private String transactionId;

    public ProductNotFoundException(String message) {
	this.message = message;
    }

    public ProductNotFoundException(String message, String transactionId) {
	super(message);
	this.message = message;
	this.transactionId = transactionId;
    }
}