package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import java.util.List;

import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import com.activenetwork.qa.awo.datacollection.legacy.orms.DocumentTemplateInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDocumentTemplatesConfigurationPage;
import com.activenetwork.qa.awo.supportscripts.AbstractSingleDatapoolSupportCase;
import com.activenetwork.qa.awo.util.DatapoolUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.AutomationLogger;

/**
 * 
 * @author fliu
 * @Date  March 19, 2012
 */
public class AddPrintDocumentTemplate extends AbstractSingleDatapoolSupportCase{
	
	private DocumentTemplateInfo docTemplateInfo = new DocumentTemplateInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private String previousContract;
	private boolean loggedIn;
	private static AutomationLogger logger = AutomationLogger.getInstance();
	private boolean passed = true;
	
	@Override
	protected void initRange() {
		startpoint = 9;
		endpoint = 9;
	}

	@Override
	protected void readDataPool(IDatapoolIterator dpIter) {
		docTemplateInfo = DatapoolUtil.fill(DocumentTemplateInfo.class, dpIter);
	}

	@Override
	public void execute(Orms orms) {
		try {
			LoginInfo loginInfo = orms.loginInfo;
			
			if (!loginInfo.contract.equals(previousContract) && loggedIn) {
				lm.logOutLicenseManager();
				loggedIn = false;
			} 
			
			if (!loggedIn) { // Logged in
				lm = orms.loginLicenseManager();
				previousContract = loginInfo.contract;
				loggedIn = true;
			}
			
			lm.gotoDocumentTemplateConfigPgFromTopMenu();
			lm.addDocumentTemplate(docTemplateInfo, true);
			this.verifyAddDocTemplateSuccess();
			
			if(passed){
				setResult("Success");
				logger.info("Adding new print document template successfully.");
			}else{
				throw new ErrorOnPageException("There are some verification points is not correct, please check the error log");
			}

		} catch (Exception e) {
			setResult("Create the new print document template failed. Error -- " + e.getMessage());
			
			logger.error(e);
			// Plz comment out this line when you debugging
			browser.close();
			loggedIn = false;
			
			throw new RuntimeException(e);
		}
	}

	/**
	 * Verify add document template success
	 */
	private void verifyAddDocTemplateSuccess(){
		LicMgrDocumentTemplatesConfigurationPage docTemplatesListPg = LicMgrDocumentTemplatesConfigurationPage.getInstance();
		
		logger.info("Verify add docment tempalte success.");

		if(!docTemplatesListPg.checkDocTemplateExists(docTemplateInfo.docTemplateName)){
			passed = false;
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
	}

}
