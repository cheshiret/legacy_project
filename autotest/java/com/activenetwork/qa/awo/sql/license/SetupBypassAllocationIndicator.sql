Alter Session Set Current_Schema=LIVE_AB;
    declare
        Cnt             Varchar(30);
        indicator_value           varchar(30);
    Begin
       SELECT COUNT(*),value into cnt,indicator_value FROM X_Prop Where NAME = 'BypassAllocationInd' and NAMESPACE = 'Contract' GROUP BY value;
       IF cnt=0 THEN
           --If Bypass allocation indicator is not configured,configure the indicator
          Insert Into X_Prop(Id, Name,Namespace,Type,Value) Values ( Contract_Seq.Nextval, 'BypassAllocationInd','Contract','Boolean','True' );
          commit;
       Else 
          If Indicator_Value='False' Then 
            Update X_Prop Set Value='True' Where Name = 'BypassAllocationInd' And Namespace = 'Contract';
            commit;
          End If;
       End If;
    End;
    
