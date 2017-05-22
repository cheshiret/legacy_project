/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Apr 20, 2012
 */


public class LicMgrMergeCustomerDetails extends LicMgrCommonTopMenuPage {
	private static LicMgrMergeCustomerDetails _instance = null;
	private RegularExpression identifier_type_pattern=new RegularExpression("CustomerIdentifierView.*",false);
	private RegularExpression identifier_status_pattern=new RegularExpression("CustomerIdentiferView-\\d+\\.status:CB_TO_NAME",false);
	private RegularExpression identifier_number_pattern=new RegularExpression("CustomerIdentiferView-\\d+\\.identifier\\.identifierNumber",false);
//	private RegularExpression identifier_state_pattern=new RegularExpression("CustomerIdentiferView-\\d+\\.identifier\\.stateProvince:CB_TO_NAME",false);
//	private RegularExpression identifier_country_pattern=new RegularExpression("CustomerIdentiferView-\\d+\\.identifier\\.country:CB_TO_NAME",false);
	
	public static LicMgrMergeCustomerDetails getInstance() {
		if (null == _instance) {
			_instance = new LicMgrMergeCustomerDetails();
		}

		return _instance;
	}
	
	public enum Item {
		Phone_Email("Phone/Email"),
		HunterSuspention("Hunter Suspention"),
		HunterEligibility("Hunter Eligibility"),
		PhysicalAddress("Physical Address"),
		MailingAddress("Mailing Address"),
		AlternateAddress("Alternate Address"),
		Indentifiers("Identifiers"),
		Points("Points"),
		SelectCustomerToKeep("Select Customer to Keep"),
		NameDOB("Name/DOB"),
		;
		
		private String value;
		Item(String value) {
			this.value=value;
		}
		
		public String toString() {
			return value;
		}
	}

	protected LicMgrMergeCustomerDetails() {

	}
	
	protected Property[] customerTabContentDiv() {
		return Property.toPropertyArray(".class","Html.DIV",".id","content_CustomerMgrTabs");
	}
	
	protected Property[] profileRow(Item rowName) {
		return Property.toPropertyArray(".class","Html.TR",".text",new RegularExpression("^"+rowName+".*",false));
	}
	
//	protected Property[] phoneEmailRow() {
//		return Property.toPropertyArray(".class","Html.TR",".text",new RegularExpression("^Phone/Email.*",false));
//	}
//	
//	protected Property[] identifierRow() {
//		return Property.toPropertyArray(".class","Html.TR",".text",new RegularExpression("^Identifiers.*",false));
//	}
//	
//	protected Property[] pointsRow() {
//		return Property.toPropertyArray(".class","Html.TR",".text",new RegularExpression("^Points.*",false));
//	}
//	
//	protected Property[] physicalAddressRow() {
//		return Property.toPropertyArray(".class","Html.TR",".text",new RegularExpression("^Physical Address.*",false));
//	}
//	
//	protected Property[] mailingAddressRow() {
//		return Property.toPropertyArray(".class","Html.TR",".text",new RegularExpression("^Mailing Address.*",false));
//	}
//	
//	protected Property[] alternateAddressRow() {
//		return Property.toPropertyArray(".class","Html.TR",".text",new RegularExpression("^Alternate Address.*",false));
//	}
//	
//	protected Property[] hunterSuspentionRow() {
//		return Property.toPropertyArray(".class","Html.TR",".text",new RegularExpression("^Hunter Suspention.*",false));
//	}
//	
//	protected Property[] hunterEligivilityRow() {
//		return Property.toPropertyArray(".class","Html.TR",".text",new RegularExpression("^Hunter Eligibility.*",false));
//	}
	
//	protected List<Property[]> profileItemTable(Item name) {
//		return Property.atList(customerTabContentDiv(),profileRow(name) ,table());
//	}
	
	protected List<Property[]> identifierSection() {
		Property[] p3=Property.toPropertyArray(".class","Html.TABLE");
		
		return Property.atList(customerTabContentDiv(),profileRow(Item.Indentifiers) ,p3);
	}
	
	protected List<Property[]> phoneEmailSection() {
		
		Property[] p3=Property.toPropertyArray(".class","Html.TD",".className",new RegularExpression("^.fullSection$",false));
		
		return Property.atList(customerTabContentDiv(),profileRow(Item.Phone_Email),p3);
	}
	
	protected List<Property[]> nameDOBSection() {
		Property[] p3=Property.toPropertyArray(".class","Html.TD",".className",new RegularExpression("^.sectionBottom$",false));
		return Property.atList(customerTabContentDiv(),profileRow(Item.NameDOB),p3);
	}
	
