package com.example.wanchengdemo.commom;


import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Objects;
import org.springframework.http.HttpStatus;
// 通用返回结果类，服务端返回结果最终都会返回为此对象


@Data
public class R<T> extends LinkedHashMap<String, Object> {
    private Integer code;
    private String msg;
    private Object data;
    public static final String CODE_TAG = "code";
    public static final String MSG_TAG = "msg";
    public static final String DATA_TAG = "data";
    private static final long serialVersionUID = 1L;

    public R() {
    }

    public R(int code, String msg) {
        super.put("code", code);
        super.put("msg", msg);
    }

    public R(int code, String msg, Object data) {
        super.put("code", code);
        super.put("msg", msg);
        if (Objects.nonNull(data)) {
            super.put("data", data);
        }

    }
    public static R success() {
        return success("操作成功");
    }

    public static R success(Object data) {
        return success("操作成功", data);
    }
    public static R success(String msg) {
        return success(msg, (Object)null);
    }

    public static R success(String msg, Object data) {
        return new R(HttpStatus.OK.value(), msg, data);
    }
    public static R error() {
        return error("操作失败");
    }

    public static R error(String msg) {
        return error(msg, null);
    }

    public static R error(String msg, Object data) {
        return new R(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, data);
    }

    public static R error(int code, String msg) {
        return new R<>(code, msg, null);
    }
}
