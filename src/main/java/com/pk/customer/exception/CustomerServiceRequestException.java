package com.pk.customer.exception;

public class CustomerServiceRequestException extends RuntimeException{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public  CustomerServiceRequestException(String message) {
	super(message);
}	
public  CustomerServiceRequestException(String  message,Throwable t) {
	super(message,t);
}	
public  CustomerServiceRequestException(Throwable t) {
	super(t);
}	

}
