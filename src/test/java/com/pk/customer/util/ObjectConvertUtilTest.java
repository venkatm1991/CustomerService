package com.pk.customer.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pk.customer.domain.Address;
import com.pk.customer.domain.CustomerRequest;
import com.pk.customer.domain.CustomerRequest.CustomerStatusEnum;
import com.pk.customer.domain.CustomerResponse;
import com.pk.customer.domain.ErrorResponse;
import com.pk.customer.domain.Payload;
import com.pk.customer.entity.AuditLogEntity;
import com.pk.customer.entity.ErrorLogEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectConvertUtilTest {

  @Autowired ObjectConvertUtil objectConvertUtil;

  CustomerRequest customerRequest = null;
  Payload payload = null;

  @Before
  public void setup() {
    customerRequest = new CustomerRequest();
    customerRequest.setBirthdate("28-08-1991");
    customerRequest.setCountry("India");
    customerRequest.setCountryCode("IN");
    customerRequest.setCustomerNumber("HKJ73985IM");
    customerRequest.setCustomerStatus(CustomerStatusEnum.O);
    customerRequest.setEmail("user1234@gmail.com");
    customerRequest.setFirstName("Venkat 12345");
    customerRequest.setLastName("Mandalapu 687");
    customerRequest.setMobileNumber(BigDecimal.valueOf(8573567678l));
    Address address = new Address();
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
  public void convertAuditLogTest() {
    assertTrue(objectConvertUtil.convertAuditLog(payload) instanceof AuditLogEntity);
    assertEquals("******85IM", objectConvertUtil.convertAuditLog(payload).getCustomerNumber());
  }

  @Test
  public void convertErrorLogTest() {
    assertTrue(
        objectConvertUtil.convertErrorLog(payload, "Parsing Error", "json Parsing Error")
            instanceof ErrorLogEntity);
    assertEquals(
        "Parsing Error",
        objectConvertUtil
            .convertErrorLog(payload, "Parsing Error", "json Parsing Error")
            .getErrorType());
    assertEquals(
        "json Parsing Error",
        objectConvertUtil
            .convertErrorLog(payload, "Parsing Error", "json Parsing Error")
            .getErrorDesc());
  }

  @Test
  public void converToPayloadTest() {
    assertTrue(
        objectConvertUtil.converToPayload(customerRequest, "YTy768", "hjg78") instanceof Payload);
    assertEquals(
        "YTy768",
        objectConvertUtil.converToPayload(customerRequest, "YTy768", "hjg78").getTranctionId());
    assertEquals(
        "hjg78",
        objectConvertUtil.converToPayload(customerRequest, "YTy768", "hjg78").getActivityId());
  }

  @Test
  public void converToCustomerResponseTest() {
    assertTrue(
        objectConvertUtil.converToCustomerResponse("Success", "Message Published")
            instanceof CustomerResponse);
    assertEquals(
        "Success",
        objectConvertUtil.converToCustomerResponse("Success", "Message Published").getStatus());
    assertEquals(
        "Message Published",
        objectConvertUtil.converToCustomerResponse("Success", "Message Published").getMessage());

    CustomerResponse customerResponse =
        new CustomerResponse().status("Success").message("customer Message Published");

    CustomerResponse customerResponse1 =
        objectConvertUtil.converToCustomerResponse("Success", "Message Published");
    assertNotEquals(customerResponse1.hashCode(), customerResponse.hashCode());
    assertNotEquals(customerResponse.toString(), customerResponse1.toString());
    assertFalse(customerResponse.equals(customerResponse1));
    assertFalse(customerResponse.equals(null));
    assertFalse(customerResponse.equals(new String()));
  }

  @Test
  public void converToErrorResponseTest() {

    assertTrue(
        objectConvertUtil.converToErrorResponse("Failure", "Parsing Error", "input parsing error")
            instanceof ErrorResponse);
    ErrorResponse errorResponse =
        new ErrorResponse().errorType("Error").status("Failure").message("input parsing error");

    ErrorResponse errorResponse1 =
        objectConvertUtil.converToErrorResponse("Failure", "Parsing Error", "input parsing error");
    assertEquals("Failure", errorResponse1.getStatus());
    assertEquals("Parsing Error", errorResponse1.getErrorType());
    assertEquals("input parsing error", errorResponse1.getMessage());
    assertNotEquals(errorResponse.hashCode(), errorResponse1.hashCode());
    assertNotEquals(errorResponse.toString(), errorResponse1.toString());
    assertFalse(errorResponse.equals(errorResponse1));
    assertFalse(errorResponse.equals(null));
    assertFalse(errorResponse.equals(new String()));
  }
}
