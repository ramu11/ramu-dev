As In Osgi world tradinal j2ee applications wont work unless adding some stuff like Bundle-SymbolicName,Import-Package,Bundle-ClassPath,Web-ContextPath to the manifest file.

So you have to use  maven-war-plugin to generate MANIFEST file
#######################################################################################################################################################
<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>2.3</version>
				<configuration>
					<archive>
						<manifestFile>${project.build.outputDirectory}/META-INF/MANIFEST.MF</manifestFile>
					</archive>

				</configuration>
			</plugin>

And felix plugin to add  necessory bundle info to MANIFEST

	<plugin>
				<groupId>org.apache.felix</groupId>
				<artifactId>maven-bundle-plugin</artifactId>
				<extensions>true</extensions>
				<executions>
					<execution>
						<id>bundle-manifest</id>
						<phase>process-classes</phase>
						<goals>
							<goal>manifest</goal>
						</goals>
						<configuration>
							<instructions>
								<Bundle-SymbolicName>UploadServlet</Bundle-SymbolicName>
								<Export-Package />
								<Import-Package>org.apache.commons.fileupload.*,javax.servlet,javax.servlet.http,javax.servlet.*,javax.servlet.jsp.*,javax.servlet.jsp.jstl.*</Import-Package>
								<DynamicImport-Package>javax.*, org.xml.sax, org.xml.sax.*,
									org.w3c.*</DynamicImport-Package>
								<Bundle-ClassPath>.,WEB-INF/classes</Bundle-ClassPath>
								<Embed-Directory>WEB-INF/lib</Embed-Directory>
								<Embed-Dependency>*;scope=compile|runtime</Embed-Dependency>
								<Embed-Transitive>true</Embed-Transitive>
								<Web-ContextPath>UploadServlet</Web-ContextPath>
								<Webapp-Context>UploadServlet</Webapp-Context>
							</instructions>
						</configuration>
					</execution>
				</executions>
				<configuration>
					<supportedProjectTypes>
						<supportedProjectType>jar</supportedProjectType>
						<supportedProjectType>bundle</supportedProjectType>
						<supportedProjectType>war</supportedProjectType>
					</supportedProjectTypes>
					<instructions>
						<!-- ... etc ... -->
					</instructions>
				</configuration>
			</plugin>


########################################################################################################################################################

I have attached simple test case. which is tested and working fine
To test the test case. 
1) Change the upload dir to your in UploadServlet class (All upload file will go this folder)and build  using command  "mvn clean install"  and just copy the war file from  /target folder to "${Fuse-Home/deploy" dir 
2) Install below as osgi bundles in karaf 
  osgi:install mvn:commons-fileupload/commons-fileupload/1.2.2 
  osgi:install mvn:commons-io/commons-io/2.4

3)http://localhost:8181/UploadServlet/index.jsp
