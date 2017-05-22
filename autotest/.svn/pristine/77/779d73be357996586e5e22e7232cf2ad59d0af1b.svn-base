package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.InvalidDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrQuotaLicenseYearWidget extends DialogWidget {
	private static LicMgrQuotaLicenseYearWidget _instance = null;
	
	private LicMgrQuotaLicenseYearWidget() {}
	
	public static LicMgrQuotaLicenseYearWidget getInstance() {
		if(_instance == null) {
			_instance = new LicMgrQuotaLicenseYearWidget();
		}
		
		return _instance;
	}
	
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "HuntLicenseYearQuotaDetailUI");
	}
	
	protected Property[] LicenseYearQuotaDetailTable(){
		return Property.toPropertyArray(".class", "Html.TABLE", ".id", "HuntLicenseYearQuotaDetailUI");
	}
	
	protected Property[] LicenseYearDropdownList() {
		return Property.toPropertyArray(".id", new RegularExpression("HuntQuotaLicenseYearView-\\d+\\.licenseYear",false));
	}
	
	protected Property[] quotaTextField() {                          
		return Property.toPropertyArray(".class","Html.INPUT.TEXT",".id", new RegularExpression("HuntQuotaDynamicTableVO-\\d+\\.quota", false));
	}
	
	protected Property[] numberOfSalsTextField() {
		return Property.toPropertyArray(".id", new RegularExpression("HuntQuotaDynamicTableVO-\\d+\\.usedInventory", false));
	}
		
	protected Property[] hybridCheckBox() {
		return Property.toPropertyArray(".id", new RegularExpression("HuntQuotaDynamicTableVO-\\d+\\.hybrid", false));
	}
	
	protected Property[] weightedTextField() {
		return Property.toPropertyArray(".id", new RegularExpression("HuntQuotaDynamicTableVO-\\d+\\.weightOnQuota", false));
	}
	
	protected Property[] randomTextField() {
		return Property.toPropertyArray(".id", new RegularExpression("HuntQuotaDynamicTableVO-\\d+\\.random", false));
	}
	
	protected Property[] splitIntoDropdownList() {
		return Property.toPropertyArray(".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue", false));
	}
	
	protected Property[] statusDropdownList() {                   
		return Property.toPropertyArray(".id", new RegularExpression("HuntQuotaLicenseYearView-\\d+\\.status", false));
	}
	
	protected Property[] quotaTable() {
		return Property.toPropertyArray(".id", new RegularExpression("quotaDynTable", false));
	}
	
	protected Property[] quotaTypeTextField() {
		return Property.toPropertyArray(".id", new RegularExpression("HuntQuotaDynamicTableVO-\\d+\\.quotaTypeDescription", false));
	}
	
	protected Property[] okBtn(){
		return Property.concatPropertyArray(this.a(), ".text", "OK");
	}
	
	protected Property[] cancelBtn(){
		return Property.concatPropertyArray(this.a(), ".text", "Cancel");
	}
	
	protected Property[] splitInto(){
		return Property.concatPropertyArray(select(), ".id",new RegularExpression("DropdownExt-\\d+\\.selectedValue",false));
	}
	
	protected Property[] quotaSplit(){
		return Property.concatPropertyArray(input("text"), ".id",new RegularExpression("HuntInventorySplitView-\\d+\\.quantity",false));
	}
	
	protected Property[] splitToHunt(){
		return Property.concatPropertyArray(select(), ".id",new RegularExpression("HuntInventorySplitView-\\d+\\.hunt",false));
	}
	
	public IHtmlTable getQuotaLicenseYearDetailTable(){
		IHtmlObject[] objs = browser.getHtmlObject(LicenseYearQuotaDetailTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		return table;
	}
	
	public IHtmlTable getQuotaTable(){
		IHtmlObject[] objs = browser.getHtmlObject(quotaTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		return table;
	}
	
	public void selectLicenseYear(String licenseYear){
		if(StringUtil.isEmpty(licenseYear)){
			browser.selectDropdownList(this.LicenseYearDropdownList(), 0);
		}else{
			browser.selectDropdownList(this.LicenseYearDropdownList(), licenseYear);
		}
	}
	
	public void selectlicenseYearStatus(String status){
		browser.selectDropdownList(this.statusDropdownList(), status, true, getQuotaLicenseYearDetailTable());
	}
	
	public void setQuota(String quota, int indexNum){
		browser.setTextField(this.quotaTextField(), quota, true, indexNum+1, getQuotaLicenseYearDetailTable());
	}
	
	/**
	 * This only display when edit a license year quota
	 */
	public void setNumOfSales(String sales, int indexNum){
		browser.setTextField(this.quotaTextField(), sales,true, indexNum, getQuotaLicenseYearDetailTable());
	}
	
	public void checkHybrid(int indexNum){
		browser.selectCheckBox(this.hybridCheckBox(), indexNum);
	}
	
	public void uncheckHybrid(int indexNum){
		browser.unSelectCheckBox(this.hybridCheckBox(), indexNum);
	}
	
	public void setWeighted(String weighted, int indexNum){
		browser.setTextField(this.weightedTextField(),weighted , indexNum);
	}
	
	
	public void selectSplitInto(String splitInto, int indexNum){
		browser.selectDropdownList(this.splitIntoDropdownList(), splitInto,indexNum);
	}
	
	public boolean isNumberOfSalesTextFieldDisplay(){
		return browser.checkHtmlObjectDisplayed(this.numberOfSalsTextField());
	}
	
	public int getQuotaTypeIndexNum(String quotaType){
		int indexNum = -1;
		IHtmlObject[] objs = browser.getHtmlObject(this.quotaTypeTextField());
		if(objs.length < 1){
			throw new ErrorOnPageException("Can not find any quota type text field object!");
		}
		for(int i=0; i<objs.length; i++){
			String type = objs[i].getAttributeValue("value");
			if(quotaType.equalsIgnoreCase(type)){
				indexNum = i;
				break;
			}
		}
		if(indexNum == -1){
			throw new ErrorOnPageException("Can not find quota type as:" + quotaType);
		}
		return indexNum;
	}
	
	public void setUpQuatoInfo(QuotaInfo quota){
		if(StringUtil.notEmpty(quota.getLicenseYear())){
			this.selectLicenseYear(quota.getLicenseYear());
		}
		for(QuotaType qtype: quota.getQuotaTypes()){
			int indexNum = this.getQuotaTypeIndexNum(qtype.getQuotaType());
			if(qtype.getQuota() != null){
				this.setQuota(qtype.getQuota(), indexNum);
			}
			if(isNumberOfSalesTextFieldDisplay()&&StringUtil.notEmpty(qtype.getNumOfSales())){//This only fit for edit, not fit for add
				this.setNumOfSales(qtype.getNumOfSales(), indexNum);
			}
			if(qtype.getIsHybrid()){
				this.checkHybrid(indexNum);
				ajax.waitLoading();
				this.waitLoading();
				if(StringUtil.notEmpty(qtype.getWeighted())){
					this.setWeighted(qtype.getWeighted(), indexNum);
				}
			}else{
				this.uncheckHybrid(indexNum);
				ajax.waitLoading();
				this.waitLoading();
			}
			int objectIndex = this.getSplitIntoDropdownNum()-1;//use the last object in widget,reduce 1 is due to array start from 0
			
			if(StringUtil.notEmpty(qtype.getSplitInto())){
				this.selectSplitInto(qtype.getSplitInto(), indexNum+objectIndex);
			}
			setSplitIntos(qtype.getSplitInto(), qtype.getSplitIntos(),indexNum+objectIndex);
		}
	}
	
	public void setUpInfoAndClickOk(QuotaInfo quota){
		this.setUpQuatoInfo(quota);
		this.clickOK();
	}
	
	public String getAttributeValue(String attrName){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^"+attrName + ".*", false), getQuotaLicenseYearDetailTable());
		if(objs.length < 1){
			throw new ErrorOnPageException("Can not find span object for attribute:" + attrName);
		}
		String content = objs[0].text().replace(attrName, "");
		Browser.unregister(objs);
		return content;
	}
	
	public String getQuotaID(){
		return this.getAttributeValue("Quota ID");
	}
	
	public String getQuotaDesc(){
		return this.getAttributeValue("Quota Description");
	}
	
	public String getSpecies(){
		return this.getAttributeValue("Species");
	}
	
	public String getLicenseYear(){
		return this.getAttributeValue("License Year");
	}
	
	public String getLicenseYearStatus(){
		return browser.getDropdownListValue(this.statusDropdownList(), 0);
	}
	
	public int getQuotaTypeNum(){
		IHtmlObject[] objs = browser.getHtmlObject(this.quotaTypeTextField(), getQuotaLicenseYearDetailTable());
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	public String getTextFieldValueOfQuotaType(int indexNum, Property[] properties){
		IHtmlObject[] objs = browser.getHtmlObject(properties, getQuotaLicenseYearDetailTable());
		String value = objs[indexNum].getAttributeValue("value");
		Browser.unregister(objs);
		return value;
	}
	
	public String getQuotaType(int indexNum){
		return this.getTextFieldValueOfQuotaType(indexNum, this.quotaTypeTextField());
	}
	
	public String getQuota(int indexNum){
		return this.getTextFieldValueOfQuotaType(indexNum+1, this.quotaTextField());
	}
	
	public String getSale(int indexNum){
		return this.getTextFieldValueOfQuotaType(indexNum, this.numberOfSalsTextField());
	}
	
	public boolean isHybridSelected(int indexNum){
		return browser.isCheckBoxSelected(".id", new RegularExpression("HuntQuotaDynamicTableVO-\\d+\\.hybrid", false), indexNum);
	}
	
	public String getWeighted(int indexNum){
		return this.getTextFieldValueOfQuotaType(indexNum, this.weightedTextField());
	}
	
	public String getRandom(int indexNum){
		return this.getTextFieldValueOfQuotaType(indexNum, this.randomTextField());
	}
	
	public String getSplitInto(int indexNum){
		return browser.getDropdownListValue(this.splitIntoDropdownList(), indexNum);
	}
	
	public int getSplitIntoDropdownNum(){
		IHtmlObject[] objs = browser.getDropdownList(splitIntoDropdownList());
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	public void selectSplitInto(String splitInto){
		browser.selectDropdownList(splitInto(), splitInto);
	}
	
	public void setQuotaSplit(String quota,int index){
		browser.setTextField(quotaSplit(), quota,index);
	}
	
	public String getQuotaSplit(int index){
		return browser.getTextFieldValue(quotaSplit(),index);
	}
	
	public void selectHunt(String hunt,int index){
		browser.selectDropdownList(splitToHunt(),hunt, index);
	}
	
	public String getHunt(int index){
		return browser.getDropdownListValue(splitToHunt(),index);
	}
	
	
	public void setSplitIntos(String splitInto,List<List<String>> splitIntos,int index){
		if(StringUtil.notEmpty(splitInto)){
			this.selectSplitInto(splitInto);
			ajax.waitLoading();
			
			if(splitIntos.size()!=Integer.parseInt(splitInto)){
				throw new InvalidDataException("Split Into Number and given Split Into info size not same.");
			}
			for(int i=0;i<splitIntos.size();i++){
				List<String> list = splitIntos.get(i);
				setQuotaSplit(list.get(0), i);
				selectHunt(list.get(1), i);
			}
		}
	}
	
	public List<List<String>> getSplitIntos(int index){
		List<String> splitInto;
		List<List<String>> splitIntos = new ArrayList<>();
		
		String splitIntoNum = this.getSplitInto(index);
		if(StringUtil.notEmpty(splitIntoNum)){
			int num = Integer.parseInt(splitIntoNum);
			for(int i=0;i<num;i++){
				splitInto = new ArrayList<String>();
				splitInto.add(getQuotaSplit(i));
				splitInto.add(getHunt(i));
				splitIntos.add(splitInto);
			}
		}
		
		return splitIntos;
	}
	
	public String getCreationUser(){
		return this.getAttributeValue("Creation User");
	}
	
	public String getCreationLocation(){
		return this.getAttributeValue("Creation Location");
	}
	
	public String getCreateDateTime(){
		return this.getAttributeValue("Creation Date/Time");
	}
	
	public String getLastUpdatedUser(){
		return this.getAttributeValue("Last Updated User");
	}
	
	public String getLastUpatedLocation(){
		return this.getAttributeValue("Last Updated Location");
	}
	
	public String getLastUpdatedDateTime(){
		return this.getAttributeValue("Last Updated Date/Time");
	}
	
	private QuotaInfo getLicenseYearInfo() {
		QuotaInfo quota = new QuotaInfo();
		quota.setQuotaId(this.getQuotaID());
		quota.setDescription(this.getQuotaDesc());
		quota.setSpecie(this.getSpecies());
		quota.setLicenseYear(this.getLicenseYear());
		quota.setLicenseYearQuotaStatus(this.getLicenseYearStatus());
		int qtypeNum = this.getQuotaTypeNum();
		List<QuotaType> qt = new ArrayList<QuotaType>();
		int objectIndex = this.getSplitIntoDropdownNum()-1;//reduce 1 due to array start from 0
		for(int i=0; i< qtypeNum; i++){
			QuotaType quotaType = new QuotaType();
			quotaType.setQuotaType(this.getQuotaType(i));
			quotaType.setQuota(this.getQuota(i));
			quotaType.setNumOfSales(this.getSale(i));
			quotaType.setIsHybrid(this.isHybridSelected(i));
			quotaType.setWeighted(this.getWeighted(i));
			quotaType.setRandom(this.getRandom(i));
			quotaType.setSplitInto(this.getSplitInto(i+objectIndex));
			quotaType.setSplitIntos(getSplitIntos(i+objectIndex));
			qt.add(quotaType);
		}
		quota.setQuotaTypes(qt);
		quota.setCreationUser(this.getCreationUser());
		quota.setCreationLocation(this.getCreationLocation());
		quota.setCreationDateTime(this.getCreateDateTime());
		quota.setLastModifiedUser(this.getLastUpdatedUser());
		quota.setLastModifiedLocation(this.getLastUpatedLocation());
		quota.setLastModifiedDateTime(this.getLastUpdatedDateTime());
		return quota;
	}
	
	public boolean compareLicenseYearDetailsInfo(QuotaInfo expected) {
		QuotaInfo actual = getLicenseYearInfo();
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Quota ID", expected.getQuotaId(), actual.getQuotaId());
		result &= MiscFunctions.compareResult("Quota Description", expected.getDescription(), actual.getDescription());
		result &= MiscFunctions.compareResult("Species", expected.getSpecie(), actual.getSpecie());

		result &= MiscFunctions.compareResult("License Year", expected.getLicenseYear(), actual.getLicenseYear());
		result &= MiscFunctions.compareResult("License Year Status", expected.getLicenseYearQuotaStatus(), actual.getLicenseYearQuotaStatus());
		
		List<QuotaType> qTypeList = expected.getQuotaTypes();
		result &= MiscFunctions.compareResult("Quota Type number", qTypeList.size(), actual.getQuotaTypes().size());
		for(int i = 0; i < qTypeList.size(); i++) {
			result &= MiscFunctions.compareResult("Quota Type", qTypeList.get(i).getQuotaType(), actual.getQuotaTypes().get(i).getQuotaType());
			result &= MiscFunctions.compareResult("Quota", qTypeList.get(i).getQuota(), actual.getQuotaTypes().get(i).getQuota());
			if(StringUtil.notEmpty(qTypeList.get(i).getNumOfSales())){
				result &= MiscFunctions.compareResult("Num of Sale", qTypeList.get(i).getNumOfSales(), actual.getQuotaTypes().get(i).getNumOfSales());
			}
			
			result &= MiscFunctions.compareResult("Is Hybrid", qTypeList.get(i).getIsHybrid(), actual.getQuotaTypes().get(i).getIsHybrid());
			if(StringUtil.notEmpty(qTypeList.get(i).getWeighted())){
				result &= MiscFunctions.compareResult("Weighted", qTypeList.get(i).getWeighted(), actual.getQuotaTypes().get(i).getWeighted());
			}
			if(StringUtil.notEmpty(qTypeList.get(i).getrandom())){
				result &= MiscFunctions.compareResult("Random", qTypeList.get(i).getrandom(), actual.getQuotaTypes().get(i).getrandom());
			}
			if(StringUtil.notEmpty(qTypeList.get(i).getSplitInto())){
				result &= MiscFunctions.compareResult("Split Into", qTypeList.get(i).getSplitInto(), actual.getQuotaTypes().get(i).getSplitInto());
			}
			
			if(qTypeList.get(i).getSplitIntos()!=null){
				result &= MiscFunctions.compareList("Split Into Details", qTypeList.get(i).getSplitIntos(), actual.getQuotaTypes().get(i).getSplitIntos());
			}
		}
		
		result &= MiscFunctions.compareResult("Creation User", expected.getCreationUser(), actual.getCreationUser().replace(" ", ""));
		result &= MiscFunctions.compareResult("Creation Location", expected.getCreationLocation(), actual.getCreationLocation());
		result &= MiscFunctions.compareResult("Creation Date/Time", expected.getCreationDateTime(), 
		 actual.getCreationDateTime().split(String.valueOf(DateFunctions.getCurrentYear()))[0] + String.valueOf(DateFunctions.getCurrentYear()));
		if(!StringUtil.isEmpty(expected.getLastModifiedDateTime())) {
			result &= MiscFunctions.compareResult("Last Modified Date/Time", expected.getLastModifiedDateTime(), actual.getLastModifiedDateTime());
		}
		if(!StringUtil.isEmpty(expected.getLastModifiedUser())) {
			result &= MiscFunctions.compareResult("Last Modified User", expected.getLastModifiedUser(), actual.getLastModifiedUser());
		}
		if(!StringUtil.isEmpty(expected.getLastModifiedLocation())) {
			result &= MiscFunctions.compareResult("Last Modified Location", expected.getLastModifiedLocation(), actual.getLastModifiedLocation());
		}
		return result;
	}
}
