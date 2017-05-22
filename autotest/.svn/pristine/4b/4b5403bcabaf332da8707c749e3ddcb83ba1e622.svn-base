package com.activenetwork.qa.awo.sql;

import java.util.List;

import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.util.StringUtil;

public class SetupPOSSql {
	private static AwoDatabase db;
	
	public SetupPOSSql(){
		db = AwoDatabase.getInstance();
	}
	
	/**
	 * The method used to add a POS group in DB
	 * @param groupName first check the given group name is exist,if yes, return group id,else insert a new group
	 * @return
	 */
	public int addPOSGroup(String groupName){
		String sql = "select PRD_GRP_ID from P_PRD_GRP where PRD_GRP_NAME = '"+groupName+"' and ACTIVE_IND=1 and DELETED_IND=0";
		int prd_grp_id =0;

		List<String> ids = db.executeQuery(sql, "PRD_GRP_ID");
		if(ids.size()<1){
			String query = "select max(PRD_GRP_ID) as PRD_GRP_ID from P_PRD_GRP";
			prd_grp_id = Integer.parseInt(((String) db.executeQuery(query,
					"PRD_GRP_ID").get(0)).trim());
			System.out.println("PRD_GRP_ID: " + ++prd_grp_id);
			String insertSql = "insert into P_PRD_GRP (PRD_GRP_ID, PRD_GRP_CAT_ID, PRD_GRP_CD, PRD_GRP_DSCR, PRD_GRP_NAME, STATUS_ID, TMPL_IND, ACTIVE_IND,"
                             	+" DELETED_IND, EQPSET_ID, WEB_PRD_TYPE_ID, DFLT_UNIT_OF_STAY_ID,PRD_SUBCAT_ID,LOB_ID)"	
                             	+" values ("+prd_grp_id+",  4, NULL, '"+groupName+"', '"+groupName+"', NULL, NULL, 1, 0, NULL, NULL, NULL,1,1)";
			db.executeUpdate(insertSql);
			System.out.println("Insert Product Group "+groupName);
		}else{
			prd_grp_id = Integer.parseInt(ids.get(0));
			System.out.println("Product Group is Exist,Product Group ID:"+prd_grp_id);
		}
	
		return prd_grp_id;		
	}
	
	/**
	 * The method used to add POS product from DB
	 * @param posName
	 * @param groupId
	 * @param barCode
	 * @param revnue_loc_id
	 * @return
	 */
	public int addPOS(String posName,int groupId,String barCode,String loc_id,String revnue_loc_id){
		String sql = "select PRD_ID from P_PRD where PRD_NAME = '"+posName+"'";
		int prd_id =0;

		List<String> ids = db.executeQuery(sql, "PRD_ID");
		if (StringUtil.isEmpty(revnue_loc_id)) {
			revnue_loc_id = "NULL";
		}
		if(ids.size()<1){
			String query = "select max(PRD_ID) as PRD_ID from P_PRD";
			prd_id = Integer.parseInt(((String) db.executeQuery(query,
					"PRD_ID").get(0)).trim());
			System.out.println("PRD_ID: " + ++prd_id);
//			if(revnue_loc_id.equals("null")){
//				revnue_loc_id = null;
//			}
			
//			String insertSql =  "INSERT INTO P_PRD (PRD_ID, FILTER_LOC, INV_IND, PRD_CD, PRD_DSCR, PRD_GRP_ID, PRD_NAME, STATUS_ID, ACTIVE_IND, DELETED_IND,"
//				+"  LOC_ID, PARK_ID, IMPORT_ID, EQPSET_ID, PRD_REL_TYPE, PARENT_ID, IMPORT_TYPE, IMPORT_RESERVABLE, IMPORT_WEB_RESERVABLE,"
//				+" UNIT_OF_STAY_TYPE_ID, REV_LOC_ID, UPC_CODE, PRD_RATE_TYPE_ID, DURATION, LAST_DEACTIVATED_DATE, LIMITED_CAPACITY,"
//				+" DEFAULT_CAPACITY, ALLOC_CAT_ID, REQ_TIME_CONFLICT_MGMT, PRODUCT_CAT_ID,LOB_ID, INV_TYPE_ID, PRD_SUBCAT_ID)"
//				+" VALUES ("+prd_id+", '0', NULL, NULL, '"+posName+"', "+groupId+", '"+posName+"', NULL, '1','0', '"+loc_id+"', NULL,NULL,NULL, 0, 0, "+groupId+",null,null, 0, "+revnue_loc_id+", '"+barCode+"', 1,"
//                +"  NULL,NULL,NULL,NULL,NULL,NULL, 4,1,1,1)";
//			
			String insertSql = "INSERT INTO P_PRD (PRD_ID, FILTER_LOC, INV_IND, PRD_CD, PRD_DSCR, PRD_GRP_ID, PRD_NAME, STATUS_ID, ACTIVE_IND, DELETED_IND,"
				+"  LOC_ID, PARK_ID, IMPORT_ID, EQPSET_ID, PRD_REL_TYPE, PARENT_ID, IMPORT_TYPE, IMPORT_RESERVABLE, IMPORT_WEB_RESERVABLE,"
				+" UNIT_OF_STAY_TYPE_ID, REV_LOC_ID, UPC_CODE, PRD_RATE_TYPE_ID, DURATION, LAST_DEACTIVATED_DATE, LIMITED_CAPACITY,"
				+" DEFAULT_CAPACITY, ALLOC_CAT_ID, REQ_TIME_CONFLICT_MGMT, PRODUCT_CAT_ID,LOB_ID, INV_TYPE_ID, PRD_SUBCAT_ID)"
				+" VALUES ("+prd_id+", '0', NULL, NULL, '"+posName+"', "+groupId+", '"+posName+"', NULL, '1','0', '"+loc_id+"', NULL,NULL,NULL, 0, 0, "+groupId+",null,null, 0, "+revnue_loc_id+", ";
			if (StringUtil.isEmpty(barCode)) {
				insertSql += "NULL, ";
			} else {
				insertSql += "'" + barCode + "', ";
			}
			insertSql += "1, NULL,NULL,NULL,NULL,NULL,NULL, 4,1,1,1)";
			
			db.executeUpdate(insertSql);
			System.out.println("Insert Product "+posName);
		}else{
			prd_id = Integer.parseInt(ids.get(0));
			String updateSql = "update P_PRD set PRD_GRP_ID="+groupId+", ACTIVE_IND=1,DELETED_IND=0,loc_id="+loc_id+",IMPORT_RESERVABLE=null,IMPORT_WEB_RESERVABLE=null,REV_LOC_ID="+revnue_loc_id+",PRD_RATE_TYPE_ID=1,PRODUCT_CAT_ID=4,LOB_ID=1,INV_TYPE_ID=1,PRD_SUBCAT_ID=1 where prd_id="+prd_id;
			db.executeUpdate(updateSql);
			System.out.println("Product "+posName+" is Exist,ID is "+prd_id+",update it to desired Product.");
		}
		return prd_id;
	}
	
