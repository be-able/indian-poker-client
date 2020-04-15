package kr.co.beable.indian.poker.net.game;

import androidx.annotation.NonNull;

/**
 * 게임 덱 상태를 들고있을 데이타 클래스
 */
public class GameDeckData {
	private final int[] cardDeck = new int[10];
	
	/**
	 * 초기화 생성자
	 * 모든 카드를 2장씩 남은 값으로 세팅한다
	 */
	public GameDeckData() {
		for (int i = 0; i < this.cardDeck.length; i++) {
			this.cardDeck[i] = 2;
		}
	}
	
	/**
	 * 남아있는 카드를 배열로 입력 받는다
	 *
	 * @param cardDeck 남이있는 카드 배열
	 */
	public GameDeckData(@NonNull int[] cardDeck) {
		System.arraycopy(cardDeck, 0, this.cardDeck, 0, cardDeck.length);
		System.arraycopy(cardDeck, cardDeck.length, this.cardDeck, cardDeck.length, this.cardDeck.length - cardDeck.length);
	}
	
	/**
	 * 남아있는 카드의 갯수를 일일이 지정하는 생성자
	 *
	 * @param c1  남아있는 카드 1
	 * @param c2  남아있는 카드 2
	 * @param c3  남아있는 카드 3
	 * @param c4  남아있는 카드 4
	 * @param c5  남아있는 카드 5
	 * @param c6  남아있는 카드 6
	 * @param c7  남아있는 카드 7
	 * @param c8  남아있는 카드 8
	 * @param c9  남아있는 카드 9
	 * @param c10 남아있는 카드 10
	 */
	public GameDeckData(int c1, int c2, int c3, int c4, int c5,
	                    int c6, int c7, int c8, int c9, int c10) {
		this.cardDeck[0] = c1;
		this.cardDeck[1] = c2;
		this.cardDeck[2] = c3;
		this.cardDeck[3] = c4;
		this.cardDeck[4] = c5;
		this.cardDeck[5] = c6;
		this.cardDeck[6] = c7;
		this.cardDeck[7] = c8;
		this.cardDeck[8] = c9;
		this.cardDeck[9] = c10;
		fixData();
	}
	
	/**
	 * 남아있는 카드 수를 반환한다
	 *
	 * @param cardNumber 카드 번호 1 ~ 10
	 * @return 남아있는 카드 수
	 */
	public int getRemainCard(int cardNumber) {
		if (cardNumber < 1 || this.cardDeck.length < cardNumber) {
			return -1;
		}
		
		return this.cardDeck[cardNumber - 1];
	}
	
	/**
	 * 남아있는 카드를 배열로 반환한다
	 *
	 * @return 남아있는 카드의 배열
	 */
	public int[] getCardDeck() {
		int[] returnArr = new int[10];
		System.arraycopy(this.cardDeck, 0, returnArr, 0, returnArr.length);
		return returnArr;
	}
	
	/**
	 * 각 데이터가 0보다 크고 2보다 작도록 만든다
	 */
	private void fixData() {
		for (int i = 0; i < this.cardDeck.length; i++) {
			if (this.cardDeck[i] < 0) {
				this.cardDeck[i] = 0;
			}
			
			if (2 < this.cardDeck[i]) {
				this.cardDeck[i] = 2;
			}
		}
	}
}
