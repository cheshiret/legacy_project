package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.system.liencompany;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrLienCompanyDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify adding an lien company detail info, through by select an existing lien company name
 * check point: 1. add lien company info with select an existing lien company name
 *              2. generate lien company record at list
 *              3. compare lien company detail info at detail page
 * @Preconditions:
 * @SPEC: <<Add Lien Company.UCS>>
 * @Task#: AUTO-1015
 * 
 * @author vzhang
 * @Date  Jul 23, 2012
 */
public class Add_SelectLienCompanyName extends LicenseManagerTestCase{
	private LienCompanyDetailsInfo lienCompany = new LienCompanyDetailsInfo();
	private LienCompanyDetailsInfo existingLienCompany = new LienCompanyDetailsInfo();
	private LicMgrLienCompanyDetailPage lienCompanyDetailPage = LicMgrLienCompanyDetailPage.getInstance();

	@Override
	public void execute() {
		lm.deleteLienCompanyDetailInfo(lienCompany.lienCompanyName, schema);
		lm.loginLicenseManager(login);
		lm.gotoSysConfPgFromTopMenu();
		
		lm.gotoLienCompaniesPageFromSysConfigPage();
		//prepare an existing lien company
		lm.addLienCompanyWithNewLienCompanyNameFromCompaniesPageWithClickOk(existingLienCompany);
		//add lien company info
		lienCompany.lienCompanyAddrID = lm.addLienCompanyWithSelectLienCompanyNameFromCompaniesPageWithClickOK(lienCompany);
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
		
		existingLienCompany.lienCompanyName = "SelectLienCompanyName";
		existingLienCompany.address = "SelectLienCompanyName_Qa";
		existingLienCompany.city = "SelectLienCompanyName_Test";
		existingLienCompany.state = "Mississippi";
		existingLienCompany.zip = "42153";
		existingLienCompany.country = "United States";
		existingLienCompany.contactPhone = "123456";
		
		lienCompany.lienCompanyName = existingLienCompany.lienCompanyName;
		lienCompany.address = "SelectLienCompanyName_Qa";
		lienCompany.city = "SelectLienCompanyName_Test";
		lienCompany.state = "California";
		lienCompany.zip = "51423";
		lienCompany.country = "United States";
		lienCompany.contactPhone = "654321";	
		lienCompany.contactfName = "Qa";
		lienCompany.contactlName = "Auto";	
		lienCompany.contactEmail = "";
		lienCompany.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		lienCompany.creationLocation = login.location.split("/")[1];
		lienCompany.creationDate = DateFunctions.getToday();
		lienCompany.lastUpdateUser = lienCompany.creationUser;
		lienCompany.lastUpdateLocation = lienCompany.creationLocation;
		lienCompany.lastUpdateDate = lienCompany.creationDate;
	}

}
