---------------------------Setup Equipment-------------------------------- 
alter session set current_schema =live_MS;
---------------------------Setup Equipment for 'MDWFP Headquarters' location class in 'Call Manager'
insert into D_EQUIPMENT_TYPE VALUES (GET_SEQUENCE('D_EQUIPMENT_TYPE'), 'MDWFP Headquarters-CallMgr', 5, 1, 0, null);