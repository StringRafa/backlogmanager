package com.panamby.backlogmanager.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.panamby.backlogmanager.exception.ProductNotFoundException;
import com.panamby.backlogmanager.exception.SubscriberException;
import com.panamby.backlogmanager.exception.dto.BacklogManagerGeneralDataErrorResponse;
import com.panamby.backlogmanager.exception.dto.BacklogManagerGeneralErrorResponse;
import com.panamby.backlogmanager.exception.dto.BacklogManagerGenericDataErrorResponse;
import com.panamby.backlogmanager.exception.dto.BacklogManagerGenericErrorResponse;
import com.panamby.backlogmanager.utils.StatusConstantUtils;

import lombok.extern.slf4j.Slf4j;


@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler extends ExceptionHandlerExceptionResolver {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest) {

		log.error(String.format("EXCEPTION [%s] - MESSAGE [%s]", ex.getClass(), ex.getMessage()));

		return new ResponseEntity<>(new BacklogManagerGenericErrorResponse(new BacklogManagerGenericDataErrorResponse("Unexpected error occurs",
				"Internal server error.", StatusConstantUtils.FAIL)), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = SubscriberException.class)
	public ResponseEntity<Object> handleSubscriberExceptions(SubscriberException ex, WebRequest webRequest) {

		log.error(ex.getMessage());

		return new ResponseEntity<>(new BacklogManagerGeneralErrorResponse(new BacklogManagerGeneralDataErrorResponse(ex.getTransactionId(),"Unexpected error occurs",
				ex.getMessage(), StatusConstantUtils.FAIL)), HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex, WebRequest webRequest) {

		return new ResponseEntity<>(new BacklogManagerGeneralErrorResponse(new BacklogManagerGeneralDataErrorResponse(ex.getTransactionId(),
				"Product isn't in database", ex.getMessage(), StatusConstantUtils.FAIL)), HttpStatus.NOT_FOUND);
    }

}
