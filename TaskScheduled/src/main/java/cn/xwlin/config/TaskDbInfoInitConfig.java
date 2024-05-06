package cn.xwlin.config;

import cn.xwlin.repo.TaskRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

import javax.sql.DataSource;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName MyConfig.java
 * @createTime 2023-3-2-0002
 */
@Configuration
@Order(5)
public class TaskDbInfoInitConfig {


  @Bean(name = "namedParameterJdbcTemplate")
  public NamedParameterJdbcTemplate userRepository(ObjectProvider<DataSource> dataSource) {
    System.out.println("NamedParameterJdbcTemplate bean");
    DataSource ifAvailable = dataSource.getIfAvailable();
    Assert.isTrue(ifAvailable != null, "No DataSource Found!");
    return new NamedParameterJdbcTemplate(ifAvailable);
  }

  @Bean(name = "taskRepository", initMethod = "init")
  public TaskRepository taskRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    System.out.println("UserRepository bean");
    return new TaskRepository(namedParameterJdbcTemplate);
  }

}
