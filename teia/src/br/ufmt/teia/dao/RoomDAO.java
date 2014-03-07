package br.ufmt.teia.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufmt.teia.helper.DatabaseHelper;
import br.ufmt.teia.model.Room;

public class RoomDAO {

	private Context context;

	public RoomDAO(Context context) {
		this.setContext(context);
	}
	
	public void setContext(Context context) {
		this.context = context;
	}

	public void save(Room room) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Names.RM_ID, room.getCodRoom());
		values.put(Names.RM_NAME, room.getNameRoom());
		if (room.getBuilding() != null){
			BuildingDAO dao = new BuildingDAO(context);
			if (dao.find(room.getBuilding().getId()) == null)
				dao.save(room.getBuilding());
			values.put(Names.B_ID, room.getBuilding().getId());
		}
		database.insert(Names.ROOMS, null, values);
		databaseHelper.close();
	}

	public void delete(Room room) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		database.delete(Names.ROOMS, Names.RM_ID + Names.EQ 
				+ room.getCodRoom().toString(), null);
		databaseHelper.close();
	}

	//list
	public List<Room> list() {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getReadableDatabase();
		Cursor c = database.rawQuery(Names.SELECT + Names.ROOMS, null);
		if (!c.moveToFirst())
			return null;
		List<Room> list = new ArrayList<Room>();
		BuildingDAO dao = new BuildingDAO(context);
		do {
			Room room = new Room();
			room.setCodRoom(c.getInt(c.getColumnIndex(Names.RM_ID)));
			room.setNameRoom(c.getString(c.getColumnIndex(Names.RM_NAME)));
			room.setBuilding(dao.find(c.getInt(c.getColumnIndex(Names.B_ID))));
			list.add(room);
		} while (c.moveToNext());
		databaseHelper.close();
		return list;
	}
	
	//find
	public Room find(Integer id) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getReadableDatabase();
		Cursor c = database.rawQuery(Names.SELECT + Names.ROOMS
				+ Names.WHERE + Names.RM_ID + Names.EQ + id.toString(), null);
		if (!c.moveToFirst())
			return null;
		Room room = new Room();
		room.setCodRoom(c.getInt(c.getColumnIndex(Names.RM_ID)));
		room.setNameRoom(c.getString(c.getColumnIndex(Names.RM_NAME)));
		room.setBuilding(new BuildingDAO(context).find(c.getInt(c.getColumnIndex(Names.B_ID))));
		databaseHelper.close();
		return room;
	}

	//update
	public int update(Room room) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Names.RM_ID, room.getCodRoom());
		values.put(Names.RM_NAME, room.getNameRoom());
		if (room.getBuilding() != null){
			BuildingDAO dao = new BuildingDAO(context);
			if (dao.find(room.getBuilding().getId()) == null)
				dao.save(room.getBuilding());
			values.put(Names.B_ID, room.getBuilding().getId());
		}
		int i = database.update(Names.ROOMS, values, 
				Names.RM_ID + Names.EQ + room.getCodRoom().toString(), null);
		databaseHelper.close();
		return i;
	}
}
