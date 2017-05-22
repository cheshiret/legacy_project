package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue.viewticketordercharges;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSChargeListInOrderDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: This case check the charge pos information in a ticket order after adjust fee for the pos order, the main work flow is:
 *       1. Go to Venue Manager, charge a pos to a tiket order and make the charge pos orders are partial payment.
 *       2. Go to this ticket reservation detail page, click 'Charges' tab, check the charge pos info.
 *       3. Return the charge pos which belong to this ticket order and make sure there is unissued Refund in the charge pos order.
 *       4. Go to this ticket reservation detail page, click 'Charges' tab, check the charge pos info.
 * @Preconditions: 
 *       1. A tour named "QA-REGRE-TEST" has been set up, so does the tour inventory and ticket fee schedule
 *       2. "Charge POS" button is enable in ticket order detail page:
 *              Administrator-NRRS - NRRS     NRRS - Venue Supervisor  VenueManager ChargePOSToReservation
 *       3. A POS product named "POS_FORCHARGE", to add the product in venue manager, following feature should be assigned:
 *              Administrator-NRRS - NRRS     NRRS - Venue Supervisor  VenueManager AddPOSProduct
 *       4. Minimal pay has been set up for the pos product:http://wiki.reserveamerica.com/display/dev/Minimum+to+Confirm+Setup
 *          INSERT INTO P_MIN_PMT_CFM (ID, EFFECTIVE_DATE, LOC_ID, PRD_GRP_ID, PRD_ID, ACTIVE_ID, DELETE_ID,SALES_CHANL_ID,PRD_CAT_ID,TICKET_CAT_ID)      
 *           VALUES (get_sequence('P_MIN_PMT_CFM'), TO_DATE('04/04/2014', 'MM/DD/YYYY'), 71746, 82963404, 327879, '1', '0', 4, 4,0);
 *           INSERT INTO P_MIN_PMT_ENTRY_CFM (ID, P_MIN_PMT_CFM_ID, PRIORITY, ORDER_ITEM_TYPE_ID, FEE_TYPE_ID, RULE_TYPE, AMOUNT)         
 *             VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'), 1213500189, 0, 0, 2,--pos fee       
 *                                                                            1,--percent             
 *       5. "Void" button is enabled in pos order detail page:
                Administrator-NRRS - NRRS    NRRS - Venue Supervisor  VenueManager  VoidPOSSale                                                                              0.2);   
 * @Task#:AUTO-1248 
 * @author Phoebe
 * @Date September 12, 2012
 */
public class ChargePosWithPartialPaymentAndVoid  extends VenueManagerTestCase{
    private POSInfo pos = new POSInfo();
    private OrmsOrderSummaryPage vmOrdSumPg = OrmsOrderSummaryPage.getInstance();
    private Map<String, String> expectInfo = new HashMap<String, String>();
    private String saleDate;
    private String facilityName;
    String facilityID;
	@Override
	public void execute() {
		//login venue manager
		vm.loginVenueManager(login);
		
		if(!vm.verifyProductExistInSys(schema, pos.productCode, pos.product)) {
			vm.gotoPOSProductSetupPage();
			vm.addPOSProduct(pos);
			this.setupMinimumPayment();
		}else{
			if(!isMinimumPaymentSet()){
				this.setupMinimumPayment();
			}
		}
		
		//Buy a ticket
		vm.makeTicketOrderToCart(ticket);
		String ticketReservNum =vm.processOrderCart(pay);
		
		//Go to ticket order detail page and charge a POS
		vm.gotoTiketOrderDetails(ticketReservNum);	
		vm.chargePOSForTicketReservationToOrderCart(pos);
		String resNums  = vm.processOrderCartToSummaryPg(pay);
		this.setExpectPayInfo(resNums);
		vm.finishOrder();
		
		//Go to pos charges label page in ticket order detail page and verify the pos charge information
		vm.gotoTiketOrderDetails(ticketReservNum);	
		vm.gotoTicketPosChargesListPage();
		this.verifyPOSChargesInfo();
		
		//Void the pos
		vm.gotoPOSChargeDetailPage(pos.ordID);
		vm.voidPosSales(pos.reason, pos.note);
		vm.processOrderCart(pay);
		this.setVoidExpectInfo();
		
		//Verify the void pos order
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

	private void setExpectPayInfo(String resNums) {
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
	
	private void setVoidExpectInfo() {
		expectInfo.put("orderStatus", "Void"); 
		expectInfo.put("saleDate", saleDate);
		expectInfo.put("saleLocation", facilityName);
		expectInfo.put("Price", "$0.00");
		expectInfo.put("Paid", "$0.00");
		expectInfo.put("Balance", "$0.00");		
	}
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NRRS Contract";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		facilityID = "71746";  
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
		
		pos.product = "POS_FORCHARGE";
		pos.productCode = "PF0";
		pos.quantity = "3";
		pos.unitPrice = "12";
		pos.productDescription = pos.product;
		pos.productGroup = "1/2 PRICE VISITOR PASS";
		pos.displayOrder = "0";
		pos.effectiveSalesStartDate = DateFunctions.getToday(timeZone);
		pos.effectiveSalesEndDate = DateFunctions.getDateAfterGivenMonth(pos.effectiveSalesStartDate, 12 * 50);
		pos.reason = "3 - General Void";
		pos.note = "Auto QA regression test";
		
		saleDate = DateFunctions.getToday();
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
	
	//Set minimum payment for the pos product
	private void setupMinimumPayment(){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String queryPrdInfo = "select prd_id, prd_grp_id from p_prd where prd_name='" + pos.product + "' and prd_cd='" + pos.productCode + "'";
		List<HashMap<String, String>> prdInfo = db.executeQuery(queryPrdInfo);
		                                                                            
        String sql = "INSERT INTO P_MIN_PMT_CFM (ID, EFFECTIVE_DATE, LOC_ID, PRD_GRP_ID, PRD_ID, ACTIVE_ID, DELETE_ID,SALES_CHANL_ID,PRD_CAT_ID,TICKET_CAT_ID)"
				+ "VALUES (get_sequence('P_MIN_PMT_CFM'), TO_DATE('04/04/2014', 'MM/DD/YYYY')," + facilityID + "," + prdInfo.get(0).get("PRD_GRP_ID") + ","
				+ prdInfo.get(0).get("PRD_ID") + ", '1', '0', 4, 4,0)";
        logger.info("Run sql:" + sql);
		db.executeUpdate(sql);
		sql = "select max(ID) as newId from P_MIN_PMT_CFM";
		logger.info("Run sql:" + sql);
		List<String> newId = db.executeQuery(sql, "NEWID");
		sql = "INSERT INTO P_MIN_PMT_ENTRY_CFM (ID, P_MIN_PMT_CFM_ID, PRIORITY, ORDER_ITEM_TYPE_ID, FEE_TYPE_ID, RULE_TYPE, AMOUNT)" +         
		             "VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'),"+newId.get(0)+", 0, 0, 2,1,0.2)";
		logger.info("Run sql:" + sql);
		db.executeUpdate(sql);
	}
	
	private boolean isMinimumPaymentSet(){
		boolean set = false;
		List<String[]> minPaySets = vm.getMinimumPaymentRuleInfo(schema,PRDCAT_POS, facilityID, pos.productGroup,pos.product);
		if(minPaySets.size() < 1){
			set = false;
		}else{
			set = true;
		}
		return set;
	}
}

