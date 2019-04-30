
Setting Security Context for the Openwire/SSL Protocol in active Mq fabric
Solution Unverified - Updated Tuesday at 10:39 PM - English
Environment

    Red Hat JBoss A-MQ
        6.2.1

Issue

    Configure A-MQ SSL into fabric
    list of steps to configure SSL A-MQ in Fuse 6.2.1 / fabric8 and signed certificates creation and setup in a fabric environment
    Setting security context in the broker configuration file in fabric environment

Resolution

    Create a custom profile by Inheriting mq-amq profile



fabric:profile-create --parents mq-amq my-mq-amq

    Create broker.xmland have ssl configuration ,below fabric:profile-edit command opens broker.xml in edit mode.sample broker.xmlis attached



fabric:profile-edit --resource broker.xml my-mq-amq 1.0
<sslContext>
            <sslContext keyStore="${keyStore}"
                        keyStorePassword="storepassword"
                        trustStore="${trustStore}"
                        trustStorePassword="storepassword"/>
        </sslContext> 

    Create io.fabric8.mq.fabric.server-broker.properties file and have keystores and truststores added
    keyStore and trustStore jks files must be copied to profile my-mq-amq



fabric:profile-edit --resource io.fabric8.mq.fabric.server-broker.properties my-mq-amq
keyStore=profile\:server-keystore.jks
trustStore=profile\:server-truststore.jks

    Please refer [1][2] for how to add resources to a fabric profile using git
    1.https://access.redhat.com/solutions/1202743
    2.https://access.redhat.com/solutions/886153
    Sample active Mq client bundle is attached


