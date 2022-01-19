package com.pk.customer.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pk.customer.filter.UniqueIdFilter;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Setter
@Getter
public class UniqueIdFilterConfiguration {

  public static final String DEFAULT_HEADER_TOKEN = "Activity-Id";
  public static final String DEFAULT_MDC_UUID_TOKEN_KEY = "Activity-Id";

  private String responseHeader = DEFAULT_HEADER_TOKEN;
  private String mdcKey = DEFAULT_MDC_UUID_TOKEN_KEY;
  private String requestHeader = DEFAULT_HEADER_TOKEN;

  @Bean
  public FilterRegistrationBean<UniqueIdFilter> servletRegistrationBean() {
    final FilterRegistrationBean<UniqueIdFilter> registrationBean = new FilterRegistrationBean<>();
    final UniqueIdFilter log4jMDCFilterFilter =
        new UniqueIdFilter(responseHeader, mdcKey, requestHeader);
    registrationBean.setFilter(log4jMDCFilterFilter);
    registrationBean.setOrder(2);
    return registrationBean;
  }
}
