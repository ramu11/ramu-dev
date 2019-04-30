package com.mycompany.cxf.soap.endpoint;

import java.io.OutputStream;

import javax.xml.stream.XMLStreamWriter;
 
import org.apache.cxf.interceptor.AttachmentOutInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.staxutils.StaxUtils;

public class CDataWriterInterceptor extends AbstractPhaseInterceptor {
	 
		public CDataWriterInterceptor() {
			super(Phase.PRE_STREAM);
			addAfter(AttachmentOutInterceptor.class.getName());
		}
	 
		@Override
		public void handleMessage(Message message) {
			// Required for CDATA to working
			message.put("disable.outputstream.optimization", Boolean.TRUE);
			XMLStreamWriter writer = StaxUtils.createXMLStreamWriter(message.getContent(OutputStream.class));
			message.setContent(XMLStreamWriter.class, new CDataXMLStreamWriter(writer));
		}
}
