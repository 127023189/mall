package org.yixi.mallcore.storage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yixi.mallcore.storage.LocalStorage;
import org.yixi.mallcore.storage.StorageService;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageAutoConfiguration {
    private final StorageProperties properties;

    public StorageAutoConfiguration(StorageProperties properties) {
        this.properties = properties;
    }

    @Bean
    public LocalStorage localStorage(){
        LocalStorage localStorage = new LocalStorage();
        localStorage.setAddress(properties.getLocal().getAddress());
        localStorage.setStoragePath(properties.getLocal().getStoragePath());
        return localStorage;
    }

    /**
     * 创建存储服务
     * @return
     */
    @Bean
   public StorageService storageService(){
        StorageService storageService = new StorageService();
        String active = properties.getActive();
        storageService.setActive(active);
        if("local".equals(active)){
            storageService.setStorage(localStorage());
        }else{
            throw new RuntimeException("Storage type is not defined");
        }
        return storageService;
    }



}
