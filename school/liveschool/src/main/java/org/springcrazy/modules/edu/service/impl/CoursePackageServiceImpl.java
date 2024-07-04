
package org.springcrazy.modules.edu.service.impl;

import org.springcrazy.modules.edu.entity.CoursePackage;
import org.springcrazy.modules.edu.vo.CoursePackageVO;
import org.springcrazy.modules.edu.mapper.CoursePackageMapper;
import org.springcrazy.modules.edu.service.ICoursePackageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 *  服务实现类
 *
 * @author TongZhou
 * @since 2020-05-05
 */
@Service
public class CoursePackageServiceImpl extends ServiceImpl<CoursePackageMapper, CoursePackage> implements ICoursePackageService {

	@Override
	public IPage<CoursePackageVO> selectCoursePackagePage(IPage<CoursePackageVO> page, CoursePackageVO coursePackage) {
		return page.setRecords(baseMapper.selectCoursePackagePage(page, coursePackage));
	}

	@Override
	public List<CoursePackageVO> selectCoursePackageList(CoursePackageVO coursePackage) {
		return baseMapper.selectCoursePackageList(coursePackage);
	}

}
