package com.pk.customer.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pk.customer.domain.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonUtilTest {
  @Autowired private JsonUtil jsonUtil;

  @Test
  public void convertObjectToStringTest() throws JsonProcessingException {

    Address address = new Address();
    address.setAddressLine1("H-NO :728,HYD");
    address.setAddressLine2("INDIA");
    address.setStreet("KPHB");
    address.setPostalCode("78745");
    assertTrue(jsonUtil.convertObjectToString(address) instanceof String);
  }

  @Test
  public void convertStringToObjectTest() throws JsonProcessingException {
    String json =
        "{\r\n"
            + "    \"addressLine1\": \"Hyd 37\",\r\n"
            + "    \"addressLine2\": \"India\",\r\n"
            + "    \"street\": \"Ameerpet\",\r\n"
            + "    \"postalCode\": \"50043\"\r\n"
            + "  }";
    assertTrue(jsonUtil.convertStringToObject(json, Address.class) instanceof Address);
    Address address = (Address) jsonUtil.convertStringToObject(json, Address.class);
    assertEquals("50043", address.getPostalCode());

    Address address1 =
        new Address().addressLine1("HYD").addressLine2("INDIA").street("KPHB").postalCode("6783");
    assertNotEquals(address.hashCode(), address1.hashCode());
    assertNotEquals(address.toString(), address1.toString());
    assertFalse(address.equals(address1));
  }
}
