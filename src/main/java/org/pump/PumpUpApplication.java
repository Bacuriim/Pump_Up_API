package org.pump;

import org.pump.mqtt.MQTTConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PumpUpApplication {
	public static void main(String[] args) {
		SpringApplication.run(PumpUpApplication.class, args);
		MQTTConnection.getInstance();
	}
}