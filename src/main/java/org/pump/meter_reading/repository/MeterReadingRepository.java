package org.pump.meter_reading.repository;

import org.pump.meter_reading.model.MeterReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeterReadingRepository extends JpaRepository<MeterReading, Long> {

	List<MeterReading> findAllByMeterId(String meterId);
}
