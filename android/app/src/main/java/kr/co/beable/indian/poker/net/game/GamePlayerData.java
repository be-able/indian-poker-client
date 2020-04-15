package kr.co.beable.indian.poker.net.game;

/**
 * 플레이어의 게임 상태를 저장하고 있을 데이타 클래스
 */
public class GamePlayerData {
	private final int headCardNumber;
	private final int battingNumber;
	private final int chipNumber;
	
	/**
	 * @param headCardNumber 머리 위의 카드
	 * @param battingNumber  테이블 위의 배팅한 칩의 갯수
	 * @param chipNumber     현재 보유 중인 칩의 갯수
	 */
	public GamePlayerData(int headCardNumber,
	                      int battingNumber,
	                      int chipNumber) {
		this.headCardNumber = headCardNumber;
		this.battingNumber = battingNumber;
		this.chipNumber = chipNumber;
	}
	
	/**
	 * @return 머리 위의 카드
	 */
	public int getHeadCardNumber() {
		return this.headCardNumber;
	}
	
	/**
	 * @return 테이블 위의 배팅한 칩의 갯수
	 */
	public int getBattingNumber() {
		return this.battingNumber;
	}
	
	/**
	 * @return 현재 보유 중인 칩의 갯수
	 */
	public int getChipNumber() {
		return this.chipNumber;
	}
}
