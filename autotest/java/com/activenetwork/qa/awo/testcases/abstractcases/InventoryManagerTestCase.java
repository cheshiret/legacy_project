package com.activenetwork.qa.awo.testcases.abstractcases;

import com.activenetwork.qa.awo.datacollection.legacy.CampingUnit;
import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.datacollection.legacy.LoopInfoData;
import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.LotterySchedule;
import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.datacollection.legacy.SiteAttributes;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EventInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TourInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeCalculationFunctions;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description : Functional Test Script
 * 
 * @author QA
 */
public abstract class InventoryManagerTestCase extends OrmsTestCase {
	/**
	 * Script Name : <b>InventoryManagerTestCase</b> Generated : <b>Mar 1, 2010
	 * 9:07:53 PM</b> Description : Functional Test Script Original Host : WinNT
	 * Version 5.1 Build 2600 (S)
	 * 
	 * @since 2010/03/01
	 * @author mchen
	 */

	protected InventoryManager im;

	protected InventoryInfo inventory;

	protected SiteAttributes siteAttr;

	protected SeasonData season;

	protected LoopInfoData loop;

	protected Closure closure;
	
	protected ReservationInfo res;

	protected CampingUnit camp;

	protected EventInfo event;

	protected TicketInfo ticket;

	protected TourInfo tour;

	protected Lottery lottery;
	
	protected POSInfo pos;

	protected LotterySchedule lotterySchedule;

	protected FeeScheduleInformation feeInfo;

	protected FeeCalculationFunctions feeCal;

	protected String templatesPath, comparedPath, errorResultPath, posBarcodeTempPatch;

	public InventoryManagerTestCase() {
		super();

		im = InventoryManager.getInstance();
		inventory = new InventoryInfo();
		res = new ReservationInfo();
		siteAttr = new SiteAttributes();
		season = new SeasonData();
		loop = new LoopInfoData();
		closure = new Closure();
		camp = new CampingUnit();
		event = new EventInfo();
		ticket = new TicketInfo();
		tour = new TourInfo();
		lottery = new Lottery();
		pos = new POSInfo();
		lotterySchedule = new LotterySchedule();
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
		
		feeInfo=FeeScheduleInformation.getInstance();
		feeCal=FeeCalculationFunctions.getInstance();
		

		String project_path = AwoUtil.PROJECT_PATH;
		if (project_path.contains("\\")) {
			templatesPath = project_path.substring(0, project_path
					.lastIndexOf("\\"))
					+ "\\ReportTemplates";
			posBarcodeTempPatch = project_path.substring(0, project_path
					.lastIndexOf("\\"))
					+ "\\POSBarcodeTemplates";

		} else {
			String path = project_path.substring(0,
					project_path.lastIndexOf("/")).replace("/", "\\");
			templatesPath = path.substring(0, path.lastIndexOf("\\"))
					+ "\\ReportTemplates";
			posBarcodeTempPatch = path.substring(0, path.lastIndexOf("\\"))
			+ "\\POSBarcodeTemplates";
		}
		comparedPath = logger.getLogPath() + "/ComparedFile";
		errorResultPath = logger.getLogPath() + "/ResultPath";
	}
	
}
