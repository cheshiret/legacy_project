package com.activenetwork.qa.awo.supportscripts.qasetup.db;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.ConstantsInterpreter;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * This script is used to resolve rule block issue and the logic is as below:
 * 1.deactivate all the exist rules and record the rule id just deactivated
 * 2. create correct rules on contract level,such as create 'advance sale' access type rule for all sales channel and 'day of sale' for field
 * by this way to make sure the required correct rules exists
 * @author ssong
 * @deprecated  - refer to CleanUpRule script
 *
 */
public class RuleCleanUp {

	private static AwoDatabase db;
	private static AutomationLogger logger;
	private static BufferedWriter rpt;
	private String contract;
	private static String newLine;
	private static List<String> ruleInfos = new ArrayList<String>();
	private static String DATE_PATTERN = "yyyy-MM-dd";

	//product category ID
	private static final int SITE_PRD_CAT_ID = 3;
	private static final int PERMIT_PRD_CAT_ID = 5;
	private static final int TICKET_PRD_CAT_ID = 6;

	//sales channel id
	private static final int SALES_CHNL_ALL = 0;
	private static final int SALES_CHNL_FIELD = 4;

	//rule id
	private final static int ACCESS_TYPE = 2;
	private final static int TIME_RESTRICTION_BEFORE_CHANGE_OF_DATES_ALLOWED = 5;
	private final static int ACCESS_TIME = 6;
	private final static int MINIMUM_STAY = 7;
	private final static int SPECIFIED_STAY_START = 8;
	private final static int MAXIMUM_NUM_OF_STAYS_PER_PERIOD = 10;
	private final static int MAXIMUM_NUM_OF_CONCURRENT_RES = 12;
	private final static int MAXIMUM_NUM_OF_RES_SAME_STARTDATE = 13;
	private final static int MAXIMUM_TOTAL_STAY = 15;
	private final static int MAXIMUM_STAY_PER_PERIOD = 16;
	private final static int MAXIMUM_CONSECUTIVE_STAY = 18;
	private final static int TRANSACTION_RESTRICTION = 26;
	private final static int MAXIMUM_NUM_OF_ORDERS_PER_CALL = 27;
	private final static int MAXIMUM_NUM_OF_ORDERS_WITHIN_BOOKING_PERIOD = 28;
	private final static int MAXIMUM_NUM_OF_ORDERS_WITHIN_STAY_PERIOD = 29;
	private final static int BLOCK_STAY = 30;
	private final static int ASSOCIATE_ENTRANCE = 31;
	private final static int RESTRICT_ENTRANCE = 36;
	private final static int ISSUE_PERMIT_RESTRICTION = 40;

	//used for calculate seek number(means weight,decide which rule is used when there are multiple rules exists)
    public final static int s_iTransactionOccurrence = 128;
    public final static int s_iAssociatedParty = 1 * 256;
    public final static int s_iCustomerMember = 2 * 256;
    public final static int s_iPaymentType = 4 * 256;
    public final static int s_iSalesChannel = 8 * 256;
    public final static int s_iOutOfState = 16 * 256;
    public final static int s_iCustomerPassType = 32 * 256;
    public final static int s_iCustomerType = 64 * 256;
    public final static int s_iSeasonType = 128 * 256;
    public final static int s_iProductGroup = 256 * 256;
    public final static int s_iSalesCategory = 512 * 256;


	public RuleCleanUp(){
		db = AwoDatabase.getInstance();
	}

	private void resetSchema(String contract){
		String schema = DataBaseFunctions.getSchemaName(contract, TestProperty.getProperty("target_env"));
		db.resetSchema(schema);
	}

	private void scanSiteRecordTable(int daysBeforeToday,int daysAfterToday){
		String query = "select distinct(contract) as contract from site_used order by contract";
		List<String> contracts = db.executeQuery(query,"contract");

		String startDate = DateFunctions.getDateAfterToday(daysBeforeToday,DATE_PATTERN);
		String endDate = DateFunctions.getDateAfterToday(daysAfterToday,DATE_PATTERN);
		try{
			int i = 0;
			for(String cont:contracts){
				contract = cont;
				this.resetSchema(contract);
				ruleInfos.add(wrapMsg(contract));
				fixAccessTimeRule(startDate, endDate);
				fixAccessTypeRule(startDate, endDate);
				deactivateAllBlockedEffectiveRules();
				outputRuleInfo(i);
				i++;
			}
		}catch(IOException e){
			e.printStackTrace();
		}

	}
	
