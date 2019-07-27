package com.beable.poker.core;

public interface AccountAPI {
	
	/**
	 * 로그인 요청
	 *
	 * @param id  아이디
	 * @param pwd 패스워드
	 * @return 성공 : 계정정보, 실패 : null
	 */
	Account requestLogin(String id, String pwd);
	
	/**
	 * 회원가입 요청
	 *
	 * @param id   아이디
	 * @param pwd  비밀번호
	 * @param pwd2 비밀번호
	 * @param nick 닉네임
	 * @return 성공 : "success", 실패 : 실패이유
	 */
	String requestCreateAccount(String id, String pwd, String pwd2, String nick);
	
	/**
	 * 유저 프로필 변경 요청
	 *
	 * @param accountID   변경 요청할 계정 아이디 (자신만 바꿀 수 있음)
	 * @param base64Image 이미지 base64 문자열
	 * @return 성공 여부
	 */
	boolean requestUpdateProfileImage(String accountID, String base64Image);
	
	/**
	 * 유저 닉네임 변경 요청
	 *
	 * @param accountID   변경 요청할 계정 아이디 (자신만 바꿀 수 있음)
	 * @param base64Image 이미지 base64 문자열
	 * @return 성공여부
	 */
	boolean requestUpdateNickname(String accountID, String base64Image);
	
}
