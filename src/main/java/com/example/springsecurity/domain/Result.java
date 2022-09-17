package com.example.springsecurity.domain;

import lombok.Data;

/**
 * @Classname Result
 * @Description
 * @Version 1.0.0
 * @Date 2022/9/10 18:48
 * @Created by 16537
 */
@Data
public class Result {
    private String msg;
    private boolean status;
    private Object data;
    public  Result ok(){
        this.status=true;
        return this;
    }
    public Result ok(Object data){
        this.status=true;
        this.data=data;
        return this;
    }
    public Result no(){
        status=false;
        return this;
    }
    public Result no(String msg){
        this.status=false;
        this.msg=msg;
        return this;
    }
    public Result no(Exception e){
        this.status=false;
        this.msg=e.getMessage();
        return this;
    }

}
