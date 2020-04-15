package kr.co.beable.indian.poker.net.model.dto;

import androidx.annotation.NonNull;

public class SignInDto {
	
	private String id;
	private String pwd;
	
	public SignInDto(@NonNull String id,
	                 @NonNull String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPwd() {
		return this.pwd;
	}
}
