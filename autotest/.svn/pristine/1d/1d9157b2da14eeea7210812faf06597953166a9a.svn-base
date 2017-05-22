ALTER SESSION SET CURRENT_SCHEMA=LIVE_RA_RIDB;


update recarea set recarealatitude=null,recarealongitude=null where recareaid=2818; --set rec area with no GPS info
update recarea set enabled=0 where recareaid=2576;
update facility set enabled=0 where facilityid= '238539';
update facility set enabled=0 where facilityid= '247988';
update recarea set enabled=0 where recareaid='3112';
update recarea set enabled=0 where recareaid='93';
COMMIT; 