package cn.xwlin.majiaxueyuan.service.impl;

import cn.xwlin.majiaxueyuan.dao.CourseMapper;
import cn.xwlin.majiaxueyuan.entity.Course;
import cn.xwlin.majiaxueyuan.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;


/**
 * 课程表 服务实现类
 *
 * @author TongZhou
 * @since 2020-04-25
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {



	@Override
	public IPage<Course> selectCoursePage(IPage<Course> page, Course course) {
		return page.setRecords(baseMapper.selectCoursePage(page, course));
	}

}
