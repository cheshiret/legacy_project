package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.huntchoiceselection;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.HFPrivilegeProperty;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryHuntsSelectionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: Verify pagination header and controls in Add Hunts page
 * @Preconditions:
 * 1. Make sure the page size = 5 in /opt/sites/qa3/uwppl/docs/brands/hfsk/jsp/layout/ui-options.xml
 * <option name="lottery" >
 *		<option name="search-results">
 *	 			<option name="page-size">
 *					<option name="hunts" value="5" />
 * 2. Make sure the lottery has at least 11 hunts assigned
 * @SPEC: List Available Hunts - Pagination Header and controls [TC:056220]
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1280
 * d_hf_add_species:id=40,50
 * d_hf_add_hunt_location:id=60,70,80,90
 * d_hf_add_date_period:id=60,70
 * d_hf_add_weapon:id=50
 * d_hf_add_privilege_prd:id=2630
 * d_hf_add_pricing:id=3822
 * d_hf_assi_pri_to_store:id=1850
 * d_hf_add_prvi_license_year:id=2740
 * d_hf_add_qty_control:id=1830
 * d_hf_add_hunt:id=480,490,500,510,520,530,540,550,560,570,580,590
 * d_hf_add_hunt_license_year:id=510,520,530,540,550,560,570,580,590,600,610
 * d_hf_assign_priv_to_hunt:id=360,370,380,390,400,410,420,430,440,450,460,470
 * d_hf_add_hunt_quota:id=210,270
 * d_hf_add_lottery_prd:id=200
 * d_hf_add_lottery_license_year:id=80
 * d_hf_assign_lottery_to_store:id=80
 * d_hf_add_lottery_quantity_control:id=80
 * d_hf_assi_hunts_to_lottery:id=90
 * @Task#: Auto-1773
 * 
 * @author Lesley Wang
 * @Date  Feb 18, 2014
 */
public class HuntsList_Pagination extends HFSKWebTestCase {
	private HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
	private String instructionText, label, species;
	private int expPageSize;
	@Override
	public void execute() {
		this.checkPageSizeInUIOption(expPageSize);
		
		hf.signIn(url, cus.email, cus.password);
		hf.gotoAddHuntsPgAsIndividualFromHeaderBar(cus, lottery.getDescription(), false);
		int totalResult = hf.getNumOfAvailableLotteryHuntsForIndividual(schema, lottery.getDescription(), lottery.getLicenseYear());
		this.verifyPagination(1, totalResult, false);
		
		// click Next page and verify
		huntsPg.gotoNextPg();
		this.verifyPagination(1+expPageSize, totalResult, false);
		
		// go to Last page and verify
		huntsPg.gotoLastPgFromPgPicker();
		this.verifyPagination((totalResult/expPageSize)*expPageSize+1, totalResult, false);
		
		// click Previous page and verify
		huntsPg.gotoPreviousPg();
		this.verifyPagination((totalResult/expPageSize - 1)*expPageSize+1, totalResult, false);
		
		// Show All
		huntsPg.gotoFirstPg();
		huntsPg.showAllHunts();
		this.verifyPagination(1, totalResult, true);
		
		// Show By Page
		huntsPg.showByPage();
		this.verifyPagination(1, totalResult, false);
		
		// filter hunts to show less than 5
		totalResult = Integer.valueOf(huntsPg.getFilterItemNum(species));
		huntsPg.filterHunts(species);
		this.verifyPagination(1, totalResult, false);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00048@webhftest.com";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4183";
		cus.identifier.state = "Saskatchewan";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.dateOfBirth = "1986-01-01";
		
		// Lottery info
		lottery.setDescription("Lottery With Mult Hunts");
		lottery.setLicenseYear(hf.getFiscalYear(schema));
		species = "SpeciesWithSubType";
		
		// The text is configuable in /opt/sites/qa3/uwppl/docs/properties/config/hf/HFPrivilegesResouces.properties, key=lottery.hunts.page.header.directive_HFSK
		instructionText = WebConfiguration.getInstance().getPropertiesValue(Conf.HFPrivilege_prop, HFPrivilegeProperty.HFSK_Head_Directive).trim();//.getHFPrivilegeProperty("lottery.hunts.page.header.directive_HFSK");
		label = "Available WMZs: ";
		expPageSize = 5;
		
	}

	private void checkPageSizeInUIOption(int size) {
		String actualSize = WebConfiguration.getInstance().getUIOption(Conf.plbrand_UIOptions, UIOptions.LotteryHuntsSearchResultPageSize, "hfsk");//UIOptionConfigurationUtil.getLotteryHuntsSearchResultPageSize("hfsk");
		if (!Integer.valueOf(actualSize).equals(size)) {
			throw new ErrorOnPageException("The page size in ui-option is wrong! " +
					"Please submit a JIRA ticket such as QA-11766 to update the settings " +
					"in /opt/sites/qa3/uwppl/docs/brands/hfsk/jsp/layout/ui-options.xml, lottery->search-results->page-size->hunts", 
					size, actualSize);
		}
		logger.info("The page size in ui-option is correct as " + size);
	}
	
	private void verifyPagination(int firstNum, int totalNum, boolean isShowAll) {
		boolean result = true;
		
		// Total Item Count		
		int toNum = expPageSize+firstNum-1;
		boolean hasNexPg = totalNum > toNum && !isShowAll;
		String expCountLabel = label + firstNum + "-" + (hasNexPg ?  toNum: totalNum) + " of " + totalNum; 
		if (isShowAll) {
			expCountLabel = label + firstNum + "-" + totalNum + " of " + totalNum;
		}
		result &= MiscFunctions.containString("Header Pagination label", huntsPg.getHeaderPaginationText(), expCountLabel);
		result &= MiscFunctions.containString("Footer Pagination label", huntsPg.getFooterPaginationText(), expCountLabel);
		
		// Instructional text
		result &= MiscFunctions.compareString("Hunt list instruction text", instructionText, huntsPg.getHuntListInstruction());
		
		// Next link
		result &= MiscFunctions.compareResult("Header Next link exist", hasNexPg, huntsPg.checkNextLinkExist());
		result &= MiscFunctions.compareResult("Footer Next link exist", hasNexPg, huntsPg.checkFooterNextLinkExist());
		
		// Previous link
		boolean isFirstPg = firstNum==1;
		result &= MiscFunctions.compareResult("Header Previous link exist", !isFirstPg, huntsPg.checkPreviousLinkExist());
		result &= MiscFunctions.compareResult("Footer Previous link exist", !isFirstPg, huntsPg.checkFooterPreviousLinkExist());
		
		// Page Picker drop down list
		boolean hasMorePg = (firstNum!=1 || totalNum > expPageSize);
		result &= MiscFunctions.compareResult("Header Page Picker exist", hasMorePg&&!isShowAll, huntsPg.checkHeaderPagePickerListExist());
		result &= MiscFunctions.compareResult("Footer Page Picker exist", hasMorePg&&!isShowAll, huntsPg.checkFooterPagePickerListExist());
		
		// Show All / Show By Page link
		result &= MiscFunctions.compareResult("Header Show All link exist", hasMorePg&&!isShowAll, huntsPg.checkHeaderShowAllLinkExist());
		result &= MiscFunctions.compareResult("Header Show By page link exist", hasMorePg&&isShowAll, huntsPg.checkHeaderShowByPageLinkExist());
		
		if(!result) {
			throw new ErrorOnPageException("Pagination is wrong!");
		}
		logger.info("Pagination is correct!");
	}
}
