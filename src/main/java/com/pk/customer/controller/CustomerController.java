package com.pk.customer.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk.customer.domain.CustomerRequest;
import com.pk.customer.domain.CustomerResponse;
import com.pk.customer.service.kafka.CustomerPublisherService;
import com.pk.customer.util.CustomerMaskUtil;
import com.pk.customer.util.JsonUtil;
import com.pk.customer.util.ObjectConvertUtil;

@RestController
@RequestMapping("/v1/api")
public class CustomerController {

  private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

  @Autowired CustomerMaskUtil customerMaskUtil;

  @Autowired JsonUtil jsonUtil;

  @Autowired ObjectConvertUtil objectConvertUtil;

  @Autowired CustomerPublisherService customerPublisherService;

  @PostMapping(
    value = "/customer",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<CustomerResponse> publishCustomer(
      @Valid @NotEmpty @RequestHeader(value = "Authorization", required = true)
          String authorization,
      @Valid @NotEmpty @RequestHeader(value = "Transaction-Id", required = true)
          String transactionId,
      @Valid @NotEmpty @RequestHeader(value = "Activity-Id", required = true) String activityId,
      @Valid @RequestBody CustomerRequest customerRequest) {
    String maskCustomerRequest =
        jsonUtil.convertObjectToString(customerMaskUtil.convert(customerRequest));
    log.info("addCustomer request {}", maskCustomerRequest);
    String response =
        customerPublisherService.publishMessage(
            objectConvertUtil.converToPayload(customerRequest, transactionId, activityId));
    CustomerResponse customerResponse =
        objectConvertUtil.converToCustomerResponse("Success", response);
    log.info("addCustomer response  {}", customerResponse);
    return new ResponseEntity<>(customerResponse, HttpStatus.OK);
  }
}
