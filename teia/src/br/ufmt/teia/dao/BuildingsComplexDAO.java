package br.ufmt.teia.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufmt.teia.helper.DatabaseHelper;
import br.ufmt.teia.model.BuildingsComplex;

public class BuildingsComplexDAO {
	private Context context;

	public BuildingsComplexDAO(Context context) {
		this.setContext(context);
	}
	
	public void setContext(Context context) {
		this.context = context;
	}

	public void save(BuildingsComplex buildingsComplex) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Names.BC_ID, buildingsComplex.getId());
		values.put(Names.BC_NAME, buildingsComplex.getName());
		database.insert(Names.BCOMPLEXES, null, values);
		databaseHelper.close();
	}

	public void delete(BuildingsComplex buildingsComplex) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		database.delete(Names.BCOMPLEXES, Names.BC_ID + Names.EQ 
				+ buildingsComplex.getId().toString(), null);
		databaseHelper.close();
	}

	//list
	public List<BuildingsComplex> list() {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getReadableDatabase();
		Cursor c = database.rawQuery(Names.SELECT + Names.BCOMPLEXES, null);
		if (!c.moveToFirst())
			return null;
		List<BuildingsComplex> list = new ArrayList<BuildingsComplex>();
		do {
			BuildingsComplex complex = new BuildingsComplex();
			complex.setId(c.getInt(c.getColumnIndex(Names.BC_ID)));
			complex.setName(c.getString(c.getColumnIndex(Names.BC_NAME)));
			list.add(complex);
		} while (c.moveToNext());
		databaseHelper.close();
		return list;
	}
	
	//find
	public BuildingsComplex find(Integer id) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getReadableDatabase();
		Cursor c = database.rawQuery(Names.SELECT + Names.BCOMPLEXES
				+ Names.WHERE + Names.BC_ID + Names.EQ + id.toString(), null);
		if (!c.moveToFirst())
			return null;
		BuildingsComplex complex = new BuildingsComplex();
		complex.setId(c.getInt(c.getColumnIndex(Names.BC_ID)));
		complex.setName(c.getString(c.getColumnIndex(Names.BC_NAME)));
		databaseHelper.close();
		return complex;
	}

	//update
	public int update(BuildingsComplex buildingsComplex) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Names.BC_ID, buildingsComplex.getId());
		values.put(Names.BC_NAME, buildingsComplex.getName());
		int i = database.update(Names.BCOMPLEXES, values, 
				Names.BC_ID + Names.EQ + buildingsComplex.getId().toString(), null);
		databaseHelper.close();
		return i;
	}
}
