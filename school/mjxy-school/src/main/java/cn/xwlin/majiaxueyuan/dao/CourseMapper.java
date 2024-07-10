
package cn.xwlin.majiaxueyuan.dao;

import cn.xwlin.majiaxueyuan.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程表 Mapper 接口
 *
 * @author TongZhou
 * @since 2020-04-25
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param course
	 * @return
	 */
	List<Course> selectCoursePage(IPage page, Course course);

	Course getCourseById(@Param("courseId") int courseId);
}
