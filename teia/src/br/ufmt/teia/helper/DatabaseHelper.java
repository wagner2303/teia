package br.ufmt.teia.helper;

import br.ufmt.teia.dao.Names;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	// Metadados
	private static final int version = 1;
	private static final String name = "datamanager.db";

	private static final String CREATE_TABLE = "create table ";
	private static final String INT = " integer ";
	private static final String PK = " primary key ";
	private static final String TEXT = " text ";
	private static final String NOT_NULL = " not null ";
	private static final String FK = " references ";
	private static final String VG = ", ";
	private static final String SERIAL = INT + PK + "autoincrement";
	
	// Table Create Statements

	// BuildingsComplexes Table
	private static final String CT_BUILDINGSCOMPLEXES = CREATE_TABLE + Names.BCOMPLEXES + " ( "
			+ Names.BC_ID + SERIAL + VG
			+ Names.BC_NAME + TEXT + NOT_NULL
			+ ")";
	// Buildings Table
	private static final String CT_BUILDINGS = CREATE_TABLE + Names.BUILDINGS + " ( "
			+ Names.B_ID + SERIAL + VG
			+ Names.B_NAME + TEXT + NOT_NULL + VG
			+ Names.BC_ID + INT + FK + Names.BCOMPLEXES + "(" + Names.BC_ID + ")"
			+ ")";
	// PerceivedRouters
	private static final String CT_PERCEIVEDROUTERS = CREATE_TABLE + Names.PROUTERS + " ( "
			+ Names.PR_DATE + INT + PK + VG
			+ Names.RM_ID + INT + FK + Names.ROOMS + "(" + Names.RM_ID + "), "
			+ Names.RT_ID + INT + FK + Names.ROUTERS + "(" + Names.RT_ID + "), "
			+ Names.PR_SIGNALLEVEL + INT + NOT_NULL
			+ ")";
	// Rooms Table
	private static final String CT_ROOMS = CREATE_TABLE + Names.ROOMS + " ( "
			+ Names.RM_ID + SERIAL + VG
			+ Names.RM_NAME + TEXT + NOT_NULL + VG
			+ Names.B_ID + INT + FK + Names.BUILDINGS + "(" + Names.B_ID + ")"
			+ ")";
	private static final String CT_ROUTERS = CREATE_TABLE + Names.ROUTERS + " ( "
			+ Names.RT_ID + SERIAL + VG
			+ Names.RT_NAME + TEXT + VG
			+ Names.RT_MAC + TEXT + NOT_NULL + VG
			+ Names.B_ID + INT + FK + Names.BUILDINGS + "(" + Names.B_ID + ")"
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
		db.execSQL(CT_BUILDINGSCOMPLEXES);
		db.execSQL(CT_BUILDINGS);
		db.execSQL(CT_ROOMS);
		db.execSQL(CT_ROUTERS);
		db.execSQL(CT_PERCEIVEDROUTERS);
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
