package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Jul 18, 2013
 */
public class AssociateEntranceRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		String schema = TestProperty.getProperty(env + ".db.schema.prefix") + (login.contract.equals("NRSO") ? "NRRS" : login.contract);
		String locationID = DataBaseFunctions.getFacilityID(ruleData.location, schema);
		String associateEntranceID = adm.getProductID("Product Name", ruleData.associateEntrance, locationID, schema);
		
		ruleCondition.setRuleCondition(Attribute.AssociatedEntrance, Integer.parseInt(associateEntranceID));
	}
}
