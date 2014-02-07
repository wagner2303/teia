package br.ufmt.teia.helper;

import java.util.ArrayList;
import java.util.List;

import br.ufmt.teia.model.Dado;
import br.ufmt.teia.model.Room;
import br.ufmt.teia.model.WiFiNetwork;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabasseHelper extends SQLiteOpenHelper{

	// Metadados
	private static final int version = 1;
	private static final String name = "datamanager.db";

	// Table names
	private static final String TABLE_ROOMS = "rooms";
	private static final String TABLE_WIFINETWORKS = "wifinetworks";
	private static final String TABLE_DADOS = "dados";

	// ROOMS COLUMNS
	private static final String COLUMN_ROOM_ID = "codroom";
	private static final String COLUMN_ROOM_NAME = "nameroom";

	// WIFINETWORKS COLUMNS
	private static final String COLUMN_WIFINETWORKS_ID = "codwifi";
	private static final String COLUMN_WIFINETWORKS_BSSID = "bssid";
	private static final String COLUMN_WIFINETWORKS_SSID = "ssid";

	// DADOS COLUMNS
	private static final String COLUMN_DADOS_DATA = "data";
	private static final String COLUMN_DADOS_SIGNALLEVEL = "signallevel";

	// Table Create Statements

	// Rooms Table
	private static final String CREATE_TABLE_ROOMS = "create table " + TABLE_ROOMS + " ( "
			+ COLUMN_ROOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_ROOM_NAME + " TEXT not null"
			+ ")";

	// WiFiNetworks Table
	private static final String CREATE_TABLE_WIFINETWORKS = "create table " + TABLE_WIFINETWORKS + " ( "
			+ COLUMN_WIFINETWORKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_WIFINETWORKS_BSSID + " TEXT, "
			+ COLUMN_WIFINETWORKS_SSID + " TEXT not null"
			+ ")";

	// Dados Table
	private static final String CREATE_TABLE_DADOS = "create table " + TABLE_DADOS + " ( "
			+ COLUMN_DADOS_DATA + " LONG not null, "
			+ COLUMN_ROOM_ID + " INTEGER FOREIGN KEY REFERENCES " + TABLE_ROOMS + "(" + COLUMN_ROOM_ID + "), "
			+ COLUMN_WIFINETWORKS_ID + " INTEGER FOREIGN KEY REFERENCES " + TABLE_WIFINETWORKS + "(" + COLUMN_WIFINETWORKS_ID + "), "
			+ COLUMN_DADOS_SIGNALLEVEL + " INTEGER not null "
			+ ")";

	public DatabasseHelper(Context context) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating tables
		db.execSQL(CREATE_TABLE_ROOMS);
		db.execSQL(CREATE_TABLE_WIFINETWORKS);
		db.execSQL(CREATE_TABLE_DADOS);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/**
	 * Inserts a Room on database
	 * @param room The new Room
	 * @return the row ID of the newly inserted row, or -1 if an error occurred 
	 */
	public long createRoom(Room room){
		SQLiteDatabase database = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		if(room.getCodRoom() != null)
			values.put(COLUMN_ROOM_ID, room.getCodRoom());
		values.put(COLUMN_ROOM_NAME, room.getNameRoom());

		return database.insert(TABLE_ROOMS, null, values);
	}

	/**
	 * Inserts a WiFiNetwork on database
	 * @param wiFiNetwork The new WiFiNetwork
	 * @return the row ID of the newly inserted row, or -1 if an error occurred 
	 */
	public long createWiFiNetwork(WiFiNetwork wiFiNetwork){
		SQLiteDatabase database = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		if(wiFiNetwork.getCodWiFi() != null)
			values.put(COLUMN_WIFINETWORKS_ID, wiFiNetwork.getCodWiFi());
		values.put(COLUMN_WIFINETWORKS_BSSID, wiFiNetwork.getBSSID());
		values.put(COLUMN_WIFINETWORKS_SSID, wiFiNetwork.getSSID());

		return database.insert(TABLE_WIFINETWORKS, null, values);
	}

	/**
	 * Inserts a Dado on database
	 * @param dado The new Dado
	 * @return the row ID of the newly inserted row, or -1 if an error occurred 
	 */
	public long createDado(Dado dado) {
		SQLiteDatabase database = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_DADOS_DATA, dado.getData());

		// Verifica se existe o Room no banco
		if (getRoom(dado.getCodRoom().getCodRoom()) == null)
			createRoom(dado.getCodRoom());

		values.put(COLUMN_ROOM_ID, 
				getRoom(dado.getCodRoom().getCodRoom()).getCodRoom());

		if (getWiFiNetwork(dado.getCodWiFi().getCodWiFi()) == null)
			createWiFiNetwork(dado.getCodWiFi());

		values.put(COLUMN_WIFINETWORKS_ID, 
				getWiFiNetwork(dado.getCodWiFi().getCodWiFi()).getCodWiFi());

		values.put(COLUMN_DADOS_SIGNALLEVEL, dado.getSignalLevel());

		return database.insert(TABLE_DADOS, null, values);
	}

	/**
	 * Gets a single Room
	 * @param codRoom The identifier of the Room
	 * @return The Room if it exists or null if it do not exists on database
	 */
	public Room getRoom(Integer codRoom) {
		SQLiteDatabase database = this.getReadableDatabase();

		String query = "SELECT * FROM " + TABLE_ROOMS + " WHERE "
				+ COLUMN_ROOM_ID + " = " + codRoom;

		Cursor c = database.rawQuery(query, null);

		if (c == null)
			return null;

		c.moveToFirst();

		Room room = new Room();
		room.setCodRoom(c.getInt(c.getColumnIndex(COLUMN_ROOM_ID)));
		room.setNameRoom(c.getString(c.getColumnIndex(COLUMN_ROOM_NAME)));

		return room;
	}

	/**
	 * Gets a single Room
	 * @param name The name of the Room to be searched
	 * @return The first Room with the given name if it exists or null if it do not exist on database
	 */
	public Room getRoom(String name) {
		SQLiteDatabase database = this.getReadableDatabase();

		String query = "SELECT * FROM " + TABLE_ROOMS + " WHERE "
				+ COLUMN_ROOM_NAME + " = " + name;

		Cursor c = database.rawQuery(query, null);

		if (c == null)
			return null;

		c.moveToFirst();

		Room room = new Room();
		room.setCodRoom(c.getInt(c.getColumnIndex(COLUMN_ROOM_ID)));
		room.setNameRoom(c.getString(c.getColumnIndex(COLUMN_ROOM_NAME)));

		return room;
	}

	/**
	 * Gets a single WiFiNetwork based on your ID
	 * @param codWiFi The WiFiNetwork identifier
	 * @return The first WiFiNetwork with the given name if it exists or null if it do not exist on database
	 */
	public WiFiNetwork getWiFiNetwork(Integer codWiFi) {
		SQLiteDatabase database = this.getReadableDatabase();

		String sql = "SELECT * FROM " + TABLE_WIFINETWORKS + " WHERE "
				+ COLUMN_WIFINETWORKS_ID + " = " + codWiFi;

		Cursor c = database.rawQuery(sql, null);

		if (c == null)
			return null;

		c.moveToFirst();

		WiFiNetwork wiFiNetwork = new WiFiNetwork();
		wiFiNetwork.setCodWiFi(c.getInt(c.getColumnIndex(COLUMN_WIFINETWORKS_ID)));
		wiFiNetwork.setSSID(c.getString(c.getColumnIndex(COLUMN_WIFINETWORKS_SSID)));
		wiFiNetwork.setBSSID(c.getString(c.getColumnIndex(COLUMN_WIFINETWORKS_BSSID)));

		return wiFiNetwork;
	}

	/**
	 * Gets a single WiFiNetwork based on your BSSID
	 * @param bssid The WiFiNetwork BSSID
	 * @return The first WiFiNetwork with the given name if it exists or null if it do not exist on database
	 */
	public WiFiNetwork getWiFiNetwork(String bssid) {
		SQLiteDatabase database = this.getReadableDatabase();

		String sql = "SELECT * FROM " + TABLE_WIFINETWORKS + " WHERE "
				+ COLUMN_WIFINETWORKS_BSSID + " = " + bssid;

		Cursor c = database.rawQuery(sql, null);

		if (c == null)
			return null;

		c.moveToFirst();

		WiFiNetwork wiFiNetwork = new WiFiNetwork();
		wiFiNetwork.setCodWiFi(c.getInt(c.getColumnIndex(COLUMN_WIFINETWORKS_ID)));
		wiFiNetwork.setSSID(c.getString(c.getColumnIndex(COLUMN_WIFINETWORKS_SSID)));
		wiFiNetwork.setBSSID(c.getString(c.getColumnIndex(COLUMN_WIFINETWORKS_BSSID)));

		return wiFiNetwork;
	}

	/**
	 * Gets all Rooms
	 * @return A List of Rooms
	 */
	public List<Room> getAllRooms() {
		SQLiteDatabase database = this.getReadableDatabase();

		String sql = "SELECT * FROM " + TABLE_ROOMS;

		Cursor c = database.rawQuery(sql, null);

		List<Room> rooms = new ArrayList<Room>();

		if (c.moveToFirst()) {
			do {
				Room room = new Room();
				room.setCodRoom(c.getInt(c.getColumnIndex(COLUMN_ROOM_ID)));
				room.setNameRoom(c.getString(c.getColumnIndex(COLUMN_ROOM_NAME)));

				rooms.add(room);
			} while (c.moveToNext());
		}

		return rooms;
	}

	/**
	 * Gets all {@link WiFiNetwork} elements
	 * @return A {@link List} of {@link WiFiNetwork}
	 */
	public List<WiFiNetwork> getAllWiFiNetworks() {
		SQLiteDatabase database = this.getReadableDatabase();

		String sql = "SELECT * FROM " + TABLE_WIFINETWORKS;

		Cursor c = database.rawQuery(sql, null);

		List<WiFiNetwork> wiFiNetworks = new ArrayList<WiFiNetwork>();

		if (c.moveToFirst()) {
			do {
				WiFiNetwork wiFiNetwork = new WiFiNetwork();
				wiFiNetwork.setCodWiFi(c.getInt(c.getColumnIndex(COLUMN_WIFINETWORKS_ID)));
				wiFiNetwork.setSSID(c.getString(c.getColumnIndex(COLUMN_WIFINETWORKS_SSID)));
				wiFiNetwork.setBSSID(c.getString(c.getColumnIndex(COLUMN_WIFINETWORKS_BSSID)));

				wiFiNetworks.add(wiFiNetwork);
			} while (c.moveToNext());
		}

		return wiFiNetworks;
	}

	/**
	 * Get all {@link Dado} elements
	 * @return A {@link List} of {@link Dado}
	 */
	public List<Dado> getAllDados() {
		SQLiteDatabase database = this.getReadableDatabase();

		String sql = "SELECT * FROM " + TABLE_DADOS;

		Cursor c = database.rawQuery(sql, null);

		List<Dado> dados = new ArrayList<Dado>();

		if (c.moveToFirst()) {
			do {
				Dado dado = new Dado();
				dado.setData(c.getLong(c.getColumnIndex(COLUMN_DADOS_DATA)));
				dado.setSignalLevel(c.getInt(c.getColumnIndex(COLUMN_DADOS_SIGNALLEVEL)));
				dado.setCodRoom(getRoom(c.getInt(c.getColumnIndex(COLUMN_ROOM_ID))));
				dado.setCodWiFi(getWiFiNetwork(c.getInt(c.getColumnIndex(COLUMN_WIFINETWORKS_ID))));

				dados.add(dado);
			} while (c.moveToNext());
		}
		return dados;
	}

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