/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.marina;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo.ReservationHistory;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReservationHistoryPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date Jan 13, 2014
 */
public class OrmsSlipReservationOrderHistoryPage extends
		OrmsReservationHistoryPage {
	private static OrmsSlipReservationOrderHistoryPage _instance = null;

	private OrmsSlipReservationOrderHistoryPage() {
	}

	public static OrmsSlipReservationOrderHistoryPage getInstance() {
		if (_instance == null) {
			_instance = new OrmsSlipReservationOrderHistoryPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text",
				"Slip Reservation Order History");
	}
	
	public ReservationHistory getOrderHistoryByTransType(String transType) {
		int toReturn = 0;
		int controller = 0;
		
		IHtmlObject objs[] =browser.getHtmlObject(".class", "Html.TABLE", ".text",new RegularExpression("^Date/Time Transaction Type.*", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find reservation history table object.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		for(int row = 0; row < table.rowCount(); row++ ){
			if(table.getCellValue(row, 1).equalsIgnoreCase(transType)) {
				controller++;
				toReturn = row;
				break;
			}
		}
		if(controller == 0) {
			throw new ItemNotFoundException("The given transaction type can not be found!");
		}
		
		ReservationHistory info = new ReservationHistory();
		info.dateTime = table.getCellValue(toReturn, table.findColumn(0, "Date/Time"));
		info.transType = table.getCellValue(toReturn, table.findColumn(0, "Transaction Type"));
		info.transOcc = table.getCellValue(toReturn, table.findColumn(0, "Trans. Occ."));
		info.info = table.getCellValue(toReturn, table.findColumn(0, "Information at time of Transaction"));
		info.transLocation = table.getCellValue(toReturn, table.findColumn(0, "Transaction Location"));
		info.user = table.getCellValue(toReturn, table.findColumn(0, "User"));	
		
		Browser.unregister(objs);
		return info;
	}

	public void compareHistoyInfo(ReservationHistory history) {
		ReservationHistory actualValue = this
				.getOrderHistoryByTransType(history.transType);

		boolean result = true;
		result &= MiscFunctions.compareResult("Transaction Type",
				history.transType, actualValue.transType);
		result &= MiscFunctions.compareResult("Trans. Occ.",
				history.transOcc, actualValue.transOcc);
		result &= MiscFunctions.compareResult("Information at time of Transaction",
				history.info, actualValue.info);
		result &= MiscFunctions.compareResult("Transaction Location",
				history.transLocation, actualValue.transLocation);
		result &= MiscFunctions.compareResult("User",
				history.user, actualValue.user);
		
		if (!result) {
			throw new ErrorOnPageException(
					"Not all the value for slip order history is correct, see the log above!");
		}

	}
	
	public void clickReturnToResDetailsButton(){
		browser.clickGuiObject(".class","Html.A",".text","Return to Reservation Details");
	}
}