	protected List<Property[]> hunterEligibilitySection() {
		Property[] p3=Property.toPropertyArray(".class","Html.TD",".className",new RegularExpression("^.fullSection$",false));
		return Property.atList(customerTabContentDiv(),profileRow(Item.HunterEligibility),p3);
	}
	
	protected List<Property[]> physicalAddressSection() {
		Property[] p3=Property.toPropertyArray(".class","Html.TD",".className",new RegularExpression("^.sectionTop$",false));
		return Property.atList(customerTabContentDiv(),profileRow(Item.PhysicalAddress),p3);
	}
	
	protected List<Property[]> mailingAddressSection() {
		Property[] p3=Property.toPropertyArray(".class","Html.TD",".className",new RegularExpression("^.sectionMid$",false));
		return Property.atList(customerTabContentDiv(),profileRow(Item.MailingAddress),p3);
	}
	
	protected List<Property[]> pointsSection() {		
		return Property.atList(customerTabContentDiv(),profileRow(Item.Points),table());
	}
	
	protected List<Property[]> selectCustomerToKeepSection() {
		Property[] p3=Property.toPropertyArray(".class","Html.TD",".className",new RegularExpression("^.sectionTop$",false));
		return Property.atList(customerTabContentDiv(),profileRow(Item.SelectCustomerToKeep),p3);
	}
	
	
	protected Property[] toMergeRow() {
		return Property.toPropertyArray(".class","Html.TR",".text", new RegularExpression("^# to Merge.*",false));
	}
	
	protected List<Property[]> toMergePointType() {
		Property[] p1= Property.toPropertyArray(".class","Html.SPAN",".text","Point Type");
		Property[] p2= Property.toPropertyArray(".class","Html.INPUT.text",".className","inputwithrubylabel readonly");
		return Property.atList(p1,p2);
	}
	
	protected List<Property[]> toMergeBalance() {
		Property[] p1= Property.toPropertyArray(".class","Html.SPAN",".text","Balance");
		Property[] p2= Property.toPropertyArray(".class","Html.INPUT.text",".className","inputwithrubylabel readonly");
		return Property.atList(p1,p2);
	}
	
	protected List<Property[]> numberToMerge() {
		Property[] p1= Property.toPropertyArray(".class","Html.SPAN",".text","# to Merge");
		Property[] p2= Property.toPropertyArray(".class","Html.INPUT.text",".id",new RegularExpression("PointAllocationView-\\d+.points",false));
		return Property.atList(p1,p2);
	}
	
