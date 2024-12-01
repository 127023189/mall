package org.yixi.malldb;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.yixi.malldb.bean.LitemallAdmin;
import org.yixi.malldb.mybatis.JsonIntegerArrayTypeHandler;
import org.yixi.malldb.service.LitemallAdminService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = MallDbApplication.class)
@MapperScan("org.yixi.malldb.mapper")
public class sveTest {
    @Autowired
    private LitemallAdminService litemallAdminService;

    @Autowired
    private ApplicationContext context;


    @Test
    public void testFindAdmin() {
        List<LitemallAdmin> admins = litemallAdminService.findAdmin("admin123");
        System.out.println(admins);
        System.out.println(admins.get(0).getRoleIds()[0]);
    }
//    @Test
//    public void testToInteger() {
//        String json = "[1,2,3]";
//        JsonIntegerArrayTypeHandler handler = new JsonIntegerArrayTypeHandler();
//        Integer[] result = handler.toObject(json);
//        System.out.println(Arrays.toString(result));
//    }
}
