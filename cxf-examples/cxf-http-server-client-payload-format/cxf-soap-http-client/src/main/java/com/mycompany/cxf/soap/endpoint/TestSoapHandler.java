/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cxf.soap.endpoint;

import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author dhawkins
 */
public class TestSoapHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * javax.xml.ws.handler.Handler#handleFault(javax.xml.ws.handler.MessageContext
     * )
     */
    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * javax.xml.ws.handler.Handler#close(javax.xml.ws.handler.MessageContext)
     */
    @Override
    public void close(MessageContext context) {
        // Empty
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.ws.handler.soap.SOAPHandler#getHeaders()
     */
    @Override
    public Set<QName> getHeaders() {
        return null;
    }
}
