package com.beable.poker.model.req;


import com.google.gson.Gson;
import lombok.Getter;

import java.util.Random;
import java.util.UUID;

public class BaseRequest {
	
	@Getter
	protected String msgID;
	
	public BaseRequest() {
		msgID = UUID.randomUUID().toString();
	}
	
	private static Gson GSON = new Gson();
	private static Random RANDOM = new Random();
	
	public String toJson() {
		return GSON.toJson(this);
	}
}
