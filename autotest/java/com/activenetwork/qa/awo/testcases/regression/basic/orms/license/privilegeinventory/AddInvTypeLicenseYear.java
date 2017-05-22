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
 * @Description:This case is used to verify add inventory type license year info
 * @Preconditions:
 * @SPEC:Add Privilege Inventory type License Year.UIS
 * @Task#:Auto-879

 * @author VZhang
 * @Date Mar 13, 2012
 */
public class AddInvTypeLicenseYear extends LicenseManagerTestCase{
	private String priInventoryType = ""; 
	private InventoryTypeLicenseYear invTypeLicenseYear = new InventoryTypeLicenseYear();

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
		//verify inventory type license year info from list 
		this.verifyInvTypeLicenseYearInfoFromList(invTypeLicenseYear);
		//verify inventory type license year info from DB, create user, create location, create date
		this.verifyInvTypeLicenseYearInfoFromDB(schema, invTypeLicenseYear);
		
		//verify inventory type license year info from detail page
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
		TimeZone timeZone= DataBaseFunctions.getContractTimeZone(schema, "1");
		
		priInventoryType = "Add Inv Type License Year";
		
		invTypeLicenseYear.inventoryType = priInventoryType;
		invTypeLicenseYear.licenseYear = "ALL";
		invTypeLicenseYear.issueFromDate = DateFunctions.getDateAfterToday(2, timeZone);
		invTypeLicenseYear.issueToDate =  DateFunctions.getDateAfterToday(30, timeZone);
		invTypeLicenseYear.cost = "12";
		invTypeLicenseYear.createUser = DataBaseFunctions.getLoginUserID(schema, login.userName);
		invTypeLicenseYear.createLocation = lm.getLocationID(schema, login.location.split("/")[1].trim());
		invTypeLicenseYear.createDate = DateFunctions.getToday("yyyy/MM/dd",timeZone);
	}
	
	private void verifyInvTypeLicenseYearInfoFromList(InventoryTypeLicenseYear expInvTypeLicYearInfo){
		LicMgrInventoryTypeLicenseYearPage invTypeLicenseYearPg = LicMgrInventoryTypeLicenseYearPage.getInstance();
		logger.info("Verify Inventory type license year info from list.");
		
		boolean result = invTypeLicenseYearPg.verifyInvTypeLicenseYearInfo(expInvTypeLicYearInfo);
		if(!result){
			throw new ErrorOnDataException("Inventory Type License Year info is not correct. Please check logger info.");
		}		
	}
	
	private void verifyInvTypeLicenseYearInfoFromDB(String schema,InventoryTypeLicenseYear expInvTypeLicYearInfo){
		logger.info("Verify inventory type license year create info form DB.");
		
		boolean result = true;	
		List<String[]> invLicYearInfo = lm.getInvLicenseYearIDAndUserInfo(schema, expInvTypeLicYearInfo);
		if(invLicYearInfo.size()!= 1){
			throw new ErrorOnDataException("Expected should have one inventory type license year info in DB, but actually is not.");
		}
		
		if(!expInvTypeLicYearInfo.createUser.equals(invLicYearInfo.get(0)[1])){
			result &= false;
			MiscFunctions.compareResult("Create user is not correct.", expInvTypeLicYearInfo.createUser, invLicYearInfo.get(0)[1]);
		}else {
			logger.info("Create user is correct.");
		}
		
		if(!expInvTypeLicYearInfo.createLocation.equals(invLicYearInfo.get(0)[2])){
			result &= false;
			MiscFunctions.compareResult("Create location is not correct.", expInvTypeLicYearInfo.createLocation, invLicYearInfo.get(0)[2]);
		}else {
			logger.info("Create location is correct.");
		}
		
		if(DateFunctions.compareDates(expInvTypeLicYearInfo.createDate, invLicYearInfo.get(0)[3]) !=0){
			result &= false;
			MiscFunctions.compareResult("Create date is not correct.", expInvTypeLicYearInfo.createDate, invLicYearInfo.get(0)[3]);
		}else {
			logger.info("Create date is correct.");
		}
		
		if(!result){
			throw new ErrorOnDataException("Inventory Type License Year create info is not correct from DB. Please check logger info.");
		}else {
			logger.info("Inventory type license year info is correct from DB.");
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
