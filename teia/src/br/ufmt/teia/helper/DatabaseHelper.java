package br.ufmt.teia.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	// Metadados
	private static final int version = 1;
	private static final String name = "datamanager.db";

	// Table names
	private static final String TABLE_BUILDINGS = "buildings";
	private static final String TABLE_BUILDINGSCOMPLEXES = "buildingscomplexes";
	private static final String TABLE_PERCEIVEDROUTERS = "perceivedrouters";
	private static final String TABLE_ROOMS = "rooms";
	private static final String TABLE_ROUTERS = "routers";

	// BUILDINGS COLUMNS
	private static final String COLUMN_BUILDINGS_ID = "idbuilding";
	private static final String COLUMN_BUILDINGS_NAME = "namebuilding";
	
	// BUILDINGSCOMPLEXES COLUMNS
	private static final String COLUMN_BUILDINGSCOMPLEXES_ID = "idBuildingComplexes";
	private static final String COLUMN_BUILDINGSCOMPLEXES_NAME = "nameBuildingComplexes";
	
	// PERCEIVEDROUTER COLUMNS
	private static final String COLUMN_PERCEIVEDROUTERS_DATE = "datePerceivedRouter";
	private static final String COLUMN_PERCEIVEDROUTERS_SIGNALLEVEL = "signalLevel";
	
	// ROOMS COLUMNS
	private static final String COLUMN_ROOM_ID = "idroom";
	private static final String COLUMN_ROOM_NAME = "nameroom";
	
	// ROUTERS COLUMNS
	private static final String COLUMN_ROUTERS_ID = "idRouter";
	private static final String COLUMN_ROUTERS_NAME = "nameRouter";
	private static final String COLUMN_ROUTERS_MAC = "macRouter";

	// Table Create Statements

	// BuildingsComplexes Table
	private static final String CREATE_TABLE_BUILDINGSCOMPLEXES = "create table " + TABLE_BUILDINGSCOMPLEXES + " ( "
			+ COLUMN_BUILDINGSCOMPLEXES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_BUILDINGSCOMPLEXES_NAME + " TEXT NOT NULL "
			+ ")";
	// Buildings Table
	private static final String CREATE_TABLE_BUILDINGS = "create table " + TABLE_BUILDINGS + " ( "
			+ COLUMN_BUILDINGS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_BUILDINGS_NAME + " TEXT NOT NULL, "
			+ COLUMN_BUILDINGSCOMPLEXES_ID + " INTEGER FOREIGN KEY REFERENCES " + TABLE_BUILDINGSCOMPLEXES + "(" + COLUMN_BUILDINGSCOMPLEXES_ID + ")"
			+ ")";
	// PerceivedRouters
	private static final String CREATE_TABLE_PERCEIVEDROUTERS = "create table " + TABLE_PERCEIVEDROUTERS + " ( "
			+ COLUMN_PERCEIVEDROUTERS_DATE + " INTEGER PRIMARY KEY, "
			+ COLUMN_ROOM_ID + " INTEGER FOREIGN KEY REFERENCES " + TABLE_ROOMS + "(" + COLUMN_ROOM_ID + "), "
			+ COLUMN_ROUTERS_ID + " INTEGER FOREIGN KEY REFERENCES " + TABLE_ROUTERS + "(" + COLUMN_ROUTERS_ID + "), "
			+ COLUMN_PERCEIVEDROUTERS_SIGNALLEVEL + " INTEGER NOT NULL "
			+ ")";
	// Rooms Table
	private static final String CREATE_TABLE_ROOMS = "create table " + TABLE_ROOMS + " ( "
			+ COLUMN_ROOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_ROOM_NAME + " TEXT not null, "
			+ COLUMN_BUILDINGS_ID + " INTEGER FOREIGN KEY REFERENCES " + TABLE_BUILDINGS + "(" + COLUMN_BUILDINGS_ID + ")"
			+ ")";
	private static final String CREATE_TABLE_ROUTERS = "create table " + TABLE_ROUTERS + " ( "
			+ COLUMN_ROUTERS_ID + " INTEGER PRIMARY KEY AUTO INCREMENT, "
			+ COLUMN_ROUTERS_NAME + " TEXT, "
			+ COLUMN_ROUTERS_MAC + " TEXT NOT NULL, "
			+ COLUMN_BUILDINGS_ID + " INTEGER FOREIGN KEY REFERENCES " + TABLE_BUILDINGS + "(" + COLUMN_BUILDINGS_ID + ")"
			+ ")";
