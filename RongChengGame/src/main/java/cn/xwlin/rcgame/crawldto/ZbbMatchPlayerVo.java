package cn.xwlin.rcgame.crawldto;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class ZbbMatchPlayerVo {
    private Integer code;
    private TeamPlayer data;


    @Data
    public static class TeamPlayer {
        @JSONField(name = "6865")
        private List<ZbbMatchPlayer> rongchengData;
    }
}
