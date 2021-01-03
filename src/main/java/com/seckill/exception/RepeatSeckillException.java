package com.seckill.exception;

public class RepeatSeckillException extends SeckillException{
    public RepeatSeckillException(String message) {
        super(message);
    }

    public RepeatSeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
