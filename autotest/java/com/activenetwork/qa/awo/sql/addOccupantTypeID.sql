alter session set current_schema=live_ID;
declare
        cnt             VARCHAR(30);
    BEGIN
       insert into d_loc_attr_value values(get_Sequence('d_loc_attr_value'),'311018','10215', 'Y');
       SELECT COUNT(*) into cnt FROM P_OCCUPANT_REQ_INF WHERE INF_REQ_TYPE_ID = 2 AND LOC_ID =1 AND OTHER_OCC_IND =1 AND PRIM_OCC_IND =1 AND OTHER_INF_REQ_TYP_ID =0 AND FIRST_LAST_NAME_IND =0 AND FULL_CUST_INFO_IND =1 AND DELETED_IND =0 AND OCC_TYPE_IND=1;
       IF cnt=0 THEN
           --make sure Deleted_ind of other records is 1 in table p_occupant_req_inf.
           INSERT INTO P_OCCUPANT_REQ_INF VALUES(GET_SEQUENCE('P_OCCUPANT_REQ_INF'),2,1,1,1,0,0,1,NULL,0,1, NULL);
        END IF;
        
        
        SELECT COUNT(*) into cnt FROM p_admission_prd_cat where ADMISSION_TYPE_ID = 1 and PRDGRP_CAT_ID = 3 and ACTIVE_IND = 1;
        IF cnt=0 THEN
           INSERT INTO P_ADMISSION_PRD_CAT (ID,ADMISSION_TYPE_ID,PRDGRP_CAT_ID,ACTIVE_IND)VALUES(GET_SEQUENCE('P_ADMISSION_PRD_CAT'),1,3,1);--Adult
        END IF;
        
        SELECT COUNT(*) into cnt FROM p_admission_prd_cat where ADMISSION_TYPE_ID = 2 and PRDGRP_CAT_ID = 3 and ACTIVE_IND = 1;
        IF cnt=0 THEN
           INSERT INTO P_ADMISSION_PRD_CAT (ID,ADMISSION_TYPE_ID,PRDGRP_CAT_ID,ACTIVE_IND)VALUES(GET_SEQUENCE('P_ADMISSION_PRD_CAT'),2,3,1);--Youth
        END IF;
    END;
/
commit;