	protected Property[] homePhone() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("CustomerProfileView-\\d+.primaryContact.homePhone",false));
	}
	
	protected Property[] businessPhone() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("CustomerProfileView-\\d+.primaryContact.businessPhone",false));
	}
	
	protected Property[] mobilePhone() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("CustomerProfileView-\\d+.primaryContact.mobilePhone",false));
	}
	
	protected Property[] fax() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("CustomerProfileView-\\d+.primaryContact.fax",false));
	}
	
	protected Property[] email() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("CustomerProfileView-\\d+.primaryContact.email",false));
	}
	
	protected Property[] phoneContactPrefereceTo() {
		return Property.toPropertyArray(".class","Html.SELECT",".id", new RegularExpression("CustomerProfileView-\\d+.primaryContact.phoneContactPreference",false));
	}
	
	protected Property[] phoneContactPrefereceFrom() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("CustomerProfileView-\\d+.primaryContact.phoneContactPreference:CB_TO_NAME",false));
	}
	
	protected Property[] phoneContactTimeTo() {
		return Property.toPropertyArray(".class","Html.SELECT",".id", new RegularExpression("CustomerProfileView-\\d+.primaryContact.preferredContactTime",false));
	}
	
	protected Property[] phoneContactTimeFrom() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("CustomerProfileView-\\d+.primaryContact.preferredContactTime:CB_TO_NAME",false));
	}
	
	protected Property[] huntSuspentionCertify() {
		return Property.toPropertyArray(".class","Html.SELECT",".id", new RegularExpression("AttributeValuesWrapper-\\d.attr\\[\\d+\\]",false));
	}
	
	protected Property[] huntEligibilityCertifyTo() {
		return Property.toPropertyArray(".class","Html.SELECT",".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[\\d+\\]",false));
	}
	
	protected Property[] huntEligibilityCertifyFrom() {
		return Property.toPropertyArray(".class","Html.Select",".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[\\d+\\]",false));
	}
	
	protected Property[] huntEducationIDTo() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[\\d+\\]",false));
	}
	
	protected Property[] huntEducationIDFrom() {
		return Property.toPropertyArray(".class","Html.TR",".text", new RegularExpression(".+ Hunter Education ID$",false));
	}
	
	protected Property[] address() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("AddressView-\\d+.address",false));
	}
	
	protected Property[] supplementalAddress() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("AddressView-\\d+.supplemental",false));
	}
	
	protected Property[] cityTown() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("AddressView-\\d+.city",false));
	}
	
	protected Property[] proviceStateSelect() {
		return Property.toPropertyArray(".class","Html.SELECT",".id", new RegularExpression("AddressView-\\d+.state",false));
	}
	
	protected Property[] proviceStateReadonly() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("AddressView-\\d+.state:CB_TO_NAME",false));
	}
	
	protected Property[] countySelect() {
		return Property.toPropertyArray(".class","Html.SELECT",".id", new RegularExpression("AddressView-\\d+.county",false));
	}
	
	protected Property[] countyReadonly() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("AddressView-\\d+.county:CB_TO_NAME",false));
	}
	
	protected Property[] postalZIP() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("AddressView-\\d+.zipCode",false));
	}
	
	protected Property[] countrySelect() {
		return Property.toPropertyArray(".class","Html.SELECT",".id", new RegularExpression("AddressView-\\d+.country",false));
	}
	
	protected Property[] countryReadonly() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("AddressView-\\d+.country:CB_TO_NAME",false));
	}
	
	protected Property[] mailingSameAsPhysical() {
		return Property.toPropertyArray(".class","Html.INPUT.checkbox",".id", new RegularExpression("AddressView-\\d+.sameAddress",false));
	}
	
	protected Property[] middleName() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("CustomerProfileView-\\d+.primaryContact.middleName",false));
	}
	
	protected Property[] residencyOverrideSelect() {
		return Property.toPropertyArray(".class","Html.SELECT",".id", new RegularExpression("CustomerProfileView-\\d+.overrideResidencyType",false));
	}
	
	protected Property[] residencyOverrideReadonly() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("CustomerProfileView-\\d+.overrideResidencyType:CB_TO_NAME",false));
	}
	
	protected Property[] overrideEffectiveTo() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id", new RegularExpression("CustomerProfileView-\\d+.overrideResidencyTypeEffectiveTo(_ForDisplay)?",false));
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text", "Select Customer to Keep");
//				&&(browser.checkHtmlObjectExists(".class", "Html.TD", ".text", "QA")));
		}
	
	public void selectLeftCustomertoKeep(){
		browser.clickGuiObject(".id","USER_SELECTION", 0);
	}
	
	public void selectRightCustomertoKeep(){
		browser.selectRadioButton(".id","USER_SELECTION", 1);
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void selectCustomerToKeep(int selection) {
    	browser.selectRadioButton(".id","USER_SELECTION", selection);
    }
    
    public String getFirstName(int selection) {
    	RegularExpression pattern=new RegularExpression("HFCustomerProfileView-\\d+\\.primaryContact\\.firstName",false);
    	return browser.getTextFieldValue(".name",pattern,selection);
    }
    
    public String getLastName(int selection) {
    	RegularExpression pattern=new RegularExpression("HFCustomerProfileView-\\d+\\.primaryContact\\.lastName",false);
    	return browser.getTextFieldValue(".name",pattern,selection);
    }
    
    public String getDateOfBirth(int selection) {
    	RegularExpression pattern=new RegularExpression("HFCustomerProfileView-\\d+\\.dateOfBirth:DATE2",false);
    	return browser.getTextFieldValue(".name",pattern,selection);
    }
    
    public void clickNext() {
    	browser.clickGuiObject(".class","Html.A",".text","Next");
    }
    
    public void clickMerge() {
    	browser.clickGuiObject(".class","Html.A",".text","Merge");
    }
    
    public boolean isMergeButtonExists() {
    	return browser.checkHtmlObjectExists(".class","Html.A",".text","Merge");
    }

	public String getErrorMsg() {
		return browser.getObjectText(".class","Html.TABLE",".id","_page_messages");
	}
		
	public int getCustomerToKeepIndex() {
		IHtmlObject[] tables=browser.getHtmlObject(selectCustomerToKeepSection());
		IHtmlObject[] objs=null;
		Property[] label=Property.toPropertyArray(".class","Html.LABEL",".text","Customer to Keep");
		int index=-1;
		for(int i=0;i<tables.length;i++) {			
			objs=browser.getHtmlObject(label,tables[i]);
			if(objs.length>0) {
				index=i;
				break;
			}
		}
		
		Browser.unregister(tables,objs);
		
		if(index>1 || index<0)
			throw new ItemNotFoundException("Failed to get CustomerToKeep index.");
		return index;
		
	}
	
	public List<String> mergePhoneEmail() {
		IHtmlObject[] sections=browser.getHtmlObject(this.phoneEmailSection());
		int toIndex=getCustomerToKeepIndex();
		int fromIndex=1-toIndex;
		List<String> merged=new ArrayList<String>();
		
		String fromHP=browser.getTextFieldValue(homePhone(), sections[fromIndex]);
		if(StringUtil.notEmpty(fromHP)) {
			merged.add("homephone="+fromHP);
			browser.setTextField(this.homePhone(),fromHP,true,0,sections[toIndex]);
		}
		
		String fromBP=browser.getTextFieldValue(businessPhone(), sections[fromIndex]);
		if(StringUtil.notEmpty(fromBP)) {
			browser.setTextField(this.businessPhone(),fromBP,true,0,sections[toIndex]);
			merged.add("businessphone="+fromBP);
		}
		String fromMP=browser.getTextFieldValue(mobilePhone(), sections[fromIndex]);
		if(StringUtil.notEmpty(fromMP)) {
			browser.setTextField(this.mobilePhone(),fromMP,true,0,sections[toIndex]);
			merged.add("mobilephone="+fromMP);
		}
		
		String fromFAX=browser.getTextFieldValue(fax(), sections[fromIndex]);
		if(StringUtil.notEmpty(fromFAX)) {
			browser.setTextField(this.fax(),fromFAX,true,0,sections[toIndex]);
			merged.add("fax="+fromFAX);
		}
		
		String fromEMAIL=browser.getTextFieldValue(email(), sections[fromIndex]);
		if(StringUtil.notEmpty(fromEMAIL)) {
			browser.setTextField(this.email(),fromEMAIL,true,0,sections[toIndex]);
			merged.add("email="+fromEMAIL);
		}
		
		String fromPhoneContactPref=browser.getTextFieldValue(phoneContactPrefereceFrom(), sections[fromIndex]);
		if(StringUtil.notEmpty(fromPhoneContactPref)) {
			browser.selectDropdownList(this.phoneContactPrefereceTo(), fromPhoneContactPref,true,0,sections[toIndex]);
			merged.add("phoneContactPref="+fromPhoneContactPref);
		}
		
		String fromPrefContactTime=browser.getTextFieldValue(this.phoneContactTimeFrom(), sections[fromIndex]);
		if(StringUtil.notEmpty(fromPrefContactTime)) {
			browser.selectDropdownList(this.phoneContactTimeTo(), fromPrefContactTime,true,0,sections[toIndex]);
			merged.add("ContactTimePref="+fromPrefContactTime);
		}
		
		Browser.unregister(sections);
		return merged;
	}
	
	public List<String> mergeNameDOB() {
		IHtmlObject[] sections=browser.getHtmlObject(nameDOBSection());
		int toIndex=getCustomerToKeepIndex();
		int fromIndex=1-toIndex;
		List<String> merged=new ArrayList<String>();
		
		String fromMN=browser.getTextFieldValue(middleName(), sections[fromIndex]);
		if(StringUtil.notEmpty(fromMN)) {
			merged.add("middleName="+fromMN);
			browser.setTextField(this.middleName(),fromMN,true,0,sections[toIndex]);
		}
		
		String residenceOverride=browser.getTextFieldValue(residencyOverrideReadonly(), sections[fromIndex]);
		if(StringUtil.notEmpty(residenceOverride)) {
			browser.selectDropdownList(this.residencyOverrideSelect(), residenceOverride,true,0,sections[toIndex]);
			merged.add("ResidenceOverride="+residenceOverride);
		}
		
		String overrideEffectiveTo=browser.getTextFieldValue(this.overrideEffectiveTo(), sections[fromIndex]);
		if(StringUtil.notEmpty(overrideEffectiveTo)) {
			browser.setTextField(this.overrideEffectiveTo(), overrideEffectiveTo,true,0,sections[toIndex]);
			merged.add("OverrideEffectiveTo="+overrideEffectiveTo);
		}
		
		Browser.unregister(sections);
		return merged;
	}
	
	public List<String> mergeHunterEligibility() {
		IHtmlObject[] sections=browser.getHtmlObject(this.hunterEligibilitySection());
		int toIndex=getCustomerToKeepIndex();
		int fromIndex=1-toIndex;
		List<String> merged=new ArrayList<String>();
		
		IHtmlObject[] fromCerts=browser.getHtmlObject(this.huntEligibilityCertifyFrom(), sections[fromIndex]);
		IHtmlObject[] toCerts=browser.getDropdownList(this.huntEligibilityCertifyTo(), sections[toIndex]);
		
		for(int i=0;i<fromCerts.length-1;i++) {
			String value=((ISelect)fromCerts[i]).getSelectedText();
			if(StringUtil.notEmpty(value)) {
				((ISelect)toCerts[i]).select(value);
				merged.add("Certify#"+i+"="+value);
			}
			
		}
		
		IHtmlObject[] objs=browser.getHtmlObject(this.huntEducationIDFrom(), sections[fromIndex]);
		String id=objs[0].text().replaceAll("Hunter Education ID", "").trim();//.getAttributeValue(".text");
		if(StringUtil.notEmpty(id)) {
			merged.add("huntEdID="+id);
			browser.setTextField(this.huntEducationIDTo(),id,true,0,sections[toIndex]);
		}
		Browser.unregister(sections,fromCerts,toCerts,objs);	
		
		return merged;
	}
	
	public List<String> mergePhysicalAddress() {
		IHtmlObject[] sections=browser.getHtmlObject(this.physicalAddressSection());
		int toIndex=getCustomerToKeepIndex();
		int fromIndex=1-toIndex;
		List<String> merged=new ArrayList<String>();
		
		String fromaddress=browser.getTextFieldValue(address(), sections[fromIndex]);
		String fromzip=browser.getTextFieldValue(postalZIP(), sections[fromIndex]);
		
		String toaddress=browser.getTextFieldValue(address(), sections[toIndex]);
		String tozip=browser.getTextFieldValue(postalZIP(), sections[toIndex]);
		
		if(!(fromzip.equalsIgnoreCase(tozip) && fromaddress.equalsIgnoreCase(toaddress))) {					
			//merge
			
			String country=browser.getTextFieldValue(this.countryReadonly(), sections[fromIndex]);
			if(StringUtil.notEmpty(country)) {
				browser.selectDropdownList(this.countrySelect(), country,true,0,sections[toIndex]);
				merged.add("physical.country="+country);
			}
			
			String province=browser.getTextFieldValue(this.proviceStateReadonly(), sections[fromIndex]);
			if(StringUtil.notEmpty(province)) {
				browser.selectDropdownList(this.proviceStateSelect(), province,true,0,sections[toIndex]);
				merged.add("physical.province="+province);
			}
			
			
			if(StringUtil.notEmpty(fromzip)) {
				merged.add("physical.zip="+fromzip);
				browser.setTextField(this.postalZIP(),fromzip,true,0,sections[toIndex]);
			}
			
			if(StringUtil.notEmpty(fromaddress)) {
				merged.add("physical.address="+fromaddress);
				browser.setTextField(this.address(),fromaddress,true,0,sections[toIndex]);
			}
			
			String supplementalAddress=browser.getTextFieldValue(this.supplementalAddress(), sections[fromIndex]);
			if(StringUtil.notEmpty(supplementalAddress)) {
				merged.add("physical.supplementalAddress="+supplementalAddress);
				browser.setTextField(this.supplementalAddress(),supplementalAddress,true,0,sections[toIndex]);
			}
			
			String cityTown=browser.getTextFieldValue(this.cityTown(), sections[fromIndex]);
			if(StringUtil.notEmpty(cityTown)) {
				merged.add("physical.cityTown="+cityTown);
				browser.setTextField(this.cityTown(),cityTown,true,0,sections[toIndex]);
			}
			
			String county=browser.getTextFieldValue(this.countyReadonly(), sections[fromIndex]);
			if(StringUtil.notEmpty(county)) {
				browser.selectDropdownList(this.countySelect(), county,true,0,sections[toIndex]);
				merged.add("physical.county="+county);
			}
		}
		
		Browser.unregister(sections);
		return merged;
	}
	
	public String[] getPhysicalAddressToCompare(int fromIndex) {
		IHtmlObject[] sections_phy=browser.getHtmlObject(this.physicalAddressSection());
		
		String phyaddress=browser.getTextFieldValue(address(), sections_phy[fromIndex]);
		String phyzip=browser.getTextFieldValue(postalZIP(), sections_phy[fromIndex]);
		
		Browser.unregister(sections_phy);
		
		return new String[]{phyaddress,phyzip};
	}
	
	public List<String> mergeMailingAddress() {
		IHtmlObject[] sections=browser.getHtmlObject(this.mailingAddressSection());
		int toIndex=getCustomerToKeepIndex();
		int fromIndex=1-toIndex;
		List<String> merged=new ArrayList<String>();
		
		String fromaddress=browser.getTextFieldValue(address(), sections[fromIndex]);
		String fromzip=browser.getTextFieldValue(postalZIP(), sections[fromIndex]);
		
		String[] physicals=this.getPhysicalAddressToCompare(fromIndex);
		
		if(physicals[0].equalsIgnoreCase(fromaddress) && physicals[1].equalsIgnoreCase(fromzip)) {
			browser.selectCheckBox(this.mailingSameAsPhysical(),0,sections[toIndex]);
			merged.add("mailingSameAsPhysical=true");
		} else {
			browser.unSelectCheckBox(this.mailingSameAsPhysical(),0,sections[toIndex]);
			merged.add("mailingSameAsPhysical=true");
		
			String toaddress=browser.getTextFieldValue(address(), sections[toIndex]);
			String tozip=browser.getTextFieldValue(postalZIP(), sections[toIndex]);
			
			if(!(fromzip.equalsIgnoreCase(tozip) && fromaddress.equalsIgnoreCase(toaddress))) {
				//merge
				String country=browser.getTextFieldValue(this.countryReadonly(), sections[fromIndex]);
				if(StringUtil.notEmpty(country)) {
					browser.selectDropdownList(this.countrySelect(), country,true,0,sections[toIndex]);
					merged.add("physical.country="+country);
				}
				
				String province=browser.getTextFieldValue(this.proviceStateReadonly(), sections[fromIndex]);
				if(StringUtil.notEmpty(province)) {
					browser.selectDropdownList(this.proviceStateSelect(), province,true,0,sections[toIndex]);
					merged.add("physical.province="+province);
				}
				
				
				if(StringUtil.notEmpty(fromzip)) {
					merged.add("physical.zip="+fromzip);
					browser.setTextField(this.postalZIP(),fromzip,true,0,sections[toIndex]);
				}
				
				if(StringUtil.notEmpty(fromaddress)) {
					merged.add("physical.address="+fromaddress);
					browser.setTextField(this.address(),fromaddress,true,0,sections[toIndex]);
				}
				
				String supplementalAddress=browser.getTextFieldValue(this.supplementalAddress(), sections[fromIndex]);
				if(StringUtil.notEmpty(supplementalAddress)) {
					merged.add("physical.supplementalAddress="+supplementalAddress);
					browser.setTextField(this.supplementalAddress(),supplementalAddress,true,0,sections[toIndex]);
				}
				
				String cityTown=browser.getTextFieldValue(this.cityTown(), sections[fromIndex]);
				if(StringUtil.notEmpty(cityTown)) {
					merged.add("physical.cityTown="+cityTown);
					browser.setTextField(this.cityTown(),cityTown,true,0,sections[toIndex]);
				}
				
				String county=browser.getTextFieldValue(this.countyReadonly(), sections[fromIndex]);
				if(StringUtil.notEmpty(county)) {
					browser.selectDropdownList(this.countySelect(), county,true,0,sections[toIndex]);
					merged.add("physical.county="+county);
				}
			}
		}
		
		Browser.unregister(sections);
		return merged;
	}
	
	/**
	 * get the left and right customers' identifiers: type, status and identifier number
	 * @return
	 */
	public List<CustomerIdentifier[]> getCustomerIdentifiers() {
		IHtmlObject[] objs=getIdentifierTable();
		List<CustomerIdentifier[]> list=new ArrayList<CustomerIdentifier[]>();
		for(int j=0;j<2;j++) {
		
			IHtmlObject table=objs[j];
//			IHtmlObject[] types=browser.getTextField(Property.toPropertyArray(".id",identifier_type_pattern),table);
			IHtmlObject[] types=browser.getTextField(Property.toPropertyArray(".name",new RegularExpression("CustomerIdentifierView-\\d+\\.identifier\\.identifierType:CB_TO_NAME",false)),table);
			IHtmlObject[] status=browser.getTextField(Property.toPropertyArray(".name",new RegularExpression("CustomerIdentifierView-\\d+.status:CB_TO_NAME",false)),table);
			IHtmlObject[] numbers=browser.getTextField(Property.toPropertyArray(".name",new RegularExpression("CustomerIdentifierView-\\d+.identifier.identifierNumber",false)),table);
					
			int size=types.length;
			CustomerIdentifier[] identifers=new CustomerIdentifier[types.length];
			for(int i=0;i<size;i++) {
				identifers[i]=new CustomerIdentifier();
				identifers[i].identifierType=types[i].getAttributeValue(".value");
				identifers[i].status=status[i].getAttributeValue(".value");
				identifers[i].identifierNum=numbers[i].getAttributeValue(".value");			
			}
			
			Browser.unregister(types,status,numbers);
			list.add(identifers);
		
		}
		
		
		return list;
	
	}
	
	public List<CustomerIdentifier> mergeIdentifiers(CustomerIdentifier[] to, CustomerIdentifier[] from) {
		IHtmlObject[] mergeCheckboxes=getMergeCheckBoxes();
		
		List<CustomerIdentifier> list=new ArrayList<CustomerIdentifier>();
		for(int i=0;i<from.length;i++) {
			CustomerIdentifier aFrom=from[i];
			if(!(aFrom.status.equalsIgnoreCase("Active") || aFrom.status.equalsIgnoreCase("Verified"))) {
				continue;
			}
			
			boolean found=false;
			int j=0;
			for(;j<to.length && !found;j++) {
				CustomerIdentifier aTo=to[j];
				found=aTo.identifierType.equalsIgnoreCase(aFrom.identifierType);
			}
			
			if(!found) {
				mergeCheckboxes[i].click();
				list.add(aFrom);
			}
		}
		
		Browser.unregister(mergeCheckboxes);
		
		return list;
	}
	
	public List<CustomerIdentifier> mergeAllCustomerIdentifiers(int fromIndex) {
		IHtmlObject[] objs=getIdentifierTable();
		
		IHtmlObject table=objs[fromIndex];		
		
		IHtmlObject[] types=browser.getTextField(Property.toPropertyArray(".name",new RegularExpression("CustomerIdentifierView-\\d+\\.identifier\\.identifierType:CB_TO_NAME",false)),table);
		IHtmlObject[] status=browser.getTextField(Property.toPropertyArray(".name",new RegularExpression("CustomerIdentifierView-\\d+.status:CB_TO_NAME",false)),table);
		IHtmlObject[] numbers=browser.getTextField(Property.toPropertyArray(".name",new RegularExpression("CustomerIdentifierView-\\d+.identifier.identifierNumber",false)),table);
		IHtmlObject[] mergeCheckboxes=getMergeCheckBoxes();
				
		int size=types.length;
		List<CustomerIdentifier> mergedIdentifers=new ArrayList<CustomerIdentifier>();
		for(int i=0;i<size;i++) {
			String aStatus=status[i].getAttributeValue(".value");
			if(!(aStatus.equalsIgnoreCase("Active") || aStatus.equalsIgnoreCase("Verified"))) {
				continue;
			} else {
				CustomerIdentifier identifer=new CustomerIdentifier();
				identifer.identifierType=types[i].getAttributeValue(".value");
				identifer.status=aStatus;
				identifer.identifierNum=numbers[i].getAttributeValue(".value");	
				mergeCheckboxes[i].click();
				mergedIdentifers.add(identifer);
			}
		}
		
		Browser.unregister(types,status,numbers);
		return mergedIdentifers;
	}
	
	public Map<String, String> getToMergePointsBalance() {
		Map<String, String> points=new HashMap<String,String>();

		IHtmlObject[] pointTypes=browser.getHtmlObject(toMergePointType());
		IHtmlObject[] balances=browser.getHtmlObject(toMergeBalance());
		
		int size=pointTypes.length;
		
		for(int i=0;i<size;i++) {
			String pointType=pointTypes[i].getAttributeValue(".value");
			String balance=balances[i].getAttributeValue(".value");
			points.put(pointType, balance);
		}
		
		return null;
	}
	
	public Map<String, String> MergeAllePointsBalance(int fromIndex) {
		IHtmlObject[] tables=getMergePointsTable();
		
		Map<String, String> points=new HashMap<String,String>();

		IHtmlObject[] pointTypes=browser.getHtmlObject(toMergePointType(), tables[fromIndex]);
		IHtmlObject[] balances=browser.getHtmlObject(toMergeBalance(), tables[fromIndex]);
		IHtmlObject[] toMergeField=browser.getHtmlObject(numberToMerge(), tables[fromIndex]);
		
		int size=pointTypes.length;
		
		for(int i=0;i<size;i++) {
			String pointType=pointTypes[i].getAttributeValue(".value");
			String balance=balances[i].getAttributeValue(".value");
			points.put(pointType, balance);
			((IText)toMergeField[i]).setText(balance);
		}
		
		Browser.unregister(tables,pointTypes,balances,toMergeField);
		
		return points;
	}
	
	public boolean isPointsTableExist() {
		boolean exists= browser.checkHtmlObjectExists(this.pointsSection());
		
		return exists;
	}
	
	public IHtmlObject[] getMergePointsTable() {
		return browser.getHtmlObject(pointsSection());
	}
	
	public IHtmlObject[] getIdentifierTable() {
		return browser.getHtmlObject(this.identifierSection());
	}
	
	public boolean isIdentifiersTableExist() {
		boolean exists= browser.checkHtmlObjectExists(this.identifierSection());
		
		return exists;
	}
	
	public IHtmlObject[] getMergeCheckBoxes() {
		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".id","content_CustomerMgrTabs");
		Property[] p2=Property.toPropertyArray(".class","Html.TR",".text",new RegularExpression("^Identifiers.*",false));
		Property[] p3=Property.toPropertyArray(".class","Html.TABLE");
		Property[] p4=Property.toPropertyArray(".class","Html.INPUT.checkbox");
		return browser.getHtmlObject(Property.atList(p1,p2,p3,p4));
	}
	
	public void setFName(String name)
	{
		browser.setTextField(".id", new RegularExpression("HFCustomerProfileView-\\d+\\.primaryContact.firstName",false), name, 0);
	}
	
	public void setLName(String name)
	{
		browser.setTextField(".id", new RegularExpression("HFCustomerProfileView-\\d+\\.primaryContact.lastName",false), name, 0);
	}
	
	public boolean isIdentifierChecked(String identifierValue)
	{
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text");
		//HtmlObject
		
		IHtmlObject tr = null;
		
		for(IHtmlObject o: objs)
		{
			String value = o.getProperty("value");
			if(value.equalsIgnoreCase(identifierValue))
			{
				IHtmlObject input = o;
				boolean isFinished = false;
				do{
					tr = (IHtmlObject)input.getParent();
					input = tr;
									
					if(tr.tag().equalsIgnoreCase("TR"))
					{
						isFinished = true;
					}
					
				}while(!isFinished);
				
				//found the first parent TR object;
				break;
			}
		}
		
				
		IHtmlObject[] checkboxes = browser.getHtmlObject(".class", "Html.INPUT.checkbox",tr);
		if(checkboxes.length<1)
		{
			throw new ErrorOnPageException("Cannot find identifiers checkbox");
		}
		ICheckBox checkbox = (ICheckBox)checkboxes[0];
		boolean isSelected =checkbox.isSelected();
		
		Browser.unregister(objs);
		return isSelected;
	}
	
	/*This method can be only used when you select left part of customer to keep. That means right part is what you selected to be merged(status will change to merged after merge).*/
	public void fillPointsToMerge(String pointType, String amount)
	{
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text");
		//HtmlObject
		
		IHtmlObject tr = null;
		IHtmlObject input = null;
		for(IHtmlObject o: objs)
		{
			String value = o.getProperty("value");
			if(value.equalsIgnoreCase(pointType))
			{
				input = o;//search to the last one of INPUT(on page right...)
			}
		}
		
		boolean isFinished = false;
		do{
			tr = (IHtmlObject)input.getParent();
			input = tr;
							
			if(tr.tag().equalsIgnoreCase("TR"))
			{
				//found the first parent TR object of INPUT;
				isFinished = true;
			}
			
		}while(!isFinished);
		
		
		
		browser.setTextField(".id", new RegularExpression("PointAllocationView-\\d+\\.points",false), amount,tr);
		Browser.unregister(objs);
		
	}
	
	private IHtmlObject[] getMergeIdentifiersTableRow() {
		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".id","content_CustomerMgrTabs");
		Property[] p2=Property.toPropertyArray(".class","Html.TR",".text",new RegularExpression("^Identifiers.*",false));
		Property[] p3=Property.toPropertyArray(".class","Html.TABLE", ".text", new RegularExpression("Merge.*",false));
		Property[] p4=Property.toPropertyArray(".class","Html.tr");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2, p3, p4));
		if (objs.length < 1) {
			throw new ErrorOnPageException("Can't find merge identifiers table row");
		}
		return objs;
	}
	
	private boolean isIdentifierTypeExist(String identType, IHtmlObject top) {
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.INPUT.text", ".id", identifier_type_pattern, ".value", identType), top);
	}
	
	private void selectMergeIdentifierCheckBox(IHtmlObject top) {
		browser.selectCheckBox(Property.toPropertyArray(".class", "Html.INPUT.checkbox"), 0, top);
	}
	
	public void selectIdentifiersToMerge(CustomerIdentifier... mergedIdents) {
		IHtmlObject[] objs = this.getMergeIdentifiersTableRow();
		logger.info("Select Identifiers to merge...");
		for(CustomerIdentifier ident : mergedIdents) {
			for (int i = 0; i < objs.length; i++) {
				if (this.isIdentifierTypeExist(ident.identifierType, objs[i])) {
					this.selectMergeIdentifierCheckBox(objs[i]);
					ajax.waitLoading();
					break;
				}
			}
		}
		Browser.unregister(objs);
	}
}
