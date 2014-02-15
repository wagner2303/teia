package br.ufmt.teia.dao;

import java.util.List;

import br.ufmt.teia.model.Router;
import android.content.Context;

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
		// TODO
	}
	
	public void delete(Router router) {
		// TODO
	}
	
	//list
	public List<Router> list() {
		// TODO
		return null;
	}
	
	//find
	public Router find(Integer id) {
		// TODO
		return null;
	}
	
	//update
	public int update(Router router) {
		// TODO
		return 0;
	}
}
