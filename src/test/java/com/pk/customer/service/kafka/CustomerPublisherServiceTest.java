package com.pk.customer.service.kafka;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.pk.customer.domain.Address;
import com.pk.customer.domain.CustomerRequest;
import com.pk.customer.domain.CustomerRequest.CustomerStatusEnum;
import com.pk.customer.domain.Payload;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerPublisherServiceTest {

	
	@Autowired
	public CustomerPublisherService customerPublisherService;
	
	@MockBean
	private KafkaTemplate<String, Object> template;
	
	CustomerRequest customerRequest = null;
	Payload payload = null;
	
	@Before
    public void setup() {
		customerRequest = new CustomerRequest ();
		customerRequest.setBirthdate("28-08-1991");
		customerRequest.setCountry("India");
		customerRequest.setCountryCode("IN");
		customerRequest.setCustomerNumber("HKJ73985IM");
		customerRequest.setCustomerStatus(CustomerStatusEnum.O);
		customerRequest.setEmail("user1234@gmail.com");
		customerRequest.setFirstName("Venkat 12345");
		customerRequest.setLastName("Mandalapu 687");
		customerRequest.setMobileNumber(BigDecimal.valueOf(8573567678l));
		Address address=new Address();
		address.setAddressLine1("H-NO :728,HYD");
		address.setAddressLine2("INDIA");
		address.setStreet("KPHB");
		address.setPostalCode("78745");
		customerRequest.setAddress(address);
		
		payload = new Payload();
	    payload.setCustomerRequest(customerRequest);
	    payload.setActivityId("12T872H");
	    payload.setTranctionId("Tx28S879");
		
	}
	
	
	@Test
	public void getMessageTest() {
		customerPublisherService.publishMessage(payload);
	}
}
