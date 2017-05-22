
select * from corporation;

--count of corporations based on status. 
--In AWO these will be vendors. 
select 
count(cp_id), cp_status_ind 
from corporation
group by cp_status_ind 


--not sure will these be included in same list as vendor
select * from vendor

select *
from agent
