--Update docuemnt auto returned settings to NO for the vendor "Ministry of Environment" in SK contract

ALTER SESSION SET CURRENT_SCHEMA=live_SK;

BEGIN

	update F_VENDOR_FINANCIAL_CONFIG set rtn_voided_doc=0 where vendor_id=(select id from d_vendor where name='Ministry of Environment');
	commit;
END;
