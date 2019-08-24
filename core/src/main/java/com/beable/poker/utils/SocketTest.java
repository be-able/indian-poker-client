package com.beable.poker.utils;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;

public class SocketTest {
	
	public static void main(String[] args) throws URISyntaxException {
		
		
		Socket socket = IO.socket("http://182.213.33.192:10001");
		socket.on("hello", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				String string = (String) args[0];
				System.out.println(string);
			}
		});
		socket.open();
		socket.emit("hello", "{\"msg\":\"hello\"}");
	}
}
