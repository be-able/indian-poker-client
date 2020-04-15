package kr.co.beable.indian.poker.net.client;

import android.database.Observable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import kr.co.beable.indian.poker.net.model.data.SignInData;
import kr.co.beable.indian.poker.net.model.data.SignUpData;
import kr.co.beable.indian.poker.net.model.req.SignInRequest;
import kr.co.beable.indian.poker.net.model.req.SignUpRequest;

public class HttpProvider {
	
	@Nullable
	public Observable<SignInData> getSigninObservable(@NonNull String id,
													  @NonNull String pwd) {
		
		SignInRequest request = new SignInRequest("http://rest.hyob.xyz" + HttpApiPath.PATH_SIGN_IN, id, pwd);
		
		return Observable.just(request)
				.observeOn(Schedulers.io())
				.map(HttpApi::signIn)
				.observeOn(AndroidSchedulers.mainThread());
	}
	
	@Nullable
	public Observable<SignUpData> signUp(@NonNull String id,
										 @NonNull String pwd,
										 @NonNull String name) {
		
		SignUpRequest request = new SignUpRequest("http://rest.hyob.xyz" + HttpApiPath.PATH_SIGN_UP, id, pwd, name);
		
		return Observable.just(request)
				.observeOn(Schedulers.io())
				.map(HttpApi::signUp)
				.observeOn(AndroidSchedulers.mainThread());
	}
}
