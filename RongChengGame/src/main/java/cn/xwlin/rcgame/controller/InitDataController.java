package cn.xwlin.rcgame.controller;

import cn.xwlin.rcgame.service.InitDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiang.liao
 * @create 2023/9/5
 */
@RestController
@RequestMapping("/init")
public class InitDataController {

    @Autowired
    private InitDataService initDataService;

    @RequestMapping("/player")
    public String initZbbPlayerData() {
        String s = initDataService.initZbbPlayer();
        return s;
    }

    @RequestMapping("/team")
    public String initTeamData() {
        String s = initDataService.initClubInfo();
        return s;
    }

    @RequestMapping("/playInfo")
    public String initPlayInfo(Long gameKindId, Long sessionId) {
        if (gameKindId == null || sessionId == null) {
            return "参数不对";
        }
        String s = initDataService.initPlayInfo(gameKindId, sessionId);
        return s;
    }

    @RequestMapping("/playPlayerInfo")
    public String playPlayerInfo(Long gamePlayId) {
        if (gamePlayId == null) {
            return "参数不对";
        }
        return initDataService.initGamePlayerInfo(gamePlayId);
    }

    @RequestMapping("/playEvent")
    public String playEvent(Long gamePlayId) {
        if (gamePlayId == null) {
            return "参数不对";
        }
        return initDataService.initPlayEvent(gamePlayId);
    }
}
