package cn.xwlin.majiaxueyuan.service.impl;

import cn.xwlin.majiaxueyuan.dao.ArticleMapper;
import cn.xwlin.majiaxueyuan.entity.Article;
import cn.xwlin.majiaxueyuan.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文章信息表 服务实现类
 *
 * @author TongZhou
 * @since 2020-02-28
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
