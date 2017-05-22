--created by pzhu for POS Group 'Donation', Used by AUTO-1307

alter session set current_schema=live_MS;
declare
       prdID             VARCHAR(30);
       attrID_1		 VARCHAR(30);
       attrID_2		 VARCHAR(30);
    BEGIN
       SELECT PRD_GRP_ID into prdID FROM P_PRD_GRP WHERE Upper(prd_grp_name)=Upper('Donation') and PRD_SUBCAT_ID=1;
       SELECT ATTR_ID into attrID_1 FROM VW_D_ATTR_EXT WHERE ATTR_CD='POSProductDefaultUnitPrice';
       SELECT ATTR_ID into attrID_2 FROM VW_D_ATTR_EXT WHERE ATTR_CD='POSProductOverrideDefaultUnitPrice';
       
       INSERT INTO P_PRD_GRP_ATTR VALUES(GET_SEQUENCE('P_PRD_GRP_ATTR'), attrID_1,0,prdID,0,1,0,0,4,0);
       INSERT INTO P_PRD_GRP_ATTR VALUES(GET_SEQUENCE('P_PRD_GRP_ATTR'), attrID_2,0,prdID,0,1,0,0,4,0);       
       
    END;
/
commit;