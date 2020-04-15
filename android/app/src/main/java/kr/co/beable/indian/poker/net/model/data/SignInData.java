package kr.co.beable.indian.poker.net.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원가입 데이타
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInData {
	
	/**
	 * 아이디
	 */
	private String id;
	
	/**
	 * 비밀번호
	 */
	private String pwd;
}
