package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.vendor.bond;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrEditVendorBondsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBondsSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This test case used to verify edit an existed bond, and view bond info in bond list and also bond detail info
 * @Preconditions: We need to prepare vendor with num 'VendorForBondBasic2', and issuers with name 'Bond Issuer for Adding Bond', 'Bond Issuer for Editing Bond';
 * @SPEC: Add Vendor Bond.doc & Edit Vendor Bond.doc
 * @Task#: Auto - 771
 * 
 * TODO: Add support script for AddBondIssuer.
 * @author jwang
 * @Date  Jan 6, 2012
 */
public class EditBond extends LicenseManagerTestCase {
	private VendorBondInfo bondInfo, updatedBondInfo;
	private LicMgrVendorBondsSubPage bondPg = LicMgrVendorBondsSubPage.getInstance();
	private String dateFormat = "EEE MMM d yyyy HH:mm a";
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromVendorsQuickSearch(bondInfo.belongVendorNum);
		lm.gotoBondSubTabFromVendorDetailPg();
		
		//deactivate all previous existing bond records
		this.deactivateAllActiveBonds();
		
		//add a new bond
		bondInfo.id  = lm.addVendorBond(bondInfo, true);
		
		//edit above bond
		//the original bond will be deactivated and a new bond will be created
		updatedBondInfo.id = bondInfo.id;
		updatedBondInfo.id = lm.editBondInfo(updatedBondInfo);
		
		bondInfo.status = "Inactive";
		bondInfo.lastModifiedUser = "Test-Auto, QA-Auto";
		bondInfo.lastModifiedDateTime = DateFunctions.formatDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)),dateFormat);
		
		//show all records to verify and also do clean up
		bondPg.showAllRecordsInList();
		//verify updated bond info in list
		verifyBondInList(updatedBondInfo);
		//verify updated bond info in widget by clicking bond id
		verifyBondDetails(updatedBondInfo);
		//verify original bond info in widget by clicking bond id
		verifyBondDetails(bondInfo);
		
		//clean up
		bondPg.deactiveBondByID(updatedBondInfo.id);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		bondInfo = new VendorBondInfo();
		bondInfo.status = OrmsConstants.ACTIVE_STATUS;
		bondInfo.belongVendorNum = "VendorForBondBasic2";
		bondInfo.issuer="Bond Issuer for Adding Bond";
		bondInfo.type="Bond";
		bondInfo.bondNum="002";
		bondInfo.bondAmount="10.00";
		bondInfo.effectiveFrom=DateFunctions.getDateAfterToday(-1);
		bondInfo.effectiveTo=DateFunctions.getDateAfterToday(3);
		bondInfo.creationUser = "Test-Auto, QA-Auto";
		bondInfo.creationDateTime = DateFunctions.formatDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)),dateFormat);
		
		updatedBondInfo = new VendorBondInfo();
		updatedBondInfo.status = OrmsConstants.ACTIVE_STATUS;
		updatedBondInfo.issuer = "Bond Issuer for Editing Bond";
		updatedBondInfo.type = "Letter of Credit";
		updatedBondInfo.bondNum = "005";
		updatedBondInfo.bondAmount = "12.00";
		updatedBondInfo.effectiveFrom = DateFunctions.getDateAfterToday(-3);
		updatedBondInfo.effectiveTo = DateFunctions.getDateAfterToday(5);		
		updatedBondInfo.creationUser = "Test-Auto, QA-Auto";
		updatedBondInfo.creationDateTime = DateFunctions.formatDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)),dateFormat);	
	}
	
	/**
	 * Verify bond info in bond list
	 * @param bondInfo
	 */
	private void verifyBondInList(VendorBondInfo bondInfo) {
		logger.info("Verify Bond info in bond list.");
		
		VendorBondInfo comapredBondInfo = bondPg.getBondInfoByID(bondInfo.id);
		boolean result = bondPg.comapareBondInfo(bondInfo, comapredBondInfo);
		if(result){
			logger.info("Verify bond info in bond list successfully");
		} else {
			throw new ErrorOnPageException("Error on bond id="+bondInfo.id+" in bond list, please check log for more details");
		}
		
	}
	
	/**
	 * Verify bond detailed info in bond widget
	 * @param bondInfo
	 */
	private void verifyBondDetails(VendorBondInfo bondInfo) {		
		LicMgrEditVendorBondsWidget bondWidget = LicMgrEditVendorBondsWidget.getInstance();
		
		logger.info("Go to bond detail info by id:"+bondInfo.id);
		bondPg.clickBondId(bondInfo.id);
		ajax.waitLoading();
		bondWidget.waitLoading();
		
		logger.info("Get compared bond info from bond widget");
		VendorBondInfo comapredBondInfo = bondWidget.getBondInfo();
		
		if(bondPg.comapareBondInfo(bondInfo, comapredBondInfo)){
			logger.info("Verify bond detail info from bond widget successfully");
		}else{
			throw new ErrorOnPageException("Error on bond id="+bondInfo.id+" in bond widget, please check log for more details");
		}
		
		bondWidget.clickCancel();
		ajax.waitLoading();
		bondPg.waitLoading();		
	}
	
	private void deactivateAllActiveBonds() {
		logger.info("Deactivate all previous existing bond records.");

		bondPg.showAllRecordsInList();
		bondPg.selectAllBonds();
		bondPg.clickDeactivate();
		ajax.waitLoading();
		bondPg.waitLoading();
	}
}
