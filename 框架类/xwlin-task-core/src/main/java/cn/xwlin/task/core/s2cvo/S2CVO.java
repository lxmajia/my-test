package cn.xwlin.task.core.s2cvo;

import lombok.Data;
import cn.xwlin.task.core.enums.S2CMsgType;

import java.io.Serializable;

@Data
public class S2CVO implements Serializable {
    private S2CMsgType c2sType;
    private String param;
}
