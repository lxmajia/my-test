package cn.xwlin.web.service;

import cn.xwlin.common.action.CacheKeyConst;
import cn.xwlin.web.entity.GameUser;
import cn.xwlin.web.mapper.GameUserMapper;
import cn.xwlin.web.vo.LoginInfo;
import com.iohao.game.bolt.broker.client.kit.ExternalCommunicationKit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private GameUserMapper gameUserMapper;

    private static final Long TOKEN_VALID_MINUTE = 60L;

    public LoginInfo login(String accountName, String password) {
        GameUser gameUser = gameUserMapper.selectAccountName(accountName);
        if (gameUser == null) {
            return null;
        }
        if (gameUser.getPassword().equals(password)) {
            String token = UUID.randomUUID().toString();
            stringRedisTemplate.opsForValue().set(token, String.valueOf(gameUser.getId()), TOKEN_VALID_MINUTE, TimeUnit.MINUTES);
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setToken(token);
            loginInfo.setUserId(gameUser.getId());

            return loginInfo;
        }
        return null;
    }

    public Long userIdByToken(String token) {
        System.out.println("userIdByToken:" + token);
        if (StringUtils.isBlank(token)) {
            return null;
        }
        String accountIdStr = stringRedisTemplate.opsForValue().get(token);
        if (StringUtils.isBlank(accountIdStr)) {
            return null;
        }
        try {
            return Long.parseLong(accountIdStr);
        } catch (Exception e) {
            return null;
        }
    }

    public void logout(String token, Long userId) {
        stringRedisTemplate.opsForValue().getAndDelete(token);
        String positionCacheKey = CacheKeyConst.playPositionCacheKey(1L, 1L, 1L);
        stringRedisTemplate.opsForHash().delete(positionCacheKey, String.valueOf(userId));
    }

}
