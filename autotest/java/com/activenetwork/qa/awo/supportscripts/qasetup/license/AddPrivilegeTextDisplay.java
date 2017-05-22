package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeTextDisplay;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddPrivilegeTextDisplayFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Aug 6, 2014
 */
public class AddPrivilegeTextDisplay extends SetupCase{
	private PrivilegeTextDisplay textInfo = new PrivilegeTextDisplay();
	private Object[] args = new Object[3];
	private AddPrivilegeTextDisplayFunction func = new AddPrivilegeTextDisplayFunction();

	@Override
	public void executeSetup() {
		func.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "D_HF_ADD_PRI_TEXT_DISPLAY";
		ids = "20,30,40,50";
	}

	@Override
	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");

		textInfo.privilegeCode = datasFromDB.get("PRICODE");
		textInfo.displayType = datasFromDB.get("DISPLAYTYPE");
		textInfo.text = datasFromDB.get("TEXT");
		textInfo.effectiveFromDate = datasFromDB.get("EFFECTIVEFROMDATE");
		if(StringUtil.isEmpty(textInfo.effectiveFromDate))
			textInfo.effectiveFromDate = DateFunctions.getToday();
		textInfo.effectiveToDate = datasFromDB.get("EFFECTIVETODATE");
		if(StringUtil.isEmpty(textInfo.effectiveToDate))
			textInfo.effectiveToDate = DateFunctions.getDateAfterGivenDay(textInfo.effectiveFromDate, 365);
		args[2] = textInfo;
	}
}
