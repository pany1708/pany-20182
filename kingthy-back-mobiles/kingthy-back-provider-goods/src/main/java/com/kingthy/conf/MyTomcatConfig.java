package com.kingthy.conf;

import org.apache.catalina.core.AprLifecycleListener;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

/**
 * @author 潘勇
 * @Data 2017/9/7 15:02.
 */
@Component
@EnableAutoConfiguration
public class MyTomcatConfig
{
    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory()
    {
        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
        tomcatFactory.setProtocol("org.apache.coyote.http11.Http11Nio2Protocol");
        tomcatFactory.addConnectorCustomizers(new TomcatConnectorCustomizer()
        {
            @Override
            public void customize(Connector connector)
            {
                Http11Nio2Protocol handler = (Http11Nio2Protocol)connector.getProtocolHandler();
                handler.setMaxThreads(20000);
                handler.setMaxConnections(20000);
                handler.setAcceptorThreadCount(8);
                handler.setTcpNoDelay(true);
                handler.setConnectionTimeout(60000);
                handler.setKeepAliveTimeout(6000);
                //表示每个连接只响应一次就关闭，避免产生大量socket time_wait
                handler.setMaxKeepAliveRequests(1000);
            }
        });

        return tomcatFactory;
    }
}

