package kr.co.beable.indian.poker.net.model.req;

import androidx.annotation.NonNull;

import kr.co.beable.indian.poker.net.model.dto.SignUpDto;

public class SignUpRequest {
	
	private String url;
	private String id;
	private String pwd;
	private String name;
	
	public SignUpRequest(@NonNull String url,
	                     @NonNull String id,
	                     @NonNull String pwd,
	                     @NonNull String name) {
		this.url = url;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}
	
	public String getUrl() {
		return this.url;
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
	
	public SignUpDto toDto() {
		return new SignUpDto(this.id, this.pwd, this.name);
	}
}
