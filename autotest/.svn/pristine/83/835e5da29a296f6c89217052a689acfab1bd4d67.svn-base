package com.activenetwork.qa.awo.supportscripts.qasetup.db;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * This setup sql is used to setup season info and inactive closure according to the site recorded info in DB
 * The logic is as below:
 * 1.	Get a park
 * 2.	Get all available seasons on All level in our expected period
 * 3.	Order all seasons by start date, and iterate through start and end date to find the gaps where seasons are missing, fill the gaps by create new peak season on all level and assign all sites to season
   4.	If there are no any available season on all level, just create a new peak season with loop/area is all and site type is all for our expected period, and assign all sites to this season
   5.	Continue to next.
 * @author ssong
 * @deprecated --refer to SetupSiteInv
 *
 */

public class SetupSiteInventory {

	private static AwoDatabase db;
	private static AutomationLogger logger;
	private static List<String> seasonInfos = new ArrayList<String>();
	private static List<String> closureInfos = new ArrayList<String>();
	private static BufferedWriter rpt;
	private static String newLine,contract;
	private static String DATE_PATTERN = "yyyy-MM-dd";
	private static int PEAK_SEASON = 1;//as lots of exists fee schedule was created for peak season,so add all new season as peak
	private static int NSS_SITE = 3;
	private static int NSS_SITE_PARENT = 2;
	private static int SS_SITE = 1;

	public SetupSiteInventory(){
		db = AwoDatabase.getInstance();
	}

	public void scanSiteRecordTable(int daysBeforeToday,int daysAfterToday){
		String query = "select distinct(park_id),contract from site_used order by contract";
		String colNames[] = {"park_id","contract"};
		List<String[]> parks = db.executeQuery(query, colNames);

		iterateParks(parks, daysBeforeToday, daysAfterToday, true);
		iterateParks(parks, daysBeforeToday, daysAfterToday, false);
	}

	public void scanSiteRecordTableForSanity(int daysBeforeToday,int daysAfterToday){
		String query = "select distinct(park_id),contract from site_used where case_name like 'testCases.sanity.%' order by contract";
		String colNames[] = {"park_id","contract"};
		List<String[]> parks = db.executeQuery(query, colNames);

		iterateParks(parks, daysBeforeToday, daysAfterToday, true);
		iterateParks(parks, daysBeforeToday, daysAfterToday, false);
	}

