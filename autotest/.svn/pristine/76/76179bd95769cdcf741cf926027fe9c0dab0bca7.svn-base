/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.dateperiods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * 			1. Add new Date Period "0PT", add successfully.
 * 			2. Add Date Period on different conditions, check error message prompted.
 * 			3. Modify Date Period "0PT", check modify result.
 * @Preconditions:  
 * 1.  Configure Hunt Components(http://wiki.reserveamerica.com/display/dev/Big+Game+Supplementary+Setup)
 * 		Insert into P_HUNT_CFG  (ID,STATUS_ID,CHART_OF_ACCT_ID,DELETED_IND,CREATE_DATETIME,CREATE_USER_ID,CREATE_LOC_ID,UPDATE_DATETIME,UPDATE_USER_ID,UPDATE_LOC_ID) VALUES (1,1,1,'0',TO_DATE('05-MAY-12','DD-MON-RR'),3,1,NULL,NULL,NULL);
 * 		INSERT INTO P_HUNT_COMP_DSP_ORDR (ID,COMPONENT_ID,DISP_ORDER,CFG_ID,STATUS_ID,DELETED_IND) VALUES (4,4,4,1,1,'0');
 * 		--COMPONENT_ID: 1: Species sub type, 2: Weapon, 3: Hunt location, 4: Date period. Refer to CBL class HuntComponent.java.
 * 		--DISP_ORDER decide which component is shown first. In the above setting, weapon will be shown before species sub type.
 * 
 * 2. Roles/features to role 'HF Administrator' as followed(QA setup: D_ASSIGN_FEATURE ID=2770). 
 * 		LicenseManager	ViewDatePeriod	View Date Period
 * 		LicenseManager	CreateModifyDatePeriod	Create Modify Date Period
 * 		LicenseManager	ViewDatePeriodLicenseYear	View Date Period License Year
 * 		LicenseManager	CreateModifyDatePeriodLicenseYear	Create Modify Date Period License Year
 * 		LicenseManager	AssignUnassignDatePeriodToHunt	Assign Unassign Date Period To Hunt
 * 		LicenseManager	ViewDatePeriodChangeHistory	View Date Period Change History
 * 		LicenseManager	ViewHuntDatePeriodList	View Hunt Date Period List
 * 		LicenseManager	ViewDatePeriodHuntList	View Date Period Hunt List
 * 
 * @SPEC:Lottery Add/edit Date Period: TC046732,046736,046737,046739,046740,046923,046925,046928,046930,046931
 * @Task#:AUTO-1259
 * 
 * @author pzhu
 * @Date  Sep 17, 2012
 */
public class CreateAndModifyDatePeriod extends LicenseManagerTestCase{
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private DatePeriodInfo datePeriodInfo = new DatePeriodInfo();
	private DatePeriodInfo infoModify =   new DatePeriodInfo();
	private List<DatePeriodLicenseYearInfo> licenseYearInfos = new ArrayList<DatePeriodLicenseYearInfo>();
	private List<Dates> periods = new ArrayList<Dates>();; 
	private LicMgrDatePeriodsListPage listPg = LicMgrDatePeriodsListPage.getInstance();
	private String[] errorMsg;

	
	private String[][] features ={
				{"ViewDatePeriod",	"View Date Period"},
				{"CreateModifyDatePeriod",		"Create Modify Date Period"},
				{"ViewDatePeriodLicenseYear",	"View Date Period License Year"},
				{"CreateModifyDatePeriodLicenseYear",	"Create Modify Date Period License Year"},
				{"AssignUnassignDatePeriodToHunt",		"Assign Unassign Date Period To Hunt"},
				{"ViewDatePeriodChangeHistory",		"View Date Period Change History"},
				{"ViewHuntDatePeriodList",		"View Hunt Date Period List"},
				{"ViewDatePeriodHuntList",		"View Date Period Hunt List"},
				}; 
		
	
	@Override
	public void execute() {
		this.deactivateOldRecord(this.datePeriodInfo);
		lm.checkRolesFeatures("HF Administrator - Auto", this.features, LICENSE_MANAGER, schema);
		
		lm.loginLicenseManager(login);
			
		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoDatePeriodListPgFromLotteriesProdListPg();
		
		//Step 1: Add new Date period.
		String msg = lm.addDatePeriods(this.datePeriodInfo);
		if(!msg.equalsIgnoreCase(this.datePeriodInfo.getCode()))
		{
			logger.info("Error msg is -->"+msg);
			throw new ErrorOnPageException("Adding new Date Period Failed...Date Period info is-->"+StringUtil.ObjToString(this.datePeriodInfo));
		}
		
		//Check point 1: check add correct Date period record.
		this.checkAddResult();

		//Check point 2: check add Date period on other conditions...
		this.checkFailedMsgOfCreate();
	
		//Step 2: go to date period detail page and modify date period.
		lm.updateDatePeriodInfo(this.infoModify);
		//Check point 3: check modify date period record.
		this.checkModifyResult();
		
		lm.logOutLicenseManager();
	}
	
	
	/**
	 * 
	 */
	private void checkModifyResult() {
		//check result;
		listPg.searchByStatus(INACTIVE_STATUS);
		List<DatePeriodInfo> records = listPg.getAllRecordsOnPage();
		boolean found = false;
		for(DatePeriodInfo rs: records)
		{
			if(rs.getCode().equalsIgnoreCase(infoModify.getCode())
				&&rs.getDescription().equalsIgnoreCase(infoModify.getDescription())
				&&rs.getStatus().equalsIgnoreCase(infoModify.getStatus()))
			{
				found = true;
				break;
			}
		}
		if(!found)
		{
			throw new ErrorOnPageException("Check modify result Failed!!! Please check this record on page-->"+StringUtil.ObjToString(this.infoModify));
		}
		logger.info("Check modify result Passed!!!");
		
	}

	/**
	 * 
	 */
	private void checkFailedMsgOfCreate() {
		String rand = String.valueOf(((new Random().nextInt(99999))));
		String msg;
		int msgIdx = 0;
		DatePeriodInfo info = new DatePeriodInfo();
		info.setLicenseYears(this.licenseYearInfos);
	
		//Add duplicated code.
		msgIdx = 0;
		info.setCode(this.datePeriodInfo.getCode());
		info.setDescription(this.datePeriodInfo.getDescription()+rand);
		msg = lm.addDatePeriods(info);
		if((!msg.equalsIgnoreCase(this.datePeriodInfo.getCode()))&&(msg.trim().equalsIgnoreCase(this.errorMsg[msgIdx])))
		{
			logger.info("Check error msg of duplicated code Passed!!!");
		}else{
			throw new ErrorOnPageException("Error message of duplicated code wrong, ",this.errorMsg[msgIdx],msg );	
		}
		
		//Add empty code.
		msgIdx = 1;
		info.setCode(null);
		info.setDescription(this.datePeriodInfo.getDescription()+rand);
		msg = lm.addDatePeriods(info);
		if((!msg.equalsIgnoreCase(this.datePeriodInfo.getCode()))&&(msg.trim().equalsIgnoreCase(this.errorMsg[msgIdx])))
		{
			logger.info("Check error msg of empty code Passed!!!");
		}else{
			throw new ErrorOnPageException("Error message of empty code wrong, ",this.errorMsg[msgIdx],msg );	
		}
		
		//Add empty description.
		msgIdx = 2;
		info.setCode(this.datePeriodInfo.getCode().substring(1)+"Z");
		info.setDescription(null);
		msg = lm.addDatePeriods(info);
		if((!msg.equalsIgnoreCase(this.datePeriodInfo.getCode()))&&(msg.trim().equalsIgnoreCase(this.errorMsg[msgIdx])))
		{
			logger.info("Check error msg of empty description Passed!!!");
		}else{
			throw new ErrorOnPageException("Error message of empty description wrong, ",this.errorMsg[msgIdx],msg );	
		}

		//Add duplicate description.
		msgIdx = 3;
		info.setCode(this.datePeriodInfo.getCode().substring(1)+"M");
		info.setDescription(this.datePeriodInfo.getDescription());
		msg = lm.addDatePeriods(info);
		if((!msg.equalsIgnoreCase(this.datePeriodInfo.getCode()))&&(msg.trim().equalsIgnoreCase(this.errorMsg[msgIdx])))
		{
			logger.info("Check error msg of duplicate description Passed!!!");
		}else{
			throw new ErrorOnPageException("Error message of duplicate description wrong, ",this.errorMsg[msgIdx],msg );	
		}
	
		
	}

	/**
	 * 
	 */
	private void checkAddResult() {
		List<DatePeriodInfo> results = listPg.getAllRecordsOnPage();
		boolean found = false;
		for(DatePeriodInfo rs: results)
		{
			if(rs.getCode().equalsIgnoreCase(this.datePeriodInfo.getCode())
				&&rs.getDescription().equalsIgnoreCase(this.datePeriodInfo.getDescription())
				&&rs.getStatus().equalsIgnoreCase(ACTIVE_STATUS))
			{
				found = true;
				break;
			}
				
		}
		
		if(!found)
		{
			throw new ErrorOnPageException("Add new Date period failed, please check Date period -->"+this.datePeriodInfo.getCode()+" on the list page.");
		}
		logger.info("Check new date period info Passed!!!");
	}

	/**
	 * @param info
	 */
	private void deactivateOldRecord(DatePeriodInfo info) {
		db.resetSchema(schema);
		String update = "UPDATE D_DATE_PERIOD SET STATUS = 0 WHERE CODE = '" +
				info.getCode() +
				"'";
		logger.info("update SQL:"+update);
		
		int result = db.executeUpdate(update);
		logger.info("Total "+result+" date period record updated...");		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";

		Dates span = new DatePeriodLicenseYearInfo().new Dates();
		span.setFromDate("Wed Jan 01 2014");
		span.setToDate("Wed Jan 20 2014");
		periods.add(span);
		
		DatePeriodLicenseYearInfo licenseYearInfo = new DatePeriodLicenseYearInfo();
		licenseYearInfo.setDates(periods);
		licenseYearInfo.setLicenseYear("2014");
		licenseYearInfos.add(licenseYearInfo);
		
		datePeriodInfo.setCode("0PT");//start with 0, be fine...
		datePeriodInfo.setDescription("CreateAndModifyDatePeriod");
		datePeriodInfo.setLicenseYears(licenseYearInfos);	
		
		
				
		
		
		errorMsg = new String[]{
				"A Date Period with the same Code already exists. Please verify.",
				"Code is required. Please specify the Code.",
				"Description is required. Please specify the Description.",
				"A Date Period with the same Description already exists. Please verify."};
		
		//For update Date period
		String randStr = String.valueOf(((new Random().nextInt(99999))))+"_"+String.valueOf(((new Random().nextInt(99999))));
		infoModify.setCode(datePeriodInfo.getCode());
		infoModify.setDescription(this.datePeriodInfo.getDescription()+randStr);
		infoModify.setStatus(INACTIVE_STATUS);
	
	}
	
	

}
