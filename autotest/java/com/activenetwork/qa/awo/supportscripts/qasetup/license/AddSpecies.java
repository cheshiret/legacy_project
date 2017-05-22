package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Species;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddSpeciesFunction;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: the support script is to add species.
 * 1. The harvest designation should be unique and the default value will be the max id of the existing record plus 1. 
 * 2. If there are already 99 species in the contract, no new species can be added.
 * 3. If the harvest designation and/or description of one record in the datapool are the same as the existing one in the system, the record won't be added.
 * 
 * @Preconditions:
 * 1. The contract, location and description should be required in the datapool.
 * 2. The description should be unique in the same contract.
 * 3. The harvesDesig should be unique, but not required in the datapool. If it is empty in the datapool, a default one will be used in the case. 
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Apr 17, 2012
 */
public class AddSpecies extends SetupCase {	
	private LoginInfo login = new LoginInfo();
	private Species species = new Species();
	private boolean useDefaultHarDes;
	private AddSpeciesFunction func = new AddSpeciesFunction();
	
	@Override
	public void executeSetup() {
		Object[] args = new Object[4];
		args[0] = login.contract;
		args[1] = login;
		args[2] = species;
		args[3] = useDefaultHarDes;
		func.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");
		
		species.speciesId = datasFromDB.get("harvestDesig").trim();
		if(StringUtil.isEmpty(species.speciesId)){
			useDefaultHarDes = true;
			species.speciesId = "";
		}else{
			useDefaultHarDes = false;
		}
		species.code = datasFromDB.get("code").trim();
		species.description = datasFromDB.get("description").trim();
		species.locationAlias = datasFromDB.get("locationalias").trim();
		int subTypeNum = Integer.parseInt(datasFromDB.get("subtypenum"));
		species.subTypeList.clear();
		String[] subTypeCodes = datasFromDB.get("stcodes").trim().split(",");
		String[] subTypeDescs = datasFromDB.get("stdescs").trim().split(",");
		for(int i=0; i<subTypeNum; i++){
			species.subTypeList.add(species.new SubType(subTypeCodes[i],subTypeDescs[i]));
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_species";
	}
}
