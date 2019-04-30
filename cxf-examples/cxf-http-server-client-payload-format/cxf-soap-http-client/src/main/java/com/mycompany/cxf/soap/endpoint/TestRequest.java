package com.mycompany.cxf.soap.endpoint;

import com.mycompany.cxf.soap.model.GetPerson;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.CxfPayload;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.message.MessageContentsList;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TestRequest {

    private final Logger logger = Logger.getLogger(this.getClass());

    public CxfPayload personRequest() throws ParserConfigurationException {
        
        String methodName = "getPerson";

        logger.info("Invoking: " + methodName);
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = dbf.newDocumentBuilder();
	Document doc = builder.newDocument();
        Element method = doc.createElementNS("http://model.soap.cxf.mycompany.com/", "mcc:" + methodName);
        doc.appendChild(method);
        Element argument1 = doc.createElement("id");
        method.appendChild(argument1);
        argument1.setTextContent("2");
                
        List<Source> outElements = new ArrayList<>();
        outElements.add(new DOMSource(doc.getDocumentElement()));
        // set the payload header with null
        CxfPayload<SoapHeader> proxyPayload = new CxfPayload<>(null, outElements, null);
        
         return proxyPayload;
    }

}
