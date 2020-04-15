package kr.co.beable.indian.poker.net.client;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import kr.co.beable.indian.poker.net.model.data.SignInData;
import kr.co.beable.indian.poker.net.model.data.SignUpData;
import kr.co.beable.indian.poker.net.model.req.SignInRequest;
import kr.co.beable.indian.poker.net.model.req.SignUpRequest;
import kr.co.beable.indian.poker.net.utils.HttpApiUtils;

public class HttpApi {
	
	public static SignInData signIn(@NonNull SignInRequest req) {
		
		String request = new Gson().toJson(req.toDto());
		String response = HttpApiUtils.post(req.getUrl(), request);
		try {
			return new Gson().fromJson(response, SignInData.class);
		} catch (JsonSyntaxException e) {
			return new SignInData("", "");
		}
	}
	
	public static SignUpData signUp(@NonNull SignUpRequest req) {
		
		String request = new Gson().toJson(req.toDto());
		String response = HttpApiUtils.post(req.getUrl(), request);
		try {
			return new Gson().fromJson(response, SignUpData.class);
		} catch (JsonSyntaxException e) {
			return new SignUpData("", "", "");
		}
	}
}
