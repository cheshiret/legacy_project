/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleDetialsInfoPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleTitleListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This script used to check set vehicle title transferable from title list page 
 * @Preconditions:
 * @SPEC:<Set Vehicle Title to Transferable>
 * @Task#:Auto-1009
 * 
 * @author ssong
 * @Date  Jul 1, 2012
 */
public class SetToTransferableFromList extends LicenseManagerTestCase{
	private BoatInfo boat = new BoatInfo();
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private LicMgrVehicleTitleDetialsInfoPage detailPg = LicMgrVehicleTitleDetialsInfoPage.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);

		// registration vehicle
		lm.registerVehicleToOrderCart(cust, boat);
		lm.processOrderToOrderSummary(pay);
		boat.registration.miNum = lmOrdSumPg.getVehicleMINum();
		lm.finishOrder();

		// title vehicle
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		lm.titleVehicleToOrderCartFromVehicleDetailPage(boat);
		lm.processOrderToOrderSummary(pay);
		boat.title.titleNum = lmOrdSumPg.getTitleNum();
		String ord_num = lmOrdSumPg.getAllOrdNums().split(" ")[0];
		lm.finishOrder();

		// set vehicle title to transferable
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		String titleId = lm.setTitleToTransferableByPrd(boat.title.product);
		this.verifySetTitleTransferable();
		lm.gotoVehicleTitleDetailPgFromTitleList(titleId);
		if(!detailPg.compareVehTitleStatus(TRANSFERABLE_STATUS)){
			throw new ErrorOnPageException("Set Title Transferable Failed.");
		}
		this.verifyTransactionFromDB(ord_num, TRANTYPE_SET_VEHICLE_TITLE_TRANSFERABLE);
		lm.logOutLicenseManager();
	}

	private void verifySetTitleTransferable(){
		LicMgrVehicleTitleListPage listPg = LicMgrVehicleTitleListPage.getInstance();
		listPg.showAll();
		String actualStatus = listPg.getTitleItemStatusByTitleNum(boat.title.titleNum);
		boolean equals = MiscFunctions.compareResult("Title# '"+boat.title.titleNum+"' Status", TRANSFERABLE_STATUS, actualStatus);
		if(!equals){
			throw new ErrorOnPageException("Set Title Transferable Failed",TRANSFERABLE_STATUS,actualStatus);
		}
	}
	
	private void verifyTransactionFromDB(String ord_num,String tran_type){
		boolean result = true;
		String[] info = lm.getOrderTransInfoByOrderItemAndTransType(schema, ord_num, tran_type);

		result = MiscFunctions.compareResult("Transaction Date ",
				DateFunctions.formatDate(info[2].split(" ")[0], "MM/dd/yyyy"),
				DateFunctions.getToday());
		result &= MiscFunctions.compareResult("Sales Channel ", info[5],
				SALESCHAN_FIELD);
		result &= MiscFunctions.compareResult("Location ", info[3], login.location.split("/")[1]);

		if (!result) {
			throw new ErrorOnPageException(
					"Order transaction info is not correct.Please check log file...");
		}
		
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		/* register vehicle */
		boat.type = "Boat";
		boat.hullIdSerialNum = "Transfer" + DateFunctions.getCurrentTime();
		boat.manufacturerName = "YAMA";
		boat.modelYear = "1997";
		boat.feet = "15";
		boat.inches = "10";
		boat.hullMaterial = "Steel";
		boat.boatUse = "OTHER";
		boat.propulsion = "Sail";
		boat.fuelType = "Gasoline";
		boat.typeOfBoat = "Open";
		
		boat.registration.product = "RV1 - RegisterVehicleBoat";
		boat.title.product = "TV1 - TitleVehicleBoat";
		boat.title.boatValue = "20";
		boat.title.purchaseType = "Original";
		
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic00005";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jun 08 1988";
		cust.lName = "TEST-Basic6";
		cust.fName = "QA-Basic6";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}
}
