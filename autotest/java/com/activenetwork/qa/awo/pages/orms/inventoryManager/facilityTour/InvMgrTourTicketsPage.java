/*
 * Created on Nov 4, 2009
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author vzhao you can access this page from tour details or allocation page
 *         by click on Tour Tickets link
 */
public class InvMgrTourTicketsPage extends InventoryManagerPage {
	private static InvMgrTourTicketsPage _instance = null;

	public static InvMgrTourTicketsPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrTourTicketsPage();
		}
		return _instance;
	}

	private RegularExpression indMinimumIndTicketRegx = new RegularExpression("minIndTickets|new_minTicketTypeIndicator_ind\\d+|minTicketTypeIndicator_ind.\\d+", false);
	private RegularExpression indMaxmumIndTicketRegx = new RegularExpression("maxIndTickets|new_maxTicketTypeIndicator_ind\\d+|maxTicketTypeIndicator_ind\\.\\d+", false);
	
	protected InvMgrTourTicketsPage() {
	}

	protected Property[] minIndTicketTypeList(int index) {
		return Property.concatPropertyArray(this.select(), ".id", "new_minTicketTypeAdmType_ind" + index);
	}
	
	protected Property[] maxIndTicketTypeList(int index) {
		return Property.concatPropertyArray(this.select(), ".id", "new_maxTicketTypeAdmType_ind" + index);
	}
	
	protected Property[] minOrgTicketTypeList(int index) {
		return Property.concatPropertyArray(this.select(), ".id", "new_minTicketTypeAdmType_org" + index);
	}
	
	protected Property[] maxOrgTicketTypeList(int index) {
		return Property.concatPropertyArray(this.select(), ".id", "new_maxTicketTypeAdmType_org" + index);
	}

	protected Property[] minIndTicketTypeTextField(int index) {
		return Property.concatPropertyArray(this.input("text"), ".id", "new_minTicketTypeIndicator_ind" + index);
	}

	protected Property[] maxIndTicketTypeTextField(int index) {
		return Property.concatPropertyArray(this.input("text"), ".id", "new_maxTicketTypeIndicator_ind" + index);
	}
	
	protected Property[] minOrgTicketTypeTextField(int index) {
		return Property.concatPropertyArray(this.input("text"), ".id", "new_minTicketTypeIndicator_org" + index);
	}

	protected Property[] maxOrgTicketTypeTextField(int index) {
		return Property.concatPropertyArray(this.input("text"), ".id", "new_maxTicketTypeIndicator_org" + index);
	}

	protected Property[] minIndTicketTypeAdvSaleCheckBox(int index) {
		return Property.concatPropertyArray(this.input("checkbox"), ".id", "new_minTicketTypeAdvanced_ind" + index);
	}

	protected Property[] maxIndTicketTypeAdvSaleCheckBox(int index) {
		return Property.concatPropertyArray(this.input("checkbox"), ".id", "new_maxTicketTypeAdvanced_ind" + index);
	}

	protected Property[] minOrgTicketTypeAdvSaleCheckBox(int index) {
		return Property.concatPropertyArray(this.input("checkbox"), ".id", "new_minTicketTypeAdvanced_org" + index);
	}

	protected Property[] maxOrgTicketTypeAdvSaleCheckBox(int index) {
		return Property.concatPropertyArray(this.input("checkbox"), ".id", "new_maxTicketTypeAdvanced_org" + index);
	}

	public boolean exists() {
		// use OK button as pageMark
		RegularExpression rex = new RegularExpression(
				"\"applyTourTicketChange\"", false);
//		return browser.checkHtmlObjectExists(".text", "OK", ".href", rex);
		return browser.checkHtmlObjectExists(".text", "OK", ".onclick", rex);// Lesley[20131015]: update due to OK button changed in 3.05.00
	}

	/** Click on Tour Details link. */
	public void clickTourDetails() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Tour Details");
	}

	/** Click on Tour Allocation link. */
	public void clickTourAllocation() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Tour Allocation");
	}

	public void clickRemoveTiketType(String type, int index) {
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression(".*new_removeTicketType_"+type+index+".*",false));
	}

	/**
	 * Fill in minimum organization tickets number.
	 *
	 * @param num
	 *            - ticket number
	 */
	public void setMinimumOrgTicket(String num) {//new_minTicketTypeIndicator_org0
		browser.setTextField( ".id",  new RegularExpression("(new_)?minTicketTypeIndicator_org\\d+", false), num);
	}

	/**
	 * Fill in maximum organization tickets number.
	 *
	 * @param num
	 *            - ticket number
	 */
	public void setMaximumOrgTicket(String num) {
		browser.setTextField(".id", new RegularExpression("new_maxTicketTypeIndicator_org\\d+", false), num);
	}

	public boolean isMinimumIndTicketExsting(){
		return browser.checkHtmlObjectExists(".id", indMinimumIndTicketRegx); 
	}
	
	public boolean isMinimumOrgTicketExsting(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("new_minTicketTypeIndicator_org\\d+",false));
	}
	/**
	 * Fill in minimum inventory tickets number.
	 *
	 * @param num
	 *            - ticket number
	 */
	public void setMinimumIndTicket(String num) {
		browser.setTextField(".id", indMinimumIndTicketRegx, num); 
	}

	/**
	 * Retieve the minimum inventory ticket number.
	 *
	 * @return - minimum ticket number
	 */
	public String getMinIndTicket() {
		IHtmlObject[] obj = browser.getHtmlObject(".id", indMinimumIndTicketRegx); 

		String num = obj[0].getProperty(".value").toString();
		Browser.unregister(obj);

		return num;
	}

	public boolean isMaxmumIndTicketExsting(){
	   return browser.checkHtmlObjectExists(".id", indMaxmumIndTicketRegx);
	}
	
	public boolean isMaxmumOrgTicketExsting(){
		   return browser.checkHtmlObjectExists(".id", new RegularExpression("minIndTickets|new_maxTicketTypeIndicator_org\\d+", false));
		}
	
	/**
	 * Fill in maximum inventory tickets number.
	 *
	 * @param num
	 *            - ticket number
	 */
	public void setMaximumIndTicket(String num) {
		browser.setTextField(".id", indMaxmumIndTicketRegx, num); //"maxIndTickets", num);
	}

	/**
	 * Retieve the maximum inventory ticket number.
	 *
	 * @return - maximum ticket number
	 */
	public String getMaxIndTicket() {
		IHtmlObject[] obj = browser.getHtmlObject(".id",  indMaxmumIndTicketRegx); //"maxIndTickets");

		String num = obj[0].getProperty(".value").toString();
		Browser.unregister(obj);

		return num;
	}

	public void clickAddTicketType() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Ticket Type");
	}
	
	public void clickAddTicketType(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Ticket Type",index);
	}
	
	public void clickAddTicketTypeIndMin(){
		this.clickAddTicketType(1);
	}
	
	public void clickAddTicketTypeIndMax(){
		this.clickAddTicketType(2);
	}
	
	public void clickAddTicketTypeOrgMin(){
		this.clickAddTicketType(4);
	}
	
	public void clickAddTicketTypeOrgMax(){
		this.clickAddTicketType(5);
	}

	public void clickAddOrganType() {
		int index;
		if(this.isIndividualTicketCategoryExists()) {
			index = 3;
		}else{
			index = 0;
		}
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Ticket Type",
				index);
	}

	public IHtmlObject[] getTourDetailsTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Ticket Types.*Add Ticket Type.*Minimum Individual Tickets.*Maximum Individual Tickets.*", false));
		if(objs==null || objs.length<1){
			throw new com.activenetwork.qa.testapi.ObjectNotFoundException("Tour Details table can't be found.");
		}
		return objs;
	}
	
	/**
	 * 
	 * @param sectionName: Ticket Types, Minimum Individual Tickets, Maximum Individual Tickets
	 */
	public int getAddTicketTypeIndex(String sectionName){
		IHtmlObject[] tourDetailsSectionObjs = null; 
		IHtmlObject[] tourDetailsAddTicketTypeObjs = null; 
		int addTicketTypeIndex = -1;

		IHtmlObject[] objs = this.getTourDetailsTable();

		IHtmlTable table = (IHtmlTable) objs[0];
		for(int i=0; i<table.rowCount(); i++){
			tourDetailsAddTicketTypeObjs = browser.getHtmlObject(".class", "Html.TR", ".text", "Add Ticket Type");
			if(tourDetailsAddTicketTypeObjs!=null && tourDetailsAddTicketTypeObjs.length>0){
				addTicketTypeIndex ++;
			}

			tourDetailsSectionObjs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression(sectionName+".*", false));
			if(tourDetailsSectionObjs!=null && tourDetailsSectionObjs.length>0){
				addTicketTypeIndex++;
				break;
			}
		}
		return addTicketTypeIndex;
	}

	public void clickAddTicketType(String sectionName){
		int index = this.getAddTicketTypeIndex(sectionName);
		logger.info("Click 'Add Ticket Type' button with index:"+index+" in section:"+sectionName);
		
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Ticket Type", index);
	}
	
	public void clickAssignedToursLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Assigned Tours");
	}

	public void clickTourTicketsLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Tour Tickets");
	}

	public void selectTicketType_Ind(String type, int index) {
		browser.selectDropdownList(".id", "new_ticketType_ind" + index, type);
	}

	public void selectTicketType_Org(String type, int index) {
		browser.selectDropdownList(".id", "new_ticketType_org" + index, type);
	}


	public void selectTicketType(String type) {
		browser.selectDropdownList(".id", "new_ticketType_ind0", type);
	}

	public void selectActiveCheckBox(String ind_org, int index) {
		browser.selectCheckBox(".id", "new_ticketTypePassRequired_" + ind_org
				+ index);
	}

	public void unCheckActive(String ind_org, int index) {
		browser.unSelectCheckBox(".id", "new_ticketTypePassRequired_" + ind_org
				+ index);
	}

	public void selectFieldVisibleCheckBox(String ind_org, int index) {
		browser.selectCheckBox(".id", "new_ticketTypeFieldVisible_" + ind_org
				+ index);
	}

	public void unCheckFieldVisible(String ind_org, int index) {
		browser.unSelectCheckBox(".id", "new_ticketTypeFieldVisible_" + ind_org
				+ index);
	}

	public void selectCallCenterVisibleCheckBox(String ind_org, int index) {
		browser.selectCheckBox(".id", "new_ticketTypeCallCenterVisible_"
				+ ind_org + index);
	}

	public void unCheckCallCenter(String ind_org, int index) {
		browser.unSelectCheckBox(".id", "new_ticketTypeCallCenterVisible_"
				+ ind_org + index);
	}

	public void selectWebVisibleCheckBox(String ind_org, int index) {
		browser.selectCheckBox(".id", "new_ticketTypeWebVisible_" + ind_org
				+ index);
	}

	public void unCheckWebVisible(String ind_org, int index) {
		browser.unSelectCheckBox(".id", "new_ticketTypeWebVisible_" + ind_org
				+ index);
	}

	public void selectIDRequiredCheckBox(String ind_org, int index) {
		browser.selectCheckBox(".id", "new_ticketTypePassRequired_" + ind_org
				+ index);
	}

	public void unCheckIDRequired(String ind_org, int index) {
		browser.unSelectCheckBox(".id", "new_ticketTypePassRequired_" + ind_org
				+ index);
	}

	public void selectOrganType(String type, int index) {
		browser.selectDropdownList(".id", "new_ticketType_org" + index, type);
	}

	public void selectOrganType(String type) {
		browser.selectDropdownList(".id", "new_ticketType_org0", type);
	}

	/** Click on OK link. */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/** Click on Cancel link. */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/** Click on Apply link. */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	/** Update the max & min reservation ticket number for tour. */
	public void updateTicketNum(InventoryInfo inv) {
		if (inv.minIndNum.length() > 0)
			this.setMinimumIndTicket(inv.minIndNum);
		
		if (inv.maxIndNum.length() > 0)
			this.setMaximumIndTicket(inv.maxIndNum);
		
		if (inv.minOrgNum.length() > 0)
			this.setMinimumOrgTicket(inv.minOrgNum);
		
		if (inv.maxOrgNum.length() > 0)
			this.setMaximumOrgTicket(inv.maxOrgNum);
	}

	public boolean checkTicketType(String type) {
		boolean res = false;
		if (browser.getDropdownListValue(".id", "existing_ticketType_ind0", 0)
				.toString().equals(type)) {
			res = true;
		}

		return res;
	}

	public boolean checkOrganType(String type) {
		boolean res = false;
		if (browser.getDropdownListValue(".id", "existing_ticketType_org0", 0)
				.toString().equals(type)) {
			res = true;
		}

		return res;
	}

	public List<String> getErrorMessage() {
		List<String> list = new ArrayList<String>();
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.DIV",".id",
				new RegularExpression("(V-\\d+)|NOTSET", false));
		if (objs.length > 0) {
			for (int i = 0; i < objs.length; i++) {
				list.add(objs[i].getProperty(".text"));
			}
		}
		return list;
	}
	
	public boolean checkErrorMessageExisting(){
		return browser.checkHtmlObjectExists(".class","Html.DIV",".id",
				new RegularExpression("V-\\d+", false));
	}

	public boolean isIndividualTicketCategoryExists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^Individual Ticket Category", false));
	}

	private IHtmlObject getIndividualTicketCategoryTR() {
		IHtmlObject table = browser.getTableTestObject(".text", new RegularExpression("^Individual Ticket Category.*", false))[0];
		IHtmlObject tbody = table.getChildren()[0];
		return (IHtmlObject) tbody.getChildren()[2];
	}

	private IHtmlObject getOrganizationTicketCategoryTR() {
		IHtmlObject table = browser.getTableTestObject(".text", new RegularExpression("^Individual Ticket Category.*", false))[0];
		IHtmlObject tbody = table.getChildren()[0];
		return (IHtmlObject) tbody.getChildren()[5];
	}

	public void selectIndividualActiveCheckbox(String ticketType) {
		TicketTypeIndex index = indexOfIndividualTicketType(ticketType.trim());

		if (index.defaultTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("ticketTypeActive\\.\\d+", false), index.defaultTypeIndex, getIndividualTicketCategoryTR());

		} else if (index.newTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("new_ticketTypeActive_ind\\d+", false), index.newTypeIndex);

		}
	}

	public void selectIndividualFieldVisibleCheckbox(String ticketType) {
		TicketTypeIndex index = indexOfIndividualTicketType(ticketType.trim());

		if (index.defaultTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("ticketTypeFieldVisible\\.\\d+", false), index.defaultTypeIndex, getIndividualTicketCategoryTR());

		} else if (index.newTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("new_ticketTypeFieldVisible_ind\\d+", false), index.newTypeIndex);

		}
	}

	public void selectIndividualCallCenterVisibleCheckbox(String ticketType) {
		TicketTypeIndex index = indexOfIndividualTicketType(ticketType.trim());

		if (index.defaultTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("ticketTypeCallCenterVisible\\.\\d+", false), index.defaultTypeIndex, getIndividualTicketCategoryTR());

		} else if (index.newTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("new_ticketTypeCallCenterVisible_ind\\d+", false), index.newTypeIndex);

		}
	}

	public void selectIndividualWebVisibleCheckbox(String ticketType) {
		TicketTypeIndex index = indexOfIndividualTicketType(ticketType.trim());

		if (index.defaultTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("ticketTypeWebVisible\\.\\d+", false), index.defaultTypeIndex, getIndividualTicketCategoryTR());

		} else if (index.newTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("new_ticketTypeWebVisible_ind\\d+", false), index.newTypeIndex);

		}
	}

	public void selectIndividualIDRequiredVisibleCheckbox(String ticketType) {
		TicketTypeIndex index = indexOfIndividualTicketType(ticketType.trim());

		if (index.defaultTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("ticketTypePassRequired\\.\\d+", false), index.defaultTypeIndex, getIndividualTicketCategoryTR());

		} else if (index.newTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("new_ticketTypePassRequired_ind\\d+", false), index.newTypeIndex);

		}
	}

	public void selectOrganizationActiveCheckbox(String ticketType) {
		TicketTypeIndex index = indexOfOrganizationTicketType(ticketType.trim());

		if (index.defaultTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("ticketTypePassRequired\\.\\d+", false), index.defaultTypeIndex, getOrganizationTicketCategoryTR());

		} else if (index.newTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("new_ticketTypePassRequired_org\\d+", false), index.newTypeIndex);

		}
	}

	public void selectOrganizationFieldVisibleCheckbox(String ticketType) {
		TicketTypeIndex index = indexOfOrganizationTicketType(ticketType.trim());

		if (index.defaultTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("ticketTypePassRequired\\.\\d+", false), index.defaultTypeIndex, getOrganizationTicketCategoryTR());

		} else if (index.newTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("new_ticketTypePassRequired_org\\d+", false), index.newTypeIndex);

		}
	}

	public void selectOrganizationCallCenterVisibleCheckbox(String ticketType) {
		TicketTypeIndex index = indexOfOrganizationTicketType(ticketType.trim());

		if (index.defaultTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("ticketTypePassRequired\\.\\d+", false), index.defaultTypeIndex, getOrganizationTicketCategoryTR());

		} else if (index.newTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("new_ticketTypePassRequired_org\\d+", false), index.newTypeIndex);

		}
	}

	public void selectOrganizationWebVisibleCheckbox(String ticketType) {
		TicketTypeIndex index = indexOfOrganizationTicketType(ticketType.trim());

		if (index.defaultTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("ticketTypePassRequired\\.\\d+", false), index.defaultTypeIndex, getOrganizationTicketCategoryTR());

		} else if (index.newTypeIndex != null) {

			browser.selectCheckBox(".id", new RegularExpression("new_ticketTypePassRequired_org\\d+", false), index.newTypeIndex);

		}
	}

	public boolean isIndividualTicketTypeExists(String type) {
		return indexOfIndividualTicketType(type.trim()).exist();
	}

	private class TicketTypeIndex {
		private Integer defaultTypeIndex;
		private Integer newTypeIndex;

		private boolean exist() {
			return defaultTypeIndex != null || newTypeIndex != null;
		}
	}

	private TicketTypeIndex indexOfIndividualTicketType(String ticketType) {
		IHtmlObject[] existingTypes = getDefaultIndividualTicketTypeDropdowns();
		Integer index = indexOfDropdownBySelectedText(existingTypes, ticketType);

		TicketTypeIndex typeIndex = new TicketTypeIndex();

		if (index == null) {
			IHtmlObject[] newTypes = getNewIndividualTicketTypeDropdowns();
			index = indexOfDropdownBySelectedText(newTypes, ticketType);

			typeIndex.newTypeIndex = index;

		} else {
			typeIndex.defaultTypeIndex = index;
		}

		return typeIndex;
	}

	private TicketTypeIndex indexOfOrganizationTicketType(String ticketType) {
		IHtmlObject[] existingTypes = getDefaultOrganizationTicketTypeDropdowns();
		Integer index = indexOfDropdownBySelectedText(existingTypes, ticketType);

		TicketTypeIndex typeIndex = new TicketTypeIndex();

		if (index == null) {
			IHtmlObject[] newTypes = getNewOrganizationTicketTypeDropdowns();
			index = indexOfDropdownBySelectedText(newTypes, ticketType);

			typeIndex.newTypeIndex = index;

		} else {
			typeIndex.defaultTypeIndex = index;
		}

		return typeIndex;
	}

	private Integer indexOfDropdownBySelectedText(IHtmlObject[] dropdowns, String text) {
		if (dropdowns != null && dropdowns.length > 0) {
			for (int i = 0; i < dropdowns.length; i++) {
				if (text.equals(((ISelect)dropdowns[i]).getSelectedText())) {
					return i;
				}
			}
		}

		return null;
	}


	private IHtmlObject[] getDefaultIndividualTicketTypeDropdowns() {
		return browser.getDropdownList(".id", new RegularExpression("existing_ticketType_ind\\d+", false));
	}

	private IHtmlObject[] getNewIndividualTicketTypeDropdowns() {
		return browser.getDropdownList(".id", new RegularExpression("new_ticketType_ind\\d+", false));
	}

	public boolean isOrganizationTicketCategoryExists() {
		return browser.checkHtmlObjectExists(".class", "Html.TR", ".text", new RegularExpression("^Organization Ticket Category", false));
	}

	public boolean isOrganizationTicketTypeExists(String type) {
		return indexOfOrganizationTicketType(type.trim()).exist();
	}

	private IHtmlObject[] getDefaultOrganizationTicketTypeDropdowns() {
		return browser.getDropdownList(".id", new RegularExpression("existing_ticketType_org\\d+", false));
	}

	private IHtmlObject[] getNewOrganizationTicketTypeDropdowns() {
		return browser.getDropdownList(".id", new RegularExpression("new_ticketType_org\\d+", false));
	}

	public boolean isAssignedToursLinkExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Assigned Tours");
	}

	public void addIndividualTicketType(String type) {
		this.clickAddTicketType();
		this.waitLoading();

		IHtmlObject[] newTypes = getNewIndividualTicketTypeDropdowns();

		((ISelect)newTypes[newTypes.length -1]).select(type.trim());

	}

	public void addOrganizationTicketType(String type) {
		this.clickAddOrganType();
		this.waitLoading();

		IHtmlObject[] newTypes = getNewOrganizationTicketTypeDropdowns();

		((ISelect)newTypes[newTypes.length -1]).select(type.trim());

	}
	
	public void selectIDRequiredCheckBox(String category, String ticketType, boolean selected) {
		IHtmlObject[] topObjs=browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^"+category+" Ticket Category", false));
		if(topObjs.length<1)
			throw new ErrorOnDataException("Could not get "+category+" ticket catergory on page.");
//		IHtmlObject[] trs=browser.getHtmlObject(".text", new RegularExpression("^"+ticketType+".*", false), topObjs[0]);
//		if(trs.length<1)
//			throw new ErrorOnDataException("Could not get ticket type "+ticketType+" under "+category+" category.");
//		if(selected)
//			browser.selectCheckBox(Property.toPropertyArray("id", new RegularExpression("ticketTypePassRequired\\.\\d+", false)), 0, trs[0]);
//		else
//			browser.unSelectCheckBox("id", new RegularExpression("ticketTypePassRequired\\.\\d+", false), 0, trs[0]);
//		Browser.unregister(trs);
		//Jane[2014-6-24]Updated for 3.06.01
		IHtmlObject[] tds = browser.getDropdownList(".id", new RegularExpression("existing_ticketType_ind\\d+", false));
		if(tds.length<1)
			throw new ErrorOnDataException("Could not get any ticket type on page.");
		boolean found = false;
		for(int i=0;i<tds.length;i++) {
			String content = browser.getDropdownListValue(Property.toPropertyArray("id", new RegularExpression("existing_ticketType_ind\\d+", false)), i);
			if(content.equalsIgnoreCase(ticketType)) {
				found = true;
				if(selected)
					browser.selectCheckBox(Property.toPropertyArray("id", new RegularExpression("ticketTypePassRequired\\.\\d+", false)), i);
				else
					browser.unSelectCheckBox("id", new RegularExpression("ticketTypePassRequired\\.\\d+", false), i);
				break;
			}
		}
		Browser.unregister(tds);
		Browser.unregister(topObjs);
		if(!found)
			throw new ErrorOnDataException("Could not found ticket type:"+ticketType+" on page.");
	}
	
	/** 
	 * Below methods are to setup Minimum/Maximum Individual/Organization Ticket Type Info
	 * */
	public void selectMinIndTicketType(String ticketType, int index) {
		browser.selectDropdownList(this.minIndTicketTypeList(index), ticketType);
	}
	
	public void selectMaxIndTicketType(String ticketType, int index) {
		browser.selectDropdownList(this.maxIndTicketTypeList(index), ticketType);
	}

	public void selectMinOrgTicketType(String ticketType, int index) {
		browser.selectDropdownList(this.minOrgTicketTypeList(index), ticketType);
	}
	
	public void selectMaxOrgTicketType(String ticketType, int index) {
		browser.selectDropdownList(this.maxOrgTicketTypeList(index), ticketType);
	}
	
	public void setMinIndTicketNum(String num, int index) {
		browser.setTextField(this.minIndTicketTypeTextField(index), num);
	}

	public void setMaxIndTicketNum(String num, int index) {
		browser.setTextField(this.maxIndTicketTypeTextField(index), num);
	}

	public void setMinOrgTicketNum(String num, int index) {
		browser.setTextField(this.minOrgTicketTypeTextField(index), num);
	}

	public void setMaxOrgTicketNum(String num, int index) {
		browser.setTextField(this.maxOrgTicketTypeTextField(index), num);
	}
	
	public void checkMinIndTicketAdvSale(int index) {
		browser.selectCheckBox(this.minIndTicketTypeAdvSaleCheckBox(index));
	}
	
	public void checkMaxIndTicketAdvSale(int index) {
		browser.selectCheckBox(this.maxIndTicketTypeAdvSaleCheckBox(index));
	}
	
	public void checkMinOrgTicketAdvSale(int index) {
		browser.selectCheckBox(this.minOrgTicketTypeAdvSaleCheckBox(index));
	}
	
	public void checkMaxOrgTicketAdvSale(int index) {
		browser.selectCheckBox(this.maxOrgTicketTypeAdvSaleCheckBox(index));
	}
	
	public void setMinIndTicket(String ticketType, String num, boolean isApplyToAdv, int index) {
		this.selectMinIndTicketType(ticketType, index);
		this.setMinIndTicketNum(num, index);
		if (isApplyToAdv) {
			this.checkMinIndTicketAdvSale(index);
		}
	}
	
	public void setMaxIndTicket(String ticketType, String num, boolean isApplyToAdv, int index) {
		this.selectMaxIndTicketType(ticketType, index);
		this.setMaxIndTicketNum(num, index);
		if (isApplyToAdv) {
			this.checkMaxIndTicketAdvSale(index);
		}
	}
	
	public void setMinOrgTicket(String ticketType, String num, boolean isApplyToAdv, int index) {
		this.selectMinOrgTicketType(ticketType, index);
		this.setMinOrgTicketNum(num, index);
		if (isApplyToAdv) {
			this.checkMinOrgTicketAdvSale(index);
		}
	}
	
	public void setMaxOrgTicket(String ticketType, String num, boolean isApplyToAdv, int index) {
		this.selectMaxOrgTicketType(ticketType, index);
		this.setMaxOrgTicketNum(num, index);
		if (isApplyToAdv) {
			this.checkMaxOrgTicketAdvSale(index);
		}
	}
}
