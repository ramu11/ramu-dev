/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cxf.soap.endpoint;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.helpers.CastUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author dhawkins
 */
public class CXFContextLoggingProcessor implements Processor {
    
        private final Logger LOG = Logger.getLogger(CXFContextLoggingProcessor.class);


    @Override
    public void process(Exchange exchng) throws Exception {
        LOG.info("Identity Hash: " + System.identityHashCode(exchng.getProperty(Client.REQUEST_CONTEXT)));
        LOG.info("Obj: " +exchng.getProperty(Client.REQUEST_CONTEXT).toString());
        Map<String, Object> requestContext = CastUtils.cast((Map<?, ?>) exchng.getProperty(Client.REQUEST_CONTEXT));
        LOG.info("the St1nky is " + requestContext.get("St1nky"));
    }
    
}
