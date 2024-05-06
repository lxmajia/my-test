package cn.xwlin.dubbo.client.base;

import cn.xwlin.dubbo.client.util.IPUtil;

import java.io.Serializable;
import java.util.Date;

public class RpcReponse<T> implements Serializable {

    private Integer code;
    private String message;
    private Long timestamp;
    private String bizIp;
    private T data;

    private RpcReponse() {
        this.setTimestamp(System.currentTimeMillis());
        this.setBizIp(IPUtil.getLocalIpAddress());
    }

    public boolean isSuccess() {
        return 0 == code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBizIp() {
        return bizIp;
    }

    public void setBizIp(String bizIp) {
        this.bizIp = bizIp;
    }

    public static RpcReponse failed(Integer code, String message) {
        RpcReponse<Object> objectRpcReponse = new RpcReponse<>();
        objectRpcReponse.setCode(code);
        objectRpcReponse.setMessage(message);
        return objectRpcReponse;
    }

    public static <T> RpcReponse<T> success(T t) {
        RpcReponse<T> objectRpcReponse = new RpcReponse<>();
        objectRpcReponse.setCode(0);
        objectRpcReponse.setMessage("SUCCESS");
        objectRpcReponse.setData(t);
        return objectRpcReponse;
    }


}
