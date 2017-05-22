package com.activenetwork.qa.awo.testcases.production.orms;

import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class VM_AbandonCartTicketing_CA extends VenueManagerTestCase {
	/**
	 * Script Name   : <b>CM_AbandonCartCamping</b>
	 * Generated     : <b>Aug 6, 2009 3:47:44 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/08/06
	 * @author vzhao
	 */
	public void execute() {
		vm.loginVenueManager(login);
		vm.makeTicketOrderToCart(ticket);
		vm.cancelCart();
		vm.logoutVenueManager();
	}

	public void wrapParameters(Object[] param) {
		
		if(env.equalsIgnoreCase("live")) {
			AwoUtil.loadLiveInformation();
		  	login.userName = TestProperty.getProperty(env+".orms.fm.user");
			login.password = TestProperty.getProperty(env+".orms.fm.pw");
		} else {
			login.userName = TestProperty.getProperty("orms.fm.user");
			login.password = TestProperty.getProperty("orms.fm.pw");
		}
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "CA Contract";
		login.location = "CA - Venue Manager Admin/Hearst Castle";

		ticket.startDate = DateFunctions.getDateAfterToday(14);
		ticket.quantity = "1";
		ticket.types = new String[2];
		ticket.typeNums = new String[2];
		ticket.types[0] = "Adult";
		ticket.typeNums[0] = "2";
		ticket.types[1] = "Child";
		ticket.typeNums[1] = "2";
	}
}
