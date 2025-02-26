package org.pump.meter_reading.service;

import org.pump.meter.model.Meter;
import org.pump.meter.repository.MeterRepository;
import org.pump.meter_reading.model.MeterReading;
import org.pump.meter_reading.repository.MeterReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MeterReadingService {
	
	@Autowired
	private MeterReadingRepository meterReadingRepository;
	
	@Autowired
	private MeterRepository meterRepository;
	
	public List<MeterReading> findALlMeterReadingsByMeterId(String meterId) {
		if (meterRepository.existsById(meterId)) {
			Optional<Meter> meter = meterRepository.findById(meterId);
			return meterReadingRepository.findAllByMeterId(meterId);
		}
		return Collections.emptyList();
	}
}
