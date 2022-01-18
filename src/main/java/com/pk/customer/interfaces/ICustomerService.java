package com.pk.customer.interfaces;

import com.pk.customer.domain.Payload;

public interface ICustomerService {
	public void saveAuditData(Payload payload);
	public void saveErrorData(Payload payload,String exceptionType, String exceptionMessage);
}
