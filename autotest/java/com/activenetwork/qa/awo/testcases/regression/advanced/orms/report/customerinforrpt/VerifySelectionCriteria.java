package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.customerinforrpt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.LicMgrCustomerLicenseYearWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC:102375,102378
 * @Task#:Auto-2196
 * 
 * @author ssong
 * @Date  Jun 17, 2014
 */
public class VerifySelectionCriteria extends ResourceManagerTestCase{

	private List<String> fiscalYears = new ArrayList<>();
	private LoginInfo loginLm = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private String idType,idNum;
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		//check License year drop down from resource manager
		compareTwoList(OrmsReportCriteriaPage.getInstance().getLicenseYears());
		
		rm.logoutResourceManager();		
		
		lm.loginLicenseManager(loginLm);
		
		lm.gotoCustomerDetailFromCustomersQuickSearch(INDIVIDUAL_CUSTOMER_CLASS,idType, idNum);
		//check from LM and verify cancel button
		verifyLyCountInLM();

		lm.logOutLicenseManager();
	}
	
	private void compareTwoList(List<String> years){
		if(!years.containsAll(fiscalYears)){
			throw new ErrorOnPageException("License/Fiscal Year dropdown elements not correct.");
		}
	}
	
	private void verifyLyCountInLM(){
		LicMgrCustomerDetailsPage detailPg = LicMgrCustomerDetailsPage.getInstance();
		LicMgrCustomerLicenseYearWidget lyWidget = LicMgrCustomerLicenseYearWidget.getInstance();
		
		detailPg.clickPrintCustomerRecord();
		ajax.waitLoading();
		lyWidget.waitLoading();
		compareTwoList(lyWidget.getLicenseYearCount());
		lyWidget.clickCancel();
		ajax.waitLoading();
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		loginLm.url = login.url;
		loginLm.userName = TestProperty.getProperty("orms.fm.user");
		loginLm.password =  TestProperty.getProperty("orms.fm.pw");
		loginLm.contract = login.contract;
		loginLm.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		idType = MS_LICENSE_TYPE_MDWFP;
		String customerId = "22000192";
		idNum = rm.getCustomerNumByCustId(customerId, schema);	
		
		// report information
		rd.group = "All";
		rd.reportName = "Customer Information Report";
		fiscalYears.add("All");
		for(int i=1;i<11;i++){
			fiscalYears.add(String.valueOf(i));
		}
	}

}
