package kr.co.beable.indian.poker.net.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	
	/**
	 * 누구로부터
	 */
	private String from;
	
	/**
	 * 메시지
	 */
	private String text;
}
