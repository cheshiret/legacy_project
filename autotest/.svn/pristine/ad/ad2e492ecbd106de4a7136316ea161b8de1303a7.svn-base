package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.batcheditlicenseyear;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrBatchEditLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePrintDocumentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:
 * 1. Check Privilege Product Fulfillment Document records sort
 * 2. Check Privilege Product Fulfillment Document and Privilege License Year records sort
 * @Preconditions:
 * 1. Have the Display Categories bELYDisplayCategory2) via configuration
 * 2. Have one Display sub-Category(BELYDisplaySubCategory) via configuration
 * 3. Have Document Template(BatchEditLicenseYearDOC) via configuration
 * 4. Privilege
 *    Code(clD), Name(BatchEditLicenseYearTest3), Display Category(BELYDisplayCategory2) and Display Sub-Category (BELYDisplaySubCategory)
 * 4.1  Active License Year: locationClass = "All";
 * 4.2  Active Documents:
 * 4.2.1 	Document Template=BatchEditLicenseYearDOC(), Harvest DOC=All
 * 4.2.2    Document Template=Doc_privi, Harvest DOC=Internet Sales
 * 4.2.3    Document Template=Doc_privi, Harvest DOC=Kiosk   
 * @SPEC: Edit License Year for Privileges in Batch
 * @Task#: AUTO-726
 * 
 * @author SWang
 * @Date  Aug 23, 2011
 */
public class ProductFulfillmentDocumentSort extends LicenseManagerTestCase{
	//Document parameters
	private LicMgrDocumentInfo document1 = new LicMgrDocumentInfo();
	private LicMgrDocumentInfo document2 = new LicMgrDocumentInfo();
	private List<LicMgrDocumentInfo> documentList = new ArrayList<LicMgrDocumentInfo>();
	//License Year parameter
	private LicenseYear ly = new LicenseYear();
	//Sorting parameters
	private List<String> Names = new ArrayList<String>();

	public void execute() {
		//login in 
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();

		//Add Documents
		this.checkAndAddPrivileges();
		this.checkAndAddPrivilegeLicenseYears();
		this.checkAndAddFulfillmentDocuments();

		lm.gotoBatchEditLicenseYearPg();

		//Document names sort and license Year and Document names sort
		this.checkPrivilegeProductFulfillmentDocumentSort();

		//logout 
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		//Privilege information
		privilege.code = "c1D";
		privilege.name = "BatchEditLicenseYearTest3";
		privilege.status = "Active";
		privilege.customerClasses = new String[]{"Individual"};
		privilege.displayCategory = "bELYDisplayCategory2";
		privilege.displaySubCategory = "BELYDisplaySubCategory";
//		privilege.reportCategory = "rachel test";
		privilege.displayOrder = "0";

		//Privileges License Years information
		ly.status = "Active"; 
		ly.licYear = String.valueOf(DateFunctions.getCurrentYear());
		ly.locationClass = "All";
		ly.sellFromDate = DateFunctions.getDateAfterToday(3);
		ly.sellFromTime = "12:00";
		ly.sellFromAmPm = "AM";
		ly.sellToDate = DateFunctions.getDateAfterToday(5);
		ly.sellToTime = "11:59";
		ly.sellToAmPm = "PM";

		//Document information
		document.prodCode = privilege.code;
		document.prodType="privilege";
		document.status = "Active";
		document.docTepl="BatchEditLicenseYearDOC";
		document.equipType="All";
		document.purchaseTypes=new String[]{"Transfer"};
		document.licYearFrom=String.valueOf(DateFunctions.getCurrentYear());
		document.licYearTo=String.valueOf(DateFunctions.getCurrentYear());
		document.effectiveFromDate =  DateFunctions.getDateAfterToday(2);
		document.effectiveToDate = DateFunctions.getDateAfterToday(100);
		document.printFromDate=DateFunctions.getDateAfterToday(3);
		document.printToDate=DateFunctions.getDateAfterToday(5);
		document.printOrd = "1";
		document.fulfillMethod="Fulfilled by Mail";
		document.species = "01 - Ducks";
		document.huntSeason = "02 - LicYearBatchAdd";
		document.harvestDocument = "Yes";
		document.configVariables = new String[]{"auto111"};

		document1.prodType="privilege";
		document1.status = "Active";
		document1.docTepl="DocumentTest"; //"Doc_privi";
		document1.equipType="Internet Sales";
		document1.purchaseTypes=new String[]{"Transfer"};
		document1.licYearFrom=String.valueOf(DateFunctions.getCurrentYear());
		document1.licYearTo=String.valueOf(DateFunctions.getCurrentYear());
		document1.effectiveFromDate=DateFunctions.getDateAfterToday(2);
		document1.printOrd = "2";
		document1.fulfillMethod="Fulfilled by Mail";
		document1.harvestDocument = "No";
		document1.configVariables = new String[]{"auto222"};

		document2.prodType="privilege";
		document2.status = "Active";
		document2.docTepl="DocumentTest"; //"Doc_privi";
		document2.equipType="Kiosk";
		document2.purchaseTypes=new String[]{"Transfer"};
		document2.licYearFrom=String.valueOf(DateFunctions.getCurrentYear());
		document2.licYearTo=String.valueOf(DateFunctions.getCurrentYear());
		document2.effectiveFromDate=DateFunctions.getDateAfterToday(2);
		document2.printOrd = "3";
		document2.fulfillMethod="Fulfilled by Mail";
		document2.harvestDocument = "No";
		document2.configVariables = new String[]{"auto333"};

		documentList.add(document);
		documentList.add(document1);
		documentList.add(document2);

		//Sort information
		Names.add(privilege.name);
		Names.add(document.docTepl+" - "+document.equipType);
		Names.add(document1.docTepl+" - "+document1.equipType);
		Names.add(document2.docTepl+" - "+document2.equipType);
	}

	private void checkAndAddPrivileges(){
		LicMgrPrivilegesListPage privilegeListPg = LicMgrPrivilegesListPage.getInstance();
		if(!privilegeListPg.verifyPrivilegeProductExist(privilege)){
			lm.addPrivilegeProduct(privilege);
		}
	}

	private void checkAndAddFulfillmentDocuments(){
		LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage
		.getInstance();
		lm.gotoPrivilegeDocumentPgFromPrivilegeDetailsPg(document.prodCode);
		for(int i=0; i<documentList.size(); i++){
			if(!printDocSubTab.verifyDocumentExist(documentList.get(i))){
				lm.addPrintDocumentForProduct(documentList.get(i));
			}
		}
		lm.gotoPrivilegeListPgFromGivePage(printDocSubTab);
	}

	private void checkAndAddPrivilegeLicenseYears(){
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
		.getInstance();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeLicenseYearPage();
		if(!licenseYearPg.verifyLicenseYearExist(ly)){
			lm.addLicenseYear(ly);
		}
	}

	private void checkPrivilegeProductFulfillmentDocumentSort(){
		LicMgrBatchEditLicenseYearPage batchEditLicenseYearPg = LicMgrBatchEditLicenseYearPage.getInstance();
		batchEditLicenseYearPg.selectYearToEdit(document2.licYearFrom);
		ajax.waitLoading();
		batchEditLicenseYearPg.clickGo();
		ajax.waitLoading();
		if(!batchEditLicenseYearPg.verifyBatchEditLicenseYearPageSort(Names, 2)){
			throw new ErrorOnDataException("The sorting of privilege product name is incorrect.");
		}
	}
}
