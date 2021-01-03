package com.seckill.service.impl;

import com.seckill.dao.SeckillDao;
import com.seckill.dao.SeckillDetailDao;
import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.entity.SeckillDetail;
import com.seckill.enums.SeckillStateEnum;
import com.seckill.exception.RepeatSeckillException;
import com.seckill.exception.SeckillClosedException;
import com.seckill.exception.SeckillException;
import com.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

    //系统当前时间
    Date nowTime = new Date();

    //注入SeckillDao,SeckillDetailDao对象

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SeckillDetailDao seckillDetailDao;

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.getById(seckillId);
    }

    @Override
    public List<Seckill> getList() {
        return seckillDao.getSeckillList(0,5);
    }

    /**
     * 判断某商品是否暴露了秒杀接口，首先应该获取该商品
     * @param seckillId 秒杀商品id
     * @return 秒杀接口暴露器
     */
    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.getById(seckillId);

        if(seckill == null){
            //数据库中没有该商品,自然无法暴露秒杀地址
            return new Exposer(false,seckillId);
        }else if(nowTime.getTime() < seckill.getStartTime().getTime() ||
                nowTime.getTime() > seckill.getEndTime().getTime()){
            //判断当前系统时间与秒杀时间的关系,秒杀未开启或秒杀结束都无法暴露秒杀地址
            return new Exposer(false,seckillId,seckill.getStartTime().getTime(),
                    seckill.getEndTime().getTime(),nowTime.getTime());
        }else{
            //否则能够正常开启秒杀,暴露秒杀地址， 对于第一次访问的商品而言，在此处会生成md5值
            String md5 = getMd5(seckillId);
            return new Exposer(true,seckillId,md5);
        }
    }

    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws RepeatSeckillException, SeckillClosedException, SeckillException {
        if(md5 == null || !(md5.equals(getMd5(seckillId)))){
            //md5为空(经过暴露后的URL中，md5不可能为空，只可能是md5被删除)或者与系统中的md5无法匹配(数据篡改), 则秒杀不成功---->返回秒杀异常
            throw new SeckillException("Seckill data rewrite");
        }else {
            try {
                //完整秒杀业务=减库存+插入明细
                //减库存
                int reduceNumber = seckillDao.reduceNumber(seckillId, nowTime);
                if(reduceNumber <= 0){
                    //sql执行失败,未能成功执行秒杀
                    throw new SeckillClosedException("Seckill is closed");
                }else {
                    //减库存执行完毕，开始插入明细
                    int insertDetail = seckillDetailDao.insertDetail(seckillId, userPhone);
                    if (insertDetail <= 0){
                        //联合主键确保不能插入重复数据
                        throw new RepeatSeckillException("Repeat seckill");
                    }else {
                        //插入明细成功
                        //查询该明细
                        SeckillDetail seckillDetail = seckillDetailDao.queryByIdAndPhone(seckillId, userPhone);
                        //stateEnum.SUCCESS(1,"秒杀成功") 本质也是一个枚举类
                        return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS,seckillDetail);
                    }
                }
            }catch (RepeatSeckillException e){
                throw new RepeatSeckillException("Repeat Seckill");
            }catch (SeckillClosedException e){
                throw new SeckillClosedException("Seckill has closed");
            }
            catch (Exception e){
                //将编译期异常转化为运行期异常，方便spring做rollback
                System.out.println(e.getMessage());
                throw new SeckillException("Inner error",e);
            }
        }
    }

    //生成md5的方法
    private String getMd5(long seckillId){
        //用于md5加密的盐值
        String salt = "raetsxrcdtvfygbjkuhnijm6856463524@#$%^&*IUYGFDSDF";
        String base = seckillId + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
