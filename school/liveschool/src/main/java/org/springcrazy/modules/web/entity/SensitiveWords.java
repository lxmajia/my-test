
package org.springcrazy.modules.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 网站敏感词实体类
 *
 * @author TongZhou
 * @since 2020-05-09
 */
@Data
@TableName("web_sensitive_words")
@ApiModel(value = "SensitiveWords对象", description = "网站敏感词")
public class SensitiveWords implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
    /**
     * 敏感词
     */
    @ApiModelProperty(value = "敏感词")
    @TableField("sensitiveWord")
  private String sensitiveWord;


}
