package kr.co.beable.indian.poker.net.utils;

import androidx.annotation.NonNull;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class HttpApiUtils {
	
	
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
		HttpClient client = new DefaultHttpClient();
		
		try {
			List<NameValuePair> paramList = convertParam(params);
			String path = url;
			if (paramList.size() != 0) {
				path = url + "?" + URLEncodedUtils.format(paramList, "UTF-8");
			}
			HttpPost post = new HttpPost(path);
			post.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
			for (Map.Entry<String, String> entry : header.entrySet()) {
				post.addHeader(entry.getKey(), entry.getValue());
			}
			
			ResponseHandler<String> rh = new BasicResponseHandler();
			
			return client.execute(post, rh);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
		}
		
		return "error";
	}
	
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
		HttpClient client = new DefaultHttpClient();
		
		try {
			List<NameValuePair> paramList = convertParam(params);
			String path = url;
			if (paramList.size() != 0) {
				path = url + "?" + URLEncodedUtils.format(paramList, "UTF-8");
			}
			HttpGet get = new HttpGet(path);
			for (Map.Entry<String, String> entry : header.entrySet()) {
				get.addHeader(entry.getKey(), entry.getValue());
			}
			
			
			ResponseHandler<String> rh = new BasicResponseHandler();
			
			return client.execute(get, rh);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
		}
		
		return "error";
	}
	
	
	/**
	 * 파라미터를 네임페어로 바꾼다
	 *
	 * @param params 파라미터
	 * @return 네임페어 목록
	 */
	private static List<NameValuePair> convertParam(Map<String, String> params) {
		List<NameValuePair> paramList = new ArrayList<>();
		for (String key : params.keySet()) {
			paramList.add(new BasicNameValuePair(key, params.get(key)));
		}
		return paramList;
	}
}
