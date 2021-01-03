package com.seckill.dao;

import com.seckill.entity.SeckillDetail;
import org.apache.ibatis.annotations.Param;

/**
 *  查询/插入秒杀明细
 */
public interface SeckillDetailDao {

    /**
     * 根据商品id和用户手机号查询订单明细
     * @param seckillId 商品id
     * @param userPhone 用户手机号
     * @return 秒杀明细
     */
    SeckillDetail queryByIdAndPhone(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);


    /**
     * 将秒杀明细插入到商品明细表中
     * @param seckillId 商品id
     * @param userPhone 用户手机号
     * @return 数据库中修改的条数
     */
    int insertDetail(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
}
