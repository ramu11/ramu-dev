package camelpoc;

import org.apache.camel.CamelContext;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.ProbeBuilder;
import org.ops4j.pax.exam.TestProbeBuilder;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.apache.karaf.features.FeaturesService;
import org.apache.felix.service.command.CommandProcessor;
import org.apache.felix.service.command.CommandSession;

import camelpoc.TestUtility;

import javax.inject.Inject;

import java.io.File;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import static org.junit.Assert.assertNotNull;
import static org.ops4j.pax.exam.CoreOptions.*;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.configureConsole;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.keepRuntimeFolder;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.logLevel;
import static org.ops4j.pax.exam.karaf.options.LogLevelOption.LogLevel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Dictionary;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(PaxExam.class)
public class SpringPropertiesTest extends Assert {
	
	ExecutorService executor = Executors.newCachedThreadPool();
	static final Long COMMAND_TIMEOUT = 10000L;
	static final Long DEFAULT_TIMEOUT = 20000L;
	static final Long SERVICE_TIMEOUT = 30000L;
	
	static final String MIME_BUNDLE_SN = "fuse-pocs.camel-spring-properties-bundle";

	@Inject
	CamelContext camelContext;

	@Inject
	protected BundleContext bundleContext;

	@Inject
	protected FeaturesService featuresService;

	@Configuration
	public Option[] configuration() {
		return new Option[] {
				karafDistributionConfiguration()
						.frameworkUrl(
								maven().groupId("org.jboss.fuse")
										.artifactId("jboss-fuse-full")
										.type("zip").versionAsInProject())
						.karafVersion("2.4.0.redhat-621084").name("JBoss Fuse")
						.unpackDirectory(new File("target/pax"))
						.useDeployFolder(false),
				keepRuntimeFolder(),
				configureConsole().ignoreLocalConsole().ignoreRemoteShell(),

				logLevel(LogLevel.INFO),

				features(
						maven().groupId("org.apache.camel.karaf")
								.artifactId("apache-camel").type("xml")
								.classifier("features").versionAsInProject(),
						"camel-spring"),

				features(
						maven().groupId("org.apache.karaf.assemblies.features")
								.artifactId("enterprise").type("xml")
								.classifier("features").versionAsInProject(),
						"jms"),

			/*	features(
						maven().groupId("org.apache.activemq")
								.artifactId("activemq-karaf").type("xml")
								.classifier("features").versionAsInProject(),
						"activemq-blueprint", "activemq-camel"),
				features(
						maven().groupId("org.apache.cxf.karaf")
								.artifactId("apache-cxf").type("xml")
								.classifier("features").versionAsInProject(),
						"cxf-jaxws"),*/

				// Tested module
				mavenBundle().groupId("fuse-pocs")
						.artifactId("fuse-pocs-camel-spring-properties-bundle")
						.versionAsInProject(), };
	}

	//@Test
	public void shouldReplaceConstructorArgumentWithPlaceholder()
			throws InterruptedException {
		// Given
		String message = "how are you";
		MockEndpoint mockEndpoint = camelContext.getEndpoint("mock:test",
				MockEndpoint.class);
		mockEndpoint.expectedMessageCount(1);
		mockEndpoint.message(0).body().isEqualTo("MyPrefix" + message);

		// When
		camelContext.createProducerTemplate().sendBody("direct:test", message);

		// Then
		mockEndpoint.assertIsSatisfied();
	}
	
	 @Test
	 public void jmsIsInstalled() throws Exception{
		 
		// test bundle find hooks
	        Bundle[] bundles = bundleContext.getBundles();
	        for (Bundle b : bundles) {
	            System.out.println("Bundle is " + b.getBundleId() + ": " + b.getSymbolicName());
	            
	        }
	     
		 
		 
	 }
	 
	 @Test
	    public void testBundleStarted()
	    {
	        final Bundle mime = getBundle(bundleContext, MIME_BUNDLE_SN);
	       
	        assertEquals("Expecting " + MIME_BUNDLE_SN + " bundle to be active", 
	                Bundle.ACTIVE, mime.getState());
	    }

	//@Test
	public void test() throws Exception {

		assertTrue(featuresService.isInstalled(featuresService
				.getFeature("jms")));
		

	}

	//@Test
	public void bundleContextShouldNotBeNull() throws Exception {
		assertNotNull(bundleContext);

	}
	
