package cn.xwlin.common.action;

/**
 * 路由文件信息，养成良好的习惯；使用接口文件来管理我们 action 的路由。
 * cmd和subCmd都只支持1-127
 */
public class CacheKeyConst {
  /**
   * 用户当前位置
   */
  private static final String SERVER_REGION_MAP_USER_POSITION = "SERVER_%s_REGION_%s_MAP_%s_POSITION";


  public static String playPositionCacheKey(Long serverId, Long regionId, Long userId) {
    return String.format(SERVER_REGION_MAP_USER_POSITION, serverId, regionId, userId);
  }

  public static void main(String[] args) {
    System.out.println(playPositionCacheKey(1L, 1L, 1L));
  }
}
