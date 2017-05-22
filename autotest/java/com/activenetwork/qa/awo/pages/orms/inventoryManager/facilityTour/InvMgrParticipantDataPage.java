package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour;



import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jun 5, 2012
 */
public class InvMgrParticipantDataPage extends InventoryManagerPage {
	private static InvMgrParticipantDataPage _instance = null;

	public static InvMgrParticipantDataPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrParticipantDataPage();
		}
		return _instance;
	}

	protected InvMgrParticipantDataPage() {
	}

	protected Property[] tpaTable(){
		return Property.concatPropertyArray(table(), ".id", new RegularExpression("dyable_\\d+", false));
	}
	
	protected Property[] individualParticipantDataSpan(){
		return Property.concatPropertyArray(span(), ".className", "label_section", ".text", "Individual Participant Data");
	}
	
	protected Property[] organizationParticipantDataSpan(){
		return Property.concatPropertyArray(span(), ".className", "label_section", ".text", "Organization Participant Data");
	}
	
	protected Property[] okButton(){
		return Property.concatPropertyArray(div(), ".className", new RegularExpression("link (standard|ok)", false), ".text", "OK");
	}
	
	protected Property[] selectedValue(){
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue",false));
	}
	
	public boolean exists() {
		// use OK button as pageMark
		return browser.checkHtmlObjectExists(okButton()) && (browser.checkHtmlObjectExists(individualParticipantDataSpan()) || browser.checkHtmlObjectExists(organizationParticipantDataSpan()));
	}

	/** Click on Tour Details link. */
	public void clickTourDetails() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Tour Details");
	}

	/** Click on Tour Tickets link. */
	public void clickTourTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Tour Tickets");
	}

	/** Click on Tour Allocation link. */
	public void clickTourAllocation() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Tour Allocation");
	}

	/** Click on OK link. */
	public void clickOK() {
		Property[] p = Property.concatPropertyArray(div(), ".className", "link standard", ".text", "OK");
		browser.searchObjectWaitExists(p, SLEEP);
		browser.clickGuiObject(p);
	}

	/** Click on Cancel link. */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/** Click on Apply link. */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	public IHtmlObject[] getAddAttributeButtonObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression(" ?Add Attribute",false));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Add Attribute button objects can't be found.");
		}
		return objs;
	}

	public void clickAddAttributeBtnForIndividual() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(" ?Add Attribute",false),0);
	}
	
	public void clickAddAttributeBtnForOrganization() {
		IHtmlObject[] objs = this.getAddAttributeButtonObjs();
		if(objs.length>1){
			objs[1].click();
		}else{
			objs[0].click();
		}
//		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(" ?Add Attribute",false),1);
	}
	
	private String[] getAttrTableIds(){
		IHtmlObject[] objs=browser.getTableTestObject(tpaTable());
	    if(objs.length < 1) {
	       throw new ObjectNotFoundException("There should be 2 tables");
	    }
	    String[] ids = null;
	    ids = objs.length == 1? new String[]{objs[0].getProperty(".id")} : new String[]{objs[0].getProperty(".id"),objs[1].getProperty(".id")};
	    Browser.unregister(objs);
	    return ids;
	}
	
	private IHtmlObject[] getAttrRow(int objIndex,boolean isIndividual){
		String[] attrTableIds = this.getAttrTableIds();
		String id = "";
		
		if(!isIndividual && attrTableIds.length<=1){
			id=getAttrTableIds()[isIndividual?0:0].replaceAll("dyable_", "")+"_"+objIndex;
		}else{
			id=getAttrTableIds()[isIndividual?0:1].replaceAll("dyable_", "")+"_"+objIndex;
		}
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.TR", ".id", id);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find the TR object by id:"+id);
		}
		return objs;
	}

	
	public void selectAttribute(String attrVal, int rowIndex,boolean isIndividual) {
		IHtmlObject[] topcell=getAttrRow(rowIndex,isIndividual);
		browser.selectDropdownList(Property.toPropertyArray(".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue",false)),attrVal,true, topcell[0]);
		Browser.unregister(topcell);
	}
	
	public void setGroup(String val, int rowIndex,boolean isIndividual) {
		IHtmlObject[] topcell=getAttrRow(rowIndex,isIndividual);
		browser.setTextField(".id", new RegularExpression("TourParticipantAttribute-\\d+\\.group",false),val, topcell[0]);
		Browser.unregister(topcell);
	}
	
	public void setLabel(String val, int rowIndex,boolean isIndividual) {
		IHtmlObject[] topcell=getAttrRow(rowIndex,isIndividual);
		browser.setTextField(".id", new RegularExpression("TourParticipantAttribute-\\d+\\.label",false),val, topcell[0]);
		Browser.unregister(topcell);
	}
	
	public void selectMandatory(String val, int rowIndex,boolean isIndividual) {
		IHtmlObject[] topcell=getAttrRow(rowIndex,isIndividual);
		browser.selectDropdownList(Property.toPropertyArray(".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue",false)), val, false, 1, topcell[0]);
		Browser.unregister(topcell);
	}
	
	public void selectCollectionMethod(String val, int rowIndex,boolean isIndividual) {
		IHtmlObject[] topcell=getAttrRow(rowIndex,isIndividual);
		browser.selectDropdownList(Property.toPropertyArray(".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue",false)), val, true, 2, topcell[0]);
		Browser.unregister(topcell);
	}
	
	public void setSortOrder(String val, int rowIndex,boolean isIndividual) {
		IHtmlObject[] topcell=getAttrRow(rowIndex,isIndividual);
		browser.setTextField(".id", new RegularExpression("TourParticipantAttribute-\\d+\\.sequence",false),val, topcell[0]);
		Browser.unregister(topcell);
	}
	
	public void waitForParticipantTable(){
		browser.searchObjectWaitExists(".class", "Html.TABLE", ".id", new RegularExpression("dyable_\\d+", false));
	}
	
	/**
	 * get 
	 * @param isIndividual
	 */
