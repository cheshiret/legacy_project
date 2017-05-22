package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityPermitType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeDetailInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeDetailInfo.AttributeInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.browser.IBrowser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


public class InvMgrPermitTypeDetailPage extends InventoryManagerPage {

	private static InvMgrPermitTypeDetailPage instance;
	private static AutomationLogger logger = AutomationLogger.getInstance();

	public static InvMgrPermitTypeDetailPage getInstance() {
		if (instance == null) {
			instance = new InvMgrPermitTypeDetailPage();
		}
		return instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id",
				"PermitTypeView.permitTypeName"); // Permit Type Name
	}
	
	public String addPermiType(PermitTypeDetailInfo permitType) {
		PermitTypeDetailInfoAdapter adapter = new PermitTypeDetailInfoAdapter(
				permitType);
		return addPermitType(adapter);
	}

	/**
	 * If have "Top" element, framework will not invoke
	 * webdriver.getPageSource() but select from top directly, this will improve
	 * some performance I think
	 *
	 * @return PermitType id
	 *
	 */
	public String addPermitType(PermitTypeDetailInfoAdapter type) {
		IHtmlObject topFormTable = getTopFormTable();

		/*
		 * Click to add HTML element without setting value to make the DOM tree
		 * fully constructed
		 */
		clickAddPermitTypeAttributes(type.attributes, topFormTable);
		clickAddIssueTimeDataAttributes(type.issueTimeDataAttrs, topFormTable);
		clickAddAdditionalQuotaTypes(type.additionalQuotaTypes, topFormTable);

		// Get top formTable again for it's maybe changed by above actions
		topFormTable = refreshDetailTableDomTree();

		logger.info("Begin setting values...");

		// Set value
		setPermitTypeName(type.permitTypeName, topFormTable);
		setPermitTypeCode(type.permitTypeCode, topFormTable);
		setDescription(type.description, topFormTable);
		selectSalesChannelField(type.salesChannels, topFormTable);
		setPermitCategory(type.permitCategory, topFormTable);
		
		//Sara (7/29/2013): after select type, ajaxSyn needs, also topFromTable is changed.
		selectType(type.type,topFormTable);// update by Sophia for Itinerary Permit
		ajax.waitLoading();
		this.waitLoading();
		topFormTable = refreshDetailTableDomTree();
		
		//Sara (7/29/2013): select overlaps allowed product groups for Itineray permit
		if(type.overlapsAllowedProductGroups!=null && type.overlapsAllowedProductGroups.length>0){
			selectGapsAllowed();
			selectOverlapsAllowedProductGroups(type.overlapsAllowedProductGroups,topFormTable);
		}
		
		setPermitTypeAttributes(type.attributes, topFormTable);
		setIssueTimeDataAttributes(type.issueTimeDataAttrs, topFormTable);
		addIssueTimeMandatoryAttributes(type.issueTimeMandatoryAttrs,
				topFormTable);

		List<QuotaType> allQuotaTypes = new ArrayList<InvMgrPermitTypeDetailPage.QuotaType>();
		// Primary quota type is always the first
		allQuotaTypes.add(type.primaryQuotaType);
		allQuotaTypes.addAll(type.additionalQuotaTypes);

		setQuotaTypes(allQuotaTypes, topFormTable);

		// Select capture person type may change the page
		clickAddPersonType(type.personTypes, getTopFormTable());
		setPersonTypes(type.personTypes, getTopFormTable());
		setPersonTypeQuotaDepletion(type.personTypeQuotaDepletions, topFormTable);
		
		logger.info("Click 'Apply' button.");

		clickApply();
		Browser.sleep(1);
		waitLoading();

		String permitTypeId = null;
		String errorMsg = getErrorMsg();
		if (StringUtil.notEmpty(errorMsg)) {
			throw new RuntimeException("Add Permit type failed for: "
					+ errorMsg);
		} else {
			permitTypeId = getPermitTypeId();
		}

		// clickOk();

		return permitTypeId;
	}

	public String getPermitTypeId() {
//		IHtmlObject table = browser.getTableTestObject(".text",
//				new RegularExpression("^Configure Permit Type.*", false))[0];
//		IHtmlObject idSpan = browser.getHtmlObject(".id","PermitTypeView.ID", table)[0];
//		String idValue = idSpan.getProperty(".value");
	
		//Sara[12242013] no ".value" property, so get it using text property.
		Property[] p1 = Property.concatPropertyArray(table(), ".text", new RegularExpression("^Configure Permit Type.*", false));
		Property[] p2 = Property.toPropertyArray(".id", "PermitTypeView.ID");
		String spanValue = browser.getObjectText(Property.atList(p1, p2));
		return RegularExpression.getMatches(spanValue, "\\d+")[0];
	}

	private void selectSalesChannelField(List<String> salesChannels,
			IHtmlObject topFormTable) {
		if (salesChannels != null && !salesChannels.isEmpty()) {
			for (String s : salesChannels) {
				if ("Field".equalsIgnoreCase(s)) {
					// Field is mandatorily selected
					continue;
				}
				if ("Call Center".equalsIgnoreCase(s)) {

					logger.info("Select 'Call Center' sales channel");

					seletCallCenterSalesChannel(topFormTable);
				} else if ("Web".equalsIgnoreCase(s)) {

					logger.info("Select 'Wed' sales channel");

					seletWebSalesChannel(topFormTable);
				}
			}
		}
	}

	private void seletWebSalesChannel(IHtmlObject topFormTable) {
		browser.selectCheckBox(".id", "PermitTypeView.webVisible", 0,
				topFormTable);
	}

	private void seletCallCenterSalesChannel(IHtmlObject topFormTable) {
		browser.selectCheckBox(".id", "PermitTypeView.callCenterVisible", 0,
				topFormTable);
	}

	public void clickOk() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A",".text", "Apply");
	}

	private String getErrorMsg() {
		IHtmlObject msg = browser.getHtmlObject(".class", "Html.TABLE", ".id",
				"statusMessages")[0];
		return msg.text();
	}

	private void setPermitTypeAttributes(Map<String, AttributeInfo> attributes,
			IHtmlObject topFormTable) {
		IHtmlObject attributeFormTable = getPermitTypeAttributeTable(topFormTable);
		
		if (attributes != null && !attributes.isEmpty()) {
			for (AttributeInfo attribute : attributes.values()) {
				if(!"Length Of Stay".equals(attribute.attrname)){// Length Of Stay was removed in 3.03
					IHtmlObject attributeRow = getPermitTypeAttributeRow(
							attribute.attrname, attributeFormTable);
					if (attributeRow == null) {
						throw new RuntimeException(
								"Couldn't find permit type attribute "
										+ attribute.attrname);
					} else {
						setPermitTypeAttribute(attribute, attributeRow);
					}
				}
			}
		}
	}

	private IHtmlObject getPermitTypeAttributeTable(IHtmlObject topFormTable) {
		return getFormTableNextToTitle(
				"Permit Type Data Collection Attributes", topFormTable);
	}

	private IHtmlObject getFormTableNextToTitle(String title, 
			IHtmlObject topFormTable) {
		IHtmlTable table=(IHtmlTable)topFormTable;

		int targetRow=table.findRow(0,title)+1;
		String text=table.getCellValue(targetRow, 0).substring(0,60);
//		HtmlObject[] trs=browser.getHtmlObject(".class", "Html.TR", ".text", text);
//		HtmlObject[] objs=browser.getHtmlObject(".class", "Html.TABLE", trs[0]);
		
		// update by Sophia for Itinerary Permit
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^"+text+".*",false));
		
		if(objs==null || objs.length<1){
				throw new ObjectNotFoundException("Can't find Table object by Title:"+title);
		}
		return objs[0];
	}

	private void setPermitTypeAttribute(AttributeInfo attribute,
			IHtmlObject attributeRow) {
		logger.info("Processing Attribute name=<" + attribute.attrname
				+ ">, value=" + Arrays.toString(attribute.attr));

		new PermitTypeAttributeHandler(attributeRow, attribute).process();
	}

	/*
	 * First select id is like "attr_val_2333", and other input/radio/select
	 * behind it in the same row is like "attr_val_2316:2346"
	 */
	public static class PermitTypeAttributeHandler {
		private String attributeName;
		private ISelect firstMandatoryDropdown;
		private String firstMandatoryVal;
		private List<String> restVals = new ArrayList<String>();
		// Rest tds contains input/select/multi select;
		private List<IHtmlObject> restFieldCells = new ArrayList<IHtmlObject>();

		private static final String CAPTURE_PERSON_TYPES = "Capture Person Types";

		public PermitTypeAttributeHandler(IHtmlObject row, AttributeInfo info) {
			this.attributeName = info.attrname;
			this.firstMandatoryDropdown = getFirstDropdown(row);
			this.firstMandatoryVal = info.attr[0];

			// Ignore first mandatory value
			for (int i = 1; i < info.attr.length; i++) {
				restVals.add(info.attr[i]);
			}
			IHtmlObject[] tds = row.getChildren();
			for (int i = 0; i < restVals.size(); i++) {
				/*
				 * First field regardless of the mandatory one will be in column
				 * 4.
				 */
				int fieldIndex = i * 2 + 4;
				restFieldCells.add((IHtmlObject) tds[fieldIndex - 1]);
			}
		}

		private ISelect getFirstDropdown(IHtmlObject row) {
			return (ISelect) Browser.getInstance().getHtmlObject(".class",
					"Html.SELECT", row)[0];
		}

		public void process() {
			selectFirstMandatoryDropdown();

			handleRestFields();

		}

		private void handleRestFields() {
			for (int i = 0; i < restVals.size(); i++) {
				String val = restVals.get(i);
				IHtmlObject cell = restFieldCells.get(i);

				Property[] fieldId = getRestFieldIdProperty();

				IBrowser browser = Browser.getInstance();
				if (isBoolean(val)) {
					if (Boolean.parseBoolean(val)) {
//						browser.selectRadioButton(fieldId, true, 0, cell);
						browser.selectCheckBox(fieldId, 0, true, cell);
					}
				} else if (isArray(val)) {
					val=val.replaceAll("Array:||array:", "");
					String[] options = getMultiSelectedOptions(val);
					MultiSelectComponent component = new MultiSelectComponent(cell);
					component.addOptions(options);
				} else { // Normal String

					// Check whether a text input
					IHtmlObject[] objs = browser.getTextField(fieldId, cell);
					if (objs != null && objs.length > 0) {
						((IText) objs[0]).setText(val);
					} else { // Maybe a single dropdown
						objs = browser.getDropdownList(fieldId, cell);
						if (objs != null && objs.length > 0
								&& StringUtil.notEmpty(val)) {
							((ISelect) objs[0]).select(val);
						} else {
							throw new RuntimeException(
									"Couldn't find HTML field for attribute value:<"
											+ val + ">");
						}
					}
				}
			}
		}

		/*
		 * This is only could be used for input/single dropdown/radio button, id
		 * pattern of multi select is different from these
		 */
		private Property[] getRestFieldIdProperty() {
			String firstDropdownId = firstMandatoryDropdown.id();
			String idPattern = "^" + firstDropdownId + ":\\d+";
			RegularExpression regex = new RegularExpression(idPattern, false);
			return Property.toPropertyArray(".id", regex);
		}

		private void selectFirstMandatoryDropdown() {
			if (StringUtil.notEmpty(firstMandatoryVal)) {
				firstMandatoryDropdown.select(firstMandatoryVal);
				if (CAPTURE_PERSON_TYPES.equalsIgnoreCase(attributeName)) {
					Browser.sleep(1);
					Browser.getInstance().waitExists();
				}
			}
		}

		private String[] getMultiSelectedOptions(String val) {
			return val.split(",");
		}

		private boolean isArray(String val) {
			return RegularExpression.contains(val, "Array", false);
		}

		private boolean isBoolean(String val) {
			return val.equalsIgnoreCase("true")
					|| val.equalsIgnoreCase("false");
		}
	}

	private IHtmlObject getPermitTypeAttributeRow(String attrName,
			IHtmlObject attributeFormTable) {
		IHtmlObject tbody = attributeFormTable.getChildren()[0];
		IHtmlObject[] trs = tbody.getChildren();
		for (int i = 0; i < trs.length; i++) {
			// First TD
			IHtmlObject permitTypeAttrName = trs[i].getChildren()[0];
			if (permitTypeAttrName.text().equals(attrName)) {
				return (IHtmlObject) trs[i];
			}
		}
		return null;
	}

	private void addIssueTimeMandatoryAttributes(String[] attributes,
			IHtmlObject topFormTable) {
		if (attributes != null && attributes.length > 0) {

			logger.info("Set issue time mandatory attributes="
					+ Arrays.toString(attributes));

			IHtmlObject mandatoryAttributeFormTable = getMandatoryAttributeFormTable(topFormTable);
			MultiSelectComponent component = new MultiSelectComponent(
					mandatoryAttributeFormTable);
			component.addOptions(attributes);
		}
	}

    private void selectOverlapsAllowedProductGroups(String[] overlapsAllowedProductGroups, IHtmlObject topFormTable){
    	if (overlapsAllowedProductGroups != null && overlapsAllowedProductGroups.length > 0) {

			logger.info("Select Overlaps Allowed Product Groups="
					+ Arrays.toString(overlapsAllowedProductGroups));

			MultiSelectComponent component = new MultiSelectComponent(topFormTable);
			component.addOptions(overlapsAllowedProductGroups);
		}
    }
    
	private IHtmlObject getMandatoryAttributeFormTable(IHtmlObject topFormTable) {
		return getFormTableNextToTitle("Issue Time Mandatory Attributes",
				topFormTable);
	}

	private void setPersonTypeQuotaDepletion(List<PersonTypeQuotaDepletion> personTypeQuotaDepletion,
			IHtmlObject topFormTable) {
		if (personTypeQuotaDepletion != null && !personTypeQuotaDepletion.isEmpty()) {
			IHtmlObject addBtn = getAddPersonTypeQuotaDepletionBtn(topFormTable);
			if (addBtn != null) {

				logger.info("Add " + personTypeQuotaDepletion.size() + " personTypesQuotaDepletion");

				IHtmlObject personTypeQuotaDepletionTable = getPersontTypeQuotaDepletionTable(topFormTable);
				/*
				 * <table id='person_types'> <thead><thead> <tbody
				 * class="displayNone"></tbody> <tbody>Form tbody (What we
				 * need)</tbody> <tbody>btns</tbody> </table>
				 */
				IHtmlObject allocationTbody = personTypeQuotaDepletionTable.getChildren()[2];
				IHtmlObject[] rows = allocationTbody.getChildren();

				for (int i = 0; i < personTypeQuotaDepletion.size(); i++) {
					PersonTypeQuotaDepletion personTypeQuotaDepletionI = personTypeQuotaDepletion.get(i);

					logger.info("\tAdd personTypeQuotaDepletion=" + personTypeQuotaDepletionI);

					setPersonTypeQuptaDepletion(personTypeQuotaDepletionI, (IHtmlObject) rows[i]);
				}
			}
		}
	}

	private IHtmlObject getPersontTypeQuotaDepletionTable(IHtmlObject topFormTable) {
		return browser.getHtmlObject(".id", "person_types_quota_depletion_table", topFormTable)[0];
	}

	private void setPersonTypeQuptaDepletion(PersonTypeQuotaDepletion personTypeQuotaDepletion, IHtmlObject row) {
		setPersonTypeQuotaDepletionName(personTypeQuotaDepletion.type, row);
		setPersonTypeQuotaDepletionQty(personTypeQuotaDepletion.qty, row);
		setPersonTypeQuotaDepletionCountedInGroupSize(personTypeQuotaDepletion.countedInGroupSize, row);
	}

	private void setPersonTypeQuotaDepletionName(String name, IHtmlObject row) {
		if (StringUtil.notEmpty(name)) {
			ISelect dropdown = getPersonTypeQuotaDepletionNameDropdown(row);
			dropdown.select(name);
		}
	}
	
	private void setPersonTypes(List<PersonType> personTypes,
			IHtmlObject topFormTable) {
		if (personTypes != null && !personTypes.isEmpty()) {
			IHtmlObject addBtn = getAddPersonTypeBtn(topFormTable);
			if (addBtn != null) {

				logger.info("Add " + personTypes.size() + " personTypes");

				IHtmlObject personTypeTable = getPersontTypesTable(topFormTable);
				/*
				 * <table id='person_types'> <thead><thead> <tbody
				 * class="displayNone"></tbody> <tbody>Form tbody (What we
				 * need)</tbody> <tbody>btns</tbody> </table>
				 */
				IHtmlObject allocationTbody = personTypeTable.getChildren()[2];
				IHtmlObject[] rows = allocationTbody.getChildren();

				for (int i = 0; i < personTypes.size(); i++) {
					PersonType personType = personTypes.get(i);

					logger.info("\tAdd personType=" + personType);

					setPersonType(personType, (IHtmlObject) rows[i]);
				}
			}
		}
	}

	private IHtmlObject getPersontTypesTable(IHtmlObject topFormTable) {
		return browser.getHtmlObject(".id", "person_types", topFormTable)[0];
	}

	private void setPersonType(PersonType personType, IHtmlObject row) {
		setPersonTypeName(personType.type, row);
		setPersonTypeSalesChannel(personType.salesChannel, row);
		setPersonTypePermitCategory(personType.permitCategory, row);
	}
	
	private void setPersonTypeName(String name, IHtmlObject row) {
		if (StringUtil.notEmpty(name)) {
			ISelect dropdown = getPersonTypeNameDropdown(row);
			dropdown.select(name);
		}
	}

	private ISelect getPersonTypeNameDropdown(IHtmlObject row) {
		Property[] ps = Property.toPropertyArray(".id", "person_type");
		IHtmlObject[] objs = browser.getDropdownList(ps, row);
		return (ISelect) objs[0];
	}

	private ISelect getPersonTypeQuotaDepletionNameDropdown(IHtmlObject row) {
		Property[] ps = Property.toPropertyArray(".id", "person_type_quota_depletion");
		IHtmlObject[] objs = browser.getDropdownList(ps, row);
		return (ISelect) objs[0];
	}
	
	private void setPersonTypeSalesChannel(String salesChannel, IHtmlObject row) {
		if (StringUtil.notEmpty(salesChannel)) {
			ISelect dropdown = getPersonTypeSalesChannelDropdown(row);
			dropdown.select(salesChannel);
		}
	}

	private void setPersonTypeQuotaDepletionQty(String personTypeQuotaDepletionQty, IHtmlObject row) {
		if (StringUtil.notEmpty(personTypeQuotaDepletionQty)) {
			IText text = getPersonTypeQuotaDepletionQtyText(row);
			text.setText(personTypeQuotaDepletionQty);
		}
	}
	
	private ISelect getPersonTypeSalesChannelDropdown(IHtmlObject row) {
		Property[] ps = Property.toPropertyArray(".id", "sales_channel");
		IHtmlObject[] objs = browser.getDropdownList(ps, row);
		return (ISelect) objs[0];
	}

	private IText getPersonTypeQuotaDepletionQtyText(IHtmlObject row) {
		Property[] ps = Property.toPropertyArray(".id", "depletedQuantityStr");
		IHtmlObject[] objs = browser.getTextField(ps, row);
		return (IText) objs[0];
	}
	
	private void setPersonTypePermitCategory(String permitCategory,
			IHtmlObject row) {
		if (StringUtil.notEmpty(permitCategory)) {
			ISelect dropdown = getPersonTypePermitCategoryDropdown(row);
			dropdown.select(permitCategory);
		}
	}

	private ISelect getPersonTypePermitCategoryDropdown(IHtmlObject row) {
		Property[] ps = Property.toPropertyArray(".id", "permit_cat");
		IHtmlObject[] objs = browser.getDropdownList(ps, row);
		return (ISelect) objs[0];
	}

	private void setPersonTypeQuotaDepletionCountedInGroupSize(String countedInGroupSize,
			IHtmlObject row) {
		if (StringUtil.notEmpty(countedInGroupSize)) {
			ISelect dropdown = getPersonTypeQuotaDepletionDropdown(row);
			dropdown.select(countedInGroupSize);
		}
	}
	
	private ISelect getPersonTypeQuotaDepletionDropdown(IHtmlObject row) {
		Property[] ps = Property.toPropertyArray(".id", "counted_in_grp_size");
		IHtmlObject[] objs = browser.getDropdownList(ps, row);
		return (ISelect) objs[0];
	}
	
	private void setQuotaTypes(List<QuotaType> quotaTypes,
			IHtmlObject topFormTable) {
		if (quotaTypes != null && !quotaTypes.isEmpty()) {

			logger.info("Add " + quotaTypes.size()
					+ " quotaTypes (Both primary & additional)");

			IHtmlObject allocationTable = getQuotaTypesTable(topFormTable);
			/*
			 * <table id='quota_allocation'> <thead><thead> <tbody
			 * class="displayNone"></tbody> <tbody>Form tbody (What we
			 * need)</tbody> <tbody>btns</tbody> </table>
			 */
			IHtmlObject allocationTbody = allocationTable.getChildren()[2];
			IHtmlObject[] rows = allocationTbody.getChildren();

			for (int i = 0; i < quotaTypes.size(); i++) {
				QuotaType quotaType = quotaTypes.get(i);

				if (quotaType != null) {
					logger.info("\tAdd quotaType: " + quotaType);
					setQuotaType(quotaType, (IHtmlObject) rows[i]);
				}
			}
		}
	}

	private IHtmlObject getQuotaTypesTable(IHtmlObject topFormTable) {
		return browser.getHtmlObject(".id", "quota_allocation", topFormTable)[0];
	}

	private void setQuotaType(QuotaType quotaType, IHtmlObject row) {
		setQuotaTypeName(row, quotaType.type);
		setQuotaQty(row, quotaType.qty);
		setQuotaAllocationMethod(row, quotaType.allocationMethod);
		ajax.waitLoading();
		waitLoading();
		
		if(quotaType.allocationMethod.matches("^Per Unit.*")){
			setUnitType(row, quotaType.unitType);
			ajax.waitLoading();
			waitLoading();
		}
		setQuotaDepletion(row, quotaType.depletion);
		selectDefinePersonTypeLevelDepletion(row, quotaType.definePersonTypeLevelDepletion);
		ajax.waitLoading();
		waitLoading();
	}

	private void setQuotaTypeName(IHtmlObject row, String typeName) {
		if (StringUtil.notEmpty(typeName)) {
			ISelect quotaType = getQuotaTypeDropdown(row);
			quotaType.select(typeName);
		}
	}

	private ISelect getQuotaTypeDropdown(IHtmlObject row) {
		Property[] ps = Property.toPropertyArray(".id", "quota_type");
		IHtmlObject[] objs = browser.getDropdownList(ps, row);
		return (ISelect) objs[0];
	}

	private void setQuotaQty(IHtmlObject row, String qty) {
		if (qty != null) {
			IText qtyField = getQuotaQtyField(row);
			if (qtyField != null) {
				qtyField.clear();
				qtyField.setText(qty);
			}
		}
	}

	private IText getQuotaQtyField(IHtmlObject row) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text",
				".id", "quota_qty", row);
		if (objs != null && objs.length != 0) {
			return (IText) objs[0];
		}
		return null;
	}

	private void setQuotaAllocationMethod(IHtmlObject row,
			String allocationMethod) {
		if (StringUtil.notEmpty(allocationMethod)) {
			ISelect dropdown = getQuotaAllocationDropdown(row);
			dropdown.select(allocationMethod);
		}
	}

	private ISelect getQuotaAllocationDropdown(IHtmlObject row) {
		Property[] ps = Property.toPropertyArray(".id", "quota_alloc_method");
		IHtmlObject[] objs = browser.getDropdownList(ps, row);
		return (ISelect) objs[0];
	}

	private void setUnitType(IHtmlObject row,String unitType) {
		if (StringUtil.notEmpty(unitType)) {
			ISelect dropdown = getUntiTypeDropdown(row);
			dropdown.select(unitType);
		}
	}

	private ISelect getUntiTypeDropdown(IHtmlObject row) {
		Property[] ps = Property.toPropertyArray(".id", "quota_alloc_unit_type");
		IHtmlObject[] objs = browser.getDropdownList(ps, row);
		return (ISelect) objs[0];
	}

	private void setQuotaDepletion(IHtmlObject row, String depletion) {
		if (StringUtil.notEmpty(depletion)) {
			ISelect dropdown = getQuotaDepletionDropdown(row);
			if (dropdown != null) {
				dropdown.select(depletion);
			}
		}
	}

	private void selectDefinePersonTypeLevelDepletion(IHtmlObject row, String definePersonTypeLevelDepletion) {
		if (StringUtil.notEmpty(definePersonTypeLevelDepletion)) {
			ICheckBox checkBox = getDefinePersonTypeLevelDepletion(row);
			if (checkBox != null) {
				if(definePersonTypeLevelDepletion.equalsIgnoreCase("Yes")){
					checkBox.select();
				}else checkBox.deselect();
			}
		}
	}
	
	private ICheckBox getDefinePersonTypeLevelDepletion(IHtmlObject row){
		Property[] ps = Property.toPropertyArray(".id", "personTypeDepletion_cb");
		IHtmlObject[] objs = browser.getCheckBox(ps, row);
		if (objs.length>0) {
			return (ICheckBox) objs[0];
		}
		return null;
	}
	
	private ISelect getQuotaDepletionDropdown(IHtmlObject row) {
		Property[] ps = Property.toPropertyArray(".id", "quota_depletion_type");
		IHtmlObject[] objs = browser.getDropdownList(ps, row);
		if (objs != null && objs.length > 0) {
			return (ISelect) objs[0];
		}
		return null;
	}

	private void setIssueTimeDataAttributes(
			List<IssueTimeDataAttribute> attributes, IHtmlObject topFormTable) {
		IHtmlObject issueTimeAttrFormTable = getIssueTimeAttrTable(topFormTable);
		for (IssueTimeDataAttribute attribute : attributes) {

			logger.info("Set issue time data collection attribute name="
					+ attribute.name + "; value=" + attribute.value);

			ISelect attrDropdown = getIssueTimeDataCollectionAttrDropdown(
					attribute.name, issueTimeAttrFormTable);
			attrDropdown.select(attribute.value);
		}
	}

	private IHtmlObject getIssueTimeAttrTable(IHtmlObject topFormTable) {
		return getFormTableNextToTitle("Issue Time Data Collection Attributes",
				topFormTable);
	}

	private ISelect getIssueTimeDataCollectionAttrDropdown(
			String attribute, IHtmlObject issueTimeAttrFormTable) {
		IHtmlObject[] trs = browser.getHtmlObject(".class", "Html.TR", ".text",
				new RegularExpression("^" + attribute + ".*", false));
		IHtmlObject tr = trs[0];
		return (ISelect) browser
				.getHtmlObject(".class", "Html.SELECT", tr)[0];
	}

	private IHtmlObject refreshDetailTableDomTree() {
		logger.info("Re-get the table to get the latest DOM tree");

		return getTopFormTable();
	}

	private ISelect getPermitTypeNameDropdown(IHtmlObject topFormTable) {
		Property[] ps = Property.toPropertyArray(".id",
				"PermitTypeView.permitTypeName");
		IHtmlObject[] objs = browser.getDropdownList(ps, topFormTable);
		return (ISelect) objs[0];
	}
	
	private void clickAddPersonTypeQuotaDepletion(List<PersonTypeQuotaDepletion> personTypesQuotaDepletion,
			IHtmlObject topFormTable) {
		if (personTypesQuotaDepletion != null && !personTypesQuotaDepletion.isEmpty()) {
			IHtmlObject addBtn = getAddPersonTypeQuotaDepletionBtn(topFormTable);
			if (addBtn != null) {

				logger.info("Click to add " + personTypesQuotaDepletion.size()
						+ " person type quota depletion");

				for (int i = 0; i < personTypesQuotaDepletion.size(); i++) {
					addBtn.click();
				}
			} else {
				throw new RuntimeException(
						"You want to set Person Types Quota Depletion but the UI doesn't exist, "
								+ "Plz check 'Define Person Type Level Depletion' attribute check box in Additional Quota Types section.");
			}
		}
	}
	
	private void clickAddPersonType(List<PersonType> personTypes,
			IHtmlObject topFormTable) {
		if (personTypes != null && !personTypes.isEmpty()) {
			IHtmlObject addBtn = getAddPersonTypeBtn(topFormTable);
			if (addBtn != null) {

				logger.info("Click to add " + personTypes.size()
						+ " person types");

				for (int i = 0; i < personTypes.size(); i++) {
					addBtn.click();
				}
			} else {
				throw new RuntimeException(
						"You want to set Person Types but the UI doesn't exist, "
								+ "Plz check 'Caputre Person Types' attribute value.");
			}
		}
	}

	private IHtmlObject getAddPersonTypeBtn(IHtmlObject topFormTable) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".onclick", new RegularExpression(
						"^addFormRow\\(\"person_types.*", false), topFormTable);
		if (objs != null && objs.length > 0) {
			return objs[0];
		}
		return null;
	}

	private IHtmlObject getAddPersonTypeQuotaDepletionBtn(IHtmlObject topFormTable) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".onclick", new RegularExpression(
						"^addFormRow\\(\"person_types_quota_depletion_table.*", false), topFormTable);
		if (objs != null && objs.length > 0) {
			return objs[0];
		}
		return null;
	}
	
	private void clickAddAdditionalQuotaTypes(List<QuotaType> quotaTypes,
			IHtmlObject topFormTable) {
		if (quotaTypes != null && !quotaTypes.isEmpty()) {
			IHtmlObject addButton = getAddAdditionalQuotaTypeBtn(topFormTable);

			logger.info("Click to add " + quotaTypes.size()
					+ " additional quota types");

			for (int i = 0; i < quotaTypes.size(); i++) {
				addButton.click();
			}
		}
	}

	private IHtmlObject getAddAdditionalQuotaTypeBtn(IHtmlObject topFormTable) {
		Property[] ps = new Property[2];
		ps[0] = new Property(".class", "Html.A");
		ps[1] = new Property(".onclick", new RegularExpression(
				"^addFormRow\\(\"quota_allocation", false));
		IHtmlObject btn = browser.getHtmlObject(ps, topFormTable)[0];
		return btn;
	}

	private void clickAddIssueTimeDataAttributes(
			List<IssueTimeDataAttribute> attributes, IHtmlObject topFormTable) {
		if (attributes != null && !attributes.isEmpty()) {
			ISelect attributeSelect = getIssueTimeDataCollectionAttrSelect(topFormTable);
			IHtmlObject addButton = getAddIssueTimeDataCollectionAttrBtn(topFormTable);

			logger.info("Click to add " + attributes.size()
					+ " issue time data collection attributes");

			for (int i = 0; i < attributes.size(); i++) {
				String attrName = attributes.get(i).name;

				logger.info("\tAdd issue time data collection attribute <"
						+ attrName + ">");

				attributeSelect.select(attrName);
				addButton.click();
				waitLoading();
			}
		}
	}

	private IHtmlObject getAddIssueTimeDataCollectionAttrBtn(
			IHtmlObject topFormTable) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Add Issue Attribute", topFormTable);
		return objs[0];
	}

	private ISelect getIssueTimeDataCollectionAttrSelect(
			IHtmlObject topFormTable) {
		Property[] ps = Property.toPropertyArray(".id",
				"issueTime_special_attr_id");
		IHtmlObject[] objs = browser.getDropdownList(ps, topFormTable);
		return (ISelect) objs[0];
	}

	private void clickAddPermitTypeAttributes(
			Map<String, AttributeInfo> attributes, IHtmlObject topFormTable) {
		if (attributes != null && !attributes.isEmpty()) {
			Set<String> keys = attributes.keySet();
			List<String> options=null;
			int newAttributeCount = 0;
			for (String key : keys) {
				AttributeInfo info = attributes.get(key);
				if (info != null) {
					options = browser.getDropdownElements(Property.toPropertyArray(".id", "special_attr_id"), topFormTable);
					String attrName = info.attrname;
					if (options.contains(attrName)) {

						logger.info("\tClick to add new attribute <" + attrName
								+ ">");

						browser.selectDropdownList(".id", "special_attr_id", attrName, false, topFormTable);
						clickAddSpecialAttrButton();
						Browser.sleep(1);
						waitLoading();

						newAttributeCount++;
					}
				}
			}
			logger.info("Added total " + newAttributeCount
					+ " special attributes");
		}
	}

