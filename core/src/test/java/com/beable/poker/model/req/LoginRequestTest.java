package com.beable.poker.model.req;

import org.junit.Test;

public class LoginRequestTest {
	
	
	@Test
	public void testToJson() {
		LoginRequest request = new LoginRequest();
		request.setId("fdsahfjkdsa");
		request.setPwd("fdhfjd");
		
		String json = request.toJson();
		System.out.println(json);
	}
}