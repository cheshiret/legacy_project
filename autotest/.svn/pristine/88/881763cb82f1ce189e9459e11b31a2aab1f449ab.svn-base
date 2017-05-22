--- Set up POS extra decimal indicator attribute for POS prodcut group 

alter session set current_schema=live_MS;

INSERT INTO P_PRD_GRP_ATTR ( prd_grp_attr_id, attr_id, mandt_ind, prd_grp_id, status_id, active_ind, deleted_ind, chargeable, prd_cat_id, web_key_attr) 
(select get_sequence('P_PRD_GRP_ATTR'), 4901, 0,  ppg.prd_grp_id, 0, 1, 0, 0, 4, 0 from P_PRD_GRP ppg where prd_grp_name='POS');
commit;
