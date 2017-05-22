package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.DocumentTemplateInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddDocTemplateWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDocumentTemplatesConfigurationPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add a print document template
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Nov 21, 2012
 */
public class AddPrintDocumentTemplateFunction extends FunctionCase {
	private DocumentTemplateInfo docTemplateInfo = new DocumentTemplateInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LoginInfo loginInfo = new LoginInfo();
	private String schema = "";
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	
	@Override
	public void wrapParameters(Object[] param) {
		loginInfo.url = AwoUtil.getOrmsURL(env);
		loginInfo.userName = TestProperty.getProperty("orms.fm.user");
		loginInfo.password = TestProperty.getProperty("orms.fm.pw");
		loginInfo.contract = (String)param[0];
		loginInfo.location = (String)param[1];
		docTemplateInfo = (DocumentTemplateInfo)param[2];
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + loginInfo.contract.split("Contract")[0].trim();
	}

	@Override
	public void execute() {
		if (!loginInfo.contract.equals(contract) && loggedin && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedin= false;
		}
		if(loginInfo.contract.equals(contract) && loggedin && isBrowserOpened){
			if(!loginInfo.location.equals(location)){
				lm.switchLocationInHomePage(loginInfo.location.replace("/", "-"));
			}
		}
		if (!loggedin || !isBrowserOpened) { 
			lm.loginLicenseManager(loginInfo);
			loggedin= true;
		}
		if (!homePg.exists()) {
			lm.gotoHomePage();
		}
		contract = loginInfo.contract;
		location = loginInfo.location;
		lm.gotoDocumentTemplateConfigPgFromTopMenu();
		lm.addDocumentTemplate(docTemplateInfo, true);
		this.verifyAddDocTemplateSuccess();
		newAddValue = lm.getDocTemplateIDByName(schema, docTemplateInfo.docTemplateName);
	}
	
	/**
	 * Verify add document template success
	 */
	private void verifyAddDocTemplateSuccess(){
		LicMgrDocumentTemplatesConfigurationPage docTemplatesListPg = LicMgrDocumentTemplatesConfigurationPage.getInstance();
		LicMgrAddDocTemplateWidget addDocTemplateWidget = LicMgrAddDocTemplateWidget.getInstance();
		boolean passed = true;
		
		logger.info("Verify add docment tempalte info...");
		
		// Check if add document template widget close
		if (addDocTemplateWidget.exists()) {
			String errMeg =  addDocTemplateWidget.getErrorMsg();
			addDocTemplateWidget.clickCancel();
			docTemplatesListPg.waitLoading();
			throw new ErrorOnPageException("Fail to add document template '" + docTemplateInfo.docTemplateName + "' " +
					"due to '" + errMeg + "'.");
		}
		
		if(!docTemplatesListPg.checkDocTemplateExists(docTemplateInfo.docTemplateName)){
			throw new ErrorOnPageException("Add Document Template Faild.");
		}

		List<String> docTemplateRowInfo = docTemplatesListPg.getDocTemplateInfo(docTemplateInfo.docTemplateName, "Document Template Name");		
		if(!docTemplateRowInfo.get(0).equalsIgnoreCase(docTemplateInfo.docTemplateName)){
			passed = false;
			logger.error("Document Template Name '" + docTemplateInfo.docTemplateName + "' is not correct.");
		}

		if(!docTemplateRowInfo.get(1).equalsIgnoreCase(docTemplateInfo.templateType)){
			passed = false;
			logger.error("Template Type '" + docTemplateInfo.templateType + "' is not correct.");
		}

		if(!docTemplateRowInfo.get(2).equalsIgnoreCase(docTemplateInfo.maxReprintCount)){
			passed = false;
			logger.error("Maximum Reprint Count '" + docTemplateInfo.maxReprintCount + "' is not correct.");
		}

		if(!docTemplateRowInfo.get(3).equalsIgnoreCase(docTemplateInfo.combIndicator)){
			passed = false;
			logger.error("Combination Indicator '" + docTemplateInfo.combIndicator + "' is not correct.");
		}

		if(!docTemplateRowInfo.get(4).equalsIgnoreCase(docTemplateInfo.maxLineCount)){
			passed = false;
			logger.error("Maximum Line Count '" + docTemplateInfo.maxLineCount + "' is not correct.");
		}

		if(!docTemplateRowInfo.get(5).equalsIgnoreCase(docTemplateInfo.harvestDocument)){
			passed = false;
			logger.error("Harvest Document '" + docTemplateInfo.harvestDocument + "' is not correct.");
		}
		
		if(passed){
			logger.info("Adding new print document template successfully.");
		}else{
			throw new ErrorOnPageException("There are some verification points is not correct, please check the error log");
		}
	}
}
