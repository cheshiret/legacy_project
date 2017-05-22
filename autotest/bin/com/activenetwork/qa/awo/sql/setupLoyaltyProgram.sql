ALTER SESSION SET CURRENT_SCHEMA=LIVE_SC;

BEGIN

insert into X_Prop (Id, Name, Namespace, Type, Value) values (CONTRACT_SEQ.nextval, 'LoyaltyEnabled', 'Contract', 'Boolean', 'true'); 

insert into d_point_alloc_reason values('1','1','1','Custom Adding Point Reason'); 
Insert Into D_Point_Alloc_Reason( Id, Alloc_Type_Id, Code, Description) Values( Contract_Seq.Nextval, 1, 'BON', 'Bonus'); 

END;
/
COMMIT;