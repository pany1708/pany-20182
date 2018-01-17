package com.kingthy.conf;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@EnableAutoConfiguration
@Component
public class MyTomcatConfig
{
    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory()
    {
        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
        AprLifecycleListener arpLifecycle = new AprLifecycleListener();
        tomcatFactory.setProtocol("org.apache.coyote.http11.Http11Nio2Protocol");
        tomcatFactory.addConnectorCustomizers(new TomcatConnectorCustomizer()
        {
            @Override
            public void customize(Connector connector)
            {
                Http11Nio2Protocol handler = (Http11Nio2Protocol)connector.getProtocolHandler();
                handler.setMaxThreads(8000);
                handler.setMaxConnections(10000);
                handler.setAcceptorThreadCount(8);
                handler.setProcessorCache(-1);
                handler.setTcpNoDelay(true);
                handler.setConnectionTimeout(8000);
                handler.setKeepAliveTimeout(6000);
            }
        });

        return tomcatFactory;
    }
}