package br.ufmt.teia.model;


public class PerceivedRouter {
	
	private Long date;
	private Room codRoom;
	private Router idRouter;
	private Integer signalLevel;
	
	public PerceivedRouter(long date, Room codRoom, Router idRouter, int signalLevel) {
		super();
		this.date = date;
		this.codRoom = codRoom;
		this.idRouter = idRouter;
		this.signalLevel = signalLevel;
	}

	public PerceivedRouter() {
	}

	public Long getDate() {
		return date;
	}

	public Room getCodRoom() {
		return codRoom;
	}

	public Router getIdRouter() {
		return idRouter;
	}

	public Integer getSignalLevel() {
		return signalLevel;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public void setCodRoom(Room codRoom) {
		this.codRoom = codRoom;
	}

	public void setIdRouter(Router idRouter) {
		this.idRouter = idRouter;
	}

	public void setSignalLevel(Integer signalLevel) {
		this.signalLevel = signalLevel;
	}
	
		
}
