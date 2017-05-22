package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear.batchadd;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearBatchAddPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @prerequisite:
 * 1: there is a privilege product code(AT1), name (Auto Batch License Status) exist..
 * 2: there are ONLY THREE active license year setup for this product(current year,current+1,current+3).
 * 3: there are ONLY TWO active fulfillment document setup for 
 * current year(TempateName:AutoBatchLic01, equipment type: all, purchase type: transfer) 
 * current+3(TempateName:AutoBatchLic02; equipment type: all; purchase type: original)
 * 4: make sure there is no active fulfillment document setup for current year+1.
 * @Description: 
 * verify batch add license year page search result based on different license year record status for the given
 * privilege product.
 * @Preconditions:
 * @SPEC: Add License Year for Privileges in Batch
 * @Task#:AUTO-725
 * 
 * @author bzhang
 * @Date  Aug 25, 2011
 */
public class VerifySchResultLicStatus extends LicenseManagerTestCase {
	private LicenseYear ly1 = new LicenseYear();  // license year for current
	private LicenseYear ly2 = new LicenseYear();  // license year for current+1
	private LicenseYear ly3 = new LicenseYear();  // license year for current+3
	private LicMgrDocumentInfo document1 = new LicMgrDocumentInfo();  //document for current
	private LicMgrDocumentInfo document2 = new LicMgrDocumentInfo();  //document for current+3
	private LicMgrPrivilegeLicenseYearBatchAddPage priLicYearBatchAddPg = LicMgrPrivilegeLicenseYearBatchAddPage.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeLicenseYearBatchAddPageFromTopMenu();
		//2 verify 2012+1 license year will show when there is no fulfillment for the year of(current+1)
		lm.searchPrivilegeLicenseYearOnBatchAddPg(ly2);
		priLicYearBatchAddPg.verifyLicYearRecordExist(ly2, true);
	
		//3 verify the 2011+1 license year and 2011+1 fulfillment document won't show. b/c copy from year don't equal to selected product license year.
		ly1.copyFromYear = Integer.toString(this.getNoRecordLicneseYearForGivenPrivilegeProduct(privilege.code, privilege.name));
		ly1.newLicYear = "2023"; // there is no record for 2023,this value may need to change later time.
		lm.searchPrivilegeLicenseYearOnBatchAddPg(ly1);
		priLicYearBatchAddPg.verifyLicYearRecordExist(ly1, false);
		priLicYearBatchAddPg.verifyFulFillMentDocumentExist(document1.docTepl, document1.equipType, false);
		
		//4 verify inactive license year record don't exist on batch add page, and the 2015 active fulfillment document won't show
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeLicenseYearPage();
		lm.searchPrivilegeLicenseYearByInfo(ly3);
		lm.gotoPrivilegeLicenseYearDetailPg(ly3.status, ly3.locationClass, ly3.licYear);
		ly3.status = "Inactive";  // Inactive license year for 2015
		lm.updatePrivilegeLicenseYearInfo(ly3);

		lm.gotoPrivilegeLicenseYearBatchAddPageFromTopMenu();
		ly3.copyFromYear = ly3.licYear;  
		ly3.newLicYear = "2023";   
		lm.searchPrivilegeLicenseYearOnBatchAddPg(ly3);
		priLicYearBatchAddPg.verifyLicYearRecordExist(ly3, false);
		priLicYearBatchAddPg.verifyFulFillMentDocumentExist(document2.docTepl, document2.equipType, false);
		
		//5 verify there is no active & inactive license year equal to new license year added value, product will show.
		ly1.copyFromYear = ly1.licYear;  //make sure privilege have active license year for 2012
		ly1.newLicYear = "2023"; // make sure privilege don't have active and inactive license year for 2023
		lm.searchPrivilegeLicenseYearOnBatchAddPg(ly1);
		priLicYearBatchAddPg.verifyLicYearRecordExist(ly1, true);
		
		//roll back the inactive update made in step4
		
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeLicenseYearPage();
		ly3.status = "Inactive"; 
		lm.searchPrivilegeLicenseYearByInfo(ly3);
		lm.gotoPrivilegeLicenseYearDetailPg(ly3.status, ly3.locationClass, ly3.licYear);
		ly3.status = "Active";  // Active license year for 2015
		lm.updatePrivilegeLicenseYearInfo(ly3);
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS"; 
		
