package com.pk.customer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk.customer.domain.Payload;
import com.pk.customer.entity.AuditLogEntity;
import com.pk.customer.entity.ErrorLogEntity;
import com.pk.customer.interfaces.ICustomerService;
import com.pk.customer.repository.AuditLogRepository;
import com.pk.customer.repository.ErrorLogRepository;
import com.pk.customer.util.ObjectConvertUtil;

@Service
public class CustomerService implements ICustomerService {

  private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

  @Autowired private AuditLogRepository auditLogRepository;

  @Autowired private ErrorLogRepository errorLogRepository;

  @Autowired ObjectConvertUtil objectConvertUtil;

  @Override
  public void saveAuditData(Payload payload) {
    AuditLogEntity auditLogEntity = objectConvertUtil.convertAuditLog(payload);
    log.info("saveAuditData - {}", auditLogEntity);
    auditLogRepository.save(auditLogEntity);
  }

  @Override
  public void saveErrorData(Payload payload, String exceptionType, String exceptionMessage) {
    ErrorLogEntity errorLogEntity =
        objectConvertUtil.convertErrorLog(payload, exceptionType, exceptionMessage);
    log.info("saveErrorData - {}", errorLogEntity);
    errorLogRepository.save(errorLogEntity);
  }
}
