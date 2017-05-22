package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * This is the hunts list page in license manager, Admin(drop down list)-->Lotteries --- > Quota
 * @author pchen
 * @Date Nov 12, 2012
 */
public class LicMgrQuotaListPage extends LicMgrLotteriesCommonPage {
	private static LicMgrQuotaListPage _instance = null;
	
	protected LicMgrQuotaListPage (){}
	
	public static LicMgrQuotaListPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrQuotaListPage();
		}
		
		return _instance;
	}
	
	private String prefix = "^HuntQuotaFilter-\\d+\\.";
	
	protected Property[] addQuotaLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Add Quota");
	}
	
	protected Property[] quotaListTable() {
		return Property.concatPropertyArray(this.table(), ".id","huntQuotaListGrid");
	}
	
	protected Property[] quotaIDLink(String id) {
		return Property.concatPropertyArray(this.a(), ".text", id);
	}
	
	protected Property[] quotaListTR(int row) {
		return Property.concatPropertyArray(this.tr(), ".id", "huntQuotaListGrid_row_"+row);
	}
	
	protected Property[] multiLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Multi");
	}
	
	protected Property[] quotaStatusList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression(prefix + "status",false));
	}
	
	protected Property[] quotaSpeciesSpan(String species) {
		return Property.concatPropertyArray(this.span(), ".className", "inputwithrubylabel checkbox", ".text", species);
	}
	
	protected Property[] quotaSpeciesCheckbox() {
		return Property.concatPropertyArray(this.input("checkbox"), ".id", new RegularExpression(prefix+"species_\\d+", false));
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(quotaListTable());
	}
	
	public void clickAddQuota(){
		browser.clickGuiObject(addQuotaLink(), true);
	}
	
	public boolean checkAddQuotaExists() {
		return browser.checkHtmlObjectExists(addQuotaLink());
	}
	
	public void selectStatus(String status){
		if(StringUtil.notEmpty(status)){
			browser.selectDropdownList(quotaStatusList(), status);	
		}else{
			browser.selectDropdownList(quotaStatusList(), 0);
		}
	}
	
	/**
	 * Select the check box before the given species
	 * @param species
	 */
	public void checkSpecies(String... species){
		for(String sp: species){
//			IHtmlObject[] speciesDivs = browser.getDropdownList(".class", "Html.Span",
//					".text", sp);
//			if(speciesDivs.length == 0){
//				throw new ErrorOnPageException("Checkbox for " + sp + " does not exist in filters!");
//			}
//		    IHtmlObject top = speciesDivs[0];
//		    browser.clickGuiObject(".class", "Html.INPUT", true, 0, top);
//		    Browser.unregister(speciesDivs);
		    browser.selectCheckBox(Property.atList(this.quotaSpeciesSpan(sp), this.quotaSpeciesCheckbox()));
		}
	}
	
	public void uncheckAllSpecies() {
		IHtmlObject[] objs = browser.getHtmlObject(this.quotaSpeciesCheckbox());
		for (int i = 0; i < objs.length; i++) {
			browser.unSelectCheckBox(quotaSpeciesCheckbox(), i);
		}
		Browser.unregister(objs);
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go", true);
	}

	public void searchQuota(String status, String... species) {
		logger.info("Search quota with status=" + status + " species=" + species);
		this.selectStatus(status);
		this.uncheckAllSpecies();
		if (species != null && species.length > 0) {
			this.checkSpecies(species);
		}
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public String getQuotaID(String quotaDes) {
		IHtmlObject[] objs = browser.getHtmlObject(quotaListTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(1, quotaDes);
		String id = table.getCellValue(row, 0);
		Browser.unregister(objs);
		return id;
	}
	
	public boolean checkQuotaIDLinkExists(String id) {
		return browser.checkHtmlObjectExists(quotaIDLink(id));
	}
	
	public void clickQuotaID(String id) {
		browser.clickGuiObject(quotaIDLink(id));
	}
	
	public void clickQuotaIDViaDesc(String desc){
		String id = getQuotaID(desc);
		clickQuotaID(id);
	}
	
	public boolean checkQuotaExist(String quotaDes) {
		int row = this.getQuotaRowIndex(quotaDes);
		boolean result = row > 1;
		return result;
	}
	
	public void verifyQuotaExist(String quotaDes, boolean exp) {
		boolean actual = this.checkQuotaExist(quotaDes);
		if (exp != actual) {
			throw new ErrorOnPageException("The quota " + quotaDes + " exist in Quota List page wrongly!", exp, actual);
		}
		logger.info("Successfully verify the quota " + quotaDes + (exp ? "" : " NOT ") + " exist in Quota List page!");
	}

	private String getMultiLinkTitle(int row, int col) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(quotaListTR(row-1), td()));
		IHtmlObject[] links = browser.getHtmlObject(multiLink(), objs[col]);
		if (links.length < 1) {
			throw new ObjectNotFoundException("Can't find multi link!");
		}
		String title = links[0].title();
		Browser.unregister(objs, links);
		return title;
	}
	
	public void verifyQuotaInfo(QuotaInfo quota) {
		IHtmlObject[] objs = browser.getHtmlObject(quotaListTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(1, quota.getDescription());
		if (row < 0) {
			throw new ErrorOnPageException("Can't find the quota " + quota.getDescription());
		}
		boolean result = true;
		
		// Quota Species
		int speciesRow = table.findRow(0, quota.getSpecie());
		logger.info("Species row index = " + speciesRow);
		result &= MiscFunctions.compareResult("Quota shown under the species", true, row > speciesRow);
		
		// Quota Id and status
		result &= MiscFunctions.compareString("Quota ID", quota.getQuotaId(), table.getCellValue(row, 0));
		result &= MiscFunctions.compareString("Quota Status", quota.getQuotaStatus(), table.getCellValue(row, 2));
		
		// Quota Type
		String quotaTypes = table.getCellValue(row, 3);
		String expTypes = "";
		String seprator = " ";
		if (quota.getQuotaTypes().size() > 1) {
			quotaTypes = this.getMultiLinkTitle(row, 3);
			seprator = "</br>";
			expTypes = "|Quota Types:"+seprator;
		} 
		for (int i = 0; i < quota.getQuotaTypes().size(); i++) {
			expTypes += quota.getQuotaTypes().get(i).getQuotaType() + seprator;
		}
		result &= MiscFunctions.compareString("Quota Type", expTypes.trim(), quotaTypes);
	
		// Quota associated lottery product
		String lotteryPrds = table.getCellValue(row, 4);
		String expLottery = "";
		seprator = " ";
		if (quota.getAssignedLotteryPrds() != null) {
			if (quota.getAssignedLotteryPrds().size() > 1) {
				lotteryPrds = this.getMultiLinkTitle(row, 4);
				expLottery = "|";
				seprator = "</br>";
			} 
			for (int i = 0; i < quota.getAssignedLotteryPrds().size(); i++) {
				expLottery += quota.getAssignedLotteryPrds().get(i) + seprator;
			}
		}
		result &= MiscFunctions.compareString("Quota Assigned Lottery Products", expLottery.trim(), lotteryPrds);
	
		// Quota associated hunt
		String hunts = table.getCellValue(row, 5);
		String expHunts = "";
		seprator = " ";
		if (quota.getAssignedHunts() != null) {
			if (quota.getAssignedHunts().size() > 1) {
				hunts = this.getMultiLinkTitle(row, 5);
				expHunts = "|";
				seprator = "</br>";
			} 
			for (int i = 0; i < quota.getAssignedHunts().size(); i++) {
				expHunts += quota.getAssignedHunts().get(i) + seprator;
			}
		}
		result &= MiscFunctions.compareString("Quota Assigned Hunts", expHunts.trim(), hunts);
	
		if (!result) {
			throw new ErrorOnPageException("Quota info for " + quota.getDescription() + " is wrong in Quota List page!");
		}
		logger.info("Verify quota info for " + quota.getDescription() + " correctly in Quota List page!");
	}
	
	public int getSpeciesRowIndex(String species) {
		IHtmlObject[] objs = browser.getHtmlObject(quotaListTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(0, species);
		Browser.unregister(objs);
		return row;
	}
	
	public int getQuotaRowIndex(String qutoaDes) {
		IHtmlObject[] objs = browser.getHtmlObject(quotaListTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(1, qutoaDes);
		Browser.unregister(objs);
		return row;
	}
}
