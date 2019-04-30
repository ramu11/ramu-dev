

    
    features:install jpa
    features:install camel-jpa
    features:install hibernate
    features:install hibernate-envers
    features:install hibernate-validator
    features:install jndi
    features:install transaction
    features:install connector
    
     
    osgi:install -s mvn:mysql/mysql-connector-java/5.1.35
    
    create jpatest database using below command:
    
    create database jpatest;
       
    
    osgi:install -s mvn:com.test/DataSource/1.0
    osgi:install -s mvn:com.test/Hibernate-JPA/0.0.1
    osgi:install -s mvn:com.mycompany/file-jpa-idempotent/1.0
    
    
    
