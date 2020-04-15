package kr.co.beable.indian.poker.net.game;

import androidx.annotation.NonNull;

public class GameData {
	private final GameDeckData deck;
	private final GamePlayerData my;
	private final GamePlayerData other;
	private final int lastBatting;
	private final boolean myTurn;
	
	
	/**
	 * 초기화 생성자
	 *
	 * @param myHeadCardNumber    내 머리 위 카드
	 * @param otherHeadCardNumber 상대 머리 위 카드
	 * @param chipCount           초기화 칩 갯수
	 */
	public GameData(int myHeadCardNumber, int otherHeadCardNumber, int chipCount) {
		this.deck = new GameDeckData();
		this.my = new GamePlayerData(0, 1, 20);
		this.other = new GamePlayerData(0, 1, 20);
		this.lastBatting = 1;
		this.myTurn = false;
	}
	
	/**
	 * 게임 데이터 생성자
	 *
	 * @param deck        남아있는 카드 덱
	 * @param my          내 데이타
	 * @param other       다른 사람 데이타
	 * @param lastBatting 마지막 배팅 숫자값
	 * @param myTurn      나의 턴인가
	 */
	public GameData(@NonNull GameDeckData deck,
	                @NonNull GamePlayerData my,
	                @NonNull GamePlayerData other,
	                int lastBatting,
	                boolean myTurn) {
		this.deck = deck;
		this.my = my;
		this.other = other;
		this.lastBatting = lastBatting;
		this.myTurn = myTurn;
	}
	
	public GameDeckData getDeck() {
		return this.deck;
	}
	
	public GamePlayerData getMy() {
		return this.my;
	}
	
	public GamePlayerData getOther() {
		return this.other;
	}
	
	public int getLastBatting() {
		return this.lastBatting;
	}
	
	public boolean isMyTurn() {
		return this.myTurn;
	}
}
