package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description:This test case was used to verify River Campsite Utilization Report which run on Resource Manager.
 * This report will be requested by each River Permit facility once a year after the end of the Peak Reservation season of the Rivers.  
 * The report contains 2 separate tabs containing an overall summary and an outfitter summary.
 * 
 * Please note that the campsite information is found under the Trip Plan permit type attribute of the Permit Order. 
 * Not All permit orders shall have the Trip Plan information as an applicable Permit type attribute. 
 * One Campsite can be assigned multiple times on the Trip Plan for a single permit order. 
 * 
 * Refer SQL to get related order numbers
 * 
select ord.ord_num order_Number,  ord.sales_cat_id salesCatID, pmtTypeName.name permitTypeName, attrValue.attr_value travelPlanAttrValue, 
pmtType.permit_cat_id permitCatID, cust.l_name cooperatorName 
from o_order ord 
inner join o_ord_item item on ord.id = item.ord_id 
inner join p_prd prd on item.prd_id = prd.prd_id 
inner join o_ord_invc invc on ord.ord_invc_id = invc.id 
inner join c_cust cust on invc.cust_id = cust.cust_id 
inner join p_entrance_permit_type ept on item.permit_type_id = ept.id 
inner join p_permit_type pmtType on ept.permit_type = pmtType.id 
inner join p_permit_type_name pmtTypeName on pmtType.permit_type_name_id = pmtTypeName.id 
inner join o_ord_item_attr_value attrValue on item.id = attrValue.ord_item_id 
where ord.ord_status_id = 1
and prd.product_cat_id = 5
and attrvalue.attr_id = 2512
and ord.sales_cat_id in ( 3, 4) 
and pmtType.permit_cat_id in ( 0, 3, 4) 
and prd.park_id = 75534 
and ord.proc_status_id in (8, 9) 
and( trunc(item.start_date) >= to_date('2010-06-01', 'yyyy-mm-dd') and trunc(item.start_date) <= to_date('2010-06-30', 'yyyy-mm-dd')
  or trunc(item.end_date) >= to_date('2010-06-01', 'yyyy-mm-dd') and trunc(item.end_date) <= to_date('2010-06-30', 'yyyy-mm-dd') ) 
and replace(replace(attrValue.attr_value, '-1', ''), ',', '') is not null 
order by ord.sales_cat_id desc, pmtType.permit_cat_id desc, pmtTypeName.name;
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC: 
 * Details of Report Request-Facility(RM) [TC:045110] 
 * Details of Report Request-Cooperator [TC:045112] 
 * Details of Report Request-Order Reservation Status [TC:045113] 
 * Details of Report Reques-Start Date [TC:045114]
 * Details of Report Request-End Date [TC:045115]
 * Details of Report Request-Report Period [TC:045116] 
 * Details of Report Reques-Output Format [TC:045117] 
 * Details of Report Reques-Delivery Method [TC:045118] 
 * Report Title [TC:045092]
 * Run Date and Time-Resource Manager [TC:045093] 
 * Location [TC:045095] 
 * Cooperator/Outfitter [TC:045096] 
 * Order Reservation Status [TC:045097] 
 * Start Date [TC:045098] 
 * End Date [TC:045099]
 * @Task#: Auto-2277
 * 
 * @author Jane Wang
 * @Date  August 02, 2014
 */
public class RiverCampsiteUtilizationReport extends ResourceManagerTestCase {

	public void execute() {
		rm.loginResourceManager(login);

		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);// XLS EMAIL
		result = rm.verifyEmailReport(templatesPath, 
				rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "All";
		rd.reportName = "River Campsite Utilization Report";
		rd.facilityID = "MIDDLE FORK OF THE SALMON (4 Rivers)";
		rd.startDate = "2010-6-1";
		rd.endDate = "2010-6-30";
		
		rd.emailSubject = rd.reportName + DateFunctions.getCurrentTime();
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";

	}

}
