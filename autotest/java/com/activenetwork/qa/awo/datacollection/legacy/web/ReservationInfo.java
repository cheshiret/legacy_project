package com.activenetwork.qa.awo.datacollection.legacy.web;

public class ReservationInfo {

	public String contract;

	public String resID;

	public String phone;

	public String lastName;

	public String firstName;

	public String email;

	public String park;

	public String loop;

	public String siteCode;

	public String arrivalDate;

	public String departDate;

	public String resStatus;

	public String processStatus;

	public String customerLastName;

	public String customerfirstName;

	public String payment1ID;

	public String payment1Method;

	public String payment1Status;

	public String refund1ID;

	public String refund1Status;

	public String payment2ID;

	public String payment2Method;

	public String payment2Status;

	public String refund2ID;

	public String refund2Status;

	public ReservationInfo() {
		this.setEmpty();
	}

	public void setEmpty() {
		contract = "";
		resID = "";
		phone = "";
		lastName = "";
		firstName = "";
		email = "";
		park = "";
		loop = "";
		siteCode = "";
		arrivalDate = "";
		departDate = "";
		resStatus = "";
		processStatus = "";
		customerLastName = "";
		customerfirstName = "";
		payment1ID = "";
		payment1Method = "";
		payment1Status = "";
		refund1ID = "";
		refund1Status = "";
		payment2ID = "";
		payment2Method = "";
		payment2Status = "";
		refund2ID = "";
		refund2Status = "";
	}

}
