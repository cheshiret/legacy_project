package com.activenetwork.qa.awo.supportscripts.support.inventorymgr;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceAttributesInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeInformation;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance.InvMgrEntranceListPage;
import com.activenetwork.qa.awo.supportscripts.AbstractSingleDatapoolSupportCase;
import com.activenetwork.qa.awo.util.DatapoolUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @author fliu
 * @Date  March 14, 2012
 */
// TODO: Move the logMsg to super class
public class AddEntrance extends AbstractSingleDatapoolSupportCase {
	private InvMgrEntranceListPage entranceListPg = InvMgrEntranceListPage.getInstance();
	private EntranceInfo entranceInfo = new EntranceInfo();
	private EntranceAttributesInfo entranceAttribInfo = new EntranceAttributesInfo();
	private List<PermitTypeInformation> permitTypeInfoList = new ArrayList<PermitTypeInformation>();
	private String successMsg1 = "Entrance created successfully.";
	private String successMsg2 = "Entrance information updated successfully.";
	private String successMsg3;
	private String facilityName;
	private String previousContract;
	private boolean loggedIn;
	private InventoryManager im;
	private InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
	private InvMgrHomePage homePage = InvMgrHomePage.getInstance();
	private static AutomationLogger logger = AutomationLogger.getInstance();
	
	@Override
	protected void initRange() {
		startpoint = 5;
		endpoint = 5;
	}

	@Override
	protected void readDataPool(IDatapoolIterator dpIter) {
		facilityName = dpIter.dpString("facilityName");
		entranceInfo = DatapoolUtil.fill(EntranceInfo.class, dpIter);
		entranceAttribInfo = DatapoolUtil.fill(EntranceAttributesInfo.class, dpIter);
		MultiplePermitType multiplePermitTypes= DatapoolUtil.fill(MultiplePermitType.class, dpIter);
		permitTypeInfoList = multiplePermitTypes.getPermitTypes();
		successMsg3 = "Entrances: " + entranceInfo.entranceCode + " were activated successfully!";
	}

	@Override
	public void execute(Orms orms) {
		try {
			LoginInfo loginInfo = orms.loginInfo;
			
			if (!loginInfo.contract.equals(previousContract) && loggedIn) {
				im.logoutInvManager();
				loggedIn = false;
			} 
			
			if (!loggedIn) { // Logged in
				im = orms.loginInventoryManager();
				previousContract = loginInfo.contract;
				loggedIn = true;
			}
			
			if(!homePage.exists()){
				topMenu.clickHome();
				homePage.waitLoading();
			}
			
			im.gotoFacilityDetailsPg(facilityName);
			im.gotoEntranceListPage("Entrance Set-up");
			
			// add entrance on Entrance Detail Page and setup the entrance info and attribute info.
			im.addNewEntrance(entranceInfo, entranceAttribInfo, null, false);
			this.verifyAddSuccessMsg(successMsg1);
			
			// setup the permit type info and deal with the set itself as exit.
			this.initialSearchInfo();
			im.addEntranceAttributeAndPermitTypes(entranceInfo, entranceAttribInfo, permitTypeInfoList);
			this.verifyAddSuccessMsg(successMsg2);
			
			// search and active
			im.changeEntranceStatus(entranceInfo, "active");
			this.verifyAddSuccessMsg(successMsg3);			

		} catch (Exception e) {
			setResult("Create the new quota type failed. Error -- " + e.getMessage());
			
			logger.error(e);
			loggedIn = false;
			
			throw new RuntimeException(e);
		}
	}

	/**
	 * Setup the search scenario for new created entrance.
	 */
	private void initialSearchInfo() {
		entranceInfo.searchBy = "Entrance Code";
		entranceInfo.searchValue = entranceInfo.entranceCode;
		entranceInfo.status = "Inactive";		
	}

