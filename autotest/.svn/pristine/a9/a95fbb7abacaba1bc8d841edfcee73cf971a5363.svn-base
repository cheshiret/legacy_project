package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddDisplayCategoriesFunction;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Aug 7, 2012
 */
public class AddDisplayCategories extends SetupCase{
	private PrivilegeDisplayCategory displayCategory = new PrivilegeDisplayCategory();
	private Object[] args = new Object[3];
	private AddDisplayCategoriesFunction func = new AddDisplayCategoriesFunction();
	
	@Override
	public void executeSetup() {
		func.execute(args);
	}

	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_display_categories";		
	}

	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");

		displayCategory.description=datasFromDB.get("description");
		displayCategory.displayOrder=datasFromDB.get("displayOrder");
		displayCategory.hiddenInSalesFlow=datasFromDB.get("hiddenInSalesFlow");
		args[2] = displayCategory;
	}
}
