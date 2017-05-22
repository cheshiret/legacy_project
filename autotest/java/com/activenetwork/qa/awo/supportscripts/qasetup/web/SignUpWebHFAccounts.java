/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.web;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.web.SignUpWebHFAccountFuc;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: it is a support script for signing up account for Web HF.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Mar 11, 2013
 */
public class SignUpWebHFAccounts extends SetupCase {
	private Customer cus = new Customer();
	private String contract;
	private SignUpWebHFAccountFuc func = new SignUpWebHFAccountFuc();
	private boolean toDeleteIdent;
	
	@Override
	public void executeSetup() {
		Object[] args = new Object[3];
		args[0] = contract;
		args[1] = cus;
		args[2] = toDeleteIdent;
		func.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		contract = datasFromDB.get("contract");
		cus.email = datasFromDB.get("email");
		cus.password = datasFromDB.get("pw");
		cus.retypePassword = cus.password;
		cus.fName = datasFromDB.get("fName");
		cus.lName = datasFromDB.get("lName");
		
		cus.dateOfBirth = datasFromDB.get("dateOfBirth");
		String yearAfterCurrentYear = datasFromDB.get("YEARAFTERCURRENTYEAR");
		if (StringUtil.isEmpty(cus.dateOfBirth)) {
			if(StringUtil.isEmpty(yearAfterCurrentYear))
				yearAfterCurrentYear = "16";
			
			if (contract.equals("MO")) {
				cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-Integer.valueOf(yearAfterCurrentYear));
			} else if (contract.matches("SK|AB")) {
				cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-Integer.valueOf(yearAfterCurrentYear))+"-01-01";
			}
		}
		cus.custGender = datasFromDB.get("gender");
		if (StringUtil.isEmpty(cus.custGender)) {
			cus.custGender = "M";
		}
		cus.eyeColor = datasFromDB.get("eyeColor");
		if (StringUtil.isEmpty(cus.eyeColor)) {
			cus.eyeColor = "Brown";
		}
		cus.hairColor = datasFromDB.get("hairColor");
		if (StringUtil.isEmpty(cus.hairColor)) {
			cus.hairColor = "Black";
		}
		cus.heightFt = datasFromDB.get("heightFt");
		if (StringUtil.isEmpty(cus.heightFt)) {
			cus.heightFt = "5";
		}
		cus.heightIn = datasFromDB.get("heightFt");
		if (StringUtil.isEmpty(cus.heightFt)) {
			cus.heightFt = "9";
		}
		cus.weight = datasFromDB.get("weight"); //For HFAB
		cus.mailingAddr.country = datasFromDB.get("mCountry");
		if (StringUtil.isEmpty(cus.mailingAddr.country)) {
			cus.mailingAddr.country = "Canada";
		}
		cus.mailingAddr.zip = datasFromDB.get("mZip");
		if (StringUtil.isEmpty(cus.mailingAddr.zip)) {
			if(contract.equals("SK")){
				cus.mailingAddr.zip = "S7N5B5";
			}else cus.mailingAddr.zip = "L5N8M6";
		}
		cus.mailingAddr.address = datasFromDB.get("mAddress");
		if (StringUtil.isEmpty(cus.mailingAddr.address)) {
			if(contract.equals("SK")){
				cus.mailingAddr.address = "129-72 Campus Drive";
			}cus.mailingAddr.address = "2480 meadowvale";
		}
		cus.mailingAddr.city = datasFromDB.get("mCity");
		if (StringUtil.isEmpty(cus.mailingAddr.city)) {
			if(contract.equals("SK")){
				cus.mailingAddr.city = "Saskatoon";
			}cus.mailingAddr.city = "Mississauga";
		}
		cus.mailingAddr.state = datasFromDB.get("mState");
		if (StringUtil.isEmpty(cus.mailingAddr.state)) {
			if(contract.equals("SK")){
				cus.mailingAddr.state = "Saskatchewan";
			}cus.mailingAddr.state = "Ontario";
		}
		cus.mailingAddr.county = datasFromDB.get("mCounty");
		
		cus.mailAddrAsPhy = Boolean.valueOf(datasFromDB.get("homeSameAsMailing"));
		if (!cus.mailAddrAsPhy) {
			cus.physicalAddr.country = datasFromDB.get("hCountry");
			if (StringUtil.isEmpty(cus.physicalAddr.country)) {
				cus.physicalAddr.country = "Canada";
			}
			cus.physicalAddr.zip = datasFromDB.get("hZip");
			if (StringUtil.isEmpty(cus.physicalAddr.zip)) {
				if(contract.equals("SK")){
					cus.physicalAddr.zip = "S7N5B5";
				}cus.physicalAddr.zip = "L5N8M6";
			}
			cus.physicalAddr.address = datasFromDB.get("hAddress");
			if (StringUtil.isEmpty(cus.physicalAddr.address)) {
				if(contract.equals("SK")){
					cus.physicalAddr.address = "129-72 Campus Drive";
				} cus.physicalAddr.address = "2480 meadowvale";
			}
			cus.physicalAddr.city = datasFromDB.get("hCity");
			if (StringUtil.isEmpty(cus.physicalAddr.city)) {
				if(contract.equals("SK")){
					cus.physicalAddr.city = "Saskatoon";
				} cus.physicalAddr.city = "Mississauga";
			}
			cus.physicalAddr.state = datasFromDB.get("hState");
			if (StringUtil.isEmpty(cus.physicalAddr.state)) {
				if(contract.equals("SK")){
					cus.physicalAddr.state = "Saskatchewan";
				} cus.physicalAddr.state = "Ontario";
			}
			cus.physicalAddr.county = datasFromDB.get("hCounty");
		}
		cus.hPhone = datasFromDB.get("hPhone");
		if (StringUtil.isEmpty(cus.hPhone)) {
			cus.hPhone = "8694567896";
		}
		
		// Add default identification to handle the update from 3.04.01.
		String identifierInfo = datasFromDB.get("identifier");
		if (StringUtil.isEmpty(identifierInfo)) {
			toDeleteIdent = true;
			cus.identifier.identifierTypeID = OrmsConstants.IDEN_SKDL_ID;
			cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_NAME_SKDL;
			cus.identifier.identifierNum = new DecimalFormat("00000000").format(new Random().nextInt(99999999));
//			cus.identifier.state = "Saskatchewan";
		} else {
			// sample data: Non-Resident Driver's Licence #|1234567|United States| |; (include a white space if no value for the sub field);  RCMP #|1234567| |Alberta|
			cus.identifier.identifierType = identifierInfo.split("\\|")[0];
			cus.identifier.identifierNum = identifierInfo.split("\\|")[1];
			cus.identifier.country = identifierInfo.split("\\|")[2];
			cus.identifier.state = identifierInfo.split("\\|")[3];
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_web_hf_signupaccount";
	}

}
