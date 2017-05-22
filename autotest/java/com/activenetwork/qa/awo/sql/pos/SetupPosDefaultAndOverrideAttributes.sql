alter session set current_schema=live_SC;
INSERT INTO P_PRD_GRP_ATTR (PRD_GRP_ATTR_ID, ATTR_ID, MANDT_IND, PRD_GRP_ID, STATUS_ID, ACTIVE_IND, DELETED_IND, CHARGEABLE, PRD_CAT_ID, WEB_KEY_ATTR) -- Full Hookup
select GET_SEQUENCE('p_prd_grp_attr'),10411, 0, b.prd_grp_id,NULL, 1, 0, NULL, 4, 0 from (select prd_grp_id from P_PRD_GRP where prd_grp_name like 'Activity (non-Golf)') b;
commit;

INSERT INTO P_PRD_GRP_ATTR (PRD_GRP_ATTR_ID, ATTR_ID, MANDT_IND, PRD_GRP_ID, STATUS_ID, ACTIVE_IND, DELETED_IND, CHARGEABLE, PRD_CAT_ID, WEB_KEY_ATTR) -- Full Hookup
select GET_SEQUENCE('p_prd_grp_attr'),10412, 0, b.prd_grp_id,NULL, 1, 0, NULL, 4, 0 from (select prd_grp_id from P_PRD_GRP where prd_grp_name like 'Activity (non-Golf)') b;
commit;


alter session set current_schema=live_NRRS;
INSERT INTO P_PRD_GRP_ATTR (PRD_GRP_ATTR_ID, ATTR_ID, MANDT_IND, PRD_GRP_ID, STATUS_ID, ACTIVE_IND, DELETED_IND, CHARGEABLE, PRD_CAT_ID, WEB_KEY_ATTR) -- Full Hookup
select GET_SEQUENCE('p_prd_grp_attr'),10411, 0, b.prd_grp_id,NULL, 1, 0, NULL, 4, 0 from (select prd_grp_id from P_PRD_GRP where prd_grp_name like 'Kindling') b;
commit;

INSERT INTO P_PRD_GRP_ATTR (PRD_GRP_ATTR_ID, ATTR_ID, MANDT_IND, PRD_GRP_ID, STATUS_ID, ACTIVE_IND, DELETED_IND, CHARGEABLE, PRD_CAT_ID, WEB_KEY_ATTR) -- Full Hookup
select GET_SEQUENCE('p_prd_grp_attr'),10412, 0, b.prd_grp_id,NULL, 1, 0, NULL, 4, 0 from (select prd_grp_id from P_PRD_GRP where prd_grp_name like 'Kindling') b;
commit;


alter session set current_schema=live_NC;
INSERT INTO P_PRD_GRP_ATTR (PRD_GRP_ATTR_ID, ATTR_ID, MANDT_IND, PRD_GRP_ID, STATUS_ID, ACTIVE_IND, DELETED_IND, CHARGEABLE, PRD_CAT_ID, WEB_KEY_ATTR) -- Full Hookup
select GET_SEQUENCE('p_prd_grp_attr'),10411, 0, b.prd_grp_id,NULL, 1, 0, NULL, 4, 0 from (select prd_grp_id from P_PRD_GRP where prd_grp_name like 'APPAREL') b;
commit;

INSERT INTO P_PRD_GRP_ATTR (PRD_GRP_ATTR_ID, ATTR_ID, MANDT_IND, PRD_GRP_ID, STATUS_ID, ACTIVE_IND, DELETED_IND, CHARGEABLE, PRD_CAT_ID, WEB_KEY_ATTR) -- Full Hookup
select GET_SEQUENCE('p_prd_grp_attr'),10412, 0, b.prd_grp_id,NULL, 1, 0, NULL, 4, 0 from (select prd_grp_id from P_PRD_GRP where prd_grp_name like 'APPAREL') b;
commit;