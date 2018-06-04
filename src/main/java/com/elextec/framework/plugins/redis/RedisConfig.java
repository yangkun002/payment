package com.elextec.framework.plugins.redis;

import com.elextec.framework.utils.WzStringUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis使用配置.
 * Created by wangtao on 2018/1/16.
 */
@Configuration
@EnableAutoConfiguration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;//redis服务器地址

    @Value("${spring.redis.port}")
    private int port;//redis服务器连接端口

    @Value("${spring.redis.timeout}")
    private int timeout;//连接超时时间（毫秒）

    @Value("${spring.redis.password}")
    private String password;//Redis服务器连接密码（默认为空）

    @Value("${spring.redis.pool.max-active}")
    private int maxActive;//连接池最大连接数（使用负值表示没有限制）

    @Value("${spring.redis.pool.max-wait}")
    private int maxWait;//连接池最大阻塞等待时间（使用负值表示没有限制）

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;//连接池中的最大空闲连接

    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;//连接池中的最小空闲连接

    @Bean
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory getJedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory jcf = new JedisConnectionFactory();
        jcf.setHostName(host);
        jcf.setPort(port);
        jcf.setTimeout(timeout);
        jcf.setPoolConfig(jedisPoolConfig);
        if (WzStringUtil.isNotBlank(password)) {
            jcf.setPassword(password);
        }
        return jcf;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jpc = new JedisPoolConfig();
        jpc.setMaxTotal(maxActive);
        jpc.setMaxWaitMillis(maxWait);
        jpc.setMaxIdle(maxIdle);
        jpc.setMinIdle(minIdle);
        return jpc;
    }
}
