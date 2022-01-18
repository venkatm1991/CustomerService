package com.pk.customer.service.kafka;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.pk.customer.domain.Address;
import com.pk.customer.domain.CustomerRequest;
import com.pk.customer.domain.CustomerRequest.CustomerStatusEnum;
import com.pk.customer.domain.Payload;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerPublisherServiceIntegrationTest {

	@Autowired
	CustomerPublisherService customerPublisherService;
	
	@Test
	public void publishMessageTest() {
		CustomerRequest customerRequest = new CustomerRequest ();
		
		customerRequest.setBirthdate("20-05-1991");
		customerRequest.setCountry("India");
		customerRequest.setCountryCode("IN");
		customerRequest.setCustomerNumber("HKJ73985IM");
		customerRequest.setCustomerStatus(CustomerStatusEnum.O);
		customerRequest.setEmail("user123@gmail.com");
		customerRequest.setFirstName("Venkat 12345");
		customerRequest.setLastName("Mandalapu 687");
		customerRequest.setMobileNumber(BigDecimal.valueOf(8573567678l));
		Address address=new Address();
		address.setAddressLine1("H-NO :728,HYD");
		address.setAddressLine2("INDIA");
		address.setStreet("KPHB");
		address.setPostalCode("78745");
		customerRequest.setAddress(address);
		Payload payload = new Payload();
		payload.setCustomerRequest(customerRequest);
		String response =customerPublisherService.publishMessage(payload);
		assertEquals("Customer message published to kafka topic", response);
		
		
	}
}
