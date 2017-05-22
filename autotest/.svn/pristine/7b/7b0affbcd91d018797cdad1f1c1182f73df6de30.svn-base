package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.purchaseorder;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:Log into the Inventory Manager in a location other than Contract or Agency, verify Master POS is not available from the drop-down list.
 * @Preconditions:Need a role which is other than Contract or Agency
 * @SPEC:step 1 of TC036511
 * @Task#:Auto-1110
 * @LinkSetUp:  d_assign_user_roles:id=170(ASSIGNLOCATION='GOLDEN MEMORIAL')
 * @author ding1
 * @Date  Jun 27, 2012
 */
public class NotDisplayMasterPOSSetup extends InventoryManagerTestCase {
    public void execute(){
    	im.loginInventoryManager(login);
    	this.verifyDisplayMasterPOSSetup();
    	im.logoutInvManager();
    }

    public void wrapParameters(Object[] args) {
    	login.url = AwoUtil.getOrmsURL(env);
 	    login.contract = "MS Contract";
 	    login.location="Administrator/GOLDEN MEMORIAL";// IMPORTANT:check point, don't change this data.
    }
    
    /**
     * Verify the Master POS Setup option shall not be displayed when user\ufffds Location is other than Contract or Agency.
     */
    private void verifyDisplayMasterPOSSetup(){
    	// get feature drop down list
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		List<String> featureList = topMenu.getTopDropDownListElements();
    	
		// verify whether Master POS Setup in the list or not
		logger.info("Verify whether Master POS Setup in the list or not");
		if(featureList.contains("Master POS Setup")){
			throw new ErrorOnPageException("---The Master POS Setup should not be displayed in the drop down list.");
		}
    }
}
