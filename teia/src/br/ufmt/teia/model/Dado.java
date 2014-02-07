package br.ufmt.teia.model;


public class Dado {
	
	private long data;
	private Room codRoom;
	private WiFiNetwork codWiFi;
	private int signalLevel;
	
	public Dado(long data, Room codRoom, WiFiNetwork codWiFi, int signalLevel) {
		super();
		this.data = data;
		this.codRoom = codRoom;
		this.codWiFi = codWiFi;
		this.signalLevel = signalLevel;
	}

	public Dado() {
	}

	// Getters
	public long getData() {
		return data;
	}

	public Room getCodRoom() {
		return codRoom;
	}

	public WiFiNetwork getCodWiFi() {
		return codWiFi;
	}

	public int getSignalLevel() {
		return signalLevel;
	}

	// Setters
	public void setData(long data) {
		this.data = data;
	}

	public void setCodRoom(Room codRoom) {
		this.codRoom = codRoom;
	}

	public void setCodWiFi(WiFiNetwork codWiFi) {
		this.codWiFi = codWiFi;
	}

	public void setSignalLevel(int signalLevel) {
		this.signalLevel = signalLevel;
	}
	
	
}
