package br.ufmt.teia.model;


public class PerceivedRouter {
	
	private Long date;
	private Room room;
	private Router router;
	private Integer signalLevel;
	
	public PerceivedRouter(long date, Room codRoom, Router idRouter, int signalLevel) {
		super();
		this.date = date;
		this.room = codRoom;
		this.router = idRouter;
		this.signalLevel = signalLevel;
	}

	public PerceivedRouter() {
	}

	public Long getDate() {
		return date;
	}

	public Room getRoom() {
		return room;
	}

	public Router getRouter() {
		return router;
	}

	public Integer getSignalLevel() {
		return signalLevel;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public void setRoom(Room codRoom) {
		this.room = codRoom;
	}

	public void setRouter(Router idRouter) {
		this.router = idRouter;
	}

	public void setSignalLevel(Integer signalLevel) {
		this.signalLevel = signalLevel;
	}

	@Override
	public String toString() {
		return "PerceivedRouter [date=" + date + ", room=" + room + ", router="
				+ router + ", signalLevel=" + signalLevel + "]";
	}
		
}
