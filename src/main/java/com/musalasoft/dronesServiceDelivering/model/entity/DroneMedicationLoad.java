package com.musalasoft.dronesServiceDelivering.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 13, 2022 5:27:35 PM
 */
@Data
@Entity
@Table(name = "DRONE_MEDICATION_LOAD")
@AllArgsConstructor
@NoArgsConstructor
public class DroneMedicationLoad implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOAD_ID")
	private Integer loadId;

	@Column(name = "SOURCE")
	private String source;

	@Column(name = "DESTINATION")
	private String destination;

	@Column(name = "CREATION_DATE")
	private LocalDateTime creationDate;

	@Column(name = "DRONE_SERIAL_NUMBER_FK", updatable = false, insertable = false)
	private String serialNumber;

	@Column(name = "MEDICATION_CODE_FK", updatable = false, insertable = false)
	private String code;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DRONE_SERIAL_NUMBER_FK", referencedColumnName = "SERIAL_NUMBER")
	private Drone drone;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MEDICATION_CODE_FK", referencedColumnName = "CODE")
	private Medication medication;
}
