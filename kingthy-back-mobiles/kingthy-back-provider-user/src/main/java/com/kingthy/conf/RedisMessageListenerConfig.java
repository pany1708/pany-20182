package com.kingthy.conf;

import com.kingthy.cache.LocalCacheManager;
import com.kingthy.common.KryoSerializeUtils;
import com.kingthy.dto.UpdateCacheQueueDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;


/**
 * 描述：redis 消息监听器
 *
 * @author likejie
 * @date 2017/11/29
 */
@Configuration
public class RedisMessageListenerConfig  {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            RedisMsgPubSubListener listener) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //注册消息监听器
        container.addMessageListener(listener, new PatternTopic(LocalCacheManager.LOCAL_CHCHE_DELETE_CHANNEL));
        container.addMessageListener(listener, new PatternTopic(LocalCacheManager.LOCAL_CHCHE_UPDATE_CHANNEL));

        return container;
    }


    /**
     *
     * redis消息监听器
     */
    @Component
    public static class RedisMsgPubSubListener implements MessageListener{

        private static final Logger LOG = LoggerFactory.getLogger(RedisMsgPubSubListener.class);

        @Autowired
        private StringRedisTemplate stringRedisTemplate;

        @Override
        public void onMessage(Message message, byte[] bytes) {

            try {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                String channel = serializer.deserialize(message.getChannel());
                String data =  serializer.deserialize(message.getBody());

                if(channel.equals(LocalCacheManager.LOCAL_CHCHE_UPDATE_CHANNEL)){
                    UpdateCacheQueueDTO dto= KryoSerializeUtils.deserializationObject(data,UpdateCacheQueueDTO.class);
                    /**更新本地缓存*/
                    LocalCacheManager.put(dto.getCacheKey(),dto.getData());
                    LOG.info(dto.getCacheKey()+"本地缓存已更新！");
                }
                else if(channel.equals(LocalCacheManager.LOCAL_CHCHE_DELETE_CHANNEL)){
                    /**删除本地缓存*/
                    LocalCacheManager.remove(data);
                    LOG.info(data+"本地缓存已删除！");
                }

            }catch (Exception ex){
                LOG.error(ex.toString());
            }
        }

    }
}
