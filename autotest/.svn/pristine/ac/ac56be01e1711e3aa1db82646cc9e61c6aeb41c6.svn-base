package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.lottery.PointTypesAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrPointTypeDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrPointTypesListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Feb 13, 2014
 */
public class AddPointTypesFunction extends FunctionCase{
	LoginInfo login;
	private LicenseManager lm = LicenseManager.getInstance();
	private Data<PointTypesAttr> pointType = new Data<PointTypesAttr>();
	private boolean loggedIn = false;
	private String location = StringUtil.EMPTY;
	private String contract = StringUtil.EMPTY;
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private LicMgrPointTypesListPage pointTypeLisPg = LicMgrPointTypesListPage.getInstance();
	
	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		pointType = (Data<PointTypesAttr>)param[1];
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
	}
	@Override
	public void execute() {
		//Login finance manager  
		if((!contract.equalsIgnoreCase(login.contract)||!location.equalsIgnoreCase(login.location))&& loggedIn && isBrowserOpened){
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		contract = login.contract;
		location = login.location;
		if((!loggedIn || !isBrowserOpened)){
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		if(!pointTypeLisPg.exists()){
			lm.gotoLotteriesProductListPgFromTopMenu();
			lm.gotoPointTypesListPgFromLotteriesProdListPg();
		}
		pointType.put(PointTypesAttr.id, lm.addPointType(pointType));
		
		this.verifyResult();
		newAddValue = pointType.stringValue(PointTypesAttr.id);
	}
	
	private void verifyResult() {
		boolean passed = true;
		LicMgrPointTypeDetailsPage pointDetailsPG = LicMgrPointTypeDetailsPage.getInstance();
		if(pointDetailsPG.exists()){
			logger.error("[FAILED]Add point type failed, failed reson:" + pointDetailsPG.getErrorMsg());
			pointDetailsPG.clickCancel();
			ajax.waitLoading();
			pointTypeLisPg.waitLoading();
			passed = false;
		}else{
			String schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.split(" ")[0];
			db.resetSchema(schema);
			//Check point type has been added
			String pointType_sql = "select id from D_POINT_TYPE where descr='"+ pointType.stringValue(PointTypesAttr.description) + "'";
			List<String> rs =  db.executeQuery(pointType_sql, "ID");
			if (rs.size()<1){
				logger.error("Point type(DESC:"+ pointType.stringValue(PointTypesAttr.description) +") added failed");
				passed = false;
			}else{
				pointType.put(PointTypesAttr.id, rs.get(0));
				logger.info("[PASSED]Create new quota(DESC:"+ pointType.stringValue(PointTypesAttr.description) +") sucessful");
			}
		}
		if(!passed){
			throw new ErrorOnPageException("Create new point type failed, please see the log above!");
		}
	}
}
