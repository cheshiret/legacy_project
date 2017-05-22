/**
 *
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.db;

import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This script is used to make sure there have available permit inventory for given entrance until given date from current date
 * @Preconditions: make sure entrance and quota type is exist
 * @SPEC:
 * @Task#:Auto-646
 *
 * @author ssong
 * @Date  Aug 4, 2011
 */
public class SetupPermitInventory extends SetupCase{

	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private String contract;
	private String park_name;
	private String entrance_name;
	private String quota_type;
	private String env;
	private String schema_pref;
	private String schema;
	private int qty;
	private int daysBeforeToday;
	private int daysAfterToday;

	@Override
	public void executeSetup() {
		db.resetSchema(schema);

		this.addOrUpdatePermitInv(park_name, entrance_name, quota_type, qty, daysBeforeToday,daysAfterToday);
		
	}

	@Override
	public void readDataFromDatabase() {
		contract = datasFromDB.get("contract");
		schema = schema_pref+contract;
		park_name = DataBaseFunctions.getFacilityName(datasFromDB.get("park_id"), schema);
		entrance_name = datasFromDB.get("entrance_name");
		qty = Integer.parseInt(datasFromDB.get("qty"));
		quota_type = datasFromDB.get("quota_type");
		daysBeforeToday = Integer.parseInt(datasFromDB.get("daysBeforeToday"));
		daysAfterToday = Integer.parseInt(datasFromDB.get("daysAfterToday"));
		
	}

	public void wrapParameters(Object[] param) {
	  	dataTableName = "d_setup_permit_inv";
	  	ids = "520";
	  	queryDataSql = "";//override this to run what you want to execute,it will execute all records by default
	  	env = TestProperty.getProperty("target_env");
	  	schema_pref = TestProperty.getProperty(env+".db.schema.prefix");
	}

