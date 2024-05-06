package cn.xwlin.common.proto;

import lombok.Data;

@Data
public class HelloReq {
    private String name;

    @Override
    public String toString() {
        return "HelloReq{name='"+ name  + "'}";
    }
}