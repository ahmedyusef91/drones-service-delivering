package com.musalasoft.dronesServiceDelivering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musalasoft.dronesServiceDelivering.model.entity.AuditLog;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 15, 2022 5:46:23 PM
 */
public interface AuditLogRepository extends JpaRepository<AuditLog, String> {

}
