package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.privilegeinventory;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrInvTypeLicenseYearChangeHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify view inventory type license year info
 * Blocked by DEFECT-33762
 * @Preconditions:
 * @SPEC:View Privilege Inventory type License Year Change History.UIS
 * @Task#:Auto-879

 * @author VZhang
 * @Date Mar 16, 2012
 */
public class ViewInvTypeLicYearChangeHistory extends LicenseManagerTestCase{
	private String priInventoryType = ""; 
	private InventoryTypeLicenseYear invTypeLicenseYear = new InventoryTypeLicenseYear();
	private TimeZone timeZone;
	private List<ChangeHistory> historyList = new ArrayList<ChangeHistory>();
	private LicMgrInvTypeLicenseYearChangeHistoryPage invTypeLicYearChangeHistoryPg = LicMgrInvTypeLicenseYearChangeHistoryPage.getInstance();
	String expectLicenseYearInfo = "";

	@Override
	public void execute() {
		//clear up from DB for inventory type license year
		lm.deleteInvTypeLicenseYearInfoFromDBByInventoryType(schema, invTypeLicenseYear.inventoryType);
		
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeInventoryTypePgFromTopMenu();
		//check and clear up for inventory, just add once when changed schema
		List<String[]> inventoryTypeInfo = lm.queryPrivilegeInventoryTyeByCode(schema, priInventoryType);
		if(inventoryTypeInfo.size()<1){
			//add privilege inventory type
			lm.addPrivilegeInventoryTypeInfo(priInventoryType);
		}
		
		lm.gotoInventoryTypeLicenseYearPageFromInventoryTypePage();
		//add inventory type license year info
		invTypeLicenseYear.id = lm.addInvTypeLicenseYear(invTypeLicenseYear);

		this.initialEidtInfoAndHistoryInfo();
		lm.editInvTypeLicenseYearInfo(invTypeLicenseYear);
		lm.gotoInvTypeLicenseYearChangeHistoryPageFromInvTypeLicenseYearPage(invTypeLicenseYear.id);
		this.verifyChangeHistoryInfo(historyList);
		
		expectLicenseYearInfo = "All (from " + DateFunctions.formatDate(invTypeLicenseYear.issueFromDate, "dd/MM/yyyy") + " to " 
                              + DateFunctions.formatDate(invTypeLicenseYear.issueToDate, "dd/MM/yyyy") + ")";
		this.verifyPrivilegeInventoryInfo(invTypeLicenseYear.inventoryType, expectLicenseYearInfo);
		
		lm.deleteInvTypeLicenseYearInfoFromDBByInventoryType(schema, invTypeLicenseYear.inventoryType);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		timeZone= DataBaseFunctions.getContractTimeZone(schema, "1");
		
		priInventoryType = "History Inv Type Lic Year";
		invTypeLicenseYear.inventoryType = priInventoryType;
		invTypeLicenseYear.licenseYear = "ALL";
		invTypeLicenseYear.issueFromDate = DateFunctions.getDateAfterToday(2,timeZone);
		invTypeLicenseYear.issueToDate =  DateFunctions.getDateAfterToday(30,timeZone);
		invTypeLicenseYear.cost = "12.00";		
	}
	
	public void initialEidtInfoAndHistoryInfo(){
		ChangeHistory history=new ChangeHistory();
		history.object = "Privilege Inventory Type License Year";
		history.action = "Add";
		history.field = "";
		history.oldValue = "";
		history.newValue = "";
		history.user = DataBaseFunctions.getLoginUserName(login.userName);
		history.location = login.location.split("/")[1].trim();
	    history.changeDate = DateFunctions.getToday("E MMM dd yyyy",timeZone);	
	    
		ChangeHistory history1=new ChangeHistory();
		history1.object = history.object;
		history1.action = "Update";
		history1.field = "Issue From Date";		
		history1.oldValue = DateFunctions.formatDate(invTypeLicenseYear.issueFromDate, "E MMM dd yyyy");
		invTypeLicenseYear.issueFromDate = DateFunctions.getDateAfterToday(6, timeZone);
		history1.newValue = DateFunctions.formatDate(invTypeLicenseYear.issueFromDate, "E MMM dd yyyy");
		history1.user = history.user;
		history1.location = history.location;
	    history1.changeDate = history.changeDate;
	    
		ChangeHistory history2=new ChangeHistory();
		history2.object = history.object;
		history2.action = "Update";
		history2.field = "Issue To Date";		
		history2.oldValue = DateFunctions.formatDate(invTypeLicenseYear.issueToDate, "E MMM dd yyyy");
		invTypeLicenseYear.issueToDate = DateFunctions.getDateAfterToday(28, timeZone);
		history2.newValue = DateFunctions.formatDate(invTypeLicenseYear.issueToDate, "E MMM dd yyyy");
		history2.user = history.user;
		history2.location = history.location;
		history2.changeDate = history.changeDate;
	    
		ChangeHistory history3=new ChangeHistory();
		history3.object = history.object;
		history3.action = "Update";
		history3.field = "Cost";		
		history3.oldValue = "$" + invTypeLicenseYear.cost;
		invTypeLicenseYear.cost = "11.00";
		history3.newValue = "$" + invTypeLicenseYear.cost;
		history3.user = history.user;
		history3.location = history.location;
		history3.changeDate = history.changeDate;   
		
		historyList.add(history);
		historyList.add(history1);
		historyList.add(history2);
		historyList.add(history3);
	}
	
	private void verifyPrivilegeInventoryInfo(String expInvType, String expLicYear){
		logger.info("Verify privilege inventory type license year info.");
		boolean result = true;
		String value = invTypeLicYearChangeHistoryPg.getInventoryType();
		if(!value.equals(expInvType)){
			result &= false;
			MiscFunctions.compareResult("Inventory type info", expInvType, value);
		}else{
			logger.info("Inventory type info is correct.");
		}
		
		value = invTypeLicYearChangeHistoryPg.getLicneseYear();
		
		if(!value.equals(expLicYear)){
			result &= false;
			MiscFunctions.compareResult("License Year info", expLicYear, value);
		}else{
			logger.info("License Year info is correct.");
		}
		
		if(!result){
			throw new ErrorOnPageException("Privilege inventory info is not correct.");
		}else{
			logger.info("Privilege inventory info is correct.");
		}
	}

	private void verifyChangeHistoryInfo(List<ChangeHistory> expectHistoryList){
		List<ChangeHistory> actualHistoryListFromUI= new ArrayList<ChangeHistory>();
		logger.info("Verify change history info.");
		
		actualHistoryListFromUI = invTypeLicYearChangeHistoryPg.getHistoriesInformation();
		
		if(actualHistoryListFromUI.size()<expectHistoryList.size()){
			throw new ErrorOnPageException("History List record size is not correct.");
		}
		
		//get actually history list info by expect history list size
		actualHistoryListFromUI = actualHistoryListFromUI.subList(0, expectHistoryList.size());		
		for(int i=0; i<expectHistoryList.size(); i++){
			for(int j=0; i<actualHistoryListFromUI.size(); j++){
				if(expectHistoryList.get(i).field.equals(actualHistoryListFromUI.get(j).field)){
					if(!actualHistoryListFromUI.get(j).equals(expectHistoryList.get(i))){
						throw new ErrorOnPageException("History record about "+ expectHistoryList.get(i).field + " is wrong.");
					}
					break;
				}else {
					if(j == actualHistoryListFromUI.size()-1){
						throw new ErrorOnPageException("History record about "+ expectHistoryList.get(i).field + " should be exists in UI.");
					}
				}
			}
		}
		
	}

}
