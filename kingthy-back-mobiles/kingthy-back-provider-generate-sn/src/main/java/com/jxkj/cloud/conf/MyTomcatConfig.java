package com.jxkj.cloud.conf;

import org.apache.catalina.core.AprLifecycleListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
//            tomcatFactory.addConnectorCustomizers(new TomcatConnectorCustomizer()
//            {
//                @Override
//                public void customize(Connector connector)
//                {
//                    Http11Nio2Protocol handler = (Http11Nio2Protocol)connector.getProtocolHandler();
//                    handler.setMaxThreads(5000);
//                    handler.setMaxConnections(10000);
//                    handler.setAcceptorThreadCount(4000);
//                    handler.setConnectionTimeout(6000);
//                    handler.setKeepAliveTimeout(6000);
//                }
//            });

        return tomcatFactory;
    }
}