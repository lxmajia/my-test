
package org.springcrazy.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springcrazy.core.tool.utils.Func;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author TongZhou
 */
@Data
@TableName("crazy_menu")
@ApiModel(value = "Menu对象", description = "Menu对象")
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 菜单父主键
	 */
	@ApiModelProperty(value = "菜单父主键")
	private Integer parentId;

	/**
	 * 菜单编号
	 */
	@ApiModelProperty(value = "菜单编号")
	private String code;

	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value = "菜单名称")
	private String name;

	/**
	 * 菜单别名
	 */
	@ApiModelProperty(value = "菜单别名")
	private String alias;

	/**
	 * 请求地址
	 */
	@ApiModelProperty(value = "请求地址")
	private String path;

	/**
	 * 菜单资源
	 */
	@ApiModelProperty(value = "菜单资源")
	private String source;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;

	/**
	 * 是否隐藏
	 */
	@ApiModelProperty(value = "是否隐藏")
	private Integer hidden;

	/**
	 * 菜单类型
	 */
	@ApiModelProperty(value = "菜单类型")
	private Integer category;

	/**
	 * 操作按钮类型
	 */
	@ApiModelProperty(value = "操作按钮类型")
	private Integer action;

	/**
	 * 是否打开新页面
	 */
	@ApiModelProperty(value = "是否打开新页面")
	private Integer isOpen;

	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;

	/**
	 * 是否已删除
	 */
	@TableLogic
	@ApiModelProperty(value = "是否已删除")
	private Integer isDeleted;


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		Menu other = (Menu) obj;
		if (Func.equals(this.getId(), other.getId())) {
			return true;
		}
		return false;
	}

}
