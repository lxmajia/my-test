package cn.xwlin.tio.task;

import lombok.AllArgsConstructor;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName ExecuteStatusType.java
 * @createTime 2022-12-8-0008
 */
@AllArgsConstructor
public enum ExecuteStatusType {

    /**
     * 上报状态枚举类
     */
    PARAMS("param", "参数上报"), SUCCESS("success", "成功结果"), FAILED("failed", "失败结果"),
    ;

    String key;
    String desc;

}
