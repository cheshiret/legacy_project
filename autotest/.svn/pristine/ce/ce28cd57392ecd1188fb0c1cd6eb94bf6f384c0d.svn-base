/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.landowner.countyquantity;

import com.activenetwork.qa.awo.pages.orms.licenseManager.landowner.LicMgrLandownerViewCountyQtyListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:This advance case cover UI check and error validation in view list page
 * @Preconditions:
 * @LinkSetUp: d_assign_feature:id=5612
 * @SPEC:TC:068003
 * @Task#:Auto-2041
 * 
 * @author ssong
 * @Date  Mar 24, 2014
 */
public class ViewQtyList extends LicenseManagerTestCase{

	private LicMgrLandownerViewCountyQtyListPage listPg = LicMgrLandownerViewCountyQtyListPage.getInstance();
	private String type_empty_msg,ly_empty_msg;
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
		String tmp = listPg.getDefaultPrivlegeType();
		if(StringUtil.notEmpty(tmp)){
			throw new ErrorOnPageException("Default Privilege Type Not Empty.");
		}
		tmp = listPg.getDefaultLicenseYear();
		if(StringUtil.notEmpty(tmp)){
			throw new ErrorOnPageException("Default License Year Not Empty.");
		}
		listPg.checkPrivilegeTypeSorting();
		listPg.checkLicenseYearSorting();
	}
	
	private void vaildateError(){
		listPg.searchCountyQty("", "");
		String msg = listPg.getErrorMsg();
		if(!msg.equalsIgnoreCase(type_empty_msg)){
			throw new ErrorOnPageException("Privilege Type Empty Msg Not correct",type_empty_msg,msg);
		}
		
		listPg.searchCountyQty("LANDOWNER ARCHERY ANTLERLESS DEER", "");
		msg = listPg.getErrorMsg();
		if(!msg.equalsIgnoreCase(ly_empty_msg)){
			throw new ErrorOnPageException("License Year Empty Msg Not correct",ly_empty_msg,msg);
		}
	}
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MO Contract";
		login.location = "MO Admin - Auto/MO Department of Conservation";
		
		type_empty_msg = "Please specify a Landowner Privilege Type to continue.";
		ly_empty_msg = "Please specify a License Year to continue.";
	}

}
