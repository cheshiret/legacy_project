--Add Minimum Payment Rule for POS, could refer to: http://wiki.reserveamerica.com/display/dev/Minimum+to+Confirm+Setup

ALTER SESSION SET CURRENT_SCHEMA=live_CO;   -- qa3 

--1. Add min payment rule for POS(POS name = 'Annual Pass', POS product group = 'Annual Pass')
--   related cases is:
--   testCases.regression.advanced.orms.financial.fee.calculation.minimumtoconfirm.calculate.call.StartPOSSales
--   testCases.regression.advanced.orms.financial.fee.calculation.minimumtoconfirm.calculate.call.ChargePOSToReservation
--   testCases.regression.advanced.orms.financial.fee.calculation.minimumtoconfirm.calculate.call.AddPOSSalesFromOrderCart



DECLARE
PRODUCT_ID VARCHAR(10);
PRODUCT_GROUP_ID VARCHAR(10);
P_MIN_PMT_CFM_ID varchar(10);

BEGIN

--1.) p_min_pmt_cfm talbe
  SELECT GET_SEQUENCE('P_MIN_PMT_CFM') INTO P_MIN_PMT_CFM_ID FROM P_MIN_PMT_CFM WHERE ROWNUM<=1;
  SELECT PRD_ID INTO PRODUCT_ID FROM P_PRD WHERE PRD_NAME = 'Annual Pass'; 
  SELECT PRD_GRP_ID INTO PRODUCT_GROUP_ID FROM P_PRD_GRP WHERE PRD_GRP_NAME = 'Annual Pass';
  INSERT INTO P_MIN_PMT_CFM (ID, EFFECTIVE_DATE, LOC_ID, PRD_GRP_ID, PRD_ID, ACTIVE_ID, DELETE_ID,PRD_CAT_ID,TICKET_CAT_ID, TRAN_TYP_ID, TRAN_OCCUR_ID, SALES_CHANL_ID, MIN_UNIT_STAY,MIN_NUM_DAYS)  
      VALUES (P_MIN_PMT_CFM_ID,TO_DATE('2/1/2012', 'MM/DD/YYYY'),1,PRODUCT_GROUP_ID,PRODUCT_ID,1,0,4,NULL,NULL,NULL,3,0,0);
      -- could get product ID from p_prd table, such as: select prd_id from p_prd where prd_name = 'Annual Pass'; (such as prd_id = 5317)
      -- could get product grop ID from p_prd_grp table, such as: select prd_grp_id from p_prd_grp where prd_grp_name = 'Annual Pass'; (such as prd_grp_id = 8)
      
      
   --2.) p_min_pmt_entry_cfm table
   INSERT INTO P_MIN_PMT_ENTRY_CFM (ID, P_MIN_PMT_CFM_ID, PRIORITY,ORDER_ITEM_TYPE_ID,FEE_TYPE_ID,RULE_TYPE,AMOUNT)
   VALUES (GET_SEQUENCE('p_min_pmt_entry_cfm'),P_MIN_PMT_CFM_ID,0,0,2,1,0.6);

   INSERT INTO P_MIN_PMT_ENTRY_CFM (ID, P_MIN_PMT_CFM_ID, PRIORITY,ORDER_ITEM_TYPE_ID,FEE_TYPE_ID,RULE_TYPE,AMOUNT)
   VALUES (GET_SEQUENCE('p_min_pmt_entry_cfm'),P_MIN_PMT_CFM_ID,0,0,1,2,5);
  
   --p_min_pmt_cfm_id should come from p_min_pmt_cmf table id attribute
   --MAKE SURE THE AMOUNT VALUE SHOULD NOT BE CHANGED      
END;
/
COMMIT;



--2. Add min payment rule for POS(POS name = 'Pet Fee', POS product group = 'Pet Fee')   
 --  related cases is:
 --  testCases.regression.advanced.orms.financial.fee.calculation.minimumtoconfirm.calculate.call.ChargePOSToEvent

DECLARE
PRODUCT_ID VARCHAR(10);
PRODUCT_GROUP_ID VARCHAR(10);
P_MIN_PMT_CFM_ID VARCHAR(10);

BEGIN

SELECT GET_SEQUENCE('P_MIN_PMT_CFM') INTO P_MIN_PMT_CFM_ID FROM P_MIN_PMT_CFM WHERE ROWNUM<=1;
  SELECT PRD_ID INTO PRODUCT_ID FROM P_PRD WHERE PRD_NAME = 'Pet Fee'; 
  SELECT PRD_GRP_ID INTO PRODUCT_GROUP_ID FROM P_PRD_GRP WHERE PRD_GRP_NAME = 'Pet Fee';

