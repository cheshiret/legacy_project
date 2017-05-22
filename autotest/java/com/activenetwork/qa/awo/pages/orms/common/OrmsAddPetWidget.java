package com.activenetwork.qa.awo.pages.orms.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @author Phoebe.chen
 * @Date Dec 2, 2013
 */
public class OrmsAddPetWidget extends DialogWidget {
	protected OrmsAddPetWidget(){
		super("Add Pets");
	}
	
	private static OrmsAddPetWidget _instance = null;
	
	public static OrmsAddPetWidget getInstance(){
		if(_instance == null){
			_instance = new OrmsAddPetWidget();
		}
		return _instance;
	}
	
	protected Property[] petKindsNumDropdownList(){
		return Property.toPropertyArray(".class","Html.SELECT",".id",new RegularExpression("^DropdownExt-\\d+\\.selectedValue$",false));
	}
	
	protected Property[] petPanelTable(){
		return Property.toPropertyArray(".class","Html.TABLE",".ID",new RegularExpression("checkBoxPanelBar_Pets_\\d+",false));
	}
	
	protected Property[] petCampingDialogDiv(){
		return Property.toPropertyArray(".class","Html.DIV",".id","PetsCampingInfoDetailRenderDialog_dialog");
	}
	
	protected Property[] numberOfPetTextField(){
		return Property.toPropertyArray(".id",new RegularExpression("campingInfoContainer_Pets_\\d+_pets-row:\\d+_numberofpets",false));
	}
	
	protected Property[] petTypeDropdownList(){
		return Property.toPropertyArray(".class","Html.SELECT",".id",new RegularExpression("campingInfoContainer_Pets_\\d+_pets-row:\\d+_pettype",false));
	}
	
	protected Property[] addPetButton(){
		return Property.toPropertyArray(".class","Html.A",".text","OK");
	}
	
	protected Property[] cancelButton(){
		return Property.toPropertyArray(".class","Html.A",".text",new RegularExpression("^Cancel",false));
	}
	
	protected IHtmlObject[] getPetPanelTable(){
		return browser.getHtmlObject(this.petPanelTable());
	}
	
	protected IHtmlObject[] getPetCampingDialogDivObj(){
		return browser.getHtmlObject(this.petCampingDialogDiv());
	}
	
	public void selectPetTotalNum(int num){
		IHtmlObject[] obj = this.getPetPanelTable();
		IHtmlObject tab = obj[1]; 
		browser.selectDropdownList(petKindsNumDropdownList(), String.valueOf(num), true, tab);
		Browser.unregister(obj);
	}
	
	public int getMaximumNumOfPets(){
		IHtmlObject[] obj = this.getPetPanelTable();
		IHtmlObject tab = obj[1]; 
		List<String> values = browser.getDropdownElements(petKindsNumDropdownList(),tab);
		return Integer.parseInt(values.get(values.size()-1));
	}
	
	public void setNumOfPet(int index, String number){                                                                       
		browser.setTextField(numberOfPetTextField(), number, index-1);
	}
	
	public void selectPetType(int index, String type){
		browser.selectDropdownList(petTypeDropdownList(), type, index-1);
	}
	
	public void setPetInfo(HashMap<String,String> pets){
		this.selectPetTotalNum(pets.size());
		Browser.sleep(5);
		Iterator<String> iterator = pets.keySet().iterator();
		int i = 1;
		while (iterator.hasNext()){
			String type = (String)iterator.next();
			String num = pets.get(type);
			this.setNumOfPet(i, num);
			this.selectPetType(i, type);
			i++;
		}
	}
	
	public void clickAddPets(){
		IHtmlObject[] objs = this.getPetCampingDialogDivObj();
		IHtmlObject div = objs[0]; 
		browser.clickGuiObject(addPetButton(), true, 0, div);
		Browser.unregister(objs);
	}
	
	public void clickCancel(){
		browser.clickGuiObject(cancelButton(),true,0,this.getPetCampingDialogDivObj()[0]);
	}
}
