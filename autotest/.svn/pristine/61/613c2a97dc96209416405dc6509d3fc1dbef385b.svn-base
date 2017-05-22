--SYSTEM 13 B config.
ALTER SESSION SET CURRENT_SCHEMA=live_mo;
select * from (
select app_id,feat_name,time_restriction,
case app_id 
  when 19 then (
                case feat_name 
                  when 'VoidPrivilegeNonRestrictive' then (
                                      case time_restriction when null then 'True' else 'False' end)
                  when 'VoidPrivilegeRestrictive' then (
                                      case time_restriction when 24 then 'True' else 'False' end)
                  when 'VoidPrivilegeMoreRestrictive' then (
                                      case time_restriction when 12 then 'True' else 'False' end)
                  when 'VoidPrivilegeMostRestrictive' then (
                                      case time_restriction when 1 then 'True' else 'False' end)
                end
                )
  when 22 then (
                case feat_name 
                  when 'VoidPrivilegeNonRestrictive' then (
                                      case time_restriction when null then 'True' else 'False' end)
                  when 'VoidPrivilegeRestrictive' then (
                                      case time_restriction when 24 then 'True' else 'False' end)
                  when 'VoidPrivilegeMoreRestrictive' then (
                                      case time_restriction when 12 then 'True' else 'False' end)
                  when 'VoidPrivilegeMostRestrictive' then (
                                      case time_restriction when 1 then 'True' else 'False' end)                   
                end
                )
end as time_restriction1                      
from D_PERMISSION_APP_TIMING )
      where time_restriction1 in ('True','False')