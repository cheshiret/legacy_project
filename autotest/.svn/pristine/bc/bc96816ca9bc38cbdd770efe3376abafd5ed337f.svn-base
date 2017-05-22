package com.activenetwork.qa.awo.supportscripts.qasetup.unused;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This script is used to make sure site availability include (make site exists,add season and inactive closure)
 * @Preconditions: record all used site info into DB at first
 * @SPEC: Enhance framework-site management feature
 * @Task#:Auto-645
 * 
 * @author ssong
 * @Date  Aug 8, 2011
 */
@Deprecated
public class SiteAvailabilitySetup {
	private static AwoDatabase db;
	private static AutomationLogger logger = AutomationLogger.getInstance();
	private static BufferedWriter rpt;
	private static String newLine,siteMsg,seasonMsg,closureMsg;
	private String schema,park_id,contract,casename;
	private static List<String> closureInfos = new ArrayList<String>();
	private static List<String> siteInfos = new ArrayList<String>();
	private static List<String> seasonInfos = new ArrayList<String>();
	private static List<String> ruleInfos = new ArrayList<String>();
	
	SiteAvailabilitySetup(){
		db = AwoDatabase.getInstance();
	}
	
	private void resetSchema(String contract){
		schema = DataBaseFunctions.getSchemaName(contract, TestProperty.getProperty("target_env"));
		db.resetSchema(schema);
	}
	
	public void checkAndReplaceSite(String siteId,String siteNum,String loopId,String park_id){
		String query = "select active_ind from p_prd where prd_id="+siteId+" and prd_cd='"+siteNum+"'";
		List<String> inds = db.executeQuery(query, "active_ind");
		if(inds==null||inds.size()<1){
			query = "select prd_cd,prd_name,loc_id from p_prd where prd_id="+siteId;
			String colNames[] = {"prd_cd","prd_name","loc_id"};
			List<String[]> values = db.executeQuery(query, colNames);
			if(values.size()<1){
				logger.info("Site Not Exist,Need to Replace with a New One.");
				replaceSite(siteId, loopId, park_id);
			}else{
				//update site number to latest site code for given site id in recorded table
				db.resetDefaultDB();
				query = "update site_used set site_cd='"+values.get(0)[0]+"',site_name='"+values.get(0)[1]+"',parent_id='"+values.get(0)[2]+"' where site_id="+siteId;
				db.executeUpdate(query);
				siteMsg = "Update Site Number to "+values.get(0)[0]+" for given Site "+siteId+" For "+casename;
				logger.info(siteMsg);
			}
		}else{
			if(!inds.get(0).equals("1")){
				query = "update p_prd set active_ind=1 where prd_id="+siteId;
				db.executeUpdate(query);
				siteMsg = "Activate Site "+siteId+" From DB----"+contract;
				logger.info(siteMsg);
			}else{
				siteMsg = "Site "+siteId+" Is Available,No need to Update----"+contract;
				logger.info(siteMsg);
			}
		}
		siteInfos.add(siteMsg);
	}
	
	private void replaceSite(String old_siteId,String loopId,String parkId){
		String query = "select prd_id,prd_cd,prd_name,loc_id from p_prd where loc_id="+loopId+" and active_ind=1 order by prd_id";
		String[] colNames = {"prd_id","prd_cd","prd_name","loc_id"};
		//search an available and not used site from the same loop
		boolean hasChanged = this.searchAndUpdateSite(query, colNames, parkId, old_siteId);
		
		if(!hasChanged){
			//search an available and not used site from the same park
			query = "select prd_id,prd_cd,prd_name,loc_id from p_prd where park_id="+parkId+" and loc_id!="+loopId+" and active_ind=1 order by prd_id";
			hasChanged = searchAndUpdateSite(query, colNames, parkId, old_siteId);
		}
		if(!hasChanged){
			siteMsg = "No available And Not Used Site Under Park "+parkId+" For "+casename;
			logger.warn(siteMsg);
			siteMsg = wrapMsg(siteMsg);
		}
	}
	
	private static String wrapMsg(String msg){
		String wrapedMsg = "<B style=\"color:#f00\">"+msg+"</B>";
		return wrapedMsg;
	}
	
