/**
 * 
 */
package com.activenetwork.qa.awo.testcases.abstractcases;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ConsumableInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.SupplyInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Vehicle;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @ScriptName LicenseManagerTestCase.java
 * @Date:Dec 23, 2010
 * @Description:
 * @author asun
 */
public abstract class LicenseManagerTestCase extends OrmsTestCase{

	protected LicenseManager lm;
	protected PrivilegeInfo privilege;
//	protected VehicleRTI vehicleRTI;
	protected Vehicle vehicle;
	protected ConsumableInfo consumable;
	protected HFLotteryProduct lotteryPrd;
	protected HuntInfo hunt;
	protected SupplyInfo supply;
	protected LicMgrDocumentInfo document;
	protected QuestionInfo question;
	protected PricingInfo pricing;
	protected boolean custValidation;
	protected String templatesPath, comparedPath;
	
	public LicenseManagerTestCase(){
		super();
		
		lm=LicenseManager.getInstance();
		
		privilege = new PrivilegeInfo();
//		vehicle = new Vehicle();
		consumable = new ConsumableInfo();
		supply = new SupplyInfo();
		lotteryPrd = new HFLotteryProduct();
		hunt = new HuntInfo();
		
		env=TestProperty.getProperty("target_env");
		//login.userName = TestProperty.getProperty("orms.fm.user");
		//login.password = TestProperty.getProperty("orms.fm.pw");
		login.userName = TestProperty.getProperty("orms.migr.user");
		login.password = TestProperty.getProperty("orms.migr.psw");
				
		login.url = AwoUtil.getOrmsURL(env);
		

		String project_path = AwoUtil.PROJECT_PATH;
		if(!project_path.contains("\\")){
			project_path = project_path.substring(0,
					project_path.lastIndexOf("/")).replace("/", "\\");
		}
		templatesPath = project_path.substring(0, project_path
				.lastIndexOf("\\"))
				+ "\\ReportTemplates";
		comparedPath = logger.getFullLogRootPath() + "/ComparedFile";
	    
		document = new LicMgrDocumentInfo();
		
		question = new QuestionInfo();
		pricing=new PricingInfo();
		
		pay.pin=lm.getPinNum(login.userName);
		custValidation=Boolean.valueOf(TestProperty.getProperty(env+".lm.cust.validation", "true"));
	}

}
