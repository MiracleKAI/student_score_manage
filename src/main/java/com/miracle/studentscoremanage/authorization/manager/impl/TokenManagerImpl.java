package com.miracle.studentscoremanage.authorization.manager.impl;

import com.miracle.studentscoremanage.authorization.manager.TokenManager;
import com.miracle.studentscoremanage.authorization.model.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @author miracle
 */
@Component
public class TokenManagerImpl implements TokenManager {
    private RedisTemplate<Long, String> redis;

    @Autowired
    @Qualifier("redisTemplate")
    public void setRedis(RedisTemplate redis) {
        this.redis = redis;

        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }


    @Override
    public TokenModel createToken(long userId) {
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);

        this.redis.boundValueOps(userId).set(token, 2L, TimeUnit.HOURS);
        return model;
    }


    @Override
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }

        long userId = Long.parseLong(param[0]);
        String token = param[1];
        return new TokenModel(userId, token);
    }


    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = this.redis.boundValueOps(model.getUserId()).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }

        this.redis.boundValueOps(model.getUserId()).expire(1L, TimeUnit.HOURS);
        return true;
    }


    @Override
    public void deleteToken(long userId) {
        this.redis.delete(userId);
    }
}
