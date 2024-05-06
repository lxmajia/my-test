package cn.xwlin.tio;

import cn.xwlin.tio.task.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName TaskConfiguration.java
 * @createTime 2022-12-8-0008
 */
@Configuration
public class TaskConfiguration {


    @Bean(initMethod = "start")
    public TaskScheduleStarter myTaskChannel(Set<TaskService> taskServices) {
        TaskDispatcherHandler taskDispatcherHandler = new TaskDispatcherHandler();
        TaskScheduleStarter channel = new TaskScheduleStarter("xwl", taskDispatcherHandler, taskServices);
        return channel;
    }


}