	private void scanSiteRecordTableForSanity(int daysBeforeToday,int daysAfterToday){
		String query = "select distinct(contract) as contract from site_used where case_name like 'testCases.sanity.%' order by contract";
		List<String> contracts = db.executeQuery(query,"contract");
		String startDate = DateFunctions.getDateAfterToday(daysBeforeToday,DATE_PATTERN);
		String endDate = DateFunctions.getDateAfterToday(daysAfterToday,DATE_PATTERN);
		try{
			int i = 0;
			for(String cont:contracts){
				contract = cont;
				this.resetSchema(contract);
				ruleInfos.add(wrapMsg(contract));
				fixAccessTimeRule(startDate, endDate);
				fixAccessTypeRule(startDate, endDate);
				deactivateAllBlockedEffectiveRules();
				outputRuleInfo(i);
				i++;
			}
		}catch(IOException e){
			e.printStackTrace();
		}

	}

	private void deactivateAllBlockedEffectiveRules(){
		this.deactivateAllEffectiveRules(MINIMUM_STAY);
		this.deactivateAllEffectiveRules(SPECIFIED_STAY_START);
		this.deactivateAllEffectiveRules(MAXIMUM_NUM_OF_STAYS_PER_PERIOD);
		this.deactivateAllEffectiveRules(MAXIMUM_NUM_OF_CONCURRENT_RES);
		this.deactivateAllEffectiveRules(MAXIMUM_NUM_OF_RES_SAME_STARTDATE);
		this.deactivateAllEffectiveRules(MAXIMUM_TOTAL_STAY);
		this.deactivateAllEffectiveRules(MAXIMUM_STAY_PER_PERIOD);
		this.deactivateAllEffectiveRules(MAXIMUM_CONSECUTIVE_STAY);
		this.deactivateAllEffectiveRules(TRANSACTION_RESTRICTION);
		this.deactivateAllEffectiveRules(MAXIMUM_NUM_OF_ORDERS_PER_CALL);
		this.deactivateAllEffectiveRules(MAXIMUM_NUM_OF_ORDERS_WITHIN_BOOKING_PERIOD);
		this.deactivateAllEffectiveRules(MAXIMUM_NUM_OF_ORDERS_WITHIN_STAY_PERIOD);
		this.deactivateAllEffectiveRules(BLOCK_STAY);
		this.deactivateAllEffectiveRules(ASSOCIATE_ENTRANCE);
		this.deactivateAllEffectiveRules(RESTRICT_ENTRANCE);
		this.deactivateAllEffectiveRules(ISSUE_PERMIT_RESTRICTION);
		this.deactivateAllEffectiveRules(TIME_RESTRICTION_BEFORE_CHANGE_OF_DATES_ALLOWED);
	}

	public void fixAccessTypeRule(String start_date,String end_date){
		ruleInfos.add(wrapMsg("Access Type"));
		this.deactivateAllEffectiveRules(ACCESS_TYPE);

		//add access type rule for site product
		//advanced sale for all sales channel and day of sale for field manager
		this.addAccessTypeRule(SITE_PRD_CAT_ID, SALES_CHNL_ALL, start_date, end_date, false);
		this.addAccessTypeRule(SITE_PRD_CAT_ID, SALES_CHNL_FIELD, start_date, end_date, true);

		//currently our system not support contract level rule for permit
//		//add access type rule for permit product
//		//advanced sale for all sales channel and day of sale for field manager
//		this.addAccessTypeRule(PERMIT_PRD_CAT_ID, SALES_CHNL_ALL, start_date, end_date, false);
//		this.addAccessTypeRule(PERMIT_PRD_CAT_ID, SALES_CHNL_FIELD, start_date, end_date, true);

		//add access type rule for ticket product
		//advanced sale for all sales channel and day of sale for field manager
		this.addAccessTypeRule(TICKET_PRD_CAT_ID, SALES_CHNL_ALL, start_date, end_date, false);
		this.addAccessTypeRule(TICKET_PRD_CAT_ID, SALES_CHNL_FIELD, start_date, end_date, true);

	}

