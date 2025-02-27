package org.pump.mqtt;

public class ReservoirCalc {
	MQTTConnection mqtt = MQTTConnection.getInstance();
	
	public Double fullnessPercentage() {
		return (40.0 - (Double.parseDouble(mqtt.getDistance()) - 10.0)) * 2.5 > 100 ? 100 : (40.0 - (Double.parseDouble(mqtt.getDistance()) - 10.0)) * 2.5; 
	}
	
	public boolean getWaterPresence() {
		return (Double.parseDouble(mqtt.getWaterPresence()) > 900.0);
	}
}
