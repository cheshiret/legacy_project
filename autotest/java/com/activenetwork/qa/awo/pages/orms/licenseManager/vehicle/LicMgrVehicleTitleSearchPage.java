package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.TitleInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
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
 * @Date  Jun 27, 2012
 */
public class LicMgrVehicleTitleSearchPage extends LicMgrCommonTopMenuPage {
	private static LicMgrVehicleTitleSearchPage _instance = null;
	
	private LicMgrVehicleTitleSearchPage() {}
	
	public static LicMgrVehicleTitleSearchPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrVehicleTitleSearchPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", new RegularExpression("vehilceTitleSearch\\&Details", false));
	}
	
	/**
	 * select title search type
	 * @param type
	 */
	public void selectTitleSearchType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("VehicleRTIRegistrationAndTitleSearchCriteria-\\d+\\.titleSearchTypeId", false), type);
	}

	/**
	 * Set title search value
	 * @param value
	 */
	public void setTitleSearchValue(String value) {
		browser.setTextField(".id", new RegularExpression("VehicleRTIRegistrationAndTitleSearchCriteria-\\d+\\.titleSearchValue", false), value);
	}
	
	/**
	 * select vehicle title status, including 'Active', 'Reserved', 'Surrendered', 'Transferable' and 'Transferred'
	 * @param status
	 */
	public void selectTitleStatus(String status) {
		if(status != null && status.length() > 0) {
			browser.selectDropdownList(".id", new RegularExpression("VehicleRTIRegistrationAndTitleSearchCriteria-\\d+\\.vehicleInstanceStatus", false), status);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("VehicleRTIRegistrationAndTitleSearchCriteria-\\d+\\.vehicleInstanceStatus", false), 0);
		}
	}
	
	public void setProductCode(String code) {
		browser.setTextField(".id", new RegularExpression("VehicleRTIRegistrationAndTitleSearchCriteria-\\d+\\.productCode", false), code);
	}
	
	public void setCreationValidFromDate(String fromDate) {
		browser.setTextField(".id", new RegularExpression("VehicleRTIRegistrationAndTitleSearchCriteria-\\d+\\.fromDate_ForDisplay", false), fromDate,0,IText.Event.LOSEFOCUS);
//		moveFocusOutOfDateComponent();
	}
	
	public void setCreationValidToDate(String toDate) {
		browser.setTextField(".id", new RegularExpression("VehicleRTIRegistrationAndTitleSearchCriteria-\\d+\\.toDate_ForDisplay", false), toDate,0,IText.Event.LOSEFOCUS);
//		moveFocusOutOfDateComponent();
	}
	
	public void setVehicleIDMiNum(String num) {
		browser.setTextField(".id", "vehicleNumber", num);
	}
	
	public void setHullIdSerialNum(String num) {
		browser.setTextField(".id", new RegularExpression("VehicleRTIRegistrationAndTitleSearchCriteria-\\d+\\.hullId", false), num);
	}
	
	/**
	 * select 'Exact Match' check box
	 */
	public void selectExactMatch() {
		browser.selectCheckBox(".id", new RegularExpression("VehicleRTIRegistrationAndTitleSearchCriteria-\\d+\\.exactMatch", false));
	}
	
	/**
	 * unselect 'Exact Match' check box
	 */
	public void unselectExactMatch() {
		browser.unSelectCheckBox(".id", new RegularExpression("VehicleRTIRegistrationAndTitleSearchCriteria-\\d+\\.exactMatch", false));
	}
	
	public void selectVehicleType(String type) {
		if(type != null && type.length() > 0) {
			browser.selectDropdownList(".id", new RegularExpression("VehicleRTIRegistrationAndTitleSearchCriteria-\\d+\\.vehicleTypeView", false), type);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("VehicleRTIRegistrationAndTitleSearchCriteria-\\d+\\.vehicleTypeView", false), 0);
		}
	}
	
	/**
	 * including options - 'Boat Use', 'Inventory #' and 'Type of Boat'
	 * @param type
	 */
	public void selectVehicleSearchType(String type) {
		if(type != null && type.length() > 0) {
			browser.selectDropdownList(".id", new RegularExpression("AttributeComboBox-\\d+\\.selectedAttrDef", false), type);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("AttributeComboBox-\\d+\\.selectedAttrDef", false), 0);
		}
	}
	
	public void selectVehicleSearchValue(String value) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]", false), value);
	}
	
	public void setVehicleSearchValue(String value) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]", false), value);
	}
	
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	/**
	 * search vehicle title 
	 * @param title
	 */
	public void searchTitle(TitleInfo title) {
		if(title.getTitleSearchType() != null && title.getTitleSearchType().length() > 0) {
			selectTitleSearchType(title.getTitleSearchType());
		}
		if(title.getTitleSearchValue() != null && title.getTitleSearchValue().length() > 0) {
			setTitleSearchValue(title.getTitleSearchValue());
		}
		if(title.status.length() > 0) {
			selectTitleStatus(title.status);
		}
		if(title.getProductCode() != null && title.getProductCode().length() > 0) {
			setProductCode(title.getProductCode());
		}
		if(title.getCreationValidFrom() != null && title.getCreationValidFrom().length() > 0) {
			setCreationValidFromDate(title.getCreationValidFrom());
		}
		if(title.getCreationValidTo() != null && title.getCreationValidTo().length() > 0) {
			setCreationValidToDate(title.getCreationValidTo());
		}
		if(title.getVehicleIdMiNum() != null && title.getVehicleIdMiNum().length() > 0) {
			setVehicleIDMiNum(title.getVehicleIdMiNum());
		}
		if(title.getHullIdSerialNum() != null && title.getHullIdSerialNum().length() > 0) {
			setHullIdSerialNum(title.getHullIdSerialNum());
		}
		if(title.isExactMatch()) {
			selectExactMatch();
		} else {
			unselectExactMatch();
		}
		if(title.getVehicleType() != null && title.getVehicleType().length() > 0) {
			selectVehicleType(title.getVehicleType());
		}
		if(title.getVehicleSearchType() != null && title.getVehicleSearchType().length() > 0) {
			selectVehicleSearchType(title.getVehicleSearchType());
			ajax.waitLoading();
		}
		if(title.getVehicleSearchValue() != null && title.getVehicleSearchValue().length() > 0) {
			selectVehicleSearchValue(title.getVehicleSearchValue());
		}
		
		clickSearch();
		ajax.waitLoading();
	}
	
	public void clearSearchCriteria() {
		selectTitleSearchType("Title #");
		setTitleSearchValue("");
		selectTitleStatus(null);
		setProductCode("");
		setCreationValidFromDate("");
		setCreationValidToDate("");
		setVehicleIDMiNum("");
		setHullIdSerialNum("");
		selectVehicleType(null);
		selectVehicleSearchType(null);
	}
	
	/**
	 * 
	 * @param searchType - 'Title #', 'Order #', 'Receipt #' and 'Title ID'
	 * @param searchValue
	 */
	public void searchTitle(String searchType, String searchValue) {
		selectTitleSearchType(searchType);
		setTitleSearchValue(searchValue);
		clickSearch();
		ajax.waitLoading();
	}
	
	public void searchTitleByTitleNum(String num) {
		searchTitle("Title #", num);
	}
	
	public void clickTitleID() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("\\d+", false));
	}
	
	public IHtmlObject[] getTitleListTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "SearchVehicleRegistrationUIGrid_LIST");
		
		if(objs.length < 0) {
			throw new ItemNotFoundException("Can't find Vehicle Title list table object.");
		}
		
		return objs;
	}
	
	/**
	 * check if a specific title record exists in the titles list
	 * @param colName - it is better to use some unique column, like 'Title #', 'Title ID', 'Vehicle #',. etc
	 * @param value
	 * @return
	 */
	public boolean checkTitleExists(String colName, String value) {
		return MiscFunctions.verifyRecordExistInSearchResult("SearchVehicleRegistrationUIGrid_LIST", colName, value);
	}
	
	/**
	 * check if the title records displayed in the list identified by title number
	 * @param tNum
	 * @return
	 */
	public boolean checkTitleExistsIdentifiedByTitleNum(String tNum) {
		return checkTitleExists("Title #", tNum);
	}
	
	public List<String> getTitleID(){
		String titleID = "";
		List<String> titleIDList = new ArrayList<String>();
		IHtmlObject objs[] = this.getTitleListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		for(int i=1; i<table.rowCount(); i++){
			titleID = table.getCellValue(i, 1);
			titleIDList.add(titleID);
		}
		return titleIDList;
	}
}
