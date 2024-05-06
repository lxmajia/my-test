package cn.xwlin.repo;

import com.google.common.collect.Maps;
import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName UserRepository.java
 * @createTime 2023-3-2-0002
 */
public class TaskRepository {

  Logger logger = LoggerFactory.getLogger(TaskRepository.class);
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private final String T_TASK_CREATE_SQL = "CREATE TABLE `t_task` ( `id` bigint NOT NULL AUTO_INCREMENT, `task_type` int DEFAULT NULL, `task_uk` varchar(64) DEFAULT NULL, `task_status` int DEFAULT NULL, `task_params` json DEFAULT NULL, `retry_times` int DEFAULT NULL,  `next_exec_time` datetime DEFAULT NULL,  `created` datetime DEFAULT NULL,  PRIMARY KEY (`id`), UNIQUE KEY `uni_type_uk` (`task_type`,`task_uk`) USING BTREE ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

  public TaskRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = jdbcTemplate;

  }

  public void init() {
    List<Map<String, Object>> maps = namedParameterJdbcTemplate.queryForList("select * from information_schema.TABLES where TABLE_NAME = 't_task';", Maps.newHashMap());
    if (CollectionUtils.isEmpty(maps)) {
      // log
      logger.info("No Create Task Table,And Create Now!");
      namedParameterJdbcTemplate.update(T_TASK_CREATE_SQL, Maps.newHashMap());
      maps = namedParameterJdbcTemplate.queryForList("select * from information_schema.TABLES where TABLE_NAME = 't_task';", Maps.newHashMap());
      if (CollectionUtils.isEmpty(maps)) {
        throw new RuntimeSqlException("There Create Task Table Failed,Check table exist failed!");
      }
    } else {
      logger.info("Task Table Already Exist!");
    }
    logger.info("Task Table Check 【SUCCESS】");
  }
}
