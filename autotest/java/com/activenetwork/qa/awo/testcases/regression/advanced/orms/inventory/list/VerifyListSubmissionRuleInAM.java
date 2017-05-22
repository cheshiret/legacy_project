package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.list;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.RuleDataInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo.ListSubmissionRules;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRuleDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * Add a new list which with list submission rule.
 * Verify rule details info in Admin Manager.
 * @Preconditions:
 * @SPEC: Create List - Create Maximum Number of Entries per List [TC:056709]
 * @Task#: Auto-1994
 * 
 * @author nding1
 * @Date  Jan 6, 2014
 */
public class VerifyListSubmissionRuleInAM extends InventoryManagerTestCase{
	private ListInfo listInfo = new ListInfo();
	private String facilityID, facilityName, ruleName;
	private LoginInfo loginAM = new LoginInfo();
	private AdminManager am = AdminManager.getInstance();
	private RuleDataInfo ruleInfo = new RuleDataInfo();

	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoListSearchPageFromTopMenu();
		listInfo.setListID(im.addList(listInfo));
		im.logoutInvManager();
		
		am.loginAdminMgr(loginAM);
		am.gotoContractRulesPage();
		am.gotoRuleDetailPage(ruleName, facilityName, "Park");
		AdmMgrRuleDetailPage ruleDetailPage = AdmMgrRuleDetailPage.getInstance();
		ruleDetailPage.searchActiveRuleBycommon(ruleInfo, ruleName);
		List<RuleDataInfo> allRuleInfo = ruleDetailPage.getAllRecordsOnPage(ruleName);
		
		// find rule through list name
		boolean result = true;
		if(allRuleInfo.size() < 0){
			throw new ErrorOnPageException("Didn't add "+ruleName+" rule according the new added list "+listInfo.getListName());
		} else {
			for(int i=0; i<allRuleInfo.size(); i++){
				RuleDataInfo actualRuleInfo = allRuleInfo.get(i);
				if(listInfo.getListName().equals(actualRuleInfo.product)){
					result &= MiscFunctions.compareResult("Location", ruleInfo.location, actualRuleInfo.location);
					result &= MiscFunctions.compareResult("Maximum Number of Entries", ruleInfo.maximumNumberOfEntries, actualRuleInfo.maximumNumberOfEntries);
					result &= MiscFunctions.compareResult("Product Category", ruleInfo.productCategory, actualRuleInfo.productCategory);
					result &= MiscFunctions.compareResult("Loop/Dock", ruleInfo.loop, actualRuleInfo.loop);
					result &= MiscFunctions.compareResult("Effective Date", ruleInfo.effectiveDate, actualRuleInfo.effectiveDate);
					break;
				}
			}
			if(!result){
				throw new ErrorOnPageException("---Check logs above!");
			}
		}
		
		am.logoutAdminMgr();
		
		// clean up: close list
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoListSearchPageFromTopMenu();

		// close it
		im.closeList(listInfo.getListID(), "Not Available", "Close this list.");
		im.logoutInvManager();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		facilityID = "552859";// New River State Park
		facilityName = im.getFacilityName(facilityID, schema);
		ruleName = "Maximum Number of Entries per List";
		
		loginAM.url = login.url;
		loginAM.contract = login.contract;
		loginAM.location = login.location;
		loginAM.userName = login.userName;
		loginAM.password = login.password;
		
		listInfo.setListName("VerifyListSubmissionRuleInAM"+DateFunctions.getCurrentTime());
		listInfo.setNumOfChoice("2");
		listInfo.setHeaderMessage("Good moring, everyone~");
		listInfo.setListTermCon("Thank you for purchasing this list.");
		
		// must setup submission rule info!!
		ListSubmissionRules listSubrules = new ListSubmissionRules();
		listSubrules.setMaxNum("8");
		listSubrules.setRule(ruleName);
		List<ListSubmissionRules> ruleList = new ArrayList<ListSubmissionRules>();
		ruleList.add(listSubrules);
		listInfo.setListSubrules(ruleList);

		ruleInfo.status = ACTIVE_STATUS;
		ruleInfo.productCategory = "List";
		ruleInfo.location = facilityName;
		ruleInfo.loop = facilityName;
		ruleInfo.maximumNumberOfEntries = Integer.valueOf(listSubrules.getMaxNum());
		
		TimeZone tz = DataBaseFunctions.getContractTimeZone(schema);
		ruleInfo.effectiveDate = DateFunctions.getToday(tz);
	}
}