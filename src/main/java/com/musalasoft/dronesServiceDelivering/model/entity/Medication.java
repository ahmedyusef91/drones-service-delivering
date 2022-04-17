package com.musalasoft.dronesServiceDelivering.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 13, 2022 5:09:00 PM
 */
@Entity
@Table(name = "MEDICATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medication implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotBlank(message = "{constraints.medication.code.notblank}")
	@Column(name = "CODE", nullable = false)
	private String code;

	@NotBlank(message = "{constraints.medication.name.notblank")
	@Column(name = "NAME", nullable = false)
	private String name;

	@NotNull(message = "{constraints.medication.weight.notnull}")
	@Column(name = "WEIGHT", nullable = false)
	private Double weight;

	@Column(name = "IMAGE")
	private byte[] image;

}
