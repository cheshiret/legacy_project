package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.TaxInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrProductTaxWidget extends LicMgrProductPricingCommonWidget {
	private static LicMgrProductTaxWidget instance = null;

	private LicMgrProductTaxWidget() {
		super(String.valueOf(new RegularExpression(
				"Edit Product Pricing|Add Product Pricing", false)));
	}

	public static LicMgrProductTaxWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrProductTaxWidget();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text",
				"Vendor Fee Tax Details") && super.exists();
	}

	private Property[] taxname() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"TaxScheduleView-\\d+\\.taxTypeID", false));
	}

	private Property[] rate() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"TaxScheduleView-\\d+\\.rate", false));
	}

	private Property[] splitPercentValue() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"TaxScheduleSplitView-\\d+\\.value", false));
	}

	private Property[] flatrate() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"TaxScheduleView-\\d+\\.rate", false));
	}

	private Property[] taxCalrate() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"TaxScheduleView-\\d+\\.taxCalculationRate", false));
	}

	private Property[] taxAmount() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"TaxScheduleSplitView-\\d+\\.value", false));
	}

	private Property[] adjustRate() {
		return Property.toPropertyArray(".class", "Html.SPAN", ".text",
				new RegularExpression("Adjusted Rate.*", false));
	}

	private Property[] account() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"TaxScheduleSplitView-\\d+\\.account", false));
	}

	private Property[] percentOrAmount() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"TaxScheduleSplitView-\\d+\\.value", false));
	}

	private Property[] addTax() {
		return Property.toPropertyArray(".class", "Html.A", ".text",
				"Add Taxes");
	}

	private Property[] remove() {
		return Property.toPropertyArray(".class", "Html.A", ".id",
				new RegularExpression(".*delete", false));
	}

	private Property[] splitByPercent() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"TaxScheduleView-\\d+\\.taxSchdSplitType0", false));
	}

	private Property[] splitByFeeSplit() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"TaxScheduleView-\\d+\\.taxSchdSplitType1", false));
	}

	private Property[] splitInto() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"TaxScheduleView-\\d+\\.taxScheduleSplitNum", false));
	}

	private Property[] vendorFeeTable() {
		return Property.toPropertyArray(".id",
				"vendorFeeTaxPanelBarContentTable");
	}

	private Property[] stateFeeTable() {
		return Property.toPropertyArray(".id",
				"stateFeeTaxPanelBarContentTable");
	}

	private Property[] transactionFeeTable() {
		return Property.toPropertyArray(".id",
				"transFeeTaxPanelBarContentTable");
	}
	//added by Summer
	private Property[] taxSplitTable(){
		return Property.toPropertyArray(".id",new RegularExpression(
				"\\d+_taxScheduleSplit_\\d+", false));
	}

	public IHtmlTable getVendorFeeTable() {
		IHtmlObject[] objs = browser.getTableTestObject(vendorFeeTable());

		if (objs.length < 1) {
			throw new ErrorOnPageException("Could not find vendor fee table...");
		}

		IHtmlTable table = (IHtmlTable) objs[0];

		// Browser.unregister(objs);

		return table;
	}

	public IHtmlTable getStateFeeTable() {
		IHtmlObject[] objs = browser.getTableTestObject(stateFeeTable());

		if (objs.length < 1) {
			throw new ErrorOnPageException("Could not find state fee table...");
		}

		IHtmlTable table = (IHtmlTable) objs[0];

		// Browser.unregister(objs);

		return table;
	}

	public IHtmlTable getTransactionFeeTable() {
		IHtmlObject[] objs = browser.getTableTestObject(transactionFeeTable());

		if (objs.length < 1) {
			throw new ErrorOnPageException(
					"Could not find transaction fee table...");
		}

		IHtmlTable table = (IHtmlTable) objs[0];

		// Browser.unregister(objs);

		return table;
	}
	//added by summer
	public IHtmlTable getTaxSplitTable(int taxIndex,IHtmlTable parent) {
		IHtmlObject[] objs = browser.getTableTestObject(taxSplitTable(),parent);
		if (objs.length < 1) {
			throw new ErrorOnPageException(
					"Could not find transaction fee table...");
		}
		IHtmlTable table = (IHtmlTable) objs[taxIndex];
		return table;
		
	}
	public void clickAddTaxesForVendorFee() {
		IHtmlTable table = this.getVendorFeeTable();
		browser.clickGuiObject(addTax(), false, 0, table);
	}

	public void clickRemoveAllTaxesForVendorFee() {
		IHtmlTable table = this.getVendorFeeTable();
		browser.clickGuiObject(remove(), false, 0, table);
	}

	public void clickAddTaxesForStateFee() {
		IHtmlTable table = this.getStateFeeTable();
		browser.clickGuiObject(addTax(), false, 0, table);
	}

	public void clickRemoveAllTaxesForStateFee() {
		IHtmlTable table = this.getStateFeeTable();
		browser.clickGuiObject(remove(), false, 0, table);
	}

	public void clickAddTaxesForTransactionFee() {
		IHtmlTable table = this.getTransactionFeeTable();
		browser.clickGuiObject(addTax(), false, 0, table);
	}

	public void clickRemoveAllTaxesForTransactionFee() {
		IHtmlTable table = this.getTransactionFeeTable();
		browser.clickGuiObject(remove(), false, 0, table);
	}

	public void selectTaxNameForVendorFee(String tax, int objectindex) {
		IHtmlTable table = this.getVendorFeeTable();
		browser.selectDropdownList(taxname(), tax, false, objectindex, table);
	}

	public void selectTaxNameForStateFee(String tax, int objectindex) {
		IHtmlTable table = this.getStateFeeTable();
		browser.selectDropdownList(taxname(), tax, false, objectindex, table);
	}

	public void selectSplitIntoForStateFee(String num, int objectindex) {
		IHtmlTable table = this.getStateFeeTable();
		browser.selectDropdownList(splitInto(), num, false, objectindex, table);
	}

	public void selectSplitIntoForTransFeeTax(String num, int objectindex) {
		IHtmlTable table = this.getTransactionFeeTable();
		browser.selectDropdownList(splitInto(), num, false, objectindex, table);
	}
	public void selectSplitIntoForTaxTable(String num, int objectindex,String taxFeeType) {
		IHtmlTable parentTable=this.getParentTableByFeeTaxType(taxFeeType);
		browser.selectDropdownList(splitInto(), num, false, objectindex, parentTable);
	} 
	public void selectTaxNameForTransactionFee(String tax, int objectindex) {
		IHtmlTable table = this.getTransactionFeeTable();
		browser.selectDropdownList(taxname(), tax, false, objectindex, table);
	}

	public List<String> getTaxNamesForVendorFee() {
		IHtmlTable table = this.getVendorFeeTable();
		IHtmlObject[] objs = browser.getDropdownList(taxname(), table);
		List<String> taxnames = new ArrayList<String>();
		if (objs.length < 1) {
			throw new ErrorOnPageException("Cann't find Tax Name object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				String value = browser
						.getDropdownListValue(taxname(), i, table);
				taxnames.add(value);
			}
		}
		return taxnames;
	}

	public List<String> getTaxNamesForStateFee() {
		IHtmlTable table = this.getStateFeeTable();
		IHtmlObject[] objs = browser.getDropdownList(taxname(), table);
		List<String> taxnames = new ArrayList<String>();
		if (objs.length < 1) {
			throw new ErrorOnPageException("Cann't find Tax Name object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				String value = browser
						.getDropdownListValue(taxname(), i, table);
				taxnames.add(value);
			}
		}
		return taxnames;
	}

	public List<String> getTaxNamesForTransactionFee() {
		IHtmlTable table = this.getTransactionFeeTable();
		IHtmlObject[] objs = browser.getDropdownList(taxname(), table);
		List<String> taxnames = new ArrayList<String>();
		if (objs.length < 1) {
			throw new ErrorOnPageException("Cann't find Tax Name object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				String value = browser
						.getDropdownListValue(taxname(), i, table);
				taxnames.add(value);
			}
		}
		return taxnames;
	}

	public void setRateForVendorFee(String rate, int objectindex) {
		IHtmlTable table = this.getVendorFeeTable();
		browser.setTextField(rate(), rate, false, objectindex, table);
	}

	public void setRateForStateFee(String rate, int objectindex) {
		IHtmlTable table = this.getStateFeeTable();
		browser.setTextField(rate(), rate, false, objectindex, table);
	}

	//added by Summer
	public void setSplitPercentRateForTaxTable(String rate, int taxIndex,int objectindex,String taxFeeType) {
		IHtmlTable parentTaxTable=this.getParentTableByFeeTaxType(taxFeeType);
		IHtmlTable taxSplitTable=getTaxSplitTable(taxIndex,parentTaxTable);
		browser.setTextField(splitPercentValue(), rate, false, objectindex,
				taxSplitTable);
	}

	public void setRateForTransactionFee(String rate, int objectindex) {
		IHtmlTable table = this.getTransactionFeeTable();
		browser.setTextField(rate(), rate, false, objectindex, table);
	}

	public List<String> getRatesForVendorFee() {
		IHtmlTable table = this.getVendorFeeTable();
		IHtmlObject[] objs = browser.getTextField(rate(), table);
		List<String> rates = new ArrayList<String>();

		if (objs.length < 1) {
			throw new ErrorOnPageException("Cann't find Rate object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				String value = browser.getTextFieldValue(rate(), i, table);
				rates.add(value);
			}
		}
		return rates;
	}

	public List<String> getRatesForStateFee() {
		IHtmlTable table = this.getStateFeeTable();
		IHtmlObject[] objs = browser.getTextField(rate(), table);
		List<String> rates = new ArrayList<String>();

		if (objs.length < 1) {
			throw new ErrorOnPageException("Cann't find Rate object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				String value = browser.getTextFieldValue(rate(), i, table);
				rates.add(value);
			}
		}
		return rates;
	}

	public List<String> getRatesForTransactionFee() {
		IHtmlTable table = this.getTransactionFeeTable();
		IHtmlObject[] objs = browser.getTextField(rate(), table);
		List<String> rates = new ArrayList<String>();

		if (objs.length < 1) {
			throw new ErrorOnPageException("Cann't find Rate object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				String value = browser.getTextFieldValue(rate(), i, table);
				rates.add(value);
			}
		}
		return rates;
	}

	public void setTaxCalRateForVendorFee(String rate, int objectindex) {
		IHtmlTable table = this.getVendorFeeTable();
		browser.setTextField(taxCalrate(), rate, false, objectindex, table);
	}

	public void setTaxCalRateForStateFee(String rate, int objectindex) {
		IHtmlTable table = this.getStateFeeTable();
		browser.setTextField(taxCalrate(), rate, false, objectindex, table);
	}

	public void setTaxCalRateForTransactionFee(String rate, int objectindex) {
		IHtmlTable table = this.getTransactionFeeTable();
		browser.setTextField(taxCalrate(), rate, false, objectindex, table);
	}

	public List<String> getTaxCalRateForVendorFee() {
		IHtmlTable table = this.getVendorFeeTable();
		IHtmlObject[] objs = browser.getTextField(taxCalrate(), table);
		List<String> rates = new ArrayList<String>();

		if (objs.length < 1) {
			throw new ErrorOnPageException(
					"Cann't find tax calculation rate object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				String value = browser
						.getTextFieldValue(taxCalrate(), i, table);
				rates.add(value);
			}
		}
		return rates;
	}

	public List<String> getTaxCalRateForStateFee() {
		IHtmlTable table = this.getStateFeeTable();
		IHtmlObject[] objs = browser.getTextField(taxCalrate(), table);
		List<String> rates = new ArrayList<String>();

		if (objs.length < 1) {
			throw new ErrorOnPageException(
					"Cann't find tax calculation rate object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				String value = browser
						.getTextFieldValue(taxCalrate(), i, table);
				rates.add(value);
			}
		}
		return rates;
	}

	public List<String> getTaxCalRateForTransactionFee() {
		IHtmlTable table = this.getTransactionFeeTable();
		IHtmlObject[] objs = browser.getTextField(taxCalrate(), table);
		List<String> rates = new ArrayList<String>();

		if (objs.length < 1) {
			throw new ErrorOnPageException(
					"Cann't find tax calculation rate object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				String value = browser
						.getTextFieldValue(taxCalrate(), i, table);
				rates.add(value);
			}
		}
		return rates;
	}

	public void setamountForVendorFee(String amount, int objectindex) {
		IHtmlTable table = this.getVendorFeeTable();
		browser.setTextField(taxAmount(), amount, false, objectindex, table);
	}

	public void setamountForStateFee(String amount, int objectindex) {
		IHtmlTable table = this.getStateFeeTable();
		browser.setTextField(taxAmount(), amount, false, objectindex, table);
	}

	public void setamountForTransactionFee(String amount, int objectindex) {
		IHtmlTable table = this.getTransactionFeeTable();
		browser.setTextField(taxAmount(), amount, false, objectindex, table);
	}

	public List<String> getAmountForVendorFee() {
		IHtmlTable table = this.getVendorFeeTable();
		IHtmlObject[] objs = browser.getTextField(taxAmount(), table);
		List<String> amt = new ArrayList<String>();

		if (objs.length < 1) {
			throw new ErrorOnPageException("Cann't find amount rate object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				String value = browser.getTextFieldValue(taxAmount(), i, table);
				amt.add(value);
			}
		}
		return amt;
	}

	public List<String> getAmountForStateFee() {
		IHtmlTable table = this.getStateFeeTable();
		IHtmlObject[] objs = browser.getTextField(taxAmount(), table);
		List<String> amt = new ArrayList<String>();

		if (objs.length < 1) {
			throw new ErrorOnPageException("Cann't find amount rate object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				String value = browser.getTextFieldValue(taxAmount(), i, table);
				amt.add(value);
			}
		}
		return amt;
	}

	public List<String> getAmountForTransactionFee() {
		IHtmlTable table = this.getTransactionFeeTable();
		IHtmlObject[] objs = browser.getTextField(taxAmount(), table);
		List<String> amt = new ArrayList<String>();

		if (objs.length < 1) {
			throw new ErrorOnPageException("Cann't find amount rate object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				String value = browser.getTextFieldValue(taxAmount(), i, table);
				amt.add(value);
			}
		}
		return amt;
	}


	public void selectAccountForVendorFee(String account, int objectindex) {
		IHtmlTable table = this.getVendorFeeTable();
		browser.selectDropdownList(account(), account, false, objectindex,
				table);
	}
	//added by Summer
	public void selecetAccountForTaxTable(String account,int taxIndex, int objectindex,String taxFeeType){
		IHtmlTable parentTaxTable=this.getParentTableByFeeTaxType(taxFeeType);
		IHtmlTable taxSplitTable=getTaxSplitTable(taxIndex,parentTaxTable);
		browser.selectDropdownList(account(), account, false, objectindex,
				taxSplitTable);
	}
	//added by Summer
	private List<String> getAccountForTaxTable(IHtmlTable parentTaxTable, int taxIndex) {
		IHtmlTable table=this.getTaxSplitTable(taxIndex, parentTaxTable);
		IHtmlObject[] objs = browser.getDropdownList(account(), table);
		List<String> account = new ArrayList<String>();
		if (objs.length < 1) {
			throw new ErrorOnPageException("Cann't find account object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				
				ISelect selectObj=(ISelect)objs[i];
				String value = selectObj.getSelectedText();
				account.add(value);
			}
		}

		Browser.unregister(objs);
		return account;
	}
	//added by Summer
	private List<String> getPercentOrAmountForTaxTable(IHtmlTable parentTaxTable,int taxIndex) {
		IHtmlTable table=this.getTaxSplitTable(taxIndex, parentTaxTable);
		IHtmlObject[] objs = browser.getTextField(percentOrAmount(), table);
		List<String> account = new ArrayList<String>();
		if (objs.length < 1) {
			throw new ErrorOnPageException("Cann't find percent(amount) object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				IText textObj=(IText)objs[i];
				String value = textObj.getText();
				account.add(value);
			}
		}

		Browser.unregister(objs);
		return account;
	}
	//added by Summer
	public List<String[]>  getAccountInfoForTaxTable(String taxFeeType,int taxIndex,boolean isFlat){
		IHtmlTable parentTaxTable=this.getParentTableByFeeTaxType(taxFeeType);
		List<String[]> accountInfo=new ArrayList<String[]>();
		List<String> account=this.getAccountForTaxTable(parentTaxTable,taxIndex);
		List<String> percent= new ArrayList<String>();
		//for state tax and transaction tax percent(amount) information should be get from UI and for Vendor tax(percent) there is no UI
		if(taxFeeType.equalsIgnoreCase("State") 
				|| taxFeeType.equalsIgnoreCase("Transaction")
				|| (taxFeeType.equalsIgnoreCase("Vendor")&&isFlat)){
			percent=this.getPercentOrAmountForTaxTable(parentTaxTable,taxIndex);
			if(account.size()!=percent.size()){
				throw new ErrorOnDataException("Account size is not consistent with percent(amount) size:"
						+ "Account size is "+account.size()
						+ "percent(amount) size is "+percent.size());
			}

		}
		for(int i=0;i < account.size();i++){
			if(taxFeeType.equalsIgnoreCase("State") || taxFeeType.equalsIgnoreCase("Transaction")){
					accountInfo.add(new String[]{account.get(i),percent.get(i)});
			} else if(taxFeeType.equalsIgnoreCase("Vendor")){
				if(isFlat){
					accountInfo.add(new String[]{account.get(i),percent.get(i)});
				} else
					accountInfo.add(new String[]{account.get(i),""});
			}
		}
		return accountInfo;
		
	}

	public List<String> getAdjustRateForStateFee() {
		IHtmlTable table = this.getStateFeeTable();
		IHtmlObject[] objs = browser.getHtmlObject(adjustRate(), table);

		List<String> rate = new ArrayList<String>();

		if (objs.length < 1) {
			throw new ErrorOnPageException(
					"Cann't find adjust rate rate object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				IHtmlObject[] objs1 = browser.getHtmlObject(".class",
						"Html.INPUT.text", objs[i]);
				String value = objs1[0].getProperty(".value").trim();
				Browser.unregister(objs1);
				rate.add(value);
			}
		}
		return rate;
	}

	public List<String> getAdjustRateForTransactionFee() {
		IHtmlTable table = this.getTransactionFeeTable();
		IHtmlObject[] objs = browser.getHtmlObject(adjustRate(), table);

		List<String> rate = new ArrayList<String>();

		if (objs.length < 1) {
			throw new ErrorOnPageException(
					"Cann't find adjust rate rate object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				IHtmlObject[] objs1 = browser.getHtmlObject(".class",
						"Html.INPUT.text", objs[i]);
				String value = objs1[0].getProperty(".value").trim();
				Browser.unregister(objs1);
				rate.add(value);
			}
		}
		return rate;
	}

	public List<String> getAdjustRateForVendorFee() {
		IHtmlTable table = this.getVendorFeeTable();
		IHtmlObject[] objs = browser.getHtmlObject(adjustRate(), table);

		List<String> rate = new ArrayList<String>();

		if (objs.length < 1) {
			throw new ErrorOnPageException(
					"Cann't find adjust rate rate object...");
		} else {
			for (int i = 0; i < objs.length; i++) {
				IHtmlObject[] objs1 = browser.getHtmlObject(".class",
						"Html.INPUT.text", objs[i]);
				String value = objs1[0].getProperty(".value").trim();
				Browser.unregister(objs1);
				rate.add(value);
			}
		}
		Browser.unregister(objs);
		return rate;
	}
	//Updated by Summer
	public List<TaxInfo> getVendorFeeTax(boolean isFlat) {
		List<String> taxnames = this.getTaxNamesForVendorFee();
		List<String> calRates = new ArrayList<String>();
//		List<String> amount = new ArrayList<String>();
		List<String> rates = new ArrayList<String>();
		List<String> adjustrates = new ArrayList<String>();
		if (isFlat) {
			calRates = this.getTaxCalRateForVendorFee();
//			amount = this.getAmountForVendorFee();
			adjustrates = this.getAdjustRateForVendorFee();
		} else {
			rates = this.getRatesForVendorFee();
		}

		List<TaxInfo> taxs = new ArrayList<TaxInfo>();
		PricingInfo pricing = new PricingInfo();
		for (int i = 0; i < taxnames.size(); i++) {
			TaxInfo tax = pricing.new TaxInfo();
			tax.setTaxName(taxnames.get(i));
			//updated by Summer, get account for each tax
			List<String[]> accountInfo=this.getAccountInfoForTaxTable("Vendor",i,isFlat);
			tax.addAccount(accountInfo.get(i));
			if (isFlat) {
				tax.setTaxCalculationRate(calRates.get(i));
				tax.setTaxAdjustRate(adjustrates.get(i));
			} else {
				tax.setRate(rates.get(i));
			}

			taxs.add(tax);
		}

		return taxs;

	}
	//Updated by Summer
	public List<TaxInfo> getStateFeeTax(boolean isFlat) {
		List<String> taxnames = this.getTaxNamesForStateFee();
		List<String> calRates = new ArrayList<String>();
		List<String> rates = new ArrayList<String>();
		List<String> adjustrates = new ArrayList<String>();
		if (isFlat) {
			calRates = this.getTaxCalRateForStateFee();
			adjustrates = this.getAdjustRateForStateFee();
		} else {
			rates = this.getRatesForStateFee();
		}

		List<TaxInfo> taxs = new ArrayList<TaxInfo>();
		PricingInfo pricing = new PricingInfo();
		for (int i = 0; i < taxnames.size(); i++) {
			TaxInfo tax = pricing.new TaxInfo();
			tax.setTaxName(taxnames.get(i));
			//updated by Summer, get account for each tax
			List<String[]> accountInfo=this.getAccountInfoForTaxTable("State",i,isFlat);
			tax.addAccount(accountInfo.get(i));
			if (isFlat) {
				tax.setTaxCalculationRate(calRates.get(i));
				tax.setTaxAdjustRate(adjustrates.get(i));
			} else {
				tax.setRate(rates.get(i));
			}

			taxs.add(tax);
		}

		return taxs;

	}
	//Updated by Summer
	public List<TaxInfo> getTransactionFeeTax(boolean isFlat) {
		List<String> taxnames = this.getTaxNamesForTransactionFee();
		List<String> calRates = new ArrayList<String>();
		List<String> rates = new ArrayList<String>();
		List<String> adjustrates = new ArrayList<String>();
		if (isFlat) {
			calRates = this.getTaxCalRateForTransactionFee();
			adjustrates = this.getAdjustRateForTransactionFee();
		} else {
			rates = this.getRatesForTransactionFee();
		}

		List<TaxInfo> taxs = new ArrayList<TaxInfo>();
		PricingInfo pricing = new PricingInfo();

		for (int i = 0; i < taxnames.size(); i++) {
			TaxInfo tax = pricing.new TaxInfo();
			tax.setTaxName(taxnames.get(i));
			//updated by Summer, get accounts for each tax
			List<String[]> accountInfo=this.getAccountInfoForTaxTable("Transaction",i,isFlat);
			tax.addAccount(accountInfo.get(i));
			if (isFlat) {
				tax.setTaxCalculationRate(calRates.get(i));
				tax.setTaxAdjustRate(adjustrates.get(i));
			} else {
				tax.setRate(rates.get(i));
			}

			taxs.add(tax);
		}

		return taxs;

	}

	public void setVendorFeeTax(List<TaxInfo> vendorTaxes) {
		this.clickRemoveAllTaxesForVendorFee();
		ajax.waitLoading();

		for (int i = 0; i < vendorTaxes.size(); i++) {
			int flatIndex = 0;
			int percentIndex = 0;
			TaxInfo tax = vendorTaxes.get(i);
			this.clickAddTaxesForVendorFee();
			ajax.waitLoading();
			this.selectTaxNameForVendorFee(tax.getTaxName(), i);
			ajax.waitLoading();
			//updated by summer,reason: tax data structure changed
			this.selectAccountForVendorFee(tax.getAccount(0)[0], i);
			
			ajax.waitLoading();
			if (tax.getIsFlat()) {
				if (StringUtil.notEmpty(tax.getTaxCalculationRate())) {
					this.setTaxCalRateForVendorFee(tax.getTaxCalculationRate(),
							flatIndex);
				}
				ajax.waitLoading();
				if (StringUtil.notEmpty(tax.getAccount(0)[1])) {
					this.setamountForVendorFee(tax.getAccount(0)[1], flatIndex);
				}
				ajax.waitLoading();
				flatIndex ++;
			} else {
				this.setRateForVendorFee(tax.getRate(), percentIndex);
				percentIndex ++;
			}
		}

	}

	public void setStateFeeTax(List<TaxInfo> stateTaxes) {
		this.clickRemoveAllTaxesForStateFee();
		ajax.waitLoading();
		int flatIndex=0;
		int percentIndex=0;
		for (int i = 0; i < stateTaxes.size(); i++) {
			TaxInfo tax = stateTaxes.get(i);
			this.clickAddTaxesForStateFee();
			ajax.waitLoading();
			this.selectTaxNameForStateFee(tax.getTaxName(), i);
			ajax.waitLoading();
			if (tax.getIsFlat()) {
				if (StringUtil.notEmpty(tax.getTaxCalculationRate())) {
					this.setTaxCalRateForStateFee(tax.getTaxCalculationRate(),
							flatIndex);
				}
				ajax.waitLoading();
				flatIndex++;
			} else {
				this.setRateForStateFee(tax.getRate(), percentIndex);
				percentIndex++;
				
			}

			setupSplitInfoForTaxTable(tax,i,"State");

		}

	}
	

	public void setTransactionFeeTax(List<TaxInfo> transactionTaxes) {
		this.clickRemoveAllTaxesForTransactionFee();
		ajax.waitLoading();
		int flatIndex=0;
		int percentIndex=0;

		for (int i = 0; i < transactionTaxes.size(); i++) {
			TaxInfo tax = transactionTaxes.get(i);
			this.clickAddTaxesForTransactionFee();
			ajax.waitLoading();
			this.selectTaxNameForTransactionFee(tax.getTaxName(), i);
			ajax.waitLoading();
			if (tax.getIsFlat()) {
				if (StringUtil.notEmpty(tax.getTaxCalculationRate())) {
					this.setTaxCalRateForTransactionFee(
							tax.getTaxCalculationRate(), flatIndex);
				}
				ajax.waitLoading();
				flatIndex++;
			} else {
				this.setRateForTransactionFee(tax.getRate(), percentIndex);
			}
			setupSplitInfoForTaxTable(tax,i,"Transaction");
			percentIndex++;
			
		}

	}

	
	public void setupSplitInfoForTaxTable(TaxInfo tax,int taxIndex,String taxFeeType){
		//set split account and rate
		if(!tax.getIsFlat()){
			if (StringUtil.notEmpty(tax.getSplitNumberInfo())) {
				String num = tax.getSplitNumberInfo();
					this.selectSplitIntoForTaxTable(num, taxIndex,taxFeeType);
			}
		}
		ajax.waitLoading();
		int accountLength=tax.getAccounts().size();
		for (int j = 0; j <= accountLength-1; j++){
			this.selecetAccountForTaxTable(tax.getAccount(j)[0],taxIndex, j,taxFeeType);
			ajax.waitLoading();
			String percentOrAmount=tax.getAccount(j)[1];
			if(StringUtil.notEmpty(percentOrAmount) && !(!tax.getIsFlat() && j==accountLength-1)){
				this.setSplitPercentRateForTaxTable(percentOrAmount,taxIndex, j,taxFeeType);
				ajax.waitLoading();
			}
		}
	}

	private IHtmlTable getParentTableByFeeTaxType(String taxFeeType){
		IHtmlTable parentTaxTable;
		if(taxFeeType.equalsIgnoreCase("State")){
			parentTaxTable = getStateFeeTable();

		} else if(taxFeeType.equalsIgnoreCase("Transaction")){
			parentTaxTable = getTransactionFeeTable();
		} else if(taxFeeType.equalsIgnoreCase("Vendor")){
			parentTaxTable = getVendorFeeTable();
		} else {
			throw new ErrorOnDataException("Unknown fee tax type");
		}
		return parentTaxTable;
	}
	
	public void compareVendorFeeTax(List<TaxInfo> vendorTaxes, boolean isFlat) {
		List<TaxInfo> actuall = this.getVendorFeeTax(isFlat);
		if (vendorTaxes.size() != actuall.size()) {
			throw new ErrorOnPageException(
					"vendor fee tax size is not correct, please check...",
					vendorTaxes.size(), actuall.size());
		}

		for (int i = 0; i < vendorTaxes.size(); i++) {
			TaxInfo expectResult = vendorTaxes.get(i);
			TaxInfo actualResult = actuall.get(i);

			boolean result = true;
			//updated by Summer, compare each account for current tax
			result &= MiscFunctions.compareResult("Tax account size",
					expectResult.getAccounts().size(), actualResult.getAccounts().size());
			if(result){
				for(int j=0;j<expectResult.getAccounts().size();j++){
					result &= MiscFunctions.compareResult("Account",
							expectResult.getAccount(j)[0], actualResult.getAccount(j)[0]);
					result &= MiscFunctions.compareResult("Percent(Amount)",
							expectResult.getAccount(j)[1], actualResult.getAccount(j)[1]);

				}
				
			}
			result &= MiscFunctions.compareResult("Tax name",
					expectResult.getTaxName(), actualResult.getTaxName());
			result &= MiscFunctions.compareResult("Account",
					expectResult.getAccount(i)[0], actualResult.getAccount(i)[0]);
			if (isFlat) {
				result &= MiscFunctions.compareResult("Tax Calculation Rate",
						expectResult.getTaxCalculationRate(),
						actualResult.getTaxCalculationRate());
				//updated by Summer, amount is no longer available since account info include amount and percent
//				result &= MiscFunctions.compareResult("Tax amount",
//						expectResult.getTaxAmount(),
//						actualResult.getTaxAmount());
				result &= MiscFunctions.compareResult("Adjust Rate",
						expectResult.getTaxAdjustRate(),
						actualResult.getTaxAdjustRate());
			} else {
				result &= MiscFunctions.compareResult("Rate",
						expectResult.getRate(), actualResult.getRate());
			}

			if (!result) {
				throw new ErrorOnPageException(
						"tax info is not correct at line :" + i
								+ "; Please check log...");
			}
		}

	}

	public void compareTransactionFeeTax(List<TaxInfo> transactionTaxes,
			boolean isFlat) {
		List<TaxInfo> actuall = this.getTransactionFeeTax(isFlat);
		if (transactionTaxes.size() != actuall.size()) {
			throw new ErrorOnPageException(
					"transaction fee tax size is not correct, please check...",
					transactionTaxes.size(), actuall.size());
		}

		for (int i = 0; i < transactionTaxes.size() - 1; i++) {
			TaxInfo expectResult = transactionTaxes.get(i);
			TaxInfo actualResult = actuall.get(i);

			boolean result = true;
			//updated by Summer, compare each account for current tax
			result &= MiscFunctions.compareResult("Tax account size",
					expectResult.getAccounts().size(), actualResult.getAccounts().size());
			if(result){
				for(int j=0;j<expectResult.getAccounts().size();j++){
					result &= MiscFunctions.compareResult("Account",
							expectResult.getAccount(j)[0], actualResult.getAccount(j)[0]);
					result &= MiscFunctions.compareResult("Percent(Amount)",
							expectResult.getAccount(j)[1], actualResult.getAccount(j)[1]);

				}
				
			}
			result &= MiscFunctions.compareResult("Tax name",
					expectResult.getTaxName(), actualResult.getTaxName());

			if (isFlat) {
				result &= MiscFunctions.compareResult("Tax Calculation Rate",
						expectResult.getTaxCalculationRate(),
						actualResult.getTaxCalculationRate());
				//updated by Summer, amount is no longer available since account info include amount and percent
//				result &= MiscFunctions.compareResult("Tax amount",
//						expectResult.getTaxAmount(),
//						actualResult.getTaxAmount());
				result &= MiscFunctions.compareResult("Adjust Rate",
						expectResult.getTaxAdjustRate(),
						actualResult.getTaxAdjustRate());
			} else {
				result &= MiscFunctions.compareResult("Rate",
						expectResult.getRate(), actualResult.getRate());
			}

			if (!result) {
				throw new ErrorOnPageException(
						"tax info is not correct at line :" + i
								+ "; Please check log...");
			}
		}

	}

	public void compareStateFeeTax(List<TaxInfo> stateTaxes, boolean isFlat) {
		List<TaxInfo> actuall = this.getStateFeeTax(isFlat);
		if (stateTaxes.size() != actuall.size()) {
			throw new ErrorOnPageException(
					"vendor fee tax size is not correct, please check...",
					stateTaxes.size(), actuall.size());
		}

		for (int i = 0; i < stateTaxes.size(); i++) {
			TaxInfo expectResult = stateTaxes.get(i);
			TaxInfo actualResult = actuall.get(i);

			boolean result = true;
			result &= MiscFunctions.compareResult("Tax account size",
					expectResult.getAccounts().size(), actualResult.getAccounts().size());
			//updated by Summer, compare each account for current tax
			if(result){
				for(int j=0;j<expectResult.getAccounts().size();j++){
					result &= MiscFunctions.compareResult("Account",
							expectResult.getAccount(j)[0], actualResult.getAccount(j)[0]);
					result &= MiscFunctions.compareResult("Percent(Amount)",
							expectResult.getAccount(j)[1], actualResult.getAccount(j)[1]);
				}
				
			}
			result &= MiscFunctions.compareResult("Tax name",
					expectResult.getTaxName(), actualResult.getTaxName());
			if (isFlat) {
				result &= MiscFunctions.compareResult("Tax Calculation Rate",
						expectResult.getTaxCalculationRate(),
						actualResult.getTaxCalculationRate());
				//updated by Summer, amount is no longer available since account info include amount and percent
//				result &= MiscFunctions.compareResult("Tax amount",
//						expectResult.getTaxAmount(),
//						actualResult.getTaxAmount());
				result &= MiscFunctions.compareResult("Adjust Rate",
						expectResult.getTaxAdjustRate(),
						actualResult.getTaxAdjustRate());
			} else {
				result &= MiscFunctions.compareResult("Rate",
						expectResult.getRate(), actualResult.getRate());
			}

			if (!result) {
				throw new ErrorOnPageException(
						"tax info is not correct at line :" + i
								+ "; Please check log...");
			}
		}

	}

	public void setTaxInfo(PricingInfo taxInfo) {
		this.setVendorFeeTax(taxInfo.vendorTaxes);
		this.setStateFeeTax(taxInfo.stateTaxes);
		this.setTransactionFeeTax(taxInfo.transactionTaxes);
	}

}
