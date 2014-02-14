package br.ufmt.teia.model;


public class PerceivedRouter {
	
	private long date;
	private Room codRoom;
	private Router idRouter;
	private int signalLevel;
	
	public PerceivedRouter(long date, Room codRoom, Router idRouter, int signalLevel) {
		super();
		this.date = date;
		this.codRoom = codRoom;
		this.idRouter = idRouter;
		this.signalLevel = signalLevel;
	}

	public PerceivedRouter() {
	}

	public long getDate() {
		return date;
	}

	public Room getCodRoom() {
		return codRoom;
	}

	public Router getIdRouter() {
		return idRouter;
	}

	public int getSignalLevel() {
		return signalLevel;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public void setCodRoom(Room codRoom) {
		this.codRoom = codRoom;
	}

	public void setIdRouter(Router idRouter) {
		this.idRouter = idRouter;
	}

	public void setSignalLevel(int signalLevel) {
		this.signalLevel = signalLevel;
	}
	
		
}
