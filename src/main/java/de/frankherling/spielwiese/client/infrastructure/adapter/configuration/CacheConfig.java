package de.frankherling.spielwiese.client.infrastructure.adapter.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@Slf4j
public class CacheConfig {
    @Bean
    public CaffeineCacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .evictionListener((key, value, cause) -> log.info("Evicted key={}, value={}, cause={}", key, value, cause))
                .removalListener((key, value, cause) -> log.info("Removed key={}, value={}, cause={}", key, value, cause))
                .expireAfterWrite(15, TimeUnit.SECONDS)
                .maximumSize(500)
                .weakKeys()
                .weakValues()
                .recordStats());
        return cacheManager;
    }
}
