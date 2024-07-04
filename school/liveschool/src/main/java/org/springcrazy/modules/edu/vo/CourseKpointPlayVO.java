
package org.springcrazy.modules.edu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springcrazy.core.tool.node.INode;
import org.springcrazy.modules.edu.entity.CourseKpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 知识点的基本信息视图实体类
 *
 * @author TongZhou
 * @since 2020-04-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CourseKpointVO对象", description = "知识点的基本信息")
public class CourseKpointPlayVO extends CourseKpoint  {
	private static final long serialVersionUID = 1L;

	private Map<String,Object> params;

	/** 进度条锁定状态 1 开 2 关 **/
	private String playProgress;

	/** 倍速开关 1 开 2 关 **/
	private String rateComponent;
}
