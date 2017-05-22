package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.system.liencompany;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrLienCompaniesConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrLienCompanyDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify adding an lien company detail info, through by new lien company name
 * check point: 1. add lien company info with new lien company name
 *              2. generate lien company record at list
 *              3. lien company name should existing at filter option
 *              4. compare lien company detail info at detail page
 * @Preconditions: Blocked by DEFECT-35770
 * @SPEC: <<Add Lien Company.UCS>>
 * @Task#: AUTO-1015
 * 
 * @author vzhang
 * @Date  Jul 23, 2012
 */
public class Add_NewLienCompanyName extends LicenseManagerTestCase{
	private LienCompanyDetailsInfo lienCompany = new LienCompanyDetailsInfo();
	private LicMgrLienCompanyDetailPage lienCompanyDetailPage = LicMgrLienCompanyDetailPage.getInstance();

	@Override
	public void execute() {
		lm.deleteLienCompanyDetailInfo(lienCompany.lienCompanyName, schema);
		lm.loginLicenseManager(login);
		lm.gotoSysConfPgFromTopMenu();
		
		lm.gotoLienCompaniesPageFromSysConfigPage();
		//add lien company with new lien company name
		lienCompany.lienCompanyAddrID = lm.addLienCompanyWithNewLienCompanyNameFromCompaniesPageWithClickOk(lienCompany);
		//lien company name should existing at Filters Option
		this.verifyLienCompanyNameWhetherExistingAtFiltersOption(lienCompany.lienCompanyName);
		//go to lien company detail page
		lm.gotoLienCompanyDetailPageFromLienCompaniesPage(lienCompany.lienCompanyAddrID);
		//compare lien company detail info from lien company detail page
		lienCompanyDetailPage.compareLienCompanyDetailInfo(lienCompany);
		
		lm.deleteLienCompanyDetailInfo(lienCompany.lienCompanyName, schema);
		lm.logOutLicenseManager();	
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		lienCompany.lienCompanyName = "NewLienCompanyName";
		lienCompany.address = "NewLienCompanyName_Qa";
		lienCompany.city = "NewLienCompanyName_Test";
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
	
	private void verifyLienCompanyNameWhetherExistingAtFiltersOption(String lienCompanyName){
		LicMgrLienCompaniesConfigurationPage lienCompanyPage = LicMgrLienCompaniesConfigurationPage.getInstance();
		
		logger.info("Verify lien company name whether existing at filters option.");
		boolean existing = lienCompanyPage.checkLienCompanyNameExistingAtFiltersOption(lienCompanyName);
		if(!existing){
			throw new ErrorOnPageException("Lien company name '" + lienCompanyName + "' should existing at Filters option when add lien company successfully.");
		}else {
			logger.info("Lien company name existing at Filters option when add lien company successfully..");
		}
	}

}
