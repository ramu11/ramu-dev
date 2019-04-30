package com.mycompany.cxf.soap.endpoint;

import java.util.HashMap;
import java.util.Map;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.endpoint.Client;
import org.apache.log4j.Logger;

public class CXFContextInitProcessor implements Processor {

    private final Logger LOG = Logger.getLogger(CXFContextInitProcessor.class);


    public void process(Exchange exchng) throws Exception {

        Map<String, Object> requestContext = new HashMap<String, Object>();

        exchng.setProperty(Client.REQUEST_CONTEXT, requestContext);

        LOG.info("Identity Hash of Request Context: "+
                    System.identityHashCode(exchng.getProperty(Client.REQUEST_CONTEXT)));
    }


}
