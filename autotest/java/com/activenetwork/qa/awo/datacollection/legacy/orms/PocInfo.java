package com.activenetwork.qa.awo.datacollection.legacy.orms;

public class PocInfo {
	 private String lastName = "";
	   
	 private String firstName = "";
   
	 private String phone = "";
   
	 private String fax = "";
   
	 private String email = "";
	 
	 private Address pocAddress = new Address();
	 /**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the pocAddress
	 */
	public Address getPocAddress() {
		return pocAddress;
	}

	/**
	 * @param pocAddress the pocAddress to set
	 */
	public void setPocAddress(Address pocAddress) {
		this.pocAddress = pocAddress;
	}

}