//	public IHtmlObject[] getParticiPentTable(){
//		waitForParticipantTable();
//		IHtmlObject[] objs  = browser.getTableTestObject(".class", "Html.TABLE", ".id", new RegularExpression("dyable_\\d+", false));
////		if(objs.length<1){
////			throw new ErrorOnPageException("No tabel exist");
////		}
////		Browser.unregister(objs);
//		return objs;
//		//ITable table = (ITable)(isIndividual?objs[0]:objs[1]);
//	}
	
	public boolean isIndividualParticipantDataSpanExist(){
		return browser.checkHtmlObjectExists(individualParticipantDataSpan());
	}
	
	public boolean isOrganizationParticipantDataSpanExist(){
		return browser.checkHtmlObjectExists(organizationParticipantDataSpan());
	}
	
	public IHtmlObject getParticiPentTableObj(boolean isIndividual){
		IHtmlObject[] objs  = browser.getTableTestObject(tpaTable());
		//Individual and Organization participant data tables both exist
		if(isIndividualParticipantDataSpanExist() && !isIndividual){
			if(objs.length<2){
				throw new ObjectNotFoundException("Failed to find Organization participant data table.");
			}else return objs[1];
			//Only one of table exists
		}else {
			if(objs.length<1){
				throw new ObjectNotFoundException("Failed to find any participant data table.");
			}else return objs[0];
		}
	}
	
	/**
	 * set Individual attritubte sort.
	 * @param nameArray
	 * @param orderNum
	 */
    public void setIndividualAttrSortOrder(String[] nameArray,String[] orderNum){
    	if(nameArray.length!=orderNum.length){
    		throw new ErrorOnPageException("The name array should same withOrderNum");
    	}
    	for(int i=0;i<nameArray.length;i++){
    		this.setAttrSortOrder(nameArray[i], orderNum[i]);
    	}
    }
	
	public IHtmlObject getAttributeElement(String name,boolean isIndividual){
		//Get all drop down list in correct participant data table
		IHtmlObject obj =getParticiPentTableObj(isIndividual);
		IHtmlObject[] dropDownObjs=browser.getDropdownList(selectedValue(), obj);
		if(dropDownObjs.length<1){
			throw new ErrorOnPageException("No tabel exist");
		}
		
		//Get wanted element
		IHtmlObject parentObjs = null;
		for(int i=0;i<dropDownObjs.length;i=i+3){
			if(((ISelect)dropDownObjs[i]).getSelectedText().equals(name)){
				parentObjs =(IHtmlObject)(dropDownObjs[i].getParent());
				parentObjs =(IHtmlObject)(parentObjs.getParent());
				parentObjs =(IHtmlObject)(parentObjs.getParent());
				break;
			}
		}
		Browser.unregister(obj);
		Browser.unregister(dropDownObjs);
		return parentObjs;
	}
	/**
	 * set attribute sort order.
	 * @param name
	 * @param orderNum
	 */
	public void setAttrSortOrder(String name,String orderNum){
	   IHtmlObject obj = this.getAttributeElement(name, true);
	    Property[] p = new Property[1];
		p[0] = new Property(".name", new RegularExpression("TourParticipantAttribute-\\d+\\.sequence",false));
		IHtmlObject[] inputobj = browser.getHtmlObject(p, obj);
		String idValue = inputobj[0].getAttributeValue(".id");
		
	   browser.setTextField(".id", idValue,orderNum);
	}
}
