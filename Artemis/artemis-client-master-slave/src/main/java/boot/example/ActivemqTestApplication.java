package boot.example;

import javax.jms.ConnectionFactory;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ActivemqTestApplication {
    private final static Logger LOG = LoggerFactory.getLogger(ActivemqTestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ActivemqTestApplication.class, args);
    }

    @ConfigurationProperties(prefix = "spring.artemis")
    public class AmqConfig {
        private String url;

        @Bean("connectionFactory")
        public ConnectionFactory connectionFactory() {
            ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(this.url);
            LOG.info("cf info : {}", cf);
            return cf;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    @Component
    class MyRoute extends RouteBuilder {

        @Override
        public void configure() throws Exception {
            from("jms:foo?connectionFactory=#connectionFactory").transacted().log("log recieved");

            from("timer:bar").setBody(constant("the message")).transacted().log("sending").to("jms:foo?connectionFactory=#connectionFactory");
        }

    }
}
