/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cxf.soap.endpoint;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.helpers.CastUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author dhawkins
 */
public class CXFContextModProcessor implements Processor {
    
    private final Logger LOG = Logger.getLogger(CXFContextModProcessor.class);


    @Override
    public void process(Exchange exchng) throws Exception {
        
        System.identityHashCode(exchng);
        Map<String, Object> requestContext = CastUtils.cast((Map<?, ?>) exchng.getProperty(Client.REQUEST_CONTEXT));
        
        Map<String, Object> tSrequestContext = deepCopy(requestContext);
        
        tSrequestContext.put("St1nky", "Cheese");
        
        exchng.setProperty(Client.REQUEST_CONTEXT, tSrequestContext);
       // requestContext.put("St1nky", "Cheese");
        
    }

    private Map<String, Object> deepCopy(Map<String, Object> original) {
        
        LOG.info("Copying map into concurrent map");
        if (original == null) {
            return new ConcurrentHashMap<String, Object>();
        }
        try {
            Map<String, Object> ret = new ConcurrentHashMap();
            for (Map.Entry<String, Object> entry : original.entrySet()) {
                if ((entry.getValue() instanceof Map)) {
                    ret.put(entry.getKey(), deepCopy((Map) entry.getValue()));
                } else {
                        if (entry.getValue() != null) {
                            ret.put(entry.getKey(), entry.getValue());
                        }
                    }
                }
            return ret;
        } catch (Exception ex) {
            LOG.trace("can't do deepCopy because of {}", ex);
        }
        return original;
    }

}
