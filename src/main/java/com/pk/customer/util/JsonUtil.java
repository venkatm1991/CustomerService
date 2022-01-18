package com.pk.customer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pk.customer.exception.CustomerServiceRequestException;

@Component
public class JsonUtil {
	
@Autowired
private ObjectMapper objectMapper;


public String convertObjectToString(Object object) throws CustomerServiceRequestException {
	try {
		return objectMapper.writeValueAsString(object);
	} catch (JsonProcessingException e) {
		throw new CustomerServiceRequestException(e.getMessage(),e);
	}
}


@SuppressWarnings("unchecked")
public Object convertStringToObject(String json, Class c) throws CustomerServiceRequestException {
	try {
		return objectMapper.readValue(json, c);
	} catch (JsonProcessingException e) {
		throw new CustomerServiceRequestException(e.getMessage(),e);
	}
}
}
