package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.product.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.LicMgrProductConfigurationQuestionsDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductQuestionsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This use case to view the assigned Consumable Question of existed Question.
 * @Preconditions:Need a exist Question for the product to assign.
 * And there shouln't exist product questions which have the same Display Text and Location Class
 * and overlapping License Year From/To.
 * @SPEC:View Question Assignments to Products.doc
 * @Task#:Auto-844
 * 
 * @author nding1
 * @Date  2012-1-9
 */
public class ViewQuestionAssignmentsToConsumable extends LicenseManagerTestCase {

	private QuestionInfo questions[] = new QuestionInfo[3];
	private LicMgrProductConfigurationQuestionsDetailPage questionAssignmentPg = LicMgrProductConfigurationQuestionsDetailPage.getInstance();
	private boolean result = true;
	
	@Override
	public void execute() {
		lm.deleteAllQuestionsFormDBForOneProduct(question.proCode, question.productType, question.location, schema);
		
		lm.loginLicenseManager(login);

		// go to consumable page
		lm.gotoConsumableQuestionPgFromTopMenu(consumable.id);
		
		// assign Product Questions to the existing Question
		List<Map<String, String>> locationClassesAndIDsList = new ArrayList<Map<String, String>>();
		for(int i=0; i<questions.length; i++) {
			final QuestionInfo question = questions[i];
			
			locationClassesAndIDsList.add(lm.safeAddQuestionsForProduct(question, new LicenseManager.QuestionCompare() {
				
				@Override
				public String exist(QuestionInfo question) {
					LicMgrConsumableProductQuestionsPage questionConsumPage = LicMgrConsumableProductQuestionsPage
							.getInstance();
					return questionConsumPage.getProductQuestionId(3, question.locationClass);
				}
			}));
		}

		// go to configuration page to verify the Product Questions
		lm.gotoProductCongurationQuestionsDetailsPg(questions[0].questDisplayText);
		List<List<String>> expectAssignmentRecords = new ArrayList<List<String>>();
		
		// get Product Questions ID
		for(int i=0; i < locationClassesAndIDsList.size(); i++) {

			// set Product Questions ID
			questions[i].id = locationClassesAndIDsList.get(i).get(questions[i].locationClass);
			List<String> expectList = this.parseAssingmentRecord(questions[i]);
			expectAssignmentRecords.add(expectList);
		}

		// get from UI
		List<List<String>> currentAssignmentRecords = questionAssignmentPg.getAllQuestionAssignments(true,consumable.code);
		
		// verify
		this.verifyAssignments(expectAssignmentRecords, currentAssignmentRecords);
		
		if(!result) {
			throw new ErrorOnPageException("Not all the check points are passed.Please check the log...");
		}

		// go to consumable page
		lm.gotoConsumableQuestionPgFromTopMenu(consumable.id);
		
		for(int n=0; n<questions.length;n++) {
			lm.deactivateProductQuestionAssignment(questions[n].id);
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		consumable.id = "5770";
		consumable.code = "203";
		consumable.name = "CONCESSION SALE";
		
		question.proCode = consumable.code;
		question.productType = "Consumable";
		question.location = login.location.split("/")[1];

		questions[0] = new QuestionInfo();
		questions[0].displayOrder = "1";
		questions[0].questDisplayText = "Does this question have assignment?";
		questions[0].originalOption = "Optional";
		questions[0].licenseYearFrom = "All";
		questions[0].licenseYearTo = "";
		questions[0].collectionMethod = "Once per Product";
		questions[0].effectiveFromDate = DateFunctions.getDateAfterToday(3);
		questions[0].effectiveToDate = DateFunctions.getDateAfterToday(5);
		questions[0].locationClass = "All";
		questions[0].status = "Active";
		questions[0].proCode = consumable.code;
		questions[0].proName = consumable.name;
		
		questions[1] = new QuestionInfo();
		questions[1].displayOrder = "2";
		questions[1].questDisplayText = questions[0].questDisplayText;
		questions[1].originalOption = "Required";
		questions[1].licenseYearFrom = "All";
		questions[1].licenseYearTo = "";
		questions[1].collectionMethod = "Once per Transaction";
		questions[1].effectiveFromDate = DateFunctions.getDateAfterToday(3);
		questions[1].effectiveToDate = DateFunctions.getDateAfterToday(5);
		questions[1].locationClass = "03-Lakes Offices";
		questions[1].status = "Active";
		questions[1].proCode = consumable.code;
		questions[1].proName = consumable.name;
		
		questions[2] = new QuestionInfo();
		questions[2].displayOrder = "3";
		questions[2].questDisplayText = questions[0].questDisplayText;
		questions[2].originalOption = "Required";
		questions[2].licenseYearFrom = "All";
		questions[2].licenseYearTo = "";
		questions[2].collectionMethod = "Once per License/Fiscal Year";
		questions[2].effectiveFromDate = DateFunctions.getDateAfterToday(3);
		questions[2].effectiveToDate = DateFunctions.getDateAfterToday(5);
		questions[2].locationClass = "04-Commercial Agent";
		questions[2].status = "Active";
		questions[2].proCode = consumable.code;
		questions[2].proName = consumable.name;
	}
	
	/**
	 * get the expect assignment list. 
	 * 
	 * @param question
	 * @return
	 */
	private List<String> parseAssingmentRecord(QuestionInfo question) {
		List<String> assignmentList = new ArrayList<String>();
		
		// id
		assignmentList.add(question.id);
		
		// status
		assignmentList.add(question.status);
		
		// Code
		assignmentList.add(question.proCode);
		
		// Name
		assignmentList.add(question.proName);
		
		// Location Class
		assignmentList.add(question.locationClass);
		
		// License Year From
		assignmentList.add(question.licenseYearFrom);
		
		// License Year To
		assignmentList.add(question.licenseYearTo);
		
		// Effective From Date
		assignmentList.add(question.effectiveFromDate);

		// Effective To Date
		assignmentList.add(question.effectiveToDate);

		// Display Order
		assignmentList.add(question.displayOrder);
		return assignmentList;
	}
	
	/**
	 * verify details of assignments.
	 * 
	 * @param expectList
	 * @param actualList
	 */
	private void verifyAssignments(List<List<String>> expectList, List<List<String>> actualList) {
		result = true;
		
		if(expectList.size() != actualList.size()) {
			result = false;
			logger.error("The size of assignment list is not correct.Exepct is:"+expectList.size()+
					", but actual is:"+actualList.size());
		}
		
		// verify each field of every record.
		for(int i=0; i<expectList.size(); i++) {
			List<String> expectAssignment = expectList.get(i);
			List<String> actualAssignment = actualList.get(i);
			
			for(int j=0;j<expectAssignment.size();j++) {

				if(j != 7 && j != 8) {
					if(!expectAssignment.get(j).equals(actualAssignment.get(j).trim())) {
						result = false;
						logger.error("The change field is not correct!The expect is:" + expectAssignment.get(j) + ", and the displayed is:" + actualAssignment.get(j));
					}
				} else {
					String effiectiveDate = expectAssignment.get(j);
					if(!"".equals(effiectiveDate)){
						effiectiveDate = DateFunctions.formatDate(effiectiveDate, "EEE MMM d yyyy");
					}
					
					if(DateFunctions.compareDates(effiectiveDate, actualAssignment.get(j).trim()) != 0) {
						result = false;
						logger.error("The change field is not correct!The expect is:" + effiectiveDate + ", and the displayed is:" + actualAssignment.get(j));
					}
				}
			}
		}
	}
}