//
//	// Dados Table
//	private static final String CREATE_TABLE_DADOS = "create table " + TABLE_DADOS + " ( "
//			+ COLUMN_DADOS_DATA + " LONG not null, "
//			+ COLUMN_ROOM_ID + " INTEGER FOREIGN KEY REFERENCES " + TABLE_ROOMS + "(" + COLUMN_ROOM_ID + "), "
//			+ COLUMN_WIFINETWORKS_ID + " INTEGER FOREIGN KEY REFERENCES " + TABLE_WIFINETWORKS + "(" + COLUMN_WIFINETWORKS_ID + "), "
//			+ COLUMN_DADOS_SIGNALLEVEL + " INTEGER not null "
//			+ ")";

	public DatabaseHelper(Context context) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating tables
		db.execSQL(CREATE_TABLE_BUILDINGSCOMPLEXES);
		db.execSQL(CREATE_TABLE_BUILDINGS);
		db.execSQL(CREATE_TABLE_ROOMS);
		db.execSQL(CREATE_TABLE_ROUTERS);
		db.execSQL(CREATE_TABLE_PERCEIVEDROUTERS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
//
//	/**
//	 * Inserts a Room on database
//	 * @param room The new Room
//	 * @return the row ID of the newly inserted row, or -1 if an error occurred 
//	 */
//	public long createRoom(Room room){
//		SQLiteDatabase database = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		if(room.getCodRoom() != null)
//			values.put(COLUMN_ROOM_ID, room.getCodRoom());
//		values.put(COLUMN_ROOM_NAME, room.getNameRoom());
//
//		return database.insert(TABLE_ROOMS, null, values);
//	}
//
//	/**
//	 * Inserts a WiFiNetwork on database
//	 * @param wiFiNetwork The new WiFiNetwork
//	 * @return the row ID of the newly inserted row, or -1 if an error occurred 
//	 */
//	public long createWiFiNetwork(WiFiNetwork wiFiNetwork){
//		SQLiteDatabase database = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		if(wiFiNetwork.getCodWiFi() != null)
//			values.put(COLUMN_WIFINETWORKS_ID, wiFiNetwork.getCodWiFi());
//		values.put(COLUMN_WIFINETWORKS_BSSID, wiFiNetwork.getBSSID());
//		values.put(COLUMN_WIFINETWORKS_SSID, wiFiNetwork.getSSID());
//
//		return database.insert(TABLE_WIFINETWORKS, null, values);
//	}
//
//	/**
//	 * Inserts a Dado on database
//	 * @param perceivedRouter The new Dado
//	 * @return the row ID of the newly inserted row, or -1 if an error occurred 
//	 */
//	public long createDado(PerceivedRouter perceivedRouter) {
//		SQLiteDatabase database = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(COLUMN_DADOS_DATA, perceivedRouter.getData());
//
//		// Verifica se existe o Room no banco
//		if (getRoom(perceivedRouter.getCodRoom().getCodRoom()) == null)
//			createRoom(perceivedRouter.getCodRoom());
//
//		values.put(COLUMN_ROOM_ID, 
//				getRoom(perceivedRouter.getCodRoom().getCodRoom()).getCodRoom());
//
//		if (getWiFiNetwork(perceivedRouter.getCodWiFi().getCodWiFi()) == null)
//			createWiFiNetwork(perceivedRouter.getCodWiFi());
//
//		values.put(COLUMN_WIFINETWORKS_ID, 
//				getWiFiNetwork(perceivedRouter.getCodWiFi().getCodWiFi()).getCodWiFi());
//
//		values.put(COLUMN_DADOS_SIGNALLEVEL, perceivedRouter.getSignalLevel());
//
//		return database.insert(TABLE_DADOS, null, values);
//	}
//
//	/**
//	 * Gets a single Room
//	 * @param codRoom The identifier of the Room
//	 * @return The Room if it exists or null if it do not exists on database
//	 */
//	public Room getRoom(Integer codRoom) {
//		SQLiteDatabase database = this.getReadableDatabase();
//
//		String query = "SELECT * FROM " + TABLE_ROOMS + " WHERE "
//				+ COLUMN_ROOM_ID + " = " + codRoom;
//
//		Cursor c = database.rawQuery(query, null);
//
//		if (c == null)
//			return null;
//
//		c.moveToFirst();
//
//		Room room = new Room();
//		room.setCodRoom(c.getInt(c.getColumnIndex(COLUMN_ROOM_ID)));
//		room.setNameRoom(c.getString(c.getColumnIndex(COLUMN_ROOM_NAME)));
//
//		return room;
//	}
//
//	/**
//	 * Gets a single Room
//	 * @param name The name of the Room to be searched
//	 * @return The first Room with the given name if it exists or null if it do not exist on database
//	 */
//	public Room getRoom(String name) {
//		SQLiteDatabase database = this.getReadableDatabase();
//
//		String query = "SELECT * FROM " + TABLE_ROOMS + " WHERE "
//				+ COLUMN_ROOM_NAME + " = " + name;
//
//		Cursor c = database.rawQuery(query, null);
//
//		if (c == null)
//			return null;
//
//		c.moveToFirst();
//
//		Room room = new Room();
//		room.setCodRoom(c.getInt(c.getColumnIndex(COLUMN_ROOM_ID)));
//		room.setNameRoom(c.getString(c.getColumnIndex(COLUMN_ROOM_NAME)));
//
//		return room;
//	}
//
//	/**
//	 * Gets a single WiFiNetwork based on your ID
//	 * @param codWiFi The WiFiNetwork identifier
//	 * @return The first WiFiNetwork with the given name if it exists or null if it do not exist on database
//	 */
//	public WiFiNetwork getWiFiNetwork(Integer codWiFi) {
//		SQLiteDatabase database = this.getReadableDatabase();
//
//		String sql = "SELECT * FROM " + TABLE_WIFINETWORKS + " WHERE "
//				+ COLUMN_WIFINETWORKS_ID + " = " + codWiFi;
//
//		Cursor c = database.rawQuery(sql, null);
//
//		if (c == null)
//			return null;
//
//		c.moveToFirst();
//
//		WiFiNetwork wiFiNetwork = new WiFiNetwork();
//		wiFiNetwork.setCodWiFi(c.getInt(c.getColumnIndex(COLUMN_WIFINETWORKS_ID)));
//		wiFiNetwork.setSSID(c.getString(c.getColumnIndex(COLUMN_WIFINETWORKS_SSID)));
//		wiFiNetwork.setBSSID(c.getString(c.getColumnIndex(COLUMN_WIFINETWORKS_BSSID)));
//
//		return wiFiNetwork;
//	}
//
//	/**
//	 * Gets a single WiFiNetwork based on your BSSID
//	 * @param bssid The WiFiNetwork BSSID
//	 * @return The first WiFiNetwork with the given name if it exists or null if it do not exist on database
//	 */
//	public WiFiNetwork getWiFiNetwork(String bssid) {
//		SQLiteDatabase database = this.getReadableDatabase();
//
//		String sql = "SELECT * FROM " + TABLE_WIFINETWORKS + " WHERE "
//				+ COLUMN_WIFINETWORKS_BSSID + " = " + bssid;
//
//		Cursor c = database.rawQuery(sql, null);
//
//		if (c == null)
//			return null;
//
//		c.moveToFirst();
//
//		WiFiNetwork wiFiNetwork = new WiFiNetwork();
//		wiFiNetwork.setCodWiFi(c.getInt(c.getColumnIndex(COLUMN_WIFINETWORKS_ID)));
//		wiFiNetwork.setSSID(c.getString(c.getColumnIndex(COLUMN_WIFINETWORKS_SSID)));
//		wiFiNetwork.setBSSID(c.getString(c.getColumnIndex(COLUMN_WIFINETWORKS_BSSID)));
//
//		return wiFiNetwork;
//	}
//
//	/**
//	 * Gets all Rooms
//	 * @return A List of Rooms
//	 */
//	public List<Room> getAllRooms() {
//		SQLiteDatabase database = this.getReadableDatabase();
//
//		String sql = "SELECT * FROM " + TABLE_ROOMS;
//
//		Cursor c = database.rawQuery(sql, null);
//
//		List<Room> rooms = new ArrayList<Room>();
//
//		if (c.moveToFirst()) {
//			do {
//				Room room = new Room();
//				room.setCodRoom(c.getInt(c.getColumnIndex(COLUMN_ROOM_ID)));
//				room.setNameRoom(c.getString(c.getColumnIndex(COLUMN_ROOM_NAME)));
//
//				rooms.add(room);
//			} while (c.moveToNext());
//		}
//
//		return rooms;
//	}
//
//	/**
//	 * Gets all {@link WiFiNetwork} elements
//	 * @return A {@link List} of {@link WiFiNetwork}
//	 */
//	public List<WiFiNetwork> getAllWiFiNetworks() {
//		SQLiteDatabase database = this.getReadableDatabase();
//
//		String sql = "SELECT * FROM " + TABLE_WIFINETWORKS;
//
//		Cursor c = database.rawQuery(sql, null);
//
//		List<WiFiNetwork> wiFiNetworks = new ArrayList<WiFiNetwork>();
//
//		if (c.moveToFirst()) {
//			do {
//				WiFiNetwork wiFiNetwork = new WiFiNetwork();
//				wiFiNetwork.setCodWiFi(c.getInt(c.getColumnIndex(COLUMN_WIFINETWORKS_ID)));
//				wiFiNetwork.setSSID(c.getString(c.getColumnIndex(COLUMN_WIFINETWORKS_SSID)));
//				wiFiNetwork.setBSSID(c.getString(c.getColumnIndex(COLUMN_WIFINETWORKS_BSSID)));
//
//				wiFiNetworks.add(wiFiNetwork);
//			} while (c.moveToNext());
//		}
//
//		return wiFiNetworks;
//	}
//
//	/**
//	 * Get all {@link PerceivedRouter} elements
//	 * @return A {@link List} of {@link PerceivedRouter}
//	 */
//	public List<PerceivedRouter> getAllDados() {
//		SQLiteDatabase database = this.getReadableDatabase();
//
//		String sql = "SELECT * FROM " + TABLE_DADOS;
//
//		Cursor c = database.rawQuery(sql, null);
//
//		List<PerceivedRouter> perceivedRouters = new ArrayList<PerceivedRouter>();
//
//		if (c.moveToFirst()) {
//			do {
//				PerceivedRouter perceivedRouter = new PerceivedRouter();
//				perceivedRouter.setData(c.getLong(c.getColumnIndex(COLUMN_DADOS_DATA)));
//				perceivedRouter.setSignalLevel(c.getInt(c.getColumnIndex(COLUMN_DADOS_SIGNALLEVEL)));
//				perceivedRouter.setCodRoom(getRoom(c.getInt(c.getColumnIndex(COLUMN_ROOM_ID))));
//				perceivedRouter.setCodWiFi(getWiFiNetwork(c.getInt(c.getColumnIndex(COLUMN_WIFINETWORKS_ID))));
//
//				perceivedRouters.add(perceivedRouter);
//			} while (c.moveToNext());
//		}
//		return perceivedRouters;
//	}

	// outras consultas
	// TODO

	// updates
	//	public int updateToDo(Todo todo) {
	//		SQLiteDatabase db = this.getWritableDatabase();
	//
	//		ContentValues values = new ContentValues();
	//		values.put(KEY_TODO, todo.getNote());
	//		values.put(KEY_STATUS, todo.getStatus());
	//
	//		// updating row
	//		return db.update(TABLE_TODO, values, KEY_ID + " = ?",
	//				new String[] { String.valueOf(todo.getId()) });
	//	}
	// TODO

	// deleters
	//	public void deleteToDo(long tado_id) {
	//		SQLiteDatabase db = this.getWritableDatabase();
	//		db.delete(TABLE_TODO, KEY_ID + " = ?",
	//				new String[] { String.valueOf(tado_id) });
	//	}
	// TODO

	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}
}
