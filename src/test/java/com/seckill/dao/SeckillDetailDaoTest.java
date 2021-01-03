package com.seckill.dao;

import com.seckill.entity.SeckillDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-dao.xml")
public class SeckillDetailDaoTest {

    @Autowired
    private SeckillDetailDao seckillDetailDao;

    @Test
    public void queryByIdAndPhone() {
        long seckillId = 1000;
        long userPhone = 15351207582L;
        SeckillDetail seckillDetail = seckillDetailDao.queryByIdAndPhone(seckillId, userPhone);
        System.out.println(seckillDetail);

    }

    @Test
    public void insertDetail() {
        long seckillId = 1000;
        long userPhone = 15351207583L;
        int result = seckillDetailDao.insertDetail(seckillId, userPhone);
        System.out.println(result);
    }
}