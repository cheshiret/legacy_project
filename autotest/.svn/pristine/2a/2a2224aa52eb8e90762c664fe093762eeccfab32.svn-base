package com.activenetwork.qa.awo.keywords;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.OrmsConstants.AttrName;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData.DiscountSchdInfo;
import com.activenetwork.qa.awo.datacollection.legacy.FinSession;
import com.activenetwork.qa.awo.datacollection.legacy.FinSession.FloatAdjustment;
import com.activenetwork.qa.awo.datacollection.legacy.FinSession.OpeningFloat;
import com.activenetwork.qa.awo.datacollection.legacy.LotterySchedule;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.datacollection.legacy.feeData.RaFeeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoiceTransactionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoiceTransmissionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoicingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTRemittanceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTReturnTransactionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.TaxInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ReceiptInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.EFTRemittanceTransInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipContractInfo;
import com.activenetwork.qa.awo.keywords.orms.Orms;
import com.activenetwork.qa.awo.keywords.web.UWP;
import com.activenetwork.qa.awo.pages.GeneralSignOutPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsHomePage;
import com.activenetwork.qa.awo.pages.web.recgov.ForeseeSurveyAdminPage;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.ConstantsInterpreter;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.LatitudeLongitude;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.InvalidDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.browser.IBrowser;
import com.activenetwork.qa.testapi.keyword.Keyword;
import com.activenetwork.qa.testapi.page.DialogPage;
import com.activenetwork.qa.testapi.page.FileDownloadDialogPage;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.CryptoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Email;
import com.activenetwork.qa.testapi.util.LimitedQueue;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public abstract class Awo extends Keyword implements OrmsConstants {
	protected AwoDatabase db = AwoDatabase.getInstance();
	protected LimitedQueue<String> urlTrack = new LimitedQueue<String>(3);
	protected static AutomationLogger logger = AutomationLogger.getInstance();
	protected String env;
	protected IBrowser browser;

	private final int HOLD = 1001;
	private final int DEPARTED = 1004;

	private final int BOOKED = 1000;
	private final int OCCUPIED = 1003;

	/** initialize some variables for DB. */
	protected Awo() {
		db = AwoDatabase.getInstance();
		env = TestProperty.getProperty("target_env").toLowerCase();
		browser = Browser.getInstance();
	}

	private void sycDB() {
		env = TestProperty.getProperty("target_env").toLowerCase();
	}

	/**
	 * Read QA_AUTOMATION table to get the value of the given VAR name
	 * 
	 * @param var
	 * @return the var value
	 */
	public String readQADB(String var) {
		db.resetDefaultDB();
		String env = TestProperty.getProperty("target_env");
		String colName = "val_" + env;
		String queryString = "select " + colName
				+ " from qa_automation where var = '" + var + "'";
		String toReturn = null;
		List<String> resultList = db.executeQuery(queryString, colName);
		if (null != resultList && resultList.size() > 0) {
			if (!StringUtil.isEmpty(resultList.get(0))) {
				toReturn = resultList.get(0).trim();
			} else {
				toReturn = "";
			}
			logger.info("----Get VAL is: " + toReturn);
		} else {
			toReturn = "";
			logger.error("----Can't get VAL which VAR is:" + var);
		}
		return toReturn;
	}

	/**
	 * Write QA_AUTOMATION table for column VAR with given value
	 * 
	 * @param var
	 * @param val
	 */
	public void writeQADB(String var, String val) {
		logger.info("Update " + var + " value as " + val
				+ " into table QA_AUTOMATION.");
		db.resetDefaultDB();
		String sql = "";
		String env = TestProperty.getProperty("target_env");
		sql = "update qa_automation set val_" + env + " = '" + val
				+ "' where var = '" + var + "'";
		int i = db.executeUpdate(sql);
		if (i < 1) {
			sql = "insert into qa_automation (var, val_" + env + ") values ('"
					+ var + "', '" + val + "')";
			db.executeUpdate(sql);
		}
	}

	public void updateWebSeq(String email) {
		String seq = RegularExpression.getMatches(email, "[0-9]{5,}")[0];
		writeQADB("WEB_SEQ", seq);
	}

	/**
	 * Check if the given reservation exists in the given schema.
	 * ItemNotFoundException will be thrown if the expected reservation was not
	 * found.
	 * 
	 * @param schema
	 * @param resID
	 *            - the reservation number
	 */
	public void checkReservationExists(String schema, String resID) {
		if (!checkOrderExists(schema, resID)) {
			throw new ItemNotFoundException("The expected reservation#" + resID
					+ " doesn't exist in DB");
		}
	}

	public boolean checkOrderExists(String schema, String ordNum) {
		db.resetSchema(schema);

		String query = "select count(*) as num from o_order where ord_num = \'"
				+ ordNum + "\'";

		String num = db.executeQuery(query, "NUM", 0);

		int count = Integer.parseInt(num);

		return count > 0;
	}

	public boolean checkPrivilegeNumberExists(String schema, String privNum) {
		db.resetSchema(schema);

		String sql = "select count(*) as NUM from O_PRIV_INST where PRIV_NUMBER = \'"
				+ privNum + "\'";
		String num = db.executeQuery(sql, "NUM", 0);
		int count = Integer.parseInt(num);

		return count > 0;
	}

	/**
	 * This method used to check partner invoice Number exists from DB
	 * 
	 * @param schema
	 * @param invoice_num
	 * @return
	 */
	public boolean checkInvoiceExist(String schema, String invoice_num) {
		db.resetSchema(schema);

		String query = "select count(*) as num from f_PRTN_INVC where id = \'"
				+ invoice_num + "\'";

		String num = db.executeQuery(query, "NUM", 0);

		int count = Integer.parseInt(num);

		return count > 0;
	}

	/**
	 * Check if the given email exists in the given schema
	 * 
	 * @param schema
	 * @param email
	 * @return
	 */
	public boolean checkEmailExists(String schema, String email) {
		db.resetSchema(schema);

		String query = "select count(*) as num from c_cust_phone where typ=\'EMAIL\' and val= \'"
				+ email + "\'";

		String num = db.executeQuery(query, "NUM", 0);

		int count = Integer.parseInt(num);

		return count > 0;
	}

	/**
	 * Check if the given login name exists in the given schema
	 * 
	 * @param schema
	 * @param name
	 * @return
	 */
	public boolean checkLoginNameExists(String schema, String name) {
		db.resetSchema(schema);

		String query = "select count(*) as num from c_cust where login_name= \'"
				+ name.toLowerCase() + "\'";

		String num = db.executeQuery(query, "NUM", 0);

		int count = Integer.parseInt(num);

		return count > 0;
	}

	public String getProductID(String byCondition, String value,
			String facilityID, String schema) {
		return getProductID(byCondition, value, facilityID, true, schema, null);
	}

	public String getSlipProductID(String byCondition, String value,
			String facilityID, String schema) {
		return getProductID(byCondition, value, facilityID, true, schema, "20");
	}

	public String getProductID(String byCondition, String value,
			String facilityID, boolean active, String schema) {
		return getProductID(byCondition, value, facilityID, active, schema,
				null);
	}

	public String getProductID(String value, String facilityID, String schema) {
		return getProductID("Product Code", value, facilityID, true, schema,
				null);
	}

	public String getPrdIDByPrdName(String value, String facilityID,
			String schema) {
		return getProductID("Product Name", value, facilityID, true, schema,
				null);
	}

	/**
	 * Get the product id by product name or product code or another conditions.
	 * 
	 * @param byCondition
	 *            - the value should be Product Name or Product Code
	 * @param value
	 *            - the corresponding value of the byCondition
	 * @param facilityID
	 * @param schema
	 * @parm prdType 3 - site 20 - slip
	 * @return product id
	 */
	public String getProductID(String byCondition, String value,
			String facilityID, boolean active, String schema, String prdType) {
		sycDB();
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema);
		String queryRelease = "";

		if (byCondition.equalsIgnoreCase("Product Name")) {
			// query only with prd_name
			if (null == facilityID || facilityID.length() < 1) {
				queryRelease = "select prd_id from p_prd where prd_name='"
						+ value + "'";
			} else {
				queryRelease = "select prd_id from p_prd where park_id="
						+ facilityID + " and prd_name='" + value + "'";
			}
		} else if (byCondition.equalsIgnoreCase("Product Code")) {
			if (null == facilityID || facilityID.length() < 1) {
				queryRelease = "select prd_id from p_prd where prd_cd='"
						+ value + "'";
			} else {
				queryRelease = "select prd_id from p_prd where park_id="
						+ facilityID + " and prd_cd='" + value + "'";
			}
		}

		queryRelease += " and DELETED_IND=0";
		if (active) {
			queryRelease += " and ACTIVE_IND=1";
		}
		if (prdType != null) {
			queryRelease += " and PRODUCT_CAT_ID=" + prdType;
		}
		logger.info("Execute query: " + queryRelease);
		List<String> productIDs = db.executeQuery(queryRelease, "prd_id");
		String productId = "";
		if (productIDs.size() == 0) {
			throw new ActionFailedException(
					"Can't find any record by your condition. Please double check the parameters.");
		} else {
			productId = productIDs.get(0);
			logger.info("Get product id: " + productId);
		}

		return productId;
	}

	public String getPOSSupplierIDByName(String schema, String name) {
		db.resetSchema(schema);
		logger.info("Try to get supplier ID by Name from DB...");
		String sql = "SELECT ID FROM D_SUPPLIER WHERE upper(NAME) = '"
				+ name.toUpperCase() + "' ORDER BY ID";
		logger.info("Execute query: " + sql);
		List<String> ids = db.executeQuery(sql, "ID");
		if (ids.size() < 1) {
			return null;
		}

		return ids.get(0);
	}

	public String getProductNameById(String schema, String id) {
		db.resetSchema(schema);
		String sql = "select PRD_NAME from P_PRD where PRD_ID=" + id;
		logger.info("Execute query: " + sql);
		List<String> names = db.executeQuery(sql, "PRD_NAME");
		if (names.size() < 1) {
			throw new ErrorOnDataException(
					"Can't find any record identified by product id - " + id);
		}

		return names.get(0);
	}

	/**
	 * Get the days to be booked with the site
	 * 
	 * @param productID
	 *            -- product id of the site to be booked
	 * @param arrivalDate
	 *            -- arrival date -1
	 * @param toDate
	 *            -- depart date+1
	 * @param schema
	 * @return the days to be booked
	 */
	public int getBookedOccupiedDaysCount(String productID, String arrivalDate,
			String toDate, String schema, String bookedOrOccupied) {
		sycDB();
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema);
		String queryRelease = "";
		int num = -1;

		if (bookedOrOccupied.equalsIgnoreCase("BOOKED")) {
			queryRelease = "Select * from i_inv_used where prd_id=" + productID
					+ " and from_time>=TO_DATE('" + arrivalDate
					+ "','mm-dd-yyyy') " + "and from_time<=TO_DATE('" + toDate
					+ "','mm-dd-yyyy') " + "and usage_type_id=" + BOOKED;

			num = db.executeUpdate(queryRelease);
		} else if (bookedOrOccupied.equalsIgnoreCase("OCCUPIED")) {
			queryRelease = "Select * from i_inv_used where prd_id=" + productID
					+ "and from_time>=TO_DATE('" + arrivalDate
					+ "','mm-dd-yyyy') " + "and from_time<=TO_DATE('" + toDate
					+ "','mm-dd-yyyy') " + "and usage_type_id=" + OCCUPIED;

			num = db.executeUpdate(queryRelease);
		} else if (bookedOrOccupied.equalsIgnoreCase("ALL")) {
			queryRelease = "Select * from i_inv_used where prd_id=" + productID
					+ "and from_time>=TO_DATE('" + arrivalDate
					+ "','mm-dd-yyyy') " + "and from_time<=TO_DATE('" + toDate
					+ "','mm-dd-yyyy') " + "and usage_type_id=" + BOOKED;
			int num1 = db.executeUpdate(queryRelease);

			queryRelease = "Select * from i_inv_used where prd_id=" + productID
					+ "and from_time>=TO_DATE('" + arrivalDate
					+ "','mm-dd-yyyy') " + "and from_time<=TO_DATE('" + toDate
					+ "','mm-dd-yyyy') " + "and usage_type_id=" + OCCUPIED;

			int num2 = db.executeUpdate(queryRelease);
			num = num1 + num2;
		}

		logger.info("Execute query: " + queryRelease);
		return num;
	}

	/**
	 * Get the customer certification types
	 * 
	 * @param schema
	 * @return
	 */
	public List<String> getCustomerCertificationTypes(String schema) {
		logger.info("Get customer cetification type(s) of "
				+ schema.substring(schema.lastIndexOf("_") + 1) + " Contract.");
		logger.info("Reset schema as " + schema);
		db.resetSchema(schema);

		String query = "select name from d_certification_type";
		List<String> types = db.executeQuery(query, "NAME");
		if (types.size() < 1) {
			throw new ActionFailedException(
					"Can't find any Customer Certification Types.");
		}

		return types;
	}

	private void cleanReservations(String[] res_nums, String schema,
			boolean newSession) {
		String contract = DataBaseFunctions.getContractFromSchemaName(schema)
				.toUpperCase();
		if (contract.matches("(KOA|ELS|TRLS)")) {
			logger.info("Cannot clean up orders in KOA, ELS and TRLS");
		} else {
			int index = schema.lastIndexOf("_");
			String contractCode = schema.substring(index + 1);
			Object[] args = new Object[2];
			Properties param = new Properties();
			param.put("contract", contractCode);
			param.put("newSession", Boolean.toString(newSession));
			args[0] = param;
			args[1] = res_nums;

//			new com.activenetwork.qa.awo.supportscripts.function.CleanupOrders()
//					.execute(args);
		}
	}

	/**
	 * Retrieve all ids of depositable payments for the given orders
	 * 
	 * @param order_numbers
	 * @param schema
	 * @return
	 */
	public String[] getDepositablePayments(String[] order_numbers, String schema) {
		StringBuffer ords = new StringBuffer();
		ords.append("(");
		for (int i = 0; i < order_numbers.length; i++) {
			ords.append("'");
			ords.append(order_numbers[i]);
			ords.append("'");
			if (i < order_numbers.length - 1) {
				ords.append(",");
			}
		}
		ords.append(")");

		String contract = DataBaseFunctions.getContractFromSchemaName(schema);
		RegularExpression contractPattern = new RegularExpression("NY", false);
		String query;
		if (contractPattern.match(contract)) {
			// NY is using park credit card, which equals cash
			query = "select distinct(p.id) as pmt_id "
					+ "from o_order oo, o_ord_item oi, f_pmt_allocation pa,f_pmt p,f_pmt_type_loc pl "
					+ "where oo.ord_num in " + ords.toString() + " "
					+ "and oo.id=oi.ord_id " + "and pa.ord_item_id=oi.id "
					+ "and p.id=pa.pmt_id " + "and p.pmt_type_loc_id=pl.id "
					+ "and p.status=2" // 2 - received
					+ "and p.type=1"; // 1 - payment 2 - refund
		} else {
			query = "select distinct(p.id) as pmt_id "
					+ "from o_order oo, o_ord_item oi, f_pmt_allocation pa,f_pmt p,f_pmt_type_loc pl, f_pmt_type pt "
					+ "where oo.ord_num in "
					+ ords.toString()
					+ " "
					+ "and oo.id=oi.ord_id "
					+ "and pa.ord_item_id=oi.id "
					+ "and p.id=pa.pmt_id "
					+ "and p.pmt_type_loc_id=pl.id "
					+ "and (pl.pmt_grp_id in (1,2) or (pl.pmt_type_id=pt.id and pt.cd='HIST') ) "
					+ "and p.status=2 " // 2 -received
					+ "and p.type=1"; // 1 - payment 2 - refund
		}
		sycDB();
		db.resetSchema(schema);
		db.connect();
		List<String> ids = db.executeQuery(query, "pmt_id");
		logger.info("Execute query: " + query);
		db.disconnect();
		return ids.toArray(new String[0]);
	}

	/**
	 * Find available site for call manager
	 * 
	 * @param schema
	 * @param site
	 * @param arrivalDate
	 * @param departDate
	 * @return
	 * @throws ItemNotFoundException
	 */
	public String findCallMgrAvailSite(String schema, SiteInfoData site,
			String arrivalDate, String departDate) throws ItemNotFoundException {
		db.resetSchema(schema);

		String query = " select distinct p.*, d_loc.name from p_prd p, d_loc "
				+ "where p.import_reservable = 'Y' and p.loc_id=d_loc.id and  p.active_ind = 1 and p.deleted_ind = 0 "
				+ "and p.unit_of_stay_type_id = 1 and (exists (select ia0.prd_id from i_inv_avail ia0 "
				+ "where ia0.prd_id = p.prd_id "
				+ "and ia0.start_time <=  TO_DATE('"
				+ arrivalDate
				+ ":23:59:59', 'yyyy-mm-dd:hh24:mi:ss') "
				+ "and ia0.end_time >=  TO_DATE('"
				+ departDate
				+ ":00:00:00', 'yyyy-mm-dd:hh24:mi:ss')) "
				+ "and not exists(select iu0.prd_id from i_inv_used iu0 where iu0.prd_id = p.prd_id "
				+ "and iu0.from_time <=  TO_DATE('"
				+ arrivalDate
				+ ":23:59:59', 'yyyy-mm-dd:hh24:mi:ss') "
				+ "and iu0.to_time >=  TO_DATE('"
				+ departDate
				+ ":00:00:00', 'yyyy-mm-dd:hh24:mi:ss') "
				+ "and iu0.movable = 0 )) and (p.park_id in (select ID from d_loc where upper(name) "
				+ "like '"
				+ site.parkName
				+ "%' and level_num=40)) and "
				+ "(p.loc_id in (select ID from d_loc start with id= (select id from d_loc where Name like '"
				+ site.parkName
				+ "%') "
				+ "connect by prior id = parent_id)) "
				+ "and p.prd_grp_id= (select prd_grp_id from P_PRD_GRP where prd_grp_name like '"
				+ site.siteType
				+ "%') "
				+ "and (not exists( select b.prd_id from o_order a, o_ord_item b "
				+ "where a.id = b.ord_id and proc_status_id in ( 1, 2 ) and ord_status_id = 1 "
				+ "and b.start_date <=  TO_DATE('"
				+ departDate
				+ ":00:00:00', 'yyyy-mm-dd:hh24:mi:ss')"
				+ " and ( (b.prd_id = p.prd_id and p.prd_rel_type = 1) or "
				+ "(b.occ_site_id = p.prd_id and p.prd_rel_type = 3)))) and "
				+ "(p.prd_rel_type in( 1,3)) order by d_loc.name, p.prd_cd, p.prd_id ";

		int index = 0;
		String siteName = db.executeQuery(query, "PRD_NAME", index);
		if (siteName.length() < 1)
			throw new ItemNotFoundException("Not available site found.");
		String siteNumber = db.executeQuery(query, "PRD_CD", index);
		String areaName = db.executeQuery(query, "NAME", index);
		String parkName = site.parkName;
		String siteType = site.siteType;

		return "parkName->" + parkName + "|areaName->" + areaName
				+ "|siteName->" + siteName + "|siteNum->" + siteNumber
				+ "|siteType->" + siteType;

	}

	/**
	 * Find available site for field manager.
	 * 
	 * @param schema
	 * @param site
	 * @param arrivalDate
	 * @param departDate
	 * @return
	 * @throws ItemNotFoundException
	 */
	public String findFldMgrAvailSite(String schema, SiteInfoData site,
			String arrivalDate, String departDate) throws ItemNotFoundException {
		db.resetSchema(schema);

		String query = " select distinct p.*, d_loc.name from p_prd p, d_loc "
				+ "where p.loc_id=d_loc.id and  p.active_ind = 1 and p.deleted_ind = 0 "
				+ "and p.unit_of_stay_type_id = 1 and (exists (select ia0.prd_id from i_inv_avail ia0 "
				+ "where ia0.prd_id = p.prd_id "
				+ "and ia0.start_time <=  TO_DATE('"
				+ arrivalDate
				+ ":23:59:59', 'yyyy-mm-dd:hh24:mi:ss') "
				+ "and ia0.end_time >=  TO_DATE('"
				+ departDate
				+ ":00:00:00', 'yyyy-mm-dd:hh24:mi:ss')) "
				+ "and not exists(select iu0.prd_id from i_inv_used iu0 where iu0.prd_id = p.prd_id "
				+ "and iu0.from_time <=  TO_DATE('"
				+ arrivalDate
				+ ":23:59:59', 'yyyy-mm-dd:hh24:mi:ss') "
				+ "and iu0.to_time >=  TO_DATE('"
				+ departDate
				+ ":00:00:00', 'yyyy-mm-dd:hh24:mi:ss') "
				+ "and iu0.movable = 0 )) and (p.park_id in (select ID from d_loc where upper(name) "
				+ "like '"
				+ site.parkName
				+ "%' and level_num=40)) and "
				+ "(p.loc_id in (select ID from d_loc start with id= (select id from d_loc where Name like '"
				+ site.parkName
				+ "%') "
				+ "connect by prior id = parent_id)) "
				+ "and p.prd_grp_id= (select prd_grp_id from P_PRD_GRP where prd_grp_name like '"
				+ site.siteType
				+ "%') "
				+ "and (not exists( select b.prd_id from o_order a, o_ord_item b "
				+ "where a.id = b.ord_id and proc_status_id in ( 1, 2 ) and ord_status_id = 1 "
				+ "and b.start_date <=  TO_DATE('"
				+ departDate
				+ ":00:00:00', 'yyyy-mm-dd:hh24:mi:ss')"
				+ " and ( (b.prd_id = p.prd_id and p.prd_rel_type = 1) or "
				+ "(b.occ_site_id = p.prd_id and p.prd_rel_type = 3)))) and "
				+ "(p.prd_rel_type in( 1,3)) order by d_loc.name, p.prd_cd, p.prd_id ";

		int index = 0;
		String siteName = db.executeQuery(query, "PRD_NAME", index);
		String siteNumber = db.executeQuery(query, "PRD_CD", index);
		String areaName = db.executeQuery(query, "NAME", index);
		String parkName = site.parkName;
		String siteType = site.siteType;

		return "parkName->" + parkName + "|areaName->" + areaName
				+ "|siteName->" + siteName + "|siteNum->" + siteNumber
				+ "|siteType->" + siteType;

	}

	public String getOrdLotteryStatusInDB(String schema, String ordNum) {
		db.resetSchema(schema);
		String sql = "Select PROC_STATUS_ID from O_ORDER where ORD_NUM = '"
				+ ordNum + "'";
		logger.info("Execute sql: " + sql);
		String result = db.executeQuery(sql, "PROC_STATUS_ID", 0);
		logger.info("The order " + ordNum + " lottery status is " + result);
		return result;
	}

	/**
	 * Verify reservation status in Auto schema
	 * 
	 * @param resId
	 * @param proc_status
	 * @param ord_status
	 * @param conf_status
	 */
	public void verifyResStatusInDB(String resId, int proc_status,
			int ord_status, int conf_status, String schema) {
		sycDB();
		// String schema=schema_prefix+"SC";
		// String schema=env+"_AUTO";
		db.resetSchema(schema);
		logger.info("Verify reservation#" + resId + " status in DB");
		logger.debug("Reset schema as " + schema);

		String resnum[] = resId.split(" ");
		for (int i = 0; i < resnum.length; i++) {
			String query = "select PROC_STATUS_ID, ORD_STATUS_ID,CONF_STATUS_ID from O_ORDER where ORD_NUM = '"
					+ resnum[i] + "'";

			List<String> result = db.executeQuery(query, 3, 1);
			logger.debug("Execute query: " + query);
			if (result.size() < 3) {
				logger.error("Query returns nothing");
				throw new ItemNotFoundException("Query returns nothing: "
						+ query);
			}

			if (proc_status > 0) {
				int actualValue = Integer.parseInt(result.get(0).toString());
				if (proc_status != actualValue) {
					throw new ItemNotFoundException("Res: " + resId
							+ " expect PROC_STATUS_ID = " + proc_status
							+ " actual value in DB = " + actualValue);
				}
			}

			if (ord_status > 0) {
				int actualValue = Integer.parseInt(result.get(1).toString());
				if (ord_status != actualValue) {
					throw new ItemNotFoundException("Res: " + resId
							+ " expect ORD_STATUS_ID = " + ord_status
							+ " actual value in DB = " + actualValue);
				}
			}

			if (conf_status > 0) {
				int actualValue = Integer.parseInt(result.get(2).toString());
				if (conf_status != actualValue) {
					throw new ItemNotFoundException("Res: " + resId
							+ " expect CONF_STATUS_ID = " + conf_status
							+ " actual value in DB = " + actualValue);
				}
			}
		}
		logger.info("verify seccessfully !");
	}

	/**
	 * Verify Event status in Auto schema
	 * 
	 * @param eventId
	 * @param event_status
	 */
	public void verifyEventStatus(String eventId, int event_status,
			String schema) {
		// 1-Active; 3-Void; 8-Closed
		sycDB();
		db.resetSchema(schema);

		logger.info("Verify event#" + eventId + " status in DB.");

		String query = "select ORD_STATUS_ID from O_ORDER where ID = '"
				+ eventId + "'";
		List<String> result = db.executeQuery(query, 1, 1);

		if (event_status > 0) {
			int actualValue = Integer.parseInt(result.get(0).toString());
			if (event_status != actualValue) {
				throw new ItemNotFoundException("Res: " + eventId
						+ " expect ORD_STATUS_ID = " + event_status
						+ " actual value in DB = " + actualValue);
			}
		}
	}

	/**
	 * Verify POS name and quantity correct in DB
	 * 
	 * @param ordNum
	 * @param posName
	 * @param qty
	 * @param schema
	 */
	public void verifyPOSInDB(String ordNum, String posName, String qty,
			String schema) {
		sycDB();
		db.resetSchema(schema);
		logger.info("Verify POS " + posName + " Correct From DB.");

		// oct 16 2012 Chevy - Modified query to return quantity from o_ord_item
		// due to quantity column added to p_prd as well//
		String query = "select prd_name,oi.quantity from o_ord_item oi,p_prd pp where oi.prd_id=pp.prd_id and ord_id ="
				+ "(select id from o_order where ord_num = '"
				+ ordNum
				+ "') order by oi.id desc";

		String colNames[] = new String[2];
		colNames[0] = "prd_name";
		colNames[1] = "quantity";
		List<String[]> result = db.executeQuery(query, colNames);
		logger.debug("Execute query: " + query);
		if (result.size() < 1) {
			logger.error("Query returns nothing");
			throw new ItemNotFoundException("Query returns nothing: " + query);
		}
		if (result.size() > 1) {
			for (int j = 0; j < result.size(); j++) {
				if (!result.get(j)[0].equals(posName.split(",")[j])) {
					throw new ItemNotFoundException("POS Order: " + ordNum
							+ " expect Pos Name = " + posName.split(",")[j]
							+ " actual value in DB = " + result.get(j)[0]);
				}
				if (!result.get(j)[1].equals(qty.split(",")[j])) {
					throw new ItemNotFoundException("POS Order: " + ordNum
							+ " expect quantity = " + qty.split(",")[j]
							+ " actual value in DB = " + result.get(j)[1]);
				}
			}
		} else {
			if (posName != null && !posName.equals("")) {
				if (!posName.equals(result.get(0)[0])) {
					throw new ItemNotFoundException("POS Order: " + ordNum
							+ " expect Pos Name = " + posName
							+ " actual value in DB = " + result.get(0)[0]);
				}
			}
			if (qty != null && !qty.equals("")) {
				int actualValue = Integer.parseInt(result.get(0)[1]);
				int expectValue = Integer.parseInt(qty);
				if (expectValue != actualValue) {
					throw new ItemNotFoundException("POS Order: " + ordNum
							+ " expect quantity = " + expectValue
							+ " actual value in DB = " + actualValue);
				}
			}
		}
	}

	/**
	 * This method used to verify given order status correct from DB
	 * 
	 * @param ordNum
	 * @param status
	 * @param schema
	 */
	public void verifyOrderStatus(String ordNum, int status, String schema) {
		sycDB();
		db.resetSchema(schema);
		logger.info("Verify Order#" + ordNum + " status in DB");
		String query = "select ORD_STATUS_ID from O_ORDER where ORD_NUM = '"
				+ ordNum + "'";

		List<String> result = db.executeQuery(query, 1, 1);
		logger.debug("Execute query: " + query);
		if (result.size() < 1) {
			logger.error("Query returns nothing");
			throw new ItemNotFoundException("Query returns nothing: " + query);
		} else {
			int actualValue = Integer.parseInt(result.get(0).toString());
			if (status != actualValue) {
				throw new ItemNotFoundException("Order: " + ordNum
						+ " expect ORD_STATUS_ID = " + status
						+ " actual value in DB = " + actualValue);
			}
		}

		logger.info("The status of order(#=" + ordNum + ") is correct.");
	}

	public void verifyOrderConfirmedStatus(String ordNum, int confStatusId,
			String schema) {
		sycDB();
		db.resetSchema(schema);
		logger.info("Verify Order#" + ordNum + " confirmed status in DB");
		String query = "select CONF_STATUS_ID from O_ORDER where ORD_NUM = '"
				+ ordNum + "'";

		List<String> result = db.executeQuery(query, 1, 1);
		logger.debug("Execute query: " + query);
		if (result.size() < 1) {
			logger.error("Query returns nothing");
			throw new ItemNotFoundException("Query returns nothing: " + query);
		} else {
			int actualValue = Integer.parseInt(result.get(0).toString());
			if (confStatusId != actualValue) {
				throw new ItemNotFoundException("Order: " + ordNum
						+ " expect CONF_STATUS_ID = " + confStatusId
						+ " actual value in DB = " + actualValue);
			}
		}

		logger.info("The confirmed status of order(#=" + ordNum
				+ ") is correct.");
	}

	public void verifyPaymentStatus(String ordNum, String status, String schema) {
		sycDB();
		db.resetSchema(schema);
		logger.info("Verify payment status of Order#" + ordNum + " in DB");
		String query = "select fp.status as PaymentStaus from o_order oo, f_pmt fp, f_pmt_allocation fpa where oo.id = fpa.ord_id and fpa.pmt_id = fp.id and oo.ORD_NUM = '"
				+ ordNum + "'";
		List<String> result = db.executeQuery(query, "PaymentStaus");
		if (result.size() < 1) {
			logger.error("Query returns nothing");
			throw new ItemNotFoundException("Query returns nothing: " + query);
		} else {
			boolean allPassed = true;
			for (int j = 0; j < result.size(); j++) {
				allPassed &= MiscFunctions.compareResult(j + " - Record",
						status, result.get(j));
			}
			if (!allPassed) {
				throw new ErrorOnPageException("Not all payment status as "
						+ status + " for order number:" + ordNum
						+ ". Please check details from previous logs.");
			}
		}
		logger.info("The payment status of order(#=" + ordNum + ") is correct.");
	}

	/**
	 * The method used to verify all privileges belong to the same order status
	 * correct from DB
	 * 
	 * @param ordNum
	 * @param status
	 * @param schema
	 */
	public void verifyAllPrivilegesStatus(String ordNum, int status,
			String schema) {
		sycDB();
		db.resetSchema(schema);
		logger.info("Verify All Privileges Status Correct belong Order "
				+ ordNum + " From DB.");

		String query = "Select opi.priv_number,opi.status_id from o_order oo, o_ord_item ooi,o_priv_inst opi "
				+ "where oo.id=ooi.ord_id and ooi.priv_inst_id=opi.id and oo.ord_num='"
				+ ordNum + "'";

		String colNames[] = new String[2];
		colNames[0] = "priv_number";
		colNames[1] = "status_id";
		List<String[]> result = db.executeQuery(query, colNames);
		logger.debug("Execute query: " + query);
		if (result.size() < 1) {
			logger.error("Query returns nothing");
			throw new ItemNotFoundException("Query returns nothing: " + query);
		} else {
			for (int j = 0; j < result.size(); j++) {
				if (Integer.parseInt(result.get(j)[1]) != status) {
					throw new ErrorOnDataException("Privilege "
							+ result.get(j)[0] + " " + "expect status is "
							+ status + " But actual value in DB is "
							+ result.get(j)[1]);
				}
			}
		}

		logger.info("All privileges status are correct.");
	}

	/**
	 * this method is used to verify all consumable order item status stored
	 * correctly in DB
	 * 
	 * @param ordNum
	 * @param status
	 * @param schema
	 */
	public void verifyAllConsumablesStatus(String ordNum, int status,
			String schema) {
		sycDB();
		db.resetSchema(schema);
		logger.info("Verify All Consumables Status Correct belong Order "
				+ ordNum + " From DB.");

		String query = "select pp.PRD_NAME, ooi.STATUS_ID from O_ORDER oo, O_ORD_ITEM ooi, P_PRD pp "
				+ "where oo.ID = ooi.ORD_ID and ooi.PRD_ID = pp.PRD_ID and oo.ORD_NUM = '"
				+ ordNum + "'";

		String colNames[] = new String[2];
		colNames[0] = "PRD_NAME";
		colNames[1] = "STATUS_ID";
		List<String[]> result = db.executeQuery(query, colNames);
		logger.debug("Execute query: " + query);
		if (result.size() < 1) {
			logger.error("Query returns nothing.");
			throw new ItemNotFoundException("Query returns nothing: " + query);
		} else {
			for (int j = 0; j < result.size(); j++) {
				if (Integer.parseInt(result.get(j)[1]) != status) {
					throw new ErrorOnDataException("Consumable - "
							+ result.get(j)[0] + " expect status is " + status
							+ ", But actual value in DB is " + result.get(j)[1]);
				}
			}
		}

		logger.info("All consumables status are correct.");
	}

	/**
	 * Get the all privilege number(s) in a order by order number
	 * 
	 * @param schema
	 * @param ordNum
	 * @return
	 */
	public String getPrivilegeNumByOrdNum(String schema, String ordNum) {
		sycDB();
		db.resetSchema(schema);

		logger.info("Get the privilege number(s) of Order(#=" + ordNum
				+ ") from DB.");
		String query = "Select ooi.priv_inst_id from o_order oo, o_ord_item ooi where oo.id=ooi.ord_id and oo.ord_num='"
				+ ordNum + "'";// ooi.id->ooi.priv_inst_id
		List<String> result = db.executeQuery(query, "PRIV_INST_ID");// ID

		if (result.size() == 0) {
			throw new ErrorOnDataException(
					"Cannot find any record by the SQL - " + query);
		}
		StringBuffer privilegeNums = new StringBuffer();
		for (int i = 0; i < result.size(); i++) {
			privilegeNums.append(result.get(i).trim() + ",");
		}

		return privilegeNums.toString().substring(0,
				privilegeNums.lastIndexOf(","));
	}

	/**
	 * Get privilege inventory number identified by order number. The
	 * privInstColName can be "used_priv_inst_id" or "sold_priv_inst_id"
	 * 
	 * @param schema
	 * @param ordNum
	 * @return
	 */
	public String getPrivilegeInventoryNumByOrdNum(String schema,
			String ordNum, String privInstColName) {
		db.resetSchema(schema);

		logger.info("Get Privilege Inventory Number identified by Order Num - "
				+ ordNum);
		String sql = "select INV_NUMBER from P_HF_INV where " + privInstColName
				+ " = "
				+ "(select PRIV_INST_ID from O_ORD_ITEM where ord_id = "
				+ "(select id from o_order where ORD_NUM = '" + ordNum + "'))";

		logger.info("Executing sql: " + sql);
		List<String> result = db.executeQuery(sql, "INV_NUMBER");
		if (result.size() == 0) {
			throw new ErrorOnDataException("Can't find any record by sql - "
					+ sql);
		}

		String invNum = result.get(0);
		logger.info("Find Privilege Inventory Number - " + invNum);

		return invNum;
	}

	/**
	 * get privilege inventory number identified by order number which is for
	 * using the inventory
	 * 
	 * @param schema
	 * @param ordNum
	 * @return
	 */
	public String getPrivilegeInventoryNumByOrdNum(String schema, String ordNum) {
		// Lesley[20130830]: refactor the related method
		return this.getPrivilegeInventoryNumByOrdNum(schema, ordNum,
				"used_priv_inst_id");
	}

	/**
	 * get privilege inventory number identified by sold order number
	 * 
	 * @param schema
	 * @param ordNum
	 * @return
	 */
	public String getPrivilegeInventoryNumBySoldOrdNum(String schema,
			String ordNum) {
		return this.getPrivilegeInventoryNumByOrdNum(schema, ordNum,
				"sold_priv_inst_id");
	}

	/**
	 * get available privilege inventory numbers
	 * 
	 * @param schema
	 * @param invType
	 * @param licenseYear
	 * @param storeName
	 * @return
	 */
	public List<String> getAvailablePrivilegeInventoryNumbers(String schema,
			String invType, String licenseYear, String storeName) {
		return getPrivilegeInventoryNumbers(schema, invType, licenseYear,
				storeName, PRIV_INV_STATUS_AVAILABLE_CODE);
	}

	public String getAvailablePrivilegeInventoryNumber(String schema,
			String invType, String storeName) {
		return getPrivilegeInventoryNumbers(schema, invType, null, storeName,
				PRIV_INV_STATUS_AVAILABLE_CODE).get(0);
	}

	public List<String> getPrivilegeInventoryNumbers(String schema,
			String invType, String licenseYear, String storeName, int statusCode) {
		db.resetSchema(schema);

		logger.info("Get Privilege Inventory Numbers(Type=" + invType
				+ ", License Year=" + licenseYear + ").");
		String sql = "select phi.INV_NUMBER from P_HF_INV phi, P_HF_INV_TYPE phit, P_HF_INV_TYPE_YEAR phity, D_STORE ds "
				+ "where phi.INV_TYPE_YEAR_ID = phity.ID and phit.ID = phity.INV_TYPE_ID and "
				+ "phit.CODE = '"
				+ invType
				+ "' and phity.YEAR "
				+ (StringUtil.isEmpty(licenseYear) ? "is null" : "='"
						+ licenseYear + "'")
				+ " and phi.STORE_ID = ds.ID and ds.NAME = '"
				+ storeName
				+ "' and phi.STATUS_ID = " + statusCode;

		logger.info("Execute sql: " + sql);
		List<String> result = db.executeQuery(sql, "INV_NUMBER");
		if (result.size() == 0) {
			throw new ErrorOnDataException(
					"Can't find any Privilege Inventory records identified by - "
							+ invType);
		}

		return result;
	}

	public boolean isPrivilegeInventoryExists(String schema,
			String inventoryType, String licenseYear, String storeName) {
		logger.info("Check if the Privilege Inventory - Type = "
				+ inventoryType + ", License Year = " + licenseYear
				+ ", Store = " + storeName + " exists.");
		db.resetSchema(schema);
		String sql = "select ID from P_HF_INV where INV_TYPE_YEAR_ID = "
				+ "(select ID from P_HF_INV_TYPE_YEAR where INV_TYPE_ID = "
				+ "(select ID from P_HF_INV_TYPE where CODE = '"
				+ inventoryType
				+ "') and YEAR "
				+ (licenseYear.equalsIgnoreCase(ALL_STATUS) ? "is null "
						: ("= '" + licenseYear + "' ")) + ")";

		if (!StringUtil.isEmpty(storeName)) {
			sql += " and STORE_ID = (select ID from D_STORE where NAME = '"
					+ storeName + "')";
		}

		logger.info("Execute sql: " + sql);
		List<String> result = db.executeQuery(sql, "ID");
		boolean exists = true;
		if (result.size() == 0) {
			exists = false;
		}
		logger.info("Privilege Inventory - Type = " + inventoryType
				+ ", License Year = " + licenseYear + ", Store = " + storeName
				+ " doesn't exist.");
		return exists;
	}

	public boolean isPrivilegeInventoryExists(String schema,
			String inventoryType, String licenseYear) {
		return isPrivilegeInventoryExists(schema, inventoryType, licenseYear,
				null);
	}

	public void deletePrivilegeInventory(String schema, String inventoryType,
			String licenseYear, String storeName) {
		logger.info("Delete the Privilege Inventory - Type = " + inventoryType
				+ ", License Year = " + licenseYear + ", Store = " + storeName
				+ ".");
		db.resetSchema(schema);
		String sql = "delete from P_HF_INV where INV_TYPE_YEAR_ID = "
				+ "(select ID from P_HF_INV_TYPE_YEAR where INV_TYPE_ID = "
				+ "(select ID from P_HF_INV_TYPE where CODE = '"
				+ inventoryType
				+ "') and YEAR "
				+ (licenseYear.equalsIgnoreCase(ALL_STATUS) ? "is null "
						: ("= '" + licenseYear + "' ")) + ")";

		if (!StringUtil.isEmpty(storeName)) {
			sql += " and STORE_ID = (select ID from D_STORE where NAME = '"
					+ storeName + "')";
		}
		logger.info("Execute sql: " + sql);
		int num = db.executeUpdate(sql);
		if (num == 0) {
			logger.info("No Privilege Inventory records found.");
		}
	}

	public void deletePrivilegeInventory(String schema, String inventoryType,
			String licenseYear) {
		deletePrivilegeInventory(schema, inventoryType, licenseYear, null);
	}

	public boolean isPrivilegeInventoryTypeLicenseYearExists(String schema,
			String inventoryType, String licenseYear) {
		logger.info("Check if the Privilege Inventory Type License Year - Type = "
				+ inventoryType + ", Year = " + licenseYear + " exists.");
		db.resetSchema(schema);
		String sql = "select ID from P_HF_INV_TYPE_YEAR where INV_TYPE_ID = (select ID from P_HF_INV_TYPE where CODE='"
				+ inventoryType
				+ "') and YEAR "
				+ (licenseYear.equalsIgnoreCase(ALL_STATUS) ? "is null" : "= '"
						+ licenseYear + "'");
		logger.info("Execute sql: " + sql);
		List<String> result = db.executeQuery(sql, "ID");
		boolean exists = true;
		if (result.size() == 0) {
			exists = false;
		}
		logger.info("Privilege Inventory Type License Year - Type = "
				+ inventoryType + ", License Year = " + licenseYear
				+ " doesn't exist.");
		return exists;
	}

	public void deletePrivilegeInventoryTypeLicenseYear(String schema,
			String inventoryType, String licenseYear) {
		logger.info("Delete Privilege Inventory Type License Year - Type = "
				+ inventoryType + ", Year = " + licenseYear);
		db.resetSchema(schema);
		String sql = "delete from P_HF_INV_TYPE_YEAR where INV_TYPE_ID = (select ID from P_HF_INV_TYPE where CODE='"
				+ inventoryType
				+ "') and YEAR "
				+ (licenseYear.equalsIgnoreCase(ALL_STATUS) ? "is null" : "= '"
						+ licenseYear + "'");
		logger.info("Execute sql: " + sql);
		int num = db.executeUpdate(sql);
		if (num == 0) {
			logger.info("No Privilege Inventory Type License Year records found.");
		}
	}

	public boolean isPrivilegeInventoryTypeExists(String schema,
			String inventoryType) {
		logger.info("Check if the Inventory Type - " + inventoryType
				+ " exists.");
		db.resetSchema(schema);
		String sql = "select ID from P_HF_INV_TYPE where CODE='"
				+ inventoryType + "'";
		logger.info("Execute sql: " + sql);
		List<String> result = db.executeQuery(sql, "ID");
		boolean exists = true;
		if (result.size() == 0) {
			exists = false;
		}
		logger.info("Privilege Inventory Type - " + inventoryType
				+ " doesn't exist.");
		return exists;
	}

	public void deletePrivilegeInventoryType(String schema, String inventoryType) {
		logger.info("Delete Privilege Inventory Type - " + inventoryType);
		db.resetSchema(schema);
		String sql = "delete from P_HF_INV_TYPE where CODE='" + inventoryType
				+ "'";
		logger.info("Execute sql: " + sql);
		int num = db.executeUpdate(sql);
		if (num == 0) {
			logger.info("No Privilege Inventory Type records found.");
		}
	}

	/**
	 * Get the all privilege numbers in a order by order number
	 * 
	 * @param schema
	 * @param ordNum
	 * @return
	 */
	public String[] getPrivilegeNumsByOrdNum(String schema, String ordNum) {
		String[] nums = getPrivilegeNumByOrdNum(schema, ordNum).split(",");
		return nums;
	}

	public String getPrivilegeValidToDate(String schema, String priv_num) {
		db.resetSchema(schema);

		logger.info("Get Privilege Valid To Date with Privilege#" + priv_num);

		String query = "select valid_to from O_PRIV_INST where priv_number="
				+ priv_num;
		String valid_to = db.executeQuery(query, "valid_to", 0);
		return valid_to;
	}

	public int getPrivilegeInventoryTotalCount(String schema,
			String inventoryType, String licenseYear, String storeID) {
		db.resetSchema(schema);
		logger.info("Get the Privilege Inventory Type(=" + inventoryType
				+ "), License Year(=" + licenseYear + ") total count.");
		String sql = "select count(*) as COUNT from P_HF_INV phi, P_HF_INV_TYPE phit, P_HF_INV_TYPE_YEAR phity "
				+ "where phi.STORE_ID="
				+ storeID
				+ " and phi.INV_TYPE_YEAR_ID=phity.ID and phit.ID=phity.INV_TYPE_ID "
				+ "and phit.CODE='" + inventoryType + "'";

		if ("All".equals(licenseYear)) {
			sql += " and phity.YEAR is null";
		} else {
			sql += " and phity.YEAR = '" + licenseYear + "' ";
		}

		logger.info("Execute SQL: " + sql);
		List<String> result = db.executeQuery(sql, "COUNT");
		if (result.size() == 0) {
			throw new ErrorOnDataException("Can't find any records by sql - "
					+ sql);
		}
		int count = Integer.parseInt(result.get(0));

		return count;
	}

	/**
	 * Get the available privilege inventory count
	 * 
	 * @param schema
	 * @param inventoryType
	 * @param privilegeName
	 * @param licenseYear
	 * @Param storeName
	 * @return
	 */
	public int getAvailablePrivInventoryCount(String schema,
			String inventoryType, String privilegeName, String licenseYear,
			String storeName) {
		sycDB();
		db.resetSchema(schema);

		logger.info("Get the available privilege inventory count.");
		String query = "select count(*) as count from p_hf_inv phi, p_hf_inv_type phit, p_hf_inv_type_year phity, d_store ds, p_prd pp "
				+ "where phit.id=phity.inv_type_id "
				+ "and phi.inv_type_year_id=phity.id "
				+ "and pp.prd_name='"
				+ privilegeName.split("-")[1]
				+ "' "
				+ "and phit.code='"
				+ inventoryType
				+ "' "
				+ "and ds.name='"
				+ storeName
				+ "' and phi.status_id = " + PRIV_INV_STATUS_AVAILABLE_CODE;
		if ("All".equals(licenseYear)) {
			query = query + " and phity.year is null";
		} else {
			query = query + " and phity.year='" + licenseYear + "' ";
		}

		List<String> result = db.executeQuery(query, "COUNT");

		if (result.size() == 0) {
			throw new ActionFailedException(
					"Cannot find any record by the SQL - " + query);
		}

		int count = Integer.parseInt(result.get(0));

		return count;
	}

	/**
	 * Get the privilege inventory status
	 * 
	 * @param schema
	 * @param inventoryType
	 * @param privilegeName
	 * @param privilegeInvNum
	 * @param licenseYear
	 * @return
	 */
	public int getPrivInventoryStatus(String schema, String inventoryType,
			String privilegeName, String privilegeInvNum, String licenseYear) {
		sycDB();
		db.resetSchema(schema);

		logger.info("Get the privilege inventory status of " + privilegeName
				+ "/" + privilegeInvNum);
		String query = "select distinct phi.status_id as status_id from p_hf_inv phi, p_hf_inv_type phit, p_hf_inv_type_year phity, p_prd_loc ppl, p_prd pp, d_store ds "
				+ "where pp.prd_id=ppl.prd_id "
				+ "and ppl.loc_id=ds.station_id "
				+ "and ds.id=phi.store_id "
				+ "and phit.id=phity.inv_type_id "
				+ "and phi.inv_type_year_id=phity.id "
				+ "and pp.prd_name='"
				+ privilegeName.split("-")[1]
				+ "' "
				+ "and phit.code='"
				+ inventoryType
				+ "' "
				+ "and phity.year='"
				+ licenseYear
				+ "' " + "and phi.inv_number= '" + privilegeInvNum + "'";

		List<String> result = db.executeQuery(query, "STATUS_ID");

		if (result.size() == 0) {
			throw new ActionFailedException(
					"Cannot find any record by the SQL - " + query);
		}

		int status = Integer.parseInt(result.get(0));

		return status;
	}

	/**
	 * This method used to verify given privilege status correct from DB
	 * 
	 * @param privilegeNum
	 * @param status
	 * @param schema
	 */
	public void verifyPrivilegeStatusFromDB(String privilegeNum, int status,
			String schema) {
		sycDB();
		db.resetSchema(schema);
		logger.info("Verify Privilege " + privilegeNum
				+ " Status Correct From DB.");

		String query = "Select status_id from o_priv_inst where priv_number='"
				+ privilegeNum + "'";

		List<String> result = db.executeQuery(query, 1, 1);
		logger.debug("Execute query: " + query);
		if (result.size() < 1) {
			logger.error("Query returns nothing");
			throw new ItemNotFoundException("Query returns nothing: " + query);
		} else {
			int actualValue = Integer.parseInt(result.get(0).toString());
			if (status != actualValue) {
				throw new ItemNotFoundException("Privilege: " + privilegeNum
						+ " expect status_id = " + status
						+ " actual value in DB = " + actualValue);
			}
		}

		logger.info("----The privilege status from DB is correct.");
	}

	/**
	 * Verify payment status in Auto schema - not usable so far
	 * 
	 * @param resId
	 * @param payGrp
	 * @param payStatus
	 * @param payType
	 */
	public void verifyPayment(String resId, int payGrp, int payStatus,
			String payType, String schema) {
		// ("1","Cash");
		// ("2", "Non Cash Depositable");
		// ("3", "Credit Card");
		// ("4", "Non Depositable");
		// ("5", "ACH");
		// ("6", "Voucher");
		sycDB();
		db.resetSchema(schema);

		String query = "select p.status, t.cd, l.PMT_GRP_ID "
				+ "from o_order o, f_pmt p, f_pmt_allocation a, f_pmt_type_loc l, f_pmt_type t "
				+ "where o.ord_num = '"
				+ resId
				+ "' and o.id = a.ord_id and a.pmt_id = p.id and p.type = 1 "
				+ "and p.PMT_TYPE_LOC_ID = l.id and l.pmt_type_id = t.id group by (p.status, t.cd, l.PMT_GRP_ID)";

		List<String> result = db.executeQuery(query, 5, -1);

		if (result.size() != 3) {
			throw new ItemNotFoundException(
					"expecting 3 records in DB, however it is not");
		}

		if (payStatus > 0) {
			int actualValue = Integer.parseInt(result.get(0).toString());
			if (payStatus != actualValue) {
				throw new ItemNotFoundException("Res: " + resId
						+ " expect PMT_STATUS = " + payStatus
						+ " actual value in DB = " + actualValue);
			}
		}

		if (payType != null && payType.length() > 0) {
			String actualValue = result.get(1).toString();
			if (!payType.equalsIgnoreCase(actualValue)) {
				throw new ItemNotFoundException("Res: " + resId
						+ " expect PMT_TYPE = " + payType
						+ " actual value in DB = " + actualValue);
			}
		}

		if (payGrp > 0) {
			int actualValue = Integer.parseInt(result.get(2).toString());
			if (payGrp != actualValue) {
				throw new ItemNotFoundException("Res: " + resId
						+ " expect PMT_GROUP = " + payGrp
						+ " actual value in DB = " + actualValue);
			}
		}
	}

	/**
	 * Verify refund status in Auto schema
	 * 
	 * @param resId
	 * @param refundGrp
	 * @param refundStatus
	 * @param refundType
	 */
	public void verifyRefund(String resId, String refundGrp, int refundStatus,
			String refundType, String schema) {
		// ("1", "Payment");
		// ("2", "Refund");
		// ("3", "Rebate");
		// ("4", "Voucher");
		sycDB();
		db.resetSchema(schema);

		String query = "select p.status, t.cd, l.PMT_GRP_ID "
				+ "from o_order o, f_pmt p, f_pmt_allocation a, f_pmt_type_loc l, f_pmt_type t "
				+ "where o.ord_num = '"
				+ resId
				+ "' and o.id = a.ord_id and a.rfnd_id = p.id and p.type = 2 "
				+ "and p.PMT_TYPE_LOC_ID = l.id and l.pmt_type_id = t.id group by (p.status, t.cd, l.PMT_GRP_ID)";

		List<String> result = db.executeQuery(query, 5, -1);

		if (refundStatus > 0) {
			int actualValue = Integer.parseInt(result.get(0).toString());
			if (refundStatus != actualValue) {
				throw new ItemNotFoundException("Res: " + resId
						+ " expect REFUND_STATUS = " + refundStatus
						+ " actual value in DB = " + actualValue);
			}
		}

		if (refundType != null && !refundType.equals("")) {
			String actualValue = result.get(1).toString();
			if (!refundType.equals(actualValue)) {
				throw new ItemNotFoundException("Res: " + resId
						+ " expect REFUND_TYPE = " + refundType
						+ " actual value in DB = " + actualValue);
			}
		}

		if (refundGrp != null && !refundGrp.equals("")) {
			String actualValue = result.get(2).toString();
			if (!refundGrp.equals(actualValue)) {
				throw new ItemNotFoundException("Res: " + resId
						+ " expect REFUND_GROUP = " + refundGrp
						+ " actual value in DB = " + actualValue);
			}
		}
	}

	/**
	 * Verify refund amount for specific reservation number
	 * 
	 * @param resId
	 * @param schema
	 * @param expectRefundAmount
	 * @param totalRefund
	 *            true-- Verify total refund false--Verify specific refund
	 * @return
	 */
	public void verifyRefundAmountFromDB(String schema, String resId,
			String expectRefundAmount, boolean totalRefund) {
		db.resetSchema(schema);

		String query = "select a.amount "
				+ "from o_order o, f_pmt p, f_pmt_allocation a "
				+ "where o.ord_num = '"
				+ resId
				+ "' and o.id = a.ord_id and a.rfnd_id = p.id and p.type = 2 order by p.id desc";

		String query_1 = "select sum(a.amount) as totalRefundAmount "
				+ "from o_order o, f_pmt p, f_pmt_allocation a "
				+ "where o.ord_num = '" + resId
				+ "' and o.id = a.ord_id and a.rfnd_id = p.id and p.type = 2";

		String refundAmount = "";
		if (!totalRefund) {
			logger.info("Query SQL: " + query);
			refundAmount = db.executeQuery(query, "amount", 0);
		} else {
			logger.info("Query SQL: " + query_1);
			refundAmount = db.executeQuery(query_1, "totalRefundAmount", 0);
		}
		if (!(Math.abs(Double.parseDouble(refundAmount)
				- Double.parseDouble(expectRefundAmount)) < 0.00001)) {
			throw new ErrorOnDataException(
					"Refund Amount is not corect for res#: " + resId);
		}
	}

	/**
	 * Verify inventory unavailable with the given information for only none NSS
	 * site
	 * 
	 * @param resID
	 * @param siteID
	 * @param arriveDate
	 * @param nightNum
	 * @param status
	 */
	public void verifyInvUnavailable(String resID, String siteID,
			String arriveDate, int nightNum, String status, String schema) {
		verifyInvUnavailable(resID, siteID, arriveDate, nightNum, status,
				false, schema);
	}

	/**
	 * Verify inventory unavailable with the given information
	 * 
	 * @param resID
	 * @param siteID
	 * @param arriveDate
	 * @param nightNum
	 * @param status
	 * @param isNssParent
	 */
	public void verifyInvUnavailable(String resID, String siteID,
			String arriveDate, int nightNum, String status,
			boolean isNssParent, String schema) {
		sycDB();
		db.resetSchema(schema);

		logger.info("Verify siteId#" + siteID + " is " + status);
		logger.debug("Reset schema as " + schema);

		String prd_id = "";

		// Calendar cDay = Calendar.getInstance();
		// DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		// DateFormat formatter1 = new SimpleDateFormat("MM-dd-yyyy kk:mm:ss");
		// try {
		// // arriveDate = arriveDate.replaceAll("/", "-");
		// Date day = DateFunctions.parseDateString(arriveDate);
		// cDay.setTime(day);
		// cDay.add(Calendar.DAY_OF_MONTH, nightNum);
		// cDay.add(Calendar.SECOND, -1);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// String departureDate = formatter1.format(cDay.getTime());
		arriveDate = DateFunctions.formatDate(arriveDate, "MM-dd-yyyy");
		String[] tokens = arriveDate.split("-");
		if (tokens[0].length() == 1)
			tokens[0] = "0" + tokens[0];
		if (tokens[1].length() == 1)
			tokens[1] = "0" + tokens[1];

		arriveDate = tokens[0] + "-" + tokens[1] + "-" + tokens[2];

		String queryStringBookingID = " select booking_id from o_ord_item where ord_id = "
				+ "(select id from o_order where ord_num = '" + resID + "')";

		String bookingID = db.executeQuery(queryStringBookingID, "booking_id",
				0);

		String queryStringPrd = "select distinct prd_id from i_inv_used where booking_id = '"
				+ bookingID + "'";
		List<String> products = db.executeQuery(queryStringPrd, "prd_id");
		if (products.size() < 1) {
			logger.error("Query returned nothing: " + queryStringPrd);
			throw new ItemNotFoundException("no inventory in used table");
		} else if (products.size() == 1) {
			prd_id = products.get(0).toString();
		} else {
			for (int i = 0; i < products.size(); i++) {
				prd_id += products.get(i) + ",";
			}
			prd_id = prd_id.substring(0, prd_id.length() - 1);
		}

		String queryString = "select usage_type_id, to_char(min(from_time),'mm-dd-yyyy'),round (max(to_time) + 1/86400 - min(from_time))"
				+ "from i_inv_used where booking_id = '"
				+ bookingID
				+ "' group by usage_type_id";
		List<String> queryResults = db.executeQuery(queryString, 3, -1);
		if (queryResults.size() < 3) {
			logger.error("Query returned nothing: " + queryString);
			throw new ErrorOnDataException("no inventory in used table");
		} else if (queryResults.size() > 3) {
			logger.error("there are more than one usage type id for one reservation: "
					+ queryString);
			throw new ErrorOnDataException(
					"there are more than one usage type id for one reservation");
		}

		String usage_type_id = queryResults.get(0).toString();
		String fromdate = queryResults.get(1).toString();
		int nights = Integer.parseInt(queryResults.get(2).toString());

		if (isNssParent) {// for NSS parent inventory check.
			queryString = "select prd_id from p_prd where parent_id = "
					+ siteID;
			List<String> prd_ids = db.executeQuery(queryString, "prd_id");
			for (int i = 0; i < products.size(); i++) {
				if (!prd_ids.contains(products.get(i))) {
					logger.error("Not a child site: " + queryString);
					throw new ErrorOnDataException("prd_id(s): "
							+ products.get(i)
							+ " is not child site from site_id:" + siteID);
				}
			}
		} else {// for SS and NSS child site check
			if (!siteID.equals(prd_id)) {
				logger.error("prd_id not match: " + queryString);
				throw new ErrorOnDataException("siteID: " + siteID
						+ " is not matched with prd_id:" + prd_id + " in DB");
			}
		}

		if (!fromdate.equals(arriveDate) || nightNum != nights) {
			logger.error("nightNum not match: " + queryString);
			throw new ErrorOnDataException("ArrivalDate/NightStay "
					+ arriveDate + "/" + nightNum + " does not match with DB "
					+ fromdate + "/" + nights);
		}

		if (status.equalsIgnoreCase("Booked")) {
			if (!usage_type_id.equals("1000")) {
				logger.error("Status is not Booked: " + queryString);
				throw new ErrorOnDataException(
						"inventory status should be booked(1000 in db), but not");
			}
		} else if (status.equalsIgnoreCase("Occupied")) {
			if (!usage_type_id.equals("1003")) {
				logger.error("Status is not Occupied: " + queryString);
				throw new ErrorOnDataException(
						"inventory status should be occupied (1003 in db), but not");
			}
		} else {
			logger.error("Unknown status: " + queryString);
			throw new ActionFailedException(
					"unhandled inventory status, we can handle inventory booked/occupied and free for now");
		}
	}
	
	public void verifySiteInvStatus(String siteID,
			String arriveDate, String depatureDate, String status,String schema){
		
		db.resetSchema(schema);
		
		String query = "select * from I_INV_USED where prd_id='"+siteID
				+"' and from_time<=to_date('"+arriveDate+" 00:00:00','mm-dd-yyyy hh24:mi:ss')"
				+" and to_time>=to_date('"+depatureDate+" 00:00:00','mm-dd-yyyy hh24:mi:ss')";
		String[] content = {"USED_ID","USAGE_TYPE_ID","ORDER_ITEM_ID","BOOKING_ID"};
		
		List<String[]> queryResults = db.executeQuery(query, content);
		
		if(null==queryResults ||queryResults.size()==0){
			throw new ErrorOnDataException("Could not find inventory used records...");
		}
		
		String[] record=queryResults.get(0);
		if (status.equalsIgnoreCase(INV_USED_STATUS_BOOKED)) {
			if (!record[1].equals("1000")) {
				logger.error("Status is not Booked: " + query);
				throw new ErrorOnDataException(
						"inventory status should be booked(1000 in db), but not");
			}
		} else if (status.equalsIgnoreCase(INV_USED_STATUS_OCCUPIED)) {
			if (!record[1].equals("1003")) {
				logger.error("Status is not Occupied: " + query);
				throw new ErrorOnDataException(
						"inventory status should be occupied (1003 in db), but not");
			}
		} else {
			logger.error("Unknown status: " + query);
			throw new ErrorOnDataException(
					"unhandled inventory status, we can handle inventory booked/occupied and free for now");
		}
		
	}

	public void verifySlipInvUnavailable(String resNum, String slipID,
			String arriveDate, int nightNum, String status, String schema) {
		verifySlipInvUnavailable(resNum, slipID, arriveDate, nightNum, status,
				false, schema);
	}

	public void verifySlipInvUnavailable(String resNum, String slipID,
			String arriveDate, int nightNum, String status,
			boolean isNssParent, String schema) {
		verifySlipInvUnavailable(resNum, slipID, arriveDate, nightNum, status,
				isNssParent, schema, true);
	}

	public void verifySlipInvUnavailable(String resNum, String slipID,
			String arriveDate, int nightNum, String status,
			boolean isNssParent, String schema,
			boolean isSlipAssociatedWithOrder) {
		verifySlipInvUnavailable(resNum, slipID, arriveDate, nightNum, status,
				isNssParent, schema, isSlipAssociatedWithOrder, false);
	}

	/**
	 * 
	 * @param resNum
	 * @param slipID
	 * @param arriveDate
	 * @param nightNum
	 * @param status
	 * @param isNssParent
	 * @param schema
	 */
	public void verifySlipInvUnavailable(String resNum, String slipID,
			String arriveDate, int nightNum, String status,
			boolean isNssParent, String schema,
			boolean isSlipAssociatedWithOrder, boolean isNSSTransientOnField) {
		sycDB();
		db.resetSchema(schema);

		logger.info("Verify slip id#" + slipID + " is " + status);
		logger.debug("Reset schema as " + schema);

		arriveDate = DateFunctions.formatDate(arriveDate, "MM-dd-yyyy");
		String[] tokens = arriveDate.split("-");
		if (tokens[0].length() == 1)
			tokens[0] = "0" + tokens[0];
		if (tokens[1].length() == 1)
			tokens[1] = "0" + tokens[1];

		arriveDate = tokens[0] + "-" + tokens[1] + "-" + tokens[2];

		String queryStringBookingID = " select booking_id from o_ord_item where ord_id = "
				+ "(select id from o_order where ord_num = '" + resNum + "')";
		// and prd_id=" + slipID;//NSS parent slip id or SS slip id

		if (isSlipAssociatedWithOrder) {
			if ((isNssParent || status.matches(INV_USED_STATUS_BOOKED + "|"
					+ INV_USED_STATUS_HOLD))
					&& !isNSSTransientOnField) {
				queryStringBookingID += " and prd_id=" + slipID;
			} else {
				queryStringBookingID += " and occ_site_id=" + slipID;
			}
		}

		String bookingID = db.executeQuery(queryStringBookingID, "booking_id",
				0);

		String queryString = "select usage_type, to_char(min(START_DATE),'mm-dd-yyyy'),round (max(END_DATE) + 1/86400 - min(START_DATE))"
				+ "from I_INV_QTY_USED where booking_id = '"
				+ bookingID
				+ "'"
				+ ((isNSSTransientOnField || status
						.equalsIgnoreCase(INV_USED_STATUS_OCCUPIED)) ? " and SITE_ID = "
						+ slipID
						: "") + " group by usage_type";
		List<String> queryResults = db.executeQuery(queryString, 3, -1);
		if (queryResults.size() < 3) {
			logger.error("Query returned nothing: " + queryString);
			throw new ErrorOnDataException("no inventory in used table");
		} else if (queryResults.size() > 3) {
			logger.error("there are more than one usage type id for one reservation: "
					+ queryString);
			throw new ErrorOnDataException(
					"there are more than one usage type id for one reservation");
		}

		String usage_type = queryResults.get(0).toString();
		String start_date = queryResults.get(1).toString();
		int nights = Integer.parseInt(queryResults.get(2).toString());

		if (!start_date.equals(arriveDate) || nightNum != nights) {
			logger.error("nightNum not match: " + queryString);
			throw new ErrorOnDataException("ArrivalDate/NightStay "
					+ arriveDate + "/" + nightNum + " does not match with DB "
					+ start_date + "/" + nights);
		}

		if (status.equalsIgnoreCase("Hold")) {
			if (!usage_type.equals("1001")) {
				logger.error("Status is not Hold: " + queryString);
				throw new ErrorOnDataException(
						"inventory status should be hold(1001 in db), but not");
			}
		} else if (status.equalsIgnoreCase("Booked")) {
			if (!usage_type.equals("1000")) {
				logger.error("Status is not Booked: " + queryString);
				throw new ErrorOnDataException(
						"inventory status should be booked(1000 in db), but not");
			}
		} else if (status.equalsIgnoreCase("Occupied")) {
			if (!usage_type.equals("1003")) {
				logger.error("Status is not Occupied: " + queryString);
				throw new ErrorOnDataException(
						"inventory status should be occupied (1003 in db), but not");
			}
		} else if (status.equalsIgnoreCase("Departed")) {
			if (!usage_type.equals("1004")) {
				logger.error("Status is not Departed: " + queryString);
				throw new ErrorOnDataException(
						"Iventory status should be departed(1004 in db), but not.");
			}
		} else {
			logger.error("Unknown status: " + queryString);
			throw new ActionFailedException(
					"unhandled inventory status, we can handle inventory booked/occupied and free for now");
		}
	}

	public void verifySlipInvUnavailable(String slipID, String arriveDate,
			int nightNum, String usedQty, String status, String schema) {
		this.verifySlipInvUnavailable(slipID, arriveDate, nightNum, usedQty,
				status, false, schema);
	}

	public void verifySlipInvUnavailable(String slipID, String arriveDate,
			int nightNum, String usedQty, String status, boolean isNSSParent,
			String schema) {

		db.resetSchema(schema);
		String queryString = "select usage_type, to_char(min(START_DATE),'mm-dd-yyyy'),round (max(END_DATE) + 1/86400 - min(START_DATE))"
				+ "from I_INV_QTY_USED where "
				+ (isNSSParent ? "SITE_ID in (select PRD_ID from P_PRD where PARENT_ID = "
						+ slipID + ") "
						: "SITE_ID = " + slipID)
				+ " and qty = "
				+ usedQty
				+ " group by usage_type";
		logger.info("Query: " + queryString);
		List<String> queryResults = db.executeQuery(queryString, 3, -1);
		if (queryResults.size() < 3) {
			logger.error("Query returned nothing: " + queryString);
			throw new ErrorOnDataException("no inventory in used table");
		} else if (queryResults.size() > 3) {
			logger.error("there are more than one usage type id for one reservation: "
					+ queryString);
			throw new ErrorOnDataException(
					"there are more than one usage type id for one reservation");
		}

		String usage_type = queryResults.get(0).toString();
		String start_date = queryResults.get(1).toString();
		int nights = Integer.parseInt(queryResults.get(2).toString());

		if ((DateFunctions.compareDates(start_date, arriveDate) != 0)
				|| (nightNum != nights)) {
			logger.error("nightNum not match: " + queryString);
			throw new ErrorOnDataException("ArrivalDate/NightStay "
					+ arriveDate + "/" + nightNum + " does not match with DB "
					+ start_date + "/" + nights);
		}

		if (status.equalsIgnoreCase("Booked")) {
			if (!usage_type.equals("1000")) {
				logger.error("Status is not Booked: " + queryString);
				throw new ErrorOnDataException(
						"inventory status should be booked(1000 in db), but not");
			}
		} else if (status.equalsIgnoreCase("Occupied")) {
			if (!usage_type.equals("1003")) {
				logger.error("Status is not Occupied: " + queryString);
				throw new ErrorOnDataException(
						"inventory status should be occupied (1003 in db), but not");
			}
		} else if (status.equalsIgnoreCase("Departed")) {
			if (!usage_type.equals("1004")) {
				logger.error("Status is not Departed: " + queryString);
				throw new ErrorOnDataException(
						"Iventory status should be departed(1004 in db), but not.");
			}
		} else if (status.equalsIgnoreCase("Hold")) {
			if (!usage_type.equals("1001")) {
				logger.error("Status is not Departed: " + queryString);
				throw new ErrorOnDataException(
						"Iventory status should be departed(1001 in db), but not.");
			}
		} else {
			logger.error("Unknown status: " + queryString);
			throw new ActionFailedException(
					"unhandled inventory status, we can handle inventory booked/occupied and free for now");
		}

		logger.info("Verify slip id#" + slipID + " is " + status);
	}

	/**
	 * This method goes to verify whether or not the site is available in given
	 * period for given arrival date.
	 * 
	 * @param siteIDs
	 *            - site ID
	 * @param arriveDate
	 *            - arrival date
	 * @param nightNum
	 *            - number of stay
	 */
	public void verifyInvAvailable(String[] siteIDs, String arriveDate,
			int nightNum, String schema) {
		for (int i = 0; i < siteIDs.length; i++) {
			verifyInvAvailable(siteIDs[i], arriveDate, nightNum, schema);
		}
	}

	/**
	 * Verify inventory available
	 * 
	 * @param siteID
	 * @param arriveDate
	 * @param nightNum
	 */
	public void verifyInvAvailable(String siteID, String arriveDate,
			int nightNum, String schema) {
		sycDB();
		// String Temp;
		db.resetSchema(schema);

		logger.info("Verify siteId#" + siteID + " Available/Released.");

		Calendar depart = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		DateFormat formatter1 = new SimpleDateFormat("MM-dd-yyyy kk:mm:ss");

		Date arrival = DateFunctions.parseDateString(arriveDate);
		arriveDate = formatter.format(arrival);

		depart.setTime(arrival);
		depart.add(Calendar.DAY_OF_MONTH, nightNum);
		depart.add(Calendar.SECOND, -1);

		String departureDate = formatter1.format(depart.getTime());

		String queryString = "SELECT count(*) as count FROM I_INV_AVAIL where PRD_ID "
				+ " in ("
				+ siteID
				+ ")"
				+ " and START_TIME <= TO_DATE('"
				+ arriveDate
				+ "', 'mm-dd-yyyy') "
				+ "and END_TIME >= TO_DATE('"
				+ departureDate
				+ "', 'mm-dd-yyyy hh24:mi:ss')"
				+ "and ACTIVE_IND > 0 and DELETED_IND <> 1 "
				+ "and PRD_ID not in (SELECT PRD_ID FROM I_INV_USED where DELETED_IND <> 1 and ( "
				+ "(TO_DATE('"
				+ arriveDate
				+ "', 'mm-dd-yyyy') <= FROM_TIME and FROM_TIME < TO_DATE('"
				+ departureDate
				+ "', 'mm-dd-yyyy hh24:mi:ss')) OR "
				+ "(FROM_TIME <= TO_DATE('"
				+ arriveDate
				+ "', 'mm-dd-yyyy') and TO_DATE('"
				+ arriveDate
				+ "', 'mm-dd-yyyy')<= TO_TIME)))";

		if (db.executeQuery(queryString, "count").size() != 1) {
			throw new RuntimeException(
					"Verify inventory failed: inventory has not be released");
		}
	}

	public void verifySlipInvAvailable(String slipID, String arriveDate,
			int nightNum, String schema) {
		sycDB();
		db.resetSchema(schema);

		logger.info("Verify slip ID#" + slipID + " is Available/Released.");

		Calendar depart = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		DateFormat formatter1 = new SimpleDateFormat("MM-dd-yyyy kk:mm:ss");

		Date arrival = DateFunctions.parseDateString(arriveDate);
		arriveDate = formatter.format(arrival);

		depart.setTime(arrival);
		depart.add(Calendar.DAY_OF_MONTH, nightNum);
		depart.add(Calendar.SECOND, -1);

		String departureDate = formatter1.format(depart.getTime());

		String queryString = "select count(*) as count from I_INV_AVAIL where PRD_ID "
				+ " in ("
				+ slipID
				+ ")"
				+ " and START_TIME <= TO_DATE('"
				+ arriveDate
				+ "', 'mm-dd-yyyy') "
				+ "and END_TIME >= TO_DATE('"
				+ departureDate
				+ "', 'mm-dd-yyyy hh24:mi:ss')"
				+ "and ACTIVE_IND > 0 and DELETED_IND <> 1 "
				+ "and PRD_ID not in (SELECT PRD_ID FROM I_INV_USED where DELETED_IND <> 1 and ( "
				+ "(TO_DATE('"
				+ arriveDate
				+ "', 'mm-dd-yyyy') <= FROM_TIME and FROM_TIME < TO_DATE('"
				+ departureDate
				+ "', 'mm-dd-yyyy hh24:mi:ss')) OR "
				+ "(FROM_TIME <= TO_DATE('"
				+ arriveDate
				+ "', 'mm-dd-yyyy') and TO_DATE('"
				+ arriveDate
				+ "', 'mm-dd-yyyy')<= TO_TIME)))";

		logger.info("Execute sql: " + queryString);
		if (db.executeQuery(queryString, "count").size() != 1) {
			throw new RuntimeException(
					"Verify inventories failed: inventories have not be released.");
		} else
			logger.info("Verify inventories succeed: inventories have been released.");
	}

	/**
	 * Get the current transaction id for the given reservation number
	 * 
	 * @param resID
	 * @return transaction ID
	 */
	public String getTransaction(String resID, String schema) {
		sycDB();
		db.resetSchema(schema);

		logger.info("Get resId#" + resID + " use fee amount.");

		String query = "select id from o_ord_item_trans where current_status = 2 and ord_id = (select id from o_order where ord_num = '"
				+ resID + "')";

		logger.debug("Run query: " + query);

		String result = db.executeQuery(query, "id", 0);

		return result;
	}

	/**
	 * Get the use fee amount for the given transaction ID and given reservation
	 * number
	 * 
	 * @param resID
	 * @param trans
	 * @return
	 */
	public double getUseFeeAmount(String resID, String trans, String schema) {
		sycDB();
		db.resetSchema(schema);
		logger.info("Get resId#" + resID + " use fee amount.");
		String query = "select sum(fee_amt) as use_Fee from O_ORD_ITEM_FEE where ORD_ITEM_ID in (select ID from O_ORD_ITEM where ORD_ID in (select ID from O_ORDER where ORD_NUM = '"
				+ resID
				+ "')) and fee_type_id = 2 and ord_item_tran_id = "
				+ trans;

		logger.debug("Run query: " + query);

		String usefee = db.executeQuery(query, "USE_FEE", 0);

		logger.info("usefee=" + usefee);
		return Double.parseDouble(usefee);
	}

	/**
	 * Get the penalty fee amount for the given transaction ID and given
	 * reservation number
	 * 
	 * @param resID
	 * @param trans
	 * @return
	 */
	public double getPenaltyAmount(String resID, String trans, String schema) {
		sycDB();
		db.resetSchema(schema);

		logger.info("Get resId#" + resID + " penalty amount.");
		String query = "select sum(fee_amt) as penalty from O_ORD_ITEM_FEE where ORD_ITEM_ID = (select ID from O_ORD_ITEM where ORD_ID = (select ID from O_ORDER where ORD_NUM = '"
				+ resID
				+ "')) and fee_type_id = 31 and ord_item_tran_id ="
				+ trans;
		// if(adjusted)
		// query="select sum(fee_amt) as penalty from O_ORD_ITEM_FEE where ORD_ITEM_ID = (select ID from O_ORD_ITEM where ORD_ID = (select ID from O_ORDER where ORD_NUM = '"+resID+"')) and fee_type_id = 31 and ord_item_tran_id ="+trans+" and fee_adjust_ind=1";

		logger.debug("Run query: " + query);

		String penalty = db.executeQuery(query, "PENALTY", 0);
		logger.info("Penalty=" + penalty);

		return Double.parseDouble(penalty);
	}

	/**
	 * Get the discount amount for the given transaction ID and given
	 * reservation number
	 * 
	 * @param resID
	 * @param trans
	 * @return
	 */
	public double getDiscountAmount(String resID, String schema) {
		sycDB();
		db.resetSchema(schema);

		logger.info("Get resId#" + resID + " discount amount.");

		String query = "select * from O_ORD_ITEM_DISCNT_SUM where ORD_ITEM_ID in (select ID from O_ORD_ITEM where ORD_ID in (select ID from O_ORDER where ORD_NUM = '"
				+ resID + "'))";

		logger.debug("Run query: " + query);

		String discount = db.executeQuery(query, "DISCNT_AMT", 0);
		logger.info("discount=" + discount);
		return Double.parseDouble(discount);
	}

	/**
	 * Generate a new email address for creating new account purpose
	 * 
	 * @param webEnv
	 *            - "ra" or "rec" or "pl"
	 * @return
	 */
	public String generateNewWebEmail(String webEnv, String qaEnv) {
		sycDB();
		// String seqVar = "WEB_SEQ";
		//
		// String seqStr = readQADB(seqVar);

		// int seq = Integer.parseInt(seqStr);
		// int seq = DataBaseFunctions.getEmailSequence();
		String seq = new DecimalFormat("00000").format(DataBaseFunctions
				.getEmailSequence());
		String email = qaEnv.toLowerCase() + "_" + webEnv.toLowerCase() + seq
				+ "@reserveamerica.com";

		String schema = TestProperty.getProperty(env + ".db.schema.prefix")
				+ "COMMON";

		while (checkEmailExists(schema, email)) {
			seq = new DecimalFormat("00000").format(DataBaseFunctions
					.getEmailSequence());
			// if (seq > 99999 || seq < 10000)
			// seq = 10000;
			email = qaEnv.toLowerCase() + "_" + webEnv.toLowerCase() + seq
					+ "@reserveamerica.com";
		}

		logger.info("Get a new login email to create: " + email);
		return email;
	}

	/**
	 * Retrieve the alt_xxxxx@reserveamerica.com email account with the next
	 * sequence number.
	 * 
	 * @return
	 */
	public String getNextEmail() {
		// int seq = DataBaseFunctions.getEmailSequence();

		String seq = new DecimalFormat("00000").format(DataBaseFunctions
				.getEmailSequence());

		String email = "alt_" + seq + "@reserveamerica.com";

		logger.info("Get a customer email: " + email);
		return email;
	}

	/**
	 * get the phone num from db for the given customer
	 * 
	 * @param schema
	 * @param email
	 * @return
	 * @Return String
	 * @Throws
	 */
	public String getPhoneNumber(String schema, String email) {
		String sql = "select a.val val from C_CUST_PHONE a,"
				+ "C_CUST_PHONE b where a.TYP='HOME' and a.CUST_ID=b.CUST_ID and  "
				+ "b.val='" + email + "' and b.TYP='EMAIL'";

		logger.info("get a existent home phone from db....");
		db.resetSchema(schema);

		List<String> phoneNums = db.executeQuery(sql, "val");

		if (phoneNums == null || phoneNums.size() < 1) {
			throw new ItemNotFoundException(
					"Can't get the telephone num from DB for " + email);
		}

		logger.info("the home phone num is " + phoneNums.get(0));
		return phoneNums.get(0);
	}

	/**
	 * Verify the pet info.
	 * 
	 * @param reservNum
	 *            - reservation number
	 * @param catnum
	 *            - number of cats
	 * @param dognum
	 *            - number of dog
	 * @param othernum
	 *            - number of other pets
	 * @param horsenum
	 *            - number of horse
	 * @param schema
	 */
	public void verifyPetInfo(String reservNum, String catnum, String dognum,
			String othernum, String horsenum, String schema) {
		sycDB();
		// String schema=schema_prefix+"SC";
		// String schema=env+"_AUTO";
		db.resetSchema(schema);
		logger.info("verify the pet info");

		String query1 = "SELECT ID from O_ORDER where ORD_NUM=" + "'"
				+ reservNum + "'";
		String ID = db.executeQuery(query1, "ID", 0);
		String query2 = "SELECT PET_TYPE_ID,PET_QTY FROM O_ORD_PROFILE_PET where ORD_ID="
				+ "'" + ID + "'";
		List<String> PETTYPEID = db.executeQuery(query2, "PET_TYPE_ID");
		List<String> PETQTY = db.executeQuery(query2, "PET_QTY");
		if (PETTYPEID.size() > 0) {
			for (int i = 0; i < PETTYPEID.size(); i++) {
				if ((!catnum.equalsIgnoreCase(""))
						&& PETTYPEID.get(i).toString().equalsIgnoreCase("601"))
					if (PETQTY.get(i).toString().equalsIgnoreCase(catnum))
						continue;
					else
						throw new RuntimeException("Fail to find pet info");
				if ((!dognum.equalsIgnoreCase(""))
						&& PETTYPEID.get(i).toString().equalsIgnoreCase("600"))
					if (PETQTY.get(i).toString().equalsIgnoreCase(dognum))
						continue;
					else
						throw new RuntimeException("Fail to find pet info");
				if ((!othernum.equalsIgnoreCase(""))
						&& PETTYPEID.get(i).toString().equalsIgnoreCase("603"))
					if (PETQTY.get(i).toString().equalsIgnoreCase(othernum))
						continue;
					else
						throw new RuntimeException("Fail to find pet info");
				if ((!horsenum.equalsIgnoreCase(""))
						&& PETTYPEID.get(i).toString().equalsIgnoreCase("602"))
					if (PETQTY.get(i).toString().equalsIgnoreCase(horsenum))
						continue;
					else
						throw new RuntimeException("Fail to find pet info");
			}
		} else
			throw new RuntimeException("Fail to find pet info");
	}

	/**
	 * Get pin number from d_user_auth
	 * 
	 * @param username
	 * @param schema
	 * @return
	 */
	public String getPinNum(String username) {
		String schema = "";
		env = TestProperty.getProperty("target_env").toLowerCase();
		schema = TestProperty.getProperty(env + ".global.schema");

		db.resetSchema(schema);

		String query = "select pin from " + schema
				+ ".d_user_auth where login='" + username + "'";

		String pin = db.executeQuery(query, "pin", 0);

		return pin;
	}

	/**
	 * Update affected orders indicator from i_clo_schdl
	 * 
	 * @param closureID
	 * @param schema
	 * @param affectedOrdIndStatus
	 *            : 0 means false and 1 means true
	 */
	public void updateAffectedOrderInd(String closureID,
			String affectedOrdIndStatus, String schema) {
		sycDB();
		db.resetSchema(schema);

		String query = "update " + schema
				+ ".i_clo_schdl set affected_ord_ind = " + "'"
				+ affectedOrdIndStatus + "'" + "where id =" + closureID;

		db.executeUpdate(query);
	}

	/**
	 * Update customer pass validate expiry date indicator
	 * 
	 * @param schema
	 * @param cusPassType
	 * @param validate_expiry_date_ind
	 *            : 1:display, 0:not displa
	 */
	public void updateCustPassValidateExpiryDateInd(String schema,
			String cusPassType, int validate_expiry_date_ind) {
		sycDB();
		db.resetSchema(schema);

		String query = "update D_REF_CUST_PASS_TYPE set validate_expiry_date_ind="
				+ validate_expiry_date_ind
				+ " where name = '"
				+ cusPassType
				+ "'";

		db.executeUpdate(query);
	}

	/**
	 * Update facility amenities and services info based on given facilityID.
	 * 
	 * @param facilityID
	 * @param schema
	 * @param isActivate
	 *            : true - activate facilities amenities and services; false-
	 *            deactivate facilities amenities and services;
	 */
	public void updateAmenitiesAndServicesForFacility(String facilityID,
			String schema, boolean isActivate) {
		db.resetSchema(schema);
		String query = "";
		if (isActivate) {
			query = "update I_ACTV_SVC set DELETED_IND=0 where ID in (select actv_svc_id from I_LOC_ACTV_SVC where loc_id= '"
					+ facilityID + "')";
		} else {
			query = "update I_ACTV_SVC set DELETED_IND=1 where ID in (select actv_svc_id from I_LOC_ACTV_SVC where loc_id= '"
					+ facilityID + "')";
		}

		db.executeUpdate(query);
	}

	/**
	 * Update application id from i_clo_schdl
	 * 
	 * @param closureID
	 * @param application
	 *            : 4 means 'InventoryManager' and 6 means 'FieldManager'
	 * @param schema
	 */
	public void updateApplication(String closureID, String application,
			String schema) {
		sycDB();
		db.resetSchema(schema);

		String query = "update " + schema + ".i_clo_schdl set create_app_id = "
				+ "'" + application + "'" + "where id =" + closureID;

		db.executeUpdate(query);
	}

	public void activatePOS(String prdCode, String schema) {
		sycDB();
		db.resetSchema(schema);

		String query = "UPDATE P_PRD SET ACTIVE_IND=1 , DELETED_IND=0 "
				+ "WHERE PRD_CD='" + prdCode + "' AND PRODUCT_CAT_ID=4";

		logger.info("update SQL: " + query);
		db.executeUpdate(query);
	}

	/**
	 * Update affected orders indicator from i_clo_schdl
	 * 
	 * @param closureID
	 * @param affectedOrdInstr
	 *            : 0:affected_ord_instr;1:Not Specified; 2:Honor
	 *            Reservations;3:Honor Cancel Reservations;4:Transfer
	 *            Reservations
	 * @param schema
	 */
	public void updateAffectedOrderInstr(String closureID,
			String affectedOrdInstr, String schema) {
		sycDB();
		db.resetSchema(schema);

		String query = "update " + schema
				+ ".i_clo_schdl set affected_ord_instr = " + "'"
				+ affectedOrdInstr + "'" + "where id =" + closureID;

		db.executeUpdate(query);
	}

	/**
	 * Get affected orders indicator from i_clo_schdl
	 * 
	 * @param closureID
	 * @return
	 */
	public String getAffectedOrderInd(String closureID, String schema) {
		sycDB();
		db.resetSchema(schema);

		String query = "select affected_ord_ind from " + schema
				+ ".i_clo_schdl where id =" + closureID;

		String affectedOrdInd = db.executeQuery(query, "affected_ord_ind", 0);

		return affectedOrdInd;
	}

	/**
	 * Verify closure information from i_clo_schdl
	 * 
	 * @param closureID
	 * @param affectedOrdInstr
	 * @param affectedOrdInd
	 * @param createdAppID
	 * @param createdUserID
	 * @param schema
	 */
	public void verifyClosureSchedl(String closureID, String affectedOrdInstr,
			String affectedOrdInd, String createdAppID, String createdUserID,
			String schema) {
		sycDB();
		db.resetSchema(schema);
		logger.info("Verify Closure" + closureID + " information in DB");
		logger.debug("Reset schema as " + schema);

		String query = "select affected_ord_instr,affected_ord_ind,create_app_id,create_user_id from "
				+ schema + ".i_clo_schdl where id =" + closureID;

		List<String> result = db.executeQuery(query, 4, 1);
		logger.debug("Execute query: " + query);
		if (result.size() < 4) {
			logger.error("Query returns nothing");
			throw new ItemNotFoundException("Query returns nothing: " + query);
		}

		if (null != affectedOrdInstr && affectedOrdInstr.length() > 0) {
			int actualValue = Integer.parseInt(result.get(0).toString());
			if (affectedOrdInstr.equals(actualValue)) {
				throw new ItemNotFoundException("affectedOrdInstr: "
						+ " expect affected_ord_instr = " + affectedOrdInstr
						+ " actual value in DB = " + actualValue);
			}
		}

		if (null != affectedOrdInd && affectedOrdInd.length() > 0) {
			int actualValue = Integer.parseInt(result.get(1).toString());
			if (affectedOrdInd.equals(actualValue)) {
				throw new ItemNotFoundException("affectedOrdInstr: "
						+ " expect affected_ord_ind = " + affectedOrdInd
						+ " actual value in DB = " + actualValue);
			}
		}

		if (null != createdAppID && createdAppID.length() > 0) {
			int actualValue = Integer.parseInt(result.get(2).toString());
			if (createdAppID.equals(actualValue)) {
				throw new ItemNotFoundException("appID: "
						+ " expect create_app_id = " + affectedOrdInd
						+ " actual value in DB = " + actualValue);
			}
		}

		if (null != createdUserID && createdUserID.length() > 0) {
			int actualValue = Integer.parseInt(result.get(3).toString());
			if (createdUserID.equals(actualValue)) {
				throw new ItemNotFoundException("createdUserID: "
						+ " expect create_user_id = " + createdUserID
						+ " actual value in DB = " + actualValue);
			}
		}
	}

	/**
	 * check whether the siteID is non site specific group
	 * 
	 * @param siteID
	 * @param parkID
	 * @param schema
	 * @return
	 */
	public boolean isNonSiteSpecificGroup(String siteID, String parkID,
			String schema) {

		db.resetSchema(schema);

		String query = "SELECT PRD_ID FROM p_prd p, d_loc l WHERE p.park_id=l.id and p.product_cat_id=3 and p.prd_rel_type=2 and active_ind=1 and deleted_ind=0 and LOC_ID ="
				+ siteID + "and PRD_ID=" + parkID;

		String result = db.executeQuery(query, "PRD_ID", 0);

		if (result.length() > 0) {
			return true;
		}

		return false;
	}

	/**
	 * update user name from email account to old customer use name which do not
	 * require signing with emial
	 * 
	 * @param email
	 *            - new customer login name
	 * @param name
	 *            - old customer login name
	 * @return
	 */
	public String updateToOldLoginName(String email, String name) {
		String schema = TestProperty.getProperty(env + ".db.schema.prefix")
				+ "COMMON";

		while (checkLoginNameExists(schema, name)) {
			name = name + 0;// add 0 suffix to user name to make unique
		}

		String query = "update C_CUST set LOGIN_NAME = \'" + name + "\' where "
				+ "LOGIN_NAME = \'" + email + "\'";
		int effectLint = db.executeUpdate(query);

		if (effectLint == 1) {
			logger.info("Generate a new login name " + name + ".");
		} else {
			throw new ItemNotFoundException("User " + email + " did not find.");
		}

		return name;
	}

	/**
	 * query fee schedule information work flow: if there is no fee schedule
	 * id,it will query fee schedule id first,then query fee rate by fee
	 * schedule id. This method used to query attribute fee, use fee,
	 * transaction fee, ticket fee, pos fee
	 * 
	 * @param feeData
	 *            : start date,departure date,product id
	 * @param schema
	 * @param id
	 * @param feeType
	 * @param isGroup
	 * @param isIncr
	 *            -- for transaction fee/ra fee which unit type is
	 *            'UNITTYPE_FLAT_BY_RANGE_OF_TICKET_QUANTITY'
	 * @param isPOS
	 * @param isTicket
	 * @return
	 */
	public Map<String, List<List<String[]>>> queryFeeInfo(
			FeeValidationData feeData, String schema, List<String> id,
			String feeType, boolean isGroup, boolean isIncr, boolean isPOS,
			boolean isTicket, boolean isSlip) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema);

		String query = "";
		List<String> feeId = id;
		if (feeId == null || feeId.size() == 0) {
			if (FEETYPE_TRANFEE.equals(feeType)
					|| FEETYPE_RATRANFEE.equals(feeType)) {
				feeId = queryTransactionFeeSchdIdByDate(feeData, schema,
						feeType);
			} else {
				feeId = queryFeeSchdIdByDate(feeData, schema, feeType);
			}
		}

		String[] timeAmount = { "MON_FEE_AMT", "TUE_FEE_AMT", "WED_FEE_AMT",
				"THU_FEE_AMT", "FRI_FEE_AMT", "SAT_FEE_AMT", "SUN_FEE_AMT",
				"PER_TIME_FEE_AMT", "PER_TIME_ID", "FEE_SCHD_ID",
				"ADMISSION_TYPE_ID" };
		String[] groupAmount = { "TARGET_TYPE_ID", "INCR_TYPE_ID", "INCR_QTY",
				"BASE_INCR_RATE", "MON_INCR_RATE", "TUE_INCR_RATE",
				"WED_INCR_RATE", "THU_INCR_RATE", "FRI_INCR_RATE",
				"SAT_INCR_RATE", "SUN_INCR_RATE", "FEE_SCHD_ID",
				"ADMISSION_TYPE_ID" };
		String[] holidayAmount = { "RATE", "NAME" };
		String[] otherAmount = { "FEE_AMT", "FEE_SCHD_ID", "ADMISSION_TYPE_ID",
				"FEE_AMT_TO", "FEE_AMT_FLAT" };
		String[] flatByTicketQtyRangeAmount = { "TARGET_TYPE_ID",
				"INCR_TYPE_ID", "INCR_QTY", "BASE_INCR_RATE", "FEE_SCHD_ID",
				"ADMISSION_TYPE_ID" };
		String[] conds = { "UNIT_TYPE_ID", "TRAN_TYPE_ID", "TRAN_OCCUR_ID",
				"FEE_TARGET_ID", "MAX_FEE", "MAX_FEE_RESTR_TYPE",
				"DATECHANGE_TRANS_APPLIES_TO" };
		String[] raAmount = { "RATE", "FEE_SCHD_ID", "ADMISSION_TYPE_ID",
				"TO_RATE" };
		String[] slipAmount = { "RANGE_FEET", "NIGHTLY_FEE", "WEEKLY_FEE",
				"MONTHLY_FEE", "SEASON_FEE", "MONDAY_FEE", "TUESDAY_FEE",
				"WEDNESDAY_FEE", "THURSDAY_FEE", "FRIDAY_FEE", "SATURDAY_FEE",
				"SUNDAY_FEE" };
		List<List<String[]>> amount = new ArrayList<List<String[]>>();
		List<List<String[]>> groupFee = new ArrayList<List<String[]>>();
		List<List<String[]>> rangeFee = new ArrayList<List<String[]>>();
		List<List<String[]>> cond = new ArrayList<List<String[]>>();
		List<String[]> result = null;
		List<String[]> condResult = null;

		Map<String, List<List<String[]>>> fee = new HashMap<String, List<List<String[]>>>();
		if (feeId == null || feeId.size() == 0) {
			throw new ErrorOnDataException(
					"database query fee_schd_id is failed");
		}
		Iterator<String> listIte = feeId.iterator();
		while (listIte.hasNext()) {
			String schdid = listIte.next();
			if (isPOS) {
				logger.debug("Query pos fee schedule information.");
				query = "SELECT * FROM p_other_fee_schd"
						+ " WHERE FEE_SCHD_ID =" + schdid;
				result = db.executeQuery(query, otherAmount);
				amount.add(result);
			} else if (isTicket) {
				logger.debug("Query ticket fee schedule information.");
				if (!isGroup) {
					query = "SELECT * FROM p_per_time_fee_schd"
							+ " WHERE FEE_SCHD_ID =" + schdid;
					result = db.executeQuery(query, timeAmount);
					amount.add(result);
				} else {
					query = "SELECT * FROM p_fee_schd_incr"
							+ " WHERE FEE_SCHD_ID =" + schdid;
					result = db.executeQuery(query, groupAmount);
					groupFee.add(result);
				}
				query = "SELECT * FROM p_fee_schd" + " WHERE ID =" + schdid;
				condResult = db.executeQuery(query, conds);
				cond.add(condResult);
			} else if (FEETYPE_TRANFEE.equals(feeType)) {
				logger.debug("Query transaction fee schedule information.");
				if (isIncr) {// use this flag to indicate the unit type is
					// 'UNITTYPE_FLAT_BY_RANGE_OF_TICKET_QUANTITY'
					query = "SELECT * FROM p_fee_schd_incr"
							+ " WHERE FEE_SCHD_ID =" + schdid;
					result = db.executeQuery(query, flatByTicketQtyRangeAmount);
					rangeFee.add(result);
				} else {
					query = "SELECT * FROM p_other_fee_schd"
							+ " WHERE FEE_SCHD_ID =" + schdid;
					result = db.executeQuery(query, otherAmount);
					amount.add(result);
				}
				query = "SELECT * FROM p_fee_schd" + " WHERE ID =" + schdid;
				condResult = db.executeQuery(query, conds);
				cond.add(condResult);
			} else if (FEETYPE_RATRANFEE.equals(feeType)) {
				logger.debug("Query ra fee schedule information.");
				if (isIncr) {// use this flag to indicate the unit type is
					// 'UNITTYPE_FLAT_BY_RANGE_OF_TICKET_QUANTITY'
					query = "SELECT * FROM p_fee_schd_incr"
							+ " WHERE FEE_SCHD_ID =" + schdid;
					result = db.executeQuery(query, flatByTicketQtyRangeAmount);
					rangeFee.add(result);
				} else {
					query = "SELECT * FROM p_rafee_rate"
							+ " WHERE FEE_SCHD_ID =" + schdid;
					result = db.executeQuery(query, raAmount);
					amount.add(result);
				}
				query = "SELECT * FROM p_fee_schd" + " WHERE ID =" + schdid;
				condResult = db.executeQuery(query, conds);
				cond.add(condResult);
			} else if (isSlip) {
				query = "SELECT * FROM P_SLIP_FEE_RANGE"
						+ " WHERE slip_fee_schd_id =" + schdid;
				result = db.executeQuery(query, slipAmount);
				amount.add(result);
			} else {
				logger.debug("Query base fee schedule information.");
				query = "SELECT * FROM p_per_time_fee_schd"
						+ " WHERE FEE_SCHD_ID =" + schdid;
				result = db.executeQuery(query, timeAmount);
				amount.add(result);
				if (feeData.isHoliday) {
					query = "SELECT * FROM p_fee_schd_holiday"
							+ " WHERE FEE_SCHD_ID =" + schdid;
					condResult = db.executeQuery(query, holidayAmount);
					cond.add(condResult);
				}
				if (isGroup) {
					query = "SELECT * FROM p_fee_schd_incr"
							+ " WHERE FEE_SCHD_ID =" + schdid;
					result = db.executeQuery(query, groupAmount);
					groupFee.add(result);
				}
			}
		}

		logger.info("Execute query SQL:" + query);
		if (isSlip) {
			fee.put("slip", amount);
		} else
			fee.put("base fee", amount);
		fee.put("Group", groupFee);
		fee.put("range fee", rangeFee);
		fee.put("Condition", cond);
		return fee;
	}

	/**
	 * query fee schedule id
	 * 
	 * @param feeData
	 *            : start date,departure date,product id
	 * @param schema
	 * @param feeType
	 * @return
	 */
	public List<String> queryFeeSchdIdByDate(FeeValidationData feeData,
			String schema, String feeType) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema + " ; Query fee schedule id");

		String startdate = "";
		String enddate = "";
		String pattern = new RegularExpression(
				"[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}", false).toString();
		if ("".equals(feeData.arriveDate)) {
			feeData.arriveDate = DateFunctions.getDateAfterToday(1);
		}
		if ("".equals(feeData.departureDate)) {
			feeData.departureDate = DateFunctions.getDateAfterToday(1);
		}
		if (feeData.arriveDate.matches(pattern)) {
			startdate = feeData.arriveDate;
		} else {
			startdate = DateFunctions.parseMonthValue(feeData.arriveDate
					.substring(4, 7))
					+ "/"
					+ feeData.arriveDate.substring(8, 10)
					+ "/"
					+ feeData.arriveDate.substring(11, 15);
		}
		if (feeData.departureDate.matches(pattern)) {
			enddate = feeData.departureDate;
		} else {
			enddate = DateFunctions.parseMonthValue(feeData.departureDate
					.substring(4, 7))
					+ "/"
					+ feeData.departureDate.substring(8, 10)
					+ "/"
					+ feeData.departureDate.substring(11, 15);
		}

		String query = "SELECT p_fee_schd.ID,START_DATE FROM(p_fee_schd INNER JOIN p_fee_cond ON p_fee_schd.FEE_COND_ID=p_fee_cond.ID)"
				+ " INNER JOIN p_fee ON p_fee_cond.FEE_ID=p_fee.FEE_ID"
				+ " WHERE ( START_DATE<=to_date('"
				+ startdate
				+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
				+ "AND END_DATE >=to_date('"
				+ enddate
				+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
				+ "OR START_DATE>=to_date('"
				+ startdate
				+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
				+ "AND START_DATE<=to_date('"
				+ enddate
				+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
				+ "OR END_DATE >=to_date('"
				+ startdate
				+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
				+ "AND END_DATE <=to_date('"
				+ enddate
				+ " 00:00:00','mm-dd-yyyy hh24:mi:ss'))"
				+ "AND p_fee.FEE_TYPE_ID='"
				+ feeType
				+ "' AND schd_type != '5' and ACTIVE_IND='1'AND PRD_ID='"
				+ feeData.productID + "'";// Shane/Vivian[20131202]/[20131205],
											// add schedule type=0 due to an
											// account schedule will be created
											// too from same table since 3.05

		if (!"".equals(feeData.permitTypeID)) {
			query = query + " AND PERMIT_TYPE_ID='" + feeData.permitTypeID
					+ "'";
		}

		if (!"".equals(feeData.locationID)) {
			query = query + " AND LOC_ID='" + feeData.locationID + "'";
		}

		query = query + " ORDER BY START_DATE ASC";
		logger.info("Execute query SQL: " + query);
		List<String> feeId = db.executeQuery(query, "ID");
		return feeId;
	}

	/**
	 * query fee schedule id
	 * 
	 * @param feeData
	 *            : start date,departure date,product id
	 * @param schema
	 * @param feeType
	 * @return
	 */
	public List<String> queryTransactionFeeSchdIdByDate(
			FeeValidationData feeData, String schema, String feeType) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema
				+ " ; Query transaction fee schedule id");

		String startdate = "";
		String enddate = "";
		String pattern = new RegularExpression(
				"[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}", false).toString();
		if ("".equals(feeData.arriveDate)) {
			feeData.arriveDate = DateFunctions.getDateAfterToday(1);
		}
		if ("".equals(feeData.departureDate)) {
			feeData.departureDate = DateFunctions.getDateAfterToday(1);
		}
		if (feeData.arriveDate.matches(pattern)) {
			startdate = feeData.arriveDate;
		} else {
			startdate = DateFunctions.parseMonthValue(feeData.arriveDate
					.substring(4, 7))
					+ "/"
					+ feeData.arriveDate.substring(8, 10)
					+ "/"
					+ feeData.arriveDate.substring(11, 15);
		}
		if (feeData.departureDate.matches(pattern)) {
			enddate = feeData.departureDate;
		} else {
			enddate = DateFunctions.parseMonthValue(feeData.departureDate
					.substring(4, 7))
					+ "/"
					+ feeData.departureDate.substring(8, 10)
					+ "/"
					+ feeData.departureDate.substring(11, 15);
		}

		String query = "SELECT p_fee_schd.ID,EFFECT_DATE FROM(p_fee_schd INNER JOIN p_fee_cond ON p_fee_schd.FEE_COND_ID=p_fee_cond.ID)"
				+ " INNER JOIN p_fee ON p_fee_cond.FEE_ID=p_fee.FEE_ID"
				+ " INNER JOIN p_condition ON p_fee_cond.COND_ID=p_condition.ID"
				+ " WHERE ( EFFECT_DATE<=to_date('"
				+ startdate
				+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
				+ "OR EFFECT_DATE<=to_date('"
				+ enddate
				+ " 00:00:00','mm-dd-yyyy hh24:mi:ss'))"
				+ "AND p_fee.FEE_TYPE_ID='" + feeType + "' AND ACTIVE_IND='1'";

		if (!"".equals(feeData.productID)) {
			query = query + " AND PRD_ID='" + feeData.productID + "'";
		} else {
			query = query + " AND PRD_ID is null AND GRP_ID is null";
		}

		if (!"".equals(feeData.locationID)) {
			query = query + " AND LOC_ID='" + feeData.locationID + "'";
		}

		if (!"".equals(feeData.tranType)) {
			query = query + " AND p_fee_schd.TRAN_TYPE_ID='" + feeData.tranType
					+ "'";
		}

		if (!"".equals(feeData.applyLevel)) {
			query = query + " AND p_fee_schd.fee_target_id='"
					+ feeData.applyLevel + "'";
		}

		if (!"".equals(feeData.permitTypeID)) {
			query = query + " AND p_fee_schd.permit_type_id='"
					+ feeData.permitTypeID + "'";
		}

		//
		if (!"".equals(feeData.salesChannel)) {
			query = query + " AND p_condition.sales_chanl_id='"
					+ feeData.salesChannel + "'";
		}

		//
		if (!"".equals(feeData.deliveryMethod)
				&& feeData.deliveryMethod.length() > 0) {
			if (feeData.deliveryMethod.equalsIgnoreCase("null")) {
				query = query + " AND p_fee_schd.delivery_method_id is null";
			} else {
				query = query + " AND p_fee_schd.delivery_method_id='"
						+ feeData.deliveryMethod + "'";
			}
		}

		//
		if (!"".equals(feeData.tranOccurance)) {
			query = query + " AND p_fee_schd.tran_occur_id='"
					+ feeData.tranOccurance + "'";
		}

		query = query + " ORDER BY EFFECT_DATE ASC";
		logger.info("Execute query SQL:" + query);
		List<String> feeId = db.executeQuery(query, "ID");
		return feeId;
	}
	
	public void updateInvntoryDateForFeeSchdByFeeSchdID(String schema, String feeSchdID, String startDate, String endDate){
		db.resetSchema(schema);
		logger.debug("Update fee schedule Inventory Date :"+feeSchdID);
		
		String query = "UPDATE p_fee_schd" + " SET START_DATE=to_date('" + startDate
				+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
				+ ",END_DATE=to_date('" + endDate
				+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')" + " WHERE ID='"
				+ feeSchdID + "'";
		
		logger.info("Execute query SQL:" + query);
		db.executeUpdate(query);
	}

	/**
	 * update fee schedule start time and end time
	 * 
	 * @param feeData
	 *            :product id
	 * @param schema
	 * @param feeType
	 * @return
	 */
	public List<String> updateFeeSchdTime(FeeValidationData feeData,
			String schema, String feeType) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema
				+ " ; Update fee schedule time");
		List<String> result = null;
		List<String> feeId = new ArrayList<String>();
		String[] time = { "ID", "START_DATE", "END_DATE" };
		String query = "SELECT p_fee_schd.ID,START_DATE,END_DATE FROM(p_fee_schd INNER JOIN p_fee_cond ON p_fee_schd.FEE_COND_ID=p_fee_cond.ID)"
				+ " INNER JOIN p_fee ON p_fee_cond.FEE_ID=p_fee.FEE_ID"
				+ " WHERE p_fee.FEE_TYPE_ID='"
				+ feeType
				+ "' AND ACTIVE_IND='1'";
		if (!"".equals(feeData.productID)) {
			query = query + " AND PRD_ID='" + feeData.productID + "'";
		} else {
			// TODO need to update this code
			query = query + " AND PRD_ID is null";
		}
		if (!"".equals(feeData.locationID)) {
			query = query + " AND LOC_ID='" + feeData.locationID + "'";
		}
		if (!"".equals(feeData.prdGrpID)) {
			query = query + " AND GRP_ID='" + feeData.prdGrpID + "'";
		}
		if (!"".equals(feeData.permitTypeID)) {
			query = query + " AND PERMIT_TYPE_ID='" + feeData.permitTypeID
					+ "'";
		}
		query = query + "ORDER BY START_DATE DESC";

		logger.info("Execute query SQL:" + query);
		List<String[]> update = db.executeQuery(query, time);
		if (update.size() == 0) {
			throw new ErrorOnDataException(
					"database query fee_schd_id is failed");
		}

		if (update.size() - feeData.updateStartTime.size() != 0) {
			throw new ErrorOnDataException(
					"fee schedule quantity is not correct.please check fee schedule...");
		}
		for (int i = 0; i < update.size(); i++) {
			String[] updateTime = update.get(i);
			String start = feeData.updateStartTime.get(i);
			String end = feeData.updateEndTime.get(i);
			feeId.add(0, updateTime[0]);
			query = "UPDATE p_fee_schd" + " SET START_DATE=to_date('" + start
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ ",END_DATE=to_date('" + end
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')" + " WHERE ID='"
					+ updateTime[0] + "'";

			db.executeUpdate(query);
			if (feeData.isHoliday) {
				query = "SELECT ID FROM p_fee_schd_holiday WHERE FEE_SCHD_ID='"
						+ updateTime[0] + "'";
				result = db.executeQuery(query, "ID");
				for (int j = 0; j < result.size(); j++) {
					String id = result.get(j);
					start = feeData.updateHolidayStartTime.get(i).get(j);
					end = feeData.updateHolidayEndTime.get(i).get(j);
					query = "UPDATE p_fee_schd_holiday"
							+ " SET START_DATE=to_date('" + start
							+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
							+ ",END_DATE=to_date('" + end
							+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
							+ " WHERE ID='" + id + "'";
					db.executeUpdate(query);
				}
			}
		}

		logger.info("Execute query SQL:" + query);
		return feeId;
	}

	/**
	 * Get active none-deleted license year value from DB for all privilege
	 * product by DESC.
	 * 
	 * @param schema
	 * @return
	 */
	public List<String> queryLicenseYearInfoForPrivilegeProduct(String schema) {
		return this.queryLicenseYearInfoForPrivilegeProduct(schema, null, null);
	}

	/**
	 * select active, none deleted license year info by DESC for the given
	 * privilege product code, name info
	 * 
	 * @param schema
	 * @param privilegeProductCode
	 * @param privilegeProductName
	 * @return
	 */
	public List<String> queryLicenseYearInfoForPrivilegeProduct(String schema,
			String privilegeProductCode, String privilegeProductName) {
		db.resetSchema(schema);
		logger.debug("Reset schema as "
				+ schema
				+ " ; getting distinct licese year info setup for the privilege product.");
		String query = "";
		if (null != privilegeProductCode && null != privilegeProductName) {
			query = "SELECT distinct PYEAR.year LIC_YEAR FROM P_PRD_YEAR PYEAR, P_PRD PRD WHERE "
					+ "PRD.PRD_CD ='"
					+ privilegeProductCode
					+ "' AND "
					+ "PRD.PRD_NAME = '"
					+ privilegeProductName
					+ "' AND "
					+ "PRD.ACTIVE_IND = '1' AND "
					+ "PRD.DELETED_IND = '0' AND "
					+ "PRD.PRD_ID = PYEAR.PRD_ID AND "
					+ "PYEAR.ACTIVE_IND = '1' AND "
					+ "PRD.DELETED_IND = '0' order by PYEAR.year desc";
		}

		if (null == privilegeProductCode && null == privilegeProductName) {
			query = "select distinct year LIC_YEAR from p_prd_year where deleted_ind ='0' and active_ind = '1' order by year desc";
		}

		List<String> year = db.executeQuery(query, "LIC_YEAR");
		return year;
	}

	/**
	 * Get existing license year from DB
	 * 
	 * @param schema
	 * @return
	 */
	public List<String> getExistingLicenseYear(String schema) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema
				+ " ; getting distinct existing and active licese year info.");

		String query = "select distinct year from p_prd_year where active_ind = 1 order by year DESC";
		List<String> licenseYears = db.executeQuery(query, "year");
		return licenseYears;
	}

	/**
	 * Get entrance id from DB
	 * 
	 * @param schema
	 * @return
	 */
	public String getEntranceID(String schema, String entranceCode,
			String parkId) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema + " ; getting entracne id.");

		String query = "select PRD_ID from P_PRD where  prd_cd = '"
				+ entranceCode + "' and PRODUCT_CAT_ID=5 and PARK_ID=" + parkId;
		List<String> prdID = db.executeQuery(query, "PRD_ID");
		if (prdID.size() != 1) {
			throw new ErrorOnDataException("Only one entrance should be found.");
		}
		return prdID.get(0);
	}

	/**
	 * Get entrance id from DB
	 * 
	 * @param schema
	 * @return
	 */
	public String getEntranceNameByCode(String schema, String entranceCode,
			String parkId) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema + " ; getting entracne id.");

		String query = "select PRD_ID from P_PRD where  prd_cd = '"
				+ entranceCode + "' and PRODUCT_CAT_ID = 5 and PARK_ID="
				+ parkId;
		List<String> prdID = db.executeQuery(query, "PRD_ID");
		if (prdID.size() != 1) {
			throw new ErrorOnDataException("Only one entrance should be found.");
		}
		return prdID.get(0);
	}

	/**
	 * Update entrance active_ind
	 * 
	 * @param schema
	 * @param entranceCode
	 * @param parkId
	 * @param isActive
	 *            : true:active, false:inactive
	 */
	public void updateEntranceStatus(String schema, String entranceCode,
			String parkId, boolean isActive) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema
				+ " ; update entrance active_ind =" + (isActive ? "1" : "0"));

		String query = "update P_PRD set active_ind = "
				+ (isActive ? "1" : "0") + " where prd_cd = '" + entranceCode
				+ "' and PRODUCT_CAT_ID = 5 and PARK_ID=" + parkId;
		int affectedNum = db.executeUpdate(query);
		if (affectedNum < 1) {
			throw new ErrorOnPageException("Fail to update entrance status.");
		} else {
			logger.info("Successfully update entrance status:" + affectedNum);
		}
	}

	/**
	 * Query entrance active_ind from DB
	 * 
	 * @param schema
	 * @param entranceCode
	 * @param parkId
	 * @return
	 */
	public String queryEntranceActiveIndFromDB(String schema,
			String entranceCode, String parkId) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema
				+ " ; query entrance active_ind for entrance:" + entranceCode
				+ " under parkID:" + parkId);

		String query = "select active_ind from P_PRD where prd_cd = '"
				+ entranceCode + "' and PRODUCT_CAT_ID = 5 and PARK_ID="
				+ parkId;
		List<String> result = db.executeQuery(query, "ACTIVE_IND");
		if (result.size() != 1) {
			throw new ErrorOnDataException("Only one entrance should be found.");
		}
		return result.get(0);
	}

	/**
	 * @param schema
	 * @param vendorNum
	 * @return
	 */
	public String[] queryVendorBankAccountID(String schema, String vendorNum) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema
				+ " ; get vendor bank account IDs.");
		String query = "";
		if (null != vendorNum) {
			query = "SELECT  ACCT.id bankAccountID FROM  F_EFT_BANK_ACCOUNT ACCT WHERE ACCT.VENDOR_ID = ("
					+ "select id from d_vendor where vendor_num = '"
					+ vendorNum + "')";
		}

		List<String> accounts = db.executeQuery(query, "bankAccountID");

		String[] ids = new String[accounts.size()];
		for (int i = 0; i < accounts.size(); i++) {
			ids[i] = accounts.get(i);
		}
		return ids;
	}

	/**
	 * 
	 * @param feeData
	 * @param schema
	 * @param feeType
	 * @param isBogo
	 *            - for discount which behavior is 'Buy X Get Y Discount'
	 * @return
	 */
	public List<String[]> queryFeeDiscountSchdInfo(FeeValidationData feeData,
			String schema, String feeType, boolean isBogo) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema
				+ " ; Query discount schedule info.");

		String pattern = new RegularExpression(
				"[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}", false).toString();
		if ("".equals(feeData.arriveDate)) {
			feeData.arriveDate = DateFunctions.getDateAfterToday(1);
		}
		if ("".equals(feeData.departureDate)) {
			feeData.departureDate = DateFunctions.getDateAfterToday(1);
		}
		if (!feeData.arriveDate.matches(pattern)) {
			feeData.arriveDate = DateFunctions
					.parseMonthValue(feeData.arriveDate.substring(4, 7))
					+ "/"
					+ feeData.arriveDate.substring(8, 10)
					+ "/"
					+ feeData.arriveDate.substring(11, 15);
		}
		if (!feeData.departureDate.matches(pattern)) {
			feeData.departureDate = DateFunctions
					.parseMonthValue(feeData.departureDate.substring(4, 7))
					+ "/"
					+ feeData.departureDate.substring(8, 10)
					+ "/"
					+ feeData.departureDate.substring(11, 15);
		}

		String[] amount = { "DISCNT_SCHD_ID", "DISCNT_AMT", "FRI_DISCNT_AMT",
				"MON_DISCNT_AMT", "SAT_DISCNT_AMT", "SUN_DISCNT_AMT",
				"THU_DISCNT_AMT", "TUE_DISCNT_AMT", "WED_DISCNT_AMT",
				"UNIT_TYPE_ID", "NAME", "DISCNT_RATE_TYPE_ID", "ADD_DSCNT_IND",
				"DISCNT_TYPE_ID",
				// below fields were added for marina discount
				"MARINA_RATE_TYPE" };
		String[] amount1 = { "DISCNT_SCHD_ID", "DISCNT_AMT", "FRI_DISCNT_AMT",
				"MON_DISCNT_AMT", "SAT_DISCNT_AMT", "SUN_DISCNT_AMT",
				"THU_DISCNT_AMT", "TUE_DISCNT_AMT", "WED_DISCNT_AMT",
				"UNIT_TYPE_ID", "NAME",
				"DISCNT_RATE_TYPE_ID",
				"ADD_DSCNT_IND",
				"DISCNT_TYPE_ID",
				// below fields were added for marina discount
				"MARINA_RATE_TYPE",
				// below fields were added for discount which behavior is 'Buy X
				// Get Y Discount'
				"MON_IND", "TUE_IND", "WED_IND", "THU_IND", "FRI_IND",
				"SAT_IND", "SUN_IND", "PAID_STAY_UNITS", "DISCNT_STAY_UNITS",
				"MAX_DISCNT_STAY_UNITS", "CALC_METHOD" };

		String query = "";
		if ("13".equals(feeType) || "4".equals(feeType)) {
			if (!"".equals(feeData.locationID) && !"".equals(feeData.prdGrpID)) {
				query = "SELECT * FROM p_discnt_schd INNER JOIN D_REF_DISCNT_TYPE ON p_discnt_schd.DISCNT_TYPE_ID=D_REF_DISCNT_TYPE.ID"
						+ " WHERE p_discnt_schd.EFFECTIVE_DATE<=to_date('"
						+ feeData.arriveDate
						+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
						+ "AND p_discnt_schd.FEE_TYPE_ID='"
						+ feeType
						+ "' AND p_discnt_schd.ACTIVE_IND='1'AND p_discnt_schd.PRD_GRP_ID='"
						+ feeData.prdGrpID
						+ "' AND p_discnt_schd.LOC_ID='"
						+ feeData.locationID
						+ "' AND p_discnt_schd.PRD_ID is null ORDER BY D_REF_DISCNT_TYPE.ADD_DSCNT_IND,p_discnt_schd.START_DATE ASC";
			} else if (!"".equals(feeData.locationID)
					&& !"".equals(feeData.productID)) {
				query = "SELECT * FROM p_discnt_schd INNER JOIN D_REF_DISCNT_TYPE ON p_discnt_schd.DISCNT_TYPE_ID=D_REF_DISCNT_TYPE.ID"
						+ " WHERE p_discnt_schd.EFFECTIVE_DATE<=to_date('"
						+ feeData.arriveDate
						+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
						+ "AND p_discnt_schd.FEE_TYPE_ID='"
						+ feeType
						+ "' AND p_discnt_schd.ACTIVE_IND='1'AND p_discnt_schd.PRD_ID='"
						+ feeData.productID
						+ "' AND p_discnt_schd.LOC_ID='"
						+ feeData.locationID
						+ "' ORDER BY D_REF_DISCNT_TYPE.ADD_DSCNT_IND,p_discnt_schd.START_DATE ASC";
			} else {
				query = "SELECT * FROM p_discnt_schd INNER JOIN D_REF_DISCNT_TYPE ON p_discnt_schd.DISCNT_TYPE_ID=D_REF_DISCNT_TYPE.ID"
						+ " WHERE p_discnt_schd.EFFECTIVE_DATE<=to_date('"
						+ feeData.arriveDate
						+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
						+ "AND p_discnt_schd.FEE_TYPE_ID='"
						+ feeType
						+ "' AND p_discnt_schd.ACTIVE_IND='1'AND p_discnt_schd.PRD_ID='"
						+ feeData.productID
						+ "' ORDER BY D_REF_DISCNT_TYPE.ADD_DSCNT_IND,p_discnt_schd.START_DATE ASC";
			}
		} else {
			query = "SELECT * FROM p_discnt_schd INNER JOIN D_REF_DISCNT_TYPE ON p_discnt_schd.DISCNT_TYPE_ID=D_REF_DISCNT_TYPE.ID";
			if (isBogo)
				query = query
						+ " INNER JOIN P_DISCNT_SCHD_BOGO ON p_discnt_schd.discnt_schd_id=P_DISCNT_SCHD_BOGO.discnt_schd_id";
			query = query
					+ " WHERE ( p_discnt_schd.START_DATE<=to_date('"
					+ feeData.arriveDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ "AND p_discnt_schd.END_DATE >=to_date('"
					+ feeData.departureDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ "OR p_discnt_schd.START_DATE>=to_date('"
					+ feeData.arriveDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ "AND p_discnt_schd.START_DATE<=to_date('"
					+ feeData.departureDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ "OR END_DATE >=to_date('"
					+ feeData.arriveDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ "AND p_discnt_schd.END_DATE <=to_date('"
					+ feeData.departureDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss'))"
					+ "AND p_discnt_schd.FEE_TYPE_ID='"
					+ feeType
					+ "' AND p_discnt_schd.ACTIVE_IND='1'AND p_discnt_schd.PRD_ID='"
					+ feeData.productID
					+ "' ORDER BY D_REF_DISCNT_TYPE.ADD_DSCNT_IND,p_discnt_schd.START_DATE ASC";
		}

		List<String[]> result = null;
		if (isBogo)
			result = db.executeQuery(query, amount1);
		else
			result = db.executeQuery(query, amount);
		for (int i = 0; i < result.size(); i++) {
			query = "SELECT * FROM D_REF_DISCNT_TYPE " + " WHERE ID='"
					+ result.get(i)[13] + "'";
			result.get(i)[9] = db.executeQuery(query, "UNIT_TYPE_ID").get(0);
		}

		return result;
	}

	public void updateDiscountSchdTime(FeeValidationData feeData,
			String schema, String feeType) {
		updateDiscountSchdTime(feeData, schema, feeType, null);
	}

	/**
	 * update fee schedule start time and end time
	 * 
	 * @param feeData
	 * @param schema
	 * @param feeType
	 * @param discountName
	 * @return
	 */
	public void updateDiscountSchdTime(FeeValidationData feeData,
			String schema, String feeType, String discountName) {
		List<String> update = this.queryDiscountScheduleID(schema,
				discountName, feeType, feeData.productID);
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema
				+ " ; Update Discount schedule time");
		for (int i = 0; i < update.size(); i++) {
			String id = update.get(i);
			String start = feeData.updateDisStartTime.get(i);
			String end = feeData.updateDisEndTime.get(i);
			String query = "UPDATE P_DISCNT_SCHD" + " SET START_DATE=to_date('"
					+ start + " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ ",END_DATE=to_date('" + end
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ " WHERE DISCNT_SCHD_ID='" + id + "'";
			db.executeUpdate(query);
		}
	}

	/**
	 * 
	 * @param schema
	 * @param dates
	 * @param ids
	 */
	public void updateDiscountOrderCreateStartDate(String schema,
			Map<String, String> dates, List<String> ids) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema
				+ " ; Update Discount schedule order create start date...");
		for (int i = 0; i < ids.size(); i++) {
			String id = ids.get(i);
			String date = dates.get(id);
			String query = "UPDATE P_DISCNT_SCHD"
					+ " SET ORDER_CREATE_START_DATE=";
			if (null == date) {
				query = query + date;
			} else {
				query = query + "to_date('" + date
						+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')";
			}
			query = query + " WHERE DISCNT_SCHD_ID='" + id + "'";

			db.executeUpdate(query);
		}
	}

	public void updateDiscountOrderCreateEndDate(String schema,Map<String,String> dates,List<String> ids){
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema
				+ " ; Update Discount schedule order create End date...");
		for (int i = 0; i < ids.size(); i++) {
			String id = ids.get(i);
			String date=dates.get(id);
			String query = "UPDATE P_DISCNT_SCHD" + " SET ORDER_CREATE_END_DATE=";
			if(null==date){
				query=query+date;
			}else{
				query=query+"to_date('"+date+  " 00:00:00','mm-dd-yyyy hh24:mi:ss')";
			}
			query=query+ " WHERE DISCNT_SCHD_ID='" + id + "'";
			
			db.executeUpdate(query);
		}
	}
	
	public void updateDiscountScheduleMinUnitOfStay(String schema,Map<String,String> minUnitOfStays,List<String> ids){
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema
				+ " ; Update Discount schedule order create start date...");
		for (int i = 0; i < ids.size(); i++) {
			String id = ids.get(i);
			String minStay=minUnitOfStays.get(id);
			String query = "UPDATE P_DISCNT_SCHD" + " SET MIN_UNIT_OF_STAY= " + minStay;
			query=query+ " WHERE DISCNT_SCHD_ID='" + id + "'";
			
			db.executeUpdate(query);
		}
	}
	
	/**
	 * 
	 * @param schema
	 * @param discountName
	 * @param feeType
	 * @param productID
	 * @return
	 */
	public List<String> queryDiscountScheduleID(String schema,
			String discountName, String feeType, String productID) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema
				+ " ; query Discount schedule id...");

		String query = "SELECT P_DISCNT_SCHD.DISCNT_SCHD_ID,P_DISCNT_SCHD.START_DATE,D_REF_DISCNT_TYPE.ADD_DSCNT_IND, P_DISCNT_SCHD.ORDER_CREATE_START_DATE"
				+ " FROM P_DISCNT_SCHD INNER JOIN D_REF_DISCNT_TYPE ON P_DISCNT_SCHD.DISCNT_TYPE_ID=D_REF_DISCNT_TYPE.ID"
				+ " WHERE P_DISCNT_SCHD.FEE_TYPE_ID='"
				+ feeType
				+ "' AND P_DISCNT_SCHD.ACTIVE_IND='1'AND P_DISCNT_SCHD.PRD_ID='"
				+ productID + "'";

		if (StringUtil.notEmpty(discountName))
			query += " AND D_REF_DISCNT_TYPE.name='" + discountName + "'";

		query += " ORDER BY D_REF_DISCNT_TYPE.ADD_DSCNT_IND, P_DISCNT_SCHD.START_DATE,ORDER_CREATE_START_DATE,MIN_UNIT_OF_STAY DESC";

		List<String> value = db.executeQuery(query, "DISCNT_SCHD_ID");
		if (value.size() == 0) {
			throw new ErrorOnDataException(
					"database query fee_schd_id is failed");
		}

		return value;
	}
	
	

	/**
	 * update discount schedule amount
	 * 
	 * @param feeData
	 * @param schema
	 * @return
	 */
	public void updateDiscountSchdAmount(FeeValidationData feeData,
			String schema) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema
				+ " ; Update Discount schedule amount");
		String query = "";
		DiscountSchdInfo info = null;

		if (feeData.disInfo.size() == 0) {
			throw new ErrorOnDataException(
					"there is no discount data need to update.");
		}

		for (int i = 0; i < feeData.disInfo.size(); i++) {
			info = feeData.disInfo.get(i);
			if ("1".equals(info.unitType)) {
				// per unit of stay
				query = "UPDATE P_DISCNT_SCHD" + " SET DISCNT_AMT="
						+ info.amount + ", MON_DISCNT_AMT=" + info.monamount
						+ ", TUE_DISCNT_AMT=" + info.tueamount
						+ ", WED_DISCNT_AMT=" + info.wedamount
						+ ", THU_DISCNT_AMT=" + info.thuamount
						+ ", FRI_DISCNT_AMT=" + info.friamount
						+ ", SAT_DISCNT_AMT=" + info.satamount
						+ ", SUN_DISCNT_AMT=" + info.sunamount
						+ " WHERE DISCNT_SCHD_ID='" + info.discountId + "'";
			} else {
				// per stay
				query = "UPDATE P_DISCNT_SCHD" + " SET DISCNT_AMT="
						+ info.amount + " WHERE DISCNT_SCHD_ID='"
						+ info.discountId + "'";
			}
			db.executeUpdate(query);
		}
	}

	/**
	 * query tax schedule information
	 * 
	 * @param feeData
	 *            :productId
	 * @param schema
	 * @return
	 */
	public List<String[]> queryTaxSchdInfo(FeeValidationData feeData,
			String schema) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema
				+ "; Query tax schedule information");

		String[] tax = { "TAX_TYPE_ID", "FEE_TYPE_ID", "RATE", "TRAN_TYPE_ID",
				"ID" };
		String[] taxInfos = { "RATE_TYPE_ID", "NAME" };
		String query = "SELECT * FROM P_TAX_SCHEDULE" + " WHERE PRD_ID ='"
				+ feeData.productID + "' AND ACTIVE_IND='1'";

		logger.info("Query tax schedule info sql:" + query);
		List<String[]> result = db.executeQuery(query, tax);

		List<String[]> taxresult = null;
		List<String[]> taxData = new ArrayList<String[]>();
		Iterator<String[]> listIte = result.iterator();

		while (listIte.hasNext()) {
			String[] taxInfo = listIte.next();
			query = "SELECT * FROM P_TAX_TYPE" + " WHERE ID ='" + taxInfo[0]
					+ "'";
			taxresult = db.executeQuery(query, taxInfos);
			String[] taxs = { taxInfo[0], taxInfo[1], "0" + taxInfo[2],
					taxInfo[3], taxInfo[4], taxresult.get(0)[0],
					taxresult.get(0)[1] };
			taxData.add(taxs);
		}

		return taxData;
	}

	/**
	 * query fee penalty schedule information
	 * 
	 * @param feeData
	 *            :productId, arrive date, end date
	 * @param schema
	 * @return
	 */
	public List<String[]> queryFeePenaltySchdInfo(FeeValidationData feeData,
			String schema) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema
				+ "; Query fee penalty schedule information");

		String[] penalty = { "ID", "FEE_TYPE_ID", "TRAN_TYPE_ID",
				"TRAN_OCCUR_ID", "FEE_PENALTY_UNIT_ID", "VALUE",
				"PERMIT_TYPE_ID" };
		String query = "";
		if ((null != feeData.tranType && feeData.tranType.length() > 0)
				&& (null != feeData.locationID && feeData.locationID.length() > 0)) {
			query = "SELECT * FROM P_FEE_PENALTY_SCHD INNER JOIN P_FEE_PENALTY ON P_FEE_PENALTY.ID=P_FEE_PENALTY_SCHD.FEE_PENALTY_ID"
					+ " WHERE ( START_DATE<=to_date('"
					+ feeData.arriveDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ "AND END_DATE >=to_date('"
					+ feeData.departureDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ "OR START_DATE>=to_date('"
					+ feeData.arriveDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ "AND START_DATE<=to_date('"
					+ feeData.departureDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ "OR END_DATE >=to_date('"
					+ feeData.arriveDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ "AND END_DATE <=to_date('"
					+ feeData.departureDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss'))"
					+ " AND PRD_ID is null AND TRAN_TYPE_ID ='"
					+ feeData.tranType
					+ "' AND LOC_ID ='"
					+ feeData.locationID
					+ "' AND ACTIVE_IND='1'";
		} else {
			query = "SELECT * FROM P_FEE_PENALTY_SCHD INNER JOIN P_FEE_PENALTY ON P_FEE_PENALTY.ID=P_FEE_PENALTY_SCHD.FEE_PENALTY_ID"
					+ " WHERE ( START_DATE<=to_date('"
					+ feeData.arriveDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ "AND END_DATE >=to_date('"
					+ feeData.departureDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ "OR START_DATE>=to_date('"
					+ feeData.arriveDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ "AND START_DATE<=to_date('"
					+ feeData.departureDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ "OR END_DATE >=to_date('"
					+ feeData.arriveDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ "AND END_DATE <=to_date('"
					+ feeData.departureDate
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss'))"
					+ " AND PRD_ID ='"
					+ feeData.productID + "' AND ACTIVE_IND='1'";

			if (null != feeData.tranType && feeData.tranType.length() > 0) {
				query = query + "AND TRAN_TYPE_ID ='" + feeData.tranType + "'";
			}
		}

		logger.info("Exexut Query: " + query);
		List<String[]> result = db.executeQuery(query, penalty);

		return result;
	}

	/**
	 * Query all actived admission types
	 * 
	 * @param schema
	 * @return
	 */
	public List<String[]> queryActiveOtherOccupantTypes(String schema) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema
				+ "; Query Other Occupant Types in contract");
		String[] types = { "ADMISSION_TYPE_ID", "Name", "Sort_id" };

		String query = "select distinct ADMISSION_TYPE_ID, NAME,Sort_id "
				+ "from p_admission_prd_cat, p_admission_type  "
				+ "where p_admission_prd_cat.active_ind = " + "1 " + "and "
				+ "p_admission_type.id =p_admission_prd_cat.admission_type_id "
				+ "order by Sort_id, NAME";
		List<String[]> result = db.executeQuery(query, types);

		return result;
	}

	/**
	 * Update the occupant type
	 * 
	 * @param schema
	 * @param typeName
	 * @param flag
	 *            --1 Active 0 Inactive
	 */
	public void updateOccTypeStatus(String schema, String typeName, String flag) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema
				+ "; Update Occupant Types status to Active or Inactive");

		String update = "update p_admission_prd_cat " + "set active_ind = "
				+ flag + " where admission_type_id = (" + "select id "
				+ "from p_admission_type " + "where name = '" + typeName + "')";

		db.executeUpdate(update);
	}

	/**
	 * update fee penalty schedule time information
	 * 
	 * @param feeData
	 *            :productId, arrive date, end date
	 * @param schema
	 * @return
	 */
	public List<String> updateFeePenaltySchdTime(FeeValidationData feeData,
			String schema) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema
				+ " ; Update fee penalty schedule time");

		List<String> feeId = new ArrayList<String>();
		String query = "SELECT ID,START_DATE FROM P_FEE_PENALTY_SCHD INNER JOIN P_FEE_PENALTY ON P_FEE_PENALTY.ID=P_FEE_PENALTY_SCHD.FEE_PENALTY_ID"
				+ " WHERE ACTIVE_IND='1'AND PRD_ID='"
				+ feeData.productID
				+ "' ORDER BY START_DATE DESC";

		List<String> update = db.executeQuery(query, "ID");
		if (update.size() == 0) {
			throw new ErrorOnDataException(
					"database query fee_schd_id is failed");
		}
		for (int i = 0; i < update.size(); i++) {
			String id = update.get(i);
			String start = feeData.updateStartTime.get(i);
			String end = feeData.updateEndTime.get(i);
			feeId.add(0, id);
			query = "UPDATE p_fee_schd" + " SET START_DATE=to_date('" + start
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
					+ ",END_DATE=to_date('" + end
					+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')" + " WHERE ID='"
					+ id + "'";
			db.executeUpdate(query);
		}
		return feeId;
	}

	public String[] getOrderVehicleInstInfo(String schema, String orderID) {
		db.resetSchema(schema);
		String[] colNames = { "VEHICLE_RTI_INST_ID", "REG_NUMBER",
				"VEHICLE_ID", "VEH_INSP_ID", "CORRECTION_COUNT",
				"DUPLICATES_COUNT", "STATUS_ID", "PRD_NAME" };

		logger.info("Query Order vehicle RTI Instance info From DB.");

		String query = "select * from o_order INNER JOIN O_ORD_ITEM ON O_ORD_ITEM.ORD_ID=o_order.id "
				+ "INNER JOIN O_VEHICLE_RTI_INST ON O_VEHICLE_RTI_INST.id=O_ORD_ITEM.VEHICLE_RTI_INST_ID "
				+ "INNER JOIN P_PRD ON P_PRD.PRD_ID=O_VEHICLE_RTI_INST.PRD_ID "
				+ "WHERE ord_num='" + orderID + "'";

		logger.debug("Execute query: " + query);
		String[] info = db.executeQuery(query, colNames).get(0);

		return info;

	}

	public String getOrderItemID(String schema, String orderNum) {
		logger.info("Query Order item ID From DB.");
		db.resetSchema(schema);
		String query = "select o_ord_item.ID from o_ord_item INNER JOIN o_order ON o_order.ID=o_ord_item.ord_id where o_order.ORD_NUM ='"
				+ orderNum + "'";
		logger.debug("Execute query: " + query);
		String orderItemID = db.executeQuery(query, "ID", 0).trim();
		return orderItemID;
	}

	public String[] getOrderTransInfoByOrderItemAndTransType(String schema,
			String orderID, String transTypeID) {
		db.resetSchema(schema);
		String[] colNames = { "ord_num", "PRD_ID", "TRANS_DATE", "name",
				"USER_ID", "SALES_CHANNEL", "PIN_USR_ID", "STATION_ID",
				"TRANS_OCCUR_ID", "TRANS_TYP_ID", "UPC", "TRAN_REASON_CD_ID",
				"TRAN_REASON_DSCR", "LOC_ID", "ID" };
		logger.info("Query Order transaction info From DB.");
		String query = "select * from O_ORD_ITEM_TRANS INNER JOIN O_ORD_ITEM ON O_ORD_ITEM_TRANS.ORD_ITEM_ID=O_ORD_ITEM.ID "
				+ "INNER JOIN o_order ON o_order.id=O_ORD_ITEM.ORD_ID INNER JOIN d_loc ON d_loc.id=O_ORD_ITEM_TRANS.LOC_ID "
				+ "where  o_order.ord_num='"
				+ orderID
				+ "' and trans_typ_id='"
				+ transTypeID + "'";
		logger.debug("Execute query: " + query);
		String[] info = db.executeQuery(query, colNames).get(0);

		return info;
	}

	/**
	 * This method get the login in user id from db
	 * 
	 * @param schema
	 * @param loginUserName
	 * @return
	 */
	public String getUserId(String schema, String loginUserName) {
		db.resetSchema(schema);
		String query = "select * from x_userid_ref where login='"
				+ loginUserName + "'";
		logger.debug("Execute query: " + query);
		String userId = db.executeQuery(query, "ID").get(0);

		return userId;
	}

	public String getOrdTransAttr(String schema, String transAttrId,
			String ordTransItemId) {
		db.resetSchema(schema);
		String query = "select * from o_ord_trans_attr where ord_trans_id ='"
				+ ordTransItemId + "' and trans_attr_id ='" + transAttrId + "'";
		logger.debug("Execute query: " + query);
		String attrValue = db.executeQuery(query, "TRANS_ATTR_VALUE").get(0);

		return attrValue;
	}

	public String[] getOrderTransInfoByOrderAndTransType(String schema,
			String orderID, String transTypeID) {
		db.resetSchema(schema);
		String[] colNames = { "ord_num", "PRD_ID", "TRANS_DATE", "name",
				"USER_ID", "SALES_CHANNEL", "PIN_USR_ID", "STATION_ID",
				"TRANS_OCCUR_ID", "TRANS_TYP_ID", "TRAN_REASON_CD_ID",
				"TRAN_REASON_DSCR", "TRANS_ATTR_VALUE" };
		logger.info("Query Order transaction info From DB.");

		String query = "select * from O_ORD_ITEM_TRANS "
				+ "INNER JOIN o_order ON o_order.id=O_ORD_ITEM_TRANS.ORD_ID INNER JOIN d_loc ON d_loc.id=O_ORD_ITEM_TRANS.LOC_ID "
				+ "INNER JOIN O_ORD_TRANS_ATTR ON O_ORD_TRANS_ATTR.ORD_TRANS_ID = O_ORD_ITEM_TRANS.ID "
				+ "where O_ORD_TRANS_ATTR.TRANS_ATTR_ID=85 and o_order.ord_num='"
				+ orderID + "' and trans_typ_id='" + transTypeID + "'";

		logger.debug("Execute query: " + query);
		String[] info = db.executeQuery(query, colNames).get(0);

		return info;
	}

	public String[] getDocumentByOrderItem(String schema, String orderID) {
		db.resetSchema(schema);
		String[] colNames = { "DOC_TEMPLATE_NAME", "PRD_NAME" };
		logger.info("Query Order transaction info From DB.");

		String query = "select P_DOCUMENT_TEMPLATE.DOC_TEMPLATE_NAME ,P_PRD.PRD_NAME from O_DOCUMENT_PRIV_INST ,O_DOCUMENT_INST,P_DOCUMENT_TEMPLATE,O_PRIV_INST,P_PRD"
				+ " where P_PRD.PRD_ID=O_PRIV_INST.PRD_ID AND O_PRIV_INST.ID=O_DOCUMENT_PRIV_INST.PRIV_INST_ID "
				+ "AND O_DOCUMENT_INST.DOCUMENT_TEMPLATE_ID=P_DOCUMENT_TEMPLATE.ID AND O_DOCUMENT_INST.id=O_DOCUMENT_PRIV_INST.doc_inst_id AND ord_item_id='"
				+ orderID + "'";

		logger.debug("Execute query: " + query);
		String[] info = db.executeQuery(query, colNames).get(0);

		return info;
	}

	public String getPrintCountForPrivDoc(String schema, String orderID) {
		db.resetSchema(schema);
		String[] colNames = { "PRINT_COUNT" };
		logger.info("Query Pint Count For Privilege Document From DB.");

		String query = "select PRINT_COUNT from O_DOCUMENT_INST,O_ORDER where O_DOCUMENT_INST.ORD_ID=O_ORDER.ID AND O_ORDER.ORD_NUM='"
				+ orderID + "'";

		logger.debug("Execute query: " + query);
		String count = db.executeQuery(query, colNames).get(0)[0];

		return count;
	}

	public String getPaymentTypeID(String schema, String type) {
		db.resetSchema(schema);
		String sql = "select ID from F_PMT_TYPE where DSCR = '" + type + "'";
		logger.info("Execute query: " + sql);
		List<String> results = db.executeQuery(sql, "ID");
		if (results.size() > 0) {
			return results.get(0);
		}

		return "";
	}

	/**
	 * The method used to retrieve payment info from database query by customer
	 * email and current date
	 * 
	 * @param schema
	 * @param custEmail
	 * @return a payment data collection
	 */
	public Payment getPaymentInfo(String schema, String custEmail) {
		db.resetSchema(schema);
		Payment pay = new Payment();
		String[] colNames = { "id", "pmt_amnt", "change", "status",
				"sales_chnl", "login", "location", "pmt_type_id" };
		// String date =
		// DateFunctions.formatDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)),
		String date = DateFunctions.formatDate(DateFunctions.getToday(),
				"dd-MMM-yy").toUpperCase();
		logger.info("Query Payment Info From DB.");
		String query = "select fp.id,fp.pmt_amnt,fc.PMT_AMNT as change, fp.status, fp.sales_chnl, du.login,d_loc.name as location,fptl.pmt_type_id from f_pmt fp,f_change fc,d_user_auth du,d_loc,f_pmt_type_loc fptl, c_cust cust where cust.cust_id=fp.cust_id "
				+ "and cust.login_name = '"
				+ custEmail
				+ "' and fp.pmt_date like '"
				+ date
				+ "' and fp.change_id=fc.id(+) and fp.user_id=du.id "
				+ "and fp.loc_id=d_loc.id and fp.pmt_type_loc_id=fptl.id order by fp.id desc";

		logger.debug("Execute query: " + query);
		List<String[]> list = db.executeQuery(query, colNames);

		if (list != null && list.size() > 0) {
			if (list.size() >= 2) { // Get the payment amount in the second
				pay.amount_1 = list.get(1)[1];
			}
			pay.paymentID = list.get(0)[0];
			pay.amount = list.get(0)[1];
			pay.changeAmount = list.get(0)[2] == null ? "0" : list.get(0)[2];
			pay.status = ConstantsInterpreter.getPaymentStatus(list.get(0)[3]);
			pay.salesChanl = ConstantsInterpreter
					.getSaleChannel(list.get(0)[4]);
			pay.collectUser = list.get(0)[5];
			pay.collectLocation = list.get(0)[6];
			pay.payType = ConstantsInterpreter.getPaymentType(list.get(0)[7]);
		} else {
			throw new ErrorOnDataException("No Payment Info Found.");
		}
		return pay;
	}

	public Payment getPaymentInfoByOrderID(String schema, String orderID) {
		db.resetSchema(schema);
		Payment pay = new Payment();
		String[] colNames = { "id", "pmt_amnt", "change", "status",
				"sales_chnl", "login", "location", "pmt_type_id" };
		logger.info("Query Payment Info From DB.");
		String query = "select distinct fp.id,fp.pmt_amnt,fc.PMT_AMNT as change, fp.status, fp.sales_chnl, du.login,d_loc.name as location,fptl.pmt_type_id"
				+ " from f_pmt fp,f_change fc,d_user_auth du,d_loc,f_pmt_type_loc fptl, o_order,f_pmt_allocation"
				+ " where o_order.id = f_pmt_allocation.ord_id and f_pmt_allocation.pmt_id = fp.id"
				+ " and fp.change_id=fc.id(+) and fp.user_id=du.id and fp.loc_id=d_loc.id and fp.pmt_type_loc_id=fptl.id and ord_num='"
				+ orderID + "' order by fp.id desc";
		logger.debug("Execute query: " + query);
		List<String[]> list = db.executeQuery(query, colNames);

		if (list != null && list.size() > 0) {
			if (list.size() >= 2) { // Get the payment amount in the second
				pay.amount_1 = list.get(1)[1];
			}
			pay.paymentID = list.get(0)[0];
			pay.amount = list.get(0)[1];
			pay.changeAmount = list.get(0)[2] == null ? "0" : list.get(0)[2];
			pay.status = ConstantsInterpreter.getPaymentStatus(list.get(0)[3]);
			pay.salesChanl = ConstantsInterpreter
					.getSaleChannel(list.get(0)[4]);
			pay.collectUser = list.get(0)[5];
			pay.collectLocation = list.get(0)[6];
			pay.payType = ConstantsInterpreter.getPaymentType(list.get(0)[7]);
		} else {
			throw new ErrorOnDataException("No Payment Info Found.");
		}
		return pay;
	}

	public List<String> getPaymentIDByOrderNumAndTypeAndPayType(String schema,
			String ordNum, String type, String payType) {
		logger.info("Get payment id by order number = " + ordNum
				+ ", and type = " + type);

		db.resetSchema(schema);
		String query = "select distinct p.id from f_pmt_type t inner join f_pmt_type_loc l on t.id = l.pmt_type_id "
				+ "inner join f_pmt p on p.pmt_type_loc_id = l.id "
				+ "inner join f_pmt_allocation pa on pa.pmt_id = p.id or pa.rfnd_id = p.id "
				+ "inner join o_order o on o.id = pa.ord_id "
				+ "where o.ord_num = '" + ordNum + "'";
		if (StringUtil.notEmpty(type)) {
			if (type.equalsIgnoreCase("Payment")) {
				type = "1";
			} else if (type.equalsIgnoreCase("Refund")) {
				type = "2";
			} else {
				throw new ErrorOnDataException(
						"The payment type not correct, please check.");
			}

			query = query + " and p.type = " + type;
		}
		if (StringUtil.notEmpty(payType)) {
			query = query + " and t.dscr = '" + payType + "' ";
		}

		logger.info("Execute query:" + query);
		List<String> ids = db.executeQuery(query, "ID");
		if (ids.size() < 1) {
			throw new ErrorOnDataException("No Payment Info Found.");
		}

		return ids;
	}

	public String getVoucherIDByPaymentID(String schema, String paymentID) {
		logger.info("Get voucher id by payment id = " + paymentID);
		db.resetSchema(schema);
		String query = "select id from f_voucher where pmt_id = " + paymentID;
		logger.info("Execute query:" + query);
		List<String> ids = db.executeQuery(query, "ID");
		String id = "";
		if (ids.size() < 1) {
			throw new ErrorOnDataException("No voucher info found.");
		} else {
			id = ids.get(0);
		}

		return id;
	}

	/**
	 * Get product,payment,custom info from DB
	 * 
	 * @param schema
	 * @param resId
	 * @param custEmail
	 * @return
	 */
	public ReservationInfo getResDetailsInfoFromDB(String schema, String resId,
			String custEmail) {
		ReservationInfo res = new ReservationInfo();

		logger.info("Get product,payment,customer info associated with specific reservation from DB");
		res = this.getResInfoFromDB(schema, resId);
		res.customer = this.getCustInfoFromDB(schema, resId);
		res.pay = this.getPaymentInfo(schema, custEmail);
		return res;
	}

	/**
	 * Get Customer info for specific reservation order
	 * 
	 * @param schema
	 * @param resId
	 *            : reservation number
	 * @return: Customer
	 */
	public Customer getCustInfoFromDB(String schema, String resId) {
		Customer cust = new Customer();

		db.resetSchema(schema);
		logger.info("Get customer info for eservation#: " + resId + " in DB.");
		logger.debug("Reset schema as " + schema);

		String[] colNames = { "f_name", "l_name", "login_name" };
		String query = "select c.f_name,c.l_name,c.login_name from o_order o, c_cust c "
				+ "where o.primary_occupant_id = c.cust_id and o.ord_num = "
				+ "'" + resId + "'";

		List<String[]> result = db.executeQuery(query, colNames);

		logger.debug("Execute query: " + query);
		if (result != null && result.size() > 0) {
			cust.fName = result.get(0)[0];
			cust.lName = result.get(0)[1];
			cust.email = result.get(0)[2];
		} else {
			throw new ItemNotFoundException("No Customer Info Found.");
		}

		return cust;
	}

	/**
	 * Get Reservation info for specific reservation order
	 * 
	 * @param schema
	 * @param resId
	 *            : reservation number
	 * @return: ReservationInfo
	 */
	public ReservationInfo getResInfoFromDB(String schema, String resId) {
		ReservationInfo res = new ReservationInfo();

		db.resetSchema(schema);
		logger.info("Get reservation info for reservation#: " + resId
				+ " in DB");
		logger.debug("Reset schema as " + schema);

		String[] colNames = { "loc_id", "prd_cd", "prd_name", "start_date",
				"end_date", "proc_status_id", "ord_status_id", "ord_price",
				"paid", "ord_balance", "conf_status_id", "collection_status",
				"ord_invc_id" };

		String query = "select o.loc_id, p.prd_cd, p.prd_name,"
				+ "oi.start_date, oi.end_date, o.proc_status_id, o.ord_status_id,"
				+ "o.ord_price, o.paid, o.ord_balance, o.conf_status_id, o.collection_status, o.ord_invc_id "
				+ "from o_order o, o_ord_item oi, p_prd p "
				+ "where o.id = oi.ord_id and p.prd_id = o.site_id and o.ord_num = "
				+ "'" + resId + "'";

		List<String[]> result = db.executeQuery(query, colNames);

		logger.debug("Execute query: " + query);
		if (result != null && result.size() > 0) {
			// Reservation
			res.parkId = result.get(0)[0];
			res.site.siteNumber = result.get(0)[1];
			res.site.siteName = result.get(0)[2];
			res.arriveDate = result.get(0)[3];
			res.departDate = result.get(0)[4];
			res.reservStatus = result.get(0)[5];
			res.orderStatus = result.get(0)[6];

			res.orderPrice = result.get(0)[7];
			res.orderPaid = result.get(0)[8];
			res.orderBalance = result.get(0)[9];
			res.orderConfStatus = result.get(0)[10];
			res.ordercollectLoc = result.get(0)[11];
			res.invoiceNum = result.get(0)[12];
		} else {
			logger.error("Query returns nothing");
			throw new ItemNotFoundException("No Reservation Info Found.");
		}

		return res;
	}

	public ReservationInfo getOrderInfo(String schema, String ordNum) {
		logger.info("Get Order info for res#: " + ordNum + " in DB");
		db.resetSchema(schema);

		String[] colNames = { "loc_id", "ord_balance", "ord_date",
				"ord_invc_id", "ord_price", "ord_type_id", "ord_status_id",
				"conf_status_id", "proc_status_id", "paid", "usr_id",
				"prd_rate_type_id", "ord_cat_id", "start_date", "end_date",
				"collection_status" };

		String query = "select * from o_order where ord_num = '" + ordNum + "'";
		logger.info("Execute query: " + query);
		List<String[]> results = db.executeQuery(query, colNames);

		ReservationInfo res = new ReservationInfo();
		if (results.size() > 0) {
			res.parkId = results.get(0)[0];
			res.orderBalance = results.get(0)[1];
			res.orderDate = results.get(0)[2].split(" ")[0];
			res.invoiceNum = results.get(0)[3];
			res.orderPrice = results.get(0)[4];
			res.orderType = results.get(0)[5];
			res.orderStatus = results.get(0)[6];
			res.orderConfStatus = results.get(0)[7];
			res.reservStatus = results.get(0)[8];
			res.paidAmount = results.get(0)[9];
			// TODO user_id
			// TODO prd_rate_type_id
			// TODO ord_cat_id
			res.arriveDate = results.get(0)[13].split(" ")[0];
			res.departDate = results.get(0)[14].split(" ")[0];
			// TODO collection_status
		}

		return res;
	}

	public String[] getOrderItemInfo(String schema, String ordNum) {
		logger.info("Get Order Item info for res#: " + ordNum + " in DB");
		db.resetSchema(schema);

		String[] colNames = { "discnt", "start_date", "end_date", "fee",
				"prd_id", "tax", "booking_id", "occ_site_id", "create_date",
				"status_id", "usr_id", "type_id", "rev_loc_id",
				"conf_status_id", "paid_amount", "min_pmnt", "quantity",
				"sales_flow_type", "rafting", "contract_id", "day_use" };

		String query = "select ooi.* from o_order oo, o_ord_item ooi where oo.id = ooi.ord_id and oo.ord_num = '"
				+ ordNum + "'";
		logger.info("Execute query: " + query);
		List<String[]> results = db.executeQuery(query, colNames);
		if (results.size() > 0) {
			return results.get(0);
		}

		return null;
	}

	public SlipContractInfo getSlipContractInfo(String schema, String ordNum) {
		logger.info("Get Slip Contract info for res#:" + ordNum);
		db.resetSchema(schema);

		String colNames[] = { "ID", "CUST_ID", "CONTRACT_TYPE_ID",
				"CONTRACT_NAME_ID", "LOC_ID", "STATUS_ID", "START_DATE",
				"END_DATE", "RENEWAL_LENGTH", "PRD_ID", "LOGIN_NAME" };
		String sql = "select osc.*, cc.LOGIN_NAME from O_SLIP_CONTRACT osc, O_ORDER oo, O_ORD_ITEM ooi, C_CUST cc where oo.ID = ooi.ORD_ID and ooi.CONTRACT_ID = osc.ID and oo.ORD_NUM = '"
				+ ordNum + "' " + "and osc.CUST_ID = cc.CUST_ID";
		logger.info("Execute query: " + sql);
		List<String[]> results = db.executeQuery(sql, colNames);

		SlipContractInfo contract = new SlipContractInfo();
		if (results.size() > 0) {
			contract.setId(results.get(0)[0]);
			contract.setCustomerID(results.get(0)[1]);
			contract.setType(results.get(0)[2]);
			contract.setName(results.get(0)[3]);
			contract.setLocation(results.get(0)[4]);
			contract.setStatus(results.get(0)[5]);
			contract.setStartDate(results.get(0)[6]);
			contract.setEndDate(results.get(0)[7]);
			contract.setProduct(results.get(0)[9]);
			contract.setCustomerEmail(results.get(0)[10]);
		}

		return contract;
	}

	public int getSlipReservationType(String schema, String ordNum) {
		db.resetSchema(schema);
		String sql = "select SLIP_RES_TYPE_ID from O_ORDER_MARINA where ORDER_ID = (select ID from O_ORDER where ORD_NUM = '"
				+ ordNum + "')";
		logger.info("Execute query: " + sql);
		List<String> results = db.executeQuery(sql, "SLIP_RES_TYPE_ID");
		int typeID = -1;
		if (results.size() > 0) {
			typeID = Integer.parseInt(results.get(0));
		}

		return typeID;
	}

	/**
	 * The method used to verify the payment is allocated to a order
	 * 
	 * @param schema
	 * @param ordNum
	 * @param paymentID
	 * @param balance
	 *            expect balance value
	 */
	public void verifyAllocatePayToOrd(String schema, String ordNum,
			String paymentID, double balance, double amount) {
		logger.info("Verify Allocate Payment To Order.");

		db.resetSchema(schema);
		String[] colNames = { "ord_balance", "paid", "CONF_STATUS_ID",
				"item_conf_status" };
		String query = "select distinct od.ord_balance, od.paid, od.CONF_STATUS_ID,oi.conf_status_id as item_conf_status "
				+ "from o_order od, o_ord_item oi, f_pmt_allocation pa "
				+ "where  od.ord_num='"
				+ ordNum
				+ "' "
				+ "and od.id=oi.ord_id "
				+ "and oi.id=pa.ord_item_id "
				+ "and pa.pmt_id=" + paymentID + " " + "and pa.active_ind=1";
		List<String[]> strs = db.executeQuery(query, colNames);
		logger.debug(query);
		if (strs != null && strs.size() > 0) {
			if (strs.get(0)[0] == null) {
				strs.get(0)[0] = "0";
			}
			if (Math.abs(Double.parseDouble(strs.get(0)[0]) - balance) > 0.00001) {
				throw new ErrorOnDataException("Order Balance "
						+ strs.get(0)[0] + " Not Correct.");
			}
			if (Math.abs(amount - Double.parseDouble(strs.get(0)[1])) > 0.00001) {
				throw new ErrorOnDataException("Order Paid Amount "
						+ strs.get(0)[1] + " Not Correct,Expected Amount is "
						+ amount);
			}
			if (balance - 0 > 0.00001) {
				if (!strs.get(0)[2].equals("1") || !strs.get(0)[3].equals("1")) {// '1'
					// stand
					// for
					// unconfirmed
					throw new ErrorOnDataException(
							"Confirm status In Order Table " + strs.get(0)[3]
									+ " Not Correct.");
				}
			} else {
				if (!strs.get(0)[2].equals("3")) {// '3' stand for full confirm
					throw new ErrorOnDataException(
							"Confirm status In Order Table " + strs.get(0)[3]
									+ " Not Correct.");
				}
				if (!strs.get(0)[3].equals("2")) {// '2' stand for confirmed
					throw new ErrorOnDataException(
							"Confirm status In Order_Item Table "
									+ strs.get(0)[4] + " Not Correct.");
				}
			}
		} else {
			throw new ItemNotFoundException(
					"No Payment Was Allocated to Order " + ordNum);
		}
	}

	/**
	 * The method used get the credit/gift card authorization response from DB
	 * 
	 * @param schema
	 * @param custEmail
	 */
	public List<String[]> getCreditCardAuthResponse(String schema,
			String creatDate, String custEmail) {
		db.resetSchema(schema);
		String[] colNames = { "status_id", "amount", "currency", "auth_date",
				"auth_code", "auth_return_code", "auth_return_code_desc",
				"request_id", "processor_name", "merchant_id", "payment_id" };
		String query = "select status_id,amount,currency,auth_date,auth_code,auth_return_code,auth_return_code_desc,request_id,processor_name,merchant_id,payment_id from "
				+ "f_epayment_request where cust_email = "
				+ "'"
				+ custEmail
				+ "' and create_date like '" + creatDate + "' order by id desc";
		logger.debug(query);
		List<String[]> strs = db.executeQuery(query, colNames);
		return strs;
	}

	public String getPaymentProcessorByPayID(String schema, String payID) {
		db.resetSchema(schema);
		String query = "select processor_name from f_epayment_request where payment_id = "
				+ payID;
		logger.info(query);
		return db.executeQuery(query, "processor_name", 0);
	}

	/**
	 * The method used to retrieve payment collection agency info from database
	 * query
	 * 
	 * @param schema
	 *            - db schema
	 * @param paymentID
	 *            - payment id
	 * @return payment collection agency
	 */
	public String getPaymentCollectionAgency(String schema, String paymentID) {
		String colNames = "name";

		db.resetSchema(schema);

		logger.info("Query Payment Collection Agency Info From DB.");
		String query = "select d_loc.name " + "from d_loc,f_pmt "
				+ "where d_loc.id=f_pmt.col_agency " + "and f_pmt.id="
				+ paymentID;

		List<String> list = db.executeQuery(query, colNames);
		String collectionAgency = "";

		if (list != null && list.size() > 0) {
			collectionAgency = list.get(0);
		} else {
			throw new ItemNotFoundException(
					"No Payment Collection Agency Info Found.");
		}
		return collectionAgency;
	}

	/**
	 * This method used to retrieve arrive date of order from database query
	 * 
	 * @param schema
	 * @param ordNum
	 * @return arrive date of order
	 */
	public String getArriDateOfOrd(String schema, String ordNum) {
		String colNames = "start_date";
		String arriDate = "";

		db.resetSchema(schema);

		logger.info("Query Arrive Date Of Reservation From DB");
		String query = "select oi.start_date "
				+ "from o_order od,o_ord_item oi " + "where od.id=oi.ord_id "
				+ "and od.ord_num=" + "'" + ordNum + "'";
		List<String> list = db.executeQuery(query, colNames);

		if (list != null && list.size() > 0) {
			arriDate = list.get(0);
		} else {
			throw new ItemNotFoundException(
					"No Payment Collection Agency Info Found.");
		}

		return arriDate;
	}

	/**
	 * This method used to retrieve journal header info of payment from database
	 * query
	 * 
	 * @param schema
	 * @param paymentID
	 * @return journal header info
	 */
	public List<String[]> getJrnlHdrInfo(String schema, String paymentID) {
		String[] colNames = { "id", "jrnl_date", "jrnl_type_cd", "dscr",
				"user_id", "login" };
		List<String[]> jrnlHdrInfo;
		db.resetSchema(schema);

		logger.info("Query Journal Header Info From DB");
		String query = "select distinct f_jrnl_hdr.id,f_jrnl_hdr.jrnl_date,jrnl_type_cd,f_jrnl_type.dscr,user_id,x_userid_ref.login "
				+ "from f_pmt_allocation,f_jrnl_hdr,f_jrnl_type,x_userid_ref "
				+ "where pmt_id="
				+ paymentID
				+ " "
				+ "and f_pmt_allocation.jrnl_hdr_id=f_jrnl_hdr.id "
				+ "and f_jrnl_hdr.jrnl_type_cd=f_jrnl_type.cd "
				+ "and f_jrnl_hdr.user_id=x_userid_ref.id ";
		List<String[]> strs = db.executeQuery(query, colNames);

		if (strs != null && strs.size() > 0) {
			jrnlHdrInfo = strs;
		} else {
			throw new ItemNotFoundException(
					"No Payment Journal Header Info Found.");
		}
		return jrnlHdrInfo;
	}

	public List<String> getJrnlHdrIDs(String schema, String paymentID) {
		String colNames = "jrnl_hdr_id";
		List<String> JrnlHdrIDs;
		db.resetSchema(schema);

		logger.info("Query Journal Header ID From DB");
		String query = "select distinct jrnl_hdr_id "
				+ "from f_pmt_allocation " + "where pmt_id = " + paymentID
				+ " " + "order by jrnl_hdr_id";

		List<String> strs = db.executeQuery(query, colNames);

		if (strs != null && strs.size() > 0) {
			JrnlHdrIDs = strs;
		} else {
			throw new ItemNotFoundException("No Journal Header ID Info Found.");
		}
		return JrnlHdrIDs;
	}

	/**
	 * Verify permit group size
	 * 
	 * @param schema
	 * @param ordNum
	 * @return
	 */
	public void verifyPermitGroupSize(String schema, String ordNum, String size) {
		String colName = "GROUP_SIZE";
		String groupSizes = "";
		db.resetSchema(schema);

		logger.info("Query Group Size from DB");
		String query = "select group_size from o_ord_item " + "where ord_id = "
				+ "(select id " + "from o_order " + "where ord_num = " + "'"
				+ ordNum + "'" + ")";

		String strs = db.executeQuery(query, colName).get(0);
		if (strs != null && strs.length() > 0) {
			groupSizes = strs;
		} else {
			throw new ItemNotFoundException("No Group Size was found.");
		}

		if (!groupSizes.equalsIgnoreCase(size)) {
			throw new TestCaseFailedException(
					"The group size cannot be equal to the expected value: "
							+ size);
		} else {
			logger.info("The group size is correct.");
		}
	}

	/**
	 * This method used to retrieve allocation info of payment from database
	 * query
	 * 
	 * @param schema
	 * @param paymentID
	 * @return payment allocation info
	 */
	public List<String[]> getPmtAllocationInfo(String schema, String jrnlHrdID) {
		String[] colNames = { "id", "jrnl_hdr_id", "ord_id", "schd_id",
				"accnt_id", "cd", "name", "amount", "cr_db", "dscr", "newid" };
		List<String[]> pmyAllocationInfo;
		db.resetSchema(schema);

		logger.info("Query Payment Allocation Info From DB");
		String query = "select distinct f_pmt_allocation.id,jrnl_hdr_id,ord_id,schd_id,"
				+ "accnt_id,f_acct.cd,f_acct.name,amount,"
				+ "f_acct_type.cr_db,f_acct_type.dscr,f_acct_type.newid "
				+ "from f_acct_type, f_acct, f_pmt_allocation "
				+ "where jrnl_hdr_id="
				+ jrnlHrdID
				+ " "
				+ "and f_pmt_allocation.accnt_id = f_acct.id "
				+ "and f_acct.acct_type_id = f_acct_type.newid "
				+ "order by jrnl_hdr_id,f_pmt_allocation.id";
		List<String[]> strs = db.executeQuery(query, colNames);

		if (strs != null && strs.size() > 0) {
			pmyAllocationInfo = strs;
		} else {
			throw new ItemNotFoundException("No Payment Allocation Info Found.");
		}
		return pmyAllocationInfo;
	}

	/**
	 * This method used to retrieve journal line info of payment from database
	 * query
	 * 
	 * @param schema
	 * @param jrnlHrdID
	 * @return journal line info
	 */
	public List<String[]> getJrnlLineInfo(String schema, String jrnlHrdID) {
		String[] colNames = { "id", "jrnl_hdr_id", "ord_id", "acct_id", "cd",
				"name", "amnt_db", "amnt_cr", "cr_db", "dscr", "newid" };
		List<String[]> jrnlLineInfo;
		db.resetSchema(schema);

		logger.info("Query Journal Line Info From DB");
		String query = "select distinct f_jrnl_line.id,f_jrnl_line.jrnl_hdr_id,f_jrnl_line.ord_id,"
				+ "f_jrnl_line.acct_id,f_acct.cd,f_acct.name,"
				+ "f_jrnl_line.amnt_db,f_jrnl_line.amnt_cr,"
				+ "f_acct_type.cr_db,f_acct_type.dscr,f_acct_type.newid "
				+ "from f_acct_type, f_acct, f_jrnl_line "
				+ "where f_jrnl_line.jrnl_hdr_id ="
				+ jrnlHrdID
				+ " "
				+ "and f_jrnl_line.acct_id = f_acct.id "
				+ "and f_acct.acct_type_id = f_acct_type.newid "
				+ "order by f_jrnl_line.jrnl_hdr_id,f_jrnl_line.id";
		List<String[]> strs = db.executeQuery(query, colNames);

		if (strs != null && strs.size() > 0) {
			jrnlLineInfo = strs;
		} else {
			throw new ItemNotFoundException(
					"No Payment Journal Header Info Found.");
		}
		return jrnlLineInfo;
	}

	/**
	 * This method used to retrieve order numbers of payment from database query
	 * 
	 * @param schema
	 * @param paymentID
	 * @return order numbers
	 */
	public List<String> getOrderNums(String schema, String paymentID) {
		String colNames = "ord_num";
		List<String> orderNums;
		db.resetSchema(schema);

		logger.info("Query Order Number Info From DB");
		String query = "select ord_num " + "from o_order,f_pmt_allocation "
				+ "where f_pmt_allocation.ord_id = o_order.id "
				+ "and pmt_id = " + paymentID + " " + "order by o_order.id";

		List<String> strs = db.executeQuery(query, colNames);

		if (strs != null && strs.size() > 0) {
			orderNums = strs;
		} else {
			throw new ItemNotFoundException("No Order Numbers Info Found.");
		}
		return orderNums;
	}

	/**
	 * This method used to retrieve order numbers(queried by receipt num)
	 * 
	 * @param schema
	 * @param receiptNum
	 * @return order number
	 */
	public List<String> getOrderNumsViaReceiptNum(String schema,
			String receiptNum) {
		String colNames = "ord_num";
		List<String> orderNums;
		db.resetSchema(schema);

		logger.info("Query Order Number Info From DB via receipt number:");
		String query = "select ord_num " + "from o_order "
				+ "where id = (select order_id " + "from o_rcpt_ord "
				+ "where rcpt_id = " + "(select id " + "from o_rcpt "
				+ "where rcpt_no = '" + receiptNum + "' ))";

		List<String> strs = db.executeQuery(query, colNames);

		if (strs != null && strs.size() > 0) {
			orderNums = strs;
		} else {
			throw new ItemNotFoundException("No Order Numbers Info Found.");
		}
		return orderNums;

	}

	/**
	 * query refund status via receipt number 0: None 1: Pending 2: Received 3:
	 * Approved 4: Issued 5: Voided
	 * 
	 * @param schema
	 * @param receiptNum
	 * @param status
	 */
	public void verifyRefundStatusIssuedByReceiptNum(String schema,
			String receiptNum, String status) {
		String colNames = "status";
		String refundStatusNum;
		db.resetSchema(schema);

		logger.info("Verify refund status Info From DB via receipt number:");
		String query = "select status " + "from F_REFUND_ISSUENCE "
				+ "where refund_id = " + "(select rfnd_id "
				+ "from O_RCPT_RFND " + "where RCPT_ID = " + "(select id "
				+ "from o_rcpt " + "where rcpt_no = " + "'" + receiptNum + "'"
				+ "))";

		List<String> strs = db.executeQuery(query, colNames);

		if (strs != null && strs.size() > 0) {
			refundStatusNum = strs.get(0);
		} else {
			throw new ItemNotFoundException("No Order Numbers Info Found.");
		}

		if (!refundStatusNum.trim().equalsIgnoreCase(status)) {
			throw new ErrorOnDataException(
					"The status number should be correct: " + status);
		}
	}

	/**
	 * Get the rule cond id from table i_rule_cond by unique rule comment.
	 * 
	 * @param schema
	 * @param ruleComment
	 *            - should be a unique string to find a unique rule condition id
	 * @return
	 */
	public String getRuleCondID(String schema, String ruleComment) {
		String id = getRuleCondID(schema, 1, ruleComment);

		return id;
	}

	public String getRuleCondID(String schema, int active_ind,
			String ruleComment) {
		logger.info("Search the Rule.Cond.ID from DB by rule coment - "
				+ ruleComment + ".");

		db.resetSchema(schema);

		String query = "select id from i_rule_cond where delete_ind=0 and active_ind="
				+ active_ind
				+ " and dscr='"
				+ ruleComment
				+ "' order by id desc";
		logger.debug("Executing query: " + query);
		List<String> IDs = db.executeQuery(query, "ID");
		db.disconnect();
		if (IDs.size() == 0) {
			// throw new ActionFailedException(
			// "Can't find any records from DB by the rule comment - "
			// + ruleComment);
			return "";
		}

		String id = IDs.get(0).trim();

		logger.info("Get the Rule.Cond.ID=" + id);

		return id;
	}

	public String getPrivilegeRuleID(String schema, String privilegeCode) {
		db.resetSchema(schema);

		String query = "select id from I_PRODUCT_RULE where product_id in (select prd_id from p_prd where prd_cd = '"
				+ privilegeCode + "') and active=1";
		logger.debug("Executing query: " + query);
		List<String> IDs = db.executeQuery(query, "ID");

		if (IDs.size() == 0) {
			throw new ActionFailedException(
					"Can't find any records from DB by the privilege code - "
							+ privilegeCode);
		} else if (IDs.size() > 1) {
			throw new ActionFailedException(
					"Find more than one record from DB by the privilege code - "
							+ privilegeCode);
		}

		String id = IDs.get(0).trim();
		db.disconnect();

		logger.info("Get the privilege rule ID=" + id);

		return id;
	}

	public boolean isRuleExists(String schema, String ruleComment) {
		return isRuleExists(schema, 1, ruleComment);
	}

	public boolean isRuleExists(String schema, int active_ind,
			String ruleComment) {
		logger.info("Check if the rule exists identified by rule coment - "
				+ ruleComment + ".");

		db.resetSchema(schema);

		String query = "select id from i_rule_cond where active_ind="
				+ active_ind + " and dscr='" + ruleComment + "'";
		logger.debug("Executing query: " + query);
		List<String> IDs = db.executeQuery(query, "ID");

		if (IDs.size() == 0) {
			return false;
		}

		return true;
	}

	/**
	 * The method execute the process that open the url
	 * 
	 * @param url
	 */
	protected void invokeURL(String url, int product, boolean getBuildNum,
			boolean newBrowser) {
		logger.info("Invoke " + url);
		if (newBrowser) {
			browser.closeAllBrowsers();
		}
		switch (product) {
		case ORMS:
			if (newBrowser) {
				browser.open(url);
			}

			OrmsHomePage ormsHmPg = OrmsHomePage.getInstance();
			ormsHmPg.waitLoading();
			if (getBuildNum) {
				getOrmsBuildNumber();
			}
			break;
		case RA:
		case BW:
		case RECGOV:
		case PL:
		case WEB_APP:
		case HF:
			if (getBuildNum) {
				UWP.getInstance().getWebBuildNumber(url + "/admin.do",
						newBrowser);
				browser.load(url);
			} else {
				if (url.matches(".*(5101(/(|.*\\.do))?)$")) {
					// rec.gov. disable foresee survey
					String mainUrl = url.split("5101")[0] + "5101";
					disableForeseeSurvey(mainUrl, newBrowser);
					newBrowser = false;
				} else if (url.contains("uat-recgov")
						|| url.contains("www.recreation.gov")) {// UAT
																// Lesley[20130924]
																// change
																// url.equal to
																// url.contains,
																// due to change
																// live rec url
																// to
																// http://www.recreation.gov
					disableForeseeSurvey(url, newBrowser);
					newBrowser = false;
				}

				if (newBrowser) {
					browser.open(url);
				} else {
					browser.load(url);
				}
			}
		}

		urlTrack.add(url);
		TestProperty.putProperty("current.url", url);
	}

	public void disableForeseeSurvey(String url, boolean newBrowser) {
		ForeseeSurveyAdminPage foreseePg = ForeseeSurveyAdminPage.getInstance();

		url = url + "/foresee/fsrtest.html";// check web server found url
		// changed from fsradmin.html to
		// fsrtest.html
		if (newBrowser) {
			browser.open(url);
		} else {
			browser.load(url);
		}
		foreseePg.waitLoading();
		foreseePg.clickClearButton();
		foreseePg.setSamplingPercentage(0);
		foreseePg.clickSetButton();
	}

	public String getOrmsBuildNumber() {
		OrmsHomePage ormsHmPg = OrmsHomePage.getInstance();
		String bn = ormsHmPg.getBuildNum();
		TestProperty.putProperty("orms.build", bn);
		logger.info("Orms Build# is " + bn);

		return bn;
	}

	public boolean isInventoryHold(String siteID, int siteType,
			String dateFrom, String dateTo, String schema) {
		sycDB();

		db.resetSchema(schema);
		dateFrom = DateFunctions.formatDate(dateFrom, "MM-dd-yyyy");
		dateTo = DateFunctions.formatDate(dateTo, "MM-dd-yyyy");
		String query = "";
		if (siteType == 1 || siteType == 23) {// Normal and NSS child site
			query = "select count(*) as count from I_INV_USED " + "where "
					+ "usage_type_id="
					+ HOLD
					+ " "
					+ "and prd_id = "
					+ siteID
					+ " "
					+ "and ("
					+ "(from_time>=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and from_time<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (to_time >=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and to_time<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (from_time <=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and to_time>=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') )" + ")";
		} else if (siteType == 21) {// NSQ site
			query = "select count(*) as count from i_inv_qty_used " + "where "
					+ "usage_type="
					+ HOLD
					+ " "
					+ "and site_id ="
					+ siteID
					+ " "
					+ "and ("
					+ "(start_date>=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and start_date<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (end_date>=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ " and end_date<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (start_date <=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and end_date>=TO_DATE('"
					+ dateTo + "','mm-dd-yyyy') )" + ")";
		} else if (siteType == 22) {// NSS site
			query = "select count(*) as count from I_INV_USED " + "where "
					+ "usage_type_id="
					+ HOLD
					+ " "
					+ "and PARENT_PRD_ID = "
					+ siteID
					+ " "
					+ "and ("
					+ "(from_time>=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ " and from_time<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (to_time>=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ " and to_time<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (from_time <=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and to_time>=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') )" + ")";
		} else {
			throw new ErrorOnDataException("Unknown site type id.");
		}
		logger.info(query);
		int num = Integer.parseInt(db.executeQuery(query, "count", 0));

		if (num < 0) {
			logger.error("Failed to run query:" + query);
			throw new ItemNotFoundException("Failed to run query.");
		} else if (num == 0)
			logger.info("Site was not on hold.");
		else
			logger.info("Site was on hold.");

		return num > 0;
	}

	/**
	 * Release the held inventories for specified site from given date.
	 * 
	 * @param siteID
	 *            - site id
	 * @param dateFrom
	 *            - from date
	 * @param dateTo
	 *            - to date
	 * @param schema
	 * @param siteType
	 *            - site type value in DB (prd_rel_type), 1 standard site, 2
	 *            nss/nsq site
	 * @return - true/false - whether the site has been held
	 */
	public boolean releaseHoldInvFromDB(String siteID, int siteType,
			String dateFrom, String dateTo, String schema) {

		sycDB();

		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema);
		dateFrom = DateFunctions.formatDate(dateFrom, "MM-dd-yyyy");
		dateTo = DateFunctions.formatDate(dateTo, "MM-dd-yyyy");
		String queryRelease = "";
		if (siteType == 1 || siteType == 23) {// Normal and NSS child site
			queryRelease = "delete from I_INV_USED " + "where "
					+ "usage_type_id="
					+ HOLD
					+ " "
					+ "and prd_id = "
					+ siteID
					+ " "
					+ "and ("
					+ "(from_time>=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and from_time<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (to_time >=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and to_time<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (from_time <=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and to_time>=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') )" + ")";
		} else if (siteType == 21) {// NSQ site
			queryRelease = "delete from i_inv_qty_used " + "where "
					+ "usage_type="
					+ HOLD
					+ " "
					+ "and site_id ="
					+ siteID
					+ " "
					+ "and ("
					+ "(start_date>=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and start_date<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (end_date>=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ " and end_date<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (start_date <=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and end_date>=TO_DATE('"
					+ dateTo + "','mm-dd-yyyy') )" + ")";
		} else if (siteType == 22) {// NSS site
			queryRelease = "delete from I_INV_USED " + "where "
					+ "usage_type_id="
					+ HOLD
					+ " "
					+ "and PARENT_PRD_ID = "
					+ siteID
					+ " "
					+ "and ("
					+ "(from_time>=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ " and from_time<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (to_time>=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ " and to_time<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (from_time <=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and to_time>=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') )" + ")";
		} else {
			throw new ErrorOnDataException("Unknown site type id.");
		}
		logger.info(queryRelease);
		int num = db.executeUpdate(queryRelease);

		if (num < 0) {
			logger.error("Failed to run query:" + queryRelease);
			throw new ItemNotFoundException("Failed to run query.");
		} else if (num == 0)
			logger.info("Site was not on hold.");
		else
			logger.info("Site was on hold and was released.");

		return num > 0;
	}

	/**
	 * Release the held inventories for specified site from given date.
	 * 
	 * @param slipID
	 *            - slip id
	 * @param dateFrom
	 *            - from date
	 * @param dateTo
	 *            - to date
	 * @param schema
	 * @param slipType
	 *            - slip type value in DB (prd_rel_type), 1 standard site, 2
	 *            nss/nsq slip
	 * @return - true/false - whether the slip has been held
	 */
	public boolean releaseHoldSlipInvFromDB(String slipID, int slipType,
			String dateFrom, String dateTo, String schema) {

		sycDB();

		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema);
		dateFrom = DateFunctions.formatDate(dateFrom, "MM-dd-yyyy");
		dateTo = DateFunctions.formatDate(dateTo, "MM-dd-yyyy");
		String queryRelease = "";
		if (slipType == 1 || slipType == 23) {// Normal and
			// NSS child
			// site
			queryRelease = "select ID as ID, to_char(START_DATE, 'mm-dd-yyyy') as START_DATE, to_char(END_DATE, 'mm-dd-yyyy') as END_DATE from I_INV_QTY_USED where "
					+ "usage_type="
					+ HOLD
					+ " "
					+ "and site_id = "
					+ slipID
					+ " "
					+ "and ("
					+ "(start_date>=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and start_date<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (end_date >=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and end_date<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (start_date <=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and end_date>=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') )" + ")";
			// TODO TBV
			// } else if (slipType == 21) {// NSQ site
			// queryRelease = "delete from i_inv_qty_used " + "where "
			// + "usage_type="
			// + HOLD
			// + " "
			// + "and site_id ="
			// + slipID
			// + " "
			// + "and ("
			// + "(start_date>=TO_DATE('"
			// + dateFrom
			// + "','mm-dd-yyyy') "
			// + "and start_date<=TO_DATE('"
			// + dateTo
			// + "','mm-dd-yyyy') ) "
			// + "or (end_date>=TO_DATE('"
			// + dateFrom
			// + "','mm-dd-yyyy') "
			// + " and end_date<=TO_DATE('"
			// + dateTo
			// + "','mm-dd-yyyy') ) "
			// + "or (start_date <=TO_DATE('"
			// + dateFrom
			// + "','mm-dd-yyyy') "
			// + "and end_date>=TO_DATE('"
			// + dateTo + "','mm-dd-yyyy') )" + ")";
		} else if (slipType == 22) {// NSS site
			queryRelease = "select ID as ID, to_char(START_DATE, 'mm-dd-yyyy') as START_DATE, to_char(END_DATE, 'mm-dd-yyyy') as END_DATE from I_INV_QTY_USED where "
					+ "usage_type="
					+ HOLD
					+ " "
					+ "and site_id in (select PRD_ID from P_PRD where PARENT_ID = "
					+ slipID
					+ ") "
					+ "and ("
					+ "(start_date>=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and start_date<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (end_date >=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and end_date<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (start_date <=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and end_date>=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') )" + ")";
		} else {
			throw new ErrorOnDataException("Unknown slip type id - " + slipType);
		}

		logger.info(queryRelease);
		List<String[]> result = db.executeQuery(queryRelease, new String[] {
				"ID", "START_DATE", "END_DATE" });

		boolean needSync = false;
		if (result.size() > 0) {
			String id, start_date, end_date;
			for (int i = 0; i < result.size(); i++) {
				id = result.get(i)[0];
				start_date = result.get(i)[1];
				end_date = result.get(i)[2];

				String queryDeleteHoldQty = "delete from I_INV_QTY_USED where id = "
						+ id;
				logger.info(queryDeleteHoldQty);
				int num = db.executeUpdate(queryDeleteHoldQty);
				if (num < 0) {
					logger.error("Failed to run query:" + queryDeleteHoldQty);
					throw new ItemNotFoundException("Failed to run query.");
				} else {
					needSync = true;
				}

				// TODO need to be enhanced for lease type
				// for (int j = 0; j < DateFunctions.diffBetween(end_date,
				// start_date); j++) {
				String queryUpdateQty = "update I_INV_QTY set BOOKED = 0, TIME_UPDATE = to_date(to_char(sysdate, 'mm-dd-yyyy'), 'mm-dd-yyyy') where BOOKED > 0 and PRD_ID = "
						+ slipID
						+ " and INV_DATE >= to_date('"
						+ DateFunctions.getDateAfterGivenDay(start_date, 0)
						+ "', 'mm-dd-yyyy') and INV_Date<=to_date('"
						+ DateFunctions.getDateAfterGivenDay(end_date, 0)
						+ "', 'mm-dd-yyyy')";
				logger.info(queryUpdateQty);
				num = db.executeUpdate(queryUpdateQty);
				if (num < 0) {
					logger.error("Failed to run query:" + queryUpdateQty);
					throw new ItemNotFoundException("Failed to run query.");
				}
				// }

				logger.info("Slip was on hold and was released.");
			}
		} else
			logger.info("Slip was not on hold.");

		return needSync;
	}

	public boolean releaseDepartureSlipInvFromDB(String slipID, int slipType,
			String dateFrom, String dateTo, String schema) {

		sycDB();

		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema);
		dateFrom = DateFunctions.formatDate(dateFrom, "MM-dd-yyyy");
		dateTo = DateFunctions.formatDate(dateTo, "MM-dd-yyyy");
		String queryRelease = "";
		if (slipType == 1 || slipType == 23) {// Normal and NSS child site
			queryRelease = "select ID as ID, to_char(START_DATE, 'mm-dd-yyyy') as START_DATE, to_char(END_DATE, 'mm-dd-yyyy') as END_DATE from I_INV_QTY_USED where "
					+ "usage_type="
					+ DEPARTED
					+ " "
					+ "and site_id = "
					+ slipID
					+ " "
					+ "and ("
					+ "(start_date>=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and start_date<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (end_date >=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and end_date<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (start_date <=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and end_date>=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') )" + ")";
			// TODO TBV
			// } else if (slipType == 21) {// NSQ site
			// queryRelease = "delete from i_inv_qty_used " + "where "
			// + "usage_type="
			// + HOLD
			// + " "
			// + "and site_id ="
			// + slipID
			// + " "
			// + "and ("
			// + "(start_date>=TO_DATE('"
			// + dateFrom
			// + "','mm-dd-yyyy') "
			// + "and start_date<=TO_DATE('"
			// + dateTo
			// + "','mm-dd-yyyy') ) "
			// + "or (end_date>=TO_DATE('"
			// + dateFrom
			// + "','mm-dd-yyyy') "
			// + " and end_date<=TO_DATE('"
			// + dateTo
			// + "','mm-dd-yyyy') ) "
			// + "or (start_date <=TO_DATE('"
			// + dateFrom
			// + "','mm-dd-yyyy') "
			// + "and end_date>=TO_DATE('"
			// + dateTo + "','mm-dd-yyyy') )" + ")";
		} else if (slipType == 22) {// NSS site
			queryRelease = "select ID as ID, to_char(START_DATE, 'mm-dd-yyyy') as START_DATE, to_char(END_DATE, 'mm-dd-yyyy') as END_DATE from I_INV_QTY_USED where "
					+ "usage_type="
					+ DEPARTED
					+ " "
					+ "and site_id in (select PRD_ID from P_PRD where PARENT_ID = "
					+ slipID
					+ ") "
					+ "and ("
					+ "(start_date>=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and start_date<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (end_date >=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and end_date<=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') ) "
					+ "or (start_date <=TO_DATE('"
					+ dateFrom
					+ "','mm-dd-yyyy') "
					+ "and end_date>=TO_DATE('"
					+ dateTo
					+ "','mm-dd-yyyy') )" + ")";
		} else {
			throw new ErrorOnDataException("Unknown slip type id - " + slipType);
		}

		logger.info(queryRelease);
		List<String[]> result = db.executeQuery(queryRelease, new String[] {
				"ID", "START_DATE", "END_DATE" });

		boolean needSync = false;
		if (result.size() > 0) {
			String id, start_date, end_date;
			for (int i = 0; i < result.size(); i++) {
				id = result.get(i)[0];
				start_date = result.get(i)[1];
				end_date = result.get(i)[2];

				String queryDeleteHoldQty = "delete from I_INV_QTY_USED where id = "
						+ id;
				logger.info(queryDeleteHoldQty);
				int num = db.executeUpdate(queryDeleteHoldQty);
				if (num < 0) {
					logger.error("Failed to run query:" + queryDeleteHoldQty);
					throw new ItemNotFoundException("Failed to run query.");
				} else {
					needSync = true;
				}

				// TODO need to be enhanced for lease type
				// for (int j = 0; j < DateFunctions.diffBetween(end_date,
				// start_date); j++) {
				String queryUpdateQty = "update I_INV_QTY set BOOKED = 0, TIME_UPDATE = to_date(to_char(sysdate, 'mm-dd-yyyy'), 'mm-dd-yyyy') where BOOKED > 0 and PRD_ID = "
						+ slipID
						+ " and INV_DATE >= to_date('"
						+ DateFunctions.getDateAfterGivenDay(start_date, 0)
						+ "', 'mm-dd-yyyy') and INV_Date<=to_date('"
						+ DateFunctions.getDateAfterGivenDay(end_date, 0)
						+ "', 'mm-dd-yyyy')";
				logger.info(queryUpdateQty);
				num = db.executeUpdate(queryUpdateQty);
				if (num < 0) {
					logger.error("Failed to run query:" + queryUpdateQty);
					throw new ItemNotFoundException("Failed to run query.");
				}
				// }

				logger.info("Slip was Departed and was released.");
			}
		} else
			logger.info("Slip was not Departed.");

		return needSync;
	}

	public void deleteAndResetUsedSlipQty(String i_inv_qty_used_id,
			String prd_id, String start_date, String end_date) {
		String queryDeleteHoldQty = "delete from I_INV_QTY_USED where id = "
				+ i_inv_qty_used_id;
		logger.info(queryDeleteHoldQty);
		int num = db.executeUpdate(queryDeleteHoldQty);
		if (num < 0) {
			logger.error("Failed to run query:" + queryDeleteHoldQty);
			throw new ItemNotFoundException("Failed to run query.");
		}

		for (int j = 0; j < DateFunctions.diffBetween(end_date, start_date); j++) {
			String queryUpdateQty = "update I_INV_QTY set BOOKED = 0, TIME_UPDATE = to_date(to_char(sysdate, 'mm-dd-yyyy'), 'mm-dd-yyyy') where BOOKED > 0 and PRD_ID = "
					+ prd_id
					+ " and INV_DATE = to_date('"
					+ DateFunctions.getDateAfterGivenDay(start_date, j)
					+ "', 'mm-dd-yyyy')";
			logger.info(queryUpdateQty);
			num = db.executeUpdate(queryUpdateQty);
			if (num < 0) {
				logger.error("Failed to run query:" + queryUpdateQty);
				throw new ItemNotFoundException("Failed to run query.");
			}
		}
	}

	/**
	 * Query the order numbers by given SQL
	 * 
	 * @param query
	 * @param schema
	 * @return
	 */
	protected String checkBookedInv(String query, String schema) {
		sycDB();
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema);

		List<String> result = db.executeQuery(query, 1, -1);
		int size = result.size();

		String ordNums = "";
		StringBuffer ordNumsBuffer = new StringBuffer();

		for (int i = 0; i < size; i += 2) {
			ordNumsBuffer.append(" " + result.get(i).trim());
			ordNums = ordNumsBuffer.toString();
		}

		ordNums = ordNumsBuffer.toString();
		ordNums = ordNums.trim();

		return ordNums;
	}

	public String getListEntryStatusByOrdNum(String ordNum, String schema) {
		db.resetSchema(schema);
		logger.info("Try to get List Entry status of list entry order-->"
				+ ordNum);

		String query = "SELECT WAITING_STATUS "
				+ "FROM O_ORD_ITEM INNER JOIN O_ORDER "
				+ "ON O_ORDER.ID=O_ORD_ITEM.ORD_ID AND O_ORDER.ORD_NUM='"
				+ ordNum + "' WHERE O_ORD_ITEM.TYPE_ID='22'";

		logger.info("SQL: " + query);

		List<HashMap<String, String>> results = db.executeQuery(query);
		if (results.size() > 0) {
			logger.info("Found records  -->" + results.size());
		} else {
			throw new ItemNotFoundException(
					"Cannot get any order item record !!!!");
		}

		return results.get(0).get("WAITING_STATUS");

	}

	public String getSlipSeasonStartDateFromDB(String facilityId,
			String seasonName, String schema) {

		List<HashMap<String, String>> results = this.getSlipSeasonDateFromDB(
				facilityId, seasonName, schema);
		return results.get(0).get("START_DATE");
	}

	public String getSlipSeasonEndDateFromDB(String facilityId,
			String seasonName, String schema) {

		List<HashMap<String, String>> results = this.getSlipSeasonDateFromDB(
				facilityId, seasonName, schema);
		return results.get(0).get("END_DATE");
	}

	public List<HashMap<String, String>> getSlipSeasonDateFromDB(
			String facilityId, String seasonName, String schema) {
		db.resetSchema(schema);
		logger.info("Try to get START_DATE and END_DATE of season-->"
				+ seasonName);

		String query = "SELECT TO_CHAR(START_DATE,'YYYY-MM-DD') as START_DATE, TO_CHAR(END_DATE,'YYYY-MM-DD') as END_DATE  "
				+ "FROM I_SEASON_SCHDL   "
				+ "WHERE DELETED_IND=0  "
				+ "AND FACILITY_ID  ="
				+ facilityId
				+ "  "
				+ "AND ACTIVE_IND   =1  "
				+ "AND PRD_CAT_ID   =20  "
				+ "AND NAME = '" + seasonName + "'";

		logger.info("SQL: " + query);

		List<HashMap<String, String>> results = db.executeQuery(query);
		if (results.size() > 0) {
			logger.info("Found records  -->" + results.size());
		} else {
			throw new ItemNotFoundException("Cannot get any season record !!!!");
		}

		return results;

	}

	/**
	 * Check whether the specified site has been booked/occupied for specified
	 * site type from the given date
	 * 
	 * @param siteID
	 *            - site id
	 * @param siteType
	 *            - site type
	 * @param dateFrom
	 *            - from date
	 * @param schema
	 * @return
	 */
	public String checkBookedInv(String siteID, int siteType, String dateFrom,
			String dateTo, String schema) {
		return checkBookedInv(siteID, siteType, dateFrom, dateTo, schema, false);
	}

	/**
	 * Check whether the specified site has been booked/occupied for specified
	 * site type from the given date
	 * 
	 * @param siteID
	 *            - site id
	 * @param siteType
	 *            - site type
	 * @param dateFrom
	 *            - from date
	 * @param schema
	 * @param isCheckIn
	 *            - check-in will be performed on the given site
	 * @return
	 */
	public String checkBookedInv(String siteID, int siteType, String dateFrom,
			String dateTo, String schema, boolean isCheckIn) {
		String queryBooked;
		final int NORMAL = 1;
		final int NSQ = 21;
		final int NSS = 22;
		final int NSSCHILD = 23;
		dateFrom = DateFunctions.formatDate(dateFrom, "MM-dd-yyyy");
		dateTo = DateFunctions.formatDate(dateTo, "MM-dd-yyyy");

		switch (siteType) {
		case NSQ:
			queryBooked = "select distinct oo.ord_num from o_order oo, o_ord_item oi, i_inv_qty_used iu "
					+ "where iu.site_id = "
					+ siteID
					+ " and iu.id=oi.booking_id and oo.id=oi.ord_id and oo.ord_status_id=1"
					+ " and ((iu.start_date >=  TO_DATE('"
					+ dateFrom
					+ "', 'mm-dd-yyyy')"
					+ " and iu.start_date <=  TO_DATE('"
					+ dateTo
					+ "', 'mm-dd-yyyy') )"
					+ " or (iu.end_date >=  TO_DATE('"
					+ dateFrom
					+ "', 'mm-dd-yyyy')"
					+ " and iu.end_date <=  TO_DATE('"
					+ dateTo
					+ "', 'mm-dd-yyyy') ) "
					+ " or (iu.start_date <=  TO_DATE('"
					+ dateFrom
					+ "', 'mm-dd-yyyy')"
					+ " and iu.end_date >=  TO_DATE('"
					+ dateTo + "', 'mm-dd-yyyy') ))";
			break;
		case NSS:
			queryBooked = "select distinct oo.ord_num from o_order oo, o_ord_item oi, I_INV_USED iu"
					+ " where IU.PARENT_PRD_ID = "
					+ siteID
					+ " and IU.order_item_id=OI.ID and OO.id=OI.ORD_ID and OO.ORD_STATUS_ID=1"
					+ " and ((iu.FROM_TIME >=  TO_DATE('"
					+ dateFrom
					+ "', 'mm-dd-yyyy')"
					+ " and iu.from_time <=  TO_DATE('"
					+ dateTo
					+ "', 'mm-dd-yyyy') )"
					+ " or (iu.TO_TIME >=  TO_DATE('"
					+ dateFrom
					+ "', 'mm-dd-yyyy') "
					+ " and iu.to_time <=  TO_DATE('"
					+ dateTo
					+ "', 'mm-dd-yyyy') )"
					+ " or (iu.FROM_TIME <=  TO_DATE('"
					+ dateFrom
					+ "', 'mm-dd-yyyy') "
					+ " and iu.to_time >=  TO_DATE('"
					+ dateTo + "', 'mm-dd-yyyy') ))";
			break;
		case NSSCHILD:
			// if (isCheckIn) {
			// queryBooked =
			// "select distinct oo.ord_num from o_order oo, o_ord_item oi, I_INV_USED iu"
			// + " where IU.PRD_ID = "+ siteID
			// +
			// " and IU.order_item_id=OI.ID and OO.id=OI.ORD_ID and OO.ORD_STATUS_ID=1"
			// + " and ((iu.FROM_TIME >= TO_DATE('"+ dateFrom+
			// "', 'mm-dd-yyyy')"
			// + " and iu.from_time <= TO_DATE('"+ dateTo+ "', 'mm-dd-yyyy') )"
			// + " or ((iu.TO_TIME >= TO_DATE('"+ dateFrom+
			// "', 'mm-dd-yyyy') or oo.proc_status_id in (1,2) )"
			// + " and iu.to_time <= TO_DATE('"+ dateTo+ "', 'mm-dd-yyyy') ))";
			// } else {
			// queryBooked =
			// "select distinct oo.ord_num from o_order oo, o_ord_item oi, I_INV_USED iu "
			// + "where IU.PRD_ID = "+ siteID
			// +
			// " and IU.order_item_id=OI.ID and OO.id=OI.ORD_ID and OO.ORD_STATUS_ID=1"
			// + " and ((iu.FROM_TIME >= TO_DATE('"+ dateFrom+
			// "', 'mm-dd-yyyy') "
			// + " and iu.from_time <= TO_DATE('"+ dateTo+ "', 'mm-dd-yyyy') )"
			// + " or ( iu.TO_TIME >= TO_DATE('"+ dateFrom+ "', 'mm-dd-yyyy') "
			// + " and iu.to_time <= TO_DATE('"+ dateTo+ "', 'mm-dd-yyyy') ))";
			// }
			// break;
		case NORMAL:
		default:
			if (isCheckIn) {
				// queryBooked =
				// "select distinct oo.ord_num from o_order oo, o_ord_item oi, i_inv_used iu "
				// + "where iu.prd_id="
				// + siteID
				// +
				// " and iu.order_item_id=oi.id and oo.id=oi.ord_id and oo.ord_status_id=1"
				// + " and iu.from_time <=TO_DATE('"
				// + dateTo
				// + "', 'mm-dd-yyyy')";
				queryBooked = "select distinct oo.ord_num from o_order oo, o_ord_item oi, i_inv_used iu "
						+ "where iu.prd_id="
						+ siteID
						+ " and iu.order_item_id=oi.id and oo.id=oi.ord_id and oo.ord_status_id=1"
						+ " and ((( iu.from_time >=  TO_DATE('"
						+ dateFrom
						+ "', 'mm-dd-yyyy')"
						+ " and iu.from_time <=  TO_DATE('"
						+ dateTo
						+ "', 'mm-dd-yyyy') )"
						+ " or ( iu.to_time >=  TO_DATE('"
						+ dateFrom
						+ "', 'mm-dd-yyyy')"
						+ " and iu.to_time <=  TO_DATE('"
						+ dateTo
						+ "', 'mm-dd-yyyy') )"
						+ " or ( iu.from_time <=  TO_DATE('"
						+ dateFrom
						+ "', 'mm-dd-yyyy')"
						+ " and iu.to_time >=  TO_DATE('"
						+ dateTo
						+ "', 'mm-dd-yyyy') ))"
						+ " or "
						+ " ( oo.proc_status_id!=3"
						+ " and  iu.from_time <=TO_DATE('"
						+ dateFrom
						+ "', 'mm-dd-yyyy')))";

			} else {
				queryBooked = "select distinct oo.ord_num from o_order oo, o_ord_item oi, i_inv_used iu "
						+ "where iu.prd_id="
						+ siteID
						+ " and iu.order_item_id=oi.id and oo.id=oi.ord_id and oo.ord_status_id=1"
						+ " and (( iu.from_time >=  TO_DATE('"
						+ dateFrom
						+ "', 'mm-dd-yyyy')"
						+ " and iu.from_time <=  TO_DATE('"
						+ dateTo
						+ "', 'mm-dd-yyyy') )"
						+ " or ( iu.to_time >=  TO_DATE('"
						+ dateFrom
						+ "', 'mm-dd-yyyy')"
						+ " and iu.to_time <=  TO_DATE('"
						+ dateTo
						+ "', 'mm-dd-yyyy') )"
						+ " or ( iu.from_time <=  TO_DATE('"
						+ dateFrom
						+ "', 'mm-dd-yyyy')"
						+ " and iu.to_time >=  TO_DATE('"
						+ dateTo + "', 'mm-dd-yyyy') ))";
			}
		}

		logger.debug("Check booked inventory using: " + queryBooked);

		String res_nums = checkBookedInv(queryBooked, schema);

		if (res_nums.length() < 1) {
			logger.info("The target site inventory was ready for test.");
			return "";
		} else {
			logger.warn("Site was booked/occupied. Please void #" + res_nums
					+ " to release the inventory.");
		}
		return res_nums;
	}

	/**
	 * Check whether the specified slip has been booked/occupied for specified
	 * slip type from the given dates
	 * 
	 * @param slipID
	 *            - slip id
	 * @param slipType
	 *            - slip type
	 * @param dateFrom
	 *            - from date
	 * @param schema
	 * @param isFloatIn
	 *            - check-in will be performed on the given slip
	 * @return
	 */
	public String checkBookedSlipInv(String slipID, int slipType,
			String dateFrom, String dateTo, String schema, boolean isFloatIn) {
		String queryBooked = "";
		final int NORMAL = 1;
		final int NSQ = 21;
		final int NSS = 22;
		final int NSSCHILD = 23;
		dateFrom = DateFunctions.formatDate(dateFrom, "MM-dd-yyyy");
		dateTo = DateFunctions.formatDate(dateTo, "MM-dd-yyyy");

		switch (slipType) {
		case NSQ:
			// queryBooked =
			// "select distinct oo.ord_num from o_order oo, o_ord_item oi, i_inv_qty_used iu "
			// + "where iu.site_id = "
			// + slipID
			// +
			// " and iu.id=oi.booking_id and oo.id=oi.ord_id and oo.ord_status_id=1"
			// + " and ((iu.start_date >=  TO_DATE('"
			// + dateFrom
			// + "', 'mm-dd-yyyy')"
			// + " and iu.start_date <=  TO_DATE('"
			// + dateTo
			// + "', 'mm-dd-yyyy') )"
			// + " or (iu.end_date >=  TO_DATE('"
			// + dateFrom
			// + "', 'mm-dd-yyyy')"
			// + " and iu.end_date <=  TO_DATE('"
			// + dateTo
			// + "', 'mm-dd-yyyy') ) "
			// + " or (iu.start_date <=  TO_DATE('"
			// + dateFrom
			// + "', 'mm-dd-yyyy')"
			// + " and iu.end_date >=  TO_DATE('"
			// + dateTo + "', 'mm-dd-yyyy') ))";
			break;
		case NSS:
			queryBooked = "select distinct oo.ord_num from o_order oo, o_ord_item oi, I_INV_USED iu"
					+ " where IU.PARENT_PRD_ID = "
					+ slipID
					+ " and IU.order_item_id=OI.ID and OO.id=OI.ORD_ID and OO.ORD_STATUS_ID=1"
					+ " and ((iu.FROM_TIME >=  TO_DATE('"
					+ dateFrom
					+ "', 'mm-dd-yyyy')"
					+ " and iu.from_time <=  TO_DATE('"
					+ dateTo
					+ "', 'mm-dd-yyyy') )"
					+ " or (iu.TO_TIME >=  TO_DATE('"
					+ dateFrom
					+ "', 'mm-dd-yyyy') "
					+ " and iu.to_time <=  TO_DATE('"
					+ dateTo
					+ "', 'mm-dd-yyyy') )"
					+ " or (iu.FROM_TIME <=  TO_DATE('"
					+ dateFrom
					+ "', 'mm-dd-yyyy') "
					+ " and iu.to_time >=  TO_DATE('"
					+ dateTo + "', 'mm-dd-yyyy') ))";
			break;
		case NSSCHILD:
		case NORMAL:
		default:
			if (isFloatIn) {
				// TODO TBV
				queryBooked = "select distinct oo.ORD_NUM from O_ORDER oo, O_ORD_ITEM ooi, I_INV_QTY_USED iiqu "
						+ "where oo.ID = ooi.ORD_ID and ooi.BOOKING_ID = iiqu.BOOKING_ID and oo.ORD_STATUS_ID = 1 "
						+ "and iiqu.SITE_ID = "
						+ slipID
						+ " and ((( iiqu.START_DATE >=  TO_DATE('"
						+ dateFrom
						+ "', 'mm-dd-yyyy') "
						+ "and iiqu.START_DATE <=  TO_DATE('"
						+ dateTo
						+ "', 'mm-dd-yyyy') ) "
						+ "or ( iiqu.END_DATE >=  TO_DATE('"
						+ dateFrom
						+ "', 'mm-dd-yyyy') "
						+ "and iiqu.END_DATE <=  TO_DATE('"
						+ dateTo
						+ "', 'mm-dd-yyyy') ) "
						+ "or ( iiqu.START_DATE <=  TO_DATE('"
						+ dateFrom
						+ "', 'mm-dd-yyyy') "
						+ "and iiqu.END_DATE >=  TO_DATE('"
						+ dateTo
						+ "', 'mm-dd-yyyy'))) "
						+ "or ( oo.PROC_STATUS_ID != 3 "
						+ "and  iiqu.START_DATE <=TO_DATE('"
						+ dateFrom
						+ "', 'mm-dd-yyyy')))";
			} else {
				queryBooked = "select distinct oo.ORD_NUM from O_ORDER oo, O_ORD_ITEM ooi, I_INV_QTY_USED iiqu "
						+ "where oo.ID = ooi.ORD_ID and ooi.BOOKING_ID = iiqu.BOOKING_ID and oo.ORD_STATUS_ID = 1 "
						+ "and iiqu.SITE_ID = "
						+ slipID
						+ "and (( iiqu.START_DATE >=  TO_DATE('"
						+ dateFrom
						+ "', 'mm-dd-yyyy') "
						+ "and iiqu.START_DATE <=  TO_DATE('"
						+ dateTo
						+ "', 'mm-dd-yyyy') ) "
						+ "or ( iiqu.END_DATE >=  TO_DATE('"
						+ dateFrom
						+ "', 'mm-dd-yyyy') "
						+ "and iiqu.END_DATE <=  TO_DATE('"
						+ dateTo
						+ "', 'mm-dd-yyyy') ) "
						+ "or ( iiqu.START_DATE <=  TO_DATE('"
						+ dateFrom
						+ "', 'mm-dd-yyyy') "
						+ "and iiqu.END_DATE >=  TO_DATE('"
						+ dateTo
						+ "', 'mm-dd-yyyy')))";
			}
		}

		logger.info("Check booked inventory using: " + queryBooked);

		String res_nums = checkBookedInv(queryBooked, schema);

		if (res_nums.length() < 1) {
			logger.info("The target slip inventory was ready for test.");
			return "";
		} else {
			logger.warn("Slip was booked/occupied. Please void #" + res_nums
					+ " to release the inventory.");
		}
		return res_nums;
	}

	public void checkAndReleaseInventory(String schema, String dateFrom,
			int days, boolean isCheckIn, String... siteIDs) {
		checkAndReleaseInventory(schema, dateFrom, days, isCheckIn, true,
				siteIDs);
	}

	public void checkAndReleaseInventory(String schema, String dateFrom,
			int days, boolean isCheckIn, boolean newSession, String... siteIDs) {
		String dateTo = DateFunctions.getDateAfterGivenDay(dateFrom, days);
		checkAndReleaseInventory(schema, dateFrom, dateTo, isCheckIn,
				newSession, siteIDs);
	}

	public void checkAndReleaseInventory(String schema, String dateFrom,
			String dateTo, boolean isCheckIn, String... siteIDs) {
		checkAndReleaseInventory(schema, dateFrom, dateTo, isCheckIn, true,
				siteIDs);
	}

	public void checkAndReleaseInventory(String schema, String dateFrom,
			String dateTo, boolean isCheckIn, boolean newSession,
			String... siteIDs) {
		boolean needSync = false;
		boolean onHold = false;
		String resNums = "";

		if (isCheckIn && (DateFunctions.compareToToday(dateFrom) > 0)) {
			throw new InvalidDataException(
					"dateFrom is after today, which is conflicts with isCheckIn=true");
		}

		if (StringUtil.isEmpty(dateTo)) {
			dateTo = DateFunctions.getDateAfterGivenDay(dateFrom, 365);
		}

		for (int i = 0; i < siteIDs.length; i++) {
			if (Boolean.valueOf(TestProperty.getProperty("record"))
					.booleanValue()) {
				recordSiteInfoToDB(schema, siteIDs[i], dateFrom, dateTo,
						TestProperty.getProperty("fullCaseName"));
			}
			// retrieve the site type from DB
			int siteType = this.getSiteRelationTypeID(siteIDs[i], schema);
			boolean dbReadOnly = Boolean.valueOf(TestProperty.getProperty(env
					+ ".db.readonly", "false"));
			if (dbReadOnly) {
				logger.info("Release inventory from UI.");
				onHold = this.isInventoryHold(siteIDs[i], siteType, dateFrom,
						dateTo, schema);
				if (onHold) {
					Object[] args = new Object[2];
					args[0] = "contract="
							+ DataBaseFunctions
									.getContractFromSchemaName(schema)
							+ ":from=" + dateFrom + ":to=" + dateTo;
					args[1] = siteIDs[i];
					new com.activenetwork.qa.awo.supportscripts.function.ReleaseHoldInventory()
							.execute(args);
				}
			} else {
				logger.info("Release inventory from DB.");
				needSync = releaseHoldInvFromDB(siteIDs[i], siteType, dateFrom,
						dateTo, schema);

				if (needSync) {
					// needSync maybe set to false if one of site is
					// not on hold when release for multiple sites
					onHold = true;
				}
			}

			resNums = resNums
					+ " "
					+ checkBookedInv(siteIDs[i], siteType, dateFrom, dateTo,
							schema, isCheckIn);
		}

		resNums = resNums.trim();
		if (resNums.length() > 0) {
			String[] res_nums = resNums.split(" ");

			cleanReservations(res_nums, schema, newSession);
		}

		if (onHold) {
			Browser.sleep(OCCAM_SYNC_TIME);
		}
	}

	public void recordSiteInfoToDB(String schema, String siteId,
			String dateFrom, String dateTo, String caseName) {
		logger.info("Record Site " + siteId + " Info To DB.");

		db.resetDefaultDB();

		caseName = caseName.replaceFirst("([a-z]+\\.)*test(C|c)ases.", "");

		String query = "select count(*) as num from site_used where site_id="
				+ siteId + " and case_name like '%" + caseName + "'";
		int num = Integer.parseInt(db.executeQuery(query, "num", 0));
		if (num < 1) {
			logger.debug("Start Recording...");
			try {
				db.resetSchema(schema);
				query = "select prd_rel_type as site_type,prd_cd as site_cd,prd_name as site_name,loc_id as loop_id,park_id from p_prd where prd_id="
						+ siteId;
				String[] colNames = { "site_type", "site_cd", "site_name",
						"loop_id", "park_id" };
				logger.debug(query);
				String[] values = db.executeQuery(query, colNames, 0);
				String contract = schema.substring(schema.lastIndexOf("_") + 1)
						.toUpperCase();
				db.resetDefaultDB();

				String sql = "Insert into site_used (site_id,site_type,site_cd,site_name,parent_id,park_id,contract,case_name) values("
						+ siteId
						+ ","
						+ values[0]
						+ ",'"
						+ values[1]
						+ "','"
						+ values[2]
						+ "',"
						+ values[3]
						+ ","
						+ values[4]
						+ ",'"
						+ contract + "','" + caseName + "')";
				logger.debug(sql);

				db.executeUpdate(sql);

				logger.info("Record Done!");
			} catch (Exception e) {
				logger.warn("Failed to record due to " + e);
			}
		} else {
			logger.info("Has been Recorded,no need to record!");
		}
	}

	/**
	 * check and release inventories for specified site from given date period.
	 * Assume check-in will not be performed
	 * 
	 * @param siteID
	 *            - site id
	 * @param period
	 *            - date from period
	 * @param schema
	 * @deprecated
	 */

	// public void checkAndReleaseInventory(String siteID, int period,
	// String schema) {
	// checkAndReleaseInventory(siteID, period, schema, false);
	// }
	/**
	 * check and release inventories for specified site from given date period
	 * 
	 * @param siteID
	 *            - site id
	 * @param period
	 *            - date from period
	 * @param schema
	 * @param isCheckIn
	 *            - check-in will be performed on the given site
	 * @deprecated
	 */
	// public void checkAndReleaseInventory(String siteID, int period,
	// String schema, boolean isCheckIn) {
	// checkAndReleaseInventory(schema, period, isCheckIn, siteID);
	// }
	/**
	 * Release the held invs, and cancel/void all booked/occupied reservations
	 * for specified sites from the fixed date period from operation manager.
	 * Assume check-in will not be performed.
	 * 
	 * @param siteID
	 *            - site id
	 * @param schema
	 */
	// public void checkAndReleaseInventory(List<String> siteID, String schema)
	// {
	// checkAndReleaseInventory(siteID, schema, false);
	// }
	/**
	 * Release the held invs, and cancel/void all booked/occupied reservations
	 * for specified sites from the fixed date period from operation manager.
	 * 
	 * @param siteID
	 *            - site id
	 * @param schema
	 * @param isCheckIn
	 *            - check-in will be performed or not
	 */
	// public void checkAndReleaseInventory(List<String> siteID, String schema,
	// boolean isCheckIn) {
	// checkAndReleaseInventory(siteID, CLEANUP_PERIOD, schema, isCheckIn);
	// }
	/**
	 * check and release inventories for specified site from fixed date
	 * period(15 days before and 15 days after). Assume check-in will not be
	 * performed
	 * 
	 * @param siteID
	 *            - site id
	 * @param schema
	 */
	// public void checkAndReleaseInventory(String siteID, String schema) {
	// checkAndReleaseInventory(siteID, schema, false);
	// }
	/**
	 * check and release inventories for specified site from fixed date period
	 * 
	 * @param siteID
	 *            - site id
	 * @param schema
	 * @param isCheckIn
	 *            - check-in will be performed or not
	 */
	// public void checkAndReleaseInventory(String siteID, String schema,
	// boolean isCheckIn) {
	// checkAndReleaseInventory(siteID, CLEANUP_PERIOD, schema, isCheckIn);
	// }
	/**
	 * Retrieve the site type from DB by given prdID;
	 * 
	 * @param prdID
	 *            - prd_id
	 * @param schema
	 * @return site type, 1 - normal site; 21 - nsq site; 22 - nss site; 23 -
	 *         nss child site
	 */
	public int getSiteRelationTypeID(String prdID, String schema) {
		sycDB();

		db.resetSchema(schema);
		logger.debug("Get the site type for '" + prdID + "' from schema: "
				+ schema);

		// activate/undelete site which is inactive/deleted
		// String
		// query="update p_prd set active_ind=1, deleted_ind=0 where prd_id = "
		// + prdID
		// + " and product_cat_id=3 and (active_ind=0 or deleted_ind=1)";
		// db.executeUpdate(query);

		String query = "select prd_rel_type from p_prd where prd_id = " + prdID
				+ " and product_cat_id=3 and active_ind=1 and deleted_ind=0";

		// If exception throws, means the given site id is not exists in DB
		String text = db.executeQuery(query, "prd_rel_type", 0);
		int siteType = Integer.parseInt(text);
		List<String> attrValue = null;
		String isNSQQuery = "select ATTR_VALUE from P_PRD_ATTR where PRD_ID="
				+ prdID + "  and ATTR_ID=10024";
		String isNSQChildQuery = "select ATTR_VALUE from P_PRD_ATTR where PRD_ID=(SELECT PARENT_ID FROM P_PRD WHERE PRD_ID = "
				+ prdID + ")  and ATTR_ID=10024";

		if (siteType == 1) {// normal site
			return siteType;
		} else if (siteType == 2) {
			attrValue = db.executeQuery(isNSQQuery, "ATTR_VALUE");
			if (attrValue.size() == 1) {// NSQ group/child site has a record
				// with attr_id = 10024
				siteType = 21;// NSQ site
			} else {
				siteType = 22;// NSS site
			}
			return siteType;
		} else if (siteType == 3) {
			attrValue = db.executeQuery(isNSQChildQuery, "ATTR_VALUE");
			if (attrValue.size() == 1) {// NSS group/child site has NO record
				// with attr_id = 10024
				throw new ItemNotFoundException(
						"Can not release inventories for specified NSQ child site.");
			} else {
				siteType = 23;// NSS Child site
			}
			return siteType;
		} else {
			throw new ItemNotFoundException(
					"Unknown site type, please update script!");
		}
	}

	/**
	 * Retrieve the slip type from DB by given product id
	 * 
	 * @param prdID
	 * @param schema
	 * @return slip type, 1 - normal slip; //TODO TBV: 21 - nsq site; 22 - nss
	 *         site; 23 - nss child site
	 */
	public int getSlipType(String prdID, String schema) {
		sycDB();

		db.resetSchema(schema);
		logger.debug("Get the site type for '" + prdID + "' from schema: "
				+ schema);

		String query = "select prd_rel_type from p_prd where prd_id = " + prdID
				+ " and product_cat_id=20 and active_ind=1 and deleted_ind=0";

		// If exception throws, means the given site id is not exists in DB
		String text = db.executeQuery(query, "prd_rel_type", 0);
		int siteType = Integer.parseInt(text);
		List<String> attrValue = null;
		String isNSQQuery = "select ATTR_VALUE from P_PRD_ATTR where PRD_ID="
				+ prdID + "  and ATTR_ID=10024";
		String isNSQChildQuery = "select ATTR_VALUE from P_PRD_ATTR where PRD_ID=(SELECT PARENT_ID FROM P_PRD WHERE PRD_ID = "
				+ prdID + ")  and ATTR_ID=10024";

		if (siteType == 1) {// normal site
			return siteType;
		} else if (siteType == 2) {
			attrValue = db.executeQuery(isNSQQuery, "ATTR_VALUE");
			// if (attrValue.size() == 1) {// NSQ group/child site has a record
			// // with attr_id = 10024
			// siteType = 21;// NSQ site
			// } else {
			siteType = 22;// NSS site
			// }
			return siteType;
		} else if (siteType == 3) {
			// attrValue = db.executeQuery(isNSQChildQuery, "ATTR_VALUE");
			// if (attrValue.size() == 1) {// NSS group/child site has NO record
			// // with attr_id = 10024
			// throw new ItemNotFoundException(
			// "Can not release inventories for specified NSQ child site.");
			// } else {
			siteType = 23;// NSS Child site
			// }
			return siteType;
		} else {
			throw new ItemNotFoundException(
					"Unknown site type, please update script!");
		}
	}

	public List<String> getAllSlipType(String schema) {
		db.resetSchema(schema);
		String query = "select prd_grp_name from p_prd_grp where prd_grp_cat_id = "
				+ PRDCAT_SLIP;
		List<String> slipType = db.executeQuery(query, "prd_grp_name");
		return slipType;
	}

	public String getParkName(String siteID, String schema) {
		db.resetSchema(schema);
		String query = "select dl.name from d_loc dl, p_prd pp where "
				+ "pp.prd_id=" + siteID + " and pp.park_id=dl.id";

		db.connect();
		String name = db.executeQuery(query, "name", 0);
		db.disconnect();
		return name;
	}

	public String getDockID(String schema, String facilityID, String dockName) {
		db.resetSchema(schema);
		String sql = "select ID from D_LOC where NAME ='" + dockName
				+ "' and PARENT_ID = " + facilityID + " and LEVEL_NUM = 50";
		List<String> ids = db.executeQuery(sql, "ID");
		logger.info("Execute query: " + sql);
		if (ids.size() < 1) {
			throw new ErrorOnDataException("Cannot find any Dock records.");
		}
		return ids.get(0);
	}

	public String getFacilityName(String facilityID, String schema) {
		return DataBaseFunctions.getFacilityName(facilityID, schema);
	}

	public String getSiteNumber(String siteID, String schema) {
		db.resetSchema(schema);
		String query = "select prd_cd from p_prd where " + "prd_id=" + siteID;

		db.connect();
		String num = db.executeQuery(query, "prd_cd", 0);
		db.disconnect();
		return num;
	}

	public String getSlipId(String slipCode, String schema) {
		String slipId = DataBaseFunctions.getSlipID(slipCode, schema);
		if (StringUtil.isEmpty(slipId)) {
			throw new ErrorOnDataException("Did not find the slip by its code.");
		}
		return slipId;
	}

	/**
	 * Get customer type id from DB
	 * 
	 * @param custType
	 * @param schema
	 * @return
	 */
	public String queryCustTypeID(String custType, String schema) {
		String custTypeID = "";

		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema + "; Query customer type id");

		String query = "select id " + "from d_ref_cust_type " + "where name = "
				+ "'" + custType + "'";

		logger.debug("Execute query: " + query);
		List<String> result = db.executeQuery(query, "id");

		if (result.size() > 0) {
			custTypeID = result.get(0);
		} else {
			throw new ErrorOnDataException("Did not find the customer type id.");
		}

		return custTypeID;
	}

	/**
	 * Get ra fee rate by fee schdule
	 * 
	 * @param schema
	 * @param scheduleID
	 * @return
	 */
	public String queryRaFeeRateByFeeSchedule(String schema, String scheduleID) {
		db.resetSchema(schema);
		String rate = "";

		String query = "select rate from p_rafee_rate where fee_schd_id = "
				+ scheduleID;
		List<String> rates = db.executeQuery(query, "rate");

		if (rates.size() > 0) {
			rate = rates.get(0);
		} else {
			throw new ErrorOnDataException(
					"Did not find the ra fee schedule rate, schedule = "
							+ scheduleID);
		}

		return rate;
	}

	/**
	 * This method is uesd to get the fee shedule ids
	 * 
	 * @param feeSchData
	 * @param schema
	 * @return
	 */
	public List<String> getFeeScheduleID(FeeScheduleData feeSchData,
			String schema) {
		db.resetSchema(schema);

		String custTypeID = "";

		String query = "select p_fee_schd.id"
				+ " from p_fee, p_fee_cond, p_fee_schd, p_condition"
				+ " where p_fee.fee_id = p_fee_cond.fee_id"
				+ " and p_fee_cond.id = p_fee_schd.fee_cond_id"
				+ " and p_condition.id = p_fee_cond.cond_id"
				+ " and p_fee_schd.active_ind = " + feeSchData.activeStatusID
				+ " and p_fee.loc_id = " + feeSchData.locationID
				+ " and p_fee.fee_type_id = " + feeSchData.feeTypeID
				+ " and p_fee_schd.prd_grp_cat_id = "
				+ feeSchData.productCategoryID
				+ " and p_condition.out_of_state_ind = " + feeSchData.stateID
				+ " and p_condition.sales_chanl_id = "
				+ feeSchData.salesChannelID
				// + " and p_fee_schd.schd_type = " + feeSchData.feeSchdTypeID
				// + " and p_fee_schd.sales_cat_id = " +
				// feeSchData.salesCategoryID
				// + " and p_fee_schd.tran_type_id = " + feeSchData.tranTypeID
				+ " and p_fee_schd.fee_target_id = " + feeSchData.applyFeeID;

		if (feeSchData.effectDate != null
				&& feeSchData.effectDate.trim().length() > 0) {
			query = query
					+ " and p_fee_schd.effect_date = to_date('"
					+ DateFunctions.formatDate(feeSchData.effectDate,
							"dd-MM-yy") + "', 'dd-mm-yy')";
		}
		if (feeSchData.tranTypeID != null
				&& feeSchData.tranTypeID.trim().length() > 0) {
			query = query + " and p_fee_schd.tran_type_id = "
					+ feeSchData.tranTypeID;
		}

		if (feeSchData.feeSchdTypeID != null
				&& feeSchData.feeSchdTypeID.trim().length() > 0) {
			query = query + " and p_fee_schd.schd_type = "
					+ feeSchData.feeSchdTypeID;
		}

		if (feeSchData.salesCategoryID != null
				&& feeSchData.salesCategoryID.trim().length() > 0) {
			query = query + " and p_fee_schd.sales_cat_id = "
					+ feeSchData.salesCategoryID;
		}

		if (StringUtil.notEmpty(feeSchData.marinaRateTypeID)) {
			query = query + " and p_fee_schd.slip_resv_type = "
					+ feeSchData.marinaRateTypeID;
		}

		if (feeSchData.prudFeeClassID != null
				&& feeSchData.prudFeeClassID.trim().length() > 0) {
			query = query + " and p_fee_schd.product_fee_class_id = "
					+ feeSchData.prudFeeClassID;
		}

		if (feeSchData.productID != null
				&& feeSchData.productID.trim().length() > 0) {
			query = query + " and p_fee.prd_id = " + feeSchData.productID;
		}

		if (feeSchData.seasonID != null
				&& feeSchData.seasonID.trim().length() > 0) {
			query = query + " and p_condition.season_id = "
					+ feeSchData.seasonID;
		}

		if (feeSchData.tranOccuID != null
				&& feeSchData.tranOccuID.trim().length() > 0) {
			query = query + " and p_fee_schd.tran_occur_id = "
					+ feeSchData.tranOccuID;
		}

		if (feeSchData.permitTypeID != null
				&& feeSchData.permitTypeID.trim().length() > 0) {
			query = query + " and p_fee_schd.permit_type_id = "
					+ feeSchData.permitTypeID;
		}

		if (!StringUtil.isEmpty(feeSchData.custType)
				&& !feeSchData.custType.equalsIgnoreCase("All")) {
			custTypeID = this.queryCustTypeID(feeSchData.custType, schema);
			query = query + " and p_condition.cust_type_id = " + custTypeID;
		}

		if (!StringUtil.isEmpty(feeSchData.startInv)) {
			query += " and p_fee_schd.START_DATE = to_date('"
					+ DateFunctions.formatDate(feeSchData.startInv, "dd-MM-yy")
					+ "', 'dd-mm-yy')";
		}
		if (!StringUtil.isEmpty(feeSchData.endInv)) {
			query += " and p_fee_schd.END_DATE = to_date('"
					+ DateFunctions.formatDate(feeSchData.endInv, "dd-MM-yy")
					+ "', 'dd-mm-yy')";
		} // Add by lesley

		query += " order by effect_date ASC";// Shane: handle multiple same
												// schedule condition except
												// effective date

		logger.debug("Execute query: " + query);

		List<String> feeSchdID = db.executeQuery(query, "id");
		return feeSchdID;
	}

	/**
	 * Get fee schedule id
	 * 
	 * @param feeSchData
	 * @param schema
	 * @return
	 */
	public String queryFeeScheduleID(FeeScheduleData feeSchData, String schema) {
		List<String> feeSchdID = getFeeScheduleID(feeSchData, schema);
		Collections.sort(feeSchdID);
		logger.info("Fee Schedule ID is " + feeSchdID);

		if (feeSchdID.size() > 0) {
			feeSchData.feeSchdId = feeSchdID.get(0);
		} else {
			throw new ErrorOnDataException("Did not find the fee schedule id");
		}

		return feeSchData.feeSchdId;

	}

	/**
	 * Get fee penalty schedule id from DB
	 * 
	 * @param feePenaltyData
	 * @param schema
	 * @return
	 */
	public List<String> getFeePenaltyScheduleId(FeePenaltyData feePenaltyData,
			String schema) {
		db.resetSchema(schema);

		String query = "select p_fee_penalty_schd.id"
				+ " from p_fee_penalty_schd, p_fee_penalty"
				+ " where p_fee_penalty_schd.fee_penalty_id = p_fee_penalty.id"
				+ " and p_fee_penalty_schd.active_ind = "
				+ feePenaltyData.activeStatusID
				+ " and p_fee_penalty_schd.effective_date = " + "'"
				+ feePenaltyData.effectDate + "'"
				+ " and p_fee_penalty_schd.fee_type_id = "
				+ feePenaltyData.feeTypeID
				+ " and p_fee_penalty_schd.tran_type_id = "
				+ feePenaltyData.tranTypeID
				+ " and p_fee_penalty_schd.fee_penalty_unit_id = "
				+ feePenaltyData.unitsID
				+ " and p_fee_penalty.prd_grp_cat_id = "
				+ feePenaltyData.productCategoryID;

		if (feePenaltyData.tranOccurID != null) {
			query = query + " and p_fee_penalty_schd.tran_occur_id = "
					+ feePenaltyData.tranOccurID;
		} else {
			query = query + " and p_fee_penalty_schd.tran_occur_id is null";
		}

		if (feePenaltyData.permitCategoryID != null) {
			query = query + " and p_fee_penalty_schd.ticket_cat_id = "
					+ feePenaltyData.permitCategoryID;
		} else {
			query = query + " and p_fee_penalty_schd.ticket_cat_id is null";
		}

		if (feePenaltyData.permitTypeID != null) {
			query = query + " and p_fee_penalty_schd.permit_type_id = "
					+ feePenaltyData.permitTypeID;
		} else {
			query = query + " and p_fee_penalty_schd.permit_type_id is null";
		}

		if (feePenaltyData.locationID != null) {
			query = query + " and p_fee_penalty.loc_id = "
					+ feePenaltyData.locationID;
		} else {
			query = query + " and p_fee_penalty.loc_id is null";
		}

		if (feePenaltyData.productID != null) {
			query = query + " and p_fee_penalty.prd_id = "
					+ feePenaltyData.productID;
		} else {
			query = query + " and p_fee_penalty.prd_id is null";
		}

		logger.debug("Execute query: " + query);

		List<String> penaltyFeeSchdID = db.executeQuery(query, "id");

		logger.info("Fee Penalty Schedule ID is " + penaltyFeeSchdID.toString());

		return penaltyFeeSchdID;
	}

	/**
	 * Get fee penalty schedule id from DB
	 * 
	 * @param feePenaltyData
	 * @param schema
	 * @return
	 */
	public String queryFeePenaltyScheduleID(FeePenaltyData feePenaltyData,
			String schema) {
		List<String> penaltyFeeSchdID = getFeePenaltyScheduleId(feePenaltyData,
				schema);

		logger.info("Fee Penalty Schedule ID is " + penaltyFeeSchdID);

		if (penaltyFeeSchdID.size() > 0) {
			feePenaltyData.id = penaltyFeeSchdID.get(0);
		} else {
			throw new ErrorOnDataException(
					"Did not find the fee penalty schedule id");
		}

		return feePenaltyData.id;

	}

	/**
	 * This method is used to get all the penalty ids for a product
	 * 
	 * @param productId
	 * @param schema
	 * @return
	 */
	public List<String> queryProductPenaltyId(String productId, String schema) {
		db.resetSchema(schema);

		String query = "select p_fee_penalty_schd.id"
				+ " from p_fee_penalty_schd, p_fee_penalty"
				+ " where p_fee_penalty_schd.fee_penalty_id = p_fee_penalty.id"
				+ " and p_fee_penalty.prd_id = " + productId
				+ " and active_ind=1";

		logger.debug("Execute query: " + query);

		List<String> penaltyFeeSchdID = db.executeQuery(query, "id");

		logger.info("Fee Penalty Schedule ID is " + penaltyFeeSchdID.toString());
		return penaltyFeeSchdID;
	}

	/**
	 * Query the recipient schedule id form database
	 * 
	 * @param schdData
	 * @param schema
	 * @return
	 */
	public String queryRecipientScheduleID(ScheduleData schdData, String schema) {
		db.resetSchema(schema);

		String query = "select f_distrib_rcpnt_schd.id"
				+ " from f_distrib_rcpnt_schd, f_distrib_rcpnt_permit"
				+ " where f_distrib_rcpnt_permit.id = f_distrib_rcpnt_schd.rcpnt_permit_id"
				+ " and f_distrib_rcpnt_permit.rev_loc_id = "
				+ schdData.revenueLocationID
				+ " and f_distrib_rcpnt_permit.rcpnt_loc_id = "
				+ schdData.recipientLocationID
				+ " and f_distrib_rcpnt_permit.rcpnt_type_id = "
				+ schdData.recipientTypeID
				+ " and f_distrib_rcpnt_permit.active_ind = "
				+ schdData.activeStatus
				+ " and f_distrib_rcpnt_schd.active_ind = "
				+ schdData.activeStatus
				// + " and f_distrib_rcpnt_schd.eff_date = "
				// + "'"
				// + schdData.effectiveDate
				// + "'"
				+ " and f_distrib_rcpnt_schd.fee_target = "
				+ schdData.feeTargetTypeID
				+ " and f_distrib_rcpnt_schd.dstr_type = "
				+ schdData.distributionTypeID
				+ " and f_distrib_rcpnt_schd.rcpnt_loc_id = "
				+ schdData.recipientLocationID
				+ " and f_distrib_rcpnt_schd.rcpnt_type_id = "
				+ schdData.recipientTypeID
				+ " and f_distrib_rcpnt_schd.prd_cat_id = "
				+ schdData.productCategoryID
				+ " and f_distrib_rcpnt_schd.sales_chnl = "
				+ schdData.salesChannelID
				+ " and f_distrib_rcpnt_schd.dstr_unit = "
				+ schdData.unitID
				+ " and f_distrib_rcpnt_schd.rev_loc_id = "
				+ schdData.revenueLocationID;

		// if(schdData.recipientPermitID !=null &&
		// schdData.recipientPermitID.length()>0){
		// query = query + " and rcpnt_permit_id = " +
		// schdData.recipientPermitID;
		// }else {
		// query = query + " and rcpnt_permit_id is null";
		// }

		// + " and f_distrib_rcpnt_schd.eff_date = "
		// + "'"
		// + schdData.effectiveDate
		// + "'"

		if (schdData.effectiveDate != null
				&& schdData.effectiveDate.length() > 1) {
			query = query + " and f_distrib_rcpnt_schd.eff_date = '"
					+ schdData.effectiveDate + "'";
		}

		if (schdData.productGroupID != null
				&& schdData.productGroupID.length() > 0) {
			query = query + " and f_distrib_rcpnt_schd.prd_grp_id = "
					+ schdData.productGroupID;
		} else {
			query = query + " and f_distrib_rcpnt_schd.prd_grp_id is null";
		}

		if (schdData.productID != null && schdData.productID.length() > 0) {
			query = query + " and f_distrib_rcpnt_schd.prd_id = "
					+ schdData.productID;
		} else {
			query = query + " and f_distrib_rcpnt_schd.prd_id is null";
		}

		if (schdData.tranTypeID != null && schdData.tranTypeID.length() > 0) {
			query = query + " and f_distrib_rcpnt_schd.trans_type_id = "
					+ schdData.tranTypeID;
		} else {
			query = query + " and f_distrib_rcpnt_schd.trans_type_id is null";
		}

		if (schdData.tranOccurID != null && schdData.tranOccurID.length() > 0) {
			query = query + " and f_distrib_rcpnt_schd.trans_occ_id = "
					+ schdData.tranOccurID;
		} else {
			query = query + " and f_distrib_rcpnt_schd.trans_occ_id is null";
		}

		if (schdData.ticketCategoryID != null
				&& schdData.ticketCategoryID.length() > 0) {
			query = query + " and f_distrib_rcpnt_schd.sales_catg = "
					+ schdData.ticketCategoryID;
		} else {
			query = query + " and f_distrib_rcpnt_schd.sales_catg is null";
		}

		logger.debug("Execute query: " + query);

		List<String> recipientScheduleID = db.executeQuery(query, "id");

		logger.info("Recipient Schedule ID is " + recipientScheduleID);

		if (recipientScheduleID.size() > 0) {
			schdData.scheduleId = recipientScheduleID.get(0);
		} else {
			throw new ErrorOnDataException(
					"Did not find the recipient schedule id");
		}

		return schdData.scheduleId;
	}

	/**
	 * query the recipient schedule effective date
	 * 
	 * @param recipeintSchdID
	 * @param schema
	 * @return
	 */
	public String queryRecipientScheduleEffectiveDate(String recipeintSchdID,
			String schema) {
		String effectiveDate = "";
		db.resetSchema(schema);

		String query = "select to_char(eff_date,'mm/dd/yyyy') as eff_date from f_distrib_rcpnt_schd where id = "
				+ recipeintSchdID;
		logger.debug("Execute query: " + query);

		List<String> effectiveDates = db.executeQuery(query, "eff_date");

		if (effectiveDates.size() == 1) {
			effectiveDate = effectiveDates.get(0);
		} else {// query result must be unique, if query result did not unique,
			throw new ErrorOnDataException(
					"The recipient schedule not correct " + recipeintSchdID);
		}

		return effectiveDate;
	}

	/**
	 * This method just use distribution rate to query recipient schedule, and
	 * the query result must be unique so this rate parameter must be special
	 * and unique
	 * 
	 * @param dstrRate
	 * @param schema
	 * @return
	 */
	public String queryRecipientScheduleIdByRate(String dstrRate, String schema) {
		String recipientScheduleID = "";
		db.resetSchema(schema);

		String query = "select f_distrib_rcpnt_schd.id "
				+ "from f_distrib_rcpnt_schd "
				+ "where f_distrib_rcpnt_schd.active_ind = 1 "
				+ "and f_distrib_rcpnt_schd.dstr_rate = " + dstrRate;

		logger.debug("Execute query: " + query);

		List<String> scheduleID = db.executeQuery(query, "id");

		if (scheduleID.size() == 1) {
			recipientScheduleID = scheduleID.get(0);
		} else {// query result must be unique, if query result did not unique,
			// you need to check the rate
			throw new ErrorOnDataException("This distribution rate " + dstrRate
					+ " is not unique or not correct.");
		}

		return recipientScheduleID;
	}

	/**
	 * Cancel any reservations that are active/preArrival with arrivalDate after
	 * today Nowshow any overdue checkin reservations Void any reservations that
	 * are active/checkedIn and checkedout
	 * 
	 * @param parkIDs
	 * @param schema
	 */
	protected void cleanupReservationsInDB(String schema, String... ord_nums) {
		db.resetSchema(schema);
		db.connect();

		String ord_num = StringUtil.arrayToString(ord_nums, true);
		logger.info("Clean up reservations from DB: "
				+ StringUtil.arrayToString(ord_nums, false));

		String query = "delete from i_inv_used where " + "booking_id in "
				+ "(select oi.booking_id from o_order o, o_ord_item oi where "
				+ "o.id = oi.ord_id " + "and o.ord_num in (" + ord_num + "))";

		db.executeUpdate(query);

		query = "update o_ord_item set booking_id = 0 where " + "ord_id in "
				+ "(select o.id from o_order o, o_ord_item oi where "
				+ "o.id = oi.ord_id " + "and o.ord_num in (" + ord_num + "))";

		db.executeUpdate(query);

		// Order status
		// 1 Active
		// 2 Cancelled
		// 3 Void
		// 4 No Show
		// 5 Locked
		// 6 Archived
		// 7 Inactive
		// 8 Closed
		// 9 Pending
		// Process status
		// 1 Pre Arrival
		// 2 Checked In
		// 3 Checked Out
		// 4 Entered
		// 5 Awarded
		// 6 Denied
		// 7 Revoked
		// 8 Reserved
		// 9 Issued

		// cancel orders whose arrival dates are after today
		query = "update o_order set ord_status_id = 3, concurrency_version_num = concurrency_version_num + 1 where "
				+ "id in "
				+ "(select o.id from o_order o, o_ord_item oi where "
				+ "o.id = oi.ord_id "
				+ "and o.proc_status_id = 1 "
				+ "and o.ord_status_id = 1 "
				+ "and oi.start_date > trunc(sysdate) "
				+ "and o.ord_num in ("
				+ ord_num + "))";

		db.executeUpdate(query);

		// noshow orders whose arrival dates are equal or before today and which
		// are not checked in
		query = "update o_order set ord_status_id = 4, concurrency_version_num = concurrency_version_num + 1 where "
				+ "id in "
				+ "(select o.id from o_order o, o_ord_item oi where "
				+ "o.id = oi.ord_id "
				+ "and o.proc_status_id = 1 "
				+ "and o.ord_status_id = 1 "
				+ "and oi.start_date <= trunc(sysdate) "
				+ "and o.ord_num in ("
				+ ord_num + "))";

		db.executeUpdate(query);

		// void order which is checked in or checked out
		query = "update o_order set ord_status_id = 2, concurrency_version_num = concurrency_version_num + 1 where "
				+ "id in "
				+ "(select o.id from o_order o, o_ord_item oi where "
				+ "o.id = oi.ord_id "
				+ "and o.proc_status_id in ( 2,3) "
				+ "and o.ord_status_id = 1 "
				+ "and o.ord_num in ("
				+ ord_num
				+ "))";

		db.executeUpdate(query);
		db.disconnect();
	}

	/**
	 * Cancel any reservations that are active/preArrival with arrivalDate after
	 * today Nowshow any overdue checkin reservations Void any reservations that
	 * are active/checkedIn and checkedout
	 * 
	 * @param schema
	 * @param ord_nums
	 */
	protected void cleanupSlipReservationsInDB(String schema,
			String... ord_nums) {
		db.resetSchema(schema);
		db.connect();

		String ord_num = StringUtil.arrayToString(ord_nums, true);
		logger.info("Clean up reservations from DB: "
				+ StringUtil.arrayToString(ord_nums, false));

		String query = "select ID, SITE_ID, to_char(START_DATE, 'mm-dd-yyyy') as START_DATE, to_char(END_DATE, 'mm-dd-yyyy') as END_DATE "
				+ "from I_INV_QTY_USED where BOOKING_ID in "
				+ "(select oi.BOOKING_ID from O_ORDER o, O_ORD_ITEM oi where "
				+ "o.id = oi.ORD_ID and o.ORD_NUM in (" + ord_num + "))";
		List<String[]> results = db.executeQuery(query, new String[] { "ID",
				"SITE_ID", "START_DATE", "END_DATE" });
		for (int i = 0; i < results.size(); i++) {
			deleteAndResetUsedSlipQty(results.get(i)[0], results.get(i)[1],
					results.get(i)[2], results.get(i)[3]);
		}

		query = "update o_ord_item set booking_id = 0 where ord_id in "
				+ "(select o.id from o_order o, o_ord_item oi where "
				+ "o.id = oi.ord_id " + "and o.ord_num in (" + ord_num + "))";

		db.executeUpdate(query);

		// Order status
		// 1 Active
		// 2 Cancelled
		// 3 Void
		// 4 No Show
		// 5 Locked
		// 6 Archived
		// 7 Inactive
		// 8 Closed
		// 9 Pending
		// Process status
		// 1 Pre Arrival
		// 2 Checked In
		// 3 Checked Out
		// 4 Entered
		// 5 Awarded
		// 6 Denied
		// 7 Revoked
		// 8 Reserved
		// 9 Issued

		// cancel orders whose arrival dates are after today
		query = "update o_order set ord_status_id = 3, concurrency_version_num = concurrency_version_num + 1 where id in "
				+ "(select o.id from o_order o, o_ord_item oi where "
				+ "o.id = oi.ord_id "
				+ "and o.proc_status_id = 1 "
				+ "and o.ord_status_id = 1 "
				+ "and oi.start_date > trunc(sysdate) "
				+ "and o.ord_num in ("
				+ ord_num + "))";

		db.executeUpdate(query);

		// noshow orders whose arrival dates are equal or before today and which
		// are not checked in
		query = "update o_order set ord_status_id = 4, concurrency_version_num = concurrency_version_num + 1 where "
				+ "id in "
				+ "(select o.id from o_order o, o_ord_item oi where "
				+ "o.id = oi.ord_id "
				+ "and o.proc_status_id = 1 "
				+ "and o.ord_status_id = 1 "
				+ "and oi.start_date <= trunc(sysdate) "
				+ "and o.ord_num in ("
				+ ord_num + "))";

		db.executeUpdate(query);

		// void order which is checked in or checked out
		query = "update o_order set ord_status_id = 2, concurrency_version_num = concurrency_version_num + 1 where "
				+ "id in "
				+ "(select o.id from o_order o, o_ord_item oi where "
				+ "o.id = oi.ord_id "
				+ "and o.proc_status_id in ( 2,3) "
				+ "and o.ord_status_id = 1 "
				+ "and o.ord_num in ("
				+ ord_num
				+ "))";

		db.executeUpdate(query);
		db.disconnect();
	}

	public String queryLotteryInventoryStartDate(String schema, String parkName) {
		// db.resetSchema(schema);
		// db.connect();
		//
		// String tomorrow = DateFunctions.getDateAfterToday(1);
		// String yesterday = DateFunctions.getDateAfterToday(-1);
		//
		//
		// String query =
		// "select to_char(Min(lot.inv_st_date), 'MM-dd-yyyy') as lot_date from P_LOTTERY_SCHD lot where prd_id in ( "
		// +
		// "SELECT lottery_id FROM P_PRD_LOTTERY_PARTICIPATION WHERE LOC_ID in ( "
		// + "SELECT ID FROM D_LOC  WHERE UPPER(NAME) = UPPER('" + parkName +
		// "')) "
		// + "AND PRD_GRP_ID IS NULL "
		// + "AND PRD_ID IS NULL) "
		// + "AND lot.app_st_date < to_date('"
		// + tomorrow + "','MM-dd-yyyy') "
		// + "AND lot.app_end_date >= to_date('"
		// + yesterday + "','MM-dd-yyyy') "
		// + "AND lot.active_ind=1" ;
		//
		// logger.debug("Execute query: " + query);
		//
		// String date = db.executeQuery(query, "LOT_DATE", 0);
		// db.disconnect();
		//
		// if (date == null) {
		// throw new ItemNotFoundException(
		// "There is no lottery available for the given parkName: "
		// + parkName);
		// } else {
		// logger.info("Get lottery available date " + date);
		// }
		//
		// return date;
		String date = this.queryLotteryInventoryStartDate(schema, parkName,
				"All");
		return date;

	}

	/**
	 * Retrieve the first available lottery date from DB based on given park
	 * name, and loop name
	 * 
	 * @param schema
	 * @param parkName
	 * @param loopName
	 * @return
	 */
	public String queryLotteryInventoryStartDate(String schema,
			String parkName, String loopName) {
		db.resetSchema(schema);
		db.connect();

		String tomorrow = DateFunctions.getDateAfterToday(1);
		String yesterday = DateFunctions.getDateAfterToday(-1);

		// String query =
		// "select to_char(Min(lot.inv_st_date), 'MM-dd-yyyy') as lot_date from P_LOTTERY_SCHD lot where prd_id in ( "
		// +
		// "SELECT lottery_id FROM P_PRD_LOTTERY_PARTICIPATION WHERE LOC_ID in ( "
		// +
		// "SELECT ID FROM D_LOC  WHERE UPPER(NAME) = UPPER('" + loopName +
		// "') " +
		// "AND PARENT_ID = (" +
		// "SELECT ID FROM D_LOC  WHERE UPPER(NAME) = UPPER('" + parkName +
		// "'))) " +
		// "AND PRD_GRP_ID IS NULL " +
		// "AND PRD_ID IS NULL) " +
		// "AND lot.app_st_date < to_date('" +
		// tomorrow +
		// "','MM-dd-yyyy') " +
		// "AND lot.app_end_date >= to_date('" +
		// yesterday +
		// "','MM-dd-yyyy') " +
		// "AND lot.active_ind=1" ;

		String query = "select to_char(Min(lot.inv_st_date), 'MM-dd-yyyy') as lot_date from P_LOTTERY_SCHD lot where prd_id in ( "
				+ "SELECT lottery_id FROM P_PRD_LOTTERY_PARTICIPATION WHERE LOC_ID in ( ";

		if (null != loopName && loopName.length() > 0
				&& !loopName.equalsIgnoreCase("All")) {
			query = query + "SELECT ID FROM D_LOC  WHERE UPPER(NAME) = UPPER('"
					+ loopName + "') " + "AND PARENT_ID = ("
					+ "SELECT ID FROM D_LOC  WHERE UPPER(NAME) = UPPER('"
					+ parkName + "'))) ";
		} else {
			query = query + "SELECT ID FROM D_LOC  WHERE UPPER(NAME) = UPPER('"
					+ parkName + "')) ";
		}

		query = query + "AND PRD_GRP_ID IS NULL " + "AND PRD_ID IS NULL) "
				+ "AND lot.app_st_date < to_date('" + tomorrow
				+ "','MM-dd-yyyy') " + "AND lot.app_end_date >= to_date('"
				+ yesterday + "','MM-dd-yyyy') " + "AND lot.active_ind=1";

		logger.debug("Execute query: " + query);

		String date = db.executeQuery(query, "LOT_DATE", 0);
		db.disconnect();

		if (date == null) {
			throw new ItemNotFoundException(
					"There is no lottery available for the given parkName: "
							+ parkName + " and LoopName: " + loopName);
		} else {
			logger.info("Get lottery available date " + date);
		}

		return date;
	}

	/**
	 * Get lottery inventory date by location ID and product name
	 * 
	 * @param schema
	 * @param locID
	 * @param prdNm
	 * @return
	 */
	public String getLotteryInvStartDate(String schema, String locID,
			String prdNm) {
		db.resetSchema(schema);
		db.connect();
		String query = "select to_char(INV_ST_DATE, 'MM-dd-yyyy') INV_ST_DATE from P_LOTTERY_SCHD where prd_id = "
				+ "(select prd_id from p_prd where loc_id ="
				+ locID
				+ " and prd_name = '"
				+ prdNm
				+ "' and active_ind = 1) "
				+ "and active_ind = 1";
		String startDate = null;
		logger.info("Query:" + query);
		List<String> dateList = db.executeQuery(query, "INV_ST_DATE");
		if (dateList.size() > 0) {
			startDate = dateList.get(0);
		} else {
			logger.error("Can't get lottery start date!");
		}
		return startDate;
	}

	/**
	 * Get lottery schedule freeze end date
	 */
	public String getLotteryFreezeEndDate(String schema, String locID,
			String prdNm) {
		db.resetSchema(schema);
		String query = "select to_char(FREEZE_END_DATE, 'MM-dd-yyyy') FREEZE_END_DATE from P_LOTTERY_SCHD where "
				+ "prd_id = (select prd_id from p_prd where loc_id ="
				+ locID
				+ " and prd_name = '"
				+ prdNm
				+ "' and active_ind = 1) "
				+ "and active_ind = 1";
		String freezeDate = null;
		logger.info("Query:" + query);
		List<String> dateList = db.executeQuery(query, "FREEZE_END_DATE");
		if (dateList.size() > 0) {
			freezeDate = dateList.get(0);
		} else {
			logger.error("Can't get lottery freeze end date!");
		}
		return freezeDate;
	}

	/**
	 * Query permit lottery inventory start date
	 * 
	 * @param schema
	 * @param lotteryName
	 * @return
	 */
	public String queryPermitLotteryInventoryStartDate(String schema,
			String lotteryName) {
		db.resetSchema(schema);
		db.connect();

		String tomorrow = DateFunctions.getDateAfterToday(1);
		String yesterday = DateFunctions.getDateAfterToday(-1);

		String query = "select to_char(Min(lot.inv_st_date), 'MM-dd-yyyy') as lot_date from P_LOTTERY_SCHD lot where prd_id in "
				+ "(select prd_id from p_prd where prd_name = '"
				+ lotteryName
				+ "') "
				+ "and lot.app_st_date < to_date('"
				+ tomorrow
				+ "','MM-dd-yyyy') "
				+ "and lot.app_end_date >= to_date('"
				+ yesterday + "','MM-dd-yyyy') " + "and lot.active_ind=1";

		logger.debug("Execute query: " + query);

		String date = db.executeQuery(query, "LOT_DATE", 0);
		db.disconnect();

		if (date == null) {
			throw new ItemNotFoundException(
					"There is no lottery available for the given lottery name: "
							+ lotteryName);
		} else {
			logger.info("Get lottery available date " + date);
		}

		return date;
	}

	/**
	 * Query permit lottery inventory end date
	 * 
	 * @param schema
	 * @param lotteryName
	 * @return
	 */
	public String queryPermitLotteryInventoryEndDate(String schema,
			String lotteryName) {
		db.resetSchema(schema);
		db.connect();

		String tomorrow = DateFunctions.getDateAfterToday(1);
		String yesterday = DateFunctions.getDateAfterToday(-1);

		String query = "select to_char(Min(lot.inv_end_date), 'MM-dd-yyyy') as lot_date from P_LOTTERY_SCHD lot where prd_id in "
				+ "(select prd_id from p_prd where prd_name = '"
				+ lotteryName
				+ "') "
				+ "and lot.app_st_date < to_date('"
				+ tomorrow
				+ "','MM-dd-yyyy') "
				+ "and lot.app_end_date >= to_date('"
				+ yesterday + "','MM-dd-yyyy') " + "and lot.active_ind=1";

		logger.debug("Execute query: " + query);

		String date = db.executeQuery(query, "LOT_DATE", 0);

		if (date == null) {
			throw new ItemNotFoundException(
					"There is no lottery available for the given lottery name: "
							+ lotteryName);
		} else {
			logger.info("Get lottery inventry end date " + date);
		}

		return date;
	}

	/**
	 * Retrieve the first available lottery date from DB based on given park
	 * name, and loop name, and siteID
	 * 
	 * @param schema
	 * @param parkName
	 * @param loopName
	 * @param siteID
	 * @return
	 */
	public String queryLotteryInventoryStartDate(String schema,
			String parkName, String loopName, String siteID) {
		db.resetSchema(schema);
		db.connect();

		String tomorrow = DateFunctions.getDateAfterToday(1);
		String yesterday = DateFunctions.getDateAfterToday(-1);

		String query = "select to_char(Min(lot.inv_st_date), 'MM-dd-yyyy') as lot_date from P_LOTTERY_SCHD lot where prd_id in "
				+ "( "
				+ "SELECT lottery_id FROM P_PRD_LOTTERY_PARTICIPATION WHERE LOC_ID = ( "
				+ "SELECT ID FROM D_LOC  WHERE UPPER(NAME) = UPPER('"
				+ loopName
				+ "') "
				+ "AND PARENT_ID = ("
				+ "SELECT ID FROM D_LOC  WHERE UPPER(NAME) = UPPER('"
				+ parkName
				+ "'))) "
				+ "AND PRD_ID = (select prd_id from p_prd where prd_id = '"
				+ siteID
				+ "')"
				+ ") "
				+ "AND lot.app_st_date < to_date('"
				+ tomorrow
				+ "','MM-dd-yyyy') "
				+ "AND lot.app_end_date >= to_date('"
				+ yesterday
				+ "','MM-dd-yyyy') " + "AND lot.active_ind=1";

		logger.debug("Execute query: " + query);

		String date = db.executeQuery(query, "LOT_DATE", 0);
		db.disconnect();

		if (date == null) {
			throw new ItemNotFoundException(
					"There is no lottery available for the given parkName: "
							+ parkName + " and LoopName: " + loopName
							+ " and siteID:" + siteID);
		} else {
			logger.info("Get lottery available date " + date);
		}

		return date;
	}
	
	public String[] queryLotteryApplicationPeriod(String schema, String facilityId, String lotteryName){
		db.resetSchema(schema);
		db.connect();
		String query = "select app_st_date, app_end_date from P_LOTTERY_SCHD where prd_id = "
				+ "(select prd_id from p_prd where prd_name = '" + lotteryName
				+ "' and product_cat_id = 9 and active_ind = 1 and loc_id = '" // 9 means lottery
				+ facilityId + "')";
		String colNames[] = new String[] { "app_st_date", "app_end_date"};
		logger.info("Execute query:" + query);
		String[] applicationPeriod = db.executeQuery(query, colNames, 0);
		db.disconnect();
		return applicationPeriod;
	}

	/**
	 * Get Execution Date & Time by location id and lottery name
	 * 
	 * @param schema
	 * @param locID
	 * @param prdNm
	 * @return
	 */
	public String getLotteryExeDateTime(String schema, String locID,
			String prdNm, String format) {
		db.resetSchema(schema);
		db.connect();
		String query = "";
		if (StringUtil.isEmpty(format)) {
			query = "select EXECUTION_DATE";
		} else {
			query = "select to_char(EXECUTION_DATE, '" + format
					+ "') EXECUTION_DATE";
		}
		query += " from P_LOTTERY_SCHD where prd_id = "
				+ "(select prd_id from p_prd where loc_id =" + locID
				+ " and prd_name = '" + prdNm + "' and active_ind = 1) "
				+ "and active_ind = 1";
		logger.info("execute query: " + query);
		String exeDT = null;
		List<String> dateList = db.executeQuery(query, "EXECUTION_DATE");
		if (dateList.size() > 0) {
			exeDT = dateList.get(0);
		} else {
			logger.error("Can't get lottery execution date!");
		}
		return exeDT;
	}

	/**
	 * Retrieve the first available lottery date within the given start and end
	 * date period
	 * 
	 * @param startDate
	 * @param endDate
	 * @param schema
	 * @param park_id
	 * @return
	 */
	public String getLotteryAvailability(String startDate, String endDate,
			String schema, int park_id, String salesChannel) {
		String channelCode = "";
		if (salesChannel.equalsIgnoreCase("web")) {
			channelCode = "2";
		} else if (salesChannel.equalsIgnoreCase("call")) {
			channelCode = "3";
		} else if (salesChannel.equalsIgnoreCase("field")) {
			channelCode = "4";
		} else {
			throw new ItemNotFoundException("Sales Channel " + salesChannel
					+ " is not valid.");
		}

		db.resetSchema(schema);
		db.connect();

		String sd = DateFunctions.formatDate(startDate, "M-d-yyyy");
		String ed = DateFunctions.formatDate(endDate, "M-d-yyyy");

		String tomorrow = DateFunctions.getDateAfterToday(1);
		String today = DateFunctions.getDateAfterToday(0);

		String query = "select to_char(Min(lot.inv_st_date), 'MM-dd-yyyy') as lot_date from p_lottery_schd lot, p_prd prd,p_lottery_schd_sales_chnl chnl where "
				+ "lot.app_st_date < to_date('"
				+ tomorrow
				+ "','MM-dd-yyyy') "
				+ "and lot.app_end_date >= to_date('"
				+ today
				+ "','MM-dd-yyyy') "
				+ "and lot.inv_st_date >= to_date('"
				+ sd
				+ "','MM-dd-yyyy') "
				+ "and lot.inv_end_date <= to_date('"
				+ ed
				+ "','MM-dd-yyyy') "
				+ "and lot.active_ind=1 "
				+ "and lot.prd_id=prd.prd_id "
				+ "and prd.loc_id= "
				+ park_id
				+ " "
				+ "and chnl.lottery_schd_id=lot.id "
				+ "and chnl.sales_chnl_id =" + channelCode;
		logger.debug("Execute query: " + query);

		String date = db.executeQuery(query, "LOT_DATE", 0);
		db.disconnect();

		if (date == null) {
			throw new ItemNotFoundException(
					"There is no lottery available in the given period "
							+ startDate + " to " + endDate);
		} else {
			logger.info("Get lottery available date " + date);
		}

		return date;
	}

	/**
	 * Retrieve the first lottery inventory date within 720 days starting from
	 * today
	 * 
	 * @param schema
	 * @param park_id
	 * @return
	 */
	public String getLotteryAvailability(String schema, int park_id,
			String salesChannel) {
		return getLotteryAvailability(DateFunctions.getDateAfterToday(0),
				DateFunctions.getDateAfterToday(720), schema, park_id,
				salesChannel);
	}

	/**
	 * get all lottery product names associate with specific location and
	 * specific applicable product category
	 * 
	 * @param schema
	 * @param locID
	 * @param applicableProductCategoryID
	 *            - Site: 3, Permit: 5, Ticket: 6, Privilege: 10, Slip: 20, etc
	 * @return
	 */
	public List<String> getLotteryProduct(String schema, String locID,
			String applicableProductCategoryID) {
		logger.info("Get lottery products by associated with location(ID="
				+ locID + ").");
		db.resetSchema(schema);
		String sql = "select PRD_NAME from P_PRD where APPL_PRD_CAT_ID = "
				+ applicableProductCategoryID
				+ " and PRODUCT_CAT_ID = 9 and LOC_ID = " + locID;
		logger.info("Execute query: " + sql);
		List<String> lotteryNames = db.executeQuery(sql, "PRD_NAME");
		if (lotteryNames.size() < 1) {
			throw new ErrorOnDataException(
					"There is not any lottery product associated with location: "
							+ locID);
		}

		return lotteryNames;
	}

	/**
	 * change Permit order entryDate in DB
	 * 
	 * @param orderNum
	 * @param startDate
	 * @param schema
	 * @Return void
	 * @Throws
	 */
	public void changePermitEntryDateInDB(String startDate, String schema,
			String... orderNum) {
		String sql = "Update O_ORD_ITEM set START_DATE = TO_DATE('" + startDate
				+ "','MM/DD/yyyy') "
				+ "where ORD_ID in (select ID from O_ORDER where ORD_NUM in ("
				+ StringUtil.arrayToString(orderNum, true) + "))";

		logger.info("change Permit order ("
				+ StringUtil.arrayToString(orderNum, false)
				+ ") entryDate in DB...");
		db.resetSchema(schema);
		db.executeUpdate(sql);
	}

	public String getDiscountPromoCode(String discountName, String schema) {
		db.resetSchema(schema);
		db.connect();
		String query = "select pc.promo_code as promo_code from d_ref_discnt_promo_code pc, d_ref_discnt_type dt where"
				+ " dt.name='"
				+ discountName
				+ "'"
				+ " and dt.id=pc.discnt_type_id";

		String code = db.executeQuery(query, "promo_code", 0);
		db.disconnect();

		if (StringUtil.isEmpty(code)) {
			return "";
		} else {
			return code + " - " + discountName;
		}
	}

	public List<String> getPOSProduct(String schema) {
		db.resetSchema(schema);
		db.connect();
		String query = "select prd_dscr from p_prd p, p_prd_grp g where p.prd_grp_id=g.prd_grp_id and g.prd_grp_cat_id=4";

		List<String> pos = db.executeQuery(query, "prd_dscr");

		return pos;
	}

	/**
	 * Query pos product name by UPC
	 * 
	 * @param schema
	 * @param barcode
	 * @return
	 */
	public List<String> getPosProductByUPC(String schema, String barcode) {
		db.resetSchema(schema);
		db.connect();

		String query = "select distinct prd_name " + "from p_prd, p_prd_upc "
				+ "where p_prd.prd_id = "
				+ "(select prd_id from p_prd_upc where upc =" + "'" + barcode
				+ "')";
		List<String> pos = db.executeQuery(query, "prd_name");

		return pos;
	}

	public List<String> getLicenseYearId(String schema) {
		db.resetSchema(schema);
		db.connect();
		String query = "select ID from P_PRD_YEAR";

		List<String> id = db.executeQuery(query, "id");
		return id;
	}

	public int getFiscalYearEffectiveYearType(String schema) {
		db.resetSchema(schema);
		String sql = "select EFFECTIVE_YEAR_TYPE from d_fiscal_year";
		logger.info("Execute query: " + sql);
		List<String> results = db.executeQuery(sql, "EFFECTIVE_YEAR_TYPE");
		if (results.size() != 1) {
			throw new ErrorOnDataException(
					"Expect fiscal year info should just have one record.");
		}
		return Integer.parseInt(results.get(0));
	}

	public String[] getFiscalYearStartingEndingDates(String schema) {
		db.resetSchema(schema);
		String sql = "select start_month, start_day, end_month, end_day from d_fiscal_year";
		logger.info("Execute query: " + sql);
		String colNames[] = new String[] { "start_month", "start_day",
				"end_month", "end_day" };
		List<String[]> results = db.executeQuery(sql, colNames);

		String dates[] = new String[2];
		if (results.size() != 1) {
			throw new ErrorOnDataException(
					"Expect fiscal year info should just have one record.");
		} else {
			dates[0] = (Integer.parseInt(results.get(0)[0]) + 1) + "/"
					+ results.get(0)[1];
			dates[1] = (Integer.parseInt(results.get(0)[2]) + 1) + "/"
					+ results.get(0)[3];
		}

		return dates;
	}

	/**
	 * Get license year base on fical year Author: Vivian
	 * 
	 * @param schema
	 * @return
	 */
	public String getFiscalYear(String schema) {
		db.resetSchema(schema);
		String fiscalYear = "";

		String query = "select effective_year_type, start_month, start_day, end_month, end_day from d_fiscal_year";

		String[] ficalYear = { "effective_year_type", "start_month",
				"start_day", "end_month", "end_day" };
		List<String[]> fiscalYearInfo = db.executeQuery(query, ficalYear);

		if (fiscalYearInfo.size() != 1) {
			throw new ErrorOnDataException(
					"Expect fiscal year info should just have one record.");
		} else {
			String currentYear = String.valueOf(DateFunctions.getCurrentYear());

			if (fiscalYearInfo.get(0)[0].equals("2")) {// 2 means: effective
														// year is Ending year
				String endMonth = String.valueOf((Integer
						.valueOf(fiscalYearInfo.get(0)[3]) + 1));// end_month
				String endDay = fiscalYearInfo.get(0)[4];// end_day
				String endDate = currentYear + "/" + endMonth + "/" + endDay;
				if (DateFunctions.compareDates(DateFunctions.getToday(),
						endDate) < 1) {
					fiscalYear = currentYear;
				} else {
					fiscalYear = String
							.valueOf(DateFunctions.getCurrentYear() + 1);
				}
			} else {// 2 means: effective year is Ending year
				String startMonth = String.valueOf((Integer
						.valueOf(fiscalYearInfo.get(0)[1]) + 1));// start_month
				String startDay = fiscalYearInfo.get(0)[2];// start_day
				String startDate = currentYear + "/" + startMonth + "/"
						+ startDay;

				if (DateFunctions.compareDates(DateFunctions.getToday(),
						startDate) < 1) {
					// fiscalYear = String.valueOf(Integer.parseInt(currentYear)
					// - 1);
					if (schema.equalsIgnoreCase("LIVE_SK")) { // Lesley[20140311]Update
																// due to
																// Defect-60194,
																// right now
																// can't add
																// license year
																// equal to
																// fiscal year
																// for SK before
																// April. After
																// PCR 4613 is
																// done, need to
																// change it
																// back.
						fiscalYear = currentYear;
					} else {
						fiscalYear = String.valueOf(Integer
								.parseInt(currentYear) - 1);
					}
				} else {
					fiscalYear = currentYear;
				}
			}
		}

		return fiscalYear;
	}

	/**
	 * Delete lien company detail info by lien company name
	 * 
	 * @param lienCompanyName
	 * @param schema
	 */
	public void deleteLienCompanyDetailInfo(String lienCompanyName,
			String schema) {
		db.resetSchema(schema);
		logger.info("Delete lien company detail info by lien company name = "
				+ lienCompanyName);
		String sql = "delete from d_lien_company_addr "
				+ "where d_lien_company_addr.lien_company_id = (select id from d_lien_company where name = '"
				+ lienCompanyName + "')";
		logger.info("Execute sql: " + sql);
		db.executeUpdate(sql);

		sql = "delete from d_lien_company where name = '" + lienCompanyName
				+ "'";
		logger.info("Execute sql: " + sql);
		db.executeUpdate(sql);
	}

	/**
	 * Check is a season is available for the given start and end date for the
	 * given schema
	 * 
	 * @param siteID
	 * @param start_date
	 * @param end_date
	 * @param schema
	 * @return
	 */
	public boolean isSeasonAvailable(int siteID, String start_date,
			String end_date, String schema) {
		db.resetSchema(schema);
		db.connect();
		start_date = DateFunctions.formatDate(start_date, "yyyy-MM-dd");
		end_date = DateFunctions.formatDate(end_date, "yyyy-MM-dd");
		String query = "select count(iss.*) as count from i_season_schdl iss, i_season_schdl_prd issp where issp.prd_id="
				+ siteID
				+ " and issp.season_schdl_id=iss.id and iss.start_date <= to_date('"
				+ start_date
				+ "','yyyy-MM-dd') and iss.end_date > to_date('"
				+ end_date
				+ "','yyyy-MM-dd') and iss.active_ind=1 and deleted ==0";

		String count = db.executeQuery(query, "count", 0);
		db.disconnect();
		return Integer.parseInt(count) > 0;
	}

	public boolean addSeason(int[] siteIDs, String start_date, String end_date,
			String schema) {
		db.resetSchema(schema);
		db.connect();
		start_date = DateFunctions.formatDate(start_date, "yyyy-MM-dd");
		end_date = DateFunctions.formatDate(end_date, "yyyy-MM-dd");
		for (int siteID : siteIDs) {
			String query = "select count(iss.*) as count from i_season_schdl iss, i_season_schdl_prd issp where issp.prd_id="
					+ siteID
					+ " and issp.season_schdl_id=iss.id and iss.start_date <= to_date('"
					+ start_date
					+ "','yyyy-MM-dd') and iss.end_date > to_date('"
					+ end_date
					+ "','yyyy-MM-dd') and iss.active_ind=1 and deleted ==0";

			db.executeQuery(query, "count", 0);
			db.disconnect();
		}
		// todo
		return false;
	}

	/**
	 * Retrieve test cases running result
	 * 
	 * @param testSet
	 *            -test set(advanced/basic)
	 * @param env
	 *            -qa1/qa2...
	 * @return
	 */
	public List<String[]> getTestCasesRuningResult(String env) {
		db.resetSchema("QA_USER");
		logger.info("Query Case Name and Running Result.");

		String sql = "select casename,if("
				+ env
				+ "_result=2,'Passed','Not Debugged' ) as "
				+ env
				+ "_result from test_cases where test_set in('basic','advanced') and "
				// + "_result from test_cases where test_set in('basic') and "
				+ env + "_active=1 order by casename asc";
		// + "' and " + env + "_active=1 and "+ env +
		// "_result!=2 order by casename asc";

		String[] colNames = { "casename", env + "_result" };
		List<String[]> values = db.executeQuery(sql, colNames);
		logger.debug("Execute query: " + sql);

		db.disconnect();
		return values;
	}

	public List<String[]> getCasesAndFailedReason(String env,
			String... executionIds) {
		db.resetSchema("QA_USER");
		logger.info("Query Case Name and Running Result.");

		String sql = "select tc.casename casename,decode(tc."
				+ env
				+ "_result,2,'Passed','Not Debugged') as "
				+ env
				+ "_result,ted.tool tool,ted.exception exception from test_cases tc,test_execution_details ted where tc.id=ted.case_id and ted.execution_Id in("
				+ StringUtil.arrayToString(executionIds, true) + ") and tc."
				+ env + "_active=1 order by casename";

		String[] colNames = { "casename", env + "_result", "tool", "exception" };
		List<String[]> values = db.executeQuery(sql, colNames);
		logger.debug("Execute query: " + sql);

		db.disconnect();
		return values;
	}

	/**
	 * This method used to query site information
	 * 
	 * @param schema
	 * @param productId
	 * @param siteCol
	 * @param value
	 * @return
	 */
	public List<String[]> getSiteInfo(String schema, String productId,
			String siteCol, String value) {

		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema + " ; Get Site Infomation...");
		String[] site = { "PRD_REL_TYPE", "PRD_CD", "PRD_NAME", "IMPORT_TYPE",
				"PRD_DSCR", "PRD_RATE_TYPE_ID", "PARENT_ID",
				"IMPORT_RESERVABLE", "IMPORT_WEB_RESERVABLE" };

		String query = "SELECT * FROM p_prd" + " WHERE PRD_ID ='" + productId
				+ "' AND " + siteCol + "='" + value + "'";

		List<String[]> result = db.executeQuery(query, site);
		return result;
	}

	/**
	 * This method used to update site information
	 * 
	 * @param schema
	 * @param productId
	 * @param siteCol
	 * @param value
	 */
	public void updateSiteInfo(String schema, String productId, String siteCol,
			String value) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema
				+ "Update Site Attribute Information...");

		String query = "UPDATE p_prd" + " SET " + siteCol + "='" + value
				+ "' WHERE PRD_ID='" + productId + "'";
		db.executeUpdate(query);

	}

	/**
	 * This method used to query site attribute values
	 * 
	 * @param schema
	 * @param productId
	 * @return<ATTR_ID,ATTR_VALUE,ATTR_NAME>
	 */
	public List<String[]> getSiteAttribute(String schema, String productId) {

		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema
				+ " ; Get Site Attribute Values...");
		String[] siteAttr = { "ATTR_ID", "ATTR_VALUE", "ATTR_NAME" };
		String query = "SELECT * FROM p_prd_attr" + " WHERE PRD_ID ="
				+ productId;

		System.out.println(query);
		List<String[]> result = db.executeQuery(query, siteAttr);
		return result;
	}

	public String getSiteAttributeValue(String schema, String productId,
			String attr_id) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema
				+ " ; Get Site Attribute Values for Attr " + attr_id);
		String query = "SELECT attr_value FROM p_prd_attr" + " WHERE PRD_ID ="
				+ productId + " and attr_id=" + attr_id;

		String result = db.executeQuery(query, "attr_value", 0);
		return result;
	}

	/**
	 * This method used to update site attribute values
	 * 
	 * @param schema
	 * @param productId
	 * @param attrName
	 * @param value
	 */
	public void updateSiteAttributeInfo(String schema, String productId,
			String attrName, String value) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema
				+ "Update Site Attribute Information...");

		String query = "UPDATE p_prd_attr" + " SET ATTR_VALUE='" + value
				+ "' WHERE PRD_ID='" + productId + "'AND ATTR_NAME='"
				+ attrName + "'";
		db.executeUpdate(query);

	}

	/**
	 * This method used to insert site attribute value records
	 * 
	 * @param schema
	 * @param productId
	 * @param attrId
	 * @param attrName
	 * @param value
	 */
	public void insertSiteAttributeInfo(String schema, String productId,
			String attrId, String attrName, String value) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema
				+ "Insert Site Attribute Information...");

		String seq = new DecimalFormat("00000000").format(
				DateFunctions.getCurrentTime()).substring(5);
		String query = "INSERT INTO p_prd_attr" + " VALUES('" + seq + "','"
				+ attrId + "','" + productId + "','','1','0','" + value + "','"
				+ attrName + "','')";
		db.executeUpdate(query);

	}

	public void deleteSiteAttributeInfo(String schema, String productId,
			String attrId, String attrName) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema
				+ "delete Site Attribute Information...");

		String query = "delete from p_prd_attr where PRD_ID='" + productId
				+ "'AND ATTR_NAME='" + attrName + "' AND  ATTR_ID='" + attrId
				+ "'";
		db.executeUpdate(query);

	}

	/**
	 * This method used to change site attribute values. Work flow: 1.query the
	 * site attribute value which is keep with our meets or not. 2. if the
	 * attribute value meets our needs,then return . 3. if the attribute value
	 * did not meets our needs,we will find the attribute whether exist or not.
	 * 4. if the attribute record is exist ,we will update the attribute value.
	 * 5. if the attribute record is not exist, we will insert new attribute
	 * value record.
	 * 
	 * @param schema
	 * @param productId
	 * @param attrId
	 * @param attrName
	 * @param value
	 */
	public void changeSiteAttribute(String schema, String productId,
			String attrId, String attrName, String value, boolean deleteRecord) {
		boolean attrValue = false;
		boolean attrExist = false;
		List<String[]> result = getSiteAttribute(schema, productId);// one
		// record
		for (String[] siteAttr : result) {
			if (attrId.equals(siteAttr[0])) {
				attrExist = true;// site id
				if (value.equals(siteAttr[1])) {
					attrValue = true;// site value
				}
			}
		}

		if (!attrValue) {
			if (attrExist) {
				if (deleteRecord) {
					deleteSiteAttributeInfo(schema, productId, attrId, attrName);
				} else
					updateSiteAttributeInfo(schema, productId, attrName, value);
			} else {
				insertSiteAttributeInfo(schema, productId, attrId, attrName,
						value);
			}
		} else {
			logger.info("'" + attrName
					+ "' site attribute do not need to change.");
		}

	}

	public void changeSiteAttribute(String schema, String productId,
			String attrId, String attrName, String value) {
		changeSiteAttribute(schema, productId, attrId, attrName, value, false);
	}

	/**
	 * This method used to change site information. Work flow:1. query the site
	 * information which is keep with our meets or not. 2. if the site
	 * information meets our needs,then return . 3. if the site information did
	 * not meets our needs,we will update the site information.
	 * 
	 * @param schema
	 * @param productId
	 * @param sites
	 */
	public void changeSiteInfomation(String schema, String productId,
			String[] sites) {
		List<String[]> result = getSiteInfo(schema, productId, sites[0],
				sites[1]);

		if (result.size() == 0) {
			updateSiteInfo(schema, productId, sites[0], sites[1]);
		} else {
			logger.info("site information do not need to change.");
		}
	}

	/**
	 * This method used to get lottery id by lottery name.
	 * 
	 * @param schema
	 * @param lotteryName
	 * @return
	 */
	public String getLotteryId(String schema, String lotteryName) {

		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema + " ; Get Lottery id...");

		String query = "SELECT * FROM p_prd" + " WHERE PRD_NAME ='"
				+ lotteryName + "' and product_cat_id=9";
		List<String> idList = db.executeQuery(query, "PRD_ID");
		String id = "";
		if (null != idList && idList.size() > 0) {
			id = db.executeQuery(query, "PRD_ID").get(0);
			logger.info("Get lottery id is: " + id);
		} else {
			logger.info("Can't get lottery ID by lottery name!");
		}
		return id;
	}

	/**
	 * This method used to get quto inventory id
	 * 
	 * @param schema
	 * @param permit
	 * @return
	 */
	public String queryQutoInventoryID(String schema, PermitInfo permit) {
		db.resetSchema(schema);
		String startDate = DateFunctions.formatDate(permit.entryDate,
				"dd-MMM-yy").toUpperCase();
		String endDate = startDate;

		String query = "select id from i_permit_inv " + "where loc_id = "
				+ permit.locationID + " and entrance_id = " + permit.entranceID
				+ " and quota_type_id = " + permit.permitTypeID
				+ " and start_date = '" + startDate + "'" + " and end_date = '"
				+ endDate + "'";

		logger.info("Execute query: " + query);
		String id = db.executeQuery(query, "id").get(0);

		return id;
	}

	/**
	 * query permit inventory used info
	 * 
	 * @param schema
	 * @param orderNum
	 * @param inventoryID
	 * @return
	 */
	public List<String[]> queryPermitInventoryUsedInfo(String schema,
			String orderNum, String inventoryID) {
		db.resetSchema(schema);

		String query = "select id, lock_time, release_time, usage_type, permit_num from i_permit_inv_used "
				+ " where permit_inv_id = "
				+ inventoryID
				+ " and permit_num = " + "'" + orderNum + "'";

		logger.info("Execute query: " + query);
		String[] invUsedInfo = { "id", "lock_time", "release_time",
				"usage_type", "permit_num" };
		List<String[]> result = db.executeQuery(query, invUsedInfo);

		return result;
	}

	/**
	 * Get POS info for specific pos order
	 * 
	 * @param schema
	 * @param POS
	 *            order number
	 * 
	 * @return: POSInfo: ORD_NUM; ORD_INVC_ID
	 */
	// public POSInfo getPOSInfoFromDB(String schema, String ordNum) {
	// POSInfo posInf = new POSInfo();
	//
	// db.resetSchema(schema);
	// logger.info("Get POS info for POS Order#: " + ordNum + " in DB");
	// logger.debug("Reset schema as " + schema);
	//
	// String[] colNames = { "ord_num", "ord_invc_id" };
	//
	// String query = "select o.ord_num, o.ord_invc_id " + "from o_order o "
	// + "where o.ord_num = " + "'" + ordNum + "'";
	//
	// List<String[]> result = db.executeQuery(query, colNames);
	//
	// logger.debug("Execute query: " + query);
	// if (result != null && result.size() > 0) {
	// // Reservation
	// posInf.ordID = result.get(0)[0];
	// posInf.invoiceID = result.get(0)[1];
	// } else {
	// logger.error("Query returns nothing");
	// throw new ItemNotFoundException("No POS order Info Found.");
	// }
	//
	// return posInf;
	// }

	public String getPassNumberFromDB(String schema, String posOrderNum) {
		db.resetSchema(schema);
		String sql = "select cp.pass_number from o_order o, d_ref_cust_pass cp where o.id=cp.ord_id and o.ord_num = '"
				+ posOrderNum + "'";
		logger.info("SQL:" + sql);

		List<String> results = db.executeQuery(sql, "pass_number");
		if (results.size() < 1)
			throw new ItemNotFoundException(
					"Can't find any pass number when pos order number="
							+ posOrderNum);
		return results.get(0);
	}

	public List<String> getPassNumsFromDB(String schema, String posOrderNum,
			String posName) {
		db.resetSchema(schema);
		String sql = "select cp.pass_number from o_order o, d_ref_cust_pass cp, p_prd prd where o.id=cp.ord_id and cp.prd_id=prd.prd_id and o.ord_num = '"
				+ posOrderNum
				+ "' and prd.prd_name='"
				+ posName
				+ "' order by pass_number";
		logger.info("SQL:" + sql);

		List<String> results = db.executeQuery(sql, "pass_number");
		if (results.size() < 1)
			throw new ItemNotFoundException(
					"Can't find any pass number when pos order number="
							+ posOrderNum);
		return results;
	}

	public String getPassNumFromDB(String schema, String posOrderNum,
			String posName, int index) {
		return getPassNumsFromDB(schema, posOrderNum, posName).get(index);
	}

	public String getPassNumFromDB(String schema, String posOrderNum,
			String posName) {
		return getPassNumFromDB(schema, posOrderNum, posName, 0);
	}

	/**
	 * Get status of import gift card
	 * 
	 * @param environment
	 * @return
	 */
	public boolean getImportGiftCardStatus(String environment) {
		boolean isImport = false;

		db.resetSchema("QA_USER");
		logger.info("Get Import Gift Card Stauts form DB.");

		String searchValue = "val_" + environment;
		String[] colNames = { searchValue };
		String query = "select " + searchValue
				+ " from qa_automation where var = 'IS_IMPORT'";

		logger.info("execute query: " + query);
		List<String[]> result = db.executeQuery(query, colNames);

		if (result.size() == 0) {
			isImport = false;
		} else if (result.size() == 1) {
			isImport = true;
		}

		return isImport;
	}

	/**
	 * Insert into import gift card status
	 * 
	 * @param environment
	 */
	public void insertImportGiftCardStauts(String environment) {
		db.resetSchema("QA_USER");
		logger.info("Insert import gift card status");

		String query = "insert into qa_automation values ('IS_IMPORT','"
				+ environment + "')";

		logger.info("execute query: " + query);
		db.executeUpdate(query);

	}

	/**
	 * Insert into import gift card status
	 * 
	 * @param
	 */
	public void updateImportGiftCardStauts(String status) {
		db.resetSchema("QA_USER");
		logger.info("Insert import gift card status");

		String query = "update QA_AUTOMATIOn set  VARL_" + env + "='" + status
				+ "' VAR='IS_OMPORTANT'";
		logger.info("execute query: " + query);
		db.executeUpdate(query);
	}

	/**
	 * The method used to return gift card number by give gift card order number
	 * 
	 * @param schema
	 * @param giftCardOrd
	 * @return
	 */
	public String getGiftCardNumByOrdNum(String schema, String giftCardOrd) {
		logger.info("Query Gift Card Number By Gift Card order.");
		db.resetSchema(schema);
		String query = "select card_number from i_giftcard_inv where ord_id = (select id from o_order where ord_num = '"
				+ giftCardOrd + "')";

		logger.info("execute query: " + query);
		String result = CryptoUtil.decryptInfoInDB(db.executeQuery(query,
				"card_number", 0));

		return result;
	}

	/**
	 * Query product status from DB
	 * 
	 * @param schema
	 * @param prdID
	 * @return
	 */
	public boolean queryProductActiveStatus(String schema, String prdID) {
		boolean isActive = false;
		db.resetSchema(schema);
		logger.info("Query product status from DB.");

		String query = "select active_ind from p_prd where prd_id = " + prdID;

		logger.info("execute query: " + query);
		String result = db.executeQuery(query, "active_ind").get(0);

		if (result.equalsIgnoreCase("0")) {
			isActive = false;
		} else if (result.equalsIgnoreCase("1")) {
			isActive = true;
		}

		return isActive;
	}

	/**
	 * Update
	 * 
	 * @param schema
	 * @param prdID
	 * @param activeStatus
	 */
	public void updateProductActiveStatus(String schema, String activeStatus,
			String... prdID) {
		String activeIndex = "";
		db.resetSchema(schema);
		logger.info("Update product status from DB.");

		if (activeStatus.equalsIgnoreCase("Active")) {
			activeIndex = "1";
		} else if (activeStatus.equalsIgnoreCase("Inactive")) {
			activeIndex = "0";
		}
		String query = "update p_prd set active_ind = " + activeIndex
				+ " where prd_id in( " + StringUtil.arrayToString(prdID, false)
				+ ")";

		logger.info("execute query: " + query);
		db.executeUpdate(query);
	}

	/**
	 * Get payment type and amount through reciept number
	 * 
	 * @param schema
	 * @param recieptNum
	 * @return
	 */
	public List<String[]> getPaymentTypeAndAmountByRecieptNum(String schema,
			String recieptNum) {
		String[] colNames = { "dscr", "pmt_amnt", "status" };
		db.resetSchema(schema);
		logger.info("Get payment type and amount by recipient number.");

		String query = "select f_pmt_type.dscr, f_pmt.pmt_amnt, f_pmt.status "
				+ "from o_rcpt,o_rcpt_pmt,f_pmt,f_pmt_type_loc,f_pmt_type "
				+ "where o_rcpt.id =o_rcpt_pmt.rcpt_id "
				+ "and o_rcpt_pmt.pmt_id = f_pmt.id "
				+ "and f_pmt.pmt_type_loc_id = f_pmt_type_loc.id "
				+ "and f_pmt_type_loc.pmt_type_id = f_pmt_type.id "
				+ "and o_rcpt.rcpt_no = '" + recieptNum + "'";

		logger.info("execute query: " + query);
		List<String[]> result = db.executeQuery(query, colNames);

		return result;
	}

	/**
	 * This methods used to check the privilege is existed or not
	 * 
	 * @param code
	 * @param schema
	 * @return
	 */
	public boolean checkPrivilegeExisted(String code, String schema) {
		db.resetSchema(schema);
		logger.debug("check privielge whether is existed.");

		String query = "select PRD_ID from p_prd where prd_cd='" + code
				+ "' and product_cat_id=10";
		List<String> result = db.executeQuery(query, "PRD_ID");
		if (result.size() > 0 && result.get(0) != null) {
			return true;
		}

		return false;
	}

	public boolean checkConsumableExisted(String code, String schema) {
		db.resetSchema(schema);
		logger.debug("check Consumable whether is existed.");

		String query = "select PRD_ID from p_prd where prd_cd='" + code
				+ "' and product_cat_id=4";
		List<String> result = db.executeQuery(query, "PRD_ID");
		if (result.size() > 0 && result.get(0) != null) {
			return true;
		}

		return false;
	}

	/**
	 * Get description of display category from DB, and sorted by display order
	 * (asc)
	 * 
	 * @param categoryType
	 *            :
	 * @param schema
	 * @return
	 */
	public List<String> getDescriptionOfDisplayCategory(String categoryType,
			String schema) {
		db.resetSchema(schema);
		logger.debug("Get descirption of display category.");

		String typeID = "";
		if (categoryType.equalsIgnoreCase("Display Category")) {
			typeID = "1";
		} else if (categoryType.equalsIgnoreCase("Display Sub Category")) {
			typeID = "2";
		} else if (categoryType.equalsIgnoreCase("Report Category")) {
			typeID = "3";
		}

		String query = "select description from p_display_category where type_id = "
				+ typeID + " order by display_order";
		List<String> result = db.executeQuery(query, "description");

		return result;
	}

	/**
	 * Get display category ID per category type id and description
	 * 
	 * @param schema
	 * @param categoryTypeID
	 * @param description
	 * @return
	 */
	public String getDisplayCategoryID(String schema, String categoryTypeID,
			String description) {
		db.resetSchema(schema);
		String query = "select ID from p_display_category where type_id = "
				+ categoryTypeID + " and description='" + description + "'";
		logger.info("Execute sql: " + query);
		String result = db.executeQuery(query, "ID", 0);
		logger.info("Display Category with description: " + description
				+ " ID is " + result);
		return result;
	}

	/**
	 * Get privilege inventory types which sorted by asc
	 * 
	 * @param revenueLocation
	 * @param schema
	 * @return
	 */
	public List<String> getPrivilegeInventoryTypesSortedByAsc(
			String revenueLocation, String schema) {
		db.resetSchema(schema);
		logger.debug("Get privilege inventory types.");

		String query = "select p_hf_inv_type.code from p_hf_inv_type, d_loc "
				+ "where p_hf_inv_type.active_ind = 1 and p_hf_inv_type.rev_loc_id = d_loc.id and d_loc.name = '"
				+ revenueLocation + "' " + "order by upper(code)";

		List<String> result = db.executeQuery(query, "code");

		return result;
	}

	/**
	 * Get privilege product create info
	 * 
	 * @param privilegeCode
	 * @param schema
	 * @return privilege crate info :privilegeID, create user name,
	 *         create/revenue location name, create date
	 */
	public List<String[]> getPrivilegeProductCreateInfo(String privilegeCode,
			String schema) {
		db.resetSchema(schema);
		logger.debug("Get privilege product create info.");
		String[] colNames = { "prd_id", "userName", "locationName",
				"createDate" };
		String query = " select p_prd.prd_id, x_user.name userName, d_loc.name locationName, to_char(p_prd.create_date, 'yyyyMMdd') createDate "
				+ "from p_prd, x_user, d_loc "
				+ "where p_prd.create_user_id = x_user.id and p_prd.create_loc_id = d_loc.id and p_prd.rev_loc_id = d_loc.id "
				+ "and p_prd.rev_loc_id = p_prd.create_loc_id and p_prd.prd_cd = '"
				+ privilegeCode + "'";
		List<String[]> result = db.executeQuery(query, colNames);
		return result;
	}

	/**
	 * Get Deposit adjustment of EFT job from DB table
	 * F_DEFT/D_STORE/D_LOC/F_ACCT
	 * 
	 * @param schema
	 * @param deposite
	 *            id
	 * @return record list
	 * 
	 */
	public List<HashMap<String, String>> getEFTDepositAdjustmentFromDB(
			String schema, String depositID) {
		db.resetSchema(schema);
		logger.debug("Get Deposit adjustment of EFT job from DB table F_DEFT/D_STORE/D_LOC/F_ACCT.");

		String query = "SELECT F_DEFT.ID AS eft_id,   "
				+ "F_DEFT.DEP_ADJ_ID,   " + "F_DEFT.dep_id,  "
				+ "F_DEFT.STORE_EFTADJ_ID,  " + "F_DEFT.DEFT_JOB_ID,  "
				+ "F_DEFT.PMT_ID,  " + "F_DEFT.store_id,  "
				+ "f_deft.JRNL_DATE as create_date,  "
				+ "f_deft.eft_schdl_id,  " + "f_deft.eft_invoice_id,  "
				+ "f_deft.eft_remittance_id,  " + "f_deft.amount,  "
				+ "D_STORE.ID   AS agent_id,  "
				+ "D_STORE.name AS agent_name,  " + "D_LOC.id AS rev_loc_id,  "
				+ "D_LOC.name  AS rev_loc_name,  "
				+ "F_ACCT.CD    AS acct_CD,  " + "F_ACCT.dscr  AS acct_dscr "
				+ "FROM F_DEFT "
				+ "LEFT OUTER JOIN D_STORE ON F_DEFT.STORE_ID=D_STORE.ID "
				+ "LEFT OUTER JOIN D_LOC ON F_DEFT.REV_LOC_ID=D_LOC.ID "
				+ "LEFT OUTER JOIN F_ACCT ON F_DEFT.ACCNT_ID  =F_ACCT.ID "
				+ "WHERE F_DEFT.dep_id = " + depositID + " order by  F_DEFT.ID";

		logger.info("Query: " + query);
		return db.executeQuery(query);

	}

	/**
	 * Get Payment of EFT job from DB table F_DEFT/O_ORDER/F_ACCT
	 * 
	 * @param schema
	 * @param order
	 *            number
	 * @return record list
	 * 
	 */
	public List<HashMap<String, String>> getEFTPaymentFromDB(String schema,
			String ordNum) {
		db.resetSchema(schema);

		logger.debug("Get Payment of EFT job from DB table F_DEFT/D_STORE/F_ACCT.");

		String query = "SELECT F_DEFT.ID as EFT_ID, "
				+ "F_DEFT.DEFT_JOB_ID,"
				+ "F_DEFT.PMT_ID,"
				+ "F_DEFT.PMT_ALLOC_ID,"
				+ "F_DEFT.ORD_ID,"
				+ "F_DEFT.ORD_ITEM_ID,"
				+ "F_DEFT.REV_LOC_ID,"
				+ "D_LOC.name as rev_loc_name, "
				+ "F_DEFT.FEE_TRAN_ID,"
				+ "F_DEFT.TRAN_ID,"
				+ "F_DEFT.LOC_ID,"
				+ "F_DEFT.AMOUNT,"
				+ "F_DEFT.EFT_SCHDL_ID,"
				+ "F_DEFT.EFT_INVOICE_JOB_ID,"
				+ "F_DEFT.EFT_INVOICE_ID,"
				+ "F_DEFT.EFT_REMITTANCE_ID,"
				+ "F_DEFT.VENDOR_ID,"
				+ "F_DEFT.ORD_NUM,"
				+ "F_DEFT.JRNL_DATE as CREATE_DATE,"
				+ "F_ACCT.CD as acct_CD, "
				+ "F_ACCT.dscr as acct_dscr, "
				+ "D_STORE.ID   AS agent_id,  "
				+ "D_STORE.name AS agent_name,  "
				+ " CB_1.CB_NAME AS FEE_TYPE  "
				+ "FROM F_DEFT LEFT OUTER JOIN D_STORE    ON F_DEFT.STORE_ID=D_STORE.ID   "
				+ "LEFT OUTER JOIN F_ACCT ON F_DEFT.ACCNT_ID=F_ACCT.ID  "
				+ "LEFT OUTER JOIN D_LOC    ON F_DEFT.REV_LOC_ID=D_LOC.ID   "
				+ "left outer join D_REF_CB_DICTIONARY  CB_1 on F_DEFT.fee_type = CB_1.CB_ID  and CB_1.CB_BASE_CLASS='com.reserveamerica.common.data.reference.configurable.FeeType'"
				+ "WHERE F_DEFT.PMT_ALLOC_ID >0 AND EFT_INVOICE_ID>0 AND EFT_REMITTANCE_ID >0 AND F_DEFT.ORD_NUM        ='"
				+ ordNum + "' ORDER BY F_DEFT.ID";

		logger.info("Query: " + query);

		return db.executeQuery(query);

	}

	/**
	 * Get EFT invoice info from DB table
	 * 
	 * @param schema
	 * @param invoice
	 *            number
	 * @return record list
	 * 
	 */
	public List<HashMap<String, String>> getEFTInvoiceFromDB(String schema,
			String invoiceNum) {
		db.resetSchema(schema);

		logger.debug("Get EFT invoice record from DB table .");

		String query = "SELECT "
				+ "F_EFT_INVOICE.ID, "
				+ "F_EFT_INVOICE.EFT_INVOICE_JOB_ID, "
				+ "DECODE(F_EFT_INVOICE.INV_TYPE,2, 'EFT','Not EFT')AS INV_TYPE, "
				+ "DECODE(F_EFT_INVOICE.INVC_STATUS, 1, 'Pending',2,'Sent','others') AS INVC_STATUS,  "
				+ "DECODE(F_EFT_INVC_TRANSM.STATUS, 1,'Pending', 'OTHERS') AS TRANS_STATUS, "
				+ "(D_VENDOR.NAME||' - '||D_VENDOR.VENDOR_NUM) AS VENDOR, "
				+ "D_VENDOR.NAME AS VENDOR_NAME, "
				+ "D_VENDOR.VENDOR_NUM AS VENDOR_NUM, "
				+ "(F_EFT_INVOICE.STORE_ID||'-'||D_STORE.NAME) AS AGENT, "
				+ "F_EFT_INVOICE.STORE_ID AS AGENT_ID, "
				+ "D_STORE.NAME AS AGENT_NAME, "
				+ "F_EFT_INVOICE.INV_GROUP_NAME, "
				+ "F_EFT_INVOICE.INVC_DATE, "
				+ "F_EFT_INVOICE.PERIOD_DATE, "
				+ "F_EFT_INVOICE.AMOUNT "
				+ "FROM F_EFT_INVOICE "
				+ "LEFT OUTER JOIN F_EFT_INVC_TRANSM ON F_EFT_INVOICE.ID = F_EFT_INVC_TRANSM.EFT_INVOICE_ID "
				+ "LEFT OUTER JOIN D_VENDOR ON D_VENDOR.ID = F_EFT_INVOICE.VENDOR_ID "
				+ "LEFT OUTER JOIN D_STORE ON F_EFT_INVOICE.STORE_ID=D_STORE.ID  "
				+ "WHERE F_EFT_INVOICE.ID=" + invoiceNum;
		logger.info("Query: " + query);

		return db.executeQuery(query);

	}

	/**
	 * Get EFT invoice Job info from DB table
	 * 
	 * @param schema
	 * @param invoice
	 *            job id
	 * @return record list
	 * 
	 */
	public List<HashMap<String, String>> getEFTInvoiceJobFromDB(String schema,
			String jobID) {
		db.resetSchema(schema);

		logger.debug("Get EFT invoice job record from DB table .");

		String query = "SELECT F_EFT_INVOICE_JOB.ID,     "
				+ "F_EFT_INVOICE_JOB.EFT_SCHDL_ID,     "
				+ "F_EFT_INVOICE_JOB.END_DATE,    "
				+ "F_EFT_INVOICE_JOB.ERROR_MSG,    "
				+ "F_EFT_INVOICE_JOB.EXEC_LOC_ID,    "
				+ "F_EFT_INVOICE_JOB.INV_DATE as INVOICE_DATE,    "
				+ "F_EFT_INVOICE_JOB.INVOICE_COUNT,    "
				+ "F_EFT_INVOICE_JOB.LOC_ID,    "
				+ "D_LOC.NAME AS LOC_NAME,        "
				+ "F_EFT_INVOICE_JOB.PERIOD_DATE,    "
				+ "F_EFT_INVOICE_JOB.REMITANCE_COUNT,   "
				+ "F_EFT_INVOICE_JOB.START_DATE,    "
				+ "DECODE(F_EFT_INVOICE_JOB.STATUS,2, 'Completed','Others')AS STATUS,    "
				+ "F_EFT_INVOICE_JOB.STORE_ID,    "
				+ "F_EFT_INVOICE_JOB.USER_ID,    "
				+ "DECODE(F_EFT_SCHEDULE.EFT_FREQUENCY,1, 'Daily','Others')AS FREQUENCY  "
				+ "FROM F_EFT_INVOICE_JOB   "
				+ "LEFT OUTER JOIN D_LOC    ON F_EFT_INVOICE_JOB.LOC_ID=D_LOC.ID     "
				+ "LEFT OUTER JOIN F_EFT_SCHEDULE    ON F_EFT_SCHEDULE.ID=F_EFT_INVOICE_JOB.EFT_SCHDL_ID     "
				+ "WHERE F_EFT_INVOICE_JOB.ID=" + jobID + "  "
				+ "ORDER BY F_EFT_INVOICE_JOB.ID DESC NULLS LAST";
		logger.info("Query: " + query);

		return db.executeQuery(query);

	}

	/**
	 * Get EFT remmitence info from DB table
	 * 
	 * @param schema
	 * @param remmitence
	 *            number
	 * @return record list
	 * 
	 */
	public List<HashMap<String, String>> getEFTRemmitanceFromDB(String schema,
			String remitNum) {
		db.resetSchema(schema);

		logger.debug("Get EFT Remmitance record from DB table .");

		String query = "SELECT "
				+ "F_EFT_REMITTANCE.ID, "
				+ "F_EFT_REMITTANCE.EFT_INVOICE_JOB_ID, "
				+ "DECODE(F_EFT_REMITT_TRANSM.STATUS, 1,'Pending','OTHERS') AS TRANS_STATUS, "
				+ "DECODE(F_EFT_REMITTANCE.STATUS, 1,'Pending', 2, 'Sent', 3,'Cleared',4,'Failed', 'OTHERS') AS REMIT_STATUS, "
				+ "(D_VENDOR.NAME||'-'||D_VENDOR.VENDOR_NUM) AS VENDOR, "
				+ "(F_EFT_REMITTANCE.STORE_ID||'-'||D_STORE.NAME) AS AGENT, "
				+ "F_EFT_REMITTANCE.STORE_ID AS AGENT_ID, "
				+ "D_STORE.NAME AS AGENT_NAME, "
				+ "F_EFT_REMITTANCE.REMITTANCE_ADJ_TYPE_ID, "
				+ "F_EFT_REMITTANCE.REMIT_DATE, "
				+ "F_EFT_REMITTANCE.PERIOD_DATE, "
				+ "F_EFT_REMITTANCE.AMOUNT, "
				+ "F_EFT_REMITTANCE.EFT_INVOICE_ID, "
				+ "F_ACCT.CD as acct_CD, "
				+ "F_ACCT.dscr as acct_dscr "
				+ "FROM F_EFT_REMITTANCE "
				+ "LEFT OUTER JOIN F_EFT_REMITT_TRANSM ON F_EFT_REMITTANCE.ID = F_EFT_REMITT_TRANSM.EFT_REMITTANCE_ID "
				+ "LEFT OUTER JOIN D_VENDOR ON D_VENDOR.ID = F_EFT_REMITTANCE.VENDOR_ID "
				+ "LEFT OUTER JOIN D_STORE ON F_EFT_REMITTANCE.STORE_ID=D_STORE.ID  "
				+ "LEFT OUTER JOIN F_ACCT    ON F_EFT_REMITTANCE.ACCNT_ID=F_ACCT.ID   "
				+ "WHERE F_EFT_REMITTANCE.ID=" + remitNum;
		logger.info("Query: " + query);

		return db.executeQuery(query);

	}

	/**
	 * Get Store adjustment of EFT job from DB table F_DEFT/D_LOC/F_ACCT
	 * 
	 * @param schema
	 * @param store
	 *            adjustment id
	 * @return record list
	 * 
	 */
	public List<HashMap<String, String>> getStoreEFTAdjustmentFromDB(
			String schema, String adjustID) {
		db.resetSchema(schema);

		logger.debug("Get Store adjustment of EFT job from DB table F_DEFT/D_LOC/F_ACCT.");

		String query = "SELECT F_DEFT.ID as eft_id, "
				+ "F_DEFT.STORE_EFTADJ_ID, "
				+ "F_DEFT.DEFT_JOB_ID, "
				+ "F_DEFT.PMT_ID, "
				+ "F_DEFT.store_id, "
				+ "F_DEFT.REV_LOC_ID, "
				+ "f_deft.JRNL_DATE as create_date, "
				+ "f_deft.eft_schdl_id, "
				+ "f_deft.eft_invoice_id,"
				+ "f_deft.eft_remittance_id, "
				+ "f_deft.amount, "
				+ "D_STORE.ID as agent_id, "
				+ "D_STORE.name as agent_name, "
				+ "D_LOC.name as rev_loc_name, "
				+ "F_ACCT.CD as acct_CD, "
				+ "F_ACCT.dscr as acct_dscr "
				+ "FROM F_DEFT    LEFT OUTER JOIN D_STORE    ON F_DEFT.STORE_ID=D_STORE.ID   "
				+ "LEFT OUTER JOIN D_LOC    ON F_DEFT.REV_LOC_ID=D_LOC.ID   "
				+ "LEFT OUTER JOIN F_ACCT    ON F_DEFT.ACCNT_ID=F_ACCT.ID   "
				+ "WHERE F_DEFT.STORE_EFTADJ_ID=" + adjustID
				+ "   ORDER BY F_DEFT.ID";

		logger.info("Query: " + query);
		return db.executeQuery(query);

	}

	/**
	 * Get Voucher record of EFT job from DB table
	 * 
	 * @param schema
	 * @param voucher
	 *            id
	 * @return record list
	 * 
	 */
	public List<HashMap<String, String>> getEFTVoucherRecordFromDB(
			String schema, String voucherID) {
		db.resetSchema(schema);

		logger.debug("Get Voucher Records of EFT job from DB table F_DEFT/D_STORE/D_LOC/F_ACCT/D_REF_CB_DICTIONARY.");

		String query = "SELECT F_DEFT.id as eft_id, "
				+ "F_DEFT.deft_job_id, "
				+ "F_DEFT.VA_ALLOC_ID, "
				+ // for voucher
				"F_DEFT.voucher_id, "
				+ // for voucher
				"F_DEFT.rev_loc_id, "
				+ "D_LOC.name as rev_loc_name, "
				+ "F_ACCT.CD as acct_cd, "
				+ "F_ACCT.dscr as acct_dscr,"
				+ "F_DEFT.tran_id, "
				+ "CB_1.CB_NAME as tran_name,"
				+ "F_DEFT.loc_id as tran_loc_id, "
				+ "D_STORE.name as tran_loc_name,"
				+ "CB_2.CB_NAME as EFT_TYPE, "
				+ "F_DEFT.JRNL_DATE as CREATE_DATE,"
				+ "F_DEFT.EFT_INVOICE_ID,"
				+ "F_DEFT.EFT_REMITTANCE_ID, "
				+ "F_DEFT.AMOUNT "
				+ "FROM F_DEFT  "
				+ "LEFT OUTER JOIN D_STORE  ON F_DEFT.STORE_ID=D_STORE.ID "
				+ "LEFT OUTER JOIN D_LOC  ON F_DEFT.REV_LOC_ID=D_LOC.ID "
				+ "LEFT OUTER JOIN F_ACCT  ON F_DEFT.ACCNT_ID=F_ACCT.ID "
				+ "left outer join D_REF_CB_DICTIONARY  CB_1 on F_DEFT.tran_type_id = CB_1.CB_ID  and CB_1.CB_BASE_CLASS='com.reserveamerica.common.data.reference.configurable.TransactionType' "
				+ "left outer join D_REF_CB_DICTIONARY  CB_2 on F_DEFT.DEFT_TYPE_ID = CB_2.CB_ID and CB_2.CB_BASE_CLASS='com.reserveamerica.system.finance.eft.configurable.DailyEFTType'   "
				+ "WHERE F_DEFT.VOUCHER_ID ="
				+ voucherID
				+ "  "
				+ "AND F_DEFT.VA_ALLOC_ID   >0  "
				+ "AND (F_DEFT.PMT_ALLOC_ID IS NULL OR F_DEFT.PMT_ALLOC_ID    =0) "
				+ "and F_DEFT.amount>0 ORDER BY F_DEFT.ID";

		logger.info("Query: " + query);
		return db.executeQuery(query);

	}

	/**
	 * Get Internal GiftCard records of EFT job from DB table
	 * 
	 * @param schema
	 * @param GiftCard
	 *            product name
	 * @return record list
	 * 
	 */
	public List<HashMap<String, String>> getEFTGCRecordFromDB(String schema,
			String giftCardPrdName) {
		db.resetSchema(schema);

		logger.debug("Get top 10 GiftCard Records of EFT job from DB table F_DEFT/D_STORE/D_LOC/F_ACCT/D_REF_CB_DICTIONARY/P_PRD.");

		String query = "select * from "
				+ "(SELECT F_DEFT.id AS eft_id,  "
				+ "F_DEFT.deft_job_id,  "
				+ "F_DEFT.GC_ALLOC_ID,  "
				+ // for gift card
				"O_ORDER.ORD_NUM,  "
				+ // for gift card
				"F_DEFT.rev_loc_id,  "
				+ "LOC_1.name  AS rev_loc_name,  "
				+ "F_ACCT.CD   AS acct_cd,  "
				+ "F_ACCT.dscr AS acct_dscr,  "
				+ "F_DEFT.tran_id,  "
				+ "CB_1.CB_NAME  AS tran_name,  "
				+ "F_DEFT.loc_id AS tran_loc_id,  "
				+ "LOC_2.NAME  AS tran_loc_name,  "
				+ "CB_2.CB_NAME  AS EFT_TYPE,  "
				+ "F_DEFT.JRNL_DATE as CREATE_DATE,  "
				+ "F_DEFT.EFT_INVOICE_ID,  "
				+ "F_DEFT.EFT_REMITTANCE_ID,  "
				+ "F_DEFT.AMOUNT "
				+ "FROM F_DEFT "
				+ "LEFT OUTER JOIN D_STORE ON F_DEFT.STORE_ID=D_STORE.ID "
				+ "LEFT OUTER JOIN D_LOC LOC_1 ON F_DEFT.REV_LOC_ID=LOC_1.ID "
				+ "LEFT OUTER JOIN D_LOC LOC_2 ON F_DEFT.LOC_ID=LOC_2.ID "
				+ "LEFT OUTER JOIN F_ACCT ON F_DEFT.ACCNT_ID=F_ACCT.ID "
				+ "LEFT OUTER JOIN D_REF_CB_DICTIONARY CB_1 ON F_DEFT.tran_type_id = CB_1.CB_ID AND CB_1.CB_BASE_CLASS ='com.reserveamerica.common.data.reference.configurable.TransactionType' "
				+ "LEFT OUTER JOIN D_REF_CB_DICTIONARY CB_2 ON F_DEFT.DEFT_TYPE_ID = CB_2.CB_ID AND CB_2.CB_BASE_CLASS    ='com.reserveamerica.system.finance.eft.configurable.DailyEFTType' "
				+ "LEFT OUTER JOIN P_PRD  ON F_DEFT.PRD_ID=P_PRD.PRD_ID AND P_PRD.PRD_NAME='"
				+ giftCardPrdName
				+ "' "
				+ " LEFT OUTER JOIN O_ORDER ON F_DEFT.GC_ORDER_ID=O_ORDER.ID "
				+ "WHERE  F_DEFT.GC_ALLOC_ID  >0 AND (F_DEFT.PMT_ALLOC_ID IS NULL OR F_DEFT.PMT_ALLOC_ID  =0) "
				+ "AND F_DEFT.amount         >0 " + "ORDER BY F_DEFT.ID DESC) "
				+ "where rownum<=10 ";

		return db.executeQuery(query);

	}

	/**
	 * set upper case configuration
	 * 
	 * @param schema
	 * @param true/false
	 * 
	 */
	// update by pzhu, in order to persist change config to UPPERCASE in MS
	// Contract
	public void updateUpperCaseConfigInDB(String schema, boolean status) {
		db.resetSchema(schema);
		logger.debug("Update configuration in table 'X_PROP'");
		String sql = "update X_PROP set value='true' where name like 'UppercaseOutput'";
		logger.info("Update: " + sql);
		int affectedRecord = db.executeUpdate(sql);

		logger.info("Total " + affectedRecord
				+ " record(s) in table X_PROP updated.");

	}

	public void checkUpperCaseConfigInDB(String schema, boolean status) {
		db.resetSchema(schema);
		logger.debug("Make sure the status of UppercaseOutpu has been set to status:"
				+ status + " in table 'X_PROP'");
		String sql = "select name from X_PROP where name like 'UppercaseOutput' and value='"
				+ status + "'";
		logger.info("Run sql: " + sql);
		List<String> name = db.executeQuery(sql, "NAME");
		if (name.size() < 1) {
			throw new ErrorOnDataException("No any x_prop records found.");
		}
	}

	/**
	 * Get Agent ID by Agent Name
	 * 
	 * @param schema
	 * @param agent
	 *            name
	 * @return Agent ID
	 * 
	 */
	public String getAgentIDByNameFromDB(String schema, String name) {
		logger.info("Get Agent ID of Agent-->" + name);
		db.resetSchema(schema);

		logger.info("Changed schema to -->>" + schema);

		String sql = "select id from d_store where name='" + name + "'";
		String id = db.executeQuery(sql, "id").get(0);
		logger.info("Agent id of " + name + " is " + id);

		return id;

	}

	public List<String> getAgentNameByStatus(String schema, int status) {
		logger.info("Get All Agent Name which agent Status is " + status);
		db.resetSchema(schema);

		logger.info("Changed schema to -->>" + schema);

		String sql = "select name from d_store where status=" + status;
		List<String> names = db.executeQuery(sql, "name");

		return names;
	}

	/**
	 * Get vendor status
	 * 
	 * @param schema
	 * @param vendorNum
	 * @return the status of the vendor
	 */
	public List<String> getVendorStatus(String schema, String vendorNum) {
		db.resetSchema(schema);
		logger.info("Get the Vendor status.");
		String colName = "status";
		String query = "select status from d_vendor where vendor_num= '"
				+ vendorNum + "'";
		List<String> result = db.executeQuery(query, colName);

		return result;
	}

	/**
	 * Get vendor ID by vendor name and vendor number
	 * 
	 * @param schema
	 * @param vendorNum
	 * @param vendorName
	 * @return
	 */
	public String getVendorID(String schema, String vendorNum, String vendorName) {
		db.resetSchema(schema);
		logger.info("Get the Vendor ID.");

		String colName = "id";
		String query = "select id from d_vendor where vendor_num= '"
				+ vendorNum + "'";
		if (StringUtil.notEmpty(vendorName)) {
			query += " and name = '" + vendorName + "'";
		}
		String id = db.executeQuery(query, colName).get(0);
		logger.info("Vendor name = " + vendorName + ", vendor number = "
				+ vendorNum + ", the vendor id is " + id);

		return id;
	}

	public List<String> getAllAgentIDInSpecificVendor(String schema,
			String vendorID, String status) {
		db.resetSchema(schema);
		logger.info("Get all agent in specific vendor, Vendor ID = " + vendorID);

		String query = "select id from d_store where vendor_id = " + vendorID;
		if (StringUtil.notEmpty(status)) {
			if (status.equalsIgnoreCase(Orms.ACTIVE_STATUS)) {
				status = "1";
			} else if (status.equalsIgnoreCase(Orms.INACTIVE_STATUS)) {
				status = "0";
			} else {
				// if need another status please update related script
				throw new ErrorOnDataException(
						"The status not expect, please check.");
			}
			query = query + " and status = " + status;
		}

		query = query + " order by id";
		List<String> ids = db.executeQuery(query, "ID");

		return ids;
	}

	/**
	 * Get PreCheckinPeriod value for 'Pre-Registration' in WEB
	 * 
	 * @param schema
	 * @return
	 */
	public String getPreCheckinPeriod(String schema) {
		db.resetSchema(schema);
		logger.info("Get the value of 'PreCheckinPeriod'.");

		String query = "select Value from x_prop where name like 'PreCheckinPeriod'";
		return db.executeQuery(query, "Value", 0);
	}

	/**
	 * update PreCheckinPeriod value for 'Pre-Registration' in WEB
	 * 
	 * @param schema
	 * @param expectValue
	 * @return
	 */
	public void updatePreCheckinPeriod(String schema, String expectValue) {
		db.resetSchema(schema);
		logger.info("update the value of 'PreCheckinPeriod'.");

		String query = "update x_prop set value = " + expectValue
				+ " where name like 'PreCheckinPeriod'";
		db.executeUpdate(query);
	}

	/**
	 * Get PreCheckinAllowedWithDiscountProof value for 'Pre-Registration' in
	 * WEB
	 * 
	 * @param schema
	 * @return
	 */
	public String getPreCheckinAllowedWithDiscountProof(String schema) {
		db.resetSchema(schema);
		logger.info("Get the value of 'PreCheckinAllowedWithDiscountProof'.");

		String query = "select Value from x_prop where name like 'PreCheckinAllowedWithDiscountProof'";
		return db.executeQuery(query, "Value", 0);
	}

	/**
	 * Update PreCheckinAllowedWithDiscountProof value for 'Pre-Registration' in
	 * WEB
	 * 
	 * @param schema
	 * @param expectValue
	 * @return
	 */
	public void updatePreCheckinAllowedWithDiscountProof(String schema,
			String expectValue) {
		db.resetSchema(schema);
		logger.info("Update the value of 'PreCheckinAllowedWithDiscountProof'.");

		String query = "update x_prop set value = '" + expectValue
				+ "' where name like 'PreCheckinAllowedWithDiscountProof'";
		db.executeUpdate(query);
	}

	/**
	 * Update Pre Check In Status ID from DB
	 * 
	 * @param schema
	 * @param resNum
	 * @param statusID
	 *            : 1, 0
	 */
	public void updatePreCheckInStatusID(String schema, String resNum,
			String statusID) {
		db.resetSchema(schema);
		logger.info("Update Pre Check In Status ID from DB.");

		String query = "update o_order set precheckin_status_id="
				+ statusID
				+ ", "
				+ "CONCURRENCY_VERSION_NUM=CONCURRENCY_VERSION_NUM+1 where ord_num='"
				+ resNum + "'";

		db.executeUpdate(query);
	}

	/**
	 * Update Pre Check In Status ID from DB
	 * 
	 * @param schema
	 * @param resNum
	 * @param resWithDiscount
	 *            : true: reservation has discount fee type false: reervation
	 *            hasn't discount fee type
	 */
	public void updatePreCheckInStatusID(String schema, String resNum,
			String arrivalDate, boolean resWithDiscount) {
		String value = StringUtil.EMPTY;
		int preCheckInPeriod = Integer.parseInt(this.getPreCheckinPeriod(schema));
		arrivalDate = DateFunctions.formatDate(arrivalDate.split(" ")[0],"M/d/yyyy");

		//Has discount
		if(resWithDiscount){
			if(this.getPreCheckinAllowedWithDiscountProof(schema).equals("true")){
				if (DateFunctions.compareDates(arrivalDate, DateFunctions.getDateAfterToday(preCheckInPeriod)) <= 0) {
					value = "1";
				} else {
					value = "0";
				}
			}else value = "0";
			//Doesn't have discount
		}else {
			if(this.getPreCheckinAllowedWithDiscountProof(schema).equals("false")){
				if (DateFunctions.compareDates(arrivalDate, DateFunctions.getDateAfterToday(preCheckInPeriod)) <= 0) {
					value = "1";
				} else {
					value = "0";
				}
			}else value = "0";
		}
		
		updatePreCheckInStatusID(schema, resNum, value);
		logger.info("Successfully update PreCheckInStatusID to " + value
				+ " with resNum = " + resNum + " and arrival date = "
				+ arrivalDate + ".");
	}

	/**
	 * Get receipt numbers which were created on current Store Manager and
	 * create day between dateBegin and dateEnd
	 * 
	 * @param schema
	 * @param dateBegin
	 * @param dateEnd
	 * @param prefix
	 * @return
	 */
	public String getReceiptNum(String schema, String dateBegin,
			String dateEnd, String prefix) {
		String rcptNum;
		String colNames = "rcpt_no";
		db.resetSchema(schema);

		logger.debug("Query Receipt Number From DB");

		String query = "select o_rcpt.rcpt_no from o_rcpt,o_rcpt_ord,o_order,o_rcpt_order_item "
				+ "where o_rcpt.id=o_rcpt_ord.rcpt_id "
				+ "and o_order.id=o_rcpt_ord.order_id "
				+ "and o_rcpt_order_item.rcpt_id=o_rcpt_ord.rcpt_id "
				+ "and o_rcpt.rcpt_no like '"
				+ prefix
				+ "%' "
				+ "and o_order.ord_status_id="
				+ ORD_STATUS_ACTIVE
				+ " "
				+ "and o_rcpt_order_item.quantity>1 "
				+ "and o_order.ord_date between '"
				+ dateBegin
				+ "' and '"
				+ dateEnd + "' " + "order by o_rcpt.rcpt_no DESC";

		List<String> rcptNums = db.executeQuery(query, colNames);

		if (rcptNums != null && rcptNums.size() > 0) {
			rcptNum = rcptNums.get(0);
		} else {
			throw new ItemNotFoundException("No Receipt Numbers Info Found.");
		}

		return rcptNum;
	}

	public ReceiptInfo getReceiptInfoFromDB(String schema, String receiptNum) {
		db.resetSchema(schema);
		
		String query = "select to_char(CREATE_DATE, 'yyyy/MM/dd') as CREATE_DATE, LOC_ID, CUST_ID, PRICE, IS_ORDER_CONFIRMED from O_RCPT where ID = " + receiptNum;
		logger.info("Execute query: " + query);
		List<String[]> results = db.executeQuery(query, new String[] {"CREATE_DATE", "LOC_ID", "CUST_ID", "PRICE", "IS_ORDER_CONFIRMED"});
		if(results.size() < 1) throw new ErrorOnDataException("Cannot find Receipt info from DB by id: " + receiptNum);
		String infos[] = results.get(0);
		
		ReceiptInfo receipt = new ReceiptInfo();
		receipt.id = receiptNum;
		receipt.receiptDateAndTime = infos[0];
		receipt.locationId = infos[1];
		receipt.custId = infos[2];
		receipt.price = Double.parseDouble(infos[3]);
		receipt.isOrderConfirmed = infos[4];
		
		return receipt;
	}
	
	public String getReceiptNumByOrderNum(String schema, String ordNum) {
		List<String> nums = getReceiptNumsByOrderNum(schema, ordNum);
		if (nums.size() > 0) {
			return nums.get(nums.size() - 1);
		}

		return null;
	}

	public List<String> getReceiptNumsByOrderNum(String schema, String ordNum) {
		db.resetSchema(schema);

		String query = "select rcpt_id from o_rcpt_ord where order_id = "
				+ "(select id from o_order where ord_num = '" + ordNum
				+ "') order by rcpt_id";
		logger.info(query);
		List<String> result = db.executeQuery(query, "rcpt_id");
		return result;
	}

	public String getDocumentNumByOrderNumber(String schema, String orderNum) {
		db.resetSchema(schema);
		String query = "select document_num from O_DOCUMENT_INST where ord_id in (select id from o_order where ord_num='"
				+ orderNum + "')";
		logger.info(query);
		List<String> result = db.executeQuery(query, "DOCUMENT_NUM");
		if (result == null || result.size() < 1) {
			throw new ErrorOnPageException(
					"No document number is found when order number is "
							+ orderNum);
		}
		return result.get(0);
	}

	/**
	 * Delte specific privilege
	 * 
	 * @param schema
	 * @param privilegeCode
	 */
	public void deletePrivilegeInDB(String schema, String privilegeCode) {
		db.resetSchema(schema);
		logger.info("Update deleted_ind =1 for privilege from DB.");

		String query = "update p_prd set deleted_ind = 1 where prd_cd ='"
				+ privilegeCode + "' and product_cat_id=10";// product_cat_id=10
															// is privilege
															// product
		db.executeUpdate(query);
	}

	/**
	 * Get all active Location Class record
	 * 
	 * @param schema
	 * @return
	 */
	public List<String> getLocationClasses(String schema) {
		db.resetSchema(schema);
		logger.info("Get Location Class records.");

		String query = "select code, location_class_name from d_location_class where active_ind=1 order by code";

		List<String[]> result = db.executeQuery(query, new String[] { "code",
				"location_class_name" });
		if (result.size() == 0) {
			throw new ErrorOnDataException(
					"No any Location Class records found.");
		}
		List<String> codeNames = new ArrayList<String>();
		for (int i = 0; i < result.size(); i++) {
			codeNames.add(result.get(i)[0] + " - " + result.get(i)[1]);
		}

		return codeNames;
	}

	/**
	 * Get revenue location id from DB
	 * 
	 * @param schema
	 * @param prdID
	 */
	public String getRevenueLocationID(String schema, String prdID) {
		db.resetSchema(schema);
		logger.info("Get Revenue Location ID from DB.");

		String query = "select rev_loc_id from p_prd where prd_id = '" + prdID
				+ "'";
		return db.executeQuery(query, "Value", 0);
	}

	public String getLocationID(String schema, String locationName) {
		db.resetSchema(schema);
		logger.info("Get Location ID from DB.");

		String query = "select id from d_loc where name = '" + locationName
				+ "'";
		String value = db.executeQuery(query, "id", 0);

		return value;
	}

	public String getAgentID(String schema, String agentName) {
		db.resetSchema(schema);
		logger.info("Get agent ID from DB. Agent name = " + agentName);

		String query = "select id from d_store where name = '" + agentName
				+ "'";
		List<String> results = db.executeQuery(query, "id");

		if (results == null || results.size() < 1)
			return null;

		return results.get(0);
	}

	/**
	 * query the facilities id for given state code.
	 * 
	 * @param schema
	 * @param stateCode
	 *            : state code in abbreviation format, for example for state
	 *            "SOUTH DAKOTA" the stateCode should be "SD"
	 * @return
	 */
	public List<String> queryStateFacility(String schema, String stateCode) {
		List<String> stateFacilities = new ArrayList<String>();
		db.resetSchema(schema);
		logger.info("Get facility from Contract DB for State:" + stateCode);

		String query = "SELECT ID FROM D_LOC WHERE DELETE_IND=0 and level_num=40 and cd not like '%|800|%' and state ='"
				+ stateCode.toUpperCase() + "'";

		stateFacilities = db.executeQuery(query, "id");

		int totalNum = 0;
		if (null != stateFacilities && stateFacilities.size() > 0) {
			totalNum = stateFacilities.size();
		}

		logger.info("The total facility for State: " + stateCode + " is "
				+ totalNum);
		return stateFacilities;
	}

	/**
	 * query the RIDB facilities id for given state code
	 * 
	 * @param ridbSchema
	 * @param stateCode
	 *            : state code in abbreviation format, for example for state
	 *            "SOUTH DAKOTA" the stateCode should be "SD"
	 * @return
	 */
	public List<String> queryStateRIDBFacilities(String ridbSchema,
			String stateCode) {
		List<String> ridbFacilities = new ArrayList<String>();

		db.resetSchema(ridbSchema);
		logger.info("Get RIDB state facilities for state: " + stateCode);
		// query facilities from RIDB facility table, exclude those facilities
		// which already maped to Contract d_loc table.
		String ridbQuery = "SELECT f.FACILITYID FROM FACILITY F inner join FACILITYADDRESS FA on "
				+ "F.FACILITYID = FA.FACILITYID "
				+ "where FA.ADDRESSSTATECODE = '"
				+ stateCode
				+ "'"
				+ "AND F.ENABLED = 1 "
				+ "and (regexp_like(f.LEGACYFACILITYID, '[[:alpha:]]{1,}', 'i') or f.LEGACYFACILITYID is null)";
		ridbFacilities = db.executeQuery(ridbQuery, "FACILITYID");

		int totalNum = 0;
		if (null != ridbFacilities && ridbFacilities.size() > 0) {
			totalNum = ridbFacilities.size();
		}
		logger.info("The total matching number of RIDB facilities for State \""
				+ stateCode + "\" is:" + totalNum);
		return ridbFacilities;
	}

	/**
	 * query the RIDB Recarea id for given state code
	 * 
	 * @param ridbSchema
	 * @param stateCode
	 * @return
	 */
	public List<String> queryStateRIDBAreas(String ridbSchema, String stateCode) {
		List<String> stateRecarea = new ArrayList<String>();

		// get RECAREA info from RIDB RECAREA table
		db.resetSchema(ridbSchema);
		logger.info("Get state Areas Info from RIDB RECAREA DB for state "
				+ stateCode);

		String query = "SELECT distinct R.RECAREAID FROM RECAREAADDRESS RA INNER JOIN RECAREA R ON "
				+ "R.RECAREAID =RA.RECAREAID "
				+ "AND R.ENABLED = '1' "
				+ "and RA.ADDRESSSTATECODE = '" + stateCode + "'";
		stateRecarea = db.executeQuery(query, "RECAREAID");

		int totalNum = 0;
		if (null != stateRecarea && stateRecarea.size() > 0) {
			totalNum = stateRecarea.size();
		}

		logger.info("The total matching number of RIDB Rec area for State \""
				+ stateCode + "\" is:" + totalNum);
		return stateRecarea;
	}

	// For latitude and longitude use
	/**
	 * get latitude info based on facilityID info, don't support RIDB for now.
	 * 
	 * @param schema
	 * @param facilityID
	 * @return
	 */
	public String queryLatitudeInfo(String schema, String facilityID) {
		db.resetSchema(schema);
		logger.info("Get Latitude info from DB.");

		String query = "select lat_dec from D_LOC_COORDINATES where loc_id = '"
				+ facilityID + "'";
		String latitude = db.executeQuery(query, "lat_dec", 0);

		return latitude;
	}

	/**
	 * get longitude info based on facilityID info , don't support RIDB for now.
	 * 
	 * @param schema
	 * @param facilityID
	 * @return
	 */
	public String queryLongitudeInfo(String schema, String facilityID) {
		db.resetSchema(schema);
		logger.info("Get longitude info from DB.");

		String query = "select long_dec from D_LOC_COORDINATES where loc_id = '"
				+ facilityID + "'";
		String longitude = db.executeQuery(query, "long_dec", 0);

		return longitude;
	}

	/**
	 * query near by facilities against D_Loc_coordinates table. For 200 miles
	 * you get \ufffddegrees = 2.891535680700597 = 2 degree, 53.4 minutes,
	 * 5.5284505221492 seconds For 50 miles you get degrees = 0.722883920175149
	 * = 0 degree, 43.2 minutes, 10.3821126305364 seconds For 1000 miles you get
	 * degrees = 14.457678403502983 = 14 degree, 27 minutes, 27.6422526107388
	 * seconds
	 * 
	 * @param schema
	 * @param latitude
	 *            : in decimal format
	 * @param longitude
	 *            : in decimal format
	 * @param distance
	 *            : 50 miles, 200 miles, 1000 miles
	 * @return
	 */
	public List<String> queryNearByFacilities(String schema, String latitude,
			String longitude, int distance) {
		List<String> nearByFacilities = new ArrayList<String>();
		String distanceDegree = "";

		// get the distance degress based on sending mile.
		distanceDegree = String.valueOf(LatitudeLongitude
				.getDegreeChangesByMile(distance));

		// get near by facilities against d_loc_coordinates table.
		db.resetSchema(schema);
		logger.info("Get near by park IDs from DB.");

		String query = "SELECT ID FROM D_LOC WHERE (ID IN (SELECT LOC_ID FROM D_LOC_COORDINATES WHERE (LAT_DEC BETWEEN "
				+ latitude
				+ "-"
				+ distanceDegree
				+ " AND "
				+ latitude
				+ "+"
				+ distanceDegree
				+ ") AND (LONG_DEC BETWEEN "
				+ longitude
				+ "-"
				+ distanceDegree
				+ " AND "
				+ longitude
				+ "+"
				+ distanceDegree
				+ "))) AND DELETE_IND=0 and level_num=40 and STATUS_ID=1 and cd not like '%|800|%'";

		nearByFacilities = db.executeQuery(query, "id");

		logger.info("The total matching number of park is:"
				+ nearByFacilities.size());
		return nearByFacilities;
	}

	/**
	 * Query all park without 'Hidden On Web Search'
	 * 
	 * @param schema
	 * @param facilityIDs
	 * @return
	 */
	public List<String> queryNearByFacilitiesDisplayOnWebSearch(String schema,
			List<String> facilityIDs) {
		db.resetSchema(schema);
		logger.info("Get near by park IDs without 'Hidden On Web Search' from DB.");
		List<String> facilityHide = new ArrayList<String>();
		List<String> facilityDisplay = facilityIDs;
		String query = "select loc_id from d_loc_attr_value where attr_id in (select attr_id from d_attr where attr_dscr = 'Hidden On Web Search') and loc_id in "
				+ facilityIDs.toString().replaceAll("\\[", "(")
						.replaceAll("\\]", ")");

		logger.info(query);
		facilityHide = db.executeQuery(query, "LOC_ID");

		for (int i = 0; i < facilityHide.size(); i++) {
			if (facilityIDs.toString().contains(facilityHide.get(i))) {
				facilityDisplay.remove(facilityHide.get(i));
			}
		}

		logger.info("The total matching number of park is:"
				+ facilityDisplay.size());
		return facilityDisplay;
	}

	/**
	 * query near by facilities against RIDB facility table. For 200 miles you
	 * get \ufffddegrees = 2.891535680700597 = 2 degree, 53.4 minutes,
	 * 5.5284505221492 seconds For 50 miles you get degrees = 0.722883920175149
	 * = 0 degree, 43.2 minutes, 10.3821126305364 seconds For 1000 miles you get
	 * degrees = 14.457678403502983 = 14 degree, 27 minutes, 27.6422526107388
	 * seconds
	 * 
	 * @param schema
	 * @param ridbSchema
	 * @param facilityID
	 * @param distance
	 *            : 50 miles, 200 miles, 1000 miles
	 * @return
	 */
	public List<String> queryNearByRIDBFacilities(String ridbSchema,
			String latitude, String longitude, int distance) {
		String distanceDegree = "";
		List<String> nearByFac = new ArrayList<String>();

		// get the distance degress based on sending mile.
		distanceDegree = String.valueOf(LatitudeLongitude
				.getDegreeChangesByMile(distance));

		// get facility info from RIDB Facility table
		db.resetSchema(ridbSchema);
		logger.info("Get near by Facility IDs from RIDB Facility DB.");
		// query all facilities which don't have Legacyfacility in contract's
		// D_LOC table.
		String ridbQuery = "select facilityID, facilitylatitude, facilitylongitude from facility where enabled =1 and facilitylatitude is not null and facilitylongitude is not null and (regexp_like(LEGACYFACILITYID, '[[:alpha:]]{1,}', 'i') or LEGACYFACILITYID is null)";
		List<String[]> ridbFac = db.executeQuery(ridbQuery, new String[] {
				"facilityID", "facilitylatitude", "facilitylongitude" });

		logger.info("Start formating latitude and longitude info for RIDB Facilities, total record is:"
				+ ridbFac.size() + " please wait.. ");
		for (int i = 0; i < ridbFac.size(); i++) {
			ridbFac.get(i)[1] = LatitudeLongitude
					.parseToDegree(ridbFac.get(i)[1]);
			ridbFac.get(i)[2] = LatitudeLongitude
					.parseToDegree(ridbFac.get(i)[2]);
		}

		// get near by facilities
		for (int i = 0; i < ridbFac.size(); i++) {
			// check latitude near given distance.
			if (Double.parseDouble(ridbFac.get(i)[1]) >= Double
					.parseDouble(latitude) - Double.parseDouble(distanceDegree)
					&& Double.parseDouble(ridbFac.get(i)[1]) <= Double
							.parseDouble(latitude)
							+ Double.parseDouble(distanceDegree)) {
				// check longitude near given distance.
				if (Double.parseDouble(ridbFac.get(i)[2]) >= Double
						.parseDouble(longitude)
						- Double.parseDouble(distanceDegree)
						&& Double.parseDouble(ridbFac.get(i)[2]) <= Double
								.parseDouble(longitude)
								+ Double.parseDouble(distanceDegree)) {
					// record the facilityID into the return near by facility
					// list.
					nearByFac.add(ridbFac.get(i)[0]);
				}
			}
		}
		logger.info("The total matching number of RIDB facilities is:"
				+ nearByFac.size());
		return nearByFac;
	}

	/**
	 * query near by Areas against RECAREA table in RIDB database. For 200 miles
	 * you get \ufffddegrees = 2.891535680700597 = 2 degree, 53.4 minutes,
	 * 5.5284505221492 seconds For 50 miles you get degrees = 0.722883920175149
	 * = 0 degree, 43.2 minutes, 10.3821126305364 seconds For 1000 miles you get
	 * degrees = 14.457678403502983 = 14 degree, 27 minutes, 27.6422526107388
	 * seconds
	 * 
	 * @param schema
	 * @param ridbSchema
	 * @param facilityID
	 * @param distance
	 *            : 50 miles, 200 miles, 1000 miles
	 * @return
	 */
	public List<String> queryNearByRIDBAreas(String ridbSchema,
			String latitude, String longitude, int distance) {
		List<String> nearByArea = new ArrayList<String>();

		// get the distance degress based on sending mile.
		String distanceDegree = String.valueOf(LatitudeLongitude
				.getDegreeChangesByMile(distance));

		// get RECAREA info from RIDB RECAREA table
		db.resetSchema(ridbSchema);
		logger.info("Get near by Areas Info from RIDB RECAREA DB.");

		String query = "select RECAREAID, RECAREALATITUDE, RECAREALONGITUDE from RECAREA where Enabled = '1' AND RECAREALATITUDE IS NOT NULL AND RECAREALONGITUDE IS NOT NULL";
		List<String[]> ridbRecarea = db.executeQuery(query, new String[] {
				"RECAREAID", "RECAREALATITUDE", "RECAREALONGITUDE" });

		logger.info("Start formating latitude and longitude info for RIDB RECAREA, total record is:"
				+ ridbRecarea.size() + " please wait.. ");
		for (int i = 0; i < ridbRecarea.size(); i++) {
			// System.out.println(ridbRecarea.get(i)[0] + "----" +
			// ridbRecarea.get(i)[1] + "----" + ridbRecarea.get(i)[2]);
			ridbRecarea.get(i)[1] = LatitudeLongitude.parseToDegree(ridbRecarea
					.get(i)[1]);
			ridbRecarea.get(i)[2] = LatitudeLongitude.parseToDegree(ridbRecarea
					.get(i)[2]);
		}

		// get near by recareas
		for (int i = 0; i < ridbRecarea.size(); i++) {
			// check latitude near given distance.
			if (Double.parseDouble(ridbRecarea.get(i)[1]) >= Double
					.parseDouble(latitude) - Double.parseDouble(distanceDegree)
					&& Double.parseDouble(ridbRecarea.get(i)[1]) <= Double
							.parseDouble(latitude)
							+ Double.parseDouble(distanceDegree)) {
				// check longitude near given distance.
				if (Double.parseDouble(ridbRecarea.get(i)[2]) >= Double
						.parseDouble(longitude)
						- Double.parseDouble(distanceDegree)
						&& Double.parseDouble(ridbRecarea.get(i)[2]) <= Double
								.parseDouble(longitude)
								+ Double.parseDouble(distanceDegree)) {
					// record the facilityID into the return near by facility
					// list.
					nearByArea.add(ridbRecarea.get(i)[0]);
				}
			}
		}
		logger.info("The total matching number of RIDB Rec area is:"
				+ nearByArea.size());
		return nearByArea;
	}

	/**
	 * Get Other Activities Applied options
	 */
	public String[] getOtherActivitiesApplied(String schema,
			String excludedActivities) {
		db.resetSchema(schema);
		logger.info("Get other activities applied from DB..");

		String query = "select ACTIVITYNAME from activity where ACTIVITYID not in ("
				+ excludedActivities + ") order by ACTIVITYNAME";

		// Get String List
		List<String> stringList = db.executeQuery(query, "ACTIVITYNAME");
		if (stringList.size() == 0) {
			throw new ErrorOnDataException(
					"No any Other activities records found.");
		}
		// Change to String Array
		String[] stringArray = new String[stringList.size()];
		for (int i = 0; i < stringList.size(); i++) {
			stringArray[i] = stringList.get(i);
		}

		return stringArray;
	}

	public List<String[]> getRIDBFacilitiesInfo(String schema,
			String contractName) {
		db.resetSchema(schema);
		logger.info("Query " + contractName
				+ " countract RIDB facilities information form DB.");

		String sql = "select name, "
				+ "'Recreation Area' as \"Facility Type\","
				+ "'"
				+ contractName
				+ "' as \"Contract\", "
				+ "addressstatecode as \"State\" from v_recarea "
				+ " where recarea_id is not NULL or (facility_id is NOT NULL and legacyfacility_id != substr(facility_id, 2)) order by Name";

		logger.debug("Execute query: " + sql);
		String[] colNames = { "Name", "Facility Type", "Contract", "State" };
		List<String[]> values = db.executeQuery(sql, colNames);

		db.disconnect();
		return values;
	}

	public List<String[]> getRIDBAndORMSFacilitiesInfo(String schema) {
		db.resetSchema(schema);
		logger.info("Query RIDB and ORMS facilities information form DB.");

		String sql = "select name, CASE"
				+ " WHEN recarea_id is NULL THEN 'facility'"
				+ " WHEN facility_id is NULL then 'recarea'"
				+ " END as Facility_type,"
				+ " case "
				+ " when recarea_id is not null then recarea_id"
				+ " when facility_id is not null then facility_id"
				+ " end as ID,"
				+ " 'NRRS' as \"Contract\", addressstatecode as \"State\""
				+ " from v_recarea  where recarea_id is not NULL or "
				+ " (facility_id is NOT NULL and (legacyfacility_id is NULL or length(trim(translate(legacyfacility_id, ' +-.0123456789',' '))) is NOT NULL or"
				+ " (length(trim(translate(legacyfacility_id, ' +-.0123456789',' '))) is NULL and to_number(facility_id)!=to_number(legacyfacility_id)+300000))) order by facility_type, ID";

		logger.debug("Execute query: " + sql);
		String[] colNames = { "NAME", "FACILITY_TYPE", "ID", "CONTRACT",
				"STATE" };
		List<String[]> values = db.executeQuery(sql, colNames);

		db.disconnect();
		return values;
	}

	public List<String[]> getORMSFacilitiesInfo(String schema,
			String contractName) {
		db.resetSchema(schema);
		logger.info("Query " + contractName
				+ " countract ORMS facilities information form DB.");

		String sql = "select a.name, case b.prd_grp_cat_id "
				+ "when 3 then 'Campground' "
				+ "when 6 then 'Tour' "
				+ "when 5 then 'Permit Location' "
				+ "ELSE 'Other' "
				+ "END "
				+ "\"Facility Type\", "
				+ "'"
				+ contractName
				+ "' as \"Contract\", a.state "
				+ "from d_loc a, d_loc_prd_cat b where a.level_num=40 and a.cd not like '%|800|%' and a.delete_ind=0 and "
				+ "b.loc_id = a.id and b.prd_grp_cat_id !=4 and b.prd_grp_cat_id !=7 and a.status_id=1 order by Name";

		logger.debug("Execute query: " + sql);
		String[] colNames = { "Name", "Facility Type", "Contract", "State" };
		List<String[]> values = db.executeQuery(sql, colNames);

		db.disconnect();
		return values;
	}

	public List<String[]> queryPrivilegeInventoryTyeByCode(String schema,
			String code) {
		db.resetSchema(schema);
		logger.info("Query privilege inventory type by code, code = " + code);

		String query = "select id, create_user_id, create_loc_id, to_char(create_date, 'yyyy/MM/dd') as create_date,"
				+ "last_update_user_id, last_update_loc_id, to_char(last_update_date, 'yyyy/MM/dd') as last_update_date "
				+ "from p_hf_inv_type where code = '" + code + "'";

		logger.debug("Execute query: " + query);
		String[] colNames = { "id", "create_user_id", "create_loc_id",
				"create_date", "last_update_user_id", "last_update_loc_id",
				"last_update_date" };
		List<String[]> values = db.executeQuery(query, colNames);
		return values;
	}

	public void deletePrivilegeInventoryTypeInfoByID(String schema,
			String invTypeID) {
		db.resetSchema(schema);
		logger.info("Delete privilege inventory type by ID, ID = " + invTypeID);

		String sql = "update p_prd_privilege set inv_type_id = null where inv_type_id = "
				+ invTypeID;
		logger.debug("Execute sql: " + sql);
		db.executeUpdate(sql);

		sql = "delete from p_hf_inv_type where id = " + invTypeID;

		logger.debug("Execute sql: " + sql);
		db.executeUpdate(sql);
	}

	public List<String[]> getInvLicenseYearIDAndUserInfo(String schema,
			InventoryTypeLicenseYear invTypeLicYear) {
		db.resetSchema(schema);
		logger.info("Get inventory license year ID and user info from DB..");

		String query = "select id, create_user_id, create_loc_id, to_char(create_date, 'yyyy/MM/dd') as create_date,"
				+ "last_update_user_id, last_update_loc_id, to_char(last_update_date, 'yyyy/MM/dd') as last_update_date"
				+ " from p_hf_inv_type_year where inv_type_id = (select id from p_hf_inv_type where code = '"
				+ invTypeLicYear.inventoryType + "')";
		if (invTypeLicYear.licenseYear.equalsIgnoreCase("ALL")) {
			// query = query
			// + " and to_char(from_date, 'yyyy/MM/dd') >= '"
			// + DateFunctions.formatDate(invTypeLicYear.issueFromDate,
			// "yyyy/MM/dd") + "'";
			// if (invTypeLicYear.issueToDate.trim().length() > 0) {
			// query = query
			// + " and to_char(to_date, 'yyyy/MM/dd') <= '"
			// + DateFunctions.formatDate(invTypeLicYear.issueToDate,
			// "yyyy/MM/dd") + "'";
			// }

			if (invTypeLicYear.issueToDate.trim().length() > 0) {
				query = query
						+ "and ((to_char(from_date, 'yyyy/MM/dd') <= '"
						+ DateFunctions.formatDate(
								invTypeLicYear.issueFromDate, "yyyy/MM/dd")
						+ "' and to_char(to_date, 'yyyy/MM/dd') <= '"
						+ DateFunctions.formatDate(invTypeLicYear.issueToDate,
								"yyyy/MM/dd")
						+ "')"
						+ " or (to_char(from_date, 'yyyy/MM/dd') >= '"
						+ DateFunctions.formatDate(
								invTypeLicYear.issueFromDate, "yyyy/MM/dd")
						+ "' and to_char(to_date, 'yyyy/MM/dd') <= '"
						+ DateFunctions.formatDate(invTypeLicYear.issueToDate,
								"yyyy/MM/dd")
						+ "')"
						+ " or (to_char(from_date, 'yyyy/MM/dd') >= '"
						+ DateFunctions.formatDate(
								invTypeLicYear.issueFromDate, "yyyy/MM/dd")
						+ "' and to_char(to_date, 'yyyy/MM/dd') >= '"
						+ DateFunctions.formatDate(invTypeLicYear.issueToDate,
								"yyyy/MM/dd")
						+ "')"
						+ " or (to_char(from_date, 'yyyy/MM/dd') <= '"
						+ DateFunctions.formatDate(
								invTypeLicYear.issueFromDate, "yyyy/MM/dd")
						+ "' and to_char(to_date, 'yyyy/MM/dd') >= '"
						+ DateFunctions.formatDate(invTypeLicYear.issueToDate,
								"yyyy/MM/dd") + "'))";
			}
		} else {
			query = query + " and year = " + invTypeLicYear.licenseYear;
		}
		logger.debug("Execute query: " + query);
		String[] colNames = { "id", "create_user_id", "create_loc_id",
				"create_date", "last_update_user_id", "last_update_loc_id",
				"last_update_date" };
		List<String[]> values = db.executeQuery(query, colNames);

		return values;
	}

	public void deleteInvTypeLiceseYearInfoFromDBByID(String schema, String ID) {
		db.resetSchema(schema);
		logger.info("Delete inventory type license year by ID, ID = " + ID);
		String sql = "delete from p_hf_inv_type_year where id = " + ID;

		logger.debug("Execute sql: " + sql);
		db.executeUpdate(sql);
	}

	public void deleteInvTypeLicenseYearInfoFromDBByInventoryType(
			String schema, String inventoryType) {
		db.resetSchema(schema);
		logger.info("Delete inventory type license year by inventory type, inventory type = "
				+ inventoryType);
		String sql = "delete from p_hf_inv_type_year where inv_type_id = (select id from p_hf_inv_type where code = '"
				+ inventoryType + "')";
		logger.debug("Execute sql: " + sql);
		db.executeUpdate(sql);
	}

	public void deletePriInventoryFromDB(String schema,
			InventoryTypeLicenseYear invTypeLicYear) {
		List<String[]> values = this.getInvLicenseYearIDAndUserInfo(schema,
				invTypeLicYear);
		if (values.size() > 0) {
			String licYearID = values.get(0)[0];
			db.resetSchema(schema);
			logger.info("Delete privilege inventory info by license year ID = "
					+ licYearID);

			String sql = "delete from p_hf_inv where inv_type_year_id = "
					+ licYearID;
			logger.debug("Execute sql: " + sql);
			db.executeUpdate(sql);
		} else {
			logger.info("Did not have privilege info.");
		}
	}

	public List<String[]> getPriInventoryInfoFromDB(String schema,
			String inventoryNum, InventoryTypeLicenseYear invTypeLicYear) {
		String licYearID = this.getInvLicenseYearIDAndUserInfo(schema,
				invTypeLicYear).get(0)[0];

		db.resetSchema(schema);
		logger.info("Get privilege inventory info by license year ID = "
				+ licYearID);

		String query = "select create_usr_id, create_loc_id, to_char(create_date, 'yyyy/MM/dd') as create_date "
				+ "from p_hf_inv "
				+ "where inv_number = '"
				+ inventoryNum
				+ "'" + " and inv_type_year_id = " + licYearID;
		logger.debug("Execute query: " + query);
		String[] colNames = { "create_usr_id", "create_loc_id", "create_date" };
		List<String[]> values = db.executeQuery(query, colNames);

		return values;
	}

	/** Set product group icon */
	public void setProductGroupIcon(String schema, String prdGrpNm,
			String iconNm) {
		db.resetSchema(schema);
		String sql = "update P_PRD_GRP set TMPL_IND="
				+ (iconNm == null ? null : ("'" + iconNm + "'"))
				+ " where PRD_GRP_NAME='" + prdGrpNm
				+ "' and ACTIVE_IND=1 and DELETED_IND=0";
		logger.info("Execute query: " + sql);
		db.executeUpdate(sql);
	}

	public List<String> getProductGroupNames(String schema, String prdCatId) {
		db.resetSchema(schema);
		String sql = "select PRD_GRP_NAME from P_PRD_GRP where PRD_GRP_CAT_ID="
				+ prdCatId
				+ " and ACTIVE_IND=1 and DELETED_IND=0 order by PRD_GRP_NAME";
		List<String> result = db.executeQuery(sql, "PRD_GRP_NAME");
		logger.info("Execute query: " + sql);

		return result;
	}

	public String getProductGropIDByPrdID(String schema, String prdID) {
		db.resetSchema(schema);
		String sql = "select prd_grp_id from p_prd where prd_id = " + prdID;
		logger.info("Execute query: " + sql);
		String prdGrpID = db.executeQuery(sql, "prd_grp_id", 0);
		return prdGrpID;
	}

	public List<String[]> getProductGroupNameUsingFeeSchdDetailPg(
			String schema, String productCategory, String productSubCategory) {
		db.resetSchema(schema);
		logger.info("Get product group name which using fee schedule detail page from DB..");

		String sql = "select PRD_GRP_NAME from P_PRD_GRP where PRD_GRP_CAT_ID='"
				+ productCategory + "' AND ACTIVE_IND='1' and DELETED_IND='0'";
		if (!"".equals(productSubCategory)) {
			sql = sql + "AND PRD_SUBCAT_ID='" + productSubCategory + "'";
		}
		sql = sql + " Order by PRD_GRP_NAME";

		String[] colNames = { "PRD_GRP_NAME" };

		List<String[]> values = db.executeQuery(sql, colNames);

		db.disconnect();

		return values;
	}

	public List<String[]> getProductNameUsingFeeSchdDetailPg(String schema,
			String productCategory, String productSubCategory, String location,
			String revenuLoc) {
		return this.getProductNameUsingFeeSchdDetailPg(schema, productCategory,
				productSubCategory, location, revenuLoc, false);
	}

	public List<String[]> getProductNameUsingFeeSchdDetailPg(String schema,
			String productCategory, String productSubCategory, String location,
			String revenuLoc, boolean isLoc) {
		return getProductNameUsingFeeSchdDetailPg(schema, productCategory,
				productSubCategory, location, revenuLoc, isLoc, true, null);
	}

	public List<String[]> getProductNameUsingFeeSchdDetailPg(String schema,
			String productCategory, String productSubCategory, String location,
			String revenuLoc, boolean isLoc, boolean parentOnly,
			String productGrp) {

		db.resetSchema(schema);
		logger.info("Get product name which using fee schedule detail page from DB..");

		String sql = "select PRD_NAME, PRD_ID from P_PRD where PRODUCT_CAT_ID='"
				+ productCategory + "' AND ACTIVE_IND='1' AND DELETED_IND='0' ";
		if (!"".equals(productSubCategory)) {
			sql = sql + " AND PRD_SUBCAT_ID='" + productSubCategory + "'";
		}
		if (!"".equals(revenuLoc)) {
			sql = sql + " AND REV_LOC_ID='" + revenuLoc + "'";
		}

		if (!"".equals(location)) {
			if (isLoc) {
				sql = sql + " AND LOC_ID='" + location + "'";
			} else {
				sql = sql + " AND PARK_ID='" + location + "'";
			}

		}
		if (StringUtil.notEmpty(productGrp)) {
			sql = sql
					+ " AND PRD_GRP_ID = (select prd_grp_id from P_PRD_GRP where PRD_GRP_NAME='"
					+ productGrp + "')";
		}

		if (parentOnly) {
			sql = sql + " and parent_id=0 Order by PRD_NAME"; // Sara[11/26/2013]
																// per
																// DEFECT-43661
		}

		sql = sql + " order by PRD_NAME";

		String[] colNames = { "PRD_NAME", "PRD_ID" };
		logger.info("Run sql:" + sql);
		List<String[]> values = db.executeQuery(sql, colNames);

		db.disconnect();

		return values;
	}

	public List<String> getTaxForFeeSchedule(String schema, String feeTypeId) {
		db.resetSchema(schema);
		logger.info("Get tax name which will show in fee schedule detail page from DB.");
		String sql = "select tt.name from P_TAX_TYPE tt, P_TAX_FEE_TYPE tft where tft.tax_type_id=tt.id and tft.fee_type_id=39 and tt.active_ind=1";
		List<String> result = db.executeQuery(sql, "NAME");
		logger.info("Execute query: " + sql);
		return result;
	}

	public List<String> getVehicleType(String schema) {
		db.resetSchema(schema);

		logger.info("Get vehicle type from DB..");

		String sql = "select NAME from D_VEHICLE_TYPE Order by NAME";

		List<String> values = db.executeQuery(sql, "NAME");

		db.disconnect();

		return values;
	}

	/**
	 * Get facility activities infromation from DB
	 * 
	 * @param schema
	 * @param facilityID
	 * @return
	 */
	public List<String> getFacilityActivitiesFromDB(String schema,
			String facilityID) {
		db.resetSchema(schema);
		logger.info("Query facilities (" + facilityID
				+ ") activities information form DB.");

		String sql = "select a.facilityactivitydescription "
				+ "from facility f, facilityactivity a "
				+ "where f.legacyfacilityid = '" + facilityID
				+ "' and a.facilityid=f.facilityid";

		logger.debug("Execute query: " + sql);
		List<String> values = db.executeQuery(sql,
				"FACILITYACTIVITYDESCRIPTION");

		db.disconnect();
		return values;
	}

	public List<String> getRecareaActivityActivitiesFromDB(String schema,
			String recareaId) {
		db.resetSchema(schema);
		logger.info("Query recareaId (" + recareaId
				+ ") activities information form DB.");

		String sql = "select distinct a.activityname as ACTIVITYNAME from activity a, recareaactivity reca where a.activityid=reca.activityid and reca.recareaid="
				+ recareaId + " order by a.activityname";

		logger.debug("Execute query: " + sql);
		List<String> values = db.executeQuery(sql, "ACTIVITYNAME");

		db.disconnect();
		return values;
	}

	public List<String> getFacilityActivitiesViaFacilityNameFromDB(
			String schema, String parkName) {
		db.resetSchema(schema);
		logger.info("Query facilities (" + parkName
				+ ") activities information form DB.");

		String sql = "select distinct t.activityname from facility f, facilityactivity a, activity t "
				+ "where f.facilityname like '"
				+ parkName
				+ "' or f.facilityname like '"
				+ parkName
				+ " ' and a.facilityid=f.facilityid and a.activityid=t.activityid";

		logger.debug("Execute query: " + sql);
		List<String> values = db.executeQuery(sql, "ACTIVITYNAME");

		db.disconnect();
		return values;
	}

	/**
	 * This method is used to delete vendor status reason record from DB
	 * 
	 * @param id
	 * @param schema
	 */
	public void deleteVendorStatusReasonFromDB(String id, String schema) {
		db.resetSchema(schema);
		logger.info("Delect vendor status reason from DB, id = " + id);

		String sql = "delete from d_vendor_status_reason where id = " + id;
		logger.info("Execute query: " + sql);

		int affectedNum = db.executeUpdate(sql);
		if (affectedNum < 1) {
			throw new ErrorOnDataException(
					"Can't find venfor satus reason record - " + id);
		} else {
			logger.info("Delete vendor status reason record successfully.");
		}
	}

	/**
	 * This method is used to get minimum payment rule info
	 * 
	 * @param schema
	 * @param prdCatID
	 * @param locationID
	 * @param prdGrpName
	 * @param prdName
	 * @return
	 */
	public List<String[]> getMinimumPaymentRuleInfo(String schema,
			String prdCatID, String locationID, String prdGrpName,
			String prdName) {
		logger.info("Get minimum payment rule info.");
		db.resetSchema(schema);

		String query = "select *"
				+ " from p_min_pmt_cfm, p_min_pmt_entry_cfm"
				+ " where p_min_pmt_cfm.id = p_min_pmt_entry_cfm.p_min_pmt_cfm_id"
				+ " and p_min_pmt_cfm.active_id = 1"
				+ " and p_min_pmt_cfm.loc_id = " + locationID;
		if (null != prdCatID && prdCatID.trim().length() > 0) {
			query = query + " and p_min_pmt_cfm.prd_cat_id = " + prdCatID;
		}

		if (null != prdGrpName && prdGrpName.trim().length() > 0) {
			query = query
					+ " and p_min_pmt_cfm.prd_grp_id = (select prd_grp_id from p_prd_grp where prd_grp_name = '"
					+ prdGrpName + "')";
		}

		if (null != prdName && prdName.trim().length() > 0) {
			query = query
					+ " and p_min_pmt_cfm.prd_id  = (select prd_id from p_prd where prd_name = '"
					+ prdName + "')";
		}

		String[] colNames = { "fee_type_id", "rule_type", "amount",
				"sales_chanl_id", "min_unit_stay", "min_num_days",
				"effective_date" };
		logger.info("Get minimun payment rule SQL:" + query);
		List<String[]> values = db.executeQuery(query, colNames);

		return values;
	}

	/**
	 * This method is used to update lottery schedule info including app start
	 * date, app end date, execution date, inventory start date and inventory
	 * end date in DB
	 * 
	 * @param schema
	 * @param schdl
	 */
	public void updateLotteryScheduleInfo(String schema, LotterySchedule schdl) {
		logger.info("Update lottery schedule(ID#=" + schdl.id + ").");
		db.resetSchema(schema);
		if (schdl.appStartDateHour.length() == 0) {
			schdl.appStartDateHour = "0";
		}
		if (schdl.appStartDateMinute.length() == 0) {
			schdl.appStartDateMinute = "0";
		}
		if (schdl.appEndDateHour.length() == 0) {
			schdl.appEndDateHour = "0";
		}
		if (schdl.appEndDateMinute.length() == 0) {
			schdl.appEndDateMinute = "0";
		}
		if (schdl.executeHour.length() == 0) {
			schdl.executeHour = "0";
		}
		if (schdl.executeMin.length() == 0) {
			schdl.executeMin = "0";
		}
		String app_st_date_time = DateFunctions.formatDate(schdl.appStartDate,
				"yyyy/MM/dd")
				+ " "
				+ (Integer.parseInt(schdl.appStartDateHour) + 1)
				+ ":"
				+ schdl.appStartDateMinute
				+ " "
				+ (schdl.appStartDateAM ? "am" : "pm");
		String app_end_date_time = DateFunctions.formatDate(schdl.appEndDate,
				"yyyy/MM/dd")
				+ " "
				+ (Integer.parseInt(schdl.appEndDateHour) + 1)
				+ ":"
				+ schdl.appEndDateMinute
				+ " "
				+ (schdl.appEndDateAM ? "am" : "pm");
		String execution_date_time = DateFunctions.formatDate(
				schdl.executeDate, "yyyy/MM/dd")
				+ " "
				+ (Integer.parseInt(schdl.executeHour) + 1)
				+ ":"
				+ schdl.executeMin + " " + (schdl.executeAM ? "am" : "pm");
		String inv_st_date = DateFunctions.formatDate(schdl.invStartDate,
				"yyyy/MM/dd");
		String inv_end_date = DateFunctions.formatDate(schdl.invEndDate,
				"yyyy/MM/dd");

		String sql = "update P_LOTTERY_SCHD set APP_ST_DATE = to_date('"
				+ app_st_date_time + "', 'yyyy/mm/dd hh12:mi am'), "
				+ "APP_END_DATE = to_date('" + app_end_date_time
				+ "', 'yyyy/mm/dd hh12:mi am'), "
				+ "EXECUTION_DATE = to_date('" + execution_date_time
				+ "', 'yyyy/mm/dd hh12:mi am'), " + "INV_ST_DATE = to_date('"
				+ inv_st_date + "', 'yyyy/mm/dd'), "
				+ "INV_END_DATE = to_date('" + inv_end_date
				+ "', 'yyyy/mm/dd') " + "where ID=" + schdl.id;

		logger.info("Execute query: " + sql);
		int affectedNum = db.executeUpdate(sql);
		if (affectedNum < 1) {
			throw new ErrorOnPageException(
					"Can't find lottery schedule record - " + schdl.id);
		} else {
			logger.info("Lottery schedule info update successfully.");
		}
	}

	public void updateLotteryScheduleAppStartDateTime(String schema, String id,
			String newDateTime) {
		newDateTime = DateFunctions.formatDate(newDateTime, "d-MMM-yy");
		logger.info("Update lottery schedule(ID#=" + id
				+ ") App Start Date as :" + newDateTime);
		db.resetSchema(schema);
		String sql1 = "update P_LOTTERY_SCHD set APP_ST_DATE = '" + newDateTime
				+ "' where ID=" + id;
		String sql2 = "update P_LOTTERY_SCHD_SALES_CHNL set START_DATE = '"
				+ newDateTime + "' where LOTTERY_SCHD_ID=" + id;
		logger.info("Execute query: " + sql1);
		logger.info("Execute query: " + sql2);
		int affectedNum1 = db.executeUpdate(sql1);
		int affectedNum2 = db.executeUpdate(sql2);
		if (affectedNum1 < 1 || affectedNum2 < 1) {
			throw new ErrorOnDataException(
					"Can't find lottery schedule record - " + id);
		} else {
			logger.info("Lottery schedule App Start Date update successfully.");
		}
	}

	public void updateLotteryScheduleAppEndDateTime(String schema, String id,
			String newDateTime) {
		logger.info("Update lottery schedule(ID#=" + id + ") App End Date as :"
				+ newDateTime);
		db.resetSchema(schema);

		String sql1 = "update P_LOTTERY_SCHD set APP_END_DATE = to_date('"
				+ newDateTime + "', 'yyyy/mm/dd hh12:mi am') where ID=" + id;
		String sql2 = "update P_LOTTERY_SCHD_SALES_CHNL set END_DATE = to_date('"
				+ newDateTime
				+ "', 'yyyy/mm/dd hh12:mi am') where LOTTERY_SCHD_ID=" + id;
		logger.info("Execute query: " + sql1);
		logger.info("Execute query: " + sql2);
		int affectedNum1 = db.executeUpdate(sql1);
		int affectedNum2 = db.executeUpdate(sql2);
		if (affectedNum1 < 1 || affectedNum2 < 1) {
			throw new ErrorOnDataException(
					"Can't find lottery schedule record - " + id);
		} else {
			logger.info("Lottery schedule App END Date update successfully.");
		}
	}

	public void updateLotteryScheduleExecutionDateTime(String schema,
			String id, String newDateTime) {
		logger.info("Update lottery schedule(ID#=" + id
				+ ") Execution Date as :" + newDateTime);
		db.resetSchema(schema);

		String sql = "update P_LOTTERY_SCHD set EXECUTION_DATE = to_date('"
				+ DateFunctions.formatDate(newDateTime, "yyyy/MM/dd h:m aa")
				+ "', 'yyyy/mm/dd hh12:mi am') where ID=" + id;
		logger.info("Execute query: " + sql);
		int affectedNum = db.executeUpdate(sql);
		if (affectedNum < 1) {
			throw new ErrorOnDataException(
					"Can't find lottery schedule record - " + id);
		} else {
			logger.info("Lottery schedule EXECUTION Date update successfully.");
		}
	}

	public void updateLotteryScheduleAcceptStartDateTime(String schema,
			String id, String newDateTime) {
		logger.info("Update lottery schedule(ID#=" + id
				+ ") Accept Start date as :" + newDateTime);
		db.resetSchema(schema);

		String sql = "update P_LOTTERY_SCHD set ACPT_ST_DATE = to_date('"
				+ DateFunctions.formatDate(newDateTime, "yyyy/MM/dd h:m aa")
				+ "', 'yyyy/mm/dd hh12:mi am') where ID=" + id;
		logger.info("Execute query: " + sql);
		int affectedNum = db.executeUpdate(sql);
		if (affectedNum < 1) {
			throw new ErrorOnDataException(
					"Can't find lottery schedule record - " + id);
		} else {
			logger.info("Lottery schedule Acceptance Period START Date update successfully.");
		}
	}

	/**
	 * Deactivate all active lottery schedules query by lottery id
	 * 
	 * @param schema
	 * @param lotteryID
	 */
	public void deactivateLotterySchedulesFromDB(String schema, String lotteryID) {
		logger.info("Deactivate lottery schedule(s) of Lottery(ID#="
				+ lotteryID + ")from DB.");
		db.resetSchema(schema);

		String query1 = "select ID from P_LOTTERY_SCHD where ACTIVE_IND=1 and PRD_ID="
				+ lotteryID;
		String query2 = "update P_LOTTERY_SCHD set ACTIVE_IND=0 where ACTIVE_IND=1 and PRD_ID="
				+ lotteryID;
		logger.info("Execute query: " + query1);
		List<String> ids = db.executeQuery(query1, "ID");

		if (ids.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (String id : ids) {
				sb.append(id + " ");
			}
			logger.info("Try to deactivate lottery schedule(s): " + sb);
			logger.info("Execute query: " + query2);
			int affectedNum = db.executeUpdate(query2);
			if (affectedNum < 1) {
				throw new ErrorOnDataException(
						"Deactivate lottery schedule(s) failed.");
			} else {
				logger.info("All active lottery schedule(s) have been deactivated successfully.");
			}
		} else {
			logger.info("There is NOT any active lottery schedules needed to be deactivated.");
		}
	}

	/**
	 * insert EFT invoice number in to table QA_AUTOMATION this method used to
	 * one agent has one invoice
	 * 
	 * @param number
	 */
	public void insertInvoiceNumIntoQAAuto(Map<String, String> number) {

		String query = "select VAL from QA_AUTOMATION where VAR='";
		String update = "update QA_AUTOMATION set VAL='";
		String insert = "INSERT INTO QA_AUTOMATION  VALUES('";

		for (String key : number.keySet()) {
			query = query + key + "'";
			List<String> val = db.executeQuery(query, "VAL");
			if (val.size() > 0) {
				logger.info("update invoice number for agent:" + key);
				update = update + number.get(key) + "'";
				db.executeUpdate(query);
			} else {
				logger.info("insert invoice number for agent: " + key);
				insert = insert + key + "','" + number.get(key) + "','')";
				db.executeUpdate(query);
			}
		}

	}

	/**
	 * get EFT invoice number form table QA_AUTOMATION by agent name
	 * 
	 * @param agent
	 * @return
	 */
	public String getInvoiceNumByAgentName(String agent) {
		logger.info("get invoice number for agent:" + agent);

		String query = "select VAL from QA_AUTOMATION where VAR='" + agent
				+ "'";
		List<String> val = db.executeQuery(query, "VAL");
		String num = val.get(0);

		return num;
	}

	/**
	 * Update privilege instance valid from/to date time
	 * 
	 * @param schema
	 * @param privNum
	 * @param validFrom
	 * @param validTo
	 */
	public void updatePrivilegeInstanceValidFromAndTo(String schema,
			String privNum, String validFrom, String validTo) {
		logger.info("Update privilege instance(#=" + privNum
				+ ") valid from date time as: " + validFrom
				+ " and valid to date time as: " + validTo);

		String pattern = "yyyy/MM/dd h:m aa";
		String validFromDate = DateFunctions.formatDate(validFrom, pattern);
		String validToDate = DateFunctions.formatDate(validTo, pattern);

		db.resetSchema(schema);
		String sql = "update O_PRIV_INST set VALID_FROM = to_date('"
				+ validFromDate + "', 'yyyy/mm/dd hh12:mi am'), "
				+ "VALID_TO=to_date('" + validToDate
				+ "', 'yyyy/mm/dd hh12:mi am') where PRIV_NUMBER = " + privNum;
		int num = db.executeUpdate(sql);
		if (num < 1) {
			throw new ErrorOnDataException(
					"Can't find privilege instance record - " + privNum);
		} else {
			logger.info("Privilege instance valid from/to date time update successfully.");
		}
	}

	public String getCustomerTypeID(String schema, String type) {
		return getCustomerTypeID(schema, type, true);
	}

	public String getCustomerTypeID(String schema, String type, boolean isActive) {
		db.resetSchema(schema);
		String sql = "select ID from d_ref_cust_type where NAME = '" + type
				+ "' " + (isActive ? " and ACTIVE_IND=1" : "");
		logger.info("Execute query: " + sql);
		List<String> results = db.executeQuery(sql, "ID");
		String id = "";
		if (results.size() > 0) {
			id = results.get(0);
		}

		return id;
	}

	public String getCustomerPassTypeID(String schema, String pass) {
		return getCustomerPassTypeID(schema, pass, true);
	}

	public String getCustomerPassTypeID(String schema, String pass,
			boolean isActive) {
		db.resetSchema(schema);
		String sql = "select ID from d_ref_cust_pass_type where NAME = '"
				+ pass + "' " + (isActive ? " and ACTIVE_IND=1" : "");
		logger.info("Execute query: " + sql);
		List<String> results = db.executeQuery(sql, "ID");
		String id = "";
		if (results.size() > 0) {
			id = results.get(0);
		}

		return id;
	}

	public List<String> getCustomerTypes(String schema) {
		db.resetSchema(schema);
		String sql = "select NAME from d_ref_cust_type where ACTIVE_IND = 1";
		logger.info("Execute query: " + sql);
		List<String> results = db.executeQuery(sql, "NAME");

		return results;
	}

	public void insertCustomerType(String schema, String code, String name,
			String dscr) {
		db.resetSchema(schema);
		String sql = "insert into d_ref_cust_type values (get_SEQUENCE('d_ref_cust_type'), '"
				+ code
				+ "', '"
				+ name
				+ "', '"
				+ dscr
				+ "', 1, '', 1, 1, 0, 0)";
		logger.info("Execute query: " + sql);
		db.executeUpdate(sql);
	}

	public List<String> getCustomerPasses(String schema) {
		db.resetSchema(schema);
		String sql = "select NAME from d_ref_cust_pass_type where ACTIVE_IND = 1";
		logger.info("Execute query: " + sql);
		List<String> results = db.executeQuery(sql, "NAME");

		return results;
	}

	/**
	 * update customer class status in LM
	 * 
	 * @param schema
	 * @param className
	 * @param isInactive
	 */
	public void updateCustomerClassStatus(String schema, String className,
			boolean isInactive) {
		logger.info("change customer class status for class name:" + className);
		db.resetSchema(schema);

		String query = "update C_CUST_CLASS set deleted_ind='";
		if (isInactive) {
			query = query + "1' where name='" + className + "'";
		} else {
			query = query + "0' where name='" + className + "'";
		}

		db.executeUpdate(query);
	}

	/**
	 * Update customer class option date of birth indicator
	 * 
	 * @param schema
	 * @param className
	 * @param isDobOptional
	 *            - true:1; false:0
	 */
	public void updateCustomerClassOptionalDobIndicator(String schema,
			String className, boolean isDobOptional) {
		db.resetSchema(schema);
		logger.info("Update customer class('" + className
				+ "') Date Of Birth indicator as " + isDobOptional);

		if (className.equalsIgnoreCase("Individual")) {
			throw new ErrorOnDataException(
					"The Option Date Of Birth is only applied to Non-Individual customer class.");
		}
		String sql = "update C_CUST_CLASS set OPTIONAL_DOB_IND = "
				+ (isDobOptional ? "1" : "0") + " where NAME='" + className
				+ "'";
		int num = db.executeUpdate(sql);
		if (num < 1) {
			throw new ErrorOnDataException(
					"Can't find customer class record by " + className);
		}
	}

	/**
	 * get customer class which status is active
	 * 
	 * @param schema
	 * @return
	 */
	public List<String> getCustomerClass(String schema) {
		db.resetSchema(schema);

		String query = "select name from C_CUST_CLASS where deleted_ind='0' order by name";

		List<String> val = db.executeQuery(query, "NAME");

		return val;
	}

	public List<String> getCustomerIdentifierType(String schema) {
		db.resetSchema(schema);

		String query = "select NAME from C_IDENTIFIER_TYPE";

		List<String> val = db.executeQuery(query, "NAME");

		return val;

	}

	public List<String> getCustomerIdentifierTypeByCustClass(String schema,
			String custclass) {
		db.resetSchema(schema);

		String query = "select C_IDENTIFIER_TYPE.NAME from C_CUST_CLASS_ID_TYPE INNER JOIN C_IDENTIFIER_TYPE ON C_CUST_CLASS_ID_TYPE.ID_TYPE_ID=C_IDENTIFIER_TYPE.ID "
				+ "INNER JOIN C_CUST_CLASS ON C_CUST_CLASS_ID_TYPE.CUST_CLASS_ID=C_CUST_CLASS.ID where C_CUST_CLASS.NAME='"
				+ custclass + "'";

		List<String> val = db.executeQuery(query, "NAME");

		return val;

	}

	public List<String> getCustomerCertificationTypeByCustClass(String schema,
			String custclass) {
		db.resetSchema(schema);

		String query = "select D_CERTIFICATION_TYPE.NAME from C_CUST_CLASS_CERT_TYPE INNER JOIN D_CERTIFICATION_TYPE ON C_CUST_CLASS_CERT_TYPE.CERT_TYPE_ID=D_CERTIFICATION_TYPE.ID "
				+ "INNER JOIN C_CUST_CLASS ON C_CUST_CLASS_CERT_TYPE.CUST_CLASS_ID=C_CUST_CLASS.ID where C_CUST_CLASS.NAME='"
				+ custclass + "'";

		List<String> val = db.executeQuery(query, "NAME");

		return val;

	}

	public List<String> getCustomerEducationType(String schema) {
		db.resetSchema(schema);

		String query = "select NAME from D_EDUCATION_TYPE";

		List<String> val = db.executeQuery(query, "NAME");

		return val;

	}

	public List<String> getCustomerEducationTypeByCustClass(String schema,
			String custclass) {
		db.resetSchema(schema);

		String query = "select D_EDUCATION_TYPE.NAME from C_CUST_CLASS_EDUCATION_TYPE INNER JOIN D_EDUCATION_TYPE ON C_CUST_CLASS_EDUCATION_TYPE.EDUCATION_TYPE_ID=D_EDUCATION_TYPE.ID "
				+ "INNER JOIN C_CUST_CLASS ON C_CUST_CLASS_EDUCATION_TYPE.CUST_CLASS_ID=C_CUST_CLASS.ID where C_CUST_CLASS.NAME='"
				+ custclass + "'";

		List<String> val = db.executeQuery(query, "NAME");

		return val;

	}

	/**
	 * Get education records based on profile first and last name, country and
	 * state
	 * 
	 * @param schema
	 * @param firstName
	 * @param lastName
	 * @param contract
	 *            , such as "Canada"
	 * @param state
	 *            , such as "Saskatchewan"
	 * @return
	 */
	public List<String> getEducationRecords(String schema, String firstName,
			String lastName, String country, String state) {
		db.resetSchema(schema);

		String query = "select * from c_cust_hfp_education "
				+ "where prof_id in (select id from C_CUST_HFPROFILE where cust_id=(select cust_id from C_CUST where f_name ='"
				+ firstName
				+ "' and l_name= '"
				+ lastName
				+ "')) "
				+ "and country_id in (select id from d_ref_country where dscr = '"
				+ country
				+ "') "
				+ "and state_id in (select id from d_ref_state_provnc where dscr = '"
				+ state + "')";

		List<String> val = db.executeQuery(query, "EDU_NUMBER");
		return val;
	}

	public String getEducationNumByType(String schema, String firstName,
			String lastName, String eduType) {
		db.resetSchema(schema);

		String query = "select EDU_NUMBER from c_cust_hfp_education "
				+ "where prof_id in (select id from C_CUST_HFPROFILE where cust_id=(select cust_id from C_CUST where f_name ='"
				+ firstName
				+ "' and l_name= '"
				+ lastName
				+ "')) "
				+ "and edu_type_id=(select id from D_EDUCATION_TYPE where name='"
				+ eduType + "') " + "and status_id=1 and deleted_ind=0";
		logger.info("Execute sql: " + query);
		List<String> vals = db.executeQuery(query, "EDU_NUMBER");
		String val = null;
		if (vals != null && vals.size() > 0) {
			val = vals.get(0);
		}
		logger.info("Get Education Num=" + val);
		return val;
	}

	/**
	 * Delete educaton records based on profile first and last name
	 * 
	 * @param schema
	 * @param firstName
	 * @param lastName
	 */
	public void deleteEducationRecords(String schema, String firstName,
			String lastName) {
		db.resetSchema(schema);

		String query = "delete from c_cust_hfp_education "
				+ "where prof_id in (select id from C_CUST_HFPROFILE where cust_id=(select cust_id from C_CUST where f_name ='"
				+ firstName + "' and l_name= '" + lastName + "'))";
		db.executeUpdate(query);
	}

	/**
	 * Delete education deferral records based on profile first and last name
	 * 
	 * @param schema
	 * @param firstName
	 * @param lastName
	 */
	public void deleteEducationDeferralRecords(String schema, String firstName,
			String lastName) {
		db.resetSchema(schema);

		String query = "delete from C_CUST_HFP_DEFERED_EDU_RECORD "
				+ "where prof_id in (select id from C_CUST_HFPROFILE where cust_id=(select cust_id from C_CUST where f_name ='"
				+ firstName + "' and l_name= '" + lastName + "'))";
		db.executeUpdate(query);
	}

	public String getNumberOfProductAssignmentToStore(String schema,
			String storeName) {
		db.resetSchema(schema);

		String query = "Select count(p_prd_loc.id) as count"
				+ " from p_prd_loc, d_loc, p_prd"
				+ " where p_prd_loc.loc_id = d_loc.id"
				+ " and p_prd.prd_id = p_prd_loc.prd_id"
				+ " and p_prd.active_ind = 1" + " and p_prd_loc.active_ind = 1"
				+ " and d_loc.type_id = 34" + " and d_loc.name = '" + storeName
				+ "'";
		List<String> value = db.executeQuery(query, "count");

		return value.get(0);
	}

	public void unAssignProductToStoreFromDB(String schema, String storeName) {
		db.resetSchema(schema);

		String query = "Select id from d_loc where type_id = 34 and name = '"
				+ storeName + "'";
		List<String> ids = db.executeQuery(query, "id");

		if (ids.size() < 1) {
			throw new ErrorOnDataException(
					"Did not found this store info. StoreName = " + storeName);
		}
		String locationID = ids.get(0);

		String sql = "update p_prd_loc set active_ind = 0 where active_ind = 1 and loc_id = "
				+ locationID;
		db.executeUpdate(sql);

		logger.info("Unassign product to this store successfully. StoreName = "
				+ storeName);
	}

	public void configureAutoApproval(String schema, String loc_name,
			int pmt_grp_id, boolean yes) {
		String msg = yes ? "Turn On " : "Turn Off ";
		db.resetSchema(schema);
		int indicator = yes ? 1 : 0;// 1 means turn on refund auto approval;0
		// means turn off
		String sql = "update F_REFUND_APPR_CONF set auto_approval_ind="
				+ indicator
				+ " where LOCATION_ID=(select ID from d_loc where name='"
				+ loc_name + "' and level_num=40) and SOURCE_PMT_GRP_ID="
				+ pmt_grp_id;
		int num = db.executeUpdate(sql);
		if (num < 1) {
			sql = "insert into F_REFUND_APPR_CONF(ID,LOCATION_ID,SOURCE_PMT_GRP_ID,auto_approval_ind)"
					+ "(select (select max(id) from F_REFUND_APPR_CONF)+rownum, dl.ID ,"
					+ pmt_grp_id
					+ ","
					+ indicator
					+ " from d_loc dl where name='"
					+ loc_name
					+ "' and level_num=40)";
			db.executeUpdate(sql);
		}
		logger.info(msg + " Refund Auto-approval for park " + loc_name);
	}

	public void configureImmediateIssued(String schema, String loc_name,
			int pmt_grp_id, boolean yes) {
		String msg = yes ? "Turn On " : "Turn Off ";
		db.resetSchema(schema);
		int indicator = yes ? 1 : 0;// 1 means turn on refund immediate issued;0
		// means turn off
		String sql = "update F_REFUND_ISSU_CONF set immediate_issu_ind="
				+ indicator
				+ " where LOCATION_ID=(select ID from d_loc where name='"
				+ loc_name + "' and level_num=40) and SOURCE_PMT_GRP_ID="
				+ pmt_grp_id;
		int num = db.executeUpdate(sql);
		if (num < 1) {
			sql = "insert into F_REFUND_ISSU_CONF(ID,LOCATION_ID,SOURCE_PMT_GRP_ID,immediate_issu_ind)"
					+ "(select (select max(id) from F_REFUND_ISSU_CONF)+rownum, dl.ID ,"
					+ pmt_grp_id
					+ ","
					+ indicator
					+ " from d_loc dl where name='"
					+ loc_name
					+ "' and level_num=40)";
			db.executeUpdate(sql);
		}
		logger.info(msg + " Refund immediate-issued for park " + loc_name);
	}

	public void checkTourInventory(String park, String tour, String date,
			String schema) {
		int invs = DataBaseFunctions.getTotalTourInventory(park, tour, date,
				schema);

		if (invs < 1) {
			throw new ItemNotFoundException(
					"There is no tour inventory available for " + tour + " on "
							+ date);
		}
	}

	public void checkPermitInventory(String park, String entrance, String date,
			String schema) {
		int invs = DataBaseFunctions.getTotalPermitInventory(park, entrance,
				date, schema);
		if (invs < 1) {
			throw new ItemNotFoundException(
					"There is no permit inventory available for " + entrance
							+ " on " + date);
		}

	}

	/**
	 * "Tours and Tickets" in "View As List" page
	 * 
	 * @param schema
	 * @param locID
	 * @return
	 */
	public List<String[]> queryTourAndTickets(String schema, String locID) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema
				+ "; Query Tour and Tickets about loc_id:" + locID);
		String[] types = { "PRD_NAME", "SORT_KEY" };

		String query = "select PRD_NAME, SORT_KEY" + " from p_prd"
				+ " where loc_id = " + locID + " and" + " active_ind=1"
				+ " order by SORT_KEY, PRD_NAME";
		List<String[]> result = db.executeQuery(query, types);

		return result;
	}

	public void downloadFile(String filePath) {
		FileDownloadDialogPage downloadPage = FileDownloadDialogPage
				.getInstance();
		File file = new File(filePath);

		if (file.exists()) {
			boolean deleted = file.delete();
			if (!deleted) {
				throw new RuntimeException("Failed to delete existing file - "
						+ filePath);
			}
		}

		downloadPage.downloadSaveFile(filePath);
		int i = 0;
		while (!file.exists()) {
			Browser.sleep(1);
			i++;
			if (i > 120) {
				break;
			}
		}

		if (file.exists()) {
			logger.info("Successfully download file in " + i + " seconds.");
		} else {
			throw new ItemNotFoundException("Can't download file - " + filePath
					+ " in " + i + " seconds.");
		}

		if (downloadPage.exists()) {
			downloadPage.clickClose();
		}
	}

	/**
	 * Get maximum reprint count of document template
	 * 
	 * @param schema
	 * @param docTemplName
	 * @return
	 */
	public int getMaxReprintCountOfDocTemplate(String schema,
			String docTemplName) {
		db.resetSchema(schema);

		logger.info("Get maximum reprint count of Document Template - "
				+ docTemplName);
		String sql = "select MAX_NUM_REPRINT from P_DOCUMENT_TEMPLATE where DOC_TEMPLATE_NAME = '"
				+ docTemplName + "'";
		List<String> result = db.executeQuery(sql, "MAX_NUM_REPRINT");
		logger.info("Execute query: " + sql);
		if (result.size() < 1) {
			throw new ErrorOnDataException(
					"Can't find record identified by Document Template Name - "
							+ docTemplName);
		}
		int maxNum = Integer.parseInt(result.get(0));
		logger.info("Get the Max Reprint count of Docoument Template - "
				+ docTemplName + " is: " + maxNum);
		return maxNum;
	}

	/**
	 * Get over payment allocation info per orderNum and active_ind. If no
	 * record found, return null, otherwise return the first record.
	 * 
	 * @param schema
	 * @param orderNum
	 * @param colnumNames
	 * @param isActive
	 * @return
	 * @author Lesley Wang
	 * @date May 11, 2012
	 */
	public String[] getOverPmtAllocationInfoFromDB(String schema,
			String orderNum, String[] colnumNames, boolean isActive) {

		db.resetSchema(schema);
		logger.info("Query over payment allocation info from DB for order "
				+ orderNum + " and active_ind=" + isActive);
		String query1 = "allo." + colnumNames[0];
		for (int i = 1; i < colnumNames.length; i++) {
			query1 += ", allo." + colnumNames[i];
		}
		String activeInd = isActive ? "1" : "0";
		String query = "select "
				+ query1
				+ " from f_pmt p, o_order o, f_pmt_allocation allo "
				+ "where o.ord_num='"
				+ orderNum
				+ "' and "
				+ "o.id=allo.ord_id and p.id=allo.rfnd_id and allo.active_ind='"
				+ activeInd + "'";

		logger.debug("Execute query: " + query);
		List<String[]> results = db.executeQuery(query, colnumNames);
		if (results.size() < 1) {
			logger.info("There is no overpayment allocation record in DB");
			return null;
		} else {
			return results.get(0);
		}
	}

	public boolean verifyProductExistInSys(String schema, String prdcd,
			String prdname) {
		db.resetSchema(schema);

		logger.info("Verify product if '" + prdname
				+ "' is exist in the System.");

		boolean exist = false;
		String query = "select * from p_prd where prd_name='" + prdname
				+ "' and prd_cd='" + prdcd + "' and DELETED_IND=0";

		List<String> result = db.executeQuery(query, "PRD_ID");
		logger.info("Execute query: " + query);

		if (null != result && result.size() > 0) {
			exist = true;
		}
		logger.info("Product - " + prdname + (exist ? "" : " doesn't")
				+ " exist in System.");
		return exist;
	}

	public boolean verifyOrderExistInSys(String schema, String prdname,
			String transid) {
		db.resetSchema(schema);

		logger.info("Verify product " + prdname
				+ "has make order in the system...");

		boolean exist = false;
		String query = "select o_ord_item.id from o_ord_item, p_prd where o_ord_item.prd_id=p_prd.prd_id and prd_name='"
				+ prdname + "'";

		List<String> result = db.executeQuery(query, "ID");

		if (null != result && result.size() > 0) {
			query = "select * from o_ord_item_trans where ord_item_id='"
					+ result.get(0) + "' and trans_typ_id='" + transid + "'";
			List<String> trans = db.executeQuery(query, "ID");
			if (null != trans && trans.size() > 0) {
				exist = true;
			}
		}
		return exist;
	}

	public List<String[]> getInspectionVehicleOrderInfoUsingPrdname(
			String schema, String prdname) {
		db.resetSchema(schema);

		logger.info("Get vehicle order info by vehicle product " + prdname
				+ "...");

		String query = "select * from o_ord_item INNER JOIN o_vehicle_rti_inst ON o_ord_item.vehicle_rti_inst_id=o_vehicle_rti_inst.id "
				+ "INNER JOIN p_prd ON p_prd.prd_id=o_ord_item.prd_id "
				+ "INNER JOIN o_order ON o_order.id=o_ord_item.ord_id INNER JOIN o_rcpt_ord ON o_ord_item.ord_id=o_rcpt_ord.order_id "
				+ "INNER JOIN o_rcpt ON o_rcpt_ord.rcpt_id=o_rcpt.id INNER JOIN C_CUST_HFPROFILE ON C_CUST_HFPROFILE.ID=O_VEHICLE_RTI_INST.CUST_PROF_ID "
				+ "where p_prd.prd_name='"
				+ prdname
				+ "'"
				+ " Order by o_ord_item.CREATE_DATE DESC";

		String[] col = { "ORD_NUM", "RCPT_NO", "CREATE_DATE", "REG_NUMBER",
				"FEE", "FISCAL_YEAR", "PURCHASE_TYPE", "ORD_PRICE",
				"ORD_BALANCE", "PAID", "CONF_STATUS_ID", "ORD_INVC_ID",
				"CUST_NUMBER", "CUST_PROF_ID" };

		List<String[]> result = db.executeQuery(query, col);
		return result;
	}

	public List<String[]> getRegisterVehicleOrderInfoUsingPrdname(
			String schema, String prdname) {
		db.resetSchema(schema);

		logger.info("Get vehicle order info by vehicle product " + prdname
				+ "...");

		String query = "select * from o_ord_item INNER JOIN o_vehicle_rti_inst ON o_ord_item.vehicle_rti_inst_id=o_vehicle_rti_inst.id "
				+ "INNER JOIN e_vehicle ON o_vehicle_rti_inst.vehicle_id=e_vehicle.id  INNER JOIN p_prd ON p_prd.prd_id=o_ord_item.prd_id "
				+ "INNER JOIN o_order ON o_order.id=o_ord_item.ord_id INNER JOIN o_rcpt_ord ON o_ord_item.ord_id=o_rcpt_ord.order_id "
				+ "INNER JOIN o_rcpt ON o_rcpt_ord.rcpt_id=o_rcpt.id INNER JOIN C_CUST_HFPROFILE ON C_CUST_HFPROFILE.ID=O_VEHICLE_RTI_INST.CUST_PROF_ID "
				+ "where p_prd.prd_name='"
				+ prdname
				+ "'"
				+ " Order by o_ord_item.CREATE_DATE DESC";

		String[] col = { "ORD_NUM", "RCPT_NO", "CREATE_DATE", "VEH_NUMBER",
				"SER_NUMBER", "REG_NUMBER", "FEE", "FISCAL_YEAR",
				"PURCHASE_TYPE", "MODEL_YR", "ORD_PRICE", "ORD_BALANCE",
				"PAID", "CONF_STATUS_ID", "ORD_INVC_ID", "VEH_MANUFACTURER_ID",
				"CUST_NUMBER", "CUST_PROF_ID", "VEHICLE_RTI_INST_ID" };

		List<String[]> result = db.executeQuery(query, col);
		return result;
	}

	public String getHFCustomerEmailInfo(String schema, String custProfId) {
		// db.resetSchema(schema);
		//
		// logger.info("Get customer info using id is " + custProfId + "...");
		//
		// String query =
		// "select * from C_CUST_HFPROFILE INNER JOIN c_cust_phone ON C_CUST_HFPROFILE.CUST_ID=c_cust_phone.CUST_ID "
		// + "where C_CUST_HFPROFILE.ID='"
		// + custProfId
		// + "' AND c_cust_phone.TYP='EMAIL'";
		//
		// String result = db.executeQuery(query, "VAL").get(0);
		// if (result == null) {
		// result = "";
		// }
		// return result;
		return this.getHFCustomerEmailInfo(schema, custProfId, null);
	}

	/**
	 * Get HF Customer Email Info by custotmer profile id and/or customer number
	 */
	public String getHFCustomerEmailInfo(String schema, String custProfId,
			String custNum) {
		db.resetSchema(schema);

		String query = "select * from C_CUST_HFPROFILE INNER JOIN c_cust_phone ON C_CUST_HFPROFILE.CUST_ID=c_cust_phone.CUST_ID "
				+ "where c_cust_phone.TYP='EMAIL'";
		if (StringUtil.notEmpty(custProfId)) {
			query += " AND C_CUST_HFPROFILE.ID='" + custProfId + "'";
		}
		if (StringUtil.notEmpty(custNum)) {
			query += " AND C_CUST_HFPROFILE.cust_number=" + custNum;
		}
		logger.info("Execute sql: " + query);
		String result = db.executeQuery(query, "VAL").get(0);
		if (result == null) {
			result = "";
		}
		logger.info("Get the email info for customer with profile id="
				+ custProfId + ", customer number=" + custNum + ": " + result);
		return result;
	}

	/** Get HF Customer login name by customer number */
	public String getHFLoginNmByCustNum(String schema, String custNum) {
		db.resetSchema(schema);

		String query = "select login_name from C_CUST INNER JOIN C_CUST_HFPROFILE ON C_CUST_HFPROFILE.CUST_ID=C_CUST.CUST_ID "
				+ "where C_CUST_HFPROFILE.cust_number=" + custNum;

		logger.info("Execute sql: " + query);
		String result = db.executeQuery(query, "login_name").get(0);
		if (result == null) {
			result = "";
		}
		logger.info("Get the login name for customer with customer number="
				+ custNum + ": " + result);
		return result;
	}

	/**
	 * Get eft invoice info identified by its corresponding number
	 * 
	 * @param schema
	 * @param invoiceNum
	 * @return
	 */
	public EFTInvoicingInfo getEFTInvoiceInfo(String schema, String invoiceNum) {
		logger.info("Get EFT Invoice info identified by number - " + invoiceNum);

		db.resetSchema(schema);
		String sql = "select fei.ID, fei.EFT_INVOICE_JOB_ID, fei.INV_TYPE, dv.VENDOR_NUM, dv.NAME as VENDOR_NAME, ds.ID as STORE_ID, ds.NAME as STORE_NAME, "
				+ "fei.INV_GROUP_NAME, fei.INVC_STATUS, TO_CHAR(fei.INVC_DATE, 'mm-dd-yyyy') as INVC_DATE, TO_CHAR(fei.PERIOD_DATE, 'mm-dd-yyyy') as PERIOD_DATE, fei.AMOUNT "
				+ "from F_EFT_INVOICE fei, D_VENDOR dv, D_STORE ds where fei.VENDOR_ID=dv.ID and fei.STORE_ID=ds.ID "
				+ "and fei.ID=" + invoiceNum;

		String[] columnNames = new String[] { "ID", "EFT_INVOICE_JOB_ID",
				"INV_TYPE", "VENDOR_NUM", "VENDOR_NAME", "STORE_ID",
				"STORE_NAME", "INV_GROUP_NAME", "INVC_STATUS", "INVC_DATE",
				"PERIOD_DATE", "AMOUNT" };
		logger.info("Execute query: " + sql);
		List<String[]> results = db.executeQuery(sql, columnNames);

		if (results.size() < 1) {
			throw new ErrorOnDataException(
					"Can't find any EFT Invoice record identified by incoice num - "
							+ invoiceNum);
		}

		EFTInvoicingInfo invoice = new EFTInvoicingInfo();
		invoice.setInvoiceNum(invoiceNum);
		invoice.setInvoiceJobId(results.get(0)[1]);
		invoice.setInvoiceType(results.get(0)[2]);
		invoice.setVendorNum(results.get(0)[3]);
		invoice.setVendorName(results.get(0)[4]);
		invoice.setAgentNum(results.get(0)[5]);
		invoice.setAgentName(results.get(0)[6]);
		invoice.setInvoiceGrouping(results.get(0)[7]);
		if (invoice.getInvoiceType().equals(EFT_TYPE_EDI_CODE)) {
			invoice.setInvoiceGrouping(results.get(0)[7].equals("Applied") ? "Debit"
					: "Credit");
		}
		invoice.setStatus(results.get(0)[8]);
		invoice.setInvoiceDate(results.get(0)[9]);
		invoice.setPeriodDate(results.get(0)[10]);
		invoice.setAmount(results.get(0)[11]);

		return invoice;
	}

	public String getStoreEFTAdjInvoiceNum(String schema, String storeEFTAdjuID) {
		db.resetSchema(schema);

		logger.info("Get Store EFT Adjustment Invoice Number info by Store Adjustment ID "
				+ storeEFTAdjuID + "...");

		String query = "select eft_invoice_id from f_deft where store_eftadj_id = "
				+ storeEFTAdjuID;

		List<String> results = db.executeQuery(query, "eft_invoice_id");

		if (results.size() != 1) {
			throw new ItemNotFoundException(
					"Did not found Store EFT Adjustment Invoice Number by Store Adjustment ID = "
							+ storeEFTAdjuID);
		} else {
			logger.info("Store EFT Adjustment Invoice Number = "
					+ results.get(0) + " by Store Adjustment ID = "
					+ storeEFTAdjuID);
		}

		return results.get(0);
	}

	public List<EFTInvoiceTransmissionInfo> getEFTInvoiceTransmissionInfo(
			String schema, String invoiceNum) {

		logger.info("Get EFT Invoice Transmission info by invoice number - "
				+ invoiceNum);
		db.resetSchema(schema);
		String sql = "select * from F_EFT_INVC_TRANSM where EFT_INVOICE_ID='"
				+ invoiceNum + "'";
		String[] columnNames = new String[] { "EFT_INVOICE_ID", "STATUS",
				"TRANS_DUE_DATE", "CREATE_DATE", "EFT_transmission_id",
				"Amount", "EFT_Transmission_job_ID" };

		List<String[]> results = db.executeQuery(sql, columnNames);
		List<EFTInvoiceTransmissionInfo> transmission = new ArrayList<EFTInvoiceTransmissionInfo>();
		for (String[] info : results) {
			EFTInvoiceTransmissionInfo trans = new EFTInvoiceTransmissionInfo();
			trans.setInvoiceId(info[0]);
			trans.setStatus(info[1]);
			trans.setTransDueDate(info[2].split(" ")[0]);
			trans.setCreateDate(info[3].split(" ")[0]);
			trans.setTransmissionID(info[4]);
			trans.setInvoiceAmount(info[5]);
			trans.setTransJobID(info[6]);
			transmission.add(trans);
		}
		return transmission;
	}

	public List<EFTInvoiceTransactionInfo> getEFTInvoiceTransactionInfo(
			String schema, String invoiceNum) {
		logger.info("Get EFT Invoice Transaction info identified by invoice Number - "
				+ invoiceNum);

		db.resetSchema(schema);

		String sql = "select feit.ID, feit.EFT_INVOICE_ID, feit.INV_TRANS_TYPE, TO_CHAR(feit.CREATE_DATE, 'mm-dd-yyyy') as CREATE_DATE, feit.EFT_RETURN_TRANSACTION_ID, "
				+ "feit.TRANSACTION_COMMENT, feit.OLD_INVOICE_AMOUNT, feit.NEW_INVOICE_AMOUNT, feit.EFT_TRANSMISSION_JOB_ID, dua.FIRST_NAME, dua.LAST_NAME, dl.NAME as LOC_NAME "
				+ "from F_EFT_INV_TRANSACTION feit, D_USER_AUTH dua, D_LOC dl where feit.USER_ID=dua.ID and feit.LOC_ID=dl.ID and EFT_INVOICE_ID = "
				+ invoiceNum;

		String columnNames[] = new String[] { "ID", "EFT_INVOICE_ID",
				"INV_TRANS_TYPE", "CREATE_DATE", "EFT_RETURN_TRANSACTION_ID",
				"TRANSACTION_COMMENT", "OLD_INVOICE_AMOUNT",
				"NEW_INVOICE_AMOUNT", "EFT_TRANSMISSION_JOB_ID", "FIRST_NAME",
				"LAST_NAME", "LOC_NAME" };
		logger.info("Execute query: " + sql);

		List<String[]> results = db.executeQuery(sql, columnNames);
		if (results.size() < 1) {
			throw new ErrorOnDataException(
					"Can't find any EFT Invoice transaction record identified by invoice number - "
							+ invoiceNum);
		}

		List<EFTInvoiceTransactionInfo> transactionInfos = new ArrayList<EFTInvoiceTransactionInfo>();
		for (int i = 0; i < results.size(); i++) {
			EFTInvoiceTransactionInfo transaction = new EFTInvoiceTransactionInfo();

			transaction.setId(results.get(i)[0]);
			transaction.setInvoiceId(results.get(i)[1]);
			transaction.setType(results.get(i)[2]);
			transaction.setTransactionDate(results.get(i)[3]);
			transaction.setReturnTransactionId(results.get(i)[4]);
			transaction.setComment(results.get(i)[5]);
			transaction
					.setOldInvoiceAmount(Double.parseDouble(results.get(i)[6]));
			transaction
					.setNewInvoiceAmount(Double.parseDouble(results.get(i)[7]));
			transaction.setTransmissionJobId(results.get(i)[8]);
			transaction.setTransactionUser(results.get(i)[10] + ","
					+ results.get(i)[9]);
			transaction.setTransactionLocation(results.get(i)[11]);

			transactionInfos.add(transaction);
		}

		return transactionInfos;
	}

	/**
	 * Verify there are records exist in the table F_DEFT identified by eft
	 * invoice job id and eft invoice id
	 * 
	 * @param schema
	 * @param invoiceJobId
	 * @param invoiceId
	 * @return
	 */
	public boolean verifyInvoiceDailyEFTInfo(String schema,
			String invoiceJobId, String invoiceId) {
		logger.info("Check there are records exist in table F_DEFT query by EFT Invoice Job Id - "
				+ invoiceJobId + " and Invoice Id - " + invoiceId);

		db.resetSchema(schema);
		String sql = "select ID from F_DEFT where EFT_INVOICE_JOB_ID="
				+ invoiceJobId + " and EFT_INVOICE_ID=" + invoiceId;
		List<String> results = db.executeQuery(sql, "ID");
		if (results.size() < 1) {
			throw new ErrorOnDataException(
					"There is not any records in F_DEFT table identified by EFT Invoice Job ID - "
							+ invoiceJobId + " and Invoice ID - " + invoiceId);
		} else
			logger.info("Verify table F_DEFT correctly.");

		return true;
	}

	public String getRemittanceNumberAtDEFTTable(String schema,
			String invoiceJob, String invoiceNum) {
		db.resetSchema(schema);
		String remittanceNum;

		logger.info("Get remitance number At DEFT Table. By invoice Job ID = "
				+ invoiceJob + ", invoice number = " + invoiceNum);

		String query = "select eft_remittance_id from f_deft where eft_invoice_job_id = "
				+ invoiceJob + " and eft_invoice_id = " + invoiceNum;

		List<String> results = db.executeQuery(query, "eft_remittance_id");

		if (results.size() < 1) {
			remittanceNum = "";
		} else {
			remittanceNum = results.get(0);
		}

		return remittanceNum;
	}

	/**
	 * Verify there are records exist in table F_DEFT identified by eft invoice
	 * job id and remittance id
	 * 
	 * @param schema
	 * @param invoiceJobId
	 * @param remittanceId
	 * @return
	 */
	public void verifyRemittanceDailyEFTInfo(String schema,
			String invoiceJobId, String remittanceId) {
		logger.info("Check there are records exist in table F_DEFT query by EFT Invoice Job Id - "
				+ invoiceJobId + " and Remittance Id - " + remittanceId);

		db.resetSchema(schema);
		String sql = "select ID from F_DEFT where EFT_INVOICE_JOB_ID="
				+ invoiceJobId + " and EFT_REMITTANCE_ID=" + remittanceId;
		List<String> results = db.executeQuery(sql, "ID");
		if (results.size() < 1) {
			throw new ErrorOnDataException(
					"There is not any records in F_DEFT table identified by EFT Invoice Job ID - "
							+ invoiceJobId + " and Remittance ID - "
							+ remittanceId);
		} else
			logger.info("Verify table F_DEFT correctly.");

	}

	public String getInvoiceAmount(String schema, String invoiceJobId,
			String invoiceId, String type, String... ordNum) {
		db.resetSchema(schema);

		String orders = "";
		if (null != ordNum) {
			for (int i = 0; i < ordNum.length; i++) {
				if (i == 0) {
					orders = "'" + ordNum[i] + "'";
				} else {
					orders = orders + ",'" + ordNum[i] + "'";
				}
			}
		}

		String sql = "select AMOUNT from F_DEFT where EFT_INVOICE_JOB_ID="
				+ invoiceJobId + " and EFT_INVOICE_ID=" + invoiceId;
		if (null != type) {
			sql = sql + " and DEFT_TYPE_ID=" + type;
		}
		if (orders.length() > 2) {
			sql = sql + " and ORD_NUM in (" + orders.trim() + ")";
		}

		List<String> results = db.executeQuery(sql, "AMOUNT");
		BigDecimal amount = BigDecimal.ZERO;
		for (String amt : results) {
			if (amt.contains("-")) {
				amt = amt.replaceAll("-", "");
				amount = amount.subtract(new BigDecimal(amt));
			} else {
				amount = amount.add(new BigDecimal(amt));
			}
		}

		return amount.toString();
	}

	public String getInvoiceAmountOfStoreAdjustment(String schema,
			String invoiceJobId, String invoiceId, String type,
			String adjustmentID) {
		db.resetSchema(schema);

		String sql = "select AMOUNT from F_DEFT where EFT_INVOICE_JOB_ID="
				+ invoiceJobId + " and EFT_INVOICE_ID=" + invoiceId
				+ " and STORE_EFTADJ_ID=" + adjustmentID;
		if (null != type) {
			sql = sql + " and DEFT_TYPE_ID=" + type;
		}
		logger.info("Execute SQL: " + sql);

		List<String> results = db.executeQuery(sql, "AMOUNT");
		BigDecimal amount = BigDecimal.ZERO;
		for (String amt : results) {
			if (amt.contains("-")) {
				amt = amt.replaceAll("-", "");
				amount = amount.subtract(new BigDecimal(amt));
			} else {
				amount = amount.add(new BigDecimal(amt));
			}
		}

		return amount.toString();
	}

	public boolean checkEFTScheduleExists(String schema, String name) {
		db.resetSchema(schema);

		String query = "select count(*) as num from f_eft_schedule where name='"
				+ name + "'";

		int num = Integer.parseInt(db.executeQuery(query.toString(), "num", 0));

		return num > 0;
	}

	/**
	 * Get Invoice Amount by invoice job id, invoice id, and deposit id
	 * 
	 * @param schema
	 * @param invoiceJobId
	 * @param invoiceId
	 * @param type
	 * @param depositID
	 * @return
	 */
	public String getInvoiceAmountOfDepositAdjustment(String schema,
			String invoiceJobId, String invoiceId, String type, String depositID) {
		db.resetSchema(schema);

		String sql = "select AMOUNT from F_DEFT where EFT_INVOICE_JOB_ID="
				+ invoiceJobId + " and EFT_INVOICE_ID=" + invoiceId
				+ " and dep_id=" + depositID;
		if (null != type) {
			sql = sql + " and DEFT_TYPE_ID=" + type;
		}
		logger.info("Execute SQL: " + sql);

		List<String> results = db.executeQuery(sql, "AMOUNT");
		BigDecimal amount = BigDecimal.ZERO;
		for (String amt : results) {
			if (amt.contains("-")) {
				amt = amt.replaceAll("-", "");
				amount = amount.subtract(new BigDecimal(amt));
			} else {
				amount = amount.add(new BigDecimal(amt));
			}
		}

		return amount.toString();
	}

	/**
	 * Get vendor Cashflow amount from db
	 * 
	 * @param invoiceNumber
	 * @param transactionTypeID
	 * @param schema
	 * @return
	 */
	public String getVendorCashflowAmount(String invoiceNumber,
			String transactionTypeID, String schema) {
		String amount = this.getCashflowAmount(invoiceNumber,
				transactionTypeID, schema, true);
		return amount;
	}

	/**
	 * Get Store Cashflow amount from db
	 * 
	 * @param invoiceNumber
	 * @param transactionType
	 * @param schema
	 * @return
	 */
	public String getStoreCashflowAmount(String invoiceNumber,
			String transactionTypeID, String schema) {
		String amount = this.getCashflowAmount(invoiceNumber,
				transactionTypeID, schema, false);
		return amount;
	}

	private String getCashflowAmount(String invoiceNumber,
			String transactionTypeID, String schema, boolean isVendor) {
		logger.info("Get Cashflow amount from DB.");
		String amount;

		db.resetSchema(schema);
		String query = "SELECT AMOUNT FROM ";
		if (isVendor) {
			query = query + "F_EFT_VENDOR_CASHFLOW ";
		} else {
			query = query + "F_EFT_STORE_CASHFLOW ";
		}
		query = query + "WHERE EFT_INVOICE_ID=" + invoiceNumber
				+ " AND TRANS_TYPE=" + transactionTypeID;

		logger.info("Execute query: " + query);
		List<String> results = db.executeQuery(query, "AMOUNT");

		if (results.size() > 0) {
			amount = results.get(0).trim();
		} else {
			amount = "";
		}
		logger.info("AMOUNT is:" + amount);
		return amount;
	}

	/**
	 * Get ticket instance ID of TPA
	 * 
	 * @param schema
	 * @param ordNum
	 * @param prdName
	 * @param ticketType
	 * @param tapValueOfTicketInstance
	 * @return
	 */
	public String getTicketInstanceIDOfTPA(String schema, String ordNum,
			String prdName, String ticketType,
			Map<String, String> tapValueOfTicketInstance) {

		db.resetSchema(schema);
		String id = "";

		String query = "select o_ticket_inst.id from o_ticket_inst, o_tour_instance, o_ord_item, o_order, p_prd, p_admission_type "
				+ "where o_ticket_inst.tour_instance_id = o_tour_instance.id "
				+ "and o_tour_instance.ord_item_id = o_ord_item.id "
				+ "and o_ord_item.ord_id = o_order.id "
				+ "and o_ord_item.prd_id = p_prd.prd_id "
				+ "and p_admission_type.id = o_ticket_inst.admission_type_id "
				+ "and o_order.ord_num = '"
				+ ordNum
				+ "' "
				+ "and prd_name = '"
				+ prdName
				+ "' "
				+ "and p_admission_type.name = '" + ticketType + "'";

		logger.debug("Execute query: " + query);
		List<String> results = db.executeQuery(query, "id");

		for (int i = 0; i < results.size(); i++) {
			boolean tpaCompareFlag = true;
			query = "select attr_id,attr_value from o_ticket_inst_attr_value "
					+ "where ticket_inst_id = " + results.get(i);
			String[] colnumNames = new String[] { "attr_id", "attr_value" };

			logger.debug("Execute query: " + query);
			List<String[]> valueResults = db.executeQuery(query, colnumNames);

			for (int j = 0; j < valueResults.size(); j++) {
				String key = this.convertAttribute(valueResults.get(j)[0]);
				if (key.equals("Date of Birth")) {
					if (DateFunctions.compareDates(
							tapValueOfTicketInstance.get(key),
							valueResults.get(j)[1]) != 0) {
						tpaCompareFlag &= false;
					}
				} else if (key.equals("Power")) {
					if (!tapValueOfTicketInstance.get(key).toLowerCase()
							.contains(valueResults.get(j)[1].toLowerCase())) {
						tpaCompareFlag &= false;
					}
				} else {
					if (!tapValueOfTicketInstance.get(key).equals(
							valueResults.get(j)[1])) {
						tpaCompareFlag &= false;
					}
				}
			}

			if (tpaCompareFlag) {
				id = results.get(i);
				break;
			}
		}

		return id;
	}

	/**
	 * Get ticket instance status of TPA
	 * 
	 * @param schema
	 * @param ordNum
	 * @param prdName
	 * @param ticketType
	 * @param tapValueOfTicketInstance
	 * @return
	 */
	public String getTicketInstanceStatusOfTPA(String schema, String ordNum,
			String prdName, String ticketType,
			Map<String, String> tapValueOfTicketInstance) {
		String ticketInstanceID = this.getTicketInstanceIDOfTPA(schema, ordNum,
				prdName, ticketType, tapValueOfTicketInstance);

		db.resetSchema(schema);

		String query = "select status from o_ticket_inst where id = "
				+ ticketInstanceID;

		logger.debug("Execute query: " + query);
		List<String> results = db.executeQuery(query, "status");

		if (results.size() < 1) {
			logger.info("There is no ticket instance info record in DB");
			return null;
		} else {
			int status = Integer.parseInt(results.get(0));
			if (status == 1) {
				return ACTIVE_STATUS;
			} else if (status == 2) {
				return INACTIVE_STATUS;
			} else if (status == 3) {
				return VOIDED_STATUS;
			} else {
				return null;
			}
		}
	}

	private String convertAttribute(String from) {
		String to = "";
		String attributeStrs[] = new String[] { "First Name", "Date of Birth",
				"Visited Times", "Arrival Time", "Power", "Last Name",
				"Special Considerations" };
		String attributeIDs[] = new String[] { "2631", "2632", "2633", "2634",
				"2635", "2636", "2637" };
		String newattributeIDs[] = new String[] { "2000003", "", "", "", "",
				"2000005", "" };

		if (from.matches("[0-9]+")) {
			for (int i = 0; i < attributeIDs.length; i++) {
				if (from.equals(attributeIDs[i])) {
					to = attributeStrs[i];
					break;
				}
			}
			if (StringUtil.isEmpty(to)) {
				for (int i = 0; i < newattributeIDs.length; i++) {
					if (from.equals(newattributeIDs[i])) {
						to = attributeStrs[i];
						break;
					}
				}
			}
		} else {
			for (int i = 0; i < attributeStrs.length; i++) {
				if (from.equals(attributeStrs[i])) {
					to = attributeIDs[i];
					break;
				}
			}
			if (StringUtil.isEmpty(to)) {
				for (int i = 0; i < attributeStrs.length; i++) {
					if (from.equals(attributeStrs[i])) {
						to = newattributeIDs[i];
						break;
					}
				}
			}
		}
		if (to.length() < 1) {
			throw new ItemNotFoundException("Unkown TPA attribute - " + from);
		}

		return to;
	}

	public List<EFTRemittanceInfo> getRemittanceInfoUsingInvoiceID(
			String schema, String invoiceNum) {
		db.resetSchema(schema);

		logger.info("Get remittance info by invoice number " + invoiceNum
				+ "...");

		String query = "select F_EFT_REMITTANCE.ID,F_EFT_REMITTANCE.EFT_INVOICE_ID, F_EFT_REMITTANCE.EFT_INVOICE_JOB_ID, F_EFT_REMITTANCE.VENDOR_ID, F_EFT_REMITTANCE.STORE_ID, F_ACCT.CD as ACCNT_CD,F_EFT_REMITTANCE.REMIT_DATE,"
				+ "F_EFT_REMITTANCE.PERIOD_DATE,F_EFT_REMITTANCE.STATUS,F_EFT_REMITTANCE.AMOUNT,D_VENDOR.VENDOR_NUM,D_VENDOR.NAME as VENDOR_NAME,D_STORE.NAME as STORE_NAME,F_ACCT.NAME as ACCOUNT_NAME "
				+ "from F_EFT_REMITTANCE INNER JOIN D_VENDOR ON D_VENDOR.ID=F_EFT_REMITTANCE.VENDOR_ID "
				+ "INNER JOIN D_STORE ON D_STORE.ID=F_EFT_REMITTANCE.STORE_ID INNER JOIN F_ACCT ON F_ACCT.ID=F_EFT_REMITTANCE.ACCNT_ID "
				+ "where EFT_INVOICE_ID='"
				+ invoiceNum
				+ "'"
				+ " order by F_EFT_REMITTANCE.ID";
		String[] col = { "ID", "EFT_INVOICE_ID", "EFT_INVOICE_JOB_ID",
				"VENDOR_ID", "STORE_ID", "ACCNT_CD", "REMIT_DATE",
				"PERIOD_DATE", "STATUS", "AMOUNT", "VENDOR_NUM", "VENDOR_NAME",
				"STORE_NAME", "ACCOUNT_NAME" };

		List<String[]> results = db.executeQuery(query, col);
		List<EFTRemittanceInfo> remittances = new ArrayList<EFTRemittanceInfo>();
		for (String[] info : results) {
			EFTRemittanceInfo remittance = new EFTRemittanceInfo();
			remittance.setRemittanceNumber(info[0]);
			remittance.setInvoiceID(info[1]);
			remittance.setInvoiceJobID(info[2]);
			remittance.setVendorID(info[3]);
			remittance.setStoreID(info[4]);
			remittance.setRemittanceDate(info[6].split(" ")[0]);
			remittance.setPeriodDate(info[7].split(" ")[0]);
			remittance.setStatus(info[8]);
			remittance.setAccountCode(info[13] + "-" + info[5]);
			remittance.setAmount(info[9]);
			remittance.setVendorNum(info[10]);
			remittance.setVendorName(info[11]);
			remittance.setStoreName(info[12]);
			remittances.add(remittance);
		}

		return remittances;
	}

	public List<EFTRemittanceTransInfo> getRemittanceTransInfoUsingInvoiceID(
			String schema, String invoiceNum) {
		db.resetSchema(schema);

		logger.info("Get remittance transmission info by invoice number "
				+ invoiceNum + "...");

		String query = "select * from F_EFT_REMITT_TRANSM INNER JOIN F_EFT_REMITTANCE ON F_EFT_REMITTANCE.ID =F_EFT_REMITT_TRANSM.EFT_REMITTANCE_ID "
				+ "where EFT_INVOICE_ID='" + invoiceNum + "'";
		String[] col = { "EFT_REMITTANCE_ID", "STATUS", "CREATE_DATE", "AMOUNT" };
		List<String[]> results = db.executeQuery(query, col);
		List<EFTRemittanceTransInfo> trans = new ArrayList<EFTRemittanceTransInfo>();

		for (String[] info : results) {
			EFTRemittanceTransInfo remittance = new EFTRemittanceTransInfo();
			remittance.setRemittanceNumber(info[0]);
			remittance.setStatus(info[1]);
			remittance.setDate(info[2].split(" ")[0]);
			remittance.setAmount(info[3]);
			trans.add(remittance);
		}

		return trans;
	}

	/**
	 * Get the gift card program id from DB per the gift card name
	 * 
	 * @param gcName
	 * @param schema
	 * @return
	 * @author Lesley Wang
	 * @date May 25, 2012
	 */
	public String getGiftCardPrgIDByName(String gcName, boolean isActive,
			String schema) {
		logger.info("Query Gift Card Program ID By Gift Card Name");

		db.resetSchema(schema);
		String query = "select id from p_voucher_program where name='" + gcName
				+ "' and ACTIVE_IND=" + (isActive ? "'1'" : "'0'");
		logger.info("execute query: " + query);
		String result = db.executeQuery(query, "id", 0);
		logger.info("The gift card program id is " + result);
		return result;
	}

	/**
	 * Update tour TPAs status via DB
	 * 
	 * @param schema
	 * @param tourName
	 * @param active_ind
	 *            : 1:Active, 0:Inactive
	 * @param deleted_ind
	 *            : 1:Delete, 0:Not Delete
	 * @param visible_ind
	 *            : 1:Visible, 0:Not Visible
	 * @param sales_category
	 *            : 1:Individual Participant Data, 2:Organization Participant
	 *            Data
	 */
	public void updateTourTPAsStatusViaDB(String schema, String tourName,
			String active_ind, String deleted_ind, String visible_ind,
			String sales_category) {
		db.resetSchema(schema);

		logger.info("Try to update Tour "
				+ (sales_category.equals("1") ? "Individual " : "Organization ")
				+ " Participant Data status, active_ind:"
				+ (active_ind.equals("1") ? "true" : "false")
				+ (deleted_ind.equals("1") ? "true" : "false")
				+ (visible_ind.equals("1") ? "true" : "false"));
		String sql = "update d_attr_ext set active_ind = "
				+ active_ind
				+ ", deleted_ind = "
				+ deleted_ind
				+ ", visible_ind = "
				+ visible_ind
				+ ""
				+ "where attr_id in (select attr_id from d_attr_object where object_id in (select ID from D_TOUR_PARTICIPANT_ATTR_INFO where tour_id in (select prd_id from p_prd where prd_name = '"
				+ tourName + "') and sales_category_id=" + sales_category
				+ "))";

		logger.info("SQL:" + sql);
		int num = db.executeUpdate(sql);
		if (num < 1) {
			throw new ItemNotFoundException(
					"No matched Tour TPAs have been updated.");
		}
		logger.info("Successfully update Tour "
				+ (sales_category.equals("1") ? "Individual " : "Organization")
				+ " Participant Data status, active_ind:"
				+ (active_ind.equals("1") ? "true" : "false")
				+ ", deleted_ind:"
				+ (deleted_ind.equals("1") ? "true" : "false")
				+ ", visible_ind:"
				+ (visible_ind.equals("1") ? "true" : "false"));
	}

	/**
	 * Get the number of Tour participant Attributes from DB
	 * 
	 * @param schema
	 * @param tourName
	 * @param sales_category
	 *            : 1:Individual Participant Data, 2:Organization Participant
	 *            Data
	 * @return
	 */
	public int getNumberOfTPAsFromDb(String schema, String tourName,
			String sales_category) {
		logger.info("Get the number of"
				+ (sales_category.equals("1") ? " Individual "
						: " Organization ") + "TPAs for tour with name--"
				+ tourName);
		db.resetSchema(schema);

		String sql = "select * from d_attr_ext "
				+ "where attr_id in (select attr_id from d_attr_object where object_id in (select ID from D_TOUR_PARTICIPANT_ATTR_INFO where tour_id in (select prd_id from p_prd where prd_name = '"
				+ tourName
				+ "')"
				+ (!StringUtil.isEmpty(sales_category) ? "and sales_category_id="
						+ sales_category
						: "") + "))";

		List<String> tpasRecords = db.executeQuery(sql, "ATTR_ID");

		return tpasRecords.size();
	}

	/**
	 * Get tour id from DB
	 * 
	 * @param schema
	 * @param tourParkId
	 * @param tourName
	 * @return
	 */
	public String getTourIdFromDb(String schema, String tourParkId,
			String tourName) {
		logger.info("Get tour id with tour park id:" + tourParkId
				+ ", tour name:" + tourName);
		db.resetSchema(schema);

		String sql = "select prd_id from p_prd" + " where" + " park_id = "
				+ tourParkId + " and prd_name = '" + tourName
				+ "' and product_cat_id = " + PRDCAT_TICKET;

		return db.executeQuery(sql, "prd_id", 0);
	}

	/**
	 * Get all tour names in park level
	 * 
	 * @param schema
	 * @param tourParkId
	 * @return
	 */
	public List<String> getAllTourNamesInParkLevel(String schema,
			String tourParkId) {
		logger.info("Get tour names in tour park (id:" + tourParkId
				+ ") level.");
		db.resetSchema(schema);

		String sql = "select prd_name from p_prd where park_id = " + tourParkId
				+ " and product_cat_id = " + PRDCAT_TICKET
				+ " and active_ind=1 order by prd_name asc";

		return db.executeQuery(sql, "prd_name");
	}

	/**
	 * Get all sites in Loop level under specific park
	 * 
	 * @param schema
	 * @param parkID
	 * @param loopName
	 * @return
	 */
	public List<String> getAllSiteCodesInLoopLevel(String schema,
			String parkID, String loopName) {
		logger.info("Get all sites under loopName:" + loopName + " and parkId:"
				+ parkID);
		db.resetSchema(schema);

		String sql = "select prd_cd from p_prd where park_id = "
				+ parkID
				+ " "
				+ "and loc_id in (select id from d_loc where name = '"
				+ loopName
				+ "') and active_ind = 1 and import_reservable='Y' and import_web_reservable = 'Y' order by prd_cd asc";

		return db.executeQuery(sql, "prd_cd");
	}

	/**
	 * Get all site codes in park level
	 * 
	 * @param schema
	 * @param parkID
	 * @param prd_rel_type
	 *            : 1- site specific, 2 - Non site specific parent, 3 - Non site
	 *            specific child.
	 * @return
	 */
	public List<String> getAllSiteCodesInParkLevel(String schema,
			String parkID, String prd_rel_type) {
		logger.info("Get all sites under parkId:" + parkID);
		db.resetSchema(schema);

		String sql = "select pp.prd_cd as SiteCodes from p_prd pp, d_loc dl "
				+ "where pp.loc_id = dl.id and pp.park_id = "
				+ parkID
				+ (!StringUtil.isEmpty(prd_rel_type) ? " and pp.prd_rel_type = "
						+ prd_rel_type
						: "") + " and pp.active_ind = 1 "
				+ "and pp.import_reservable='Y' "
				+ "and pp.import_web_reservable = 'Y' "
				+ "order by pp.prd_cd asc";

		return db.executeQuery(sql, "SiteCodes");
	}

	/**
	 * Get all site loops in park level
	 * 
	 * @param schema
	 * @param parkID
	 * @param prd_rel_type
	 *            : 1- site specific, 2 - Non site specific parent, 3 - Non site
	 *            specific child.
	 * @return
	 */
	public List<String> getAllLoopsInParkLevel(String schema, String parkID,
			String prd_rel_type) {
		logger.info("Get all sites under parkId:" + parkID);
		db.resetSchema(schema);

		String sql = "select dl.name as Loops from p_prd pp, d_loc dl "
				+ "where pp.loc_id = dl.id and pp.park_id = "
				+ parkID
				+ (!StringUtil.isEmpty(prd_rel_type) ? " and pp.prd_rel_type = "
						+ prd_rel_type
						: "") + " and pp.active_ind = 1 "
				+ "and pp.import_reservable='Y' "
				+ "and pp.import_web_reservable = 'Y' "
				+ "order by pp.prd_cd asc";

		return db.executeQuery(sql, "Loops");
	}

	/**
	 * Delete all Individual or Organization tour participants from DB
	 * 
	 * @param schema
	 * @param tourName
	 * @param sales_category
	 *            : 1:Individual Participant Data, 2:Organization Participant
	 *            Data
	 */
	public void deleteAllTPAsFromDb(String schema, String tourName,
			String sales_category) {
		logger.info("Delete all"
				+ (sales_category.equals("1") ? " Individual "
						: " Organization ") + "TPAs for tour with name--"
				+ tourName);
		db.resetSchema(schema);

		String query = "delete from d_attr_ext "
				+ "where attr_id in (select attr_id from d_attr_object where object_id in (select ID from D_TOUR_PARTICIPANT_ATTR_INFO where tour_id in (select prd_id from p_prd where prd_name = '"
				+ tourName
				+ "')"
				+ (!StringUtil.isEmpty(sales_category) ? "and sales_category_id="
						+ sales_category
						: "") + "))";
		logger.info("execute query: " + query);
		db.executeUpdate(query);
	}

	public void updateTourTPAsSortOrderViaDB(String schema, String tourName,
			String sales_category, String tpaAttrNames[], String sortOrders[]) {
		if (tpaAttrNames == null || sortOrders == null
				|| tpaAttrNames.length != sortOrders.length) {
			throw new ErrorOnDataException(
					"TPS name and sortOrder should has same length.");
		}
		for (int i = 0; i < tpaAttrNames.length; i++) {
			this.updateTourTPAsSortOrderViaDB(schema, tourName, sales_category,
					tpaAttrNames[i], sortOrders[i]);
		}

	}

	/**
	 * 
	 * @param schema
	 * @param tourName
	 * @param sales_category
	 *            : 1:Individual Participant Data, 2:Organization Participant
	 *            Data
	 * @param tpaName
	 *            --tpa attribute name
	 * @param sortOrder
	 */
	public void updateTourTPAsSortOrderViaDB(String schema, String tourName,
			String sales_category, String tpaName, String sortOrder) {
		db.resetSchema(schema);

		logger.info("Try to update Tour "
				+ (sales_category.equals("1") ? "Individual " : "Organization ")
				+ " Participant Data status, sortOrder:" + sortOrder);
		String sql = "update d_attr_ext set display_seq_no = "
				+ sortOrder
				+ " "
				+ "where attr_name = '"
				+ tpaName
				+ "' and attr_id in (select attr_id from d_attr_object where object_id in (select ID from D_TOUR_PARTICIPANT_ATTR_INFO where tour_id in (select prd_id from p_prd where prd_name = '"
				+ tourName + "') and sales_category_id=" + sales_category
				+ "))";
		int num = db.executeUpdate(sql);
		if (num < 1) {
			throw new ItemNotFoundException(
					"No matched Tour TPAs have been updated.");
		}
		logger.info("Successfully update Tour "
				+ (sales_category.equals("1") ? "Individual " : "Organization ")
				+ " Participant Data status, attr name:" + tpaName
				+ ", sortOrder:" + sortOrder);
	}

	/**
	 * 
	 * @param schema
	 * @param tourName
	 * @param sales_category
	 *            : 1:Individual Participant Data, 2:Organization Participant
	 *            Data
	 * @param sortOrder
	 */
	public void updateTourTPADefaultValueViaDB(String schema, String tourName,
			String sales_category, String tpaName, String defaultValue) {
		db.resetSchema(schema);

		logger.info("Try to update Tour "
				+ (sales_category.equals("1") ? "Individual " : "Organization ")
				+ " Participant Data status, default value:" + defaultValue);
		String sql = "update d_attr_ext set default_value = '"
				+ defaultValue
				+ "' "
				+ "where attr_name = '"
				+ tpaName
				+ "' and attr_id in (select attr_id from d_attr_object where object_id in (select ID from D_TOUR_PARTICIPANT_ATTR_INFO where tour_id in (select prd_id from p_prd where prd_name = '"
				+ tourName + "') and sales_category_id=" + sales_category
				+ "))";
		int num = db.executeUpdate(sql);
		logger.info("SQL:" + sql);

		if (num < 1) {
			throw new ItemNotFoundException(
					"No matched Tour TPAs have been updated.");
		}
		logger.info("Successfully update Tour "
				+ (sales_category.equals("1") ? "Individual " : "Organization ")
				+ " Participant Data status, default value:" + defaultValue);
	}

	/**
	 * 
	 * @param schema
	 * @param appID
	 * @param prdGrpID
	 * @param prdSubCatID
	 * @param custClass
	 * @param existed
	 * @return
	 */
	public boolean verifyCustClassConfigPrecondition(String schema,
			String appID, String prdGrpID, String prdSubCatID,
			String custClass, boolean existed) {
		boolean flag = false;
		db.resetSchema(schema);
		String query = "select * from C_CUST_CLASS_CFG where APP_ID=" + appID
				+ " and PRD_GRP_CAT_ID=" + prdGrpID + " and PRD_SUBCAT_ID"
				+ (prdSubCatID == null ? " is null" : "=" + prdSubCatID)
				+ " and CUST_CLASS_ID=" + custClass;
		List<String> result = db.executeQuery(query, "ID");
		if (existed && null != result && result.size() > 0) {
			return true;
		} else if (!existed && (null == result || result.size() == 0)) {
			return true;
		}
		return flag;
	}

	/**
	 * Get customer number identifier by customer last name and first name
	 * 
	 * @param lName
	 * @param fName
	 * @param schema
	 * @return
	 */
	public String getCustomerNumByCustName(String lName, String fName,
			String schema) {
		return this.getCustomerNumByCustName(lName, fName, "", schema);
	}

	public String getCustomerNumByCustName(String lName, String fName,
			String mName, String schema) {
		return this.getCustomerNumByCustName(lName, fName, mName, null, schema);
	}

	public String getCustomerNumByCustName(String lName, String fName,
			String mName, String bName, String schema) {
		return this.getCustomerNumByCustNameStatus(lName, fName, mName, bName,
				schema, "1");
	}

	/**
	 * get customer number form db by customer last name, first name and middle
	 * name
	 * 
	 * @param lName
	 * @param fName
	 * @param mName
	 * @param bName
	 * @param schema
	 * @param status
	 *            2-inactive, 1-active, 4-merged
	 * @return
	 * @Return String
	 * @Throws
	 */
	public String getCustomerNumByCustNameStatus(String lName, String fName,
			String mName, String bName, String schema, String status) {
		String custNum = "";
		String sql = "select cch.CUST_NUMBER from C_CUST cc, C_CUST_HFPROFILE cch where cc.F_NAME='"
				+ fName
				+ "' and cc.L_NAME='"
				+ lName
				+ "'"
				+ (!StringUtil.isEmpty(mName) ? (" and cc.M_NAME='" + mName + "'")
						: "")
				+ (!StringUtil.isEmpty(bName) ? (" and cc.ORG_NAME='" + bName + "'")
						: "")
				+ " and cc.CUST_ID = cch.CUST_ID and cch.status_id=" + status;
		db.resetSchema(schema);
		logger.info("Execute sql:" + sql);
		List<String> ids = db.executeQuery(sql, "CUST_NUMBER");
		if (ids.size() > 0) {
			custNum = ids.get(0).trim();
		}
		logger.info("Find customer number is: " + custNum);
		return custNum;
	}

	public List<String> getAllCustNumsByCustNameStatus(String lName,
			String fName, String mName, String bName, String schema,
			String status) {
		String sql = "select cch.CUST_NUMBER from C_CUST cc, C_CUST_HFPROFILE cch where cc.F_NAME='"
				+ fName
				+ "' and cc.L_NAME='"
				+ lName
				+ "'"
				+ (!StringUtil.isEmpty(mName) ? (" and cc.M_NAME='" + mName + "'")
						: "")
				+ (!StringUtil.isEmpty(bName) ? (" and cc.ORG_NAME='" + bName + "'")
						: "")
				+ " and cc.CUST_ID = cch.CUST_ID and cch.status_id=" + status;
		db.resetSchema(schema);
		logger.info("Execute sql:" + sql);
		List<String> ids = db.executeQuery(sql, "CUST_NUMBER");
		return ids;
	}

	/**
	 * This method was used to get validFrom and validTo date sting from
	 * Datebase
	 * 
	 * @param prdCD
	 * @param schema
	 * @return
	 */
	public List<String> registerVehicleValidDateCalc(String prdCD, String schema) {
		db.resetSchema(schema);

		String query = "select * from P_PRD,P_VEHICLE_RTI,P_VHCL_RTI_VLD_TO_DATE_CALC where P_PRD.PRD_CD='"
				+ prdCD
				+ "'"
				+ " and P_VEHICLE_RTI.PRD_ID=P_PRD.PRD_ID"
				+ " and P_VEHICLE_RTI.VLD_TO_DATE_CALC_ID=P_VHCL_RTI_VLD_TO_DATE_CALC.ID";

		String[] colNames = new String[] { "VLD_TO_DATE_CALC_TYPE",
				"RLTV_VALID_MONTHS", "FXD_VALID_YEARS", "FXD_VALID_TO_MONTH",
				"FXD_VALID_TO_DAY", "FXD_CYCLE_START_YEAR" };
		List<String[]> results = db.executeQuery(query, colNames);

		if (results.isEmpty()) {
			throw new ErrorOnDataException(
					"Could not get any registration for vehicle from Datebase.");
		}

		String calcType = results.get(0)[0];
		String rltvMonth = results.get(0)[1];
		String fxdToYear = results.get(0)[2];
		String fxdToMonth = results.get(0)[3];
		String fxdToDay = results.get(0)[4];
		String fxdCycleStartYear = results.get(0)[5];

		String validFrom = DateFunctions.getToday(DataBaseFunctions
				.getContractTimeZone(schema));
		String validTo = "";

		if (calcType.equals("1")) {
			// fixed
			String combined = DateFunctions.combineStringToDate(
					fxdCycleStartYear,
					DateFunctions.parseMonthValue(fxdToMonth), fxdToDay);
			do {
				int year = Integer.parseInt(fxdCycleStartYear)
						+ Integer.parseInt(fxdToYear);
				fxdCycleStartYear = String.valueOf(year);
				combined = DateFunctions.combineStringToDate(
						String.valueOf(year),
						DateFunctions.parseMonthValue(fxdToMonth), fxdToDay);
			} while (DateFunctions.compareDates(combined, validFrom) < 0);
			validTo = combined;
		} else if (calcType.equals("2")) {
			String validToDate = DateFunctions.getDateAfterGivenMonth(
					validFrom, Integer.parseInt(rltvMonth));
			validTo = DateFunctions.getLastDateOfMonth(validToDate);
		} else {
			throw new ErrorOnDataException(
					"Unhandled valid to caculation type:" + calcType);
		}

		ArrayList<String> validDate = new ArrayList<String>();
		validDate.add(validFrom);
		validDate.add(validTo);

		return validDate;

	}

	/**
	 * Get HF product's customer price from DB per the product code and purchase
	 * type.
	 * 
	 * @param prodCD
	 * @param purchaseTypeID
	 * @param schema
	 * @return
	 * @author Lesley Wang
	 * @date Jun 13, 2012
	 */
	public String getHFProdPrice(String prodCD, String purchaseTypeID,
			String schema) {
		db.resetSchema(schema);

		String query = "select CUST_PRICE from P_PRD p, P_PRD_HFPRICE price "
				+ "where p.PRD_CD='" + prodCD + "' "
				+ "and p.PRD_ID=price.PRD_ID " + "and price.PURCHASE_TYPE_ID="
				+ purchaseTypeID;

		List<String> results = db.executeQuery(query, "CUST_PRICE");

		if (results.isEmpty()) {
			throw new ErrorOnDataException(
					"Could not get any price for vehicle product with code="
							+ prodCD + " and purchaseTypeID=" + purchaseTypeID);
		}

		String price = results.get(0);
		BigDecimal amount = new BigDecimal(price).setScale(2,
				RoundingMode.HALF_UP);
		return amount.toString();
	}
	
	public List<TaxInfo> getHFProductTaxInfo(String taxname,String prdCD,String feeType,String schema){
		db.resetSchema(schema);
		
		String query="select * from p_tax_schedule "
				+"inner join p_tax_type on p_tax_schedule.tax_type_id=p_tax_type.id "
				+"inner join p_prd on p_tax_schedule.prd_id= p_prd.prd_id "
				+"where p_tax_type.name='"+taxname
				+"' and p_prd.prd_cd='"+prdCD
				+"' and p_tax_schedule.fee_type_id='"+feeType
				+"' and p_tax_schedule.active_ind='1'";
		
		String[] colNames = new String[] { "RATE",
				"TAX_SCHD_SPLIT_TYPE_ID", "TAX_CALCULATION_RATE_UI", "PURCHASE_TYPE"};
		
		
		List<String[]> results = db.executeQuery(query, colNames);
		List<TaxInfo> TaxInfos=new ArrayList<TaxInfo>();
		PricingInfo pricing = new PricingInfo();
		if(results.size()>0){
			for(String[] value:results){
				TaxInfo tax = pricing.new TaxInfo();
				tax.setRate(value[0]);
				tax.setTaxSplitType(value[1]);
				tax.setTaxCalculationRate(value[2]);
				tax.setPurchaseType(value[3]);
				TaxInfos.add(tax);
			}
		}
		
		return TaxInfos;
	}

	/**
	 * Update store status from DB
	 * 
	 * @param schema
	 * @param storeNm
	 * @param status
	 * @author Lesley Wang
	 * @date Jun 25, 2012
	 */
	public void updateStoreStatusFromDB(String schema, String storeNm,
			int status) {
		db.resetSchema(schema);

		String query = "select Status from D_STORE " + "where name='" + storeNm
				+ "'";
		logger.info("Execute query: " + query);
		List<String> results = db.executeQuery(query, "Status");
		if (!String.valueOf(status).equals(results.get(0))) {
			query = "update D_STORE set status='" + status + "' "
					+ "where name='" + storeNm + "'";
			logger.info("update query: " + query);
			db.executeUpdate(query);
		}
	}

	/**
	 * Config issue refunds in Cash for the location.
	 * 
	 * @param schema
	 * @param locNm
	 * @param yes
	 * @author Lesley Wang
	 * @date Jun 26, 2012
	 */
	public void configIssueRefundsInCash(String schema, String locNm,
			boolean yes) {
		String msg = yes ? "Turn On " : "Turn Off ";
		db.resetSchema(schema);

		String subSql = "where loc_id = (select ID from D_LOC where name='"
				+ locNm + "' and level_num=40) " + "and pmt_grp_id = "
				+ PMT_GROUP_CASH + " and pmt_rfnd_type in (0, 2)";
		String sql = "select * from F_PMT_TYPE_LOC " + subSql;

		List<String> results = db.executeQuery(sql, "ID");
		if (results.size() < 1 && yes) { // Insert a new record to turn on the
			// config
			sql = "insert into F_PMT_TYPE_LOC(ID, PMT_TYPE_ID, LOC_ID, PMT_GRP_ID) "
					+ "values (get_sequence('F_PMT_TYPE_LOC'), "
					+ "(select id from F_PMT_TYPE where CD='"
					+ PMT_TYPE_CASH
					+ "'), "
					+ "(select ID from D_LOC where name='"
					+ locNm
					+ "' and level_num=40), " + PMT_GROUP_CASH + ")";
			db.executeUpdate(sql);
		} else if (results.size() > 0 && !yes) { // Update the record to turn
			// off the config
			sql = "update F_PMT_TYPE_LOC set pmt_rfnd_type=1 " + subSql;
			db.executeUpdate(sql);
		}

		logger.info(msg + " Refund issue in Cash for location " + locNm);
	}

	public RaFeeScheduleData getRAFeeSchdBaseRateInfoBySchdId(String schema,
			String schdID, RaFeeScheduleData schd) {
		return getRAFeeSchdBaseRateInfoBySchdId(schema, schdID, schd, 0);
	}

	/**
	 * query ra fee schedule base rate and max amount
	 * 
	 * @param schema
	 * @param schdID
	 * @param schd
	 *            this param set to null is acceptable.
	 * @param rateIndex
	 * @return
	 */
	public RaFeeScheduleData getRAFeeSchdBaseRateInfoBySchdId(String schema,
			String schdID, RaFeeScheduleData schd, int rateIndex) {

		logger.info("Get RA fee schedule base rate by schedule Number - "
				+ schdID);

		RaFeeScheduleData rafee;
		if (null != schd) {
			rafee = schd;
		} else {
			rafee = new RaFeeScheduleData();
		}

		db.resetSchema(schema);
		String sql = "";
		if (rateIndex > -1) {// The index has means, indicate which one of the
								// rafee rate will be used
			sql = "select * from p_rafee_rate where fee_schd_id='" + schdID
					+ "' order by UI_INDEX";
		} else if (rateIndex == -1) {// The index equals to "-1" means use the
										// rafee rate with loweast rate(new
										// units)
			sql = "select * from p_rafee_rate a where a.rate=(select min(rate) from p_rafee_rate b where fee_schd_id="
					+ schdID + ")";
		}
		String columnNames[] = new String[] { "RATE", "TO_RATE", "FLAT_RATE" };

		logger.info("Execute query: " + sql);

		List<String[]> results = db.executeQuery(sql, columnNames);

		if (results.size() < 1) {
			logger.info("Can't find any RA fee schedule base rate by schedule Number - "
					+ schdID);
		} else {
			if (rateIndex > 0) {
				rafee.baseRate = results.get(rateIndex)[0];
				rafee.changeRate = results.get(rateIndex)[1];
				rafee.flatAmt = results.get(rateIndex)[2];
			} else {
				rafee.baseRate = results.get(0)[0];
				rafee.changeRate = results.get(0)[1];
				rafee.flatAmt = results.get(0)[2];
			}
		}

		sql = "select * from p_fee_schd where id='" + schdID + "'";
		columnNames = new String[] { "MAX_FEE", "MAX_FEE_RESTR_TYPE" };

		logger.info("Execute query: " + sql);
		results = db.executeQuery(sql, columnNames);
		if (results.size() < 1) {
			logger.info("Can't find any RA fee schedule max amount by schedule Number - "
					+ schdID);
		} else {
			rafee.maxAmt = results.get(0)[0];
			rafee.maxRestricCond = results.get(0)[1];
		}

		return rafee;
	}

	public List<RaFeeInfo> getProcessRAFeeInfo(String schema, String ordNum,
			String schdID) {

		logger.info("Get RA fee information by order Number - " + ordNum);

		db.resetSchema(schema);

		String sql = "select O_ORD_ITEM_RAFEE.ID as RA_FEE_ID, FEE_AMT,FEE_SCHD_ID,FEE_TYPE_ID,TRANS_TYP_ID,RVRS,RAFEE_TYPE_ID,"
				+ "RAFEE_STATUS,PRICE_TIME,UNIT_TYPE_ID,NUM_OF_UNIT,RAFEE_RATE,FEE_RATE_TYPE,CREATE_TIME,MATCH_DEBIT_RAFEE_ID "
				+ "from O_ORD_ITEM_RAFEE INNER JOIN O_ORD_ITEM ON O_ORD_ITEM_RAFEE.ORD_ITEM_ID=O_ORD_ITEM.ID "
				+ "INNER JOIN O_ORDER ON O_ORD_ITEM.ORD_ID=O_ORDER.ID INNER JOIN O_ORD_ITEM_TRANS ON O_ORD_ITEM_TRANS.ID=O_ORD_ITEM_RAFEE.ORIG_ORD_ITEM_TRAN_ID "
				+ "where ORD_NUM='" + ordNum + "'";
		if (schdID != null) {
			sql = sql + " AND FEE_SCHD_ID='" + schdID + "'";
		}
		sql = sql + " Order by O_ORD_ITEM_RAFEE.ID";

		String columnNames[] = new String[] { "RA_FEE_ID", "FEE_AMT",
				"FEE_SCHD_ID", "FEE_TYPE_ID", "TRANS_TYP_ID", "RVRS",
				"RAFEE_TYPE_ID", "RAFEE_STATUS", "PRICE_TIME", "UNIT_TYPE_ID",
				"NUM_OF_UNIT", "RAFEE_RATE", "FEE_RATE_TYPE",
				"MATCH_DEBIT_RAFEE_ID" };
		logger.info("Execute query: " + sql);

		List<String[]> results = db.executeQuery(sql, columnNames);

		if (results.size() < 1) {
			logger.info("Can't find any RA Fee record by feeInfos - " + ordNum);
			return null;
		} else {
			List<RaFeeInfo> feeInfos = new ArrayList<RaFeeInfo>();
			for (int i = 0; i < results.size(); i++) {
				RaFeeInfo rafee = new RaFeeInfo();
				rafee.setRaFeeID(results.get(i)[0]);
				rafee.setPrice(results.get(i)[1]);
				rafee.setRaFeeScheduleID(results.get(i)[2]);
				rafee.setTranType(results.get(i)[4]);
				rafee.setType(results.get(i)[6]);
				rafee.setStatus(results.get(i)[7]);
				rafee.setNumberOfUnit(results.get(i)[10]);
				rafee.setMatchDebitRAFeeID(results.get(i)[13]);
				rafee.setRaFeeRate(results.get(i)[11]);
				feeInfos.add(rafee);
			}

			return feeInfos;
		}

	}

	/**
	 * Get ra threshold current count by threshold schedule id
	 * 
	 * @param schema
	 * @param thresholdSchdID
	 * @return
	 */
	public String queryRaThresholdCurrentCount(String thresholdSchdID,
			String schema) {
		db.resetSchema(schema);

		String query = "select current_count from p_threshd_counter where fee_schd_id = "
				+ thresholdSchdID;
		logger.info("Excuse sql:" + query);
		List<String> results = db.executeQuery(query, "current_count");

		if (results.isEmpty()) {
			throw new ErrorOnDataException(
					"Did nout fount the ra threshold current count for threshold id = "
							+ thresholdSchdID);
		}

		return results.get(0);
	}

	public int getThreshdRateRangeIndex(String thresholdSchdID, int accountNum,
			String schema) {
		db.resetSchema(schema);
		String query = "select UI_INDEX from p_threshd_schd where fee_schd_id = "
				+ thresholdSchdID
				+ " and lower_bound <"
				+ accountNum
				+ " and upper_bound >=" + accountNum;
		logger.info("run sql:" + query);
		List<String> results = db.executeQuery(query, "UI_INDEX");
		if (results.size() != 1) {
			throw new ErrorOnDataException(
					"Did nout fount a ra threshold rate range or found more than one for threshold id = "
							+ thresholdSchdID);
		}
		return Integer.parseInt(results.get(0));
	}

	public boolean isExtraDecimalIndicatorSetForProductGroup(String schema,
			String group) {
		db.resetSchema(schema);

		String query = "select P_PRD_GRP_ATTR.ACTIVE_IND from P_PRD_GRP,P_PRD_GRP_ATTR where P_PRD_GRP.PRD_GRP_ID=P_PRD_GRP_ATTR.PRD_GRP_ID "
				+ "and P_PRD_GRP.PRD_GRP_NAME='"
				+ group
				+ "' "
				+ "and P_PRD_GRP_ATTR.ATTR_ID=4901";// 4901 was extra decimal
		// indicator for pos product

		List<String> results = db.executeQuery(query, "ACTIVE_IND");

		if (results.isEmpty()) {
			// throw new
			// ErrorOnDataException("Could not get any extra decimal indicator records for product group "+group);
			return false;
		}

		boolean toReturn = Integer.parseInt(results.get(0)) == 1 ? true : false;
		return toReturn;
	}

	/**
	 * Verify the extra decimal indicator attribute for specific POS product
	 * group
	 * 
	 * @param group
	 * @param schema
	 * @param isTrue
	 */
	public void verifyExtraDecimalIndicatorForPosGroup(String group,
			String schema, boolean isTrue) {
		boolean result = isExtraDecimalIndicatorSetForProductGroup(schema,
				group);
		logger.info("Extra decimal indicator was set up as " + result
				+ " for product group " + group);
		if (isTrue != result) {
			throw new ErrorOnDataException("Pos " + group
					+ " Extra decimal indicator setup was not expected.",
					Boolean.toString(isTrue), Boolean.toString(result));
		}
		logger.info("Pos "
				+ group
				+ " Extra decimal indicator attribute setup was correct for test.");
	}

	public void setupExtraDecimalIndicatorForProductGroup(String schema,
			String group) {
		logger.info("Setup Extra Decimal Place attribute for product group - "
				+ group);

		db.resetSchema(schema);
		String sql = "INSERT INTO P_PRD_GRP_ATTR (PRD_GRP_ATTR_ID, ATTR_ID, MANDT_IND, PRD_GRP_ID, STATUS_ID, ACTIVE_IND, DELETED_IND, CHARGEABLE, PRD_CAT_ID, WEB_KEY_ATTR) values (get_sequence('P_PRD_GRP_ATTR'), 4901, 0, (select PRD_GRP_ID from P_PRD_GRP where PRD_GRP_NAME='"
				+ group + "'), 0, 1, 0, 0, 4, 0)";

		logger.info("Executing query: " + sql);
		db.executeUpdate(sql);
	}

	/**
	 * Verify partial qty allowed attribute for pos product
	 * 
	 * @param product
	 * @param schema
	 * @param allowed
	 */
	public void verifyPartialQtyAllowedAttrForPosProduct(String product,
			String schema, boolean allowed) {
		db.resetSchema(schema);

		String query = "select PARTIAL_QTY_ALLOWED from P_PRD_LOC where PRD_NAME='"
				+ product + "'";

		List<String> results = db.executeQuery(query, "PARTIAL_QTY_ALLOWED");

		if (results.isEmpty()) {
			throw new ErrorOnDataException(
					"Could not get Partial Qty Allowed attribute for product "
							+ product);
		}

		boolean result = results.get(0).equals("1") ? Boolean.TRUE
				: Boolean.FALSE;
		logger.info("Partial Qty Allowed attribute was set up as " + result
				+ " for product group " + product);
		if (allowed != result) {
			throw new ErrorOnDataException("Pos " + product
					+ " Partial Qty Allowed attribute setup was not expected.",
					Boolean.toString(allowed), Boolean.toString(result));
		}
		logger.info("Pos " + product
				+ " Partial Qty Allowed attribute setup was correct for test.");
	}

	/**
	 * Get Pos qty on hand
	 * 
	 * @param product
	 * @param schema
	 * @return
	 */
	public String getPosQtyOnHand(String product, String schema) {
		db.resetSchema(schema);

		String query = "select QTY_ON_HAND from P_PRD_LOC where PRD_ID=(select PRD_ID from P_PRD where PRD_NAME='"
				+ product
				+ "' and PRODUCT_CAT_ID="
				+ PRDCAT_POS
				+ ""
				+ " and active_ind=1)";

		List<String> results = db.executeQuery(query, "QTY_ON_HAND");

		if (results.isEmpty()) {
			throw new ErrorOnDataException(
					"Could not get Qty On Hand for product " + product);
		}

		return results.get(0);
	}

	/**
	 * Get child vehicle ID from DB
	 * 
	 * @param miNum
	 * @param schema
	 * @return
	 */
	public String getChildVehIDByMiNum(String miNum, String schema) {
		db.resetSchema(schema);

		String query = "select CHILD_VEH_ID from E_VEHICLE,E_CHILD_VEHICLE where E_VEHICLE.ID=E_CHILD_VEHICLE.VEH_ID and VEH_NUMBER='"
				+ miNum + "'";

		List<String> results = db.executeQuery(query, "CHILD_VEH_ID");

		if (results.isEmpty()) {
			logger.info("Could not get child vehicle id for vehicle with miNum "
					+ miNum);
		}

		return results.get(0);
	}

	/**
	 * Get vehicle title id by serial num
	 * 
	 * @param schema
	 * @param serialNum
	 * @return
	 */
	public String getVehicleTitleIDBySerialNum(String schema, String serialNum) {
		db.resetSchema(schema);

		String query = "select TITLE_INST_ID from E_VEHICLE where SER_NUMBER='"
				+ serialNum + "'";

		List<String> results = db.executeQuery(query, "TITLE_INST_ID");

		if (results.isEmpty()) {
			throw new ErrorOnDataException(
					"Can not get vehicle title id by Serial Num - " + serialNum);
		}

		return results.get(0);
	}

	/**
	 * get vehicle title id identified by mi num(title num)
	 * 
	 * @param schema
	 * @param miNum
	 * @return
	 */
	public String getVehicleTitleIDByMiNum(String schema, String miNum) {
		db.resetSchema(schema);

		String query = "select TITLE_INST_ID from E_VEHICLE where VEH_NUMBER='"
				+ miNum + "'";

		List<String> results = db.executeQuery(query, "TITLE_INST_ID");

		if (results.isEmpty()) {
			throw new ErrorOnDataException(
					"Can not get vehicle title id by miNum - " + miNum);
		}

		return results.get(0);
	}

	/**
	 * 
	 * @param miNum
	 * @param schema
	 * @return
	 */
	public String getVehicleIDByMiNum(String miNum, String schema) {
		db.resetSchema(schema);

		String query = "select * from E_VEHICLE where VEH_NUMBER='" + miNum
				+ "'";

		List<String> results = db.executeQuery(query, "ID");

		if (results.isEmpty()) {
			logger.info("Could not get vehicle id by miNum " + miNum);
		}

		return results.get(0);
	}

	/**
	 * Get vehicle id by serial number
	 * 
	 * @param schema
	 * @param hullIdSerialNum
	 * @return vehicl id
	 */
	public String getVehIDBySerNum(String hullIdSerialNum, String schema) {
		db.resetSchema(schema);
		String query = "select * from E_VEHICLE where SER_NUMBER='"
				+ hullIdSerialNum + "'";

		List<String> results = db.executeQuery(query, "ID");

		if (results.isEmpty()) {
			logger.info("Could not get vehicle id by serNum " + hullIdSerialNum);
		}
		return results.get(0);
	}

	/**
	 * Get vehicle id identified by serial number
	 * 
	 * @param schema
	 * @param serialNum
	 * @return
	 */
	public String getVehicleIDBySerialNum(String schema, String serialNum) {
		db.resetSchema(schema);

		String query = "select * from E_VEHICLE where SER_NUMBER = '"
				+ serialNum + "'";

		List<String> results = db.executeQuery(query, "ID");

		if (results.isEmpty()) {
			logger.info("Could not get vehicle id by Serial Num - " + serialNum);
		}

		return results.get(0);
	}

	/**
	 * 
	 * @param owner
	 * @param vehID
	 * @param schema
	 * @return
	 */
	public String getVehicleCoOwnerID(OwnerInfo owner, String vehID,
			String schema) {
		db.resetSchema(schema);

		String query = "select * from E_VEHICLE_COOWNER" + " where VEH_ID='"
				+ vehID + "'" + " and F_NAME='" + owner.firstName + "'"
				+ " and L_NAME='" + owner.lastName + "'" + " and STATUS_ID='1'";
		if (!StringUtil.isEmpty(owner.midName))
			query = query + " and M_NAME='" + owner.midName + "'";
		if (!StringUtil.isEmpty(owner.suffix))
			query = query + " and SUFFIX='" + owner.suffix + "'";
		if (!StringUtil.isEmpty(owner.businessName))
			query = query + " and BUSINESS_NAME='" + owner.businessName + "'";
		if (!StringUtil.isEmpty(owner.dateOfBirth))
			query = query + " and DOB=to_date('" + owner.dateOfBirth
					+ "','yyyy-mm-dd')";
		List<String> results = db.executeQuery(query, "ID");

		if (results.isEmpty() || results.size() < 1) {
			logger.info("Could not get co-owner id by given parameters.");
		}

		return results.get(0);
	}

	/**
	 * Get all permit types for specific contract
	 * 
	 * @param schema
	 * @param facilityID
	 * @return
	 */
	public List<String> getAllPermitTypes(String schema, String facilityID) {
		// db.resetSchema(schema);
		// logger.info("Get permit types for facility - " + facilityID);
		//
		// String sql =
		// "select pptn.NAME from P_PERMIT_TYPE ppt, P_PERMIT_TYPE_NAME pptn where ppt.LOC_ID = "
		// + facilityID +
		// " and ppt.PERMIT_TYPE_NAME_ID = pptn.ID and ppt.ACTIVE_IND = 1";
		// List<String> results = db.executeQuery(sql, "NAME");
		// if (results.size() < 1) {
		// throw new ErrorOnDataException(
		// "There is not any Permit Types in DB.");
		// }
		//
		// return results;
		return this.getAllPermitTypes(schema, facilityID, "1", null, null);
	}

	/**
	 * Get All Permit types in the facility
	 * 
	 * @param schema
	 * @param facilityID
	 * @param activeInd
	 *            : can be 1 or 0 or null or empty
	 * @param webVisibleInd
	 *            : can be 1 or 0 or null or empty
	 * @param permitCatID
	 *            : can be null or empty or specific id value
	 * @return
	 * @author Lesley
	 * @date Jul 17, 2013
	 */
	public List<String> getAllPermitTypes(String schema, String facilityID,
			String activeInd, String webVisibleInd, String permitCatID) {
		db.resetSchema(schema);
		logger.info("Get permit types for facility - " + facilityID);

		String sql = "select distinct pptn.NAME from P_PERMIT_TYPE ppt, P_PERMIT_TYPE_NAME pptn, p_entrance_permit_type pept where ppt.LOC_ID = "
				+ facilityID
				+ " and ppt.PERMIT_TYPE_NAME_ID = pptn.ID and pept.permit_type = ppt.id ";

		if (StringUtil.notEmpty(activeInd)) {
			sql += " and ppt.active_ind=" + activeInd;
		}
		if (StringUtil.notEmpty(webVisibleInd)) {
			sql += " and ppt.web_visible=" + webVisibleInd;
		}
		if (StringUtil.notEmpty(permitCatID)) {
			sql += " and ppt.permit_cat_id in (" + permitCatID + ")";
		}
		sql += " order by pptn.NAME";
		List<String> results = db.executeQuery(sql, "NAME");
		if (results.size() < 1) {
			throw new ErrorOnDataException(
					"There is not any Permit Types in DB.");
		}

		return results;
	}

	/**
	 * Get Permit type description per permit type name and location id
	 * 
	 * @param schema
	 * @param locID
	 * @param permitTypeNm
	 * @return
	 * @author Lesley Wang
	 * @Date Jun 27, 2013
	 */
	public String getPermitTypeDescription(String schema, String locID,
			String permitTypeNm) {
		db.resetSchema(schema);
		logger.info("Get permit type description for facility - " + locID
				+ ", permit type - " + permitTypeNm);

		String sql = "select ppt.DESCRIPTION from P_PERMIT_TYPE ppt, P_PERMIT_TYPE_NAME pptn where ppt.LOC_ID = "
				+ locID
				+ " and ppt.PERMIT_TYPE_NAME_ID = pptn.ID and pptn.NAME='"
				+ permitTypeNm + "'";
		return db.executeQuery(sql, "DESCRIPTION", 0);
	}

	/** Get entrance description */
	public String getEntranceDescription(String schema, String locID,
			String entranceCode) {
		db.resetSchema(schema);
		logger.info("Get entrance description for facility - " + locID
				+ ", entrance code - " + entranceCode);

		String sql = "select PRD_DSCR from P_PRD where LOC_ID=" + locID
				+ " and PRD_CD='" + entranceCode + "'";
		return db.executeQuery(sql, "PRD_DSCR", 0);
	}

	/**
	 * Get Max Group Size Vaule in DB according to the permit name and entrance
	 * code
	 * 
	 * @param schema
	 * @param permitType
	 * @param entranceCode
	 * @return
	 * @author Lesley Wang
	 * @date Jun 28, 2012
	 */
	public String getMaxGroupSizeInDB(String schema, String permitType,
			String entranceCode) {
		db.resetSchema(schema);

		logger.info("Query max group size for the permit type=" + permitType
				+ " and entrance=" + entranceCode);
		String query = "Select entPerAttr.ATTR_VALUE "
				+ "from P_ENTRANCE_PERMIT_TYPE_ATTR entPerAttr, "
				+ "P_ENTRANCE_PERMIT_TYPE entPer, P_PERMIT_TYPE per, "
				+ "P_PERMIT_TYPE_NAME perNm, P_PRD ent, D_ATTR attr "
				+ "where perNm.name = '" + permitType + "' and "
				+ "ent.prd_cd='" + entranceCode + "' and "
				+ "attr.ATTR_DSCR = 'Max Group Size' and "
				+ "per.id = entPer.permit_type and "
				+ "ent.prd_id = entPer.entrance and "
				+ "entPer.id = entPerAttr.prd_permit_type_assign_id and "
				+ "attr.attr_id = entPerAttr.attr_id and "
				+ "perNm.id = per.permit_type_name_id";

		return db.executeQuery(query, "ATTR_VALUE", 0);
	}

	/**
	 * Get Update restriction window in DB
	 * 
	 * @param schema
	 * @param parkID
	 * @param entranceCode
	 * @param permitTypeName
	 * @return
	 */
	public int getUpdateRestrictionWindowInDB(String schema, String parkID,
			String entranceCode, String permitTypeName) {
		db.resetSchema(schema);

		logger.info("Query update restriction window for entrance code="
				+ entranceCode + ", permit type=" + permitTypeName
				+ " and parkID=" + parkID);
		String query = "select update_restriction_window "
				+ "from p_entrance_permit_type "
				+ "where entrance in (select prd_id from p_prd where prd_cd = '"
				+ entranceCode
				+ "' and park_id = "
				+ parkID
				+ ") "
				+ "and permit_type in (select ID from P_PERMIT_TYPE where permit_type_name_id in (select id from p_permit_type_name where name like '"
				+ permitTypeName + "') and active_ind = 1)";

		return Integer.valueOf(db.executeQuery(query,
				"UPDATE_RESTRICTION_WINDOW", 0));
	}

	public void updateEntrancePermitTypeRelatedInDB(String schema,
			String parkID, String entranceCode, String permitTypeName,
			String permitTypeAttr, String expectedValue) {
		db.resetSchema(schema);

		logger.info("update entrance permit type attr:'" + permitTypeAttr
				+ "' for entrance code=" + entranceCode + ", permit type="
				+ permitTypeName + " and parkID=" + parkID);
		String query = "update p_entrance_permit_type set "
				+ permitTypeAttr
				+ " = '"
				+ expectedValue
				+ "' where entrance in (select prd_id from p_prd where prd_cd = '"
				+ entranceCode
				+ "' and park_id = "
				+ parkID
				+ ") "
				+ "and permit_type in (select ID from P_PERMIT_TYPE where permit_type_name_id in (select id from p_permit_type_name where name like '"
				+ permitTypeName + "') and active_ind = 1 and deleted_ind=0)";

		db.executeUpdate(query);
	}

	public void updateEntranceUpdateRestrictionWindowInDB(String schema,
			String parkID, String entranceCode, String permitTypeName,
			int update_restriction_window) {
		this.updateEntrancePermitTypeRelatedInDB(schema, parkID, entranceCode,
				permitTypeName, "update_restriction_window",
				String.valueOf(update_restriction_window));
	}

	public void updateIssueStartDaysPriorInDB(String schema, String parkID,
			String entranceCode, String permitTypeName,
			String issue_start_days_prior) {
		this.updateEntrancePermitTypeRelatedInDB(schema, parkID, entranceCode,
				permitTypeName, "issue_start_days_prior",
				String.valueOf(issue_start_days_prior));
	}

	public void updateIssueEndDaysPriorInDB(String schema, String parkID,
			String entranceCode, String permitTypeName,
			String issue_start_days_prior) {
		this.updateEntrancePermitTypeRelatedInDB(schema, parkID, entranceCode,
				permitTypeName, "issue_end_days_prior",
				String.valueOf(issue_start_days_prior));
	}

	/**
	 * Get entrance codes and names of specific permit type
	 * 
	 * @param schema
	 * @param parkID
	 * @param permitTypeName
	 * @param isActive
	 *            true:active, false:inactive
	 * @return
	 */
	public List<String> queryEntranceCodesAndNamesOfPermitType(String schema,
			String parkID, String permitTypeName, boolean isActive) {
		db.resetSchema(schema);
		logger.info("Query entrance code and name for permit type="
				+ permitTypeName + " and parkID=" + parkID);

		String query = "select prd_cd||' '||prd_name as CodeAndName from p_prd where park_id = '"
				+ parkID
				+ "' and prd_id in (select entrance from P_ENTRANCE_PERMIT_TYPE where permit_type in (select ID from P_PERMIT_TYPE where permit_type_name_id in (select id from p_permit_type_name where name like '"
				+ permitTypeName
				+ "') and active_ind = 1) and active_ind = 1) and active_ind ="
				+ (isActive ? "1" : "0")
				+ " and deleted_ind !=1 order by prd_name asc";
		logger.info("execute sql: " + query);
		return db.executeQuery(query, "CODEANDNAME");
	}

	public List<String> queryEntranceCodesAndNamesOfPermitType(String schema,
			String parkID, String permitTypeName) {
		return this.queryEntranceCodesAndNamesOfPermitType(schema, parkID,
				permitTypeName, true);
	}

	public List<String> queryEntranceNamesAndCodesOfPermitType(String schema,
			String parkID, String permitTypeName, boolean isEntranceActive) {
		db.resetSchema(schema);
		logger.info("Query update restriction window for permit type="
				+ permitTypeName + " and parkID=" + parkID);

		String query = "select prd_name||' '||prd_cd as CodeAndName from p_prd where park_id = '"
				+ parkID
				+ "' and prd_id in (select entrance from P_ENTRANCE_PERMIT_TYPE where permit_type in (select ID from P_PERMIT_TYPE where permit_type_name_id in (select id from p_permit_type_name where name like '"
				+ permitTypeName
				+ "') and active_ind = 1) and active_ind = 1) and active_ind ="
				+ (isEntranceActive ? "1" : "0") + " order by prd_name asc";
		return db.executeQuery(query, "CODEANDNAME");
	}

	public List<String> queryEntranceNamesAndCodesOfPermitType(String schema,
			String parkID, String permitTypeName) {
		return this.queryEntranceNamesAndCodesOfPermitType(schema, parkID,
				permitTypeName, true);
	}

	/**
	 * Get entrance names of specific permit type
	 * 
	 * @param schema
	 * @param parkID
	 * @param permitTypeName
	 * @return
	 */
	public List<String> queryEntranceNameOfPermitType(String schema,
			String parkID, String permitTypeName) {
		db.resetSchema(schema);
		logger.info("Query update restriction window for permit type="
				+ permitTypeName + " and parkID=" + parkID);

		String query = "select prd_name from p_prd where park_id = '"
				+ parkID
				+ "' and prd_id in (select entrance from P_ENTRANCE_PERMIT_TYPE where permit_type in (select ID from P_PERMIT_TYPE where permit_type_name_id in (select id from p_permit_type_name where name like '"
				+ permitTypeName
				+ "') and active_ind = 1) and active_ind =1) order by prd_name asc";
		logger.info("query:" + query);
		return db.executeQuery(query, "PRD_NAME");
	}

	/**
	 * Get entrance codes of specific permit type
	 * 
	 * @param schema
	 * @param parkID
	 * @param permitTypeName
	 * @return
	 */
	public List<String> queryEntranceCodeOfPermitType(String schema,
			String parkID, String permitTypeName) {
		db.resetSchema(schema);
		logger.info("Query update restriction window for permit type="
				+ permitTypeName + " and parkID=" + parkID);

		String query = "select prd_cd from p_prd where park_id = '"
				+ parkID
				+ "' and prd_id in (select entrance from P_ENTRANCE_PERMIT_TYPE where permit_type in (select ID from P_PERMIT_TYPE where permit_type_name_id in (select id from p_permit_type_name where name like '"
				+ permitTypeName
				+ "') and active_ind = 1) and active_ind =1) order by prd_name asc";
		return db.executeQuery(query, "PRD_CD");
	}

	public List<String> queryAllEntranceNamesInParkLevel(String schema,
			String parkID) {
		db.resetSchema(schema);
		logger.info("Query all entrance names in park (id:" + parkID
				+ ") level");

		String query = "select prd_name from p_prd where park_id = '" + parkID
				+ "' and product_cat_id = " + PRDCAT_PERMIT
				+ " and active_ind=1 and deleted_ind=0 order by prd_name asc";
		return db.executeQuery(query, "PRD_NAME");
	}

	public String getPermitTypeID(String schema, String type) {
		db.resetSchema(schema);
		String sql = "select ppt.ID from P_PERMIT_TYPE ppt, P_PERMIT_TYPE_NAME pptn where ppt.PERMIT_TYPE_NAME_ID = pptn.ID and pptn.NAME = '"
				+ type + "'";
		logger.info("Execute query: " + sql);
		List<String> ids = db.executeQuery(sql, "ID");
		if (ids.size() > 0) {
			return ids.get(0);
		}

		return "";
	}

	public String getPermitPersonTypeIDs(String schema, String permitTypeID,
			String personType) {
		db.resetSchema(schema);
		String sql = "select pat.ID from P_PERMIT_TYPE_PERSON pptp, P_ADMISSION_TYPE pat where pptp.ADMISSION_TYPE_ID = pat.ID and pat.NAME = '"
				+ personType + "' and pptp.PERMIT_TYPE_ID = " + permitTypeID;
		logger.info("Execute query: " + sql);
		List<String> ids = db.executeQuery(sql, "ID");
		if (ids.size() > 0) {
			return ids.get(0);
		}

		return "";
	}

	public String getPermitTypeNameID(String schema, String parkID,
			String permitTypeName) {
		db.resetSchema(schema);

		String query = "select PERMIT_TYPE_NAME_ID from p_permit_type where permit_type_name_id in (select id from p_permit_type_name where name like '"
				+ permitTypeName
				+ "') and active_ind = 1 and loc_id = "
				+ parkID;
		logger.info("Execute query: " + query);
		return db.executeQuery(query, "PERMIT_TYPE_NAME_ID", 0);
	}

	/**
	 * Get permit type ID from DB
	 * 
	 * @param schema
	 * @param parkID
	 * @param permitTypeName
	 * @return
	 */
	public String getPermitTypeIdInDB(String schema, String parkID,
			String permitTypeName) {
		db.resetSchema(schema);

		logger.info("Check if permit type=" + permitTypeName + " in parkID="
				+ parkID + " under schema=" + schema
				+ " has Permit issue station.");

		String query = "select id from p_permit_type where permit_type_name_id in (select id from p_permit_type_name where name like '"
				+ permitTypeName
				+ "') and active_ind = 1 and loc_id = "
				+ parkID;
		logger.info("Execute query: " + query);
		return db.executeQuery(query, "ID", 0);
	}

	/**
	 * Get permit attr ID from DB
	 * 
	 * @param schema
	 * @param attr_cd
	 *            : Such as PT_PermitDeliveryMethod, PT_PermitIssueStation
	 * @return
	 */
	public String getPermitAttrIdInDB(String schema, String attr_cd) {
		db.resetSchema(schema);
		logger.info("Get permit type name id from DB when the attr_cd="
				+ attr_cd + " under schema=" + schema);
		String query = "select attr_id from d_attr where attr_cat like 'PermitType' and attr_cd like '"
				+ attr_cd + "'";
		return db.executeQuery(query, "ATTR_ID", 0);
	}

	/**
	 * 
	 * @param schema
	 * @param parkID
	 * @param permitTypeName
	 * @param attr_cd
	 *            : Such as PT_PermitDeliveryMethod, PT_PermitIssueStation
	 * @return
	 */
	public boolean isPermitWithSpecificAttr(String schema, String parkID,
			String permitTypeName, String attr_cd) {
		db.resetSchema(schema);

		logger.info("Check if permit type=" + permitTypeName + " under parkID="
				+ parkID + " has attribute=" + attr_cd + ".");
		String query = "select count(*) as COUNT from p_permit_type_attr_value "
				+ "where active_ind = 1 and deleted_ind = 0 and attr_value not in ('Not Applicable') "
				+ "and permit_type_id in (select id from p_permit_type where permit_type_name_id in (select id from p_permit_type_name where name like '"
				+ permitTypeName
				+ "') and active_ind = 1 and loc_id = "
				+ parkID
				+ ") "
				+ "and attr_id in (select attr_id from d_attr where attr_cat like 'PermitType' and attr_cd like '"
				+ attr_cd + "')";

		logger.info(query);

		int count = Integer.valueOf(db.executeQuery(query, "COUNT", 0));
		if (count == 0) {
			logger.info("The permit type=" + permitTypeName + " under parkID="
					+ parkID + " doesn't have attribute=" + attr_cd + ".");
			return false;
		} else {
			logger.info("The permit type=" + permitTypeName + " under parkID="
					+ parkID + " has attribute=" + attr_cd + ".");
			return true;
		}
	}

	public int setupPermitWithSpecificAttr(String schema, String parkID,
			String permitTypeName, String attr_cd, String value) {
		db.resetSchema(schema);

		logger.info("Setup permit type=" + permitTypeName + " under parkID="
				+ parkID + " with attribute=" + attr_cd + ".");

		String sql = "INSERT INTO p_permit_type_attr_value ("
				+ "id,attr_id,permit_type_id,attr_value,active_ind,deleted_ind,parent_id,issuetime_mandatory_ind)"
				+ " (SELECT"
				+ " (SELECT DECODE(MAX(id),NULL,0,MAX(id)) FROM p_permit_type_attr_value) + rownum,"
				+ " (select attr_id from d_attr where attr_cat like 'PermitType' and attr_cd like '"
				+ attr_cd
				+ "'),"
				+ " pmt.id,'"
				+ value
				+ "',1,0,NULL, 0 FROM p_permit_type pmt"
				+ " WHERE pmt.permit_type_name_id IN (SELECT id FROM p_permit_type_name WHERE name LIKE '"
				+ permitTypeName + "'"
				+ ") AND active_ind = 1 AND loc_id     = " + parkID + ")";
		int insertROW = db.executeUpdate(sql);
		return insertROW;
	}

	public boolean isPermitWithDeliveryMethodAttr(String schema, String parkID,
			String permitTypeName) {
		return this.isPermitWithSpecificAttr(schema, parkID, permitTypeName,
				"PT_PermitDeliveryMethod");
	}

	public int setupPermitWithDeliveryMethodAttr(String schema, String parkID,
			String permitTypeName) {
		return this.setupPermitWithSpecificAttr(schema, parkID, permitTypeName,
				"PT_PermitDeliveryMethod", "Applicable");
	}

	public boolean isPermitWithIssueStationAttr(String schema, String parkID,
			String permitTypeName) {
		return this.isPermitWithSpecificAttr(schema, parkID, permitTypeName,
				"PT_PermitIssueStation");
	}

	public int setupPermitWithIssueStationAttr(String schema, String parkID,
			String permitTypeName) {
		return this.setupPermitWithSpecificAttr(schema, parkID, permitTypeName,
				"PT_PermitIssueStation", "Mandatory");
	}

	public boolean isTripItineraryRestrictFirstNightsStay(String schema,
			String parkID, String permitTypeName) {
		return this.isPermitWithSpecificAttr(schema, parkID, permitTypeName,
				"RestrictFirstNightStay");
	}

	/**
	 * Get Deposit Adjustment Invoice Number info.
	 * 
	 * @param schema
	 * @param depositID
	 * @return
	 */
	public List<String> getDepositAdjInvoiceNum(String schema, String depositID) {
		db.resetSchema(schema);
		logger.info("Get Deposit Adjustment Invoice Number info by deposit ID "
				+ depositID + "...");

		String query = "select eft_invoice_id from f_deft where dep_id = "
				+ depositID;
		List<String> results = db.executeQuery(query, "eft_invoice_id");

		if (results.size() < 1) {
			throw new ItemNotFoundException(
					"Can not found Deposit Adjustment Invoice Number by deposit ID = "
							+ depositID);
		} else {
			logger.info("Store EFT Adjustment Invoice Number = "
					+ results.get(0) + " by deposit ID = " + depositID);
		}
		return results;
	}

	/**
	 * Query fee schedule id by location, product, fee type id and create date.
	 * 
	 * @param feeData
	 * @param schema
	 * @param createDate
	 * @return
	 * @author Lesley Wang
	 * @date Jul 23, 2012
	 */
	public List<String> queryFeeSchdIDByLocProdAndCreateDate(String schema,
			String product, String location, String feeTypeID,
			String createDate, String salesChannelID) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema + " ; Query fee schedule id");

		String query = "SELECT p_fee_schd.ID"
				+ " FROM p_fee, p_fee_cond, p_fee_schd, p_condition"
				+ " WHERE p_fee.fee_id = p_fee_cond.fee_id"
				+ " and p_fee_cond.id = p_fee_schd.fee_cond_id"
				+ " and p_condition.id = p_fee_cond.cond_id"
				+ " AND p_fee.FEE_TYPE_ID='" + feeTypeID
				+ "' AND ACTIVE_IND='1' AND PRD_ID="
				+ "(select PRD_ID from P_PRD where PRD_NAME='" + product
				+ "' and ACTIVE_IND=1)" + " AND LOC_ID ="
				+ "(select ID from D_LOC where name = '" + location + "')"
				+ " and p_condition.sales_chanl_id = " + salesChannelID;
		if (!StringUtil.isEmpty(createDate)) {
			createDate = DateFunctions.formatDate(createDate, "d-MMM-yy");
			query += " and CREATE_DATE ='" + createDate + "'";
		}

		logger.info("Execute the query: " + query);
		List<String> feeId = db.executeQuery(query, "ID");
		return feeId;
	}

	/**
	 * 
	 * @param schema
	 * @param product
	 * @param location
	 * @param feeScheduleTypeID
	 *            : 1 - RA Fee Schedule; 2 - RA Fee Threshold schedule; 17 - RA
	 *            Fee Schedule for Slip; 18 - RA Fee Threshold schedule for Slip
	 * @param salesChannelID
	 * @return
	 */
	public List<String> queryFeeSchdIDByLocProdAndSalesChannel(String schema,
			String product, String location, String feeScheduleTypeID,
			String salesChannelID) {
		db.resetSchema(schema);

		logger.debug("Reset schema as " + schema + " ; Query fee schedule id");

		String query = "SELECT p_fee_schd.ID"
				+ " FROM p_fee, p_fee_cond, p_fee_schd, p_condition"
				+ " WHERE p_fee.fee_id = p_fee_cond.fee_id"
				+ " and p_fee_cond.id = p_fee_schd.fee_cond_id"
				+ " and p_condition.id = p_fee_cond.cond_id"
				+ " AND p_fee_schd.SCHD_TYPE = " + feeScheduleTypeID
				+ " AND p_fee_schd.ACTIVE_IND=1 AND p_fee.PRD_ID="
				+ "(select PRD_ID from P_PRD where PRD_NAME='" + product
				+ "' and ACTIVE_IND=1)" + " AND p_fee_schd.SCHD_LOC_ID ="
				+ "(select ID from D_LOC where name = '" + location + "')"
				+ " and p_condition.sales_chanl_id = " + salesChannelID;

		logger.info("Execute the query: " + query);
		List<String> feeId = db.executeQuery(query, "ID");
		return feeId;
	}

	/**
	 * Get permit specific attribute child values from DB according to locID and
	 * permit type name
	 * 
	 * @param schema
	 * @param locID
	 * @param permitTypeName
	 * @return
	 */
	public String getPermitAttrChildValueFromDB(String schema, String locID,
			String permitTypeName, String attr_cat, String attr_cd) {
		logger.info("Start to get permit emergency contract rows from DB for permit:"
				+ permitTypeName
				+ ". attr_cd:"
				+ attr_cd
				+ " under locID:"
				+ locID);
		db.resetSchema(schema);

		logger.info("Query Emergency Contract rows for the permit type="
				+ permitTypeName);
		String query = "select attr_value from p_permit_type_attr_value "
				+ "where attr_id in "
				+ "(select attr_id from d_ATTR where attr_cat like '"
				+ attr_cat
				+ "' and attr_cd like '"
				+ attr_cd
				+ "') "
				+ "and permit_type_id in "
				+ "(select id from p_permit_type where permit_type_name_id in (select id from p_permit_type_name where name like '"
				+ permitTypeName + "') and active_ind = 1 and loc_id = "
				+ locID + ")";

		return db.executeQuery(query, "ATTR_VALUE", 0);
	}

	public int getPermitEmergencyContractRowsFromDB(String schema,
			String locID, String permitTypeName) {
		return Integer.valueOf(this.getPermitAttrChildValueFromDB(schema,
				locID, permitTypeName, "PermitType", "NumOfRows"));
	}

	public String getPermitEmergencyContractDisplayLabelFromDB(String schema,
			String locID, String permitTypeName) {
		return this.getPermitAttrChildValueFromDB(schema, locID,
				permitTypeName, "PermitType", "DisplayLabel");
	}

	public int getPermitAlternateLeadersMaxRowsFromDB(String schema,
			String locID, String permitTypeName) {
		return Integer.valueOf(this.getPermitAttrChildValueFromDB(schema,
				locID, permitTypeName, "Info", "NumOfAltLeaders"));
	}

	/**
	 * Get permit emergency contract applicable status from DB according to
	 * locID and permit type name status: Mandatory, Optional, Not Applicable
	 * 
	 * @param schema
	 * @param locID
	 * @param permitTypeName
	 * @param attr_cd
	 *            : select * from d_attr where attr_name LIKE 'PT_%'
	 * @return
	 */
	public String getPermitAttributeApplicableStatus(String schema,
			String locID, String permitTypeName, String attr_cd) {
		logger.info("Start to get permit atttribute applicable stauts from DB for permit:"
				+ permitTypeName + " under locID:" + locID);
		db.resetSchema(schema);

		logger.info("Query:" + permitTypeName);

		String query = "select attr_value from p_permit_type_attr_value "
				+ "where permit_type_id in (select id from p_permit_type where permit_type_name_id in (select id from p_permit_type_name where name like '"
				+ permitTypeName
				+ "') and active_ind = 1 and loc_id = "
				+ locID
				+ ") "
				+ "and attr_id in (select attr_id from d_attr where attr_cat like 'PermitType' and attr_cd like '"
				+ attr_cd + "')";

		logger.info("Sql:" + query);
		return db.executeQuery(query, "ATTR_VALUE", 0);
	}

	public String getPermitEmergencyContractApplicableStatus(String schema,
			String locID, String permitTypeName) {
		return this.getPermitAttributeApplicableStatus(schema, locID,
				permitTypeName, AttrName.PERMIT_EMERGENCYCONTACT);
	}

	public String getPermitTrailheadsApplicableStatus(String schema,
			String locID, String permitTypeName) {
		return this.getPermitAttributeApplicableStatus(schema, locID,
				permitTypeName, AttrName.PERMIT_TRAILHEAD);
	}

	public String getPermitTripItineraryApplicableStatus(String schema,
			String locID, String permitTypeName) {
		return this.getPermitAttributeApplicableStatus(schema, locID,
				permitTypeName, AttrName.PERMIT_TRIPITINERARY);
	}

	public String getPermitAlternateLeadersApplicableStatus(String schema,
			String locID, String permitTypeName) {
		return this.getPermitAttributeApplicableStatus(schema, locID,
				permitTypeName, AttrName.PERMIT_ALTERNATELEADERS);
	}

	public void updatePermitAttributeApplicableStatus(String schema,
			String locID, String permitTypeName, String attr_cd,
			String applicableStatus) {
		logger.info("Start to geupdatet permit atttribute applicable stauts from DB for permit:"
				+ permitTypeName + " under locID:" + locID);
		db.resetSchema(schema);

		String query = "update p_permit_type_attr_value set attr_value = '"
				+ applicableStatus
				+ "' "
				+ "where permit_type_id in (select id from p_permit_type where permit_type_name_id in (select id from p_permit_type_name where name like '"
				+ permitTypeName
				+ "') and active_ind = 1 and loc_id = "
				+ locID
				+ ") "
				+ "and attr_id in (select attr_id from d_attr where attr_cat like 'PermitType' and attr_cd like '"
				+ attr_cd + "')";

		logger.info("Sql:" + query);
		int count = db.executeUpdate(query);
		if (count < 1) {
			throw new ErrorOnPageException("Failed to update permit attribute:"
					+ attr_cd + " applicable status.");
		}
	}

	public void updatePermitIssueStationApplicableStatus(String schema,
			String locID, String permitTypeName, String applicableStatus) {
		this.updatePermitAttributeApplicableStatus(schema, locID,
				permitTypeName, AttrName.PERMIT_PERMITISSUESTATION,
				applicableStatus);
	}

	public void updatePermitDeliveryApplicableStatus(String schema,
			String locID, String permitTypeName, String applicableStatus) {
		this.updatePermitAttributeApplicableStatus(schema, locID,
				permitTypeName, AttrName.PERMIT_PERMITDELIVERYMETHOD,
				applicableStatus);
	}

	public void updatePermitMethodOfTravelStatus(String schema,
			String locID, String permitTypeName, String applicableStatus) {
		this.updatePermitAttributeApplicableStatus(schema, locID,
				permitTypeName, AttrName.PERMIT_METHODOFTRAVEL,
				applicableStatus);
	}
	/**
	 * Insert permit trailhead into DB
	 * 
	 * @param schema
	 * @param locID
	 * @param optionName
	 */
	public void insertPermitTrailheadOptionIntoDB(String schema, String locID,
			String optionName) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema + " ; "
				+ "Insert permit Trailhead option with locID:" + locID
				+ " option Name:" + optionName + " into DB");

		String query = "select ID from d_loc_trailhead where loc_id=" + locID
				+ " and name = '" + optionName + "'";
		List<String> results = db.executeQuery(query, "ID");
		if (results.isEmpty()) {
			logger.info("Add Trailhead option: " + optionName);
			query = "insert into d_loc_trailhead values (get_sequence('d_loc_trailhead'), '"
					+ optionName + "', " + locID + ", 0, 1, 1)";
			db.executeUpdate(query);
			logger.info("Insert done!");
		} else {
			query = "update d_loc_trailhead set active_ind=1 where loc_id='"
					+ locID + "' and name='" + optionName + "'";
			db.executeUpdate(query);
			logger.info("Update the Trail Head active_ind =1");
		}
	}

	/**
	 * Update permit trail head option active_ind
	 * 
	 * @param schema
	 * @param locID
	 * @param optionName
	 * @param isActive
	 *            : true:active, fals:inactive
	 */
	public void changePermitTrailheadOptionStatusFromDB(String schema,
			String locID, String optionName, boolean isActive) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema + " ; "
				+ "Update permit Trailhead options active_ind as '"
				+ (isActive ? "1" : "0") + "' with locID:" + locID
				+ " optionName:" + optionName + " from DB");

		String query = "update d_loc_trailhead set active_ind="
				+ (isActive ? "1" : "0") + " where loc_id='" + locID
				+ "' and name='" + optionName + "'";
		int count = db.executeUpdate(query);
		if (count == 0) {
			throw new ErrorOnDataException("Update failed.");
		}
	}

	/**
	 * Query permit TrailHead options from DB
	 * 
	 * @param schema
	 * @param locID
	 * @param isActive
	 *            : true:active, false:inactive
	 * @return
	 */
	public List<String> queryPermitTrailheadOptionsFromDB(String schema,
			String locID, boolean isActive) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema + " ; "
				+ "Query permit Trailhead options with locID:" + locID
				+ " active_ind:" + (isActive ? "1" : "0") + " from DB");

		String query = "select name from d_loc_trailhead where loc_id=" + locID
				+ " and active_ind = " + (isActive ? "1" : "0")
				+ " order by name asc";
		logger.info("Execute the query: " + query);

		List<String> trailHeadOptions = db.executeQuery(query, "NAME");
		return trailHeadOptions;
	}

	/**
	 * Insert permit Tripitinerary into DB
	 * 
	 * @param schema
	 * @param locID
	 * @param tripitinerary
	 */
	public void insertPermitTripitineraryOptionIntoDB(String schema,
			String entranceCode, String parkID, String tripitinerary) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema + " ; "
				+ "Insert permit Tripitinerary option with prd_cd:"
				+ entranceCode + " option Name:" + tripitinerary + " into DB");

		// String locID = db.executeQuery(
		// "select distinct loc_id from p_prd where park_id = '" + parkID
		// + "' and prd_cd = '" + entranceCode + "'", "LOC_ID")
		// .get(0);
		String entranceZoneId = db
				.executeQuery(
						"select id from d_loc where id in (select loc_id from P_ENTR_ZONE where entr_id in (select PRD_ID from P_PRD where  prd_cd = '"
								+ entranceCode
								+ "' and PARK_ID= "
								+ parkID
								+ " ))", "ID").get(0);
		String query = "select ID from d_loc_permit_point where loc_id = "
				+ entranceZoneId + " and name = '" + tripitinerary + "'";

		logger.info("Check query:" + query);

		List<String> results = db.executeQuery(query, "ID");
		if (results.isEmpty()) {
			logger.info("Add Tripitinerary option: " + tripitinerary);

			query = "insert into d_loc_permit_point (id, loc_id, name, campsite_ind, active_ind) values (get_sequence('d_loc_permit_point'), "
					+ entranceZoneId + ", '" + tripitinerary + "', 1, 1)";

			logger.info("Insert query:" + query);
			db.executeUpdate(query);
			logger.info("Insert done!");
		} else {
			query = "update d_loc_permit_point set active_ind=1 where loc_id  = "
					+ entranceZoneId + " and name='" + tripitinerary + "'";
			db.executeUpdate(query);
			logger.info("Update the Tripitinerary active_ind =1");
		}
	}

	/**
	 * Update permit Tripitinerary option active_ind
	 * 
	 * @param schema
	 * @param tripitinerary
	 * @param isActive
	 *            : true:active, false:inactive
	 */
	public void changeTripitineraryOptionStatusFromDB(String schema,
			String tripitinerary, boolean isActive) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema + " ; "
				+ "Update permit Tripitinerary options active_ind as '"
				+ (isActive ? "1" : "0") + "' with optionName:" + tripitinerary
				+ " from DB");

		String query = "update d_loc_permit_point set active_ind="
				+ (isActive ? "1" : "0") + " where name='" + tripitinerary
				+ "'";
		int count = db.executeUpdate(query);
		if (count == 0) {
			throw new ErrorOnDataException("Update failed.");
		}
	}

	/**
	 * Update permit Tripitinerary RestricFirstNightsStay status
	 * 
	 * @param schema
	 * @param parkID
	 * @param optionName
	 * @param isActive
	 *            : true:active, false:inactive
	 */
	public void setupTripitineraryRestricFirstNightsStayFromDB(String schema,
			String parkID, String permitTypeName, String optionName,
			boolean isActive) {
		db.resetSchema(schema);
		logger.debug("Reset schema as "
				+ schema
				+ " ; "
				+ "Update permit Tripitinerary 'Restrict First Night's Stay' active_ind as '"
				+ (isActive ? "1" : "0") + "' with locID:" + parkID
				+ " optionName:" + optionName + " from DB");

		String query = "update p_permit_type_attr_value set active_ind "
				+ (isActive ? "1" : "0")
				+ " where id in "
				+ "(select id from p_permit_type_attr_value where parent_id in (select id from p_permit_type_attr_value where attr_id in ((select attr_id from d_ATTR where attr_cat like 'PermitType' and attr_cd like 'PT_TripItinerary') and permit_type_id in (select id from p_permit_type where permit_type_name_id in (select id from p_permit_type_name where name like '"
				+ permitTypeName + "') and active_ind = 1 and loc_id = "
				+ parkID + ")))";

		db.executeUpdate(query);
	}

	/**
	 * Query permit Tripitinerary options from DB
	 * 
	 * @param schema
	 * @param locID
	 * @param isActive
	 *            : true:active, false:inactive
	 * @return
	 */
	public List<String> queryPermitTripitineraryOptionsFromDB(String schema,
			String parkID, boolean isActive) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema + " ; "
				+ "Query permit Trailhead options with parkID:" + parkID
				+ " active_ind:" + (isActive ? "1" : "0") + " from DB");

		String query = "select distinct p.name from d_loc_permit_point p, d_loc l where p.loc_id=l.id and l.parent_id="
				+ parkID
				+ " and l.type_id=35 and campsite_ind=1  and p.active_ind = "
				+ (isActive ? "1" : "0") + " order by p.name asc";
		logger.info("Execute the query: " + query);

		List<String> trailHeadOptions = db.executeQuery(query, "NAME");
		return trailHeadOptions;
	}

	/**
	 * Query all active locations which in the same zone as the Entrance
	 * 
	 * @param schema
	 * @param parkID
	 * @param entranceCode
	 * @param isActive
	 * @return
	 */
	public List<String> queryPermitTripitineraryOptionsInEntranceZoneLevel(
			String schema, String parkID, String entranceCode, boolean isActive) {
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema + " ; "
				+ "Query permit Trailhead options with parkID:" + parkID
				+ " active_ind:" + (isActive ? "1" : "0") + " in entrance:"
				+ entranceCode + " level from DB");

		String query = "select distinct name from d_loc_permit_point where loc_id in "
				+ "(select id from d_loc where id in "
				+ "(select loc_id from P_ENTR_ZONE where entr_id in "
				+ "(select PRD_ID from P_PRD where  prd_cd = '"
				+ entranceCode
				+ "' and PARK_ID= '"
				+ parkID
				+ "'))) and active_ind = "
				+ (isActive ? "1" : "0");

		logger.info("Execute the query: " + query);

		List<String> trailHeadOptions = db.executeQuery(query, "NAME");
		return trailHeadOptions;
	}

	public boolean checkCustomerExists(String fname, String lname, String schema) {
		String query = "select count(*) as qty from c_cust where f_name='"
				+ fname + "' and l_name='" + lname + "'";
		db.resetSchema(schema);
		logger.info("query:" + query);
		String result = db.executeQuery(query, "qty", 0);

		return !result.equalsIgnoreCase("0");
	}

	public List<String> getCustomerPrivileges(String fname, String lname,
			String licenseYear, String schema) {
		String query = "select opi.priv_number from o_priv_inst opi, c_cust cc, c_cust_hfprofile cch where cc.f_name='"
				+ fname
				+ "' and cc.l_name='"
				+ lname
				+ "' and cc.cust_id=cch.cust_id and cch.id=opi.cust_prof_id and opi.status_id=1 and license_year="
				+ "'" + licenseYear + "'";

		db.resetSchema(schema);
		List<String> result = db.executeQuery(query, "priv_number");

		return result;
	}

	public boolean getVendorAutoReturnVoidedDoc(String locationName,
			String schema) {
		String query = "select fc.rtn_voided_doc as value from d_loc dl, d_store ds, f_vendor_financial_config fc where dl.name ='"
				+ locationName
				+ "' and dl.id=ds.loc_id and ds.vendor_id=fc.vendor_id";
		db.resetSchema(schema);
		String code = db.executeQuery(query, "value", 0);

		if (code.equalsIgnoreCase("1"))
			return true;
		else if (code.equalsIgnoreCase("0"))
			return false;
		else {
			throw new ItemNotFoundException(
					"Failed to get AutoReturnVoidedDoc code using sql \""
							+ query + "\"");
		}

	}

	/**
	 * Check wheather fulfillment with given tracking number exist
	 * 
	 * @param trackingNum
	 * @param schema
	 * @return true: exist false: no exist
	 */
	public boolean fulfillmentWithTrackingNumExist(String trackingNum,
			String schema) {
		boolean exist = false;
		db.resetSchema(schema);
		String query = "select * from d_packing_slip where shipping_track_num='"
				+ trackingNum + "'";
		List<String> results = db.executeQuery(query, "ID");
		if (results.isEmpty()) {
			logger.info("Fulfillment with tracking number " + trackingNum
					+ " does not exist.");
			return false;
		} else {
			logger.info("Fulfillment with tracking number " + trackingNum
					+ " exist!");
			exist = true;
		}
		return exist;
	}

	/**
	 * Add a new carrier if it does not exist
	 * 
	 * @param carrierName
	 * @param schema
	 */
	public void addCarrier(String carrierName, String schema,
			String carrierEmail) {
		db.resetSchema(schema);
		String query = "select * from d_shipping_carrier where name='"
				+ carrierName + "'";
		List<String> results = db.executeQuery(query, "ID");
		if (results.isEmpty()) {
			logger.info("Add a new carrier: " + carrierName);
			String sql = "Insert into d_shipping_carrier(id,name,url) values((select max(id) from SITE_USED)+1"
					+ "," + carrierName + ", '" + carrierEmail + "')";
			logger.debug(sql);
			db.executeUpdate(sql);
			logger.info("Insert done!");

		}
	}

	/**
	 * Get transmission day offset from the table f_eft_schedule by the eft
	 * frequency ID
	 * 
	 * @param eftFrequencyID
	 * @param schema
	 */
	public int getTransmissionDayOffset(String eftFrequencyID, String schema) {
		db.resetSchema(schema);
		String query = "select TRANS_DAY_OFFSET from F_EFT_SCHEDULE "
				+ "where EFT_FREQUENCY=" + eftFrequencyID;
		logger.info("Execute the Query: " + query);
		String result = db.executeQuery(query, "TRANS_DAY_OFFSET", 0);
		logger.info("The transmission day offset is " + result);
		return Integer.valueOf(result);
	}

	/**
	 * Get EFT invoice payment records by invoice ID
	 * 
	 * @param invoice
	 *            ID
	 * @param schema
	 * @return
	 */
	public List<HashMap<String, String>> getEFTInvoiceAdjPaymentRecords(
			String invoiceID, String schema) {
		db.resetSchema(schema);
		logger.info("Get EFT Invoice Adjustment Payment ID by EFT invoice ID "
				+ invoiceID + "...");

		String query = "SELECT ID,EFT_INVC_PMT_ID,AMOUNT FROM F_EFT_INVC_ADJ WHERE EFT_INVOICE_ID="
				+ invoiceID;
		logger.info("SQL: " + query);

		List<HashMap<String, String>> results = db.executeQuery(query);
		if (results.size() > 0) {
			logger.info("Number of Invoice Adjustment Payment record is -->"
					+ results.size());
			return results;
		} else {
			logger.info("Cannot get any invoice adjustment Payment ID!!!!");
			return null;
		}
	}

	/**
	 * Get Roles/Feature records.
	 * 
	 * @param role
	 *            , role name
	 * @param featureDesc
	 *            , feature description
	 * @param applicationName
	 *            , application on which the feature will be assgined.
	 * @param schema
	 * @return
	 */
	public List<HashMap<String, String>> getRolesFeatures(String role,
			String featureDesc, String applicationName, String schema) {

		if (!role.toUpperCase().contains("Auto".toUpperCase())) {
			role = role + " - Auto";// Added for new role of Automation
		}
		db.resetSchema(schema);
		logger.info("Try to get role/feature record of -->" + role + "/"
				+ featureDesc + "/" + applicationName);
		String query = "";
		if (applicationName.equalsIgnoreCase(MARINA_MANAGER)) {// there
			// is
			// no
			// record
			// for
			// marina
			// manager
			// in
			// table
			// D_REF_CB_DICTIONARY.CB_BASE_CLASS='com.reserveamerica.framework.security.configurable.ApplicationID'
			// and
			// "
			query = "SELECT X_ROLE_FEAT.* "
					+ "FROM X_ROLE_FEAT,   X_FEAT,    X_ROLE, X_APPLICATION "
					+ "WHERE X_FEAT.FEAT_ID                 =X_ROLE_FEAT.FEAT_ID "
					+ "AND X_FEAT.APP_ID                    = X_APPLICATION.APP_ID "
					+ "AND X_APPLICATION.APP_NAME = '" + applicationName + "' "
					+ "AND X_ROLE_FEAT.ROLE_ID              = X_ROLE.ID "
					+ "AND X_ROLE.NAME                      = '" + role + "' "
					+ "AND X_FEAT.FEAT_DESC                 = '" + featureDesc
					+ "'  ";
		} else {
			query = "SELECT X_ROLE_FEAT.* "
					+ "FROM X_ROLE_FEAT, X_FEAT, D_REF_CB_DICTIONARY, X_ROLE "
					+ "WHERE X_FEAT.FEAT_ID =X_ROLE_FEAT.FEAT_ID  "
					+ "AND X_FEAT.APP_ID = D_REF_CB_DICTIONARY.CB_ID "
					+ "AND D_REF_CB_DICTIONARY.CB_BASE_CLASS='com.reserveamerica.framework.security.configurable.ApplicationID'  "
					+ "AND X_ROLE_FEAT.ROLE_ID = X_ROLE.ID "
					+ "AND D_REF_CB_DICTIONARY.CB_NAME = '" + applicationName
					+ "' " + "AND X_ROLE.NAME = '" + role + "' "
					+ "AND X_FEAT.FEAT_DESC = '" + featureDesc + "'  ";
		}
		logger.info("SQL: " + query);

		List<HashMap<String, String>> results = db.executeQuery(query);
		if (results.size() > 0) {
			logger.info("Found records  -->" + results.size());

		} else {
			logger.info("Cannot get any role/feature record !!!!");
		}

		return results;
	}

	/**
	 * @role:
	 * @features: String[][] features ={ {"RequestStockTransfer",
	 *            "Request Stock Transfer"}, {"SearchStockTransfer",
	 *            "Search Stock Transfer"}, }
	 * @applicationName: FieldManager/LicenseManager....
	 */
	public void checkRolesFeatures(String role, String[][] features,
			String applicationName, String schema) {
		List<HashMap<String, String>> rs;
		String log = "";
		int IDX_FEATURE_DESC = 1;

		for (String[] feature : features) {
			rs = this.getRolesFeatures(role, feature[IDX_FEATURE_DESC],
					applicationName, schema);
			if (rs.size() < 1) {
				log += "Cannot find role/feature of " + role + "/"
						+ feature[IDX_FEATURE_DESC] + " in " + applicationName
						+ " of " + schema
						+ ", Please add this feature in AdminManager!!!"
						+ "\r\n";

			}
		}
		if (!StringUtil.isEmpty(log)) {
			throw new ErrorOnDataException(log);
		}

	}

	public List<HashMap<String, String>> getRAFeeInfoFromDB(String ordNum,
			String schema) {
		db.resetSchema(schema);
		logger.info("Changed schema to -->>" + schema);

		String query = "SELECT O_ORD_ITEM_RAFEE.fee_amt AMOUNT,  "
				+ "O_ORD_ITEM_RAFEE.id ID,  "
				+ "O_ORD_ITEM_RAFEE.fee_schd_id SCHEDULE_ID,  "
				+ "O_ORD_ITEM_RAFEE.rafee_type_id RAFEE_TYPE,  "
				+ "O_ORD_ITEM_RAFEE.rafee_status  RAFEE_STATUS, "
				+ "O_ORD_ITEM_RAFEE.rafee_threshd_id THRESHD_ID "
				+ "FROM O_ORD_ITEM_RAFEE INNER JOIN o_order ON O_ORD_ITEM_RAFEE.ord_id=o_order.ID "
				+ "WHERE o_order.ord_num ='" + ordNum + "' order by id";

		logger.debug("Execute query: " + query);
		List<HashMap<String, String>> records = db.executeQuery(query);
		logger.info("Data fetched from DB is -->" + records.size()
				+ " records...");
		return records;
	}

	public String getRAFeeRateFromDB(String feeScheduleId, String schema) {
		db.resetSchema(schema);
		String query = "select rate from p_rafee_rate where fee_schd_id="
				+ feeScheduleId;
		logger.debug("Execute query: " + query);

		String rate = db.executeQuery(query, "RATE", 0);
		logger.info("The transmission day offset is " + rate);
		return rate;
	}

	public HashMap<String, String> getRAFeeInfoByIdFromDB(String feeId,
			String schema) {
		db.resetSchema(schema);
		logger.info("Changed schema to -->>" + schema);

		String query = "SELECT FEE_AMT, RAFEE_TYPE_ID,RAFEE_STATUS FROM O_ORD_ITEM_RAFEE WHERE ID     ="
				+ feeId;

		logger.debug("Execute query: " + query);
		List<HashMap<String, String>> records = db.executeQuery(query);
		if (records.size() < 1) {
			throw new ErrorOnDataException(
					"Cannot find Ra fee info record of id-->" + feeId);
		}
		logger.info("Data fetched from DB is -->" + records.size()
				+ " records...");
		return records.get(0);
	}

	public String getThresholdCounterFromDB(String thresholdSchedueId,
			String schema) {
		db.resetSchema(schema);
		logger.info("Changed schema to -->>" + schema);

		String query = "SELECT CURRENT_COUNT, FEE_SCHD_ID,START_COUNT "
				+ "FROM P_THRESHD_COUNTER " + "WHERE FEE_SCHD_ID="
				+ thresholdSchedueId;
		logger.debug("Execute query: " + query);
		List<HashMap<String, String>> records = db.executeQuery(query);

		if (records.size() < 1) {
			throw new ErrorOnDataException(
					"Cannot find any threshold info from table 'P_THRESHD_COUNTER', please check RA fee threshold schedule-->"
							+ thresholdSchedueId);
		}
		logger.info("Data fetched from DB is "
				+ records.get(0).get("CURRENT_COUNT") + " "
				+ records.get(0).get("START_COUNT"));
		return records.get(0).get("CURRENT_COUNT");
	}

	/**
	 * 
	 * @attributes: String[][] facilityAttr= {{"Waiting List","Y"},
	 *              {"Transfer List", "Y"}};
	 * @facilityId: facility id
	 * @schema:
	 * 
	 */
	public void checkFacilityAttribute(String[][] attributes,
			String facilityId, String schema) {
		List<HashMap<String, String>> rs;
		String log = "";
		int ATTR_NAME_IDX = 0;
		int ATTR_VALUE_IDX = 1;

		for (String[] attr : attributes) {
			rs = this.getFacilityAttr(facilityId, attr[ATTR_NAME_IDX], schema);
			if (rs.size() < 1) {
				log += "Cannot find facility/attibute of " + facilityId + "/"
						+ attr[ATTR_NAME_IDX] + " in " + schema + "\r\n";

			} else if (!rs.get(0).get("VALUE")
					.equalsIgnoreCase(attr[ATTR_VALUE_IDX])) {
				log += "Attibute value of " + facilityId + "/"
						+ attr[ATTR_NAME_IDX] + " does not equal to "
						+ attr[ATTR_VALUE_IDX] + "\r\n";
			}
		}
		if (!StringUtil.isEmpty(log)) {
			throw new ErrorOnDataException(log);
		}
	}

	public void setFacilityAttributes(String attributes[][], String facilityID,
			String schema) {
		db.resetSchema(schema);
		String query = "";
		String value = "";
		for (int i = 0; i < attributes.length; i++) {
			value = attributes[i][1].matches("\\d+") ? attributes[i][1] : "'"
					+ attributes[i][1] + "'";
			query = "insert into D_LOC_ATTR_VALUE(ID, LOC_ID, ATTR_ID, VALUE) values(get_sequence('D_LOC_ATTR_VALUE'), "
					+ facilityID
					+ ", (select ATTR_ID from D_ATTR where ATTR_NAME='"
					+ attributes[i][0] + "'), " + value + ")";
			logger.info("Execute query: " + query);
			db.executeUpdate(query);
		}
	}

	public boolean isFacilityAttributesSet(String attributes[][],
			String facilityID, String schema) {
		List<HashMap<String, String>> rs;
		int ATTR_NAME_IDX = 0;
		int ATTR_VALUE_IDX = 1;

		boolean result = true;
		for (String[] attr : attributes) {
			rs = this.getFacilityAttr(facilityID, attr[ATTR_NAME_IDX], schema);
			if (rs.size() < 1) {
				return false;
			} else if (!MiscFunctions.compareResult(attr[ATTR_NAME_IDX],
					attr[ATTR_VALUE_IDX], rs.get(0).get("VALUE"))) {
				result &= false;
			}
		}

		return result;
	}

	/**
	 * Get facility/attribute records.
	 * 
	 * @param facilityId
	 *            , id of facility
	 * @param attrName
	 *            , attribute name
	 * @param schema
	 * @return
	 */
	public List<HashMap<String, String>> getFacilityAttr(String facilityId,
			String attrName, String schema) {

		db.resetSchema(schema);
		logger.info("Try to get attribute -->" + attrName + " of facility["
				+ facilityId + "]");

		String query = "SELECT D_LOC_ATTR_VALUE.VALUE "
				+ "FROM D_LOC_ATTR_VALUE  "
				+ "INNER JOIN D_ATTR ON D_LOC_ATTR_VALUE.ATTR_ID=D_ATTR.ATTR_ID AND D_ATTR.ATTR_NAME='"
				+ attrName + "' " + "WHERE D_LOC_ATTR_VALUE.LOC_ID IN ("
				+ facilityId + ")";
		logger.info("SQL: " + query);

		List<HashMap<String, String>> results = db.executeQuery(query);
		if (results.size() > 0) {
			logger.info("Found records  -->" + results.size());

		} else {
			logger.info("Cannot get any facility/attribute record !!!!");
		}

		return results;
	}

	/**
	 * Get Facility Attribute Value per attribute name and facility id
	 * 
	 * @param facilityId
	 * @param attrName
	 * @param schema
	 * @return
	 * @author Lesley Wang
	 * @Date Jun 26, 2013
	 */
	public String getFacilityAttrValue(String facilityId, String attrName,
			String schema) {
		List<HashMap<String, String>> rs;
		rs = this.getFacilityAttr(facilityId, attrName, schema);
		return rs.get(0).get("VALUE");
	}

	/**
	 * Get EFT invoice Transmission record by invoice ID
	 * 
	 * @param invoice
	 *            ID
	 * @param schema
	 * @return
	 */
	public List<HashMap<String, String>> getEFTInvoiceTransmissionRecords(
			String invoiceID, String schema) {
		db.resetSchema(schema);
		logger.info("Get EFT Invoice Transmissions by EFT invoice ID "
				+ invoiceID + "...");

		String query = "SELECT ID,  " + "ACTIVE_IND, " + "AMOUNT , "
				+ "CONC_VERS_ID," + "CREATE_DATE , " + "EFT_INVOICE_ID, "
				+ "EFT_INVC_ADJ_ID, " + "EFT_RETURN_TRANSACTION_ID, "
				+ "EFT_TRANSMISSION_ID, " + "EFT_TRANSMISSION_JOB_ID, "
				+ "FROM_ID , " + "STATUS , " + "TRANS_DUE_DATE "
				+ "FROM F_EFT_INVC_TRANSM  " + "WHERE EFT_INVOICE_ID="
				+ invoiceID + " " + "ORDER BY CREATE_DATE DESC NULLS LAST ";
		logger.info("SQL: " + query);

		List<HashMap<String, String>> results = db.executeQuery(query);
		if (results.size() > 0) {
			logger.info("Number of Invoice Transmissions records is -->"
					+ results.size());
			return results;
		} else {
			logger.info("Cannot get any invoice adjustment Payment ID!!!!");
			return null;
		}
	}

	/**
	 * Get Daily EFT Summary records by invoice ID
	 * 
	 * @param invoice
	 *            ID
	 * @param schema
	 * @return
	 */
	public List<HashMap<String, String>> getDailyEFTSummaryRecords(
			String invoiceID, String schema) {
		db.resetSchema(schema);
		logger.info("Get Daily EFT Summary by EFT invoice ID " + invoiceID
				+ "...");

		String query = "select "
				+ "aa.Posted_Date "
				+ "as postedDate, "
				+ "count(distinct(bb.RCPT_ID)) as numberOfReceipts, "
				+ "sum(aa.subAmountForSales) amountForSales, "
				+ "sum(aa.subAmountForDocReturn) amountForDocReturn, "
				+ "sum(aa.subAmountForChargedVoids) amountForChargedVoids, "
				+ "sum(aa.subAmountForCreditedVoids) amountForCreditedVoids, "
				+ "sum(aa.subAmountForDeductedVendorFees) amountForDeductedVendorFees, "
				+ "sum(aa.subAmountForStoreAdjustments) amountForStoreAdjustments, "
				+ "sum(aa.subAmountForDepositAdjustments) amountForDepositAdjustments, "
				+ "sum(aa.subAmountForVoucherGC) amountForVoucherGC, "
				+ "sum(aa.subAmountForSales)+sum(aa.subAmountForDocReturn)+sum(aa.subAmountForChargedVoids)+sum(aa.subAmountForCreditedVoids)-sum(aa.subAmountForDeductedVendorFees)+sum(aa.subAmountForStoreAdjustments)+sum(aa.subAmountForDepositAdjustments)+sum(aa.subAmountForVoucherGC) netAmount "
				+ "FROM ( "
				+ "select TO_CHAR(a.JRNL_DATE,'YYYY-MM-DD') as Posted_Date, a.TRAN_ID, a.amount as subAmountForSales, 0 as subAmountForDocReturn, 0 as subAmountForChargedVoids, 0 as subAmountForCreditedVoids, 0 as subAmountForDeductedVendorFees, 0 as subAmountForStoreAdjustments, 0 as subAmountForDepositAdjustments, 0 as subAmountForVoucherGC from F_DEFT a where a.EFT_INVOICE_ID="
				+ invoiceID
				+ "  and a.PMT_ALLOC_ID>0 and not (a.TRAN_TYPE_ID in (10003, 10004) and a.TRAN_PROC_GRP_ID not in (select yy.PROCESS_GRP_ID from O_ORD_ITEM_TRANS yy where yy.TRANS_TYP_ID=10007) and exists(select * from o_ord_item_trans t2 where t2.process_grp_id = a.TRAN_PROC_GRP_ID and t2.id in (select attr.ord_trans_id from O_ORD_TRANS_ATTR attr where attr.trans_attr_id=10004 and attr.trans_attr_value='true')) ) and not (a.TRAN_TYPE_ID=6011 and a.FEE_TRAN_TYPE_ID=10019) and not (a.TRAN_TYPE_ID=10007) union all "
				+ "select TO_CHAR(a.JRNL_DATE,'YYYY-MM-DD') as Posted_Date, a.TRAN_ID, 0 as subAmountForSales, a.amount as subAmountForDocReturn, 0 as subAmountForChargedVoids, 0 as subAmountForCreditedVoids, 0 as subAmountForDeductedVendorFees, 0 as subAmountForStoreAdjustments, 0 as subAmountForDepositAdjustments, 0 as subAmountForVoucherGC from F_DEFT a where a.EFT_INVOICE_ID="
				+ invoiceID
				+ "  and a.PMT_ALLOC_ID>0 and a.TRAN_TYPE_ID in (10003, 10004) and a.TRAN_PROC_GRP_ID not in (select yy.PROCESS_GRP_ID from O_ORD_ITEM_TRANS yy where yy.TRANS_TYP_ID=10007) and exists(select * from o_ord_item_trans t2 where t2.process_grp_id = a.TRAN_PROC_GRP_ID and t2.id in (select attr.ord_trans_id from O_ORD_TRANS_ATTR attr where attr.trans_attr_id=10004 and attr.trans_attr_value='true')) union all "
				+ "select TO_CHAR(a.JRNL_DATE,'YYYY-MM-DD') as Posted_Date, a.TRAN_ID, 0 as subAmountForSales, 0 as subAmountForDocReturn, a.amount as subAmountForChargedVoids, 0 as subAmountForCreditedVoids, 0 as subAmountForDeductedVendorFees, 0 as subAmountForStoreAdjustments, 0 as subAmountForDepositAdjustments, 0 as subAmountForVoucherGC from F_DEFT a where a.EFT_INVOICE_ID="
				+ invoiceID
				+ "  and a.PMT_ALLOC_ID>0 and a.TRAN_TYPE_ID=6011 and a.FEE_TRAN_TYPE_ID=10019 union all "
				+ "select TO_CHAR(a.JRNL_DATE,'YYYY-MM-DD') as Posted_Date, a.TRAN_ID, 0 as subAmountForSales, 0 as subAmountForDocReturn, 0 as subAmountForChargedVoids, a.amount as subAmountForCreditedVoids, 0 as subAmountForDeductedVendorFees, 0 as subAmountForStoreAdjustments, 0 as subAmountForDepositAdjustments, 0 as subAmountForVoucherGC from F_DEFT a where a.EFT_INVOICE_ID="
				+ invoiceID
				+ "  and a.PMT_ALLOC_ID>0 and a.TRAN_TYPE_ID=10007 union all "
				+ "select TO_CHAR(a.JRNL_DATE,'YYYY-MM-DD') as Posted_Date, a.TRAN_ID, 0 as subAmountForSales, 0 as subAmountForDocReturn, 0 as subAmountForChargedVoids, 0 as subAmountForCreditedVoids, a.amount as subAmountForDeductedVendorFees, 0 as subAmountForStoreAdjustments, 0 as subAmountForDepositAdjustments, 0 as subAmountForVoucherGC from F_DEFT a where a.EFT_INVOICE_ID="
				+ invoiceID
				+ "  and a.PMT_ALLOC_ID>0 and a.FEE_TYPE=36 and (a.DIFF_LOC_IND=0 or a.DIFF_LOC_IND is null) and a.EFT_SCHDL_ID is not null and exists (select zz.ID from F_EFT_CONF_SCHEDULE zz where zz.ID=a.EFT_SCHDL_ID and zz.DEDUCT_VENDOR_FEE_IND=1) union all "
				+ "select TO_CHAR(a.JRNL_DATE,'YYYY-MM-DD') as Posted_Date, a.TRAN_ID, 0 as subAmountForSales, 0 as subAmountForDocReturn, 0 as subAmountForChargedVoids, 0 as subAmountForCreditedVoids, 0 as subAmountForDeductedVendorFees, a.amount as subAmountForStoreAdjustments, 0 as subAmountForDepositAdjustments, 0 as subAmountForVoucherGC from F_DEFT a where a.EFT_INVOICE_ID="
				+ invoiceID
				+ "  and a.STORE_EFTADJ_ID>0 union all "
				+ "select TO_CHAR(a.JRNL_DATE,'YYYY-MM-DD') as Posted_Date, a.TRAN_ID, 0 as subAmountForSales, 0 as subAmountForDocReturn, 0 as subAmountForChargedVoids, 0 as subAmountForCreditedVoids, 0 as subAmountForDeductedVendorFees, 0 as subAmountForStoreAdjustments, a.amount as subAmountForDepositAdjustments, 0 as subAmountForVoucherGC from F_DEFT a where a.EFT_INVOICE_ID="
				+ invoiceID
				+ "  and a.DEP_ADJ_ID>0 union all "
				+ "select TO_CHAR(a.JRNL_DATE,'YYYY-MM-DD') as Posted_Date, a.TRAN_ID, 0 as subAmountForSales, 0 as subAmountForDocReturn, 0 as subAmountForChargedVoids, 0 as subAmountForCreditedVoids, 0 as subAmountForDeductedVendorFees, 0 as subAmountForStoreAdjustments, 0 as subAmountForDepositAdjustments, a.amount as subAmountForVoucherGC from F_DEFT a where a.EFT_INVOICE_ID="
				+ invoiceID
				+ "  and ( a.va_ALLOC_ID > 0 or a.gc_alloc_id > 0) "
				+ ") aa left join O_RCPT_TRANS bb on aa.TRAN_ID=bb.ORD_ITEM_TRANS_ID "
				+ "GROUP BY AA.POSTED_DATE " + "order by aa.Posted_Date ";

		logger.info("SQL: " + query);

		List<HashMap<String, String>> results = db.executeQuery(query);
		if (results.size() > 0) {
			logger.info("There are " + results.size()
					+ " Daily EFT summary records.");
			return results;
		} else {
			logger.info("Cannot get records!!!!");
			return null;
		}
	}

	public void insertLocationAttributeValue(String schema, String attrName,
			String locName, boolean yes) {
		db.resetSchema(schema);

		String attrID = MiscFunctions.covertAttribute(attrName);
		String value = yes ? "Y" : "N";

		String sql = "insert into d_loc_attr_value(id, loc_id, attr_id, value) values( get_sequence('d_loc_attr_value'), "
				+ "(select id from d_loc where d_loc.name = '"
				+ locName
				+ "' and type_id = 7), " + attrID + ", '" + value + "')";
		db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
	}

	public void updateLocationAttributeValue(String schema, String attrName,
			String locName, boolean yes) {
		db.resetSchema(schema);

		String attrID = MiscFunctions.covertAttribute(attrName);
		String value = yes ? "Y" : "N";

		String sql = "update d_loc_attr_value set VALUE = '" + value
				+ "' where attr_id = " + attrID
				+ " and loc_id = (select id from d_loc where name = '"
				+ locName + "' and type_id = 7)";
		int num = db.executeUpdate(sql);
		if (num == 0) {
			throw new ErrorOnDataException("No record found.");
		}
		logger.info("Execute query: " + sql);
	}

	/**
	 * Check location attribute value record whether existing
	 * 
	 * @param locactionName
	 * @param attributeName
	 * @param valueIsTrue
	 * @param schema
	 * @return
	 */
	public boolean checkLocationAttributeValueWetherExisting(
			String locactionName, String attributeName, boolean valueIsTrue,
			String schema) {
		db.resetSchema(schema);

		String attrID = MiscFunctions.covertAttribute(attributeName);
		String value = valueIsTrue ? "Y" : "N";

		String query = "select * from d_loc_attr_value where value = '" + value
				+ "'" + " and attr_id = " + attrID
				+ " and loc_id = (select id from d_loc where name = '"
				+ locactionName + "'" + " and type_id = 7)";

		logger.info("Execute the Query: " + query);
		List<String> results = db.executeQuery(query, "ID");
		if (results.isEmpty()) {
			logger.info("This location attribute value info not existing in  d_loc_attr_value table. "
					+ "location name = "
					+ locactionName
					+ ", attribute name = "
					+ attributeName
					+ ", value = "
					+ value);
			return false;
		} else {
			logger.info("This location attribute value info existing in  d_loc_attr_value table. "
					+ "location name = "
					+ locactionName
					+ ", attribute name = "
					+ attributeName
					+ ", value = "
					+ value);
			return true;
		}
	}

	/**
	 * Get Invoice Nums by order num
	 * 
	 * @param ordNum
	 * @param schema
	 * @return
	 */
	public List<String> getInvoiceNumByOrderNum(String ordNum, String schema) {
		db.resetSchema(schema);
		logger.info("Get Invoice Number info by order number " + ordNum + "...");

		String query = "select eft_invoice_id from f_deft where ord_num = '"
				+ ordNum + "' order by ID";
		logger.info("SQL:" + query);
		List<String> results = db.executeQuery(query, "eft_invoice_id");
		return results;
	}

	/**
	 * Get Invoice job id by order num
	 * 
	 * @param ordNum
	 * @param schema
	 * @return
	 */
	public List<String> getInvoiceJobIDByOrderNum(String ordNum, String schema) {
		db.resetSchema(schema);
		logger.info("Get Invoice Job ID by order number " + ordNum + "...");

		String query = "select EFT_INVOICE_JOB_ID from f_deft where ord_num = '"
				+ ordNum + "' order by ID";
		logger.info("SQL:" + query);
		List<String> results = db.executeQuery(query, "EFT_INVOICE_JOB_ID");
		return results;
	}

	/**
	 * This method get the invoice number for the orders which are deal with the
	 * same EFT daily job and EFT invoicing processing Note: This method is just
	 * suitable for the orders that just generate one invoice id after the daily
	 * and invoice processing
	 * 
	 * @param schema
	 * @param ordNums
	 * @return invoice number for orders with ordNums
	 */
	public String getInvoiceNumByOrderNums(String schema, String... ordNums) {
		db.resetSchema(schema);
		String query = "select distinct eft_invoice_id from f_deft where ord_num =";
		String orders = "";
		for (int i = 0; i < ordNums.length; i++) {
			orders += ordNums[i];
			query += "'" + ordNums[i] + "'";
			if (i != ordNums.length - 1) {
				query += " or ord_num=";
				orders += ",";
			}
		}
		// query += ";";
		String log = "Get Invoice Number info by order numbers: " + orders;
		logger.info(log);
		List<String> results = db.executeQuery(query, "eft_invoice_id");
		if (results.isEmpty()) {
			throw new ErrorOnDataException(
					"Failed to get invoice id using sql \"" + query + "\"");
		} else if (results.size() != 1) {
			throw new ErrorOnDataException(
					"More than one invoice was found for the ordNums: "
							+ orders);
		}
		return results.get(0);
	}

	/**
	 * Get Voucher record of EFT job from DB table
	 * 
	 * @param schema
	 * @param voucher
	 *            id
	 * @return record list
	 * 
	 */
	public List<HashMap<String, String>> getInvoiceVoucherRecordFromDB(
			String schema, String voucherID, String invoiceId) {
		db.resetSchema(schema);

		logger.debug("Get Voucher Records of Invoice from DB table F_DEFT/D_STORE/D_LOC/F_ACCT/D_REF_CB_DICTIONARY.");

		String query = "select F_DEFT.id as daily_rec_id, "
				+ "F_DEFT.deft_job_id as daily_job_id, "
				+ "F_DEFT.va_alloc_id as allocation_id, "
				+ "F_DEFT.voucher_id, "
				+ "F_DEFT.rev_loc_id, "
				+ "D_LOC.name as revenue_location, "
				+ "F_ACCT.dscr as account_desc, "
				+ "F_ACCT.CD as account_code, "
				+ "F_DEFT.tran_id, "
				+ "CB_1.CB_NAME as tran_name, "
				+ "F_DEFT.loc_id as tran_loc_id, "
				+ "D_STORE.name as tran_loc_name, "
				+ "CB_2.CB_NAME as daily_eft_type, "
				+ "F_DEFT.JRNL_DATE as posted_date, "
				+ "F_DEFT.EFT_REMITTANCE_ID as remittance_number, "
				+ "F_DEFT.AMOUNT "
				+ "FROM F_DEFT "
				+ "LEFT OUTER JOIN D_STORE  ON F_DEFT.STORE_ID=D_STORE.ID "
				+ "LEFT OUTER JOIN D_LOC  ON F_DEFT.REV_LOC_ID=D_LOC.ID "
				+ "LEFT OUTER JOIN F_ACCT  ON F_DEFT.ACCNT_ID=F_ACCT.ID "
				+ "left outer join D_REF_CB_DICTIONARY  CB_1 on F_DEFT.tran_type_id = CB_1.CB_ID  and CB_1.CB_BASE_CLASS='com.reserveamerica.common.data.reference.configurable.TransactionType' "
				+ "left outer join D_REF_CB_DICTIONARY  CB_2 on F_DEFT.DEFT_TYPE_ID = CB_2.CB_ID and CB_2.CB_BASE_CLASS='com.reserveamerica.system.finance.eft.configurable.DailyEFTType' "
				+ "WHERE F_DEFT.VOUCHER_ID ="
				+ voucherID
				+ "  "
				+ "AND F_DEFT.EFT_INVOICE_ID ="
				+ invoiceId
				+ "  "
				+ "AND F_DEFT.VA_ALLOC_ID   >0  "
				+ "AND (F_DEFT.PMT_ALLOC_ID IS NULL OR F_DEFT.PMT_ALLOC_ID    =0) "
				+ "and F_DEFT.amount is not null order by daily_rec_id";

		logger.info("Query: " + query);
		return db.executeQuery(query);

	}

	/**
	 * Get Internal GiftCard records of EFT job from DB table
	 * 
	 * @param schema
	 * @param GiftCard
	 *            product name
	 * @return record list
	 * 
	 */
	public List<HashMap<String, String>> getInvoiceGCRecordFromDB(
			String schema, String giftCardPrdName, String invoiceId) {
		db.resetSchema(schema);

		logger.debug("Get top 10 GiftCard Records of invoice from DB table F_DEFT/D_STORE/D_LOC/F_ACCT/D_REF_CB_DICTIONARY/P_PRD.");

		String query = "select * from "
				+ "(SELECT F_DEFT.id as daily_rec_id,  "
				+ "F_DEFT.deft_job_id as daily_job_id,  "
				+ "F_DEFT.GC_ALLOC_ID as allocation_id,  "
				+ // for gift card
				"O_ORDER.ORD_NUM,  "
				+ // for gift card
				"F_DEFT.rev_loc_id,  "
				+ "LOC_1.name  as revenue_location,  "
				+ "F_ACCT.CD   as account_code,  "
				+ "F_ACCT.dscr as account_desc,  "
				+ "F_DEFT.tran_id,  "
				+ "CB_1.CB_NAME  as tran_name,  "
				+ "F_DEFT.loc_id as tran_loc_id,  "
				+ "LOC_2.NAME  as tran_loc_name,  "
				+ "CB_2.CB_NAME  as daily_eft_type,  "
				+ "F_DEFT.JRNL_DATE as posted_date,  "
				+
				// "F_DEFT.EFT_INVOICE_ID,  " +
				"F_DEFT.EFT_REMITTANCE_ID as remittance_number,"
				+ "F_DEFT.AMOUNT "
				+ "FROM F_DEFT "
				+ "LEFT OUTER JOIN D_STORE ON F_DEFT.STORE_ID=D_STORE.ID "
				+ "LEFT OUTER JOIN D_LOC LOC_1 ON F_DEFT.REV_LOC_ID=LOC_1.ID "
				+ "LEFT OUTER JOIN D_LOC LOC_2 ON F_DEFT.LOC_ID=LOC_2.ID "
				+ "LEFT OUTER JOIN F_ACCT ON F_DEFT.ACCNT_ID=F_ACCT.ID "
				+ "LEFT OUTER JOIN D_REF_CB_DICTIONARY CB_1 ON F_DEFT.tran_type_id = CB_1.CB_ID AND CB_1.CB_BASE_CLASS ='com.reserveamerica.common.data.reference.configurable.TransactionType' "
				+ "LEFT OUTER JOIN D_REF_CB_DICTIONARY CB_2 ON F_DEFT.DEFT_TYPE_ID = CB_2.CB_ID AND CB_2.CB_BASE_CLASS    ='com.reserveamerica.system.finance.eft.configurable.DailyEFTType' "
				+ "LEFT OUTER JOIN P_PRD  ON F_DEFT.PRD_ID=P_PRD.PRD_ID AND P_PRD.PRD_NAME='"
				+ giftCardPrdName
				+ "' "
				+ "LEFT OUTER JOIN O_ORDER ON F_DEFT.GC_ORDER_ID=O_ORDER.ID "
				+ "WHERE  F_DEFT.GC_ALLOC_ID  >0 AND (F_DEFT.PMT_ALLOC_ID IS NULL OR F_DEFT.PMT_ALLOC_ID  =0) "
				+ "AND F_DEFT.amount is not null "
				+ "AND F_DEFT.EFT_INVOICE_ID =" + invoiceId + "  "
				+ "ORDER BY F_DEFT.ID) " + "where rownum<=10 ";

		logger.info("Query: " + query);
		return db.executeQuery(query);

	}

	/**
	 * Check if customer pass type exist.
	 * 
	 * @param schema
	 * @param passType
	 * @return
	 * @author Lesley Wang
	 * @date Sep 5, 2012
	 */
	public boolean isCustomerPassTypeExist(String schema, String passType) {
		db.resetSchema(schema);
		String query = "select * from D_REF_CUST_PASS_TYPE " + "where name='"
				+ passType + "'";
		logger.info("Execute query: " + query);
		List<String> results = db.executeQuery(query, "ID");
		if (results.size() < 1) {
			logger.info("The customer pass type doesn't exist.");
			return false;
		} else {
			logger.info("The customer pass type has existed.");
			return true;
		}
	}

	/**
	 * Insert a customer pass type
	 * 
	 * @param schema
	 * @param passType
	 * @author Lesley Wang
	 * @date Sep 5, 2012
	 */
	public void insertCustomerPassType(String schema, String passType) {
		db.resetSchema(schema);
		String query = "insert into d_ref_cust_pass_type (id, cd, name, dscr, active_ind, duration_days, proof_req_type_id) "
				+ "values (2, 'Customer Type', '"
				+ passType
				+ "', '"
				+ passType + "', 1, 0, 0)";
		logger.info("Excute query: " + query);
		int result = db.executeUpdate(query);
		if (result < 1) {
			throw new ErrorOnDataException(
					"No new customer pass type is inserted!");
		}
		logger.info("A new customer pass type has been inserted.");
	}

	/**
	 * Check if display category exist.
	 * 
	 * @param schema
	 * @param typeID
	 * @param productClass
	 * @return
	 * @author Lesley Wang
	 * @date Sep 5, 2012
	 */
	public boolean isDisplayCategoryExist(String schema, String typeID,
			String productClass) {
		db.resetSchema(schema);
		String query = "select * from P_DISPLAY_CATEGORY " + "where type_id="
				+ typeID + " and description='" + productClass + "'";
		logger.info("Execute query: " + query);
		List<String> results = db.executeQuery(query, "ID");
		if (results.size() < 1) {
			logger.info("The display category doesn't exist.");
			return false;
		} else {
			logger.info("The display category has existed.");
			return true;
		}
	}

	/** Check if product class exist */
	public boolean isProductClassExist(String schema, String productClass) {
		return this.isDisplayCategoryExist(schema, PRODUCT_CLASS_TYPE_ID,
				productClass);
	}

	/** Check if product sub class exist */
	public boolean isProductSubClassExist(String schema, String productSubClass) {
		return this.isDisplayCategoryExist(schema, PRODUCT_SUB_CLASS_TYPE_ID,
				productSubClass);
	}

	/**
	 * Insert a display category
	 * 
	 * @param schema
	 * @param typeID
	 * @param productClass
	 * @author Lesley Wang
	 * @date Sep 5, 2012
	 */
	public void insertDisplayCategory(String schema, String typeID,
			String productClass) {
		db.resetSchema(schema);
		String query = "insert into P_DISPLAY_CATEGORY( id, type_id, description ) "
				+ "values ( get_sequence( 'P_DISPLAY_CATEGORY' ), "
				+ typeID
				+ ", '" + productClass + "')";
		logger.info("Execute sql: " + query);
		int result = db.executeUpdate(query);
		if (result < 1) {
			throw new ErrorOnDataException(
					"No new display category is inserted!");
		}
		logger.info("A new display category has been inserted.");
	}

	/** Insert a product class */
	public void insertProductClass(String schema, String productClass) {
		this.insertDisplayCategory(schema, PRODUCT_CLASS_TYPE_ID, productClass);
	}

	/** Insert a product sub class */
	public void insertProductSubClass(String schema, String subClass) {
		this.insertDisplayCategory(schema, PRODUCT_SUB_CLASS_TYPE_ID, subClass);
	}

	/**
	 * 
	 * @param schema
	 * @param giftCardPrdName
	 * @param invoiceId
	 * @return
	 */
	public List<HashMap<String, String>> getMatchedTransactionOfReturnJob(
			String schema) {
		db.resetSchema(schema);

		logger.debug("Get return_job_id, transaction_id and return_file_name of matched transaction of return job from DB table"
				+ "F_EFT_RETURN_TRANSACTION and F_EFT_RETURN_JOB.");

		String query = "select F_EFT_RETURN_TRANSACTION.id as transaction_id,"
				+ "F_EFT_RETURN_JOB.id  as return_job_id,"
				+ "F_EFT_RETURN_TRANSACTION.matching_eft_transm_id as transmission_id,"
				+ "F_EFT_RETURN_TRANSACTION.company_entry_desc as description,"
				+ "F_EFT_RETURN_JOB.file_name as return_file_name "
				+ "from F_EFT_RETURN_TRANSACTION "
				+ "left outer join F_EFT_RETURN_JOB "
				+ "on F_EFT_RETURN_TRANSACTION.eft_return_job_id = F_EFT_RETURN_JOB.id "
				+ "where F_EFT_RETURN_TRANSACTION.matching_eft_transm_id is not null order by F_EFT_RETURN_JOB.id desc";

		logger.info("Query: " + query);
		return db.executeQuery(query);
	}

	public EFTReturnTransactionInfo getTransInfoAccordingtoDB(
			String transactionId, String schema) {
		EFTReturnTransactionInfo transactionInfo = new EFTReturnTransactionInfo();
		db.resetSchema(schema);
		logger.info("Get EFT return transaction info from database.");
		String sql = "select * from F_EFT_RETURN_TRANSACTION where id="
				+ transactionId;
		logger.info("Query:" + sql);
		List<HashMap<String, String>> result = db.executeQuery(sql);
		transactionInfo.setReferenceNum(result.get(0).get("REFERENCE_NUM"));
		transactionInfo.setImmediateDestination(result.get(0).get(
				"IMMEDIATE_DEST"));
		transactionInfo.setCompanyIdentification(result.get(0).get(
				"COMPANY_IDENT"));
		transactionInfo.setStandardEntryClassCode(result.get(0).get(
				"STANDARD_ENTRY_CLASS_CD"));
		transactionInfo.setCompanyEntryDescription(result.get(0).get(
				"COMPANY_ENTRY_DESC"));
		transactionInfo.setEffectiveEntryDate(result.get(0).get(
				"EFFECTIVE_ENTRY_DATE"));
		transactionInfo.setOriginationDFIIdentification(result.get(0).get(
				"ORIG_DFI_IDENT"));
		transactionInfo.setRDFITransitRoutingOrABANum(result.get(0).get(
				"TRANSIT_ROUTING_NUMBER"));
		transactionInfo.setCheckDigit(result.get(0).get(
				"TRANSIT_ROUTING_CHCK_DIGIT"));
		transactionInfo.setOriginalEntryTranceNum(result.get(0).get(
				"ORIG_ENTRY_TRACE_NUMBER"));
		transactionInfo.setTransactionCode(result.get(0).get("TRANS_CODE"));
		transactionInfo.setTransitRoutingNum(result.get(0).get(
				"ORIGINAL_RECEIVING_DEFI_IDENT"));
		transactionInfo.setAccountNum(result.get(0).get("DFI_ACCOUNT_NUMBER"));
		transactionInfo.setAmount("$" + result.get(0).get("AMOUNT"));
		transactionInfo.setReturnReasonCode(result.get(0).get(
				"RETURN_REASON_CODE"));
		transactionInfo.setIndividualId(result.get(0).get(
				"INDIVIDUAL_IDENT_NUMBER"));
		transactionInfo.setIndividualName(result.get(0).get("INDIVIDUAL_NAME"));
		transactionInfo.setAddendaTranceNum(result.get(0).get(
				"ADDENDA_TRACE_NUMBER"));
		transactionInfo
				.setAddendaInformation(result.get(0).get("ADDENDA_INFO"));
		transactionInfo.setReturnId(transactionId);
		transactionInfo.setReturnJobId(result.get(0).get("EFT_RETURN_JOB_ID"));
		return transactionInfo;
	}

	/**
	 * Get detail information of a return job
	 * 
	 * @param schema
	 * @param returnJobId
	 * @return
	 */
	public List<HashMap<String, String>> getReturnJobInfo(String schema,
			String returnJobId) {

		db.resetSchema(schema);

		logger.debug("Get return_job_id, transaction_id and return_file_name of matched transaction of return job from DB table"
				+ "F_EFT_RETURN_TRANSACTION and F_EFT_RETURN_JOB.");

		String query = "select F_EFT_RETURN_JOB.id,"
				+ "F_EFT_RETURN_JOB.status,"
				+ "F_EFT_RETURN_JOB.matching_status,"
				+ "F_EFT_RETURN_JOB.file_name,"
				+ "F_EFT_RETURN_JOB.num_ret_trans,"
				+ "F_EFT_RETURN_JOB.num_corrections,"
				+ "F_EFT_RETURN_JOB.start_date,"
				+ "F_EFT_RETURN_JOB.end_date,"
				+ "d_loc.name as run_location,"
				+ "x_user.LAST_NAME||','||x_user.FIRST_NAME as run_user "
				+ "from  F_EFT_RETURN_JOB "
				+ "left outer join x_user on x_user.id = F_EFT_RETURN_JOB.user_id "
				+ "left outer join d_loc on d_loc.id = F_EFT_RETURN_JOB.exec_loc_id "
				+ "where F_EFT_RETURN_JOB.id=" + returnJobId;

		logger.info("Query: " + query);
		List<HashMap<String, String>> temp = db.executeQuery(query);
		switch (Integer.parseInt(temp.get(0).get("MATCHING_STATUS"))) {
		case 1:
			temp.get(0).put("MATCHING_STATUS", "Fully Matched");
			break;
		case 2:
			temp.get(0).put("MATCHING_STATUS", "Unmatched");
			break;
		case 3:
			temp.get(0).put("MATCHING_STATUS", "Partially Matched");
			break;
		}
		switch (Integer.parseInt(temp.get(0).get("STATUS"))) {
		case 1:
			temp.get(0).put("STATUS", "Pending");
			break;
		case 2:
			temp.get(0).put("STATUS", "Completed");
			break;
		case 3:
			temp.get(0).put("STATUS", "Failed");
			break;
		case 4:
			temp.get(0).put("STATUS", "Void");
			break;
		}
		return temp;
	}

	/**
	 * Get customer price from table P_PRD_HFPRICE by product code.
	 * 
	 * @param schema
	 * @param prdCategory
	 * @param prdCode
	 * @param purchaseType
	 *            1-Original, 2-Replacement, 3-Transfer
	 * @param isAppliedToAllState
	 *            0-applied to specified state, 1-applied to all state
	 * @return
	 */
	public String getHFPrdCustPrice(String schema, String prdCategory,
			String prdCode, String purchaseType, String isAppliedToAllState) {

		logger.info("Get customer price form table P_PRD_HFPRICE by product category:"
				+ prdCategory + " and product code:" + prdCode);
		db.resetSchema(schema);
		// String query =
		// "select cust_price from P_PRD_HFPRICE where purchase_type_id = "
		// + purchaseType
		// +
		// " and active_ind = 1 and prd_id = (select prd_id from p_prd where product_cat_id ='"
		// + prdCategory
		// + "' and prd_cd = '"
		// + prdCode
		// + "' ) and applicable_to_all_state = "
		// + isAppliedToAllState;
		// Lesley[20131022]: purchase_type_id = null in cust_price after Oct
		// 2013, so change the sql.
		String query = "select price.cust_price from P_PRD_HFPRICE price, P_FEE_SCHD fee where price.active_ind = 1"
				+ " and price.prd_id = (select prd_id from p_prd where product_cat_id ='"
				+ prdCategory
				+ "' and prd_cd = '"
				+ prdCode
				+ "' ) and price.applicable_to_all_state = "
				+ isAppliedToAllState
				+ " and price.trans_fee_schd_id=fee.id and fee.purchase_type="
				+ purchaseType;
		logger.info("SQL: " + query);
		List<String> resultList = db.executeQuery(query, "cust_price");
		if (resultList.size() < 1) {
			throw new ErrorOnDataException(
					"There isn't any customer price for product code "
							+ prdCode);
		}
		String price = resultList.get(0);
		logger.info("Price is " + price);
		return price;
	}

	public String getPriCustPrice(String schema, String privilegeCode,
			String purchaseType, String isAppliedToAllState) {
		return getHFPrdCustPrice(schema, PRDCAT_PRIVILEGE, privilegeCode,
				purchaseType, isAppliedToAllState);
	}

	public String getVehCustPrice(String schema, String vehicleCode,
			String purchaseType, String isAppliedToAllState) {
		return getHFPrdCustPrice(schema, PRDCAT_VEHICLERTI, vehicleCode,
				purchaseType, isAppliedToAllState);
	}

	public String getCusumableCustPrice(String schema, String prdCode) {

		logger.info("Get customer price form table P_PRD_HFPRICE by consumable code:"
				+ prdCode);
		db.resetSchema(schema);
		String query = "select cust_price from P_PRD_HFPRICE where  active_ind = 1 and prd_id = (select prd_id from p_prd where product_cat_id=4 and prd_cd = '"
				+ prdCode + "')";
		logger.info("SQL: " + query);
		List<String> resultList = db.executeQuery(query, "cust_price");
		if (resultList.size() < 1) {
			throw new ErrorOnDataException(
					"There isn't any customer price for consumable " + prdCode);
		}
		String price = resultList.get(0);
		logger.info("Price is " + price);
		return price;

	}

	/**
	 * Get customer price from table P_PRD_HFPRICE by privilege code and state
	 * id.
	 * 
	 * @param schema
	 * @param privilegeCode
	 * @param purchaseType
	 *            1-Original, 2-Replacement, 3-Transfer
	 * @param isAppliedToAllState
	 *            0-applied to specified state, 1-applied to all state
	 * @param stateID
	 *            state id of the specified state(in table: D_REF_STATE_PROVNC)
	 * @return
	 */
	public String getPriCustPrice(String schema, String privilegeCode,
			String purchaseType, String isAppliedToAllState, String stateID) {

		logger.info("Get customer price form table P_PRD_HFPRICE by privilege code:"
				+ privilegeCode);
		db.resetSchema(schema);
		String query = "select cust_price from P_PRD_HFPRICE "
				+ "INNER JOIN P_PRD_HFPRICE_STATE_PROVNC "
				+ "ON P_PRD_HFPRICE_STATE_PROVNC.PRD_PRICE_ID = P_PRD_HFPRICE.ID "
				+ "and P_PRD_HFPRICE_STATE_PROVNC.state_id="
				+ stateID
				+ " where active_ind = 1 and prd_id = (select prd_id from p_prd where prd_cd = '"
				+ privilegeCode + "') and applicable_to_all_state = "
				+ isAppliedToAllState;
		logger.info("SQL: " + query);
		List<String> resultList = db.executeQuery(query, "cust_price");
		if (resultList.size() < 1) {
			throw new ErrorOnDataException(
					"There isn't any customer price for privilege "
							+ privilegeCode);
		}
		String price = resultList.get(0);
		logger.info("Price is " + price);
		return price;
	}

	public String getPriVendorFee(String schema, String privilegeCode,
			String purchaseType, String isAppliedToAllState) {
		return getHFPrdVendorFee(schema, PRDCAT_PRIVILEGE, privilegeCode,
				purchaseType, isAppliedToAllState);
	}

	public String getVehVendorFee(String schema, String vehicleCode,
			String purchaseType, String isAppliedToAllState) {
		return getHFPrdVendorFee(schema, PRDCAT_VEHICLERTI, vehicleCode,
				purchaseType, isAppliedToAllState);
	}

	/**
	 * Get vendor fee of privilege from table P_OTHER_FEE_SCHD purchase type:
	 * 1-Original 2-Replacement 3-Transfer 4-Renewal 5-Corrected
	 * 
	 * isAppliedToAllState: 1- applied to all state
	 */
	public String getHFPrdVendorFee(String schema, String prdCategory,
			String privilegeCode, String purchaseType,
			String isAppliedToAllState) {

		logger.info("Get vendor fee of privilege form table P_OTHER_FEE_SCHD by privilege code:"
				+ privilegeCode);
		db.resetSchema(schema);

		// get vendor fee schedule
		String query = "select vendor_fee_schd_id from P_PRD_HFPRICE where purchase_type_id = "
				+ purchaseType
				+ " and active_ind = 1 and prd_id = (select prd_id from p_prd where product_cat_id = '"
				+ prdCategory
				+ "' and prd_cd = '"
				+ privilegeCode
				+ "' and applicable_to_all_state = "
				+ isAppliedToAllState
				+ ")";
		logger.info("Execute SQL:" + query);
		List<String> resultList = db.executeQuery(query, "vendor_fee_schd_id");
		if (resultList.size() < 1) {
			throw new ErrorOnDataException(
					"There isn't any vendor fee for privilege " + privilegeCode);
		}
		String scheduleID = resultList.get(0);

		// get vendor fee
		query = "select fee_amt from P_OTHER_FEE_SCHD where fee_schd_id = '"
				+ scheduleID + "'";
		logger.info("Execute SQL:" + query);
		resultList = db.executeQuery(query, "fee_amt");
		if (resultList.size() < 1) {
			throw new ErrorOnDataException(
					"There isn't any vendor fee for privilege " + privilegeCode);
		}
		String vendorFee = resultList.get(0);
		logger.info("Vendor fee is " + vendorFee);
		return vendorFee;
	}

	/**
	 * Update PrivilegeReplacementTransactionCoverage value
	 * 
	 * @param schema
	 * @param expectValue
	 * @return
	 */
	public void updatePriReplacementTransactionCoverage(String schema,
			String expectValue) {
		db.resetSchema(schema);
		logger.info("Update the value of 'PrivilegeReplacementTransactionCoverage'.");
		String query = "update x_prop set value = " + expectValue
				+ " where name like 'PrivilegeReplacementTransactionCoverage'";
		int cnt = db.executeUpdate(query);
		if (cnt < 1) {
			query = "insert into x_prop values((select max(id) from x_prop)+1, 'PrivilegeReplacementTransactionCoverage', 'Contract', 'Number', expectValue)";
		}
	}

	public boolean tryToSignOut() {
		return tryToSignOut(null, false);
	}

	public boolean tryToSignOut(boolean finSessionMayExists) {
		return tryToSignOut(null, finSessionMayExists);
	}

	/**
	 * This method is used for session cleanup. This is the best effort method,
	 * as signout may not be successful in some situations
	 */
	public boolean tryToSignOut(DialogPage dialogToDismiss,
			boolean finSessionMayExists) {
		GeneralSignOutPage signoutPg = GeneralSignOutPage.getInstance();
		logger.info("Trying to sign out from current session");
		return signoutPg.tryToSignOut(dialogToDismiss, finSessionMayExists);
	}

	public int getEFTScheduleTransmissionOffsetDays(String schema,
			String frequency) {
		logger.info("Get EFT schedule(Frequency=" + frequency
				+ ") Transmission Day Offset.");
		Map<String, Integer> frequencies = new HashMap<String, Integer>();
		frequencies.put("Daily", 1);
		frequencies.put("Weekly", 2);
		frequencies.put("Monthly", 3);
		frequencies.put("Quarterly", 4);
		frequencies.put("Annually", 5);
		db.resetSchema(schema);

		String sql = "select TRANS_DAY_OFFSET from F_EFT_SCHEDULE where EFT_FREQUENCY="
				+ frequencies.get(frequency);
		List<String> results = db.executeQuery(sql, "TRANS_DAY_OFFSET");
		if (results.size() < 1) {
			throw new ErrorOnDataException(
					"Can't find eft schedule record identified by frequency - "
							+ frequency);
		}

		int offset = Integer.parseInt(results.get(0));
		logger.info("Get EFT Schedule(Frequency=" + frequency
				+ ") Transmission Day Offeset is: " + offset);
		return offset;
	}

	/**
	 * Get customer number by customer id
	 * 
	 * @param custId
	 * @param schema
	 * @return
	 */
	public String getCustomerNumByCustId(String custId, String schema) {
		String custNum = "";
		String sql = "select cust_number from C_CUST_HFPROFILE where cust_id ='"
				+ custId + "'";
		db.resetSchema(schema);
		List<String> ids = db.executeQuery(sql, "CUST_NUMBER");
		if (ids.size() > 0) {
			custNum = db.executeQuery(sql, "CUST_NUMBER").get(0).trim();
		}
		logger.info("Find customer number is: " + custNum);
		return custNum;
	}

	public String getCustomerNumByEmail(String email, String schema) {
		String sql = "select cust_number from C_CUST_HFPROFILE where cust_id in (select cust_id from c_cust_phone where typ='EMAIL' and val='"
				+ email + "') order by cust_number desc";
		db.resetSchema(schema);
		String custNum = db.executeQuery(sql, "CUST_NUMBER").get(0).trim();
		logger.info("Find customer number is: " + custNum);
		return custNum;
	}

	/**
	 * Get hunt id if a hunt with the huntCode exist
	 * 
	 * @param huntCode
	 * @param schema
	 * @return
	 */
	public String getHuntIdByHuntCode(String huntCode, String description,
			String schema) {
		String huntId = "";
		String sql = "select id from P_HUNT where code='"
				+ huntCode
				+ "'"
				+ (StringUtil.notEmpty(description) ? " and description='"
						+ description + "'" : "");
		db.resetSchema(schema);
		List<String> ids = db.executeQuery(sql, "ID");
		if (ids.size() > 0) {
			huntId = db.executeQuery(sql, "ID").get(0).trim();
		}
		logger.info("Find hunt with code:" + huntCode + ", its id is:" + huntId);
		return huntId;
	}

	public String getHuntIdByHuntCode(String huntCode, String schema) {
		return getHuntIdByHuntCode(huntCode, StringUtil.EMPTY, schema);
	}

	/**
	 * This method delete the hunt with huntCode in db
	 * 
	 * @param huntCode
	 * @param schema
	 */
	public void clearHunt(String huntCode, String schema) {
		String sql = "delete from P_HUNT where code = '" + huntCode + "'";
		db.resetSchema(schema);
		int num = db.executeUpdate(sql);
		logger.info("Delete " + num + " hunt record from db which code is "
				+ huntCode);
	}

	/**
	 * query hunt info status by hunt code from DB
	 * 
	 * @param huntCode
	 * @param schema
	 * @return
	 */
	public String getHuntInfoStatusByHuntCodeFromDB(String huntCode,
			String schema) {
		String status = "";
		db.resetSchema(schema);

		String sql = "select status_id from P_HUNT where code = '" + huntCode
				+ "'";
		List<String> results = db.executeQuery(sql, "status_id");
		if (results.size() > 0) {
			status = results.get(0);
			if (status.equals("1")) {
				status = ACTIVE_STATUS;
			} else if (status.equals("2")) {
				status = INACTIVE_STATUS;
			} else {
				throw new ErrorOnDataException(
						"The status is not expected, status = " + status);
			}
		}

		logger.info("The hunt info status is " + status + ", hunt code = "
				+ huntCode);
		return status;
	}

	/**
	 * Delete hunt permit info by hunt permit id from db
	 * 
	 * @param huntPermitID
	 * @param schema
	 */
	public void deleteHuntPermitInfoByIDFromDB(String huntPermitID,
			String schema) {
		db.resetSchema(schema);
		String sql = "delete from P_HUNT_PERMIT where id = '" + huntPermitID
				+ "'";
		int num = db.executeUpdate(sql);
		logger.info("Delete " + num
				+ " hunt permit record from db which hunt permit id is "
				+ huntPermitID);
	}

	/**
	 * Delete hunt permit info by hunt code from db
	 * 
	 * @param huntCode
	 * @param schema
	 */
	public void deleteHuntPermitInfosByHuntCodeFromDB(String huntCode,
			String schema) {
		db.resetSchema(schema);
		String sql = "delete from P_HUNT_PERMIT where hunt_id = (select id from P_HUNT where code = '"
				+ huntCode + "')";
		int num = db.executeUpdate(sql);
		logger.info("Delete " + num
				+ " hunt permit record from db which hunt permit code is "
				+ huntCode);
	}

	/**
	 * This method delete quota and the related information in db
	 * 
	 * @param desc
	 * @param schema
	 */
	public void clearQuota(String desc, String schema) {
		String delete_inv_sql = "delete from I_HUNT_INV where HUNT_LICENSE_YEAR_QUOTA_ID in "
				+ "(select id from D_HUNT_LICENSE_YEAR_QUOTA where HUNT_QUOTA_ID="
				+ "(select id from D_HUNT_QUOTA where description='"
				+ desc
				+ "'))  "
				+ "and QUOTA_TYPE_ID in (select id from D_HUNT_QUOTA_TYPE where HUNT_QUOTA_ID="
				+ "(select id from D_HUNT_QUOTA where description='"
				+ desc
				+ "') )";
		String delete_quota_type_sql = "delete from D_HUNT_QUOTA_TYPE where  HUNT_QUOTA_ID= "
				+ "(select id from D_HUNT_QUOTA where description='"
				+ desc
				+ "') ";
		String delete_license_year_sql = "delete from D_HUNT_LICENSE_YEAR_QUOTA where  HUNT_QUOTA_ID="
				+ "(select id from D_HUNT_QUOTA where description='"
				+ desc
				+ "') ";
		String delete_quota_sql = "delete from D_HUNT_QUOTA where description='"
				+ desc + "'";
		db.resetSchema(schema);
		int num = db.executeUpdate(delete_inv_sql);
		logger.info("Delete " + num + " record from table I_HUNT_INV");
		num = db.executeUpdate(delete_quota_type_sql);
		logger.info("Delete " + num + " record from table D_HUNT_QUOTA_TYPE");
		num = db.executeUpdate(delete_license_year_sql);
		logger.info("Delete " + num
				+ " record from table D_HUNT_LICENSE_YEAR_QUOTA");
		num = db.executeUpdate(delete_quota_sql);
		logger.info("Delete " + num + " record from table D_HUNT_QUOTA");
	}

	public String getQuotaID(String desc, String schema) {
		db.resetSchema(schema);
		String query = "select ID from D_HUNT_QUOTA where description='" + desc
				+ "' ";
		logger.info("Search By sql:" + query);
		String id = db.executeQuery(query, "ID").get(0);
		return id;
	}

	public String getPOSFeeAmountFromDB(String posName, String schema) {
		db.resetSchema(schema);
		logger.debug("Get POS Fee Schedule amount of " + posName
				+ " from DB table .");

		String query = "SELECT OTHERFEE.FEE_AMT "
				+ "FROM P_FEE_SCHD SCHD,      "
				+ "P_FEE_COND FEECOND,      "
				+ "P_FEE FEE,      "
				+ "P_CONDITION COND,      "
				+ "P_OTHER_FEE_SCHD OTHERFEE    "
				+ "WHERE SCHD.DELETE_IND = '0'   "
				+ "AND SCHD.ACTIVE_IND = '1'   "
				+ "AND SCHD.FEE_COND_ID  = FEECOND.ID    "
				+ "AND FEECOND.FEE_ID    = FEE.FEE_ID    "
				+ "AND FEECOND.COND_ID   = COND.ID   "
				+ "AND OTHERFEE.FEE_SCHD_ID = SCHD.ID    "
				+ "AND FEE.PRD_ID       IN      (SELECT PRD_ID      FROM P_PRD      WHERE UPPER(PRD_NAME) = UPPER('"
				+ posName + "') )   " + "AND SCHD.SCHD_TYPE          <> 5    "
				+ "AND SCHD.PRD_GRP_CAT_ID NOT IN (10,11,15)    "
				+ "AND COND.SALES_CHANL_ID IN ( 2)    " + // Web
				"AND FEE.FEE_TYPE_ID IN ( 13 )    " + // POS Fee
				"ORDER BY SCHD.ID DESC NULLS LAST";
		logger.info("Query: " + query);

		return db.executeQuery(query, "FEE_AMT").get(0);

	}

	public String getPOSTransFeeFromDB(String posName, String schema) {
		db.resetSchema(schema);
		logger.debug("Get Trans Fee Amount of " + posName + " from DB table .");

		String query = "SELECT OTHERFEE.FEE_AMT "
				+ "FROM P_FEE_SCHD SCHD,      "
				+ "P_FEE_COND FEECOND,      "
				+ "P_FEE FEE,      "
				+ "P_CONDITION COND,      "
				+ "P_OTHER_FEE_SCHD OTHERFEE    "
				+ "WHERE SCHD.DELETE_IND = '0'   "
				+ "AND SCHD.ACTIVE_IND = '1'   "
				+ "AND SCHD.FEE_COND_ID  = FEECOND.ID    "
				+ "AND FEECOND.FEE_ID    = FEE.FEE_ID    "
				+ "AND FEECOND.COND_ID   = COND.ID   "
				+ "AND OTHERFEE.FEE_SCHD_ID = SCHD.ID    "
				+ "AND FEE.PRD_ID       IN      (SELECT PRD_ID      FROM P_PRD      WHERE UPPER(PRD_NAME) = UPPER('"
				+ posName + "'))    " + "AND SCHD.SCHD_TYPE          <> 5    "
				+ "AND SCHD.TRAN_TYPE_ID        =8001  "
				+ // Purchase POS
				"AND SCHD.PRD_GRP_CAT_ID NOT IN (10,11,15)    "
				+ "AND FEE.FEE_TYPE_ID IN ( 4 )    " + // Transaction Fee
				"ORDER BY SCHD.ID DESC NULLS LAST";
		logger.info("Query: " + query);

		return db.executeQuery(query, "FEE_AMT").get(0);

	}

	/**
	 * Get quota id by quota description
	 * 
	 * @param desc
	 * @param schema
	 * @return
	 */
	public String getQuotaIdByDesc(String desc, String schema) {
		String quotaId = "";
		String sql = "select id from D_HUNT_QUOTA where description='" + desc
				+ "'";
		db.resetSchema(schema);
		List<String> ids = db.executeQuery(sql, "ID");
		if (ids.size() > 0) {
			quotaId = db.executeQuery(sql, "ID").get(0).trim();
		}
		logger.info("Find quota with description:" + desc + ", its id is:"
				+ quotaId);
		return quotaId;
	}

	/**
	 * Get date period id by date period description
	 * 
	 * @param desc
	 * @param schema
	 * @return
	 */
	public String getDatePeriodIdByCode(String code, String schema) {
		String dpId = "";
		String sql = "select id from D_DATE_PERIOD where code='" + code + "'";
		db.resetSchema(schema);
		List<String> ids = db.executeQuery(sql, "ID");
		if (ids.size() > 0) {
			dpId = db.executeQuery(sql, "ID").get(0).trim();
		}
		logger.info("Find date period with code:" + code + ", its id is:"
				+ dpId);
		return dpId;
	}

	/**
	 * Get weapon id by weapon code
	 * 
	 * @param code
	 * @param schema
	 * @return
	 */
	public String getWeaponIdByCode(String code, String schema) {
		String wpId = "";
		String sql = "select id from D_WEAPON where code='" + code + "'";
		db.resetSchema(schema);
		List<String> ids = db.executeQuery(sql, "ID");
		if (ids.size() > 0) {
			wpId = db.executeQuery(sql, "ID").get(0).trim();
		}
		logger.info("Find weapon with code:" + code + ", its id is:" + wpId);
		return wpId;
	}

	/**
	 * Get specie id by description
	 * 
	 * @param description
	 * @param schema
	 * @return
	 */
	public String getSpecieIdByDesc(String description, String schema) {
		String speciesId = "";
		String sql = "select id from d_species where description='"
				+ description + "'";
		db.resetSchema(schema);
		List<String> ids = db.executeQuery(sql, "ID");
		if (ids.size() > 0) {
			speciesId = db.executeQuery(sql, "ID").get(0).trim();
		}
		logger.info("Find species with description:" + description
				+ ", its id is:" + speciesId);
		return speciesId;
	}

	/**
	 * Get specie sub type id by code
	 * 
	 * @param code
	 * @param schema
	 * @return
	 */
	public String getSpecieSubTypeIdByCode(String code, String schema) {
		String stId = "";
		String sql = "select id from D_SPECIES_SUB_TYPE where code='" + code
				+ "'";
		db.resetSchema(schema);
		List<String> ids = db.executeQuery(sql, "ID");
		if (ids.size() > 0) {
			stId = db.executeQuery(sql, "ID").get(0).trim();
		}
		logger.info("Find specie sub type with code:" + code + ", its id is:"
				+ stId);
		return stId;
	}

	/**
	 * Get location id by code and desc
	 * 
	 * @param code
	 * @param desc
	 * @param schema
	 * @return
	 */
	public String getLocationIdByCodeAndDesc(String code, String desc,
			String schema) {
		String locId = "";
		String sql = "select id from D_HUNT_LOCATION where code='" + code
				+ "' and description='" + desc + "'";
		db.resetSchema(schema);
		List<String> ids = db.executeQuery(sql, "ID");
		if (ids.size() > 0) {
			locId = db.executeQuery(sql, "ID").get(0).trim();
		}
		logger.info("Find location with code:" + code + "and desc" + desc
				+ ", its id is:" + locId);
		return locId;
	}

	/**
	 * This method delete location and the related information in db
	 * 
	 * @param desc
	 * @param schema
	 */
	public void clearLocation(String locationCode, String locationDesc,
			String schema) {
		String delete_sub_location_sql = "delete from D_HUNT_SUB_LOCATION where HUNT_LOCATION_ID="
				+ "(select id from D_HUNT_LOCATION where code='"
				+ locationCode
				+ "' and description='" + locationDesc + "') ";
		String delete_location_sql = "delete from D_HUNT_LOCATION where code='"
				+ locationCode + "' and description='" + locationDesc + "'";
		db.resetSchema(schema);
		int num = db.executeUpdate(delete_sub_location_sql);
		logger.info("Delete " + num + " record from table D_HUNT_SUB_LOCATION");
		num = db.executeUpdate(delete_location_sql);
		logger.info("Delete " + num + " record from table D_HUNT_LOCATION");
	}

	/**
	 * This method delete date period and the related information in db
	 * 
	 * @param desc
	 * @param schema
	 */
	public void clearDatePeriod(String dpCode, String dpDesc, String schema) {
		String delete_dp_ly_range_sql = "delete from D_DP_LY_DATE_RANGE where DP_LY_ID="
				+ "(select id from D_DATE_PERIOD_LICENSE_YEAR where DATE_PERIOD_ID="
				+ "(select id from D_DATE_PERIOD where code='"
				+ dpCode
				+ "' and description='" + dpDesc + "'))  ";
		String delete_dp_ly_sql = "delete from D_DATE_PERIOD_LICENSE_YEAR where  DATE_PERIOD_ID="
				+ "(select id from D_DATE_PERIOD where code='"
				+ dpCode
				+ "' and description='" + dpDesc + "') ";
		String delete_date_period_sql = "delete from D_DATE_PERIOD  where code='"
				+ dpCode + "' and description='" + dpDesc + "'";
		db.resetSchema(schema);
		int num = db.executeUpdate(delete_dp_ly_range_sql);
		logger.info("Delete " + num + " record from table D_DP_LY_DATE_RANGE");
		num = db.executeUpdate(delete_dp_ly_sql);
		logger.info("Delete " + num + " record from table delete_dp_ly_sql");
		num = db.executeUpdate(delete_date_period_sql);
		logger.info("Delete " + num + " record from table D_DATE_PERIOD");
	}

	/**
	 * This method is used to get
	 * createUser/createDate/createLocation/accountResident information which
	 * are not shown on the page from db for a hunt
	 * 
	 * @param schema
	 * @param huntCode
	 * @return
	 */
	public HuntInfo getHuntInfoFromDB(String huntCode, String schema) {
		HuntInfo hunt = new HuntInfo();
		db.resetSchema(schema);
		logger.debug("Get createUser/createDate/createLocation/accountResident information from db.");
		String query = "select user_1.name as createUser, "
				+ "user_2.name as updateUser,  "
				+ "P_HUNT.create_dateTime as createDate,  "
				+ "P_HUNT.update_dateTime as updateDate,  "
				+ "P_HUNT.quota_id as quotaId,  "
				+ "P_HUNT.date_period_id as datePeriodId,  "
				+ "P_HUNT.hunt_loc_id as huntLocId,  "
				+ "P_HUNT.species_id as speciesId,  "
				+ "LOC_1.name as createLocation,  "
				+ "LOC_2.name as accountResident,  "
				+ "LOC_3.name as updateLocation from P_HUNT "
				+ "left join X_USER user_1 on user_1.id=P_HUNT.create_user_id "
				+ "left join X_USER user_2 on user_2.id=P_HUNT.update_user_id "
				+ "left join D_LOC LOC_1 on LOC_1.id = P_HUNT.create_loc_id "
				+ "left join D_LOC LOC_2 on LOC_2.id = P_HUNT.ch_acct_loc_id "
				+ "left join D_LOC LOC_3 on LOC_3.id = P_HUNT.update_loc_id "
				+ "where P_HUNT.code='" + huntCode + "'";
		logger.info("Query: " + query);
		List<HashMap<String, String>> tmp = db.executeQuery(query);
		if (tmp.size() < 1) {
			throw new ErrorOnDataException(
					"Can't find hunt info in db by huntCode - " + huntCode);
		}
		hunt.setCreateDate(tmp.get(0).get("CREATEDATE").split(" ")[0]);
		hunt.setCreateUser(tmp.get(0).get("CREATEUSER"));
		hunt.setCreateLocation(tmp.get(0).get("CREATELOCATION"));
		hunt.setResidentAccount(tmp.get(0).get("ACCOUNTRESIDENT"));
		hunt.setUpdateDate(tmp.get(0).get("UPDATEDATE").split(" ")[0]);
		hunt.setUpdateUser(tmp.get(0).get("UPDATEUSER"));
		hunt.setUpdateLocation(tmp.get(0).get("UPDATELOCATION"));
		hunt.setQuotaId(tmp.get(0).get("QUOTAID"));
		hunt.setDatePeriodId(tmp.get(0).get("DATEPERIODID"));
		hunt.setLocationId(tmp.get(0).get("HUNTLOCID"));
		hunt.setSpeciesId(tmp.get(0).get("SPECIESID"));
		return hunt;
	}

	/**
	 * @param schema
	 * @param stateCode
	 * @param orgid
	 * @param recareaidExcepted:Sucg as San Juan Wilderness (12830), which has ORGIN=145,127, only 145 displays
	 * @return
	 */
		public List<String> queryStateRIDBAreaNames(String schema,
				String stateCode, String orgid, String recareaidExcepted) {
			db.resetSchema(schema);
			logger.info("Query " + stateCode + " stateCode and " + orgid
					+ " orgid RIDB facilities names form DB.");

			String sql = "select r.recareaname from recarea r, recareaaddress a where "+(StringUtil.notEmpty(recareaidExcepted)?"r.recareaid not in ("+recareaidExcepted+") and ":"")+"r.recareaid in (select recareaid from orgrecarea where orgid=" 
					+ orgid
					+ ") and r.recareaid=a.recareaid and a.addressstatecode='"
					+ stateCode + "' and r.enabled=1 order by r.recareaname asc";

			logger.debug("Execute query: " + sql);
			List<String> values = db.executeQuery(sql, "RECAREANAME");

			db.disconnect();
			return values;
		}

		public List<String> queryStateRIDBFacilityNames(String schema,
				String stateCode, String orgid) {
			db.resetSchema(schema);
			logger.info("Query " + stateCode + " stateCode and " + orgid
					+ " orgid RIDB facilities names form DB.");

			String sql = "select f.facilityname from facility f, facilityaddress a "
					+ "where f.facilityid in (select facilityid from orgfacility where orgid="
					+ orgid
					+ ") and f.facilityid=a.facilityid and a.addressstatecode='"
					+ stateCode + "' and f.enabled=1 order by f.facilityname asc";

			logger.debug("Execute query: " + sql);
			List<String> values = db.executeQuery(sql, "FACILITYNAME");

			db.disconnect();
			return values;
		}

		public List<String> queryStateRIDBAreaNames(String schema,
				String stateCode, String orgid) {
			return queryStateRIDBAreaNames(schema, stateCode, orgid, StringUtil.EMPTY);
		}

	/**
	 * 
	 * @param schema
	 * @param stateCode
	 * @param agencyCode
	 * @param product_cat_id
	 * @return
	 */
	public List<String> queryContractFacilityNames(String schema,
			String stateCode, String agencyCode, String product_cat_id,
			String unit_of_stay_type_id) {
		db.resetSchema(schema);
		logger.info("Query " + stateCode + " stateCode and " + agencyCode
				+ " agencyCode ORMS facilities names form DB.");

		String sql = "select distinct d.name from d_loc d, p_prd p, d_loc_prd_cat dlpc "
				+ "where d.id=dlpc.loc_id " 
				+ "and d.id not in (select dla.loc_id from d_loc_attr_value dla join d_attr da on dla.attr_id = da.attr_id where attr_dscr = 'Hidden On Web Search')"
				+ (StringUtil.notEmpty(agencyCode)?" and d.cd like '|1|" +agencyCode+ "|%'":StringUtil.EMPTY)
				+ (!StringUtil.isEmpty(stateCode) ? " and state='" + stateCode
						+ "'" : "")
				+ " and d.LEVEL_NUM=40"
				+ " and d.STATUS_ID=1"
				+ " and d.delete_ind=0"
				+ " and d.active_ind=1"
				+ (!StringUtil.isEmpty(product_cat_id) ? " and dlpc.PRD_GRP_CAT_ID="
						+ product_cat_id + " and dlpc.LOC_ID=d.ID "
						: "")
				+ (!StringUtil.isEmpty(unit_of_stay_type_id) ? " and p.unit_of_stay_type_id = '"
						+ unit_of_stay_type_id + "'  and p.park_id=d.id"
						: "")
				// + ((StringUtil.isEmpty(product_cat_id) &&
				// StringUtil.isEmpty(unit_of_stay_type_id)) ? "" :
				// " and p.park_id=d.id")
				+ (product_cat_id.equals(PRDCAT_TICKET)?" and d.id in (select loc_id from d_loc_attr_value where attr_id=10030)":"") //Sara[5/21/2014] 10030, Web Settings seciont in Inventory Manager Facility details page
				+ " order by d.name asc";

		logger.debug("Execute query: " + sql);
		List<String> values = db.executeQuery(sql, "NAME");

		if (values == null || values.size() < 1) {
			throw new ErrorOnDataException("No recore is found.");
		} else {
			for (int i = 0; i < values.size(); i++) {
				values.set(i, values.get(i).trim());
			}
		}

		db.disconnect();
		return values;
	}

	public boolean isUserExists(String schema, String userName) {
		db.resetSchema(schema);

		logger.info("Check if the User(UserName=" + userName
				+ ") exists in system.");
		String sql = "select ID from X_USER where NAME = '" + userName
				+ " and ACTIVE=1 and DELETED=0";
		logger.info("Execute sql: " + sql);
		List<String> result = db.executeQuery(sql, "ID");
		if (result.size() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Get all agency filters from DB which will display as agency filter in
	 * "View As List" page
	 * 
	 * @param ridbSchema
	 * @param contractSchema
	 * @return
	 */
	public List<String> getAllAgencyFiltersFromDB(String ridbSchema,
			String contractSchema) {
		db.resetSchema(ridbSchema);

		logger.info("Get the agency ids and names from db using schema:"
				+ ridbSchema);
		String sql = "select orgname from organization where orgid not in (select ridb_ag_id from ag_mapping) and (orgid in (select orgid from orgfacility) or orgid in (select orgid from orgrecarea)) union select "
				+ contractSchema
				+ ".d_loc.dscr from "
				+ contractSchema
				+ ".d_loc where "
				+ contractSchema
				+ ".d_loc.id in (select orms_ag_id from ag_mapping) order by orgname";

		logger.info("Execute sql: " + sql);
		List<String> result = db.executeQuery(sql, "ORGNAME");
		if (result.size() < 1) {
			throw new ErrorOnDataException("No data found.");
		}
		return result;
	}

	/**
	 * Get the last password change date from X_USER identifier by user name
	 * 
	 * @param schema
	 * @param userName
	 * @return
	 */
	public String getUserLastPasswordChangeDate(String schema, String userName) {
		db.resetSchema(schema);

		logger.info("Get the User(UserName = " + userName
				+ ") Last Password ChangeDate from DB.");
		String sql = "select to_char(PWD_CHANGE_DATE, 'yyyy/mm/dd') as PWD_CHANGE_DATE from X_USER where Name = '"
				+ userName + "'";
		logger.info("Execute sql: " + sql);
		List<String> result = db.executeQuery(sql, "PWD_CHANGE_DATE");
		if (result.size() < 1) {
			throw new ErrorOnDataException(
					"No data found identified by UserName - " + userName);
		}
		return result.get(0);
	}

	/**
	 * get Invoice Number from DB by order number.
	 * 
	 * @param orderNum
	 * @param schema
	 */
	public String getInvoiceNum(String orderNum, String schema) {
		logger.info("Get Invoice ID from Table:F_DEFT.");

		db.resetSchema(schema);
		String query = "Select distinct EFT_INVOICE_ID From F_DEFT Where ord_num ='"
				+ orderNum + "' order by EFT_INVOICE_ID desc";
		logger.info("Execute SQL:" + query);
		String invoiceNum = db.executeQuery(query, "EFT_INVOICE_ID").get(0)
				.trim();
		logger.info("Invoice Number is:" + invoiceNum);
		return invoiceNum;
	}

	/**
	 * get Invoice Job Number from DB by Invoice Number.
	 * 
	 * @param invoiceID
	 * @param schema
	 */
	public String getInvoiceJobNum(String invoiceID, String schema) {
		logger.info("Get Invoice Job ID from Table:F_DEFT by Invoice ID.");

		db.resetSchema(schema);
		String query = "Select EFT_INVOICE_JOB_ID From F_DEFT Where EFT_INVOICE_ID ='"
				+ invoiceID + "'";
		logger.info("Execute SQL:" + query);
		String invoiceJobNum = db.executeQuery(query, "EFT_INVOICE_JOB_ID")
				.get(0).trim();
		logger.info("Invoice Job Number is:" + invoiceJobNum);
		return invoiceJobNum;
	}

	public List<String[]> getSerialPOSRngInfo(String pos, String schema) {
		db.resetSchema(schema);

		logger.info("Get Serial POS product Inventory Info and Sale status for product: "
				+ pos);

		String query = "select P_PRD_SERIAL_RNG.create_date as rng_date, last_used,sales_status_id,RNG_TO,id from P_PRD_SERIAL_RNG INNER JOIN p_prd ON P_PRD_SERIAL_RNG.prd_id= p_prd.prd_id where p_prd.prd_name='"
				+ pos + "' order by  P_PRD_SERIAL_RNG.RNG_TO DESC";
		String columnNames[] = new String[] { "RNG_TO", "sales_status_id",
				"last_used", "rng_date", "ID" };

		logger.info("Execute SQL:" + query);
		List<String[]> result = db.executeQuery(query, columnNames);

		return result;
	}

	public List<String[]> getCustPassInfoForPOS(String order, String schema) {
		db.resetSchema(schema);

		logger.info("Get Serial POS product customer pass info for order: "
				+ order);

		String query = "select * from d_ref_cust_pass INNER JOIN o_order ON o_order.id=d_ref_cust_pass.ord_id where o_order.ord_num='"
				+ order + "' order by  d_ref_cust_pass.PASS_NUMBER DESC";
		String columnNames[] = new String[] { "PASS_TYPE_ID", "PASS_NUMBER",
				"EXPIRY_DATE", "IMPORT_ID", "FULFILLED_STATUS", "CREATE_DATE",
				"CREATE_USER", "ORD_ITEM_ID" };

		logger.info("Execute SQL:" + query);
		List<String[]> result = db.executeQuery(query, columnNames);

		return result;

	}

	public List<String[]> getCustPassInfoForPOSByPassNum(String number,
			String schema) {
		db.resetSchema(schema);

		logger.info("Get Serial POS product customer pass info by pass number: "
				+ number);

		String query = "select * from d_ref_cust_pass where pass_number='"
				+ number + "'";
		String columnNames[] = new String[] { "PASS_TYPE_ID", "PASS_NUMBER",
				"EXPIRY_DATE", "IMPORT_ID", "FULFILLED_STATUS", "CREATE_DATE",
				"CREATE_USER", "ORD_ITEM_ID", "ORD_ID" };

		logger.info("Execute SQL:" + query);
		List<String[]> result = db.executeQuery(query, columnNames);

		return result;
	}

	/**
	 * Get the config of the auto approve supply order for x_prop table
	 * 
	 * @param schema
	 * @return true if the config is on, false if the config is off
	 * @author Lesley Wang
	 * @date Oct 19, 2012
	 */
	public boolean isAutoApproveSupplyOrderConfigOn(String schema) {
		db.resetSchema(schema);

		String query = "select Value from x_prop where name='AutoApproveSupplyOrder'";
		logger.info("Execute SQL:" + query);
		String value = db.executeQuery(query, "Value", 0);
		boolean result = Boolean.parseBoolean(value);
		logger.info("The config of Auto Approve Supply Order is "
				+ (result ? " on" : "off"));
		return result;
	}

	public void checkAndReleaseSlipRafting(String schema, String arrivalDate,
			boolean newSession, String... slipIDs) {
		this.checkAndReleaseSlipRafting(schema, arrivalDate, StringUtil.EMPTY,
				newSession, slipIDs);
	}

	public void checkAndReleaseSlipRafting(String schema, String arrivalDate,
			String departureDate, boolean newSession, String... slipIDs) {
		db.resetSchema(schema);
		String slipIds = slipIDs[0];
		for (int i = 1; i < slipIDs.length; i++) {
			slipIds += "," + slipIDs[i];
		}
		String query = "select distinct oo.ORD_NUM from O_ORDER oo, O_ORD_ITEM ooi "
				+ "where oo.ID = ooi.ORD_ID and oo.ord_status_id=1 and ooi.prd_id in ("
				+ slipIds
				+ ")  and "
				+ "ooi.start_date<= TO_DATE('"
				+ arrivalDate + "', 'mm-dd-yyyy') and ooi.rafting=1";
		if (StringUtil.notEmpty(departureDate)) {
			query += " and ooi.end_date>= TO_DATE('" + departureDate
					+ "', 'mm-dd-yyyy')";
		}
		logger.info("Execute SQL:" + query);
		List<String> ordNums = db.executeQuery(query, "ord_num");

		String[] res_nums = ordNums.toArray(new String[ordNums.size()]);
		TestProperty.putProperty("ClearnupOrders.isSlipRes", "true");
		if (res_nums.length > 0) {
			cleanReservations(res_nums, schema, newSession);
		}
	}

	public void checkAndReleaseSlipInventory(String schema, String dateFrom,
			int days, boolean isFloatIn, String... slipIDs) {
		checkAndReleaseSlipInventory(schema, dateFrom, days, isFloatIn, true,
				slipIDs);
	}

	public void checkAndReleaseSlipInventory(String schema, String dateFrom,
			int days, boolean isFloatIn, boolean newSession, String... slipIDs) {
		String dateTo = DateFunctions.getDateAfterGivenDay(dateFrom, days);
		checkAndReleaseSlipInventory(schema, dateFrom, dateTo, isFloatIn,
				newSession, slipIDs);
	}

	public void checkAndReleaseSlipInventory(String schema, String dateFrom,
			String dateTo, boolean isFloatIn, String... slipIDs) {
		checkAndReleaseSlipInventory(schema, dateFrom, dateTo, isFloatIn, true,
				slipIDs);
	}

	public void checkAndReleaseSlipInventory(String schema, String dateFrom,
			String dateTo, boolean isFloatIn, boolean newSession,
			String... slipIDs) {

		boolean needSync = false;
		boolean onHold = false;
		String resNums = "";

		if (isFloatIn && (DateFunctions.compareToToday(dateFrom) > 0)) {
			throw new InvalidDataException(
					"dateFrom is after today, which is conflicts with isCheckIn=true");
		}

		if (StringUtil.isEmpty(dateTo)) {
			dateTo = DateFunctions.getDateAfterGivenDay(dateFrom, 365);
		}

		for (int i = 0; i < slipIDs.length; i++) {
			// if
			// (Boolean.valueOf(TestProperty.getProperty("record")).booleanValue())
			// {
			// recordSiteInfoToDB(schema, slipIDs[i], dateFrom,
			// dateTo,TestProperty.getProperty("fullCaseName"));
			// }
			// retrieve the site type from DB
			int slipType = this.getSlipType(slipIDs[i], schema);
			boolean dbReadOnly = Boolean.valueOf(TestProperty.getProperty(env
					+ ".db.readonly", "false"));
			if (dbReadOnly) {
				logger.info("Release inventory from UI.");
				onHold = this.isInventoryHold(slipIDs[i], slipType, dateFrom,
						dateTo, schema);
				if (onHold) {
					Object[] args = new Object[2];
					args[0] = "contract="
							+ DataBaseFunctions
									.getContractFromSchemaName(schema)
							+ ":from=" + dateFrom + ":to=" + dateTo;
					args[1] = slipIDs[i];
					// TODO to implement releaseSlipInventory in
					// InventoryManager
					// new supportscripts.function.ReleaseHoldInventory()
					// .execute(args);
				}
			} else {
				logger.info("Release inventory from DB.");
				needSync = releaseHoldSlipInvFromDB(slipIDs[i], slipType,
						dateFrom, dateTo, schema);
				// needSync |= releaseDepartureSlipInvFromDB(slipIDs[i],
				// slipType,
				// dateFrom, dateTo, schema); This is no need for departured
				// inventory is just for undock now

				if (needSync) {
					// needSync maybe set to false if one of site is
					// not on hold when release for multiple sites
					onHold = true;
				}
			}

			String resNum = checkBookedSlipInv(slipIDs[i], slipType, dateFrom,
					dateTo, schema, isFloatIn);
			if (!resNums.contains(" " + resNum)) {
				resNums = resNums + " " + resNum;
			}
		}

		resNums = resNums.trim();
		if (resNums.length() > 0) {
			String[] res_nums = resNums.split(" ");
			TestProperty.putProperty("ClearnupOrders.isSlipRes", "true");
			cleanReservations(res_nums, schema, newSession);
		}

		if (onHold) {
			Browser.sleep(OCCAM_SYNC_TIME);
		}
	}

	public String[] getVendorNameAndNumByStoreName(String schema,
			String storeName) {
		db.resetSchema(schema);

		logger.info("Get vendor number and vendor name by store name:"
				+ storeName);

		String query = "select D_VENDOR.VENDOR_NUM,D_VENDOR.NAME from D_VENDOR, D_STORE where D_VENDOR.ID = D_STORE.VENDOR_ID and D_STORE.name = '"
				+ storeName + "'";
		String columnNames[] = new String[] { "NAME", "VENDOR_NUM" };
		logger.info("Execute SQL:" + query);
		List<String[]> result = db.executeQuery(query, columnNames);

		if (result == null || result.size() < 1)
			throw new ErrorOnDataException(
					"Could not get any vendor info by store name:" + storeName);

		return result.get(0);
	}

	public String getStateByParkName(String schema, String parkName) {
		logger.info("Get state abbreviation by park name:" + parkName);
		db.resetSchema(schema);
		String query = "select state from d_loc where name = '" + parkName
				+ "'";
		logger.info("Execute SQL:" + query);
		List<String> result = db.executeQuery(query, "state");
		if (null == result || result.size() < 1) {
			throw new ErrorOnDataException(
					"Could not get state by given park name:" + parkName);
		} else {
			return result.get(0);
		}
	}

	/**
	 * Get all states in contract level
	 * 
	 * @param schema
	 * @return
	 */
	public List<String> getAllStatesInContractLevel(String schema) {
		db.resetSchema(schema);
		String sql = "select distinct state from d_loc where state !='null'";

		logger.info("Run sql:" + sql);
		return db.executeQuery(sql, "STATE");
	}

	/**
	 * This method was used to get order number by receipt number, and there are
	 * more than one order number within this recepit Especially for HF
	 * privilege order which maybe contains convinance fee(transaction type id
	 * was 9167)
	 * 
	 * @param schema
	 * @param receiptNum
	 * @param transTypeID
	 * @return
	 */
	public String getOrderNumsByReceiptNum(String schema, String receiptNum,
			String transTypeID) {
		db.resetSchema(schema);

		logger.info("Query order id by receipt number " + receiptNum
				+ " and transaction type id " + transTypeID);
		String query = "select ord_id from O_ORD_ITEM_TRANS "
				+ "where trans_typ_id='" + transTypeID + "' "
				+ "and id in (select fee_trans_id from o_rcpt_ord "
				+ "where rcpt_id = (select id from o_rcpt "
				+ "where rcpt_no = '" + receiptNum + "'))";
		List<String> ids = db.executeQuery(query, "ord_id");
		if (ids == null || ids.size() < 1) {
			logger.error("No Order Id Found.");
			return null;
		}
		// Note: The search result should be unique order id here.
		if (ids.size() > 1)
			throw new ErrorOnDataException(
					"Could not get unique order id. Please check your input parameters or modify this method!");

		logger.info("Query Order Number by Order ID:" + ids.get(0));
		query = "select ord_num from o_order " + "where id =" + ids.get(0);
		List<String> strs = db.executeQuery(query, "ord_num");
		if (strs == null || strs.size() < 1)
			throw new ItemNotFoundException("No Order Numbers Info Found.");

		return strs.get(0);
	}

	public String getOrdPriceByOrdNum(String schema, String ordNum) {
		db.resetSchema(schema);
		logger.info("Query Order Price by Order Num:" + ordNum);
		String query = "select ord_price from o_order " + "where ord_num ='"
				+ ordNum + "'";
		List<String> strs = db.executeQuery(query, "ord_price");
		if (strs == null || strs.size() < 1)
			throw new ItemNotFoundException("No Order Numbers Info Found.");

		return strs.get(0);
	}

	public String getRcptPricePriceByRcptNum(String schema, String rcptNum) {
		db.resetSchema(schema);
		logger.info("Query Receipt Price by Receipt Num:" + rcptNum);
		String query = "select price from o_rcpt " + "where rcpt_no ='"
				+ rcptNum + "'";
		List<String> strs = db.executeQuery(query, "price");
		if (strs == null || strs.size() < 1)
			throw new ItemNotFoundException("No Receipt Info Found.");

		return strs.get(0);
	}

	/**
	 * Check the attribut for pos product group whether exist
	 * 
	 * @param schema
	 * @param group
	 * @param attributId
	 * @return
	 */
	public boolean isAttributeSetForPosProductGroup(String schema,
			String group, String attributId) {
		db.resetSchema(schema);

		String query = "select P_PRD_GRP_ATTR.ACTIVE_IND from P_PRD_GRP,P_PRD_GRP_ATTR where P_PRD_GRP.PRD_GRP_ID=P_PRD_GRP_ATTR.PRD_GRP_ID "
				+ "and P_PRD_GRP.PRD_GRP_NAME='"
				+ group
				+ "' "
				+ "and P_PRD_GRP_ATTR.ATTR_ID=" + attributId;
		List<String> results = db.executeQuery(query, "ACTIVE_IND");
		if (results.isEmpty()) {
			return false;
		}
		boolean toReturn = Integer.parseInt(results.get(0)) == 1 ? true : false;
		return toReturn;
	}

	/**
	 * Set up a attribut for pos product group
	 * 
	 * @param schema
	 * @param group
	 * @param attributId
	 */
	public void setupAttributeForPosProductGroup(String schema, String group,
			String attributId) {
		logger.info("Setup Extra Decimal Place attribute for product group - "
				+ group);

		db.resetSchema(schema);
		String sql = "INSERT INTO P_PRD_GRP_ATTR (PRD_GRP_ATTR_ID, ATTR_ID, MANDT_IND, PRD_GRP_ID, STATUS_ID, ACTIVE_IND, DELETED_IND, CHARGEABLE, PRD_CAT_ID, WEB_KEY_ATTR) values (get_sequence('P_PRD_GRP_ATTR'), "
				+ attributId
				+ ", 0, (select PRD_GRP_ID from P_PRD_GRP where PRD_GRP_NAME='"
				+ group + "'), NULL, 1, 0, NULL, 4, 0)";
		logger.info("Executing query: " + sql);
		db.executeUpdate(sql);
	}

	/**
	 * 'POS Product Default Unit Price' is an attribute used for create a new
	 * pos product, if it is set, pos product will have a default unit price
	 * 
	 * @param schema
	 * @param group
	 * @return
	 */
	public boolean isDefaultUnitPriceAttributeSetForProductGroup(String schema,
			String group) {
		return this.isAttributeSetForPosProductGroup(schema, group, "10411"); // '10411'
		// attribute
		// id
		// for
		// \ufffdPOS
		// Product
		// Default
		// Unit
		// Price\ufffd
	}

	public void setupDefaultUnitPriceAttributeSetForProductGroup(String schema,
			String group) {
		this.setupAttributeForPosProductGroup(schema, group, "10411");
	}

	/**
	 * 'POS Product Override Default Unit Price' is a attribute for create a new
	 * pos product, if it is set as 'No', then the default unit price will can
	 * not be editable when a default unit price has been set up
	 * 
	 * @param schema
	 * @param group
	 * @return
	 */
	public boolean isOverrideDefaultUnitPriceAttributeSetForProductGroup(
			String schema, String group) {
		return this.isAttributeSetForPosProductGroup(schema, group, "10412"); // '10412'
		// attribute
		// id
		// for
		// \ufffdPOS
		// Product
		// Override
		// Default
		// Unit
		// Price\ufffd
	}

	public void setupOverrideDefaultUnitPriceAttributeSetForProductGroup(
			String schema, String group) {
		this.setupAttributeForPosProductGroup(schema, group, "10412");
	}

	/**
	 * Get the total count of child sites for the parent site.
	 * 
	 * @param schema
	 * @param parent_id
	 * @return
	 * @author Lesley Wang
	 * @date Nov 7, 2012
	 */
	public String getChildSiteTotalCount(String schema, String parent_id) {
		db.resetSchema(schema);
		logger.info("Get the child site total count of the parent site: "
				+ parent_id);
		String query = "SELECT Count(*) FROM P_PRD  " + "WHERE parent_id ='"
				+ parent_id + "' and active_ind=1 and deleted_ind=0";
		return db.executeQuery(query, "Count(*)", 0);
	}

	public void updateTransmissionDueDate(String newDate, String incoiceNum,
			String schema) {
		logger.info("Update transmission due date and create date in order to run EFT Transmission immediately");

		db.resetSchema(schema);
		String sql1 = "update F_EFT_INVC_TRANSM set create_date = to_date('"
				+ newDate + "', 'mm-dd-yy') where eft_invoice_id='"
				+ incoiceNum + "'";
		String sql2 = "update F_EFT_INVC_TRANSM set trans_due_date = to_date('"
				+ newDate + "', 'mm-dd-yy') where eft_invoice_id='"
				+ incoiceNum + "'";
		logger.info("Execute SQL:" + sql1);
		db.executeUpdate(sql1);

		logger.info("Execute SQL:" + sql2);
		db.executeUpdate(sql2);
	}

	/**
	 * Get vendor bank account ID by vendor name and routing number.
	 * 
	 * @param schema
	 * @param vendorName
	 * @param routingNumber
	 */
	public String getVendorBankAccountID(String schema, String vendorName,
			String routingNumber) {
		logger.info("Get vendor bank account ID by vendor name and routing number.");
		db.resetSchema(schema);
		String sql1 = "select ID from D_VENDOR where name = '" + vendorName
				+ "'";// get vendor ID by name
		String sql2 = "select ID from F_EFT_BANK_ACCOUNT where routing_num = '"
				+ routingNumber
				+ "' and STATUS=1 and DISCRIMINATOR='VENDOR' and vendor_id = ("
				+ sql1 + ")";
		logger.info("Execute SQL:" + sql2);
		db.executeQuery(sql2);

		List<String> results = db.executeQuery(sql2, "ID");
		if (results.isEmpty()) {
			return StringUtil.EMPTY;
		} else {
			return results.get(0);
		}
	}

	/**
	 * Get Question ID by Question Display Text
	 * 
	 * @param schema
	 * @param displayText
	 * @return
	 */
	public String getQuestionIDByDisplayText(String schema, String displayText) {
		db.resetSchema(schema);
		String sql = "select ID from P_QUESTION where DISPLAY_TEXT = '"
				+ displayText + "'";
		logger.info("Execute SQL:" + sql);
		String id = db.executeQuery(sql, "ID", 0);
		logger.info("The question ID of the question display text: "
				+ displayText + " is: " + id);
		return id;
	}

	/**
	 * Get document template ID by template name
	 * 
	 * @param schema
	 * @param displayText
	 * @return
	 */
	public String getDocTemplateIDByName(String schema, String templateName) {
		db.resetSchema(schema);
		String sql = "select ID from P_DOCUMENT_TEMPLATE where DOC_TEMPLATE_NAME = '"
				+ templateName + "'";
		logger.info("Execute SQL:" + sql);
		String id = db.executeQuery(sql, "ID", 0);
		logger.info("The document template ID of the template name: "
				+ templateName + " is: " + id);
		return id;
	}

	/**
	 * Get bond issuer id by name
	 * 
	 * @param schema
	 * @param displayText
	 * @return
	 */
	public String getBondIssuerIDByName(String schema, String businessName) {
		db.resetSchema(schema);
		String sql = "select ID from f_bond_issuer where business_name = '"
				+ businessName + "'";
		logger.info("Execute SQL:" + sql);
		String id = db.executeQuery(sql, "ID", 0);
		logger.info("The ID of the bond issuer: " + businessName + " is: " + id);
		return id;
	}

	/**
	 * Get Facility Short Name by Facility ID
	 * 
	 * @param schema
	 * @param facilityID
	 * @return
	 * @author Lesley Wang
	 * @date Nov 28, 2012
	 */
	public String getFacilityShortName(String schema, String facilityID) {
		db.resetSchema(schema);
		String sql = "select short_name from d_loc where id = " + facilityID;
		logger.info("Execute SQL:" + sql);
		String sName = db.executeQuery(sql, "short_name", 0);
		logger.info("The short name of the facility  " + facilityID + " is: "
				+ sName);
		return sName;
	}

	/**
	 * Get zones' names associated with the facility, order by name
	 * alphabetical.
	 */
	public List<String> getFacilityZonesNames(String schema, String facilityID) {
		db.resetSchema(schema);
		String sql = "select name from d_loc where parent_id = " + facilityID
				+ " and type_id=35 and level_num=50 order by name";
		logger.info("Execute SQL:" + sql);
		List<String> names = db.executeQuery(sql, "name");
		return names;
	}

	/** Get Entrance Type name according to permit type name and facility id */
	public List<String> getEntranceTypePerPermitType(String schema,
			String facilityID, String permitTypeNm) {
		db.resetSchema(schema);
		String sql = "select distinct grp.prd_grp_name from P_PRD_GRP grp, P_PRD prd, P_ENTRANCE_PERMIT_TYPE entPer, "
				+ "P_PERMIT_TYPE perType, P_PERMIT_TYPE_NAME perNm "
				+ "where grp.prd_grp_id=prd.prd_grp_id and prd.loc_id="
				+ facilityID
				+ " and prd.prd_id=entPer.entrance and 	entPer.permit_Type=perType.id "
				+ " and entPer.active_ind=1 and entPer.deleted_ind=0 "
				+ "and perNm.id=perType.permit_type_name_id and perNm.name='"
				+ permitTypeNm + "' order by grp.prd_grp_name";
		logger.info("Execute SQL:" + sql);
		List<String> names = db.executeQuery(sql, "prd_grp_name");
		return names;
	}

	/**
	 * Get customer price from table P_PRD_HFPRICE by privilege code and name.
	 * 
	 * @param schema
	 * @param productCode
	 * @param purchaseType
	 *            1-Original, 2-Replacement, 3-Transfer
	 * @param isAppliedToAllState
	 *            0-applied to specified state, 1-applied to all state
	 * @return
	 */
	public String getPriCustPrice(String schema, PrivilegeInfo pri,
			String purchaseType, String isAppliedToAllState) {

		logger.info("Get customer price form table P_PRD_HFPRICE by privilege:"
				+ pri.code);
		db.resetSchema(schema);
		String query = "select cust_price from P_PRD_HFPRICE where purchase_type_id = "
				+ purchaseType
				+ " and active_ind = 1 and prd_id = (select prd_id from p_prd where prd_cd = '"
				+ pri.code
				+ "' and prd_name ='"
				+ pri.name
				+ "') and applicable_to_all_state = " + isAppliedToAllState;
		logger.info("SQL: " + query);
		List<String> resultList = db.executeQuery(query, "cust_price");
		if (resultList.size() < 1) {
			throw new ErrorOnDataException(
					"There isn't any customer price for privilege " + pri.code);
		}
		String price = resultList.get(0);
		logger.info("Price is " + price);
		return price;
	}

	public String getTranslationByLocationCode(String labelKey,
			String locationCode, String schema) {
		logger.info("Get translation for lable key " + labelKey
				+ " for location " + locationCode);
		db.resetSchema(schema);
		String query = "select * from X_TRANSLATION where label_key='"
				+ labelKey + "' and loc_code='" + locationCode + "'";
		List<String> values = db.executeQuery(query, "label_value");
		if (values == null || values.size() < 1)
			return null;
		if (values.size() > 1)
			throw new ErrorOnDataException(
					"Found more than one label value Please check your data.");
		return values.get(0);
	}

	/**
	 * Set orms user account password change date. The method can be used to set
	 * password expired.
	 * 
	 * @param schema
	 * @param userName
	 * @param date
	 * @author Lesley Wang
	 * @date Dec 14, 2012
	 */
	public void updateOrmsUserPasswordChangeDate(String schema,
			String userName, String date) {
		db.resetSchema(schema);
		date = DateFunctions.formatDate(date, "dd-MMM-yy");
		String query = "update D_USER_AUTH set PWD_CHANGE_DATE='" + date + "' "
				+ "where LOGIN='" + userName + "'";
		logger.info("Execute query: " + query);
		if (db.executeUpdate(query) != 1) {
			throw new ErrorOnDataException("Wrongly execute query");
		}
	}

	public void updateSeasonStatusAsDeleteFromDB(String facility,
			String prdCat, String startDate, String endDate, String schema) {

		db.resetSchema(schema);
		logger.info("Update facility " + facility + " season from " + startDate
				+ " to " + endDate + " status as deleted");
		String sql = "update I_SEASON_SCHDL set deleted_ind=1"
				+ " where facility_id=" + facility
				+ " and (start_date >= to_date('" + startDate
				+ "','mm/dd/yyyy') and start_date <= to_date('" + endDate
				+ "','mm/dd/yyyy'))" + " and  prd_cat_id=" + prdCat
				+ " and deleted_ind!=1";
		db.executeUpdate(sql);
	}

	/**
	 * Need a active ongoing lottery for the expect dock.
	 * 
	 * @param facilityID
	 * @param dockName
	 * @param lotteryName
	 * @param lotteryIDInSupport
	 */
	public String checkOngoingLottery(String facilityID, String dockName,
			String lotteryName, String schema, String today) {
		logger.info("Check the lottery for dock " + dockName
				+ " is ongoing or not.");

		db.resetSchema(schema);
		String lotteryID;

		// need exist an active lottery for given facility
		String query = "select prd_id from p_prd where prd_name = '"
				+ lotteryName
				+ "' and product_cat_id = 9 and active_ind = 1 and loc_id = '"
				+ facilityID + "'";// 9 means lottery
		List<String> lotteryList = db.executeQuery(query, "prd_id");
		if (null == lotteryList || lotteryList.size() <= 0) {// no active
			// lottery
			throw new InvalidDataException(
					"There isn't active lottery, please run data in d_inv_new_lottery_program");
		} else {
			lotteryID = lotteryList.get(0);
		}

		// lottery should be assigned to the dock
		if (StringUtil.notEmpty(dockName)) {
			query = "select loc_id from P_PRD_LOTTERY_PARTICIPATION where lottery_id = "
					+ lotteryID;
			List<String> locIDList1 = db.executeQuery(query, "loc_id");
			query = "select id from d_loc where name = '" + dockName + "'";
			List<String> locIDList2 = db.executeQuery(query, "id");
			if (!MiscFunctions.compareResult("Location ID", locIDList1.get(0),
					locIDList2.get(0))) {
				throw new InvalidDataException(
						"Lottery Participation location should be the dock:"
								+ dockName
								+ ". Please run data in d_inv_new_lottery_program");
			}
		}

		// schedule
		query = "select active_ind, to_char(app_st_date, 'yyyy-mm-dd hh:mm:ss') app_st_date, to_char(app_end_date, 'yyyy-mm-dd hh:mm:ss') app_end_date, id from P_LOTTERY_SCHD where prd_id = "
				+ lotteryID;
		String[] colNames = { "active_ind", "app_st_date", "app_end_date", "id" };
		List<String[]> scheduleList = db.executeQuery(query, colNames);
		if (null == scheduleList || scheduleList.size() <= 0) {// no active
			// schedule.
			throw new InvalidDataException(
					"There isn't active lottery schedule, please run data in d_inv_new_lottery_program");
		} else {
			String appStartDate, appEndDate;
			for (int i = 0; i < scheduleList.size(); i++) {
				if ("1".equals(scheduleList.get(i)[0])) {// there is active
					// schedule
					appStartDate = scheduleList.get(i)[1];
					appEndDate = scheduleList.get(i)[2];
					// today must be between app start date and app end date.
					if (DateFunctions.compareWithCurrentDateTime(
							DataBaseFunctions.getContractTimeZone(schema),
							appStartDate) > 0
							|| DateFunctions.compareWithCurrentDateTime(
									DataBaseFunctions
											.getContractTimeZone(schema),
									appEndDate) < 0) {
						throw new InvalidDataException(
								"There isn't active lottery schedule, please run data in d_inv_new_lottery_program");
					}
					return scheduleList.get(i)[3];// there is an ongoing
													// lottery.
				}
			}
		}
		return "";
	}

	/**
	 * This method is used to set the season's status as deleted for a special
	 * facility and has a time overlap with the given one, be careful when use
	 * this method
	 * 
	 * @param schema
	 * @param facilityId
	 * @param season
	 */
	public void updateSeasonDeletaStatusViaDB(String schema, String facilityId,
			SeasonData season) {

		db.resetSchema(schema);
		String sql = "update I_SEASON_SCHDL set deleted_ind=1 where loc_id="
				+ facilityId
				+ " and deleted_ind!=1 and ((start_date >= to_date('"
				+ season.m_StartDate
				+ "','mm/dd/yyyy') and start_date <= to_date('"
				+ season.m_EndDate + "','mm/dd/yyyy')) or "
				+ "(end_date >= to_date('" + season.m_StartDate
				+ "','mm/dd/yyyy') and end_date <= to_date('"
				+ season.m_EndDate
				+ "','mm/dd/yyyy')) or (start_date <= to_date('"
				+ season.m_StartDate
				+ "','mm/dd/yyyy') and end_date >= to_date('"
				+ season.m_EndDate + "','mm/dd/yyyy')))";
		logger.info("Run sql:" + sql);
		int affectNum = db.executeUpdate(sql);
		logger.info("There are " + affectNum + " are delted in db!");
	}

	public int getSeasonTypeID(String schema, String season) {
		db.resetSchema(schema);
		String sql = "select ID from I_SEASON_TYPE where TYPE_NAME = '"
				+ season + "'";
		logger.info("Execute query: " + sql);
		List<String> results = db.executeQuery(sql, "ID");
		if (results.size() > 0) {
			return Integer.parseInt(results.get(0));
		}

		return 0;
	}

	public List<String> getSeasonTypes(String schema) {
		db.resetSchema(schema);
		String sql = "select TYPE_NAME from I_SEASON_TYPE order by TYPE_NAME";
		List<String> result = db.executeQuery(sql, "TYPE_NAME");
		logger.info("Execute query: " + sql);

		return result;
	}

	public void deleteSlipSeasonByDisplayName(String schema, String facilityId,
			String displayName) {

		db.resetSchema(schema);
		String sql = "update I_SEASON_SCHDL set deleted_ind=1 where facility_id="
				+ facilityId
				+ " and deleted_ind!=1 and name='"
				+ displayName
				+ "'";
		logger.info("Run sql:" + sql);
		int affectNum = db.executeUpdate(sql);
		logger.info("There are " + affectNum + " are deleted in db!");
	}

	/**
	 * This method get site season id, whose start and end date are including
	 * the special date and has a site with special id assigned to it
	 * 
	 * @param schema
	 * @param facilityId
	 * @param start
	 * @param end
	 * @param siteId
	 * @return
	 */
	public String getSiteSeasonIDByStartDateAndEndDate(String schema,
			String facilityId, String start, String end, String siteId) {

		db.resetSchema(schema);
		String query = "Select i.id from I_SEASON_SCHDL i, I_SEASON_SCHDL_PRD p where i.loc_id="
				+ facilityId
				+ " and i.deleted_ind=0 and i.active_ind =1 and i.prd_cat_id=3 and i.start_date <= to_date('"
				+ start
				+ "','mm/dd/yyyy') and i.end_date >= to_date('"
				+ end
				+ "','mm/dd/yyyy') and i.id=p.season_schdl_id and p.prd_id="
				+ siteId;
		logger.info("Run sql:" + query);
		List<String> values = db.executeQuery(query, "id");
		if (values.size() < 1) {
			throw new InvalidDataException(
					"There isn't active season exist for facility:"
							+ facilityId + "from date:" + start + " to Date:"
							+ end + " for site:" + siteId
							+ ", please prepare the season!");
		}
		return values.get(0);
	}

	public String getSlipSeasonIDByName(String schema, String displayName) {
		db.resetSchema(schema);
		String sql = "Select id from I_SEASON_SCHDL where name='" + displayName
				+ "' and deleted_ind=0 and prd_cat_id=20";
		List<String> values = db.executeQuery(sql, "ID");
		logger.info("Execute query: " + sql);
		if (values.size() < 1) {
			return "";
		}

		return values.get(0);
	}

	public String getSlipSeasonIDByName(String schema, String seasonName,
			String slipId) {
		db.resetSchema(schema);
		String query = "Select i.id from I_SEASON_SCHDL i, I_SEASON_SCHDL_MR_PRD p where i.name='"
				+ seasonName
				+ "' and i.deleted_ind=0 and i.active_ind =1 and i.prd_cat_id=20 and i.id=p.season_schd_id and p.prd_id="
				+ slipId;
		logger.info("Run sql:" + query);
		List<String> values = db.executeQuery(query, "id");
		if (values.size() < 1) {
			throw new InvalidDataException("There isn't active season named:"
					+ seasonName + " exist and assigned with slip:" + slipId
					+ ", please rerun the season set up script!");
		}
		return values.get(0);
	}

	/**
	 * Get start date and end for season
	 * 
	 * @author Phoebe
	 * @date Feb 26, 2013
	 */
	public String[] getSeasonDateInfo(String schema, String seasonId) {
		db.resetSchema(schema);
		String sql = "select to_char(start_date, 'mm/dd/yyyy') as START_DATE, to_char(end_date, 'mm/dd/yyyy') as END_DATE from I_SEASON_SCHDL where id="
				+ seasonId;
		logger.info("Query from db: " + sql);
		String[] colNames = { "start_date", "end_date" };
		return db.executeQuery(sql, colNames, 0);
	}

	public String[] getSeasonDateInfo(String schema, String seasonName,
			String facilityID) {
		db.resetSchema(schema);
		String sql = "select to_char(START_DATE, 'mm/dd/yyyy') as START_DATE, to_char(END_DATE, 'mm/dd/yyyy') as END_DATE from I_SEASON_SCHDL where name = '"
				+ seasonName
				+ "'"
				+ " and facility_id = "
				+ facilityID
				+ " and deleted_ind != 1";
		String[] colNames = { "start_date", "end_date" };
		logger.info("Execute query: " + sql);
		return db.executeQuery(sql, colNames, 0);
	}

	public void deleteSeasonScheduleByID(String schema, String seasonID) {
		db.resetSchema(schema);
		String sql = "update I_SEASON_SCHDL set deleted_ind = 1 where id = '"
				+ seasonID + "' and deleted_ind != 1";
		logger.info("Execute query: " + sql);
		int affectNum = db.executeUpdate(sql);
		logger.info("There are " + affectNum + " season(s) deleted in db!");
	}

	public void deleteSeasonScheduleByNameViaDB(String schema, String seasonName) {
		db.resetSchema(schema);
		String sql = "update I_SEASON_SCHDL set deleted_ind = 1 where name = '"
				+ seasonName + "' and deleted_ind != 1";
		logger.info("Run sql:" + sql);
		int affectNum = db.executeUpdate(sql);
		logger.info("There are " + affectNum + " season(s) deleted in db!");
	}

	public List<String> getProductUsingSameGroup(String schema, String groupName) {
		db.resetSchema(schema);
		String sql = "select * from P_PRD INNER JOIN P_PRD_GRP ON P_PRD_GRP.PRD_GRP_ID=P_PRD.PRD_GRP_ID where P_PRD_GRP.prd_grp_name='"
				+ groupName + "'";
		logger.info("Run sql:" + sql);

		List<String> result = db.executeQuery(sql, "PRD_NAME");
		return result;
	}

	public List<String> getDocksByParkID(String schema, String parkID) {
		db.resetSchema(schema);
		String sql = "select NAME from D_LOC where CD like '%"
				+ parkID
				+ "%' and STATUS_ID = 1 and TYPE_ID=32 and DELETE_IND = 0 order by NAME";
		logger.info("Query from db: " + sql);
		List<String> docks = db.executeQuery(sql, "NAME");
		return docks;
	}

	public List<String> getSlipsByParkIDAndDockName(String schema,
			String parkID, String dock) {
		db.resetSchema(schema);
		String sql = "select * from (p_prd inner join d_loc on p_prd.loc_id=d_loc.id"
				+ " and d_loc.name='"
				+ dock
				+ "' and d_loc.parent_id="
				+ parkID
				+ " and d_loc.status_id=1)"
				+ " where p_prd.active_ind=1";
		logger.info("Query from db: " + sql);
		List<String> slips = db.executeQuery(sql, "prd_name");
		return slips;
	}

	public String getSlipIDByCodeAndLocationId(String schema, String slipCode,
			String locId) {
		db.resetSchema(schema);
		String query = "select PRD_ID from P_PRD where ACTIVE_IND=1 and PRD_CD = '"
				+ slipCode + "' and loc_id='" + locId + "'";
		logger.info("Query from db: " + query);
		List<String> ids = db.executeQuery(query, "PRD_ID");
		if (ids.size() < 1) {
			throw new ErrorOnDataException(
					"Cannot find any slip records whose code is:" + slipCode
							+ " and location id is:" + locId);
		}
		return ids.get(0);
	}

	public List<String> getSlipsByParkID(String schema, String parkID) {
		db.resetSchema(schema);
		String sql = "select * from p_prd where product_cat_id=" + PRDCAT_SLIP
				+ " and park_id='" + parkID + "' and active_ind=" + ACTIVE;
		logger.info("Query from db: " + sql);
		List<String> slips = db.executeQuery(sql, "prd_name");
		return slips;
	}

	public List<String> getSlipsNotContainNSSCByParkID(String schema,
			String parkID) {
		db.resetSchema(schema);
		String sql = "select * from p_prd where product_cat_id=" + PRDCAT_SLIP
				+ " and park_id='" + parkID + "' and active_ind=" + ACTIVE
				+ " and prd_rel_type !=3";
		logger.info("Query from db: " + sql);
		List<String> slips = db.executeQuery(sql, "prd_name");
		return slips;
	}

	public List<String> getListsByParkID(String schema, String parkID) {
		db.resetSchema(schema);
		String sql = "select * from p_prd where product_cat_id=" + PRDCAT_LIST
				+ " and park_id='" + parkID + "' and active_ind=" + ACTIVE
				+ " and deleted_ind=0";
		logger.info("Query from db: " + sql);
		List<String> lists = db.executeQuery(sql, "prd_name");
		return lists;
	}

	/**
	 * Get min payment cfm record ids in P_MIN_PMT_CFM
	 * 
	 * @author Lesley
	 * @date Feb 1, 2013
	 */
	public List<String> getMinPaymentCFMIDs(String schema, String locID,
			String prdGrpID, String prdID, String prdCatID, String ticketCatID,
			String salesChanlID) {
		db.resetSchema(schema);
		String sql = "select * from P_MIN_PMT_CFM where loc_id="
				+ locID
				+ " and prd_grp_id"
				+ (StringUtil.isEmpty(prdGrpID) ? " is null" : "=" + prdGrpID)
				+ " and prd_id="
				+ prdID
				+ " and active_id=1 and delete_id=0 and prd_cat_id="
				+ prdCatID
				+ " and ticket_cat_id"
				+ (StringUtil.isEmpty(ticketCatID) ? " is null" : "="
						+ ticketCatID) + " and sales_chanl_id=" + salesChanlID
				+ "order by id desc";
		logger.info("Query from db: " + sql);
		return db.executeQuery(sql, "id");
	}

	/**
	 * Set minimum payment cfm record. If there is an existing record, return
	 * it's id; otherwise insert a new record and return the new one's id.
	 * 
	 * @author Lesley
	 * @date Feb 1, 2013
	 */
	public String setMinPaymentCFM(String schema, String effDate, String locID,
			String prdGrpID, String prdID, String prdCatID, String ticketCatID,
			String salesChanlID) {
		List<String> results = this.getMinPaymentCFMIDs(schema, locID,
				prdGrpID, prdID, prdCatID, ticketCatID, salesChanlID);
		if (results.size() > 0) {
			logger.info("There is an existing min payment record in DB. id="
					+ results.get(0));
			return results.get(0);
		} else {
			if (StringUtil.isEmpty(effDate)) {
				effDate = DateFunctions.getDateAfterToday(-1);
			}
			db.resetSchema(schema);
			String sql = "Insert into P_MIN_PMT_CFM (ID, EFFECTIVE_DATE, "
					+ "LOC_ID, PRD_GRP_ID, PRD_ID, ACTIVE_ID, DELETE_ID,PRD_CAT_ID,TICKET_CAT_ID, SALES_CHANL_ID)"
					+ "values (get_sequence('P_MIN_PMT_CFM'), TO_DATE('"
					+ effDate + "', 'MM/DD/YYYY'), " + locID + ", " + prdGrpID
					+ ", " + prdID + ", 1, 0, " + prdCatID + ", " + ticketCatID
					+ ", " + salesChanlID + ")";
			logger.info("Insert a miniPaymentCFM record with sql=" + sql);
			db.executeUpdate(sql);
			return this.getMinPaymentCFMIDs(schema, locID, prdGrpID, prdID,
					prdCatID, ticketCatID, salesChanlID).get(0);
		}
	}

	/**
	 * Get min payment entry cfm ids
	 * 
	 * @author Lesley
	 * @date Feb 1, 2013
	 */
	public List<String> getMinPaymentEntryCFM(String schema,
			String minPayCFMID, String feeTypeID, String ruleTypeID,
			String amount) {
		db.resetSchema(schema);
		String sql = "select * from P_MIN_PMT_ENTRY_CFM where p_min_pmt_cfm_id="
				+ minPayCFMID
				+ " and fee_type_id="
				+ feeTypeID
				+ " and rule_type=" + ruleTypeID + " and amount=" + amount;
		logger.info("Query from db: " + sql);
		return db.executeQuery(sql, "id");
	}

	/**
	 * Set min payment entry cfm. When there is no any existing record, insert a
	 * new one
	 * 
	 * @author Lesley
	 * @date Feb 1, 2013
	 */
	public void setMinPaymentEntry(String schema, String minPayCFMID,
			String feeTypeID, String ruleTypeID, String amount) {
		List<String> results = this.getMinPaymentEntryCFM(schema, minPayCFMID,
				feeTypeID, ruleTypeID, amount);
		if (results.size() > 0) {
			logger.info("There is an existing record for minPayCFMID="
					+ minPayCFMID);
		} else {
			db.resetSchema(schema);
			String sql = "Insert into P_MIN_PMT_ENTRY_CFM (ID, P_MIN_PMT_CFM_ID, PRIORITY, ORDER_ITEM_TYPE_ID, "
					+ "FEE_TYPE_ID, RULE_TYPE, AMOUNT)"
					+ "values (get_sequence('P_MIN_PMT_ENTRY_CFM'), "
					+ minPayCFMID
					+ ", 0, 0, "
					+ feeTypeID
					+ ", "
					+ ruleTypeID
					+ ", " + amount + ")";
			logger.info("Insert a min Payment Entry CFM record with sql=" + sql);
			db.executeUpdate(sql);
		}
	}

	public String getAvailableQuotaQty(String schema, String parkID,
			String prd, String quotaType, String date) {
		db.resetSchema(schema);

		String startdate = DateFunctions.formatDate(date, "yyyy-MM-dd");
		String enddate = DateFunctions.formatDate(
				DateFunctions.getDateAfterGivenDay(date, 1), "yyyy-MM-dd");
		String sql = "select I_PERMIT_INV.AVAILABLE from I_PERMIT_INV INNER JOIN P_PRD ON P_PRD.PRD_ID= I_PERMIT_INV.ENTRANCE_ID "
				+ "INNER JOIN P_QUOTA_TYPE  ON P_QUOTA_TYPE.ID=I_PERMIT_INV.QUOTA_TYPE_ID "
				+ "INNER JOIN D_LOC ON D_LOC.ID=I_PERMIT_INV.LOC_ID "
				+ "where P_PRD.PRD_NAME='"
				+ prd
				+ "' and P_QUOTA_TYPE.NAME='"
				+ quotaType
				+ "' "
				+ "and D_LOC.ID='"
				+ parkID
				+ "' and I_PERMIT_INV.START_DATE>=TO_DATE('"
				+ startdate
				+ ":23:59:59', 'yyyy-mm-dd:hh24:mi:ss') "
				+ "and I_PERMIT_INV.END_DATE<=TO_DATE('"
				+ enddate
				+ ":23:59:59', 'yyyy-mm-dd:hh24:mi:ss') "
				+ "and I_PERMIT_INV.active_ind='1' and I_PERMIT_INV.deleted_ind='0'";

		logger.info("Query from db: " + sql);
		List<String> result = db.executeQuery(sql, "AVAILABLE");
		if (result.size() < 1) {
			throw new ErrorOnDataException("can not find records...");
		}
		String qty = result.get(0);
		return qty;
	}

	/**
	 * update Other Occupant required flag, 2 - Other Occupant section will be
	 * displayed in Res Details page and mandatoty to input, 4 - Other Occupant
	 * section will be NOT displayed
	 * 
	 * @param schema
	 * @param isRequired
	 */
	public void updateOtherOccupantCollectRequired(String schema,
			boolean isRequired) {
		db.resetSchema(schema);
		String sql = "update P_OCCUPANT_REQ_INF set OTHER_INF_REQ_TYP_ID = "
				+ (isRequired ? 2 : 4) + " where ID = 1";
		int num = db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
		if (num < 1) {
			throw new ErrorOnDataException("No records found in DB.");
		} else
			logger.info("Update 'Other Occupant' required type successfully.");
	}

	/**
	 * update Vechile info required flag, 1 - Vehicle Info section will be
	 * displayed in Res Details page, 4 - Vehicle Info section will be NOT
	 * displayed
	 * 
	 * @param schema
	 * @param isRequired
	 */
	public void updateVehicleInfoCollectRequired(String schema,
			boolean isRequired) {
		db.resetSchema(schema);
		String sql = "update P_EQUIP_INFO_CNFG set INFO_REQ_TYPE_ID = "
				+ (isRequired ? 1 : 4) + " where EQUIP_ID = 7";
		int num = db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
		if (num < 1) {
			throw new ErrorOnDataException("No records found in DB.");
		} else
			logger.info("Update 'Vehicle Info' required type successfully.");
	}

	/**
	 * check if the 'Boat Information' collect record has been setup in table
	 * P_EQUIP_INFO_CNFG
	 * 
	 * @param schema
	 * @param facilityID
	 * @param slipType
	 * @return
	 */
	public boolean checkBoatInfoCollectRecordExists(String schema,
			String facilityID, String slipType) {
		db.resetSchema(schema);
		String sql = "select ID from P_EQUIP_INFO_CNFG where EQUIP_ID = 357770 and LOC_ID = "
				+ facilityID
				+ " and PRD_GRP_ID = (select PRD_GRP_ID from P_PRD_GRP where PRD_GRP_NAME = '"
				+ slipType + "')";
		List<String> results = db.executeQuery(sql, "ID");
		logger.info("Execute query: " + sql);
		if (results.size() == 1) {
			return true;
		}
		return false;
	}

	public void insertBoatInfoCollectRecord(String schema, String facilityID,
			String slipType) {
		db.resetSchema(schema);
		String sql = "INSERT INTO P_EQUIP_INFO_CNFG(ID, EQUIP_ID, LOC_ID, PRD_GRP_ID, LICENSE_IND, MAKE_IND,"
				+ " MODEL_IND, STATE_IND, YEAR_IND, COLOR_IND, INFO_REQ_TYPE_ID, DELETED_IND, CLASS_ID,"
				+ " VESSEL_TYPE, REG_NUMBER, BOAT_CAT, CAPACITY, HORSE_POWER, HULL_ID, MOTOR_MANU,"
				+ " CONSTRUCTION, TRAILER_TYPE, TRAILER_LIC)"
				+ " VALUES (get_sequence('P_EQUIP_INFO_CNFG'), '357770', '"
				+ facilityID
				+ "', (select PRD_GRP_ID from P_PRD_GRP where PRD_GRP_CAT_ID=20 and PRD_GRP_NAME='"
				+ slipType
				+ "'), '1', '1', '1', '1', '1', '1', '3', '0', '20', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1')";
		db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
	}

	public boolean getBoatInfoCollectRequired(String schema, String facilityID,
			String slipType) {
		db.resetSchema(schema);
		String sql = "select INFO_REQ_TYPE_ID from P_EQUIP_INFO_CNFG "
				+ " where LOC_ID = "
				+ facilityID
				+ " and PRD_GRP_ID = (select PRD_GRP_ID from P_PRD_GRP where PRD_GRP_CAT_ID=20 and PRD_GRP_NAME='"
				+ slipType + "')";
		List<String> results = db.executeQuery(sql, "INFO_REQ_TYPE_ID");
		logger.info("Execute query: " + sql);
		if (results.size() < 1) {
			return false;
		}

		return Integer.parseInt(results.get(0)) == 3;
	}

	/**
	 * update 'Boat Information' required flag, 3 - 'Boat Information' section
	 * will be displayed at Res Details page and mandatory to input, 4 - 'Boat
	 * Information' will NOT be diaplyed
	 * 
	 * @param schema
	 * @param facilityID
	 * @param slipType
	 * @param isRequired
	 */
	public void updateBoatInfoCollectRequired(String schema, String facilityID,
			String slipType, boolean isRequired) {
		db.resetSchema(schema);
		String sql = "update P_EQUIP_INFO_CNFG set INFO_REQ_TYPE_ID = "
				+ (isRequired ? 3 : 4)
				+ " where LOC_ID = "
				+ facilityID
				+ " and PRD_GRP_ID = (select PRD_GRP_ID from P_PRD_GRP where PRD_GRP_CAT_ID=20 and PRD_GRP_NAME='"
				+ slipType + "')";
		int num = db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
		if (num < 1) {
			throw new ErrorOnDataException("No records found in DB.");
		} else
			logger.info("Update 'Boat Info' required type successfully.");
	}

	public boolean isPropertyValueExists(String schema, String propName) {
		return !StringUtil.isEmpty(getPropertyValue(schema, propName));
	}

	public String getPropertyValue(String schema, String propName) {
		db.resetSchema(schema);
		String sql = "select VALUE from X_PROP where NAME = '" + propName + "'";
		List<String> result = db.executeQuery(sql, "VALUE");
		logger.info("Execute query: " + sql);
		if (result.size() > 0) {
			return result.get(0);
		} else
			return null;
	}

	public void insertPropertyValue(String schema, String name,
			String namespace, String type, String value) {
		String sql = "insert into X_PROP(ID, NAME, NAMESPACE, TYPE, VALUE) values (get_sequence('X_PROP'), '"
				+ name
				+ "', '"
				+ namespace
				+ "', '"
				+ type
				+ "', '"
				+ value
				+ "')";
		db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
	}

	public void updatePropertyValue(String schema, String name, String value) {
		db.resetSchema(schema);
		String sql = "update X_PROP set VALUE = '" + value + "' where NAME = '"
				+ name + "'";
		int num = db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
		if (num < 1) {
			throw new ErrorOnDataException("No records found in DB.");
		} else
			logger.info("Update '" + name + "' value to '" + value
					+ "' successfully.");
	}

	public void deletePropertyValue(String schema, String name) {
		db.resetSchema(schema);
		String sql = "delete from  X_PROP where NAME = '" + name + "'";
		int num = db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
		if (num < 1) {
			throw new ErrorOnDataException("No records found in DB.");
		} else
			logger.info("Delete '" + name + "' property successfully.");
	}
	
	public boolean checkBoatOwnerCollectRecordExists(String schema) {
		String value = getPropertyValue(schema, "CollectMarinaBoatOwnerInfo");
		return !StringUtil.isEmpty(value);
	}

	public String getBoatOwnerCollectValue(String schema) {
		return getPropertyValue(schema, "CollectMarinaBoatOwnerInfo");
	}

	public void insertBoatOwnerCollectRecord(String schema, boolean isRequired) {
		db.resetSchema(schema);
		String sql = "insert into X_PROP(ID, NAME, NAMESPACE, TYPE, VALUE) values(get_sequence('X_PROP'), 'CollectMarinaBoatOwnerInfo', 'Contract', 'Boolean', '"
				+ String.valueOf(isRequired) + "')";
		db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
	}

	public void updateBoatOwnerCollectRequired(String schema, boolean isRequired) {
		db.resetSchema(schema);
		String sql = "update X_PROP set VALUE = '" + String.valueOf(isRequired)
				+ "' where NAME = 'CollectMarinaBoatOwnerInfo'";
		int num = db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
		if (num < 1) {
			throw new ErrorOnDataException("No records found in DB.");
		} else
			logger.info("Update 'Boat Owner' required type successfully.");
	}

	/**
	 * Update customer password
	 * 
	 * @param schema
	 * @param loginName
	 * @param password
	 */
	public void updateCustPasswordInDB(String schema, String loginName,
			String password) {
		logger.info("Update customer password, schema:" + schema
				+ ", login name:" + loginName + " and password:" + password);
		db.resetSchema(schema);
		String sql = "update c_cust set password='" + password
				+ "' where login_name = '" + loginName + "' and active_ind = 1";
		db.executeUpdate(sql);
	}

	public boolean checkBoatCaptainCollectRecordExists(String schema) {
		String value = getPropertyValue(schema, "CollectMarinaBoatCaptainName");
		return !StringUtil.isEmpty(value);
	}

	public String getBoatCaptainCollectValue(String schema) {
		return getPropertyValue(schema, "CollectMarinaBoatCaptainName");
	}

	public void insertBoatCaptainCollectRecord(String schema, boolean isRequired) {
		db.resetSchema(schema);
		String sql = "insert into X_PROP(ID, NAME, NAMESPACE, TYPE, VALUE) values (get_sequence('X_PROP'), 'CollectMarinaBoatCaptainName', 'Contract', 'Boolean', '"
				+ String.valueOf(isRequired) + "')";
		db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
	}

	public void updateBoatCaptainCollectRequired(String schema,
			boolean isRequired) {
		db.resetSchema(schema);
		String sql = "update X_PROP set VALUE = '" + String.valueOf(isRequired)
				+ "' where NAME = 'CollectMarinaBoatCaptainName'";
		int num = db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
		if (num < 1) {
			throw new ErrorOnDataException("No records found in DB.");
		} else
			logger.info("Update 'Boat Captain' required type successfully.");
	}

	public boolean checkBoatingPermitNumberCollectRecordExists(String schema,
			String locId, String slipType) {
		db.resetSchema(schema);
		String sql = "select ID from P_VESSEL_PERMIT_NUM_CNFG where LOC_ID="
				+ locId
				+ " and SLIP_TYPE_ID=(select PRD_GRP_ID from P_PRD_GRP where prd_grp_cat_id = 20 and PRD_GRP_NAME='"
				+ slipType + "')";
		List<String> results = db.executeQuery(sql, "ID");
		if (results.size() < 1) {
			return false;
		}
		return true;
	}

	public void insertBoatingPermitNumberCollectRecord(String schema,
			String locId, String slipType) {
		db.resetSchema(schema);
		String sql = "insert into P_VESSEL_PERMIT_NUM_CNFG (id, loc_id, slip_type_id, active_ind) values (get_sequence('P_VESSEL_PERMIT_NUM_CNFG'), "
				+ locId
				+ ", (select PRD_GRP_ID from P_PRD_GRP where PRD_GRP_CAT_ID = 20 and PRD_GRP_NAME='"
				+ slipType + "'), '1')";
		db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
	}

	public void updateBoatingPermitNumberCollectRequired(String schema,
			String locId, String slipType, boolean isRequired) {
		db.resetSchema(schema);
		String sql = "update P_VESSEL_PERMIT_NUM_CNFG set ACTIVE_IND = '"
				+ (isRequired ? 1 : 0)
				+ "' where LOC_ID="
				+ locId
				+ " and SLIP_TYPE_ID=(select PRD_GRP_ID from P_PRD_GRP where PRD_GRP_CAT_ID = 20 and PRD_GRP_NAME='"
				+ slipType + "')";
		int num = db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
		if (num < 1) {
			throw new ErrorOnDataException("No records found in DB.");
		} else
			logger.info("Update 'Boating Permit Number' required type successfully.");
	}

	public boolean checkBoatingPermitNumberPrefixLetterRecordExistsForTrans(
			String schema) {
		String value = getPropertyValue(schema, "1_3_BoatPermitNumPrefixLetter");
		return !StringUtil.isEmpty(value);
	}

	public boolean checkBoatingPermitNumberPrefixLetterRecordExistsForSeasonal(
			String schema) {
		String value = getPropertyValue(schema, "1_1_BoatPermitNumPrefixLetter");
		return !StringUtil.isEmpty(value);
	}

	/**
	 * 
	 * @param schema
	 * @param prefixLetter
	 *            - 'M', etc
	 */
	public void insertTransBoatingPermitNumberPrefixLetterRecord(String schema,
			String prefixLetter) {
		insertPropertyValue(schema, "1_3_BoatPermitNumPrefixLetter",
				"Contract", "String", prefixLetter); // Transient
	}

	/**
	 * 
	 * @param schema
	 * @param prefixLetter
	 *            - 'M', etc
	 */
	public void insertSeasonalBoatingPermitNumberPrefixLetterRecord(
			String schema, String prefixLetter) {
		insertPropertyValue(schema, "1_1_BoatPermitNumPrefixLetter",
				"Contract", "String", prefixLetter); // Seasonal
	}

	public void updateBoatingPermitNumberPrefixLetter(String schema,
			String prefixLetter) {
		updatePropertyValue(schema, "1_3_BoatPermitNumPrefixLetter",
				prefixLetter);
	}

	public String[] getSlipDimensions(String schema, String prdID) {
		db.resetSchema(schema);
		String sql = "select LENGTH, WIDTH, DEPTH from P_PRD where PRD_ID = "
				+ prdID;
		List<String[]> results = db.executeQuery(sql, new String[] { "LENGTH",
				"WIDTH", "DEPTH" });
		if (results.size() < 1) {
			throw new ErrorOnDataException(
					"Can't find any record by Product ID: " + prdID);
		}
		return results.get(0);
	}

	/**
	 * Get Product attribute value
	 * 
	 * @param schema
	 * @param prdCode
	 * @param attrName
	 * @return
	 * @author Lesley Wang Feb 17, 2013
	 */
	public String getProductAttributeValue(String schema, String prdCode,
			String facilityId, String attributeName) {
		db.resetSchema(schema);
		String query = "Select attr_value from P_PRD_ATTR where "
				+ "prd_id=(Select prd_id from p_prd where prd_cd='" + prdCode
				+ "' and " + " park_id=" + facilityId
				+ " and active_ind=1 and deleted_ind=0) and " + "attr_name='"
				+ attributeName + "'";
		logger.info("Query from db: " + query);
		List<String> result = db.executeQuery(query, "attr_value");
		if (result.size() < 1) {
			return StringUtil.EMPTY;
		}

		return result.get(0);
	}

	public void updateProductAttributeName(String schema, String prdId,
			String attrId, String attrName) {
		db.resetSchema(schema);
		String sql = "update p_prd_attr set attr_name = '" + attrName
				+ "' where attr_id= " + attrId + " and prd_id= " + prdId;
		logger.info("Execute query: " + sql);
		int num = db.executeUpdate(sql);
		if (num < 1)
			logger.debug("There is not attribute record found by attribute id: "
					+ attrId + " and product id: " + prdId);
		logger.info("Update attribute(ID=" + attrId + ") name successfully.");
	}

	public void updateProductAttributeValue(String schema, String prdId,
			String attrId, String attrValue) {
		db.resetSchema(schema);
		String sql = "update p_prd_attr set attr_value = '" + attrValue
				+ "' where attr_id= " + attrId + " and prd_id= " + prdId;
		logger.info("Execute query: " + sql);
		int num = db.executeUpdate(sql);
		if (num < 1)
			logger.debug("There is not attribute record found by attribute id: "
					+ attrId + " and product id: " + prdId);
		logger.info("Update attribute(ID=" + attrId + ") value successfully.");
	}

	public void updateProductAttributeValue(String schema, String prdCode,
			String facilityId, String attributeName, String attributeValue) {
		db.resetSchema(schema);
		String sql = "update P_PRD_ATTR set ATTR_VALUE = '" + attributeValue
				+ "' where "
				+ "PRD_ID = (select PRD_ID from P_PRD where PRD_CD = '"
				+ prdCode + "' and " + " PARK_ID = " + facilityId
				+ " and ACTIVE_IND = 1 and DELETED_IND = 0) and ATTR_NAME = '"
				+ attributeName + "'";
		logger.info("Execute query: " + sql);
		int num = db.executeUpdate(sql);
		if (num < 1) {
			logger.debug("There is not attribute record found by name - "
					+ attributeName);
		} else
			logger.info("Update product attribute successfully.");
	}

	public void updateMaxQtyOfCampUnit(String schema, String facilityId,
			String max_qty) {
		db.resetSchema(schema);
		String sql = "update P_CAMP_EQUIP_SET set max_qty_all_equp='" + max_qty
				+ "' where loc_id=" + facilityId;
		logger.info("Execute query: " + sql);
		int num = db.executeUpdate(sql);
		if (num < 1) {
			logger.debug("There is no given location found for - " + facilityId);
		} else
			logger.info("Update Max Camping Unit QTY successfully.");
	}

	public void deleteProductAttribute(String schema, String prdCode,
			String facilityId, String attributeName) {
		String sql = "delete from P_PRD_ATTR where "
				+ "PRD_ID = (select PRD_ID from P_PRD where PRD_CD = '"
				+ prdCode + "' and " + " PARK_ID = " + facilityId
				+ " and ACTIVE_IND = 1 and DELETED_IND = 0) and ATTR_NAME = '"
				+ attributeName + "'";
		logger.info("Execute query: " + sql);
		int num = db.executeUpdate(sql);
		if (num < 1) {
			logger.debug("There is not attribute record found by name - "
					+ attributeName);
		} else
			logger.info("Delete product attribute successfully.");
	}

	/**
	 * This method was used to setup customer class config for
	 * purchase/duplicate/reserve privilege/vehicle/consumable in License/Call
	 * Manager
	 * 
	 * @param schema
	 * @param appID
	 * @param prdGrpID
	 * @param prdSubCatID
	 * @param custClass
	 */
	public void setupCustClassConfig(String schema, String appID,
			String prdGrpID, String prdSubCatID, String custClass) {
		db.resetSchema(schema);
		String update = "insert into C_CUST_CLASS_CFG (ID, APP_ID, PRD_GRP_CAT_ID, PRD_SUBCAT_ID, CUST_CLASS_ID)"
				+ " values (GET_SEQUENCE('C_CUST_CLASS_CFG'), "
				+ appID
				+ ", "
				+ prdGrpID + ", " + prdSubCatID + ", " + custClass + ")";
		db.executeUpdate(update);
	}

	/**
	 * Get customer secure id according to identifier type name and num
	 * 
	 * @param schema
	 * @param idenTypeName
	 * @param idenNum
	 * @return
	 */
	public String getCustIdenSecureID(String schema, String idenTypeName,
			String idenNum) {
		db.resetSchema(schema);

		String sql = "select secure_id from c_identifier where type_id in (select id from C_IDENTIFIER_TYPE where name = '"
				+ idenTypeName + "')";
		List<String> secure_ids = db.executeQuery(sql, "secure_id");

		String secure_id = "";
		for (int i = 0; i < secure_ids.size(); i++) {
			secure_id = secure_ids.get(i);
			if (secure_ids.get(i).equals(idenNum)) {
				break;
			}
		}

		return secure_id;
	}

	/**
	 * Delete cust identifier according secure id
	 * 
	 * @param schema
	 * @param custIdenSecureID
	 */
	public void deleteCustIden(String schema, String custIdenSecureID) {
		db.resetSchema(schema);

		String deletedSql_1 = "delete from C_CUST_HFP_IDENTIFIER where identifier_id in (select id from c_identifier where secure_id = '"
				+ custIdenSecureID + "')";
		// String deletedSql_2 = "delete from c_identifier where secure_id = '"
		// + custIdenSecureID + "'";
		db.executeUpdate(deletedSql_1);
		// db.executeUpdate(deletedSql_2);
	}

	public void deleteCustIdenExceptGivenIdAndCustNum(String schema,
			String identTypeID, String loginName) {
		db.resetSchema(schema);
		String sql = "delete from C_CUST_HFP_IDENTIFIER where prof_id in ("
				+ "select id from C_CUST_HFPROFILE where cust_id in ("
				+ "select cc.cust_id from c_cust_hfprofile cch, c_cust cc, c_cust_phone ph where cc.cust_id=cch.cust_id and cc.cust_id=ph.cust_id and (cc.login_name='"
				+ loginName
				+ "' or ph.typ='EMAIL' and ph.val='"
				+ loginName
				+ "'))) "
				+ "and identifier_id in (select id from C_IDENTIFIER where type_id not in (1,"
				+ identTypeID + "))";
		logger.info("execute sql:" + sql);
		db.executeUpdate(sql);
	}

	public String getCustIdenID(String schema, String identTypeID,
			String loginName) {
		db.resetSchema(schema);
		String sql = "select ID from C_CUST_HFP_IDENTIFIER where prof_id in ("
				+ "select id from C_CUST_HFPROFILE where cust_id in ("
				+ "select cc.cust_id from c_cust_hfprofile cch, c_cust cc, c_cust_phone ph where cc.cust_id=cch.cust_id and cc.cust_id=ph.cust_id and (cc.login_name='"
				+ loginName
				+ "' or ph.typ='EMAIL' and ph.val='"
				+ loginName
				+ "'))) "
				+ "and identifier_id in (select id from C_IDENTIFIER where type_id="
				+ identTypeID + ")";
		logger.info("execute sql:" + sql);
		List<String> results = db.executeQuery(sql, "ID");
		if (null == results || results.size() < 1) {
			throw new ErrorOnPageException(
					"Can't find customer identifier ID when identifier type id="
							+ identTypeID + " and login name=" + loginName);
		}
		return results.get(0);
	}

	public void deleteCustIden(String schema, String identTypeID,
			String loginName) {
		db.resetSchema(schema);
		String sql = "delete from C_CUST_HFP_IDENTIFIER where prof_id in ("
				+ "select id from C_CUST_HFPROFILE where cust_id in ("
				+ "select cc.cust_id from c_cust_hfprofile cch, c_cust cc, c_cust_phone ph where cc.cust_id=cch.cust_id and cc.cust_id=ph.cust_id and (cc.login_name='"
				+ loginName
				+ "' or ph.typ='EMAIL' and ph.val='"
				+ loginName
				+ "'))) "
				+ "and identifier_id in (select id from C_IDENTIFIER where type_id="
				+ identTypeID + ")";
		logger.info("execute sql:" + sql);
		db.executeUpdate(sql);
	}

	public void deleteCustIden(String schema, String identTypeID,
			String firstName, String lastName) {
		db.resetSchema(schema);
		String sql = "delete from C_CUST_HFP_IDENTIFIER where prof_id=("
				+ "select id from C_CUST_HFPROFILE where cust_id=("
				+ "select cust_id from C_CUST where f_name='"
				+ firstName
				+ "' and l_name='"
				+ lastName
				+ "')) "
				+ "and identifier_id in (select id from C_IDENTIFIER where type_id="
				+ identTypeID + ")";
		logger.info("execute sql:" + sql);
		db.executeUpdate(sql);
	}

	/** Delete customer identifier by customer number */
	public void deleteCustIdentByCustNum(String schema, String identTypeID,
			String custNum) {
		db.resetSchema(schema);
		String sql = "delete from C_CUST_HFP_IDENTIFIER where prof_id=("
				+ "select id from C_CUST_HFPROFILE where cust_number="
				+ custNum
				+ ") "
				+ "and identifier_id in (select id from C_IDENTIFIER where type_id="
				+ identTypeID + ")";
		logger.info("execute sql:" + sql);
		db.executeUpdate(sql);
	}

	// Delete all customer's identifiers except the customer default identifier
	// which type id=1.
	public void deleteCustAllIdentExceptCustNum(String schema, String loginName) {
		db.resetSchema(schema);
		String sql = "delete from C_CUST_HFP_IDENTIFIER where prof_id in ("
				+ "select id from C_CUST_HFPROFILE where cust_id in "
				+ "(select cc.cust_id from c_cust_hfprofile cch, c_cust cc, c_cust_phone ph where cc.cust_id=cch.cust_id and cc.cust_id=ph.cust_id and (cc.login_name='"
				+ loginName
				+ "' or ph.typ='EMAIL' and ph.val='"
				+ loginName
				+ "'))) "
				+ "and identifier_id in (select id from C_IDENTIFIER where type_id!=1)";
		logger.info("execute sql:" + sql);
		db.executeUpdate(sql);
	}

	// Delete all customer's identifiers except the customer default identifier
	// which type id=1.
	public void deleteCustAllIdentExceptCustNumUsingRegxLoginName(
			String schema, String loginName) {
		db.resetSchema(schema);
		String sql = "delete from C_CUST_HFP_IDENTIFIER where prof_id in ("
				+ "select id from C_CUST_HFPROFILE where cust_id in "
				+ "(select cc.cust_id from c_cust_hfprofile cch, c_cust cc, c_cust_phone ph where cc.cust_id=cch.cust_id and cc.cust_id=ph.cust_id and (cc.login_name like '"
				+ loginName
				+ "' or ph.typ='EMAIL' and ph.val like '"
				+ loginName
				+ "'))) "
				+ "and identifier_id in (select id from C_IDENTIFIER where type_id!=1)";
		logger.info("execute sql:" + sql);
		db.executeUpdate(sql);
	}

	/**
	 * Delete all identifiers related with the customer
	 * 
	 * @param schema
	 * @param cusEmail
	 * @author Lesley Wang Apr 16, 2013
	 */
	// public void deleteCusAllIdentifiers(String schema, String cusEmail) {
	// db.resetSchema(schema);
	// String sql = "delete from C_CUST_HFP_IDENTIFIER where prof_id=("
	// + "select id from C_CUST_HFPROFILE where cust_id=("
	// + "select cust_id from C_CUST where login_name='" + cusEmail
	// + "'))";
	// logger.info("Execute sql: " + sql);
	// db.executeUpdate(sql);
	// }

	/**
	 * Delete all attributes related with the customer
	 * 
	 * @param schema
	 * @param cusEmail
	 * @author Lesley Wang Apr 16, 2013
	 */
	public void deleteCusAllAttr(String schema, String cusEmail) {
		db.resetSchema(schema);
		String sql = "delete from C_CUST_HFPROFILE_ATTR where prof_id=("
				+ "select id from C_CUST_HFPROFILE where cust_id=("
				+ "select cust_id from c_cust_phone where typ='EMAIL' and val='"
				+ cusEmail + "'))";
		logger.info("Execute sql: " + sql);
		db.executeUpdate(sql);
	}

	/**
	 * Update Customer HF profile attribute value
	 * 
	 * @param schema
	 * @param cusEmail
	 * @param attrID
	 * @param value
	 * @author Lesley Wang Apr 19, 2013
	 */
	public void updateCustHFProfileAttr(String schema, String cusEmail,
			String attrID, String value) {
		db.resetSchema(schema);
		String sql = "update C_CUST_HFPROFILE_ATTR "
				+ "set VALUE='"
				+ value
				+ "' where attr_id="
				+ attrID
				+ " and prof_id=("
				+ "select id from C_CUST_HFPROFILE where cust_id=("
				+ "select cust_id from c_cust_phone where typ='EMAIL' and val='"
				+ cusEmail + "'))";
		logger.info("Execute sql: " + sql);
		db.executeUpdate(sql);
	}

	public void deleteCusAllAddress(String schema, String cusEmail) {
		db.resetSchema(schema);
		String sql = "delete from C_CUST_HFP_ADDRESS where prof_id=("
				+ "select id from C_CUST_HFPROFILE where cust_id=("
				+ "select cust_id from  c_cust_phone where typ='EMAIL' and val='"
				+ cusEmail + "'))";
		logger.info("Execute sql: " + sql);
		db.executeUpdate(sql);
	}

	public void deleteCusHFProfile(String schema, String cusEmail) {
		db.resetSchema(schema);
		String sql = "delete from C_CUST_HFPROFILE where cust_id=("
				+ "select cust_id from  c_cust_phone where typ='EMAIL' and val='"
				+ cusEmail + "')";
		logger.info("Execute sql: " + sql);
		db.executeUpdate(sql);
	}

	public void updateCustLastName(String schema, String cusEmail, String value) {
		db.resetSchema(schema);
		String sql = "update C_CUST set L_NAME='"
				+ value
				+ "' where cust_id=(select cust_id from c_cust_phone where typ='EMAIL' and val='"
				+ cusEmail + "')";
		logger.info("Execute sql: " + sql);
		db.executeUpdate(sql);
	}

	/**
	 * Update Customer DOB in C_CUST_HFPROFILE by customer login name
	 * 
	 * @param schema
	 * @param loginName
	 * @param newDOB
	 * @author Lesley Wang May 10, 2013
	 */
	public void updateCustDOBInProfile(String schema, String cusEmail,
			String newDOB) {
		db.resetSchema(schema);
		String sql = "update C_CUST_HFPROFILE set BIRTHDAY='"
				+ newDOB
				+ "' where CUST_ID=(select CUST_ID from c_cust_phone where typ='EMAIL' and val='"
				+ cusEmail + "')";
		logger.info("Execute sql: " + sql);
		db.executeUpdate(sql);
	}

	public void udpateCustDOB(String schema, String cusEmail, String newDOB) {
		db.resetSchema(schema);
		String sql = "update C_CUST set BIRTHDATE='"
				+ newDOB
				+ "' where CUST_ID=(select CUST_ID from c_cust_phone where typ='EMAIL' and val='"
				+ cusEmail + "')";
		logger.info("Execute sql: " + sql);
		db.executeUpdate(sql);
	}

	/**
	 * Get customer identification type id acconding to identification name
	 * 
	 * @param schema
	 * @param idenTypeName
	 * @return
	 */
	public String getCustIdenTypeID(String schema, String idenTypeName) {
		db.resetSchema(schema);

		String sql = "select ID from C_IDENTIFIER_TYPE where name = '"
				+ idenTypeName + "'";
		List<String> secure_ids = db.executeQuery(sql, "ID");
		return secure_ids.get(0);
	}

	/**
	 * Get identification type NAME, VERIFIABLE_IND, STATE_REQ_IND,
	 * COUNTRY_REQ_IND, MAX_VALUE via identification id
	 * 
	 * @param schema
	 * @param idenTypeID
	 * @return
	 */
	public String[] getIdenTypeInfoViaIdenTypeID(String schema,
			String idenTypeID) {
		db.resetSchema(schema);
		String sql = "select NAME, VERIFIABLE_IND, STATE_REQ_IND, COUNTRY_REQ_IND, MAX_VALUE from C_IDENTIFIER_TYPE where ID = '"
				+ idenTypeID + "'";
		return db.executeQuery(
				sql,
				new String[] { "NAME", "VERIFIABLE_IND", "STATE_REQ_IND",
						"COUNTRY_REQ_IND", "MAX_VALUE" }).get(0);
	}

	/** Get Identifier Type Short Name */
	public String getIdenTypeShortName(String schema, String idenTypeID) {
		db.resetSchema(schema);
		String sql = "select SHORT_NAME from C_IDENTIFIER_TYPE where ID = "
				+ idenTypeID;
		logger.info("Execute sql: " + sql);
		return db.executeQuery(sql, "SHORT_NAME").get(0);
	}

	/**
	 * Get identification type name via id, state ind and country ind
	 * 
	 * @param schema
	 * @param idenTypeID
	 * @param hasStateInd
	 * @param hasCountryInd
	 * @return
	 */
	public String getIdenTypeName(String schema, String idenTypeID,
			boolean hasStateInd, boolean hasCountryInd, String maxValue) {
		db.resetSchema(schema);
		String sql = "select NAME from C_IDENTIFIER_TYPE where ID = "
				+ idenTypeID
				+ " and state_req_ind = "
				+ (hasStateInd ? "1" : "0")
				+ " and country_req_ind = "
				+ (hasCountryInd ? "1" : "0")
				+ (StringUtil.isEmpty(maxValue) ? "" : " and max_value = '"
						+ maxValue + "'");
		return db.executeQuery(sql, "Name").get(0);
	}

	public String getIdenTypeName(String schema, String idenTypeID,
			boolean hasStateInd, boolean hasCountryInd) {
		return this.getIdenTypeName(schema, idenTypeID, hasStateInd,
				hasCountryInd, StringUtil.EMPTY);
	}

	public String getIdenTypeMaxValue(String schema, String idenTypeID) {
		db.resetSchema(schema);
		String sql = "select MAX_VALUE from c_identifier_type where id = "
				+ idenTypeID;
		List<String> results = db.executeQuery(sql, "MAX_VALUE");
		if (results.size() < 1) {
			throw new ErrorOnPageException("No result is found.");
		}
		return results.get(0);
	}

	/**
	 * Select countries other than option 'Unknown', 'Canada' and 'United
	 * States' via identifier type id
	 * 
	 * @param commonSchema
	 * @param contractSchema
	 * @param idenTypeID
	 * @return
	 */
	public List<String> getIdenCountriesViaIdenTypeID(String commonSchema,
			String contractSchema, String idenTypeID) {
		db.resetSchema(commonSchema);
		String sql = "select name from d_ref_country where name<> 'Unknown' and name<> 'Canada' and name<> 'United States' and id in (select country_id from "
				+ contractSchema
				+ ".c_identifier_type_country where type_id="
				+ idenTypeID + ") order by name";
		logger.info("SQL:" + sql);
		return db.executeQuery(sql, "Name");
	}

	/**
	 * Get all countries in common schema, the first option is "Canada", the
	 * second option is "United States", the others
	 * 
	 * @param conmmonSchema
	 * @return
	 */
	public List<String> getCountries(String conmmonSchema) {
		db.resetSchema(conmmonSchema);
		String sql1 = "select name from d_ref_country where dscr = 'United States of America'";
		String sql2 = "select name from d_ref_country where dscr = 'Canada'";
		String sql3 = "select name from d_ref_country where dscr<> 'Unknown' and dscr<> 'Canada' and dscr<> 'United States of America' order by dscr asc";

		List<String> results = new ArrayList<String>();
		results.addAll(db.executeQuery(sql1, "Name"));
		results.addAll(db.executeQuery(sql2, "Name"));
		results.addAll(db.executeQuery(sql3, "Name"));

		return results;
	}

	/**
	 * Select states other than option 'Unknown'
	 * 
	 * @param commonSchema
	 * @param contractSchema
	 * @param idenTypeID
	 * @param contractName
	 * @return
	 */
	public List<String> getIdenContractRelatedStates(String commonSchema,
			String contractSchema, String idenTypeID, String contractName) {
		db.resetSchema(commonSchema);
		String sql = "select * from d_ref_state_provnc where "
				+ "id in (select state_id from "
				+ contractSchema
				+ ".c_identifier_type_state where type_id="
				+ idenTypeID
				+ ") "
				+ (StringUtil.notEmpty(contractName) ? "and country_id in (select id from d_ref_country where name = '"
						+ contractName
						+ "' and id in (select country_id from "
						+ contractSchema
						+ ".c_identifier_type_country where type_id="
						+ idenTypeID + ")) "
						: "") + "order by name";
		logger.info("SQL:" + sql);
		return db.executeQuery(sql, "Name");
	}

	public List<String> getContractRelatedStates(String commonSchema,
			String contractName) {
		db.resetSchema(commonSchema);
		String sql = "select name from d_ref_state_provnc where country_id in (select id from d_ref_country where name = '"
				+ contractName + "') order by name asc";
		logger.info("SQL:" + sql);
		return db.executeQuery(sql, "Name");
	}

	/**
	 * Remove "Boat" because it is doesn't make sense
	 * 
	 * @param commonSchema
	 * @param contract
	 * @return
	 */
	public List<String> getVehicleMakes(String schema) {
		db.resetSchema(schema);
		String sql = "select name from d_ref_system_code where cd like 'VehicleMake%' and name not in ('Boat', 'Motorcycle') and active_ind=1 order by upper(name) asc";
		logger.info("SQL:" + sql);
		return db.executeQuery(sql, "Name");
	}

	public List<String> getVehicleColors(String schema) {
		db.resetSchema(schema);
		String sql = "select name from d_ref_system_code where cd like 'VehicleColor%' and active_ind=1 order by name asc";
		logger.info("SQL:" + sql);
		return db.executeQuery(sql, "Name");
	}

	/**
	 * This method used to get notification/confirmation email from mail box
	 * 
	 * @param host
	 * @param username
	 * @param password
	 * @param emailSubject
	 * @param date
	 * @param timeDiff
	 *            if it is 0, will search email by subject only,else it should
	 *            be the time difference between mail server and script running
	 *            machine,will search email subject and send date
	 * @param delete
	 * @return
	 */
	public Properties[] getEmailFromMailBox(String mailFolder, String host,
			String username, String password, String emailSubject, Date date,
			int timeDiff, boolean delete) {
		logger.info("Get email from mail box");

		Email e = new Email();
		e.connect(host, username, password, "pop3");
		RegularExpression subjectPattern = new RegularExpression(emailSubject,
				false);
		int time = CHECK_NOTIFICATION_IN_MAILBOX_THRESHOLD;
		Properties[] pros = null;
		if (timeDiff != 0) {
			pros = e.searchEmail(mailFolder, subjectPattern, date, timeDiff,
					time, delete);
		} else {
			pros = e.searchEmail(mailFolder, subjectPattern, time, delete);
		}
		e.disconnect();

		return pros;
	}

	public Properties[] getEmailFromMailBox(String host, String username,
			String password, String emailSubject, Date date, int timeDiff,
			boolean delete) {

		String mailFolder = TestProperty
				.getProperty("mail.notification.folder");

		logger.info("Get email from mail box from folder:" + mailFolder);
		return this.getEmailFromMailBox(mailFolder, host, username, password,
				emailSubject, date, timeDiff, delete);
	}

	public Properties[] getEmailFromMailBox(String host, String username,
			String password, String emailSubject, int timeDiff) {

		String mailFolder = TestProperty
				.getProperty("mail.notification.folder");

		logger.info("Get email from mail box from folder:" + mailFolder);
		return this.getEmailFromMailBox(mailFolder, host, username, password,
				emailSubject, DateFunctions.getCurrentDate(), timeDiff, true);
	}

	/**
	 * Get quota type id by loc id and quota type code
	 * 
	 * @param schema
	 * @param locID
	 * @param typeCode
	 * @return
	 * @author Lesley Wang Mar 19, 2013
	 */
	public String getQuotaTypeID(String schema, String locID, String typeCode) {
		db.resetSchema(schema);
		String query = "Select ID from P_QUOTA_TYPE where " + "LOC_ID=" + locID
				+ " AND CODE='" + typeCode + "'";
		logger.info("Query from db: " + query);
		String id = db.executeQuery(query, "ID", 0);
		return id;
	}

	/**
	 * Change quota inventory status
	 * 
	 * @param schema
	 * @param invID
	 * @param colNum
	 * @param value
	 * @author Lesley Wang Mar 19, 2013
	 */
	public void changeQuotaInventoryStatus(String schema, String invID,
			String colNum, String value) {
		db.resetSchema(schema);
		String query = "Update I_PERMIT_INV set " + colNum + "='" + value
				+ "' where ID=" + invID;
		logger.info("Update from db: " + query);
		db.executeUpdate(query);
	}

	public void activateOrDeactivateQuotaInventory(String schema, String invID,
			boolean isActive) {
		String value = isActive ? "1" : "0";
		this.changeQuotaInventoryStatus(schema, invID, "ACTIVE_IND", value);
	}

	public void closeOrOpenQuotaInventory(String schema, String invID,
			boolean isClosed) {
		String value = isClosed ? "1" : "0";
		this.changeQuotaInventoryStatus(schema, invID, "CLOSED_IND", value);
	}

	public void changePermitInventoryPoolQty(String schema, String invID,
			String salesCat, String alloType, String qty) {
		db.resetSchema(schema);
		String query = "Update I_PERMIT_INV_POOL set QUANTITY='" + qty + "' "
				+ "where PERMIT_INV_ID=" + invID;
		if (!StringUtil.isEmpty(salesCat)) {
			query += " and SALES_CAT=" + salesCat;
		}
		if (!StringUtil.isEmpty(alloType)) {
			query += " and ALLOCATION_TYPE=" + alloType;
		}
		logger.info("Update from db: " + query);
		db.executeUpdate(query);
	}

	/** Get permit inventiry qunatity and capacity */
	public String[] getPermitInvQtyAndCapacity(String schema, String invID,
			String salesCat, String alloType) {
		db.resetSchema(schema);
		String query = "Select QUANTITY, CAPACITY from I_PERMIT_INV_POOL "
				+ "where I_PERMIT_INV_POOL.PERMIT_INV_ID=" + invID;
		if (!StringUtil.isEmpty(salesCat)) {
			query += " and SALES_CAT=" + salesCat;
		}
		if (!StringUtil.isEmpty(alloType)) {
			query += " and ALLOCATION_TYPE=" + alloType;
		}
		logger.info("Execute from db: " + query);
		return db.executeQuery(query, new String[] { "QUANTITY", "CAPACITY" },
				0);
	}

	public void deleteNotesAlertsById(String schema, String id) {
		logger.info("Delete Notes/Alerts(ID=" + id + ") from DB.");
		db.resetSchema(schema);
		String sql = "update C_MSG set DELETED_IND=1 where ACTIVE_IND = 1 and ID = "
				+ id;
		db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
	}

	public void deleteNotesAlertsByText(String schema, String text) {
		logger.info("Delete Notes/Alerts(Text=" + text + ") from DB.");
		db.resetSchema(schema);
		String sql = "update C_MSG set DELETED_IND=1 where ACTIVE_IND=1 and MESSAGE = '"
				+ text + "'";
		db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
	}

	/**
	 * Get booking ID from table I_INV_QTY_USED
	 */
	public String getBookingID(String schema, String slipID, String qty,
			String startDate) {

		// if there are several customers book this slip at the same time, using
		// length to distinguish different order.
		String query = "SELECT BOOKING_ID FROM I_INV_QTY_USED WHERE SITE_ID = "
				+ slipID + " AND QTY =" + qty + " AND start_date = TO_DATE('"
				+ startDate + "', 'mm-dd-yyyy')";
		db.resetSchema(schema);
		List<String> resultList = db.executeQuery(query, "BOOKING_ID");
		logger.info("Execute query: " + query);
		if (null == resultList || resultList.size() < 1) {
			throw new ErrorOnPageException("Booking ID for slip(" + slipID
					+ ") doesn't exist.");
		}
		return resultList.get(0);
	}

	public String getParentDockID(String schema, String dockName) {
		db.resetSchema(schema);
		String sql = "select Parent_ID from D_LOC where NAME ='" + dockName
				+ "' and LEVEL_NUM = 50";
		List<String> ids = db.executeQuery(sql, "Parent_ID");
		logger.info("Execute query: " + sql);
		if (ids.size() < 1) {
			throw new ErrorOnDataException(
					"Cannot find any Dock records by given dock name:"
							+ dockName);
		}
		return ids.get(0);
	}

	public List<String> getParentDocksID(String schema, String docksName) {
		db.resetSchema(schema);
		String sql = "select Parent_ID from D_LOC where NAME in(" + docksName
				+ ") and LEVEL_NUM = 50";
		List<String> ids = db.executeQuery(sql, "Parent_ID");
		logger.info("Execute query: " + sql);
		if (ids.size() < 1) {
			throw new ErrorOnDataException(
					"Cannot find any Dock records by given dock name:"
							+ docksName);
		}
		return ids;
	}

	public List<String> getDocksID(String schema, String docksName) {
		db.resetSchema(schema);
		String sql = "select ID from D_LOC where NAME in(" + docksName
				+ ") and LEVEL_NUM = 50";
		List<String> ids = db.executeQuery(sql, "ID");
		logger.info("Execute query: " + sql);
		if (ids.size() < 1) {
			throw new ErrorOnDataException("Cannot find any Dock records.");
		}
		return ids;

	}

	public String getDockID(String schema, String dockName) {
		db.resetSchema(schema);
		String sql = "select ID from D_LOC where NAME ='" + dockName
				+ "' and LEVEL_NUM = 50";
		List<String> ids = db.executeQuery(sql, "ID");
		logger.info("Execute query: " + sql);
		if (ids.size() < 1) {
			throw new ErrorOnDataException("Cannot find any Dock records.");
		}
		return ids.get(0);
	}

	/**
	 * Get all identifier types related with customer class
	 */
	public List<String> getAllIdenTypesFromDB(String schema, String custClassID) {
		db.resetSchema(schema);
		String sql = "select NAME from C_IDENTIFIER_TYPE "
				+ "where ID in (select ID_TYPE_ID from C_CUST_CLASS_ID_TYPE where CUST_CLASS_ID="
				+ custClassID + ") order by sales_priority";
		logger.info("sql:" + sql);
		return db.executeQuery(sql, "NAME");
	}

	/**
	 * Get all identifier types from DB related with Individual customer class
	 * 
	 * @param schema
	 * @return
	 */
	public List<String> getAllIdenTypesFromDB(String schema) {
		// db.resetSchema(schema);
		// String sql =
		// "select NAME from C_IDENTIFIER_TYPE order by sales_priority";
		// logger.info("sql:" + sql);
		// return db.executeQuery(sql, "NAME");
		return this.getAllIdenTypesFromDB(schema, INDIVIDUAL_CUST_CLASS_ID);
	}

	public List<String> getAllIdenTypeIDsFromDB(String schema) {
		db.resetSchema(schema);
		String sql = "select id from C_IDENTIFIER_TYPE order by sales_priority";
		logger.info("sql:" + sql);
		return db.executeQuery(sql, "ID");
	}

	public List<Boolean> getAllStatesReqIndFromDB(String schema,
			List<String> idenID) {
		db.resetSchema(schema);
		List<Boolean> values = new ArrayList<Boolean>();

		for (int i = 0; i < idenID.size(); i++) {
			String sql = "select STATE_REQ_IND from C_IDENTIFIER_TYPE where id = "
					+ idenID.get(i);
			logger.info("sql:" + sql);
			if (db.executeQuery(sql, "STATE_REQ_IND").get(0).equals("1")) {
				values.add(true);
			} else
				values.add(false);
		}
		return values;
	}

	public List<Boolean> getAllCountriesReqIndFromDB(String schema,
			List<String> idenID) {
		db.resetSchema(schema);
		List<Boolean> values = new ArrayList<Boolean>();

		for (int i = 0; i < idenID.size(); i++) {
			String sql = "select country_req_ind from C_IDENTIFIER_TYPE where id = "
					+ idenID.get(i);
			logger.info("sql:" + sql);
			if (db.executeQuery(sql, "country_req_ind").get(0).equals("1")) {
				values.add(true);
			} else
				values.add(false);
		}
		return values;
	}

	public OpeningFloat getFinSessionOpeningFloat(String schema,
			String finSessID, String openingFloatAmount) {
		return getFinSessionOpeningFloat(schema, finSessID, null,
				openingFloatAmount);
	}

	public OpeningFloat getFinSessionOpeningFloat(String schema,
			String finSessID, String reversedOpeningFloatID,
			String openingFloatAmount) {
		db.resetSchema(schema);
		String sql = "select * from F_FLOAT where FIN_SESS_ID=" + finSessID
				+ " and AMOUNT='" + openingFloatAmount + "'";
		if (!StringUtil.isEmpty(reversedOpeningFloatID)) {
			sql += " and REVERSED_FLOAT_ID=" + reversedOpeningFloatID;
		}

		logger.info("Execute query: " + sql);
		List<HashMap<String, String>> list = db.executeQuery(sql);
		if (list.size() < 1) {
			throw new ErrorOnDataException(
					"Cannot find any Opening Float record by Fin Session ID="
							+ finSessID);
		}

		HashMap<String, String> map = list.get(0);
		OpeningFloat of = new FinSession().new OpeningFloat();
		of.setId(map.get("ID"));
		of.setCreateDate(map.get("CRT_DATE"));
		of.setAmount(map.get("AMOUNT"));
		of.setStatus(map.get("STATUS"));
		of.setUserID(map.get("USER_ID"));
		of.setPinUserID(map.get("PIN_USER_ID"));
		of.setLocationID(map.get("LOC_ID"));
		of.setCurrencyID(map.get("CURR_ID"));
		of.setFinSessionID(map.get("FIN_SESS_ID"));
		of.setPaymentTypeID(map.get("PMT_TYPE_ID"));
		of.setPaymentGroupID(map.get("PMT_GROUP"));
		of.setReversedFloatID(map.get("REVERSED_FLOAT_ID"));
		of.setFloatAjustmentID(map.get("FLOAT_ADJ_ID"));

		return of;
	}

	public FloatAdjustment getFinSessionFloatAdjustment(String schema,
			String finSessID) {
		db.resetSchema(schema);
		String sql = "select * from F_FLOAT ff, F_FLOAT_ADJ ffa where ff.FIN_SESS_ID="
				+ finSessID + " and ff.ID=ffa.FLOAT_ID";
		logger.info("Execute query: " + sql);
		List<HashMap<String, String>> list = db.executeQuery(sql);
		if (list.size() < 1) {
			throw new ErrorOnDataException(
					"Cannot find any Float Adjustment record by Fin Session ID="
							+ finSessID);
		}
		HashMap<String, String> map = list.get(0);
		FloatAdjustment fa = new FinSession().new FloatAdjustment();
		fa.setId(map.get("ID"));
		fa.setAmount(Double.parseDouble(map.get("AMOUNT")));
		fa.setFloatID(map.get("FLOAT_ID"));
		fa.setUserID(map.get("USER_ID"));
		fa.setPinUserID(map.get("PIN_USER_ID"));
		fa.setLocID(map.get("LOC_ID"));
		fa.setCreateDate(map.get("CRT_DATE"));

		return fa;
	}

	/**
	 * This method was used to get slip avaliable inventory start/end dates from
	 * DB
	 * 
	 * @param schema
	 * @param prdID
	 * @param parentID
	 * @return the first result if multiply records were found
	 */
	public String[] getSlipAvailInvDates(String schema, String prdID,
			String parentID) {
		logger.info("Get slip avaliable inventory dates for prd_id=" + prdID
				+ " and parent_prd_id=" + parentID);

		db.resetSchema(schema);
		String[] colNames = new String[] { "start_time", "end_time" };
		String query = "select * from I_INV_AVAIL where prd_id=" + prdID
				+ " and ACTIVE_IND=1 and DELETED_IND=0";

		if (!StringUtil.isEmpty(parentID))
			query = query + " and parent_prd_id=" + parentID;

		List<String[]> dates = db.executeQuery(query, colNames);

		if (dates == null || dates.size() < 1)
			throw new ItemNotFoundException(
					"Could not get slip avaliable inventory dates for prd_id="
							+ prdID + " and parent_prd_id=" + parentID);

		return dates.get(0);
	}

	/**
	 * Verify no avaliable slip inventory for slip from DB
	 * 
	 * @param schema
	 * @param prdID
	 * @param parentID
	 */
	public void verifyNoAvailInvForSlip(String schema, String prdID,
			String parentID) {
		logger.info("Verify No avaliable inventory for slip(" + prdID
				+ ") from DB.");

		db.resetSchema(schema);
		String query = "select * from I_INV_AVAIL where prd_id=" + prdID;
		if (StringUtil.notEmpty(parentID))
			query += " and parent_prd_id=" + parentID;

		List<String> result = db.executeQuery(query, "INV_AVAIL_ID");
		if (result == null || result.size() < 1)
			logger.info("---Verify No avaliable inventory for slip(" + prdID
					+ ") from DB successfully.");
		else
			throw new TestCaseFailedException(
					"Failed to verify No avaliable inventory for slip(" + prdID
							+ ") from DB. Check INV_AVAIL_ID=" + result);
	}

	// /**
	// * Verify avaliable slip invenroty start/end date from DB
	// * @param schema
	// * @param prdID
	// * @param parentID
	// * @param startDate
	// * @param endDate
	// */
	// public void verifyAvailInvDatesForSlip(String schema, String prdID,
	// String parentID, String startDate, String endDate) {
	// logger.info("Verify generated inventory for slip("+prdID+") from DB.");
	//
	// String[] dates = getSlipAvailInvDates(schema, prdID, parentID);
	// if(dates[0].equals(startDate) && dates[1].equals(endDate)) {
	// logger.info("---Verify generated inventory for slip("+prdID+") from DB successfully.");
	// } else {
	// throw new
	// ItemNotFoundException("Failed to verify generated inventory for slip("+prdID+") from DB. Expected startDate="+startDate+",endDate="+endDate+", and Actual startDate="+dates[0]+",endDate="+dates[1]);
	// }
	// }

	/**
	 * Verify avalible slip inventory start/end date from DB
	 * 
	 * @param schema
	 * @param prdID
	 * @param parentID
	 * @param startDate
	 * @param endDate
	 */
	public void verifyAvailInvForSlipWithDates(String schema, String prdID,
			String parentID, String startDate, String endDate) {
		logger.info("Get slip avaliable inventory dates for prd_id=" + prdID
				+ " and parent_prd_id=" + parentID);

		db.resetSchema(schema);
		String[] colNames = new String[] { "start_time", "end_time" };
		String query = "select * from I_INV_AVAIL where prd_id=" + prdID
				+ " and ACTIVE_IND=1 and DELETED_IND=0";

		if (!StringUtil.isEmpty(parentID))
			query = query + " and parent_prd_id=" + parentID;

		List<String[]> dates = db.executeQuery(query, colNames);

		if (dates == null || dates.size() < 1)
			throw new ItemNotFoundException(
					"Could not get slip avaliable inventory dates for prd_id="
							+ prdID + " and parent_prd_id=" + parentID);

		boolean found = false;
		for (int i = 0; i < dates.size(); i++) {
			String sDate = dates.get(i)[0].split(" ")[0];// handle with
															// Start_Date/End_Date
															// where date format
															// in DB
			String eDate = dates.get(i)[1].split(" ")[0];
			if (DateFunctions.compareDates(sDate, startDate) == 0
					&& DateFunctions.compareDates(eDate, endDate) == 0) {
				found = true;
				break;
			}
		}

		if (!found)
			throw new ItemNotFoundException(
					"Failed to verify generated inventory for slip(" + prdID
							+ ") from DB. Expected startDate=" + startDate
							+ ",endDate=" + endDate);

		logger.info("---Verify generated inventory for slip(" + prdID
				+ ") startDate=" + startDate + ",endDate=" + endDate
				+ " from DB successfully.");

	}

	/** Change order date in the table O_ORDER */
	public void changeOrdDate(String schema, String ordNum, String newOrdDate) {
		db.resetSchema(schema);
		newOrdDate = DateFunctions.formatDate(newOrdDate, "dd-MMM-yy");
		String sql = "update O_ORDER set ORD_DATE='"
				+ newOrdDate
				+ "', "
				+ "CONCURRENCY_VERSION_NUM=("
				+ "select CONCURRENCY_VERSION_NUM + 1 from O_ORDER where ord_num='"
				+ ordNum + "') " + "where ord_num='" + ordNum + "'";
		logger.info("execute sql: " + sql);
		db.executeUpdate(sql);
	}

	public int getPermitMinimumAdultsNum(String schema, String facilityIdOrName) {
		db.resetSchema(schema);
		logger.info("Get permit Minimum Adults Restriction number.");
		String sql = "select VALUE from D_LOC_ATTR_VALUE where LOC_ID = "
				+ (facilityIdOrName.matches("\\d+") ? facilityIdOrName
						: "(select ID from D_LOC where NAME = '"
								+ facilityIdOrName + "')")
				+ " and ATTR_ID = (select ATTR_ID from D_ATTR where ATTR_NAME = 'MinimumAdultsRule')";
		logger.info("Execute query: " + sql);
		List<String> results = db.executeQuery(sql, "VALUE");
		int num = 0;
		if (results.size() < 1) {
			num = 0;
		} else {
			num = Integer.parseInt(results.get(0));
		}

		return num;
	}

	/** Get harvest numbers by privilege number */
	public List<String> getHarvestNumsByPriNum(String schema, String priNum) {
		db.resetSchema(schema);
		String sql = "select HARVEST_NUM from O_HARVEST_RECORD where PRIV_INST_ID = "
				+ priNum + " order by HARVEST_NUM";
		logger.info("Execute query: " + sql);
		List<String> results = db.executeQuery(sql, "HARVEST_NUM");
		logger.info("The harvest numbers for the privilege num=" + priNum
				+ " are: " + results.toString());
		return results;
	}

	/**
	 * Update the display of the Facility External links.
	 * 
	 * @param schema
	 * @param stateCode
	 * @param agencyID
	 * @param parkID
	 * @param deleteValue
	 *            : 1 - not display; 0 - display
	 * @author Lesley Wang
	 * @Date Jun 25, 2013
	 */
	public void updateParkLinkDeleteInd(String schema, String stateCode,
			String agencyID, String parkID, String deleteValue) {
		db.resetSchema(schema);
		String sql = "update D_PARKLINK set delete_ind=" + deleteValue
				+ " where ";
		if (StringUtil.notEmpty(stateCode)) {
			sql += "state_code='" + stateCode + "' ";
		}
		if (StringUtil.notEmpty(agencyID)) {
			sql += "agency_id='" + agencyID + "' ";
		}
		if (StringUtil.notEmpty(parkID)) {
			sql += "park_id='" + parkID + "' ";
		}
		logger.info("Execute query: " + sql);
		db.executeUpdate(sql);
	}

	/**
	 * Get transaction type by reservation number and verify
	 * 
	 * @param schema
	 * @param expectType
	 * @param resNum
	 */
	public void verifyTranType(String schema, String expectType, String resNum) {
		db.resetSchema(schema);

		logger.info("Query transaction type from DB.");
		String query = "select trans_typ_id from o_ord_item_trans where ord_id = (select id from o_order where ord_num = '"
				+ resNum + "')";

		logger.info("Execute query:" + query);
		boolean result = false;
		List<String> transTypIDs = db.executeQuery(query, "trans_typ_id");
		if (null == transTypIDs || transTypIDs.size() < 1) {
			throw new ErrorOnPageException();
		} else {
			for (String typID : transTypIDs) {
				if (typID.equals(expectType)) {
					result = true;
					break;
				}
			}
			if (!result) {
				throw new ErrorOnPageException(
						"Should exist one record which trans_typ_id is "
								+ expectType);
			}
		}
	}

	public String[] getOrderItemPenalty(String schema, String ordItemFeeId) {
		sycDB();
		db.resetSchema(schema);
		String[] colNames = { "FEE_PENALTY_SCHD_ID", "FEE_PENALTY_RATE",
				"PENALTY_AMOUNT", "FEE_SCHD_ID" };
		String query = "select * from O_ORD_ITEM_FEE_PENALTY where ord_item_fee_id ="
				+ ordItemFeeId;

		logger.debug("Run query: " + query);

		String[] feeInfo = db.executeQuery(query, colNames, 0);

		return feeInfo;
	}

	public String[] getOrderItemFee(String schema, String ordNum,
			String transactionTypeId, String feeTypeId) {
		db.resetSchema(schema);
		String columnNames[] = new String[] { "FEE_AMT", "FEE_SCHD_ID",
				"REV_LOC_ID", "PRD_ID" };
		String sql = "select ooif.* from O_ORD_ITEM_FEE ooif, O_ORD_ITEM_TRANS ooit, O_ORDER oo where ooit.ORD_ID = oo.ID "
				+ " and ooif.ORD_ITEM_TRAN_ID = ooit.ID and ooit.TRANS_TYP_ID = "
				+ transactionTypeId
				+ " and ooif.FEE_TYPE_ID = "
				+ feeTypeId
				+ " and oo.ORD_NUM = '" + ordNum + "'";
		logger.info("Execute query: " + sql);

		String feeInfo[] = db.executeQuery(sql, columnNames, 0);

		return feeInfo;
	}

	public String[] getOrderItemDiscount(String schema, String ordItemFeeId) {
		sycDB();
		db.resetSchema(schema);
		String[] colNames = { "	DISCNT_SCHD_ID", "AMOUNT", "RATE" };
		String query = "select * from O_ORD_ITEM_DISCOUNT where ord_item_fee_id ="
				+ ordItemFeeId;

		logger.debug("Run query: " + query);

		String[] feeInfo = db.executeQuery(query, colNames, 0);

		return feeInfo;
	}

	public String[] getOrderItemTax(String schema, String taxFeeId) {
		sycDB();
		db.resetSchema(schema);
		String[] colNames = { "TAX_SCHD_ID", "TAX_AMOUNT", "RATE_APPLIED" };
		String query = "select * from o_ord_item_tax where fee_id =" + taxFeeId;

		logger.debug("Run query: " + query);

		String[] feeInfo = db.executeQuery(query, colNames, 0);

		return feeInfo;
	}

	public boolean checkOrderItemStatus(String schema, String ordNum,
			int expStatusId) {
		sycDB();
		db.resetSchema(schema);
		String[] colNames = { "STATUS_ID" };
		String query = "select ooi.STATUS_ID as status_id from O_ORDER oo, O_ORD_ITEM ooi "
				+ "where oo.ID = ooi.ORD_ID and oo.ORD_NUM = '"
				+ ordNum
				+ "' and ooi.occ_site_id is not null";

		logger.debug("Run query: " + query);

		String statusId = db.executeQuery(query, colNames, 0)[0];
		if (!statusId.equalsIgnoreCase(String.valueOf(expStatusId))) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * get Order ID from DB by order number.
	 * 
	 * @param orderNum
	 * @param schema
	 */
	public String getOrderID(String orderNum, String schema) {
		logger.info("Get Order ID from Table: O_order.");
		db.resetSchema(schema);
		String query = "Select ID From O_order Where ord_num ='" + orderNum
				+ "'";
		logger.info("Execute SQL:" + query);
		List<String> list = db.executeQuery(query, "ID");
		if (null == list || list.size() < 0) {
			throw new ItemNotFoundException(
					"Can't get order ID by given order number:" + orderNum);
		}
		String orderId = list.get(0).trim();
		logger.info("Order ID is:" + orderId);
		return orderId;
	}

	/** Get File Header configuration for Import File in LM **/
	public String getFileHeaderCFG(String schema, String fileType, String colNm) {
		db.resetSchema(schema);
		String sql = "Select * from D_FILE_HEADER_CFG where FILE_IMPORT_TYPE='"
				+ fileType + "'";
		logger.info("Execute SQL:" + sql);
		String result = db.executeQuery(sql, colNm, 0);
		logger.info(fileType + " file's " + colNm + " value is " + result);
		return result;
	}

	/** Check if file header is required when import file */
	public boolean isFileHeaderRequired(String schema, String fileType) {
		String headerInd = this
				.getFileHeaderCFG(schema, fileType, "HEADER_IND");
		return headerInd.equals("1");
	}

	/** Get File header colnum names */
	public List<String> getFileHeaderColNms(String schema, String fileType) {
		db.resetSchema(schema);
		String sql = "Select * from D_FILE_HEADER_COL_MAPPING where FILE_HEADER_CFG_ID=("
				+ "Select ID from D_FILE_HEADER_CFG where FILE_IMPORT_TYPE='"
				+ fileType + "') " + "order by COL_ORDER";
		logger.info("Execute SQL:" + sql);
		List<String> result = db.executeQuery(sql, "COL_LABEL");
		return result;
	}

	/** Get point type value by point type name and column name */
	private String getPointTypeValue(String schema, String pointTypeNm,
			String colNm) {
		db.resetSchema(schema);
		String sql = "Select * from D_POINT_TYPE where NAME='" + pointTypeNm
				+ "'";
		logger.info("Execute SQL:" + sql);
		String value = db.executeQuery(sql, colNm, 0);
		logger.info(pointTypeNm + "'s " + colNm + " is " + value);
		return value;
	}

	/** Get point type code by point type name */
	public String getPointTypeCode(String schema, String pointTypeNm) {
		return getPointTypeValue(schema, pointTypeNm, "CODE");
	}

	/** Check if use point text indicator */
	public boolean isUsePointTextIndicator(String schema) {
		db.resetSchema(schema);
		String sql = "Select value from x_prop where name='UsePointTextIndicator'";
		logger.info("Execute SQL:" + sql);
		String value = db.executeQuery(sql, "value", 0);
		return Boolean.valueOf(value);
	}

	/** Get the translation vaule by label key */
	public String getTranslationValueByKey(String schema, String labelKey) {
		db.resetSchema(schema);
		String sql = "Select label_value from x_translation where label_key='"
				+ labelKey + "'";
		logger.info("Execute SQL:" + sql);
		String value = db.executeQuery(sql, "label_value", 0);
		return value;
	}

	/** Get point tracking type translation value */
	public String getPointTrackingTypeTranslation_Add(String schema) {
		return this.getTranslationValueByKey(schema, "translatable.addpoint");
	}

	public String getPointTrackingTypeTranslation_Deduct(String schema) {
		return this
				.getTranslationValueByKey(schema, "translatable.deductpoint");
	}

	public String getPointTrackingTypeTranslation_ImportAdd(String schema) {
		return this.getTranslationValueByKey(schema,
				"translatable.importaddpoint");
	}

	public String getPointTrackingTypeTranslation_ImportDeduct(String schema) {
		return this.getTranslationValueByKey(schema,
				"translatable.importdeductpoint");
	}

	/** Get the translation of the point balance */
	public String getPointBalanceTranslation(String schema,
			String pointTypeCode, String balance) {
		db.resetSchema(schema);
		String sql = "Select label_value from x_translation where label_key='translatable.pointsbalance."
				+ pointTypeCode + "." + balance + "'";
		logger.info("Execute SQL:" + sql);
		String value = db.executeQuery(sql, "label_value", 0);
		return value;
	}

	/** Get point balance */
	public String getPointBalance(String schema, String custNum,
			String pointTypeCode) {
		db.resetSchema(schema);
		String sql = "select account.id as ID from d_point_account account, c_cust_hfprofile cust "
				+ "where account.customer_id=cust.cust_id "
				+ "and cust.cust_number=" + custNum;
		logger.info("Execute SQL:" + sql);
		List<String> accountID = db.executeQuery(sql, "ID");
		if (accountID == null || accountID.size() == 0) {
			logger.info("No such point account, that is no point balance.");
			return "0"; // no point account found.
		} else {
			sql = "select alloc.balance from d_point_alloc alloc, d_point_type_cnfg cfg, d_point_type type "
					+ "where alloc.point_acc_id="
					+ accountID.get(0)
					+ " and alloc.point_type_cnfg_id=cfg.id "
					+ "and cfg.point_type_id=type.id "
					+ "and type.code= '"
					+ pointTypeCode + "' order by alloc.id desc";
			logger.info("Execute SQL:" + sql);
			List<String> values = db.executeQuery(sql, "balance");
			String value = "0";
			if (values != null && values.size() > 0) {
				value = values.get(0);
			}
			logger.info("Current Balance from DB is " + value);
			return value;
		}
	}

	public void deleteCustPointAllocation(String schema, String custNum) {
		db.resetSchema(schema);
		String sql = "delete from d_point_alloc where point_acc_id="
				+ "(select c.id from d_point_account c, c_cust_hfprofile p "
				+ "where c.customer_id=p.cust_id and p.cust_number=" + custNum
				+ ")";
		logger.info("Execute sql: " + sql);
		db.executeUpdate(sql);
	}

	public boolean isUnlockedPriExist(String schema, String custNum,
			PrivilegeInfo priv, HuntInfo hunt, String ulPriRecordStatusID) {
		db.resetSchema(schema);
		String sql = "Select count(*) from P_PRV_UNLOCKED where"
				+ " CUST_PROF_ID=(Select ID from C_CUST_HFPROFILE where CUST_NUMBER="
				+ custNum
				+ ")"
				+ " and HUNT_ID=(Select ID from P_HUNT where CODE='"
				+ hunt.getHuntCode()
				+ "')"
				+
				// " and TAG_QTY=" + hunt.getNumOfTagQty() +
				(StringUtil.notEmpty(hunt.getNumOfTagQty()) ? " and TAG_QTY="
						+ hunt.getNumOfTagQty() : "")
				+
				// " and POINT_TYPE_ID=(Select ID from D_POINT_TYPE where CODE="
				// + hunt.getPointTypeCode() + ")"+
				(StringUtil.notEmpty(hunt.getPointTypeCode()) ? " and POINT_TYPE_ID=(Select ID from D_POINT_TYPE where CODE="
						+ hunt.getPointTypeCode() + ")"
						: "") + " and LICENSE_YEAR=" + priv.licenseYear
				+ " and PURCHASE_STATUS=" + ulPriRecordStatusID
				+ " and ACTIVE_IND=1 and DELETED_IND=0 ";
		logger.info("Execute SQL:" + sql);
		String numOfResults = db.executeQuery(sql, "COUNT(*)", 0);
		logger.info("Num of results is " + numOfResults);
		return Integer.valueOf(numOfResults) > 0;
	}

	/**
	 * Get MA pass attributes based on order number and attribute names
	 * 
	 * @param schema
	 * @param orderNum
	 * @param attrsName
	 *            : 'Placard Required', 'Gift Tag Required', 'Vehicle Plate',
	 *            '2nd Vehicle Plate'
	 * @return
	 */
	public List<String> getOrderMaPassAttrs(String schema, String orderNum,
			String attrsName) {
		db.resetSchema(schema);

		String sql = "select oiav.* from o_ord_item_attr_value oiav, o_ord_item oi, o_order o, d_attr ad "
				+ "where o.id=oi.ord_id "
				+ "and oi.id=oiav.ord_item_id "
				+ "and oiav.attr_id=ad.attr_id "
				+ "and o.ord_num='"
				+ orderNum
				+ "' "
				+ "and ad.attr_cat='POSSales' "
				+ "and ad.attr_name in ("
				+ attrsName
				+ ") "
				+ "order by case ad.attr_name when 'Placard Required' then 1 when 'Gift Tag Required' then 2 when 'Vehicle Plate' then 3 when '2nd Vehicle Plate' then 4 end";

		logger.info("Execute SQL:" + sql);
		List<String> result = db.executeQuery(sql, "ATTR_VALUE");
		return result;
	}

	/** Update fulfillment method setup in DB */
	public void updateFulfillmentMethodSetup(String schema, String namespace,
			String value) {
		db.resetSchema(schema);
		String sql = "select value from x_prop "
				+ "where name like 'ApplicableInventoryFulfillmentMethods' and namespace='"
				+ namespace + "'";
		String result = db.executeQuery(sql, "Value", 0);
		if (!value.equalsIgnoreCase(result)) {
			sql = "update x_prop set value='"
					+ value
					+ "' "
					+ "where name like 'ApplicableInventoryFulfillmentMethods' and namespace='"
					+ namespace + "'";
			logger.info("Execute sql: " + sql);
			db.executeUpdate(sql);
		}
	}

	public void updateFulfillmentMethodForWeb(String schema, String value) {
		this.updateFulfillmentMethodSetup(schema, "PublicWeb", value);
	}

	/**
	 * Update privilege inventory type's fulfill with document value and alias
	 * in DB
	 */
	public void updatePrivInvType(String schema, String privInvTypeCode,
			String fulfillWithDocValue, String alias) {
		db.resetSchema(schema);
		String sql = "Select count(*) as count from P_HF_INV_TYPE where code='"
				+ privInvTypeCode + "' " + "and fulfill_with_document="
				+ fulfillWithDocValue + " and alias='" + alias + "'";
		if (db.executeQuery(sql, "count", 0).equals("0")) {
			logger.info("Update privilege inventory type " + privInvTypeCode
					+ ": fulfill_with_document=" + fulfillWithDocValue
					+ ", alias=" + alias);
			sql = "Update P_HF_INV_TYPE set fulfill_with_document="
					+ fulfillWithDocValue + ", alias='" + alias + "' "
					+ "where code='" + privInvTypeCode + "'";
			db.executeUpdate(sql);
		}
	}

	/** Get privilege inventory type's alias */
	public String getPrivInvTypeAlias(String schema, String privInvTypeCode) {
		db.resetSchema(schema);
		String sql = "Select ALIAS from P_HF_INV_TYPE where code='"
				+ privInvTypeCode + "' ";
		String result = db.executeQuery(sql, "ALIAS", 0);
		logger.info("privilege inventory type " + privInvTypeCode + " alias="
				+ result);
		return result;
	}

	public String getClosureIdByComments(String schema, String locID,
			String comments) {
		db.resetSchema(schema);

		logger.info("Query closure id for loc:" + locID + " with comments:"
				+ comments);
		String sql = "select * from i_clo_schdl where loc_id=" + locID
				+ " and dscr='" + comments + "' and active_ind=1";
		List<String> result = db.executeQuery(sql, "ID");
		if (result == null || result.size() < 1)
			throw new ItemNotFoundException(
					"Could not find any actived closure id. Please check your data.");
		return result.get(0);
	}

	public String[] getClosureStartEndDate(String schema, String locID,
			String comments) {
		db.resetSchema(schema);
		logger.info("Query closure Start and End Date for loc:" + locID
				+ " with comments:" + comments);

		String sql = "select start_date,end_date from i_clo_schdl where loc_id="
				+ locID + " and dscr='" + comments + "' and active_ind=1";
		String[] colNames = new String[] { "start_date", "end_date" };
		List<String[]> result = db.executeQuery(sql, colNames);

		return result.get(0);
	}

	public String getHuntLicenseYearQuotaID(String schema, String lotteryCode,
			String huntCode, String quotaDescription, String quotaLicYear) {
		db.resetSchema(schema);

		logger.info("Get Hunt License Year Quota ID.");
		String sql = "select distinct dhlyq.ID from d_hunt_license_year_quota dhlyq, p_prd pp, p_hunt_priv_lottery phpl, p_hunt ph, d_hunt_quota dhq "
				+ "where "
				+ (StringUtil.notEmpty(lotteryCode) ? "pp.prd_cd = '"
						+ lotteryCode + "' and" : "")
				+ " pp.prd_id = phpl.priv_lottery_id and ph.code = '"
				+ huntCode
				+ "' "
				+ "and dhq.description = '"
				+ quotaDescription
				+ "' and dhq.id = dhlyq.hunt_quota_id and dhlyq.license_year = '"
				+ quotaLicYear + "' " + "and dhlyq.status_id = 1";
		logger.info("Execute query: " + sql);
		List<String> ids = db.executeQuery(sql, "ID");
		if (ids.size() < 1)
			throw new ErrorOnDataException(
					"Cannot find Hunt-License Year-Quota ID.");

		return ids.get(0);
	}

	public String getHuntLicenseYearQuotaIDForPrivilege(String schema,
			String privilegeCode, String huntCode, String quotaDescription,
			String quotaLicYear) {
		db.resetSchema(schema);

		logger.info("Get Hunt License Year Quota ID.");
		String sql = "select distinct dhlyq.ID from d_hunt_license_year_quota dhlyq, p_prd pp, p_hunt ph, d_hunt_quota dhq "
				+ "where "
				+ (StringUtil.notEmpty(privilegeCode) ? "pp.prd_cd = '"
						+ privilegeCode + "' and" : "")
				+ " ph.code = '"
				+ huntCode
				+ "' "
				+ "and dhq.description = '"
				+ quotaDescription
				+ "' and dhq.id = dhlyq.hunt_quota_id and dhlyq.license_year = '"
				+ quotaLicYear + "' " + "and dhlyq.status_id = 1";
		logger.info("Execute query: " + sql);
		List<String> ids = db.executeQuery(sql, "ID");
		if (ids.size() < 1)
			throw new ErrorOnDataException(
					"Cannot find Hunt-License Year-Quota ID.");

		return ids.get(0);
	}

	public int[] getHuntInventoryQuantities(String schema,
			String huntLicenseYearQuotaID) {
		db.resetSchema(schema);

		logger.info("Get hunt inventory total quuantity and available quantity from table I_HUNT_INV.");
		String sql = "select QUANTITY, AVAILABLE from I_HUNT_INV where HUNT_LICENSE_YEAR_QUOTA_ID = "
				+ huntLicenseYearQuotaID;
		List<String[]> results = db.executeQuery(sql, new String[] {
				"QUANTITY", "AVAILABLE" });
		logger.info("Execute query: " + sql);
		if (results.size() < 1)
			throw new ErrorOnDataException("Not results found.");
		int quantities[] = new int[] { Integer.parseInt(results.get(0)[0]),
				Integer.parseInt(results.get(0)[1]) };

		return quantities;
	}

	public void addHuntInventoryQuantities(String schema,
			String huntLicenseYearQuotaID, int addedCount) {
		db.resetSchema(schema);

		String sql = "update I_HUNT_INV set QUANTITY = QUANTITY + "
				+ addedCount + ", AVAILABLE = AVAILABLE + " + addedCount
				+ " where HUNT_LICENSE_YEAR_QUOTA_ID = "
				+ huntLicenseYearQuotaID;
		int num = db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
		if (num < 1)
			throw new ErrorOnDataException(
					"Update Hunt Quantity and Available Quantity failed.");
	}

	public void updateHuntInventoryQuantities(String schema,
			String huntLicenseYearQuotaID, int quantity, int available) {
		db.resetSchema(schema);

		String sql = "update I_HUNT_INV set QUANTITY = " + quantity
				+ ", AVAILABLE = " + available
				+ " where HUNT_LICENSE_YEAR_QUOTA_ID = "
				+ huntLicenseYearQuotaID;
		int num = db.executeUpdate(sql);
		logger.info("Execute query: " + sql);
		if (num < 1)
			throw new ErrorOnDataException(
					"Update Hunt Quantity and Available Quantity failed.");
	}

	public void updateHuntInventoryAvailableQuantity(String schema,
			String huntLicenseYearQuotaID, int qty) {
		db.resetSchema(schema);

		String sql = "update I_HUNT_INV set AVAILABLE = " + qty
				+ " where HUNT_LICENSE_YEAR_QUOTA_ID = "
				+ huntLicenseYearQuotaID;
		logger.info("Execute query: " + sql);
		int num = db.executeUpdate(sql);
		if (num < 1)
			throw new ErrorOnDataException(
					"Update Hunt Available Quantity failed.");
	}

	public String getHuntInventoryStatus(String schema,
			String huntLicenseYearQuotaID) {
		db.resetSchema(schema);

		String sql = "select ihiu.STATUS_ID from i_hunt_inv_used ihiu, i_hunt_inv ihi where ihi.ID = ihiu.hunt_inv_id and ihi.hunt_license_year_quota_id = "
				+ huntLicenseYearQuotaID + " order by ihiu.ID desc";
		logger.info("Execute query: " + sql);
		List<String> statusIDs = db.executeQuery(sql, "STATUS_ID");
		if (statusIDs.size() < 1)
			throw new ErrorOnDataException("Cannot find Hunt Inventory status.");

		return statusIDs.get(0);
	}

	public boolean isHuntInventoryRecordExists(String schema,
			String huntLicenseYearQuotaID, String statusID) {
		db.resetSchema(schema);

		String sql = "select count(ihiu.ID) as COUNT from i_hunt_inv_used ihiu, i_hunt_inv ihi where ihi.ID = ihiu.hunt_inv_id and ihi.hunt_license_year_quota_id = "
				+ huntLicenseYearQuotaID + " and ihiu.status_id = " + statusID;
		logger.info("Execute query: " + sql);
		List<String> results = db.executeQuery(sql, "COUNT");
		if (results.size() < 1)
			return false;
		return true;
	}

	public List<String> getLoopsByParkID(String schema, String parkID) {
		db.resetSchema(schema);
		String sql = "select NAME from D_LOC where CD like '%"
				+ parkID
				+ "%' and STATUS_ID = 1 and TYPE_ID=9 and DELETE_IND = 0 order by NAME";
		logger.info("Query from db: " + sql);
		List<String> docks = db.executeQuery(sql, "NAME");
		return docks;
	}

	public List<String> getPrdIDsByParkIDAndLocName(String schema,
			String parkID, String loops) {
		db.resetSchema(schema);
		String sql = "select prd_id from p_prd,d_loc where p_prd.loc_id=d_loc.id and park_id="
				+ parkID
				+ " and d_loc.name='"
				+ loops
				+ "'"
				+ " and p_prd.active_ind=1 and deleted_ind=0 order by prd_id";
		logger.info("Query from db: " + sql);
		List<String> ids = db.executeQuery(sql, "prd_id");
		return ids;
	}

	public List<String> getImportCustIDs(String schema, String... idenNums) {
		// Generate identifier number String
		String idenNumString = StringUtil.EMPTY;
		for (int i = 0; i < idenNums.length; i++) {
			idenNumString += "'" + idenNums[i] + "'"
					+ (i == idenNums.length - 1 ? "" : ",");
		}

		// Get results
		db.resetSchema(schema);
		String sql = "select distinct imported_cust_id from c_imported_cust_identifier where ID_NUM in ("
				+ idenNumString + ")";
		logger.info("Query from db: " + sql);
		List<String> records = db.executeQuery(sql, "IMPORTED_CUST_ID");
		return records;
	}

	public void deleteImportedCustIdenRecords(String schema, String... idenNums) {
		// Generate identifier number String
		String idenNumString = StringUtil.EMPTY;
		for (int i = 0; i < idenNums.length; i++) {
			idenNumString += "'" + idenNums[i] + "'"
					+ (i == idenNums.length - 1 ? "" : ",");
		}

		// Delete records
		db.resetSchema(schema);
		String sql = "delete from c_imported_cust_identifier where ID_NUM in ("
				+ idenNumString + ")";
		logger.info("Execute sql: " + sql);
		int num = db.executeUpdate(sql);
		if (num == 0) {
			logger.info("No identifier records found.");
		}
	}

	public void insertCustIdenRecords(String schema, String importedCustID,
			String idenTypeID, String idNum, String stateID, String countryID) {
		db.resetSchema(schema);
		// Insert data
		String query = "insert into c_imported_cust_identifier values (get_Sequence('c_imported_cust_identifier'), "
				+ importedCustID
				+ ", "
				+ idenTypeID
				+ ", '"
				+ idNum
				+ "', "
				+ (StringUtil.isEmpty(stateID) ? "''" : stateID)
				+ ", "
				+ (StringUtil.isEmpty(countryID) ? "''" : countryID) + ")";
		int count = db.executeUpdate(query);
		if (count == 0) {
			throw new ErrorOnPageException(
					"Failed to insert external data file records");
		}
	}

	public List<String> getExternalDataFileIDs(String schema,
			List<String> importedCusIDs) {
		// Generate imported customer IDs
		String importedCusIDString = StringUtil.EMPTY;
		for (int i = 0; i < importedCusIDs.size(); i++) {
			importedCusIDString += "'" + importedCusIDs.get(i) + "'"
					+ (i == importedCusIDs.size() - 1 ? "" : ",");
		}

		// Get results
		db.resetSchema(schema);
		String sql = "select distinct ext_data_file_id from c_imported_customer where ID in ("
				+ importedCusIDString + ")";
		logger.info("Query from db: " + sql);
		List<String> records = db.executeQuery(sql, "EXT_DATA_FILE_ID");
		return records;
	}

	public String getExternalDataFileID(String schema,
			String externalDataFileName) {
		db.resetSchema(schema);
		String sql = "select distinct id from X_EXTERNAL_DATA_FILE where file_name = '"
				+ externalDataFileName + "'";
		logger.info("Query from db: " + sql);
		List<String> records = db.executeQuery(sql, "ID");
		if (records.size() == 0) {
			throw new ErrorOnPageException(
					"Failed to get external data file id");
		}
		return records.get(0);
	}

	public void deleteImportedCustRecords(String schema,
			List<String> importedCusIDs) {
		// Generate identifier number String
		String importedCusIDString = StringUtil.EMPTY;
		for (int i = 0; i < importedCusIDs.size(); i++) {
			importedCusIDString += "'" + importedCusIDs.get(i) + "'"
					+ (i == importedCusIDs.size() - 1 ? "" : ",");
		}

		// Delete records
		db.resetSchema(schema);
		String sql = "delete from c_imported_customer where ID in ("
				+ importedCusIDString + ")";
		logger.info("Execute sql: " + sql);
		int num = db.executeUpdate(sql);
		if (num == 0) {
			logger.info("No imported cutomer records found.");
		}
	}

	public String insertImportedCustRecords(String schema, Customer cust,
			String externalDataFileID, String lineNum) {
		db.resetSchema(schema);
		// Insert data
		String query = "insert into c_imported_customer (ID, EXT_DATA_FILE_ID, LAST_NAME, FIRST_NAME, MIDDLE_NAME, ADDRESS, CITY, STATE_ID, ZIPCODE, GENDER, BIRTHDAY, HEIGHT, WEIGHT, EYE_COLOR, LINE_NUM)"
				+ "values " + "(get_Sequence('c_imported_customer'), "
				+ externalDataFileID
				+ ", '"
				+ cust.lName
				+ "','"
				+ cust.fName
				+ "','"
				+ cust.mName
				+ "','"
				+ cust.address
				+ "', '"
				+ cust.city
				+ "', '"
				+ cust.stateID
				+ "', '"
				+ cust.zip
				+ "', '"
				+ cust.custGender
				+ "','"
				+ DateFunctions.formatDate(cust.dateOfBirth, "dd-MMM-yy")
				+ "','"
				+ cust.heightFt
				+ "', '"
				+ cust.heightIn
				+ "','"
				+ cust.eyeColorID + "','" + lineNum + "')";
		int count = db.executeUpdate(query);
		if (count == 0) {
			throw new ErrorOnPageException(
					"Failed to insert external data file records");
		}
		// Get dataID
		String queryID = "select ID from c_imported_customer where first_name = '"
				+ cust.fName + "'";
		return db.executeQuery(queryID, "ID").get(0);
	}

	public void deleteExternalDataFiletRecords(String schema,
			List<String> externalDataFIleIDs) {
		// Generate imported customer IDs
		String externalDataFileIDsString = StringUtil.EMPTY;
		for (int i = 0; i < externalDataFIleIDs.size(); i++) {
			externalDataFileIDsString += "'" + externalDataFIleIDs.get(i) + "'"
					+ (i == externalDataFIleIDs.size() - 1 ? "" : ",");
		}

		db.resetSchema(schema);
		String sql = "delete from X_EXTERNAL_DATA_FILE where ID in ("
				+ externalDataFileIDsString + ")";
		logger.info("Execute sql: " + sql);
		int num = db.executeUpdate(sql);
		if (num == 0) {
			logger.info("No external data file records found.");
		}
	}

	public boolean hasExternalDataFileRecord(String schema,
			String externalDataFileName) {
		db.resetSchema(schema);
		String queryID = "select ID from X_EXTERNAL_DATA_FILE where FILE_NAME = '"
				+ externalDataFileName + "'";
		if (db.executeQuery(queryID, "ID").size() > 0) {
			return true;
		} else
			return false;
	}

	public void deleteExternalDataFiletRecords(String schema,
			String externalDataFileName) {
		db.resetSchema(schema);
		String sql = "delete from X_EXTERNAL_DATA_FILE where FILE_NAME = '"
				+ externalDataFileName + "'";
		logger.info("Execute sql: " + sql);
		int num = db.executeUpdate(sql);
		if (num == 0) {
			logger.info("No external data file records found.");
		}
	}

	public String insertExternalDataFileRecords(String schema,
			String fileDataType, String fileName, int recordsCount, int statusID) {
		db.resetSchema(schema);
		// Insert data
		String query = "insert into X_EXTERNAL_DATA_FILE (ID, FILE_DATA_TYPE, FILE_NAME, RECORDS_COUNT, STATUS_ID) values (get_Sequence('X_EXTERNAL_DATA_FILE'), '"
				+ fileDataType
				+ "', '"
				+ fileName
				+ "',"
				+ statusID
				+ ", "
				+ statusID + ")";
		int count = db.executeUpdate(query);
		if (count == 0) {
			throw new ErrorOnPageException(
					"Failed to insert external data file records");
		}
		// Get dataID
		String queryID = "select ID from X_EXTERNAL_DATA_FILE where FILE_NAME = '"
				+ fileName + "'";
		return db.executeQuery(queryID, "ID").get(0);
	}

	public void deleteBoatFromDB(String schema, String boatName) {
		db.resetSchema(schema);
		String sql_1 = "delete from c_cust_prof_equp where id in(select cpv.id from c_cust_prof_marina_vessel cpv where cpv.boat_name = '"
				+ boatName + "')";
		String sql_2 = "delete from c_cust_prof_marina_vessel where id in(select cpv.id from c_cust_prof_marina_vessel cpv where cpv.boat_name = '"
				+ boatName + "')";
		db.executeUpdate(sql_1);
		db.executeUpdate(sql_2);
	}

	public void deleteAllDeliveryMethodsForFacility(String parkID, String schema) {
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String deleteSql = "DELETE  FROM D_TICKET_DELIVERY_METHOD_CFG  Where LOC_ID="
				+ parkID;
		String querySql = "SELECT count(*) FROM D_TICKET_DELIVERY_METHOD_CFG  Where LOC_ID="
				+ parkID;
		logger.info("Delete all delivery method for park which of id:" + parkID);
		if (Integer.parseInt(db.executeQuery(querySql, "count(*)", 0)) > 0) {
			db.executeUpdate(deleteSql);
		}
	}

	public void setUpDeliveryMethod(String parkID, String method_ID,
			String ticketCat_ID, String salsChannel_ID, String occurence_ID,
			String schema) {
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		logger.info("Set up delivery method...");

		String insertSql = "insert into D_TICKET_DELIVERY_METHOD_CFG (ID, LOC_ID, DELIVERY_METHOD_ID, TICKET_CATEGORY_ID, SALE_CHANNEL_ID, TRANSACTION_OCCURRENCE_ID) "
				+ "values "
				+ "(get_sequence('D_TICKET_DELIVERY_METHOD_CFG'),"
				+ parkID
				+ ","
				+ method_ID
				+ ","
				+ ticketCat_ID
				+ ","
				+ salsChannel_ID + "," + occurence_ID + ")";
		if (db.executeUpdate(insertSql) < 1) {
			throw new ActionFailedException("Failed set up Delivery Method");
		}
	}

	public void insertLotteryRevokeReason(String schema, String typeID,
			String descr, String code) {
		db.resetSchema(schema);
		String query1 = "select id from O_TOUR_REASON_CD where type_id="
				+ typeID + " and descr= '" + descr + "' and code = '" + code
				+ "'";
		String query2 = "INSERT INTO O_TOUR_REASON_CD VALUES (get_sequence('O_TOUR_REASON_CD'), "
				+ typeID + ", '" + descr + "', 0, '" + code + "', 0)";
		List<String> results = db.executeQuery(query1, "ID");
		if (results.size() < 1) {
			db.executeUpdate(query2);
		}
	}

	public List<String[]> getOrderDiscountInfo(String orderNum, String schema) {
		db.resetSchema(schema);
		String[] colNames = { "discnt_schd_id", "override_amount",
				"start_date", "end_date" };
		String query = "select  * from O_ORD_DISCNT where ord_id=(Select id from   O_ORDER  where ord_num='"
				+ orderNum + "')";
		logger.info("To get discount information run sql:" + query);
		List<String[]> results = db.executeQuery(query, colNames);
		return results;
	}

	public void updatePermitGroupSizeAttrValue(String schema, String parkID,
			String permitTypeCode, String attrID, String attrName) {
		db.resetSchema(schema);
		String sql = "update p_permit_type_attr_value set attr_value='"
				+ attrName
				+ "' where permit_type_id in (select id from P_PERMIT_TYPE where loc_id = '"
				+ parkID + "' and code = '" + permitTypeCode
				+ "') and attr_id=" + attrID;
		db.executeUpdate(sql);
	}

	public void activateOrDeactivatePermitGroupSize(String schema,
			String parkID, String permitTypeCode, String attrID,
			boolean activate) {
		db.resetSchema(schema);
		String sql = "update p_permit_type_attr_value set active_ind="
				+ (activate ? "1" : "0")
				+ " where permit_type_id in (select id from P_PERMIT_TYPE where loc_id = '"
				+ parkID + "' and code = '" + permitTypeCode
				+ "') and attr_id=" + attrID;
		db.executeUpdate(sql);
	}

	public List<String[]> getPmtAllocationInfo(String schema, String orderNum,
			String feeTypeID) {
		db.resetSchema(schema);
		String sql = "select AMOUNT, SCHD_ID from f_pmt_allocation where ord_id=(select id from o_order where ord_num='"
				+ orderNum
				+ "') and fee_type="
				+ feeTypeID
				+ " order by id asc";
		logger.info("Query from db: " + sql);
		List<String[]> records = db.executeQuery(sql, new String[] { "AMOUNT",
				"SCHD_ID" });
		return records;
	}

	public List<String> getNotificationLogFromDB(String schema, String TypeID,
			String locID, String orderNum, String noticeID) {
		db.resetSchema(schema);
		String sql = "select send_date from c_notification_log where type_id="
				+ TypeID
				+ " and loc_id="
				+ locID
				+ " and order_num = '"
				+ orderNum
				+ "' and status=1"
				+ (StringUtil.notEmpty(noticeID) ? " and notice_id=" + noticeID
						: "");
		logger.info("Query from db: " + sql);
		List<String> records = db.executeQuery(sql, "SEND_DATE");
		logger.info("Results:" + records.toString());
		return records;
	}

	public List<String> getNotificationLogFromDB(String schema, String TypeID,
			String locID, String orderNum) {
		return getNotificationLogFromDB(schema, TypeID, locID, orderNum,
				StringUtil.EMPTY);
	}

	/** Delete hunt assignment from DB by hunt code and lottery code */
	public void deleteHuntAssignment(String schema, String huntCode,
			String lotteryPrdCode) {
		if (StringUtil.isEmpty(huntCode) && StringUtil.isEmpty(lotteryPrdCode)) {
			throw new ErrorOnDataException(
					"Must specific lottery product code or hunt code!");
		}
		db.resetSchema(schema);
		String sql = "delete from p_hunt_priv_lottery where priv_lottery_id="
				+ "(select prd_id from p_prd where prd_cd='" + lotteryPrdCode
				+ "' and product_cat_id=9)";
		if (StringUtil.notEmpty(huntCode)) {
			sql += " and hunt_id=(select id from p_hunt where code='"
					+ huntCode + "')";
		}
		logger.info("Execute sql: " + sql);
		db.executeUpdate(sql);
		logger.info("Delete hunt assignment sucessfully.");
	}

	/** Get hunt assignment id */
	public String getHuntAssignmentID(String schema, String huntCode,
			String lotteryPrdCode) {
		db.resetSchema(schema);
		String sql = "select ID from p_hunt_priv_lottery where hunt_id="
				+ "(select id from p_hunt where code='" + huntCode
				+ "') and priv_lottery_id="
				+ "(select prd_id from p_prd where prd_cd='" + lotteryPrdCode
				+ "' and product_cat_id=9)";
		logger.info("Execute sql: " + sql);
		String id = db.executeQuery(sql, "ID", 0);
		logger.info("Hunt Assingment ID is " + id);
		return id;
	}

	/**
	 * Get number of available hunts assigned to the lottery with the specific
	 * year
	 * 
	 * @param schema
	 * @param lotteryDes
	 * @param year
	 * @param individualInd
	 * @param groupInd
	 * @return
	 * @author Lesley
	 * @date Feb 20, 2014
	 */
	public int getNumOfAvailableLotteryHunts(String schema, String lotteryDes,
			String year, String individualInd, String groupInd) {
		db.resetSchema(schema);
		String today = DateFunctions.getToday();
		String sql = "select count(licYear.hunt_id) as num from p_prd_year licYear, p_prd prd, p_hunt hunt "
				+ "where licYear.year='"
				+ year
				+ "' and licYear.active_ind=1 and licYear.deleted_ind=0 and "
				+ "licYear.sell_from_date<=TO_Date('"
				+ today
				+ "', 'mm-dd-yy') and licYear.sell_to_date>=TO_Date('"
				+ today
				+ "', 'mm-dd-yy') and "
				+ "prd.prd_id=licYear.prd_id and prd.prd_dscr='"
				+ lotteryDes
				+ "' and "
				+ "licYear.hunt_id=hunt.id and hunt.status_id=1 and hunt.deleted_ind=0";
		if (StringUtil.notEmpty(individualInd)) {
			sql += " and hunt.individual_ind=" + individualInd;
		}
		if (StringUtil.notEmpty(groupInd)) {
			sql += " and hunt.group_ind=" + groupInd;
		}
		logger.info("Execute query: " + sql);
		String num = db.executeQuery(sql, "num", 0);
		logger.info("The number of available lottery hunts for lottery "
				+ lotteryDes + " with year= " + year + " is " + num);
		return Integer.valueOf(num);
	}

	public int getNumOfAvailableLotteryHuntsForIndividual(String schema,
			String lotteryDes, String year) {
		return this.getNumOfAvailableLotteryHunts(schema, lotteryDes, year,
				"1", StringUtil.EMPTY);
	}

	public void deleteFacilityFromDB(String facilityID, String schema) {
		logger.info("Delete facility(ID#=" + facilityID + ") from DB.");
		db.resetSchema(schema);
		String sql = "update d_loc set delete_ind=1, active_ind=0 where id="
				+ facilityID;
		int result = db.executeUpdate(sql);
		if (result != 1) {
			throw new ErrorOnDataException(
					"There is not 1 record has been updated.");
		} else {
			logger.info("Delete facility sucessfully.");
		}
	}

	public void deleteFacilityPrdFromDB(String prdCode, String schema) {
		logger.info("Delete facility prd(Code" + prdCode + ") from DB");
		db.resetSchema(schema);
		String sql = "update p_prd set deleted_ind=1, active_ind=0 where prd_cd='"
				+ prdCode + "'";
		db.executeUpdate(sql);
	}

	public List<String> getParticipationsForList(String schema, String listId) {
		db.resetSchema(schema);
		String sql = "select prd_name from p_prd where prd_id in (select prd_id from P_PRD_LOTTERY_PARTICIPATION where lottery_id= "
				+ listId + ")";
		List<String> records = db.executeQuery(sql, "PRD_NAME");
		return records;
	}

	public List<String> getFacilityPrdTypesFromDB(String schema, String TypeID,
			String locID, String orderNum, String noticeID) {
		db.resetSchema(schema);
		String sql = "select send_date from c_notification_log where type_id="
				+ TypeID
				+ " and loc_id="
				+ locID
				+ " and order_num = '"
				+ orderNum
				+ "' and status=1"
				+ (StringUtil.notEmpty(noticeID) ? " and notice_id=" + noticeID
						: "");
		logger.info("Query from db: " + sql);
		List<String> records = db.executeQuery(sql, "SEND_DATE");
		logger.info("Results:" + records.toString());
		return records;
	}

	public List<String> getFacilityPrdTypesFromDB(String schema) {
		db.resetSchema(schema);
		String query = "select prd_grp_name from p_prd_grp where prd_grp_cat_id = "
				+ PRDCAT_FACILITY
				+ " and prd_subcat_id="
				+ PRDSUBCAT_PRDTYPE_FACILITY
				+ " order by Lower(prd_grp_name) asc";
		List<String> facilityPrdType = db.executeQuery(query, "prd_grp_name");
		return facilityPrdType;
	}

	public String getPrivilegeLegalNameFromDB(String schema,
			String privilegeName) {
		db.resetSchema(schema);
		String sql = "select prd_legal_name from p_prd where prd_name = '"
				+ privilegeName + "'";
		logger.info("Query from db: " + sql);
		List<String> records = db.executeQuery(sql, "PRD_LEGAL_NAME");
		if (records.size() == 0) {
			throw new ErrorOnPageException(
					"Failed to get the legal name of privilege which name is "
							+ privilegeName);
		}
		return records.get(0);
	}

	public void deleteCustPropertyFromDB(String schema, String propertyID) {
		db.resetSchema(schema);
		String sql_landPropertyAttrValue = "delete from D_LAND_PROPERTY_ATTR_VALUE where land_property_id="
				+ propertyID;
		String sql_landPropertyOwnership = "delete from D_LAND_PROPERTY_OWNERSHIP where land_property_id="
				+ propertyID;
		String sql_landProperty = "delete from D_LAND_PROPERTY where id="
				+ propertyID;
		db.executeUpdate(sql_landPropertyAttrValue);
		db.executeUpdate(sql_landPropertyOwnership);
		db.executeUpdate(sql_landProperty);
	}

	public void deleteCustOwnerFromDB(String schema, String propertyID) {
		db.resetSchema(schema);
		String sql_landPropertyOwnership = "delete from D_LAND_PROPERTY_OWNERSHIP where land_property_id="
				+ propertyID;
		db.executeUpdate(sql_landPropertyOwnership);
	}

	public String getOwnerIDFromDB(String schema, String propertyID) {
		db.resetSchema(schema);
		String query = "select ID from D_LAND_PROPERTY_OWNERSHIP where land_property_id="
				+ propertyID + " order by id desc";
		List<String> ownerIDs = db.executeQuery(query, "ID");
		if (ownerIDs.size() < 1) {
			throw new ErrorOnPageException(
					"Failed to get owner for property which id=" + propertyID);
		}
		return ownerIDs.get(0);
	}

	public List<String> getCountyBasedOnState(String schema, String state) {
		db.resetSchema(schema);
		String query = "select name from D_REF_COUNTY where state_id in (select id from D_REF_STATE_PROVNC where name='"
				+ state + "') and active_ind=1 order by name asc";
		List<String> results = db.executeQuery(query, "NAME");
		if (results.size() < 1) {
			throw new ErrorOnPageException("Failed to get county under state:"
					+ state);
		}
		return results;
	}

	public List<String> getAuditsFromDB(String schema, String propertyID) {
		db.resetSchema(schema);
		String query = "select ID from D_LAND_PROPERTY_AUDIT where LAND_PROPERTY_ID="
				+ propertyID + " order by id desc";
		List<String> results = db.executeQuery(query, "ID");
		return results;
	}

	public List<String> getOwnersFromDB(String schema, String propertyID) {
		db.resetSchema(schema);
		String query = "select ID from D_LAND_PROPERTY_OWNERSHIP WHERE LAND_PROPERTY_ID="
				+ propertyID + " order by id desc";
		List<String> results = db.executeQuery(query, "ID");
		return results;
	}

	public void allowDocumentNumber(String schema, boolean allow) {
		db.resetSchema(schema);
		String sql = "update x_prop set value = '" + (allow ? "true" : "false")
				+ "' where name = 'AllowDocumentNumber'";
		db.executeUpdate(sql);
	}

	public String getDocumentNumColName(String schema) {
		db.resetSchema(schema);
		String sql = "select label_value from x_translation where label_key = 'translatable.documentnumber'";
		logger.info("Query from db: " + sql);
		List<String> records = db.executeQuery(sql, "LABEL_VALUE");
		if (records.size() == 0) {
			throw new ErrorOnPageException(
					"Failed to get document number label name from DB.");
		}
		return records.get(0);
	}

	public void updateDocumentNumColName(String schema, String colName) {
		db.resetSchema(schema);
		String sql = "update x_translation set label_value = '" + colName
				+ "' where label_key = 'translatable.documentnumber'";
		db.executeUpdate(sql);
	}

	public String getAgencyByFacilityName(String schema, String facilityID) {
		db.resetSchema(schema);
		String sql = "select name from d_loc where id = "
				+ "(select parent_id from d_loc where id = "
				+ "(select parent_id from d_loc where id = " + facilityID
				+ "))";
		logger.info("Query from db: " + sql);
		List<String> names = db.executeQuery(sql, "name");
		if (names.size() == 0) {
			throw new ErrorOnPageException(
					"Failed to get agency name from DB by facility id = "
							+ facilityID);
		}
		return names.get(0);
	}

	public String getRegionByFacilityName(String schema, String facilityID) {
		db.resetSchema(schema);
		String sql = "select name from d_loc where id = "
				+ "(select parent_id from d_loc where id = " + facilityID + ")";
		logger.info("Query from db: " + sql);
		List<String> names = db.executeQuery(sql, "name");
		if (names.size() == 0) {
			throw new ErrorOnPageException(
					"Failed to get region name from DB by facility id = "
							+ facilityID);
		}
		return names.get(0);
	}

	public String getConfirmLetterIdByOrderNum(String schema, String order) {
		db.resetSchema(schema);
		String sql = "select * from c_confirm_letter where res_num='" + order
				+ "'";
		logger.info("Query from db: " + sql);
		List<String> id = db.executeQuery(sql, "id");
		if (id.size() == 0) {
			throw new ErrorOnPageException(
					"Failed to get confirm letter id from DB by order num = "
							+ order);
		}
		return id.get(0);
	}

	public boolean checkOrderTranFeeLabelSetupExisting(String schema,
			String labelKey) {
		boolean setupIsExisting;
		db.resetSchema(schema);
		logger.info("Check Delivery Method Fee label setup existing for label key = "
				+ labelKey);
		String query = "select id from x_translation where label_key = '"
				+ labelKey + "'";
		List<String> id = db.executeQuery(query, "id");
		if (id.size() == 1) {
			setupIsExisting = true;
		} else {
			setupIsExisting = false;
		}
		return setupIsExisting;
	}

	/**
	 * set up order transaction fee label.
	 * 
	 * @param schema
	 * @param id
	 * @param labelKey
	 * @param labelValue
	 */
	public void setupOrderTranFeeLabel(String schema, String id,
			String labelKey, String labelValue) {
		db.resetSchema(schema);
		logger.info("Script in the Delivery Method Fee label value");
		String query = "insert into x_translation (ID, LABEL_KEY,LABEL_VALUE,LOC_CODE) values('"
				+ id + "','" + labelKey + "','" + labelValue + "','|1|')";
		db.executeUpdate(query);
	}

	/**
	 * clean up order level transaction fee label.
	 * 
	 * @param schema
	 * @param labelKey
	 */
	public void cleanUpOrderTranFeeLabelSetup(String schema, String labelKey) {
		db.resetSchema(schema);
		logger.info("Delete the order level transaction fee label");
		String query = "delete from x_translation where label_key = '"
				+ labelKey + "'";
		db.executeUpdate(query);
	}

	public void updateVehicleRenewalDaysInfo(String schema, String vehicleCode,
			String renewalDays) {
		db.resetSchema(schema);
		logger.info("Update vehicle renewal days info, vehicle code = '"
				+ vehicleCode + "', renewal days = '" + renewalDays + "'");

		String query = "select adv_renewal_days from ((p_vhcl_rti_vld_to_date_calc inner join p_vehicle_rti on p_vhcl_rti_vld_to_date_calc.id = p_vehicle_rti.vld_to_date_calc_id) "
				+ "inner join p_prd on p_vehicle_rti.prd_id = p_prd.prd_id) "
				+ "where product_cat_id = 11 and active_ind = 1 and prd_cd = '"
				+ vehicleCode + "'";
		logger.info("Execute query:" + query);
		List<String> result = db.executeQuery(query, "adv_renewal_days");

		if (result.size() < 1) {
			throw new ErrorOnPageException(
					"Did not get any renewal days info for vehicle '"
							+ vehicleCode + "'");
		} else
			logger.info("Vehicle renewal days info is correct, vehicle code = '"
					+ vehicleCode + "', renewal days = '" + renewalDays + "'");

		String renewalDaysFromDB = result.get(0);
		if (!renewalDaysFromDB.equalsIgnoreCase(renewalDays)) {
			String update = "update p_vhcl_rti_vld_to_date_calc set adv_renewal_days = "
					+ renewalDays
					+ " where id = (select vld_to_date_calc_id from p_vehicle_rti where prd_id = "
					+ "(select prd_id from p_prd where product_cat_id = 11 and prd_cd = '"
					+ vehicleCode + "'))";
			logger.info("Execute update:" + update);
			db.executeUpdate(update);
		}

	}

	public void updateVehicleValidMonthsInfo(String schema, String vehicleCode,
			String validMonths) {
		db.resetSchema(schema);
		logger.info("Update vehicle valid months info, vehicle code = '"
				+ vehicleCode + "', valid months = '" + validMonths + "'");

		String query = "select rltv_valid_months from ((p_vhcl_rti_vld_to_date_calc inner join p_vehicle_rti on p_vhcl_rti_vld_to_date_calc.id = p_vehicle_rti.vld_to_date_calc_id) "
				+ "inner join p_prd on p_vehicle_rti.prd_id = p_prd.prd_id) "
				+ "where product_cat_id = 11 and active_ind = 1 and prd_cd = '"
				+ vehicleCode + "'";
		logger.info("Execute query:" + query);
		List<String> result = db.executeQuery(query, "rltv_valid_months");

		if (result.size() < 1) {
			throw new ErrorOnPageException(
					"Did not get any valid months info for vehicle '"
							+ vehicleCode + "'");
		}
		logger.info("Vehicle renewal days info is correct, vehicle code = '"
				+ vehicleCode + "', valid months = '" + validMonths + "'");

		String validMonthsFromDB = result.get(0);
		if (!validMonthsFromDB.equalsIgnoreCase(validMonths)) {
			String update = "update p_vhcl_rti_vld_to_date_calc set rltv_valid_months = "
					+ validMonths
					+ " where id = (select vld_to_date_calc_id from p_vehicle_rti where prd_id = "
					+ "(select prd_id from p_prd where product_cat_id = 11 and prd_cd = '"
					+ vehicleCode + "'))";
			logger.info("Execute update:" + update);
			db.executeUpdate(update);
		}

	}

	public void updateDocumentUploadFileSizePropertyInDB(String schema,
			String sizeValue) {
		logger.info("Update or Insert DocumentFileUploadMaximumSize property with value = "
				+ sizeValue);
		String prp = this.getPropertyValue(schema,
				"DocumentFileUploadMaximumSize");
		if (StringUtil.isEmpty(prp)) {
			this.insertPropertyValue(schema, "DocumentFileUploadMaximumSize",
					"Contract", "Number", sizeValue);
		} else {
			this.updatePropertyValue(schema, "DocumentFileUploadMaximumSize",
					sizeValue);
		}
	}

	public void updateDocumentUploadTranslationInfo(String schema,
			String labelKey, String labelValue) {
		logger.info("Update or Insert Document upload translation value, with label key = "
				+ labelKey + " and label value = " + labelValue);
		db.resetSchema(schema);
		String query = "select label_value from x_translation where label_key = '"
				+ labelKey + "'";
		logger.info("Execute query:" + query);
		List<String> label_value = db.executeQuery(query, "label_value");
		if (label_value.size() < 1) {
			String insert = "insert into x_translation(ID, LABEL_KEY, LABEL_VALUE, LOC_CODE) "
					+ "values(CONTRACT_SEQ.nextval,'"
					+ labelKey
					+ "','"
					+ labelValue + "','|1|')";
			logger.info("Execute insert:" + insert);
			db.executeUpdate(insert);
		} else if (label_value.size() == 1) {
			if (!label_value.get(0).equalsIgnoreCase(labelValue)) {
				String update = "update x_translation set label_value = '"
						+ labelValue + "'";
				logger.info("Execute update:" + update);
				db.executeUpdate(update);
			}
		} else {
			throw new ErrorOnDataException(
					"Please check translation info in DB, "
							+ "should have one record for translation ifo which label_key = '"
							+ labelKey + "'");
		}
	}

	public int getDocumentUploadTotalCountForSpecificVendor(String schema,
			String vendorNum, String vendorName, String status) {
		logger.info("Get document upload total count for specific vendor, vendor num = "
				+ vendorNum + ", and vendor name = " + vendorName);

		db.resetSchema(schema);
		String query = "select count(f.id) as TOTALNUM from D_DOC_REPO_FILE f "
				+ "inner join D_DOC_FILE_REPO_VENDOR fv on fv.id = f.file_repo_id "
				+ "inner join D_VENDOR v on v.id = fv.vendor_id "
				+ "where v.vendor_num = '" + vendorNum + "' and v.name = '"
				+ vendorName + "' ";
		// current no delete index, if will have delete index in future please
		// update releated sql
		if (StringUtil.notEmpty(status)) {
			int active_ind;
			if (status.equalsIgnoreCase(Orms.ACTIVE_STATUS)) {
				active_ind = Orms.ACTIVE;
			} else {
				active_ind = Orms.INACTIVE;
			}

			query = query + "and f.active_ind = " + active_ind;
		}

		logger.info("Execute query:" + query);
		String result = db.executeQuery(query, "TOTALNUM").get(0);

		return Integer.valueOf(result);
	}

	public int getDocumentUploadTotalCountForSpecificVehicle(String schema,
			String vehicleSerNum, String status) {
		logger.info("Get document upload total count for specific vehicle, vehicle seria number = "
				+ vehicleSerNum);

		db.resetSchema(schema);
		String query = "select count(f.id) as TOTALNUM from D_DOC_REPO_FILE f "
				+ "inner join D_DOC_FILE_REPO_VEHICLE fv on fv.id = f.file_repo_id "
				+ "inner join E_VEHICLE v on v.id = fv.vehicle_id "
				+ "where v.ser_number = '" + vehicleSerNum + "' ";
		// current no delete index, if will have delete index in future please
		// update releated sql
		if (StringUtil.notEmpty(status)) {
			int active_ind;
			if (status.equalsIgnoreCase(Orms.ACTIVE_STATUS)) {
				active_ind = Orms.ACTIVE;
			} else {
				active_ind = Orms.INACTIVE;
			}

			query = query + "and f.active_ind = " + active_ind;
		}

		logger.info("Execute query:" + query);
		String result = db.executeQuery(query, "TOTALNUM").get(0);

		return Integer.valueOf(result);
	}

	private int getTotalCountNumberOfReservationByNeedToAction(String schema,
			String locID, String orderCategory, String action) {
		logger.info("Get total count number of need to " + action
				+ "check-outs reservation, by location id = " + locID
				+ ", order category = " + orderCategory);
		db.resetSchema(schema);
		if (orderCategory.equalsIgnoreCase("Marina Order")) {
			orderCategory = "20";
		} else {
			throw new ErrorOnDataException("Order Category not correct.");
		}
		TimeZone tz = DataBaseFunctions.getContractTimeZone(schema);
		String today = DateFunctions.getToday("dd-MMM-yy", tz);

		String query = "select count(o.id) as TOTALNUM from o_order o inner join o_ord_item oi on o.id = oi.ord_id  where o.ord_status_id = 1 and o.loc_id = "
				+ locID
				+ " and o.ord_cat_id = "
				+ orderCategory
				+ " and o.proc_status_id = ";
		String procStatusId = "";
		if (action.equalsIgnoreCase("Check in")) {
			procStatusId = "1";// proc_status_id = 1: means reservation is
								// pre-arrival
			query = query + procStatusId + " and o.start_date <= '" + today
					+ "'";
		} else if (action.equalsIgnoreCase("Check out")) {
			procStatusId = "2";// proc_status_id = 2: means reservation is
								// check-in
			query = query + procStatusId + " and o.end_date ='" + today + "'";
		} else if (action.equalsIgnoreCase("Check outs overdue")) {
			procStatusId = "2";// proc_status_id = 2: means reservation is
								// check-in
			query = query + procStatusId + " and o.end_date <'" + today + "'";
		} else if (action.equalsIgnoreCase("Dockings")) {
			procStatusId = "2";// proc_status_id = 2: means reservation is
								// check-in
			// oi.status_id = 22: order item status is undocked
			query = query + procStatusId + " and o.end_date >'" + today
					+ "' and oi.status_id = 22";
		} else {
			throw new ErrorOnDataException("Need to Action not correct.");
		}

		logger.info("Execute query:" + query);
		String count = db.executeQuery(query, "TOTALNUM").get(0);
		return Integer.valueOf(count);
	}

	public int getTotalCountNumberOfNeedToCheckInsReservation(String schema,
			String locID, String orderCategory) {
		return this.getTotalCountNumberOfReservationByNeedToAction(schema,
				locID, orderCategory, "Check in");
	}

	public int getTotalCountNumberOfNeedToCheckOutsReservation(String schema,
			String locID, String orderCategory) {
		return this.getTotalCountNumberOfReservationByNeedToAction(schema,
				locID, orderCategory, "Check out");
	}

	public int getTotalCountNumberOfNeedToCheckOutsOverdueReservation(
			String schema, String locID, String orderCategory) {
		return this.getTotalCountNumberOfReservationByNeedToAction(schema,
				locID, orderCategory, "Check outs Overdue");
	}

	public int getTotalCountNumberOfNeedToDockingsReservation(String schema,
			String locID, String orderCategory) {
		return this.getTotalCountNumberOfReservationByNeedToAction(schema,
				locID, orderCategory, "Dockings");
	}

	public List<String> getInstructorTypes(String schema) {
		db.resetSchema(schema);
		String query = "select name from C_INSTRUCTOR_TYPE order by name";
		List<String> results = db.executeQuery(query, "NAME");
		return results;
	}

	/**
	 * deactive gift card Number.
	 * 
	 * @param schema
	 * @param cardNum
	 */
	public void deactiveGiftCardNum(String schema, String cardNum) {
		logger.info("set the gift card " + cardNum + " ord_id to null");
		db.resetSchema(schema);
		String encryCardNum = CryptoUtil.encryptGiftCard(cardNum);
		String sql = "update I_GIFTCARD_INV set ORD_ID = null where card_number = '"
				+ encryCardNum + "'";
		logger.info(sql);
		db.executeUpdate(sql);
	}

	public void checkCountyQuantityForLyFromDB(String typeId,
			String licenseYear, String qty, String schema) {
		logger.info("Check All County Quantity is " + qty + "for Type Id#"
				+ typeId + " and License Year is " + licenseYear);

		db.resetSchema(schema);

		String query = "select plplq.id from P_LANDOWNER_PTYPE_LY_QTY plplq,P_LANDOWNER_PTYPE_LY plpl where plplq.landowner_ptype_ly_id=plpl.id and plpl.license_year='"
				+ licenseYear
				+ "' and plpl.landowner_ptype_id="
				+ typeId
				+ " and qty!=" + qty;
		List<String> ids = db.executeQuery(query, "id");
		if (ids != null && ids.size() > 0) {
			throw new ErrorOnDataException(
					"County Quantity Not Correct for id="
							+ StringUtil.listToString(ids, false));
		}
		logger.info("All County Qty are correct in DB.");
	}

	public void deleteCountyQuantityForLy(String typeId, String licenseYear,
			String schema) {
		logger.info("Delete County Quantity for Type Id#" + typeId
				+ " and License Year is " + licenseYear);

		db.resetSchema(schema);

		String query = "select id from P_LANDOWNER_PTYPE_LY where license_year='"
				+ licenseYear + "' and landowner_ptype_id=" + typeId;
		List<String> ids = db.executeQuery(query, "id");
		if (ids != null && ids.size() > 0) {
			// delete from child table P_LANDOWNER_PTYPE_LY_QTY
			String del = "delete from  P_LANDOWNER_PTYPE_LY_QTY where landowner_ptype_ly_id="
					+ ids.get(0);
			db.executeUpdate(del);
			// delete from parent table
			del = "delete from P_LANDOWNER_PTYPE_LY where id=" + ids.get(0);
			db.executeUpdate(del);
		}
	}

	public void updateOccupantTypeMaxNum(String schema, String site_id,
			String occupant_type, String max_num) {
		logger.info("Set occupant type '" + occupant_type
				+ "' maximum number as " + max_num);

		db.resetSchema(schema);

		String query = "SELECT ppatgr.id id FROM P_PRD_ADM_TYPE_GRP_RST ppatgr, P_PRD_ADM_TYPE_GRP ppatg, "
				+ " P_ADMISSION_TYPE pdt WHERE "
				+ "ppatgr.prd_id ='"
				+ site_id
				+ "' AND ppatg.admission_type_grp_id = ppatgr.id "
				+ "AND ppatg.admission_type_id =pdt.id AND pdt.name ='"
				+ occupant_type + "'";

		List<String> ids = db.executeQuery(query, "id");
		query = "";
		if (ids != null && ids.size() > 0) {// UDPATE existing restriction
											// number
			query = "update P_PRD_ADM_TYPE_GRP_RST set restriction='" + max_num
					+ "' where id=" + ids.get(0);
			db.executeUpdate(query);
		} else {// add new one
			query = "select max(admission_type_grp_id) as id from P_PRD_ADM_TYPE_GRP";
			List<String> ids1 = db.executeQuery(query, "id");
			int maxId = 1;
			if (ids1 != null && ids1.size() > 0) {
				if (ids1.get(0).equalsIgnoreCase("null")) {
					maxId = 1;
				} else {
					maxId = Integer.parseInt(ids1.get(0)) + 1;
				}
			}
			// insert occupant type group
			String sql = "insert into P_PRD_ADM_TYPE_GRP (select " + maxId
					+ ",id from P_ADMISSION_TYPE where name='" + occupant_type
					+ "')";
			db.executeUpdate(sql);
			// connect with occupant type group with a specific product and set
			// restriction number
			sql = "insert into P_PRD_ADM_TYPE_GRP_RST values(" + maxId + ","
					+ site_id + "," + max_num + ")";
			db.executeUpdate(sql);
		}
	}

	public void insertDetailConfigInfoForSpecificPrdGrpAndLocation(
			String prdGrpID, String locationID, String configType,
			String configValue, String schema) {
		logger.info("Insert detail configure info for specific product group and location.");
		db.resetSchema(schema);

		String sql = "INSERT INTO D_PRD_GRP_LOC_CFG (ID,PRD_GRP_ID,CONFIG_TYPE,CONFIG_VALUE,LOC_ID) "
				+ "VALUES(contract_seq.nextval,"
				+ prdGrpID
				+ ","
				+ configType
				+ ",'" + configValue + "',";
		if (StringUtil.isEmpty(locationID)) {
			sql = sql + "null)";
		} else
			sql = sql + locationID + ")";

		db.executeUpdate(sql);
	}

	public void deleteDetailConfigInfoForSpecificPrdGrpAndLocation(
			String prdGrpID, String locationID, String configType, String schema) {
		logger.info("Delete detail configure info of specific product group and location.");
		db.resetSchema(schema);

		String sql = "delete from D_PRD_GRP_LOC_CFG where prd_grp_id = "
				+ prdGrpID + " and config_type = " + configType
				+ " and loc_id ";
		if (StringUtil.isEmpty(locationID)) {
			sql = sql + "is null";
		} else {
			sql = sql + "= " + locationID;
		}

		db.executeUpdate(sql);
	}

	public String getCustomerPassNumberAddingBySupport(String custmail,
			String prgram) {
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();

		db.connect();
		String query = "select * from d_fie_enroll_cust_to_program where CUST_EMAIL='"
				+ custmail + "' And LOYALTY_PROGRAM_NAME='" + prgram+"'";
		String target =TestProperty.getProperty("target_env");
		String col=target.toUpperCase()+"_Result";
				
	    String result=db.executeQuery(query,col,0);
		db.disconnect();
		return result;
	}
	
	/**
	 * Get facility's parent name
	 * @param schema
	 * @param facilityId
	 * @param parentLevel
	 * @return
	 */
	
	public String getFacilityParentName(String schema, String facilityId, int parentLevel) {
		db.resetSchema(schema);
		String query = StringUtil.EMPTY;
		if(parentLevel==0){
			query = "select dscr from d_loc where id = (select parent_id from d_loc where id="+facilityId+")";
		}else query = "select dscr from d_loc where id = (select parent_id from d_loc where id=(select parent_id from d_loc where id="+facilityId+"))";
		List<String> results = db.executeQuery(query, "DSCR");
		if(results.size()<1){
			throw new ItemNotFoundException("Can't find any result when facility id="+facilityId);
		}else return results.get(0);
	}
	
	public String getFacilityParentName(String schema, String facilityId) {
		return getFacilityParentName(schema, facilityId, 0);
	}
	
	
	public List<String> getActivitySessionDatesById(String schema, String id) {
		db.resetSchema(schema);
		String sql = "select meet_date from P_ACTIVITY_DATE where activity_id=" + id;
		logger.info("Execute query: " + sql);
		List<String> dates = db.executeQuery(sql, "MEET_DATE");
		if (dates.size() < 1) {
			throw new ErrorOnDataException(
					"Can't find any record identified by activity product id - " + id);
		}
		return dates;
	}
	
	public String getActivitySessionDateById(String schema, String id){
		List<String> dates = this.getActivitySessionDatesById(schema, id);
		String date = dates.get(0).split(" ")[0];
		return date;
	}
	
	public List<String> getCustPassTypeInDB(String schema){
		db.resetSchema(schema);
		String sql = "select name from D_REF_CUST_PASS_TYPE where active_ind=1";
		logger.info("Execute query: " + sql);
		List<String> names = db.executeQuery(sql, "Name");
		return names;
	}
	
	public String getPrivilegeStatus(String code, String schema) {
		db.resetSchema(schema);
		logger.debug("Get privilege "+code+" status");

		String query = "select ACTIVE_IND from p_prd where prd_cd='" + code 	+ "' and product_cat_id=10";
		List<String> result = db.executeQuery(query, "ACTIVE_IND");
		if(result==null || result.size()<1)
			throw new ItemNotFoundException("Could not find privilege "+code);
		return result.get(0);
	}
	
	//updated  by Summer.Date:2014/06/25 Reason: for lottery product there is no purchase Type, so the parameter purchaseTypeID should be null in the case of lottery
	public List<String> getHFTaxSchdIDByPrdIDFeeTypeAndPurchaseType (String prdID,String feeTypeID,String purchaseTypeID,String taxName,String schema){
		db.resetSchema(schema);
		logger.debug("Get product id  "+prdID+" Tax Schedule ID and Fee Type is " + feeTypeID + " and Purchase type = " + purchaseTypeID);
		
		String query = "select sch.id from p_tax_schedule sch inner join p_tax_type t on sch.tax_type_id = t.id"
				     + " where sch.active_ind =1 "
				     + " and sch.prd_id = " + prdID
				     + " and sch.fee_type_id = " + feeTypeID
				     + " and t.name = '" + taxName + "'";
		//purchaseTypeID is not null for privilege and must be null for lottery tax schedule
		if(null!=purchaseTypeID){
			query=query + " and sch.purchase_type = " + purchaseTypeID;
		}
			
		List<String> result = db.executeQuery(query, "id");
		return result;
	}	
	
	public String[] getHFTaxProcessInfo(String ordNum,String feeType,String schema){
		db.resetSchema(schema);
		
		logger.info("Get tax process info for order number = " + ordNum  + "; Fee type = " + feeType);
		String query = "select tax.id,tax.fee_type_id,tax.tax_schd_id,tax.rate_type_id,tax.tax_calculation_rate,tax.rate_applied,fee.rev_loc_id "
				     + "from o_ord_item_tax tax "
				     + "inner join o_ord_item_fee fee on tax.fee_id = fee.id "
				     + "inner join o_ord_item i on fee.ord_item_id = i.id "
				     + "inner join o_order o on o.id = i.ord_id "
				     + "where fee.fee_type_id = 32 "       //this is tax
				     + "and o.ord_num = '" + ordNum + "'"
				     + "and tax.fee_type_id = " + feeType;
	    String[] colNames = new String[]{"id","fee_type_id","tax_schd_id","rate_type_id","tax_calculation_rate","rate_applied","rev_loc_id"};
	    List<String[]> result = db.executeQuery(query, colNames);
	    if(result.size()<1){
	    	throw new ItemNotFoundException("System did not have any HF tax process info for order number = " + ordNum  + "; Fee type = " + feeType);
	    }
	    return result.get(0);
	}
	
	public String getRevenueLocationIDFromOrdItemInfo(String ordNum,String schema){
		db.resetSchema(schema);
		
		logger.info("Get revenue location id from order item info, order number = " + ordNum );
		
		String query = "select i.rev_loc_id from o_ord_item i inner join o_order o on o.id = i.ord_id "
				     + "where o.ord_num = '" + ordNum + "'";
		List<String> result = db.executeQuery(query, "rev_loc_id");
		if(result.size()<1){
	    	throw new ItemNotFoundException("System did not have any revenue location info at order item record for order number = " + ordNum);
		}
	    return result.get(0);
	}
	
	public void updateHuntDisplayOrder(String huntCode, String displayOrder, String schema){
		db.resetSchema(schema);
		String query = "update P_HUNT set display_order='"+displayOrder+"' where code = '"+huntCode+"' and status_id=1";
		db.executeUpdate(query);
	}
	
	public boolean verifySearchReservationByLoyaltyCardExists(String schema, int applicationId) {
		db.resetSchema(schema);
		String name = applicationId + "_ReservationSearchByLoyaltyCard";
		
		String sql = "select VALUE from X_PROP where NAME = '" + name + "'";
		logger.info("Execute query: " + sql);
		List<String> results = db.executeQuery(sql, "VALUE");
		if(results != null && results.size() > 0) return true;
		return false;
	}
	
	public void insertSearchReservationByLoyaltyCard(String schema, int applicationId) {
		String name = applicationId + "_ReservationSearchByLoyaltyCard";
		insertPropertyValue(schema, name, "ReservationSearchUI", "Boolean", "true");
	}
	
	public void updateSearchReservationByLoyaltyCard(String schema, int applicationId, boolean yes) {
		String name = applicationId + "_ReservationSearchByLoyaltyCard";
		updatePropertyValue(schema, name, String.valueOf(yes));
	}
	
	public void deleteSearchReservationByLoyaltyCard(String schema, int applicationId) {
		String name = applicationId + "_ReservationSearchByLoyaltyCard";
		deletePropertyValue(schema, name);
	}
	
	public String getPartnerInstanceIDByPrivilegeNum(String privilegeNum, String schema){
		logger.info("Get partner instance id for privilege with privilege number = " + privilegeNum);
		db.resetSchema(schema);
		
		String query = "select partner_inst_id from o_priv_inst where priv_number = " + privilegeNum;
		logger.info("Execute query: " + query);
		List<String> results = db.executeQuery(query, "partner_inst_id");
		if(results.size()<1){
			throw new ItemNotFoundException("Did not get any partner instance id info " +
					"for privilege with privilege number = " + privilegeNum );
		}
		return results.get(0);
	}
	
	public String getPrimaryInstanceIDByPrivilegeNum(String privilegeNum, String schema){
		logger.info("Get primary instance id for privilege with privilege number = " + privilegeNum);
		db.resetSchema(schema);
		
		String query = "select primary_inst_id from o_priv_inst where priv_number = " + privilegeNum;
		logger.info("Execute query: " + query);
		List<String> results = db.executeQuery(query, "primary_inst_id");
		if(results.size()<1){
			throw new ItemNotFoundException("Did not get any primary instance id info " +
					"for privilege with privilege number = " + privilegeNum );
		}
		return results.get(0);
	}
	
	public int getPrivilegeVoidReversalTransactionCoverageValueFromDB(String schema){
		db.resetSchema(schema);
		
		String sql = "select VALUE from X_PROP where NAME = 'PrivilegeVoidReversalTransactionCoverage'";
		logger.info("Execute query: " + sql);
		List<String> results = db.executeQuery(sql, "VALUE");
		if(results != null && results.size() > 0){
			return Integer.valueOf(results.get(0));
		}else throw new ItemNotFoundException("Can't find any PrivilegeVoidReversalTransactionCoverage value");
	}
	
	public int getPermitInventoryLocked(String schema, String parkID, String entranceName, String date){//DateFormat:02-Aug-14
		db.resetSchema(schema);
		String sql = "select (capacity-available) as lockedInventory from i_permit_inv where " +
				"entrance_id in (select prd_id from p_prd where prd_name= '"+entranceName+"' and park_id = '"+parkID+"') and start_date = '"+DateFunctions.formatDate(date, "dd-MMM-yy")+"'";
		List<String> results = db.executeQuery(sql, "lockedInventory");
		if(results != null && results.size() > 0){
			return Integer.valueOf(results.get(0));
		}else throw new ItemNotFoundException("Can't find any permit inventory value");
	}
	//added by summer 
	public List<String> getBypassAllocationIndicator(String schema){
		db.resetSchema(schema);
		logger.info("Get Bypass Allocation Indicator for contract");
		String query="Select Value From X_Prop Where NAME = 'BypassAllocationInd' and NAMESPACE = 'Contract'";
		List<String> result=db.executeQuery(query,"Value");
		return result;

	}
	//added by summer 
	public boolean insertBypassAllocationIndicator(String indicator,String schema){
		List<String> result=getBypassAllocationIndicator(schema);
		boolean isInserted = false;
		if(null==result||result.size()<1){
			logger.info("Insert Bypass Allocation Indicator for contract");
			String sql="INSERT INTO X_PROP(ID, " +
					"NAME,NAMESPACE,TYPE,VALUE) VALUES ( CONTRACT_SEQ.nextval, 'BypassAllocationInd','Contract','Boolean','"+indicator+"' ) ";
			db.executeUpdate(sql);
			isInserted=true;
		}
		return isInserted;
	}
	//added by summer 
	public void deleteBypassAllocationIndicator(String schema){
		List<String> result=getBypassAllocationIndicator(schema);
		if(null!=result&&result.size()>0){
			logger.info("Delete Bypass Allocation Indicator for contract");
			String sql="DELETE from X_PROP where name = 'BypassAllocationInd' and NAMESPACE = 'Contract' ";
			db.executeUpdate(sql);
		}
	}
	
	public List<String[]> getSiteInvStatus(String siteID, String arriveDate,
			String depatureDate, String schema) {
		db.resetSchema(schema);

		String query = "select * from I_INV_USED where prd_id='" + siteID
				+ "' and from_time<=to_date('" + arriveDate
				+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')"
				+ " and to_time>=to_date('" + depatureDate
				+ " 00:00:00','mm-dd-yyyy hh24:mi:ss')";
		
		String[] content = { "USED_ID", "USAGE_TYPE_ID", "ORDER_ITEM_ID",
				"BOOKING_ID" };

		logger.info("Check site inventory using: "+query);
		List<String[]> queryResults = db.executeQuery(query, content);
		
		return queryResults;
	}
	
	public String getOrderNumber(String orderItemID, String schema){
		logger.info("Get Order Number from Table: O_order.");
		
		db.resetSchema(schema);
		
		String query = "Select O_ORDER.ORD_NUM From O_ORD_ITEM INNER JOIN O_ORDER ON O_ORD_ITEM.ORD_ID = O_ORDER.ID  Where O_ORD_ITEM.ID ='" + orderItemID
				+ "'";
		
		logger.info("Execute SQL:" + query);
		List<String> list = db.executeQuery(query, "ORD_NUM");
		if (null == list || list.size() < 0) {
			throw new ItemNotFoundException(
					"Can't get order number by given order item id:" + orderItemID);
		}
		
		String orderNumber = list.get(0).trim();
		logger.info("Order Number is:" + orderNumber);
		return orderNumber;
		
	}
}
