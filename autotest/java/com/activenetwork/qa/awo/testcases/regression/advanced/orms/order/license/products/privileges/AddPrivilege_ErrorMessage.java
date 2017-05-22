package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges;

import java.util.Random;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrCreateNewPriviledgePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case used to verify error message when add privilege product, 
 * This case have DEFECT-30235, but closed, did not fix now, and block by DEFECT-30236 
 * @Preconditions:
 * @SPEC:Add Privilege Product.doc
 * @Task#:Auto-672
 * 
 * @author VZhang
 * @Date  Jul 27, 2011
 */
public class AddPrivilege_ErrorMessage extends LicenseManagerTestCase{

	private boolean result = true;
	private Random r= new Random(); 
	private String errorMessage1,errorMessage2,errorMessage3,errorMessage4,
	errorMessage5,errorMessage6,errorMessage7,errorMessage8,
	errorMessage9,errorMessage10,errorMessage11;
	private LicMgrCreateNewPriviledgePage createNewPrivilegeProductPage = LicMgrCreateNewPriviledgePage.getInstance();
	private LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();

	public void execute() {
		//login in 
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		
		//privilege code is not specified
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage1);// Spec:Message1
		
		//privilege code is more than three alphanumeric characters
		privilege.code = "aaaa";
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage2);// Spec:Message2
		
		//privilege code is more than three alphanumeric characters
		privilege.code = "9999";
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage2);// Spec:Message2
		
		//privilege code is more than three alphanumeric characters
		privilege.code = "aa99";
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage2);// Spec:Message2
		
		//privilege name is not specified
		privilege.code = "V" + String.valueOf(r.nextInt(99));
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage3);// Spec:Message4
		
		//customer class is not specified
		privilege.name = privilege.code + " QA Auto";
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage4);// Spec:Message10
		
		//prompt indicator is not specified
		privilege.customerClasses = new String[]{"Individual"};
		privilege.validFromDateCalculation = "Prompt for Valid From Date";
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage5);// Spec:Message14
		
		//prompt indicator is not specified
		privilege.validFromDateCalculation = "Prompt for Valid From Date and Time";
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage5);// Spec:Message14
		
		//valid years or valid days is not specified
		privilege.promptIndicator = "Per Privilege";
		privilege.validToDateCalculation = "Valid From Date plus Valid Days/Years";
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage6);//Spec:Message16
		
		//valid years or valid days is not greater than 0
		privilege.validDaysYears = "-1";
		privilege.dateUnitOfValidToDate = "Years";
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage7);//Spec:Message19
		
		//valid years or valid days is not specified
		privilege.validDaysYears = "";
		privilege.dateUnitOfValidToDate = "";
		privilege.renewalDays = "2";
		privilege.validToDateCalculation = "Valid To Date of Previous License plus Valid Days/Years";
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage6);//Spec:Message16
		
		//valid years or valid days is not greater than 0
		privilege.validDaysYears = "-1";
		privilege.dateUnitOfValidToDate = "Years";
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage7);//Spec:Message19
		
		//renewal is not specified
		privilege.renewalDays = "";
		privilege.validDaysYears = String.valueOf(DateFunctions.getCurrentYear());
		privilege.dateUnitOfValidToDate = "Years";
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage8);//Spec:Message17
		
		//renewal is not greater than 0
		privilege.renewalDays = "-1";
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage9);//Spec:Message20
		
		//age is not specified
		privilege.validToDateCalculation = "Expires when the Customer reaches Valid To Age";
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage10);//Spec:Message18
		
		//age is not greater than 0
		privilege.validToAge = "-1";
		lm.addPrivilegeProduct(privilege);
		this.verifyErrorMessage(errorMessage11);//Spec:Message21
		
		if(!result){
			throw new ErrorOnPageException("For some check points failed,please check error log.");
		}
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		errorMessage1 = "The Privilege Product Code is required. Please specify the Code.";
		errorMessage2 = "The Privilege Product Code entered is not valid. Please enter a maximum of three alphanumeric characters.";
		errorMessage3 = "The Privilege Product Name is required. Please specify the Name.";
		errorMessage4 = "At least one Customer Class must be selected. Please select the Customer Class from the list provided.";
		errorMessage5 = "The indicator whether to prompt Per Privilege or Per Transaction is required. Please specify.";
		errorMessage6 = "The Valid Days or Valid Years is required. Please specify the Valid Days or Valid Years.";
		errorMessage7 = "The Valid Days or Valid Years entered is not valid. Please enter an integer value greater than 0.";
		errorMessage8 = "The Renewal Days is required. Please specify the Renewal Days.";
		errorMessage9 = "The Renewal Days entered is not valid. Please enter an integer value greater than 0.";
		errorMessage10 = "The Valid To Age is required. Please specify the Valid To Age.";
		errorMessage11 = "The Valid To Age entered is not valid. Please enter an integer value greater than 0.";
	}
	
	/**
	 * Verify error message
	 * @param expectedMessage
	 */
	private void verifyErrorMessage(String expectedMessage){		
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
