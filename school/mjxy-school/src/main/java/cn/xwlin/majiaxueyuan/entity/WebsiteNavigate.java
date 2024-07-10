
package cn.xwlin.majiaxueyuan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 导航表实体类
 *
 * @author TongZhou
 * @since 2020-05-09
 */
@Data
@TableName("web_website_navigate")
public class WebsiteNavigate implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  private Integer id;
  /**
   * 名称
   */
  private String name;
  /**
   * url地址
   */
  private String url;
  /**
   * 是否在新页面打开0是1否
   */
  private Integer newpage;
  /**
   * 类型：index首页、user个人中心、friendlink尾部友链、tab尾部标签
   */
  private String type;
  /**
   * 手机端脚步导航图片
   */
  private String image;
  /**
   * 显示排序
   */
  private Integer sort;
  /**
   * 0正常1冻结
   */
  private String status;


}
