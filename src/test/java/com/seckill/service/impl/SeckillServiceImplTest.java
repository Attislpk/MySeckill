package com.seckill.service.impl;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatSeckillException;
import com.seckill.exception.SeckillClosedException;
import com.seckill.exception.SeckillException;
import com.seckill.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public class SeckillServiceImplTest {

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getById() {
        long seckillId = 1000;
        Seckill seckill = seckillService.getById(seckillId);//实际上调用的是该接口的实现类SeckillServiceImpl.getById(seckillId)
        System.out.println(seckill);
    }

    @Test
    public void getList() {
        List<Seckill> list = seckillService.getList();
        for (Seckill seckill: list){
            System.out.println(seckill);
        }
    }

    //测试代码的可重用性，逻辑的完整性
    @Test
    public void seckillLogic() {
        long seckillId = 1004;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()){
            System.out.println(exposer);//md5=988e5c16efbfb17475354b20a67cec36
            long userPhone = 15321207532L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
                System.out.println(seckillExecution);
            }catch (RepeatSeckillException | SeckillClosedException e){
                System.out.println(e.getMessage());
            }catch (SeckillException e){
                System.out.println(e.getMessage());
            }
        }else {
            System.out.println(exposer);
        }
    }
}