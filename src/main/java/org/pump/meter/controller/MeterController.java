package org.pump.meter.controller;

import org.pump.meter.model.Meter;
import org.pump.meter.repository.MeterRepository;
import org.pump.meter.service.MeterService;
import org.pump.mqtt.MQTTConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="/meter")
// API for a liquid reservoir meter
@CrossOrigin(origins="*")
public class MeterController {
	
	@Autowired
	private MeterRepository meterRepository;
	
	@Autowired
	private MeterService meterService;
	
	@GetMapping("/meters")
	@ResponseBody
	public List<Meter> getAllMeters() {
		return meterService.findAllMeters();
	}
	
	@PostMapping("/new_meter/{name}")
	@ResponseBody
	public ResponseEntity<Meter> createMeter(@PathVariable String name) {
		Meter meter = new Meter(UUID.randomUUID().toString(), name);
		meterRepository.save(meter);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/meter_change/{name}")
	@ResponseBody
	public ResponseEntity<String> updateMeter(@PathVariable String name) {
		if (meterRepository.existsByName(name)) {
			Meter meter = meterRepository.findMeterByName(name);
			meter.setName(name);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/remove_meter/{name}")
	@ResponseBody
	public ResponseEntity<String> removeMeter(@PathVariable String name) {
		if (meterRepository.existsByName(name)) {
			Meter meter = meterRepository.findMeterByName(name);
			meterRepository.delete(meter);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
	
}
