DROP TABLE IF EXISTS temp;

CREATE EXTERNAL TABLE IF NOT EXISTS temp (
name string,
thumbnail string,
information string,
views int
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
STORED AS TEXTFILE
LOCATION '/user/hive/recipe'
tblproperties ("skip.header.line.count"="1");
