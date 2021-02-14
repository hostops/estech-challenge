package com.danfoss.wpcem.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.danfoss.wpcem.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.danfoss.wpcem.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.danfoss.wpcem.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.danfoss.wpcem.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.danfoss.wpcem.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.danfoss.wpcem.domain.SystemUnit.class.getName(), jcacheConfiguration);
            cm.createCache(com.danfoss.wpcem.domain.SystemUnit.class.getName() + ".systemDevices", jcacheConfiguration);
            cm.createCache(com.danfoss.wpcem.domain.SystemUnit.class.getName() + ".users", jcacheConfiguration);
            cm.createCache(com.danfoss.wpcem.domain.SystemDevice.class.getName(), jcacheConfiguration);
            cm.createCache(com.danfoss.wpcem.domain.SystemDevice.class.getName() + ".data", jcacheConfiguration);
            cm.createCache(com.danfoss.wpcem.domain.SystemDevice.class.getName() + ".connectionsUpcomings", jcacheConfiguration);
            cm.createCache(com.danfoss.wpcem.domain.SystemDevice.class.getName() + ".connectionsOutgoings", jcacheConfiguration);
            cm.createCache(com.danfoss.wpcem.domain.SystemDeviceType.class.getName(), jcacheConfiguration);
            cm.createCache(com.danfoss.wpcem.domain.SystemDeviceType.class.getName() + ".devices", jcacheConfiguration);
            cm.createCache(com.danfoss.wpcem.domain.SystemDeviceData.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
