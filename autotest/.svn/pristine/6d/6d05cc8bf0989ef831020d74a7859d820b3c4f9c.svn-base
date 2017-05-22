package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue.viewticketordercharges;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSChargeListInOrderDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: This case check the charge pos information in a ticket order, the main work flow is:
 *       1. Go to Venue Manager, charge a pos to a ticket order.
 *       2. Go to this ticket reservation detail page, click 'Charges' tab, check the charge pos info.
 *       3. Return one of the charged pos which belong to this permit order, make sure there is unissued Refund in the charge pos order.
 *       4. Go to this ticket reservation detail page, click 'Charges' tab, check the charge pos info.
 * @Preconditions: 
 *       1. A tour named "QA-REGRE-TEST" has been set up, so does the tour inventory and ticket fee schedule
 *       2. "Charge POS" button is enable in ticket order detail page:
 *              Administrator-NRRS - NRRS     NRRS - Venue Supervisor  VenueManager ChargePOSToReservation
 *       3. A POS product named "POS_FORPUCHARS_1", to add the product in venue manager, following feature should be assigned:
 *              Administrator-NRRS - NRRS     NRRS - Venue Supervisor  VenueManager AddPOSProduct
 *              Administrator-NRRS - NRRS     NRRS - Venue Supervisor  VenueManager AssignPOSProductSetup
 *       4. Pos quantity should can be edited:
                Administrator-NRRS - NRRS     NRRS - Venue Supervisor  VenueManager ModifyPOSQty
 * Note:Blocked by defect DEFECT-36811(for the return pos step),the defect has been fixed
 * @Task#:AUTO-1248 
 * @author Phoebe
 * @Date September 12, 2012
 */
public class NormalChargeAndReturn extends VenueManagerTestCase{
    private POSInfo pos = new POSInfo();
    private OrmsOrderSummaryPage vmOrdSumPg = OrmsOrderSummaryPage.getInstance();
    private Map<String, String> expectInfo = new HashMap<String, String>();
    private String saleDate;
    private String facilityName;
	@Override
	public void execute() {
		//login venue manager
		vm.loginVenueManager(login);
		
		if(!vm.verifyProductExistInSys(schema, pos.productCode, pos.product)) {
			vm.gotoPOSProductSetupPage();
			vm.addPOSProduct(pos);
		}
		
		//Buy a ticket
		vm.makeTicketOrderToCart(ticket);
		String ticketReservNum =vm.processOrderCart(pay);
		
		//Go to ticket order detail page and charge a POS
		vm.gotoTiketOrderDetails(ticketReservNum);	
		vm.chargePOSForTicketReservationToOrderCart(pos);
		pay.payType = "Money Order";
		pay.checkNumber = "12345678";
		String resNums  = vm.processOrderCartToSummaryPg(pay);
		this.setExpectPosInfo(resNums);
		vm.finishOrder();
		
		//Verify the pos info under charge lable page
		vm.gotoTiketOrderDetails(ticketReservNum);	
		vm.gotoTicketPosChargesListPage();
		this.verifyPOSChargesInfo();
		
		//Return one of the pos
		vm.gotoPOSChargeDetailPage(pos.ordID);
		vm.returnPOSToCart(pos);
		vm.processOrderCartToSummaryPg(pay);
		this.setReturnPosInfo();
		vm.finishOrder();
		
		//Verify return info
		vm.gotoTiketOrderDetails(ticketReservNum);	
		vm.gotoTicketPosChargesListPage();
		this.verifyPOSChargesInfo();
		
		//Clean up
		vm.gotoTiketOrderDetails(ticketReservNum);
		vm.invalidTickets();
		vm.cancelTicketToCart(true," ");
		vm.processOrderCart(pay);

		vm.logoutVenueManager();
	}

	private void setExpectPosInfo(String resNums) {
		expectInfo.put("orderStatus", "Active"); 
		expectInfo.put("saleDate", saleDate);
		expectInfo.put("saleLocation", facilityName);
		Map<String, String> getPayInfo = vmOrdSumPg.getPayInfo(false, false);
		pos.ordID = resNums.split(" ")[1];		
		expectInfo.put("saleNum", pos.ordID);
		expectInfo.put("Price", getPayInfo.get("Price"));
		expectInfo.put("Paid", getPayInfo.get("Paid"));
		expectInfo.put("Balance", getPayInfo.get("Balance"));
	}
	
	private void setReturnPosInfo() {
		expectInfo.put("orderStatus", "Active"); 
		expectInfo.put("saleDate", saleDate);
		expectInfo.put("saleLocation", facilityName);
		Map<String, String> getPayInfo = vmOrdSumPg.getPayInfo(true, false);
		expectInfo.put("Price", getPayInfo.get("Price"));
		expectInfo.put("Paid", getPayInfo.get("Paid"));
		expectInfo.put("Balance", getPayInfo.get("Balance"));		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NRRS Contract";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		String facilityID = "71746";  
		facilityName = vm.getFacilityName(facilityID, schema);		
		TimeZone timeZone = DataBaseFunctions.getParkTimeZone(schema, facilityName);
		
		login.location = "NRRS - Venue Supervisor/" + facilityName; //YOUNG LAKE (SOUTH) CABIN;
		
		ticket.tour = "qa reg test 1";
		ticket.category = "Individual";
		ticket.startDate = DateFunctions.getDateAfterToday(3);
		ticket.timeSlot = "2:00";
		ticket.quantity = "1";
		ticket.types = new String[] { "Adult" };
		ticket.typeNums = new String[] { "1" };	
		
		ticket.voidReason = "Other";
		ticket.note = "QA Automation";
		
		pos.product = "POS_FORPUCHARS_1";
		pos.quantity = "3";
		pos.returnQty = "1";	
		pos.reason = "6 - General Return";
		pos.note = "Auto QA regression test";
		
		pos.productCode = "PF1";
		pos.unitPrice = "12";
		pos.productDescription = pos.product;
		pos.productGroup = "1/2 PRICE VISITOR PASS";
		pos.displayOrder = "0";
		pos.effectiveSalesStartDate = DateFunctions.getToday(timeZone);
		pos.effectiveSalesEndDate = DateFunctions.getDateAfterGivenMonth(pos.effectiveSalesStartDate, 12 * 50);
		
		saleDate = DateFunctions.getToday(timeZone);
	}
	
	private void verifyPOSChargesInfo() {
		boolean passed = true;
		OrmsPOSChargeListInOrderDetailPage chargeLabel = OrmsPOSChargeListInOrderDetailPage.getInstance();
		Map<String, String> ordInfo = chargeLabel.getPOSSaleDetail(pos.ordID);
		for(Map.Entry<String, String> info:ordInfo.entrySet()){
			passed &= MiscFunctions.compareResult("Compare "+ info.getKey(),expectInfo.get(info.getKey()), info.getValue());
		}
		if(!passed){
			throw new ErrorOnPageException("Records info of pos charge, id:'"+ pos.ordID + "' is not correct, see the detail log above!"); 
		}
	}
        
}
