package com.activenetwork.qa.awo.sql;

import com.activenetwork.qa.awo.util.AwoDatabase;

public class AddFeeSchSql {

	public AwoDatabase db;

	public AddFeeSchSql() {
		db = AwoDatabase.getInstance();
	}

	public AddFeeSchSql(AwoDatabase omConn) {
		this.db = omConn;
	}

	/**
	 * Add a fee schedule, 2 is use fee, 4 is transaction fee, 12 is attribution fee.
	 * @param fd - fee schedule data
	 */
	public void addFeeSchedule(FeeSchDataForDB fd) {
		if (fd.feeTypeId.equals("2")) {
			addUseFee(fd);
		} else if (fd.feeTypeId.equals("4")) {
			addTransactionFee(fd);
		} else if (fd.feeTypeId.equals("12")) {
			addAttrFee(fd);
		} else {
			System.out.println("Unknown Fee Type");
		}
	}

	/**
	 * Retrieve the maximum fee schedule id where column name is fee_id in DB.
	 * @return - maximum fee schedule id
	 */
	public String getMaxFeeId() {
		String query = "select max(FEE_ID) as fee_id from P_FEE";
		return ((String) db.executeQuery(query, "fee_id").get(0)).trim();
	}

	/**
	 * Retrieve the maximum fee schedule id where column name is fee_schd_id in DB.
	 * @return - maximum fee schedule id
	 */
	public String getFeeSchdId() {
		String query = "select max(ID) as fee_schd_id from P_FEE_SCHD";
		return ((String) db.executeQuery(query, "fee_schd_id").get(0))
				.trim();
	}