	private boolean searchAndUpdateSite(String query,String[] colNames,String parkId,String old_siteId){
		List<String[]> values = db.executeQuery(query, colNames);
		db.resetDefaultDB();
		String site_id,site_cd,site_name,parent_id;
		boolean hasChanged = false;
		for(int i=0;i<values.size();i++){
			site_id = values.get(i)[0];
			site_cd = values.get(i)[1];
			site_name = values.get(i)[2];
			parent_id = values.get(i)[3];
			query = "select count(*) as num from site_used where park_id="+parkId+" and site_id="+site_id+" and contract='"+contract+"'";
			int num = Integer.parseInt(db.executeQuery(query, "num", 0));
			if(num==0){
				query = "update site_used set site_id="+site_id+",site_cd='"+site_cd+"',site_name='"+site_name+"',parent_id="+parent_id+" where site_id="+old_siteId+" and park_id="+parkId+" and contract='"+contract+"'";
				db.executeUpdate(query);
				siteMsg = "Change Site From "+old_siteId+" to "+site_id+" under park "+parkId+" For "+casename;
				logger.info(siteMsg);
				siteMsg = wrapMsg(siteMsg);
				hasChanged = true;
				break;
			}
		}
		return hasChanged;
	}
	
	public void scanSiteInfo(int daysBeforeToday,int daysAfterToday){
		String query = "select id,site_id,site_cd,parent_id,park_id,contract,case_name from site_used order by contract";
		String colNames[] = {"id","site_id","site_cd","parent_id","park_id","contract","case_name"};
		List<String[]> sites = db.executeQuery(query, colNames);
		String site_id,site_cd,parent_id,park_id;
		for(int i=0;i<sites.size();i++){
			site_id = sites.get(i)[1];
			site_cd = sites.get(i)[2];
			parent_id = sites.get(i)[3];
			park_id = sites.get(i)[4];
			contract = sites.get(i)[5];
			casename = sites.get(i)[6];
			this.resetSchema(contract);
			checkAndReplaceSite(site_id,site_cd,parent_id,park_id);
			this.resetSchema(contract);
			checkAndAddSeason(park_id,site_id,daysBeforeToday,daysAfterToday);
		}
	}
	
	public static List<String[]> showDuplicateUsedSite(){
		db.resetDefaultDB();
		String query = "select site_id,site_cd,from_date,to_date,case_name from site_used where (site_id,contract) in(select site_Id,contract from site_used group by site_id,contract having count(*)>1)";
		String colNames[] = {"site_id","site_cd","from_date","to_date","case_name"};
		List<String[]> sitesInfo = db.executeQuery(query, colNames);
		
		return sitesInfo;
	}
	
	private void addNewSeason(String park_id,String startDate,String endDate){
		logger.info("Add New Season for Park "+park_id);
		
		startDate = DateFunctions.formatDate(startDate,"dd-MMM-yy");
		endDate = DateFunctions.formatDate(endDate,"dd-MMM-yy");
		String query = "select max(id) as id from i_season_schdl";
		int id = Integer.parseInt(db.executeQuery(query, "id", 0))+1;
		String sql = "insert into i_season_schdl(id,seasontype_id,start_date,end_date,loc_id,facility_id,active_ind,deleted_ind) values("+id+",1,'"+startDate+"','"+endDate+"',"+park_id+","+park_id+",1,0)";
		db.executeUpdate(sql);
		//assign all sites to season 
		assignAllSitesToSeason(park_id, id);
		//create avail inventory for each assigned site
		addAvalInventoryForAllSites(park_id, startDate, endDate, id);
		seasonMsg = "Add New Season From "+startDate+" To "+endDate+" For Park "+park_id+"----"+contract;
		seasonInfos.add(seasonMsg);
	}
	
	private void assignAllSitesToSeason(String park_id,int seasonId){
		logger.info("Assign all sites under park "+park_id+" to season "+seasonId);
		
		String sql = "insert into I_SEASON_SCHDL_PRD (SEASON_SCHDL_ID, PRD_ID)" +
				" (select "+seasonId+", prd_id from p_prd" +
				" where park_id = "+park_id+" and prd_id not in (select ssp.prd_id from i_season_schdl_prd ssp where ssp.season_schdl_id = "+seasonId+"))";
		db.executeUpdate(sql);
	}
	
