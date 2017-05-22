package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear.batchadd;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearBatchAddPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: 
 * 1: verify Batch Add License Year page, search result page display order.
 * @Preconditions:
 * 1: there is one document template setup named "AutoBatchAddOrder";
 * 2: there are two Display Categories named "AT_BatchAdd_A" and "BatchAdd_AT_A" setup in Admin->Configuration->Display Categories section.
 * 3: there are two privilege products code "ATA" and code "ATO" setup under "AT_BatchAdd_A" Display categories.
 * 4: there is one privilege product named "ATB" setup under "BatchAdd_AT_A" display categories.
 * 5: there is one fullfillment(equipment type: Internet Sales, document template:AutoBatchAddOrder ) and one license year for 2011 setup for privilege product "ATA"
 * 6: there is one licnese year(2011) setup for privilege product "ATO";
 * 7: there are two fulfillment document(2011) setup  for privilege product "ATO", one in the equipment type "All" , the other in the equipment type "License Printer 01", both in the document template "AutoBatchAddOrder"
 * 8: there is one fullfillment(equipment type: Kiosk, document template:AutoBatchAddOrder ) and one license year for 2011 setup for privilege product "ATB"
 * 
 * @SPEC: Add License Year for Privileges in Batch
 * @Task#:AUTO-725
 * 
 * @author bzhang
 * @Date  Sep 6, 2011
 */
public class VerifySchResultDispOrder extends LicenseManagerTestCase {
	
	PrivilegeInfo pri_ATA = new PrivilegeInfo();
	PrivilegeInfo pri_ATO = new PrivilegeInfo();
	PrivilegeInfo pri_ATB = new PrivilegeInfo();
	LicenseYear lic_ATA = new LicenseYear();
	LicenseYear lic_ATO = new LicenseYear();
	LicenseYear lic_ATB = new LicenseYear();
	LicenseYear licYr = new LicenseYear(); // license year data collection used for Batch Add Licnese Year page search.
	
	LicMgrDocumentInfo doc_ATA = new LicMgrDocumentInfo();
    LicMgrDocumentInfo doc_ATO1 = new LicMgrDocumentInfo();
    LicMgrDocumentInfo doc_ATO2 = new LicMgrDocumentInfo();
    LicMgrDocumentInfo doc_ATB = new LicMgrDocumentInfo();
	
    public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeLicenseYearBatchAddPageFromTopMenu();
		lm.searchPrivilegeLicenseYearOnBatchAddPg(licYr);
		this.verifyDisplayOrderForSearchResult();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		pri_ATA.code = "ATA";
		pri_ATA.name = "AutoBatchAddDisATA";
		pri_ATA.displayCategory = "AT_BatchAdd_A";
		
		pri_ATO.code = "ATO";
		pri_ATO.name = "AutoBatchAddDisATO";
		pri_ATO.displayCategory = "AT_BatchAdd_A";
		
		pri_ATB.code = "ATO";
		pri_ATB.name = "AutoBatchAddDisATB";
		pri_ATB.displayCategory = "BatchAdd_AT_A";
		
		licYr.copyFromYear = "2011";
		licYr.newLicYear = "2023";
		licYr.numOfYearToAdd = "2";
		
		lic_ATA.licYear = "2011";
		lic_ATA.productCode = pri_ATA.code;
		lic_ATA.productName = pri_ATA.name;
		lic_ATA.locationClass = "01 - MDWFP Headquarters";
		
		lic_ATO.licYear = "2011";
		lic_ATO.productCode = pri_ATO.code;
		lic_ATO.productName = pri_ATO.name;
		lic_ATO.locationClass = "All";
		
		lic_ATB.licYear = "2011";
		lic_ATB.productCode = pri_ATB.code;
		lic_ATB.productName = pri_ATB.name;
		lic_ATB.locationClass = "03 - Lakes Offices";
		
		doc_ATA.docTepl = "AutoBatchAddOrder";
		doc_ATA.equipType = "Internet Sales";
		
		doc_ATO1.docTepl = "AutoBatchAddOrder";
		doc_ATO1.equipType = "All";
		
		doc_ATO2.docTepl = "AutoBatchAddOrder";
		doc_ATO2.equipType = "License Printer 01";
		
		doc_ATB.docTepl = "AutoBatchAddOrder";
		doc_ATB.equipType = "Kiosk";
	}
	
	public void verifyDisplayOrderForSearchResult(){
		LicMgrPrivilegeLicenseYearBatchAddPage batchAddPage = LicMgrPrivilegeLicenseYearBatchAddPage.getInstance();
		//wrap parameter for display category columns
		List<String> disCategories = new ArrayList<String>();
		disCategories.add(pri_ATA.displayCategory);
		disCategories.add(pri_ATB.displayCategory);
		
		//wrap parameter for privilege code columns
		List<String> disCode = new ArrayList<String>();
		disCode.add(pri_ATA.code);
		disCode.add(pri_ATO.code);
		disCode.add(pri_ATB.code);
		
		//wrap parameter for privilege code columns
		List<String> licfuls = new ArrayList<String>();
		licfuls.add(lic_ATA.productName);
		licfuls.add(doc_ATA.docTepl + " - " + doc_ATA.equipType);
		
		licfuls.add(lic_ATO.productName);
		licfuls.add(doc_ATO1.docTepl + " - " + doc_ATO1.equipType);
		licfuls.add(doc_ATO2.docTepl + " - " + doc_ATO2.equipType);
		
		licfuls.add(lic_ATB.productName);
		licfuls.add(doc_ATB.docTepl + " - " + doc_ATB.equipType);

		//verify the display order for privilege category.
		if (!batchAddPage.verifyTableRecordsDisplaySort(".id", "PrivilegeLicenseYearList", 0, disCategories, true)){
			throw new ErrorOnPageException("The display order for privilege category is not correct..");
		}
		
		//verify the display order for privilege code.
		if (!batchAddPage.verifyTableRecordsDisplaySort(".id", "PrivilegeLicenseYearList", 1, disCode, true)){
			throw new ErrorOnPageException("The display order for privilege code is not correct..");
		}
		
		//verify the display order for license year and fulfillment document.
		if (!batchAddPage.verifyTableRecordsDisplaySort(".id", "PrivilegeLicenseYearList", 2, licfuls, true)){
			throw new ErrorOnPageException("The display order for license year and fulfillment document is not correct..");
		}
	}

}
