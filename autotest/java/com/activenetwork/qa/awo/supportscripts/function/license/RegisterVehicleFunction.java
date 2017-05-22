package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class RegisterVehicleFunction extends FunctionCase{
	LoginInfo login=new LoginInfo();
	private boolean loggedIn = false;// Don't login.
	private String preContract = "";
	private String preLocation = "";

	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private LicenseManager lm = LicenseManager.getInstance();
	private OrmsOrderSummaryPage ordSummaryPg = OrmsOrderSummaryPage.getInstance();
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private String schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
	private Customer cust = new Customer();
	private Payment pay = new Payment("Visa");
	private BoatInfo vehicle = new BoatInfo();
	private String vehNumber = "";

	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		cust = (Customer)param[1];
		vehicle = (BoatInfo)param[2];

		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
	}

	public void execute() {
		//License Manager only can switch in Home page
		if (!login.contract.equals(preContract) && loggedIn && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedIn= false;
		}
		if(login.contract.equals(preContract) && loggedIn && isBrowserOpened){
			if(!login.location.equals(preLocation)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedIn || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedIn= true;
		}
		if (!homePg.exists()) {
			lm.gotoHomePage();
		}

		preContract = login.contract;
		preLocation = login.location;

		lm.registerVehicleToOrderCart(cust, vehicle);
		lm.processOrderCartToOrderSummaryPage(pay, true);
		vehNumber = ordSummaryPg.getMINum();
		lm.finishOrder();

		verifyResule(vehicle.hullIdSerialNum);
		newAddValue = vehNumber;
	}

	private void verifyResule(String serialNum){
		boolean passed = true;
		db.resetSchema(schema);
		logger.info("Changed schema to -->>"+schema);
		String[] colNames = { "veh_number","ser_number"};

		String query = "select veh_number, ser_number from E_VEHICLE ";

		if(vehicle.type.equalsIgnoreCase("Boat"))//veh_type_id: 101:boat, 102: motor, 103: dealer 
		{
			query += "where SER_NUMBER = '"+vehicle.hullIdSerialNum+"'"
					+" and veh_type_id=101 and deleted_ind=0";
		}else if(vehicle.type.equalsIgnoreCase("Motor"))
		{
			query += "where SER_NUMBER = '"+vehicle.hullIdSerialNum+"'"
					+" and veh_type_id=102 and deleted_ind=0";
		}else if(vehicle.type.equalsIgnoreCase("Dealer"))
		{
			query += "where veh_number = '"+vehNumber+"'"
					+" and veh_type_id=103 and deleted_ind=0";
		}

		logger.info("Execute query: " + query);
		List<String[]> result = db.executeQuery(query, colNames);

		if(1==result.size())
		{
			logger.info("[PASSED]Register vehicle successful, hullIdSerialNum is:" + vehicle.hullIdSerialNum);
		}else{
			logger.info("[FAILED]Register vehicle failed, hullIdSerialNum is:" + vehicle.hullIdSerialNum);
			passed = false;
		}

		if(!passed){
			throw new ErrorOnPageException("Register vehicle failed, please see the log above!");
		}
	}
}
