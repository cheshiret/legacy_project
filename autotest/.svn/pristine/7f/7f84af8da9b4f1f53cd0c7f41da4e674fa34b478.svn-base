/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.createlottery;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotterySearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description: This case is used to verify create lottery program for slip  
 *               check point:1. Dock, Site Type and Slip attributes are not applicable for the lottery
 *                           2. The contract associated with the lottery has one or more Boat Categories
 *                           3. Enter a duplicated perference Attribute and Click OK
 *                           4. Keep one of the Display Orders of Preference Attribute as empty
 *                           5. Enter one of the Display Orders as 0.
 *                           6. Enter one of the Display Orders as negative interger number like -2.
 *                           7. Enter one of the Display Orders as decimal number like 1.5.
 *                           8. Enter one of the Display Orders same as another.
 * @Preconditions: 
 * @SPEC: TC:042087,TC:042085
 * @Task#: AUTO-1341 
 * 
 * @author szhou
 * @Date  Dec 25, 2012
 */
public class VerifyPreferenceAttributeValidation extends
		InventoryManagerTestCase {
	String error;

	@Override
	public void execute() {
		// log into inventory manager
		im.loginInventoryManager(login);

		// Dock, Site Type and Slip attributes are not applicable for the lottery
		im.gotoAddNewLottery(lottery);
		this.addLotteryProgram(lottery);
		this.verifyErrorMessage(error);
		this.gotoSearchPage();

		// The contract associated with the lottery has one or more Boat Categories
		error="Boat Category must be an applicable lottery attribute. Please change your lottery setup.";
		lottery.attributes.get(4).label = "Dock";
		lottery.attributes.get(4).entryRequired = "Per Preference";
		im.gotoAddNewLottery(lottery);
		this.addLotteryProgram(lottery);
		this.verifyErrorMessage(error);
		this.gotoSearchPage();

		// Enter a duplicated perference Attribute and Click OK
		error="Duplicate Preference Attributes have been specified. Duplicates are not allowed.";
		lottery.attributes.get(4).label = "Boat Category";
		lottery.attributes.get(4).entryRequired = "Per Application";
		LotteryPreferenceAttribute attr6 = new LotteryPreferenceAttribute();
		attr6.label = "Boat Category";
		attr6.displayOrder = "6";
		lottery.attributes.add(attr6);
		im.gotoAddNewLottery(lottery);
		this.addLotteryProgram(lottery);
		this.verifyErrorMessage(error);
		this.gotoSearchPage();
		
		// Keep one of the Display Orders of Preference Attribute as empty
		error="The Display Order for each Preference Attribute must be specified. Please enter an integer value greater than 0.";
		lottery.attributes.get(5).label = "Dock";
		lottery.attributes.get(5).displayOrder = "";
		im.gotoAddNewLottery(lottery);
		this.addLotteryProgram(lottery);
		this.verifyErrorMessage(error);
		this.gotoSearchPage();
		
		// Enter one of the Display Orders as 0.
		error="The Display Order for each Preference Attribute must be an integer value greater than 0. Please change your entries.";
		lottery.attributes.get(5).displayOrder = "0";
		im.gotoAddNewLottery(lottery);
		this.addLotteryProgram(lottery);
		this.verifyErrorMessage(error);
		this.gotoSearchPage();
		
		// Enter one of the Display Orders as negative interger number like -2.
		lottery.attributes.get(5).displayOrder = "-2";
		im.gotoAddNewLottery(lottery);
		this.addLotteryProgram(lottery);
		this.verifyErrorMessage(error);
		this.gotoSearchPage();
		
		// Enter one of the Display Orders as decimal number like 1.5.
		lottery.attributes.get(5).displayOrder = "1.5";
		im.gotoAddNewLottery(lottery);
		this.addLotteryProgram(lottery);
		this.verifyErrorMessage(error);
		this.gotoSearchPage();
		
		// Enter one of the Display Orders same as another.
		error="Duplicate Display Order values have been specified. Duplicates are not allowed.";
		lottery.attributes.get(5).displayOrder = "5";
		im.gotoAddNewLottery(lottery);
		this.addLotteryProgram(lottery);
		this.verifyErrorMessage(error);
		this.gotoSearchPage();
		
		
		// log out
		im.logoutInvManager();
	}
	
	private void gotoSearchPage(){
		LotteryDetailsPage detailPg = LotteryDetailsPage.getInstance();
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		
		detailPg.clickCancel();
		ajax.waitLoading();
		lotterySearchPg.waitLoading();
	}

	private void verifyErrorMessage(String error) {
		LotteryDetailsPage detailPg = LotteryDetailsPage.getInstance();

		String message = detailPg.getErrorMessage();
		if (!error.equals(message)) {
			throw new ErrorOnPageException("Error Message:", error, message);
		}
	}

	private void addLotteryProgram(Lottery lottery) {
		LotteryDetailsPage detailPg = LotteryDetailsPage.getInstance();

		detailPg.setLotteryName(lottery.name);
		detailPg.setDescription(lottery.description);
		detailPg.selectProCategory(lottery.productCategory);
		detailPg.selectProGroups(lottery.productGroup);
		detailPg.waitLoading();
		detailPg.selectPermitCategories(lottery.permitCategory);
		detailPg.waitLoading();
		detailPg.selectPermitType(lottery.permitTypes);
		detailPg.selectMaxiNum(lottery.maxNumber);
		this.addPreferenceAttributes(lottery.attributes);

		detailPg.clickApply();
		detailPg.waitLoading();
	}
	
	public void addPreferenceAttributes(List<LotteryPreferenceAttribute> attrs) {
		LotteryDetailsPage detailPg = LotteryDetailsPage.getInstance();
		if (attrs == null) {
			attrs = new ArrayList<LotteryPreferenceAttribute>();
		}
		detailPg.mergeWithRecordOnPage(attrs);
		detailPg.setPrefAttrLabelInfo(attrs);
		detailPg.setPreAttrOtherInfo(attrs);
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";

		lottery.name = "lottery for slip "
				+ DataBaseFunctions.getEmailSequence();
		lottery.location = "Jordan Lake State Rec Area";
		lottery.locationCategory = "Park";
		lottery.description = "lottery for slip test";
		lottery.productCategory = "Slip";
		lottery.isCollectCreditCard = true;
		lottery.isRequiresCustomerAcceptance = true;
		LotteryPreferenceAttribute attr1 = new LotteryPreferenceAttribute();
		attr1.label = "Facility";
		attr1.displayOrder = "1";
		LotteryPreferenceAttribute attr2 = new LotteryPreferenceAttribute();
		attr2.label = "Min Slip Depth";
		attr2.displayOrder = "2";
		LotteryPreferenceAttribute attr3 = new LotteryPreferenceAttribute();
		attr3.label = "Min Slip Length";
		attr3.displayOrder = "3";
		LotteryPreferenceAttribute attr4 = new LotteryPreferenceAttribute();
		attr4.label = "Min Slip Width";
		attr4.displayOrder = "4";
		LotteryPreferenceAttribute attr5 = new LotteryPreferenceAttribute();
		attr5.label = "Boat Category";
		attr5.displayOrder = "5";
		lottery.attributes.add(attr1);
		lottery.attributes.add(attr2);
		lottery.attributes.add(attr3);
		lottery.attributes.add(attr4);
		lottery.attributes.add(attr5);
	
		error="A slip lottery requires either the Dock, Slip type or the Slip (Code and Name) to be an applicable attribute for the lottery. Please update your lottery attributes.";
	}

}