	public void addAccessTypeRule(int prd_cat_id,int sales_chnl,String start_date,String end_date,boolean isDayOfSale){
		int rule_cond_id = this.addNewRule(ACCESS_TYPE, prd_cat_id, sales_chnl, start_date, end_date);

		if(isDayOfSale){
			//add access type attribute(10) with value=4(day of Sale)
			this.addRuleAttr(ACCESS_TYPE, rule_cond_id, 10, 4);
			//add length attribute(2) with value=1
			this.addRuleAttr(ACCESS_TYPE, rule_cond_id, 2, 1);
		}else{
			//add access type attribute(10) with value=3(advanced Sale)
			this.addRuleAttr(ACCESS_TYPE, rule_cond_id, 10, 3);
		}
		//add Unit attribute(1) with value=2(day)
		this.addRuleAttr(ACCESS_TYPE, rule_cond_id, 1, 2);
		String msg = "Add '"+(isDayOfSale?"Day of Sale":"Advance Sale")+"' Access Type Rule("+rule_cond_id+") for '"+ConstantsInterpreter.getPrdCategory(prd_cat_id)+"' For contract "+contract;
		logger.info(msg);
		ruleInfos.add(msg);
	}

	public void fixAccessTimeRule(String start_date,String end_date){
		ruleInfos.add(wrapMsg("Access Time"));
		this.deactivateAllEffectiveRules(ACCESS_TIME);

		//add access time rule for site product
		addAccessTimeRule(SITE_PRD_CAT_ID, SALES_CHNL_ALL, start_date, end_date);
		//currently our system not support contract level rule for permit
//		//add access time rule for permit product
//		addAccessTimeRule(PERMIT_PRD_CAT_ID, SALES_CHNL_ALL, start_date, end_date);
		//add access time rule for ticket product
		addAccessTimeRule(TICKET_PRD_CAT_ID, SALES_CHNL_ALL, start_date, end_date);
	}

	/**
	 * add access time rule for different products on different sales channel
	 * @param prd_cat_id
	 * @param sales_chnl
	 * @param start_date
	 * @param end_date
	 */
	public void addAccessTimeRule(int prd_cat_id,int sales_chnl,String start_date,String end_date){
		int rule_cond_id = this.addNewRule(ACCESS_TIME, prd_cat_id, sales_chnl, start_date, end_date);
		//add 'open time'(30) attribute value as 'during maximum window'(1)
		this.addRuleAttr(ACCESS_TIME, rule_cond_id, 30, 1);
		//add 'daily open time'(32) attribute value as '00:00'(18000000)
		this.addRuleAttr(ACCESS_TIME, rule_cond_id, 32, 18000000);
		//add 'close time'(40) attribute value as 'during maximum window'(1)
		this.addRuleAttr(ACCESS_TIME, rule_cond_id, 40, 1);
		//add 'daily close time'(42) attribute value as '23:59'(104340000)
		this.addRuleAttr(ACCESS_TIME, rule_cond_id, 42, 104340000);
		String msg = "Add Access Time Rule("+rule_cond_id+") for '"+ConstantsInterpreter.getPrdCategory(prd_cat_id)+"' For contract "+contract;
		logger.info(msg);
		ruleInfos.add(msg);
	}

