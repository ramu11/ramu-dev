package com.mycompany.cxf.soap.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.headers.Header;
import org.apache.cxf.headers.Header.Direction;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.staxutils.StaxUtils;
import org.apache.cxf.jaxb.JAXBDataBinding;

import com.mycompany.cxf.soap.model.Caller;

import javax.xml.namespace.QName;



public class InsertRequestOutHeaderProcessor  implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		  org.apache.camel.Message in = exchange.getIn();
	        List<SoapHeader> soapHeaders = new ArrayList<SoapHeader>();
	        in.setHeader(Header.HEADER_LIST, soapHeaders);

	        Caller testHeader = new Caller();
	        testHeader.setFirstname("Joe");
	        testHeader.setLastname("Luo");

	        QName headerQname = new QName("http://model.soap.cxf.mycompany.com/", "testHeader");
	        SoapHeader newHeader = new SoapHeader(headerQname, testHeader, new JAXBDataBinding(Caller.class));
	         
	        // make sure direction is IN since it is a request message.
	        newHeader.setDirection(Direction.DIRECTION_IN);
	        soapHeaders.add(newHeader);

		
	}
	
	
	

}
