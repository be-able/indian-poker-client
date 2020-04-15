package kr.co.beable.indian.poker.net.client;

import org.junit.Test;

public class WebSocketProviderTest {
	
	private WebSocketProvider getProvider() {
		return new WebSocketProvider(System.out::println);
	}
	
	
	@Test
	public void send() {
	}
	
	@Test
	public void connect() {
		new Thread(() -> {
			WebSocketProvider provider = getProvider();
			provider.connect("182.213.33.192:80");
//			provider.send("connect");
		}).start();
		
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}