package br.ufmt.teia.model;

import java.util.List;

public class Building {

	private Integer id;
	private String name;
	private List<Room> rooms;
	private List<Router> routers;
	
	public Building() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public List<Router> getRouters() {
		return routers;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public void setRouters(List<Router> routers) {
		this.routers = routers;
	}
	
	public void addRoom(Room room) {
		rooms.add(room);
	}
	
	public void addRouter(Router router) {
		routers.add(router);
	}
}
