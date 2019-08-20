package com.ops.sunwou.exception;

public class MyException extends RuntimeException {

    public String selfId;

    public String opertionId;

    public String bz;

    public MyException(String err) {
        super(err);
        this.selfId = "";
        this.opertionId = "";
        this.bz = "";
    }

    public MyException(String selfId, String opertionId, String bz) {
        super();
        this.selfId = selfId;
        this.opertionId = opertionId;
        this.bz = bz;
    }


}