	/**
	 * Set up Use fee and update sequence table.
	 * @param fd - fee schedule data
	 */
	public void addUseFee(FeeSchDataForDB fd) {

		/**
		 * get max IDs from table
		 */

		String query = "select max(FEE_ID) as fee_id from P_FEE";
		int fee_id = Integer.parseInt(((String) db.executeQuery(query,
				"fee_id").get(0)).trim());
		System.out.println("Fee_ID: " + fee_id++);

		query = "select max(ID) as fee_cond_id from P_FEE_COND";
		int fee_cond_id = Integer.parseInt((String) db.executeQuery(query,
				"fee_cond_id").get(0));
		System.out.println("Fee_Cond_ID" + fee_cond_id++);

		query = "select max(ID) as fee_schd_id from P_FEE_SCHD";
		int fee_schd_id = Integer.parseInt((String) db.executeQuery(query,
				"fee_schd_id").get(0));

		System.out.println("Fee Schedule Id: " + fee_schd_id++);

		query = "select max(ID) as per_time_fee_schd_id from P_PER_TIME_FEE_SCHD";
		int per_time_fee_schd_id = Integer.parseInt((String) db
				.executeQuery(query, "per_time_fee_schd_id").get(0));
		System.out.println("per_time_fee_schd_id: " + per_time_fee_schd_id++);

		/**
		 * insert Use fee schedule
		 */

		query = "select *  from D_LOC where ID = " + fd.locId;

		int locLength = ((String) db.executeQuery(query, "CD").get(0))
				.length();

		System.out.println("fee_id : " + fee_id);

		//	String grpID = fd.grpId == -1 ? "NULL" : fd.grpId + "";
		//	String prdID = fd.prdId == -1 ? "NULL" : fd.prdId + "";

		query = "insert into P_FEE (FEE_ID, FEE_TYPE_ID, GRP_ID, LOC_ID, PRD_ID, LOC_LENGTH)"
				+ " values ("
				+ fee_id
				+ ","
				+ fd.feeTypeId
				+ ","
				+ fd.grpId
				+ "," + fd.locId + "," + fd.prdId + "," + locLength + ")";

		/**
		 * for setup Fee Type and Assignment
		 */
		db.executeUpdate(query);

		query = "insert into P_FEE_COND (ID, COND_ID, FEE_ID, FEE_TYPE_ID) "
				+ " values (" + fee_cond_id + "," + fd.condId + ", " + fee_id
				+ ", NULL)";
		/**
		 * for setup Condition
		 */
		db.executeUpdate(query);

		if (fd.feeTypeId.equals("12")) {
			fd.acctId = "12"; // 
		}

		query = "insert into P_FEE_SCHD (ID, ACCT_ID, ACTIVE_IND, DELETE_IND, EFFECT_DATE, END_DATE, FEE_COND_ID, FEE_ID, START_DATE, "
				+ " TRAN_DAYS, TRAN_OCCUR_ID, TRAN_TYPE_ID)"
				+ " values ("
				+ fee_schd_id
				+ ", "
				+ fd.acctId
				+ ", '1', '0', TO_DATE('"
				+ fd.effectDate
				+ " 12:00:00 AM', 'dd-Mon-yyyy HH:MI:SS AM'), TO_DATE('"
				+ fd.endInv
				+ " 12:00:00 AM', 'dd-Mon-yyyy HH:MI:SS AM'),"
				+ fee_cond_id
				+ ", NULL, TO_DATE('"
				+ fd.startInv
				+ " 12:00:00 AM',  'dd-Mon-yyyy HH:MI:SS AM'), 0, NULL, NULL)";

		db.executeUpdate(query);

		/**
		 * for setup Use Fee
		 */
		per_time_fee_schd_id++;

		query = "insert into P_PER_TIME_FEE_SCHD (ID, FEE_SCHD_ID, FRI_FEE_AMT, HOLIDAY_FEE_AMT, MON_FEE_AMT, PER_TIME_FEE_AMT, PER_TIME_ID, PER_TIME_QTY, PERSON_INCR_RATE, SAT_FEE_AMT, SUN_FEE_AMT, THU_FEE_AMT, TUE_FEE_AMT, VEHICLE_INCR_RATE, WED_FEE_AMT) "
				+ "values ("
				+ per_time_fee_schd_id
				+ ","
				+ fee_schd_id
				+ ", 0, 0, 0, 0, 905, 0, 0, 0, 0, 0, 0, 0, 0)";

		db.executeUpdate(query);
		per_time_fee_schd_id++;

		query = "insert into P_PER_TIME_FEE_SCHD (ID, FEE_SCHD_ID, FRI_FEE_AMT, HOLIDAY_FEE_AMT, MON_FEE_AMT, PER_TIME_FEE_AMT, PER_TIME_ID, PER_TIME_QTY, PERSON_INCR_RATE, SAT_FEE_AMT, SUN_FEE_AMT, THU_FEE_AMT, TUE_FEE_AMT, VEHICLE_INCR_RATE, WED_FEE_AMT) "
				+ "values ("
				+ per_time_fee_schd_id
				+ ","
				+ fee_schd_id
				+ ", "
				+ fd.friRate
				+ ", 0,"
				+ fd.monRate
				+ ","
				+ fd.nightlyRate
				+ ", 909, 0, 0,"
				+ fd.satRate
				+ ","
				+ fd.sunRate
				+ ","
				+ fd.thurRate + "," + fd.tuesRate + ", 0," + fd.wedRate + ")";

		db.executeUpdate(query);

		per_time_fee_schd_id++;

		query = "insert into P_PER_TIME_FEE_SCHD (ID, FEE_SCHD_ID, FRI_FEE_AMT, HOLIDAY_FEE_AMT, MON_FEE_AMT, PER_TIME_FEE_AMT, PER_TIME_ID, PER_TIME_QTY, PERSON_INCR_RATE, SAT_FEE_AMT, SUN_FEE_AMT, THU_FEE_AMT, TUE_FEE_AMT, VEHICLE_INCR_RATE, WED_FEE_AMT) "
				+ "values ("
				+ per_time_fee_schd_id
				+ ","
				+ fee_schd_id
				+ ", 0, 0, 0,"
				+ fd.weeklyRate
				+ ", 903, 0, 0, 0, 0, 0, 0, 0, 0)";

		db.executeUpdate(query);
		per_time_fee_schd_id++;

		query = "insert into P_PER_TIME_FEE_SCHD (ID, FEE_SCHD_ID, FRI_FEE_AMT, HOLIDAY_FEE_AMT, MON_FEE_AMT, PER_TIME_FEE_AMT, PER_TIME_ID, PER_TIME_QTY, PERSON_INCR_RATE, SAT_FEE_AMT, SUN_FEE_AMT, THU_FEE_AMT, TUE_FEE_AMT, VEHICLE_INCR_RATE, WED_FEE_AMT) "
				+ "values ("
				+ per_time_fee_schd_id
				+ ","
				+ fee_schd_id
				+ ", 0, 0, 0,"
				+ fd.monthlyRate
				+ ", 904, 0, 0, 0, 0, 0, 0, 0, 0)";

		db.executeUpdate(query);
		per_time_fee_schd_id++;

		query = "insert into P_PER_TIME_FEE_SCHD (ID, FEE_SCHD_ID, FRI_FEE_AMT, HOLIDAY_FEE_AMT, MON_FEE_AMT, PER_TIME_FEE_AMT, PER_TIME_ID, PER_TIME_QTY, PERSON_INCR_RATE, SAT_FEE_AMT, SUN_FEE_AMT, THU_FEE_AMT, TUE_FEE_AMT, VEHICLE_INCR_RATE, WED_FEE_AMT) "
				+ "values ("
				+ per_time_fee_schd_id
				+ ","
				+ fee_schd_id
				+ ", 0, 0, 0, 0, 910, 0, 0, 0, 0, 0, 0, 0, 0)";

		db.executeUpdate(query);

		/**
		 * update sequence table 
		 */

		query = "update sequence set seq_count = (select max(fee_id) + 1 from P_FEE) where seq_name = 'SEQ_P_FEE'";
		db.executeUpdate(query);

		query = "update sequence set seq_count = (select max(id) + 1 from P_FEE_COND) where seq_name = 'SEQ_P_FEE_COND'";

		db.executeUpdate(query);

		query = "update sequence set seq_count = (select max(id) + 1 from P_FEE_SCHD) where seq_name = 'SEQ_P_FEE_SCHD'";

		db.executeUpdate(query);

		query = "update sequence set seq_count = (select max(id) + 1 from P_PER_TIME_FEE_SCHD) where seq_name = 'SEQ_P_PER_TIME_FEE_SCHD'";

		db.executeUpdate(query);

	}

