package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.tax.schedule;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.Tax;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: This case is used to verify when search by different search type of search field
 * @Preconditions:A List named 'EditTaxSchdStatusUsed' has been created, the precondition for this list is that a slip code is 'FULT' has been added
 * 				d_inv_add_list:id=60(LISTNAME='EditTaxSchdStatusUsed')
 *				d_inv_add_slip:id=290(SLIPNAME='For Used List Test')
 * @SPEC:Search Tax Schedule - Search field [TC:049974]
 * @Task#: Auto-1441
 * @author Phoebe
 * @Date  Feb 7 2013
 */
public class VerifySearchResult_SearchField extends FinanceManagerTestCase {
	private Tax tax = new Tax();
	private ScheduleData schedule = new ScheduleData();
	private FinMgrTaxSchedulePage schedulePg = FinMgrTaxSchedulePage.getInstance();
	private String searchValue;
	private static final String ACCOUNT_SEARCH_TYPE = "Account";
	private static final String LOCATION_SEARCH_TYPE = "Location";
	private static final String PRODUCT_SEARCH_TYPE = "Product";
	private static final String PRODUCT_GROUP_SEARCH_TYPE = "Product Group";
	private static final String PRODUCT_OR_GROUP_SHOW = "Product Or Product Group";
	private static final String TAX_NAME_SEARCH_TYPE = "Tax Name";
	private static final String TAX_SCHEDULE_ID_SEARCH_TYPE = "Tax Schedule ID";
	private String warningMessage = "No Tax Schedules found for search criteria.";
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
	    fnm.gotoTaxMainPage();
	    //Check data prepare and clean the environment  
	    if(!fnm.checkTaxExistOrNotFromDB(tax.taxName, schema)){
	    	fnm.addNewTax(tax);
	    }
		//Add a new schedule for the tax
	    schedule.scheduleId = fnm.addNewTaxSchedule(schedule, true);
	    
	    //1.Search by a valid Account code
	    String accountCode = schedule.accountCode.split(";")[0].trim();
	    searchValue = accountCode;
	    searchBySearchField(ACCOUNT_SEARCH_TYPE, searchValue);
	    verifySearchValueInColumn(ACCOUNT_SEARCH_TYPE, searchValue, true);
	    
	    //2.Search by part of a valid Account code
	    searchValue = accountCode.substring(0, accountCode.length()-5);
	    searchBySearchField(ACCOUNT_SEARCH_TYPE, searchValue);
	    verifySearchValueInColumn(ACCOUNT_SEARCH_TYPE, searchValue, false);
	    
	    //3.Search by location
	    searchValue = schedule.location;
	    searchBySearchField(LOCATION_SEARCH_TYPE, searchValue);
	    verifySearchValueInColumn(LOCATION_SEARCH_TYPE, searchValue, true);
	    
	    //4.Search by part of a valid location name
	    searchValue = schedule.location.substring(0,schedule.location.length()-5);
	    searchBySearchField(LOCATION_SEARCH_TYPE, searchValue);
	    verifySearchValueInColumn(LOCATION_SEARCH_TYPE, searchValue, false);
	    
	    //5.Search by low-case location name
	    searchValue = schedule.location.toLowerCase();
	    searchBySearchField(LOCATION_SEARCH_TYPE, searchValue);
	    verifySearchValueInColumn(LOCATION_SEARCH_TYPE, searchValue, true);
	    
	    //6.Search by product
	    String product = schedule.product.split("-")[0];
	    searchValue = product;
	    searchBySearchField(PRODUCT_SEARCH_TYPE, searchValue);
	    verifySearchValueInColumn(PRODUCT_OR_GROUP_SHOW, searchValue, true);
	    
	    //7.Search by  part of a valid Product name 
	    searchValue = product.substring(0, product.length()-5);
	    searchBySearchField(PRODUCT_SEARCH_TYPE, searchValue);
	    verifySearchValueInColumn(PRODUCT_OR_GROUP_SHOW, searchValue, false);
	    
	    //8.Search by low-case product name
	    searchValue = product.toLowerCase();
	    searchBySearchField(PRODUCT_SEARCH_TYPE, searchValue);
	    verifySearchValueInColumn(PRODUCT_OR_GROUP_SHOW, searchValue, true);
	    
	    //9.Search by product group
	    searchValue = "Full attributes";
	    searchBySearchField(PRODUCT_GROUP_SEARCH_TYPE, searchValue);
	    verifySearchValueForPrdGrp("Full attributes");
	    
	    //10.Search by part of a valid Product Group name
	    searchValue = "Full attri";
	    searchBySearchField(PRODUCT_GROUP_SEARCH_TYPE, searchValue);
	    verifySearchValueForPrdGrp("Full attributes");
	    
	    //11.Search by low-case product group name
	    searchValue = "Full attributes".toLowerCase();
	    searchBySearchField(PRODUCT_GROUP_SEARCH_TYPE, searchValue);
	    verifySearchValueForPrdGrp("Full attributes");

	    //12.Search by Tax Schedule ID
	    searchValue = schedule.scheduleId;
	    searchBySearchField(TAX_SCHEDULE_ID_SEARCH_TYPE, searchValue);
	    verifySearchValueInColumn(TAX_SCHEDULE_ID_SEARCH_TYPE, searchValue, true);
	    
	    //13.Search by part of an existing tax schedule ID
	    searchValue = schedule.scheduleId.substring(0,schedule.scheduleId.length()-3);
	    searchBySearchField(TAX_SCHEDULE_ID_SEARCH_TYPE, searchValue);
	    verifyErrorMessage();
	    
