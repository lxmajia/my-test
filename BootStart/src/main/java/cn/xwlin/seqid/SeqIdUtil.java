package cn.xwlin.seqid;

import cn.xwlin.constans.DsName;
import cn.xwlin.datasource.core.DynamicDatasource;
import cn.xwlin.util.SpringContextUtil;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiang.liao
 * @create 2024/6/4
 */
public class SeqIdUtil {

  private static final Object lock = new Object();
  private static final Map<String, MySqlIdGenerator> sequenceMap = new HashMap<>();

  public static long getSequenceId(String tableName) {
    return getSequenceId(DsName.ID_SEQ.toUpperCase(), tableName);
  }

  public static long getSequenceId(String dsName, String tableName) {
    String key = dsName.toUpperCase() + "$" + tableName;
    if (!sequenceMap.containsKey(key)) {
      synchronized (lock) {
        if (!sequenceMap.containsKey(key)) {
          DynamicDatasource dataSource = SpringContextUtil.getApplicationContext().getBean(DynamicDatasource.class);
          Map<Object, DataSource> resolvedDataSources;
          try {
            Field field = DynamicDatasource.class.getSuperclass().getDeclaredField("resolvedDataSources");
            field.setAccessible(true);
            resolvedDataSources = (Map<Object, DataSource>) field.get(dataSource);
          } catch (Exception e) {
            throw new RuntimeException("通过AbstractRoutingDataSource.resolvedDataSources获取数据源发生异常");
          }
          sequenceMap.put(key, new MySqlIdGenerator(resolvedDataSources.get(dsName), tableName));
        }
      }
    }
    MySqlIdGenerator mySQLIdGenerator = sequenceMap.get(key);
    if (null == mySQLIdGenerator) {
      throw new RuntimeException("获取主键发号表信息" + key + "失败");
    }
    return mySQLIdGenerator.generateId();
  }
}
