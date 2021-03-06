package com.musalasoft.dronesServiceDelivering.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musalasoft.dronesServiceDelivering.model.entity.Drone;
import com.musalasoft.dronesServiceDelivering.model.type.State;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 13, 2022 4:48:05 PM
 */
public interface DroneRepository extends JpaRepository<Drone, String> {

	List<Drone> findAllByState(State state);

	Optional<Drone> findBySerialNumber(String serialNumber);
	
	default void updateState(Drone drone, State state) {
		drone.setState(state);
		this.save(drone);
	}


}