
alter session set current_schema=live_MS;
insert into C_CUST_CLASS_CFG (ID, APP_ID, PRD_GRP_CAT_ID, PRD_SUBCAT_ID, CUST_CLASS_ID)
values (GET_SEQUENCE('C_CUST_CLASS_CFG'),19,10,null,3);

--19 means via License Manager to purchase (call manager :app_id=5)
--10 means can purchase privielge product (Vehicle:Prd_grp_cat_id=11;POS:Prd_grp_cat_id=4)
--prd_subcat_id mainly for vehicle product  Sub cat: 5-registration, 6-title, 7-insepction
--3 means the customer class is enterprise (1:Individual, 2: business)

insert into C_CUST_CLASS_CFG values (get_sequence('C_CUST_CLASS_CFG'),5,10,Null,2);

--5 means via Call Manager to purchase (call manager :app_id=5)
--10 means can purchase privielge product (Vehicle:Prd_grp_cat_id=11;POS:Prd_grp_cat_id=4)
--2 means the customer class is business)(1:Individual, 2: business)
--for test case: testCases.regression.advanced.orms.order.pos.extradecimalplace.inventory.VerifyQuantityWithExtraDecimalFalse
commit;
