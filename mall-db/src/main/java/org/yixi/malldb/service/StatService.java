package org.yixi.malldb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yixi.malldb.mapper.StatMapper;

import java.util.List;
import java.util.Map;

@Service
public class StatService {
    @Autowired
    private StatMapper statMapper;

    public List<Map> statUser(){return statMapper.statUser();}

    public List<Map> statOrder(){return statMapper.statOrder();}

    public List<Map> statGoods(){return statMapper.statGoods();}
}
