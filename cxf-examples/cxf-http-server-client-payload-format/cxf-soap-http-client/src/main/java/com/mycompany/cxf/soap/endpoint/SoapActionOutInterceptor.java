package com.mycompany.cxf.soap.endpoint;

import com.mycompany.cxf.soap.model.Caller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.Soap11;
import org.apache.cxf.binding.soap.Soap12;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.model.SoapOperationInfo;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.OperationInfo;

public class SoapActionOutInterceptor extends AbstractSoapInterceptor {

    public SoapActionOutInterceptor() {
        super(Phase.PRE_PROTOCOL);

    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        if (message.getVersion() instanceof Soap11) {
            Map<String, List<String>> headers = CastUtils.cast((Map) message.get(Message.PROTOCOL_HEADERS));
            if (headers != null) {
                List<String> sa = headers.get("SOAPAction");
                if (sa != null && sa.size() > 0) {
                    String action = sa.get(0);
                    if (action.startsWith("\"")) {
                        action = action.substring(1, action.length() - 1);
                    }
                    getAndSetOperation(message, action);
                }
            }
        } else if (message.getVersion() instanceof Soap12) {

        }
    }

    private void getAndSetOperation(SoapMessage message, String action) {
        
        try {
            List<Header> headers = message.getHeaders();

            if (headers == null) {
                headers = new ArrayList<>();
            }

            Caller testHeader = new Caller();
            testHeader.setFirstname("Joe");
            testHeader.setLastname("Luo");

            QName headerQname = new QName("http://model.soap.cxf.mycompany.com/", "testHeader");
            SoapHeader newHeader;

            newHeader = new SoapHeader(headerQname, testHeader, new JAXBDataBinding(Caller.class));

            // make sure direction is IN since it is a request message.
            newHeader.setDirection(Header.Direction.DIRECTION_INOUT);
            headers.add(newHeader);
        } catch (JAXBException ex1) {
            Logger.getLogger(SoapActionOutInterceptor.class.getName()).log(Level.SEVERE, null, ex1);
        }
        
        if ("".equals(action)) {
            return;
        }

        Exchange ex = message.getExchange();
        Endpoint ep = ex.get(Endpoint.class);

        BindingOperationInfo bindingOp = null;

        Collection<BindingOperationInfo> bops = ep.getBinding().getBindingInfo().getOperations();
        for (BindingOperationInfo boi : bops) {
            SoapOperationInfo soi = (SoapOperationInfo) boi.getExtensor(SoapOperationInfo.class);
            if (soi != null && soi.getAction().equals(action)) {
                if (bindingOp != null) {
                    //more than one op with the same action, will need to parse normally
                    return;
                }
                bindingOp = boi;
            }
        }
        if (bindingOp != null) {
            ex.put(BindingOperationInfo.class, bindingOp);
            ex.put(OperationInfo.class, bindingOp.getOperationInfo());
        }
        
    }

}
