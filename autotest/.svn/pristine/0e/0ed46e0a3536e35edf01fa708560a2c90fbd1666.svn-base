package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.vendor.bond;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBondsSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This test case used to verify bond list by search criteria
 * @Preconditions: We need to prepare vendor with num 'VendorForBondBasic3', and issuers with name 'Bond Issuer for Viewing Bond-1' and 'Bond Issuer for Viewing Bond-2'
 * @SPEC: View Vendor Bond List.doc
 * @Task#: Auto - 771
 * 
 * @author jwang
 * @Date  Dec 24, 2011
 */
public class ViewBondListByFilter extends LicenseManagerTestCase{
	private VendorBondInfo bondInfo1,bondInfo2;
	private LicMgrVendorBondsSubPage vendorBondPg = LicMgrVendorBondsSubPage.getInstance();
	private boolean pass = true;
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromVendorsQuickSearch(bondInfo1.belongVendorNum);
		lm.gotoBondSubTabFromVendorDetailPg();
		
		//deactivate all previous existing bond records
		this.deactivateAllActiveBonds();
		
		//add 2 vendor bond as preconditions
		bondInfo1.id = lm.addVendorBond(bondInfo1, true);
		bondInfo2.id = lm.addVendorBond(bondInfo2, true);

		vendorBondPg.setupSearchCriteria(false,"null",true,true,true,true);
		//verify the combination of those five options
		verifyBondList("null",true,true,true,true);
		
		//verify issuer option
		vendorBondPg.setupSearchCriteria(false,bondInfo1.issuer,false,false,false,false);
		verifyBondList(bondInfo1.issuer,false,false,false,false);
		
		//verify active option
		vendorBondPg.setupSearchCriteria(false,"null",true,false,false,false);
		verifyBondList("null",true,false,false,false);
		
		//verify type 'Letter of Credit' option
		vendorBondPg.setupSearchCriteria(false,"null",false,false,false,true);
		verifyBondList("null",false,false,false,true);
		
		if(!pass){
			throw new ErrorOnPageException("Error Message Not Correct,please check Error Log.");
		}
		
		vendorBondPg.deactiveBondByID(bondInfo1.id);
		vendorBondPg.deactiveBondByID(bondInfo2.id);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		bondInfo1 = new VendorBondInfo();
		bondInfo1.status=OrmsConstants.ACTIVE_STATUS;
		bondInfo1.issuer="Bond Issuer for Viewing Bond-1";
		bondInfo1.type="Bond";
		bondInfo1.bondNum="001";
		bondInfo1.bondAmount="10.00";
		bondInfo1.effectiveFrom=DateFunctions.getDateAfterToday(-1);
		bondInfo1.effectiveTo=DateFunctions.getDateAfterToday(3);
		bondInfo1.belongVendorNum = "VendorForBondBasic3";
		
		bondInfo2 = new VendorBondInfo();
		bondInfo2.status=OrmsConstants.ACTIVE_STATUS;
		bondInfo2.issuer="Bond Issuer for Viewing Bond-2";
		bondInfo2.type="Letter of Credit";
		bondInfo2.bondNum="002";
		bondInfo2.bondAmount="15.00";
		bondInfo2.effectiveFrom=DateFunctions.getDateAfterToday(-3);
		bondInfo2.effectiveTo=DateFunctions.getDateAfterToday(5);
	}
		
	/**
	 * verify bond info for each column
	 * @param id
	 * @param bondInfo
	 * @return
	 */
	private void verifyBondList(String issuer, boolean includeActive, boolean includeInActive, boolean includeBond, boolean includeLetterOfCredit) {
		
		List<VendorBondInfo> comparedBond = new ArrayList<VendorBondInfo>();
		if(includeActive && !includeInActive){
			comparedBond = vendorBondPg.getAllBonds(true);
		}else{
			comparedBond = vendorBondPg.getAllBonds(false);
		}
		
		for(int i=0;i<comparedBond.size();i++){
			if(includeActive && !includeInActive && comparedBond.get(i).status.equalsIgnoreCase("Inactive")){
				logger.error("Bond status displays uncorrectly, check bond info by id:"+comparedBond.get(i).id);
				pass &= false;
				break;
			}
			if(!issuer.equalsIgnoreCase("null")&& !comparedBond.get(i).issuer.startsWith(issuer)){
				logger.error("Bond issuer displays uncorrectly, check bond info by id:"+comparedBond.get(i).id);
				pass &= false;
				break;
			}
			if(includeBond && !includeLetterOfCredit &&!comparedBond.get(i).type.equals("Bond")){
				logger.error("Bond type displays uncorrectly, check bond info by id:"+comparedBond.get(i).id);
				pass &= false;
				break;
			}
			
			if(includeLetterOfCredit && !includeBond &&!comparedBond.get(i).type.equals("Letter of Credit")){
				logger.error("Bond type displays uncorrectly, check bond info by id:"+comparedBond.get(i).id);
				pass &= false;
				break;
			}
			logger.info("Item "+i+" in bond list page displays correctly");			
		}
	}
	
	private void deactivateAllActiveBonds() {
		logger.info("Deactivate all previous existing bond records.");

		vendorBondPg.showAllRecordsInList();
		vendorBondPg.selectAllBonds();
		vendorBondPg.clickDeactivate();
		ajax.waitLoading();
		vendorBondPg.waitLoading();
	}
}
