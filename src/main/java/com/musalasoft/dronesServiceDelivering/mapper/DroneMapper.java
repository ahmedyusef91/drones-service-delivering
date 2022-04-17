package com.musalasoft.dronesServiceDelivering.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.musalasoft.dronesServiceDelivering.dto.request.DroneRequest;
import com.musalasoft.dronesServiceDelivering.model.entity.Drone;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 16, 2022 6:59:51 PM
 */
@Mapper(componentModel = "spring")
public abstract interface DroneMapper {
	DroneRequest map(Drone drone);

	Drone unmap(DroneRequest droneRequest);

	List<DroneRequest> map(List<Drone> droneList);

	List<Drone> unmap(List<DroneRequest> droneListRequest);
}
