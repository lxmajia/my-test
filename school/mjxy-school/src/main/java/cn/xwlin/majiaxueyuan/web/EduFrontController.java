package cn.xwlin.majiaxueyuan.web;

import cn.hutool.core.util.ObjectUtil;
import cn.xwlin.majiaxueyuan.entity.Article;
import cn.xwlin.majiaxueyuan.entity.Course;
import cn.xwlin.majiaxueyuan.entity.Subject;
import cn.xwlin.majiaxueyuan.entity.WebsiteNavigate;
import cn.xwlin.majiaxueyuan.service.IArticleService;
import cn.xwlin.majiaxueyuan.service.ICourseService;
import cn.xwlin.majiaxueyuan.service.ISubjectService;
import cn.xwlin.majiaxueyuan.service.IWebsiteNavigateService;
import cn.xwlin.majiaxueyuan.vo.Query;
import cn.xwlin.majiaxueyuan.vo.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiang.liao
 * @create 2024/7/10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/front")
public class EduFrontController {

  private IWebsiteNavigateService websiteNavigateService;
  private ICourseService courseService;
  private ISubjectService subjectService;
  private IArticleService articleService;

  /**
   * 查询尾部链接与 首部导航条 尾部标签
   * 1首页、3尾部友链、4尾部标签
   */
  @GetMapping("/websiteNavigate/list")
  public R<List<WebsiteNavigate>> websiteNavigate(String type) {
    WebsiteNavigate websiteNavigate = new WebsiteNavigate();
    websiteNavigate.setType(type);
    websiteNavigate.setStatus("2");
    List<WebsiteNavigate> list = websiteNavigateService.list(new QueryWrapper<WebsiteNavigate>(websiteNavigate).lambda().orderByDesc(WebsiteNavigate::getSort));
    return R.data(list);
  }


  /**
   * 查询课程
   */
  @GetMapping("/course/list")
  public R<IPage<Course>> course(String courseName, Integer subjectId, String orderBy, Query query) {
    Course course = new Course();
    List<Integer> idList = Lists.newArrayList();
    //当为第一层的时候查询所有第二层
    if(subjectId != null){
      Subject subject = new Subject();
      subject.setParentId(subjectId);
      List<Subject> list = subjectService.list(new QueryWrapper<>(subject));
      if(list.size() == 0){
        idList.add(subjectId);
      }else{
        idList = list.stream().map(t -> t.getId()).collect(Collectors.toList());
      }
    }
    QueryWrapper<Course> queryWrapper = new QueryWrapper<Course>(course)
            .in(idList.size()>0,"subject_id",idList);
    //默认排序
    if (ObjectUtil.isEmpty(orderBy) || "default".equals(orderBy)) {
      queryWrapper.orderByDesc("sort DESC,create_time");
    }
    //最新排序
    if ("update".equals(orderBy)) {
      queryWrapper.orderByDesc("create_time");
    }
    //只查询上架商品
    queryWrapper.lambda().eq(Course::getIsAvaliable, "1").like(StringUtils.hasLength(courseName), Course::getCourseName, courseName);
    //免费课程
    if ("free".equals(orderBy)) {
      queryWrapper.orderByDesc("sort DESC,create_time");
      queryWrapper.lambda().eq(Course::getCurrentPrice, "0");
    }
    Page<Course> page = new Page(query.getCurrent(), (long) query.getSize());
    IPage<Course> pages = courseService.page(page, queryWrapper);
    return R.data(pages);
  }


  @GetMapping("/article/list")
  public R<IPage<Article>> article(Integer subjectId, Query query) {
    Article article = new Article();
    article.setSubjectId(subjectId);
    Page<Article> page = new Page(query.getCurrent(), (long) query.getSize());
    QueryWrapper<Article> queryWrapper = new QueryWrapper(article);
    queryWrapper.orderByDesc("sort desc,id");
    IPage<Article> pages = articleService.page(page, queryWrapper);
    return R.data(pages);
  }

  @GetMapping("/article/hotList")
  public R<IPage<Article>> hotArticle(Integer subjectId, Query query) {
    Article article = new Article();
    article.setSubjectId(subjectId);


    Page<Article> page = new Page(query.getCurrent(), (long) query.getSize());
    QueryWrapper<Article> queryWrapper = new QueryWrapper(article);
    queryWrapper.orderByDesc("click_num");

    IPage<Article> pages = articleService.page(page, queryWrapper);
    return R.data(pages);
  }
}
