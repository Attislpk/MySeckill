package com.seckill.dto;

//用于封装方法执行结果的DTO， 将执行结果展示到view页面--->VO: DTO+描述 传输至表现层
// 对于暴露秒杀接口方法---- > MethodExecutionResult<exposer>  对于秒杀执行接口方法---> MethodExecutionResult<SeckillExecution>
public class MethodExecutionResult<E> {

    private boolean result; //方法执行是否成功？

    private E data; //封装的数据 --> 返回对象

    private String errorInfo; //错误信息

    /**
     * 方法执行成功
     * @param result true
     * @param data 方法执行成功返回的对象
     */
    public MethodExecutionResult(boolean result, E data) {
        this.result = result;
        this.data = data;
    }

    /**
     * 方法执行失败
     * @param result false
     * @param errorInfo  方法执行失败返回的错误信息, 失败了没有必要返回对应的数据对象
     */
    public MethodExecutionResult(boolean result, String errorInfo) {
        this.result = result;
        this.errorInfo = errorInfo;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
