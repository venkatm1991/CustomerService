package com.pk.customer.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.pk.customer.domain.Payload;
import com.pk.customer.interfaces.ICustomerConsumerService;
import com.pk.customer.service.CustomerService;

@Service
public class CustomerConsumerService implements ICustomerConsumerService {

  @Autowired CustomerService customerService;

  @Override
  @KafkaListener(
    groupId = "customer-service-1",
    topics = "customer_topic",
    containerFactory = "consumerKafkaListenerContainerFactory"
  )
  public void getMessage(Payload payload) {
    customerService.saveAuditData(payload);
  }
}
