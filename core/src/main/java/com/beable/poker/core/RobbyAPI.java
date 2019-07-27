package com.beable.poker.core;

import com.beable.poker.model.RoomModel;
import com.beable.poker.model.UserModel;

import java.util.List;

public interface RobbyAPI {
	
	
	/**
	 * 유저 정보 요청
	 *
	 * @param userID 유저 아이디
	 * @return 유저 정보
	 */
	UserModel requestUser(String userID);
	
	
	/**
	 * 방 목록 요청
	 *
	 * @param page 페이지당 10개의 방
	 * @return 최대 10개의 방 리턴
	 */
	List<RoomModel> requestRooms(int page);
	
	/**
	 * 방 하나의 정보 요청
	 *
	 * @param roomID 방 아이디
	 * @return 방 정보
	 */
	RoomModel requestRoom(String roomID);
	
	/**
	 * 방 검색, 알아서 검색
	 *
	 * @param roomName 검색할 방 부분 이름
	 * @return 최대 10개의 방 리턴
	 */
	List<RoomModel> requestFindRoom(String roomName);
	
	/**
	 * 방 생성 요청
	 *
	 * @param userID   유저 아이디
	 * @param roomName 방 이름
	 * @return 성공여부
	 */
	boolean requestCreateRoom(String userID, String roomName);
	
	/**
	 * 방 접속 요청
	 *
	 * @param userID 유저 아이디
	 * @param roomID 방 아이디
	 * @return 성공 여부
	 */
	boolean requestJoinRoom(String userID, String roomID);
	
	/**
	 * 방 종료 요청
	 *
	 * @param userID 유저 아이디
	 * @param roomID 방 아이디
	 * @return 성공 여부
	 */
	boolean requestExitRoom(String userID, String roomID);
	
	/**
	 * 방의 상태가 변했음을 알린다
	 * 방 안에 유저 아이디가 있으므로
	 * 함수의 인수로 방 하나만 받아도 전송 가능
	 *
	 * @param room 방의 상태가 변했음을 알릴 방
	 */
	void notifyRoom(RoomModel room);
	
	/**
	 * 게임 시작 요청
	 *
	 * @param roomID 방 아이디
	 */
	void requestStartRoom(String roomID);
	
	/**
	 * 게임이 시작 되었음을 알린다
	 * 방 안에 유저 아이디가 있으므로
	 * 함수의 인수로 방 하나만 받아도 전송 가능
	 */
	void notifyStartGame(RoomModel room);
	
	
}
