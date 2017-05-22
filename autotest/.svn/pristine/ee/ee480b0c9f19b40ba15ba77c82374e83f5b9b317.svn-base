package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.manualhuntselection;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.LotteryParameterInfo;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.HFPrivilegeProperty;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFABLotteryApplicationTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) In Hunt selection page, verify UI messages, lottery attributes, hunts choice
 * @Preconditions:Privilege lottery setup: Min choice for lottery=2; Max choice for lottery=5 (Setup in case:ErrorMsgValidation)
 * @LinkSetUp:
 * D_WEB_HF_SIGNUPACCOUNT:id=1590
 * D_HF_ADD_PRIVILEGE_PRD:id=3170  -ABT ABLotteryAppPriv 
 * D_HF_ADD_PRICING:id=4612,4622
 * D_HF_ASSI_PRI_TO_STORE:id=2390
 * D_HF_ADD_QTY_CONTROL:id=2370
 * D_HF_ADD_PRVI_LICENSE_YEAR:id=3300
 * D_HF_ADD_HUNT:id=1110,1120,1130,1140,1150,1160,1170
 * -ABH, AB Walleye Hunt, Individual;Group,DisplayOrder(3)
 * -bAB, bAB Walleye Hunt, Individual;Group, DisplayOrder(1)
 * -3AB, 3 Walleye Hunt, Individual;Group, DisplayOrder(empty) 
 * -IAB, Individual Walleye Hunt, Individual, DisplayOrder(empty) 
 * -GAB, Group Walleye Hunt, Group, DisplayOrder(empty) 
 * -cAB, cAB Walleye Hunt, Individual, DisplayOrder(empty)
 * -dAB, dAB Walleye Hunt, Individual, DisplayOrder(empty)
 * D_HF_ADD_HUNT_QUOTA:id=340
 * D_HF_ADD_HUNT_LICENSE_YEAR:id=1190,1200,1210,1220,1230,1240,1250
 * D_HF_ASSIGN_PRIV_TO_HUNT:id=830,840,850,860,870,880,890
 * D_HF_ADD_LOTTERY_PRD:id=560
 * D_HF_ADD_LOTTERY_LICENSE_YEAR:id=440
 * D_HF_ASSIGN_LOTTERY_TO_STORE:id=430
 * D_HF_ADD_LOTTERY_QUANTITY_CONT:id=440
 * D_HF_ASSI_HUNTS_TO_LOTTERY:id=630,640,650,660,670.680
 * D_HF_ADD_LOTTERY_PARAMETER:id=20,30
 * @SPEC:
 * Setup select hunts page for Manual Hunt selection [TC:121555]
 * Manual Hunt selection - UI [TC:121556]
 * Manually Select Hunts page - Hunt Selection Display - Hunt Code/Hunt Description configuration test [TC:121737]
 * Manual Hunt selection - Dropdown and dropdown list options display [TC:121557]
 * @Task#:AUTO-2129
 * 
 * @author SWang
 * @Date  Jun 4, 2014
 */
public class UIValidation extends HFABLotteryApplicationTestCase{
	private WebConfiguration config = WebConfiguration.getInstance();
	private String pageHeader, subHeader_Indiv, subHeader_Group, instruction, manualHuntOptional;
	private List<String> huntChoices_individual = new ArrayList<String>();
	private List<String> huntChoices_group = new ArrayList<String>();
	private List<String> huntChoicesTitle = new ArrayList<String>();

	@Override
	public void execute() {
		hfab.signIn(url, cus.email, cus.password);

		// Headers for individual type
		hfab.gotoAddHuntsPgAsIndividualFromHeaderBar(cus, lottery.getLegalName());
		verifyUIMsg(subHeader_Indiv);
		verifyLotteryAttributes();
		verifyHuntsChoice(huntChoices_individual);

		// Headers for group leader type
		hfab.gotoLotteryDetailsPgFromAddHuntsPg();
		hfab.applyLotteryAsGroupLeaderToAddHuntsPg();
		verifyUIMsg(subHeader_Group);
		verifyLotteryAttributes();
		verifyHuntsChoice(huntChoices_group);

		hfab.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.custNum = hfab.getCustomerNumByEmail("hfab_00011@webhftest.com", schema);
		cus.email = cus.custNum;
		cus.residencyStatus = RESID_STATUS_ALBERTA;

		lottery.setDescription("AB Walleye Lottery");
		lottery.setLegalName("AB Walleye Lottery Legal");
		lottery.setLicenseYear(hfab.getFiscalYear(schema));
		lottery.setMaxChoices("5");
		lottery.setParameters("AB Walleye Lottery - Para 1", "AB Walleye Lottery - Para 1 value", false);

		this.initialPageHeaderAndText();

		huntChoices_individual.add("-- Please Select --");
		huntChoices_individual.add("bAB bAB Walleye Hunt");
		huntChoices_individual.add("ABH AB Walleye Hunt");
		huntChoices_individual.add("3AB 3 Walleye Hunt");
		huntChoices_individual.add("IAB Individual Walleye Hunt");
		huntChoices_individual.add("cAB cAB Walleye Hunt");
		huntChoices_individual.add("dAB dAB Walleye Hunt");

		huntChoices_group.add("-- Please Select --");
		huntChoices_group.add("bAB bAB Walleye Hunt");
		huntChoices_group.add("ABH AB Walleye Hunt");
		huntChoices_group.add("3AB 3 Walleye Hunt");
		huntChoices_group.add("GAB Group Walleye Hunt");
	}

