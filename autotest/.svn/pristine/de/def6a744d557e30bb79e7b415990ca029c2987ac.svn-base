package com.activenetwork.qa.awo.pages.orms.common.ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class OrmsTourParticipantsWidget extends DialogWidget{
	
	 private static OrmsTourParticipantsWidget _instance=null;
	 
	 private OrmsTourParticipantsWidget(){};
	 
	 public static OrmsTourParticipantsWidget getInstance(){
		 if(null == _instance){
			 _instance = new OrmsTourParticipantsWidget();
		 }
		 
		 return _instance;
	 }
	 
	 public boolean exists(){
		   boolean flag1=super.exists();
		   boolean flag2=browser.checkHtmlObjectExists(".class", "Html.DIV",".text","Tour Participantsclose");
	       return flag1 && flag2;
	 }
	 
		/**
		 * set tour participant attributes
		 * @param map - key is TPA name, value is TPA value
		 */
	 public void setTourParticipantAttributesForPerInventory(String tourName,Map<String, String> map) {
		 IHtmlObject[] tourObjs =  browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Tour Inventory(\\s)?Tour(\\s)?" + tourName + ".*",false));
		 
		 if(tourObjs.length<1){
			 throw new ItemNotFoundException("Did not found tour inventory info. Tour Name = " + tourName);
		 }

		 IHtmlObject trObjs[] = browser.getHtmlObject(".class", "Html.TR", ".id", new RegularExpression("attrSectionPerInventory", false),tourObjs[tourObjs.length - 1]);
		 if(trObjs.length < 1) {
			 throw new ItemNotFoundException("Can't find Tour Participant Attribute section object.");
		 }

		 if(trObjs.length != map.size()) {
			 throw new ErrorOnPageException("The actual Tour Participant Attributes length doesn't match your parameter. Expected is: " + map.size() + ", but actual is: " + trObjs.length);
		 }
		 Set<Map.Entry<String, String>> set = map.entrySet();
		 for(Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
			 Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
			 setTourParticipantAttributeForPerInventory(entry.getKey(), entry.getValue(),tourObjs[tourObjs.length-1]);
		 }

	 }
	 
	 /**
	  * Set tour participant attribute info for per inventory
	  * @param key
	  * @param value
	  * @param tourObject
	  */
		public void setTourParticipantAttributeForPerInventory(String key, String value, IHtmlObject tourObject) {
//			HtmlObject trObjs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".id", new RegularExpression("attrSectionPerInventory", false), ".text", new RegularExpression("^" + key, false)),tourObject);
			//modified for QA3(orms3.02.03) regression test
			IHtmlObject trObjs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".id", new RegularExpression("(attrSectionPerInventory)", false), ".text", new RegularExpression("^" + key, false)),tourObject);
			if(trObjs.length < 1) {
				trObjs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.Table", ".id", new RegularExpression("(attrFormViewTag\\d+PerInventory)", false), ".text", new RegularExpression("^" + key, false)),tourObject);
			}
			if(trObjs.length < 1){
				throw new ItemNotFoundException("Can't find Tour Participant Attribute section object by key - " + key);
			}
			String attributeID = "";
			attributeID = convertAttribute(key);			
			switch(Integer.parseInt(attributeID)) {
			case 2631:
			case 2632:
			case 2633:
			case 2634:
			case 2635:
			case 2636:
				//TODO
				break;
			case 2000007:
				browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]", false), value, true, 0, trObjs[trObjs.length-1]);
				break;
			case 2637:
				browser.setTextArea(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]", false), value, true, 0, trObjs[trObjs.length-1]);
				break;
			case 300000003:
				this.unSelectAllPerInventoryTPACheckBoxes(attributeID, trObjs[trObjs.length-1]);
				this.selectPerInventoryTPACheckBoxes(attributeID, trObjs[trObjs.length-1], value);
				break;
				default:
					//TODO
			}
			Browser.unregister(trObjs);
		}
		
		public void addTourParticipantAttributesForPerTicketWhenChangingTime(String tourName, String toDate, String toTime, Map<String, List<Map<String, String>>> map, boolean isAdded) {
			setOrAddTourParticipantAttributesForPerTicket(tourName, toDate, toTime, map, isAdded);
		}
		
		public void addTourParticipantAttributesForPerTicket(String tourName, Map<String, List<Map<String, String>>> map) {
			setOrAddTourParticipantAttributesForPerTicket(tourName, null, null, map, true);
		}
		
		/**
		 * set TPA info for 'Per Ticket'
		 * @param tourName
		 * @param map
		 */
		public void setTourParticipantAttributesForPerTicket(String tourName, Map<String, List<Map<String, String>>> map) {
			setOrAddTourParticipantAttributesForPerTicket(tourName, null, null, map, false);
		}
		
		/**
		 * set TPA info for 'Per Ticket'
		 * @param tourName
		 * @param map
		 */
		private void setOrAddTourParticipantAttributesForPerTicket(String tourName, String toDate, String toTime, Map<String, List<Map<String, String>>> map, boolean isAdded) {
			String marker = tourName;
			if(toDate != null) {
				marker += ".*Tour Date(\\s)?" + DateFunctions.formatDate(toDate, "EEE MMM d yyyy");
			}
			if(toTime != null) {
				marker += "(\\s)?Tour Time(\\s)?" + toTime; 
			}
			IHtmlObject[] tourObjs =  browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Tour Inventory(\\s)?Tour(\\s)?" + marker + ".*",false));

			if(tourObjs.length<1){
				throw new ItemNotFoundException("Did not found tour inventory info. Tour Name = " + tourName);
			}
			
			String ticketType = "";
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			IHtmlObject trObjs[] = null;
			IHtmlObject attrObjs[] = null;
			Set<Map.Entry<String, List<Map<String, String>>>> set = map.entrySet();
			for(Iterator<Map.Entry<String, List<Map<String, String>>>> it = set.iterator(); it.hasNext();) {
				Map.Entry<String, List<Map<String, String>>> entry = it.next();
				ticketType = entry.getKey();
				if(ticketType.contains("(") && ticketType.contains(")")) {
					ticketType = ticketType.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
				}
				list = entry.getValue();
				attrObjs = browser.getHtmlObject(".class", "Html.Table", ".id", new RegularExpression("attrTableTag\\d+PerTicket", false), tourObjs[tourObjs.length - 1]);
				trObjs = browser.getHtmlObject(".class", "Html.TR", ".id", new RegularExpression("currentSubsectionRow\\d+" + ticketType, false),attrObjs[attrObjs.length - 1]);
				if(trObjs.length < 1) {
					throw new ItemNotFoundException("Can't find any TPA TR object(s) under ticket type - " + ticketType);
				}
//				ArrayList<HtmlObject> trObjs1 = new ArrayList<HtmlObject>();
//				for(int p=0;p<trObjs.length;p++){//on QA3, a hidden objs for TR
//					if(trObjs[p].exists()){
//						trObjs1.add(trObjs[p]);
//					}
//				}
				int countOnPage = trObjs.length - 2;//the 1st and last rows are NOT TPAs
				int expectedCount = list.size();
				IHtmlObject usefulTR[] = new IHtmlObject[expectedCount];
				if(!isAdded) {//totally set all TPA fields, no matter the field has value or not
					if( countOnPage != expectedCount) {
						throw new ErrorOnPageException("There should be " + expectedCount + " 'Per Ticket' TPAs for these tickets, but actual is " + countOnPage);
					}
					for(int k = 0; k < countOnPage; k ++) {
						usefulTR[k] = trObjs[k + 1];
					}
				} else {//only set blank TPA fields
					for(int j = countOnPage; j > countOnPage - expectedCount; j--) {
						usefulTR[countOnPage - j] = trObjs[j];		
					}
				}
				for(int i = 0; i < list.size(); i ++) {
					setTPAsForEachTicket(usefulTR[i], ticketType, list.get(i));
				}
			}
			Browser.unregister(trObjs);
			Browser.unregister(tourObjs);
		}
		
		public void setTPAsForEachTicket(IHtmlObject trTopObj, String ticketType, Map<String, String> map) {
			String attributeID = "";
			String key = "";
			Set<Map.Entry<String, String>> set = map.entrySet();
			for(Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
				key = entry.getKey();				
				attributeID = convertAttribute(key);
				
				//use corresponding method to set TPA values
				switch(Integer.parseInt(attributeID)) {
				case 2631:
				case 2632:
				case 2636:
				case 2633:
				case 300000001:
					if(attributeID.equals("2631"))
						attributeID = "2631|2000003";
					if(attributeID.equals("2636"))
						attributeID = "2636|2000005";
					if (attributeID.equals("2633"))
						attributeID = "2633|300000006";
					if (attributeID.equals("2632"))
						attributeID = "2632|2000001";
					commonSetTPATextField(trTopObj,attributeID, entry.getValue());
					break;
//				case 2633:
				case 2634:
				case 2635:
				case 	300000002:
					commonSelectTPADropdownList(trTopObj,attributeID, entry.getValue());
					break;
				case 2637:
					//TODO								
				default:
					//TODO
				}
			}
		}

		private void commonSetTPATextField(IHtmlObject trTopObj, String attributeID, String text) {
			//IMPORTANT: trTopObj can't be unregistered
			IHtmlObject[] objs = browser.getTextField(Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[" + attributeID + "\\]", false)), trTopObj);
			if(objs.length<1){
				throw new ItemNotFoundException("Did not found tpa object. attributeID = " + attributeID);
			}
			browser.setTextField(".id", objs[0].id(), text);
			Browser.unregister(objs);
		}

		private void commonSelectTPADropdownList(IHtmlObject trTopObj, String attributeID, String value) {
			browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[" + attributeID + "\\].*", false), value, true, trTopObj);			
//			Note[Jane]:trTopObj could not be registered
//			Browser.unregister(trTopObj);
		}

	public void removeTPAInfoOfPerTicket(Map<String, List<Map<String, String>>> map) {
		removeTPAInfoOfPerTicket(map, false);
	}
	
	public void removeTPAInfoOfPerTicket(Map<String, List<Map<String, String>>> map, boolean isChangeTime) {
		String key = "";
		String attributeNameOnPage = "";
		String attributeValueOnPage = "";
		List<Map<String, String>> tpaList = new ArrayList<Map<String,String>>();
		Map<String, String> tpaMap = new HashMap<String, String>();
		IHtmlObject tourTableObjs[] = null;
		IHtmlObject trObjs[] = null;
		IHtmlObject tdObjs[] = null;
		boolean tpaCompareFlag = true;
		Property p[] = new Property[1];
		p[0] = new Property(".id", new RegularExpression("^AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\](|\\:DATE\\d+\\_ForDisplay$)",false));
		
		Set<Map.Entry<String, List<Map<String, String>>>> set = map.entrySet();
		for(Iterator<Map.Entry<String, List<Map<String, String>>>> it = set.iterator(); it.hasNext();) {//Tour loop
			Map.Entry<String, List<Map<String, String>>> entry = (Map.Entry<String, List<Map<String, String>>>)it.next();
			key = entry.getKey();
			tpaList = entry.getValue();
			tourTableObjs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Tour Inventory(\\s)?Tour(\\s)?" + key + ".*",false));
			
			if(tourTableObjs.length < 1) {
				throw new ItemNotFoundException("Can't find tour - " + key);
			}
			
			//get all TPA tr objects
//			trObjs = browser.getHtmlObject(".class", "Html.TR", ".id", new RegularExpression("^currentSubsectionRow\\d+", false), tourTableObjs[tourTableObjs.length - (isChangeTime?3:1)]);
			IHtmlObject[] perTicketObjs = browser.getHtmlObject(".id", "attrSectionPerTicket", tourTableObjs[tourTableObjs.length - (isChangeTime?3:1)]);
			if(perTicketObjs.length < 1)
				throw new ItemNotFoundException("Can't find per ticket Object for tour - " + key);
			
			trObjs = browser.getHtmlObject(".class", "Html.TR", ".id", new RegularExpression("^currentSubsectionRow\\d+", false), perTicketObjs[0]);
			System.out.println(tourTableObjs.length);
			if(trObjs.length < 1) {
				throw new ItemNotFoundException("Can't find any TPA rows.");
			}
			
			for(int j = 0; j < tpaList.size(); j ++) {//TPA list loop
				tpaMap = tpaList.get(j);
				
				for(int k = 0; k < trObjs.length; k++) {
					if(!browser.checkHtmlObjectExists(p, trObjs[k])) {
						continue;
					}
					tpaCompareFlag = true;
					tdObjs = browser.getHtmlObject(p, trObjs[k]);
					for(int m = 0; m < tdObjs.length; m++) {
						if(tdObjs[m].getProperty(".type").equals("hidden")) {//removing hidden elements
							continue;
						}
						attributeNameOnPage = convertAttribute(tdObjs[m].getProperty(".id").split("\\[")[1].split("\\]")[0]);
						attributeValueOnPage = tdObjs[m].getProperty(".value");
						if(attributeNameOnPage.equals("Date of Birth")) {
							if((attributeValueOnPage.length() < 1 && tpaMap.get(attributeNameOnPage).length() > 0) || DateFunctions.compareDates(attributeValueOnPage, tpaMap.get(attributeNameOnPage)) != 0) {
								tpaCompareFlag &= false;
							}
						} else {
							if(!tpaMap.get(attributeNameOnPage).equals(attributeValueOnPage)) {
								tpaCompareFlag &= false;
							}
						}
					}
					
					if(tpaCompareFlag) {
						browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true, 0, trObjs[k]);
						ajax.waitLoading();
						this.waitLoading();
						break;
					}
				
				}
			}
		}
		
		Browser.unregister(tdObjs);
		Browser.unregister(trObjs);
		Browser.unregister(tourTableObjs);
	}
	
	private String convertAttribute(String from) {
		String to = "";
		String attributeStrs[] = new String[] {"First Name", "Date of Birth", "Visited Times", "Arrival Time", "Power", "Last Name", "Special Considerations", "Names", "Alerts", "Gender", "WishPrice"};
		String attributeIDs[] = new String[] {"2631", "2632", "2633", "2634", "2635", "2636", "2637", "300000001", "300000003", "300000002",  "2000007"};
		String newAttributeIDs[] = new String[] {"2000003", "", "300000006", "", "", "2000005", ""};
		
		if(from.matches("[0-9]+")) {
			for(int i = 0; i < attributeIDs.length; i ++) {
				if(from.equals(attributeIDs[i])) {
					to = attributeStrs[i];
					break;
				}
			}
			if(StringUtil.isEmpty(to)) {
				for(int i = 0; i < newAttributeIDs.length; i ++) {
					if(from.equals(newAttributeIDs[i])) {
						to = attributeStrs[i];
						break;
					}
				}
			}
		} else {
			for(int i = 0; i < attributeStrs.length; i ++) {
				if(from.equals(attributeStrs[i])) {
					to = attributeIDs[i];
					break;
				}
			}
		}
		if(to.length() < 1) {
			throw new ItemNotFoundException("Unkown TPA attribute - " + from);
		}
		
		return to;
	}
	
	public String getErrorMessage() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find the error message DIV object.");
		}
		String msg = objs[0].getProperty(".text");
		
		Browser.unregister(objs);
		return msg;
	}

	/**
	 * Unselect all per inventory checkbox tpa options
	 * @param attrID
	 * @param topObj
	 * @author Lesley
	 * @date Jan 24, 2013
	 */
	public void unSelectAllPerInventoryTPACheckBoxes(String attrID, IHtmlObject topObj) {
		IHtmlObject[] checkBoxes = browser.getCheckBox(Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[" + attrID + "\\]_\\d+", false)), topObj);
		if(checkBoxes.length<1){
			throw new ObjectNotFoundException("Checkboxes objects for attrID=" + attrID + " can't be found.");
		}
		logger.info("Unselect all checkboxes for TPA with attrID=" + attrID);
		for(int i=0; i<checkBoxes.length; i++){
			((ICheckBox)checkBoxes[i]).deselect();
		}
		Browser.unregister(checkBoxes);
	}
	
	/**
	 * Select tour participant attribute for per inventory from checkboxes 
	 * @param key
	 * @param value
	 * @author Lesley
	 * @date Jan 24, 2013
	 */
	public void selectPerInventoryTPACheckBoxes(String attrID, IHtmlObject topObj, String value) {
		IHtmlObject[] checkBoxLabels = null;
		String[] values = value.split(StringUtil.COMMA);
		for(int i=0; i<values.length; i++) {
			checkBoxLabels = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.Label", 
					".for", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[" + attrID + "\\]_\\d+", false), 
					".text", values[i]), topObj);
			if (checkBoxLabels.length > 0) {
				logger.info("select TPA checkbox with value=" + values[i] + " for attrID=" + attrID);
				browser.selectCheckBox(".id", checkBoxLabels[0].getAttributeValue("for"));
			}
			Browser.unregister(checkBoxLabels);
		}
	} 
}
