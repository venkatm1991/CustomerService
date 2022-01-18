package com.pk.customer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pk.customer.domain.CustomerRequest;
import com.pk.customer.domain.CustomerResponse;
import com.pk.customer.domain.ErrorResponse;
import com.pk.customer.domain.Payload;
import com.pk.customer.entity.AuditLogEntity;
import com.pk.customer.entity.ErrorLogEntity;
@Component
public class ObjectConvertUtil {
	@Autowired
	JsonUtil jsonUtil;
	
	@Autowired
	CustomerMaskUtil customerMaskUtil;
	
	public AuditLogEntity convertAuditLog(Payload payload) {
	
		CustomerRequest customerMaskRequest = customerMaskUtil.convert(payload.getCustomerRequest());
		
		payload.setCustomerRequest(customerMaskRequest);
		String maskPayloadRequest=jsonUtil.convertObjectToString(payload);
		
		AuditLogEntity auditLogEntity= new AuditLogEntity();
		auditLogEntity.setCustomerNumber(customerMaskRequest.getCustomerNumber());
		auditLogEntity.setPayload(maskPayloadRequest);
		return auditLogEntity;
	}
	
	public ErrorLogEntity convertErrorLog(Payload payload,String exceptionType, String exceptionMessage) {
		
		CustomerRequest customerMaskRequest = customerMaskUtil.convert(payload.getCustomerRequest());
		
		payload.setCustomerRequest(customerMaskRequest);
		String maskPayloadRequest=jsonUtil.convertObjectToString(payload);
		
		ErrorLogEntity errorLogEntity= new ErrorLogEntity();
		errorLogEntity.setPayload(maskPayloadRequest);
		errorLogEntity.setErrorDesc(exceptionMessage);
		errorLogEntity.setErrorType(exceptionType);
		
		return errorLogEntity;
	}
	
	public Payload converToPayload(CustomerRequest customerRequest, String tractionId,String activityId) {
		Payload payload= new Payload();
		payload.setCustomerRequest(customerRequest);
		payload.setTranctionId(tractionId);
		payload.setActivityId(activityId);
		return payload;
	}
	
	public CustomerResponse converToCustomerResponse(String status, String message) {
		CustomerResponse customerResponse= new CustomerResponse();
		customerResponse.setStatus(status);
		customerResponse.setMessage(message);
		return customerResponse;
	}
	
	public ErrorResponse converToErrorResponse(String status, String errorType,String errorMessage) {
		ErrorResponse errorResponse= new ErrorResponse();
		errorResponse.setStatus(status);
		errorResponse.setErrorType(errorType);
		errorResponse.setMessage(errorMessage);
		return errorResponse;
	}
	
}
