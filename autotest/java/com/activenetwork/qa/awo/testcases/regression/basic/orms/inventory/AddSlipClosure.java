package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosureDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * Create Closure Schedule [TC:045393] 
 * @Task#: Auto-1893
 * @author Jane
 * @Date  Sep 17, 2013
 */
public class AddSlipClosure extends InventoryManagerTestCase {
	private String facilityID;
	private TimeZone tz;
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoClosurePage();
		
		closure.closureID = this.createClosure(closure);
		im.gotoClosureDetaiPg(closure.closureID, false);
		verifyClosureDetailsInfo(closure);
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
	    login.contract = "NC Contract";
	    login.location="Administrator/North Carolina State Parks";
	    schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NC";
	    tz = DataBaseFunctions.getContractTimeZone(schema);
	    
	    facilityID = "552834";
	    
	    closure.productCategory = "Slip";
	    closure.type="Maintenance";
	    closure.startDate=DateFunctions.getToday(tz);
	    closure.endDate=DateFunctions.getDateAfterToday(90, tz);
	    closure.status=OrmsConstants.ACTIVE_STATUS;
	    closure.occurencePattern = "Monthly";
	    closure.dayOfMonth = "2";
	    closure.comment="Regression Test";
	}
	
	private String createClosure(Closure closure) {
		InvMgrClosureDetailPage invClosureDetailPg = InvMgrClosureDetailPage.getInstance();
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();
		String closureID = "";

		logger.info("Add a new closure.");

		invClosurePg.clickAddNew();
		invClosureDetailPg.waitLoading();

		invClosureDetailPg.fillClosureDetails(closure);
		invClosureDetailPg.clickApply();
		invClosureDetailPg.waitLoading();
		
		closureID = invClosureDetailPg.getClosureID();
		invClosureDetailPg.clickOK();
		invClosurePg.waitLoading();
		
		return closureID;
	}

	private void verifyClosureDetailsInfo(Closure closure) {
		InvMgrClosureDetailPage invClosureDetailPg = InvMgrClosureDetailPage.getInstance();
		boolean passed = true;
		
		String closureType = invClosureDetailPg.getClosureType();
		String prdCategory = invClosureDetailPg.getProductCategory();
		String startDate = invClosureDetailPg.getStartDate();
		String endDate =  invClosureDetailPg.getEndDate();
		boolean isActive = invClosureDetailPg.isActive();
		String affRes = invClosureDetailPg.getAffRes();
		String application = invClosureDetailPg.getApplication();
		String occPattern = invClosureDetailPg.getOccPattern();
		String dayOfMonth = invClosureDetailPg.getDayOfMonth();
		String createDateTime = invClosureDetailPg.getCreateDate();
		String createDate = RegularExpression.getMatches(createDateTime, "[a-zA-Z]{3} \\d{1,2}, \\d{4}")[0];
		
		passed &= MiscFunctions.compareResult("Closure Type was wrong on page.", closure.type, closureType);
		passed &= MiscFunctions.compareResult("Product Category was wrong on page.", closure.productCategory, prdCategory);
		passed &= MiscFunctions.compareResult("Create date was wrong on page.", DateFunctions.getToday(tz), createDate);
		passed &= MiscFunctions.compareResult("Active status was wrong on page.", closure.status.equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS)?true:false, isActive);
		passed &= MiscFunctions.compareResult("Application was wrong on page.", "Inventory Manager", application);
		passed &= MiscFunctions.compareResult("Affected Reservations was wrong on page.", "No-Not Specified", affRes);
		passed &= MiscFunctions.compareResult("Start date was wrong on page.", closure.startDate, startDate);
		passed &= MiscFunctions.compareResult("End date was wrong on page.", closure.endDate, endDate);
		passed &= MiscFunctions.compareResult("Occurrence Pattern was wrong on page.", closure.occurencePattern, occPattern);
		passed &= MiscFunctions.compareResult("Day of Month was wrong on page.", closure.dayOfMonth, dayOfMonth);
		
		if(!passed)
			throw new ErrorOnPageException("Closure details info was wrong on page, please check log for details.");
		logger.info("---Verify closure details info successfully on page.");
	}
}
