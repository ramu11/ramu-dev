package com.mycompany.cxf.soap.endpoint;

import java.util.List;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SoapProcessor {

    public void processHeaders(@Header("org.apache.cxf.headers.Header.list") List<SoapHeader> soapHeaders, Exchange exchange) {
        for (SoapHeader header : soapHeaders) {

            NodeList childNodes = ((Element) header.getObject()).getChildNodes();
            System.out.println("Length ::" + childNodes.getLength());
            Node node = null;
            for (int nodeCount = 0; childNodes.getLength() > nodeCount; nodeCount++) {
                node = childNodes.item(nodeCount);
                NodeList soapHeaderList = node.getChildNodes();
                for (int count = 0; count < soapHeaderList.getLength(); count++) {
                    Node soapHeadernode = soapHeaderList.item(count);

                    System.out.println(node.getLocalName() + "  "
                            + soapHeadernode.getNodeValue());
                    exchange.getIn().setHeader(node.getLocalName(),
                            soapHeadernode.getNodeValue());
                }
            }
        }
    }
}
