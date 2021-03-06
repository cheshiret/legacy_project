--Add by Sara
ALTER SESSION SET CURRENT_SCHEMA=LIVE_NM;

BEGIN
insert into D_REF_CUST_PASS_TYPE (ID, CD, NAME, DSCR, ACTIVE_IND, EXPIRY_DATE_IND, PASS_HOLDER_LEVEL_TYP_ID, HOLDER_NAME_IND, PASS_REL_TYP_ID, PARENT_PASS_TYP_ID, DURATION_DAYS, PROOF_REQ_TYPE_ID, PASS_NUM_IND)
values (101,'PassProgSingle','PassProgSingle','Pass Program - Single', 1, 1, '', 1, 1, '', '', 1, 2);
insert into D_REF_CUST_PASS_TYPE (ID, CD, NAME, DSCR, ACTIVE_IND, EXPIRY_DATE_IND, PASS_HOLDER_LEVEL_TYP_ID, HOLDER_NAME_IND, PASS_REL_TYP_ID, PARENT_PASS_TYP_ID, DURATION_DAYS, PROOF_REQ_TYPE_ID, PASS_NUM_IND)
values (102,'PassProgParent','PassProgParent','Pass Program - Parent1', 1, 2, '1', 1, 2, '', '', 1, 2);
insert into D_REF_CUST_PASS_TYPE (ID, CD, NAME, DSCR, ACTIVE_IND, EXPIRY_DATE_IND, PASS_HOLDER_LEVEL_TYP_ID, HOLDER_NAME_IND, PASS_REL_TYP_ID, PARENT_PASS_TYP_ID, DURATION_DAYS, PROOF_REQ_TYPE_ID, PASS_NUM_IND)
values (103,'PassProgParent2','PassProgParent2','Pass Program - Parent2', 1, 1, '', '', 2, '', '', 1, 1);
insert into D_REF_CUST_PASS_TYPE (ID, CD, NAME, DSCR, ACTIVE_IND, EXPIRY_DATE_IND, PASS_HOLDER_LEVEL_TYP_ID, HOLDER_NAME_IND, PASS_REL_TYP_ID, PARENT_PASS_TYP_ID, DURATION_DAYS, PROOF_REQ_TYPE_ID, PASS_NUM_IND)
values (104,'PassProgChild1','PassProgChild1','Pass Program - Child1', 1, 1, '', '1', 3, '102', '', 1, 2);
insert into D_REF_CUST_PASS_TYPE (ID, CD, NAME, DSCR, ACTIVE_IND, EXPIRY_DATE_IND, PASS_HOLDER_LEVEL_TYP_ID, HOLDER_NAME_IND, PASS_REL_TYP_ID, PARENT_PASS_TYP_ID, DURATION_DAYS, PROOF_REQ_TYPE_ID, PASS_NUM_IND)
values (105,'PassProgChild2','PassProgChild2','Pass Program - Child2', 1, 1, '', '', 3, '103', '', 1, 1);
END;
/
COMMIT; 

ALTER SESSION SET CURRENT_SCHEMA=LIVE_NRRS;

BEGIN
insert into D_REF_CUST_PASS_TYPE (ID, CD, NAME, DSCR, ACTIVE_IND, EXPIRY_DATE_IND, PASS_HOLDER_LEVEL_TYP_ID, HOLDER_NAME_IND, PASS_REL_TYP_ID, PARENT_PASS_TYP_ID, DURATION_DAYS, PROOF_REQ_TYPE_ID, PASS_NUM_IND)
values (101,'PassProgSingle','PassProgSingle','Pass Program - Single', 1, 1, '', 1, 1, '', '', 1, 2);
insert into D_REF_CUST_PASS_TYPE (ID, CD, NAME, DSCR, ACTIVE_IND, EXPIRY_DATE_IND, PASS_HOLDER_LEVEL_TYP_ID, HOLDER_NAME_IND, PASS_REL_TYP_ID, PARENT_PASS_TYP_ID, DURATION_DAYS, PROOF_REQ_TYPE_ID, PASS_NUM_IND)
values (102,'PassProgParent','PassProgParent','Pass Program - Parent1', 1, 2, '1', 1, 2, '', '', 1, 2);
insert into D_REF_CUST_PASS_TYPE (ID, CD, NAME, DSCR, ACTIVE_IND, EXPIRY_DATE_IND, PASS_HOLDER_LEVEL_TYP_ID, HOLDER_NAME_IND, PASS_REL_TYP_ID, PARENT_PASS_TYP_ID, DURATION_DAYS, PROOF_REQ_TYPE_ID, PASS_NUM_IND)
values (103,'PassProgParent2','PassProgParent2','Pass Program - Parent2', 1, 1, '', '', 2, '', '', 1, 1);
insert into D_REF_CUST_PASS_TYPE (ID, CD, NAME, DSCR, ACTIVE_IND, EXPIRY_DATE_IND, PASS_HOLDER_LEVEL_TYP_ID, HOLDER_NAME_IND, PASS_REL_TYP_ID, PARENT_PASS_TYP_ID, DURATION_DAYS, PROOF_REQ_TYPE_ID, PASS_NUM_IND)
values (104,'PassProgChild1','PassProgChild1','Pass Program - Child1', 1, 1, '', '1', 3, '102', '', 1, 2);
insert into D_REF_CUST_PASS_TYPE (ID, CD, NAME, DSCR, ACTIVE_IND, EXPIRY_DATE_IND, PASS_HOLDER_LEVEL_TYP_ID, HOLDER_NAME_IND, PASS_REL_TYP_ID, PARENT_PASS_TYP_ID, DURATION_DAYS, PROOF_REQ_TYPE_ID, PASS_NUM_IND)
values (105,'PassProgChild2','PassProgChild2','Pass Program - Child2', 1, 1, '', '', 3, '103', '', 1, 1);
END;
/
COMMIT; 