"# Generic_SQL_Logger" 

*****************

This Java application can be used to run Oracle scripts iteratively on a machine without installing the Oracle client.
It uses the standard JDBC library to connect to an Oracle DB.

*****************

To configure your SQL statement and DB connection in the following property file /resources/config.properties:

db="Your Oracle DB connection string."
sqlstart="Start of your SQL statement." 
values="Middle portion of your SQL statement." 
sqlend="End of your SQL statement." 

Sample config for preset values:
#SDR Logging Properties
#Windows connection sample below.
db=sdruser/sdruser1@//112.20.60.154:1521/pcspdb
sqlstart=INSERT INTO  EIP_DW_DELIVERY_FILE  VALUES(
values='MERI','DONE',to_timestamp('24-04-2017 14:45:36','DD-MM-YYYY HH24:MI:SS'),'EIP'
sqlend=)  

### Without parameters run it from the command line as: java SDRLogger.jar ### 

***************

Sample config for parameterized values:
#SDR Logging Properties
#Linux connection sample below.
db=test/test@172.29.65.161:1521:mudr
sqlstart=INSERT INTO EIP_DW_DELIVERY_FILE VALUES(
values=
sqlend=)

### Without parameters run it from the command line as: java SDRLogger.jar "'MERI','DONE',to_timestamp('24-04-2017 14:45:36','DD-MM-YYYY HH24:MI:SS'),'EIP'"  ## 

*******************************************

Logging can be configured in /resources/log4j.properties.
