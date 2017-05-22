package com.activenetwork.qa.awo.sql;

import com.activenetwork.qa.awo.util.AwoDatabase;

public class SplitStaySiteSql {	
private static AwoDatabase db;

public SplitStaySiteSql(){
	db = AwoDatabase.getInstance();
}

/**
 * Check if need to do data setup to Split Stay
 * @param locId
 * @param prdIds
 * @return
 */
public boolean checkSplitStaySetup(String locId, String prdId){
	boolean needSetup = false;
	String sql = "select count(*) as num from P_SPLIT_STAY_SCHD where LOC_ID = '"+locId+"' and PRD_ID = '"+prdId+"'";
	String num = db.executeQuery(sql, "num", 0);
	if(Integer.valueOf(num)<=0)
	{
		needSetup = true;
		System.out.println("The split stay site which locId = "+locId+" and prdId = "+prdId+" should be insert into DB!");
	}else {
		System.out.println("The split stay site which locId = "+locId+" and prdId = "+prdId+" has been inserted into DB!");
	}
	return needSetup;
}

/**
 * Insert 	
 * @param locId
 * @param prdId
 * @param schema
 */
public void setupSplitStay(String locId, String prdId, String schema){
	db.resetSchema(schema);
	//Get ID
	String query = "select get_sequence('P_SPLIT_STAY_SCHD') as ID from dual";
	String id = db.executeQuery(query, "ID", 0);
	//Insert data
	String sql = "insert into P_SPLIT_STAY_SCHD (ID,LOC_ID,PRD_ID,ACTIVE_IND,SPLIT_GROUP_NUMBER) values("+id+","+locId+","+prdId+",'1',1)";
	db.executeUpdate(sql);
	System.out.println("Successfully insert the split stay data which locId = "+locId+" and prdId = "+prdId+" .");
}
}
