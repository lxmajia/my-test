
package cn.xwlin.majiaxueyuan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 文章信息表实体类
 *
 * @author TongZhou
 * @since 2020-02-28
 */
@Data
@TableName("cms_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章简介
     */
    private String summary;
  /**
   * 文章内容
   */
  private String content;
    /**
     * 文章图片url
     */
    private String imageUrl;
    /**
     * 文章发布时间
     */
    private LocalDateTime publishTime;
    /**
     * 文章点击量
     */
    private Integer clickNum;
    /**
     * 点赞数量
     */
    private Integer praiseCount;
    /**
     * 资讯类型（lineNotice 线下课公告 lineExam线下课考试资讯 lineArticle线下课资讯 article网校资讯 webNotice首页公告）
     */
    private String type;
    /**
     * 专业分类id
     */
    private Integer subjectId;
    /**
     * 评论数
     */
    private Integer commentNum;
    /**
     * 排序值
     */
    private Integer sort;


  private Integer createUser;
  @DateTimeFormat(
          pattern = "yyyy-MM-dd HH:mm:ss"
  )
  @JsonFormat(
          pattern = "yyyy-MM-dd HH:mm:ss"
  )
  private Date createTime;
  private Integer updateUser;
  @DateTimeFormat(
          pattern = "yyyy-MM-dd HH:mm:ss"
  )
  @JsonFormat(
          pattern = "yyyy-MM-dd HH:mm:ss"
  )
  private Date updateTime;
  private Integer status;
  @TableLogic
  private Integer isDeleted;
}