	    //14.Search by Tax Name
	    searchValue = schedule.taxName;
	    searchBySearchField(TAX_NAME_SEARCH_TYPE, searchValue);
	    verifySearchValueInColumn(TAX_NAME_SEARCH_TYPE, searchValue, true);
	    
	    //15.Search by part of a valid Tax Name
	    searchValue = schedule.taxName.substring(0, schedule.taxName.length()-5);
	    searchBySearchField(TAX_NAME_SEARCH_TYPE, searchValue);
	    verifySearchValueInColumn(TAX_NAME_SEARCH_TYPE, searchValue, false);
	    
	    //16.Search by low-case product Tax Name
	    searchValue = schedule.taxName.toLowerCase();
	    searchBySearchField(TAX_NAME_SEARCH_TYPE, searchValue);
	    verifySearchValueInColumn(TAX_NAME_SEARCH_TYPE, searchValue, true);
	    
	    fnm.logoutFinanceManager();
	}
	
	private void verifyErrorMessage(){
		String warningMsg = schedulePg.getWarningMsg();
		if(!warningMessage.equalsIgnoreCase(warningMsg)){
			throw new ErrorOnPageException("Error message for search by part of an existing tax schedule Id is not correct," +
					"!expect:"+ warningMessage + ",But actualy is:" + warningMsg);
		}
		logger.info("Check error message for search by part of an existing tax schedule Id pass!");
	}
	
	private void searchBySearchField(String searchType, String searchValue){
		schedulePg.selectSearchType(searchType);
		schedulePg.setTaxSchSearchValue(searchValue);
		schedulePg.clickGo();
		ajax.waitLoading();
		schedulePg.waitLoading();
	}
	
	private void verifySearchValueInColumn(String column, String expectValue, boolean FullMatch){
		boolean passed = true;
		List<String> colList = schedulePg.getColumnByName(column);
		if(colList.size() < 1){
			throw new ItemNotFoundException("Can't find any record by column name:"+column);
		}
		for(int i=0; i<colList.size(); i++){
			String value = "";
			if(column.equalsIgnoreCase(ACCOUNT_SEARCH_TYPE)){
				value = colList.get(i).split(";")[0].trim();
			}else{
				value = colList.get(i).trim();
			}
			if(FullMatch&&(!value.equalsIgnoreCase(expectValue))){
				passed = false;
				logger.error("The " + i + "th value is not correct for " + column + " column fullMatch, expect:" + expectValue + ",but acturally is:"+value);
			}
			if(!FullMatch&&(!value.startsWith(expectValue))){
				passed = false;
				logger.error("The " + i + "th value is not correct for " + column + " column part match, expect start with:" + expectValue + ",but acturally is:"+value);
			}
		}
		if(!passed){
			throw new ErrorOnPageException("Search result for " + column + " is not correct, check the log above!");
		}
		String matchType = FullMatch?"FullMatch ":"PartiallyMatch ";
		logger.info("Search for " + column + " " + matchType +" has passed");
	}
	
	private void verifySearchValueForPrdGrp(String expectValue) {
		boolean passed = true;
		String column = PRODUCT_OR_GROUP_SHOW;
		List<String> colList = schedulePg.getColumnByName(column);
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		for(int i=0; i < colList.size(); i++){
			if(!expectValue.equalsIgnoreCase(colList.get(i))){
				String temp = colList.get(i);
				if(temp.contains("'"))
					temp = temp.replace("'", "''");
				String query = "select g.prd_grp_name as prdGrpName from p_prd_grp g, p_prd p where p.prd_grp_id=g.prd_grp_id and p.prd_name='" + temp + "'";
				String prdgrpname = db.executeQuery(query, "prdgrpname", 0);
				if(!prdgrpname.equalsIgnoreCase(expectValue)){
					passed = false;
					logger.error("The group of the" + i + "th shedule is not correct, expect:" +  expectValue + ",but actual is:"+ prdgrpname);
				}
			}
		}
		if(!passed){
			throw new ErrorOnPageException("Not all the List Tax Schedule with Applicable Product Category is Slip, check log above!");
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.contract = "NC Contract";
	  	login.location = "Administrator - Auto/North Carolina State Parks";
	  	
	  	schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		String facility = fnm.getFacilityName("552903", schema); //Jordan Lake State Rec Area
	  	//initialize Tax info
	  	tax.taxName = "verify_searchResult";
	  	tax.taxCode = "VerifySearchResult";
	  	tax.taxDescription = "verify the search result";
	  	tax.taxRateType ="Percentage";
	  	tax.feeTypes.add("Transaction Fee");
	  	
	  	schedule.location = facility;
	 	schedule.locationCategory = "Park";
	 		 	
	 	schedule.taxName = tax.taxName;
	 	schedule.productCategory = "List"; //This is the test point, can not be changed
	  	schedule.feeType = "Transaction Fee"; 
	  	schedule.appPrdCategory = "Slip";
	  	schedule.product = "EditTaxSchdStatusUsed";
	  	schedule.tranType = "Add to Transfer List";
	  	schedule.startDate = "3/1/2010";
	  	schedule.endDate = "3/31/2010";
	  	schedule.customerType = "Standard";
	  	schedule.accountCode = "2000.1601.000300000; Default Overpayment Deposit";
	  	schedule.rate = "83.83";
	}
}
