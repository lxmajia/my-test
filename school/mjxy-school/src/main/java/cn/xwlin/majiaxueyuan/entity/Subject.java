
package cn.xwlin.majiaxueyuan.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 专业分类实体类
 *
 * @author TongZhou
 * @since 2020-04-25
 */
@Data
@TableName("edu_subject")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
    /**
     * 专业名称
     */
    private String subjectName;
    /**
     * 状态 1:正常 2:删除
     */
    @TableLogic
    private Boolean status;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 父id
     */
    private Integer parentId;
    /**
     * 排序字段
     */
    private Integer sort;
    /**
     * 专业类型 course 课程
     */
    private String type;


}
