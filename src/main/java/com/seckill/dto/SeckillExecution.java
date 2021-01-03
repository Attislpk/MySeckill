package com.seckill.dto;

import com.seckill.entity.SeckillDetail;
import com.seckill.enums.SeckillStateEnum;

public class SeckillExecution {
    private long seckillId;

    private int state;

    private String stateInfo;

    private SeckillDetail seckillDetail;

    //秒杀失败, 其实这个构造方法和异常二选一即可，抛出异常则该构造方法没有用处，如果使用该构造方法则无需抛出异常
    public SeckillExecution(long seckillId,SeckillStateEnum stateEnum, String stateInfo) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //秒杀成功
    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum, SeckillDetail seckillDetail) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.seckillDetail = seckillDetail;
    }


    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", seckillDetail=" + seckillDetail +
                '}';
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SeckillDetail getSeckillDetail() {
        return seckillDetail;
    }

    public void setSeckillDetail(SeckillDetail seckillDetail) {
        this.seckillDetail = seckillDetail;
    }
}