	/**
	 * Setup Transaction Fee and update sequence table.
	 * @param fd - fee schedule data
	 */
	public void addTransactionFee(FeeSchDataForDB fd) {

		/**
		 * get max IDs from table
		 */

		String query = "select max(FEE_ID) as fee_id from P_FEE";
		int fee_id = Integer.parseInt(((String) db.executeQuery(query,
				"fee_id").get(0)).trim());
		System.out.println("Fee_ID: " + fee_id++);

		query = "select max(ID) as fee_cond_id from P_FEE_COND";
		int fee_cond_id = Integer.parseInt((String) db.executeQuery(query,
				"fee_cond_id").get(0));
		System.out.println("Fee_Cond_ID" + fee_cond_id++);

		query = "select max(ID) as fee_schd_id from P_FEE_SCHD";
		int fee_schd_id = Integer.parseInt((String) db.executeQuery(query,
				"fee_schd_id").get(0));

		System.out.println("Fee Schedule Id: " + fee_schd_id++);

		query = "select max(ID) as other_fee_schd_id from P_OTHER_FEE_SCHD";
		int other_fee_schd_id = Integer.parseInt((String) db.executeQuery(
				query, "other_fee_schd_id").get(0));

		System.out.println("other_fee_schd_id: " + other_fee_schd_id++);

		/**
		 * insert fee schedule
		 */

		query = "select *  from D_LOC where ID = " + fd.prdId;

		int locLength = ((String) db.executeQuery(query, "CD").get(0))
				.length();

		System.out.println("fee_id : " + fee_id);

		//	String grpID = fd.grpId == -1 ? "NULL" : fd.grpId + "";
		//	String prdID = fd.prdId == -1 ? "NULL" : fd.prdId + "";

		query = "insert into P_FEE (FEE_ID, FEE_TYPE_ID, GRP_ID, LOC_ID, PRD_ID, LOC_LENGTH)"
				+ " values ("
				+ fee_id
				+ ","
				+ fd.feeTypeId
				+ ","
				+ fd.grpId
				+ "," + fd.locId + "," + fd.grpId + "," + locLength + ")";

		/**
		 * for setup Fee Type and Assignment
		 */
		db.executeUpdate(query);

		query = "select max(ID) as id from P_ATTR_FEE";
		int attr_fee_schd_id = Integer.parseInt((String) db.executeQuery(
				query, "id").get(0));

		attr_fee_schd_id++;

		query = "insert into P_ATTR_FEE (ID, FEE_ID, GRP_ATTR_ID, GRP_ATTR_CNST_ID) "
				+ "values ("
				+ attr_fee_schd_id
				+ ","
				+ fee_id
				+ ", 148, 104200)";

		query = "insert into P_FEE_COND (ID, COND_ID, FEE_ID, FEE_TYPE_ID) "
				+ " values (" + fee_cond_id + "," + fd.condId + ", " + fee_id
				+ ", NULL)";
		/**
		 * for setup Condition
		 */
		db.executeUpdate(query);

		if (fd.feeTypeId.equals("12")) {
			fd.acctId = "12"; // 
		}

		query = "insert into P_FEE_SCHD (ID, ACCT_ID, ACTIVE_IND, DELETE_IND, EFFECT_DATE, END_DATE, FEE_COND_ID, FEE_ID, START_DATE, "
				+ " TRAN_DAYS, TRAN_OCCUR_ID, TRAN_TYPE_ID)"
				+ " values ("
				+ fee_schd_id
				+ ", "
				+ fd.acctId
				+ ", '1', '0', TO_DATE('"
				+ fd.effectDate
				+ " 12:00:00 AM', 'dd-Mon-yyyy HH:MI:SS AM'), TO_DATE('"
				+ fd.endInv
				+ " 12:00:00 AM', 'dd-Mon-yyyy HH:MI:SS AM'),"
				+ fee_cond_id
				+ ", NULL, TO_DATE('"
				+ fd.startInv
				+ " 12:00:00 AM',  'dd-Mon-yyyy HH:MI:SS AM'), 0, NULL, NULL)";

		db.executeUpdate(query);

		/**
		 * for setup Transaction Fee
		 */

		other_fee_schd_id++;

		query = "insert into P_OTHER_FEE_SCHD (ID, FEE_AMT, FEE_SCHD_ID, MIN_VOLUMN, MAX_VOLUME)"
				+ "values ("
				+ other_fee_schd_id
				+ ","
				+ fd.minValue
				+ ","
				+ fee_schd_id + "," + fd.maxValue + ", NULL)";

		/**
		 * update sequence table 
		 */

		query = "update sequence set seq_count = (select max(fee_id) + 1 from P_FEE) where seq_name = 'SEQ_P_FEE'";
		db.executeUpdate(query);

		query = "update sequence set seq_count = (select max(id) + 1 from P_FEE_COND) where seq_name = 'SEQ_P_FEE_COND'";

		db.executeUpdate(query);

		query = "update sequence set seq_count = (select max(id) + 1 from P_FEE_SCHD) where seq_name = 'SEQ_P_FEE_SCHD'";

		db.executeUpdate(query);

		query = "update sequence set seq_count = (select max(id) + 1 from P_OTHER_FEE_SCHD) where seq_name = 'SEQ_P_OTHER_FEE_SCHD'";

		db.executeUpdate(query);
	}

