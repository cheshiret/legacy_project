package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Aug 2, 2013
 */
public class LicMgrPrivilegeLotterySelectHuntPage extends LicMgrTopMenuPage {
	
	private static LicMgrPrivilegeLotterySelectHuntPage _instance = null;
	
	private LicMgrPrivilegeLotterySelectHuntPage() {}
	
	public static LicMgrPrivilegeLotterySelectHuntPage getInstance() {
		if(_instance == null) {
			_instance = new LicMgrPrivilegeLotterySelectHuntPage();
		}
		
		return _instance;
	}
	
	public static String TABLE_COLNAME_POINT = "Point";
	
	protected Property[] pointTypeTable(){
		return Property.concatPropertyArray(div(), ".id", "privilegeLotteryList");
	}
	
	protected Property[] huntSearchBar(){
		return Property.toPropertyArray( ".id", "huntSearchBar");
	}
	
	protected Property[] pointTypeCheckBox(){
		return Property.toPropertyArray(".id", new RegularExpression("PointPurchaseChoiceView-\\d+\\.selected", false));
	}
	
	protected Property[] idenNum(){                                   
		return Property.toPropertyArray(".id", new RegularExpression("ApplicantGroupMemberView-\\d+\\.identifierNumber", false));
	}
	
	protected Property[] custNum(){                           
		return Property.toPropertyArray(".id", new RegularExpression("ApplicantGroupMemberView-\\d+\\.customerNumber", false));
	}
	
	protected Property[] dateOfBirth(){
		return Property.toPropertyArray(".id", new RegularExpression("ApplicantGroupMemberView-\\d+\\.birthDay_ForDisplay", false));
	}
	
	protected Property[] addMember(){
		return Property.concatPropertyArray(a(), ".text", "Add Member");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(huntSearchBar()) ||browser.checkHtmlObjectExists(pointTypeTable());
	}
	
	public void setHuntCode(String code) {
		browser.setTextField(".id", new RegularExpression("HuntSearchCriteria-\\d+\\.code", false), code);
	}
	
	public void setHuntDescription(String desc) {
		browser.setTextField(".id", new RegularExpression("HuntSearchCriteria-\\d+\\.description", false), desc);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void clickReset() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Reset");
	}
	
	public void clickAddMember() {
		browser.clickGuiObject(addMember());
	}
	
	public void setCustNum(String cusNum, int index){
		browser.setTextField(custNum(), cusNum, index);
	}
	
	public void setIdenNum(String idenNum, int index){
		browser.setTextField(idenNum(), idenNum, index);
	}
	
	public String getCustNum(){
		IHtmlObject[] objs = browser.getTextField(custNum());
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find customter number objects.");
		}
		String value = objs[0].getProperty(".value");
		Browser.unregister(objs);
		return value;
	}
	
	public void setDateOfBirth(String dob, int index){
		browser.setTextField(dateOfBirth(), dob, index);
	}
	
	public void searchHunt(String code, String desc) {
		this.clickReset();
		ajax.waitLoading();
		this.setHuntCode(code);
		this.setHuntDescription(desc);
		this.clickGo();
		ajax.waitLoading();
	}
	
	private IHtmlObject[] getAvailableHuntListTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "availableHuntList");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find Available Hunt list table object.");
		}
		
		return objs;
	}
	
	public void selectHunts(String... codes) {
		IHtmlObject tableObjs[] = this.getAvailableHuntListTableObject();
		IHtmlTable table = (IHtmlTable)tableObjs[0];
		
		int colIndex = -1;
		int rowIndex = -1;
		for(String code : codes) {
			colIndex = table.findColumn(0, "Hunt Code/Description");
			rowIndex = table.findRow(colIndex, new RegularExpression(code, false));
			Property[] p = Property.toPropertyArray(".id", new RegularExpression("HuntChoiceView-\\d+\\.selected", false));
			if(browser.checkHtmlObjectExists(p)){
				browser.selectCheckBox(p, rowIndex - 1);
			}else browser.selectRadioButton(".type", "radio");
			ajax.waitLoading();
		}
		Browser.unregister(tableObjs);
	}
	
	public void unSelectAllHunts(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("HuntChoiceView-\\d+\\.selected", false));
		for(int i=0; i<objs.length; i++){
			browser.unSelectCheckBox(".id", new RegularExpression("HuntChoiceView-\\d+\\.selected", false), i);
			ajax.waitLoading();
		}
		Browser.unregister(objs);
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	private IHtmlObject[] getAvailablePointTypeListTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(pointTypeTable());
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find Available point type list table object.");
		}
		
		return objs;
	}
	
	public void selectPointTypes(String... pointTypes) {
		IHtmlObject tableObjs[] = this.getAvailablePointTypeListTableObject();
		IHtmlTable table = (IHtmlTable)tableObjs[0];
		
		int colIndex = -1;
		int rowIndex = -1;
		for(String pointType : pointTypes) {
			colIndex = table.findColumn(0, TABLE_COLNAME_POINT);
			rowIndex = table.findRow(colIndex, new RegularExpression(pointType, false));
			browser.selectCheckBox(pointTypeCheckBox(), rowIndex - 1);
			ajax.waitLoading();
		}
		Browser.unregister(tableObjs);
	}
	
	public void unSelectAllPointTypes(){
		IHtmlObject[] objs = browser.getHtmlObject(this.pointTypeCheckBox());
		for(int i=0; i<objs.length; i++){
			browser.unSelectCheckBox(this.pointTypeCheckBox(), i);
			ajax.waitLoading();
		}
		Browser.unregister(objs);
	}
	
	public void setupGroupMembers(List<String[]> groupMembers){
		for(int i=0; i<groupMembers.size(); i++){
			clickAddMember();
			ajax.waitLoading();
			setIdenNum(groupMembers.get(i)[0],i);
			setDateOfBirth(groupMembers.get(i)[1],i);
		}
	}
}
