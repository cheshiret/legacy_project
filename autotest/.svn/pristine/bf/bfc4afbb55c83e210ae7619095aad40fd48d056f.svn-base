/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.harvestquestions;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.HarvestReportQuestionnaires;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductCommonPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date  Jun 16, 2011
 */
public class LicMgrHavestQuestionsListPage extends LicMgrProductCommonPage {

	private static LicMgrHavestQuestionsListPage instance = null;

	private LicMgrHavestQuestionsListPage() {
	}

	private boolean pass = true;

	public static LicMgrHavestQuestionsListPage getInsatance() {
		if (instance == null) {
			instance = new LicMgrHavestQuestionsListPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser
				.checkHtmlObjectExists(".id", "harvest_questionaire_grid");
	}

	public void clickHarvestId(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id);
		ajax.waitLoading();
	}

	public String getStatus(String id) {
		String status = "";
		IHtmlTable table = this.getHavestListTable();
		int row = table.findRow(0, id);
		status = table.getCellValue(row, 1);
		Browser.unregister(table);
		return status;
	}

	public List<String> getRowInfo(String id) {

		IHtmlTable tableObj = this.getHavestListTable();
		int row = tableObj.findRow(0, id);
		List<String> rowInfo = tableObj.getRowValues(row);

		return rowInfo;
	}

	/**
	 * Get the harvest question list table.
	 * 
	 * @return ITable - the table .
	 */
	private IHtmlTable getHavestListTable() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"harvest_questionaire_grid");
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the Havest List table...");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		return table;
	}

	/**
	 * Get the row number.
	 * @param - the product species
	 * @param- the product season
	 * @return - the row number .
	 */
	private int getRow(String species, String season) {
		int row = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		IHtmlTable table = this.getHavestListTable();
		list.add(table.findRow(2, species));
		for (int i = 0; i < list.size(); i++) {
			if (season.equals(table.getCellValue(list.get(i), 3))) {

				row = list.get(i);
			}
		}

		return row;
	}

	
	public String getFieldValue(String species, String season, String colName) {
		int row = getRow(species, season);
		IHtmlTable table = this.getHavestListTable();
		int col = table.findColumn(0, colName);
		return table.getCellValue(row, col);
	}

	public String getIdValue(String species, String season) {
		return this.getFieldValue(species, season, "ID");
	}

	/**
	 * Get the id value
	 * 
	 * @param expectedSpecies
	 *            - the value of species
	 * @param expectedSeason
	 *            - the value of season
	 * @param expectedStatus
	 *            -the value of expectedStatus
	 * @param expectedEffectiveDate
	 *            - the value of expectedEffectiveDate
	 * @return - the harvest id .
	 */

	public String getHarvestQuestionId(String expectedSpecies, String expectedSeason, String expectedStatus,
			String expectedEffectiveDate) {
		IHtmlTable table = this.getHavestListTable();
		
		logger.info("table colum count is: "+ table.columnCount() + " table row count is: " + table.rowCount());
		
		String questionId = "";
		String species, season, status, effectiveDate = "";
		
		if (expectedEffectiveDate.length() > 0) {
			expectedEffectiveDate = DateFunctions.formatDate(expectedEffectiveDate, "E MMM d yyyy");
		}
		
		for (int i = 1; i < table.rowCount(); i++) {
			species = table.getCellValue(i, 4).trim();//Vivian [20140616]
			season = table.getCellValue(i, 5).trim();
			effectiveDate = table.getCellValue(i, 7).trim();
			effectiveDate = effectiveDate.length()>0?DateFunctions.formatDate(effectiveDate, "E MMM d yyyy"):effectiveDate;
			status = table.getCellValue(i, 1).trim();
			if(null == expectedStatus || expectedStatus.equals("")) {
				expectedStatus = "";
				status = "";
			}
			if(null == expectedEffectiveDate||expectedEffectiveDate.equals("")) {
				expectedEffectiveDate = "";
				effectiveDate="";
				
			}
			if (status.equals(expectedStatus) && effectiveDate.equals(expectedEffectiveDate)
				&& species.equals(expectedSpecies) && season.equals(expectedSeason)) {
				questionId = table.getCellValue(i, 0);
				break;
			}
		}
		return questionId;
	}

	/**
	 * Get the question number
	 * 
	 * @param id - the value of id
	 * 
	 * @return - the question number .
	 */
	public String getQuesNumberFromTable(String id) {
		String questNumber = "";
		IHtmlTable table = this.getHavestListTable();

		int row = table.findRow(0, id);

		questNumber = table.getCellValue(row, 8);

		return questNumber;
	}


	/**
	 * Get the harvest question list item.
	 * 
	 * @param havQuestReport - Info of harvest question.
	 * @param isVerifyNumber - the number is need to verify or not

	 */
	public void verifyHarvestQuestItem(HarvestReportQuestionnaires havQuestReport){
		pass = true;
		List<String> rowInfo = this.getRowInfo(havQuestReport.harvestId);
		if(null == rowInfo||rowInfo.size()<1){
			pass &= false;
			logger.error("There is no list item exist");	
		}
		
		if(!rowInfo.get(0).equals(havQuestReport.harvestId)){
			pass &= false;
			logger.error("Harvest id '"+rowInfo.get(0)+"' error. Expected: '"+havQuestReport.harvestId+"'");	
		}
		if(!rowInfo.get(1).equals(havQuestReport.status)){
			pass &= false;
			logger.error("Harvest status '"+rowInfo.get(1)+"' error. Expected: '"+havQuestReport.status+"'");	
		}
		if(!rowInfo.get(4).equals(havQuestReport.species)){
			pass &= false;
			logger.error("Species species '"+rowInfo.get(4)+"' error. Expected: '"+havQuestReport.species+"'");	
		}
		if(!rowInfo.get(5).equals(havQuestReport.season)){
			pass &= false;
			logger.error("Season season '"+rowInfo.get(5)+"' error. Expected: '"+havQuestReport.season+"'");	
		}
		
		
		if(!DateFunctions.formatDate(rowInfo.get(7), "E MMM dd yyyy").equals(DateFunctions.formatDate(havQuestReport.effectiveDate, "E MMM dd yyyy"))){
			pass &= false;
			logger.error("EffectiveDate effective date \""+rowInfo.get(7)+"\" error. Expected: \""+havQuestReport.effectiveDate+"\"");	
		}
			if (!pass) {
				throw new ErrorOnDataException("Harvest list Item view error");
			} else {
				logger.info("Harvest list Item view successfully");
			}

		}
}
