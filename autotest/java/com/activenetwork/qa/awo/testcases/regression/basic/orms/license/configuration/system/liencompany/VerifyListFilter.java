package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.system.liencompany;

import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrLienCompaniesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify view lien company list info
 * check point: 1. list info is correct after add success
 *              2. display corresponding lien company info after filter
 *              3. lien company list default sorting
 *              4. lien company filter option sorting
 * @Preconditions:Blocked by DEFECT-35770;DEFECT-35811
 * @SPEC: <<View Lien Company List.UCS>>
 * @Task#: AUTO-1015
 * 
 * @author vzhang
 * @Date  Jul 23, 2012
 */
public class VerifyListFilter extends LicenseManagerTestCase{
	private LienCompanyDetailsInfo diffLienCompanyName = new LienCompanyDetailsInfo();
	private LienCompanyDetailsInfo sameLienCompanyNameForNew = new LienCompanyDetailsInfo();
	private LienCompanyDetailsInfo sameLienCompanyNameForSelect = new LienCompanyDetailsInfo();
	private	LicMgrLienCompaniesConfigurationPage lienCompanyPage = LicMgrLienCompaniesConfigurationPage.getInstance();

	@Override
	public void execute() {
		lm.deleteLienCompanyDetailInfo(diffLienCompanyName.lienCompanyName, schema);
		lm.deleteLienCompanyDetailInfo(sameLienCompanyNameForNew.lienCompanyName, schema);
		lm.deleteLienCompanyDetailInfo(sameLienCompanyNameForSelect.lienCompanyName, schema);
		
		lm.loginLicenseManager(login);
		lm.gotoSysConfPgFromTopMenu();
		lm.gotoLienCompaniesPageFromSysConfigPage();
		//add lien company with new lien company name
		sameLienCompanyNameForNew.lienCompanyAddrID = lm.addLienCompanyWithNewLienCompanyNameFromCompaniesPageWithClickOk(sameLienCompanyNameForNew);
		//add lien company with new lien company name
		sameLienCompanyNameForSelect.lienCompanyAddrID = lm.addLienCompanyWithSelectLienCompanyNameFromCompaniesPageWithClickOK(sameLienCompanyNameForSelect);
		//add lien company with new lien company name
		diffLienCompanyName.lienCompanyAddrID = lm.addLienCompanyWithNewLienCompanyNameFromCompaniesPageWithClickOk(diffLienCompanyName);

		//verify lien company list default sorting, by lien company name case-insensitive ascending
		List<String> actualLienCompanyNameColumnList = lienCompanyPage.getLienCompanyNameColumnValues();
		List<String> expLienCompanyNameNameColumnList = lienCompanyPage.getLienCompanyNameColumnValues();
		this.verifySortingOfLienCompanyName(actualLienCompanyNameColumnList,expLienCompanyNameNameColumnList);
		
		//verify lien company filter option sorting, by lien company name case-insensitive ascending
		List<String> actLienCompanyFilterOptionList = lienCompanyPage.getFiltersOptionValues();
		List<String> expLienCompanyFilterOptionList = lienCompanyPage.getFiltersOptionValues();
		this.verifySortingOfLienCompanyName(actLienCompanyFilterOptionList.subList(1, actLienCompanyFilterOptionList.size()),
				expLienCompanyFilterOptionList.subList(1, expLienCompanyFilterOptionList.size()));
		//compare lien company info from list
		lienCompanyPage.compareLienCompanyInfo(diffLienCompanyName);
		
		lm.filterLienCompanyInfoFromLienCompanyConfigurationPage(sameLienCompanyNameForNew.lienCompanyName);
		//verify lien company name column by filter
		this.verifyLienCompanyNameColumnByFilter(sameLienCompanyNameForNew.lienCompanyName);
		//verify lien company info should be existing by filter
		this.verifyLienCompanyInfoExistingByFilter();
		
		//clear up
		lm.deleteLienCompanyDetailInfo(diffLienCompanyName.lienCompanyName, schema);
		lm.deleteLienCompanyDetailInfo(sameLienCompanyNameForNew.lienCompanyName, schema);
		lm.deleteLienCompanyDetailInfo(sameLienCompanyNameForSelect.lienCompanyName, schema);
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		diffLienCompanyName.lienCompanyName = "DiffLienCompanyName";
		diffLienCompanyName.address = "DiffLienCompanyName_Qa";
		diffLienCompanyName.city = "DiffLienCompanyName_Test";
		diffLienCompanyName.state = "California";
		diffLienCompanyName.zip = "51423";
		diffLienCompanyName.country = "United States";
		diffLienCompanyName.contactPhone = "654321";	
		diffLienCompanyName.contactfName = "Qa";
		diffLienCompanyName.contactlName = "Auto";	
		diffLienCompanyName.contactEmail = "Qa_test@reserveamerica.com";
		
		sameLienCompanyNameForNew.lienCompanyName = "SameLienCompanyName";
		sameLienCompanyNameForNew.address = "SameLienCompanyNameForNew_Qa";
		sameLienCompanyNameForNew.city = "SameLienCompanyNameForNew_Test";
		sameLienCompanyNameForNew.state = "California";
		sameLienCompanyNameForNew.zip = "51423";
		sameLienCompanyNameForNew.country = "United States";
		sameLienCompanyNameForNew.contactPhone = "654321";	
		sameLienCompanyNameForNew.contactfName = "";
		sameLienCompanyNameForNew.contactlName = "";	
		sameLienCompanyNameForNew.contactEmail = "Qa_test@reserveamerica.com";
		
		sameLienCompanyNameForSelect.lienCompanyName = "SameLienCompanyName";
		sameLienCompanyNameForSelect.address = "SameLienCompanyNameForSelect_Qa";
		sameLienCompanyNameForSelect.city = "SameLienCompanyNameForSelect_Test";
		sameLienCompanyNameForSelect.state = "California";
		sameLienCompanyNameForSelect.zip = "51423";
		sameLienCompanyNameForSelect.country = "United States";
		sameLienCompanyNameForSelect.contactPhone = "654321";	
		sameLienCompanyNameForSelect.contactfName = "";
		sameLienCompanyNameForSelect.contactlName = "";	
		sameLienCompanyNameForSelect.contactEmail = "Qa_test@reserveamerica.com";
	}
	
