package com.activenetwork.qa.awo.supportscripts.qasetup.finance;


import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddFeeScheduleFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

//	Data in datapool #70 is wrong; 
//	#122: edw-lottery-1 is not available yet so update to use 'qa auto lottery for site 201'
//  #155 & #161 & #170 facility - MAMMOTH CAVE NATIONAL PARK TOURS failed due to Expected option: Adult is not available! -- need to confirm if there is defect on QA4.


public class AddTranFeeSchedule  extends SetupCase{

	private String contract = "";
	private FeeScheduleData fd;
	boolean loggedIn = false;
	LoginInfo login = new LoginInfo();
	AddFeeScheduleFunction func = new AddFeeScheduleFunction();
	
	public void executeSetup() {				
		Object[] args = new Object[2];
		args[0] = this.login;
		args[1] = this.fd;		
		func.execute(args);
	}

	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_tran_fee_sched";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
	}

	public void readDataFromDatabase() {
		contract = datasFromDB.get("contract");
		if(!contract.contains("Contract")){
			contract = contract + " Contract";
		}
		login.contract = contract;
		login.location = datasFromDB.get("roleLocation");

		fd = new FeeScheduleData();
		
		fd.feeType = "Transaction Fee";
		
		fd.location = datasFromDB.get("locationName");
		fd.locationCategory = datasFromDB.get("locationCategory");
		fd.productCategory = datasFromDB.get("productCategory");
		fd.loop = datasFromDB.get("loopname");
		fd.productGroup = datasFromDB.get("prodGroup");
		fd.product = datasFromDB.get("product");
		fd.effectDate = datasFromDB.get("effectDate");
		if(StringUtil.isEmpty(fd.effectDate)){
			fd.effectDate = DateFunctions.getDateAfterToday(-5);
		}
		fd.salesChannel = datasFromDB.get("salesChannel");
		fd.state = datasFromDB.get("state");
		fd.tranType = datasFromDB.get("tranType");
		fd.tranOccur = datasFromDB.get("tranOccur");
		//		fd.acctCode = datasFromDB.get("acctCode");	
		fd.tranFeeOption = datasFromDB.get("tranFeeOption");

		if (fd.productCategory.equalsIgnoreCase("Permit")){
			fd.permitCategory = datasFromDB.get("salesCategory");//permit category
			fd.permitType = datasFromDB.get("permitType");
			fd.personTypes = datasFromDB.get("personOrTicketTypes").split("\\|");//Person types
			fd.anyDayRates = datasFromDB.get("tranFee").split("\\|");			
		}else if (fd.productCategory.equalsIgnoreCase("Ticket")){
			fd.ticketCategory = datasFromDB.get("salesCategory");//permit category
			fd.applyFee = datasFromDB.get("applyFee");
			if(!StringUtil.isEmpty(datasFromDB.get("isEmbedInTicketFee"))){
				fd.isEmbedInTicketFee = datasFromDB.get("isEmbedInTicketFee").equalsIgnoreCase("true")?true:false;
			}
			if(!StringUtil.isEmpty(datasFromDB.get("deliverymethod"))){
				fd.deliveryMethod = datasFromDB.get("deliverymethod");
			}
			fd.ticketTypes = datasFromDB.get("personOrTicketTypes").split("\\|");
			if(fd.tranFeeOption.equalsIgnoreCase("Flat by Range of Ticket Quantity"))
				fd.anyDayRanges=datasFromDB.get("RateRange").split("\\|");
			fd.anyDayRates = datasFromDB.get("tranFee").split("\\|");
		}else if (fd.productCategory.equalsIgnoreCase("Lottery")){
			fd.appProductGroup = datasFromDB.get("appProductGroup"); 
			fd.tranFee = datasFromDB.get("tranFee");
		}else {
			fd.tranFee = datasFromDB.get("tranFee");
		}	
		fd.minimumUnitOfStay = datasFromDB.get("MINIMUM_UNIT_OF_STAY");
		fd.minimumNumOfDayBeforeArrivalDay = datasFromDB.get("MINIMUM_NUM_DAYS_BEFORE_ARRIVA");
		
		fd.maximumFeeRestriction = datasFromDB.get("MAXIMUM_FEE_RESTRICTION");
		fd.maximumFeeRate = datasFromDB.get("MAXIMUM_FEE_RATE");
		fd.tranFeeChangedUnits = datasFromDB.get("TRANS_FEE_CHANGED_UNITS");
		fd.tranFeeFlatAmount = datasFromDB.get("TRANS_FEE_FLAT_AMOUNT");
		fd.rateApplyTo = datasFromDB.get("RATE_APPLIES_TO");
		
	}

}
