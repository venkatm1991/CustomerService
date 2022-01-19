package com.pk.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pk.customer.entity.ErrorLogEntity;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLogEntity, Integer> {}
