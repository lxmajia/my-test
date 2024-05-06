package cn.xwlin.web;

import cn.xwlin.entity.Cavans;
import cn.xwlin.entity.Dict;
import cn.xwlin.mapper.CavansRepository;
import org.apache.el.lang.ExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiang.liao
 * @create 2023/8/31
 */
@RestController
public class CavansController {

  @Autowired
  private CavansRepository cavansRepository;

  @RequestMapping("list")
  public List<Cavans> list() {
    return cavansRepository.findAll();
  }

  private <R> Predicate test(Root<R> root, List<String> ids, CriteriaBuilder criteriaBuilder){
    Path<Integer> path = root.get("id");  //定义查询的字段
    CriteriaBuilder.In<Integer> in = criteriaBuilder.in(path);
    Predicate like1 = criteriaBuilder.like(root.get("cavansNme"), "%123%");
    CriteriaQuery<Object> query1 = criteriaBuilder.createQuery();

    CriteriaQuery<Cavans> query = criteriaBuilder.createQuery(Cavans.class);
    Predicate like = criteriaBuilder.like(root.get("123"), "%asd%");

    return null;
  }
}
