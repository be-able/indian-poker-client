package kr.co.beable.indian.poker.net.client;

import io.socket.client.IO;
import io.socket.client.Socket;

public final class SocketIOProvider {
	
	private Socket socket;
	
	private final String serverAddress;
	
	public SocketIOProvider(final String serverAddress) {
		this.serverAddress = serverAddress;
	}
	
	private Socket getSocket() {
		if (this.socket == null) {
			try {
				this.socket = IO.socket(serverAddress);
				this.socket.on("methodName", this::methodName);
			} catch (Exception e) {
				this.socket = null;
				e.printStackTrace();
			}
		}
		return this.socket;
	}
	
	
	private void methodName(Object... args) {
	
	}
	
}
