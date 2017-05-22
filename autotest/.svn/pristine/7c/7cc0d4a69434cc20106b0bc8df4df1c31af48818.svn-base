package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddPosSupplierFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This support script was used for add POS supplier in Inventory manager.
 * @Preconditions: Should run after AddWarehouse.java
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Sep 19, 2012
 */
public class AddPosSupplier extends SetupCase {

	private LoginInfo login=new LoginInfo();
	private PosSupplier supplier = new PosSupplier();
	private String warehouseName;
	private AddPosSupplierFunction addPosSupplier = new AddPosSupplierFunction();
	
	public void executeSetup() {
		addPosSupplier.execute(login, warehouseName, supplier);
	}

	@Override
	public void readDataFromDatabase() {
		// login info
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");
		warehouseName = datasFromDB.get("warehouseName");
		
		supplier = new PosSupplier();
		supplier.name = datasFromDB.get("name");
		if(!StringUtil.isEmpty(datasFromDB.get("description"))){
			supplier.description = datasFromDB.get("description");
		}
		
		String[] orderAddr = datasFromDB.get("orderAddress").split(",");
		if(orderAddr.length != 5){
			throw new ErrorOnDataException("The order address should contains ONLY 5 fields:Address, City/Town, State/province, ZIP/Postal Code, Country!");
		}
		// Address, City/Town, State/province, ZIP/Postal Code, Country
		Address orderAddress = new Address();
		orderAddress.address = orderAddr[0];
		orderAddress.city = orderAddr[1];
		orderAddress.state = orderAddr[2];
		orderAddress.zip = orderAddr[3];
		orderAddress.country = orderAddr[4];
		supplier.orderAddress = orderAddress;
		
		String isSameAddr = datasFromDB.get("isPaymentAddrSameOrderAddr");
		if(!StringUtil.isEmpty(isSameAddr)) {
			if("false".equalsIgnoreCase(isSameAddr)) {
				supplier.isPaymentAddrSameOrderAddr = false;
			}
		}

		String payAddr = datasFromDB.get("paymentAddress");
		if(!StringUtil.isEmpty(payAddr) && !supplier.isPaymentAddrSameOrderAddr) {
			String[] payAddress = payAddr.split(",");
			if(payAddress.length != 5){
				throw new ErrorOnDataException("The payment address should contains ONLY 5 fields:Address, City/Town, State/province, ZIP/Postal Code, Country!");
			}
			// Address, City/Town, State/province, ZIP/Postal Code, Country
			Address paymentAddr = new Address();
			paymentAddr.address = payAddress[0];
			paymentAddr.city = payAddress[1];
			paymentAddr.state = payAddress[2];
			paymentAddr.zip = payAddress[3];
			orderAddress.country = payAddress[4];
			supplier.paymentAddress = paymentAddr;
		}

		String primPocDB = datasFromDB.get("primPoc");
		if(!StringUtil.isEmpty(primPocDB)){
			String[] primPocDBArray = primPocDB.split(",");
			if(primPocDBArray.length != 5){
				throw new ErrorOnDataException("The Primary POC should contains ONLY 5 fields:Last Name, First Name, Phone, Fax, Email!");
			}
			// Last Name, First Name, Phone, Fax, Email
			supplier.primPoc.lastName = primPocDBArray[0];
			supplier.primPoc.firstName = primPocDBArray[1];
			supplier.primPoc.phone = primPocDBArray[2];
			supplier.primPoc.fax = primPocDBArray[3];
			supplier.primPoc.email = primPocDBArray[4];
		}
		
		String otherPocDB = datasFromDB.get("otherPoc");
		if(!StringUtil.isEmpty(otherPocDB)){
			String[] otherPocArray = otherPocDB.split(",");
			if(otherPocArray.length != 5){
				throw new ErrorOnDataException("The Other POC should contains ONLY 5 fields:Last Name, First Name, Phone, Fax, Email!");
			}
			// Last Name, First Name, Phone, Fax, Email
			supplier.otherPoc.lastName = otherPocArray[0];
			supplier.otherPoc.firstName = otherPocArray[1];
			supplier.otherPoc.phone = otherPocArray[2];
			supplier.otherPoc.fax = otherPocArray[3];
			supplier.otherPoc.email = otherPocArray[4];
		}

		if(!StringUtil.isEmpty(datasFromDB.get("paymentTerms"))){
			supplier.paymentTerms = datasFromDB.get("paymentTerms");
		}
		
		if(!StringUtil.isEmpty(datasFromDB.get("paymentMethod"))){
			supplier.paymentMethod = datasFromDB.get("paymentMethod");
		}
		
		if(!StringUtil.isEmpty(datasFromDB.get("shippingMethod"))){
			supplier.shippingMethod = datasFromDB.get("shippingMethod");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_inv_add_possupplier";
		queryDataSql = "";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
	}
}
