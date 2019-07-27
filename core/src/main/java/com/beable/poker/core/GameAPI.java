package com.beable.poker.core;

import com.beable.poker.model.CardModel;
import com.beable.poker.client.Game;
import com.beable.poker.model.GameModel;

public interface GameAPI {
	
	/**
	 * 상대의 카드 조회
	 *
	 * @param accountID 계정 아이디
	 * @param userID    상대방 유저 아이디
	 * @return 성공시 카드 정보 리턴, 실패시 널 혹은 빈 정보 카드
	 */
	CardModel requestCard(String accountID, String userID);
	
	/**
	 * 게임 전체 상황을 리턴한다
	 * 상대방이 낸 배팅 정보나
	 * 내가 낸 배팅 정보 등
	 * 서로의 카드 정보는 비밀로 감싸야 한다
	 *
	 * @param userID 유저 아이디
	 * @return 성공시 게임 전체 상황 리턴, 실패시 빈 껍데기 리턴
	 */
	GameModel requestGame(String userID);
	
	/**
	 * 배팅 요청
	 *
	 * @param accountID 계정 아이디
	 * @param amount    칩의 갯수
	 * @return 배팅 성공 여부
	 */
	boolean requestBetting(String accountID, int amount);
	
	/**
	 * 포기 요청
	 *
	 * @param accountID 계정 아이디
	 * @return 포기 성공 여부
	 */
	boolean requestDie(String accountID);
	
	/**
	 * 업데이트 요청
	 * Game 안에 참여자들 목록이 있다
	 * 참여자들에게만 업데이트 요청을 전송한다
	 *
	 * @param game 게임 리턴
	 */
	void notifyUpdate(Game game);
	
	
}
