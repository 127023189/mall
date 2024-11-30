package org.yixi.mallcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = "org.yixi")
public class MallCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCoreApplication.class, args);
    }

}
