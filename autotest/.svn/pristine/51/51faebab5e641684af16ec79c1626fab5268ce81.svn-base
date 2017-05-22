package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.system.liencompany;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrLienCompaniesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify adding an lien company detail info with click Cancel
 * check point: 1. add lien company info with cancel success
 *              2. did not generate lien company record at list
 *              3. lien company name should not existing at filter option
 * @Preconditions:
 * @SPEC: <<Add Lien Company.UCS>>
 * @Task#: AUTO-1015
 * 
 * @author vzhang
 * @Date  Jul 23, 2012
 */
public class AddWithClickCancel extends LicenseManagerTestCase{
	private LienCompanyDetailsInfo lienCompany = new LienCompanyDetailsInfo();

	@Override
	public void execute() {
		lm.deleteLienCompanyDetailInfo(lienCompany.lienCompanyName, schema);
		lm.loginLicenseManager(login);
		lm.gotoSysConfPgFromTopMenu();
		
		lm.gotoLienCompaniesPageFromSysConfigPage();
		//add lien company with new lien company name
		lienCompany.lienCompanyAddrID = lm.addLienCompanyWithNewLienCompanyNameFromCompaniesPageWithClickCancel(lienCompany);
		//should not generate lien company info due to click cancel when add
		this.verifyNotGenerateLienCompanyInfo();
		//Filters option should not existing lien company name due to click cancel when add
		this.verifyLienCompanyNameWhetherExistingAtFiltersOption(lienCompany.lienCompanyName);		
		
		lm.deleteLienCompanyDetailInfo(lienCompany.lienCompanyName, schema);
		lm.logOutLicenseManager();	
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		lienCompany.lienCompanyName = "AddLienCompanyWithCancel";
		lienCompany.address = "AddLienCompanyWithCancel_Qa";
		lienCompany.city = "AddLienCompanyWithCancel_Test";
		lienCompany.state = "California";
		lienCompany.zip = "51423";
		lienCompany.country = "United States";
		lienCompany.contactPhone = "654321";	
		lienCompany.contactfName = "";
		lienCompany.contactlName = "";	
		lienCompany.contactEmail = "Qa_test@reserveamerica.com";
		lienCompany.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		lienCompany.creationLocation = login.location.split("/")[1];
		lienCompany.creationDate = DateFunctions.getToday();
		lienCompany.lastUpdateUser = lienCompany.creationUser;
		lienCompany.lastUpdateLocation = lienCompany.creationLocation;
		lienCompany.lastUpdateDate = lienCompany.creationDate;		
	}
	
	private void verifyNotGenerateLienCompanyInfo(){
		logger.info("Verify not generate lien company info.");
		
		if(lienCompany.lienCompanyAddrID != null){
			throw new ErrorOnPageException("Should not generate lien company address id when add lien company with click Cancel." +
					"But actullay genereate address id = " + lienCompany.lienCompanyAddrID);
		}else{
			logger.info("Not generate lien company address info when add lien company with click Cancel.");
		}
	}
	
	private void verifyLienCompanyNameWhetherExistingAtFiltersOption(String lienCompanyName){
		LicMgrLienCompaniesConfigurationPage lienCompanyPage = LicMgrLienCompaniesConfigurationPage.getInstance();
		
		logger.info("Verify lien company name whether existing at filters option.");
		boolean existing = lienCompanyPage.checkLienCompanyNameExistingAtFiltersOption(lienCompanyName);
		if(existing){
			throw new ErrorOnPageException("Lien company name should not existing at Filters option when add lien company with click Cancel.");
		}else {
			logger.info("Lien company name not existing at Filters option when add lien company with click Cancel.");
		}
	}

}
