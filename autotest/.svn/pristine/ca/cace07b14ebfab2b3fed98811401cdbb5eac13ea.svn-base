/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.landowner.countyquantity;

import com.activenetwork.qa.awo.pages.orms.licenseManager.landowner.LicMgrEditLandownerCountyQtyPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.landowner.LicMgrLandownerViewCountyQtyListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This advance case cover UI check and error validation in edit county quantity page
 * @Preconditions:
 * @LinkSetUp: d_assign_feature:id=5612
 * @SPEC:TC:068002
 * @Task#:Auto-2041
 * 
 * @author ssong
 * @Date  Mar 27, 2014
 */
public class CheckEditCountQty extends LicenseManagerTestCase{

	private LicMgrLandownerViewCountyQtyListPage listPg = LicMgrLandownerViewCountyQtyListPage.getInstance();
	private LicMgrEditLandownerCountyQtyPage editPg = LicMgrEditLandownerCountyQtyPage.getInstance();
	
	private String invalid_msg,changed_county,changed_qty;
	private String last_year = String.valueOf(DateFunctions.getCurrentYear()-1);
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoLandownerConfigFromTopMenu();
		checkDefaultUI();
		vaildateError();
		
		lm.logOutLicenseManager();
	}

	private void checkDefaultUI(){
		listPg.searchCountyQty("LANDOWNER ARCHERY ANTLERLESS DEER", last_year);
		listPg.clickEditQuantity();
		ajax.waitLoading();
		editPg.waitLoading();
		if(editPg.isAddCountyLicenseYearQtyEnable()){
			throw new ErrorOnPageException("Add County Quantity Button Should be Disabled in Edit Page.");
		}
		if(editPg.isChangeHistoryBtnEnable()){
			throw new ErrorOnPageException("Change History Button Should be Disabled in Edit Page.");
		}
		if(editPg.isLandownerPrivilegeTypeEnable()){
			throw new ErrorOnPageException("Privilege Type DropDown Should be Disabled in Edit Page.");
		}
		if(editPg.isLicenseYearEnable()){
			throw new ErrorOnPageException("License Year DropDown Should be Disabled in Edit Page.");
		}
		if(editPg.isGoBtnEnable()){
			throw new ErrorOnPageException("Go Button Should be Disabled in Edit Page.");
		}
		
	}
	
	private void vaildateError(){
		editPg.editCountyQty(changed_county, changed_qty);
		verifyErrorMsg(invalid_msg);
		changed_qty = "-1";
		editPg.editCountyQty(changed_county, changed_qty);
		verifyErrorMsg(invalid_msg);
		changed_qty = "100";
		editPg.editCountyQty(changed_county, changed_qty);
		editPg.clickCancel();
		ajax.waitLoading();
		if(listPg.checkQtyCorrect(changed_county, changed_qty)){
			throw new ErrorOnPageException("Cancel Button not work, edit the county qty success.");
		}
	}
	
	private void verifyErrorMsg(String expectMsg){
		editPg.clickSave();
		ajax.waitLoading();
		String actualMsg = editPg.getErrorMsg();
		if(!actualMsg.equalsIgnoreCase(expectMsg)){
			throw new ErrorOnPageException("Error Message Not Correct.",expectMsg,actualMsg);
		}
	}
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MO Contract";
		login.location = "MO Admin - Auto/MO Department of Conservation";
		
		changed_county = "Barry";
		changed_qty = "";
		invalid_msg = "Please enter a quantity for "+changed_county+" that is an integer greater than or equal to zero to continue.";
	}

}

