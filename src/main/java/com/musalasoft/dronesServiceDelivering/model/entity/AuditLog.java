package com.musalasoft.dronesServiceDelivering.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 15, 2022 5:35:06 PM
 */
@Entity
@Table(name = "MS_AUDITLOG")
@Getter
@Setter
public class AuditLog implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "serial_number", length = 100, nullable = false)
	private String serialNumber;
	@Column(name = "drone_battery_level", length = 100, nullable = false)
	private BigDecimal droneBatteryLevel;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	@PrePersist
	private void setCreatedAt() {
		createdAt = new Date();
	}

}
