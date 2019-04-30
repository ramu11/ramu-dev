CamelContext:
###############################################################################################################################
<camelContext id="midc-log" xmlns="http://camel.apache.org/schema/blueprint" useMDCLogging="true">
    <route id="timerToLog">
      <from uri="timer:foo?period=5000"/>
      <setBody>
          <method ref="helloBean" method="hello"/>
      </setBody>
      <log message="The message contains ${body}" loggingLevel="DEBUG" logName="com.test.sample"/>
      <to uri="mock:result"/>
    </route>
  </camelContext>

Changes in org.ops4j.pax.logging.cfg.(Just replace sift appender conf with below)

# Sift appender
log4j.logger.com.test.sample=DEBUG,test

log4j.appender.test=org.apache.log4j.sift.MDCSiftingAppender
log4j.appender.test.key=camel.contextId
log4j.appender.test.default=unknown
log4j.appender.test.appender=org.apache.log4j.RollingFileAppender
log4j.appender.test.appender.layout=org.apache.log4j.PatternLayout
log4j.appender.test.appender.layout.ConversionPattern=%d{ABSOLUTE} | %-5.5p | %X{camel.routeId}-%X{bundle.version} | %X{camel.exchangeId} | %m%n
log4j.appender.test.appender.file=${karaf.data}/log/$\\{camel.contextId\\}.log
log4j.appender.test.appender.append=true
log4j.appender.test.appender.maxFileSize=1MB
log4j.appender.test.appender.maxBackupIndex=10
###############################################################################################################################
The above conf will create a log file with name midc-log.log (camel-contextId)
