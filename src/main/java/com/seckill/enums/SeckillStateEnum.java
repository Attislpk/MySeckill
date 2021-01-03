package com.seckill.enums;

public enum SeckillStateEnum {
    //如下也分别是枚举类
    SUCCESS(1,"秒杀成功"),
    SECKILL_CLOSE(0,"秒杀结束"),
    REPEAT_SECKILL(-1,"重复秒杀"),
    DATA_REWRITE(-2,"数据篡改"),
    INNER_ERROR(-3,"系统异常")
    ;

    private final int state;

    private final String stateInfo;

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    //根据state 获取 stateInfo
    public static SeckillStateEnum getStateInfo(int state){
        for(SeckillStateEnum stateEnum: values()){
            if(state == stateEnum.state)
                //返回state对应的枚举
                return stateEnum;
        }
        //如果没有对应的枚举
        return null;
    }
}
