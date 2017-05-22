/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.pricing;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePricingPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:1. Deactivate all active pricings...
 * 				2. Add 3 pricings for privilege product.(States info: [All]/[AP]/[AA,AE])
 * 			    3. Check value of column 'State/Provinces' in each record is correct. 
 * 
 * @Preconditions: 1. Privilege code 'pig'(multistatepricing), table: D_HF_ADD_PRIVILEGE_PRD, ID=1720
 * 				   	
 * @SPEC: PCR 2940
 * @Task#: AUTO-1237(TC042509,042700)
 * 
 * @author pzhu
 * @Date  Oct 31, 2012
 */

public class AddPrivilegePricingWithMultiState extends LicenseManagerTestCase {

	
	private PricingInfo pricingAll=new PricingInfo();//All states
	private PricingInfo pricingAP=new PricingInfo();//State: 'Armed Forces Pacific'(AP):73
	private PricingInfo pricingMulti=new PricingInfo();//State: 'Armed Forces Americas'(AA):71 and 'Armed Forced Other'(AE):72
	
	private ILicMgrProductPricingPage pricingPage = null;
	private LicMgrPrivilegePricingPage pricingListPg = LicMgrPrivilegePricingPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		pricingPage = lm.gotoProductPricingPageFromListPage(pricingAll.prodType,	pricingAll.prodCode);
		//Step 1: Deactivate all active pricings...
		lm.deactivateAllProductPricings(pricingPage);
		
		//Step 2: Add 3 pricings which have different States.
		pricingAll.id = lm.addPricingForProduct(pricingAll, pricingPage);
		logger.info("New pricing ID for pricingAll is -->"+this.pricingAll.id);
		
		pricingAP.id = lm.addPricingForProduct(pricingAP, pricingPage);
		logger.info("New pricing ID for pricingAP is -->"+this.pricingAP.id);
		
		pricingMulti.id = lm.addPricingForProduct(pricingMulti, pricingPage);
		logger.info("New pricing ID for pricingMulti is -->"+this.pricingMulti.id);
		
		//Step 3: Get all pricing records.
		List<PricingInfo> pricingRecords = pricingListPg.getAllRecordsOnPage();
		
		//Check Point: check value of column 'State/Provinces' is correct.
		this.checkPricingRecords(pricingRecords);
				
		
		lm.logOutLicenseManager();
	}



	/**
	 * @param rs 
	 * 
	 */
	private void checkPricingRecords(List<PricingInfo> rs) {
		
		for(PricingInfo record: rs)
		{
			if(record.id.equalsIgnoreCase(this.pricingAll.id))
			{
				if(!record.stateProvinces.equalsIgnoreCase(this.pricingAll.stateProvinces))
				{
					throw new ErrorOnPageException("Value of column 'State/Provinces' is wrong for pricing ID-->"+this.pricingAll.id+", ", this.pricingAll.stateProvinces, record.stateProvinces);
				}else{
					logger.info("Check display of pricing("+this.pricingAll.stateProvinces+") ID-->"+this.pricingAll.id+" Passed!!!");
				}
			}
			
			if(record.id.equalsIgnoreCase(this.pricingAP.id))
			{
				if(!record.stateProvinces.equalsIgnoreCase(this.pricingAP.stateProvinces))
				{
					throw new ErrorOnPageException("Value of column 'State/Provinces' is wrong for pricing ID-->"+this.pricingAP.id+", ", this.pricingAP.stateProvinces, record.stateProvinces);
				}else{
					logger.info("Check display of pricing("+this.pricingAP.stateProvinces+") ID-->"+this.pricingAP.id+" Passed!!!");
				}
			}
			
			if(record.id.equalsIgnoreCase(this.pricingMulti.id))
			{
				if(!record.stateProvinces.equalsIgnoreCase(this.pricingMulti.stateProvinces))
				{
					throw new ErrorOnPageException("Value of column 'State/Provinces' is wrong for pricing ID-->"+this.pricingMulti.id+", ", this.pricingMulti.stateProvinces, record.stateProvinces);
				}else{
					logger.info("Check display of pricing("+this.pricingMulti.stateProvinces+") ID-->"+this.pricingMulti.id+" Passed!!!");
				}
			}
		}
		
	}



	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//pricing for all states.
		pricingAll.prodType = "Privilege";
		pricingAll.prodCode = "pig";
		pricingAll.status = "Active";
		pricingAll.locationClass = "All";
		pricingAll.licYearFrom = "ALL";
		pricingAll.purchaseType = "Original";
		pricingAll.effectFrom = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		pricingAll.vendorFee ="10";
		pricingAll.stateTransFee = "5";
		pricingAll.stateFee = "5";
		pricingAll.transFee = "6";
		pricingAll.isAppliesToAllState = true;
		pricingAll.stateProvinces = "All";
		

		//pricing for state:Armed Forces Pacific(AP):73
		pricingAP.prodType = "Privilege";
		pricingAP.prodCode = "pig";
		pricingAP.status = "Active";
		pricingAP.locationClass = "All";
		pricingAP.licYearFrom = "ALL";
		pricingAP.purchaseType = "Replacement";
		pricingAP.effectFrom = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		pricingAP.vendorFee ="10";
		pricingAP.stateTransFee = "5";
		pricingAP.stateFee = "5";
		pricingAP.transFee = "6";
		pricingAP.isAppliesToAllState = false;
		pricingAP.addState = "AP";
		pricingAP.stateProvinces = "AP";
		
		
		//pricing for 2 States: 'Armed Forces Americas'(AA):71 and 'Armed Forced Other'(AE):72
		pricingMulti.prodType = "Privilege";
		pricingMulti.prodCode = "pig";
		pricingMulti.status = "Active";
		pricingMulti.locationClass = "All";
		pricingMulti.licYearFrom = "ALL";
		pricingMulti.purchaseType = "Transfer";
		pricingMulti.effectFrom = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		pricingMulti.vendorFee ="10";
		pricingMulti.stateTransFee = "5";
		pricingMulti.stateFee = "5";
		pricingMulti.transFee = "6";
		pricingMulti.isAppliesToAllState = false;
		pricingMulti.addState = "AA,AE";
		pricingMulti.stateProvinces = "Multi";
		
		
	}
	
}
