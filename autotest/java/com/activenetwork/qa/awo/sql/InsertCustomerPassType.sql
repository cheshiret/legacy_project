ALTER SESSION SET CURRENT_SCHEMA=live_KY;

BEGIN

INSERT INTO D_REF_CUST_PASS_TYPE (ID, CD, NAME, DSCR, ACTIVE_IND, DURATION_DAYS, PROOF_REQ_TYPE_ID) VALUES (1, 'CustomerType', 'KY Pass',  'KY Pass', 1,0,0);
INSERT INTO D_REF_CUST_PASS_TYPE (ID, CD, NAME, DSCR, ACTIVE_IND, DURATION_DAYS, PROOF_REQ_TYPE_ID) VALUES (2, 'CustomerType', 'Test Pass',  'Test Pass', 1,0,0);
INSERT INTO D_REF_CUST_PASS_TYPE (ID, CD, NAME, DSCR, ACTIVE_IND, DURATION_DAYS, PROOF_REQ_TYPE_ID) VALUES (3, 'CustomerType', 'LT Pass',  'LT Pass', 1,0,0);

END;
/
COMMIT;