--1.) p_min_pmt_cfm talbe

   INSERT INTO P_MIN_PMT_CFM (ID, EFFECTIVE_DATE, LOC_ID, PRD_GRP_ID, PRD_ID, ACTIVE_ID, DELETE_ID,PRD_CAT_ID,TICKET_CAT_ID, TRAN_TYP_ID, TRAN_OCCUR_ID, SALES_CHANL_ID, MIN_UNIT_STAY,MIN_NUM_DAYS)
   VALUES (P_MIN_PMT_CFM_ID,TO_DATE('2/1/2012', 'MM/DD/YYYY'),1,PRODUCT_GROUP_ID,PRODUCT_ID,1,0,4,NULL,NULL,NULL,3,0,0);

    --could get product ID from p_prd table, such as: select prd_id from p_prd where prd_name = 'Pet Fee'; (such as prd_id = 5319)
    --could get product grop ID from p_prd_grp table, such as: select prd_grp_id from p_prd_grp where prd_grp_name = 'Pet Fee'; (such as prd_grp_id = 10)


--2.) p_min_entry_cfm table

   INSERT INTO P_MIN_PMT_ENTRY_CFM (ID, P_MIN_PMT_CFM_ID, PRIORITY,ORDER_ITEM_TYPE_ID,FEE_TYPE_ID,RULE_TYPE,AMOUNT)
   VALUES (GET_SEQUENCE('p_min_pmt_entry_cfm'),P_MIN_PMT_CFM_ID,0,0,2,2,50);
  
   INSERT INTO P_MIN_PMT_ENTRY_CFM (ID, P_MIN_PMT_CFM_ID, PRIORITY,ORDER_ITEM_TYPE_ID,FEE_TYPE_ID,RULE_TYPE,AMOUNT)
   VALUES (GET_SEQUENCE('p_min_pmt_entry_cfm'),P_MIN_PMT_CFM_ID,0,0,1,1,0.2);

   --p_min_pmt_cfm_id should come from p_min_pmt_cmf table id attribute
   --make sure the amount value should not be changed

END;
/
COMMIT;

--Add Minimum Payment Rule for Ticket, could refer to: 
--http://wiki.reserveamerica.com/display/qa/Functional+QA+Knowledge+Base#FunctionalQAKnowledgeBase-22.ScriptforMinimumtoConfirmSupportPerUnitforTicketingSharedbyJillyZhao

ALTER SESSION SET CURRENT_SCHEMA=live_NRRS;

--1. Add min payment rule for Ticket in facility level

DECLARE
P_MIN_PMT_CFM_ID varchar(10);

BEGIN

--1.) p_min_pmt_cfm talbe

SELECT GET_SEQUENCE('P_MIN_PMT_CFM') INTO P_MIN_PMT_CFM_ID FROM P_MIN_PMT_CFM WHERE ROWNUM<=1;

INSERT INTO P_MIN_PMT_CFM (ID, EFFECTIVE_DATE, LOC_ID, PRD_GRP_ID, PRD_ID, ACTIVE_ID, DELETE_ID,SALES_CHANL_ID,PRD_CAT_ID,TICKET_CAT_ID)    
VALUES (P_MIN_PMT_CFM_ID, TO_DATE('10/21/2012', 'MM/DD/YYYY'), 77813, '', '', '1', '0', 1, 6,null); 

--2.) p_min_entry_cfm table

INSERT INTO P_MIN_PMT_ENTRY_CFM (ID, P_MIN_PMT_CFM_ID, PRIORITY, ORDER_ITEM_TYPE_ID, FEE_TYPE_ID, RULE_TYPE, AMOUNT)      
VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'), P_MIN_PMT_CFM_ID, 0, 0, 2,4,5);
INSERT INTO P_MIN_PMT_ENTRY_CFM (ID, P_MIN_PMT_CFM_ID, PRIORITY, ORDER_ITEM_TYPE_ID, FEE_TYPE_ID, RULE_TYPE, AMOUNT)      
VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'), P_MIN_PMT_CFM_ID, 0, 0, 2,4,1);

END;
/
COMMIT;

--2. Add min payment rule for Ticket 'Lower Cave'

DECLARE
P_MIN_PMT_CFM_ID varchar(10);

BEGIN

--1.) p_min_pmt_cfm talbe

SELECT GET_SEQUENCE('P_MIN_PMT_CFM') INTO P_MIN_PMT_CFM_ID FROM P_MIN_PMT_CFM WHERE ROWNUM<=1;

