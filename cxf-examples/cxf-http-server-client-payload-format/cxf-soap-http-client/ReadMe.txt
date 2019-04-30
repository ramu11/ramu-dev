Generate Certificate:
keytool -genkeypair -alias CertAlias -keypass CertPassword -keystore CertName.jks -storepass CertPassword -keyalg RSA -dname "CN=localhost, DC=my, DC=domain" -validity 9999 -v 


keytool -export -alias CertAlias -file server.crt -keystore CertName.jks

keytool -genkeypair -alias clientAlias -keypass CertPassword -keystore Client.jks -storepass CertPassword -keyalg RSA -dname "CN=localhost-client, DC=my, DC=domain" -validity 9999 -v 

keytool -import -trustcacerts -alias CertAlias -file server.crt -keystore Client.jks

keytool -export -alias clientAlias -file client.crt -keystore Client.jks

keytool -import -trustcacerts -alias clientAlias -file client.crt -keystore CertName.jks
