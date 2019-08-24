package com.beable.poker.utils;

public class BaseVoidModule {
	
	private OnVoidListener listener;
	private OnVoidListener nothingListener = () -> {
		//nothing to do
	};
	
	public BaseVoidModule() {
		setNothing();
	}
	
	public void setListener(OnVoidListener listener) {
		if (listener == null) {
			setNothing();
		} else {
			this.listener = listener;
		}
	}
	
	public void setNothing() {
		this.listener = nothingListener;
	}
	
	public void onReturn() {
		this.listener.onReturn();
	}
}
