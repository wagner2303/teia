package br.ufmt.teia.model;

public class Room {

	private Integer codRoom = null;
	private String nameRoom;
	
	public Room(Integer codRoom, String nameRoom) {
		this.codRoom = codRoom;
		this.nameRoom = nameRoom;
	}
	
	public Room() {}

	public Room(String nameRoom) {
		this.nameRoom = nameRoom;
	}

	// Getters
	public Integer getCodRoom() {
		return codRoom;
	}

	public String getNameRoom() {
		return nameRoom;
	}

	// Setters
	public void setCodRoom(Integer codRoom) {
		this.codRoom = codRoom;
	}

	public void setNameRoom(String nameRoom) {
		this.nameRoom = nameRoom;
	}
	
	
}
