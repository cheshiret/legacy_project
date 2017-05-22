package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.system.liencompany;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrLienCompanyChangeHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify view lien company history info
 * check point: 1. update history info is correct
 *              2. add history info is correct
 *              3. verify lien company info
 * @Preconditions:DEFECT-35826
 * @SPEC: <<View Lien Company Info Change History.UCS>>
 * @Task#: AUTO-1015
 * 
 * @author vzhang
 * @Date  Jul 23, 2012
 */
public class ViewChangeHistory extends LicenseManagerTestCase{
	private LienCompanyDetailsInfo lienCompany = new LienCompanyDetailsInfo();
	private LienCompanyDetailsInfo editLienCompany = new LienCompanyDetailsInfo();
	private List<ChangeHistory> expHistoryList = new ArrayList<ChangeHistory>();
	private LicMgrLienCompanyChangeHistoryPage lienCompanyChangeHistoryPg = LicMgrLienCompanyChangeHistoryPage.getInstance();

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
		lm.editLienCompanyDetailInfoWithClickApply(editLienCompany);
		
		lm.gotoLienCompanyHistoryPageFromLienCompanyDetailPage();
		//verify lien company history list page
		this.verifyChangeHistoryListInfo(expHistoryList);
		//verify lien company info
		lienCompanyChangeHistoryPg.compareLienCompanyInfo(editLienCompany);
		lm.gotoLienCompanyDetailPageFromLienCompanyHistoryPage();
		
		//clear up
		lm.deleteLienCompanyDetailInfo(lienCompany.lienCompanyName, schema);
		lm.deleteLienCompanyDetailInfo(editLienCompany.lienCompanyName, schema);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		lienCompany.lienCompanyName = "LienCompanyNameForHistory";
		lienCompany.address = "LienCompanyNameForHistory_Qa";
		lienCompany.city = "LienCompanyNameForHistory_Test";
		lienCompany.state = "California";
		lienCompany.zip = "51423";
		lienCompany.country = "United States";
		lienCompany.contactPhone = "654321";
		lienCompany.contactfName = "LienCompanyNameForHistory_Qa";
		lienCompany.contactlName = "LienCompanyNameForHistory_Test";	
		lienCompany.contactEmail = "LienCompanyNameForHistory@reserveamerica.com";
		
		editLienCompany.lienCompanyName = "ViewHistoryLienCompanyName";
		editLienCompany.address = "ViewHistoryLienCompanyName_Qa";
		editLienCompany.city = "ViewHistoryLienCompanyName_Test";
		editLienCompany.state = "Mississippi";
		editLienCompany.zip = "23451";
		editLienCompany.country = "United States";
		editLienCompany.contactPhone = "123456";	
		editLienCompany.contactfName = "ViewHistoryLienCompanyName_Qa";
		editLienCompany.contactlName = "ViewHistoryLienCompanyName_Test";	
		editLienCompany.contactEmail = "ViewHistoryLienCompanyName@reserveamerica.com";
		
		ChangeHistory addHistory = new ChangeHistory();
		addHistory.changeDate = DateFunctions.getToday(timeZone);
		addHistory.object = "Lien Company";
		addHistory.action = "Add";
		addHistory.field = "";
		addHistory.oldValue = "";
		addHistory.newValue = "";
		addHistory.user = DataBaseFunctions.getLoginUserName(login.userName);
		addHistory.location = login.location.split("/")[1];
		
		ChangeHistory lienCompanyNameHistory = new ChangeHistory();
		lienCompanyNameHistory.changeDate = addHistory.changeDate;
		lienCompanyNameHistory.object = addHistory.object;
		lienCompanyNameHistory.action = "Update";
		lienCompanyNameHistory.field = "Lien Company Name";
		lienCompanyNameHistory.oldValue = lienCompany.lienCompanyName;
		lienCompanyNameHistory.newValue = editLienCompany.lienCompanyName;
		lienCompanyNameHistory.user = addHistory.user;
		lienCompanyNameHistory.location = addHistory.location;
		
		ChangeHistory stateHistory = new ChangeHistory();
		stateHistory.changeDate = addHistory.changeDate;
		stateHistory.object = addHistory.object;
		stateHistory.action = lienCompanyNameHistory.action;
		stateHistory.field = "State";
		stateHistory.oldValue = lienCompany.state;
		stateHistory.newValue = editLienCompany.state;
		stateHistory.user = addHistory.user;
		stateHistory.location = addHistory.location;
		
		ChangeHistory contactLastNameHistory = new ChangeHistory();
		contactLastNameHistory.changeDate = addHistory.changeDate;
		contactLastNameHistory.object = addHistory.object;
		contactLastNameHistory.action = lienCompanyNameHistory.action;
		contactLastNameHistory.field = "Contact Last Name";
		contactLastNameHistory.oldValue = lienCompany.contactlName;
		contactLastNameHistory.newValue = editLienCompany.contactlName;
		contactLastNameHistory.user = addHistory.user;
		contactLastNameHistory.location = addHistory.location;
		
