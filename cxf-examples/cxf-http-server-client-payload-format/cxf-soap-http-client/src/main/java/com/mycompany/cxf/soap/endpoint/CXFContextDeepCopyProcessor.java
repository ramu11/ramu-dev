/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cxf.soap.endpoint;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.util.ExchangeHelper;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.helpers.CastUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author dhawkins
 */
public class CXFContextDeepCopyProcessor implements Processor {

    private final Logger LOG = Logger.getLogger(CXFContextDeepCopyProcessor.class);

    @Override
    public void process(Exchange exchng) throws Exception {

        Map<String, Object> requestContext = CastUtils.cast((Map<?, ?>) exchng.getProperty(Client.REQUEST_CONTEXT));

        Map<String, Object> tSrequestContext = deepCopy(requestContext);

        exchng.setProperty(Client.REQUEST_CONTEXT, tSrequestContext);

        LOG.info("Identity Hash: " + System.identityHashCode(exchng.getProperty(Client.REQUEST_CONTEXT)));
    }

    private Map<String, Object> deepCopy(Map<String, Object> original) {

        LOG.info("Copying map into concurrent map");
        if (original == null) {
            return new ConcurrentHashMap<String, Object>();
        }

        synchronized (original) {
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
                LOG.info("can't do deepCopy because of {}", ex);
                ex.printStackTrace();
            }
        }
        return original;
    }

}
