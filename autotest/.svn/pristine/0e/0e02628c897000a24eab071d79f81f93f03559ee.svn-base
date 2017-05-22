package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.editlotteryschedule.inventory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class CheckLotteyDetailDisabled extends InventoryManagerTestCase {

	LotteryDetailsPage lotteryDetailPg = LotteryDetailsPage.getInstance();
	private List<Exception> exceptionList = new ArrayList<Exception>();

	public void execute() {
		// Goto Lottery Details page
		im.loginInventoryManager(login);
		im.gotoLotteryDetailsPageFromHomePage(lottery);

		this.checkLotteryDetailsObjects();
		if (exceptionList.size() > 0) {
			for (Exception e : exceptionList) {
				e.printStackTrace();
			}
			throw new TestCaseFailedException("Failed:" + exceptionList.size());
		}

		// Clean environment
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] param) {

		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";

		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		// need to set lottery schedule or use a lottery with an active schedule.
		lottery.id = im.getLotteryId(schema, "Four Rivers Lottery edite lottery schedule for permit");
			//	"Web Sanity submit lottery");
	}

	public void checkLotteryDetailsObjects() {
		if (!lotteryDetailPg.lotteryNameDisable()) {
			exceptionList.add(new ErrorOnPageException(
					"The button is not disabled."));
		} else if (lotteryDetailPg.lotteryDescriptionDisable()) {
			exceptionList.add(new ErrorOnPageException(
					"The button is not disabled."));
		} else if (lotteryDetailPg.lotteryLocationDisable()) {
			exceptionList.add(new ErrorOnPageException(
					"The button is not disabled."));
		} else if (!lotteryDetailPg.productCategoryDisable()) {
			exceptionList.add(new ErrorOnPageException(
					"The button is not disabled."));
		} else if (!lotteryDetailPg.productGroupsDisable()) {
			exceptionList.add(new ErrorOnPageException(
					"The button is not disabled."));
		} else if (lotteryDetailPg.permitCategoriesDisable()) {
			exceptionList.add(new ErrorOnPageException(
					"The button is not disabled."));
		} else if (lotteryDetailPg.permitTypeDisable()) {
			exceptionList.add(new ErrorOnPageException(
					"The button is not disabled."));
		} else if (lotteryDetailPg.maxNumDisable()) {
			exceptionList.add(new ErrorOnPageException(
					"The button is not disabled."));
		} else if (lotteryDetailPg.requiredCustomerYesOrNoButtonDisable(1)) {
			exceptionList.add(new ErrorOnPageException(
					"The button is not disabled."));
		} else if (lotteryDetailPg.creditCardYesOrNoButtonDisable(1)) {
			exceptionList.add(new ErrorOnPageException(
					"The button is not disabled."));
		} else {

		}
	}

}
