package cn.xwlin.task.core.c2svo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ReportParamVO implements Serializable {

    private List<ParamVO> paramList;

    @Getter
    @Setter
    public class ParamVO implements Serializable {
        private Long taskId;
        private Long execId;
        private Long reportTime;
        private String key;
        private String value;
    }
}
