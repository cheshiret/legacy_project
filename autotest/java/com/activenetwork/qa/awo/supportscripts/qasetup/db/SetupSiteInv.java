/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.db;

import java.util.List;

import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:this script include 2 parts:add season and inactive closure for given park 
 * @Task#:Auto-2111
 * 
 * @author ssong
 * @Date  Mar 31, 2014
 */
public class SetupSiteInv extends SetupCase{
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private String schema_pref,contract,park_id,schema;
	private static String DATE_PATTERN = "yyyy-MM-dd";
	private static int PEAK_SEASON = 1;//as lots of exists fee schedule was created for peak season,so add all new season as peak
	private static int NSS_SITE = 3;
	private static int NSS_SITE_PARENT = 2;
	private static int SS_SITE = 1;
	
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.awo.supportscripts.SetupCase#executeSetup()
	 */
	@Override
	public void executeSetup() {
		db.resetSchema(schema);
		
		newAddValue = this.addSeason(park_id, -1, 365);
		this.inactiveBlockedClosure(park_id);
	}
	
	public String addSeason(String park_id,int daysBeforeToday,int daysAfterToday){
		String start_date = DateFunctions.formatDate(DateFunctions.getDateAfterToday(daysBeforeToday),DATE_PATTERN);
		String end_date = DateFunctions.formatDate(DateFunctions.getDateAfterToday(daysAfterToday),DATE_PATTERN);

		//check no season cover period and fill seasons for those no season period
		String ids = this.fillSeason(park_id, start_date, end_date);
		this.updateSeasonType(park_id, start_date);
		return ids;
	}

	private void updateSeasonType(String park_id,String start_date){
		String sql = "update i_season_schdl set seasontype_id="+PEAK_SEASON+",active_ind=1  where facility_id="+park_id+" and end_date>"+wrapCharToDate(start_date)+" and deleted_ind=0";
		db.executeUpdate(sql);
		logger.info("Update all effective seaon type to 'Non-Peak' for park "+park_id);
	}

	public String fillSeason(String park_id,String start_date,String end_date){
		logger.info("Fill Season For Park "+park_id+" From '"+start_date+"' To '"+end_date+"'--"+contract);

		//check loop/area is all and site type is all season
		String query = "select "+wrapDateToChar("start_date")+" start_date,"+wrapDateToChar("end_date")+" end_date from i_season_schdl where facility_id="+park_id+" and end_date>"+wrapCharToDate(start_date)+" and loc_id="+park_id+" and deleted_ind=0 and prd_grp_id is null order by start_date";
		String[] colNames = {"start_date","end_date"};
		List<String[]> values = db.executeQuery(query, colNames);
		int size = values.size();
		String sDate ="",eDate="";
		StringBuilder seasonIds = new StringBuilder();

		if(size>0){
			//fill season gap between given start date and the earliest exist season start date
			sDate = values.get(0)[0];
			if(DateFunctions.compareDates(start_date, sDate)<0){
				seasonIds.append(addNewSeason(park_id, start_date, DateFunctions.getDateAfterGivenDay(sDate, -1))).append(",");
			}
			for(int i=0;i<size;i++){
				sDate = values.get(i)[1];
				if(i+1<size){//not the last season record
					eDate = values.get(i+1)[0];
				}else{
					eDate = values.get(i)[1];
				}
				if(DateFunctions.daysBetween(sDate, eDate)>1){
					seasonIds.append(addNewSeason(park_id, DateFunctions.getDateAfterGivenDay(sDate, 1), DateFunctions.getDateAfterGivenDay(eDate, -1))).append(",");
				}
			}

			//add season from max exist season date to the expected end date
			if(DateFunctions.compareDates(eDate, end_date)<0){
				seasonIds.append(addNewSeason(park_id, DateFunctions.getDateAfterGivenDay(eDate, 1), end_date)).append(",");
			}
		}else{//no season exist from given start date
			seasonIds.append(addNewSeason(park_id, start_date, end_date)).append(",");
		}
		return seasonIds.toString();
	}

	private String addNewSeason(String park_id,String startDate,String endDate){
		logger.info("Add New Season for Park "+park_id+" from '"+startDate+"' to '"+endDate+"'.");

		startDate = DateFunctions.formatDate(startDate,DATE_PATTERN);
		endDate = DateFunctions.formatDate(endDate,DATE_PATTERN);
		String query = "select max(id) as id from i_season_schdl";
		String max_id = db.executeQuery(query, "id", 0);
		int id = (max_id == null ? 1:Integer.parseInt(max_id)+1);
		String sql = "insert into i_season_schdl(id,seasontype_id,start_date,end_date,loc_id,facility_id,active_ind,deleted_ind,prd_cat_id) values("+id+","+PEAK_SEASON+","+wrapCharToDate(startDate)+","+wrapCharToDate(endDate)+","+park_id+","+park_id+",1,0,3)";//prd_cat_id=3 means site
		db.executeUpdate(sql);
		//assign all sites to season
		assignAllSitesToSeason(park_id, id);
		//create avail inventory for each assigned site
		addAvalInventoryForAllSites(park_id, startDate, endDate, id);
		return String.valueOf(id);
	}

