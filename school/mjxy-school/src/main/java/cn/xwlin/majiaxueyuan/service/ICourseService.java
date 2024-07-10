package cn.xwlin.majiaxueyuan.service;

import cn.xwlin.majiaxueyuan.entity.Course;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 课程表 服务类
 *
 * @author TongZhou
 * @since 2020-04-25
 */
public interface ICourseService extends IService<Course> {

	/**
	 * 自定义分页
	 */
	IPage<Course> selectCoursePage(IPage<Course> page, Course course);
}
