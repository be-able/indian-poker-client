package kr.co.beable.indian.poker;

import org.junit.Test;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketTest {
	
	@Test
	public void basicSocketTest() throws URISyntaxException {
		
		Socket socket = IO.socket("http://182.213.33.192:10001");
		socket.on("hello", args1 -> {
			String string = (String) args1[0];
			System.out.println(string);
		});
		socket.open();
		socket.emit("hello", "{\"msg\":\"hello\"}");
	}
}
