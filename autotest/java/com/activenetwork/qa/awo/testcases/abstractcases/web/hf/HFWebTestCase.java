/**
 * 
 */
package com.activenetwork.qa.awo.testcases.abstractcases.web.hf;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.keywords.web.HFABKeyword;
import com.activenetwork.qa.awo.keywords.web.HFKeyword;
import com.activenetwork.qa.awo.testcases.abstractcases.AWOTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is for HF Web Test Cases
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Feb 25, 2013
 */
public abstract class HFWebTestCase extends AWOTestCase  {
	protected HFKeyword hf; 
	protected HFABKeyword hfab; 
	protected String url;
	protected Customer cus;
	protected Customer cusNew;
	protected PrivilegeInfo privilege;
	protected HFLotteryProduct lottery;
	protected Payment pay;
	protected LoginInfo loginLM;
	protected LicenseManager lm;
	protected Suspension suspension;
	protected String voidReserveReason, undoVoidReserveReason, searchLicYear, conmmonSchema;
	protected String[] searchStatus;
	
	public HFWebTestCase() {
		super();
		hf = HFKeyword.getInstance();
		hfab = HFABKeyword.getInstance();
		cus = new Customer();
		cusNew = new Customer();
		pay = new Payment("Visa");
		lm = LicenseManager.getInstance();
		suspension = new Suspension();
		
		privilege = new PrivilegeInfo();
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";	
		voidReserveReason = "14 - Other";
		undoVoidReserveReason = "17 - Other";
		
		lottery = new HFLotteryProduct();
		
		loginLM = new LoginInfo();
		loginLM.url = AwoUtil.getOrmsURL(env);
		loginLM.userName = TestProperty.getProperty("orms.fm.user");
		loginLM.password = TestProperty.getProperty("orms.fm.pw");
	} 
	
	protected boolean isValidDatesHide(String url) {
		String brand = MiscFunctions.getPLNameFromURL(url);
		//Lesley[20140227] update due to different settings for HFSK and HFMO
//		return WebConfiguration.getInstance().isHFWebHideValidDates(brand);
		String contracts =WebConfiguration.getInstance().getUIOption(Conf.plbrand_UIOptions, UIOptions.ContractsWithHFWebHideValidDates, brand); //UIOptionConfigurationUtil.getContractsWithHFWebHideValidDates(brand);
		boolean displayValidDates=WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.HFWebDisplayValidDates, brand);
		return !displayValidDates || (StringUtil.notEmpty(contracts) && contracts.toLowerCase().contains(brand.replace("hf", "")));
//		return contracts.toLowerCase().contains(brand.replace("hf", ""));
	}
}
