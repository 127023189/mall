package org.yixi.malldb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.yixi.malldb.bean.LitemallAdmin;
import org.yixi.malldb.service.LitemallAdminService;

import java.util.List;

@MapperScan("org.yixi.malldb.mapper")
@SpringBootApplication
public class MallDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallDbApplication.class, args);

    }

}
