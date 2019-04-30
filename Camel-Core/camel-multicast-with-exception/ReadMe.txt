Camel Router Project for Blueprint (OSGi)
=========================================

To build this project use

    mvn install

To run the project you can execute the following Maven goal

    mvn camel:run

Notes:

1)So I think what you can do is to make the multicast EIP the 1st EIP in a route that starts from a "fresh" endpoint such as direct-vm (not direct).
So if you do
 <route id="iuDataInProcessorRoute">
			<from uri="direct-vm:iuDataInProcessor"/> 
             <log message="Headers before multicast : ${headers}" /> 
              <setHeader headerName="TEST1"><constant>TEST HEADER !!!!!!!!!!!!</constant></setHeader>
			    <setHeader headerName="TEST2"><constant>TEST HEADER 2 !!!!!!!!!!!!</constant></setHeader>
             <to uri="direct-vm:multicast"/>
         </route>
 
 <route id="iuMulticastRoute">
         <from uri="direct-vm:multicast"/>
            <multicast parallelProcessing="true" shareUnitOfWork="true"  stopOnException="true" strategyRef="simpleStrategy" >
				<to uri="direct-vm:payLoadActionPersistorSyncServiceResponse" />
				<to uri="direct-vm:setIUMetaDataForDataFact" />
			 </multicast>
			<log message="Headers after multicast : ${headers}" /> 			 
			<to uri="direct-vm:testHeadersOnException" />
         </route>
Then that likely works now, eg we splitup the route into 2 routes and linked via direct-vm endpoint

2)Okay so CAMEL-9444 and CAMEL-9573 are related and part of the same bigger work. So the EIPs splitter, aggregator, multicast, recipient list etc were beforehand working a bit differently in their unit of work logic. These CAMEL tickets are issues where we are aligning them to be more consistent in their code-logic.
So the customer issue is that they are using
share unit of work
onException with useOriginalMessage
So when you now have share unit of work, its essentially using the parent unit of work, eg from the original incoming route (they 1st route), and since these headers are added afterwards during routing (via the setHeaders in the routes), then the incoming message does not contain them.

3)I would say that the old behavior in 6.2.1 was "wrong" as the share-unit-of-work and using use-original-message combination with the Multicast EIP was flawed. With the CAMEL-9444 and CAMEL-9573 we have aligned the code logic of the EIPs.
Beforehand you would end up with mixed-data as the original message was polluted with changed data during routing, which was not its intention.

4)One caveat with the splitup of multicast into 2 routes and linked with direct-vm, is that the original message because the original message at the point just before the multicast, so if the message body is changed then its that body you will get.
Mind that most components such as jms, direct-vm, vm, etc create a new exchange, and only direct and a few others do not. And that matters what is regarded as the original message.