	/**
	 * Verify whether adding new entrance successful or not.
	 */
	private void verifyAddSuccessMsg(String successMsg) {
		logger.info("Verify whether adding new entrance successful or not.");

		// get successful message
		String message = entranceListPg.getNotesInfo();
		if(!successMsg.equals(message)) {
			logger.error("The expect message is: "  + successMsg + "but the message is: " + message);
			throw new ErrorOnPageException("The expect message is: "  + successMsg + "but the message is: " + message);
		}
		else{
			setResult("Success");
			logger.info("Adding new entrance successfully.");
		}
	}
	
	public static class MultiplePermitType extends PermitTypeInformation {
		
		public List<PermitTypeInformation> getPermitTypes() {
			List<PermitTypeInformation> permitTypes = new ArrayList<PermitTypeInformation>();
			
			String[] permitTypeArray = StringUtil.splitBySemicolon(permitType);
			String[] maxStayLengthArray= StringUtil.splitBySemicolon(maxStayLength);
			String[] maxGroupSizeArray= StringUtil.splitBySemicolon(maxGroupSize);
			String[] maxWatercraftArray= StringUtil.splitBySemicolon(maxWatercraft);
			String[] maxGroupMemArray= StringUtil.splitBySemicolon(maxGroupMem);
			String[] maxPeteArray= StringUtil.splitBySemicolon(maxPets);
			String[] maxStockArray= StringUtil.splitBySemicolon(maxStock);
			String[] stockRatioArray= StringUtil.splitBySemicolon(stockRatio);
			String[] multiSelectEntrancesArray= StringUtil.splitBySemicolon(multiSelectEntrances);
			String[] issueStartDateTypeArray= StringUtil.splitBySemicolon(issueStartDateType);
			String[] issueEndDateTypeArray= StringUtil.splitBySemicolon(issueEndDateType);
			String[] startDateTimeArray = StringUtil.splitBySemicolon(startDateTime);
			String[] startAmPmArray= StringUtil.splitBySemicolon(startAmPm);
			String[] endDateTimeArray= StringUtil.splitBySemicolon(endDateTime);
			String[] endAmPmArray= StringUtil.splitBySemicolon(endAmPm);
			String[] updateResWindowArray= StringUtil.splitBySemicolon(updateResWindow);
			String[] statusArray= StringUtil.splitBySemicolon(status);
			
			for (int i = 0 ; i < permitTypeArray.length; i++) {
				PermitTypeInformation permitType = new PermitTypeInformation();
				permitType.permitType = arrayIndexVal(permitTypeArray, i);
				permitType.maxStayLength =arrayIndexVal(maxStayLengthArray, i);
				permitType.maxGroupSize =arrayIndexVal(maxGroupSizeArray, i);
				permitType.maxWatercraft =arrayIndexVal(maxWatercraftArray, i);
				permitType.maxGroupMem =arrayIndexVal(maxGroupMemArray, i);
				permitType.maxPets =arrayIndexVal(maxPeteArray, i);
				permitType.maxStock =arrayIndexVal(maxStockArray, i);
				permitType.stockRatio =arrayIndexVal(stockRatioArray, i);
				permitType.multiSelectEntrances =arrayIndexVal(multiSelectEntrancesArray, i);
				permitType.issueStartDateType =arrayIndexVal(issueStartDateTypeArray, i);
				permitType.issueEndDateType =arrayIndexVal(issueEndDateTypeArray, i);
				permitType.startDateTime =arrayIndexVal(startDateTimeArray , i);
				permitType.startAmPm =arrayIndexVal(startAmPmArray, i);
				permitType.endDateTime =arrayIndexVal(endDateTimeArray, i);
				permitType.endAmPm =arrayIndexVal(endAmPmArray, i);
				permitType.updateResWindow =arrayIndexVal(updateResWindowArray, i);
				permitType.status =arrayIndexVal(statusArray, i);
				
				permitTypes.add(permitType);
			}
			
			return permitTypes;
		}
		
		private String arrayIndexVal(String[] array, int index) {
			if (array.length < index + 1) {
				return null;
			}
			String val = array[index].trim();
			if (val.isEmpty()) {
				return null;
			}
			return val;
		}
	}
}
