package kr.co.beable.indian.poker.net.model.req;

import androidx.annotation.NonNull;

import kr.co.beable.indian.poker.net.model.dto.SignInDto;

public class SignInRequest {
	
	private String url;
	private String id;
	private String pwd;
	
	public SignInRequest(@NonNull String url,
	                     @NonNull String id,
	                     @NonNull String pwd) {
		this.url = url;
		this.id = id;
		this.pwd = pwd;
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
	
	public SignInDto toDto() {
		return new SignInDto(this.id, this.pwd);
	}
}
