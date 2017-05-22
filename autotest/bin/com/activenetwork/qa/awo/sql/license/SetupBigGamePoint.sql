--this sql is setup of Big Game Point
--Please refer to http://wiki.reserveamerica.com/display/dev/Big+Game+Supplementary+Setup for more detail info.
--This configuration is used by : testCases.regression.basic.orms.license.customer.merge.MergeCustomer_Points

alter session set current_schema=live_MS;

DECLARE 
  POINT_TYPE_CD  VARCHAR(10) :='tst';
  POINT_TYPE_NAME  VARCHAR(10) :='test';
  POINT_TYPE_DESCR  VARCHAR(100) :='This is a test point type';
  CNT NUMBER;
  POINT_TYPE_ID NUMBER;
  ADD_POINT_REASON_CD VARCHAR(10):='1';
  ADD_POINT_REASON_DESCR varchar(100):='Custom Adding Point Reason';  
  
  
  BEGIN

     SELECT COUNT(*) INTO CNT FROM  D_POINT_TYPE WHERE CODE = POINT_TYPE_CD AND DESCR = POINT_TYPE_DESCR;
     IF CNT=0    THEN
         --create point type
        INSERT INTO D_POINT_TYPE (ID, NAME, DESCR, STATUS_ID, CODE, CREATE_USER_ID, CREATE_LOC_ID, CREATE_DATETIME) 
        VALUES (GET_SEQUENCE('D_POINT_TYPE'), POINT_TYPE_NAME , POINT_TYPE_DESCR, 1, POINT_TYPE_CD, '70001', '1', sysdate);
         --create point type config
        SELECT MAX(GET_SEQUENCE('D_POINT_TYPE')) INTO POINT_TYPE_ID FROM D_POINT_TYPE;
        Insert Into D_Point_Type_Cnfg (Id, Point_Type_Id, Status_Id, Effective_Date, Config_Type_Id, Create_User_Id, Create_Loc_Id, Create_Datetime, Max_Allowed_Points, Ltpt_Award_Lmt_Id, Ltpt_Batch_Rst_Criteria_Id, Ltpt_Batch_Rst_Criteria_Years, Redeemable_Ind) 
        VALUES (GET_SEQUENCE('D_POINT_TYPE_CNFG'), POINT_TYPE_ID, 1, SYSDATE, 1, 70001, 1, SYSDATE, 20, 2, NULL, 1, 0);
     END IF;
            
      --create Reason for Adding Points
      SELECT COUNT(*) INTO CNT FROM  D_POINT_ALLOC_REASON WHERE CODE = ADD_POINT_REASON_CD AND DESCRIPTION = ADD_POINT_REASON_DESCR;
      IF CNT=0    THEN
      --alloc_type_id=1 means for adding
      INSERT INTO D_POINT_ALLOC_REASON(ID,ALLOC_TYPE_ID,CODE,DESCRIPTION) VALUES (GET_SEQUENCE('D_POINT_ALLOC_REASON'),1,ADD_POINT_REASON_CD,ADD_POINT_REASON_DESCR);
      END IF;
            
  END;
 /
 COMMIT;