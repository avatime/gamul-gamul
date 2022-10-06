DROP TABLE IF EXISTS sep_meat;

CREATE EXTERNAL TABLE IF NOT EXISTS sep_meat (
category string,
date_time date,
price int,
quantity double,
unit string,
store string,
high int,
type int,
midclass string
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
STORED AS TEXTFILE
LOCATION '/user/hive/sep_meat'
tblproperties ("skip.header.line.count"="1");
