package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddQuotaPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: add a new quota for hunt.
 * @Preconditions: Feature 'ViewPrivilegeLotteryProductList' and 'CreateModifyPrivilegeLotteryQuota' has been assigned
 * @author Phoebe
 * @Date  Nov 27, 2012
 */
public class AddHuntQuotaFunction extends FunctionCase{
	LoginInfo login;
	private LicenseManager lm = LicenseManager.getInstance();
	private QuotaInfo quota = new QuotaInfo();
	private boolean loggedIn = false;
	private String location = "";
	private String contract = "";
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private LicMgrQuotaListPage quotaLisPg = LicMgrQuotaListPage.getInstance();
	
	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		quota = (QuotaInfo)param[1];
		
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
		if(!quotaLisPg.exists()){
			lm.gotoLotteriesProductListPgFromTopMenu();
			lm.gotoQuotaListPgFromLotteriesProdListPg();
		}
		lm.addQuotas(quota);
		
		this.verifyResult();
		newAddValue = quota.getQuotaId();
	}
	
	private void verifyResult() {
		boolean passed = true;
		LicMgrAddQuotaPage addPg = LicMgrAddQuotaPage.getInstance();
		if(addPg.exists()){
			logger.error("[FAILED]Add quota failed, failed reson:" + addPg.getErrorMsg());
			addPg.clickCancel();
			ajax.waitLoading();
			quotaLisPg.waitLoading();
			passed = false;
		}else{
			String schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.split(" ")[0];
			db.resetSchema(schema);
			//Check quota has been added
			String quota_sql = "select id from D_HUNT_QUOTA where description='"
					+ quota.getDescription() + "'";
			List<String> rs =  db.executeQuery(quota_sql, "ID");
			if (rs.size()<1){
				logger.error("Quota(DESC:"+ quota.getDescription() +") added failed");
				passed = false;
			}else{
				quota.setQuotaId(rs.get(0));
				logger.info("[PASSED]Create new quota(DESC:"+ quota.getDescription() +") sucessful");
			}
		}
		if(!passed){
			throw new ErrorOnPageException("Create new quota failed, please see the log above!");
		}
	}
}