	private void addAvalInventoryForAllSites(String park_id,String startDate,String endDate,int seasonId){
		logger.info("Add Available Inv For All Sites In Park "+park_id);
		
		String sql = "insert into I_INV_AVAIL(INV_AVAIL_ID, PRD_ID, START_TIME, END_TIME, TOTAL_QTY, NOTE, SEASON_SCH_ID, ACTIVE_IND, DELETED_IND, STATUS, RESERVED_QTY, PARENT_PRD_ID, BOOKING_ID)" +
				" (select (select max(inv_avail_id) from i_inv_avail) + rownum, p.prd_id, '"+startDate+"','"+endDate+"'," +
				" 1, p.prd_name, NULL, 1, 0, NULL, 0, 0, NULL from p_prd p" +
				" where p.park_id = "+park_id+" and p.prd_id in (select ssp.prd_id from i_season_schdl_prd ssp where ssp.season_schdl_id = "+seasonId+"))";
		db.executeUpdate(sql);
	}
	
	private void addAvalInventoryForSite(String site_id,String start_date,String end_date){
		logger.info("Add Available Inv For Site "+site_id+" From "+start_date+" To "+end_date);
		
		start_date = DateFunctions.formatDate(start_date,"dd-MMM-yy");
		end_date = DateFunctions.formatDate(end_date,"dd-MMM-yy");
		String sql = "insert into I_INV_AVAIL(INV_AVAIL_ID, PRD_ID, START_TIME, END_TIME, TOTAL_QTY, NOTE, SEASON_SCH_ID, ACTIVE_IND, DELETED_IND, STATUS, RESERVED_QTY, PARENT_PRD_ID, BOOKING_ID)" +
		" (select (select max(inv_avail_id) from i_inv_avail) + rownum, "+site_id+", '"+start_date+"', '"+end_date+"'," +
		" 1, p.prd_name, NULL, 1, 0, NULL, 0, 0, NULL from p_prd p" +
		" where p.prd_id = "+site_id+")";
		db.executeUpdate(sql);
	}
	
