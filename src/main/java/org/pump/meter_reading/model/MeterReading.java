package org.pump.meter_reading.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pump.meter.model.Meter;

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

	@ManyToOne
	@JoinColumn(name = "meter_id", nullable = false)
	private Meter meterId;

	@Column(name = "reading", nullable = false)
	private Double reading;

	@Column(name = "timestamp", nullable = false, updatable = false)
	private LocalDateTime timestamp = LocalDateTime.now();
}
