package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAddPrintDocumentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePrintDocumentsPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddPriPrintDocumentFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrDocumentInfo document = new LicMgrDocumentInfo();
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = (String)param[0];
		login.location = (String)param[1];
		document = (LicMgrDocumentInfo)param[2];
	}

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedin= false;
		}
		if(login.contract.equals(contract) && loggedin && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedin || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedin= true;
		}
		if (!homePg.exists()) {
			lm.gotoHomePage();
		}
		contract = login.contract;
		location = login.location;
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(document.prodCode);
		lm.addPrintDocumentForProduct(document,true);	
		newAddValue = this.verifyResult();
	
	}
	
	private String verifyResult(){
		LicMgrAddPrintDocumentWidget addPrintDocWidget = LicMgrAddPrintDocumentWidget.getInstance();
		LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage.getInstance();
		
		if (addPrintDocWidget.exists()) {
			String errMsg = addPrintDocWidget.getWarningMessage();
			addPrintDocWidget.clickCancel();
			printDocSubTab.waitLoading();
			throw new ErrorOnPageException("Add privilege print docuemnt failed:privilege code="+document.prodCode+",document template="+document.docTepl
					+ ". Due to:" + errMsg);
		}
		
		String ids = "Document IDs: ";
		for (String equType : document.equipTypes) {
			document.equipType = equType;
			for (String purType : document.purchaseTypes) {
				document.purchaseType = purType;
				String id = printDocSubTab.getDocumentID(document);
				if (StringUtil.isEmpty(id)) {
					throw new ErrorOnPageException("Failed to add privilege pint document: privilege code=" + document.prodCode);
				}
				ids += id + ", ";
			}
		}
		
		logger.info("Successfully add privilege print document: privilege code="+document.prodCode+",document template="+document.docTepl);
		return ids.substring(0, ids.lastIndexOf(", "));
	}
}
