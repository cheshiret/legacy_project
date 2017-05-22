package com.activenetwork.qa.awo.supportscripts.qasetup.db;

import java.util.List;

import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;


public class SetupPOSMinimumPaymentRule extends SupportCase {
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private MinPaymentInfo minPaymentInfo = new MinPaymentInfo();
	class MinPaymentInfo{
		String contract = "";
		String p_min_pmt_cfm_id = "";
		String effective_date = "";
		String loc_id = "";
		String prd_grp_name = "";
		String prd_name = "";
		String active_id = "";
		String delete_id = "";
		String prd_cat_id = "";
		String ticket_cat_id = "";
		String tran_typ_id = "";
		String tran_occur_id = "";
		String sales_chanl_id = "";
		String min_unit_stay = "";
		String min_num_days = "";
		String p_min_pmt_entry_cfm_id = "";
		String priority = "";
		String order_item_type_id = "";
		String fee_type_id = "";
		String rule_type = "";
		String amount = "";
	}
	
	public void resetSchema(String contract){
		String schema = DataBaseFunctions.getSchemaName(contract, TestProperty.getProperty("target_env"));
		db.resetSchema(schema);
	}
	
	public void setupPMinPmtCfmInfo(MinPaymentInfo minPmtInfo){
		this.resetSchema(minPmtInfo.contract);
		
		String prdGrpID = "";
		if(!minPmtInfo.prd_grp_name.equalsIgnoreCase("NULL")){
			String queryPrdGrpID = "select prd_grp_id from p_prd_grp where prd_grp_name = '" + minPmtInfo.prd_grp_name + "'";
			logger.info("Execute sql: " + queryPrdGrpID);
			prdGrpID = db.executeQuery(queryPrdGrpID, "prd_grp_id",0);
		}else{
			prdGrpID = "NULL";
		}
		String prdID = "";
		if(!minPmtInfo.prd_name.equals("NULL")){
			String queryPrdID = "select prd_id from p_prd where prd_name = '" + minPmtInfo.prd_name + "'";
			logger.info("Execute sql: " + queryPrdID);
			prdID = db.executeQuery(queryPrdID, "prd_id",0);
		}else{
			prdID = "NULL";
		}
		
		StringBuffer query = new StringBuffer(128);
		query.append("select id from p_min_pmt_cfm"
				+ " where prd_cat_id = " + minPmtInfo.prd_cat_id
				+ " and prd_grp_id ");
		if(prdGrpID.equalsIgnoreCase("NULL")){
			query.append("is NULL");
		}else{
			query.append("= " +prdGrpID);
		}
		query.append(" and prd_id ");
		if(prdID.equalsIgnoreCase("NULL")){
			query.append("is NULL");
		}else{
			query.append("= " + prdID);
		}
		logger.info("Execute sql: " + query);
		List<String> results = db.executeQuery(query.toString(), "id");
		if(results.size()<1){
			StringBuffer sql = new StringBuffer(256);
			sql.append("insert into P_MIN_PMT_CFM (ID, EFFECTIVE_DATE, LOC_ID, PRD_GRP_ID, PRD_ID, ACTIVE_ID, DELETE_ID,PRD_CAT_ID,"
                      + "TICKET_CAT_ID, TRAN_TYP_ID, TRAN_OCCUR_ID, SALES_CHANL_ID, MIN_UNIT_STAY,MIN_NUM_DAYS) "
                      + "values (get_sequence('P_MIN_PMT_CFM'), to_date('" + minPmtInfo.effective_date + "', 'dd-mm-yy'), " + minPmtInfo.loc_id + ", "
                      + prdGrpID + ", " + prdID + ", " + minPmtInfo.active_id + ", " + minPmtInfo.delete_id + ", " + minPmtInfo.prd_cat_id + ", " 
                      + minPmtInfo.ticket_cat_id + ", " + minPmtInfo.tran_typ_id + ", " + minPmtInfo.tran_occur_id + ", " + minPmtInfo.sales_chanl_id + ", "
                      + minPmtInfo.min_unit_stay + ", " + minPmtInfo.min_num_days + ")");
			
			logger.info("Execute sql: " + sql);
			int num = db.executeUpdate(sql.toString());
			if(num>0){
				minPmtInfo.p_min_pmt_cfm_id = db.executeQuery(query.toString(), "id", 0);
				logger.info("P_MIN_PMT_CFM table success insert info which product name = " + minPmtInfo.prd_name
						+ ", and product group = " +  minPmtInfo.prd_grp_name + ", and location = " + minPmtInfo.loc_id);
			}else {
				throw new ErrorOnDataException("P_MIN_PMT_CFM table failed insert info which product name = " + minPmtInfo.prd_name
						+ ", and product group = " +  minPmtInfo.prd_grp_name + ", and location = " + minPmtInfo.loc_id);
			}
		}else{
			minPmtInfo.p_min_pmt_cfm_id = results.get(0);
			logger.info("P_MIN_PMT_CFM table existing this info which product name = " + minPmtInfo.prd_name
					+ ", and product group = " +  minPmtInfo.prd_grp_name + ", and location = " + minPmtInfo.loc_id);
		}
	}
	