	private List<String> deactivateAllEffectiveRules(int rule_type_id){
		String rule_name = ConstantsInterpreter.getRuleName(rule_type_id);
		String sql = "Select id from I_rule_cond where rule_id="+rule_type_id+" and active_ind=1 and delete_ind=0 and end_date>sysdate";//exclude permit product
		List<String> ids = db.executeQuery(sql, "id");

		sql = "update I_rule_cond set active_ind=0 where rule_id="+rule_type_id+" and active_ind=1 and delete_ind=0 and end_date>sysdate";//exclude permit product
		int num = db.executeUpdate(sql);
		String msg = "Deactivate "+rule_name+" Rule:"+StringUtil.listToString(ids, true)+" For contract "+contract;
		logger.info(msg);
		ruleInfos.add(msg);
		msg = "Total "+num+" "+rule_name+" Rule(s) Were Deactivated For Contract "+contract;
		logger.info(msg);
		ruleInfos.add(msg);
		return ids;
	}
	/**
	 * Add new rule from rule condition table
	 * @param rule_type_id
	 * @param prd_cat_id
	 * @param sales_chnl
	 * @param start_date
	 * @param end_date
	 * @return
	 */
	private int addNewRule(int rule_type_id,int prd_cat_id,int sales_chnl,String start_date,String end_date){
		int seek_num = 0;
		//as all the other attributes was set on all level,just sales channel maybe not on all level
		//so just calculate seek number for sales channel here
		if(sales_chnl!=SALES_CHNL_ALL){
			seek_num = s_iSalesChannel;
		}
		String sql = "select max(id) as id from I_rule_cond";
		String max_id = db.executeQuery(sql, "id", 0);
		int id = (max_id == null ? 1:Integer.parseInt(max_id)+1);
		sql = "insert into I_rule_cond(id,rule_id,loc_id,sale_chnl_id,seek_order,start_date,end_date,effect_date,active_ind,cust_memb_id,out_state_id,delete_ind,prd_cat_id,sales_cat_id,tran_occur_id)" +
				               " values("+id+","+rule_type_id+",1,"+sales_chnl+","+seek_num+","+wrapCharToDate(start_date)+","+wrapCharToDate(end_date)+",sysdate,1,2,2,0,"+prd_cat_id+",0,0)";
		db.executeUpdate(sql);
		return id;
	}

	private void addRuleAttr(int rule_type_id,int rule_cond_id,int rule_attr_id,int value){
		String sql = "select max(id) as id from I_RULE_ATTR_VALUE";
		String max_id = db.executeQuery(sql, "id", 0);
		int id = (max_id == null ? 1:Integer.parseInt(max_id)+1);
		sql = "insert into I_RULE_ATTR_VALUE values("+id+","+rule_type_id+","+rule_attr_id+","+rule_cond_id+","+value+")";
		db.executeUpdate(sql);
	}

	private static String wrapMsg(String msg){
		String wrapedMsg = "<B style=\"color:#f00\">"+msg+"</B>"+newLine;
		return wrapedMsg;
	}

	private String wrapCharToDate(String date){
		return "to_date('"+date+"','YYYY-MM-DD')";
	}

	private static void outputHeader() throws IOException {
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
		rpt.write("<li><a href=\"#rule\">Rule Info</a></li>" + newLine);
		rpt.write("</ul>" + newLine);
		rpt.newLine();
		rpt.flush();
	}

	private static void outputRuleInfo(int i) throws IOException{
		logger.info("Write Rule Info.");

		if(i==0){
			rpt.newLine();
			rpt.write("<a name=\"rule\"><h2>Rule Info</h2></a>" + newLine);
			rpt.newLine();
		}
		for(String str:ruleInfos){
			rpt.write("<blockquote>" + newLine);
			rpt.write(str+newLine);
			rpt.write("</blockquote>" + newLine);
		}
		ruleInfos.clear();
		rpt.flush();
	}

	private static void outputFooter() throws IOException {
		logger.info("Writing footer");

		rpt.newLine();
		rpt.write("</body>" + newLine);
		rpt.write("</html>" + newLine);
		rpt.flush();
	}


	public static void main(String[] args) throws IOException {
		rpt = new BufferedWriter(new FileWriter("RuleReport_"+DateFunctions.getCurrentTime()+".html"));
		newLine = System.getProperty("line.separator");
		AwoUtil.initTestProperty();
		AutomationLogger.init(AwoUtil.getProjectPath(), AwoUtil.LOG4J_PROPERTY);
		logger = AutomationLogger.getInstance();
		outputHeader();
		outputContents();
		RuleCleanUp cleanUp = new RuleCleanUp();
		cleanUp.scanSiteRecordTable(-30, 1000);
//		cleanUp.scanSiteRecordTableForSanity(-30,1000);
		outputFooter();

	}

}
