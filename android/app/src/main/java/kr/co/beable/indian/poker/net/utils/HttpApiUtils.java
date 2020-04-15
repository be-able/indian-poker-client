package kr.co.beable.indian.poker.net.utils;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HttpApiUtils {
	
	/**
	 * Get 요청을 보낸다
	 *
	 * @param url 보낼 URL
	 * @return 응답
	 */
	public static String get(@NonNull String url) {
		
		return get(url, new ConcurrentHashMap<>());
	}
	
	/**
	 * Get 요청을 보낸다
	 *
	 * @param url    주소
	 * @param header 헤더
	 * @return 응답
	 */
	public static String get(@NonNull String url,
							 @NonNull Map<String, String> header) {
		
		return get(url, header, new ConcurrentHashMap<>());
	}
	
	/**
	 * Get 요청을 보낸다
	 *
	 * @param url    주소
	 * @param header 헤더
	 * @param params 파라미터
	 * @return 응답
	 */
	public static String get(@NonNull String url,
							 @NonNull Map<String, String> header,
							 @NonNull Map<String, String> params) {
		//todo
		
		return "error";
	}
	
	/**
	 * 포스트 요청을 보낸다
	 *
	 * @param url  주소
	 * @param json 바디
	 * @return 응답
	 */
	public static String post(@NonNull String url,
							  @NonNull String json) {
		
		return post(url, new ConcurrentHashMap<>(), json);
	}
	
	/**
	 * 포스트 요청을 보낸다
	 *
	 * @param url    주소
	 * @param header 헤더
	 * @param json   바디
	 * @return 응답
	 */
	public static String post(@NonNull String url,
							  @NonNull Map<String, String> header,
							  @NonNull String json) {
		
		return post(url, header, new ConcurrentHashMap<>(), json);
	}
	
	/**
	 * 포스트 요청을 보낸다
	 *
	 * @param url    주소
	 * @param header 헤더
	 * @param params 파라미터
	 * @param json   바디
	 * @return 응답
	 */
	public static String post(@NonNull String url,
							  @NonNull Map<String, String> header,
							  @NonNull Map<String, String> params,
							  @NonNull String json) {
		//todo
		return "";
	}
	
}
