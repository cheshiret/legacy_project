package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AssignPriToStoreFunction;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang8
 * @Date  Mar 19, 2012
 */
public class AssignPriToStore extends SetupCase{

	private Object[] args = new Object[4];
	private AssignPriToStoreFunction assignPriToStroeFunc = new AssignPriToStoreFunction();
	
	public void executeSetup() {
		assignPriToStroeFunc.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_assi_pri_to_store"; 
	}
	
	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");
		args[2] = datasFromDB.get("priCode");
		args[3] = this.splitTextByComma(datasFromDB.get("locationClass"));
	}

	private String[] splitTextByComma(String text){
		String[] list = new String[]{};
		if(text.contains(",")){
			list =  text.split(",", 0);
		}else if(!text.equals("")){
			list = new String[]{text};
		}
		return list;
	}
}
