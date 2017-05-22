package com.activenetwork.qa.awo.datacollection.legacy.orms;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.TestData;

public class QuestionInfo extends TestData {
	
	public String id;
	
	public String questDisplayText = "";// this attribute is also used at adding
	// product question process

	public String productType ="";

	public String displayOrder = "";

	public String originalOption = "";

	public String replacementOption = "";

	public String transfterOption = "";

	public String licenseYearFrom = "";

	public String licenseYearTo = "";

	public String collectionMethod = "";

	public String effectiveFromDate = "";

	public String effectiveToDate = "";

	public String locationClass = "";

	public String locationClasses[] = null;

	public String questPrintText = "";

	public String questionType = "";

	public String questAnswer = "";

	public String[] questAnswers;
	
	public List<String[]> dependentAnswers=new ArrayList<String[]>();

	public String questDescription = "";

	public String hipQuestion = "No";

	public String answerType = "";

	public String minLength = "";

	public String maxLength = "";

	public AcceptableAnswers[] anwsers;

	public String status = "Active";
	
	public String createUser = "Test-Auto,QA-Auto";
	
	public String createLocation="";
	
	public String createDateTime="";

	public String lastUpdateUser = "";

	public String lastUpdateLocation = "";
	
	public String lastUpdateDateTime="";

	public String[] printDocuments;

	public String assignID = "";
	
	public String proCode = "";
	
	public String proName = "";
	
	public String location="";
	
	public boolean needConfirm=false;

	public class AcceptableAnswers {

		public String answer = "";

		public String dispalyOrder = "";

		public String nextAction = "";

		public String[] actionValue;

		public boolean defaultSelection = false;
	}
}
