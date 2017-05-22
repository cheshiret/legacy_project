/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderTransactionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleDetialsInfoPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date Jul 8, 2012
 */
public class ReactiveVehicleTitle_FromTransferableDetail extends
		LicenseManagerTestCase {
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage
			.getInstance();
	private LicMgrVehicleTitleDetialsInfoPage detailPg = LicMgrVehicleTitleDetialsInfoPage
			.getInstance();
	private MotorInfo motor = new MotorInfo();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// title a motor
		lm.titleMotorToOrderCart(cust, motor);
		lm.processOrderToOrderSummary(pay);
		motor.title.titleNum = lmOrdSumPg.getTitleNum();
		String orderNum = lmOrdSumPg.getAllOrdNums().split(" ")[0];
		lm.finishOrder();

		lm.gotoVehicleTitleDetailPage(motor.title.titleNum);
		detailPg.setToTransferable();

		// reactive title
		detailPg.reactiveTitle();
		// verify vehicle title status in the vehicle detail page
		this.verifyStatusForVehicle(ACTIVE_STATUS);
		// verify vehicle title instance has new record for reactive title
		OrderTransactionInfo transinfo = this.setTransactionInfo(orderNum);
		this.verifyTransactionInfoInDB(transinfo, schema, orderNum,
				TRANTYPE_REACTIVATE_VEHICLE_TITLE);
		this.verifyVehicleInstInfoInDB(motor, schema, orderNum);

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		/* title vehicle */
		motor.setSerialNum("Transfer" + DateFunctions.getCurrentTime());
		motor.setManufacturerName("YAMA");
		motor.setModelYear("1997");
		motor.setHorsePower("200");
		motor.setMotorFuel("Gasoline");
		
		motor.title.setMotorValue("1000");
		motor.title.product = "TV3 - TitleVehicleMotor";
		motor.title.purchaseType = "Original";

		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000022";
		cust.dateOfBirth = "Jun 12 1988";
		cust.lName = "TEST-Basic22";
		cust.fName = "QA-Basic22";
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";

	}

	private void verifyStatusForVehicle(String status) {
		motor.title.titleId = detailPg.getTitleId();
		if (!detailPg.compareVehTitleStatus(status)) {
			throw new ErrorOnPageException("Status is not correct,please check log...");
		}
	}

	private OrderTransactionInfo setTransactionInfo(String ordernum) {

		OrderTransactionInfo transinfo = new OrderTransactionInfo();

		transinfo.setOrderID(ordernum);
		transinfo.setTransactionType(TRANTYPE_REACTIVATE_VEHICLE_TITLE);
		transinfo.setTransactionDate(DateFunctions.getToday());
		transinfo.setSalesChannel(SALESCHAN_FIELD);
		transinfo.setLocation("WAL-MART");

		return transinfo;
	}

	private void verifyVehicleInstInfoInDB(MotorInfo v, String schema,
			String ordnum) {
		boolean result = true;
		String[] info = lm.getOrderVehicleInstInfo(schema, ordnum);

		result &= MiscFunctions.compareResult("product name", v.title.product
				.split("-")[1].trim(), info[7]);
		result &= MiscFunctions.compareResult("Title Number", v.title.titleNum,
				info[1]);
		result &= MiscFunctions.compareResult("Title ID", v.title.titleId,
				info[0]);
		result &= MiscFunctions.compareResult("Status", String.valueOf(ACTIVE),
				info[6]);

		if (!result) {
			throw new ErrorOnPageException(
					"Vehicle Instance info is not correct.Please check log file...");
		}
	}

	private void verifyTransactionInfoInDB(OrderTransactionInfo transinfo,
			String schema, String ordnum, String transID) {
		boolean result = true;
		String[] info = lm.getOrderTransInfoByOrderItemAndTransType(schema,
				ordnum, transID);

		result &= MiscFunctions.compareResult("Order Number ", info[0],
				transinfo.getOrderID());
		result &= MiscFunctions.compareResult("Transaction Type ", info[9],
				transinfo.getTransactionType());
		result &= MiscFunctions.compareResult("Transaction Date ",
				DateFunctions.formatDate(info[2].split(" ")[0], "MM/dd/yyyy"),
				transinfo.getTransactionDate());
		result &= MiscFunctions.compareResult("Sales Channel ", info[5],
				transinfo.getSalesChannel());
		result &= MiscFunctions.compareResult("Location ", info[3], transinfo
				.getLocation());

		if (!result) {
			throw new ErrorOnPageException(
					"Order transaction info is not correct.Please check log file...");
		}
	}

}
