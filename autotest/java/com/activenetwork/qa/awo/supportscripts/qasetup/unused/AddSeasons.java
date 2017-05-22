package com.activenetwork.qa.awo.supportscripts.qasetup.unused;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddSeasons extends SupportCase
{
	/**
	 * Script Name   : <b>AddSeasons</b>
	 * Generated     : <b>Nov 26, 2009 3:46:05 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/11/26
	 * @author QA
	 */
	
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private InventoryManager im=InventoryManager.getInstance();
	
	private String schema_pref="";
	private LoginInfo login=new LoginInfo();
	private SeasonData season=new SeasonData();
	private String parkName="";
	private String contract="";
	private String env="";
	boolean loggedin=false;
	private String startDate="";
	private String newSeasonId = "";
	
	/**
	 * Get all season ids which are going to be deleted. We only delete seasons whose start date is after today
	 * @param parkName
	 * @param schema
	 * @return
	 */
	public void wrapParameters(Object[] args) 
	{
	  	startpoint = 42; //the start point in the datapool
		endpoint = 42; // the end point in the datapool
		
	  	dataSource = casePath + "/FacilityList";
	  	
	  	env=TestProperty.getProperty("target_env");
	  	schema_pref = TestProperty.getProperty(env+".db.schema.prefix");
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "";
		login.contractAbbrev = "";
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
		
		season.m_StartDate=startDate;
		season.m_EndDate=DateFunctions.getDateAfterGivenDay(DateFunctions.getToday(),365);
		
		logMsg = new String[5];
		logMsg[0] = "park";
		logMsg[1] = "contract";
		logMsg[2] = "season";
		logMsg[3] = "seasonID";
		logMsg[4] = "result";
	}
		
	public void execute() {
	  	String[] seasonIDs=getSeasonIDsToBeDeleted(parkName,schema_pref+contract);

	  	if(!login.contractAbbrev.equalsIgnoreCase(contract) || !loggedin) {
	  		if(contract.equalsIgnoreCase("LARC"))
	  			login.contract="Larimer County Contract";
	  		else
	  			login.contract=contract.toUpperCase()+" Contract";
	  	  	if(loggedin) {
	  	  	  im.logoutInvManager();
	  	  	}
	  	  	im.loginInventoryManager(login);
	  	  	loggedin=true;
	  	}
	  	
	  	im.gotoFacilityDetailsPg(parkName.trim());//handle some park name has a blank in the end in DB
		im.gotoSeasonsPg();
		im.operateSeasons(seasonIDs,"Delete");
		season.m_StartDate=startDate;
		newSeasonId=im.addNewSeason(season);
		im.gotoInventoryHomePg();
		
		if(dpIter.dpDone()){
		  	im.logoutInvManager();
		}
		
		//set log information
		logMsg[3] = newSeasonId;
		logMsg[4] = "Successful";
		
  	  	login.contractAbbrev=contract;
	}
	
	public void getNextData() {
	  	parkName=dpIter.dpString("park_name");
	  	contract=dpIter.dpString("contract_abbr");
	  	season.m_SeasonType=dpIter.dpString("season");
	  	
		if (dpIter.dpString("startDate") != null
				&& dpIter.dpString("startDate").length() > 0) {
				season.m_StartDate= dpIter.dpString("startDate");
		}
		
		if (dpIter.dpString("endDate") != null
				&& dpIter.dpString("endDate").length() > 0) {
				season.m_EndDate= dpIter.dpString("endDate");
		}
		
		if(dpIter.dpString("siteIDs") != null && dpIter.dpString("siteIDs").length()>0){
			season.assignAll = false;
			season.siteIds = dpIter.dpString("siteIDs").split(",");
		}
	  	
		logMsg[0] = parkName;
		logMsg[1] = contract;
		logMsg[2] = season.m_SeasonType;
		logMsg[3] = "unknown";
		logMsg[4] = "Failed due to error";
	}
	
	private String[] getSeasonIDsToBeDeleted(String parkName, String schema) {
		db.resetSchema(schema);
		db.connect();
		
		//result like : parkID = 91913
		logger.info("Getting the seasons to be deactivated/deleted.");
		String query="select id from d_loc where UPPER(name) = UPPER('"+parkName+"')";
		String parkid=db.executeQuery(query,"id",0);
		logger.info("Got park id="+parkid+" for park: \""+parkName+"\".");//91913
		
		//result like : seasonId = 116209953
		query="select id from i_season_schdl where loc_id="+parkid+" and end_date > to_date(CURRENT_DATE) and deleted_ind=0";
		List<?> ids=(List<?>)db.executeQuery(query,"id");
	  
		//result like : end-date = 10-31-2010			
		query="select to_char(max(end_date),'MM-DD-YYYY') as end_date from i_season_schdl where loc_id="+parkid+" and start_date < to_date(to_char(CURRENT_DATE, 'YYYY-MM-DD'), 'YYYY-MM-DD') and end_date < to_date(to_char(CURRENT_DATE, 'YYYY-MM-DD'), 'YYYY-MM-DD') and deleted_ind=0 and active_ind=1";
		String end_date=db.executeQuery(query,"end_date",0);
		
		if(end_date==null||end_date.trim().length()<1)
		  startDate=DateFunctions.getToday();
		else
		  //result like : startDate = 11-1-2010   
		  startDate=DateFunctions.getDateAfterGivenDay(end_date,1);
		
		logger.info("The new season start date should be "+startDate);
		
		db.disconnect();
		
		String[] seasonIDs=null;
		String idString="";
		StringBuffer idStringBuff = new StringBuffer();
		if(ids.size()>0) {
		  seasonIDs=new String[ids.size()];
		  for (int i=0;i<ids.size();i++) {
			seasonIDs[i]=ids.get(i).toString();
			idStringBuff.append(ids.get(i).toString()+" ");
		  }
		  idString = idStringBuff.toString();
		}
		
		idString=idString.trim().replaceAll(" ",",");
		logger.info("We need to deactivate/delete the following seasons: "+idString);
		
		return seasonIDs;		
	}
}

