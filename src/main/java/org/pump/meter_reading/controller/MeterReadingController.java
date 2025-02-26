package org.pump.meter_reading.controller;

import org.pump.meter.model.Meter;
import org.pump.meter.repository.MeterRepository;
import org.pump.meter_reading.model.MeterReading;
import org.pump.meter_reading.repository.MeterReadingRepository;
import org.pump.meter_reading.service.MeterReadingService;
import org.pump.mqtt.MQTTConnection;
import org.pump.mqtt.ReservoirCalc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value="/meter_reading")
// API for a liquid reservoir meter
@CrossOrigin(origins="*")
public class MeterReadingController {
	
	@Autowired
	private MeterReadingRepository meterReadingRepository;
	
	@Autowired
	private MeterReadingService meterReadingService;
	
	@Autowired
	private MeterRepository meterRepository;
	
	private final ReservoirCalc reservoirCalc = new ReservoirCalc();
	
	@GetMapping("/meter/{name}")
	@ResponseBody
	public List<MeterReading> getMeterReadingByMeterName(@PathVariable String name) {
		if (meterRepository.existsByName(name)) {
			Meter meter = meterRepository.findMeterByName(name);
			return meterReadingService.findALlMeterReadingsByMeterId(meter.getId());
		}
		return Collections.emptyList();
	}
	
	@PostMapping("/new_reading/{name}")
	@ResponseBody
	public ResponseEntity<String> createMeterReading(@PathVariable String name) {
		if (meterRepository.existsByName(name)) {
			Meter meter = meterRepository.findMeterByName(name);
			MeterReading meterReading = new MeterReading((long) (meterReadingRepository.findAllByMeterId(meter.getId()).size() + 1),
					meter, reservoirCalc.fullnessPercentage(), LocalDateTime.now());
			meterReadingRepository.save(meterReading);
			return ResponseEntity.ok("Meter Reading Created");
		}
		return ResponseEntity.notFound().build();
	}
}