	public void setupPMinPmtEntryCfm(MinPaymentInfo minPmtInfo){
		this.resetSchema(minPmtInfo.contract);
		
		StringBuffer query = new StringBuffer(128);
		query.append("select id from p_min_pmt_entry_cfm where p_min_pmt_cfm_id = " + minPmtInfo.p_min_pmt_cfm_id
				    + " and order_item_type_id = " + minPmtInfo.order_item_type_id 
				    + " and fee_type_id = " + minPmtInfo.fee_type_id
				    + " and rule_type = " + minPmtInfo.rule_type);
		logger.info("Execute sql: " + query);
		List<String> results = db.executeQuery(query.toString(), "id");
		if(results.size()<1){
			StringBuffer sql = new StringBuffer(256);
			sql.append("insert into p_min_pmt_entry_cfm (id, p_min_pmt_cfm_id, priority,order_item_type_id,fee_type_id,rule_type,amount)" +
					" values (get_sequence('p_min_pmt_entry_cfm'), " + minPmtInfo.p_min_pmt_cfm_id + ", " + minPmtInfo.priority +
					", " + minPmtInfo.order_item_type_id + ", " + minPmtInfo.fee_type_id + ", " + minPmtInfo.rule_type + ", " + minPmtInfo.amount + ")");
			
			logger.info("Execute sql: " + sql);
			int num = db.executeUpdate(sql.toString());
			if(num>0){ 
				minPmtInfo.p_min_pmt_entry_cfm_id =  db.executeQuery(query.toString(), "id", 0);
				logger.info("p_min_pmt_entry_cfm table success insert info which product name = " + minPmtInfo.prd_name
						+ ", and product group = " +  minPmtInfo.prd_grp_name + ", and location = " + minPmtInfo.loc_id
						+ ", and fee type id = " +  minPmtInfo.fee_type_id + ", and rule type id = " + minPmtInfo.rule_type);
			}else{
				throw new ErrorOnDataException("p_min_pmt_entry_cfm table failed insert info which product name = " + minPmtInfo.prd_name
						+ ", and product group = " +  minPmtInfo.prd_grp_name + ", and location = " + minPmtInfo.loc_id
						+ ", and fee type id = " +  minPmtInfo.fee_type_id + ", and rule type id = " + minPmtInfo.rule_type);
			}
			
		}else{
			logger.info("p_min_pmt_entry_cfm table existing this info which product name = " + minPmtInfo.prd_name
					+ ", and product group = " +  minPmtInfo.prd_grp_name + ", and location = " + minPmtInfo.loc_id
					+ ", and fee type id = " +  minPmtInfo.fee_type_id + ", and rule type id = " + minPmtInfo.rule_type);
		}
		
	}

