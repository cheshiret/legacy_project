package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.createanaccount;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Verify customer attributes in create account page
 * Drop down list fields (Gender, Eye Colour,Hair Colour) have default option "Please select" and selected option will display
 * Text field (Heigh) maximum length is 50.

 * @Preconditions: No
 * @SPEC: Customer Profile - Customer attributes [TC:044142] 
 * @Task#: Auto-1770
 * 
 * @author SWang
 * @Date  Jul 16, 2013
 */
public class CusAttibutesValidation extends HFSKWebTestCase {
	private HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();
	private String ddlDefaultValue; //, fifthCharactersString, fifthAndOneCharactersString;

	public void execute() {
		//Go to create account page
		hf.invokeURL(url);
		hf.gotoCreateAccountPage(url, cus);

		//Verify customer attributes
		verifyCustAttributes();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "createdAccount" + hf.getNextEmail();
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.retypePassword = cus.password;
		cus.setDefaultValuesForHFWebSignUp();
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.custGender = "M";
		cus.eyeColor = "Blue";
		cus.hairColor = "Bald";
		cus.heightFt = "5";
		cus.heightIn = "7";
		cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_NAME_OTHER;
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		cus.identifier.country = "Albania";

		ddlDefaultValue = "Please select";
//		fifthAndOneCharactersString = "1 3 5 7 9 a b c d e A B C D E 1 3 5 7 9 1 3 5 7 99T";
//		fifthCharactersString = fifthAndOneCharactersString.split("T")[0];
	}

	private void verifyCustAttributes(){
		//Verify DDL default values
		boolean result = MiscFunctions.compareResult("Gender default value", ddlDefaultValue, createAccPg.getGender());
		result &= MiscFunctions.compareResult("Eye colour default value", ddlDefaultValue, createAccPg.getEyeColour());
		result &= MiscFunctions.compareResult("Hair colour default value", ddlDefaultValue, createAccPg.getHairColour());

		//Get DDL selected values
		createAccPg.selectGender(cus.custGender);
		createAccPg.selectEyeColour(cus.eyeColor);
		createAccPg.selectHairColour(cus.hairColor);
		result &= MiscFunctions.compareResult("Gender selected", cus.custGender, createAccPg.getGender());
		result &= MiscFunctions.compareResult("Eye colour selected", cus.eyeColor, createAccPg.getEyeColour());
		result &= MiscFunctions.compareResult("Hair colour selected", cus.hairColor, createAccPg.getHairColour());

		//Verify text field max length. Lesley[20131029]: ignore the checkpoint per Lucy's reply: 
		// "So the “less than or equal to 8 feet and 4 inches” added somewhere by developer.Yes, it is a numeric field,  we can ignore the length of text."
//		createAccPg.setHeight(fifthAndOneCharactersString, fifthAndOneCharactersString);
//		result &= MiscFunctions.compareResult("Max length of height_ft", fifthCharactersString, createAccPg.getHeight_ft());
//		result &= MiscFunctions.compareResult("Max length of height_in", fifthCharactersString, createAccPg.getHeight_in());

		if(!result){
			throw new ErrorOnPageException("Failed to verify all customer attributes. Please find details from previous logs.");
		}
		logger.info("Successfully verify all customer attribute.");
	}
}
