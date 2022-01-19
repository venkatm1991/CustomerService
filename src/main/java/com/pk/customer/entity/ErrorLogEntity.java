package com.pk.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "ERROR_LOG")
public class ErrorLogEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "ERROR_TYPE")
  private String errorType;

  @Column(name = "ERROR_DESCRIPTION")
  private String errorDesc;

  @Column(columnDefinition = "CLOB")
  private String payload;
}
