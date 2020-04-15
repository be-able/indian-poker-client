package kr.co.beable.indian.poker.base;

/**
 * 들어오고 나가는 함수 리스너
 *
 * @param <O> output
 * @param <I> input
 */
public interface OnFunctionListener<O, I> {
	
	O onResult(I input);
}
