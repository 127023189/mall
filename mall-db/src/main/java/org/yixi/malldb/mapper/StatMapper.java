package org.yixi.malldb.mapper;

import org.omg.CORBA.MARSHAL;

import java.util.List;
import java.util.Map;

public interface StatMapper {
    List<Map> statUser();

    List<Map> statOrder();

    List<Map> statGoods();
}
