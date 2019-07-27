package com.beable.poker.client;

import com.google.gson.Gson;
import com.beable.poker.utils.OnReturnListener;
import io.socket.client.IO;
import io.socket.client.Socket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Robby {
	Socket socket;
	public OnReturnListener<Account> loginListener;
	public OnReturnListener<String> createAccountListener;
	public OnReturnListener<Boolean> updateProfileListener;
	public OnReturnListener<Boolean> updateNicknameListener;
	public OnReturnListener<User> userListener;
	public OnReturnListener<Room[]> roomsListener;
	public OnReturnListener<Room> roomListener;
	public OnReturnListener<Room[]> findRoomListener;
	public OnReturnListener<Boolean> createRoomListener;
	public OnReturnListener<Boolean> joinRoomListener;
	public OnReturnListener<Boolean> exitRoomListener;
	public OnReturnListener<Room> notifyRoomListener;
	public OnReturnListener<Void> startRoomListener;
	public OnReturnListener<Room> startGameListener;
	
	public Robby() {
	
	}
	
	private Socket getSocket() {
		if (this.socket == null) {
			try {
				this.socket = IO.socket("http://localhost:3100");
				Arrays.stream(this.getClass().getMethods())
						.filter(this::predicateResponse)
						.forEach(this::socketOn);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return socket;
	}
	
	private boolean predicateResponse(Method method) {
		return method.getName().startsWith("response");
	}
	
	private void socketOn(Method method) {
		this.socket.on(method.getName(), args -> this.methodOn(method, args));
	}
	
	
	private void methodOn(Method method, Object... args) {
		try {
			method.invoke(args);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	
	private void responseLogin(Object... args) {
		if (0 < args.length) {
			this.loginListener.onReturn(new Gson().fromJson((String) args[0], Account.class));
		}
	}
	
	private void responseCreateAccount(Object... args) {
		if (0 < args.length) {
			this.createAccountListener.onReturn((String) args[0]);
		}
	}
	
	private void responseUpdateProfileImage(Object... args) {
		if (0 < args.length) {
			this.updateProfileListener.onReturn((Boolean) args[0]);
		}
	}
	
	private void responseUpdateNickname(Object... args) {
		if (0 < args.length) {
			this.updateNicknameListener.onReturn((Boolean) args[0]);
		}
	}
	
	private void responseUser(Object... args) {
		if (0 < args.length) {
			this.userListener.onReturn((User) args[0]);
		}
	}
	
	private void responseRooms(Object... args) {
		if (0 < args.length) {
			this.roomsListener.onReturn((Room[]) args[0]);
		}
	}
	
	private void responseRoom(Object... args) {
		if (0 < args.length) {
			this.roomListener.onReturn((Room) args[0]);
		}
	}
	
	private void responseFindRoom(Object... args) {
		if (0 < args.length) {
			this.findRoomListener.onReturn((Room[]) args[0]);
		}
	}
	
	private void responseCreateRoom(Object... args) {
		if (0 < args.length) {
			this.createRoomListener.onReturn((Boolean) args[0]);
		}
	}
	
	private void responseJoinRoom(Object... args) {
		if (0 < args.length) {
			this.joinRoomListener.onReturn((Boolean) args[0]);
		}
	}
	
	private void responseExitRoom(Object... args) {
		if (0 < args.length) {
			this.exitRoomListener.onReturn((Boolean) args[0]);
		}
	}
	
	private void responseNotifyRoom(Object... args) {
		if (0 < args.length) {
			this.notifyRoomListener.onReturn((Room) args[0]);
		}
	}
	
	private void responseStartGame(Object... args) {
		if (0 < args.length) {
			this.startGameListener.onReturn((Room) args[0]);
		}
	}
	
	
	void responseStartRoom(Object... args) {
		if (0 < args.length) {
			this.startRoomListener.onReturn((Void) args[0]);
		}
	}
	
	public void requestLogin(String id, String pwd) {
		getSocket().emit("requestLogin", id, pwd);
	}
	
	void requestCreateAccount(String id, String pwd, String pwd2, String nick) {
		getSocket().emit("requestCreateAccount", id, pwd, pwd2, nick);
	}
	
	void requestUpdateProfileImage(String accountID, String base64Image) {
		getSocket().emit("requestUpdateProfileImage", accountID, base64Image);
	}
	
	void requestUpdateNickname(String accountID, String base64Image) {
		getSocket().emit("requestUpdateNickname", accountID, base64Image);
	}
	
	void requestUser(String userID) {
		getSocket().emit("requestUser", userID);
	}
	
	void requestRooms(int page) {
		getSocket().emit("requestRooms", page);
	}
	
	void requestRoom(String roomID) {
		getSocket().emit("requestRoom", roomID);
	}
	
	void requestFindRoom(String roomName) {
		getSocket().emit("requestFindRoom", roomName);
	}
	
	void requestCreateRoom(String userID, String roomName) {
		getSocket().emit("requestCreateRoom", userID, roomName);
	}
	
	void requestJoinRoom(String userID, String roomID) {
		getSocket().emit("requestJoinRoom", userID, roomID);
	}
	
	void requestExitRoom(String userID, String roomID) {
		getSocket().emit("requestExitRoom", userID, roomID);
	}
	
	void requestStartRoom(String roomID) {
		getSocket().emit("requestStartRoom", roomID);
	}
	
}