INSERT INTO P_MIN_PMT_CFM (ID, EFFECTIVE_DATE, LOC_ID, PRD_GRP_ID, PRD_ID, ACTIVE_ID,DELETE_ID,SALES_CHANL_ID,PRD_CAT_ID,TICKET_CAT_ID) 
VALUES (P_MIN_PMT_CFM_ID,TO_DATE('10/21/2012','MM/DD/YYYY'), 77813, 96606, 206320, '1', '0', 1, 6,null);

--2.) p_min_entry_cfm table

INSERT INTO P_MIN_PMT_ENTRY_CFM (ID,P_MIN_PMT_CFM_ID,PRIORITY,
ORDER_ITEM_TYPE_ID, FEE_TYPE_ID, RULE_TYPE, AMOUNT)
VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'), P_MIN_PMT_CFM_ID, 0,0, 2,4,10); 
INSERT INTO P_MIN_PMT_ENTRY_CFM (ID,P_MIN_PMT_CFM_ID,PRIORITY,
ORDER_ITEM_TYPE_ID, FEE_TYPE_ID,RULE_TYPE, AMOUNT) 
VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'), P_MIN_PMT_CFM_ID, 0, 0,1,4,1);

END;
/
COMMIT;

--3. Add min payment rule for Ticket 'Left Hand Tunnel'

DECLARE
P_MIN_PMT_CFM_ID varchar(10);

BEGIN

--1.) p_min_pmt_cfm talbe

SELECT GET_SEQUENCE('P_MIN_PMT_CFM') INTO P_MIN_PMT_CFM_ID FROM P_MIN_PMT_CFM WHERE ROWNUM<=1;

INSERT INTO P_MIN_PMT_CFM (ID, EFFECTIVE_DATE, LOC_ID, PRD_GRP_ID, PRD_ID, ACTIVE_ID,DELETE_ID,SALES_CHANL_ID,PRD_CAT_ID,TICKET_CAT_ID) 
VALUES (P_MIN_PMT_CFM_ID,TO_DATE('10/21/2012', 'MM/DD/YYYY'), 77813, '96606', '206319', '1', '0', 1, 6,null);

--2.) p_min_entry_cfm table

INSERT INTO P_MIN_PMT_ENTRY_CFM (ID,P_MIN_PMT_CFM_ID,PRIORITY,
ORDER_ITEM_TYPE_ID, FEE_TYPE_ID, RULE_TYPE, AMOUNT)
VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'), P_MIN_PMT_CFM_ID, 0, 0, 2,4,7);
INSERT INTO P_MIN_PMT_ENTRY_CFM (ID,P_MIN_PMT_CFM_ID,PRIORITY,
ORDER_ITEM_TYPE_ID, FEE_TYPE_ID,RULE_TYPE, AMOUNT)
VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'), P_MIN_PMT_CFM_ID, 0, 0, 1,4,3);

END;
/
COMMIT;

--4. Add min payment rule for Ticket 'The Big Room Tour'

DECLARE
P_MIN_PMT_CFM_ID varchar(10);

BEGIN

--1.) p_min_pmt_cfm talbe

SELECT GET_SEQUENCE('P_MIN_PMT_CFM') INTO P_MIN_PMT_CFM_ID FROM P_MIN_PMT_CFM WHERE ROWNUM<=1;

INSERT INTO P_MIN_PMT_CFM (ID, EFFECTIVE_DATE, LOC_ID, PRD_GRP_ID, PRD_ID, ACTIVE_ID,DELETE_ID,SALES_CHANL_ID,PRD_CAT_ID,TICKET_CAT_ID) 
VALUES (P_MIN_PMT_CFM_ID ,TO_DATE('10/21/2012', 'MM/DD/YYYY'), 77813, 96606, 294137, '1', '0', 1, 6,null); 

--2.) p_min_entry_cfm table

INSERT INTO P_MIN_PMT_ENTRY_CFM (ID,P_MIN_PMT_CFM_ID,PRIORITY,
ORDER_ITEM_TYPE_ID, FEE_TYPE_ID, RULE_TYPE, AMOUNT)
VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'), P_MIN_PMT_CFM_ID, 0, 0, 2,4,18.91);
INSERT INTO P_MIN_PMT_ENTRY_CFM (ID,P_MIN_PMT_CFM_ID,PRIORITY,
ORDER_ITEM_TYPE_ID, FEE_TYPE_ID,RULE_TYPE, AMOUNT)
VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'), P_MIN_PMT_CFM_ID, 0, 0, 1,4,9.99);

END;
/
COMMIT;

--5. Add min payment rule for Ticket 'King's Palace'

DECLARE
P_MIN_PMT_CFM_ID varchar(10);

BEGIN

--1.) p_min_pmt_cfm talbe

SELECT GET_SEQUENCE('P_MIN_PMT_CFM') INTO P_MIN_PMT_CFM_ID FROM P_MIN_PMT_CFM WHERE ROWNUM<=1;