//	private ISelect getAddPermitAttributeDropdown(HtmlObject topFormTable) {
//		Property[] ps = Property.toPropertyArray(".id", "special_attr_id");
//		HtmlObject[] objs = browser.getDropdownList(ps, topFormTable);
//		return (ISelect) objs[0];
//	}

	private void clickAddSpecialAttrButton() {
		
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".text","Add Special Attribute"),false,0,getTopFormTable());
	}

	private IHtmlObject getTopFormTable() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Permit Type Info.*", false));
		return objs[0];
	}

	private void setPermitTypeCode(String permitTypeCode,
			IHtmlObject topFormTable) {
		if (permitTypeCode != null) {

			logger.info("Set permitTypeCode=" + permitTypeCode);

			IText textField = getPermitTypeCodeField(topFormTable);
			textField.setText(permitTypeCode);
		}
	}

	private IText getPermitTypeCodeField(IHtmlObject topFormTable) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text",
				".id", "PermitTypeView.code", topFormTable);
		return (IText) objs[0];
	}

	private void setPermitTypeName(String permitTypeName,
			IHtmlObject topFormTable) {
		if (permitTypeName != null) {

			logger.info("Set permitType=" + permitTypeName);

			ISelect dropdown = getPermitTypeNameDropdown(topFormTable);
			dropdown.select(permitTypeName);
		}
	}

	private void setDescription(String description, IHtmlObject topFormTable) {
		if (description != null) {

			logger.info("Set description=" + description);

			IText txtField = getDescriptionField(topFormTable);
			txtField.setText(description);
		}
	}

	private IText getDescriptionField(IHtmlObject topFormTable) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TEXTAREA",
				".id", "PermitTypeView.description", topFormTable);
		return (IText) objs[0];
	}

	private void setPermitCategory(String permitCategory,
			IHtmlObject topFormTable) {
		if (permitCategory != null) {

			logger.info("Set permitCategory=" + permitCategory);

			ISelect dropdown = getPermitCategoryDropdown(topFormTable);
			dropdown.select(permitCategory);
		}
	}

	private ISelect getPermitCategoryDropdown(IHtmlObject topFormTable) {
		Property[] ps = Property.toPropertyArray(".id",
				"PermitTypeView.permitCategory");
		IHtmlObject[] objs = browser.getDropdownList(ps, topFormTable);
		return (ISelect) objs[0];
	}
	
	private void selectType(String type,
			IHtmlObject topFormTable) {
		if (type != null) {
			logger.info("Select type=" + type);
			browser.selectDropdownList(".id", "PermitTypeView.permitTypeClass", type, false, topFormTable);
		}
	}
	
	private void selectGapsAllowed(){
		browser.selectCheckBox(".id", "attr_val_2761");
	}
	
	private static class MultiSelectComponent {
		private ISelect mainList;
		@SuppressWarnings("unused")
		private ISelect selectedList;
		private IHtmlObject addBtn;
		@SuppressWarnings("unused")
		private IHtmlObject removeBtn;
		private IHtmlObject top;

		private MultiSelectComponent(IHtmlObject top) {
			this.top = top;
			getAddBtn();
			getRemoveBtn();
			getMainList();
			getSelectedList();
		}

		private void getRemoveBtn() {
			removeBtn = Browser.getInstance().getHtmlObject(".class", "Html.A",
					".text", "<<Remove", top)[0];
		}

		private void getAddBtn() {
			addBtn = Browser.getInstance().getHtmlObject(".class", "Html.A",
					".text", "Add>>", top)[0];
		}

		private void getMainList() {
			mainList = (ISelect) Browser.getInstance().getHtmlObject(
					".class", "Html.SELECT", ".id",
					new RegularExpression("\\w+_MainList", false), top)[0];
			System.out.println(mainList.getAllOptions().toString());
		}

		private void getSelectedList() {
			selectedList = (ISelect) Browser.getInstance().getHtmlObject(
					".class", "Html.SELECT", ".id",
					new RegularExpression("\\w+_SelectedList", false), top)[0];
		}

		public void addOptions(String[] options) {
			if (options != null && options.length > 0) {
				mainList.select(options);
				addBtn.click();
			}
		}
	}

	/**
	 * Adapter for original PermitTypeDetailInfo, make it easy for use.
	 *
	 * @author Reed Wang
	 *
	 */
	public static class PermitTypeDetailInfoAdapter {

		public String permitTypeName;
		public String permitTypeCode;
		public String description;
		public List<String> salesChannels;
		public String permitCategory;
		public String type;
		public String[] overlapsAllowedProductGroups;
		public Map<String, AttributeInfo> attributes = new HashMap<String, PermitTypeDetailInfo.AttributeInfo>();
		public List<IssueTimeDataAttribute> issueTimeDataAttrs = new ArrayList<InvMgrPermitTypeDetailPage.IssueTimeDataAttribute>();
		public String[] issueTimeMandatoryAttrs;
		public QuotaType primaryQuotaType;
		public List<QuotaType> additionalQuotaTypes = new ArrayList<InvMgrPermitTypeDetailPage.QuotaType>();
		public List<PersonType> personTypes = new ArrayList<InvMgrPermitTypeDetailPage.PersonType>();
		public List<PersonTypeQuotaDepletion> personTypeQuotaDepletions = new ArrayList<InvMgrPermitTypeDetailPage.PersonTypeQuotaDepletion>();

		/*
		 * Add this to get all the pre-defined column label like
		 * "Permit Type Name"
		 */
		private static final PermitTypeDetailInfo CONSTANT = new PermitTypeDetailInfo();

		private static final List<String> ATTRIBUTE_NAMES = new ArrayList<String>();
		private static final List<String> ISSUE_TIME_ADDITIONAL_ATTRIBTUES = new ArrayList<String>();

		static {
			// All permit type attribute names
			ATTRIBUTE_NAMES.add(CONSTANT.entrance);
			ATTRIBUTE_NAMES.add(CONSTANT.entryDate);
			ATTRIBUTE_NAMES.add(CONSTANT.groupSize);
			ATTRIBUTE_NAMES.add(CONSTANT.alternateLeaders);
			ATTRIBUTE_NAMES.add(CONSTANT.capturePersonTypes);
			ATTRIBUTE_NAMES.add(CONSTANT.commerciallyGuidedTrip);
			ATTRIBUTE_NAMES.add(CONSTANT.exitDate);
			ATTRIBUTE_NAMES.add(CONSTANT.exitPoint);
			ATTRIBUTE_NAMES.add(CONSTANT.lengthOfStay);
			ATTRIBUTE_NAMES.add(CONSTANT.permitIssueStation);

			// Special attribute name
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrOfGuides);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrWatercraft);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrOfPets);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrOfStock);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrComments);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrCommercialUseType);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrEmergencyContact);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrGroupMemberInfo);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrLaunchPoint);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrDirectionOfTravel);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrMethodOfTravel);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrNProfitOrg);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrPmitDelMetd);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrReentryDate);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrTakeOutPoint);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrTrailhead);
			ATTRIBUTE_NAMES.add(CONSTANT.specialAttrTripItinerary);

			// Issue time additional attributes
			ISSUE_TIME_ADDITIONAL_ATTRIBTUES
					.add(CONSTANT.issueAttrActualEntryDate);
			ISSUE_TIME_ADDITIONAL_ATTRIBTUES.add(CONSTANT.issueAttrBoatLeader);
			ISSUE_TIME_ADDITIONAL_ATTRIBTUES.add(CONSTANT.issueAttrTravelPlan);
			ISSUE_TIME_ADDITIONAL_ATTRIBTUES
					.add(CONSTANT.issueAttrVehiclePlate);
		}

		public PermitTypeDetailInfoAdapter(PermitTypeDetailInfo info) {
			AllValues val = new AllValues(info.attrArray);

			this.permitTypeName = val.getString(CONSTANT.permitTypeName);
			this.permitTypeCode = val.getString(CONSTANT.permitTypeCode);
			this.description = val.getString(CONSTANT.description);
			this.permitCategory = val.getString(CONSTANT.permitCategory);
			this.type = val.getString(CONSTANT.type);
			setOverlapsAllowedProductGroup(val);
			setIssueTimeMandatoryAttrs(val);
			setSalesChannels(val);
			setAttributes(val);
			setIssueTimeDataAttributes(val);
			setPrimaryQuotaType(val);
			setAddtionalQuotaTypes(val);
			setPersonTypes(val);
			setPersonTypeQuotaDepletion(val);
		}

		private void setOverlapsAllowedProductGroup(AllValues val) {
			String[] attrs = val
					.getArray(CONSTANT.overlapsAllowedProductGroups);
			if (attrs != null) {
				this.overlapsAllowedProductGroups = attrs[0].split(",");
			}
		}
		
		private void setIssueTimeMandatoryAttrs(AllValues val) {
			String[] attrs = val
					.getArray(CONSTANT.issueTimeMandatoryAttributes);
			if (attrs != null) {
				this.issueTimeMandatoryAttrs = attrs[0].split(",");
			}
		}

		private void setSalesChannels(AllValues val) {
			String[] channels = val.getArray(CONSTANT.salesChannel);
			if (channels != null) {
				salesChannels = Arrays.asList(channels[0].split(","));
			}
		}

		private void setAttributes(AllValues val) {
			for (String attrName : ATTRIBUTE_NAMES) {
				AttributeInfo info = val.getSingle(attrName);
				if (info != null && info.attr != null && info.attr.length > 0) {
					attributes.put(info.attrname, info);
				}
			}
		}

		private void setIssueTimeDataAttributes(AllValues val) {
			for (String attrName : ISSUE_TIME_ADDITIONAL_ATTRIBTUES) {
				AttributeInfo info = val.getSingle(attrName);
				if (info != null) {
					String value = null;
					if (info.attr != null && info.attr.length > 0) {
						value = info.attr[0];
					}
					issueTimeDataAttrs.add(new IssueTimeDataAttribute(
							info.attrname, value));
				}
			}
		}

		private void setAddtionalQuotaTypes(AllValues val) {
			List<AttributeInfo> infos = val
					.getCollection(CONSTANT.AdditionalQuotaTypes);
			if (infos != null) {
				for (AttributeInfo info : infos) {
					additionalQuotaTypes.add(new QuotaType(info.attr));
				}
			}
		}

		private void setPrimaryQuotaType(AllValues val) {
			String[] array = val.getArray(CONSTANT.primaryQuotaType);
			if (array != null && array.length > 0) {
				primaryQuotaType = new QuotaType(array);
			}
		}

		private void setPersonTypes(AllValues val) {
			List<AttributeInfo> infos = val.getCollection(CONSTANT.personTypes);
			for (AttributeInfo info : infos) {
				personTypes.add(new PersonType(info.attr));
			}
		}

		private void setPersonTypeQuotaDepletion(AllValues val) {
			List<AttributeInfo> infos = val.getCollection(CONSTANT.personTypeQuotaDepletions);
			for (AttributeInfo info : infos) {
				personTypeQuotaDepletions.add(new PersonTypeQuotaDepletion(info.attr));
			}
		}
		
		private class AllValues {

			private List<AttributeInfo> values;

			private AllValues(List<AttributeInfo> values) {
				this.values = values;
			}

			public String getString(String name) {
				String[] array = getArray(name);
				if (array != null && array.length > 0) {
					return array[0];
				}
				return null;
			}

			public String[] getArray(String name) {
				AttributeInfo info = getSingle(name);
				if (info != null) {
					return info.attr;
				}
				return null;
			}

			public AttributeInfo getSingle(String name) {
				List<AttributeInfo> infos = getCollection(name);
				if (infos != null && !infos.isEmpty()) {
					return infos.get(0);
				}
				return null;
			}

			public List<AttributeInfo> getCollection(String name) {
				List<AttributeInfo> result = new ArrayList<PermitTypeDetailInfo.AttributeInfo>();
				if (values != null && !values.isEmpty()) {
					for (AttributeInfo info : values) {
						if (info.attrname.equalsIgnoreCase(name)) {
							result.add(info);
						}
					}
				}
				return result;
			}

		}

	}

	public static class PersonTypeQuotaDepletion {
		public String type;
		public String qty;
		public String countedInGroupSize;

		public PersonTypeQuotaDepletion() {
		}

		public PersonTypeQuotaDepletion(String[] array) {
			this.type = array[0];
			this.qty = array[1];
			this.countedInGroupSize = array[2];
		}

		@Override
		public String toString() {
			return "PersonType:[type=" + type + ", qty="
					+ qty + ", countedInGroupSize=" + countedInGroupSize + "]";
		}
	}

	public static class PersonType {
		public String type;
		public String salesChannel;
		public String permitCategory;

		public PersonType() {
		}

		public PersonType(String[] array) {
			this.type = array[0];
			this.salesChannel = array[1];
			this.permitCategory = array[2];
		}

		@Override
		public String toString() {
			return "PersonType:[type=" + type + ", salesChannel="
					+ salesChannel + ", permitCategory=" + permitCategory + "]";
		}
	}
	
	public static class QuotaType {
		public String type;
		public String qty;
		public String allocationMethod;
		public String unitType;
		public String depletion;
		public String definePersonTypeLevelDepletion;
		
		public QuotaType() {
		}

		public QuotaType(String[] array) {
			this.type = array[0];
			this.qty = array[1];
			this.allocationMethod = array[2];
			this.unitType = array[3];
			this.depletion = array[4];
			this.definePersonTypeLevelDepletion = array[5];
		}

		@Override
		public String toString() {
			return "QuotaType:[type=" + type + ", qty=" + qty
					+ ", allocation Method=" + allocationMethod
					+ ", Unit Type=" + unitType
					+ ", depletion="+ depletion
					+ ", definePersonTypeLevelDepletion="+ definePersonTypeLevelDepletion +"]";
		}
	}

	public static class IssueTimeDataAttribute {
		public String name;
		public String value;

		public IssueTimeDataAttribute() {
		}

		public IssueTimeDataAttribute(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}
}
