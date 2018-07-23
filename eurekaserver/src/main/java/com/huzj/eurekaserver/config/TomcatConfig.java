package com.huzj.eurekaserver.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * Tomcat配置类
 * @author HuZJ
 */
public class TomcatConfig {
    @Value("${tomcat.server.port}")
    private int port;
    @Value("${tomcat.server.acceptorThreadCount}")
    private int acceptorThreadCount;
    @Value("${tomcat.server.minSpareThreads}")
    private int minSpareThreads;
    @Value("${tomcat.server.maxSpareThreads}")
    private int maxSpareThreads;
    @Value("${tomcat.server.maxThreads}")
    private int maxThreads;
    @Value("${tomcat.server.maxConnections}")
    private int maxConnections;
    @Value("${tomcat.server.protocol}")
    private String protocol;
    @Value("${tomcat.server.redirectPort}")
    private int redirectPort;
    @Value("${tomcat.server.compression}")
    private String compression;
    @Value("${tomcat.server.connectionTimeout}")
    private int connectionTimeout;
    @Value("${tomcat.server.uriEncoding}")
    private String uriEncoding;

    @Bean
    public ServletWebServerFactory tomcatContainer() {
        // 2.0.3以上版本的Spring用TomcatServletWebServerFactory这个类,
        // 以下版本的用TomcatEmbeddedServletContainerFactory这个类
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            public void postProcessContext(Context context) {
                // 安全约束
                SecurityConstraint securityConstraint = new SecurityConstraint();
                // "confidential"，机密的，表示信任的
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection securityCollection = new SecurityCollection();
                securityCollection.addPattern("/*");
                securityConstraint.addCollection(securityCollection);
                context.addConstraint(securityConstraint);
            }
        };
        TomcatConnectorCustomizer tomcatConnectorCustomizer = new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                connector.setPort(port);
                connector.setAttribute("connectionTimeout", connectionTimeout);
                connector.setAttribute("acceptorThreadCount", acceptorThreadCount);
                connector.setAttribute("minSpareThreads", minSpareThreads);
                connector.setAttribute("maxSpareThreads", maxSpareThreads);
                connector.setAttribute("maxThreads", maxThreads);
                connector.setAttribute("maxConnections", maxConnections);
                connector.setAttribute("protocol", protocol);
                connector.setAttribute("redirectPort",redirectPort);
                connector.setAttribute("compression", compression);
                connector.setAttribute("URIEncoding", uriEncoding);

                connector.setSecure(false);
                connector.setRedirectPort(8443);
                connector.setScheme("http");
                connector.setURIEncoding(uriEncoding != null ? uriEncoding : "UTF-8");
            }
        };
        tomcat.addConnectorCustomizers(tomcatConnectorCustomizer);


        return tomcat;
    }
}
