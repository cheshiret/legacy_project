/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:this page is used to add a print document to a specified product
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  May 16, 2011
 */
public class LicMgrAddPrintDocumentWidget extends LicMgrPrintDocumentsDetailsWidget {
	
	private static LicMgrAddPrintDocumentWidget _instance=null;
	
	private  LicMgrAddPrintDocumentWidget(){
		super("Add Print Document");
	}
	
	public static LicMgrAddPrintDocumentWidget getInstance(){
		if(_instance==null){
			_instance=new LicMgrAddPrintDocumentWidget();
		}
		return _instance;
	}
	
	
	public void selectDocumentTemplate(String templ){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.documentTemplate",false);
		browser.selectDropdownList(".id", regx, templ);
	}
	
	/**
	 * select equipment type:All
	 */
	public void selectEquipmentType_All(){
		RegularExpression regx=new RegularExpression("EquipmentTypeSelectionState-\\d+\\.appliedToAllTypes",false);
	    browser.selectCheckBox(".id", regx);
	}
	
	/**
	 * unselect equipment type:All
	 */
	public void unSelectEquipmentType_All(){
		RegularExpression regx=new RegularExpression("EquipmentTypeSelectionState-\\d+\\.appliedToAllTypes",false);
	    browser.unSelectCheckBox(".id", regx);
	}
	
	/**
	 * Unselect all Equipment Types checkboxs
	 */
	public void unselectAllEquipmentTypes(){
		RegularExpression regx=new RegularExpression("EquipmentTypeSelectionState-\\d+\\.equipmentTypes_\\d+",false);
		
		this.unSelectEquipmentType_All();
		ajax.waitLoading();
		IHtmlObject[] objs=browser.getCheckBox(".id", regx);
		
		for(int i=0;i<objs.length;i++){
			browser.unSelectCheckBox(".id", regx,i);
		}
		Browser.unregister(objs);
	}
	
	public void selectPurchaseTypes(String... types){
//		int index=0;
		for(String type:types){
			// updated by Nicole. because there is 'Privilege Inventory Replacement' before 'Duplicate' so index isn't correct. 
//			index=this.getPurchaseTypeIndex(type);
			this.selectPurchaseType(type);
		}
	}
	
	public void selectEquipmentTypes(String... types){
		for(String type:types){
			this.selectEquipmentType(type);
		}
	}
	
	public void selectEquipmentType(String type){
		IHtmlObject[] objs = browser.getHtmlObject(".className",
				"trailing", ".text", type);
		objs[0].click();
		Browser.unregister(objs);
	}
	
	/**
	 * get purchase type index 
	 * @param type
	 * @return
	 */
//	public int getPurchaseTypeIndex(String type){
//		int index=0;
//		String types=this.getPurchaseTypes().get(0);
//		String[] typeArray=types.split("[A-Z]");
//		for(int i=1;i<typeArray.length;i++){
//			if(typeArray[i].trim().equals(type.substring(1))){
//				index=i;
//			}
//		}
//		if(index==0){
//			throw new ErrorOnPageException("Can't find the type:"+type);
//		}
//		return index-1;
//		
//	}
	
	/**
	 * Unselect all purchase types 
	 */
	public void unSelectAllPurchaseTypes( ){
		RegularExpression regx=new RegularExpression("PurchaseTypeSelectionState-\\d+\\.purchaseTypes_\\d+",false);
		IHtmlObject[] objs=browser.getCheckBox(".id", regx);
		
		for(int i=0; i<objs.length;i++){
			browser.unSelectCheckBox(".id", regx, i);
		}
		Browser.unregister(objs);
	}
	
	public void selectPurchaseType(String type){
//		RegularExpression regx=new RegularExpression("PurchaseTypeSelectionState-\\d+.purchaseTypes_\\d+",false);
//	    browser.selectCheckBox(".id", regx, index);

		// updated by Nicole. because there is 'Privilege Inventory Replacement' before 'Duplicate' so index isn't correct. 
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LABEL", ".text", type);
		String id = objs[0].getAttributeValue("for");
	    browser.selectCheckBox(".id", id);

	}
	
	public void selectSpecies(String species){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.species",false);
		if(species.trim().length()<1){
			browser.selectDropdownList(".id", regx, 0);
			return;
		}
		browser.selectDropdownList(".id", regx, species);
	}
	
	public List<String> getSpeciesElements(){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.species",false);	
		List<String> elements = browser.getDropdownElements(".id", regx);
		return elements;
	}
	
	/**
	 * select Hunting season if DOC is a havest document
	 */
	public void selectHuntingSeason(String season){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.huntingSeason",false);
	    if(season.trim().length()<1){
	    	browser.selectDropdownList(".id", regx, 0);
	    }
		browser.selectDropdownList(".id", regx, season);
	}
	
	public List<String> getHuntingSeasonElements(){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.huntingSeason",false);	
		List<String> elements = browser.getDropdownElements(".id", regx);
		return elements;
	}
	
	
	/**
	 * Set up document info 
	 */
	public void setDocInfo(LicMgrDocumentInfo docInfo){
		this.selectDocumentTemplate(docInfo.docTepl);
		ajax.waitLoading();
		super.setDocInfo(docInfo);
		
		if(docInfo.equipType.equalsIgnoreCase("All") || (docInfo.equipTypes.length>0&&docInfo.equipTypes[0].equalsIgnoreCase("All"))){
			this.selectEquipmentType_All();
			ajax.waitLoading();
		}else{
			this.unSelectEquipmentType_All();
			ajax.waitLoading();
			
			if(null!=docInfo.equipTypes && docInfo.equipTypes.length>0){
				for(String type: docInfo.equipTypes){
					if(StringUtil.notEmpty(type)){
						this.selectEquipmentType(type);
					}
				}
			}else{
				if(docInfo.equipType!=null && docInfo.equipType.length()>0)
					this.selectEquipmentType(docInfo.equipType);
				else{
					unselectAllEquipmentTypes();
				}
			}
		}
		
		if(null!=docInfo.purchaseTypes && docInfo.purchaseTypes.length>0){
			for(String type: docInfo.purchaseTypes){
				this.selectPurchaseTypes(type);
			}
		}else if(docInfo.purchaseType.trim().length()>0){
			this.selectPurchaseTypes(docInfo.purchaseType);
		}else{
			this.unSelectAllPurchaseTypes();
			ajax.waitLoading();
		}
		
		if(docInfo.prodType.equalsIgnoreCase("Privilege") && docInfo.licYearFrom.equals(docInfo.licYearTo)) {
			if(isPrintFromAndPrintToTextFeildExist()){
				this.setPrintFromDate(docInfo.printFromDate);
				this.setPrintToDate(docInfo.printToDate);
			}
		}
		if(this.isSpeciesDropdownListExist()&&StringUtil.notEmpty(docInfo.species)){
			this.selectSpecies(docInfo.species);
		}
		
		if(this.isHuntingSeasonDropdownListExist()&&StringUtil.notEmpty(docInfo.huntSeason)){
			this.selectHuntingSeason(docInfo.huntSeason);
		}
	}
	
	/**
	 * when adding a print document, the status drop down list should be 'disabled' 
	 * and the 'Active' item should be selected. 
	 */
	public void verifyStatusDropDownList(){
		if(!this.getStatus().equals("Active")){
			throw new ErrorOnPageException("The default status should be 'Active'.");
		}
		
		if(!this.isStatusDisabled()){
			throw new ErrorOnPageException("Status drop down list should be disabled.");
		}
	}

}
