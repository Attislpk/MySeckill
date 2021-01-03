package com.seckill.service;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatSeckillException;
import com.seckill.exception.SeckillClosedException;
import com.seckill.exception.SeckillException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SeckillService {

    /**
     * 根据id查询单个商品
     * @param seckillId 商品id
     * @return 商品对象
     */
    public Seckill getById(long seckillId);

    /**
     * 查询秒杀商品列表
     * @return 秒杀商品列表
     */
    public List<Seckill> getList();

    /**
     * 执行某商品的秒杀url地址的查询操作，返回秒杀地址暴露器(封装秒杀开启相关的信息)
     * @param seckillId 秒杀商品id
     *
     */
    public Exposer exportSeckillUrl(long seckillId);


    /**
     * 执行秒杀操作，返回秒杀执行器(封装秒杀执行的数据)
     * @param seckillId 秒杀商品id
     * @param userPhone 用户手机号
     * @param md5 加密信息
     */
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws RepeatSeckillException,
            SeckillClosedException, SeckillException;

}
