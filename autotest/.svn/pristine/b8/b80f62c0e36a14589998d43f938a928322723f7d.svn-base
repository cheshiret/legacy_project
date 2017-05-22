package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class AdvancedReservationByFacilityReport extends
		ResourceManagerTestCase {
	/**
	 * Script Name : <b>RM_rptAdvancedReservationByFacilityReport</b> Generated
	 * : <b>Jul 20, 2009 6:04:13 AM</b> Description : Functional Test Script
	 * Original Host : WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2009/07/20
	 * @author QA
	 * 
	 *         we can use SQL followd to find the total Reservation from DB:
	 *         select * from o_order, O_ORD_ITEM where o_order.id =
	 *         o_ord_item.ord_id and o_ord_item.start_date >='1-JUL-07' and
	 *         o_ord_item.start_date<='10-JUL-07' and o_order.ord_status_id not
	 *         in (2,3,4) --1: check out, 2: Cancelled, 3: Void, 4: No Show, and
	 *         o_order.ord_type_id = 108054 -- 108054: Reservation , 124:
	 *         registration/walk in and o_order.loc_id=10120 order by
	 *         o_order.ord_num desc
	 */

	public void execute() {
		rm.loginResourceManager(login);

		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);

		rd.dateType = "Order Date";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result &= rm.skipVerifyOnlineReport(templatesPath, fileName);

		rm.verifyReportCorrect(result);

		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		rd.group = "Operational Reports";
		rd.reportName = "Advanced Reservations by Facility Report";
		rd.agencyID = "SC parks";
		rd.regionID = "All";
		rd.facilityID = "All";
		rd.startDate = "07/01/2007";
		rd.endDate = "07/10/2007";
		rd.productGroup = "All";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Online";
		rd.dateType = "Arrival Date";
	}
}

