The problem is:

    <execution>

             <id>bundle-manifest</id>

             <phase>process-classes</phase>

             <goals>

                 <goal>manifest</goal>

             </goals>

            </execution>

configuration of maven-bundle-plugin. It generates target/classes/META-INF/MANIFEST.MF file.

Then org.apache.camel.test.blueprint.CamelBlueprintHelper#createTestBundle() is called which generates bundle that will be installed in felix-connect (formerly known as PojoSR). Then when simplified Felix framework starts, it actually has *two* bundles with OSGI-INF/blueprint/blueprint.xml resources, so two blueprint containers are started and CamelBlueprintTestSupport has problem determining which is which.

When you skip this configuration, you'll have correct execution of test. But remember:
 – com.redhat.blueprintTestsupport.RouteTest#loadConfigAdminConfigurationFile() provides initial configuration
 – com.redhat.blueprintTestsupport.RouteTest#useOverridePropertiesWithConfigAdmin() overrides this configuration

so when @Test method starts, "{{destination}}" placeholder is resolved to "mock:extra", so correct @Test method is:

    getMockEndpoint("mock:extra").expectedMinimumMessageCount(1);

no message will be sent to "mock:original".

