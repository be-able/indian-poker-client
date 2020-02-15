using System;
using System.Collections;
using System.Collections.Generic;
using socket.io;
using UnityEngine;

public class Sockets : MonoBehaviour {

	private Socket socket;

	void Awake() {
		DontDestroyOnLoad(gameObject);
	}

	void Start() {
		socket = Socket.Connect("http://182.213.33.192:10001");

		socket.On(SystemEvents.connect, () => {
			Debug.Log("Socket Connected");
		});

		socket.On(SystemEvents.reconnect, (int rec) => {
			Debug.Log("Socket Reconnected : " + rec);
		});

		socket.On(SystemEvents.disconnect, () => {
			Debug.Log("Socket Disconnected");
		});

		socket.On("hello", (string data) => {
			Debug.Log("Hello : " + data);
		});

		socket.On("getRooms", (string data) => {
			Debug.Log("getRooms : " + data);
			CreateRoom();
		});

		socket.On("createRoom", (string data) => {
			Debug.Log("createRoom : " + data);
		});

		StartCoroutine(ee());
	}

	IEnumerator ee() {
		Debug.Log("wait for seconds");
		yield return new WaitForSeconds(5);

		Debug.Log("emit!!!");
		socket.EmitJson("getRooms", "{\"pid\":\"getRooms\"}");
	}

	private void CreateRoom() {
		socket.EmitJson("createRoom", "{\"pid\":\"CreateRoom\",\"room\":\"TestRoom\"}");
	}

	private void OnApplicationQuit() {
		socket.OnDisconnect();
	}

	// Update is called once per frame
	void Update() {
	}

}