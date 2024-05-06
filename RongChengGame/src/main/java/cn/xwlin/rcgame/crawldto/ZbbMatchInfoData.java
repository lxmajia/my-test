package cn.xwlin.rcgame.crawldto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ZbbMatchInfoData {
    private Integer status;
    private List<ZbbMatchInfo> data;
}
