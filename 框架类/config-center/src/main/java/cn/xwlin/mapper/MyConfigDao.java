package cn.xwlin.mapper;

import cn.xwlin.entity.MyConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author xiang.liao
 * @create 2024/5/6
 */
@Mapper
public interface MyConfigDao {

  MyConfig selectByPrimaryKey(Long id);

  MyConfig selectByConfigKey(@Param("configKey") String configKey);

  List<MyConfig> listChangeConfig(@Param("modified") Date modified);

  List<MyConfig> listAllConfig();
}
