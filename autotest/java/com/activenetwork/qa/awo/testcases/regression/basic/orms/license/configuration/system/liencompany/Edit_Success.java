package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.system.liencompany;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrLienCompaniesConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrLienCompanyDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify edit an lien company detail info successfully
 *  check point: 1. edit successfully
 *               2. older lien company info should not existing at list
 *               3. edit lien company info should existing at list
 *               4. older lien company name should not existing at filter option
 *               5. edit lien company name should existing at filter option
 *               6. verify edit lien company detail info at detail page
 * @Preconditions:
 * @SPEC: <<Edit Lien Company.UCS>>
 * @Task#: AUTO-1015
 * 
 * @author vzhang
 * @Date  Jul 23, 2012
 */

/**
 * 
 * @Description: This case is used to verify edit an lien company detail info with success
 * check point: 1. edit lien company info with success with update lien company name
 *              2. generate edit lien company record at list
 *              3. older lien company info should not existing at list
 *              4. the older lien company name should not existing at filter option
 *              5. the edit lien company name should existing at filter option
 * @Preconditions:
 * @SPEC: <<Edit Lien Company.UCS>>
 * @Task#: AUTO-1015
 * 
 * @author vzhang
 * @Date  Jul 23, 2012
 */
public class Edit_Success extends LicenseManagerTestCase{
	private LienCompanyDetailsInfo lienCompany = new LienCompanyDetailsInfo();
	private LienCompanyDetailsInfo editLienCompany = new LienCompanyDetailsInfo();
	private LicMgrLienCompanyDetailPage lienCompanyDetailPage = LicMgrLienCompanyDetailPage.getInstance();
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
		lm.editLienCompanyDetailInfoWithClickOK(editLienCompany);
		//verify edit lien company info at list page
		this.verifyEditLienCompanyInfoFromList();
		//verify older lien company info should not existing at list page
		this.verifyOlderLienCompanyInfoNotExistingFromList();
		//verify the older lien company name should not existing filter option 
		//and edit lien company name should existing at filter option
		this.verifyLienCompanyNameWhetherExistingAtFiltersOption();
		
		lm.gotoLienCompanyDetailPageFromLienCompaniesPage(editLienCompany.lienCompanyAddrID);
		//compare edit lien company detail info from lien company detail page
		lienCompanyDetailPage.compareLienCompanyDetailInfo(editLienCompany);
		
		lm.deleteLienCompanyDetailInfo(editLienCompany.lienCompanyName, schema);
		lm.logOutLicenseManager();			
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		lienCompany.lienCompanyName = "LienCompanyNameForEdit";
		lienCompany.address = "LienCompanyNameForEdit_Qa";
		lienCompany.city = "LienCompanyNameForEdit_Test";
		lienCompany.state = "California";
		lienCompany.zip = "51423";
		lienCompany.country = "United States";
		lienCompany.contactPhone = "654321";
		
		editLienCompany.lienCompanyName = "EditLienCompanyName";
		editLienCompany.address = "EditLienCompanyName_Qa";
		editLienCompany.city = "EditLienCompanyName_Test";
		editLienCompany.state = "Mississippi";
		editLienCompany.zip = "23451";
		editLienCompany.country = "United States";
		editLienCompany.contactPhone = "123456";	
		editLienCompany.contactfName = "Qa";
		editLienCompany.contactlName = "Test";	
		editLienCompany.contactEmail = "Qa_test@reserveamerica.com";
		editLienCompany.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		editLienCompany.creationLocation = login.location.split("/")[1];
		editLienCompany.creationDate = DateFunctions.getToday();
		editLienCompany.lastUpdateUser = editLienCompany.creationUser;
		editLienCompany.lastUpdateLocation = editLienCompany.creationLocation;
		editLienCompany.lastUpdateDate = editLienCompany.creationDate;		
	}
	
	/**
	 * Use the edit info to get lien company address id from list page.
	 * If the lien company address id is same as before, that means the edit info is correct at list page
	 */
	private void verifyEditLienCompanyInfoFromList(){		
		logger.info("Verify Edit Lien Company Info from List.");

		editLienCompany.lienCompanyAddrID = lienCompanyPage.getLienCompanyAddrID(editLienCompany);
		boolean result = MiscFunctions.compareResult("Lien Company Address ID after edit", lienCompany.lienCompanyAddrID, editLienCompany.lienCompanyAddrID);
		if(!result){
			throw new ErrorOnPageException("Lien company address ID not correct after edit. please check log.");
		}else {
			logger.info("Lien company address ID correct after edit.");
		}
	}
	
	/**
	 * Use the oder info to get lien company address id from list page.
	 * If the lien company address id is not existing, that means the older info is not existing at list page
	 */
	private void verifyOlderLienCompanyInfoNotExistingFromList(){
		logger.info("Verify older Lien Company Info from List. Should not existing.");
		
		lienCompany.lienCompanyAddrID = lienCompanyPage.getLienCompanyAddrID(lienCompany);
		if(lienCompany.lienCompanyAddrID != null){
			throw new ErrorOnPageException("After edit the older lien company info should not existing at list page, " +
					"but actullay the older lien company info existing id = " + lienCompany.lienCompanyAddrID);
		}else{
			logger.info("After edit the older lien company info not existing at list page");
		}
	}
	
	private void verifyLienCompanyNameWhetherExistingAtFiltersOption(){
		LicMgrLienCompaniesConfigurationPage lienCompanyPage = LicMgrLienCompaniesConfigurationPage.getInstance();
		
		logger.info("Verify lien company name whether existing at filters option.");
		
		List<String> filterOptionValues = lienCompanyPage.getFiltersOptionValues();
		if(filterOptionValues.contains(lienCompany.lienCompanyName) || !filterOptionValues.contains(editLienCompany.lienCompanyName)){
			throw new ErrorOnPageException("The older lien company name should not existing at Filters option when after edit lien company successfully.\n" +
					"The edit lien company name should existing at filters option.");
		}else{
			logger.info("The older lien company name not existing at Filters option when after edit lien company successfully.\n"+
					"The edit lien company name existing at filters option.");
		}
	}

}
