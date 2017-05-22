package com.activenetwork.qa.awo.supportscripts.support.financemgr;

import java.util.List;

import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.supportscripts.AbstractSingleDatapoolSupportCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DatapoolUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author fliu
 * @Date  March 15, 2012
 * Notice: feeSchData.acctCode will be changed after DB schema changed so we should not set them from datapool static but get them from UI automatically.
 */
public class AddUseFeeForContract extends AbstractSingleDatapoolSupportCase {

	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private String env;
	private String schema = "";
	private String previousContract;
	private String feeScheduleId = "";
	private String result;
	private boolean loggedIn = false;
	private FinanceManager fin;
	private FinMgrFeeMainPage finFeeMainPg = FinMgrFeeMainPage.getInstance();
	private FeeScheduleData feeSchData = new FeeScheduleData();

	@Override
	protected void initRange() {
		startpoint = 0;
		endpoint = 2;
		env = TestProperty.getProperty("target_env");
	}

	@Override
	protected void readDataPool(IDatapoolIterator dpIter) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+dpIter.dpString("contractForSchema");
		feeSchData = DatapoolUtil.fill(FeeScheduleData.class, dpIter);
		feeSchData.location = "";
		result = "";
	}

	@Override
	public void execute(Orms orms) {
		try {
			db.resetSchema(schema);

			String sql = "select distinct NAME from D_LOC inner join F_ACCT_LOC on D_LOC.id=F_ACCT_LOC.LOC_ID";
			
			//find all the contract and agency level locations which are assigned the accounts.
			List<String> locNames = db.executeQuery(sql, "NAME");

			if(locNames.size()>0){

				for(int i = 0 ; i < locNames.size(); i++) {

					feeSchData.location = locNames.get(i);
					
					String getSchdID = "select schd.id FROM P_FEE_SCHD schd, P_FEE_COND feecond, P_FEE fee, P_CONDITION cond WHERE schd.delete_ind = '" + "0' and schd.FEE_COND_ID = feecond.ID AND feecond.FEE_ID = fee.FEE_ID AND feecond.COND_ID = cond.ID And schd.active_ind = '" + "1'AND schd.PRD_GRP_CAT_ID=3 AND fee.FEE_TYPE_ID IN ( 2 ) and exists(select loc0.id from d_loc loc0 where loc0.id = schd.SCHD_LOC_ID AND loc0.id = fee.LOC_ID and UPPER(loc0.NAME) LIKE '" + feeSchData.location + "')";
					//find all the existing&active use fee schedules then update the inventory start date and end date
					List<String> schdID = db.executeQuery(getSchdID, "ID");
					if(schdID.size()>0){
						
						for(int j =0; j <=  schdID.size(); j++){
							feeScheduleId = schdID.get(j);
							String updateStartDate = "update P_FEE_SCHD set start_date = '01-Jan-10' where id = '" + feeScheduleId + "'";
							int affectedNum1 = db.executeUpdate(updateStartDate);
							
							String updateEndDate = "update P_FEE_SCHD set end_date = '31-Dec-20' where id = '" + feeScheduleId + "'";
							int affectedNum2 = db.executeUpdate(updateEndDate);
							
							if (affectedNum1 < 1 && affectedNum2 < 1) {
								result = result + feeScheduleId + " :failed ";
								throw new ErrorOnDataException("Can't find the start date or end date for the use fee schedule - " + feeScheduleId);
							} else {
								logger.info("Update the start date and end date for the use fee scedule - '" + feeScheduleId + "' successfully.");
								result = result + feeScheduleId + " :success ";
							}
						}
					}
					else{
						// add the new use fee schedule for these contract or agency level locations
						// get location category from location name
						String getTypeID = "select TYPE_ID from D_LOC where NAME = '" + feeSchData.location + "'";
						int type_id = Integer.parseInt(((String) db.executeQuery(getTypeID, "TYPE_ID").get(0)).trim());
						if(type_id == 1){
							feeSchData.locationCategory = "Contract";
						}
						else{
							feeSchData.locationCategory = "Agency";
						}
						
						LoginInfo loginInfo = orms.loginInfo;

						if (!loginInfo.contract.equals(previousContract) && loggedIn) {
							fin.switchToContract(loginInfo.contract, loginInfo.location);
							loggedIn = true;
						}

						if (!loggedIn) { // Logged in
							fin = orms.loginFinanceManager();
							previousContract = loginInfo.contract;
							loggedIn = true;
						}

						fin.gotoFeeMainPage();
						// add the new use fee schedule for contract or agency
						feeScheduleId = fin.addNewFeeSchedule(feeSchData);
						finFeeMainPg.changeScheduleStatus(feeScheduleId, "Active");

						// get current fee schedule's status
						if (finFeeMainPg.isFeeScheduleActive(feeScheduleId)
								&& feeScheduleId.length() > 0) {
							result = result + feeScheduleId + " :success ";

						} else {
							logger.error("Status is inactive for use fee schedule: " + feeScheduleId);
							result = result + feeScheduleId + " :failed ";
						}						
					}
				}
				setResult(result);
			}
			else{
				result = "failed ";
				setResult(result);
				throw new ErrorOnDataException("Can't find any contract or agency level location assigned with accounts under schema - " + schema);
			}

		} catch (Exception e) {
			setResult(result + "Create the new use fee failed. Error -- " + e.getMessage());

			logger.error(e);
			// Plz comment out this line when you debugging
			loggedIn = false;

			throw new RuntimeException(e);
		}
	}
}
