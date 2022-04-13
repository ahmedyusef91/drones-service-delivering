package com.musalasoft.dronesServiceDelivering.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.musalasoft.dronesServiceDelivering.model.type.Model;
import com.musalasoft.dronesServiceDelivering.model.type.State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 13, 2022 4:04:50 PM
 */
@Data
@Entity
@Table(name = "DRONES")
@NoArgsConstructor
@AllArgsConstructor
public class Drone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull(message = "{constraints.drone.serialnumber.notblank}")
	@Length(min = 1, max = 100, message = "{constraints.drone.serialnumber.length}")
	@Column(name = "SERIAL_NUMBER", nullable = false, length = 100)
	private String serialNumber;

	@Enumerated
	@Column(name = "MODEL", nullable = false)
	private Model model;

	@NotNull(message = "{constraints.drone.weight.notnull}")
	@DecimalMin(value = "0.0", inclusive = true, message = "{constraints.drone.weight.min}")
	@DecimalMax(value = "500.0", inclusive = true, message = "{constraints.drone.weight.max}")
	@Column(name = "WEIGHT_LIMIT", nullable = false)
	private Double weightLimit;

	@NotNull(message = "{constraints.drone.batteryCapacity.notnull}")
	@Range(min = 0, max = 100, message = "{constraints.drone.batteryCapacity.range}")
	@Column(name = "BATTERY_CAPACITY", nullable = false)
	private BigDecimal batteryCapacity;

	@Enumerated
	@Column(name = "STATE", nullable = false)
	private State state;

}
