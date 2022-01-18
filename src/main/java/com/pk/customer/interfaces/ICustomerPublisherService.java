package com.pk.customer.interfaces;

import com.pk.customer.domain.Payload;

public interface ICustomerPublisherService {
	public String publishMessage(Payload payload);
}
