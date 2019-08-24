package com.beable.poker.client;

import com.beable.poker.core.ServerResponseAPI;
import com.beable.poker.model.AccountModel;
import com.beable.poker.model.RoomModel;
import com.beable.poker.model.UserModel;
import com.beable.poker.model.req.*;
import com.google.gson.Gson;
import io.socket.client.IO;
import io.socket.client.Socket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public final class MainSocket {
	
	private Socket socket;
	
	private ServerResponseAPI responses;
	
	private final String serverAddress;
	
	public MainSocket(final String serverAddress, ServerResponseAPI responses) {
		this.responses = responses;
		this.serverAddress = serverAddress;
	}
	
	private Socket getSocket() {
		if (this.socket == null) {
			try {
				this.socket = IO.socket(serverAddress);
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
			this.responses.createRoom((RoomModel) args[0]);
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
	
	public void requestLogin(LoginRequest request) {
		getSocket().emit("login", request.toJson());
	}
	
	public void requestCreateAccount(CreateAccountRequest request) {
		getSocket().emit("createAccount", request.toJson());
	}
	
	public void requestUpdateProfileImage(UpdateProfileImageRequest request) {
		getSocket().emit("updateProfileImage", request.toJson());
	}
	
	public void requestUpdateNickname(UpdateNicknameRequest request) {
		getSocket().emit("updateNickname", request.toJson());
	}
	
	public void requestUser(UserRequest request) {
		getSocket().emit("user", request.toJson());
	}
	
	public void requestRooms(RoomListRequest request) {
		getSocket().emit("rooms", request.toJson());
	}
	
	public void requestRoom(RoomRequest request) {
		getSocket().emit("room", request.toJson());
	}
	
	public void requestFindRoom(FindRoomRequest request) {
		getSocket().emit("findRoom", request.toJson());
	}
	
	public void requestCreateRoom(CreateRoomRequest request) {
		getSocket().emit("createRoom", request.toJson());
	}
	
	public void requestJoinRoom(JoinRoomRequest request) {
		getSocket().emit("joinRoom", request.toJson());
	}
	
	public void requestExitRoom(ExitRoomRequest request) {
		getSocket().emit("exitRoom", request.toJson());
	}
	
	public void requestStartRoom(StartRoomRequest request) {
		getSocket().emit("startRoom", request.toJson());
	}
	
}
