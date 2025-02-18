package cn.xwlin.rcgame.controller;

import cn.xwlin.rcgame.service.crawl.Titan007Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiang.liao
 * @create 2025/2/18
 */
@RestController
@RequestMapping("/titan/data")
public class TitanDateController {

  @Autowired
  private Titan007Service titan007Service;

  @RequestMapping("/yearMatch")
  public String initDate(Integer year, Integer typeId, Integer seasonId) {
    return titan007Service.getYearGameInfo(year, typeId, seasonId);
  }


}