	/**
	 * Set up attribution fee and update sequence table.
	 * @param fd - fee schedule data
	 */
	public void addAttrFee(FeeSchDataForDB fd) {

		/**
		 * get max IDs from table
		 */

		String query = "select max(FEE_ID) as fee_id from P_FEE";
		int fee_id = Integer.parseInt(((String) db.executeQuery(query,
				"fee_id").get(0)).trim());
		System.out.println("Fee_ID: " + fee_id++);

		query = "select max(ID) as fee_cond_id from P_FEE_COND";
		int fee_cond_id = Integer.parseInt((String) db.executeQuery(query,
				"fee_cond_id").get(0));
		System.out.println("Fee_Cond_ID" + fee_cond_id++);

		query = "select max(ID) as fee_schd_id from P_FEE_SCHD";
		int fee_schd_id = Integer.parseInt((String) db.executeQuery(query,
				"fee_schd_id").get(0));
		System.out.println("Fee Schedule Id: " + fee_schd_id++);

		query = "select max(ID) as per_time_fee_schd_id from P_PER_TIME_FEE_SCHD";
		int per_time_fee_schd_id = Integer.parseInt((String) db
				.executeQuery(query, "per_time_fee_schd_id").get(0));
		System.out.println("per_time_fee_schd_id: " + per_time_fee_schd_id++);

		query = "select max(ID) as other_fee_schd_id from P_OTHER_FEE_SCHD";
		int other_fee_schd_id = Integer.parseInt((String) db.executeQuery(
				query, "other_fee_schd_id").get(0));
		System.out.println("other_fee_schd_id: " + other_fee_schd_id++);

		/**
		 * insert fee schedule
		 */

		query = "select *  from D_LOC where ID = " + fd.prdId;
		int locLength = ((String) db.executeQuery(query, "CD").get(0))
				.length();
		System.out.println("fee_id : " + fee_id);

		//String grpID = fd.grpId == -1 ? "NULL" : fd.grpId + "";
		//String prdID = fd.prdId == -1 ? "NULL" : fd.prdId + "";

		query = "insert into P_FEE (FEE_ID, FEE_TYPE_ID, GRP_ID, LOC_ID, PRD_ID, LOC_LENGTH)"
				+ " values ("
				+ fee_id
				+ ","
				+ fd.feeTypeId
				+ ","
				+ fd.grpId
				+ "," + fd.locId + "," + fd.prdId + "," + locLength + ")";
		db.executeUpdate(query);

		/**
		 * for setup Fee Type and Assignment
		 */

		query = "select max(ID) as id from P_ATTR_FEE";
		int attr_fee_schd_id = Integer.parseInt((String) db.executeQuery(
				query, "id").get(0));

		attr_fee_schd_id++;

		query = "insert into P_ATTR_FEE (ID, FEE_ID, GRP_ATTR_ID, GRP_ATTR_CNST_ID) "
				+ "values ("
				+ attr_fee_schd_id
				+ ","
				+ fee_id
				+ ", 148, 104200)";
		db.executeUpdate(query);

		query = "insert into P_FEE_COND (ID, COND_ID, FEE_ID, FEE_TYPE_ID) "
				+ " values (" + fee_cond_id + "," + fd.condId + ", " + fee_id
				+ ", NULL)";
		db.executeUpdate(query);

		/**
		 * for setup Condition
		 */

		if (fd.feeTypeId.equals("12")) {
			fd.acctId = "12"; // 
		}

		query = "insert into P_FEE_SCHD (ID, ACCT_ID, ACTIVE_IND, DELETE_IND, EFFECT_DATE, END_DATE, FEE_COND_ID, FEE_ID, START_DATE, "
				+ " TRAN_DAYS, TRAN_OCCUR_ID, TRAN_TYPE_ID)"
				+ " values ("
				+ fee_schd_id
				+ ", "
				+ fd.acctId
				+ ", '1', '0', TO_DATE('"
				+ fd.effectDate
				+ " 12:00:00 AM', 'dd-Mon-yyyy HH:MI:SS AM'), TO_DATE('"
				+ fd.endInv
				+ " 12:00:00 AM', 'dd-Mon-yyyy HH:MI:SS AM'),"
				+ fee_cond_id
				+ ", NULL, TO_DATE('"
				+ fd.startInv
				+ " 12:00:00 AM',  'dd-Mon-yyyy HH:MI:SS AM'), 0, NULL, NULL)";
		db.executeUpdate(query);

		/**
		 * for setup Use Fee
		 */

		per_time_fee_schd_id++;
		query = "insert into P_PER_TIME_FEE_SCHD (ID, FEE_SCHD_ID, FRI_FEE_AMT, HOLIDAY_FEE_AMT, MON_FEE_AMT, PER_TIME_FEE_AMT, PER_TIME_ID, PER_TIME_QTY, PERSON_INCR_RATE, SAT_FEE_AMT, SUN_FEE_AMT, THU_FEE_AMT, TUE_FEE_AMT, VEHICLE_INCR_RATE, WED_FEE_AMT) "
				+ "values ("
				+ per_time_fee_schd_id
				+ ","
				+ fee_schd_id
				+ ", 0, 0, 0, 0, 905, 0, 0, 0, 0, 0, 0, 0, 0)";
		db.executeUpdate(query);

		per_time_fee_schd_id++;
		query = "insert into P_PER_TIME_FEE_SCHD (ID, FEE_SCHD_ID, FRI_FEE_AMT, HOLIDAY_FEE_AMT, MON_FEE_AMT, PER_TIME_FEE_AMT, PER_TIME_ID, PER_TIME_QTY, PERSON_INCR_RATE, SAT_FEE_AMT, SUN_FEE_AMT, THU_FEE_AMT, TUE_FEE_AMT, VEHICLE_INCR_RATE, WED_FEE_AMT) "
				+ "values ("
				+ per_time_fee_schd_id
				+ ","
				+ fee_schd_id
				+ ", "
				+ fd.friRate
				+ ", 0,"
				+ fd.monRate
				+ ","
				+ fd.nightlyRate
				+ ", 909, 0, 0,"
				+ fd.satRate
				+ ","
				+ fd.sunRate
				+ ","
				+ fd.thurRate + "," + fd.tuesRate + ", 0," + fd.wedRate + ")";
		db.executeUpdate(query);

		per_time_fee_schd_id++;
		query = "insert into P_PER_TIME_FEE_SCHD (ID, FEE_SCHD_ID, FRI_FEE_AMT, HOLIDAY_FEE_AMT, MON_FEE_AMT, PER_TIME_FEE_AMT, PER_TIME_ID, PER_TIME_QTY, PERSON_INCR_RATE, SAT_FEE_AMT, SUN_FEE_AMT, THU_FEE_AMT, TUE_FEE_AMT, VEHICLE_INCR_RATE, WED_FEE_AMT) "
				+ "values ("
				+ per_time_fee_schd_id
				+ ","
				+ fee_schd_id
				+ ", 0, 0, 0,"
				+ fd.weeklyRate
				+ ", 903, 0, 0, 0, 0, 0, 0, 0, 0)";
		db.executeUpdate(query);

		per_time_fee_schd_id++;
		query = "insert into P_PER_TIME_FEE_SCHD (ID, FEE_SCHD_ID, FRI_FEE_AMT, HOLIDAY_FEE_AMT, MON_FEE_AMT, PER_TIME_FEE_AMT, PER_TIME_ID, PER_TIME_QTY, PERSON_INCR_RATE, SAT_FEE_AMT, SUN_FEE_AMT, THU_FEE_AMT, TUE_FEE_AMT, VEHICLE_INCR_RATE, WED_FEE_AMT) "
				+ "values ("
				+ per_time_fee_schd_id
				+ ","
				+ fee_schd_id
				+ ", 0, 0, 0,"
				+ fd.monthlyRate
				+ ", 904, 0, 0, 0, 0, 0, 0, 0, 0)";
		db.executeUpdate(query);

		per_time_fee_schd_id++;
		query = "insert into P_PER_TIME_FEE_SCHD (ID, FEE_SCHD_ID, FRI_FEE_AMT, HOLIDAY_FEE_AMT, MON_FEE_AMT, PER_TIME_FEE_AMT, PER_TIME_ID, PER_TIME_QTY, PERSON_INCR_RATE, SAT_FEE_AMT, SUN_FEE_AMT, THU_FEE_AMT, TUE_FEE_AMT, VEHICLE_INCR_RATE, WED_FEE_AMT) "
				+ "values ("
				+ per_time_fee_schd_id
				+ ","
				+ fee_schd_id
				+ ", 0, 0, 0, 0, 910, 0, 0, 0, 0, 0, 0, 0, 0)";
		db.executeUpdate(query);

		/**
		 * update sequence table 
		 */

		query = "update sequence set seq_count = (select max(fee_id) + 1 from P_FEE) where seq_name = 'SEQ_P_FEE'";
		db.executeUpdate(query);

		query = "update sequence set seq_count = (select max(id) + 1 from P_FEE_COND) where seq_name = 'SEQ_P_FEE_COND'";
		db.executeUpdate(query);

		query = "update sequence set seq_count = (select max(id) + 1 from P_FEE_SCHD) where seq_name = 'SEQ_P_FEE_SCHD'";
		db.executeUpdate(query);

		query = "update sequence set seq_count = (select max(id) + 1 from P_PER_TIME_FEE_SCHD) where seq_name = 'SEQ_P_PER_TIME_FEE_SCHD'";
		db.executeUpdate(query);

		query = "update sequence set seq_count = (select max(id) + 1 from P_ATTR_FEE) where seq_name = 'SEQ_P_ATTR_FEE'";
		db.executeUpdate(query);
	}

	public static void main(String[] args) {
		int fd = -2;
		String grpID = fd == -1 ? "NULL" : fd + "";

		System.out.print(grpID);
	}

}
