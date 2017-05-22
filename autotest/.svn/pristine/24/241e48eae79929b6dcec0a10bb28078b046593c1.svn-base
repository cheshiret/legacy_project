package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.privilegeinventory;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrEditInvTypeLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrInventoryTypeLicenseYearPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify edit inventory type license year info
 * @Preconditions:
 * @SPEC:Edit Privilege Inventory type License Year.UIS
 * @Task#:Auto-879

 * @author VZhang
 * @Date Mar 13, 2012
 */
public class EditInvTypeLicenseYear extends LicenseManagerTestCase{
	private String priInventoryType = ""; 
	private InventoryTypeLicenseYear invTypeLicenseYear = new InventoryTypeLicenseYear();
	private TimeZone timeZone;

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

		invTypeLicenseYear.issueFromDate = DateFunctions.getDateAfterToday(6, timeZone);
		invTypeLicenseYear.issueToDate = DateFunctions.getDateAfterToday(28, timeZone);
		invTypeLicenseYear.cost = "11";
		lm.editInvTypeLicenseYearInfo(invTypeLicenseYear);
		
		this.verifyInvTypeLicenseYearInfoFromDB(schema, invTypeLicenseYear);
		this.verifyInvTypeLicenseYearInfoFromList(invTypeLicenseYear);
		
		lm.gotoEditInvTypeLicenseYearPageFromInvTypeLicYearList(invTypeLicenseYear.id);
		this.verifyInvTypeLicenseYearInfoFromDetail(invTypeLicenseYear);
		lm.gotoInvTypeLicenseYearPageFromEidtInvTypeLicenseYearPage();
		
		//clear up
		lm.deleteInvTypeLicenseYearInfoFromDBByInventoryType(schema, invTypeLicenseYear.inventoryType);
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		timeZone= DataBaseFunctions.getContractTimeZone(schema, "1");
		
		priInventoryType = "Ed Inv Type License Year";
		
		invTypeLicenseYear.inventoryType = priInventoryType;
		invTypeLicenseYear.licenseYear = "ALL";
		invTypeLicenseYear.issueFromDate = DateFunctions.getDateAfterToday(2, timeZone);
		invTypeLicenseYear.issueToDate =  DateFunctions.getDateAfterToday(30, timeZone);
		invTypeLicenseYear.cost = "12";
		invTypeLicenseYear.lastUpdateUser = DataBaseFunctions.getLoginUserID(schema, login.userName);
		invTypeLicenseYear.lastUpdateLocation = lm.getLocationID(schema, login.location.split("/")[1].trim());
		invTypeLicenseYear.lastUpdateDate = DateFunctions.getToday("yyyy/MM/dd",timeZone);		
	}
	
	private void verifyInvTypeLicenseYearInfoFromDB(String schema, InventoryTypeLicenseYear expInvTypeLicYearInfo){
		logger.info("Verify inventory type license year create info form DB.");
		
		boolean result = true;	
		List<String[]> invLicYearInfo = lm.getInvLicenseYearIDAndUserInfo(schema, expInvTypeLicYearInfo);
		if(invLicYearInfo.size()!= 1){
			throw new ErrorOnDataException("Expected should have one inventory type license year info in DB, but actually is not.");
		}
		
		if(!expInvTypeLicYearInfo.lastUpdateUser.equals(invLicYearInfo.get(0)[4])){
			result &= false;
			MiscFunctions.compareResult("Last update user is not correct.", expInvTypeLicYearInfo.lastUpdateUser, invLicYearInfo.get(0)[4]);
		}else {
			logger.info("Last update user is correct.");
		}
		
		if(!expInvTypeLicYearInfo.lastUpdateLocation.equals(invLicYearInfo.get(0)[5])){
			result &= false;
			MiscFunctions.compareResult("Last update location is not correct.", expInvTypeLicYearInfo.lastUpdateLocation, invLicYearInfo.get(0)[5]);
		}else {
			logger.info("Last update location is correct.");
		}
		
		if(DateFunctions.compareDates(expInvTypeLicYearInfo.lastUpdateDate, invLicYearInfo.get(0)[6]) !=0){
			result &= false;
			MiscFunctions.compareResult("Last udpate date is not correct.", expInvTypeLicYearInfo.lastUpdateDate, invLicYearInfo.get(0)[6]);
		}else {
			logger.info("Last update date is correct.");
		}
		
		if(!result){
			throw new ErrorOnDataException("Inventory Type License Year update info is not correct from DB. Please check logger info.");
		}else {
			logger.info("Inventory type license year info is correct from DB.");
		}
	}
	
	private void verifyInvTypeLicenseYearInfoFromList(InventoryTypeLicenseYear expInvTypeLicenseYearInfo){
		LicMgrInventoryTypeLicenseYearPage invTypeLicenseYearPg = LicMgrInventoryTypeLicenseYearPage.getInstance();
		logger.info("Verify Inventory type license year info from list.");
		
		boolean result = invTypeLicenseYearPg.verifyInvTypeLicenseYearInfo(expInvTypeLicenseYearInfo);
		if(!result){
			throw new ErrorOnDataException("Inventory Type License Year info is not correct. Please check logger info.");
		}
	}
	
	private void verifyInvTypeLicenseYearInfoFromDetail(InventoryTypeLicenseYear expInvTypeLicenseYearInfo){
		LicMgrEditInvTypeLicenseYearWidget eidtInvTypeLicenseYearPg = LicMgrEditInvTypeLicenseYearWidget.getInstance();
		logger.info("Verify inventory type license year info from detail page.");
		
		boolean result = eidtInvTypeLicenseYearPg.verifyInventoryTypeLicenseYearInfo(expInvTypeLicenseYearInfo);
		if(!result){
			throw new ErrorOnDataException("Inventory type license year info is not correct in detail page. Please check logger info.");
		}else{
			logger.info("Inventory type license year info is correct in detail page.");
		}
	}

}
