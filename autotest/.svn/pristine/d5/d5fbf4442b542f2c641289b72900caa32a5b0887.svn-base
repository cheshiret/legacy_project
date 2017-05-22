package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddPosProductFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This support script was used for add POS product in field manager, 
 * the unit price will be set up for POS,
 * and also adjust POS Inventory if specify POS qtyonhand
 * @Preconditions: 
 * (1)This support script should be run after sql(SetupExtraDecimalIndicatorForPOSPrdGrp.sql) setup
 * Cause some POS product need extra decimal indicator attribute which setup on product group 'POS' by sql 
 * (2)Add account schedule in Finance Manager for POS product group
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Sep 9, 2012
 */
public class AddPosProduct extends SetupCase {

	private LoginInfo login = new LoginInfo();
	private POSInfo pos;
	private String warehouseName;
	private AddPosProductFunction addPosPrd = new AddPosProductFunction();
	
	public void executeSetup() {
		addPosPrd.execute(login, warehouseName, pos);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");
		
		warehouseName = datasFromDB.get("warehouseName");
		
		pos = new POSInfo();
		pos.product = datasFromDB.get("product");
		if(!StringUtil.isEmpty(datasFromDB.get("PRODUCTCODE"))){
			pos.productCode = datasFromDB.get("PRODUCTCODE");
		}
		
		if(!StringUtil.isEmpty(datasFromDB.get("PRDCLASS"))){
			pos.productClass = datasFromDB.get("PRDCLASS");
		}
		
		if(!StringUtil.isEmpty(datasFromDB.get("PRDSUBCLASS"))){
			pos.productSubClass = datasFromDB.get("PRDSUBCLASS");
		}

		pos.productGroup = datasFromDB.get("productgroup");
		pos.inventoryType = datasFromDB.get("invtype");
		
		if(null == datasFromDB.get("relationType")){
			pos.productRelactionshipType = "Individual";// Individual is default value
		} else {
			pos.productRelactionshipType = datasFromDB.get("relationType");
		}
		
		pos.productDescription = datasFromDB.get("description");
		if(!StringUtil.isEmpty(datasFromDB.get("SERIALTYPE"))){
			pos.serilizType = datasFromDB.get("SERIALTYPE");
		}

		if(!StringUtil.isEmpty(datasFromDB.get("SERIALREFERENCEID"))){
			pos.serilizReferenceId = datasFromDB.get("SERIALREFERENCEID");
		}
		
		if(!StringUtil.isEmpty(datasFromDB.get("SERIALIZAENUMTYPE"))){
			pos.serilizeNumberType = datasFromDB.get("SERIALIZAENUMTYPE");
		}

		if(!StringUtil.isEmpty(datasFromDB.get("SERIALFORMAT"))){
			pos.serilizFormat = datasFromDB.get("SERIALFORMAT");
		}

		if(!StringUtil.isEmpty(datasFromDB.get("SERIALINCREMENT"))){
			pos.serilizIncrement = datasFromDB.get("SERIALINCREMENT");
		}
		
		if(!StringUtil.isEmpty(datasFromDB.get("SERIALEXPIRDATERULE"))){
			pos.serilizRule = datasFromDB.get("SERIALEXPIRDATERULE");
		}

		if(!StringUtil.isEmpty(datasFromDB.get("avaliableloc"))){
			pos.availableLocation = datasFromDB.get("avaliableloc");
		}

		if(!StringUtil.isEmpty(datasFromDB.get("specloc"))){
			pos.specificLocation = Boolean.valueOf(datasFromDB.get("specloc"));
		}
		if(pos.specificLocation){
			pos.revLocation.agency = datasFromDB.get("revlocagency");
			pos.revLocation.region = datasFromDB.get("revlocregion");
			pos.revLocation.facility = datasFromDB.get("revlocfacility");
		}

		if(!StringUtil.isEmpty(datasFromDB.get("ACQUIREZIPCODE"))){
			pos.acquierZipCodeInSale = datasFromDB.get("ACQUIREZIPCODE");
		}

		if(!StringUtil.isEmpty(datasFromDB.get("APPLICABLECUST"))){
			pos.applicationCustomer = datasFromDB.get("APPLICABLECUST");
		}
		
		String extraDecimal = datasFromDB.get("extradecimal");
		if(StringUtil.isEmpty(extraDecimal)){
			extraDecimal = "No";//default value
		}
		pos.extraDecimalIndicator = extraDecimal;

		String attrs = datasFromDB.get("ISADDPOSATTR");
		if(!StringUtil.isEmpty(attrs) && "Yes".equalsIgnoreCase(attrs)){
			POSInfo.PosSalesAttributes posAttrs = pos.new PosSalesAttributes();
			posAttrs.attributes = datasFromDB.get("attributes");
		    posAttrs.displaySequence = datasFromDB.get("displaySequence");
		    posAttrs.mandatory = datasFromDB.get("mandatory");
		    posAttrs.active = datasFromDB.get("active");
		    pos.attributesArray.add(posAttrs);
		}

		if(!StringUtil.isEmpty(datasFromDB.get("PRODUCTCLASS"))){
			pos.productClass = datasFromDB.get("PRODUCTCLASS");
		}

		if(!StringUtil.isEmpty(datasFromDB.get("PRODUCTSUBCLASS"))){
			pos.productSubClass = datasFromDB.get("PRODUCTSUBCLASS");
		}
		
		if(!StringUtil.isEmpty(datasFromDB.get("DEFAULTUNITPRICE"))){
			pos.defaultUnitPrice = datasFromDB.get("DEFAULTUNITPRICE");
		}

		if(!StringUtil.isEmpty(datasFromDB.get("OVERRIDEUNITPRICE"))){
			pos.overrideUnitPrice = datasFromDB.get("OVERRIDEUNITPRICE");
		}

		if(!StringUtil.isEmpty(datasFromDB.get("BARCODE"))){
			String[] bars = datasFromDB.get("BARCODE").split(";");
			for(String bar: bars)
			{
				if(!StringUtil.isEmpty(bar))
				{
					POSInfo.BarCode barcode = pos.new BarCode();
					barcode.barCode = bar;
					pos.barcodeList.add(barcode);
				}
			}
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_inv_add_pos";
		
		ids = "200";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
	}
}
