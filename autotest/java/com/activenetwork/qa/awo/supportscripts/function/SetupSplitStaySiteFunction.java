package com.activenetwork.qa.awo.supportscripts.function;

import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoDatabase;

public class SetupSplitStaySiteFunction extends FunctionCase {
	private static AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
	private String schema = "";
	private String locId = "";
	private String prdId = "";
	private String splitGroupNum = "";

	@Override
	public void execute() {
		db.resetSchema(schema);
		boolean needSetup = checkSplitStaySetup(locId, prdId, splitGroupNum);
		if(needSetup){
			setupSplitStay(locId, prdId, splitGroupNum, schema);
		}
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = param[0].toString();
		locId = param[1].toString();		
		prdId = param[2].toString();	
		splitGroupNum = param[3].toString();
	}
	
	/**
	 * Check if need to do data setup to Split Stay
	 * @param locId
	 * @param prdIds
	 * @return
	 */
	public boolean checkSplitStaySetup(String locId, String prdId, String splitGroupNum){
		boolean needSetup = false;
		String sql = "select count(*) as num from P_SPLIT_STAY_SCHD where LOC_ID = '"+locId+"' and PRD_ID = '"+prdId+"' and split_group_number = '"+splitGroupNum+"'"  ;
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
	public void setupSplitStay(String locId, String prdId, String splitGroupNum, String schema){
		db.resetSchema(schema);
		//Get ID
		String query = "select get_sequence('P_SPLIT_STAY_SCHD') as ID from dual";
		String id = db.executeQuery(query, "ID", 0);
		//Insert data
		String sql = "insert into P_SPLIT_STAY_SCHD (ID,LOC_ID,PRD_ID,ACTIVE_IND,SPLIT_GROUP_NUMBER) values("+id+","+locId+","+prdId+",'1',"+splitGroupNum+")";
		db.executeUpdate(sql);
		System.out.println("Successfully insert the split stay data which locId = "+locId+" and prdId = "+prdId+" and splitGroupNum="+splitGroupNum+".");
		newAddValue = id;
	}

}
