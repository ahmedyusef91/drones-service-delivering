package com.musalasoft.dronesServiceDelivering.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.musalasoft.dronesServiceDelivering.dto.request.MedicationRequest;
import com.musalasoft.dronesServiceDelivering.model.entity.Medication;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 16, 2022 9:17:35 PM
 */
@Mapper(componentModel = "spring")
public interface MedicationMapper {
	
	MedicationRequest map(Medication medication);

	Medication unmap(MedicationRequest medicationRequest);

	List<MedicationRequest> map(List<Medication> medicationList);

	List<Medication> unmap(List<MedicationRequest> ListRequest);
}
