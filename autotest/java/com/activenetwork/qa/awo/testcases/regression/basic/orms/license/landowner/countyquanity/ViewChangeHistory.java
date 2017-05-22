/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.landowner.countyquanity;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.LandownerCountyQuantityAttr;
import com.activenetwork.qa.awo.pages.orms.licenseManager.landowner.LicMgrLandownerCountyQtyChangeHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This is a basic case cover major flow of view change history,first add,then edit and check change history
 * @Preconditions:
 * @LinkSetUp: d_assign_feature:id=5612
 * @SPEC:TC:068004
 * @Task#:Auto-2041
 * 
 * @author ssong
 * @Date  Mar 24, 2014
 */
public class ViewChangeHistory extends LicenseManagerTestCase{

	private Data<LandownerCountyQuantityAttr> attr;
	private String landowner_ptype_id,changedCounty,changedQty;
	private String current_year = String.valueOf(DateFunctions.getCurrentYear());
	private List<List<String>> expectInfos;
	
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
		
		lm.editCountyQuantity(attr.stringValue(LandownerCountyQuantityAttr.lANDOWNER_PRIVILEGE_TYPE), attr.stringValue(LandownerCountyQuantityAttr.lICENSE_YEAR), changedCounty,changedQty);
		lm.gotoChangeHistoryPg();
		checkChangeHistory();
		
		//clean up
		lm.deleteCountyQuantityForLy(landowner_ptype_id, attr.stringValue(LandownerCountyQuantityAttr.lICENSE_YEAR), schema);
				
		lm.logOutLicenseManager();
	}
	
	private void checkChangeHistory(){
		LicMgrLandownerCountyQtyChangeHistoryPage historyPg = LicMgrLandownerCountyQtyChangeHistoryPage.getInstance();
		
		historyPg.searchCountyQty(attr.stringValue(LandownerCountyQuantityAttr.lANDOWNER_PRIVILEGE_TYPE), attr.stringValue(LandownerCountyQuantityAttr.lICENSE_YEAR));
		List<List<String>> actualInfos = historyPg.getChangeHistory();
		boolean compareResult = false;
		for(int i=0;i<expectInfos.size();i++){
			compareResult = actualInfos.get(i).containsAll(expectInfos.get(i));
			if(!compareResult){
				throw new ErrorOnPageException("Change History Info Not Correct.");
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MO";
		String location = lm.getFacilityName("650001", schema);
		
		login.contract = "MO Contract";
		login.location = "MO Admin - Auto/"+location;

		attr = LandownerCountyQuantityAttr.init();
		attr.put(LandownerCountyQuantityAttr.lANDOWNER_PRIVILEGE_TYPE, "LANDOWNER DEER");
		landowner_ptype_id = "103";//Paired with above privilege type, hard code this ID,as this is not easy change one
		attr.put(LandownerCountyQuantityAttr.lICENSE_YEAR, current_year);
		attr.put(LandownerCountyQuantityAttr.COPY_FROM_YEAR, false);
		attr.put(LandownerCountyQuantityAttr.ENTER_QUANTITY, true);
		attr.put(LandownerCountyQuantityAttr.QUANTITY, "2");
		
		changedCounty = "Caldwell";
		changedQty = "3";
		
		expectInfos = new ArrayList<>();
		List<String> addLogs = new ArrayList<>();
		List<String> editLogs = new ArrayList<>();
		
		//object
		String object = attr.stringValue(LandownerCountyQuantityAttr.lANDOWNER_PRIVILEGE_TYPE)+" ("+current_year+")";
		addLogs.add(object);
		editLogs.add(object+" "+changedCounty);
		//action
		addLogs.add("Add");
		editLogs.add("Update");
		//field
		addLogs.add("");
		editLogs.add("Quantity");
		//Old value
		addLogs.add("");
		editLogs.add("2");
		//new value
		addLogs.add("");
		editLogs.add("3");
		//user
		String user = DataBaseFunctions.getLoginUserName(login.userName).replaceAll(",", ", ");
		addLogs.add(user);
		editLogs.add(user);
		//location
		addLogs.add(location);
		editLogs.add(location);
		
		expectInfos.add(editLogs);
		expectInfos.add(addLogs);
	}

}
