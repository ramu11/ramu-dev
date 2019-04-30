features:install jndi
features:install hibernate
osgi:install -s wrap:file:/path/to/hsqldb-2.3.3/hsqldb/lib/hsqldb.jar
osgi:install -s mvn:com.mycompany.esb.test/jpa-reproducer/1.0.0-SNAPSHOT