/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.landowner.countyquanity;

import java.util.HashMap;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.LandownerCountyQuantityAttr;
import com.activenetwork.qa.awo.pages.orms.licenseManager.landowner.LicMgrLandownerViewCountyQtyListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This is a basic case cover major flow of edit county quantity
 * @Preconditions:
 * @LinkSetUp: d_assign_feature:id=5612
 * @SPEC:TC:068002
 * @Task#:Auto-2041
 * 
 * @author ssong
 * @Date  Mar 24, 2014
 */
public class EditCountyQty extends LicenseManagerTestCase{

	private Data<LandownerCountyQuantityAttr> attr;
	private LicMgrLandownerViewCountyQtyListPage listPg = LicMgrLandownerViewCountyQtyListPage.getInstance();
	private String landowner_ptype_id;
	private HashMap<String, String> editValues;
	
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//clean up
		lm.deleteCountyQuantityForLy(landowner_ptype_id, attr.stringValue(LandownerCountyQuantityAttr.lICENSE_YEAR), schema);
		
		lm.gotoLandownerConfigFromTopMenu();
		lm.addCountyLicenseYearQuantity(attr);
		
		lm.editCountyQuantity(attr.stringValue(LandownerCountyQuantityAttr.lANDOWNER_PRIVILEGE_TYPE), attr.stringValue(LandownerCountyQuantityAttr.lICENSE_YEAR), editValues);
		
		listPg.checkQty(editValues);
		
		//clean up
		lm.deleteCountyQuantityForLy(landowner_ptype_id, attr.stringValue(LandownerCountyQuantityAttr.lICENSE_YEAR), schema);
				
		lm.logOutLicenseManager();		
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
		attr.put(LandownerCountyQuantityAttr.lANDOWNER_PRIVILEGE_TYPE, "LANDOWNER FALL TURKEY");
		landowner_ptype_id = "104";//Paired with above privilege type, hard code this ID,as this is not easy change one
		attr.put(LandownerCountyQuantityAttr.lICENSE_YEAR, String.valueOf(DateFunctions.getCurrentYear()));
		attr.put(LandownerCountyQuantityAttr.COPY_FROM_YEAR, false);
		attr.put(LandownerCountyQuantityAttr.ENTER_QUANTITY, true);
		attr.put(LandownerCountyQuantityAttr.QUANTITY, "4");
		
		editValues = new HashMap<>();
		editValues.put("Andrew", "1");
		editValues.put("Clinton", "3");
	}

}
