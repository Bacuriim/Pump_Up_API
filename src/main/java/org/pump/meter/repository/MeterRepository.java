package org.pump.meter.repository;

import org.pump.meter.model.Meter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterRepository extends JpaRepository<Meter, String> {

	boolean existsByName(String name);

	Meter findMeterByName(String name);
}
