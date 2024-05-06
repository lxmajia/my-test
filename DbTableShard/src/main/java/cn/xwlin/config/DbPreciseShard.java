package cn.xwlin.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

//标准分片：PreciseShardingAlgorithm：精确
//范围分片：RangeShardingAlgorithm：范围
//复合分片：ComplexKeysShardingAlgorithm：多个分片键
//Hint分片:HintShardingAlgorithm：无分片键
public class DbPreciseShard implements PreciseShardingAlgorithm<Long> {

  @Override
  public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
    Long value = preciseShardingValue.getValue();
    long l = (value / 10) % 2;
    return "mj-order" + l;
  }
}
