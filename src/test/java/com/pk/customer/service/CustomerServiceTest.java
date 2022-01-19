package com.pk.customer.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.pk.customer.domain.Address;
import com.pk.customer.domain.CustomerRequest;
import com.pk.customer.domain.CustomerRequest.CustomerStatusEnum;
import com.pk.customer.domain.Payload;
import com.pk.customer.entity.AuditLogEntity;
import com.pk.customer.entity.ErrorLogEntity;
import com.pk.customer.repository.AuditLogRepository;
import com.pk.customer.repository.ErrorLogRepository;
import com.pk.customer.util.ObjectConvertUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

  @Autowired private CustomerService customerService;

  @MockBean private AuditLogRepository auditLogRepository;

  @MockBean private ErrorLogRepository errorLogRepository;

  @MockBean ObjectConvertUtil objectConvertUtil;

  AuditLogEntity auditLogEntity = null;
  ErrorLogEntity errorLogEntity = null;
  Payload payload = null;

  @Before
  public void setup() {
    CustomerRequest customerRequest = new CustomerRequest();
    customerRequest.setBirthdate("20-05-1991");
    customerRequest.setCountry("India");
    customerRequest.setCountryCode("IN");
    customerRequest.setCustomerNumber("HKJ73985IM");
    customerRequest.setCustomerStatus(CustomerStatusEnum.O);
    customerRequest.setEmail("user123@gmail.com");
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
    auditLogEntity = objectConvertUtil.convertAuditLog(payload);
    errorLogEntity =
        objectConvertUtil.convertErrorLog(payload, "CustomerServiceException", "invalid input");
  }

  @Test
  public void saveAuditDataTest() {
    when(objectConvertUtil.convertAuditLog(any())).thenReturn(auditLogEntity);
    when(auditLogRepository.save(any())).thenReturn(auditLogEntity);
    customerService.saveAuditData(any());
  }

  @Test
  public void saveErrorDataTest() {
    when(objectConvertUtil.convertErrorLog(any(), anyString(), anyString()))
        .thenReturn(errorLogEntity);
    when(errorLogRepository.save(any())).thenReturn(errorLogEntity);
    customerService.saveErrorData(payload, "CustomerServiceException", "invalid input");
  }
}
