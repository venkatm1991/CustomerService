package com.pk.customer.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.pk.customer.domain.Payload;
import com.pk.customer.interfaces.ICustomerPublisherService;

@Service
public class CustomerPublisherService implements ICustomerPublisherService{
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	private String topic = "customer_topic";
	String message = "Customer message published to kafka topic";
	@Override
	public String publishMessage(Payload payload) {
		template.send(topic,payload);
		 return message;
	}
}
