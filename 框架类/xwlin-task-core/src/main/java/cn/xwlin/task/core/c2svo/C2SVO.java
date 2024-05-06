package cn.xwlin.task.core.c2svo;

import lombok.Data;
import cn.xwlin.task.core.enums.C2SMsgType;

import java.io.Serializable;

@Data
public class C2SVO implements Serializable {
    private C2SMsgType c2sType;
    private String param;
}
