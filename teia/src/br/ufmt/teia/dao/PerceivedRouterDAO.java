package br.ufmt.teia.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufmt.teia.helper.DatabaseHelper;
import br.ufmt.teia.model.PerceivedRouter;

public class PerceivedRouterDAO {

	private Context context;

	public PerceivedRouterDAO(Context context) {
		this.setContext(context);
	}
	
	public void setContext(Context context) {
		this.context = context;
	}

	public void save(PerceivedRouter perceivedRouter) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Names.PR_DATE, perceivedRouter.getDate());
		values.put(Names.PR_SIGNALLEVEL, perceivedRouter.getSignalLevel());
		if(perceivedRouter.getRoom() != null){
			RoomDAO dao = new RoomDAO(context);
			if (dao.find(perceivedRouter.getRoom().getCodRoom()) == null)
				dao.save(perceivedRouter.getRoom());
			values.put(Names.RM_ID, perceivedRouter.getRoom().getCodRoom());
		}
		if (perceivedRouter.getRouter() != null) {
			RouterDAO dao = new RouterDAO(context);
			if (dao.find(perceivedRouter.getRouter().getId()) == null)
				dao.save(perceivedRouter.getRouter());
			values.put(Names.RT_ID, perceivedRouter.getRouter().getId());
		}
		database.insert(Names.PROUTERS, null, values);
		databaseHelper.close();
	}

	public void delete(PerceivedRouter perceivedRouter) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		database.delete(Names.PROUTERS, Names.PR_DATE + Names.EQ 
				+ perceivedRouter.getDate().toString(), null);
		databaseHelper.close();
	}

	//list
	public List<PerceivedRouter> list() {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getReadableDatabase();
		Cursor c = database.rawQuery(Names.SELECT + Names.PROUTERS, null);
		if (!c.moveToFirst())
			return null;
		List<PerceivedRouter> list = new ArrayList<PerceivedRouter>();
		RoomDAO roomDAO = new RoomDAO(context);
		RouterDAO routerDAO = new RouterDAO(context);
		do {
			PerceivedRouter pr = new PerceivedRouter();
			pr.setDate(c.getLong(c.getColumnIndex(Names.PR_DATE)));
			pr.setSignalLevel(c.getInt(c.getColumnIndex(Names.PR_SIGNALLEVEL)));
			pr.setRoom(roomDAO.find(c.getInt(c.getColumnIndex(Names.RM_ID))));
			pr.setRouter(routerDAO.find(c.getInt(c.getColumnIndex(Names.RT_ID))));
			list.add(pr);
		} while (c.moveToNext());
		databaseHelper.close();
		return list;
	}
	
	//find
	public PerceivedRouter find(Long date) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getReadableDatabase();
		Cursor c = database.rawQuery(Names.SELECT + Names.PROUTERS
				+ Names.WHERE + Names.PR_DATE + Names.EQ + date.toString(), null);
		if (!c.moveToFirst())
			return null;
		PerceivedRouter pr = new PerceivedRouter();
		pr.setDate(c.getLong(c.getColumnIndex(Names.PR_DATE)));
		pr.setSignalLevel(c.getInt(c.getColumnIndex(Names.PR_SIGNALLEVEL)));
		pr.setRoom(new RoomDAO(context).find(c.getInt(c.getColumnIndex(Names.RM_ID))));
		pr.setRouter(new RouterDAO(context).find(c.getInt(c.getColumnIndex(Names.RT_ID))));
		databaseHelper.close();
		return pr;
	}

	//update
	public int update(PerceivedRouter perceivedRouter) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Names.PR_DATE, perceivedRouter.getDate());
		values.put(Names.PR_SIGNALLEVEL, perceivedRouter.getSignalLevel());
		if(perceivedRouter.getRoom() != null){
			RoomDAO dao = new RoomDAO(context);
			if (dao.find(perceivedRouter.getRoom().getCodRoom()) == null)
				dao.save(perceivedRouter.getRoom());
			values.put(Names.RM_ID, perceivedRouter.getRoom().getCodRoom());
		}
		if (perceivedRouter.getRouter() != null) {
			RouterDAO dao = new RouterDAO(context);
			if (dao.find(perceivedRouter.getRouter().getId()) == null)
				dao.save(perceivedRouter.getRouter());
			values.put(Names.RT_ID, perceivedRouter.getRouter().getId());
		}
		int i = database.update(Names.PROUTERS, values, 
				Names.PR_DATE + Names.EQ + perceivedRouter.getDate().toString(), null);
		databaseHelper.close();
		return i;
	}
}
