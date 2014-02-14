package br.ufmt.teia.model;

import java.util.List;

public class Room {

	private Integer codRoom = null;
	private String nameRoom;
	private List<Room> neighbors;
	
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

	public List<Room> getNeighbors() {
		return neighbors;
	}

	// Setters
	public void setCodRoom(Integer codRoom) {
		this.codRoom = codRoom;
	}

	public void setNameRoom(String nameRoom) {
		this.nameRoom = nameRoom;
	}

	public void setNeighbors(List<Room> neighbors) {
		this.neighbors = neighbors;
	}
	
	public void addNeighbor(Room neighbor) {
		neighbors.add(neighbor);
	}
}
