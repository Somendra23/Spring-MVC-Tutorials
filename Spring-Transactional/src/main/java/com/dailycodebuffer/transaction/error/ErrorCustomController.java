package com.dailycodebuffer.transaction.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorCustomController extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleExceptionGeneric(Exception ex, WebRequest request) throws Exception {
		if (ex instanceof HttpRequestMethodNotSupportedException subEx) {
			return handleHttpRequestMethodNotSupported(subEx, subEx.getHeaders(), subEx.getStatusCode(), request);
		}
		
		// Create a generic error response
		ErrorResponsee errorResponse = new ErrorResponsee("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
}
}


class ErrorResponsee {
    private String message;
    private int status;

    public ErrorResponsee(String message, int status) {
        this.message = message;
        this.status = status;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
