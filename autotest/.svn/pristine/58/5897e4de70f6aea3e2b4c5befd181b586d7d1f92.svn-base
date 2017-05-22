package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.permitsandwilderness;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityDetail.InvMgrFacilityDetailPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * 1.Verify the top result displayed as required;
 * 2.Verify the alerts showing right under the title as required.
 * @Preconditions:
 * @SPEC:
 *  UWP Unified Search_FacilitySearch_UC (Permit Search Results)
 *  UWP Unified Search_Search Form_UI (Permit Search Results)
 * @Task#:AUTO-783
 * 
 * @author SWang
 * @Date  Oct 31, 2011
 */
public class PermitAlertValidation extends RecgovTestCase{
	private RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
	private String parkSpecificMess;

	private InventoryManager im = InventoryManager.getInstance();
	private LoginInfo loginIm = new LoginInfo();

	public void execute() {
		im.loginInventoryManager(loginIm);
		parkSpecificMess = this.getWebFacilitySpecificMess();
		im.logoutInvManager();

		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		searchResult.verifyParkName(bd.contractCode, bd.parkId, bd.whereTextValue);
		this.verifyParkAttention();

		bd.parkId = "72202";
		bd.whereTextValue = DataBaseFunctions.getFacilityName(bd.parkId, schema);//ORMS facility: Desolation Wilderness

		im.loginInventoryManager(loginIm);
		parkSpecificMess = this.getWebFacilitySpecificMess();
		im.logoutInvManager();

		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		searchResult.verifyParkName(bd.contractCode, bd.parkId, bd.whereTextValue);
		this.verifyParkAttention();
	}

	public void wrapParameters(Object[] param) {
		loginIm.url = AwoUtil.getOrmsURL(env);
		loginIm.userName = TestProperty.getProperty("orms.im.user");
		loginIm.password = TestProperty.getProperty("orms.im.pw");
		loginIm.contract = "NRRS Contract";
		loginIm.location = "Administrator/NRRS";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		bd.contractCode = "NRSO";
		bd.parkId = "79064";

		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		bd.whereTextValue = DataBaseFunctions.getFacilityName(bd.parkId, schema);//ORMS facility: CABLES ON HALF DOME
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
	}	

	private String getWebFacilitySpecificMess(){
		InvMgrFacilityDetailPage invDetails = InvMgrFacilityDetailPage
		.getInstance();
		im.gotoFacilityDetailsPg(bd.whereTextValue);
		return invDetails.getWebFacilitySpecificMess();
	}

	private void verifyParkAttention(){
		if(parkSpecificMess.trim().equals("")){
			boolean flag = searchResult.checkAttentionExist(bd.contractCode, bd.parkId);
			if(flag){
				throw new ErrorOnDataException("If the specific message is empty in Inventory message, it " +
				"should not display in park view as list page.");
			}
		}else{
			searchResult.verifyAttentionText(bd.contractCode, bd.parkId, parkSpecificMess);
		}
	}
}
