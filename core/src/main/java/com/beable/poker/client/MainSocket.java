package com.beable.poker.client;

import com.beable.poker.core.ServerResponseAPI;
import com.beable.poker.model.AccountModel;
import com.beable.poker.model.RoomModel;
import com.beable.poker.model.UserModel;
import com.beable.poker.utils.ServerInfo;
import com.google.gson.Gson;
import io.socket.client.IO;
import io.socket.client.Socket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public final class MainSocket {
	
	private Socket socket;
	
	private ServerResponseAPI responses;
	
	public MainSocket(ServerResponseAPI responses) {
		this.responses = responses;
	}
	
	private Socket getSocket() {
		if (this.socket == null) {
			try {
				this.socket = IO.socket(ServerInfo.ADDRESS);
				Arrays.stream(this.getClass().getMethods())
						.filter(this::predicateResponse)
						.forEach(this::socketOn);
			} catch (Exception e) {
				this.socket = null;
				e.printStackTrace();
			}
		}
		return this.socket;
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
			this.responses.login(new Gson().fromJson((String) args[0], AccountModel.class));
		}
	}
	
	private void responseCreateAccount(Object... args) {
		if (0 < args.length) {
			this.responses.createAccount((String) args[0]);
		}
	}
	
	private void responseUpdateProfileImage(Object... args) {
		if (0 < args.length) {
			this.responses.updateProfile((Boolean) args[0]);
		}
	}
	
	private void responseUpdateNickname(Object... args) {
		if (0 < args.length) {
			this.responses.updateNickname((Boolean) args[0]);
		}
	}
	
	private void responseUser(Object... args) {
		if (0 < args.length) {
			this.responses.user((UserModel) args[0]);
		}
	}
	
	private void responseRooms(Object... args) {
		if (0 < args.length) {
			this.responses.rooms((RoomModel[]) args[0]);
		}
	}
	
	private void responseRoom(Object... args) {
		if (0 < args.length) {
			this.responses.room((RoomModel) args[0]);
		}
	}
	
	private void responseFindRoom(Object... args) {
		if (0 < args.length) {
			this.responses.findRoom((RoomModel[]) args[0]);
		}
	}
	
	private void responseCreateRoom(Object... args) {
		if (0 < args.length) {
			this.responses.createRoom((Boolean) args[0]);
		}
	}
	
	private void responseJoinRoom(Object... args) {
		if (0 < args.length) {
			this.responses.joinRoom((Boolean) args[0]);
		}
	}
	
	private void responseExitRoom(Object... args) {
		if (0 < args.length) {
			this.responses.exitRoom((Boolean) args[0]);
		}
	}
	
	private void responseNotifyRoom(Object... args) {
		if (0 < args.length) {
			this.responses.notifyRoom((RoomModel) args[0]);
		}
	}
	
	private void responseStartGame(Object... args) {
		if (0 < args.length) {
			this.responses.startGame((RoomModel) args[0]);
		}
	}
	
	
	public void responseStartRoom(Object... args) {
		if (0 < args.length) {
			this.responses.startRoom();
		}
	}
	
	public void requestLogin(String id, String pwd) {
		getSocket().emit("requestLogin", id, pwd);
	}
	
	public void requestCreateAccount(String id, String pwd, String pwd2, String nick) {
		getSocket().emit("requestCreateAccount", id, pwd, pwd2, nick);
	}
	
	public void requestUpdateProfileImage(String accountID, String base64Image) {
		getSocket().emit("requestUpdateProfileImage", accountID, base64Image);
	}
	
	public void requestUpdateNickname(String accountID, String base64Image) {
		getSocket().emit("requestUpdateNickname", accountID, base64Image);
	}
	
	public void requestUser(String userID) {
		getSocket().emit("requestUser", userID);
	}
	
	public void requestRooms(int page) {
		getSocket().emit("requestRooms", page);
	}
	
	public void requestRoom(String roomID) {
		getSocket().emit("requestRoom", roomID);
	}
	
	public void requestFindRoom(String roomName) {
		getSocket().emit("requestFindRoom", roomName);
	}
	
	public void requestCreateRoom(String userID, String roomName) {
		getSocket().emit("requestCreateRoom", userID, roomName);
	}
	
	public void requestJoinRoom(String userID, String roomID) {
		getSocket().emit("requestJoinRoom", userID, roomID);
	}
	
	public void requestExitRoom(String userID, String roomID) {
		getSocket().emit("requestExitRoom", userID, roomID);
	}
	
	public void requestStartRoom(String roomID) {
		getSocket().emit("requestStartRoom", roomID);
	}
	
}
