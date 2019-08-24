package com.beable.poker.client;

import com.beable.poker.model.AccountModel;
import com.beable.poker.model.RoomModel;
import com.beable.poker.model.UserModel;
import com.beable.poker.utils.OnReturnListener;
import com.beable.poker.utils.OnVoidListener;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public class Client {
	
	private MainSocket mainSocket;
	
	@Nullable
	private AccountModel account = null;
	
	@Nullable
	private UserModel user = null;
	
	@Nullable
	private RoomModel room = null;
	
	private ServerResponses responses = new ServerResponses();
	
	public Client(@NotNull String serverAddress) {
		this.mainSocket = new MainSocket(serverAddress, responses);
	}
	
	/**
	 * 로그인을한다.
	 * 로그인후 계정정보와 유저정보를 불러온다
	 *
	 * @param id     아이디
	 * @param pwd    패스워드
	 * @param result 성공여부
	 */
	public void login(@NotNull String id,
					  @NotNull String pwd,
					  @NotNull OnReturnListener<Boolean> result) {
		this.responses.setLoginListener(account -> {
			if (account == null || account.accountID == null || account.userID == null) {
				result.onReturn(false);
			} else {
				this.getUser(this.account.userID, userModel -> {
					if (userModel == null) {
						result.onReturn(false);
					} else {
						this.account = account;
						this.user = userModel;
						result.onReturn(true);
					}
				});
			}
		});
		this.mainSocket.requestLogin(id, pwd);
	}
	
	/**
	 * 계정을 만들고 성공인지 실패인지는 문구를 통해 보여주는 것으로 한다
	 *
	 * @param id     아이디
	 * @param pwd    패스워드 1
	 * @param pwd2   패스워드 2
	 * @param nick   닉네임
	 * @param result 서버문구
	 */
	public void createAccount(@NotNull String id,
							  @NotNull String pwd,
							  @NotNull String pwd2,
							  @NotNull String nick,
							  @NotNull OnReturnListener<String> result) {
		this.responses.setCreateAccountListener(result);
		this.mainSocket.requestCreateAccount(id, pwd, pwd2, nick);
	}
	
	/**
	 * 프로필을 업데이트한다
	 *
	 * @param base64Image 변경할 프로필 이미지
	 * @param result      업데이트 성공여부
	 */
	public void updateProfile(@NotNull String base64Image,
							  @NotNull OnReturnListener<Boolean> result) {
		if (this.account == null || this.account.accountID == null) {
			return;
		}
		this.responses.setUpdateProfileListener(result);
		this.mainSocket.requestUpdateProfileImage(this.account.accountID, base64Image);
	}
	
	/**
	 * 닉네임을 변경한다
	 *
	 * @param nickName 변경할 닉네임
	 * @param result   성공여부
	 */
	public void updateNickname(@NotNull String nickName,
							   @NotNull OnReturnListener<Boolean> result) {
		if (this.account == null || this.account.accountID == null) {
			return;
		}
		this.responses.setUpdateNicknameListener(success -> {
			if (success) {
				this.user.nick = nickName;
			}
			result.onReturn(success);
		});
		this.mainSocket.requestUpdateNickname(this.account.accountID, nickName);
	}
	
	/**
	 * 유저정보를 얻는다
	 *
	 * @param userID 유저 아이디
	 * @param result 유저정보
	 */
	public void getUser(@NotNull String userID,
						@NotNull OnReturnListener<UserModel> result) {
		this.responses.setUserListener(result);
		this.mainSocket.requestUser(userID);
	}
	
	/**
	 * 방들을 얻는다
	 *
	 * @param page   방 페이지
	 * @param result 방들
	 */
	public void rooms(int page,
					  @NotNull OnReturnListener<RoomModel[]> result) {
		this.responses.setRoomsListener(result);
		this.mainSocket.requestRooms(page);
	}
	
	/**
	 * 방 정보를 얻는다
	 *
	 * @param roomID 방아이디
	 * @param result 방정보
	 */
	public void getRoomInfo(@NotNull String roomID,
							@NotNull OnReturnListener<RoomModel> result) {
		this.responses.setRoomListener(result);
		this.mainSocket.requestRoom(roomID);
	}
	
	/**
	 * 방을 찾는다
	 *
	 * @param roomName 찾을 방 이름
	 * @param result   방 이름에 해당하는 방들
	 */
	public void findRoom(@NotNull String roomName,
						 @NotNull OnReturnListener<RoomModel[]> result) {
		this.responses.setFindRoomListener(result);
		this.mainSocket.requestFindRoom(roomName);
	}
	
	/**
	 * 방을 생성한다
	 * 생성 후 들어간다
	 *
	 * @param roomName  방 이름
	 * @param result    생성후 들어가기 성공여부
	 * @param notify    방 상태 변화 알림
	 * @param startGame 방 시작 알림
	 */
	public void createRoom(@NotNull String roomName,
						   @NotNull OnReturnListener<Boolean> result,
						   @NotNull OnReturnListener<RoomModel> notify,
						   @NotNull OnReturnListener<RoomModel> startGame) {
		if (this.user == null || this.user.userID == null) {
			return;
		}
		
		this.responses.setCreateRoomListener(roomModel -> joinRoom(roomModel.roomID, result, notify, startGame));
		this.mainSocket.requestCreateRoom(this.user.userID, roomName);
	}
	
	/**
	 * 방 들어가기
	 *
	 * @param roomID    방 아이디
	 * @param result    방 들어가기 성공 여부
	 * @param notify    방 상태 변화 알림
	 * @param startGame 방 시작 알림
	 */
	public void joinRoom(@NotNull String roomID,
						 @NotNull OnReturnListener<Boolean> result,
						 @NotNull OnReturnListener<RoomModel> notify,
						 @NotNull OnVoidListener startGame) {
		if (this.user == null || this.user.userID == null) {
			return;
		}
		
		this.responses.setJoinRoomListener(success -> joinRoomHandler(roomID, success, result, notify, startGame));
		this.mainSocket.requestJoinRoom(this.user.userID, roomID);
	}
	
	private void joinRoomHandler(@NotNull String roomID,
								 @NotNull Boolean success,
								 @NotNull OnReturnListener<Boolean> result,
								 @NotNull OnReturnListener<RoomModel> notify,
								 @NotNull OnVoidListener startGame) {
		if (success) {
			this.getRoomInfo(roomID, roomModel -> joinGetRoomHandler(roomModel, result, notify, startGame));
		} else {
			result.onReturn(false);
		}
	}
	
	/**
	 * 방에 들어가고나서 방을 얻어올 때 할 동작
	 *
	 * @param roomModel
	 * @param result
	 * @param notify
	 * @param startGame
	 */
	private void joinGetRoomHandler(RoomModel roomModel,
									@NotNull OnReturnListener<Boolean> result,
									@NotNull OnReturnListener<RoomModel> notify,
									@NotNull OnVoidListener startGame) {
		if (roomModel == null) {
			result.onReturn(false);
		} else {
			this.room = roomModel;
			this.responses.setNotifyRoomListener(notify);
			this.responses.setStartGameListener(room -> startGameHandler(room, startGame));
			result.onReturn(true);
		}
	}
	
	/**
	 * 게임이 시작하려할 때 해야할 동작
	 *
	 * @param room      게임 시작하려하는 룸 모델
	 * @param startGame 게임시작시 알려줄 콜백
	 */
	private void startGameHandler(RoomModel room, OnVoidListener startGame) {
	
	}
	
	/**
	 * 방을 나간다
	 *
	 * @param result 나가기 성공 여부
	 */
	public void exitRoom(@NotNull OnReturnListener<Boolean> result) {
		if (this.user == null || this.user.userID == null) {
			return;
		}
		
		if (this.room == null || this.room.roomID == null) {
			return;
		}
		
		this.responses.setExitRoomListener(success -> {
			if (success) {
				this.room = null;
				this.responses.setNotifyRoomListener(null);
				this.responses.setStartGameListener(null);
			}
			result.onReturn(success);
		});
		this.mainSocket.requestExitRoom(this.user.userID, this.room.roomID);
	}
	
	/**
	 * 방을 시작한다
	 */
	public void startRoom() {
		if (this.room == null || this.room.roomID == null) {
			return;
		}
		
		this.mainSocket.requestStartRoom(this.room.roomID);
	}
	
}
