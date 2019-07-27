package com.beable.poker.core;

import com.beable.poker.model.AccountModel;
import com.beable.poker.model.RoomModel;
import com.beable.poker.model.UserModel;

public interface ServerResponseAPI {
	
	void login(AccountModel model);
	
	void createAccount(String model);
	
	void updateProfile(Boolean model);
	
	void updateNickname(Boolean model);
	
	void user(UserModel model);
	
	void rooms(RoomModel[] model);
	
	void room(RoomModel model);
	
	void findRoom(RoomModel[] model);
	
	void createRoom(Boolean model);
	
	void joinRoom(Boolean model);
	
	void exitRoom(Boolean model);
	
	void notifyRoom(RoomModel model);
	
	void startRoom();
	
	void startGame(RoomModel model);
	
}
