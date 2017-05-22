package com.activenetwork.qa.awo.pages.orms.common.marina;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReservationDetailsCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class OrmsListEntryReservationDetailPage extends OrmsReservationDetailsCommonPage{
	private static OrmsListEntryReservationDetailPage _instance = null;
	
	private OrmsListEntryReservationDetailPage(){}
	
	public static OrmsListEntryReservationDetailPage getInstance(){
		if(null == _instance){
			_instance = new OrmsListEntryReservationDetailPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".id", "ListReservationDetal");
	}
	
	private String prefix_Item = "SlipWaitingListItemView-\\d+\\.";
	
	private String prefix_product = "SlipWaitingListProductView-\\d+\\.";
	
	private String prefix_order = "MarinaOrderView-\\d+\\.";
	
	private String prefix_customer = "BillingCustomerInfo-\\d+\\.";
	
	protected Property[] listIdSpan() {  
		return Property.toPropertyArray(".id", new RegularExpression(prefix_Item + "productId",false));
	}
	protected Property[] listNameSpan() {  
		return Property.toPropertyArray(".id", new RegularExpression(prefix_Item + "productName",false));
	}
	protected Property[] listStatusSpan() {  
		return Property.toPropertyArray(".id", new RegularExpression(prefix_product + "status:CB_TO_NAME",false));
	}
	protected Property[] ListEntryTypeSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_Item + "waitingType:CB_TO_NAME",false));
	}
	protected Property[] ListResOrderNumSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_order + "orderName",false));
	}
	protected Property[] ListResOrderStatusSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_order + "status:CB_TO_NAME",false));
	}
	protected Property[] ListResEntryStatusSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_Item + "waitingStatus:CB_TO_NAME",false));
	}
	protected Property[] ListResCreatedDateSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_order + "createDate",false));
	}
	protected Property[] ListResCreatedBySpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_order + "createUser.name",false));
	}
	protected Property[] ListResPriceSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_order + "price:CURRENCY",false));
	}
	protected Property[] ListResPaidSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_order + "paidAmount:CURRENCY",false));
	}
	protected Property[] ListResUnissuedRefundSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_order + "unissuedRefunForDisplay:CURRENCY",false));
	}
	protected Property[] ListResConfirmedStatusSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_order + "confirmationStatus:CB_TO_NAME",false));
	}
	protected Property[] ListResBalanceSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_order + "balance:CURRENCY",false));
	}
	protected Property[] ListContactStatusSpan(){ 
		return Property.toPropertyArray(".id", new RegularExpression(prefix_Item + "contactStatusDesc:CB_TO_NAME",false));
	}
	
	protected Property[] LocationMarinaSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_product + "marinaName",false));
	}
	protected Property[] LocationParticipationSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_product + "participationDisplayString",false));
	}
	
	protected Property[] customerPhoneSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_customer + "preferredPhoneNumber",false));
	}
	protected Property[] customerEmailAddressSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_customer + "email",false));
	}
	protected Property[] customerOrganizationNameSpan(){  
		return Property.toPropertyArray(".id", new RegularExpression(prefix_customer + "organizationName",false));
	}
	protected Property[] customerPhoneContactPreferenceSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_customer + "phoneContactPref:CB_TO_NAME",false));
	}
	protected Property[] customerPreferenceContactTimeSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_customer + "preferedContactTime:CB_TO_NAME",false));
	}
	
	protected Property[] addContactLogButton(){
		return Property.toPropertyArray(".class", "Html.A",".text", "Add Contact Log");
	}
	
	protected Property[] invoiceButton(){
		return Property.toPropertyArray(".class", "Html.A",".text", new RegularExpression("^\\d+$",false));
	}
											
	private String prefix_preferredChoice = "SlipLotteryPreferenceView-\\d+\\.";
	protected Property[] preferredSlipTypeDropDownList(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_preferredChoice + "slipTypeID",false));
	}
	protected Property[] preferredDockDropDownList(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_preferredChoice + "dock",false));
	}
	protected Property[] preferredSlipDropDownList(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_preferredChoice + "slipView",false));
	}
	
	protected Property[] minSlipLengthTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_Item+"minSlipLengthStr",false));
	}
	protected Property[] minSlipWidthTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_Item + "minSlipWidthStr",false));
	}
	protected Property[] minSlipDepthTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_Item + "minSlipDepthStr",false));
	}
	
	protected Property[] validateButton(){
		return Property.toPropertyArray(".class", "Html.A",".text", "Validate");
	}
	
	public void clickValidate(){
		browser.clickGuiObject(this.validateButton());
	}
	
	
	public boolean isInvoiceButtonEnabled(){
		boolean exist =  browser.checkHtmlObjectEnabled(this.invoiceButton());
		return exist;
	}
	
	public List<String[]> getContactLogInfo(){
		List<String[]> contacts = new ArrayList<String[]>();
		IHtmlObject[] trs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Contact Log.*", false));
		if(trs.length < 1){
			throw new ErrorOnPageException("No tr found for 'Contact Log' section!");
		}
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TEXTAREA", trs[0]);
		String[] content = objs[0].text().split("Date:");
		for(int i=1; i<content.length; i++){
			String[] items = new String[4];
			items[0] = content[i].split("User:")[0].trim();
			items[1] = content[i].substring(content[i].indexOf("User:")+"User:".length(), content[i].indexOf("Contact Status:")).trim();
			items[2] = RegularExpression.getMatches(content[i],	"Contact Status: (Pending Response|Rejected|Confirmed|Other)", false)[0].split("Contact Status: ")[1].trim();
			items[3] = content[i].split(items[2])[1].trim();
			contacts.add(items);
		}
		return contacts;
	}
	
	public void clickAddContactLog(){
		browser.clickGuiObject(this.addContactLogButton());
	}
	
	public void setMinSlipLength(String minSlipLength){
		browser.setTextField(minSlipLengthTextField(), minSlipLength);
	}
	
	public void setMinSlipWidth(String minSlipWidth){
		browser.setTextField(minSlipWidthTextField(),  minSlipWidth);
	}
	
	public void setMinSlipDepth(String minSlipDepth){
		browser.setTextField(minSlipDepthTextField(),  minSlipDepth);
	}
	
	public void setPreferredDimensions(String length, String width, String depth, boolean validate){
		if(StringUtil.notEmpty(length)){
			this.setMinSlipLength(length);
		}
		if(StringUtil.notEmpty(width)){
			this.setMinSlipWidth(width);
		}
		if(StringUtil.notEmpty(depth)){
			this.setMinSlipDepth(depth);
		}
		if(validate){
			this.clickValidate();
			ajax.waitLoading();
			this.waitLoading();
		}
	}
	
	public IHtmlObject[] getPreferredChoiceTableObj(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "PreferredWaitingListChoiceFormBar");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found preferred choice table.");
		}
		return objs;
	}
	
	public void selectSlipTypeOfPreferredChoice(String slipType){
		IHtmlObject[] objs = this.getPreferredChoiceTableObj();
		browser.selectDropdownList(preferredSlipTypeDropDownList(), slipType,true,objs[0]);
		Browser.unregister(objs); 
	}
	
	public void selectDockOfPreferredChoice(String dock){
		IHtmlObject[] objs = this.getPreferredChoiceTableObj();
		browser.selectDropdownList(preferredDockDropDownList(), dock,true, objs[0]);
		Browser.unregister(objs); 
	}
	
	public void selectSlipOfPreferredChoice(String slip){
		IHtmlObject[] objs = this.getPreferredChoiceTableObj();
		browser.selectDropdownList(preferredSlipDropDownList(), slip,true, objs[0]);
		Browser.unregister(objs); 
	}
	
	public String getSlipTypeValue(int index){
		return browser.getDropdownListValue(preferredSlipTypeDropDownList(), index);
	}
	public String getDockValue(int index){
		return browser.getDropdownListValue(preferredDockDropDownList(), index);
	}
	public String getSlipValue(int index){
		return browser.getDropdownListValue(preferredSlipDropDownList(), index);
	}
	
	private IHtmlObject getPreferChoiceTopObjByIndex(int index){
		IHtmlObject[] tdTops = browser.getHtmlObject(".class", "Html.TD", ".text", new RegularExpression("^#" + (index+1) + ".*", false));
		if(tdTops.length < 1){
			throw new ErrorOnPageException("Did not found the top object for the " + index + "th preferred choice!");
		}
		return tdTops[0];
	}
	
	public List<String> getSlipTypeElement(int index){
		return browser.getDropdownElements(preferredSlipTypeDropDownList(), getPreferChoiceTopObjByIndex(index));
	}
	public List<String> getDockElement(int index){
		return browser.getDropdownElements(preferredDockDropDownList(), getPreferChoiceTopObjByIndex(index));
	}
	public List<String> getSlipElement(int index){
		return browser.getDropdownElements(preferredSlipDropDownList(), getPreferChoiceTopObjByIndex(index));
	}
	
	public void setListInfo(ListInfo listInfo){
		//to do set preferred dimension
		if(null != listInfo.getPreferredChoice()){
			if(StringUtil.notEmpty(listInfo.getPreferredChoice().getSlipType())){
				this.selectSlipTypeOfPreferredChoice(listInfo.getPreferredChoice().getSlipType());
				ajax.waitLoading();
			}
			if(StringUtil.notEmpty(listInfo.getPreferredChoice().getDockName())){
				this.selectDockOfPreferredChoice(listInfo.getPreferredChoice().getDockName());
				ajax.waitLoading();
			}
			if(StringUtil.notEmpty(listInfo.getPreferredChoice().getSlipName())){
				this.selectSlipOfPreferredChoice(listInfo.getPreferredChoice().getSlipName());
				ajax.waitLoading();
			}
		}
		if(listInfo.getAlternativeChoice().size() > 0){
			this.selectAlternativeChoice(listInfo.getAlternativeChoice());
		}
	}
	
	
	public String getOrderNum() {
		return browser.getObjectText(".id", new RegularExpression("MarinaOrderView-\\d+\\.orderName", false)).replace("Order #", "").trim();
	}
	
	public String getOrderStatus() {
		return browser.getObjectText(".id", new RegularExpression("MarinaOrderView-\\d+\\.status:CB_TO_NAME", false)).replace("Order Status", "").trim();
	}
	
	public String getEntryStatus(){
		String entryStatus = browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("SlipWaitingListItemView-\\d+\\.waitingStatus:CB_TO_NAME",false));
		return entryStatus.replaceAll("Entry Status", "").trim();
	}
	
	public String getParticipation(){
		String participation = browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("SlipWaitingListProductView-\\d+\\.participationDisplayString",false));
		return participation.replaceAll("Participation", "").trim();
	}
	
	public void verifyOrderStatus(String expected) {
		if(!MiscFunctions.compareResult("List Entry Order Status", expected, this.getOrderStatus())) throw new ErrorOnPageException("List Entry(#=" + this.getOrderNum() + ") Order Status is NOT correct.");
	}
	
	public void verifyEntryStatus(String expected) {
		if(!MiscFunctions.compareResult("List Entry Entry Status", expected, this.getEntryStatus())) throw new ErrorOnPageException("List Entry(#=" + this.getOrderNum() + ") is NOT correct.");
	}
	
	private boolean isButtonEnabled(String name) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", name);
	}
	
	public boolean isCancelEnabled() {
		return isButtonEnabled("Cancel");
	}
	
	public boolean isVoidEnabled() {
		return isButtonEnabled("Void");
	}
	
	public void verifyCancelButtonEnabled(boolean enabled) {
		if(!MiscFunctions.compareResult("'Cancel' button shall be " + (enabled ? "enabled" : "disbaled"), enabled, this.isCancelEnabled())) throw new ErrorOnPageException("'Cancel' button is NOT " + (enabled ? "enabled." : "disabled."));
	}
	
	public void verifyVoidButtonEnabled(boolean enabled) {
		if(!MiscFunctions.compareResult("'Void' button shall be " + (enabled ? "enabled" : "disbaled"), enabled, this.isVoidEnabled())) throw new ErrorOnPageException("'Void' button is NOT " + (enabled ? "enabled." : "disabled."));
	}
	
	public String getAttrValue(Property[] properties){
		IHtmlObject[] spans = browser.getHtmlObject(properties);
		if(spans.length < 1){
			throw new ErrorOnPageException("No span found for properties:" + properties.toString());
		}
		IHtmlObject[] subSpans = browser.getHtmlObject(".class", "Html.SPAN", spans[0]);
		String content = subSpans[0].text().trim();
		Browser.unregister(spans);
		Browser.unregister(subSpans);
		return content;
	}
	
	public String getListIdInListDetailsSection(){
		return this.getAttrValue(this.listIdSpan()).replace("List ID", "").trim();
	}
	
	public String getListNameInListDetailsSection(){
		return this.getAttrValue(this.listNameSpan()).replace("Name","").trim();
	}
	
	public String getListStatusInListDetailsSection(){
		return this.getAttrValue(this.listStatusSpan()).replace("List Status","").trim();
	}
	
	public String getEntryTypeInListDetailSection(){
		return this.getAttrValue(this.ListEntryTypeSpan()).replace("Entry Type","").trim();
	}
	
	public String getOrderNumInListReservationSection(){
		return this.getAttrValue(this.ListResOrderNumSpan()).replace("Order #","").trim();
	}
	
	public String getOrderStatusInListReservationSection(){
		return this.getAttrValue(this.ListResOrderStatusSpan()).replace("Order Status","").trim();
	}
	
	public String getEntryStatusInListReservationSection(){
		return this.getAttrValue(this.ListResEntryStatusSpan()).replace("Entry Status","").trim();
	}
	
	public String getCreatedDateInListReservationSection(){
		return this.getAttrValue(this.ListResCreatedDateSpan()).replace("Created Date","").trim();
	}
	
	public String getCreatedByInListReservationSection(){
		return this.getAttrValue(this.ListResCreatedBySpan()).replace("Created By","").trim();
	}
	
	public String getPriceInListReservationSection(){
		return this.getAttrValue(this.ListResPriceSpan()).replace("Price","").trim();
	}
	
	public String getPaidInListReservationSection(){
		return this.getAttrValue(this.ListResPaidSpan()).replace("Paid","").trim();
	}
	
	public String getUnissuedRefundInListReservationSection(){
		return this.getAttrValue(this.ListResUnissuedRefundSpan()).replace("Unissued Refund","").trim();
	}
	
	public String getConfirmaedStatusInListReservationSection(){
		return this.getAttrValue(this.ListResConfirmedStatusSpan()).replace("Confirmed Status","").trim();
	}
	
	public String getBalanceInListReservationSection(){
		return this.getAttrValue(this.ListResBalanceSpan()).replace("Balance","").trim();
	}
	
	public String getContactStatusInListReservationSection(){
		return this.getAttrValue(this.ListContactStatusSpan()).replace("Contact Status","").trim();
	}
	
	public String getMarinaNameInLocationSection(){
		return this.getAttrValue(this.LocationMarinaSpan()).replace("Marina","").trim();
	}
	
	public String getParticipationInLocationSection(){
		return this.getAttrValue(this.LocationParticipationSpan()).replace("Participation","").trim();
	}
	
	public String getPhoneInCustomerSection(){
		return this.getAttrValue(this.customerPhoneSpan()).replace("Phone","").trim();
	}
	
	public String getEmailAddressInCustomerSection(){
		return this.getAttrValue(this.customerEmailAddressSpan()).replace("Email Address","").trim();
	}
	
	public String getOrganizationNameInCustomerSection(){
		return this.getAttrValue(this.customerOrganizationNameSpan()).replace("Organization Name","").trim();
	}
	
	public String getPhoneContactPreInCustomerSection(){
		return this.getAttrValue(this.customerPhoneContactPreferenceSpan()).replace("Phone Contact Preference","").trim();
	}
	
	public String getPreferenceContractTimeInCustomerSection(){
		return this.getAttrValue(this.customerPreferenceContactTimeSpan()).replace("Preference Contact Time","").trim();
	}
	
	
	public IHtmlObject[] getAlternativeChoiceTableObj(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "AlternativeWaitingListChoiceFormBar");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found alternative choice table.");
		}
		return objs;
	}

	public void selectSlipTypeOfAlternativeChoice(String slipType, int index){
		IHtmlObject[] objs = this.getAlternativeChoiceTableObj();
		browser.selectDropdownList(preferredSlipTypeDropDownList(), slipType, true, index, objs[0]);
		Browser.unregister(objs); 
	}
	
	public void selectDockOfAlternativeChoice(String dock, int index){
		IHtmlObject[] objs = this.getAlternativeChoiceTableObj();
		browser.selectDropdownList(preferredDockDropDownList(), dock, true, index, objs[0]);
		Browser.unregister(objs); 
	}
	
	public void selectSlipOfAlternativeChoice(String slip, int index){
		IHtmlObject[] objs = this.getAlternativeChoiceTableObj();
		browser.selectDropdownList(preferredSlipDropDownList(), slip, true, index, objs[0]);
		Browser.unregister(objs); 
	}
	
	public void selectAlternativeChoice(List<ListInfo.SlipChoice> alternativeChoiceList){
		IHtmlObject[] objs = this.getAlternativeChoiceTableObj();
		IHtmlObject[] alterChoiceObj = browser.getHtmlObject(preferredSlipTypeDropDownList(), objs[0]);
		
		// given number of alternative choice must less than (<=) Alternative Choice on UI 
		if(alternativeChoiceList.size() <= alterChoiceObj.length){
			ListInfo.SlipChoice alterChoice = new ListInfo().new SlipChoice();
			
			for(int i=0; i<alternativeChoiceList.size(); i++){
				alterChoice = alternativeChoiceList.get(i);
				
				if(StringUtil.notEmpty(alterChoice.getSlipType())){
					this.selectSlipTypeOfAlternativeChoice(alterChoice.getSlipType(), i);
					ajax.waitLoading();
					this.waitLoading();
				}

				if(StringUtil.notEmpty(alterChoice.getDockName())){
					this.selectDockOfAlternativeChoice(alterChoice.getDockName(), i);
					ajax.waitLoading();
					this.waitLoading();
				}
				
				if(StringUtil.notEmpty(alterChoice.getSlipName())){
					this.selectSlipOfAlternativeChoice(alterChoice.getSlipName(), i);
					ajax.waitLoading();
					this.waitLoading();
				}
			}
		} else {
			throw new ErrorOnDataException("Given number of alternative choice is too many.");
		}
	}
	
	public void selectPerferredChoice(ListInfo.SlipChoice preferredChoice){
		if(StringUtil.notEmpty(preferredChoice.getSlipType())){
			this.selectSlipTypeOfPreferredChoice(preferredChoice.getSlipType());
			ajax.waitLoading();
			this.waitLoading();
		}

		if(StringUtil.notEmpty(preferredChoice.getDockName())){
			this.selectDockOfPreferredChoice(preferredChoice.getDockName());
			ajax.waitLoading();
			this.waitLoading();

		}
		if(StringUtil.notEmpty(preferredChoice.getSlipName())){
			this.selectSlipOfPreferredChoice(preferredChoice.getSlipName());
			ajax.waitLoading();
			this.waitLoading();
		}
	}

	public void setPreferredDimensions(ListInfo.PreferredDimensions preferredDimensions, boolean validate){
		if(StringUtil.notEmpty(preferredDimensions.getMinSlipLength())){
			this.setMinSlipLength(preferredDimensions.getMinSlipLength());
		}
		if(StringUtil.notEmpty(preferredDimensions.getMinSlipWidth())){
			this.setMinSlipWidth(preferredDimensions.getMinSlipWidth());
		}
		if(StringUtil.notEmpty(preferredDimensions.getMinSlipDepth())){
			this.setMinSlipDepth(preferredDimensions.getMinSlipDepth());
		}
		
		if(validate){
			this.clickValidate();
			ajax.waitLoading();
			browser.waitExists();
		}
	}
}
