package com.seckill.dao;



import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import com.seckill.entity.Seckill;

/**
 * 对数据库进行操作的dao层，对seckill表进行操作
 */
public interface SeckillDao {

    /**
     * 根据id查询秒杀商品
     * @param seckillId 商品id
     * @return 秒杀商品
     */
    public Seckill getById(long seckillId);

    /**
     * 查询所有秒杀商品列表, 并分页显示
     * @return 所有商品列表
     */
    public List<Seckill> getSeckillList(@Param("offset") int offerset,@Param("limit") int limit);


    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
}