	/**
	 * From web server, the path is: /opt/sites/torqa3/uwppl/docs/properties/config/hf/HFPrivilegeResources.properties
	 */
	private void initialPageHeaderAndText() {
		pageHeader = config.getPropertiesValue(Conf.HFPrivilege_prop, HFPrivilegeProperty.HFAB_Head);
		subHeader_Indiv = config.getPropertiesValue(Conf.HFPrivilege_prop, HFPrivilegeProperty.HFAB_SubHead_Individual);
		subHeader_Group = config.getPropertiesValue(Conf.HFPrivilege_prop, HFPrivilegeProperty.HF_SubHead_Group_Leader);
		instruction = config.getPropertiesValue(Conf.HFPrivilege_prop, HFPrivilegeProperty.HFAB_Instruction).trim();
		manualHuntOptional = config.getPropertiesValue(Conf.HFPrivilege_prop, HFPrivilegeProperty.HFAB_manualHunt_optional).trim();
	}

	/**
	 * UI check: header, sub header, instruction, hunt optional title
	 * @param subHeader
	 * @param huntChoices
	 */
	private void verifyUIMsg(String subHeader) {
		boolean result = true;
		result &= MiscFunctions.compareResult("Page Header", pageHeader, huntsPg.getPageHeader());
		result &= MiscFunctions.compareResult("Page Sub Header", subHeader, huntsPg.getPageSubHeader());
		result &= MiscFunctions.compareResult("Page Instruction", instruction, huntsPg.getInstruction());
		if(StringUtil.isEmpty(manualHuntOptional)){
			result &= MiscFunctions.compareResult("Manual Hunt Optional", false, huntsPg.isManualHuntOptionalExist());
		}else {
			result &= MiscFunctions.compareResult("Manual Hunt Optional", manualHuntOptional, huntsPg.getmanualHuntOptional());
		}

		if (!result) {
			throw new ErrorOnPageException("Failed to verify all UI message. Please check the details from previous logs.");
		}
		logger.info("Successfully verify all UI message.");
	}

	/**
	 *Lottery attributes: legal name, parameter, license year, maximum choices
	 */
	private void verifyLotteryAttributes() {
		boolean result = true;
		String actualInfo = huntsPg.getLotteryAttributes();
		result &= MiscFunctions.startWithString("Lottery legal name", actualInfo, lottery.getLegalName());
		result &= MiscFunctions.containString("Lottery license year", actualInfo, "Licence Year: " + lottery.getLicenseYear());
		result &= MiscFunctions.containString("Lottery maximum choices", actualInfo, "Maximum Choices: " + lottery.getMaxChoices());
		Data<LotteryParameterInfo> param = lottery.getParameters().get(0);
		String expParam = param.stringValue(LotteryParameterInfo.Description) + ": " + param.stringValue(LotteryParameterInfo.Value);
		result &= MiscFunctions.containString("Lottery parameter", actualInfo, expParam);

		if (!result) {
			throw new ErrorOnPageException("Failed to verify all lottery attributes, Please check the details from previous logs.");
		}
		logger.info("Successfully verify all lottery attributes.");
	}

	/**
	 *Verify hunt choice (title, default value, drop down list options) for individual and group leader
	 * @param huntChoices
	 */
	private void verifyHuntsChoice(List<String> huntChoices){
		boolean result = true;
		int lotteryMaximumChoicesSize = Integer.valueOf(lottery.getMaxChoices());
		int lotteryHuntsSize = huntChoices.size()-1;
		int lotteryHuntsDDLSize = huntsPg.getNumOfHuntChoiceDDL();
		if(lotteryHuntsSize<lotteryMaximumChoicesSize){
			result &= MiscFunctions.compareResult("Hunt choices drop down list size", lotteryHuntsSize, lotteryHuntsDDLSize);
		}else {
			result &= MiscFunctions.compareResult("Hunt choices drop down list size", lotteryMaximumChoicesSize, lotteryHuntsDDLSize);
			lotteryHuntsDDLSize = lotteryMaximumChoicesSize;
		}

		huntChoicesTitle.clear();
		for(int i=0; i<lotteryHuntsDDLSize; i++){
			if(i==0){
				huntChoicesTitle.add(i+1+"st Choice:");
			}else if(i==1){
				huntChoicesTitle.add(i+1+"nd Choice:");
			}else if(i==2){
				huntChoicesTitle.add(i+1+"rd Choice:");
			}else {
				huntChoicesTitle.add(i+1+"th Choice:");
			}
			
			result &= MiscFunctions.compareResult(i+" - Hunt choice DDL default value", huntChoices.get(0), huntsPg.getHuntChoice(i));
			result &= MiscFunctions.compareResult(i+" - Hunt choice DDL options", huntChoices.toString(), huntsPg.getHuntChoices(i).toString());
		}
		result &= MiscFunctions.compareResult("Hunt choice title", huntChoicesTitle.toString(), huntsPg.getHuntChoicesTitle().toString());

		if (!result){
			throw new ErrorOnPageException("Failed to verify all hunt choices. Please check the details from previous logs.");
		}
		logger.info("Successfully verify all hunt choices info.");
	}
}

