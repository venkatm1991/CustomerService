package com.pk.customer.constants;

public class CustomerMaskConstants {
	public static final String MASK_CUSTOMER_NUMBER= "\\w(?=\\w{4})";
	public static final String MASK_BIRTH_DATE="";
	public static final String MASK_EMAIL="(?<=.{4}).(?=.*@)"; 
}
