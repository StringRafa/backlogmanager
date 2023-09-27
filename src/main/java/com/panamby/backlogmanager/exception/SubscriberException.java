package com.panamby.backlogmanager.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SubscriberException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private String status;
    private String message;
    private String transactionId;
    private String id;
    
    public SubscriberException(String message) {
        super(message);
        this.message = message;
    }

    public SubscriberException(String message, String status, String transactionId, String msisdn) {
        super(message);
        this.message = message;
        this.status = status;
        this.transactionId = transactionId;
        this.id = msisdn;
    }
}
