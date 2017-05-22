/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.marina;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipReservationInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsNoteAlertDetailPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Mar 24, 2014
 */
public class OrmsSlipReservationNoteAlertDetailPage extends OrmsNoteAlertDetailPage{
	private static OrmsSlipReservationNoteAlertDetailPage _instance = null;
	private OrmsSlipReservationNoteAlertDetailPage(){}
	public static OrmsSlipReservationNoteAlertDetailPage getInstance(){
		if(_instance == null){
			_instance = new OrmsSlipReservationNoteAlertDetailPage();
		}
		return _instance;
	}
	
	public String getSlipReservationNum() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN",
				".id", new RegularExpression(
						"MarinaOrderView-\\d+\\.orderName", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Slip Reservation Num div object.");
		}

		String text = objs[0].text().split("Slip Reservation #")[1].trim();
		Browser.unregister(objs);

		return text;
	}
	
	public String getSlipResStatus() {
		return this.getAttributeByName("Status");
	}

	public String getSlipResOrderStatus() {
		return this.getAttributeByName("Order Status");
	}

	public String getSlipResCreatedDate() {
		return this.getAttributeByName("Created Date");
	}

	public String getSlipResCreatedBy() {
		return this.getAttributeByName("Created By");
	}

	public String getSlipResPrice() {
		return this.getAttributeByName("Price");
	}

	public String getSlipResPaid() {
		return this.getAttributeByName("Paid");
	}

	public String getSlipResUnissuedRefund() {
		return this.getAttributeByName("Unissued Refund");
	}

	public String getSlipResConfirmationStatus() {
		return this.getAttributeByName("Confirmation Status");
	}

	public String getSlipResBalance() {
		return this.getAttributeByName("Balance");
	}

	public String getSlipResCollectionStatus() {
		return this.getAttributeByName("Collection Status");
	}
	
	public SlipReservationInfo getSlipReservationInfo() {
		SlipReservationInfo slipResInfo = new SlipReservationInfo();

		logger.info("Get slip reservation info.");
		slipResInfo.reservNum = getSlipReservationNum();
		slipResInfo.reservStatus = getSlipResStatus();
		slipResInfo.orderStatus = getSlipResOrderStatus();
		slipResInfo.setCreatedDate(getSlipResCreatedDate());
		slipResInfo.setCreatedBy(getSlipResCreatedBy());
		slipResInfo.orderPrice = getSlipResPrice();
		slipResInfo.orderPaid = getSlipResPaid();
		slipResInfo.setUnissuedRefund(getSlipResUnissuedRefund());
		slipResInfo.orderConfStatus = getSlipResConfirmationStatus();
		slipResInfo.orderBalance = getSlipResBalance();
		slipResInfo.setCollectionStatus(getSlipResCollectionStatus());
		return slipResInfo;
	}
	
	public void verifySlipReservationInfo(SlipReservationInfo expSlipResInfo){
		SlipReservationInfo actSlipResInfo = this.getSlipReservationInfo();
		
		logger.info("Verify slip reservation info.");
		boolean result = true;
		result &= MiscFunctions.compareResult("Slip Reservation Num", expSlipResInfo.reservNum, actSlipResInfo.reservNum);
		result &= MiscFunctions.compareResult("Slip Reservation Status", expSlipResInfo.reservStatus, actSlipResInfo.reservStatus);
		result &= MiscFunctions.compareResult("Slip Order Status", expSlipResInfo.orderStatus, actSlipResInfo.orderStatus);
		result &= MiscFunctions.compareResult("Slip Reservation Create Date", expSlipResInfo.getCreatedDate(), actSlipResInfo.getCreatedDate());
		result &= MiscFunctions.compareResult("Slip Reservation Create By", expSlipResInfo.getCreatedBy(), actSlipResInfo.getCreatedBy());
		result &= MiscFunctions.compareResult("Slip Order Price", expSlipResInfo.orderPrice, actSlipResInfo.orderPrice);
		result &= MiscFunctions.compareResult("Slip Reservation Unissued Refund", expSlipResInfo.getUnissuedRefund(),actSlipResInfo.getUnissuedRefund());
		result &= MiscFunctions.compareResult("Slip Reservation Confirmation Satus", expSlipResInfo.orderConfStatus, actSlipResInfo.orderConfStatus);
		result &= MiscFunctions.compareResult("Slip Reservation Balance", expSlipResInfo.orderBalance, actSlipResInfo.orderBalance);
		result &= MiscFunctions.compareResult("Slip Reservation Collection Status", expSlipResInfo.getCollectionStatus(), actSlipResInfo.getCollectionStatus());
		
		if(!result){
			throw new ErrorOnPageException("Slip Reservation info not correct, please check log.");
		}
	}

}
