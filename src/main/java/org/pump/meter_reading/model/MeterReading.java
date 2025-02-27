package org.pump.meter_reading.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "meter_readings")
@NoArgsConstructor
@AllArgsConstructor
public class MeterReading {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "meter_id", nullable = false)
	private String meterId;

	@Column(name = "reading", nullable = false)
	private Double reading;
	
	@Column(name = "water_presence", nullable = false)
	private boolean waterPresence;

	@Column(name = "timestamp", nullable = false, updatable = false)
	private LocalDateTime timestamp = LocalDateTime.now();
}
