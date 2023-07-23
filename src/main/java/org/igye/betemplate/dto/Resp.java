package org.igye.betemplate.dto;

import java.util.concurrent.Callable;

public class Resp<T> {
    public static <T> Resp<T> get(Callable<T> callable) {
        try {
            return new Resp<>(callable.call(), null);
        } catch (Exception ex) {
            return new Resp<>(null, new ResultErr(-1, ex.getClass().getCanonicalName() + " " + ex.getMessage()));
        }
    }

    private T data;
    private ResultErr err;

    public Resp(T data, ResultErr err) {
        this.data = data;
        this.err = err;
    }

    public T getData() {
        return data;
    }

    public ResultErr getErr() {
        return err;
    }

    public static class ResultErr {
        private int code;
        private String msg;

        public ResultErr(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

}
