package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class MakePermitWithOldCustomer extends RecgovTestCase {
	private String email, pw, url, schema;
	private Customer cust;
	private PermitInfo bd;
	private boolean isRecgov = true;
	private String contract = "NRRS"; //for creating schema name
	private BWCooperator bw = BWCooperator.getInstance();

	public void execute() {
		web.invokeURL(url);
		//create a new account
		web.createAccount(cust);
		web.signOut();
		web.updateWebSeq(cust.email);
		
		//create an old login name which not using email addr
		String oldLoginName = cust.email.split("@")[0];
		oldLoginName = web.updateToOldLoginName(cust.email, oldLoginName);
		
		//make a permit reservation
		web.signIn(oldLoginName, pw);
		
		bw.makePermitOrderToCart(bd, isRecgov);
		String resId = bw.checkOutCart(pay);

		// change schema to verify order exists in db
		web.checkReservationExists(schema, resId);
		
		bw.gotoPermitDetailsFromHome(resId, isRecgov);
		bw.verifyOrderStatus("Confirmed");
		
		//update permit order
		bw.changeGroupSize(3,pay);
		bw.gotoPermitDetailsFromHome(resId, isRecgov);
		bw.verifyOrderStatus("Confirmed");
		
		// cancel permit order
		bw.cancelPermitOrderFromDetails();
		bw.gotoPermitDetailsFromHome(resId, isRecgov);
		bw.verifyOrderStatus("Cancelled");
		
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.generateNewWebEmail("old", env);
		pw = web.readQADB("OLD_PWD");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract;
		
		cust = new Customer();
		cust.setDefaultValuesForWeb(email, pw);

		bd = new PermitInfo();
		bd.facility = "Boundary Waters Canoe Area Wilderness (Reservations)";
		bd.groupSize = "2";
		bd.permitType = "Overnight Paddle";
		bd.entrance = "01 Trout Lake (op,om)";
		bd.isRange = false;
		bd.entryDate = DateFunctions.getDateAfterToday(10);
		bd.isUnifiedSearch=isUnifiedSearch();
		bd.contractCode="NRSO";
		bd.parkId="72600";
	}
}
