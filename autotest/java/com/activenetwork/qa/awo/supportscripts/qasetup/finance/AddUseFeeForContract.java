package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddFeeScheduleFunction;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DatapoolUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author fliu
 * @Date  March 15, 2012
 * Notice: feeSchData.acctCode will be changed after DB schema changed so we should not set them from datapool static but get them from UI automatically.
 */
public class AddUseFeeForContract extends SetupCase {

	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private String schema = "";
//	private String feeScheduleId = "";
//	private String result;
	private FeeScheduleData feeSchData;
	private LoginInfo loginInfo = new LoginInfo();
	private String prefix_=null;
	@Override
	public void readDataFromDatabase() {
		
		feeSchData = new FeeScheduleData();
		
		schema = prefix_+datasFromDB.get("contractForSchema");
	
		feeSchData = DatapoolUtil.fill(FeeScheduleData.class, datasFromDB);
		feeSchData.loop = datasFromDB.get("loopname");
		loginInfo.contract = datasFromDB.get("Contract");
		loginInfo.location = datasFromDB.get("Location");
		feeSchData.location = "";
		if(StringUtil.isEmpty(feeSchData.effectDate)){
			feeSchData.effectDate = DateFunctions.getDateAfterToday(-1);
		}

		if(StringUtil.isEmpty(feeSchData.startInv)){
			feeSchData.startInv = DateFunctions.getDateAfterToday(-1);
		}

		if(StringUtil.isEmpty(feeSchData.endInv)){
			feeSchData.endInv = DateFunctions.getDateAfterToday(366);
		}
	}

	@Override
	public void executeSetup() {
			db.resetSchema(schema);

			String sql = "select distinct NAME from D_LOC inner join F_ACCT_LOC on D_LOC.id=F_ACCT_LOC.LOC_ID";
			
			//find all the contract and agency level locations which are assigned the accounts.
			List<String> locNames = db.executeQuery(sql, "NAME");

			if(locNames.size()>0){

				for(int i = 0 ; i < locNames.size(); i++) {

					feeSchData.location = locNames.get(i);
					
//					String getSchdID = "select schd.id FROM P_FEE_SCHD schd, P_FEE_COND feecond, P_FEE fee, P_CONDITION cond WHERE schd.delete_ind = '" + "0' and schd.FEE_COND_ID = feecond.ID AND feecond.FEE_ID = fee.FEE_ID AND feecond.COND_ID = cond.ID And schd.active_ind = '" + "1'AND schd.PRD_GRP_CAT_ID=3 AND fee.FEE_TYPE_ID IN ( 2 ) and exists(select loc0.id from d_loc loc0 where loc0.id = schd.SCHD_LOC_ID AND loc0.id = fee.LOC_ID and UPPER(loc0.NAME) LIKE '" + feeSchData.location + "')";
//					//find all the existing&active use fee schedules then update the inventory start date and end date
//					List<String> schdID = db.executeQuery(getSchdID, "ID");
//					if(schdID.size()>0){
//						
//						for(int j =0; j <=  schdID.size(); j++){
//							feeScheduleId = schdID.get(j);
//							String updateStartDate = "update P_FEE_SCHD set start_date = '01-Jan-10' where id = '" + feeScheduleId + "'";
//							int affectedNum1 = db.executeUpdate(updateStartDate);
//							
//							String updateEndDate = "update P_FEE_SCHD set end_date = '31-Dec-20' where id = '" + feeScheduleId + "'";
//							int affectedNum2 = db.executeUpdate(updateEndDate);
//							
//							if (affectedNum1 < 1 && affectedNum2 < 1) {
//								result = result + feeScheduleId + " :failed ";
//								throw new ErrorOnDataException("Can't find the start date or end date for the use fee schedule - " + feeScheduleId);
//							} else {
//								logger.info("Update the start date and end date for the use fee scedule - '" + feeScheduleId + "' successfully.");
//								result = result + feeScheduleId + " :success ";
//							}
//						}
//					}
//					else{
						// add the new use fee schedule for these contract or agency level locations
						// get location category from location name
					db.resetSchema(schema);
						String getTypeID = "select TYPE_ID from D_LOC where NAME = '" + feeSchData.location + "'";
						int type_id = Integer.parseInt(((String) db.executeQuery(getTypeID, "TYPE_ID").get(0)).trim());
						if(type_id == 1){
							feeSchData.locationCategory = "Contract";
						}
						else{
							feeSchData.locationCategory = "Agency";
						}
						
						Object[] args = new Object[4];
						args[0] = this.loginInfo;
						args[1] = this.feeSchData;
						
						AddFeeScheduleFunction func = new AddFeeScheduleFunction();
						func.execute(args);
															
//					}
				}
			}
			else{
				throw new ErrorOnDataException("Can't find any contract or agency level location assigned with accounts under schema - " + schema);
			}

	}

	
	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_usefeeforcontract";
		ids = "select ID from " + dataTableName + " where QA4_RESULT <> 2";

		String env = TestProperty.getProperty("target_env");
		loginInfo.url = AwoUtil.getOrmsURL(env);
		
		loginInfo.userName = TestProperty.getProperty("orms.fnm.user");
		loginInfo.password = TestProperty.getProperty("orms.fnm.pw");
		prefix_ = TestProperty.getProperty(env + ".db.schema.prefix");
	}
}
