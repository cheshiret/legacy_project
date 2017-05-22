package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges;

import java.util.Random;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case used to verify cancel action when add privilege product.
 * @Preconditions:
 * @SPEC:Add Privilege Product.doc
 * @Task#:Auto-672
 * 
 * @author VZhang
 * @Date  Jul 27, 2011
 */
public class AddPrivilege_Cancel extends LicenseManagerTestCase{

	private boolean result = true;
	private Random r= new Random(); 
	
	public void execute() {
		//login in 
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		
		lm.addPrivilegeProduct(privilege,true);
		this.verifyCancelAction();
		
		if(!result){
			throw new ErrorOnPageException("For some check points failed,please check error log.");
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
//		privilege.validDatePrinting = new String[]{};
//		privilege.customerClasses = new String[]{};
//		privilege.status = "Active";
//		privilege.productGroup = "Privileges";
//		privilege.authorizationQuantity = "Return as Individual Privileges";
//		privilege.invType = "None";
//		//If change schema, the following info did not exists, you should update the following privilege info
//		privilege.displayCategory = "MPrivi";
//		privilege.displaySubCategory = "MPrivi_subW";
//		privilege.reportCategory = "jilly-categories";
		
	}
	
	/**
	 * verify cancel action
	 */
	private void verifyCancelAction(){
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();
		
		logger.info("Verify Cancel Action.");
		if(!privilegeListPage.exists()){
			result &= false;
			throw new ErrorOnPageException("Expected page should be privilege list page. Due to cancel added privilege action.");
		}
		
		if(privilegeListPage.isThisPrivilegeExist(privilege.code)){
			result &= false;
			logger.error("This privilege code " + privilege.code + " should not display in privilege list page. " +
					"Due to cancel added privilege action.");
		}		
	}

}
