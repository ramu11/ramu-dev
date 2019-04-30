package org.jboss.fuse.samples.processor;

import org.apache.camel.component.cxf.DataFormat;
import org.apache.camel.component.cxf.CxfConstants;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.hello_world_soap_http.types.GreetMe;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.Name;
import javax.xml.namespace.QName;

import com.sun.xml.messaging.saaj.soap.ver1_1.Message1_1Impl;

public class MyRequestProcessor implements Processor  {
    final static Logger logger = LoggerFactory.getLogger(MyRequestProcessor.class);

    private String dataFormat;
	
    public void process(Exchange exchange) throws Exception {
        //DataFormat dataFormat = (DataFormat)exchange.getProperty(CxfConstants.DATA_FORMAT_PROPERTY);
        switch (dataFormat) {
        case "PAYLOAD":
            logger.info("DataFormat: PAYLOAD is used!");
            GreetMe greet = new GreetMe();
            greet.setRequestType("claus");
            exchange.getIn().setBody(greet);
            break;
        case "POJO":
            logger.info("DataFormat: POJO is used!");
            Object[] req = new Object[]{"claus"};
            exchange.getIn().setBody(req);
            break;
        case "CXF_MESSAGE":
            logger.info("DataFormat: CXF_MESSAGE is used!");
            exchange.getIn().setBody(new Message1_1Impl(requestForCxfMessage()));
            break;
        default:
            logger.info("Unknown DataFormat: " + dataFormat + " is ignore!");
             break;
        }

        logger.info("Exchange: \n" + exchange);
    }	 

    
    private SOAPMessage requestForCxfMessage() {
        SOAPMessage message = null;
        try {
            MessageFactory myMsgFct = MessageFactory.newInstance();
            message = myMsgFct.createMessage();
            SOAPPart soapPart = message.getSOAPPart();
            SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
            SOAPBody body = soapEnvelope.getBody();
            Name bodyName = soapEnvelope.createName("greetMe", "ns2",
                                       "http://apache.org/hello_world_soap_http/types");

            SOAPBodyElement gltp = body.addBodyElement(bodyName);
            Name myContent = soapEnvelope.createName("requestType", "ns2",
                                       "http://apache.org/hello_world_soap_http/types");

            SOAPElement mySymbol = gltp.addChildElement(myContent);
            mySymbol.addTextNode("Joe");
        } catch (SOAPException soapEx) {
            System.out.println("caught a SOAPException");
        }
        return message;
    }
    
    
    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

}
