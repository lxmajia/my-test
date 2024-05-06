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
public enum EnumEventType {
    GOAL(1, "进球"),
    WULONG_GOAL(2, "乌龙球"),
    YELLOW_CARD(3, "黄牌"),
    TWO_YELLOW_CARD(4, "两黄罚下"),
    RED_CARD(5, "红牌"),
    POINT_GOAL(6, "点球罚金"),
    ASSIST(7, "助攻"),
    EXCHANGE_UP(8, "换上"),
    EXCHANGE_DOWN(9, "换下"),
    HURT_DOWN(10, "受伤换下"),
    MAKE_POINT_GOAL(11, "造点"),
    POINT_GOAL_LOSE(12, "点球罚丢"),
    ;
    private int status;
    private String desc;
}
