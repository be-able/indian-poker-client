package kr.co.beable.indian.poker.base;

/**
 * 객체의 값을 가져올 리스너. 보통 객체가 자기 자신을 알리고 싶지 않을 때 사용한다
 *
 * @param <T> 실행 결과
 */
public interface OnReturnListener<T> {
	
	T onResult();
}
