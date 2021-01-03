package com.seckill.dao;

import com.seckill.entity.Seckill;
import com.seckill.entity.SeckillDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-dao.xml" )
public class SeckillDaoTest {

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void getById() {
        long seckillId = 1000;
        Seckill seckill = seckillDao.getById(seckillId);
        System.out.println(seckill);
    }

    @Test
    public void getSeckillList() {
        List<Seckill> seckillList = seckillDao.getSeckillList(0, 5);
        for (Seckill seckill : seckillList) {
            System.out.println(seckill);
        }
    }

    @Test
    public void reduceNumber() {
        long seckillId = 1000;
        Date nowTime = new Date();
        seckillDao.reduceNumber(seckillId,nowTime);
    }
}