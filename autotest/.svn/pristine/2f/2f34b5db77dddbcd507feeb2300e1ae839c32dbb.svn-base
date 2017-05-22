package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.DocumentTemplateInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAddPrintDocumentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePrintDocumentsPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddPriPrintDocument extends SupportCase{
	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private LicenseManager lm = LicenseManager.getInstance();
	private DocumentTemplateInfo docTempInfo = new DocumentTemplateInfo();
	private LicMgrDocumentInfo document = new LicMgrDocumentInfo();
	private LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage.getInstance();
	private String privilegeCode = "";
	private String privilegeName = "";
	public void execute() {
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if((!loggedIn )|| (loggedIn && !printDocSubTab.exists())){
			lm.loginLicenseManager(login);
			loggedIn=true;
			lm.checkAndAddDocumentTem(docTempInfo);
			//goto privilege document page
			lm.gotoPrivilegeDocumentPgFromTopMenu(privilegeCode);
			
			//check and deactivate the exist document if already there
			this.checkAndDeactivateDocument();
		}
		//add print document template for privilege
		lm.addPrintDocumentForProduct(document,false);
		this.verifyResule();
		
		contract=login.contract;
	}

	public void getNextData() {
		
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		privilegeCode = dpIter.dpString("privilegeCode");
		privilegeName = dpIter.dpString("privilegeName");
		document.prodType = dpIter.dpString("productType");
		document.docTepl = dpIter.dpString("docTepl");
		System.out.println(this.splitTextByComma(dpIter.dpString("equipTypes")));
		document.equipTypes = this.splitTextByComma(dpIter.dpString("equipTypes"));
		System.out.println(this.splitTextByComma(dpIter.dpString("purchaseTypes")));
		document.purchaseTypes = this.splitTextByComma(dpIter.dpString("purchaseTypes"));
		
		document.licYearFrom = dpIter.dpString("licYearFrom");
		if(document.licYearFrom.length()<1){
			document.licYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		}
		document.licYearTo = dpIter.dpString("licYearTo");
		if(document.licYearTo.length()<1){
			document.licYearTo = String.valueOf(DateFunctions.getCurrentYear());
		}
		document.effectiveFromDate = dpIter.dpString("effectiveFromDate");
		if(document.effectiveFromDate.length()<1){
			document.effectiveFromDate = DateFunctions.formatDate(DateFunctions.getToday(),"EE MMM d yyyy");
		}
		document.effectiveToDate = dpIter.dpString("effectiveToDate");
		if(document.effectiveToDate.length()<1){
			document.effectiveToDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(3),"EE MMM d yyyy");
		}
		document.createDate = dpIter.dpString("createDate");
		if(document.createDate.length()<1){
			document.createDate = DateFunctions.formatDate(DateFunctions.getToday(),"EE MMM d yyyy");
		}
		document.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		document.printOrd = dpIter.dpString("printOrder");
		document.fulfillMethod = dpIter.dpString("fulFillMethod");
		document.configVariables = this.splitTextByComma(dpIter.dpString("configvariables"));
		document.prodCode = privilegeCode;
		document.prodName = privilegeName;		
		
		docTempInfo.docTemplateName = document.docTepl;
		docTempInfo.templateType = dpIter.dpString("templateType");
		docTempInfo.harvestDocument = dpIter.dpString("harvestDocument");
		docTempInfo.combIndicator =dpIter.dpString("combindicator");
		
		logMsg[0]=privilegeCode;
		logMsg[1]=document.docTepl;
	}

	public void wrapParameters(Object[] param) {
		cursor = 0;
		startpoint = 0 ; // the start point in the data pool
		endpoint = 0 ; // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		logMsg=new String[3];
		logMsg[0]="PrivilegeCode";
		logMsg[1]="DocumentTemplate";
		logMsg[2]="result";
	}
	
	
	private void checkAndDeactivateDocument() {
		
		List<String> documentAssignIDs = new ArrayList<String>();
		
		if (document.equipTypes.length > 0 || document.purchaseTypes.length > 0){
			for(int i = 0; i < document.equipTypes.length; i++){
				for(int j = 0; j < document.purchaseTypes.length; j++){
					document.equipType = document.equipTypes[i];
					document.purchaseType = document.purchaseTypes[j];
					documentAssignIDs = printDocSubTab.getPrintDocumentAssignmentIdsForCleanUp(document);
				
					logger.info("DOCUMENT number to deactive: " + documentAssignIDs.size());
				
					for(int k=0;k<documentAssignIDs.size();k++){
					
						logger.info("To deactvate document id is : " + documentAssignIDs.get(k));
					
						lm.deactivePrivilegeDocumentAssignment(documentAssignIDs.get(k));
					}	
				}
			}
			
		}
	}
	
	private void verifyResule(){
		LicMgrAddPrintDocumentWidget addPrintDocWidget = LicMgrAddPrintDocumentWidget.getInstance();
		if(!printDocSubTab.exists()){
			logger.error("Add privilege pricing failed:privilege code="+privilegeCode+",document template="+document.docTepl+addPrintDocWidget.getWarningMessage());
			logMsg[2]="Failed";
		}else{
			logMsg[2]="Pass";
		}
	}

	private String[] splitTextByComma(String text){
		String[] list;;
		if(text.contains(",")){
			list =  text.split(",", 0);
		}else{
			list = new String[]{text};
		}
		return list;
	}
}
