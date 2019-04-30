//create database kafka_test;
use kafka_test;
create table order_data(okey varchar(50), ovalue varchar(50));
create table order_offsets(topic_name varchar(50), partitionNo int, offset int);
insert into order_offsets values('OrderTopic',0,0);
insert into order_offsets values('OrderTopic',1,0);
insert into order_offsets values('OrderTopic',2,0);
