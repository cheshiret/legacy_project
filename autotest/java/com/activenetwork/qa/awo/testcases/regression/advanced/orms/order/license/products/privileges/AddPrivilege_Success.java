package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrCreateNewPriviledgePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditDisplayOrderPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case used to verify add privilege success and exits error message, 
 * and display order error message
 * @Preconditions:Blocked by DEFECT-32143
 * @SPEC:Add Privilege Product.doc
 * @Task#:Auto-672
 * 
 * @author VZhang
 * @Date  Jul 27, 2011
 */
public class AddPrivilege_Success extends LicenseManagerTestCase{

	private boolean result = true;
	private Random r= new Random(); 
	private String createOrRevenueLocation = "", createDate = "", errorMessage1, errorMessage2;
	private List<String[]> privilegeCreateInfoFromDB = new ArrayList<String[]>();
	private LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();
	
	public void execute() {
		//login in 
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		
		lm.addPrivilegeProduct(privilege);
		this.verifyAddedSuccess();
		
		//display order is not greater than 0
		privilege.displayOrder = "0";
		lm.editDisplayOrderForPrivilege(privilege.code, privilege.displayOrder);
		this.verifyDisplayOrderErrorMessage(errorMessage1);//Spec:Message8
		
		privilege.displayOrder = "5";
		lm.editDisplayOrderForPrivilege(privilege.code, privilege.displayOrder);
		this.verifyEditDisplayOrderSuccess();
		
		//add a exists privilege info
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage2);//Spec:Message3
		
		if(!result){
			throw new ErrorOnPageException("For csome check points failed,please check error log.");
		}
		
		lm.logOutLicenseManager();		
	}
	
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		privilege.code = "V" + String.valueOf(r.nextInt(99));
		privilege.name = privilege.code + " QA Auto";
		privilege.customerClasses = new String[]{"Individual"};
		privilege.validFromDateCalculation = "Prompt for Valid From Date and Time";
		privilege.promptIndicator = "Per Privilege";
		privilege.validToDateCalculation = "Valid To Date of Previous License plus Valid Days/Years";
		privilege.validDaysYears = String.valueOf(DateFunctions.getCurrentYear());
		privilege.dateUnitOfValidToDate = "Years";
		privilege.renewalDays = "2";
		privilege.validDatePrinting = new String[]{};
		privilege.customerClasses = new String[]{"Individual"};
		privilege.status = "Active";
		privilege.productGroup = "Privileges";
		privilege.authorizationQuantity = "Return as Individual Privileges";
		privilege.invType = "None";
		//If change schema, the following info did not exists, you should update the following privilege info
		privilege.displayCategory = "Lifetime";
		privilege.displaySubCategory = "Annual";
		privilege.reportCategory = "Lifetime Licenses";
		privilege.validToAge = "0";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		createOrRevenueLocation = login.location.split("/")[1];
		createDate = DateFunctions.getToday("yyyyMMdd");
		
		errorMessage1 = "The Display Order entered is not valid. Please enter an integer value greater than 0.";
		errorMessage2 = "The Privilege Product Code entered already exists. The Code must be unique.";		
	}
	
	/**
	 * Verify added success
	 * compare privilege info and create info
	 */
	private void verifyAddedSuccess(){
		LicMgrPrivilegeProductDetailsPage privilegeProductDetailPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		
		logger.info("Verify Add Success.");
		if(!privilegeListPage.exists()){
			result &= false;
			logger.error("Expected page should be privilege list page. Due to added privilege success.");
		}
		
		if(!privilegeListPage.isThisPrivilegeExist(privilege.code)){
			result &= false;
			logger.error("This privilege code " + privilege.code + " should be display in privilege list page. " +
					"Due to added privilege success.");
		}
		
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);		
		result &= privilegeProductDetailPg.comparePrivilegeInfoIsCorrect(privilege);//compare privilege info
		
		privilegeProductDetailPg.clickOk();
		privilegeListPage.waitLoading();	
		
		//verify create info
		privilegeCreateInfoFromDB = lm.getPrivilegeProductCreateInfo(privilege.code, schema);
		if(privilegeCreateInfoFromDB.size() !=1){
			result &= false;
			logger.error("The privilege create record should be one.");
		}else {
			if(!privilegeCreateInfoFromDB.get(0)[1].equals(login.userName)){//verify create user
				result &= false;
				logger.error("Creat user should be " + login.userName 
						+ ", but acutally is " +privilegeCreateInfoFromDB.get(0)[1]);
			}
			if(!privilegeCreateInfoFromDB.get(0)[2].equals(createOrRevenueLocation)){//verify create or revenue location
				result &= false;
				logger.error("Create or revenue location name should be " + createOrRevenueLocation + 
						", but acutally is privilegeCreateInfoFromDB.get(0)[2]");
			}
			if(!privilegeCreateInfoFromDB.get(0)[3].equals(createDate)){//verify create date
				result &= false;
				logger.error("Create date should be " + createDate + 
						", but acutally is " + privilegeCreateInfoFromDB.get(0)[3]);
			}
		}
	}
	
	//verify display order error message
	private void verifyDisplayOrderErrorMessage(String expectedErrorMessage){
		LicMgrPrivilegeEditDisplayOrderPage privilegeEditDisplayOrderPage = 
			LicMgrPrivilegeEditDisplayOrderPage.getInstance();
		if(!privilegeEditDisplayOrderPage.exists()){
			result &= false;
			throw new ErrorOnPageException("Expected page should be edit privilege page and expected error message.");
		}
		
		String actualMessage = privilegeEditDisplayOrderPage.getErrorMessage();
		
		if(!actualMessage.equals(expectedErrorMessage)){
			result &= false;
			logger.error("Expected error message should be " + expectedErrorMessage + 
					", but actually is " + actualMessage);
		}
		
		privilegeEditDisplayOrderPage.clickCancle();
		privilegeListPage.waitLoading();		
	}	
	
	//verify edit display order success
	private void verifyEditDisplayOrderSuccess(){
		if(!privilegeListPage.exists()){
			result &= false;
			throw new ErrorOnPageException("Expected page should be privilege list page, because edit display order success.");
		}
		
		if(!privilege.displayOrder.equals(privilegeListPage.getPrivilegeListInfoByColumnName(privilege.code,"Display Order"))){
			result &= false;
			logger.error("Edit display order failed.");
		}		
	}
	
	/**
	 * Verify error message
	 * @param expectedMessage
	 */
	private void verifyErrorMessage(String expectedMessage){	
		LicMgrCreateNewPriviledgePage createNewPrivilegeProductPage = LicMgrCreateNewPriviledgePage.getInstance();
		
		if(!createNewPrivilegeProductPage.exists()){
			result &= false;
			throw new ErrorOnPageException("Expected page should be add privilege page which have error message.");
		}
		
		String actualMessage = createNewPrivilegeProductPage.getWarningMessage();
		if(!actualMessage.equals(expectedMessage)){
			result &= false;
			logger.error("Expected error message should be " + expectedMessage 
					+ ", but actual error message is " + actualMessage);
		}
		
		createNewPrivilegeProductPage.clickCancel();
		privilegeListPage.waitLoading();
	}
}
