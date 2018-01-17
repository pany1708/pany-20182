package com.kingthy.conf;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.apache.coyote.http2.Http2Protocol;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 潘勇
 * @date 2017/11/22 10:22.
 */
@Configuration
@EnableAutoConfiguration
public class MyTomcatConfig
{

    //    @Bean
//    public EmbeddedServletContainerCustomizer tomcatCustomizer() {
//        return (container) -> {
//            if (container instanceof TomcatEmbeddedServletContainerFactory) {
//                ((TomcatEmbeddedServletContainerFactory) container)
//                    .addConnectorCustomizers((connector) -> {
//                        connector.addUpgradeProtocol(new Http2Protocol());
//                    });
//            }
//        };
//    }
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
                /**最大并发数，worker线程数，处理io事件，默认200，线程数并非越大越好，如果maxThreads设置过大，那么cpu会
                 花费大量时间用于线程切换，整体效率会降低，具体根据硬件条件设置*/
                handler.setMaxThreads(20000);
                /**最大连接数，nio默认10000 */
                handler.setMaxConnections(10000);
                handler.setMinSpareThreads(100);
                /**用于接收连接的线程的数量，默认值是1。
                 * 一般这个指需要改动的时候是因为该服务器是一个多核CPU，如果是多核 CPU 一般配置为 2 */
                handler.setAcceptorThreadCount(10);
                /**接受一个连接后等待时间，默认60000 */
                handler.setConnectionTimeout(60000);
                /**在NIO里，为了追求性能，对SocketProcessor也做了cache，用完后将对象状态清空然后放入cache，下次有新的请求过来先从cache里获取对象，获取不到再创建一个新的**/
                handler.setProcessorCache(-1);

                handler.setKeepAliveTimeout(6000);

                handler.setMaxKeepAliveRequests(10000);

            }
        });
        return tomcatFactory;
    }
}
