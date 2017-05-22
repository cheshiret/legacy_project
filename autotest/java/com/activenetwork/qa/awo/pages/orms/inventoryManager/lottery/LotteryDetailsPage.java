package com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.Lottery.AwardRulePara;
import com.activenetwork.qa.awo.datacollection.legacy.Lottery.SubmissionRulePara;
import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LotteryDetailsPage extends InvMgrCommonTopMenuPage {
	private static LotteryDetailsPage _instance = null;

	public static LotteryDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new LotteryDetailsPage();
		}

		return _instance;
	}

	protected LotteryDetailsPage() {
	}

	protected Property[] submissionTicketCatList() {
		return Property.toPropertyArray(".id", "submission_cat");
	}
	
	protected Property[] awardTicketCatList() {
		return Property.toPropertyArray(".id", "award_cat");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id",
				"LotteryView.applicableProductCategory");
	}

	public void setLotteryName(String lotteryName) {
		browser.setTextField(".id", "LotteryView.lotteryName", lotteryName);
	}

	public void setDescription(String description) {
		if (null != description) {
			browser.setTextArea(".id", "LotteryView.lotteryDescription",
					description);
		}
	}

	public void selectProCategory(String category) {
		browser.selectDropdownList(".id",
				"LotteryView.applicableProductCategory", category);
		ajax.waitLoading();
		this.waitLoading();
	}

	public void selectRevenue(String revenue) {
		browser.selectDropdownList(".id", "LotteryView.revenueLocation",
				revenue);
	}

	public void selectPermitCategories(String category) {
		if (StringUtil.notEmpty(category)) {
			IHtmlObject[] categories = browser.getHtmlObject(".id",
					"permit_category");
			if (categories == null || categories.length == 0) {
				throw new IllegalStateException(
						"You have passed in PermitCategory"
								+ "<"
								+ category
								+ ">"
								+ " but there is no dropdown list, please check you data setup");
			} else {
				browser.selectDropdownList(".id", "permit_category", category,
						categories.length - 1);
				// browser.selectDropdownList(".id", "permit_category",
				// category);
			}
		}
	}

	public void selectPermitType(String[] types) {
		if (types != null && types.length > 0) {
			Property[] p = new Property[1];
			Property id = new Property(".id", "permit_type");
			p[0] = id;

			for (int i = 0; i < types.length; i++) {
				IHtmlObject tbody = getPermitTypeDropdownParentTbody();
				if (tbody == null) {
					throw new IllegalStateException(
							"You have passed in PermitType"
									+ "<"
									+ types[i]
									+ ">"
									+ " index="
									+ i
									+ ", but the dropdown doesn't exist, please check you data setup");
				}
				IHtmlObject[] dropdowns = browser.getDropdownList(p, tbody);
				((ISelect) dropdowns[i]).select(types[i]);
				this.waitLoading();

				if (dropdowns != null && dropdowns.length < types.length) {
					clickAddNewPermitType();
				}
			}
		}
	}

	public void selectProGroups(String groups) {
		if (StringUtil.notEmpty(groups)) {
			browser.selectDropdownList(".id", "product_group", groups, 1);
		}
	}

	public boolean isMaxNumDropdownListEnabled() {
		IHtmlObject objs[] = browser.getDropdownList(".id",
				"LotteryView.maximumPreferences");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Maximum Number dropdown list");
		}
		boolean enabled = ((ISelect) objs[0]).isEnabled();

		Browser.unregister(objs);
		return enabled;
	}

	public String getMaxNum() {
		IHtmlObject objs[] = browser.getDropdownList(".id",
				"LotteryView.maximumPreferences");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Maximum Number dropdown list");
		}
		String num = ((ISelect) objs[0]).getSelectedText();
		Browser.unregister(objs);

		return num;
	}

	public void selectMaxiNum(String number) {
		if (StringUtil.notEmpty(number)) {
			browser.selectDropdownList(".id", "LotteryView.maximumPreferences",
					number);
		}
	}

	public void selectCreditCard() {
		browser.selectRadioButton(".id", "LotteryView.creditCardPayment", 0);
	}

	public boolean isSelectCreditCard() {
		IHtmlObject[] objs = browser.getRadioButton(".id",
				"LotteryView.creditCardPayment");
		IRadioButton button = (IRadioButton) objs[0];
		boolean isselected = button.isSelected();
		Browser.unregister(objs);
		return isselected;
	}

	public void unselectCreditCard() {
		browser.selectRadioButton(".id", "LotteryView.creditCardPayment", 1);
	}

	public void selectRequiredPermit() {
		browser.selectRadioButton(".id", "LotteryView.infoRequired", 0);
	}

	public void unselectRequiredPermit() {
		browser.selectRadioButton(".id", "LotteryView.infoRequired", 1);
	}

	public void selectCustomerAcceptance() {
		browser.selectRadioButton(".id", "LotteryView.acceptanceRequired", 0);
	}

	public boolean isSelectCustomerAcceptance() {
		IHtmlObject[] objs = browser.getRadioButton(".id",
				"LotteryView.acceptanceRequired");
		IRadioButton button = (IRadioButton) objs[0];
		boolean isselected = button.isSelected();
		Browser.unregister(objs);
		return isselected;
	}

	public void unselectCustomerAcceptance() {
		browser.selectRadioButton(".id", "LotteryView.acceptanceRequired", 1);
	}

	public void clickAdd() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}

	public void clickAddNewPermitType() {
		browser.clickGuiObject("onclick", new RegularExpression(
				"addFormRow.*permit_types.*", false));
	}

	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
	}

	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", true);
	}

	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply", true);
	}

	public void clickLotteryParticiption() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Lottery Participation");
	}

	public void clickLotterySchedule() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Lottery Schedules");
	}

	public String getErrorMessage() {
		String error = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "statusMessages");
		error = objs[0].text();

		Browser.unregister(objs);
		return error;
	}

	public boolean isErrorOccur() {
		return browser.getObjectText(".id", "statusMessages").trim().length() > 0;
	}

	public String getDescription() {
		String description = "";
		IHtmlObject[] objs = browser.getTextArea(".id",
				"LotteryView.lotteryDescription");
		description = objs[0].text();

		Browser.unregister(objs);
		return description;
	}

	public String getRevenueLocation() {
		String loc = "";
		IHtmlObject[] objs = browser.getDropdownList(".id",
				"LotteryView.revenueLocation");
		loc = objs[0].text();

		Browser.unregister(objs);
		return loc;
	}

	public String getMaxNumber() {
		String maxNum = "";
		IHtmlObject[] objs = browser.getDropdownList(".id",
				"LotteryView.maximumPreferences");
		maxNum = ((ISelect) objs[0]).getSelectedTexts().toString();
		maxNum = maxNum.substring(1, maxNum.length() - 1);
		Browser.unregister(objs);

		return maxNum;
	}

	/**
	 * Retrieve new added lottery id
	 * 
	 * @return
	 */
	public String getLotteryId() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Lottery.*", false));
		IHtmlTable grid = (IHtmlTable) objs[0];
		String text = grid.text();
		Browser.unregister(objs);
		String id = text.substring(
				text.indexOf("Lottery ID") + "Lottery ID".length(),
				text.indexOf("Name")).trim();
		return id;
	}

	public String getLotteryName() {
		return this.getSpanObjectText("Name");
	}

	public String getLotteryCoverage() {
		return this.getSpanObjectText("Coverage");
	}

	public String getSpanObjectText(String divName) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".text", new RegularExpression("^" + divName + ".*", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Did not found div object with name start " + divName);
		}

		IHtmlObject[] spanObjs = browser.getHtmlObject(".class", "Html.SPAN",
				objs[objs.length - 1]);
		if (spanObjs.length < 1) {
			throw new ItemNotFoundException(
					"Did not found span object with div object name start "
							+ divName);
		}

		String text = spanObjs[spanObjs.length - 1].text();

		Browser.unregister(spanObjs);
		Browser.unregister(objs);
		return text;
	}

	public List<String> getProductCategoryElements() {
		return browser.getDropdownElements(".id",
				"LotteryView.applicableProductCategory");
	}

	public String getProductCategory() {
		return browser.getDropdownListValue(".id",
				"LotteryView.applicableProductCategory");
	}

	public boolean checkSpanObjectTextIsEditable(String divName) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".text", new RegularExpression("^" + divName + ".*", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Did not found div object with name start " + divName);
		}

		IHtmlObject[] spanObjs = browser.getHtmlObject(".class", "Html.SPAN",
				objs[objs.length - 1]);
		if (spanObjs.length < 1) {
			throw new ItemNotFoundException(
					"Did not found span object with div object name start "
							+ divName);
		}

		String value = spanObjs[spanObjs.length - 1]
				.getAttributeValue("isTextEdit");
		Browser.unregister(spanObjs);
		Browser.unregister(objs);

		return Boolean.parseBoolean(value);
	}

	public boolean checkLotteryIDIsEditable() {
		return this.checkSpanObjectTextIsEditable("Lottery ID");
	}

	public boolean checkLotteryNameIsEditable() {
		return this.checkSpanObjectTextIsEditable("Name");
	}

	public boolean checkLotteryCoverageIsEditable() {
		return this.checkSpanObjectTextIsEditable("Coverage");
	}

	public List<String> getProductGroups() {
		IHtmlObject[] objs = browser.getDropdownList(".id", "product_group");
		if (objs.length < 2) {
			throw new ItemNotFoundException(
					"Did not found product group object.");
		}
		List<String> productGroupsList = new ArrayList<String>();
		for (int i = 1; i < objs.length; i++) {
			String productGroup = ((ISelect) objs[i]).getSelectedText();
			productGroupsList.add(productGroup);
		}

		Browser.unregister(objs);
		return productGroupsList;
	}

	public boolean checkProductGroupsIsEnabled() {
		IHtmlObject[] objs = browser.getDropdownList(".id", "product_group");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Did not found product group object.");
		}

		boolean isEnabled = true;
		for (int i = 0; i < objs.length - 1; i++) {
			isEnabled &= ((ISelect) objs[i]).isEnabled();
		}

		Browser.unregister(objs);
		return isEnabled;
	}

	public void generateDisplayOrder() {
		IHtmlObject[] objs = browser.getTextField(".id", "display_order");
		for (int i = 1; i < objs.length; i++) {
			IText displayOrder = (IText) objs[i];
			if (StringUtil.notEmpty(displayOrder.getText())) {
				throw new IllegalStateException(
						"DisplayOrder index="
								+ i
								+ " already has value="
								+ displayOrder.getText()
								+ " generate failed,"
								+ " maybe you could try setPrefAttrDisplayOrder(String displayOrder, int attrTableRowIndex)");
			} else {
				displayOrder.setText(i);
			}
		}
		Browser.unregister(objs);
	}

	public String getAttributeDisplayOrder(int index) {
		return ((IHtmlObject)getPrefAttrDisplayOrderField(getPrefAttrRows()[index])).text();
	}

	public boolean getCreditcardStatus() {
		boolean status = false;

		IHtmlObject[] objs = browser.getRadioButton(".id",
				"LotteryView.creditCardPayment");
		if (((IRadioButton) objs[0]).getProperty(".checked").toString()
				.equalsIgnoreCase("true")) {
			status = true;
		}
		Browser.unregister(objs);
		return status;
	}

	/** Set Requires Customer Acceptance as Yes */
	public void customerAcceptanceRequired() {
		browser.selectRadioButton(".id", "LotteryView.acceptanceRequired");
	}

	public boolean lotteryNameDisable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text",
				".id", "LotteryView.lotteryName");
		return Boolean.valueOf(objs[0].getAttributeValue("disabled"));
	}

	public boolean lotteryDescriptionDisable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TEXTAREA",
				".id", "LotteryView.lotteryDescription");
		return Boolean.valueOf(objs[0].getAttributeValue("disabled"));
	}

	public boolean lotteryLocationDisable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",
				".id", "LotteryView.revenueLocation");
		return Boolean.valueOf(objs[0].getAttributeValue("disabled"));
	}

	public boolean productCategoryDisable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",
				".id", "LotteryView.applicableProductCategory");
		return Boolean.valueOf(objs[0].getAttributeValue("disabled"));
	}

	public boolean productGroupsDisable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",
				".id", "product_group");
		return Boolean.valueOf(objs[1].getAttributeValue("disabled"));
	}

	public boolean permitCategoriesDisable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",
				".id", "permit_category");
		return Boolean.valueOf(objs[1].getAttributeValue("disabled"));
	}

	public boolean permitTypeDisable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",
				".id", "permit_type");
		return Boolean.valueOf(objs[1].getAttributeValue("disabled"));
	}

	public boolean maxNumDisable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",
				".id", "LotteryView.maximumPreferences");
		return Boolean.valueOf(objs[0].getAttributeValue("disabled"));
	}

	public boolean attributesLabelDisable(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",
				".id", "attr_id");
		return Boolean.valueOf(objs[index].getAttributeValue("disabled"));
	}

	public boolean creditCardYesOrNoButtonDisable(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.radio",
				".id", "LotteryView.creditCardPayment");
		return Boolean.valueOf(objs[index].getAttributeValue("disabled"));
	}

	public boolean requiredPermitYesOrNoButtonDisable(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.radio",
				".id", "LotteryView.infoRequired");
		return Boolean.valueOf(objs[index].getAttributeValue("disabled"));
	}

	public boolean requiredCustomerYesOrNoButtonDisable(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.radio",
				".id", "LotteryView.acceptanceRequired");
		return Boolean.valueOf(objs[index].getAttributeValue("disabled"));
	}

	/*
	 * TODO: Question: we only passed description, category, permitType & number
	 * into this method, why process other field? if they need to be changed,
	 * why not passed them in? comment the rest out.
	 */
	public void updateLotteryDetail(String description, String category,
			String[] types, String number) {
		this.setDescription(description);
		this.selectPermitCategories(category);
		this.selectPermitType(types);
		if (isMaxNumDropdownListEnabled()) {
			this.selectMaxiNum(number);
		}
		// this.generateDefaultPerferenceAttribute();
		// this.selectCreditCard();
		// this.selectRequiredPermit();
		// this.unselectCustomerAcceptance();
	}

	/** Update Lottery Details. Added by Lesley, to update other field values */
	public void updateLotteryDetail(Lottery lottery) {
		this.updateLotteryDetail(lottery.description, lottery.category, lottery.permitTypes, lottery.maxNumber);
		if (lottery.isCollectCreditCard) {
			this.selectCreditCard();
		} else {
			this.unselectCreditCard();
		}
		if (lottery.isRequiredByPermitType) {
			this.selectRequiredPermit();
		} else {
			this.unselectRequiredPermit();
		}
	}
	
	public void updateLotteryDetail(Lottery lottery,
			EditPermitType changePermitType,
			EditPerferenceAttribute changeAttribute) {
		this.setDescription(lottery.description);
		this.selectPermitCategories(lottery.permitCategory);
		if (null != changePermitType) {
			changePermitType.update(this);
		}
		if (null != changeAttribute) {
			changeAttribute.update(this);
		}
		if (isMaxNumDropdownListEnabled()) {
			this.selectMaxiNum(lottery.maxNumber);
		}
		if (lottery.isCollectCreditCard) {
			this.selectCreditCard();
		} else {
			this.unselectCreditCard();
		}
		if (lottery.isRequiredByPermitType) {
			this.selectRequiredPermit();
		} else {
			this.unselectRequiredPermit();
		}
		if (lottery.isRequiresCustomerAcceptance) {
			this.selectCustomerAcceptance();
		} else {
			this.unselectCustomerAcceptance();
		}

		// submission rule
		int num = this.getSubmissionRuleRemoveObjectLength();
		if (num > 1) {
			for (int i = 0; i < num-1; i++) {
				this.clickRemoveSubmissionRule(i);
			}
		}
		int submissionRuleCounter = 1;
		if (lottery.isMaxNumPerPrimaryOccu) {
			this.clickAddNewSubRules();
			this.selectSubmissionRules("Maximum Number per Primary Occupant",
					submissionRuleCounter);
			this.setMaxNumOfPriOccupant(lottery.maxNumOfPriOccupant);
			submissionRuleCounter += 1;
		}

		// award rule
		num = this.getAwardRuleRemoveObjectLength();
		if (num > 1) {
			for (int i = 0; i < num-1; i++) {
				this.clickRemoveAwardRule(i);
			}
		}
		if (null != lottery.awardRules && lottery.awardRules.size() > 0) {
			for (int i = 0; i < lottery.awardRules.size(); i++) {
				this.clickAddNewWardRule();
				this.selectAwardRules(lottery.awardRules.get(i).ruleName, i + 1);
				this.setMaxNumForAwardRule(lottery.awardRules.get(i).maxNumber,
						i + 1);
			}
		}

	}

	public static interface EditPermitType {

		public void update(LotteryDetailsPage page);
	}

	public static interface EditPerferenceAttribute {

		public void update(LotteryDetailsPage page);
	}

	public String addLotteryInfo(Lottery lottery) {
		this.setLotteryName(lottery.name);
		this.setDescription(lottery.description);
		this.selectProCategory(lottery.productCategory);
		this.selectProGroups(lottery.productGroup);
		this.waitLoading();
		this.selectPermitCategories(lottery.permitCategory);
		this.waitLoading();
		this.selectPermitType(lottery.permitTypes);
		this.selectMaxiNum(lottery.maxNumber);
		this.addPreferenceAttributes(lottery.attributes);

		if (lottery.isCollectCreditCard) {
			this.selectCreditCard();
		}
		if (lottery.isRequiredByPermitType) {
			this.selectRequiredPermit();
		}
		if (lottery.isRequiresCustomerAcceptance) {
			this.selectCustomerAcceptance();
		}

		int submissionRuleCounter = 1; /*
										 * tricky part, because on lottery
										 * details page there is always one
										 * hidden element.
										 */
		if (lottery.isMaxNumPerPrimaryOccu) {
			this.clickAddNewSubRules();
			this.selectSubmissionRules("Maximum Number per Primary Occupant",
					submissionRuleCounter);
			this.setMaxNumOfPriOccupant(lottery.maxNumOfPriOccupant);
			submissionRuleCounter += 1;
		}

		if (lottery.isMinStay) {
			this.clickAddNewSubRules();
			this.selectSubmissionRules("Minimum Stay", submissionRuleCounter);
			this.setMinStay(lottery.minStay);
			submissionRuleCounter += 1;
		}

		if (lottery.isSpecialStayStart) {
			this.clickAddNewSubRules();
			this.selectSubmissionRules("Specify Stay Start",
					submissionRuleCounter);

			// parse the stay start string;
			String dayOfWeeks = lottery.stayStart.trim();
			if (null != dayOfWeeks && dayOfWeeks.length() > 0) {
				String[] day = dayOfWeeks.split(",");
				for (int i = 0; i < day.length; i++) {
					this.setStayStart(day[i]);
				}
			}
			submissionRuleCounter += 1;
		}

		// Setup submission rules by Lesley[20131212]
		if (lottery.submissionRules != null && lottery.submissionRules.size() > 0) {
			for (int i = 0; i < lottery.submissionRules.size(); i++) {
				SubmissionRulePara subRule = lottery.submissionRules.get(i);
				this.clickAddNewSubRules();
				this.waitLoading();
				this.selectSubmissionRules(subRule.ruleName, i+1);
				this.setMaxNumOfPriOccupant(subRule.maxNum, i+1);
				if (StringUtil.notEmpty(subRule.ticketCategory)) {
					this.selectSubTicketCategory(subRule.ticketCategory, i+1);
					this.waitLoading();
				}
			}
		}
		
		if (null != lottery.awardRules && lottery.awardRules.size() > 0) {
			for (int i = 0; i < lottery.awardRules.size(); i++) {
				AwardRulePara awardRule = lottery.awardRules.get(i);
				this.clickAddNewWardRule();
				this.selectAwardRules(awardRule.ruleName, i + 1);
				this.setMaxNumForAwardRule(awardRule.maxNumber,
						i + 1);
				if (StringUtil.notEmpty(awardRule.ticketCategory)) {
					this.selectAwardTicketCategory(awardRule.ticketCategory, i+1);
					this.waitLoading();
				}
			}
		}

		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();
		if (this.isErrorOccur()) {
			 throw new ErrorOnPageException(
			 "Error on creating lottery application, please check your data.");
		} else {
			lottery.id = this.getLotteryId();
		}
		this.clickOK();
		ajax.waitLoading();
		return lottery.id;
	}

	/** Click Add link to add new preference attributes. */
	public void clickAddNewPrefAttribute() {
		IHtmlObject[] objs = browser.getHtmlObject(".id",
				"preference_attributes");
		browser.clickGuiObject(".class", "Html.A", ".text", "Add", true, 0,
				objs[0]);
		Browser.unregister(objs);
	}

	/** Retrieve submission rule frame object. */
	public IHtmlObject getSubRuleTable() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"submission_rules");
		return objs[0];
	}

	/** Click Add link to add new preference attributes. */
	public void clickAddNewSubRules() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add", true, 0,
				getSubRuleTable());
	}

	/** select submission rules */
	public void selectSubmissionRules(String rule) {
		browser.selectDropdownList(".id", "submission_rule", rule);
	}

	public int getSubmissionRuleDropDownListObjectLength() {
		IHtmlObject[] objs = browser.getDropdownList(".id", "submission_rule");
		int length = objs.length;
		Browser.unregister(objs);
		return length;
	}
	
	public int getPreferenceAttrRemoveObjectLength() {
		IHtmlObject[] removeObjs = browser.getHtmlObject(".class", "Html.A",
				".text", "Remove", this.getPreferenceAttributesTbody());
		int length = removeObjs.length;
		Browser.unregister(removeObjs);
		return length;
	}
	
	public void clickRemovePreferenceAttr(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true,
				index, getPreferenceAttributesTbody());
	}

	public int getSubmissionRuleRemoveObjectLength() {
		IHtmlObject[] removeObjs = browser.getHtmlObject(".class", "Html.A",
				".text", "Remove", this.getSubRuleTable());
		int length = removeObjs.length;
		Browser.unregister(removeObjs);
		return length;
	}

	public void clickRemoveSubmissionRule(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true,
				index + 1, getSubRuleTable());
	}

	public IHtmlObject[] getAwardRuleTable() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "award_rules");
		return objs;
	}

	public void clickAddNewWardRule() {
		IHtmlObject[] objs = this.getAwardRuleTable();
		browser.clickGuiObject(".class", "Html.A", ".text", "Add", true, 0,
				objs[0]);
		Browser.unregister(objs);
	}

	public int getAwardRuleDropDownListObjectLength() {
		IHtmlObject[] objs = browser.getDropdownList(".id", "award_rule");
		int length = objs.length;
		Browser.unregister(objs);
		return length;
	}

	public int getAwardRuleRemoveObjectLength() {
		IHtmlObject[] objs = this.getAwardRuleTable();
		IHtmlObject[] removeObjs = browser.getHtmlObject(".class", "Html.A",
				".text", "Remove", objs[0]);
		int length = removeObjs.length;
		Browser.unregister(removeObjs);
		Browser.unregister(objs);
		return length;
	}

	public List<AwardRulePara> getAwardRulesPara(Lottery lottery) {
		List<AwardRulePara> rules = new ArrayList<AwardRulePara>();

		IHtmlObject[] objs = this.getAwardRuleTable();
		IHtmlTable table = (IHtmlTable) objs[0];

		for (int i = 1; i < table.rowCount() - 3; i++) {
			AwardRulePara para = lottery.new AwardRulePara();
			para.ruleName = table.getCellValue(i, 0);
			para.maxNumber = this.getMaxNumForAwardRule(i);
			rules.add(para);
		}
		Browser.unregister(objs);
		return rules;
	}

	private String getMaxNumForAwardRule(int index) {
		String value = browser
				.getTextFieldValue(".id", "award_rule_max", index);
		return value;
	}
	
	private String getMaxNumForSubmissionRule(int index) {
		String value = browser
				.getTextFieldValue(".id", "submission_rule_max", index);
		return value;
	}

	public void clickRemoveAwardRule(int index) {
		IHtmlObject[] objs = this.getAwardRuleTable();
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true,
				index + 1, objs[0]);
		Browser.unregister(objs);
	}

	public IHtmlObject[] getPrdGroupsTable() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "product_groups");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Did not found product group table object.");
		}
		return objs;
	}

	public boolean checkAddPrdGroupsIsDisabled() {
		IHtmlObject[] objs = this.getPrdGroupsTable();
		IHtmlObject[] addObjs = browser.getHtmlObject(".class", "Html.A",
				".text", "Add", objs[0]);

		boolean isDisabled;
		if (addObjs.length < 1) {
			isDisabled = true;
		} else {
			isDisabled = false;
		}

		Browser.unregister(addObjs);
		Browser.unregister(objs);
		return isDisabled;
	}

	public int getPrdGroupDropDownListObjectLength() {
		IHtmlObject[] objs = this.getPrdGroupsTable();

		IHtmlObject[] prdGroupObjs = browser.getDropdownList(
				Property.toPropertyArray(".id", "product_group"), objs[0]);
		int length = prdGroupObjs.length;
		Browser.unregister(objs);
		Browser.unregister(prdGroupObjs);
		return length;
	}

	public int getPrdGroupRemoveObjectLength() {
		IHtmlObject[] objs = this.getPrdGroupsTable();
		IHtmlObject[] removeObjs = browser.getHtmlObject(".class", "Html.A",
				".text", "Remove", objs[0]);
		int length = removeObjs.length;
		Browser.unregister(objs);
		Browser.unregister(removeObjs);
		return length;
	}

	public void clickAddNewPrdGroups() {
		IHtmlObject[] objs = this.getPrdGroupsTable();
		browser.clickGuiObject(".class", "Html.A", ".text", "Add", true, 0,
				objs[0]);
		Browser.unregister(objs);
	}

	public void clickRemovePrdGroups(int index) {
		IHtmlObject[] objs = this.getPrdGroupsTable();
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true,
				index + 1, objs[0]);
	}

	public void selectPrdGroups(String groups, int index) {
		browser.selectDropdownList(".id", "product_group", groups, index + 1);
	}

	/**
	 * @param rule
	 * @param index
	 *            ; the index value of Submission Rules dropdown list value; the
	 *            index should start from 1, because there is always 1 element
	 *            is hidden.
	 */
	public void selectSubmissionRules(String rule, int index) {
		Property[] property = new Property[] { new Property(".id",
				"submission_rule") };
		IHtmlObject[] objs = browser.getDropdownList(property);
		ISelect submission = (ISelect) (objs[index]);

		submission.select(rule);
		Browser.unregister(objs);
	}

	public void selectAwardRules(String rule, int index) {
		// browser.selectDropdownList(".id", "award_rule", rule, index);

		Property[] property = new Property[] { new Property(".id", "award_rule") };
		IHtmlObject[] objs = browser.getDropdownList(property);
		ISelect submission = (ISelect) (objs[index]);

		submission.select(rule);
		Browser.unregister(objs);
	}

	public void setMaxNumForAwardRule(String num, int index) {
		browser.setTextField(".id", "award_rule_max", num, index);
	}

	public void selectSubTicketCategory(String cat, int index) {
		IHtmlObject[] objs = browser.getDropdownList(this.submissionTicketCatList());
		ISelect list = (ISelect) (objs[index]);

		list.select(cat);
		Browser.unregister(objs);
	}
	
	public void selectAwardTicketCategory(String cat, int index) {
		IHtmlObject[] objs = browser.getDropdownList(this.awardTicketCatList());
		ISelect list = (ISelect) (objs[index]);

		list.select(cat);
		Browser.unregister(objs);
	}
	
	public void setMaxNumOfPriOccupant(String num, int index) {
		browser.setTextField(".id", "submission_rule_max", num, index);
	}

	/** Fill in the number of maximum number per primary occupant. */
	public void setMaxNumOfPriOccupant(String number) {
		browser.setTextField(".id", "submission_rule_max", number, true, 0,
				getSubRuleTable());

		Property[] property = new Property[] {
				new Property(".class", "Html.DIV"),
				new Property(".id", "submission_rule"),
				new Property(".text", new RegularExpression(
						"^Maximum Number.*", false)) };

		// first level top object
		IHtmlObject[] topObjs = browser.getHtmlObject(property);
		int index = -1;

		for (int i = 1; i < topObjs.length; i++) { // index begin from 1,
			// because the 1st one
			// element is always hidden.
			if (!topObjs[i].style("display").equalsIgnoreCase("none")) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			throw new ErrorOnPageException(
					"can't find top object for Start Stay Day of Week check boxs");
		} else {
			// target check box
			browser.setTextField(".id", "submission_rule_max", number, true, 0,
					topObjs[index]);
		}
		Browser.unregister(topObjs);
	}

	/** Fill in the number of minimum stay rule. */
	public void setMinStay(String number) {
		Property[] property = new Property[] {
				new Property(".class", "Html.DIV"),
				new Property(".id", "submission_rule"),
				new Property(".text", new RegularExpression(
						"^Minimum Stay\\(days\\).*", false)) };

		// first level top object
		IHtmlObject[] topObjs = browser.getHtmlObject(property);
		int index = -1;

		for (int i = 1; i < topObjs.length; i++) { // index begin from 1,
			// because the 1st one
			// element is always hidden.
			if (!topObjs[i].style("display").equalsIgnoreCase("none")) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			throw new ErrorOnPageException(
					"can't find top object for Start Stay Day of Week check boxs");
		} else {
			// target check box
			browser.setTextField(".id", "ruleAttribute_60", number, true, 0,
					topObjs[index]);
		}
		Browser.unregister(topObjs);
	}

	/**
	 * Fill in the start date of the specify stay start
	 * 
	 * @param dayOfWeek
	 *            ; Mon, Tue, Wed, Thu, Fri, Sat, Sun
	 */
	public void setStayStart(String dayOfWeek) {
		Property[] property = new Property[] {
				new Property(".class", "Html.DIV"),
				new Property(".id", "submission_rule"),
				new Property(".text", new RegularExpression(
						"^Start Stay Day Of Week.*", false)) };

		// first level top object
		IHtmlObject[] topObjs = browser.getHtmlObject(property);
		int value = DateFunctions.getDayOfWeekByWeekDay(dayOfWeek);
		
		browser.selectCheckBox(".id","ruleAttribute_70",".value",String.valueOf(value),topObjs.length-1);
		Browser.unregister(topObjs);
	}

	public void setPrefAttrLabel(String label, int attrTableRowIndex) {
		if (StringUtil.notEmpty(label)) {
			IHtmlObject tr = getPrefAttrRows()[attrTableRowIndex];
			setPrefAttrLabel(tr, label);
		}
	}

	public void setPrefAttrDisplayOrder(String displayOrder,
			int attrTableRowIndex) {
		if (displayOrder != null) {
			IHtmlObject tr = getPrefAttrRows()[attrTableRowIndex];
			setPrefAttrDisplayOrder(tr, displayOrder);
		}
	}

	public void setPrefAttrEntryRequired(String entryRequired,
			int attrTableRowIndex) {
		if (StringUtil.notEmpty(entryRequired)) {
			IHtmlObject tr = getPrefAttrRows()[attrTableRowIndex];
			setPrefAttrEntryRequired(tr, entryRequired);
		}
	}

	public void setPrefAttrFixedValue(String fixedValue, int attrTableRowIndex) {
		if (fixedValue != null) {
			IHtmlObject tr = getPrefAttrRows()[attrTableRowIndex];
			setPrefAttrFixedValue(tr, fixedValue);
		}
	}

	public void setPrefAttribute(LotteryPreferenceAttribute attr,
			int tableRowIndex) {
		setPrefAttrLabel(attr.label, tableRowIndex);
		setPrefAttrDisplayOrder(attr.displayOrder, tableRowIndex);
		setPrefAttrEntryRequired(attr.entryRequired, tableRowIndex);
		setPrefAttrFixedValue(attr.fixedValue, tableRowIndex);
	}

	public void addPrefAttribute(LotteryPreferenceAttribute attr) {
		this.clickAddNewPrefAttribute();

		IHtmlObject[] rows = getPrefAttrRows();
		int lastRow = rows.length - 1;

		setPrefAttribute(attr, lastRow);
	}

	public LotteryPreferenceAttribute getPrefAttr(int attrTableRowIndex) {
		IHtmlObject tr = getPrefAttrRows()[attrTableRowIndex];
		return getPrefAttr(tr).get(0);
	}

	/**
	 * Get all preferenceAttributes on detail page
	 * 
	 * @return A list of {@link LotteryPreferenceAttribute} contains all records
	 *         on detail page
	 */
	public List<LotteryPreferenceAttribute> getPreferenceAttributes() {
		IHtmlObject[] rows = getPrefAttrRows();
		return getPrefAttr(rows);
	}

	private List<LotteryPreferenceAttribute> getPrefAttr(IHtmlObject... rows) {
		List<LotteryPreferenceAttribute> attributes = new ArrayList<LotteryPreferenceAttribute>();

		for (int i = 0; i < rows.length; i++) {
			String label = getPrefAttrLabelDropdown(rows[i]).getSelectedText();
			if (StringUtil.notEmpty(label)) {
				LotteryPreferenceAttribute attr = new LotteryPreferenceAttribute(
						label);

				attr.displayOrder = getPrefAttrDisplayOrderFieldValue(rows[i]);
				attr.entryRequired = getPrefAttrEntryRequiredDropdown(rows[i])
						.getSelectedText();

				ISelect fixedValue = getPrefAttrFixedValueDropdown(rows[i]);
				if (fixedValue != null) {
					attr.fixedValue = fixedValue.getSelectedText();
				}

				attributes.add(attr);
			}
		}
		return attributes;
	}

	/**
	 * This will merge passed in {@code attrs} with existing records(Default
	 * attributes) as below:
	 * <ul>
	 * <li>
	 * DisplayOrder will be automatically generated if empty, validate value is
	 * from <code>1</code> to <code>mergedResult.length</code>, the higher in
	 * the attribute table, smaller value will be assigned</code><br>
	 * &nbsp;</li>
	 * <li>
	 * If "label" of existing record doesn't exist in <code>attrs</code>,
	 * connect these two collection. For example:<br>
	 * <br>
	 * <code>attrs:</code>
	 * <table border="1">
	 * <tr>
	 * <td><b>label</b></td>
	 * <td><b>displayOrder</b></td>
	 * </tr>
	 * <tr>
	 * <td>Entrance</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 * <br>
	 * On page records:
	 * <table border="1">
	 * <tr>
	 * <td><b>label</b></td>
	 * <td><b>displayOrder</b></td>
	 * </tr>
	 * <tr>
	 * <td>Entry Date</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * <tr>
	 * <td>Facility</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * </table>
	 * <br>
	 * Result will be:
	 * <table border="1">
	 * <tr>
	 * <td><b>label</b></td>
	 * <td><b>displayOrder</b></td>
	 * </tr>
	 * <tr>
	 * <td>Entry Date</td>
	 * <td>2</td>
	 * </tr>
	 * <tr>
	 * <td>Facility</td>
	 * <td>3</td>
	 * </tr>
	 * <tr>
	 * <td>Entrance</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 * </li>
	 * <li>
	 * If "label" of existing record exists in <code>attrs</code>, use the
	 * passed in value to replace existing record, like:<br>
	 * <br>
	 * <code>attrs:</code>
	 * <table border="1">
	 * <tr>
	 * <td><b>label</b></td>
	 * <td><b>displayOrder</b></td>
	 * </tr>
	 * <tr>
	 * <td>Entrance</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>Facility</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 * <br>
	 * On page records:
	 * <table border="1">
	 * <tr>
	 * <td><b>label</b></td>
	 * <td><b>displayOrder</b></td>
	 * </tr>
	 * <tr>
	 * <td>Entry Date</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * <tr>
	 * <td>Facility</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * <tr>
	 * <td>PermitType</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * </table>
	 * <br>
	 * Result will be:
	 * <table border="1">
	 * <tr>
	 * <td><b>label</b></td>
	 * <td><b>displayOrder</b></td>
	 * </tr>
	 * <tr>
	 * <td>Entry Date</td>
	 * <td>3</td>
	 * </tr>
	 * <tr>
	 * <td>Facility</td>
	 * <td>2</td>
	 * </tr>
	 * <tr>
	 * <td>PermitType</td>
	 * <td>4</td>
	 * </tr>
	 * <tr>
	 * <td>Entrance</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 * </li>
	 * 
	 * @param attrs
	 *            Attributes list passed in
	 */
	public void addPreferenceAttributes(List<LotteryPreferenceAttribute> attrs) {
		if (attrs == null) {
			attrs = new ArrayList<LotteryPreferenceAttribute>();
		}
		mergeWithRecordOnPage(attrs);
		generateDisplayOrder(attrs);
		this.setPrefAttrLabelInfo(attrs);
		this.setPreAttrOtherInfo(attrs);
	}

	public void setPrefAttrLabelInfo(List<LotteryPreferenceAttribute> attrs) {
		IHtmlObject[] rows = getPrefAttrRows();
		for (int i = 0; i < rows.length; i++) {
			LotteryPreferenceAttribute attr = attrs.get(i);
			setPrefAttrLabel(rows[i], attr.label);
		}
	}

	public void setPreAttrOtherInfo(List<LotteryPreferenceAttribute> attrs) {
		IHtmlObject[] rows = getPrefAttrRows();
		for (int i = 0; i < rows.length; i++) {
			LotteryPreferenceAttribute attr = attrs.get(i);
//			setPrefAttrDisplayOrder(rows[i], attr.displayOrder);
			setPrefAttrDisplayOrder(getPrefAttrRows()[i], attr.displayOrder);
//			setPrefAttrEntryRequired(rows[i], attr.entryRequired);
			setPrefAttrEntryRequired(getPrefAttrRows()[i], attr.entryRequired);
			if (!"".equals(attr.fixedValue)) {
				setPrefAttrFixedValue(getPrefAttrRows()[i], attr.fixedValue);
			}
		}
	}

	private void generateDisplayOrder(List<LotteryPreferenceAttribute> attrs) {
		List<Integer> existingOrders = getExistingOrders(attrs);

		int position = 1;
		for (LotteryPreferenceAttribute attr : attrs) {

			if (!StringUtil.notEmpty(attr.displayOrder)) {

				while (existingOrders.contains(position)) {
					position++;
				}
				attr.displayOrder = String.valueOf(position);
			}

			if (attr.displayOrder.equals(String.valueOf(position))) {
				position++;
			}
		}
	}

	private List<Integer> getExistingOrders(
			List<LotteryPreferenceAttribute> attrs) {
		List<Integer> existingOrders = new ArrayList<Integer>();
		for (LotteryPreferenceAttribute attr : attrs) {
			if (StringUtil.notEmpty(attr.displayOrder)) {
				existingOrders.add(Integer.valueOf(attr.displayOrder));
			}
		}
		return existingOrders;
	}

	public void mergeWithRecordOnPage(List<LotteryPreferenceAttribute> attrs) {
		IHtmlObject[] rows = getPrefAttrRows();
		List<LotteryPreferenceAttribute> onPageAttrs = getPrefAttr(rows);
		for (int i = 0; i < onPageAttrs.size(); i++) {
			LotteryPreferenceAttribute onPageAttr = onPageAttrs.get(i);
			boolean found = false;
			for (int j = attrs.size() - 1; j >= 0; j--) {

				LotteryPreferenceAttribute attr = attrs.get(j);

				if (onPageAttr.label.equals(attr.label)) {
					if (i != j) {
						// Move position
						attrs.remove(j);
						attrs.add(i, attr);
					}
					found = true;
				}
			}
			if (!found) {
				attrs.add(i, onPageAttr);
			}
		}

		// Click add new preference attribute
		for (int i = 0; i < attrs.size() - onPageAttrs.size(); i++) {
			clickAddNewPrefAttribute();
		}
	}

	private void setPrefAttrFixedValue(IHtmlObject tr, String fixedValue) {
		ISelect dropdown = getPrefAttrFixedValueDropdown(tr);
		if ((dropdown == null || !isEnabled(dropdown)) && fixedValue != null) {
			throw new IllegalStateException(
					"You passed fixed value"
							+ "<"
							+ fixedValue
							+ ">"
							+ "in but the dropdown couldn't be found or disabled, please check your data setup");
		} else {
			if (fixedValue != null) {
				dropdown.select(fixedValue);
			}
		}
	}

	private void setPrefAttrEntryRequired(IHtmlObject tr, String entryRequired) {
		ISelect dropdown = getPrefAttrEntryRequiredDropdown(tr);
		if (isEnabled(dropdown)) {
			if (entryRequired != null) {
				dropdown.select(entryRequired);

				// Page may reload be the value you selected, sleep for a while
				Browser.sleep(1);
				this.waitLoading();
			}
		}
	}

	private void setPrefAttrDisplayOrder(IHtmlObject tr, String displayOrder) {
		IText textField = getPrefAttrDisplayOrderField(tr);
		textField.setText(displayOrder);
	}

	private void setPrefAttrLabel(IHtmlObject tr, String label) {
		ISelect dropdown = getPrefAttrLabelDropdown(tr);
		if (isEnabled(dropdown)) {
			dropdown.select(label);
		}
		// browser.selectDropdownList(".id", "attr_id", label, false, tr);
	}

	private boolean isEmpty(IHtmlObject htmlObject) {
		return !StringUtil.notEmpty(htmlObject.text());
	}

	private ISelect getPrefAttrLabelDropdown(IHtmlObject tr) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",
				".id", "attr_id", tr);
		System.out.println(objs.length);
		return (ISelect) browser.getHtmlObject(".class", "Html.SELECT",
				".id", "attr_id", tr)[0];
	}

	private IText getPrefAttrDisplayOrderField(IHtmlObject tr) {
		return (IText) browser.getHtmlObject(".class",
				"Html.INPUT.text", ".id", "display_order", tr)[0];
	}

	private String getPrefAttrDisplayOrderFieldValue(IHtmlObject tr) {
		Property[] p = new Property[1];
		Property id = new Property(".id", "display_order");
		p[0] = id;

		String text = browser.getTextFieldValue(p, tr);
		return text;
	}

	private ISelect getPrefAttrEntryRequiredDropdown(IHtmlObject tr) {
		return (ISelect) browser.getHtmlObject(".class", "Html.SELECT",
				".id", "entry_required", tr)[0];
	}

	private ISelect getPrefAttrFixedValueDropdown(IHtmlObject tr) {
		IHtmlObject[] fixedValue = browser.getHtmlObject(".class",
				"Html.SELECT", ".id", "fixed_value", tr);
		if (fixedValue != null && fixedValue.length > 0) {
			return (ISelect) fixedValue[0];
		}
		return null;
	}

	/*
	 * Return all Trs of Preference attribute table
	 */
	private IHtmlObject[] getPrefAttrRows() {
		IHtmlObject tbody = getPreferenceAttributesTbody();
		IHtmlObject[] trs = browser.getHtmlObject(".class", "Html.TR", tbody);
		return filterOutEmptyTr(trs);
	}

	/*
	 * There is an empty TR under default preference attribute row, need to
	 * filter it out
	 */
	private IHtmlObject[] filterOutEmptyTr(IHtmlObject[] trs) {
		List<IHtmlObject> result = new ArrayList<IHtmlObject>();
		for (int i = 0; i < trs.length; i++) {
			if (!isEmpty(trs[i])) {
				result.add(trs[i]);
			}
		}
		return result.toArray(new IHtmlObject[0]);
	}

	private IHtmlObject getPreferenceAttributesTbody() {
		return getVisibleTbodyById("preference_attributes");
	}

	private IHtmlObject getPermitTypeDropdownParentTbody() {
		return getVisibleTbodyById("permit_types");
	}

	private IHtmlObject getVisibleTbodyById(String id) {
		IHtmlObject permitTypeTable = browser.getHtmlObject(".class",
				"Html.TABLE", ".id", id)[0];
		IHtmlObject[] tbodys = browser.getHtmlObject(".class", "Html.TBODY",
				permitTypeTable);

		/*
		 * Here is a hidden TBODY in table which id=permit_types, it also
		 * contains a SELECT which id is "permit_type", but it's never used,
		 * need to filter it out. same for "preference_attributes" table
		 */
		return filterOutHiddenTbody(tbodys);
	}

	private IHtmlObject filterOutHiddenTbody(IHtmlObject[] tbodys) {
		IHtmlObject tbody = null;
		for (IHtmlObject obj : tbodys) {
			if (!StringUtil.notEmpty(obj.getProperty(".className"))) {
				tbody = obj;
				break;
			}
		}
		return tbody;
	}

	private boolean isEnabled(IHtmlObject obj) {
		return !Boolean.valueOf(obj.getProperty(".isDisabled"));
	}

	public Lottery getLotteryInfo(Lottery expect) {
		Lottery info = new Lottery();

		/* get lottery information */
		info.name = this.getLotteryName();
		info.description = this.getDescription();
		info.revenueLocation = this.getRevenueLocation();

		/* get Applicable */
		info.productCategory = this.getProductCategory();
		info.productGroups = this.getProductGroups();

		/* get Preferences to be Collected */
		info.attributes = this.getPreferenceAttributes();

		/* get Other Information to be Collected */
		info.isCollectCreditCard = this.isSelectCreditCard();
		/* get Customer Acceptance Of Awarded Reservation */
		info.isRequiresCustomerAcceptance = this.isSelectCustomerAcceptance();

		/* get Lottery Application Submission Rules */// TODO other rule need
														// adding
		if (expect.isMaxNumPerPrimaryOccu) {
			info.maxNumOfPriOccupant = this.getMaxNumForSubmissionRule(1);
		}

		/* get Lottery Award Rules */
		info.awardRules = this.getAwardRulesPara(info);

		return info;
	}

	public void compareLotteryProgrammerInfo(Lottery expect, Lottery actually) {
		boolean result = true;

		/* compare lottery information */
		result &= MiscFunctions.compareResult("Lottery Name", expect.name,
				actually.name);
		result &= MiscFunctions.compareResult("Description",
				expect.description, actually.description);
		result &= MiscFunctions.compareResult("Revenue Location",
				expect.revenueLocation, actually.revenueLocation);

		/* compare Applicable */
		result &= MiscFunctions.compareResult("Product Category",
				expect.productCategory, actually.productCategory);
		if (null != expect.productGroups) {
			for (int i = 0; i < expect.productGroups.size(); i++) {
				result &= MiscFunctions.compareResult("Product Groups",
						expect.productGroups.get(i),
						actually.productGroups.get(i));
			}
		}

		/* compare Preferences to be Collected */
		for (int i = 0; i < expect.attributes.size(); i++) {
			result &= MiscFunctions.compareResult("Label",
					expect.attributes.get(i).label,
					actually.attributes.get(i).label);
			result &= MiscFunctions.compareResult("Display Order",
					expect.attributes.get(i).displayOrder,
					actually.attributes.get(i).displayOrder);

		}

		/* compare Other Information to be Collected */
		result &= MiscFunctions.compareResult(
				"Other Information to be Collected",
				expect.isCollectCreditCard, actually.isCollectCreditCard);
		/* compare Customer Acceptance Of Awarded Reservation */
		result &= MiscFunctions.compareResult(
				"Customer Acceptance Of Awarded Reservation",
				expect.isRequiresCustomerAcceptance,
				actually.isRequiresCustomerAcceptance);

		/* compare Lottery Application Submission Rules */// TODO other rule
		// need adding
		if (expect.isMaxNumPerPrimaryOccu) {
			result &= MiscFunctions.compareResult(
					"Max Number Per Primary Occupant",
					expect.maxNumOfPriOccupant, actually.maxNumOfPriOccupant);
		}

		/* compare Lottery Award Rules */
		if (null != expect.awardRules) {
			for (int i = 0; i < expect.awardRules.size(); i++) {
				String expectValue = expect.awardRules.get(i).ruleName;
				String actuallyValue = actually.awardRules.get(i).ruleName;
				result &= MiscFunctions.compareResult("Rule Name", expectValue,
						actuallyValue);
				expectValue = expect.awardRules.get(i).maxNumber;
				actuallyValue = actually.awardRules.get(i).maxNumber;
				result &= MiscFunctions.compareResult("Maximum Number",
						expectValue, actuallyValue);
			}
		}

		if (!result) {
			throw new ErrorOnPageException(
					"lottery content is not correct, please check the log...");
		}
	}
}