	private void verifySortingOfLienCompanyName(List<String> actualLienCompanyNameList, List<String> expLienCompanyNameNameList){
		logger.info("Verify lien company name list sorting");
		
		//case-insensitive sorting method
		Collections.sort(expLienCompanyNameNameList, String.CASE_INSENSITIVE_ORDER);
		
		if(!expLienCompanyNameNameList.equals(actualLienCompanyNameList)){
			throw new ErrorOnPageException("The lien compnay name list sorting not correct.\nExpect sorting is: " + expLienCompanyNameNameList +
					"\nBut actual sorting is: " + actualLienCompanyNameList);
		}else {
			logger.info("The lien compnay name list sorting is correct");
		}
	}
	
	private void verifyLienCompanyNameColumnByFilter(String filterOption){
		logger.info("Verify lien company name columen by filter option = " + filterOption);
		
		List<String> lienCompanyNameListFromUI = lienCompanyPage.getLienCompanyNameColumnValues();
		boolean result = true;
		for(String lienCompanyName : lienCompanyNameListFromUI){
			if(!lienCompanyName.equals(filterOption)){
				result &= false;
			}
		}
		
		if(!result){
			throw new ErrorOnPageException("The lien company name column list should all are same with filter option = " + filterOption);
		}else {
			logger.info("The lien company name column list all are same with filter option = " + filterOption);
		}
	}
	
	private void verifyLienCompanyInfoExistingByFilter(){
		logger.info("Verify lien company info existing by filter.");
		
		List<String> lienCompanyAddrIDListFromUI = lienCompanyPage.getLienCompanyAddrIDColumnValues();
		if(!lienCompanyAddrIDListFromUI.contains(sameLienCompanyNameForNew.lienCompanyAddrID) || 
				!lienCompanyAddrIDListFromUI.contains(sameLienCompanyNameForSelect.lienCompanyAddrID)){
			throw new ErrorOnPageException("The lien company info should be existing by filter. " +
					"The lien company info are lien company id = " +
					sameLienCompanyNameForNew.lienCompanyAddrID + "," + sameLienCompanyNameForSelect.lienCompanyAddrID);
		}else {
			logger.info("The lien company info are existing by filter");
		}
	}

}
