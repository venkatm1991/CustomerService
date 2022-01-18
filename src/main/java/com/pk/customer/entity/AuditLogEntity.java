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
@Table(name="AUDIT_LOG")
public class AuditLogEntity {
	
		@Id
		@GeneratedValue (strategy = GenerationType.IDENTITY)
		private int id;
		@Column(name="CUSTOMER_NUMBER")
		private String customerNumber;
		@Column(columnDefinition="CLOB")
		private String payload;
	}

