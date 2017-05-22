package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.system.liencompany;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrLienCompaniesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify edit an lien company detail info with click Cancel
 * check point: 1. edit lien company info with cancel success
 *              2. did not generate edit lien company record at list
 *              3. older lien company info should  existing at list
 *              4. the older lien company name should existing at filter option
 *              5. the edit lien company name should not existing at filter option
 * @Preconditions:
 * @SPEC: <<Edit Lien Company.UCS>>
 * @Task#: AUTO-1015
 * 
 * @author vzhang
 * @Date  Jul 23, 2012
 */
public class Edit_Cancel extends LicenseManagerTestCase{
	private LienCompanyDetailsInfo lienCompany = new LienCompanyDetailsInfo();
	private LienCompanyDetailsInfo editLienCompany = new LienCompanyDetailsInfo();
	private	LicMgrLienCompaniesConfigurationPage lienCompanyPage = LicMgrLienCompaniesConfigurationPage.getInstance();

	@Override
	public void execute() {
		lm.deleteLienCompanyDetailInfo(lienCompany.lienCompanyName, schema);
		lm.deleteLienCompanyDetailInfo(editLienCompany.lienCompanyName, schema);
		lm.loginLicenseManager(login);
		lm.gotoSysConfPgFromTopMenu();
		
		lm.gotoLienCompaniesPageFromSysConfigPage();
		//add lien company with new lien company name
		lienCompany.lienCompanyAddrID = lm.addLienCompanyWithNewLienCompanyNameFromCompaniesPageWithClickOk(lienCompany);
		editLienCompany.lienCompanyAddrID = lienCompany.lienCompanyAddrID;
		//go to lien company detail page
		lm.gotoLienCompanyDetailPageFromLienCompaniesPage(editLienCompany.lienCompanyAddrID);
		lm.editLienCompanyDetailInfoWithClickCancel(editLienCompany);
		//verify edit lien company info at list page, should not existing
		this.verifyEditLienCompanyInfoNotExistingFromList();
		//verify older lien company info should existing at list page
		this.verifyOlderLienCompanyInfoExistingFromList();
		//verify edit lien company name should not existing at filter option
		//and the older lien company name should existing filter option 
		this.verifyLienCompanyNameWhetherExistingAtFiltersOption();
		
		//clear up
		lm.deleteLienCompanyDetailInfo(lienCompany.lienCompanyName, schema);
		lm.logOutLicenseManager();	
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		lienCompany.lienCompanyName = "LienCompanyNameForEditCancel";
		lienCompany.address = "LienCompanyNameForEditCancel_Qa";
		lienCompany.city = "LienCompanyNameForEditCancel_Test";
		lienCompany.state = "California";
		lienCompany.zip = "51423";
		lienCompany.country = "United States";
		lienCompany.contactPhone = "654321";
		
		editLienCompany.lienCompanyName = "EditLienCompanyNameCancel";
		editLienCompany.address = "EditLienCompanyNameCancel_Qa";
		editLienCompany.city = "EditLienCompanyNameCancel_Test";
		editLienCompany.state = "Mississippi";
		editLienCompany.zip = "23451";
		editLienCompany.country = "United States";
		editLienCompany.contactPhone = "123456";	
		editLienCompany.contactfName = "Qa";
		editLienCompany.contactlName = "Test";	
		editLienCompany.contactEmail = "Qa_test@reserveamerica.com";
	}
	
	/**
	 * Use the edit info to get lien company address id from list page.
	 * If the lien company address id is not existing, that means the edit with click cancel success
	 */
	private void verifyEditLienCompanyInfoNotExistingFromList(){		
		logger.info("Verify Edit Lien Company Info not existing from List.");

		editLienCompany.lienCompanyAddrID = lienCompanyPage.getLienCompanyAddrID(editLienCompany);
		if(editLienCompany.lienCompanyAddrID != null){
			throw new ErrorOnPageException("After edit with click cancel the edit lien company info should not existing at list page, " +
					"but actullay the edit lien company info existing id = " + editLienCompany.lienCompanyAddrID);
		}else{
			logger.info("After edit with click cancel the edit lien company info not existing at list page");
		}
	}
	
	/**
	 * Use the oder info to get lien company address id from list page.
	 * If the lien company address id is existing and same as before, that means the older info is existing at list page
	 */
	private void verifyOlderLienCompanyInfoExistingFromList(){
		logger.info("Verify older Lien Company Info from List. Should not existing.");
		
		String olderAddressId = lienCompany.lienCompanyAddrID;
		lienCompany.lienCompanyAddrID = lienCompanyPage.getLienCompanyAddrID(lienCompany);
		boolean result = MiscFunctions.compareResult("Lien Company Address ID after edit", olderAddressId, lienCompany.lienCompanyAddrID);
		if(!result){
			throw new ErrorOnPageException("Older Lien company address ID not correct after edit with click cancel. please check log.");
		}else {
			logger.info("Older Lien company address ID correct after edit with click cancel.");
		}
	}
	
	private void verifyLienCompanyNameWhetherExistingAtFiltersOption(){
		LicMgrLienCompaniesConfigurationPage lienCompanyPage = LicMgrLienCompaniesConfigurationPage.getInstance();
		
		logger.info("Verify lien company name whether existing at filters option.");
		
		List<String> filterOptionValues = lienCompanyPage.getFiltersOptionValues();
		if(filterOptionValues.contains(editLienCompany.lienCompanyName) || !filterOptionValues.contains(lienCompany.lienCompanyName)){
			throw new ErrorOnPageException("The edit lien company name should not existing at Filters option when after edit lien company with click cancel successfully.\n" + 
					"The older lien company name should existing at Filters option");
		}else{
			logger.info("The edit lien company name not existing at Filters option when after edit lien company with click cancel successfully.\n" +
					"The older lien company name should existing at Filters option");
		}
	}

}