	/**
	 * The method used to assign product to location
	 * @param loc_id
	 * @param prd_id
	 * @param prd_name
	 */
	public void assignProductToLocation(int loc_id,int prd_id,String prd_name){
		int prd_loc_id =0;
		String sql = "select ID from p_prd_loc where prd_id="+prd_id+" and ACTIVE_IND=1 and DELETED_IND=0 and loc_id="+loc_id;
		List<String> ids = db.executeQuery(sql, "ID");
		if(ids.size()<1){
			String query = "select max(ID) as ID from P_PRD_LOC";
			String id = db.executeQuery(query,"ID").get(0);
			if(id!=null&&!id.equals("")){
				prd_loc_id = Integer.parseInt(id.trim());
			}
			
			System.out.println("PRD_LOC_ID: " + ++prd_loc_id);
			String insertSql = "INSERT INTO P_PRD_LOC (ID, LOC_ID, PRD_ID, PRD_NAME, PRD_DSCR, SALE_AVAILABILITY, VARIABLE_PRICE_IND, TOP_SELLER_SEQ, ACTIVE_IND,"
	                            +" DELETED_IND, PARTIAL_QTY_ALLOWED)"
	                            +" VALUES ("+prd_loc_id+", "+loc_id+", "+prd_id+", '"+prd_name+"', '"+prd_name+"', 0, 1, 3, 1, 0, 0)";
			db.executeUpdate(insertSql);
			System.out.println("Assign Product "+prd_name+" to Location "+loc_id);
		}else{
			prd_loc_id = Integer.parseInt(ids.get(0));
			System.out.println("POS "+prd_id+" has been assigned to location "+loc_id+",ID is "+prd_loc_id);
		}
		
	}
	
	public static void main(String[] args){
//		SetupPOSSql addPos = new SetupPOSSql();
//		String env = TestProperty.getProperty("target_env");
//		db.resetSchema(TestProperty.getProperty(env+".db.schema.prefix")+"SC");
//		PosDataForDB data = new PosDataForDB();
//		data.groupName = "Clothing";
//		data.productName = "T-Shirt";
//		data.barCode = "D333388";
//		data.revenue_loc_id =10402;
//		data.loc_id = 10402;
//		data.groupName = "Fishing Supplies";
//		data.productName = "6 ft Rod";
//		data.barCode = "C99345211";
//		data.revenue_loc_id =10000;
//		data.loc_id = 802;
//		data.groupId = addPos.addPOSGroup(data.groupName);
//		data.productId = addPos.addPOS(data.productName, data.groupId, data.barCode, data.revenue_loc_id);
//		addPos.assignProductToLocation(data.loc_id, data.productId, data.productName);
	}
}
