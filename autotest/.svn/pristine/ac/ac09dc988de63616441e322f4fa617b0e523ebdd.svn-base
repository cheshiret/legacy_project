package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartEndPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class ChangeTicketType extends VenueManagerTestCase {
	/**
	 * Script Name   : <b>VM_ChangeTicketType</b>
	 * Generated     : <b>Oct 23, 2009 1:41:57 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/10/23
	 * @author QA
	 */
	String addNumber = "";

	String preType = "";

	String aftType = "";

	String adultNum = "";

	String childNum = "";

	String youthNum = "";

	public void execute() {
		vm.loginVenueManager(login);
		vm.makeTicketOrderToCart(ticket);
		//pay.payType = "cash";
		vm.selectCustomerFromOrderCart(cust);
		String ordNum = vm.processOrderCart(pay,true,true);

		vm.gotoTiketOrderDetails(ordNum);
		getTypeNum();

		//vm.invalidTickets();
		vm.changeTicketType(preType, aftType, addNumber);
		//vm.processOrderCart(pay);
		this.processOrderCart(pay);

		vm.gotoTiketOrderDetails(ordNum);
		vm.invalidTickets();
		vm.changeTicketType(preType, aftType, addNumber);
		vm.processOrderCart(pay);
		
		vm.gotoTiketOrderDetails(ordNum);
		this.verifyChangeTypeSuccessful();

		vm.voidTicketToCart(ticket.voidReason, ticket.note);
		vm.processOrderCart(pay);

		vm.logoutVenueManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/YOUNG LAKE (SOUTH) CABIN";

		ticket.tour = "QA-REGRE-TEST";
		ticket.startDate = DateFunctions.getDateAfterToday(2);
		ticket.types = new String[3];
		ticket.typeNums = new String[3];
		ticket.types[0] = "Adult";
		ticket.types[1] = "Child";
		ticket.types[2] = "Youth";
		ticket.typeNums[0] = "2";
		ticket.typeNums[1] = "1";
		ticket.typeNums[2] = "1";
		ticket.quantity = "4";
		addNumber = "1";
		preType = "Child";
		aftType = "Adult";

		ticket.voidReason = "Other";
		ticket.note = "QA Automation";
	}
	
	public void processOrderCart(Payment pay){
	    OrmsOrderCartPage vmOrdCartPg = OrmsOrderCartPage.getInstance();
	    OrmsOrderCartEndPage vmOrdCartCancelPg=OrmsOrderCartEndPage.getInstance();
	    VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
	    
	    vmOrdCartPg.setupPayment(pay);
		vmOrdCartPg.clickProcessOrder();
		
		vmOrdCartPg.waitLoading();
		if(!vmOrdCartPg.getWarningMessage().matches("Please invalidate the following printed tickets.*")){
		   throw new ItemNotFoundException("Vaild waring message display incorrect");
		}
		vmOrdCartPg.clickCancelCart();
		
		vmOrdCartCancelPg.waitLoading();
		vmOrdCartCancelPg.clickCancelCart();
		vmHmPg.waitLoading();
		
	}

	/**
	 * Get the count of different type
	 * 
	 * */
	public void getTypeNum() {
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		adultNum = vmTicketDetailPg.getTypeNum("Adult");
		childNum = vmTicketDetailPg.getTypeNum("Child");
		youthNum = vmTicketDetailPg.getTypeNum("Youth");
	}

	/**
	 * The previous number reduce the modified number
	 * the modified number is addNumber
	 * the @param is the type will be modified.
	 * */
	public String subTypesNum(String type) {
		String subNumber = "";
		int addNum = Integer.parseInt(addNumber);
		int typeNum = Integer.parseInt(type);
		typeNum = typeNum - addNum;
		subNumber = String.valueOf(typeNum);

		return subNumber;
	}

	/**
	 * The latter number add the modified number
	 * */
	public String addTypesNum(String type) {
		String addedNumber = "";
		int addNum = Integer.parseInt(addNumber);
		int typeNum = Integer.parseInt(type);
		typeNum = typeNum + addNum;
		addedNumber = String.valueOf(typeNum);

		return addedNumber;
	}

	/**
	 * Compared the number of different modified type 
	 * */
	public void verifyChangeTypeSuccessful() {
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		String changedAdult = vmTicketDetailPg.getTypeNum("Adult");
		String changeChild = vmTicketDetailPg.getTypeNum("Child");
		String changeYouth = vmTicketDetailPg.getTypeNum("Youth");
		if (preType.equals("Adult")) {
			adultNum = subTypesNum(adultNum);
		} else if (preType.equals("Child")) {
			childNum = subTypesNum(childNum);
		} else if (preType.equals("Youth")) {
			youthNum = subTypesNum(youthNum);
		}

		if (aftType.equals("Adult")) {
			adultNum = addTypesNum(adultNum);
		} else if (aftType.equals("Child")) {
			childNum = addTypesNum(childNum);
		} else if (aftType.equals("Youth")) {
			youthNum = addTypesNum(youthNum);
		}

		if (!changedAdult.equals(adultNum)) {
			throw new ItemNotFoundException(
					"Adult number changed unsuccessfully.");
		}
		if (!changeChild.equals(childNum)) {
			throw new ItemNotFoundException(
					"Child number changed unseccessfully.");
		}
		if (!changeYouth.equals(youthNum)) {
			throw new ItemNotFoundException(
					"Youth number changed unseccessfully.");
		}
	}
}
