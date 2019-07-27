package com.beable.poker.model;

import java.util.ArrayList;
import java.util.List;

public class RoomModel {
	
	//방 고유 아이디
	public String roomID;
	
	//방 이름
	public String roomName;
	
	//방에 들어와 있는 인원들
	public List<String> accountIDs = new ArrayList<>();
	
	//방장 아이디
	public String ownerAccountID;
}