INSERT INTO P_MIN_PMT_CFM (ID, EFFECTIVE_DATE, LOC_ID, PRD_GRP_ID, PRD_ID, ACTIVE_ID,DELETE_ID,SALES_CHANL_ID,PRD_CAT_ID,TICKET_CAT_ID) 
VALUES (P_MIN_PMT_CFM_ID,TO_DATE('10/21/2012', 'MM/DD/YYYY'), 77813, 96606, 206318, '1', '0', 1, 6,null); 

--2.) p_min_entry_cfm table

INSERT INTO P_MIN_PMT_ENTRY_CFM (ID,P_MIN_PMT_CFM_ID,PRIORITY,
ORDER_ITEM_TYPE_ID, FEE_TYPE_ID, RULE_TYPE, AMOUNT)
VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'), P_MIN_PMT_CFM_ID, 0, 0, 2,4,9.7);
INSERT INTO P_MIN_PMT_ENTRY_CFM (ID,P_MIN_PMT_CFM_ID,PRIORITY,
ORDER_ITEM_TYPE_ID, FEE_TYPE_ID,RULE_TYPE, AMOUNT)
VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'), P_MIN_PMT_CFM_ID, 0, 0, 1,4,2.3);

END;
/
COMMIT;

--6. Add min payment rule for Ticket 'Slaughter Canyon Cave'

DECLARE
P_MIN_PMT_CFM_ID varchar(10);

BEGIN

--1.) p_min_pmt_cfm talbe

SELECT GET_SEQUENCE('P_MIN_PMT_CFM') INTO P_MIN_PMT_CFM_ID FROM P_MIN_PMT_CFM WHERE ROWNUM<=1;

INSERT INTO P_MIN_PMT_CFM (ID, EFFECTIVE_DATE, LOC_ID, PRD_GRP_ID, PRD_ID, ACTIVE_ID,DELETE_ID,SALES_CHANL_ID,PRD_CAT_ID,TICKET_CAT_ID) 
VALUES (P_MIN_PMT_CFM_ID, TO_DATE('10/21/2012', 'MM/DD/YYYY'), 77813, '96606', '206322', '1', '0', 1, 6,null);

--2.) p_min_entry_cfm table

INSERT INTO P_MIN_PMT_ENTRY_CFM (ID,P_MIN_PMT_CFM_ID,PRIORITY,
ORDER_ITEM_TYPE_ID, FEE_TYPE_ID, RULE_TYPE, AMOUNT)
VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'), P_MIN_PMT_CFM_ID , 0, 0, 2,4,15.1);
INSERT INTO P_MIN_PMT_ENTRY_CFM (ID,P_MIN_PMT_CFM_ID,PRIORITY,
ORDER_ITEM_TYPE_ID, FEE_TYPE_ID,RULE_TYPE, AMOUNT)
VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'), P_MIN_PMT_CFM_ID , 0, 0, 1,4,1.1);

END;
/
COMMIT;

--7. Add min payment rule for Ticket 'Hall of The White Giant'

DECLARE
P_MIN_PMT_CFM_ID varchar(10);

BEGIN

--1.) p_min_pmt_cfm talbe

SELECT GET_SEQUENCE('P_MIN_PMT_CFM') INTO P_MIN_PMT_CFM_ID FROM P_MIN_PMT_CFM WHERE ROWNUM<=1;

INSERT INTO P_MIN_PMT_CFM (ID, EFFECTIVE_DATE, LOC_ID, PRD_GRP_ID, PRD_ID, ACTIVE_ID,DELETE_ID,SALES_CHANL_ID,PRD_CAT_ID,TICKET_CAT_ID) 
VALUES (P_MIN_PMT_CFM_ID,TO_DATE('10/21/2012', 'MM/DD/YYYY'), 77813, '96606', '206316', '1', '0', 1, 6,null);

--2.) p_min_entry_cfm table

INSERT INTO P_MIN_PMT_ENTRY_CFM (ID,P_MIN_PMT_CFM_ID,PRIORITY,
ORDER_ITEM_TYPE_ID, FEE_TYPE_ID, RULE_TYPE, AMOUNT)
VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'), P_MIN_PMT_CFM_ID, 0, 0, 2,4,19.9);
INSERT INTO P_MIN_PMT_ENTRY_CFM (ID,P_MIN_PMT_CFM_ID,PRIORITY,
ORDER_ITEM_TYPE_ID, FEE_TYPE_ID,RULE_TYPE, AMOUNT)
VALUES (get_sequence('P_MIN_PMT_ENTRY_CFM'), P_MIN_PMT_CFM_ID, 0, 0, 1,4,1.1);

END;
/
COMMIT;




