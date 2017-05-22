package com.activenetwork.qa.awo.supportscripts.qasetup.db;

import java.util.List;

import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class SetupTPAAttributeInFacilityLevel extends SupportCase{
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private TPAAttrDBInfo tpaAttrDB = new TPAAttrDBInfo();
	
	class TPAAttrDBInfo{
		String contract = "";
		String notactive_cntr = "";
		String attr_discrim = "";
		String base_attr_id = "";
		String attr_id = "";
		String attr_name = "";
		String attr_type_id = "";
		String attr_cat = "";
		String display_name = "";
		String active_ind_allDAttr = "";
		String deleted_ind_allDAttr = "";
		String display_seq_no = "";
		String renderer = "";
		String web_searchable_ind = "";
		String field_searchable_ind = "";
		String call_searchable_ind = "";
		
		String schema_TPA = "";
		String facility_id = "";
		String col_method_id = "";
		
		String business_obj_type = "";
		String active_ind_dAttrObj = "";
	}
	
	public void resetSchema(String contract){
		String schema = DataBaseFunctions.getSchemaName(contract, TestProperty.getProperty("target_env"));
		db.resetSchema(schema);
	}
	
	public void setupAllAttributeInfoAtCommonSchema(TPAAttrDBInfo tapAttrDBInfo){
		this.resetSchema("common");
		String query = "select attr_name from all_d_attr where attr_id = " + tapAttrDBInfo.attr_id;
		
		logger.info("Execution sql:" + query);
		List<String> results = db.executeQuery(query, "attr_name");
		if(results.size()<1){
			StringBuffer sql = new StringBuffer(251);
			sql.append("INSERT INTO ALL_D_ATTR(CONTRACT, NOTACTIVE_CNTR,ATTR_DISCRIM,ATTR_ID,BASE_ATTR_ID,ATTR_NAME,ATTR_TYPE_ID," +
					"ATTR_CAT,DISPLAY_NAME,ACTIVE_IND,DELETED_IND,DISPLAY_SEQ_NO,WEB_SEARCHABLE_IND,FIELD_SEARCHABLE_IND,CALL_SEARCHABLE_IND,RENDERER)" +
					"VALUES('" + tapAttrDBInfo.contract + "','" + tapAttrDBInfo.notactive_cntr + "'," + tapAttrDBInfo.attr_discrim + "," +
					tapAttrDBInfo.attr_id + "," + tapAttrDBInfo.base_attr_id + ",'" + tapAttrDBInfo.attr_name + "'," + tapAttrDBInfo.attr_type_id + ",'" +
					tapAttrDBInfo.attr_cat + "','" + tapAttrDBInfo.display_name +"','" + tapAttrDBInfo.active_ind_allDAttr + "','" + 
					tapAttrDBInfo.deleted_ind_allDAttr + "'," + tapAttrDBInfo.display_seq_no + ",'" + tapAttrDBInfo.web_searchable_ind + "','"+
					tapAttrDBInfo.field_searchable_ind + "','" + tapAttrDBInfo.call_searchable_ind + "',");
			
			if(tapAttrDBInfo.renderer.equals("NULL")){
				sql.append(tapAttrDBInfo.renderer + ")");
			}else{
				sql.append("'" + tapAttrDBInfo.renderer + "')");
			}
			logger.info("Execution sql:" + sql);
			int number = db.executeUpdate(sql.toString());
			if(number>0){
				logger.info("ALL_D_ATTR table success insert this attribute info which attr_id = " + tapAttrDBInfo.attr_id);
			}else{
				throw new ErrorOnDataException("ALL_D_ATTR table faild to insert attribute info which attr_id = " + tapAttrDBInfo.attr_id
						+", but att_name not is " + tapAttrDBInfo.attr_name);
			}
			
		}else{
			if(!results.contains(tapAttrDBInfo.attr_name)){
				throw new ErrorOnDataException("ALL_D_ATTR table has an existing attribute info which attr_id = " + tapAttrDBInfo.attr_id
						+", but att_name not is " + tapAttrDBInfo.attr_name);
			}else logger.info("ALL_D_ATTR table has this attribute info which attr_id = " + tapAttrDBInfo.attr_id
					+", att_name = " + tapAttrDBInfo.attr_name);
		}	
	}
	
	public void setupDTourParticipantAttrInfo(TPAAttrDBInfo tapAttrDBInfo){
		this.resetSchema(tapAttrDBInfo.schema_TPA);
		StringBuffer query = new StringBuffer(128);
		query.append("select col_method_id from d_tour_participant_attr_info where facility_id = " + tapAttrDBInfo.facility_id
	             + " and col_method_id ");
		
		if(tapAttrDBInfo.col_method_id.equalsIgnoreCase("null")){
			query.append("is null");
		}else{
			query.append("= " + tapAttrDBInfo.col_method_id);
		}
		logger.info("Execution sql:" + query);
		List<String> results = db.executeQuery(query.toString(), "col_method_id");
		if(results.size()<1){
			String sql = "insert into d_tour_participant_attr_info (id, facility_id, col_method_id ) values "
				       + "( get_sequence( 'd_tour_participant_attr_info' ), " + tapAttrDBInfo.facility_id + ", " + tapAttrDBInfo.col_method_id +")";
			logger.info("Execution sql:" + sql);
			int number = db.executeUpdate(sql);
			if(number>0){
				logger.info("d_tour_participant_attr_info table success insert info which facility_id = " + tapAttrDBInfo.facility_id
						+ ", and col_method_id = " + tapAttrDBInfo.col_method_id);
			}else{
				throw new ErrorOnDataException("d_tour_participant_attr_info table failed insert info which facility_id = " + tapAttrDBInfo.facility_id
						+ ", and col_method_id = " + tapAttrDBInfo.col_method_id);
			}
		}else {
			logger.info("d_tour_participant_attr_info table have this info which facility_id = " + tapAttrDBInfo.facility_id
					+ ", and col_method_id = " + tapAttrDBInfo.col_method_id);
		}
		
	}
	
	public void setupDAttrObjectInfo(TPAAttrDBInfo tapAttrDBInfo){
		this.resetSchema(tapAttrDBInfo.schema_TPA);
		
		StringBuffer query = new StringBuffer(256);
		query.append("select d_attr_object.id from d_attr_object,d_tour_participant_attr_info where d_tour_participant_attr_info.id = d_attr_object.object_id"
		             + " and d_attr_object.attr_id = " + tapAttrDBInfo.attr_id
		             + " and d_tour_participant_attr_info.facility_id = " + tapAttrDBInfo.facility_id
		             + " and d_tour_participant_attr_info.col_method_id ");
		if(tapAttrDBInfo.col_method_id.equalsIgnoreCase("null")){
			query.append("is null");
		}else{
			query.append("= " + tapAttrDBInfo.col_method_id);
		}
		logger.info("Execution sql:" + query);
		List<String> results = db.executeQuery(query.toString(), "id");
		if(results.size()<1){
			StringBuffer sql = new StringBuffer(256);
			sql.append("insert into d_attr_object (id, business_obj_type, object_id, attr_cat, active_ind, attr_id)"
				       + " select get_sequence('d_attr_object')," + tapAttrDBInfo.business_obj_type + "," + "d_tour_participant_attr_info.id,'"
				       + tapAttrDBInfo.attr_cat + "'," + tapAttrDBInfo.active_ind_dAttrObj + "," + tapAttrDBInfo.attr_id
				       + " from d_tour_participant_attr_info"
				       + " where d_tour_participant_attr_info.facility_id = " + tapAttrDBInfo.facility_id
				       + " and d_tour_participant_attr_info.col_method_id ");
			if(tapAttrDBInfo.col_method_id.equalsIgnoreCase("NULL")){
				sql.append("is NULL");
			}else{
				sql.append("= " + tapAttrDBInfo.col_method_id);
			}
			logger.info("Execution sql:" + sql);
			int number = db.executeUpdate(sql.toString());
			if(number>0){
				logger.info("d_attr_object table success insert this info which facility_id = " + tapAttrDBInfo.facility_id
						+ ", and col_method_id = " + tapAttrDBInfo.col_method_id + ", and attr_id = " + tapAttrDBInfo.attr_id);
				
			}else{
				throw new ErrorOnDataException("d_attr_object table failed insert this info which facility_id = " + tapAttrDBInfo.facility_id
						+ ", and col_method_id = " + tapAttrDBInfo.col_method_id + ", and attr_id = " + tapAttrDBInfo.attr_id);
			}
		}else {
			logger.info("d_attr_object table existing this info which facility_id = " + tapAttrDBInfo.facility_id
					+ ", and col_method_id = " + tapAttrDBInfo.col_method_id + ", and attr_id = " + tapAttrDBInfo.attr_id);
		}
		
	}
	
	@Override
	public void execute() {
		this.setupAllAttributeInfoAtCommonSchema(tpaAttrDB);
		this.setupDTourParticipantAttrInfo(tpaAttrDB);
		this.setupDAttrObjectInfo(tpaAttrDB);
		logMsg[6] = "Success";		
	}

	@Override
	public void getNextData() {
		tpaAttrDB.contract = datasFromDB.get("contract");
		tpaAttrDB.notactive_cntr = datasFromDB.get("notactive_cntr");
		tpaAttrDB.attr_discrim = datasFromDB.get("attr_discrim");
		tpaAttrDB.attr_id = datasFromDB.get("attr_id");
		tpaAttrDB.base_attr_id = datasFromDB.get("base_attr_id");
		if(StringUtil.isEmpty(tpaAttrDB.base_attr_id)){
			tpaAttrDB.base_attr_id = "NULL";
		}
		tpaAttrDB.attr_name = datasFromDB.get("attr_name");
		tpaAttrDB.attr_type_id = datasFromDB.get("attr_type_id");
		tpaAttrDB.attr_cat = "Tour Participant";
		tpaAttrDB.display_name = datasFromDB.get("display_name");
		tpaAttrDB.active_ind_allDAttr = datasFromDB.get("active_ind_allDAttr");
		tpaAttrDB.deleted_ind_allDAttr = datasFromDB.get("deleted_ind_allDAttr");
		tpaAttrDB.display_seq_no = datasFromDB.get("display_seq_no");
		if(StringUtil.isEmpty(tpaAttrDB.display_seq_no)){
			tpaAttrDB.display_seq_no = "NULL";
		}
		tpaAttrDB.renderer = datasFromDB.get("renderer");
		if(StringUtil.isEmpty(tpaAttrDB.renderer)){
			tpaAttrDB.renderer = "NULL";
		}
		tpaAttrDB.web_searchable_ind = datasFromDB.get("web_searchable_ind");
		tpaAttrDB.field_searchable_ind = datasFromDB.get("field_searchable_ind");
		tpaAttrDB.call_searchable_ind = datasFromDB.get("call_searchable_ind");
		
		tpaAttrDB.schema_TPA = datasFromDB.get("schema_TPA");
		tpaAttrDB.facility_id = datasFromDB.get("facility_id");
		tpaAttrDB.col_method_id = datasFromDB.get("col_method_id");
		if(StringUtil.isEmpty(tpaAttrDB.col_method_id)){
			tpaAttrDB.col_method_id = "NULL";
		}
		
		tpaAttrDB.business_obj_type = datasFromDB.get("business_obj_type");
		tpaAttrDB.active_ind_dAttrObj = datasFromDB.get("active_ind_dAttrObj");
		
		logMsg[0] = cursor + " ";
		logMsg[1] = tpaAttrDB.attr_id;
		logMsg[2] = tpaAttrDB.attr_name;
		logMsg[3] = tpaAttrDB.display_name;
		logMsg[4] = tpaAttrDB.facility_id;
		logMsg[5] = tpaAttrDB.col_method_id;
		logMsg[6] = "Failed";
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataSource = "d_setup_tpa_db";
		
		queryDataSql = "";

		logMsg = new String[7];
		logMsg[0] = "Index";
		logMsg[1] = "AttributeID";
		logMsg[2] = "AttributeName";
		logMsg[3] = "AttributeDisplayName";
		logMsg[4] = "AttributeFacilityID";
		logMsg[5] = "CollectMethod";
		logMsg[6] = "Result";
	}
}