	public void addOrUpdatePermitInv(String park_name,String entrance_name,String quota_type,int qty,int daysBeforeToday,int daysAfterToday){
		String query = "select prd_id,park_id from p_prd where prd_name='"+entrance_name+"' and park_id=(select id from d_loc where name = '"+park_name+"')";
		String[] colNames = {"prd_id","park_id"};
		String[] values = db.executeQuery(query, colNames, 0);
		String entrance_id = values[0];
		String park_Id = values[1];
		query = "select  max(start_date) as start_date from i_permit_inv where active_ind=1 and entrance_id="+entrance_id;
		String max_date = db.executeQuery(query, "start_date", 0).split(" ")[0];
		//check park attribute PermitInventoryAllocationsApplicable
		query = "select count(*) as num from d_loc_attr_value where attr_id=100125 and loc_id="+park_Id;
		boolean updatePool = Integer.parseInt(db.executeQuery(query, "num", 0))>0;
		String fromDate = DateFunctions.getDateAfterToday(daysBeforeToday);
		if(DateFunctions.compareDates(max_date, fromDate)!=1){//No Quota Inventory For given start date
			logger.info("No Quota Inventory For "+entrance_name+"From "+fromDate+",need add quota Inventory.");
			this.addQuotaInventory(park_Id, entrance_id, quota_type, fromDate, daysAfterToday, qty);
			if(updatePool){//if PermitInventoryAllocationsApplicable,need to add allocation type in i_permit_inv_pool table
				this.addPermitInvPool(entrance_id,fromDate, qty);
			}
		}else if(DateFunctions.compareDates(max_date, DateFunctions.getDateAfterToday(daysAfterToday))!=1){//need to add new quota inventory after max date
			logger.info("Existing Quota Inventory Max Available Date is "+max_date);
			this.reloadInvCapacity(qty, entrance_id,fromDate, updatePool);
			this.addQuotaInventory(park_Id, entrance_id, quota_type, DateFunctions.getDateAfterGivenDay(max_date, 1), DateFunctions.daysBetween(max_date, DateFunctions.getDateAfterToday(daysAfterToday)), qty);
			if(updatePool){//if PermitInventoryAllocationsApplicable,need to add allocation type in i_permit_inv_pool table
				this.addPermitInvPool(entrance_id,DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(max_date, 1),"dd-MMM-yy"), qty);
			}
		}else{
			logger.info("Existing Quota Inventory is Ok for used, just update Capacity.");
			this.reloadInvCapacity(qty, entrance_id,fromDate, updatePool);
		}
	}

	/**
	 * update permit inventory capacity to (current capacity plus qty) and available number to (available plus qty) for given entrance
	 * @param qty
	 * @param entrance_id
	 * @param fromDate
	 * @param updatePool
	 */
	private void reloadInvCapacity(int qty,String entrance_id,String fromDate,boolean updatePool){
		logger.info("Reload Permit Inventory Capacity for Entrance "+entrance_id);
		fromDate = DateFunctions.formatDate(fromDate,"dd-MMM-yy");
		String sql = "update i_permit_inv set capacity=capacity+"+qty+",available=available+"+qty+" where active_ind=1 and start_date>to_date('"+fromDate+"') and entrance_id="+entrance_id;
		db.executeUpdate(sql);
		if(updatePool){
			this.updatePermitInvPool(entrance_id, fromDate, qty);
		}
	}

	/**
	 * The method used to add quota inventory for given entrance from start date to given days later
	 * @param parkId
	 * @param entrance_id
	 * @param quota_type
	 * @param startDate from date
	 * @param period to given days later
	 * @param qty
	 */
	private void addQuotaInventory(String parkId,String entrance_id,String quota_type,String startDate,int period,int qty){
		logger.info("Add Quota Inventory For Entrance "+entrance_id+" from "+startDate+" to "+DateFunctions.getDateAfterGivenDay(startDate, period));

		startDate = DateFunctions.formatDate(startDate,"yyyy-MMM-dd");
		String query = "select id from p_quota_type where loc_id="+parkId+" and name='"+quota_type+"'";
		String quota_type_id = db.executeQuery(query, "id", 0);
		if(quota_type_id==null||quota_type_id.equals("")){
			throw new ErrorOnDataException("No "+quota_type+" found for park "+parkId);
		}
		query = "insert into i_permit_inv(id,loc_id,entrance_id,quota_type_id,start_date,end_date,capacity,available,active_ind,closed_ind,deleted_ind,CONCURRENCY_VERSION_NUM,LASTMODTIME) " +
		"(select (select max(id) from i_permit_inv)+rownum,"+parkId+","+entrance_id+","+quota_type_id+",temp.basedate,temp.basedate,"+qty+","+qty+",1,0,0,1,sysdate from (select basedate from ( select to_date('"+startDate+"','YYYY-MM-DD-HH24:MI:SS') + a.mynum basedate from ((select rownum mynum from d_loc where rownum<="+period+") a)) c where basedate not in (select start_date from i_permit_inv where active_ind=1 and entrance_id="+entrance_id+") order by basedate) temp)";
		logger.debug(query);
		db.executeUpdate(query);
//		String date;
//		for(int i=0;i<period;i++){//bottle neck to connect db too many times,need to enhance
//			date = DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(startDate,i),"dd-MMM-yy");
//			query = "select count(*) as num from i_permit_inv where active_ind=1 and start_date='"+date+"' and entrance_id="+entrance_id;
//			if(Integer.parseInt(db.executeQuery(query, "num", 0))==0){
//				query = "insert into i_permit_inv(id,loc_id,entrance_id,quota_type_id,start_date,end_date,capacity,available,active_ind,closed_ind,deleted_ind,CONCURRENCY_VERSION_NUM,LASTMODTIME) values((select max(id) from i_permit_inv)+1,"+parkId+","+entrance_id+","+quota_type_id+",'"+date+"','"+date+"',"+qty+","+qty+",1,0,0,1,to_date(current_date))";
//				db.executeUpdate(query);
//			}
//		}
	}

	/**
	 * Add permit inventory from i_permit_inv_pool table for different permit category
	 * @param entrance_id
	 * @param start_date
	 * @param qty
	 */
	private void addPermitInvPool(String entrance_id,String start_date,int qty){
		logger.info("Add Permit Inventory Pool For Entrance "+entrance_id+" from "+start_date);

//		String query = "select id from i_permit_inv where active_ind=1 and start_date>'"+start_date+"' and entrance_id="+entrance_id;
//		List<String> ids = db.executeQuery(query, "id");
//		for(int i=0;i<ids.size();i++){
//			query = "insert into i_permit_inv_pool values((select max(id) from i_permit_inv_pool)+1,"+ids.get(i)+",3,"+qty/2+",1,"+qty/2+",1)";   //3-commercial
//			db.executeUpdate(query);
//			query = "insert into i_permit_inv_pool values((select max(id) from i_permit_inv_pool)+1,"+ids.get(i)+",4,"+qty/2+",1,"+qty/2+",1)";   //4-nonCommercial
//			db.executeUpdate(query);
//		}
		start_date = DateFunctions.formatDate(start_date,"yyyy-MMM-dd");
		//insert for advanced sale commercial,non-commercial and shared inventory
		logger.info("Setup for Advanced sale inventory");
		String query = "insert into i_permit_inv_pool select (select max(id) from i_permit_inv_pool)+rownum,id,3,"+qty/6+",1,"+qty/6+",1 from i_permit_inv where active_ind=1 and start_date>to_date('"+start_date+"','YYYY-MM-DD-HH24:MI:SS') and entrance_id="+entrance_id+"";   //3-commercial,2-advanced
		db.executeUpdate(query);
		logger.debug(query);
		query = "insert into i_permit_inv_pool select (select max(id) from i_permit_inv_pool)+rownum,id,4,"+qty/6+",1,"+qty/6+",1 from i_permit_inv where active_ind=1 and start_date>to_date('"+start_date+"','YYYY-MM-DD-HH24:MI:SS') and entrance_id="+entrance_id+"";   //4-nonCommercial,2-advanced
		db.executeUpdate(query);
		logger.debug(query);
		query = "insert into i_permit_inv_pool select (select max(id) from i_permit_inv_pool)+rownum,id,0,"+qty/6+",1,"+qty/6+",1 from i_permit_inv where active_ind=1 and start_date>to_date('"+start_date+"','YYYY-MM-DD-HH24:MI:SS') and entrance_id="+entrance_id+"";   //0-shared,2-advanced
		db.executeUpdate(query);
		logger.debug(query);
		//insert for walk in commercial,non-commercial and shared inventory
		logger.info("Setup for Walk in inventory");
		query = "insert into i_permit_inv_pool select (select max(id) from i_permit_inv_pool)+rownum,id,3,"+qty/6+",1,"+qty/6+",2 from i_permit_inv where active_ind=1 and start_date>to_date('"+start_date+"','YYYY-MM-DD-HH24:MI:SS') and entrance_id="+entrance_id+"";   //3-commercial,2-walk in
		db.executeUpdate(query);
		logger.debug(query);
		query = "insert into i_permit_inv_pool select (select max(id) from i_permit_inv_pool)+rownum,id,4,"+qty/6+",1,"+qty/6+",2 from i_permit_inv where active_ind=1 and start_date>to_date('"+start_date+"','YYYY-MM-DD-HH24:MI:SS') and entrance_id="+entrance_id+"";   //4-nonCommercial,2-walk in
		db.executeUpdate(query);
		logger.debug(query);
		query = "insert into i_permit_inv_pool select (select max(id) from i_permit_inv_pool)+rownum,id,0,"+qty/6+",1,"+qty/6+",2 from i_permit_inv where active_ind=1 and start_date>to_date('"+start_date+"','YYYY-MM-DD-HH24:MI:SS') and entrance_id="+entrance_id+"";   //0-shared,2-walk in
		db.executeUpdate(query);
		logger.debug(query);
	}

	/**
	 * update permit inventory from i_permit_inv_pool table for different permit category
	 * @param entrance_id
	 * @param start_date
	 * @param qty
	 */
	private void updatePermitInvPool(String entrance_id,String start_date,int qty){
		logger.info("Update Permit Inventory Pool For Entrance "+entrance_id+" from "+start_date);

		String query = "update i_permit_inv_pool set quantity=quantity+"+qty/6+",capacity=capacity+"+qty/6+" where allocation_type in (1,2) and sales_cat in(3,4,0) and permit_inv_id in (select id from i_permit_inv where active_ind=1 and start_date>'"+start_date+"' and entrance_id="+entrance_id+")";
		db.executeUpdate(query);
	}

}
