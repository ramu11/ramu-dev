package com.redhat.camel.spring;

public class HtmlBean {
	
	public static String toHtml(String body) {
        body = body.replaceAll("\n", "<br/>");
        body = "<body>" + body + "</body>";
        return body;
    }

}
