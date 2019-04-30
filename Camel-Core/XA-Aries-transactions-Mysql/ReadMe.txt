This example works in Fuse 6.2.0 with Mysql database



1)Run below statements to create database

Mysql1>DROP SCHEMA IF EXISTS transactions CASCADE;

CREATE SCHEMA transactions;

CREATE TABLE transactions.FLIGHTS (
  number    VARCHAR(12) NOT NULL,
  departure VARCHAR(3),
  arrival   VARCHAR(3)
);

Mysql2>DROP SCHEMA IF EXISTS transactions2 CASCADE;

CREATE SCHEMA transactions2;

CREATE TABLE transactions2.PLANES (
  number    VARCHAR(12) NOT NULL,
  departure VARCHAR(3),
  arrival   VARCHAR(3)
);

2) Add camel-sql and jndi features to karaf

features:install camel-sql
features:install jndi
osgi:install -s wrap:mvn:mysql/mysql-connector-java/5.1.37

3) Install datasource,route bundles
  osgi:install -s mvn:com.mysql1/mysql1/1.0
  osgi:install -s mvn:com.mysql2/mysql2/1.0
  osgi:install -s mvn:com.route/route1/1.0
