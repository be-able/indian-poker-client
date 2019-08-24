package com.beable.poker.client;

import com.beable.poker.core.ServerResponseAPI;
import com.beable.poker.model.AccountModel;
import com.beable.poker.model.RoomModel;
import com.beable.poker.model.UserModel;
import com.beable.poker.utils.BaseReturnModule;
import com.beable.poker.utils.OnReturnListener;
import lombok.NonNull;

public class ServerResponses implements ServerResponseAPI {
	
	private BaseReturnModule<AccountModel> loginListener = new BaseReturnModule<>();
	private BaseReturnModule<String> createAccountListener = new BaseReturnModule<>();
	private BaseReturnModule<Boolean> updateProfileListener = new BaseReturnModule<>();
	private BaseReturnModule<Boolean> updateNicknameListener = new BaseReturnModule<>();
	private BaseReturnModule<UserModel> userListener = new BaseReturnModule<>();
	private BaseReturnModule<RoomModel[]> roomsListener = new BaseReturnModule<>();
	private BaseReturnModule<RoomModel> roomListener = new BaseReturnModule<>();
	private BaseReturnModule<RoomModel[]> findRoomListener = new BaseReturnModule<>();
	private BaseReturnModule<RoomModel> createRoomListener = new BaseReturnModule<>();
	private BaseReturnModule<Boolean> joinRoomListener = new BaseReturnModule<>();
	private BaseReturnModule<Boolean> exitRoomListener = new BaseReturnModule<>();
	private BaseReturnModule<RoomModel> notifyRoomListener = new BaseReturnModule<>();
	private BaseReturnModule<RoomModel> startGameListener = new BaseReturnModule<>();
	
	public void setLoginListener(@NonNull OnReturnListener<AccountModel> loginListener) {
		this.loginListener.setListener(loginListener);
	}
	
	public void setCreateAccountListener(OnReturnListener<String> createAccountListener) {
		this.createAccountListener.setListener(createAccountListener);
	}
	
	public void setUpdateProfileListener(OnReturnListener<Boolean> updateProfileListener) {
		this.updateProfileListener.setListener(updateProfileListener);
	}
	
	public void setUpdateNicknameListener(OnReturnListener<Boolean> updateNicknameListener) {
		this.updateNicknameListener.setListener(updateNicknameListener);
	}
	
	public void setUserListener(OnReturnListener<UserModel> userListener) {
		this.userListener.setListener(userListener);
	}
	
	public void setRoomsListener(OnReturnListener<RoomModel[]> roomsListener) {
		this.roomsListener.setListener(roomsListener);
	}
	
	public void setRoomListener(OnReturnListener<RoomModel> roomListener) {
		this.roomListener.setListener(roomListener);
	}
	
	public void setFindRoomListener(OnReturnListener<RoomModel[]> findRoomListener) {
		this.findRoomListener.setListener(findRoomListener);
	}
	
	public void setCreateRoomListener(OnReturnListener<RoomModel> createRoomListener) {
		this.createRoomListener.setListener(createRoomListener);
	}
	
	public void setJoinRoomListener(OnReturnListener<Boolean> joinRoomListener) {
		this.joinRoomListener.setListener(joinRoomListener);
	}
	
	public void setExitRoomListener(OnReturnListener<Boolean> exitRoomListener) {
		this.exitRoomListener.setListener(exitRoomListener);
	}
	
	public void setNotifyRoomListener(OnReturnListener<RoomModel> notifyRoomListener) {
		this.notifyRoomListener.setListener(notifyRoomListener);
	}
	
	public void setStartGameListener(OnReturnListener<RoomModel> startGameListener) {
		this.startGameListener.setListener(startGameListener);
	}
	
	@Override
	public void login(AccountModel model) {
		this.loginListener.onReturn(model);
		this.loginListener.setNothing();
	}
	
	@Override
	public void createAccount(String model) {
		this.createAccountListener.onReturn(model);
		this.createAccountListener.setNothing();
	}
	
	@Override
	public void updateProfile(Boolean model) {
		this.updateProfileListener.onReturn(model);
		this.updateProfileListener.setNothing();
	}
	
	@Override
	public void updateNickname(Boolean model) {
		this.updateNicknameListener.onReturn(model);
		this.updateNicknameListener.setNothing();
	}
	
	@Override
	public void user(UserModel model) {
		this.userListener.onReturn(model);
		this.userListener.setNothing();
	}
	
	@Override
	public void rooms(RoomModel[] model) {
		this.roomsListener.onReturn(model);
		this.roomsListener.setNothing();
	}
	
	@Override
	public void room(RoomModel model) {
		this.roomListener.onReturn(model);
		this.roomListener.setNothing();
	}
	
	@Override
	public void findRoom(RoomModel[] model) {
		this.findRoomListener.onReturn(model);
		this.findRoomListener.setNothing();
	}
	
	@Override
	public void createRoom(RoomModel model) {
		this.createRoomListener.onReturn(model);
		this.createRoomListener.setNothing();
	}
	
	@Override
	public void joinRoom(Boolean model) {
		this.joinRoomListener.onReturn(model);
		this.joinRoomListener.setNothing();
	}
	
	@Override
	public void exitRoom(Boolean model) {
		this.exitRoomListener.onReturn(model);
		this.exitRoomListener.setNothing();
	}
	
	@Override
	public void notifyRoom(RoomModel model) {
		this.notifyRoomListener.onReturn(model);
	}
	
	@Override
	public void startGame(RoomModel model) {
		this.startGameListener.onReturn(model);
		this.startGameListener.setNothing();
	}
}
