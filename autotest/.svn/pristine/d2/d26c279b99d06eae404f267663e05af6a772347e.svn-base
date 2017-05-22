package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddSubDisplayCategoryFunction;
/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Aug 7, 2012
 */
public class AddSubDisplayCategories extends SetupCase{
	private Object[] args;
	private AddSubDisplayCategoryFunction func = new AddSubDisplayCategoryFunction();
	
	@Override
	public void executeSetup() {
		func.execute(args);
	}

	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_subdisplay_categories";
	}

	public void readDataFromDatabase() {
		args = new Object[4];
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");

		args[2] = datasFromDB.get("description");
		args[3] = datasFromDB.get("displayOrder");
	}
}