	@Override
	public void execute() {
		this.setupPMinPmtCfmInfo(minPaymentInfo);
		this.setupPMinPmtEntryCfm(minPaymentInfo);
		logMsg[6] = "Success";
	}

	@Override
	public void getNextData() {
		minPaymentInfo.p_min_pmt_cfm_id = "";
		minPaymentInfo.contract = datasFromDB.get("contract");
		minPaymentInfo.effective_date = datasFromDB.get("effective_date");
		if(StringUtil.isEmpty(minPaymentInfo.effective_date)){
			minPaymentInfo.effective_date = DateFunctions.getToday("dd-MM-yy");
		}
		minPaymentInfo.loc_id = datasFromDB.get("loc_id");
		minPaymentInfo.prd_grp_name = datasFromDB.get("prd_grp_name");
		if(StringUtil.isEmpty(minPaymentInfo.prd_grp_name )){
			minPaymentInfo.prd_grp_name  = "NULL";
		}
		minPaymentInfo.prd_name = datasFromDB.get("prd_name");
		if(StringUtil.isEmpty(minPaymentInfo.prd_name )){
			minPaymentInfo.prd_name  = "NULL";
		}
		minPaymentInfo.active_id = datasFromDB.get("active_id");
		minPaymentInfo.delete_id = datasFromDB.get("delete_id");
		minPaymentInfo.prd_cat_id = datasFromDB.get("prd_cat_id");
		minPaymentInfo.ticket_cat_id = datasFromDB.get("ticket_cat_id");
		if(StringUtil.isEmpty(minPaymentInfo.ticket_cat_id )){
			minPaymentInfo.ticket_cat_id  = "NULL";
		}
		minPaymentInfo.tran_typ_id = datasFromDB.get("tran_typ_id");
		if(StringUtil.isEmpty(minPaymentInfo.tran_typ_id )){
			minPaymentInfo.tran_typ_id  = "NULL";
		}
		minPaymentInfo.tran_occur_id = datasFromDB.get("tran_occur_id");
		if(StringUtil.isEmpty(minPaymentInfo.tran_occur_id )){
			minPaymentInfo.tran_occur_id  = "NULL";
		}
		minPaymentInfo.sales_chanl_id = datasFromDB.get("sales_chanl_id");
		minPaymentInfo.min_unit_stay = datasFromDB.get("min_unit_stay");
		minPaymentInfo.min_num_days = datasFromDB.get("min_num_days");
		minPaymentInfo.p_min_pmt_entry_cfm_id = "";
		minPaymentInfo.priority = datasFromDB.get("priority");
		minPaymentInfo.order_item_type_id = datasFromDB.get("order_item_type_id");
		minPaymentInfo.fee_type_id = datasFromDB.get("fee_type_id");
		minPaymentInfo.rule_type = datasFromDB.get("rule_type");
		minPaymentInfo.amount = datasFromDB.get("amount");
		
		logMsg[0] = cursor + " ";
		logMsg[1] = minPaymentInfo.prd_name;
		logMsg[2] = minPaymentInfo.prd_grp_name;
		logMsg[3] = minPaymentInfo.loc_id;
		logMsg[4] = minPaymentInfo.fee_type_id;
		logMsg[5] = minPaymentInfo.rule_type;
		logMsg[6] = "Failed";
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataSource = "d_setup_pos_minpay_db";

		queryDataSql = "select * from d_setup_pos_minpay_db where id = 20";

		logMsg = new String[7];
		logMsg[0] = "Index";
		logMsg[1] = "ProductName";
		logMsg[2] = "ProductGroup";
		logMsg[3] = "Location";
		logMsg[4] = "FeeTypeID";
		logMsg[5] = "RuleTypeID";
		logMsg[6] = "Result";
	}
}
