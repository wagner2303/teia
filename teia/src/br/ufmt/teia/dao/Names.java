package br.ufmt.teia.dao;

public class Names {

	// Table names
	public static final String BUILDINGS = "buildings";
	public static final String BCOMPLEXES = "buildingscomplexes";
	public static final String PROUTERS = "perceivedrouters";
	public static final String ROOMS = "rooms";
	public static final String ROUTERS = "routers";
	
	// Column Names
	public static final String B_ID = "idbuilding";
	public static final String B_NAME = "namebuilding";
	
	public static final String BC_ID = "idBuildingComplexes";
	public static final String BC_NAME = "nameBuildingComplexes";
	
	public static final String PR_DATE = "datePerceivedRouter";
	public static final String PR_SIGNALLEVEL = "signalLevel";
	
	public static final String RM_ID = "idRoom";
	public static final String RM_NAME = "nameRoom";
	
	public static final String RT_ID = "idRouter";
	public static final String RT_NAME = "nameRouter";
	public static final String RT_MAC = "macRouter";
	
	public static final String SELECT = "select * from ";
	public static final String WHERE = " where ";
	public static final String ORDER_BY = " order by ";
	public static final String EQ = " = ";
	public static final String LT = " < ";
	public static final String GT = " > ";
	public static final String LE = " <= ";
	public static final String GE = " >= ";
	public static final String AND = " and ";
	public static final String OR = " or ";
}
