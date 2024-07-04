
package org.springcrazy.modules.edu.wrapper;

import org.springcrazy.common.constant.CommonConstant;
import org.springcrazy.core.mp.support.BaseEntityWrapper;
import org.springcrazy.core.tool.node.ForestNodeMerger;
import org.springcrazy.core.tool.utils.BeanUtil;
import org.springcrazy.core.tool.utils.Func;
import org.springcrazy.core.tool.utils.SpringUtil;
import org.springcrazy.modules.edu.entity.Course;
import org.springcrazy.modules.edu.entity.Subject;
import org.springcrazy.modules.edu.service.ICourseService;
import org.springcrazy.modules.edu.service.ISubjectService;
import org.springcrazy.modules.edu.vo.CourseVO;
import org.springcrazy.modules.edu.vo.SubjectVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author TongZhou
 */
public class CourseWrapper extends BaseEntityWrapper<Course, CourseVO> {

	private static ICourseService courseService;


	static {
		courseService = SpringUtil.getBean(ICourseService.class);
	}

	public static CourseWrapper build() {
		return new CourseWrapper();
	}

	@Override
	public CourseVO entityVO(Course course) {
		CourseVO courseVO = BeanUtil.copy(course, CourseVO.class);
		return courseVO;
	}



}
