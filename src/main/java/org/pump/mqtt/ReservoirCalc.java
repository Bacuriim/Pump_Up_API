package org.pump.mqtt;

public class ReservoirCalc {
	MQTTConnection mqtt = MQTTConnection.getInstance();
	
	public Double fullnessPercentage() {
		return 100 - (Double.parseDouble(mqtt.getDistance()) - 20); 
	}
}
