/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.acme.timer;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.google.drive.GoogleDriveComponent;
import org.apache.camel.component.google.drive.GoogleDriveConfiguration;
import org.apache.camel.model.dataformat.JsonLibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.model.File;
import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.Headers;

public class GoogleDriveRoute extends RouteBuilder {
    
  
    private static  String ACCESS_TOKEN = "ya29.a0AfH6SMDZrI9zqi1Or9KCGfsS59bJIm3K2XcJkfv6pHqN3xRVE0Sz3Bh0r2LPdPuR5WfPUeTkWADm7J1pfGo8IX-oOJ0oZXsjoUpkTE9er2nfmBMZyYfk7E9XPwj7cGlubMi0Jn4igN69V3NGfx1mq2HokkCeUs8yH2E";
    private static  String CLIENT_ID = "169356949028-vs2cpa44opv204kuvil220l4h334mehm.apps.googleusercontent.com";
    private static  String CLIENT_SECRET = "0XkSXfMA7ynGbYfxw-VByUjK";
    
    
    
    private static GoogleDriveConfiguration getComponent()
    {
        final GoogleDriveConfiguration configuration = new GoogleDriveConfiguration();
        configuration.setAccessToken(ACCESS_TOKEN);
        configuration.setClientId(CLIENT_ID);
        configuration.setClientSecret(CLIENT_SECRET);
        
        return configuration;
        
        
    }

    @Override
    public void configure() throws Exception {
        
        final CamelContext context = super.getContext();
        
        // add GoogleDriveComponent  to Camel context
        final GoogleDriveComponent component = new GoogleDriveComponent(context);
        component.setConfiguration(getComponent());       
        
        context.addComponent("google-drive", component);
        
        
        
      /*  from("timer:foo?period=10000")
                .to("google-drive://drive-files/list")
                .transform(simple("${body.toString()}"))
                .log("${body}");*/
        
       /* from("timer:foo?period=10000")
        //call processor and set headers
        .process(new MyProcessor())
        .to("google-drive://drive-files/insert")
        .log("done");*/
        
       /* from("timer:foo?period=10000")
        .process(new MyProcessor())
        .to("google-drive://drive-files/get?fileId=16t0SbrIualNnIZ27Iqy_iLTXv_CE5Zik")
        .log("${body}");*/
        
        /*from("timer:foo?period=10000")
        .process(new MyProcessor())
        .to("google-drive://drive-files/delete?fileId=16t0SbrIualNnIZ27Iqy_iLTXv_CE5Zik")
        .log("${body}");*/
        
       /* from("timer:foo?period=10000")
        .process(new MyProcessor())
        .to("google-drive://drive-files/copy?fileId=1gfZ6jCRnIwsj80KhAHpAMauoPqefOgzr")
        .to("file:src/main/resources/drive");*/
        
    }
}
