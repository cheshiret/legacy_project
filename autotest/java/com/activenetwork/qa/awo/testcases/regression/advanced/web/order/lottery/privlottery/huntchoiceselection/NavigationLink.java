package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.huntchoiceselection;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryHuntsSelectionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: Verify the navigation link on Add Hunts page
 * @Preconditions: 
 * 1. Make sure the lottery product exists and has been assigned to web.
 * 2. Make sure there are more than 5 hunts associated with the lottery
 * Make sure the page size = 5 in /opt/sites/qa3/uwppl/docs/brands/hfsk/jsp/layout/ui-options.xml
 * <option name="lottery" >
 *		<option name="search-results">
 *	 			<option name="page-size">
 *					<option name="hunts" value="5" />
 * If it is not, please submit a JIRA ticket such as QA-11766 to update the settings
 * @SPEC: Add Hunts page - Navigation Link [TC:056455]
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1280
 * d_hf_add_species:id=40
 * d_hf_add_hunt:id=450,480,490,500,510,520,530,540,550,560,570,580,590
 * d_hf_add_lottery_prd:id=200
 * d_hf_add_lottery_license_year:id=80
 * d_hf_assign_lottery_to_store:id=80
 * d_hf_add_lottery_quantity_control:id=80
 * d_hf_assi_hunts_to_lottery:id=90
 * d_hf_add_hunt_license_year:id=460,510,520,530,540,550,560,570,580,590,600,610,620
 * d_hf_add_pricing:id=3822
 * @Task#: Auto-1772
 * 
 * @author Lesley Wang
 * @Date  Feb 11, 2014
 */
public class NavigationLink extends HFSKWebTestCase {
	HFLotteryDetailsPage lotteryDetailsPg = HFLotteryDetailsPage.getInstance();
	HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
	
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);
		hf.gotoLotteryCategoriesListPg(cus);
		hf.gotoLotteryDetailsPgFromCatListPg(lottery.getDescription());
		
		hf.applyLotteryAsIndividualToAddHuntsPg();
		hf.gotoLotteryDetailsPgFromAddHuntsPg();
		this.verifyApplicantTypeSelection(true, false);
		
		hf.applyLotteryAsGroupLeaderToAddHuntsPg();
		hf.gotoLotteryDetailsPgFromAddHuntsPg();
		this.verifyApplicantTypeSelection(false, true);
		
		hf.applyLotteryAsIndividualToAddHuntsPg();
		huntsPg.addFirstChoice();
		huntsPg.gotoNextPg();
		hf.gotoLotteryDetailsPgFromAddHuntsPg();
		this.verifyApplicantTypeSelection(true, false);
		
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
		
		lottery.setDescription("Lottery With Mult Hunts");
	}

	private void verifyApplicantTypeSelection(boolean individualSelected, boolean groupSelected) {
		boolean result = MiscFunctions.compareResult("Individual Type Selected", individualSelected, lotteryDetailsPg.isIndividualTypeSelected());
		result &= MiscFunctions.compareResult("Group Leader Type selected", groupSelected, lotteryDetailsPg.isGroupLeaderTypeSelected());
		
		if (!result) {
			throw new ErrorOnPageException("Applicant Type Selection is wrong!");
		}
		logger.info("Successfully verify applicante type selection!");
	}
}
