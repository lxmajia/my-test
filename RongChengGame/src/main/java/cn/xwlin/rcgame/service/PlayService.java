package cn.xwlin.rcgame.service;

import cn.xwlin.rcgame.dao.GamePlatEventMapper;
import cn.xwlin.rcgame.dao.GamePlayInfoMapper;
import cn.xwlin.rcgame.dao.GamePlayPlayerMapper;
import org.springframework.stereotype.Service;

/**
 * @author xiang.liao
 * @create 2023/9/1
 */
@Service
public class PlayService {
  private GamePlayInfoMapper gamePlayInfoMapper;
  private GamePlayPlayerMapper gamePlayPlayerMapper;
  private GamePlatEventMapper gamePlatEventMapper;


}
