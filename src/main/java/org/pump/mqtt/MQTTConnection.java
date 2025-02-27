package org.pump.mqtt;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.json.GsonJsonParser;

import java.util.UUID;

@Getter
public class MQTTConnection {
	private static final String BROKER_URL = "wss://test.mosquitto.org:8081";
	private static final String CLIENT_ID = "java-mqtt-subscriber-2345";
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
			options.setConnectionTimeout(300);
			options.setServerURIs(new String[]{BROKER_URL});
			options.setKeepAliveInterval(300);
			options.setCleanSession(false);
			client.setCallback(new MqttCallback() {
				@Override
				public void connectionLost(Throwable cause) {
					System.out.println("Conexão perdida! Tentando reconectar...");
					try {
						client.reconnect();
					} catch (MqttException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void messageArrived(String topic, MqttMessage message) {
					System.out.println("Mensagem recebida: " + new String(message.getPayload()));
				}

				@Override
				public void deliveryComplete(IMqttDeliveryToken token) {}
			});


			client.connect(options);
			System.out.println("Conectado ao broker MQTT!");

			client.subscribe(TOPIC, this::processMessage);
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

	private void processMessage(String topic, MqttMessage message) {
		String payload = new String(message.getPayload());
		System.out.println("Recebido -> Tópico: " + topic + ", Mensagem: " + payload);

		try {
			JsonObject jsonObject = JsonParser.parseString(payload).getAsJsonObject();
			distance = jsonObject.has("distancia") ? jsonObject.get("distancia").getAsString() : "0";
			waterPresence = jsonObject.has("presenca_de_agua") ? jsonObject.get("presenca_de_agua").getAsString() : "0";

			System.out.println("Distância: " + distance + ", Presença de Água: " + waterPresence);
		} catch (Exception e) {
			System.err.println("Erro ao processar JSON: " + e.getMessage());
		}
	}
}