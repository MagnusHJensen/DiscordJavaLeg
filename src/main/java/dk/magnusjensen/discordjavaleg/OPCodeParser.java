package dk.magnusjensen.discordjavaleg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;

public class OPCodeParser extends WebSocketAdapter {
	DiscordJavaLeg client;

	public OPCodeParser(DiscordJavaLeg client) {
		this.client = client;
	}

	@Override
	public void onTextMessage(WebSocket websocket, String text) throws Exception {
		System.out.println(text);
		JsonNode node = client.getMapper().readValue(text, JsonNode.class);
		int opCode = node.get("op").intValue();
		parseWSOpCode(opCode, websocket, node);
	}

	private void parseWSOpCode(int opCode, WebSocket webSocket, JsonNode node) throws JsonProcessingException {
		int sequence = node.get("s").intValue();

		client.setLastSequence(sequence);

		if (opCode == 0) { // Event being dispatched only dk.magnusjensen.discordjavaleg.events, where intents are enabled are getting sent.
			client.handleEvent(node);
		}
		else if (opCode == 1) { // Server requesting a heartbeat - Send one back.
			client.sendHeartBeat();
		}
		else if (opCode == 7) { // Reconnect message. You should try and recconect and resume immediately.
			//client.reconnect();
		}
		else if (opCode == 9) { // Invalid Session - You should reconnect and identify and resume accordingly.

		}
		else if (opCode == 10) { // Server sending the Hello message, and requests a Identify Payload - Resulting in a ready Payload getting sent back.
			client.identifySelf();
			client.setupHeartBeat(node.get("d").get("heartbeat_interval").intValue());
		}
		else if (opCode == 11) {
			this.client.recievedHeartbeatACK();
			// Heartbeack ACK - Sent after a heartbeat, to acknowledge it has been recieved.
			// If no Heartbeat ACK is recieved inbetween sending a heartbeat, it should immediately terminate the connection with a non-1000 close code, reconnect, and attempt to resume.
		}
	}
}
