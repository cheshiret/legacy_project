/*
 * Created on Sep 7, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author jdu
 *
 *This class can automatically generate all kinds of bank file for deposit reconciliation ,credit card settlement
 *and credit card reconciliation
 */

public class GenerateBankFile {

	private static AwoDatabase dbconn = AwoDatabase.getInstance();

	private static String env = TestProperty.getProperty("target_env")
			.toLowerCase();

	private static String schema_prefix = TestProperty.getProperty(env
			+ ".db.schema.prefix");

	/**
	 * This method can automatically generate a Lockbox Statement file for deposit reconciliation
	 * for NRRS contract. The Lockbox file format is based on the requirements for Orms 277 release.
	 * 
	 * To generate a Lockbox Statement file for a set of deposits, you need to define 2 variables: 
	 * 1. String deposits[] - a String array contains deposit ids. 
	 * Example: String deposits[]={"123,456", "789"}; - contains 2 batches. 
	 * Means:
	 * 		The 1st batch has deposits 123 and 456
	 * 		The 2nd batch has deposits 678
	 * 2. String payType[]	-  a String array defines the payment Type for the corresponding deposits set.
	 * 1 - Call Center, 2 - Field, 3 - Return
	 * Example: String payType[]={"2","3"} - contains the pay Type for the above 2 batches.
	 * Means: 
	 * 		The 1st batch has pay type as Field
	 * 		The 2nd batch has pay type as Return
	 * String adjustedAmounts[] - change amount to the given amount,by default it is should be null
	 * we can get unreconcile/partially reconciled and fully reconcile deposit by modify amount here 
	 */
	public static String generateLockBoxFile(String[] deposits,
			String[] payType, String contract,String[] adjustedAmounts) throws IOException {
		String schema = schema_prefix + contract;
		String timeStamp = DateFunctions.getLongDateStamp();
		String fileName = AwoUtil.RESOURCES_FOLDER + "/LB" + timeStamp + ".dat";//txt";

		//		Write record1 - Start of transmission line
		String record1 = "10000000000000111000012" + timeStamp;
		FileUtil.writeLog(fileName, record1);

		//Write record2 - service record
		String record2 = "200000000000111000012000000000040008000801";
		FileUtil.writeLog(fileName, record2);

		int batches = deposits.length;

		for (int i = 0; i < batches; i++) {
			//write record5 - batch start record
			String batch = new DecimalFormat("000").format(i + 1);
			String record5 = "5" + batch + "0000281470"
					+ DateFunctions.getShortDateStamp()
					+ "00000000000111000012" + payType[i] + "470819";

			FileUtil.writeLog(fileName, record5);
			dbconn.resetSchema(schema);
			dbconn.connect();

			String nsf = "";
			if (payType[i].equalsIgnoreCase("3")) {
				nsf = "and fl.PMT_TYPE_ID=81055";
			}
			//process non-cash depositable records
			String queryStr = "select tb.*, fa1.PMT_ATTR_VALUE as Check_num, fa2.PMT_ATTR_VALUE as Remitter_name, fa3.PMT_ATTR_VALUE as Transit, fa4.PMT_ATTR_VALUE as Account# "
					+ "from ( select fp.id as pmt_id, fd.id as Deposit_ID, fd.BILLING_NBR as Billing_num, fd.LOC_ID Dep_loc_id, fp.PMT_AMNT as Amount "
					+ "from F_DEP fd, f_fin_ses fs, F_FIN_SES_TRAN ft, F_PMT fp, F_PMT_TYPE_LOC fl "
					+ "where fd.id in ("
					+ deposits[i]
					+ ") "
					+ "and fs.DEP_ID=fd.id "
					+ "and fs.id=ft.FIN_SES_ID "
					+ "and ft.PMT_ID=fp.id "
					+ "and fl.id=fp.PMT_TYPE_LOC_ID "
					+ "and fp.TYPE=1 "
					+ "and fp.STATUS=2 "
					+ "and fl.PMT_GRP_ID =2"
					+ nsf
					+ ") tb "
					+ "left join F_PMT_ATTR fa1 "
					+ "on tb.pmt_id=fa1.PMT_ID and fa1.ATTR_ID=93606 "
					+ "left join F_PMT_ATTR fa2 "
					+ "on tb.pmt_id=fa2.PMT_ID and fa2.ATTR_ID=93608 "
					+ "left join F_PMT_ATTR fa3 "
					+ "on tb.pmt_id=fa3.pmt_id and fa3.ATTR_ID=93610 "
					+ "left join F_PMT_ATTR fa4 "
					+ "on tb.pmt_id=fa4.pmt_id and fa4.ATTR_ID=93611 ";
			List<String> queryResult = dbconn.executeQuery(queryStr, 9, -1);
			int size = queryResult.size();
			int rows = (size + 1) / 10;
			for (int j = 0; j < rows; j++) {//row
				List<String> orders;
				String itemNum = new DecimalFormat("000").format(j + 1);
				String pmtId = queryResult.get(j * 10).toString();
				String queryStr2 = "select distinct oo.ORD_NUM "
						+ "from o_order oo, F_PMT_ALLOCATION fpa "
						+ "where fpa.PMT_ID=" + pmtId + " "
						+ "and fpa.ORD_ID = oo.id";
				orders = dbconn.executeQuery(queryStr2, "ORD_NUM");
				String contractNum = queryResult.get(j * 10 + 1).toString();
				contractNum = new DecimalFormat("0000000000").format(Integer
						.parseInt(contractNum));
				String billNum = queryResult.get(j * 10 + 2).toString();
				billNum = new DecimalFormat("0000000000").format(Integer
						.parseInt(billNum));
				String unitNum = queryResult.get(j * 10 + 3).toString();
				unitNum = new DecimalFormat("0000000000").format(Integer
						.parseInt(unitNum));
				String amount = queryResult.get(j * 10 + 4).toString();
				if(j<adjustedAmounts.length){
					if(!adjustedAmounts[j].equals("")&&adjustedAmounts[j].length()>0){  //change non-cash deposit amount with given amount
						amount = adjustedAmounts[j];
					}
				}
				
				amount = new DecimalFormat("0000000000").format(Double
						.parseDouble(amount) * 100);
				String checkNum;
				if (queryResult.get(j * 10 + 5) == null) {
					checkNum = "00000000";
				} else {
					checkNum = queryResult.get(j * 10 + 5).toString();
					if (checkNum.length() > 8)
						checkNum = checkNum.substring(0, 8);
					checkNum = new DecimalFormat("00000000").format(Integer
							.parseInt(checkNum));
				}
				String remitterName = "                              ";
				if (queryResult.get(j * 10 + 6) != null) {
					String name = queryResult.get(j * 10 + 6).toString();
					if (name.length() > 30)
						remitterName = name.substring(0, 30);
					else
						remitterName = remitterName.substring(0, 30 - name
								.length())
								+ name;
				}
				String transitNum;
				if (queryResult.get(j * 10 + 7) == null) {
					transitNum = "000000000";
				} else {
					transitNum = queryResult.get(j * 10 + 7).toString();
					if (transitNum.length() > 9) {
						transitNum = transitNum.substring(0, 9);
					}
					transitNum = new DecimalFormat("000000000").format(Integer
							.parseInt(transitNum));
				}
				String accountNum;
				if (queryResult.get(j * 10 + 8) == null) {
					accountNum = "0000000000";
				} else {
					accountNum = queryResult.get(j * 10 + 8).toString();
					if (accountNum.length() > 10) {
						accountNum = accountNum.substring(0, 10);
					}
					accountNum = new DecimalFormat("0000000000").format(Integer
							.parseInt(accountNum));
				}

				//write record 6 - payment details
				String record6 = "6" + batch + itemNum + amount + transitNum
						+ accountNum + checkNum + billNum + unitNum + "    "
						+ contractNum;
				FileUtil.writeLog(fileName, record6);

				//write record 3 - Remitter name
				String record3 = "3" + batch + itemNum + "6019" + remitterName;
				FileUtil.writeLog(fileName, record3);

				//write record 4 - order number
				int ordNum = orders.size();
				if (ordNum > 0) {
					String record4 = "4" + batch + itemNum + "6019";
					int w = 0;
					for (int k = 0; k < ordNum; k++) {
						String orderStr = orders.get(k).toString();
						String[] items = orderStr.split("-");
						String locationRef1 = new DecimalFormat("0000")
								.format(Integer.parseInt(items[0]));
						String productRef1 = new DecimalFormat("0000000000")
								.format(Integer.parseInt(items[1]));
						record4 = record4 + " " + locationRef1 + productRef1;
						w++;
						if (w > 3 && k < ordNum - 1) {
							FileUtil.writeLog(fileName, record4);
							w = 0;
							record4 = "4" + batch + itemNum + "6019";
						}
					}

					while (w < 4) {
						record4 = record4 + " 00000000000000";
						w++;
					}
					FileUtil.writeLog(fileName, record4);
				}
			}

			//process cash recodes - sum up and convert to money order
			if (!payType[i].equalsIgnoreCase("3")) {
				String queryCash = "select fd.id as dep_id, fd.loc_id as unit, fd.BILLING_NBR as bill, SUM(fp.PMT_AMNT) as amount "
						+ "from F_DEP fd, f_fin_ses fs, F_FIN_SES_TRAN ft, F_PMT fp, F_PMT_TYPE_LOC fl "
						+ "where fd.id in ("
						+ deposits[i]
						+ ") "
						+ "and fs.DEP_ID=fd.id "
						+ "and fs.id=ft.FIN_SES_ID "
						+ "and ft.PMT_ID=fp.id "
						+ "and fl.id=fp.PMT_TYPE_LOC_ID "
						+ "and fl.PMT_GRP_ID=1 "
						+ "and fp.TYPE=1 "
						+ "and fp.STATUS=2 "
						+ "Group by fd.id, fd.loc_id, fd.BILLING_NBR";
				List<String> cashes = dbconn.executeQuery(queryCash, 4, -1);
				int cashSize = cashes.size();
				int cashRows = (cashSize + 1) / 5;
				if (cashSize > 1) {
					for (int j = 0; j < cashRows; j++) {
						String contractNum = cashes.get(j * 5).toString();
						contractNum = new DecimalFormat("0000000000")
								.format(Integer.parseInt(contractNum));
						String unitNum = cashes.get(j * 5 + 1).toString();
						unitNum = new DecimalFormat("0000000000")
								.format(Integer.parseInt(unitNum));
						String billNum = cashes.get(j * 5 + 2).toString();
						billNum = new DecimalFormat("0000000000")
								.format(Integer.parseInt(billNum));
						//total cash
						double cashTotal = Double.parseDouble(cashes.get(
								j * 5 + 3).toString());
						//total cash refund
						String queryRefund = "select SUM(fp.PMT_AMNT) as amount "
								+ "from F_DEP fd, f_fin_ses fs, F_FIN_SES_TRAN ft, F_PMT fp, F_PMT_TYPE_LOC fl "
								+ "where fd.id="
								+ contractNum
								+ " "
								+ "and fs.DEP_ID=fd.id "
								+ "and fs.id=ft.FIN_SES_ID "
								+ "and ft.PMT_ID=fp.id "
								+ "and fl.id=fp.PMT_TYPE_LOC_ID "
								+ "and fl.PMT_GRP_ID=1 "
								+ "and fp.TYPE=2 "
								+ "and ft.STATUS in (4,18)";
						List<String> refunds = dbconn.executeQuery(queryRefund, 1, -1);
						if (refunds.get(0) != null) {
							cashTotal = cashTotal
									- Double.parseDouble(refunds.get(0)
											.toString());
						}

						//total cash refund reverse
						String queryRefundrev = "select SUM(fp.PMT_AMNT) as amount "
								+ "from F_DEP fd, f_fin_ses fs, F_FIN_SES_TRAN ft, F_PMT fp, F_PMT_TYPE_LOC fl "
								+ "where fd.id="
								+ contractNum
								+ " "
								+ "and fs.DEP_ID=fd.id "
								+ "and fs.id=ft.FIN_SES_ID "
								+ "and ft.PMT_ID=fp.id "
								+ "and fl.id=fp.PMT_TYPE_LOC_ID "
								+ "and fl.PMT_GRP_ID=1 "
								+ "and fp.TYPE=2 "
								+ "and ft.STATUS=5";
						List<String> refundrevs = dbconn.executeQuery(queryRefundrev,
								1, -1);
						if (refundrevs.get(0) != null) {
							cashTotal = cashTotal
									+ Double.parseDouble(refundrevs.get(0)
											.toString());
						}

						//Total cash change

						String queryChange = "select SUM(fc.PMT_AMNT) as amount "
								+ "from F_DEP fd, f_fin_ses fs, F_FIN_SES_TRAN ft, F_PMT fp, F_PMT_TYPE_LOC fl, F_CHANGE fc "
								+ "where fd.id ="
								+ contractNum
								+ " "
								+ "and fs.DEP_ID=fd.id "
								+ "and fs.id=ft.FIN_SES_ID "
								+ "and ft.PMT_ID=fp.id "
								+ "and fl.id=fp.PMT_TYPE_LOC_ID "
								+ "and fp.TYPE=1 " + "and fp.CHANGE_ID=fc.ID";
						List<String> changes = dbconn
								.executeQuery(queryChange, 1, -1);
						if (changes.get(0) != null) {
							cashTotal = cashTotal
									- Double.parseDouble(changes.get(0)
											.toString());
						}

						//Total fin session adjustment
						String querySesAdj = "select SUM(distinct fa.ADJ_AMNT) as amount "
								+ "from F_FIN_SES fs, F_SES_ADJ fsa, F_ADJ fa "
								+ "where fs.DEP_ID="
								+ contractNum
								+ " "
								+ "and fsa.SES_ID=fs.ID "
								+ "and fa.PMT_TYPE_ID=81052 "
								+ "and fa.id=fsa.ID";
						List<String> sesadj = dbconn.executeQuery(querySesAdj, 1, -1);
						if (sesadj.get(0) != null) {
							cashTotal = cashTotal
									+ Double.parseDouble(sesadj.get(0)
											.toString());
						}

						//Total deposit adjustment
						String queryDepAdj = "select SUM(distinct fa.ADJ_AMNT) as amount "
								+ "from F_DEP_ADJ fda, F_ADJ fa "
								+ "where fda.DPS_ID="
								+ contractNum
								+ " "
								+ "and fda.ID=fa.ID "
								+ "and fa.PMT_TYPE_ID=81052 "
								+ "and fa.ADJ_SUBTYPE_ID=2";
						List<String> depadjs = dbconn.executeQuery(queryDepAdj, 1, -1);
						if (depadjs.get(0) != null) {
							cashTotal = cashTotal
									+ Double.parseDouble(depadjs.get(0)
											.toString());
						}

						//Total money order cost
						String queryMonCost = "select SUM(distinct fa.ADJ_AMNT) as amount "
								+ "from F_DEP_ADJ fda, F_ADJ fa "
								+ "where fda.DPS_ID="
								+ contractNum
								+ " "
								+ "and fda.ID=fa.ID "
								+ "and fa.ADJ_SUBTYPE_ID in (1,3)";
						List<String> moncost = dbconn.executeQuery(queryMonCost, 1, -1);
						if (moncost.get(0) != null) {
							cashTotal = cashTotal
									- Double.parseDouble(moncost.get(0)
											.toString());
						}

						String itemNum = new DecimalFormat("000").format(rows
								+ j + 1);
						//write record 6 - payment details
						String checkNum = new DecimalFormat("00000000")
								.format(j + 1);
						String amount = "";
						if(adjustedAmounts.length>rows){   //change cash amount to given adjust amount
							if(!adjustedAmounts[rows+j].equals("")&&adjustedAmounts[rows+j].length()>0){
								cashTotal = Double.parseDouble(adjustedAmounts[rows+j]);
							}
						}
						if (cashTotal > 0) {
							amount = new DecimalFormat("0000000000")
									.format(cashTotal * 100);
						} else if (cashTotal < 0) {
							amount = new DecimalFormat("000000000")
									.format(cashTotal * 100);
						}
						String record6 = "6" + batch + itemNum + amount
								+ "1111111112222222222" + checkNum + billNum
								+ unitNum + "4122" + contractNum;
						FileUtil.writeLog(fileName, record6);

						//write record 3 - Remitter name
						String record3 = "3" + batch + itemNum
								+ "6019      Deposit Cash Money Order";
						FileUtil.writeLog(fileName, record3);

						//write record 4 - order numbers
						String record4 = "4"
								+ batch
								+ itemNum
								+ "6019 00000000000000 00000000000000 00000000000000 00000000000000";
						FileUtil.writeLog(fileName, record4);

					}
				}
			}

			//write record7 - batch end
			String record7 = "7" + batch + "0000281470"
					+ DateFunctions.getShortDateStamp() + "0030000016450";
			FileUtil.writeLog(fileName, record7);
		}

		//write record8 - summary
		String record8 = "80000000281470" + DateFunctions.getShortDateStamp()
				+ "01460001222375";
		FileUtil.writeLog(fileName, record8);

		//write record9 - end of file
		String record9 = "9000509";
		FileUtil.writeLog(fileName, record9);
		dbconn.disconnect();
		return fileName;
	}

}
