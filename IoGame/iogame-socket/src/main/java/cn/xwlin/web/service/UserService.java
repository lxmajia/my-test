package cn.xwlin.web.service;

import cn.xwlin.web.entity.GameUser;
import cn.xwlin.web.mapper.GameUserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private GameUserMapper gameUserMapper;

    public String login(String accountName, String password) {
        GameUser gameUser = gameUserMapper.selectAccountName(accountName);
        if (gameUser.getPassword().equals(password)) {
            String token = UUID.randomUUID().toString();
            stringRedisTemplate.opsForValue().set(token, String.valueOf(gameUser.getId()));
            return token;
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
}
