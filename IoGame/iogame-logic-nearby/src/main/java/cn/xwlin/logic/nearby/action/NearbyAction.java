package cn.xwlin.logic.nearby.action;

import cn.xwlin.common.action.WsActionResp;
import cn.xwlin.common.action.CacheKeyConst;
import cn.xwlin.common.action.CmdConst;
import cn.xwlin.logic.nearby.req.NearPlayerReq;
import cn.xwlin.logic.nearby.resp.NearPlayerResp;
import cn.xwlin.logic.nearby.resp.data.NearPlayerInfo;
import cn.xwlin.web.util.WebServerUtil;
import com.alibaba.fastjson2.JSON;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import org.assertj.core.util.Lists;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.math.BigDecimal;
import java.util.List;

/**
 * 附近一定范围的人
 */
@ActionController(CmdConst.NearbyCmd.cmd)
public class NearbyAction {

  /**
   * 示例 here 方法
   */
  @ActionMethod(CmdConst.NearbyCmd.SubCmd.nearPlayer)
  public WsActionResp<NearPlayerResp> nearPlayer(NearPlayerReq nearPlayerReq, FlowContext flowContext) {
    // 记录我的位置，获取周边位置
    StringRedisTemplate redisTemplate = WebServerUtil.getStringRedisTemplate();

    String positionCacheKey = CacheKeyConst.playPositionCacheKey(1L, 1L, 1L);
    Point myPoint = new Point(nearPlayerReq.getLongitude().doubleValue(), nearPlayerReq.getLatitude().doubleValue());
    redisTemplate.opsForGeo().add(positionCacheKey, myPoint, String.valueOf(flowContext.getUserId()));


    Metric metric = RedisGeoCommands.DistanceUnit.METERS;
    Distance findDistance = new Distance(1000, metric);
    Circle circle = new Circle(myPoint, findDistance);

    //设置参数 包括距离、坐标、条数
    RedisGeoCommands.GeoRadiusCommandArgs geoRadiusCommandArgs = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance()//包含距离
            .includeCoordinates()//包含经纬度
            .sortAscending()//正序排序
            .limit(50); //条数
    GeoResults<RedisGeoCommands.GeoLocation<String>> radius = redisTemplate.opsForGeo().radius(positionCacheKey, circle, geoRadiusCommandArgs);
    System.out.println(JSON.toJSONString(radius));

    NearPlayerResp nearPlayerResp = new NearPlayerResp();
    List<NearPlayerInfo> nearPlayerInfoList = Lists.newArrayList();
    for (GeoResult<RedisGeoCommands.GeoLocation<String>> geoLocationGeoResult : radius) {
      Distance distance = geoLocationGeoResult.getDistance();
      RedisGeoCommands.GeoLocation<String> content = geoLocationGeoResult.getContent();
      // 自己不返回
      if(content.getName().equals(String.valueOf(flowContext.getUserId()))){
        continue;
      }
      NearPlayerInfo nearPlayerInfo = new NearPlayerInfo();
      nearPlayerInfo.setUserId(Long.valueOf(content.getName()));
      nearPlayerInfo.setLongitude(BigDecimal.valueOf(content.getPoint().getX()));
      nearPlayerInfo.setLatitude(BigDecimal.valueOf(content.getPoint().getY()));
      nearPlayerInfo.setDistance(BigDecimal.valueOf(distance.getValue()));
      nearPlayerInfoList.add(nearPlayerInfo);
    }
    nearPlayerResp.setNearPlayerInfoList(nearPlayerInfoList);
    return WsActionResp.succuess(nearPlayerResp);
  }
}