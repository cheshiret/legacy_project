alter session set current_schema=live_NC;
--17 for marina manager
insert into X_PROP(ID,NAME,NAMESPACE,TYPE,VALUE) VALUES (GET_SEQUENCE('X_PROP'), '17_CustomerSearchByAddress','CustomerSearchUI','Boolean','true' );
insert into X_PROP(ID,NAME,NAMESPACE,TYPE,VALUE) VALUES (GET_SEQUENCE('X_PROP'), '17_CustomerSearchByVehicle','CustomerSearchUI','Boolean','true' );
insert into x_prop(id,name,namespace,type,value) values (get_sequence('X_PROP'), '17_CustomerSearchByCampingUnit','CustomerSearchUI','Boolean','true' );
insert into X_PROP(ID,NAME,NAMESPACE,TYPE,VALUE) VALUES (GET_SEQUENCE('X_PROP'), '17_CustomerSearchByPet','CustomerSearchUI','Boolean','true' );
--6 for field manager
insert into X_PROP(ID,NAME,NAMESPACE,TYPE,VALUE) VALUES (GET_SEQUENCE('X_PROP'), '6_CustomerSearchByAddress','CustomerSearchUI','Boolean','true' );
insert into X_PROP(ID,NAME,NAMESPACE,TYPE,VALUE) VALUES (GET_SEQUENCE('X_PROP'), '6_CustomerSearchByVehicle','CustomerSearchUI','Boolean','true' );
insert into x_prop(id,name,namespace,type,value) values (get_sequence('X_PROP'), '6_CustomerSearchByCampingUnit','CustomerSearchUI','Boolean','true' );
insert into X_PROP(ID,NAME,NAMESPACE,TYPE,VALUE) VALUES (GET_SEQUENCE('X_PROP'), '6_CustomerSearchByPet','CustomerSearchUI','Boolean','true' );

Update P_EQUIP_INFO_CNFG set MAKE_IND = 1 where equip_id = 7 ;
Update P_EQUIP_INFO_CNFG set MODEL_IND = 1 where equip_id = 7 ;
Update P_EQUIP_INFO_CNFG set STATE_IND = 1 where equip_id = 7 ;
Update P_EQUIP_INFO_CNFG set COLOR_IND = 1 where equip_id = 7 ;
commit;