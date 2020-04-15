package kr.co.beable.indian.poker.base;

/**
 * 실행 결과값을 반환할 리스너
 *
 * @param <T> 결과값 타입
 */
public interface OnResultListener<T> {
	
	void onResult(T t);
}
