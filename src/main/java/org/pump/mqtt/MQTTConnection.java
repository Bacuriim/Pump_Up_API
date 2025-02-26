package org.pump.mqtt;

import lombok.Getter;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.json.GsonJsonParser;

@Getter
public class MQTTConnection {
	private static final String BROKER_URL = "wss://broker.hivemq.com:8884";
	private static final String CLIENT_ID = "java-mqtt-subscriber";
	private static final String TOPIC = "meu/topico/json";

	private static MQTTConnection instance;
	private MqttClient client;
	private String distance;
	private String waterPresence;

	private MQTTConnection() {
		try {
			client = new MqttClient(BROKER_URL, CLIENT_ID, new MemoryPersistence());
			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(10);

			client.connect(options);
			System.out.println("Conectado ao broker MQTT!");

			client.subscribe(TOPIC, (topic, message) -> {
				System.out.println("Recebido -> Tópico: " + topic + ", Mensagem: " + new String(message.getPayload()));
				getPayloadJsonInfo(new String(message.getPayload()));
			});
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public static synchronized MQTTConnection getInstance() {
		if (instance == null) {
			instance = new MQTTConnection();
		}
		return instance;
	}

	private void getPayloadJsonInfo(String json) {
		GsonJsonParser parser = new GsonJsonParser();
		distance = parser.parseMap(json).get("distancia").toString();
		waterPresence = parser.parseMap(json).get("presenca_de_agua").toString();
	}
}