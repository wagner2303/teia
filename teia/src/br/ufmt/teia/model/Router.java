package br.ufmt.teia.model;

import java.util.List;

public class Router {

	private Integer id;
	private String name;
	private String MAC;
	private Building building;
	
	public Router() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mAC) {
		MAC = mAC;
	}
	
	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public List<Room> reaches(int minIntensity) {
		// TODO 
		return null;
	}
}
