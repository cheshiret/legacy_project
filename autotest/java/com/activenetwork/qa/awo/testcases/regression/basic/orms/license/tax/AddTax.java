package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.tax;

import com.activenetwork.qa.awo.datacollection.legacy.Tax;
import com.activenetwork.qa.awo.pages.orms.licenseManager.tax.LicMgrTaxDetialPg;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This cases verified flow for add edit a discount schedule for pos, and discount with flat discount rate type 
 * @LinkSetUp:  d_inv_add_mpos:id=760(PRODUCT='POS_For_Disc_1')
 * 			    d_inv_add_mpos:id=770(PRODUCT='POS_For_Disc_2')
 * 				d_fin_add_discount:id=1540(DISCOUNTNAME='Discount_For_POS_CustomerPass_Flat')
 * @SPEC:[TC:122328] Add Discount Schedule - POS Fee - Customer Pass 
 * 		 [TC:122341] Edit Discount Schedule - POS Fee - Customer Pass 
 * @Task#: Auto-2179
 * @author Phoebe
 * @Date  May 14, 2014
 */
public class AddTax extends LicenseManagerTestCase{
	private LicMgrTaxDetialPg detailPg = LicMgrTaxDetialPg.getInstance();
	private Tax tax = new Tax();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoTaxListPageFromRightTopMenu();
		
		tax.id = lm.addNewTax(tax);
		
		lm.gotoTaxDetailPage(tax.taxName);
		detailPg.verifyTaxInfo(tax);

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		String facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		//Set up fee schedule information
		//initialize Tax info
	  	tax.taxName = "ActivityTax"+DateFunctions.getCurrentTime();
	  	tax.taxCode = "AT";
	  	tax.taxDescription = "Automation Test";
	  	tax.taxRateType ="Percentage";
	  	tax.feeTypes.add("Activity Fee");
	  	tax.feeTypes.add("Transaction Fee");
	  	tax.feeTypes.add("Vendor Fee");
		
	}

}
