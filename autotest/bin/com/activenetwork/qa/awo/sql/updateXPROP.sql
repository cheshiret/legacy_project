--this sql is a collection of which need to update some values in X_PROP table.


--update phone number format as 4008111111 from (400)8111111
alter session set current_schema=live_MS;
update X_PROP set VALUE= 'false' where NAME = 'NeedPhoneNumberFormat';

--configure site ADA attributes
ALTER SESSION SET CURRENT_SCHEMA=live_NRRS;
declare
        cnt             VARCHAR(30);
    BEGIN
       select COUNT(*) into cnt from x_prop where name like 'ormsADAOrdering';
       IF cnt=0 THEN
          insert into X_PROP values(get_Sequence('X_PROP'), 'ormsADAOrdering','Contract', 'String', 'yes');
        END IF;
    END;
/
COMMIT;


--Turn On the "Find Active Customer Profile Using Merged Customer Number During Sale" Feature for SK contract
ALTER SESSION SET CURRENT_SCHEMA=live_SK;

DECLARE
	numofRecords NUMBER(38);

BEGIN
	SELECT count(*) into numofRecords FROM X_PROP WHERE NAME='FindActiveCustomerProfileUsingMergedCustomerNumberDuringSale';
	IF numofRecords = 0 THEN
		INSERT INTO X_PROP ( id, name, namespace, type, value ) 
			VALUES ( CONTRACT_SEQ.NEXTVAL, 'FindActiveCustomerProfileUsingMergedCustomerNumberDuringSale', 'Contract', 'Boolean', 'true' );
	ELSE
		SELECT count(*) into numofRecords FROM X_PROP WHERE NAME='FindActiveCustomerProfileUsingMergedCustomerNumberDuringSale' and VALUE='true';
		IF numofRecords = 0 THEN
			UPDATE X_PROP set value='true' where name='FindActiveCustomerProfileUsingMergedCustomerNumberDuringSale';  
		END IF;

	END IF;  
	COMMIT;
END;

--Setup fulfillment method on Web for SK contract, by Lesley
ALTER SESSION SET CURRENT_SCHEMA=live_SK;

DECLARE
	numofRecords NUMBER(38);

BEGIN
	---Fulfillment method setup
	SELECT count(*) into numofRecords FROM X_PROP WHERE NAME='ApplicableInventoryFulfillmentMethods' AND NAMESPACE='PublicWeb';
	IF numofRecords = 0 THEN
		INSERT INTO X_PROP ( id, name, namespace, type, value ) 
			VALUES ( CONTRACT_SEQ.NEXTVAL, 'ApplicableInventoryFulfillmentMethods', 'PublicWeb', 'String', '1,2' );
	ELSE
		SELECT count(*) into numofRecords FROM X_PROP WHERE NAME='ApplicableInventoryFulfillmentMethods' AND NAMESPACE='PublicWeb' and VALUE='1,2';
		IF numofRecords = 0 THEN
			UPDATE X_PROP set value='1,2' where name='ApplicableInventoryFulfillmentMethods' AND NAMESPACE='PublicWeb';  
    END IF;
  end if;

	---Allow Reprint privilege documents fulfilled by mail
	SELECT count(*) into numofRecords FROM X_PROP WHERE NAME='AllowReprintPrivilegeDocsFulfilledByMail' AND NAMESPACE='Contract';
	IF numofRecords = 0 THEN
		INSERT INTO X_PROP ( id, name, namespace, type, value ) 
			VALUES ( CONTRACT_SEQ.NEXTVAL, 'AllowReprintPrivilegeDocsFulfilledByMail', 'Contract', 'Boolean', 'true' );
	ELSE
		SELECT count(*) into numofRecords FROM X_PROP WHERE NAME='AllowReprintPrivilegeDocsFulfilledByMail' AND NAMESPACE='Contract' and VALUE='true';
		IF numofRecords = 0 THEN
			UPDATE X_PROP set value='true' where name='AllowReprintPrivilegeDocsFulfilledByMail' AND NAMESPACE='Contract';  
		END IF;
	END IF;  

	---Allow Print Orig privilege documents fulfilled by mail
	SELECT count(*) into numofRecords FROM X_PROP WHERE NAME='AllowPrintOrigPrivilegeDocsFulfilledByMail' AND NAMESPACE='Contract';
	IF numofRecords = 0 THEN
		INSERT INTO X_PROP ( id, name, namespace, type, value ) 
			VALUES ( CONTRACT_SEQ.NEXTVAL, 'AllowPrintOrigPrivilegeDocsFulfilledByMail', 'Contract', 'Boolean', 'true' );
	ELSE
		SELECT count(*) into numofRecords FROM X_PROP WHERE NAME='AllowPrintOrigPrivilegeDocsFulfilledByMail' AND NAMESPACE='Contract' and VALUE='true';
		IF numofRecords = 0 THEN
			UPDATE X_PROP set value='true' where name='AllowPrintOrigPrivilegeDocsFulfilledByMail' AND NAMESPACE='Contract';  
		END IF;
	END IF;  

	---Allow Print privilege inventory where no inventory assigned
	SELECT count(*) into numofRecords FROM X_PROP WHERE NAME='PrintPrivilegesRequiringInventoryWhereNoInventoryAssigned' AND NAMESPACE='Contract';
	IF numofRecords = 0 THEN
		INSERT INTO X_PROP ( id, name, namespace, type, value ) 
			VALUES ( CONTRACT_SEQ.NEXTVAL, 'PrintPrivilegesRequiringInventoryWhereNoInventoryAssigned', 'Contract', 'Boolean', 'true' );
	ELSE
		SELECT count(*) into numofRecords FROM X_PROP WHERE NAME='PrintPrivilegesRequiringInventoryWhereNoInventoryAssigned' AND NAMESPACE='Contract' and VALUE='true';
		IF numofRecords = 0 THEN
			UPDATE X_PROP set value='true' where name='PrintPrivilegesRequiringInventoryWhereNoInventoryAssigned' AND NAMESPACE='Contract';  
		END IF;
	END IF;  

	---Setup Inventory Fulfillment store - Ministry of Environment - Big River
	SELECT count(*) into numofRecords FROM X_PROP WHERE NAME='InventoryFulfillmentStore' AND NAMESPACE='Contract' AND value=90506;
	IF numofRecords = 0 THEN
		INSERT INTO X_PROP ( id, name, namespace, type, value ) 
			VALUES ( CONTRACT_SEQ.NEXTVAL, 'InventoryFulfillmentStore', 'Contract', 'Number', 90506 );
	END IF;  
 end;
 /
COMMIT;