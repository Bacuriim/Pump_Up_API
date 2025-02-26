package org.pump.meter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pump.meter_reading.model.MeterReading;

import java.util.List;

@Data
@Entity
@Table(name = "meters")
@NoArgsConstructor
@AllArgsConstructor
public class Meter {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "meter_name", nullable = false, length = 50)
	private String name;
}