		ChangeHistory contactFirstNameHistory = new ChangeHistory();
		contactFirstNameHistory.changeDate = addHistory.changeDate;
		contactFirstNameHistory.object = addHistory.object;
		contactFirstNameHistory.action = lienCompanyNameHistory.action;
		contactFirstNameHistory.field = "Contact First Name";
		contactFirstNameHistory.oldValue = lienCompany.contactfName;
		contactFirstNameHistory.newValue = editLienCompany.contactfName;
		contactFirstNameHistory.user = addHistory.user;
		contactFirstNameHistory.location = addHistory.location;
		
		ChangeHistory contactEmailHistory = new ChangeHistory();
		contactEmailHistory.changeDate = addHistory.changeDate;
		contactEmailHistory.object = addHistory.object;
		contactEmailHistory.action = lienCompanyNameHistory.action;
		contactEmailHistory.field = "Contact Email";
		contactEmailHistory.oldValue = lienCompany.contactEmail;
		contactEmailHistory.newValue = editLienCompany.contactEmail;
		contactEmailHistory.user = addHistory.user;
		contactEmailHistory.location = addHistory.location;
		
		ChangeHistory contactPhoneHistory = new ChangeHistory();
		contactPhoneHistory.changeDate = addHistory.changeDate;
		contactPhoneHistory.object = addHistory.object;
		contactPhoneHistory.action = lienCompanyNameHistory.action;
		contactPhoneHistory.field = "Contact Phone";
		contactPhoneHistory.oldValue = lienCompany.contactPhone;
		contactPhoneHistory.newValue = editLienCompany.contactPhone;
		contactPhoneHistory.user = addHistory.user;
		contactPhoneHistory.location = addHistory.location;
		
		ChangeHistory zipHistory = new ChangeHistory();
		zipHistory.changeDate = addHistory.changeDate;
		zipHistory.object = addHistory.object;
		zipHistory.action = lienCompanyNameHistory.action;
		zipHistory.field = "ZIP/Postal";
		zipHistory.oldValue = lienCompany.zip;
		zipHistory.newValue = editLienCompany.zip;
		zipHistory.user = addHistory.user;
		zipHistory.location = addHistory.location;
		
		ChangeHistory cityHistory = new ChangeHistory();
		cityHistory.changeDate = addHistory.changeDate;
		cityHistory.object = addHistory.object;
		cityHistory.action = lienCompanyNameHistory.action;
		cityHistory.field = "City/Town";
		cityHistory.oldValue = lienCompany.city;
		cityHistory.newValue = editLienCompany.city;
		cityHistory.user = addHistory.user;
		cityHistory.location = addHistory.location;
		
		ChangeHistory addressHistory = new ChangeHistory();
		addressHistory.changeDate = addHistory.changeDate;
		addressHistory.object = addHistory.object;
		addressHistory.action = lienCompanyNameHistory.action;
		addressHistory.field = "Address";
		addressHistory.oldValue = lienCompany.address;
		addressHistory.newValue = editLienCompany.address;
		addressHistory.user = addHistory.user;
		addressHistory.location = addHistory.location;
		
		expHistoryList.add(addHistory);
		expHistoryList.add(lienCompanyNameHistory);
		expHistoryList.add(stateHistory);
		expHistoryList.add(contactLastNameHistory);
		expHistoryList.add(contactFirstNameHistory);
		expHistoryList.add(contactEmailHistory);
		expHistoryList.add(contactPhoneHistory);
		expHistoryList.add(zipHistory);
		expHistoryList.add(cityHistory);
		expHistoryList.add(addressHistory);
	}
	
	private void verifyChangeHistoryListInfo(List<ChangeHistory> expectHistoryLists){		
		logger.info("Verify change history list info.");
		List<ChangeHistory> historyListFromUI = lienCompanyChangeHistoryPg.getChangeHistoryLists();
		if(historyListFromUI.size()<expectHistoryLists.size()){
			throw new ErrorOnPageException("The lien company change history list info are not correct. Expect size is " + expectHistoryLists.size());
		}
		
		List<ChangeHistory> actHistoryListFromUI = historyListFromUI.subList(0, expectHistoryLists.size());
		boolean result = true;		
		for(int i=0; i<expectHistoryLists.size(); i++){			
			for(int j=0; j<actHistoryListFromUI.size(); j++){
				if(expectHistoryLists.get(i).field.equals(actHistoryListFromUI.get(j).field)){
					result &= actHistoryListFromUI.get(j).equals(expectHistoryLists.get(i));
					break;
				}else{
					if(j>=actHistoryListFromUI.size()-1){
						result &= false;
						logger.error("Have not the history info when filed = " + expectHistoryLists.get(i).field);
					}
				}
			}
			
		}
		
		if(!result){
			throw new ErrorOnPageException("The lien company change history list info is not correct. Please check log.");
		}
	}

}
