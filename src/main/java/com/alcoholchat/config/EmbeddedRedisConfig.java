package com.alcoholchat.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;
import java.io.IOException;

@Slf4j
@Configuration
public class EmbeddedRedisConfig {

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() throws IOException {

        redisServer = new RedisServer(6370);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {

        if (redisServer != null) {

            redisServer.stop();
        }
    }
}