	public void iterateParks(List<String[]> parks,int daysBeforeToday,int daysAfterToday,boolean addSeason){
		String park_id="";
		for(int i=0;i<parks.size();i++){
			park_id = parks.get(i)[0];
			contract = parks.get(i)[1];
			this.resetSchema(contract);
			try{
				if(addSeason){
					addSeason(park_id,daysBeforeToday,daysAfterToday);
					outputSeasonInfo(park_id,i);
				}else{
					inactiveBlockedClosure(park_id);
					outputClosureInfo(park_id,i);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public void resetSchema(String contract){
		String schema = DataBaseFunctions.getSchemaName(contract, TestProperty.getProperty("target_env"));
		db.resetSchema(schema);
	}

	public void addSeason(String park_id,int daysBeforeToday,int daysAfterToday){
		String start_date = DateFunctions.formatDate(DateFunctions.getDateAfterToday(daysBeforeToday),DATE_PATTERN);
		String end_date = DateFunctions.formatDate(DateFunctions.getDateAfterToday(daysAfterToday),DATE_PATTERN);

		//check no season cover period and fill seasons for those no season period
		this.fillSeason(park_id, start_date, end_date);
		this.updateSeasonType(park_id, start_date);
	}

	private void updateSeasonType(String park_id,String start_date){
		String sql = "update i_season_schdl set seasontype_id="+PEAK_SEASON+",active_ind=1  where facility_id="+park_id+" and end_date>"+wrapCharToDate(start_date)+" and deleted_ind=0";
		db.executeUpdate(sql);
		logger.info("Update all effective seaon type to 'Non-Peak' for park "+park_id);
	}

	public void fillSeason(String park_id,String start_date,String end_date){
		logger.info("Fill Season For Park "+park_id+" From '"+start_date+"' To '"+end_date+"'--"+contract);

		//check loop/area is all and site type is all season
		String query = "select "+wrapDateToChar("start_date")+" start_date,"+wrapDateToChar("end_date")+" end_date from i_season_schdl where facility_id="+park_id+" and end_date>"+wrapCharToDate(start_date)+" and loc_id="+park_id+" and deleted_ind=0 and prd_grp_id is null order by start_date";
		String[] colNames = {"start_date","end_date"};
		List<String[]> values = db.executeQuery(query, colNames);
		int size = values.size();
		String sDate ="",eDate="";
		if(size>0){
			//fill season gap between given start date and the earliest exist season start date
			sDate = values.get(0)[0];
			if(DateFunctions.compareDates(start_date, sDate)<0){
				addNewSeason(park_id, start_date, DateFunctions.getDateAfterGivenDay(sDate, -1));
			}
			for(int i=0;i<size;i++){
				sDate = values.get(i)[1];
				if(i+1<size){//not the last season record
					eDate = values.get(i+1)[0];
				}else{
					eDate = values.get(i)[1];
				}
				if(DateFunctions.daysBetween(sDate, eDate)>1){
					addNewSeason(park_id, DateFunctions.getDateAfterGivenDay(sDate, 1), DateFunctions.getDateAfterGivenDay(eDate, -1));
				}
			}

			//add season from max exist season date to the expected end date
			if(DateFunctions.compareDates(eDate, end_date)<0){
				addNewSeason(park_id, DateFunctions.getDateAfterGivenDay(eDate, 1), end_date);
			}
		}else{//no season exist from given start date
			addNewSeason(park_id, start_date, end_date);
		}
	}

	private void addNewSeason(String park_id,String startDate,String endDate){
		logger.info("Add New Season for Park "+park_id+" from '"+startDate+"' to '"+endDate+"'.");

		startDate = DateFunctions.formatDate(startDate,DATE_PATTERN);
		endDate = DateFunctions.formatDate(endDate,DATE_PATTERN);
		String query = "select max(id) as id from i_season_schdl";
		String max_id = db.executeQuery(query, "id", 0);
		int id = (max_id == null ? 1:Integer.parseInt(max_id)+1);
		String sql = "insert into i_season_schdl(id,seasontype_id,start_date,end_date,loc_id,facility_id,active_ind,deleted_ind,prd_cat_id) values("+id+","+PEAK_SEASON+","+wrapCharToDate(startDate)+","+wrapCharToDate(endDate)+","+park_id+","+park_id+",1,0,3)";//prd_cat_id=3 means site
		db.executeUpdate(sql);
		seasonInfos.add("Add New Season "+id+" From "+startDate+" To "+endDate+" For Park "+park_id+"----"+contract);
		//assign all sites to season
		assignAllSitesToSeason(park_id, id);
		//create avail inventory for each assigned site
		addAvalInventoryForAllSites(park_id, startDate, endDate, id);
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
		seasonInfos.add(msg);
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
		seasonInfos.add(msg);
	}

	private String wrapDateToChar(String date){
		return "to_char("+date+",'YYYY-MM-DD')";
	}

	private String wrapCharToDate(String date){
		return "to_date('"+date+"','YYYY-MM-DD')";
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
		closureInfos.add(closureMsg);
	}

	private static void outputHeader() throws IOException {
		logger.info("Writing header");

		rpt.write("<html>" + newLine);
		rpt.write("<head>" + newLine);
		rpt.write("<title>Site Inventory Report</title>" + newLine);
		rpt.write("</head>" + newLine);
		rpt.write("<body>" + newLine);
		rpt.newLine();
		rpt.write("<h1>Site Inventory Report</h1>" + newLine);
		rpt.newLine();

		rpt.write("<p>Created on " +DateFunctions.getToday("dd-MMM-yyyy")+ ".</p>" + newLine);

		rpt.newLine();
		rpt.flush();
	}

	private static void outputContents() throws IOException {
		logger.info("Writing contents");

		rpt.newLine();
		rpt.write("<h2>Contents</h2>" + newLine);
		rpt.newLine();
		rpt.write("<ul>" + newLine);
		rpt.write("<li><a href=\"#season\">Season Info</a></li>" + newLine);
		rpt.write("<li><a href=\"#closure\">Closure Info</a></li>" + newLine);
		rpt.write("</ul>" + newLine);
		rpt.newLine();
		rpt.flush();
	}

	private void outputSeasonInfo(String park_id,int rowNum) throws IOException{
		logger.info("Write Season Info For Park "+park_id);

		if(rowNum == 0){//is first row,we need to write title
			rpt.newLine();
			rpt.write("<a name=\"season\"><h2>Season Info</h2></a>" + newLine);
			rpt.newLine();
		}
		for(String str:seasonInfos){
			rpt.write("<blockquote>" + newLine);
			rpt.write(str+newLine);
			rpt.write("</blockquote>" + newLine);
		}
		rpt.flush();
		seasonInfos.clear();
	}

	private void outputClosureInfo(String park_id,int rowNum) throws IOException{
		logger.info("Write Closure Info For Park "+park_id);

		if(rowNum == 0){//is first row,we need to write title
			rpt.newLine();
			rpt.write("<a name=\"closure\"><h2>Closure Info</h2></a>" + newLine);
			rpt.newLine();
		}
		for(String str:closureInfos){
			rpt.write("<blockquote>" + newLine);
			rpt.write(str+newLine);
			rpt.write("</blockquote>" + newLine);
		}
		rpt.flush();
		closureInfos.clear();
	}

	private static void outputFooter() throws IOException {
		logger.info("Writing footer");

		rpt.newLine();
		rpt.write("</body>" + newLine);
		rpt.write("</html>" + newLine);
		rpt.flush();
	}

	public void cleanUp(String seasonId){

	}

	public static void main(String[] args) throws IOException{
		rpt = new BufferedWriter(new FileWriter("SiteInventoryReport_"+DateFunctions.getCurrentTime()+".html"));
		newLine = System.getProperty("line.separator");
		AwoUtil.initTestProperty();
		AutomationLogger.init(AwoUtil.getProjectPath(), AwoUtil.LOG4J_PROPERTY);
		logger = AutomationLogger.getInstance();
		outputHeader();
		outputContents();
		SetupSiteInventory setInv = new SetupSiteInventory();
		setInv.scanSiteRecordTable(-1, 365);
//		setInv.scanSiteRecordTableForSanity(-7, 200);
		
		//scan individual park
//		List<String[]> parks=new ArrayList<String[]>();
//		parks.add(new String[] {"110028","EBAY"});
//		setInv.iterateParks(parks, -1, 200, true);
		outputFooter();
	}
}
