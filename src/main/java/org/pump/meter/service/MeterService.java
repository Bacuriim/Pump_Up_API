package org.pump.meter.service;

import org.pump.meter.model.Meter;
import org.pump.meter.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

import static java.util.Arrays.asList;

@Service
public class MeterService {
	
	@Autowired
	private MeterRepository meterRepository;
	
	public List<Meter> findAllMeters() {
		return meterRepository.findAll();
	}
	
}
