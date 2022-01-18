package com.pk.customer.domain;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * CustomerRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-01-13T11:30:14.983Z")


public class CustomerRequest   {
  @JsonProperty("customerNumber")
  private String customerNumber = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("birthdate")
  private String birthdate = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("countryCode")
  private String countryCode = null;

  @JsonProperty("mobileNumber")
  private BigDecimal mobileNumber = null;

  @JsonProperty("email")
  private String email = null;

  /**
   * required, enum values (O-Open , C-Close , S-Suspended , R-Restored)
   */
  public enum CustomerStatusEnum {
    O("O"),
    
    C("C"),
    
    S("S"),
    
    R("R");

    private String value;

    CustomerStatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CustomerStatusEnum fromValue(String text) {
      for (CustomerStatusEnum b : CustomerStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("customerStatus")
  private CustomerStatusEnum customerStatus = null;

  @JsonProperty("address")
  private Address address = null;

  public CustomerRequest customerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
    return this;
  }

  /**
   * Get customerNumber
   * @return customerNumber
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Pattern(regexp="^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$") @Size(max=10) 
  public String getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
  }

  public CustomerRequest firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=10,max=50) 
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public CustomerRequest lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=10,max=50) 
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public CustomerRequest birthdate(String birthdate) {
    this.birthdate = birthdate;
    return this;
  }

  /**
   * Get birthdate
   * @return birthdate
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public CustomerRequest country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public CustomerRequest countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  /**
   * Get countryCode
   * @return countryCode
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(max=2) 
  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public CustomerRequest mobileNumber(BigDecimal mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  /**
   * Get mobileNumber
   * @return mobileNumber
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
 
  public BigDecimal getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(BigDecimal mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public CustomerRequest email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Email

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public CustomerRequest customerStatus(CustomerStatusEnum customerStatus) {
    this.customerStatus = customerStatus;
    return this;
  }

  /**
   * required, enum values (O-Open , C-Close , S-Suspended , R-Restored)
   * @return customerStatus
  **/
  @ApiModelProperty(required = true, value = "required, enum values (O-Open , C-Close , S-Suspended , R-Restored)")
  @NotNull


  public CustomerStatusEnum getCustomerStatus() {
    return customerStatus;
  }

  public void setCustomerStatus(CustomerStatusEnum customerStatus) {
    this.customerStatus = customerStatus;
  }

  public CustomerRequest address(Address address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerRequest customerRequest = (CustomerRequest) o;
    return Objects.equals(this.customerNumber, customerRequest.customerNumber) &&
        Objects.equals(this.firstName, customerRequest.firstName) &&
        Objects.equals(this.lastName, customerRequest.lastName) &&
        Objects.equals(this.birthdate, customerRequest.birthdate) &&
        Objects.equals(this.country, customerRequest.country) &&
        Objects.equals(this.countryCode, customerRequest.countryCode) &&
        Objects.equals(this.mobileNumber, customerRequest.mobileNumber) &&
        Objects.equals(this.email, customerRequest.email) &&
        Objects.equals(this.customerStatus, customerRequest.customerStatus) &&
        Objects.equals(this.address, customerRequest.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerNumber, firstName, lastName, birthdate, country, countryCode, mobileNumber, email, customerStatus, address);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerRequest {\n");
    
    sb.append("    customerNumber: ").append(toIndentedString(customerNumber)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    birthdate: ").append(toIndentedString(birthdate)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
    sb.append("    mobileNumber: ").append(toIndentedString(mobileNumber)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    customerStatus: ").append(toIndentedString(customerStatus)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

