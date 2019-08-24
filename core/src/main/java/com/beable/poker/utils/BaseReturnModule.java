package com.beable.poker.utils;

public class BaseReturnModule<T> {
	
	private OnReturnListener<T> listener;
	private OnReturnListener<T> nothingListener = t -> {
		//nothing to do
	};
	
	
	public BaseReturnModule() {
		setNothing();
	}
	
	public void setListener(OnReturnListener<T> listener) {
		if (listener == null) {
			setNothing();
		} else {
			this.listener = listener;
		}
	}
	
	public void setNothing() {
		this.listener = nothingListener;
	}
	
	public void onReturn(T t) {
		this.listener.onReturn(t);
	}
}
