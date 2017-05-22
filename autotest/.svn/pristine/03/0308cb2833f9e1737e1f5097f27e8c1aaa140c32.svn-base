package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear.batchadd;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearBatchAddPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 1: verify the value populated in the search control(Copy From Year, New License Year, # of Years To Add)
 * @Preconditions:
 * @SPEC: Add License Year for Privileges in Batch
 * @Task#:AUTO-725
 * 
 * @author bzhang
 * @Date  Aug 23, 2011
 */
public class VerifyBatchSchBoxValues extends LicenseManagerTestCase {
	private boolean pass = true;
	
	//The System shall require the User to specify the new License Year being added, selected from a list of Years starting from the [maximum(should be minimum) License Year in the License Year to Copy From PLUS 1 year], up to [maximum License Year in the License Year to Copy From PLUS 10 years]
	private int minimumLicYearCopyPlus;
	private int maximumLicYearCopyPlus;
	private List<String> licYearInDB = new ArrayList<String>();
	
	public void execute() {
		licYearInDB=lm.queryLicenseYearInfoForPrivilegeProduct(schema);
		
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeLicenseYearBatchAddPageFromTopMenu();
		
		//1: verify the items(value and display order) in the License year copy from year list.
		this.verifyLicenseYearCopyFromItems();    
		
		//2: verify the items in New License Year Being added drop down list.
		this.verifyNewLicenseYearItems();  //(inconsistent with product for now)
		
		//3: verify the items in the #of years drop down list.		
		this.verifyNumOfYearsItems();  
		
		lm.logOutLicenseManager();
		
		if(!pass){
			throw new ErrorOnPageException("the Batch Add License Year Page's search box default value verification failed, please refer to log file for more details info");
		}
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//New Licnese Year Bussiness rule switch value
		minimumLicYearCopyPlus = 1;
		maximumLicYearCopyPlus = 10;
		
	}
	
	/**
	 * get the expect New License Year populated items based on the value Copy From Year items in DB.
	 * follow this rule:
	 * starting from the [Maximum License Year in the License Year to Copy From PLUS 1 year], up to [maximum License Year in the License Year to Copy From PLUS 10 years]
	 * @param minimumPlus
	 * @param maximumPlus
	 * @return
	 */
	private List<String> getExpectNewLicenseYearItems(int minimumPlus, int maximumPlus){
		int newlicenseYearNum = maximumPlus-minimumPlus+1;
		List<String> newlicenseYear = new ArrayList<String>();
		
		int maxCopyYear = Integer.parseInt(licYearInDB.get(0)); //get the license copy from year info in descending order, and get the maximum value
		
		//Initialize new License Year being added
		for (int i = 0; i < newlicenseYearNum; i ++){
			newlicenseYear.add(maxCopyYear + i + 1 + "");
		}
		return newlicenseYear;
	}
	
	/**
	 * verify the items in the New License Year drop down list.
	 * items in the New License Year drop down list should be: [maximum License Year in the License Year to Copy From PLUS 1 year], up to [maximum License Year in the License Year to Copy From PLUS 10 years]
	 */
	private void verifyNewLicenseYearItems(){
		LicMgrPrivilegeLicenseYearBatchAddPage priLicYearBatchAddPg = LicMgrPrivilegeLicenseYearBatchAddPage.getInstance();
		List<String> expectNewlicenseYearItems = this.getExpectNewLicenseYearItems(minimumLicYearCopyPlus, maximumLicYearCopyPlus);
		List<String> actualNewlicenseYearItems = priLicYearBatchAddPg.getNewLicneseYearItems();
		
		//compare data info
		logger.info("Start verifing the data value and display order info in the New LIcense Year being added drop down list");
		if(expectNewlicenseYearItems.equals(actualNewlicenseYearItems)){
			logger.info("verify New License Year being added drop down list items value and display order successfully..");
		}else{
			logger.info("The expect  new license year items is:" + expectNewlicenseYearItems.toString());
			logger.info("The current new license year items is:" + actualNewlicenseYearItems.toString());
			throw new ErrorOnPageException("The expect new license year dropdown list item does not match with expect value..");
		}
	}
	
	/**
	 * verify License Year Copy From items display in descending order, and the value consistent with active license year in DB.
	 */
	private void verifyLicenseYearCopyFromItems(){
		LicMgrPrivilegeLicenseYearBatchAddPage priLicYearBatchAddPg = LicMgrPrivilegeLicenseYearBatchAddPage.getInstance();
		List<String> licYearOnPg = priLicYearBatchAddPg.getCopyFromYearItems();
		
		logger.info("Start verifing items and display order for License Year Copy From drop down list...");
		if (licYearInDB.equals(licYearOnPg)){
			logger.info("verify License Year Copy From drop down list items value and display order successfully..");
		}else{
			logger.info("The expect  copy from items is:" + licYearInDB.toString());
			logger.info("The current copy from items is:" + licYearOnPg.toString());
			throw new ErrorOnPageException("The expect copy from dropdown list item does not match with expect value..");

		}
	}
	
	/**
	 * verify the items in the # of years drop down list, should be integer larger than 0 and less than 11.
	 */
	private void verifyNumOfYearsItems(){
		LicMgrPrivilegeLicenseYearBatchAddPage priLicYearBatchAddPg = LicMgrPrivilegeLicenseYearBatchAddPage.getInstance();
		int numYears[] = priLicYearBatchAddPg.getNumYearsToAddItems();
		boolean flag = true;
		
		if (numYears.length != 10){
			flag = false;
			pass &= false;
			logger.error("The total items number in the # of Years Add drop down list is incorret.");
		} else {
			for(int i =0; i < numYears.length; i ++){
				if (numYears[i] != (i+1)){
					flag = false;
					pass &= false;
					logger.error("The values in the # of Years Add Drop down list is incorrect");
					break;
				}
			}
		}
		if(flag){
			logger.info("verify the # of Years Add drop down list items value and display order successfully..");
		}
		
	}
}