	private void assignAllSitesToSeason(String park_id,int seasonId){
		String msg = "Assign all sites under park "+park_id+" to season "+seasonId;
		logger.info(msg);

		//prd_rel_type=1 means site specific site,first assign all site specific site to season
		String sql = "insert into I_SEASON_SCHDL_PRD (SEASON_SCHDL_ID, PRD_ID)" +
				" (select "+seasonId+", prd_id from p_prd" +
				" where park_id = "+park_id+" and prd_rel_type="+SS_SITE+" and active_ind=1 and deleted_ind=0 and prd_id not in (select ssp.prd_id from i_season_schdl_prd ssp where ssp.season_schdl_id = "+seasonId+"))";
		db.executeUpdate(sql);
		//prd_rel_type=2 means NSS site parent,for NSS site just assign the parent to season,no need to assign all NSS sites to a season
		sql = "insert into I_SEASON_SCHDL_PRD (SEASON_SCHDL_ID, PRD_ID)" +
		" (select "+seasonId+", prd_id from p_prd" +
		" where park_id = "+park_id+" and prd_rel_type="+NSS_SITE_PARENT+" and active_ind=1 and deleted_ind=0 and prd_id not in (select ssp.prd_id from i_season_schdl_prd ssp where ssp.season_schdl_id = "+seasonId+"))";
		db.executeUpdate(sql);
	}

	private void addAvalInventoryForAllSites(String park_id,String startDate,String endDate,int seasonId){
		String msg = "Add Available Inv For All Sites In Park "+park_id;
		logger.info(msg);

		String sql = "insert into I_INV_AVAIL(INV_AVAIL_ID, PRD_ID, START_TIME, END_TIME, TOTAL_QTY, NOTE, SEASON_SCH_ID, ACTIVE_IND, DELETED_IND, STATUS, RESERVED_QTY, PARENT_PRD_ID, BOOKING_ID)" +
				" (select (select decode(max(inv_avail_id),null,0,max(inv_avail_id)) from i_inv_avail) + rownum, p.prd_id, "+wrapCharToDate(startDate)+","+wrapCharToDate(endDate)+"," +
				" 1, p.prd_name, NULL, 1, 0, NULL, 0, 0, NULL from p_prd p" +
				" where p.park_id = "+park_id+" and p.prd_id in (select ssp.prd_id from i_season_schdl_prd ssp where ssp.season_schdl_id = "+seasonId+"))";
		db.executeUpdate(sql);
		sql = "insert into I_INV_AVAIL(INV_AVAIL_ID, PRD_ID, START_TIME, END_TIME, TOTAL_QTY, NOTE, SEASON_SCH_ID, ACTIVE_IND, DELETED_IND, STATUS, RESERVED_QTY, PARENT_PRD_ID, BOOKING_ID)" +
		" (select (select decode(max(inv_avail_id),null,0,max(inv_avail_id)) from i_inv_avail) + rownum, p.prd_id, "+wrapCharToDate(startDate)+","+wrapCharToDate(endDate)+"," +
		" 1, p.prd_name, NULL, 1, 0, NULL, 0, 0, NULL from p_prd p" +
		" where p.park_id = "+park_id+" and p.prd_rel_type="+NSS_SITE+" and p.parent_id in (select ssp.prd_id from i_season_schdl_prd ssp where ssp.season_schdl_id = "+seasonId+"))";//prd_rel_type=3 means NSS site,for NSS site only their parent was in the i_season_schdl_prd table
		db.executeUpdate(sql);
	}

	private void inactiveBlockedClosure(String park_id){
		logger.info("Start to Deactivate closure for Park "+park_id);

		String sql = "delete from i_inv_used where clo_schd_id in (select id from i_clo_schdl where loc_id="+park_id+" and active_ind=1 and end_date>sysdate)";
		db.executeUpdate(sql);
		sql = "update i_clo_schdl set active_ind=0 where id in(select id from i_clo_schdl where loc_id="+park_id+" and active_ind=1 and end_date>sysdate)";
		int num = db.executeUpdate(sql);
		String closureMsg;
		if(num>0){
			closureMsg = "Total "+num+" Closures are deactivated for Park "+park_id+"----"+contract;
		}else{
			closureMsg = "No Closure Blocked for park "+park_id+"----"+contract;
		}
		logger.info(closureMsg);
	}
	
	private String wrapDateToChar(String date){
		return "to_char("+date+",'YYYY-MM-DD')";
	}

	private String wrapCharToDate(String date){
		return "to_date('"+date+"','YYYY-MM-DD')";
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.awo.supportscripts.SetupCase#readDataFromDatabase()
	 */
	@Override
	public void readDataFromDatabase() {
		contract = datasFromDB.get("contract");
		schema = schema_pref+contract;
		park_id = datasFromDB.get("park_id");
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_setup_site_inv";
	  	env = TestProperty.getProperty("target_env");
	  	schema_pref = TestProperty.getProperty(env+".db.schema.prefix");
	}

}
