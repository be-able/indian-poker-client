package kr.co.beable.indian.poker.android.game;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import kr.co.beable.indian.poker.R;

/**
 * 게임을 하는 액티비티 클래스
 */
public class GameActivity extends AppCompatActivity {
	
	/**
	 * 액티비티 생성
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_robby);
	}
}
