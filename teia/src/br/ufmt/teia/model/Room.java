package br.ufmt.teia.model;

import java.util.List;

public class Room {

	private Integer codRoom = null;
	private String nameRoom;
	private Building building;
	private List<Room> neighbors;
	
	public Room(Integer codRoom, String nameRoom) {
		this.codRoom = codRoom;
		this.nameRoom = nameRoom;
	}
	
	public Room() {}

	public Room(String nameRoom) {
		this.nameRoom = nameRoom;
	}
			
	public Integer getCodRoom() {
		return codRoom;
	}

	public String getNameRoom() {
		return nameRoom;
	}

	public Building getBuilding() {
		return building;
	}

	public List<Room> getNeighbors() {
		return neighbors;
	}

	public void setCodRoom(Integer codRoom) {
		this.codRoom = codRoom;
	}

	public void setNameRoom(String nameRoom) {
		this.nameRoom = nameRoom;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public void setNeighbors(List<Room> neighbors) {
		this.neighbors = neighbors;
	}

	public void addNeighbor(Room neighbor) {
		neighbors.add(neighbor);
	}

	@Override
	public String toString() {
		return "Room [codRoom=" + codRoom + ", nameRoom=" + nameRoom + "]";
	}
	
	
}
