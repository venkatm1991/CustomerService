package com.pk.customer.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pk.customer.advice.CustomerControllerAdvice;
import com.pk.customer.domain.Address;
import com.pk.customer.domain.CustomerRequest;
import com.pk.customer.domain.CustomerRequest.CustomerStatusEnum;
import com.pk.customer.domain.CustomerResponse;
import com.pk.customer.service.kafka.CustomerPublisherService;
import com.pk.customer.util.ObjectConvertUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerControllerTest.class)
@WithMockUser
public class CustomerControllerTest {

  @MockBean CustomerPublisherService customerPublisherService;

  private MockMvc mockMvc;

  @MockBean CustomerController customerController;

  @MockBean ObjectConvertUtil objectConvertUtil;

  CustomerRequest customerRequest = null;

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

    mockMvc =
        MockMvcBuilders.standaloneSetup(customerController)
            .setControllerAdvice(new CustomerControllerAdvice())
            .build();
  }

  @Test
  public void publishCustomerTest() throws Exception {

    CustomerResponse customerResponse = new CustomerResponse();
    customerResponse.setMessage("Message published");
    customerResponse.setStatus("Success");
    when(customerPublisherService.publishMessage(any())).thenReturn("Message published");
    when(objectConvertUtil.converToCustomerResponse(anyString(), anyString()))
        .thenReturn(customerResponse);
    mockMvc
        .perform(
            post("/v1/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer 96b33cec-643d").header("Transaction-Id", "HJ7845FGKSK").header("Activity-Id", "HDK453")
                .content(
                    "{\r\n"
                    + "  \"customerNumber\": \"AF46375GE4\",\r\n"
                    + "  \"firstName\": \"Venkat232344\",\r\n"
                    + "  \"lastName\": \"Mandalapu46578\",\r\n"
                    + "  \"birthdate\": \"28-10-1995\",\r\n"
                    + "  \"country\": \"India\",\r\n"
                    + "  \"countryCode\": \"IN\",\r\n"
                    + "  \"mobileNumber\": 9012347891,\r\n"
                    + "  \"email\": \"newuser123@example.com\",\r\n"
                    + "  \"customerStatus\": \"O\",\r\n"
                    + "  \"address\": {\r\n"
                    + "    \"addressLine1\": \"Hyd 37\",\r\n"
                    + "    \"addressLine2\": \"India\",\r\n"
                    + "    \"street\": \"Ameerpet\",\r\n"
                    + "    \"postalCode\": \"50043\"\r\n"
                    + "  }\r\n"
                    + "}"))
        .andExpect(status().isOk());
  }
  @Test
  public void publishCustomerHeaderMissingTest() throws Exception {

    CustomerResponse customerResponse = new CustomerResponse();
    customerResponse.setMessage("Message published");
    customerResponse.setStatus("Success");
    when(customerPublisherService.publishMessage(any())).thenReturn("Message published");
    when(objectConvertUtil.converToCustomerResponse(anyString(), anyString()))
        .thenReturn(customerResponse);
    mockMvc
        .perform(
            post("/v1/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                    "{\r\n"
                    + "  \"customerNumber\": \"AF46375GE4\",\r\n"
                    + "  \"firstName\": \"Venkat232344\",\r\n"
                    + "  \"lastName\": \"Mandalapu46578\",\r\n"
                    + "  \"birthdate\": \"28-10-1995\",\r\n"
                    + "  \"country\": \"India\",\r\n"
                    + "  \"countryCode\": \"IN\",\r\n"
                    + "  \"mobileNumber\": 9012347891,\r\n"
                    + "  \"email\": \"newuser123@example.com\",\r\n"
                    + "  \"customerStatus\": \"O\",\r\n"
                    + "  \"address\": {\r\n"
                    + "    \"addressLine1\": \"Hyd 37\",\r\n"
                    + "    \"addressLine2\": \"India\",\r\n"
                    + "    \"street\": \"Ameerpet\",\r\n"
                    + "    \"postalCode\": \"50043\"\r\n"
                    + "  }\r\n"
                    + "}"))
        .andExpect(status().isBadRequest());
  }


  @Test
  public void publishCustomerBadRequestTest() throws Exception {
    CustomerResponse customerResponse = new CustomerResponse();
    customerResponse.setMessage("Message published");
    customerResponse.setStatus("Success");
    when(customerPublisherService.publishMessage(any())).thenReturn("Message published");
    when(objectConvertUtil.converToCustomerResponse(anyString(), anyString()))
        .thenReturn(customerResponse);
    mockMvc
        .perform(
            post("/v1/api/customer")
                .header("Transaction-Id", "CS9URF")
                .header("Activity-Id", "CS00546")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer 96b33cec-643d").header("Transaction-Id", "HJ7845FGKSK").header("Activity-Id", "HDK453")
                .content(
                    "{\r\n"
                        + "  \"customerNumber\": \"AF46375GE4\",\r\n"
                        + "  \"firstName\": \"Venkat232344\",\r\n"
                        + "  \"lastName\": \"Mandalapu46578\",\r\n"
                        + "  \"country\": \"India\",\r\n"
                        + "  \"countryCode\": \"IN\",\r\n"
                        + "  \"mobileNumber\": 1234567890,\r\n"
                        + "  \"email\": \"newuser123@example.com\",\r\n"
                        + "  \"customerStatus\": \"O\",\r\n"
                        + "  \"address\": \r\n"
                        + "    \"addressLine1\": \"Hyd 37\",\r\n"
                        + "    \"addressLine2\": \"India\",\r\n"
                        + "    \"street\": \"Ameerpet\",\r\n"
                        + "    \"postalCode\": \"50043\"\r\n"
                        + "  }\r\n"
                        + "}"))
        .andExpect(status().isBadRequest());
  }
}
