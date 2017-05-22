/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.landowner.countyquantity;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.LandownerCountyQuantityAttr;
import com.activenetwork.qa.awo.pages.orms.licenseManager.landowner.LicMgrAddCountyLicenseYearQtyWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.landowner.LicMgrLandownerViewCountyQtyListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This advance case cover UI check and error validation in add county quantity page
 * @Preconditions:
 * @LinkSetUp: d_assign_feature:id=5612
 * @SPEC:TC:067841
 * @Task#:Auto-2041
 * 
 * @author ssong
 * @Date  Mar 27, 2014
 */
public class AddCountyQty extends LicenseManagerTestCase{

	private LicMgrLandownerViewCountyQtyListPage listPg = LicMgrLandownerViewCountyQtyListPage.getInstance();
	private LicMgrAddCountyLicenseYearQtyWidget addPg = LicMgrAddCountyLicenseYearQtyWidget.getInstance();
	private String type_empty_msg,ly_empty_msg,radio_empty,from_year_empty,enter_qty_empty,duplicate_county_qty_msg;
	private Data<LandownerCountyQuantityAttr> attr;
	private String landowner_ptype_id;
	private String current_year = String.valueOf(DateFunctions.getCurrentYear());
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.deleteCountyQuantityForLy(landowner_ptype_id, current_year, schema);
		
		lm.gotoLandownerConfigFromTopMenu();
		lm.addCountyLicenseYearQuantity(attr);
		listPg.clickAddCountyLicenseYearQty();
		vaildateError();
		checkUI();
		
		lm.logOutLicenseManager();
	}

	private void checkUI(){
		addPg.checkPrivilegeTypeSorting();
		//license year start from current year to 10 years later
		addPg.checkLicenseYearOptions();
		//select copy from, check input quantity input disabled
		addPg.selectCopyFromYear();
		ajax.waitLoading();
		boolean enable = addPg.checkQuantityInputEnable();
		if(enable){
			throw new ErrorOnPageException("After copy from Year selected, Enter quantity text field should be disabled.");
		}
		
		addPg.selectEnterQuantity();
		ajax.waitLoading();
		enable = addPg.checkFromYearEnable();
		if(enable){
			throw new ErrorOnPageException("After enter quantity selected, from year drop down should be disabled.");
		}
		//check cancel
		addPg.clickCancel();
		ajax.waitLoading();
		listPg.waitLoading();
	}
	
	private void vaildateError(){
		ajax.waitLoading();
		addPg.waitLoading();
		verifyErrorMsg(type_empty_msg);
		addPg.selectLandownerPrivilegeType(attr.stringValue(LandownerCountyQuantityAttr.lANDOWNER_PRIVILEGE_TYPE));
		ajax.waitLoading();
		verifyErrorMsg(ly_empty_msg);
		addPg.selectLicenseYear(current_year);
		verifyErrorMsg(duplicate_county_qty_msg);
		//clean up existing records
		lm.deleteCountyQuantityForLy(landowner_ptype_id, current_year, schema);
		verifyErrorMsg(radio_empty);
		addPg.selectCopyFromYear();
		ajax.waitLoading();
		verifyErrorMsg(from_year_empty);
		addPg.selectEnterQuantity();
		ajax.waitLoading();
		verifyErrorMsg(enter_qty_empty);
		addPg.setQuantity("-1");
		verifyErrorMsg(enter_qty_empty);
	}
	
	private void verifyErrorMsg(String expectMsg){
		addPg.clickOKAndWait();
		String msg = listPg.getErrorMsg();
		if(!msg.equalsIgnoreCase(expectMsg)){
			throw new ErrorOnPageException("Error Msg Not correct",expectMsg,msg);
		}
	}
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MO Contract";
		login.location = "MO Admin - Auto/MO Department of Conservation";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MO";

		attr = LandownerCountyQuantityAttr.init();
		attr.put(LandownerCountyQuantityAttr.lANDOWNER_PRIVILEGE_TYPE, "LANDOWNER ARCHERY DEER/TURKEY");
		landowner_ptype_id = "102";//Paired with above privilege type, hard code this ID,as this is not easy change one
		attr.put(LandownerCountyQuantityAttr.lICENSE_YEAR, current_year);
		attr.put(LandownerCountyQuantityAttr.COPY_FROM_YEAR, false);
		attr.put(LandownerCountyQuantityAttr.ENTER_QUANTITY, true);
		attr.put(LandownerCountyQuantityAttr.QUANTITY, "3");
		
		type_empty_msg = "Please specify a Landowner Privilege Type to continue.";
		ly_empty_msg = "Please specify a License Year to continue.";
		radio_empty = "Please select either 'Copy from Year' or 'Enter a Quantity' option to continue.";
		from_year_empty = "Please specify a Copy From License Year to continue.";
		enter_qty_empty = "Please enter a quantity that is an integer greater than zero to continue.";
		duplicate_county_qty_msg = "The specified Landowner Privilege Type and License Year combination already exists. Please check your entries.";
	}

}
