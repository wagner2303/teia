package br.ufmt.teia.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufmt.teia.helper.DatabaseHelper;
import br.ufmt.teia.model.Building;

public class BuildingDAO {

private Context context;
	
	public BuildingDAO(Context context) {
		this.setContext(context);
	}
	
	public void setContext(Context context) {
		this.context = context;
	}

	public void save(Building building) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Names.B_ID, building.getId());
		values.put(Names.B_NAME, building.getName());
		//TODO
		database.insert(Names.BUILDINGS, null, values);
		databaseHelper.close();
	}
	
	public void delete(Building building) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		database.delete(Names.BUILDINGS, 
				Names.B_ID + Names.EQ + building.getId().toString(), null);
		databaseHelper.close();
	}
	
	//list
	public List<Building> list() {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getReadableDatabase();
		Cursor c = database.rawQuery(Names.SELECT + Names.BUILDINGS, null);
		if (!c.moveToFirst())
			return null;
		List<Building> list = new ArrayList<Building>();
		do {
			Building building = new Building();
			building.setId(c.getInt(c.getColumnIndex(Names.B_ID)));
			building.setName(c.getString(c.getColumnIndex(Names.B_NAME)));
			list.add(building);
		} while (c.moveToNext());
		databaseHelper.close();
		return list;
	}
	
	//find
	public Building find(Integer id) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getReadableDatabase();
		Cursor c = database.rawQuery(Names.SELECT + Names.BUILDINGS
				+ Names.WHERE + Names.B_ID + Names.EQ + id.toString(), null);
		if (!c.moveToFirst())
			return null;
		Building building = new Building();
		building.setId(c.getInt(c.getColumnIndex(Names.B_ID)));
		building.setName(c.getString(c.getColumnIndex(Names.B_NAME)));
		databaseHelper.close();
		return building;
	}
	
	//update
	public int update(Building building) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Names.B_ID, building.getId());
		values.put(Names.B_NAME, building.getName());
		int i = database.update(Names.BUILDINGS, values, 
				Names.B_ID + Names.EQ + building.getId().toString(), null);
		databaseHelper.close();
		return i;
	}
}
