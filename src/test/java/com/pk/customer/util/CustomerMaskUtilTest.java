package com.pk.customer.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.pk.customer.domain.Address;
import com.pk.customer.domain.CustomerRequest;
import com.pk.customer.domain.CustomerRequest.CustomerStatusEnum;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerMaskUtilTest {

  @Autowired private CustomerMaskUtil customerMaskUtil;

  @Test
  public void convertTest() {
    CustomerRequest customerRequest = new CustomerRequest();

    customerRequest.birthdate("28-08-1991");
    customerRequest.country("India");
    customerRequest.countryCode("IN");
    customerRequest.customerNumber("HKJ73985IM");
    customerRequest.customerStatus(CustomerStatusEnum.O);
    customerRequest.email("user1234@gmail.com");
    customerRequest.firstName("Venkat 12345");
    customerRequest.lastName("Mandalapu 687");
    customerRequest.mobileNumber(BigDecimal.valueOf(8573567678l));
    Address address = new Address();
    address.addressLine1("H-NO :728,HYD");
    address.addressLine2("INDIA");
    address.street("KPHB");
    address.postalCode("78745");
    customerRequest.address(address);

    CustomerRequest customerMaskRequest = customerMaskUtil.convert(customerRequest);
    assertEquals("******85IM", customerMaskRequest.getCustomerNumber());
    assertEquals("user****@gmail.com", customerMaskRequest.getEmail());
    assertEquals("******1991", customerMaskRequest.getBirthdate());
    assertNotEquals(customerRequest.toString(), customerMaskRequest.toString());
    assertFalse(customerRequest.equals(customerMaskRequest));
    assertNotEquals(customerRequest.hashCode(), customerMaskRequest.hashCode());
    assertTrue(customerRequest.equals(customerRequest));
    assertEquals(CustomerStatusEnum.O, customerMaskRequest.getCustomerStatus());
  }

  @Test
  public void maskDataTest() {
    assertEquals("******1991", customerMaskUtil.maskData("28-08-1991", 4, "*"));
  }
}