	private void fillSeason(String park_id,String start_date,String end_date){
		logger.info("Fill Season For Park "+park_id+" From "+start_date+" To "+end_date);
		
		String query = "select start_date,end_date from i_season_schdl where facility_id="+park_id+" and end_date>'"+start_date+"' and active_ind=1 order by start_date";
		String[] colNames = {"start_date","end_date"};
		List<String[]> values = db.executeQuery(query, colNames);
		int size = values.size();
		String sDate,eDate="";
		if(size>0){
			//fill seasons
			for(int i=0;i<size;i++){
				sDate = values.get(i)[1].split(" ")[0];
				if(i+1<size){
					eDate = values.get(i+1)[0].split(" ")[0];
				}else{
					eDate = values.get(i)[1].split(" ")[0];
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
	
	private void fillSiteAvailability(String site_id,String start_date,String end_date){
		logger.info("Fill Site Availibility From "+start_date+" To "+end_date+" For Site "+site_id);
		
		String query = "select start_time,end_time from i_inv_avail where end_time>'"+start_date+"' and active_ind=1 and prd_id ="+site_id+" order by start_time";
		String[] colNames = {"start_time","end_time"};
		List<String[]>values = db.executeQuery(query, colNames);
		int size = values.size();
		if(size>0){
			String sDate,eDate = "";
			//fill site availability
			for(int i=0;i<size;i++){
				sDate = values.get(i)[1].split(" ")[0];
				if(i+1<size){
					eDate = values.get(i+1)[0].split(" ")[0];
				}else{
					eDate = values.get(i)[1].split(" ")[0];
				}
				if(DateFunctions.daysBetween(sDate, eDate)>1){
					addAvalInventoryForSite(site_id, DateFunctions.getDateAfterGivenDay(sDate, 1), DateFunctions.getDateAfterGivenDay(eDate, -1));
				}
			}
			//add site availability from max exist date to the expected end date
			if(DateFunctions.compareDates(eDate, end_date)<0){
				addAvalInventoryForSite(site_id, DateFunctions.getDateAfterGivenDay(eDate, 1), end_date);
			}
		}
	}
	
	public void checkAndAddSeason(String park_id,String site_id,int daysBeforeToday,int daysAfterToday){
		String start_date = DateFunctions.formatDate(DateFunctions.getDateAfterToday(daysBeforeToday),"dd-MMM-yy");
		String end_date = DateFunctions.formatDate(DateFunctions.getDateAfterToday(daysAfterToday),"dd-MMM-yy");
		
		//check no season cover period and fill seasons for those no season period
		this.fillSeason(park_id, start_date, end_date);
		//for exist seasons maybe not assigned for all sites,assign all sites to all seasons,if have assigned, not assign again
		String query = "select id from i_season_schdl where facility_id="+park_id+" and end_date>'"+start_date+"'";
		List<String> ids = db.executeQuery(query, "id");
		for(int i=0;i<ids.size();i++){
			assignAllSitesToSeason(park_id, Integer.parseInt(ids.get(i)));
		}
		//check site availability for given time period,if not available,add site availability
		this.fillSiteAvailability(site_id, start_date, end_date);
	}
	
	private void inactiveBlockedClosure(String parkId){
		if(!park_id.equals(parkId)){
			park_id = parkId;
			String sql = "delete from i_inv_used where clo_schd_id in (select id from i_clo_schdl where loc_id="+park_id+" and active_ind=1 and end_date>to_date(current_date))";
			db.executeUpdate(sql);
			sql = "update i_clo_schdl set active_ind=0 where id in(select id from i_clo_schdl where loc_id="+park_id+" and active_ind=1 and end_date>to_date(current_date))";
			int num = db.executeUpdate(sql);
			if(num>0){
				closureMsg = "Total "+num+" Closures are Inactivated for Park "+park_id+"----"+contract;
			}else{
				closureMsg = "No Closure Blocked for park "+park_id+"----"+contract;
			}
			logger.info(closureMsg);
			closureInfos.add(closureMsg);
		}
	}
	
	public void inactiveClosure(){
		logger.info("Start To Inactive Blocked Closure.");
		
		db.resetDefaultDB();
		String sql = "select distinct(park_id),contract from site_used";
		String[] colNames = {"park_id","contract"};
		List<String[]> infos = db.executeQuery(sql, colNames);
		for(int i=0;i<infos.size();i++){
			this.resetSchema(infos.get(i)[1]);
			this.inactiveBlockedClosure(infos.get(i)[0]);
		}
	}

	/**
	 * Deactivate all blocked rule
	 * http://wiki.reserveamerica.com/display/dev/ORMS+Code+Base+Look-ups see each rule id stands for
	 * @return
	 */
	private static int deactivateRule(){
		String sql = "update I_RULE_COND set active_ind=0 where rule_id in (7,8,10,12,13,15,16,18,26,27,28,29,30) and active_ind=1 and delete_ind=0 and end_date>current_date";
		return db.executeUpdate(sql);
	}
	
	/**
	 * Deactivate access type true which attribute is no access
	 */
	private static int deactivateAccessTypeRule(){
		String sql = "update I_rule_cond set active_ind=0 where id in (select irc.id from I_RULE_COND irc,I_RULE_ATTR_VALUE irav where irc.id=irav.rule_cond_id and irc.active_ind=1 and irc.delete_ind=0 and irav.rule_attr_id=10 and irav.value=2 and irc.end_date>current_date)";
		return db.executeUpdate(sql);
	}
	
	public void inactiveBlockedRule(){
		logger.info("Start To Inactive Blocked Rule.");
		
		db.resetDefaultDB();
		String query = "select distinct(contract) from site_used";
		List<String> list = db.executeQuery(query, "contract");
		for(String str:list){
			this.resetSchema(str);
			int num = deactivateRule()+deactivateAccessTypeRule();
			String msg = "Deactivate "+num+" Rules For contract "+str;
			logger.info(msg);
			ruleInfos.add(msg);
		}
	}

	private static void outputHeader() throws Exception {
		logger.info("Writing header");

		rpt.write("<html>" + newLine);
		rpt.write("<head>" + newLine);
		rpt.write("<title>Site Scan Report</title>" + newLine);
		rpt.write("</head>" + newLine);
		rpt.write("<body>" + newLine);
		rpt.newLine();
		rpt.write("<h1>Site Scan Report</h1>" + newLine);
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
		rpt.write("<li><a href=\"#site_availablity\">Site Availablity</a></li>" + newLine);
		rpt.write("<li><a href=\"#season\">Season Info</a></li>" + newLine);
		rpt.write("<li><a href=\"#closure\">Closure Info</a></li>" + newLine);
		rpt.write("<li><a href=\"#rule\">Rule Info</a></li>" + newLine);
		rpt.write("<li><a href=\"#d_used_site\">Duplicated Used Sites</a></li>" + newLine);
		rpt.write("</ul>" + newLine);
		rpt.newLine();
		rpt.flush();
	}
	
	private static void outputSiteInfo() throws IOException{
		logger.info("Write Site Availability Info.");
		
		rpt.newLine();
		rpt.write("<a name=\"site_availablity\"><h2>Site Availability</h2></a>" + newLine);
		rpt.newLine();
		for(String str:siteInfos){			
			rpt.write("<blockquote>" + newLine);
			rpt.write(str+newLine);
			rpt.write("</blockquote>" + newLine);
		}
		rpt.flush();
	}
	
	private static void outputSeasonInfo() throws IOException{
		logger.info("Write Season Info.");
		
		rpt.newLine();
		rpt.write("<a name=\"season\"><h2>Season Info</h2></a>" + newLine);
		rpt.newLine();
		for(String str:seasonInfos){			
			rpt.write("<blockquote>" + newLine);
			rpt.write(str+newLine);
			rpt.write("</blockquote>" + newLine);
		}
		rpt.flush();
	}
	
	private static void outputClosureInfo() throws IOException{
		logger.info("Write Closure Info.");
		
		rpt.newLine();
		rpt.write("<a name=\"closure\"><h2>Closure Info</h2></a>" + newLine);
		rpt.newLine();
		for(String str:closureInfos){			
			rpt.write("<blockquote>" + newLine);
			rpt.write(str+newLine);
			rpt.write("</blockquote>" + newLine);
		}
		rpt.flush();
	}
	
	private static void outputRuleInfo() throws IOException{
		logger.info("Write Rule Info.");
		
		rpt.newLine();
		rpt.write("<a name=\"rule\"><h2>Rule Info</h2></a>" + newLine);
		rpt.newLine();
		for(String str:ruleInfos){			
			rpt.write("<blockquote>" + newLine);
			rpt.write(str+newLine);
			rpt.write("</blockquote>" + newLine);
		}
		rpt.flush();
	}
	
	private static void outputDuplicatedUsedSites() throws IOException{
		logger.info("Write Duplicated Used Sites Info.");
		
		List<String[]> siteInfos = showDuplicateUsedSite();
		rpt.newLine();
		rpt.write("<a name=\"d_used_site\"><h2>Duplicated Used Sites</h2></a>" + newLine);
		rpt.newLine();
		rpt.write("<table border=\"1\">" + newLine);
		rpt.write("<tr><th>Site ID</th><th>Site CD</th><th>Arrival Date</th><th>Leave Date</th><th>Case Name</th></tr>" + newLine);
		for(int i=0;i<siteInfos.size();i++){
			rpt.write("<tr><td>" + siteInfos.get(i)[0] + "</td><td>"+siteInfos.get(i)[1]+"</td><td>"+siteInfos.get(i)[2]+"</td><td>"+siteInfos.get(i)[3]+"</td><td>"+siteInfos.get(i)[4]+"</td></tr>" + newLine);
		}
		rpt.write("</table>" + newLine);
		rpt.flush();
	}
	
	private static void outputFooter() throws IOException {
		logger.info("Writing footer");

		rpt.newLine();
		rpt.write("</body>" + newLine);
		rpt.write("</html>" + newLine);
		rpt.flush();
	}
	
	public static void main(String []args) throws Exception{
		rpt = new BufferedWriter(new FileWriter("SiteAvailabilityReport.html"));
		newLine = System.getProperty("line.separator");
		outputHeader();
		outputContents();
		SiteAvailabilitySetup sas = new SiteAvailabilitySetup();
		sas.scanSiteInfo(-1,150);
		outputSiteInfo();
		outputSeasonInfo();
		sas.inactiveClosure();
		outputClosureInfo();
		sas.inactiveBlockedRule();
		outputRuleInfo();
		outputDuplicatedUsedSites();
		outputFooter();
		
	}
}
