/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.db;

import com.activenetwork.qa.awo.sql.ResetPWForVeriFoneSql;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date  Nov 23, 2012
 */
public class ResetPWForVeriFoneFunction extends FunctionCase{
	private String schema,regID,pw,locID;
	
	@Override
	public void execute() {
		dbCon.resetSchema(schema);
		ResetPWForVeriFoneSql setup = new ResetPWForVeriFoneSql();
		
		setup.resetPassWord(regID, pw, locID);
	}

	
	@Override
	public void wrapParameters(Object[] param) {
		String contract = param[0].toString();
		schema=DataBaseFunctions.getSchemaName(contract,env);
		regID=param[1].toString();
		pw=param[2].toString();
		locID=param[3].toString();
	}

}
