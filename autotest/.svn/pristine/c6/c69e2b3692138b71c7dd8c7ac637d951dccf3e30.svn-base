package com.activenetwork.qa.awo.supportscripts.function.db;

import com.activenetwork.qa.awo.sql.PosDataForDB;
import com.activenetwork.qa.awo.sql.SetupPOSSql;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

public class SetupPosFunction extends FunctionCase{
	private PosDataForDB data = new PosDataForDB();
	private String schema = null;
	
	@Override
	public void wrapParameters(Object[] param) {
		String contract = param[0].toString();
		schema=DataBaseFunctions.getSchemaName(contract,env);
		data = (PosDataForDB)param[1];		
	}

	@Override
	public void execute() {
		dbCon.resetSchema(schema);
		SetupPOSSql setup = new SetupPOSSql();
		// add POS group if not exists
		data.groupId = setup.addPOSGroup(data.groupName);
		// add POS if not exists
		data.productId = setup.addPOS(data.productName, data.groupId,
				data.barCode, data.loc_id, data.revenue_loc_id);
		// assign POS to location
		if (!StringUtil.isEmpty(data.sale_loc_id)) {
			if (data.sale_loc_id.contains(",")) {
				String[] ids = data.sale_loc_id.split(",");
				for (int i = 0; i < ids.length; i++) {
					setup.assignProductToLocation(Integer.parseInt(ids[i]),
							data.productId, data.productName);
				}
			} else {
				setup.assignProductToLocation(Integer.parseInt(data.sale_loc_id),
						data.productId, data.productName);
			}
		}
		newAddValue = String.valueOf(data.productId);
	}

}
