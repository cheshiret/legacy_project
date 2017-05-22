package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderSearchCommonPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date Oct 27, 2011
 */
public class LicMgrVehicleOrderSearchPage extends LicMgrOrderSearchCommonPage {

	private static LicMgrVehicleOrderSearchPage _instance = null;

	protected LicMgrVehicleOrderSearchPage() {
	}

	public static LicMgrVehicleOrderSearchPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrVehicleOrderSearchPage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".text", "Search", ".href",
				new RegularExpression("vehicleOrder$", false));
	}

	/**
	 * Select vehicle product group
	 * 
	 * @param proGroup
	 */
	public void selectProductGroup(String proGroup) {
		browser.selectDropdownList(".id", new RegularExpression(
				"VehicleOrderSearchCriteria-\\d+\\.productGroup", false),
				proGroup);
	}

	public void selectProductGroup() {
		browser.selectDropdownList(".id", new RegularExpression(
				"VehicleOrderSearchCriteria-\\d+\\.productGroup", false), 0);
	}

	public void selectPurchaseType(String type) {
		browser.selectDropdownList(".id", new RegularExpression(
				"VehicleOrderSearchCriteria-\\d+\\.purchaseType", false), type);
	}

	public void selectPurchaseType() {
		browser.selectDropdownList(".id", new RegularExpression(
				"VehicleOrderSearchCriteria-\\d+\\.purchaseType", false), 0);
	}

	/**
	 * Set vehicle number - Vehicle ID/MI #
	 * 
	 * @param vehicleNum
	 */
	public void setVehicleNum(String vehicleNum) {
		browser.setTextField(".id", new RegularExpression(
				"VehicleOrderSearchCriteria-\\d+\\.vehicleNum", false),
				vehicleNum);
	}

	public void setOperatorFirstName(String name) {
		browser.setTextField(".id", new RegularExpression(
				"VehicleOrderSearchCriteria-\\d+\\.firstName", false), name);
	}

	public void setOperatorLastName(String name) {
		browser.setTextField(".id", new RegularExpression(
				"VehicleOrderSearchCriteria-\\d+\\.lastName", false), name);
	}

	/**
	 * Set Hull ID - Hull ID/Serial #
	 * 
	 * @param hullID
	 */
	public void setHullID(String hullID) {
		browser.setTextField(".id", new RegularExpression(
				"VehicleOrderSearchCriteria-\\d+\\.hullID", false), hullID);
	}

	/**
	 * Select vehicle type value
	 * 
	 * @param vType
	 */
	public void selectVehicleType(String vehicleType) {
		browser.selectDropdownList(".id", new RegularExpression(
				"VehicleOrderSearchCriteria-\\d+\\.vehicleType", false),
				vehicleType);
	}

	public void selectVehicleType() {
		browser.selectDropdownList(".id", new RegularExpression(
				"VehicleOrderSearchCriteria-\\d+\\.vehicleType", false), 0);
	}

	/**
	 * Select vehicle search type
	 * 
	 * @param searchType
	 */
	public void selectVehicleSearchType(String searchType) {
		browser.selectDropdownList(".id", new RegularExpression(
				"AttributeComboBox-\\d+\\.selectedAttrDef", false), searchType);
	}

	public void selectVehicleSearchType() {
		browser.selectDropdownList(".id", new RegularExpression(
				"AttributeComboBox-\\d+\\.selectedAttrDef", false), 0);
	}

	/**
	 * Set/select vehicle search type value
	 * 
	 * @param value
	 */
	public void setVehicleSearchTypeValue(String value) {
		IHtmlObject objs1[] = browser.getTextField(".id", new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]", false));
		IHtmlObject objs2[] = browser.getDropdownList(".id",
				new RegularExpression(
						"AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]", false));

		if (objs1.length > 0) {
			((IText) objs1[0]).setText(value);
		} else if (objs2.length > 0) {
			((ISelect) objs2[0]).select(value);
		} else {
			throw new ErrorOnPageException(
					"Can't find Vehicle Search Type value object.");
		}

		Browser.unregister(objs1);
		Browser.unregister(objs2);
	}

	@Override
	public void setupOrderSearchCriteria(Object object) {
		if (!(object instanceof OrderInfo)) {
			throw new ErrorOnDataException(
					"It should be vehicle order object...");
		}

		OrderInfo vehicle = (OrderInfo) object;

		if (null != vehicle.searchType) {
			this.selectOrderSearchType(vehicle.searchType);
			if (vehicle.searchValue == null) {
				throw new ErrorOnPageException("Please set search value.");
			} else {
				this.setOrderSearchValue(vehicle.searchValue);
			}
		}

		if (null != vehicle.orderFromDate) {
			this.setOrdersFromDate(vehicle.orderFromDate);
			this.clickBlankPg();
		}
		if (null != vehicle.orderToDate) {
			this.setOrdersToDate(vehicle.orderToDate);
			this.clickBlankPg();
		}
		if (null != vehicle.saleLocation) {
			this.setOrdersSalesLocation(vehicle.saleLocation);
		}
		if (!"".equals(vehicle.productGrp)) {
			this.selectProductGroup(vehicle.productGrp);
		} else {
			this.selectProductGroup();
		}
		if (!"".equals(vehicle.purchaseType)) {
			this.selectPurchaseType(vehicle.purchaseType);
		} else {
			this.selectPurchaseType();
		}
		if (null != vehicle.productCode) {
			this.setOrdersProductCode(vehicle.productCode);
		}
		if (null != vehicle.miNum) {
			this.setVehicleNum(vehicle.miNum);
		}
		if (null != vehicle.hullIdSerialNum) {
			this.setHullID(vehicle.hullIdSerialNum);
		}
		if (!"".equals(vehicle.vehicleType)) {
			this.selectVehicleType(vehicle.vehicleType);
		} else {
			this.selectVehicleType();
		}
		if (!"".equals(vehicle.vehicleSearchType)) {
			this.selectVehicleSearchType(vehicle.vehicleSearchType);
			ajax.waitLoading();
			this.waitLoading();
		} else {
			this.selectVehicleSearchType();
			ajax.waitLoading();
			this.waitLoading();
		}
		if (null != vehicle.vehicleSearchValue) {
			this.setVehicleSearchTypeValue(vehicle.vehicleSearchValue);
		}
		if (!"".equals(vehicle.operatorFirstName)) {
			this.setOperatorFirstName(vehicle.operatorFirstName);
		}
		if (!"".equals(vehicle.operatorLastName)) {
			this.setOperatorLastName(vehicle.operatorLastName);
		}
	}

	public void verifySearchResultsForCol(String val, String col) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"privilegeOrderList_LIST");
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		int colNum = tableGrid.findColumn(0, col);

		List<String> values = tableGrid.getColumnValues(colNum);
		values.remove(0);

		for (String value : values) {
			if (!value.contains(val)) {
				throw new ErrorOnPageException("Search vehicle order fail!",
						val, value);
			}
		}
		Browser.unregister(objs);
	}

	public void verifyOrderInSearchList(String id) {
		boolean foundSchd = false;
		IHtmlObject[] objs = null;
		IHtmlTable tableGrid = null;
		List<String> colList = new ArrayList<String>();
		
		/*if(id.contains("-")) {
			id = id.replace("-", "");
		}*/
		do {
			objs = browser.getTableTestObject(".id",
					"privilegeOrderList_LIST");
			tableGrid = (IHtmlTable) objs[0];
//			for(int i =0;i<tableGrid.getColumnValues(0).size();i++){
//				String  value= tableGrid.getColumnValues(0).get(i).replace("-", "");
//				colList.add(value);
//			}
			colList = tableGrid.getColumnValues(0);
			if (colList.contains(id)) {
				logger.info("Vehicle order :" + id
						+ " is in the search result!");
				foundSchd = true;
				break;
			}
		} while (gotoNext());

		Browser.unregister(objs);
		if (!foundSchd) {
			throw new ItemNotFoundException("Search vehicle order fail!");
		}

	}

	/** If "Next" button is available, click next button */
	public boolean gotoNext() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TABLE",
				".className", "pagingBar");
		boolean exists = browser.checkHtmlObjectExists(".class", "Html.A",
				".text", "Next", objs[0]);
		if (exists) {
			this.clickNext();
			ajax.waitLoading();
			waitLoading();
		}

		return exists;
	}

	public void clickNext() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Next");
	}
}
