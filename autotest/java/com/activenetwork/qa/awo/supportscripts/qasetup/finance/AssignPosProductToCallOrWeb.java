/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AssignPOSToCallWebFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This support script was designed to assign pos product to call center/web
 * Assignto column could be Call Center or Web
 * @Preconditions: 
 * (1)This support script should be run after supportscripts.qasetup.inventory.AddMasterPosProduct
 * (2)There is a POS product
 * Feature AssignCallCenterPOSProductSetup and AssignWebPOSProductSetup have been assigned to Administrator role
 * (please refer records in table D_ASSIGN_FEATURE)
 * @SPEC:
 * @Task#:
 * 
 * @author Jane Wang
 * @Date  Jul 5, 2012
 */
public class AssignPosProductToCallOrWeb extends SetupCase {

	private LoginInfo login=new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
	private POSInfo pos = new POSInfo();
	private String assignChannel = "";
	private AssignPOSToCallWebFunction func = new AssignPOSToCallWebFunction();

	public void executeSetup() {
		Object[] args = new Object[4];
		args[0] = login;
		args[1] = assignChannel;
		args[2] = pos;
		
		func.execute(args);
	}

	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		String schema = TestProperty.getProperty(env + ".db.schema.prefix")+ login.contract.replaceAll("Contract", "").trim();
		
		pos.product = datasFromDB.get("productname");
		pos.productID = fnm.getProductID("Product Name", pos.product, null, schema);
		pos.unitPrice = datasFromDB.get("unitPrice");
		pos.variablePriceAllowed = datasFromDB.get("variablePriceAllowed");
		pos.partialQuantityAllowed = datasFromDB.get("partialQuantityAllowed");
		String startDate = datasFromDB.get("effSalesStartDate");
		if(StringUtil.isEmpty(startDate)){
			startDate = DateFunctions.getToday();
		}
		pos.effectiveSalesStartDate = startDate;
		String endDate = datasFromDB.get("effSalesEndDate");
		if(StringUtil.isEmpty(startDate)){
			endDate = DateFunctions.getDateAfterToday(365);
		}
		pos.effectiveSalesEndDate = endDate;
		assignChannel = datasFromDB.get("assignto");
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_assi_pos";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
		
	}
		
}