		//privileges info
		privilege.status = "Active";
		privilege.code = "AT1";
		privilege.name = "Auto Batch License Status";
		privilege.displayCategory = "Freshwater Fishing";
		privilege.displaySubCategory = "Annual";
		privilege.customerClasses = new String[2];
		privilege.customerClasses[0] = "Individual";		
		privilege.customerClasses[1] = "Business";
		
		//fulfillment document info for 2012
		document1.docTepl = "AutoBatchLic01";
		document1.equipType = "All";
		document1.purchaseType = "Transfer";
		
		//fulfillment document info for 2015
		document2.docTepl = "AutoBatchLic02";
		document2.equipType = "All";
		document2.purchaseType = "Original";

		//license year info		
		this.resetLicneseYearInfo();
	}
	
	private void resetLicneseYearInfo(){
		ly1.status = "Active";
		ly1.locationClass = "03 - Lakes Offices";
		ly1.licYear = Integer.toString(DateFunctions.getCurrentYear());//lm.getFiscalYear(schema);
		
		ly1.productCode = privilege.code;
		ly1.productName = privilege.name;		
		
		ly1.numOfYearToAdd = "1";
		
		ly2.status = "Active";
		ly2.locationClass = "03 - Lakes Offices";
		ly2.licYear = Integer.toString(DateFunctions.getYearAfterCurrentYear(1));//(Integer.valueOf(lm.getFiscalYear(schema))+1)+"";
		
		ly2.productCode = privilege.code;
		ly2.productName = privilege.name;
		
		ly2.copyFromYear = ly2.licYear;
		ly2.newLicYear = "2023";
		ly2.numOfYearToAdd = "1";
		
		ly3.status = "Active";
		ly3.locationClass = "03 - Lakes Offices";
		ly3.licYear = Integer.toString(DateFunctions.getYearAfterCurrentYear(3));//(Integer.valueOf(lm.getFiscalYear(schema))+3)+"";
		
		ly3.sellFromDate = DateFunctions.getDateAfterToday(1, "EEE MMM dd yyyy");
		ly3.sellFromTime = "12:00";
		ly3.sellFromAmPm = "AM";
		
		ly3.sellToDate = DateFunctions.getDateAfterToday(2, "EEE MMM dd yyyy");
		ly3.sellToTime = "12:00";
		ly3.sellToAmPm = "PM";
		
		ly3.validFromDate = ly3.sellFromDate;
		ly3.validFromTime = ly3.sellFromTime;
		ly3.validFromAmPm = ly3.sellFromAmPm;

		ly3.validToDate = ly3.sellToDate;
		ly3.validToTime = ly3.sellToTime;
		ly3.validToAmPm = ly3.sellToAmPm; 
		
		ly3.productCode = privilege.code;
		ly3.productName = privilege.name;		
		
		ly3.numOfYearToAdd = "1";
	}
	
	/**
	 * get first license year number where we don't have any license year record for it.
	 * if privilegeCode and privilegeName are all Null, get first license year number where we don't have any licnese year for all privilege product.
	 * @return
	 */	
	private int getNoRecordLicneseYearForGivenPrivilegeProduct(String privilegeCode, String privilegeName){
		List<String> licYearInDb = lm.queryLicenseYearInfoForPrivilegeProduct(schema, privilegeCode, privilegeName);	
		List<String> licYear = priLicYearBatchAddPg.getCopyFromYearItems();
		
		//get first unavailable license year to copy from value used for getting warning message
		int lic = -1;
		
		for (int i = 0 ; i <licYear.size(); i ++){
			if(!licYearInDb.contains(licYear.get(i))){
				lic = Integer.parseInt(licYear.get(i));
				break;
			}
		}			
		
		if (lic == -1){
			throw new ErrorOnDataException("can't find a license year without license year Info in the DB, please inactive one licenese year to get a license year number where system don't have corresponding license year record");
		}
		
		return lic;
	}
}
