package com.laioffer.twitch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Properties;


@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class TwitchApplication {
    public static void main(String[] args) {
//        Properties prop = System.getProperties();
//        prop.setProperty("http.proxyHost", "127.0.0.1");
//        prop.setProperty("http.proxyPort", "7890");
//        prop.setProperty("http.nonProxyHosts", "localhost|127.0.0.*");
//        prop.setProperty("http.proxyHost", "127.0.0.1");
//        prop.setProperty("https.proxyHost", "127.0.0.1");
//        prop.setProperty("https.proxyPort", "7890");
        SpringApplication.run(TwitchApplication.class, args);
    }
}


