package com.pk.customer.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pk.customer.constants.CustomerMaskConstants;
import com.pk.customer.domain.CustomerRequest;

@Component
public class CustomerMaskUtil {
	private static final  Logger log = LoggerFactory.getLogger(CustomerMaskUtil.class);

	public CustomerRequest convert(CustomerRequest customerRequest) {
		final String maskChar="*";
		CustomerRequest customerMaskRequest = new CustomerRequest ();
		
		customerMaskRequest.setAddress(customerRequest.getAddress());
		customerMaskRequest.setBirthdate(maskData(customerRequest.getBirthdate(),4,maskChar));
		customerMaskRequest.setCountry(customerRequest.getCountry());
		customerMaskRequest.setCountryCode(customerRequest.getCountryCode());
		customerMaskRequest.setCustomerNumber(customerRequest.getCustomerNumber().replaceAll(CustomerMaskConstants.MASK_CUSTOMER_NUMBER, maskChar));
		customerMaskRequest.setCustomerStatus(customerRequest.getCustomerStatus());
		customerMaskRequest.setEmail(customerRequest.getEmail().replaceAll(CustomerMaskConstants.MASK_EMAIL, maskChar));
		customerMaskRequest.setFirstName(customerRequest.getFirstName());
		customerMaskRequest.setLastName(customerRequest.getLastName());
		customerMaskRequest.setMobileNumber(customerRequest.getMobileNumber());
		log.info("customerMaskRequest {}",customerMaskRequest);
		return customerMaskRequest;
	}
	
	public String maskData(String value, int nonMaskLength, String maskChar) {
		int firstNonMaskIndex=Math.max(0, value.length()-nonMaskLength);
		return Stream.generate(() -> maskChar)
		.limit(firstNonMaskIndex)
		.collect(Collectors.joining())
		.concat(value.substring(firstNonMaskIndex,value.length()));
	}
	
}
