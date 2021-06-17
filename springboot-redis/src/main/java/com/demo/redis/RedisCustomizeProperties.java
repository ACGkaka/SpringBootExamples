package com.demo.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p> @Title RedisCustomizeProperties
 * <p> @Description Redis属性定制
 *
 * @author ACGkaka
 * @date 2021/6/17 11:44
 */
@ConfigurationProperties(prefix = "spring.redis")
public class RedisCustomizeProperties {

    /**
     * 默认前缀
     */
    private static final String REDIS_DEFAULT_PREFIX = "acgkaka:";
    /**
     * 自定义前缀，例：stats.prefix=ACGkaka
     */
    private String prefix = REDIS_DEFAULT_PREFIX;

    public String getPrefix() {
        if (prefix == null) {
            prefix = REDIS_DEFAULT_PREFIX;
        }
        System.out.println("prefix: " + prefix);
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
