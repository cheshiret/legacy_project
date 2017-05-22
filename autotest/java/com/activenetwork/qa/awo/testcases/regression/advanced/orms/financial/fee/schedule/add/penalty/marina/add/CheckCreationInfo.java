package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.penalty.marina.add;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: 
 * 		1.Check the creation info needed user to enter. 
 *        System prompts the User to enter the following information for the Fee Penalty Schedule being added: 
          Fee Type, Dock/Area/Venue, Product Group, Product, Effective Date, Inventory Start Date, Inventory End Date, Transaction Type, Transaction Occurrence, Account, Marina Rate Type, Account and Penalty Rate Details.
        
        2. Check the drop down list of Fee Type. 
          Use Fee and Attribute Fee
          Use Fee is the default value. 


 * @Preconditions:
 * @SPEC: 
 * TC:049552 -- Add Fee Penalty Scheduel - Creation info
 * TC:049553 -- Add Fee Penalty Scheduel - Creation info, Fee Type 
 * TC:049555 -- Add Fee Penalty Scheduel - Creation info, Marina Rate Type
 * TC:049557 -- Add Fee Penalty Scheduel - Creation info, Common info.
 * TC:049463 -- Add Fee Penalty Scheduel - Account format
 * @Task#:AUTO-1431
 * 
 * @author Jasmine
 * @Date Jan 25, 2013(P)
 */
public class CheckCreationInfo extends FinanceManagerTestCase{
	private FinMgrFeePenaltyDetailPage detailPg = FinMgrFeePenaltyDetailPage.getInstance();
	private FinMgrFeePenaltyMainPage mainPg = FinMgrFeePenaltyMainPage.getInstance();
	private FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage.getInstance();
	private FeePenaltyData schedule = new FeePenaltyData();
	private List<String> feeTypeList;
	private List<String> marinaRateTypeList;
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoFeePenaltyPage();
		this.gotoPenaltyScheduleDetailsPageByAddNew(schedule);
		this.verifyAccountIncludesemicolon();
		detailPg.verifyPenaltyCreateionInfoEnalbe();
		this.verifyMarinaRateTypeDefauleValue(schedule.marinaRateType);
		this.verifyFeeTypeList();
		this.veirfyMarinaRateTypeList();
		this.verifyTranscOccrDefaultValue();
		this.verifyMarinaRateTypeDefauleValue(schedule.marinaRateType);
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		schedule.location = fnm.getFacilityName("552834", schema);// Medoc Mountain State Park
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";
		schedule.marinaRateType = "Transient";
		schedule.tranOccur = "All";
		feeTypeList = new ArrayList<String>();
		feeTypeList.add("Use Fee");
		feeTypeList.add("Attribute Fee");
		detailPg.setSlipType(schedule.productCategory);
		
		marinaRateTypeList= new ArrayList<String>();
		marinaRateTypeList.add("Seasonal");
		marinaRateTypeList.add("Lease");
		marinaRateTypeList.add("Transient");
	}
	
	/**
	 * go to fee schedule details page.
	 * @param fd
	 */
	private void gotoPenaltyScheduleDetailsPageByAddNew(FeePenaltyData penalty){
		mainPg.clickAddNew();
		ajax.waitLoading();
		findLocPg.waitLoading();
		findLocPg.searchByLocationName(penalty.location, penalty.locationCategory);
		ajax.waitLoading();
		findLocPg.selectLocation(penalty.location);
		detailPg.waitLoading();
		detailPg.selectPrdCategory(penalty.productCategory);
		ajax.waitLoading();
		detailPg.waitLoading();
	}
	
	private void verifyMarinaRateTypeDefauleValue(String expectedRateType){
		String rateType = detailPg.getSelectedMarinaTateType();
		if(!rateType.equals(expectedRateType)){
			throw new ErrorOnPageException("Default marina rate type value is",expectedRateType,rateType);
		}else{
			logger.info("Result was correct");
		}
	}
	private void verifyListInfo(List<String> expectedList, List<String> actualList){
		if(expectedList.size() != actualList.size()){
			throw new ErrorOnPageException("List size should same",expectedList.size(),actualList.size());
		}
		if(expectedList.contains(actualList)){
			throw new ErrorOnPageException("List info error",expectedList.size(),actualList.size());
		}
	}
	
	private void verifyFeeTypeList(){
		List<String> actualList = detailPg.getFeeTypeList();
		this.verifyListInfo(feeTypeList, actualList);
	}
	
	
	private void veirfyMarinaRateTypeList(){
		List<String> actualList = detailPg.getMarinaRateTypeList();
		this.verifyListInfo(marinaRateTypeList, actualList);
	}
	
	private void verifyTranscOccrDefaultValue(){
		String trancOccur = detailPg.getTransactionOccu();
		if(!trancOccur.equals(schedule.tranOccur)){
			throw new ErrorOnPageException("Transaction Occurrence default error",schedule.tranOccur,trancOccur);
		}else{
			logger.info("Result was correct");
		}
	}
	/**
	 * verify account includes miscolon.
	 */
	private void verifyAccountIncludesemicolon(){
		List<String> accountList = detailPg.getAccountCount();
		accountList.remove(0);
		for(String account:accountList){
			if(!account.contains(";"))
				throw new ErrorOnPageException("account code '"+account+"' should include ;");
		}
//		if(!accountList.contains(";")){
//			throw new ErrorOnPageException("account code should include ;");
//		}else{
//			
//		}
		logger.info("Result was correct");
			
	}
	
	
	
	
	
}
