/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.ticket.tourparticipant.orderdetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.AwoAjax;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityDetail.InvMgrFacilityDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrFacilityTourPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrParticipantDataPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourReservationDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 1. make ticket order to Tour Order Details page; 2. do
 *               verification in your Local Excute method; 3. make order to
 *               Reservation details page 4. verify TPa info is correct in Res
 *               details page 5. Void Ticket Order from VM;
 * @Preconditions:
 * @SPEC:'Per Ticket' attributes sorting [TC:041406]
 * @Task#:AUTO-1161, AUTO-2138
 * 
 * @author asun, SWang
 * @Date Jul 5, 2012, May 19, 2014
 */
public class PerTicketAttrSorting extends WebTestCase {
	protected TicketInfo ticket;
	protected LoginInfo loginInv = new LoginInfo();
	protected InventoryManager im = InventoryManager.getInstance();

	protected boolean isGroupTicket = false;
	protected UwpTourOrderDetailsPage ordDetailsPage = UwpTourOrderDetailsPage
			.getInstance();
	protected List<String> groupAndLabelsForPerTicket;
	protected List<List<String>> gropAndLabelsForPerInv;
	UwpTourReservationDetailsPage resDetailsPg = UwpTourReservationDetailsPage
			.getInstance();
	protected Map<String, String> msgMap = null;
	protected String key;
	List<String> attrLabels;
	String[] tpaNames = new String[] { "LastName", "FirstName", "DateofBirth",
			"EverVisit", "GenderInfo", "VisitedTime" };
	String[] sortOrders;
	String[] tpaLabelName = new String[] { "Last Name", "First Name",
			"Date of Birth", "Ever Visit?", "Gender", "Visited Times" };

	@Override
	public void execute() {

		sortOrders = new String[] { "1", "2", "3", "4", "5", "" };
		updateTPASortingOrderFromInventoryManager(false, tpaNames, sortOrders);
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookTourIntoTourSearchPg(ticket);
		web.bookTourIntoTourDetailsPgFromTourSearchPanel(ticket, isGroupTicket);
		web.bookTourIntoTourOrderDetailsPgFromTourDetailsPg(ticket.tourDate,
				ticket.park, ticket, ticket.isComboTour);
		this.verifySortingForAllSortingOrderIsZero(tpaLabelName, false);

		sortOrders = new String[] { "", "", "", "", "", "" };
		updateTPASortingOrderFromInventoryManager(false, tpaNames, sortOrders);
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookTourIntoTourSearchPg(ticket);
		web.bookTourIntoTourDetailsPgFromTourSearchPanel(ticket, isGroupTicket);
		web.bookTourIntoTourOrderDetailsPgFromTourDetailsPg(ticket.tourDate,
				ticket.park, ticket, ticket.isComboTour);
		this.verifySortingForAllSortingOrderIsZero(tpaLabelName, true);

	}

	/**
	 * 
	 */
	private void verifySortingForAllSortingOrderIsZero(String[] tpaLabelNames,
			boolean isAllZero) {

		List<String> tpaNamesForSorting = new ArrayList<String>();
		for (int i = 0; i < tpaLabelNames.length; i++) {
			tpaNamesForSorting.add(isAllZero ? tpaLabelNames[i].toUpperCase()
					: tpaLabelNames[i]);
		}

		if (isAllZero) {
			Collections.sort(tpaNamesForSorting);
		}

		attrLabels = ordDetailsPage
				.getPerTicketTourParticipantAttributeLabels(ticket.ticketTypes
						.get(0));

		if (tpaNamesForSorting.size() != attrLabels.size()) {
			throw new ErrorOnDataException(
					"the size of per ticket tpa is not equal");
		}

		for (int i = 0; i < tpaNamesForSorting.size(); i++) {
			if (!tpaNamesForSorting.get(i).equalsIgnoreCase(attrLabels.get(i))) {
				throw new ErrorOnPageException(
						"Sorting is wrong for perticket attribute",
						tpaNamesForSorting.toString(), attrLabels.toString());
			}
		}

	}

	public void updateTPASortingOrderFromInventoryManager(boolean isGroupsale,
			String[] tpaAttrNames, String[] sortOrders) {

		InvMgrFacilityTourPage facilityToursPg = InvMgrFacilityTourPage
				.getInstance();
		InvMgrFacilityDetailPage facDetailsPg = InvMgrFacilityDetailPage
				.getInstance();
		InvMgrParticipantDataPage participantDataPg = InvMgrParticipantDataPage
				.getInstance();
		logger.info("Clear cache in Inventory Manager for TPAs.");

		// im.updateTourTPAsSortOrderViaDB(schema, ticket.tourName,
		// (isGroupsale?"2":"1"), tpaAttrNames, sortOrders);

		im.loginInventoryManager(loginInv);
		im.gotoFacilityDetailPageById(ticket.parkId);
		im.gotoTourSetUpPg();
		if (ticket.isComboTour) {
			im.gotoComboTourDetailsPgFromFacilityTourPgByCode(ticket.tourCode);
		} else {
			im.gotoTourDetailsFromFacilityTourpageByCode(ticket.tourCode);
		}

		im.gotoParticipantDataPageFromTourDetailspage(ticket.isComboTour);
		participantDataPg.setIndividualAttrSortOrder(tpaAttrNames, sortOrders);
		facDetailsPg.clickOK();
		AwoAjax.getInstance().waitLoading();
		facilityToursPg.waitLoading();
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] param) {
		ticket = new TicketInfo();

		url = TestProperty.getProperty(env + WEB_URL_RECGOV);
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		// VOYAGEURS NATIONAL PARK TOURS
		ticket.parkId = "72777";
		ticket.contractCode = "NRSO";
		ticket.park = DataBaseFunctions.getFacilityName(ticket.parkId, schema);
		ticket.isUnifiedSearch = true;
		ticket.deliveryMethod = "Print at Home";

		ticket.tourName = "lchen 01";
		ticket.tourCode = "lchen 01";
		ticket.tourID = web.getTourIdFromDb(schema, ticket.parkId,
				ticket.tourName);
		ticket.ticketNums = "1";
		ticket.ticketTypes.add("School");
		ticket.deliveryMethod = "Print at Home";
		ticket.ticketTypeNums.add("1");

		loginInv.userName = TestProperty.getProperty("orms.im.user");
		loginInv.password = TestProperty.getProperty("orms.im.pw");
		loginInv.url = AwoUtil.getOrmsURL(env);
		loginInv.contract = "NRRS Contract";
		loginInv.location = "Administrator/NRRS";
	}

}
