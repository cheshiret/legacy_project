/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.landowner.countyquanity;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.LandownerCountyQuantityAttr;
import com.activenetwork.qa.awo.pages.orms.licenseManager.landowner.LicMgrLandownerViewCountyQtyListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This is a basic case cover major flow of add county quantity,first add for current year, then check copy from year for next year
 * @Preconditions:
 * @LinkSetUp: d_assign_feature:id=5612
 * @SPEC:TC:067841
 * @Task#:Auto-2041
 * 
 * @author ssong
 * @Date  Mar 24, 2014
 */
public class AddCountyQty extends LicenseManagerTestCase{

	private Data<LandownerCountyQuantityAttr> attr;
	private LicMgrLandownerViewCountyQtyListPage listPg = LicMgrLandownerViewCountyQtyListPage.getInstance();
	private String landowner_ptype_id;
	private String current_year = String.valueOf(DateFunctions.getCurrentYear());
	private String next_year = String.valueOf(DateFunctions.getCurrentYear()+1);
	
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//clean up
		lm.deleteCountyQuantityForLy(landowner_ptype_id, current_year, schema);
		lm.deleteCountyQuantityForLy(landowner_ptype_id, next_year, schema);
		
		lm.gotoLandownerConfigFromTopMenu();
		lm.addCountyLicenseYearQuantity(attr);
		listPg.checkQty(attr.stringValue(LandownerCountyQuantityAttr.QUANTITY));
		lm.checkCountyQuantityForLyFromDB(landowner_ptype_id, attr.stringValue(LandownerCountyQuantityAttr.lICENSE_YEAR), attr.stringValue(LandownerCountyQuantityAttr.QUANTITY), schema);
		
		//update data and check copy from year function
		attr.save(LandownerCountyQuantityAttr.lICENSE_YEAR, next_year);
		attr.save(LandownerCountyQuantityAttr.COPY_FROM_YEAR, true);
		attr.put(LandownerCountyQuantityAttr.ENTER_QUANTITY, false);
		attr.put(LandownerCountyQuantityAttr.FROM_YEAR, current_year);
		
		//after add, the filters should be filled with add parameters automatic and displayed all added records
		lm.addCountyLicenseYearQuantity(attr);
		listPg.checkQty(attr.stringValue(LandownerCountyQuantityAttr.QUANTITY));
		lm.checkCountyQuantityForLyFromDB(landowner_ptype_id, attr.stringValue(LandownerCountyQuantityAttr.lICENSE_YEAR), attr.stringValue(LandownerCountyQuantityAttr.QUANTITY), schema);
		
		//clean up
		lm.deleteCountyQuantityForLy(landowner_ptype_id, current_year, schema);
		lm.deleteCountyQuantityForLy(landowner_ptype_id, next_year, schema);
				
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
		attr.put(LandownerCountyQuantityAttr.lANDOWNER_PRIVILEGE_TYPE, "LANDOWNER SPRING TURKEY");
		landowner_ptype_id = "105";//Paired with above privilege type, hard code this ID,as this is not easy change one
		attr.put(LandownerCountyQuantityAttr.lICENSE_YEAR, current_year);
		attr.put(LandownerCountyQuantityAttr.COPY_FROM_YEAR, false);
		attr.put(LandownerCountyQuantityAttr.ENTER_QUANTITY, true);
		attr.put(LandownerCountyQuantityAttr.QUANTITY, "3");
	}

}
