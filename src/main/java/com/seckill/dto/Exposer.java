package com.seckill.dto;

/**
 * 执行查询秒杀地址操作后，返回一个暴露器对象，该对象中包括秒杀是否开启、商品id、秒杀时间等信息
 */

public class Exposer {
    //秒杀是否开启/能否开启
    private boolean exposed;

    //id
    private long seckillId;

    //加密措施
    private String md5;

    //开始时间
    private long startTime;

    //结束时间
    private long endTime;

    //当前时间
    private long now;

    //秒杀开启判断逻辑(不能开启)
    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    //秒杀时间相关逻辑
    public Exposer(boolean exposed, long seckillId, long startTime, long endTime, long now) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.now = now;
    }

    //秒杀开启判断逻辑(秒杀开启)
    public Exposer(boolean exposed, long seckillId, String md5) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.md5 = md5;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", seckillId=" + seckillId +
                ", md5='" + md5 + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", now=" + now +
                '}';
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }
}
