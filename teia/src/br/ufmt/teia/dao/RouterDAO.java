package br.ufmt.teia.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufmt.teia.helper.DatabaseHelper;
import br.ufmt.teia.model.Building;
import br.ufmt.teia.model.Router;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RouterDAO {

	private Context context;
	
	public RouterDAO(Context context) {
		super();
		this.setContext(context);
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public void save(Router router) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Names.RT_ID, router.getId());
		values.put(Names.RT_MAC, router.getMAC());
		if (router.getName() != null)
			values.put(Names.RT_NAME, router.getName());
		if (router.getBuilding() != null){
			BuildingDAO buildingDAO = new BuildingDAO(context);
			if (buildingDAO.find(router.getBuilding().getId()) == null)
				buildingDAO.save(router.getBuilding());
			values.put(Names.B_ID, router.getBuilding().getId());
		}
		database.insert(Names.ROUTERS, null, values);
		databaseHelper.close();
	}
	
	public void delete(Router router) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		database.delete(Names.ROUTERS, Names.RT_ID + Names.EQ 
				+ router.getId().toString(), null);
		databaseHelper.close();
	}
	
	//list
	public List<Router> list() {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getReadableDatabase();
		Cursor c = database.rawQuery(Names.SELECT + Names.ROUTERS, null);
		if (!c.moveToFirst())
			return null;
		List<Router> list = new ArrayList<Router>();
		BuildingDAO buildingDAO = new BuildingDAO(context);
		do {
			Router router = new Router();
			router.setId(c.getInt(c.getColumnIndex(Names.RT_ID)));
			router.setName(c.getString(c.getColumnIndex(Names.RT_NAME)));
			router.setMAC(c.getString(c.getColumnIndex(Names.RT_MAC)));
			router.setBuilding(buildingDAO.find(c.getInt(c.getColumnIndex(Names.B_ID))));
			list.add(router);
		} while (c.moveToNext());
		databaseHelper.close();
		return list;
	}
	
	public List<Router> listByBuilding(Building building) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getReadableDatabase();
		Cursor c = database.rawQuery(Names.SELECT + Names.ROUTERS 
				+ Names.WHERE + Names.B_ID + Names.EQ + building.getId().toString(), null);
		if (!c.moveToFirst())
			return null;
		List<Router> list = new ArrayList<Router>();
		BuildingDAO buildingDAO = new BuildingDAO(context);
		do {
			Router router = new Router();
			router.setId(c.getInt(c.getColumnIndex(Names.RT_ID)));
			router.setName(c.getString(c.getColumnIndex(Names.RT_NAME)));
			router.setMAC(c.getString(c.getColumnIndex(Names.RT_MAC)));
			router.setBuilding(buildingDAO.find(c.getInt(c.getColumnIndex(Names.B_ID))));
			list.add(router);
		} while (c.moveToNext());
		databaseHelper.close();
		return list;
	}
	
	//find
	public Router find(Integer id) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getReadableDatabase();
		Cursor c = database.rawQuery(Names.SELECT + Names.ROUTERS
				+ Names.WHERE + Names.RT_ID + Names.EQ + id.toString(), null);
		if (!c.moveToFirst())
			return null;
		Router router = new Router();
		router.setId(c.getInt(c.getColumnIndex(Names.RT_ID)));
		router.setName(c.getString(c.getColumnIndex(Names.RT_NAME)));
		router.setMAC(c.getString(c.getColumnIndex(Names.RT_MAC)));
		router.setBuilding(new BuildingDAO(context).find(c.getInt(c.getColumnIndex(Names.B_ID))));
		databaseHelper.close();
		return router;
	}
	
	//update
	public int update(Router router) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Names.RT_ID, router.getId());
		values.put(Names.RT_MAC, router.getMAC());
		if (router.getName() != null)
			values.put(Names.RT_NAME, router.getName());
		if (router.getBuilding() != null){
			BuildingDAO buildingDAO = new BuildingDAO(context);
			if (buildingDAO.find(router.getBuilding().getId()) == null)
				buildingDAO.save(router.getBuilding());
			values.put(Names.B_ID, router.getBuilding().getId());
		}
		int i = database.update(Names.ROUTERS, values, 
				Names.RT_ID + Names.EQ + router.getId().toString(), null);
		databaseHelper.close();
		return i;
	}
}
