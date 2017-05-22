package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.DocumentTemplateInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddPrintDocumentTemplateFunction;

/**
 * 
 * @author fliu
 * @Date  March 19, 2012
 */
public class AddPrintDocumentTemplate extends SetupCase{
	LoginInfo loginInfo = new LoginInfo();
	private Object[] args = new Object[3];
	private AddPrintDocumentTemplateFunction func = new AddPrintDocumentTemplateFunction();
	private DocumentTemplateInfo info = new DocumentTemplateInfo();
	
	@Override
	public void executeSetup() {
		func.execute(args);
	}

	/* (non-Javadoc)
	 * @see testAPI.SupportCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_print_doc_temp";
	}
	
	@Override
	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");
		
		info.docTemplateName = datasFromDB.get("docTemplateName");
		info.templateType = datasFromDB.get("templateType");
		info.maxReprintCount = datasFromDB.get("maxReprintCount");
		info.harvestDocument = datasFromDB.get("harvestDocument");
		info.combIndicator = datasFromDB.get("combIndicator");
		info.maxLineCount = datasFromDB.get("maxLineCount");
		info.generateDocNum = datasFromDB.get("generateDocNum");
		info.reGenerateDocNum = datasFromDB.get("reGenerateDocNum");
		args[2] = info;
//		args[2] = DatapoolUtil.fill(DocumentTemplateInfo.class, datasFromDB);
	}
}
