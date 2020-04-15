package kr.co.beable.indian.poker.net.client;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;

import kr.co.beable.indian.poker.base.OnResultListener;

public final class WebSocketProvider {
	
	private static final String TAG = "WebSocketProvider";
	private WebSocketStompClient client;
	
	private OnResultListener<String> onReceivedListener;
	private StompSession session;
	
	public WebSocketProvider(@NonNull OnResultListener<String> onReceived) {
		this.onReceivedListener = onReceived;
	}
	
	/**
	 * 보낸다
	 *
	 * @param send 보낼 메시짙
	 */
	public void send(@NonNull String send) {
		if (this.isConnected()) {
			this.session.send("/connect", send);
		}
	}
	
	/**
	 * 연결을 시도한다
	 *
	 * @param url ex : 182.213.33.192:80
	 */
	public void connect(@NonNull String url) {
		StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
		this.client = new WebSocketStompClient(webSocketClient);
		this.client.setMessageConverter(new MappingJackson2MessageConverter());
		this.client.connect("ws://" + url, new StompSessionHandlerAdapter() {
			@Override
			public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
				System.out.println("New session established : " + session.getSessionId());
				session.subscribe("/connect", this);
				System.out.println("Subscribed to /topic/messages");
				session.send("/connect", getSampleMessage());
				System.out.println("Message sent to websocket server");
			}
			
			@Override
			public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
				System.err.println("Got an exception : " + exception.getMessage());
			}
			
			@NotNull
			@Override
			public Type getPayloadType(StompHeaders headers) {
				return Message.class;
			}
			
			@Override
			public void handleFrame(StompHeaders headers, Object payload) {
				Message msg = (Message) payload;
				System.out.println("Received : " + msg.getText() + " from : " + msg.getFrom());
			}
			
			/**
			 * A sample message instance.
			 * @return instance of <code>Message</code>
			 */
			private Message getSampleMessage() {
				Message msg = new Message();
				msg.setFrom("Nicky");
				msg.setText("Howdy!!");
				return msg;
			}
		});
	}
	
	/**
	 * 웹소켓을 닫는다
	 */
	private void close() {
		if (this.client == null) {
			return;
		}
		
		this.client.stop();
	}
	
	/**
	 * 연결 되어 있는가
	 *
	 * @return 연결되어있으면 true
	 */
	private boolean isConnected() {
		if (this.client == null) {
			return false;
			
		}
		
		return this.client.isRunning();
	}
	
}
