

Generate Certificate:
keytool -genkeypair -alias CertAlias -keypass CertPassword -keystore CertName.jks -storepass CertPassword -keyalg RSA -dname "CN=localhost, DC=my, DC=domain" -validity 9999 -v 

//Show the content of server cert
keytool -list -keystore CertName.jks -v

//Export certificate from keystore
keytool -export -alias CertAlias -file server.crt -keystore CertName.jks

//import certificate in a new keystore
keytool -import -trustcacerts -alias CertAlias -file server.crt -keystore Client.jks