	private Bundle getBundle(BundleContext ctx, String symbolicName) {
        for(Bundle b : ctx.getBundles()) {
            if(symbolicName.equals(b.getSymbolicName())) {
                return b;
            }
        }
        return null;
    }
	/**
	 * Executes a shell command and returns output as a String. Commands have a
	 * default timeout of 10 seconds.
	 * 
	 * @param command
	 * @return
	 */
	protected String executeCommand(final String command) {
		return executeCommand(command, COMMAND_TIMEOUT, false);
	}

	/**
	 * Executes a shell command and returns output as a String. Commands have a
	 * default timeout of 10 seconds.
	 * 
	 * @param command
	 *            The command to execute.
	 * @param timeout
	 *            The amount of time in millis to wait for the command to
	 *            execute.
	 * @param silent
	 *            Specifies if the command should be displayed in the screen.
	 * @return
	 */
	protected String executeCommand(final String command, final Long timeout, final Boolean silent) {
		String response;
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		final PrintStream printStream = new PrintStream(byteArrayOutputStream);
		final CommandProcessor commandProcessor = getOsgiService(CommandProcessor.class);
		final CommandSession commandSession = commandProcessor.createSession(System.in, printStream, System.err);
		FutureTask<String> commandFuture = new FutureTask<String>(new Callable<String>() {
			public String call() {
				try {
					if (!silent) {
						System.err.println(command);
					}
					commandSession.execute(command);
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
				printStream.flush();
				return byteArrayOutputStream.toString();
			}
		});

		try {
			executor.submit(commandFuture);
			response = commandFuture.get(timeout, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace(System.err);
			response = "SHELL COMMAND TIMED OUT: ";
		}

		return response;
	}

	/**
	 * Executes multiple commands inside a Single Session. Commands have a
	 * default timeout of 10 seconds.
	 * 
	 * @param commands
	 * @return
	 */
	protected String executeCommands(final String... commands) {
		String response;
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		final PrintStream printStream = new PrintStream(byteArrayOutputStream);
		final CommandProcessor commandProcessor = getOsgiService(CommandProcessor.class);
		final CommandSession commandSession = commandProcessor.createSession(System.in, printStream, System.err);
		FutureTask<String> commandFuture = new FutureTask<String>(new Callable<String>() {
			public String call() {
				try {
					for (String command : commands) {
						System.err.println(command);
						commandSession.execute(command);
					}
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
				return byteArrayOutputStream.toString();
			}
		});

		try {
			executor.submit(commandFuture);
			response = commandFuture.get(COMMAND_TIMEOUT, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace(System.err);
			response = "SHELL COMMAND TIMED OUT: ";
		}

		return response;
	}

	protected <T> T getOsgiService(Class<T> type, long timeout) {
		return getOsgiService(type, null, timeout);
	}

	protected <T> T getOsgiService(Class<T> type) {
		return getOsgiService(type, null, SERVICE_TIMEOUT);
	}

	protected <T> T getOsgiService(Class<T> type, String filter, long timeout) {
		ServiceTracker tracker = null;
		try {
			String flt;
			if (filter != null) {
				if (filter.startsWith("(")) {
					flt = "(&(" + Constants.OBJECTCLASS + "=" + type.getName() + ")" + filter + ")";
				} else {
					flt = "(&(" + Constants.OBJECTCLASS + "=" + type.getName() + ")(" + filter + "))";
				}
			} else {
				flt = "(" + Constants.OBJECTCLASS + "=" + type.getName() + ")";
			}
			Filter osgiFilter = FrameworkUtil.createFilter(flt);
			tracker = new ServiceTracker(bundleContext, osgiFilter, null);
			tracker.open(true);
			// Note that the tracker is not closed to keep the reference
			// This is buggy, as the service reference may change i think
			Object svc = type.cast(tracker.waitForService(timeout));
			if (svc == null) {
				Dictionary dic = bundleContext.getBundle().getHeaders();
				System.err.println("Test bundle headers: " + TestUtility.explode(dic));

				for (ServiceReference ref : TestUtility.asCollection(bundleContext.getAllServiceReferences(null, null))) {
					System.err.println("ServiceReference: " + ref);
				}

				for (ServiceReference ref : TestUtility.asCollection(bundleContext.getAllServiceReferences(null, flt))) {
					System.err.println("Filtered ServiceReference: " + ref);
				}

				throw new RuntimeException("Gave up waiting for service " + flt);
			}
			return type.cast(svc);
		} catch (InvalidSyntaxException e) {
			throw new IllegalArgumentException("Invalid filter", e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
