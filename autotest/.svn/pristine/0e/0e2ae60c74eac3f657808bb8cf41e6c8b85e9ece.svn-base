package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.huntchoiceselection;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.HFCustomerProperty;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.HFPrivilegeProperty;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryHuntsSelectionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify the headers and text on Add Hunts page
 * @Preconditions:
 * 1. Make sure the lottery product exists and has been assigned to web.
 * 2. Make sure at least one hunt assigned to the lottery.
 * @SPEC: 
 * Add Hunts page - Headers & Text - Individual (points / no points) [TC:056214]
 * Add Hunts page - Headers & Text - Group leader [TC:056218]
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1280
 * d_hf_add_privilege_prd:id=2630
 * d_hf_add_pricing:id=3822
 * d_hf_assi_pri_to_store:id=1850
 * d_hf_add_prvi_license_year:id=2740
 * d_hf_add_qty_control:id=1830
 * d_hf_add_hunt:id=480
 * d_hf_add_hunt_license_year:id=510
 * d_hf_assign_priv_to_hunt:id=360
 * d_hf_add_hunt_quota:id=210
 * d_hf_add_lottery_prd:id=200
 * d_hf_add_lottery_license_year:id=80
 * d_hf_assign_lottery_to_store:id=80
 * d_hf_add_lottery_quantity_control:id=80
 * d_hf_assi_hunts_to_lottery:id=90
 * @Task#: Auto-1773
 * 
 * @author Lesley Wang
 * @Date  Feb 12, 2014
 */
public class HeadersAndText extends HFSKWebTestCase {
	private HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
	private WebConfiguration config = WebConfiguration.getInstance();
	private String pageHeader, subHeader_Indiv, subHeader_Group, 
		instruction_Points, instruction, pointNote;
	
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);
		hf.gotoLotteryCategoriesListPg(cus);
		
		// Headers for individual type and no points
		hf.gotoLotteryDetailsPgFromCatListPg(lottery.getDescription());
		hf.applyLotteryAsIndividualToAddHuntsPg();
		this.verifyHeadersAndText(pageHeader, subHeader_Indiv, instruction, StringUtil.EMPTY, "Individual type");
		hf.gotoLotteryDetailsPgFromAddHuntsPg();
		
		// Headers for group leader type and no points
		hf.applyLotteryAsGroupLeaderToAddHuntsPg();
		this.verifyHeadersAndText(pageHeader, subHeader_Group, instruction, StringUtil.EMPTY, "Group leader type");
		hf.gotoLotteryDetailsPgFromAddHuntsPg();
			
		// Headers for individual type and with points
		hf.submitLotteryWithPointsAsIndividualToAddHuntsPg();
		this.verifyHeadersAndText(pageHeader, subHeader_Indiv, instruction_Points, pointNote, "purchase points");
				
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
		
		this.initialPageHeaderAndText();
	}

	private void initialPageHeaderAndText() {
		// All info are configurable in /opt/sites/qa3/uwppl/docs/properties/config/hf/HFPrivilegeResources.properties
		pageHeader = config.getPropertiesValue(Conf.HFPrivilege_prop, HFPrivilegeProperty.HFSK_Head);//this.getHFPrivilegeProperty("lottery.hunts.page.header");
		subHeader_Indiv = config.getPropertiesValue(Conf.HFPrivilege_prop, HFPrivilegeProperty.HFSK_SubHead_Individual);//this.getHFPrivilegeProperty("lottery.hunts.page.sub.header.individual");
		subHeader_Group = config.getPropertiesValue(Conf.HFPrivilege_prop, HFPrivilegeProperty.HFSK_SubHead_Group_Leader);//this.getHFPrivilegeProperty("lottery.hunts.page.sub.header.group.leader");
		instruction_Points = config.getPropertiesValue(Conf.HFPrivilege_prop, HFPrivilegeProperty.HFSK_Instruction_Points);//this.getHFPrivilegeProperty("lottery.hunts.page.instrction.points");
		instruction = config.getPropertiesValue(Conf.HFPrivilege_prop, HFPrivilegeProperty.HFSK_Instruction).trim();//this.getHFPrivilegeProperty("lottery.hunts.page.instrction");
		pointNote = "Note:"+config.getPropertiesValue(Conf.HFPrivilege_prop, HFPrivilegeProperty.HFSK_Note_Points);//this.getHFPrivilegeProperty("lottery.hunts.note.points");
	}
	
//	private String getHFPrivilegeProperty(String key) {
//		String value = config.getPropertiesValue(Conf.HFPrivilege_prop, HFPrivilegeProperty.HFSK_Head);//config.getHFPrivilegeProperty(key + "_HFSK");
//		if (value==null) {
//			value = config.getHFPrivilegeProperty(key);
//		}
//		return value.trim();
//	}
	
	private void verifyHeadersAndText(String pageHeader, String subHeader, String instruction, String pointNote, String msg) {
		boolean result = true;
		result &= MiscFunctions.compareString("Page Header", pageHeader, huntsPg.getPageHeader());
		result &= MiscFunctions.compareString("Page Sub Header", subHeader, huntsPg.getPageSubHeader());
		result &= MiscFunctions.compareString("Page Instruction", instruction, huntsPg.getInstruction());
		if (StringUtil.isEmpty(pointNote)) {
			result &= MiscFunctions.compareResult("Page point note exist", false, huntsPg.checkPointNoteExist());
		} else {
			result &= MiscFunctions.compareString("Page Point Note", pointNote, huntsPg.getPointNote());
		}
		if (!result) {
			throw new ErrorOnPageException("Add Hunts page header and text are wrong when select " + msg);
		}
		logger.info("Successfully verify Add Hunts page header and text when select " + msg);
	}
}
