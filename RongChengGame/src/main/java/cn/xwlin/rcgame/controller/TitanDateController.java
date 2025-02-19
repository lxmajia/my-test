package cn.xwlin.rcgame.controller;

import cn.xwlin.rcgame.service.crawl.Titan007Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xiang.liao
 * @create 2025/2/18
 */
@RestController
@RequestMapping("/titan/data")
public class TitanDateController {

  @Autowired
  private Titan007Service titan007Service;

  //  @RequestMapping("/yearMatch")
  public String initDate(Integer year, Integer typeId, Integer seasonId) {
    return titan007Service.getYearGameInfo(year, typeId, seasonId);
  }

  @RequestMapping("/initGameInfo")
  public List<String> initGameInfo(Integer startId, Integer endId) {
    List<String> resule = new ArrayList<>();
    for (int i = startId; i <= endId; i++) {
      String yearGameInfo = titan007Service.getYearGameInfo(i);
      resule.add(yearGameInfo);
    }
    return resule;
  }


}

