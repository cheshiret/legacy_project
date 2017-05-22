package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @author ssong
 * @date Dec 6, 2011
 */
public class LicMgrVehicleCoOwnersPage extends LicMgrVehicleDetailPage{
	
	private static LicMgrVehicleCoOwnersPage _instance = null;
	
	protected LicMgrVehicleCoOwnersPage(){
		
	}
	
	public static LicMgrVehicleCoOwnersPage getInstance(){
		if(_instance == null){
			_instance = new LicMgrVehicleCoOwnersPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class","Html.A",".text","View Previous Co-Owners");
	}
	
	public void clickViewPreviousCoOwners(){
		browser.clickGuiObject(".class","Html.A",".text","View Previous Co-Owners");
	}
	
	public void clickRemoveCoOwner(){
		browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("^Remove Co-Owner from [Boat|Motor|Dealer]",false));
	}
	
	public void clickAddCoOwner(){
		browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("^Add Co-Owner to [Boat|Motor|Dealer]",false));
	}
	
	public void clickCoOwnerNum(String num) {
		browser.clickGuiObject(".class", "Html.A", ".text", num, true);
	}
	
	public List<OwnerInfo> getVehicleCoOwners(){
		IHtmlObject[] objs = getCoOwnerTable();
		IHtmlTable grid = (IHtmlTable)objs[0];
		OwnerInfo owner;
		List<OwnerInfo> owners = new ArrayList<OwnerInfo>();
		
		for(int i=1;i<grid.rowCount();i++){
			owner = new OwnerInfo();
			owner.id = grid.getCellValue(i, 1);
			owner.firstName = grid.getCellValue(i, 2);
			owner.midName = grid.getCellValue(i, 3);
			owner.lastName = grid.getCellValue(i, 4);
			owner.dateOfBirth = grid.getCellValue(i, 5);
			owner.businessName = grid.getCellValue(i, 6);
			owner.identifierNum = grid.getCellValue(i, 7);
			owner.coOwnerFrom = grid.getCellValue(i, 8);
			owners.add(owner);
		}
		Browser.unregister(objs);
		return owners;
	}
	
	/**
	 * 
	 * @param ownerInfo
	 * @return
	 */
	public String getCoOwnerNumByCoOwnerInfo(OwnerInfo ownerInfo){
		IHtmlObject[] objs = getCoOwnerTable();
		if(objs.length<1){
			throw new ErrorOnPageException("Could not find table with text 'Co-Owner # First Name Middle Name...'");
		}
		IHtmlTable grid = (IHtmlTable)objs[0];
		String num = null;
		for(int i=1;i<grid.rowCount();i++){
			String fName = grid.getCellValue(i, 2);
			String lName = grid.getCellValue(i, 4);
			String mName = grid.getCellValue(i, 3);
			String dateOfBirth = grid.getCellValue(i, 5);
			String bName = grid.getCellValue(i, 6);
//			String identification = grid.getCellValue(i, 7);
//			String from = grid.getCellValue(i, 8);
			
			if("" != dateOfBirth){
				dateOfBirth = DateFunctions.formatDate(ownerInfo.dateOfBirth, "yyyy-M-d");
			}
			if(ownerInfo.firstName.equals(fName)
			&& ownerInfo.lastName.equals(lName)
			&& ownerInfo.midName.equals(mName)
			&& ownerInfo.businessName.equals(bName)
			&& ownerInfo.dateOfBirth.equals(dateOfBirth)){
				num = grid.getCellValue(i, 1);
				break;
			}
			//TODO Consider other paramaters
		}
		Browser.unregister(objs);
		
		return num;
	}
	
	public void selectCheckBoxByID(String id){
		IHtmlObject[] objs = getCoOwnerTable();
		if(objs.length<1){
			throw new ErrorOnPageException("Could not find table with text 'Co-Owner # First Name Middle Name...'");
		}
		IHtmlTable grid = (IHtmlTable)objs[0];
		for(int i=1;i<grid.rowCount();i++){
			String index = grid.getCellValue(i, 1);
			if(index.equals(id)){
				browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false));
				break;
			}
		}
		Browser.unregister(objs);
	}
	
	public void verifyCoOwnerNumDisplay(){
		IHtmlObject[] objs = getCoOwnerTable();
		if(objs.length<1){
			throw new ErrorOnPageException("Could not find table with text 'Co-Owner # First Name Middle Name...'");
		}
		logger.info("Co-Owner # has displayed in Previous Co-Owner table");
		Browser.unregister(objs);
	}
	
	public IHtmlObject[] getCoOwnerTable() {
		return browser.getTableTestObject(".text", new RegularExpression("^Co-Owner # First Name Middle Name.*", false));
	}
	
	public boolean verifyVehicleCoOwnerInfoInList(OwnerInfo owner){
		List<OwnerInfo> owners = this.getVehicleCoOwners();
		if(null != owners && owners.size()>0){
			String fName;
			String lName;
			String mName;
			String birth;
			String bName;
			String identifierNum;
			
			for(int i=0; i<owners.size(); i++){
				fName = owners.get(i).firstName;
				lName = owners.get(i).lastName;
				mName = owners.get(i).midName;
				birth = owners.get(i).dateOfBirth;
				bName = owners.get(i).businessName;
				identifierNum = owners.get(i).identifierNum.replaceAll("\\s+", "");//identifierType+identifierNum
				
				if(owner.firstName.equals(fName)
				&& owner.lastName.equals(lName)
				&& owner.midName.equals(mName)
				&& (!StringUtil.isEmpty(owner.dateOfBirth) && DateFunctions.compareDates(owner.dateOfBirth, birth)==0)
				&& owner.businessName.equals(bName)
				&& (owner.identifierType+owner.identifierNum).replaceAll("\\s+", "").equals(identifierNum)){
					logger.info("Coowner detail info has been verified successfully in co-owner list.");
					return true;
				}
			}
		}else{
			throw new ErrorOnPageException("Could not find any vehicle coowner info on page.");
		}
	
		return false;
	}
	
	/**
	 * get coOwner  id.
	 * @param ownerInfo
	 * @return
	 */
	public String getCoOwnerIdToBoat(OwnerInfo ownerInfo){
		String id = "";
		List<OwnerInfo> owners = this.getVehicleCoOwners();
		for(int i =0;i<owners.size();i++){
			/*System.out.println(owners.get(i).firstName);
			System.out.println(ownerInfo.firstName);
			System.out.println(owners.get(i).lastName);
			System.out.println(ownerInfo.lastName);
			System.out.println(owners.get(i).coOwnerFrom);
		    System.out.println(ownerInfo.coOwnerFrom);
			//System.out.println(DateFunctions.formatDate(owners.get(i).coOwnerFrom.split(""), "EEE MMM d yyyy"));
			//System.out.println(DateFunctions.formatDate(ownerInfo.coOwnerFrom,"EEE MMM dd yyyy"));
			System.out.println(owners.get(i).businessName);
			System.out.println(ownerInfo.businessName);
			System.out.println(owners.get(i).identifierNum);
			System.out.println(ownerInfo.identifierNum);*/
			if( owners.get(i).firstName.equals(ownerInfo.firstName)
			   && owners.get(i).midName.equals(ownerInfo.midName) && owners.get(i).lastName.equals(ownerInfo.lastName)
			   && owners.get(i).dateOfBirth.equals(ownerInfo.dateOfBirth)&& owners.get(i).businessName.equals(ownerInfo.businessName)
			   && owners.get(i).identifierNum.equals(ownerInfo.identifierNum)&&owners.get(i).coOwnerFrom.replaceAll("\\s+", StringUtil.EMPTY).equals(ownerInfo.coOwnerFrom.replaceAll("\\s+", StringUtil.EMPTY))){
				id = owners.get(i).id;
			}
		}
		return id;
	}
	/**
	 * check the coOwnerExist or not.
	 * @param coOwnerId
	 * @return
	 */
	public boolean checkCoOwnerExist(String coOwnerId){
		IHtmlObject[] objs = getCoOwnerTable();
		if(objs.length<1){
			throw new ErrorOnDataException("No table element exist");
		}
		IHtmlTable grid = (IHtmlTable)objs[0];
		boolean isExist = grid.getColumnValues(1).contains(coOwnerId);
		Browser.unregister(objs);
		return isExist;
	}
	/**
	 * verify co owner not exist.
	 * @param coOwnerId
	 */
	public void verifyCoOwnerNotExist(String coOwnerId){
		boolean isExist =  this.checkCoOwnerExist(coOwnerId);
		if(isExist){
			throw new ErrorOnPageException("The co-Owner id "+coOwnerId+" should not exist after transfer");
		}
	}
	
	public void selectAllcoOwner(){
		browser.selectCheckBox("name", "all_slct", 0, true);
	}
	
	public int getCoOwnerNum(){
		IHtmlObject[] cbObjs = browser.getCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false));
		return cbObjs.length;
	}
	
	public List<String> getAllCoOwnerIdForVehicle(){
		List<String> coOwnerIds = new ArrayList<String>();
		List<OwnerInfo> owners = this.getVehicleCoOwners();
		for(int i =0;i<owners.size();i++){
			coOwnerIds.add(owners.get(i).id);
		
		}
		return coOwnerIds;
	}
}
