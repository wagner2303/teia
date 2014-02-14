package br.ufmt.teia.model;

import java.util.List;

public class BuildingsComplex {

	private Integer id;
	private String name;
	private List<Building> buildings;
	
	public BuildingsComplex() {
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	public void addBuilding(Building building) {
		buildings.add(building);
	}
}
