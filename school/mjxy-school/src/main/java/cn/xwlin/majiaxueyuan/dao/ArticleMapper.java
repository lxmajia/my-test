package cn.xwlin.majiaxueyuan.dao;

import cn.xwlin.majiaxueyuan.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 文章信息表 Mapper 接口
 *
 * @author TongZhou
 * @since 2020-02-28
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
