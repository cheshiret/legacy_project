---------------------------Contract schema level, set up the entire NY contract electricity hookup to simple-option list
alter session set current_schema =live_NY;
-------Step 1: Make sure attr_type_id=3 (simple-option list). If not, updte it to 3; If no record, add one.
---select attr_type_id from all_d_attr where attr_id=218 and contract = 'NY';  218:Electricity Hookup
update all_d_attr set attr_type_id = 3 where attr_id=218 and contract = 'NY';
insert into all_d_attr values('NY',0,218,'Physical',null,'Electricity Hookup','Electricity Hookup',3,null,null,null,null,1,0,120,1,1,1,null,null,1,0,'Hookups','Electricity',240,0,null,null,null,null,null);

-------Step 2: add electric hookup options info SC and NY contract
--select * from all_d_attr where attr_id=218; -- you can see contract specific attributes defined. This is the master list of all attributes. Again, attr_id=218 stands for electricity hookup.
--select * from d_attr_cnst where attr_id=218; -- these are all the possible values for the attributes. (e.g. 15amp, 30amp, Yes, Multiple).
insert into d_attr_cnst values(1,null,null,'No',218,null,'1','0');
insert into d_attr_cnst values(2,null,null,'None',218,null,'1','0');
insert into d_attr_cnst values(3,null,null,'Yes',218,null,'1','0');
insert into d_attr_cnst values(4,null,null,'Multiple',218,null,'1','0');

-------Step 3: attributes are assigned to product groups
--select * from p_prd_grp_attr where attr_id=218 and prd_grp_attr_id=41; --make sure acitve_ind=1 and deleted_ind=0--attributes are assigned to product groups if you want to use them (I chose to work with prd_grp_attr_id=41 which is for “Standard Site?i.e. prd_grp_id=5)
--select * from p_prd_grp_attr_cnst where prd_grp_attr_id=41; -- make sure active_ind=1 and deleted_ind=0 --attribute values are assigned here as well for prd_grp_attr_id=41
insert into p_prd_grp_attr_cnst (PRD_GRP_ATTR_CNST_ID, ATTR_CNST_ID, PRD_GRP_ATTR_ID,ACTIVE_IND, DELETED_IND)values(200000, 1, 41, '1', '0');
insert into p_prd_grp_attr_cnst (PRD_GRP_ATTR_CNST_ID, ATTR_CNST_ID, PRD_GRP_ATTR_ID,ACTIVE_IND, DELETED_IND)values(200001, 2, 41, '1', '0');
insert into p_prd_grp_attr_cnst (PRD_GRP_ATTR_CNST_ID, ATTR_CNST_ID, PRD_GRP_ATTR_ID,ACTIVE_IND, DELETED_IND)values(200002, 3, 41, '1', '0');
insert into p_prd_grp_attr_cnst (PRD_GRP_ATTR_CNST_ID, ATTR_CNST_ID, PRD_GRP_ATTR_ID,ACTIVE_IND, DELETED_IND)values(200003, 4, 41, '1', '0');

commit;


---------------------------Contract schema level, set up the entire SC contract electricity hookup to multi-option list
alter session set current_schema =live_SC;

-------Step 1: Make sure attr_type_id=4 (multi-option list). If not, updte it to 4; If no record, add one.
--select attr_type_id from all_d_attr where attr_id=218 and contract = 'SC';  218:Electricity Hookup
update all_d_attr set attr_type_id = 4 where attr_id=218 and contract = 'SC';
insert into all_d_attr values('SC',0,218,'Physical',null,'Electricity Hookup','Electricity Hookup',4,null,null,null,null,1,0,120,1,1,1,null,null,1,0,'Hookups','Electricity',240,0,null,null,null,null,null);

-------Step 2: add electric hookup options info SC and NY contract
--select * from all_d_attr where attr_id=218; -- you can see contract specific attributes defined. This is the master list of all attributes. Again, attr_id=218 stands for electricity hookup.
--select * from d_attr_cnst where attr_id=218; -- these are all the possible values for the attributes. (e.g. 15amp, 30amp, Yes, Multiple).
insert into d_attr_cnst values(1,null,null,'No',218,null,'1','0');
insert into d_attr_cnst values(2,null,null,'None',218,null,'1','0');
insert into d_attr_cnst values(3,null,null,'Yes',218,null,'1','0');
insert into d_attr_cnst values(4,null,null,'Multiple',218,null,'1','0');

-------Step 3: attributes are assigned to product groups
--select * from p_prd_grp_attr where attr_id=218 and prd_grp_attr_id=41; --make sure acitve_ind=1 and deleted_ind=0--attributes are assigned to product groups if you want to use them (I chose to work with prd_grp_attr_id=41 which is for “Standard Site?i.e. prd_grp_id=5)
---select * from p_prd_grp_attr_cnst where prd_grp_attr_id=41; -- make sure active_ind=1 and deleted_ind=0 --attribute values are assigned here as well for prd_grp_attr_id=41
insert into p_prd_grp_attr_cnst (PRD_GRP_ATTR_CNST_ID, ATTR_CNST_ID, PRD_GRP_ATTR_ID,ACTIVE_IND, DELETED_IND)values(200000, 1, 41, '1', '0');
insert into p_prd_grp_attr_cnst (PRD_GRP_ATTR_CNST_ID, ATTR_CNST_ID, PRD_GRP_ATTR_ID,ACTIVE_IND, DELETED_IND)values(200001, 2, 41, '1', '0');
insert into p_prd_grp_attr_cnst (PRD_GRP_ATTR_CNST_ID, ATTR_CNST_ID, PRD_GRP_ATTR_ID,ACTIVE_IND, DELETED_IND)values(200002, 3, 41, '1', '0');
insert into p_prd_grp_attr_cnst (PRD_GRP_ATTR_CNST_ID, ATTR_CNST_ID, PRD_GRP_ATTR_ID,ACTIVE_IND, DELETED_IND)values(200003, 4, 41, '1', '0');


COMMIT;