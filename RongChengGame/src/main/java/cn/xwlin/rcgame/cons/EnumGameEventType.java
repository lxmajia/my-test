package cn.xwlin.rcgame.cons;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiang.liao
 * @create 2023/9/1
 * 是否主场
 */
@Getter
@AllArgsConstructor
public enum EnumGameEventType {
    Goal(1, "进球","/1.png"),
    NoGoalOnTarget(2, "射中门柱","/31.png"),
    FailToPointGoal(3, "犯规造成点球","/36.png"),
    PointGoalYes(4, "点球命中","/7.png"),
    PointGoalNo(5, "射失点球","/13.png"),
    PointGoalReject(6, "扑出点球","/30.png"),
    WuLong(7, "乌龙","/8.png"),
    Assist(8, "助攻","/12.png"),
    Yellow(9, "黄牌","/3.png"),
    Red(10, "红牌","/2.png"),
    TwoYellow(11, "两黄变红","/9.png"),
    TransIn(12, "换入","/4.png"),
    TransOut(13, "换出","/5.png"),
    ;
    private int status;
    private String desc;
    // https://live.titan007.com/detail/2262992cn.htm
    private String titan007Check;
}
