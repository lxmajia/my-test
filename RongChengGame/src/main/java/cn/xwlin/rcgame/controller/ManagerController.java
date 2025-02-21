package cn.xwlin.rcgame.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.xwlin.rcgame.controller.request.LoginReq;
import cn.xwlin.rcgame.controller.response.HttpResp;
import cn.xwlin.rcgame.controller.response.LoginResp;
import cn.xwlin.rcgame.controller.response.ToDayGame;
import cn.xwlin.rcgame.service.OperateGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xiang.liao
 * @create 2024/5/6
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {

  @Autowired
  private OperateGameService operateGameService;

  @RequestMapping("/login")
  @SaIgnore
  public HttpResp<LoginResp> login(@RequestBody LoginReq loginReq) {
    if ("liaoxiang".equals(loginReq.getUsername()) && "123456.".equals(loginReq.getPassword())) {
      // 第1步，先登录上
      StpUtil.login(1L);
      SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
      LoginResp loginResp = new LoginResp();
      loginResp.setNickName("LxLxLx");
      loginResp.setToken(tokenInfo.getTokenValue());
      return HttpResp.success(loginResp);
    }
    return HttpResp.fail(401, "登录失败");
  }

  @RequestMapping("/todayGame")
  public HttpResp<ToDayGame> todayGame() {
    return operateGameService.todayGame();
  }

  @RequestMapping("/setFirstPlayer")
  public HttpResp setFirstPlayer(Integer gameId, String firstPlayer) {
    return operateGameService.setFirstPlayer(gameId, firstPlayer);
  }

  @RequestMapping("/setSubPlayer")
  public HttpResp setSubPlayer(Integer gameId, String subPlayer) {
    return operateGameService.setSubPlayer(gameId, subPlayer);
  }

  @RequestMapping("/startGame")
  public HttpResp startGame(Integer gameId) {
    return operateGameService.startGame(gameId);
  }

  @RequestMapping("/endGame")
  public HttpResp endGame(Integer gameId) {
    return operateGameService.endGame(gameId);
  }

  @RequestMapping("/event")
  public HttpResp event(Integer gameId, Integer gamePlayerId, Integer eventType, Integer minute, Integer minuteAdd) {
    return operateGameService.event(gameId, gamePlayerId, eventType, minute, minuteAdd);
  }
}
