package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.applicationspg;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryApplicationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * 
 * @Description: (P) Verify Lottery application page title and no results message when account without any lottery order history
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1240
 * @SPEC:Lottery Applications - blank applications list section [TC:058282] 
 * @Task#:Auto-1724
 * 
 * @author SWang
 * @Date  Feb 10, 2014
 */
public class BlankApp_NoLotteryOrder extends HFSKWebTestCase {
	private String pageTitleRegx, noResultsMsg;
	
	@Override
	public void execute() {
		//Login in with an account without any lottery order
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoLotteryAppPgFromYourAccountFoundPg();
		
		//Check page title and no results message in lottery application page
		verifyMsgInLotterAppPgWhenAccountWithoutAnyLotteryOrder();
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4179";
		cus.identifier.state = "Ontario";
		cus.dateOfBirth = "1986-01-01";
		
		pageTitleRegx = "Big Game Draw Application Summary Note: The individual who submitted the Big Game Draw Application is the only member who can edit or modify it \\(update WMZs and/or update members on the application\\). Applications can only be edited through your online account if they were submitted online\\.";
	    noResultsMsg = "Your Big Game Draw applications will appear here.";
	}

	private void verifyMsgInLotterAppPgWhenAccountWithoutAnyLotteryOrder() {
		HFLotteryApplicationPage lotteryAppPg = HFLotteryApplicationPage.getInstance();
		boolean result = MiscFunctions.matchString("Page title", lotteryAppPg.getPageTitle(), pageTitleRegx);
		result &= MiscFunctions.compareString("No results message", noResultsMsg, lotteryAppPg.getNoResultMsg());
		if (!result) {
			throw new ErrorOnPageException("Page title or no results message are wrong. Please check the details from previous logs");
		}
		logger.info("Page title and no results message are wrong!");
	}
}

