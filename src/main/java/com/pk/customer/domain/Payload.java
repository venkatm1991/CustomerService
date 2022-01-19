package com.pk.customer.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({"tractionId", "activityId", "customerRequest"})
@Setter
@Getter
@NoArgsConstructor
public class Payload {
  @JsonProperty("tractionId")
  private String tranctionId;

  @JsonProperty("activityId")
  private String activityId;

  @JsonProperty("customerRequest")
  private CustomerRequest customerRequest;
}
