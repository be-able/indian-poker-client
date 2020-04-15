package kr.co.beable.indian.poker.net.model.dto;

import androidx.annotation.NonNull;

public class SignUpDto {
	
	private String id;
	private String pwd;
	private String name;
	
	public SignUpDto(@NonNull String id,
	                 @NonNull String pwd,
	                 @NonNull String name) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPwd() {
		return this.pwd;
	}
	
	public String getName() {
		return this.name;
	}
}
