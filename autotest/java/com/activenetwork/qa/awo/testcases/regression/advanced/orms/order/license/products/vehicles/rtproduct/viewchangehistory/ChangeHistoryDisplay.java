/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.rtproduct.viewchangehistory;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductViewChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrEditVehicleDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @ScriptName VehicleProduct_ChangeHistoryDisplay.java
 * @Date:Apr 29, 2011
 * @Description:
 * @author asun
 * @code review by Billy Zhang
 */
public class ChangeHistoryDisplay extends LicenseManagerTestCase {

	String vehicleId="";
	ChangeHistory pHistory = null;
	TimeZone timeZone;
	private VehicleRTI vehicleRTI = new VehicleRTI();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVehicleProductDetailsPage(vehicleRTI.getPrdCode());
		this.updateVehicleValidMonthAndGotoHistoryPage(schema);
		vehicleId=this.getProductIDFromDB(vehicleRTI.getPrdCode(),schema);
		this.verifyProductID(vehicleId);
		this.verifyHistoryInfo();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema=TestProperty.getProperty(env+".db.schema.prefix")+"MS";
		
		vehicleRTI.setPrdCode("R1");
		pHistory = new ChangeHistory();
		pHistory.object="Vehicle RT Product";
		pHistory.user=login.userName;
		pHistory.location=login.location.split("/")[1];
		
		timeZone=DataBaseFunctions.getContractTimeZone((TestProperty.getProperty(env+".db.schema.prefix")+"MS"));
		
	}
	
	/**
	 * update Vehicle ValidMonths in vehicle details page And GotoHistoryPage
	 * @Return void
	 */
	public void updateVehicleValidMonthAndGotoHistoryPage(String schema ){
		LicMgrEditVehicleDetailsPage page=LicMgrEditVehicleDetailsPage.getInstance();
		LicMgrProductViewChangeHistoryPage historyPage=LicMgrProductViewChangeHistoryPage.getInstance();
		
		logger.info("update Vehicle ValidMonths And GotoHistoryPage...");
		pHistory.action="Update";
		pHistory.field="Valid Month";
		pHistory.oldValue=page.getValidMonths().trim();
		int validMon=Integer.parseInt(pHistory.oldValue);
		vehicleRTI.setValidMonths(String.valueOf(validMon>2?--validMon:++validMon));
		page.setValidMonths(vehicleRTI.getValidMonths());
		pHistory.newValue=vehicleRTI.getValidMonths();
		page.clickApply();
//		pHistory.changeDate=DateFunctions.parseDateString(DateFunctions.getToday("E MMM d yyyy hh:mm a", timeZone), "E MMM d yyyy hh:mm a");
		pHistory.changeDate=DateFunctions.getToday("E MMM d yyyy hh:mm a", timeZone);
		page.clickViewChangeHistory();
		historyPage.waitLoading();
	}
	
	/**
	 * verify product ID on view history page
	 * @param idInDB
	 * @Return void
	 */
	public void verifyProductID(String idInDB){
		LicMgrProductViewChangeHistoryPage page=LicMgrProductViewChangeHistoryPage.getInstance();
		String idOnpage=page.getProductID();
		logger.info("verify product ID on page");
		if(!idInDB.equals(idOnpage)){
			throw new ErrorOnPageException("The current product id should be "+idInDB);
		}
		logger.info("Verify product id successfully.....");
	}
	
	
	/**
	 * verify all records are sorted by asc in change history page
	 * @Return void
	 */
	public void verifyHistoryInfo(){
		LicMgrProductViewChangeHistoryPage historyPage=LicMgrProductViewChangeHistoryPage.getInstance();
		List<ChangeHistory> list=historyPage.getChangeHistoryInfo();
		
		logger.info("verify:The Log records found shall be displayed sorted in descending order of the Date & Time, i.e. latest to earliest.");
		for(int i=0;i<list.size()-1;i++){
			if(DateFunctions.compareExactDates(list.get(i).changeDate, list.get(i+1).changeDate) < 0){
				throw new ErrorOnDataException("Date:"+list.get(i).changeDate+" should be behind of date:"+list.get(i+1).changeDate);
			}			
		}
		logger.info("verify successfully");
		
		logger.info("verify update....");
		ChangeHistory firstHistoryOnpage=list.get(0);
		if(!firstHistoryOnpage.equals(pHistory)){
		    throw new ErrorOnPageException("verify history failed.");
		}
		logger.info("Verify update successfully");
		
		logger.info("Verify display info...");
		for(ChangeHistory history:list){
			String msg="";
			if(history.changeDate.toString().trim().length()<1){
				msg+="changeDate,";
			}
			
			if(history.user.trim().length()<1){
				msg+="User,";
			}
			
			if(history.location.trim().length()<1){
				msg+="Location,";
			}
			
			if(history.action.trim().length()<1){
				msg+="Action,";
			}else{
				if(history.action.equalsIgnoreCase("Update")){
					if(history.field.trim().length()<1){
						msg+="Feild lebel,";
					}
//				    if(history.oldValue.trim().length()<1){  //we don`t need check this, sometimes the first record of update may empty on this field. 
//				    	msg+="Old value,";
//				    }
				    if(history.newValue.trim().length()<1){
				    	msg+="New Value,";
				    }
				}
			}
			if(msg.trim().length()>0){
				logger.info("Error on verifying history record:-->"+StringUtil.ObjToString(history));
				throw new ErrorOnPageException("verify "+msg+" failed");
			}
		}
		logger.info("Verify every record display info succcessfully!");
		
		logger.info("Verify the last record");
		ChangeHistory lastHistory=list.get(list.size()-1);
		if(!lastHistory.object.equals("Vehicle RT Product")){
			throw new ErrorOnPageException("The object of the last record should be 'Vehicle RT Product'");
		}
		
		if(!(lastHistory.user.equals(login.userName)||lastHistory.user.equalsIgnoreCase("admin"))){
			throw new ErrorOnPageException("The user of the last record should be '"+login.userName+"'");
		}
		
		if(!lastHistory.location.equals(login.location.split("/")[1])){
			throw new ErrorOnPageException("The location of the last record should be '"+login.userName+"'");
		}
		
//		if(!lastHistory.action.equalsIgnoreCase("Add")){
//			throw new ErrorOnPageException("The action of the last record should be 'Add'");
//		}
		logger.info("verify successfully.");
	    
	}
	
	/**
	 * get product ID from DB by vehicle Code
	 * @param vehicleId
	 * @param schema
	 * @Return String
	 */
	public String getProductIDFromDB(String vehicleCode,String schema){
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		String sql="Select PRD_ID from P_PRD where PRD_CD ='"+vehicleCode+"'";
		db.resetSchema(schema);
		return db.executeQuery(sql, "PRD_ID", 0);
	}

}
