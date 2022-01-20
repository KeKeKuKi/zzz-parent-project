package per.zzz.sdr.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.*;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *
 *
 * @author 阿忠 2669918628@qq.com
 * 2021/11/4 14:41
 */
@Slf4j
public class RedisTemplateCacheServiceImpl implements CacheService, InitializingBean {
    String HEAR_BEAT = "HEAR_BEAT";

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(this.redisTemplate, "StringRedisTemplate is need!!!");
        set(HEAR_BEAT, String.valueOf(System.currentTimeMillis()));
        log.info("######################################-Redis service initialized");
    }

    private final StringRedisTemplate redisTemplate;

    public RedisTemplateCacheServiceImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean set(String key, String value) {
        return doAction("set",
                () -> redisTemplate.opsForValue().set(getString(key), getString(value)), key, value);
    }

    @Override
    public Boolean setNx(String key, String val, Long time, TimeUnit timeUnit) {
        return doAction("setNx",
                () -> redisTemplate.opsForValue().setIfAbsent(getString(key), getString(val), time, timeUnit), key, val);
    }

    @Override
    public boolean setWithExpire(String key, String value, Integer second) {
        return doAction("setWithExpire",
                () -> redisTemplate.opsForValue().set(getString(key), getString(value), second, TimeUnit.SECONDS),
                key, value, second);
    }

    @Override
    public boolean setWithPrefix(String prefix, String key, String value) {
        return doAction("setWithPrefix",
                () -> redisTemplate.opsForHash().put(getString(prefix), getString(key), getString(value)),
                key, value, value);
    }

    @Override
    public boolean setUseJson(String key, Object object) {
        return doAction("setUseJson", () -> {
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            if (object != null) {
                ops.set(getString(key), JSON.toJSONString(object));
            } else {
                redisTemplate.delete(getString(key));
            }
        }, key, object);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(getString(key));
    }

    @Override
    public String getWithPrefix(String prefix, String key) {
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        return hash.get(getString(prefix), getString(key));
    }

    @Override
    public <T> T getFromJson(String key, Class<T> tClass) {
        String value = redisTemplate.opsForValue().get(getString(key));
        if (!StringUtils.isEmpty(value)) {
            return JSON.parseObject(value, tClass);
        }
        return null;
    }

    @Override
    public Set<String> getKeys(String key) {
        return redisTemplate.keys(getString(key));
    }

    @Override
    public boolean lPush(String name, Object object) {
        return doAction("lPush", () -> {
            ListOperations<String, String> list = redisTemplate.opsForList();
            if (object != null) {
                list.leftPush(getString(name), JSON.toJSONString(object));
            }
        }, name, object);
    }

    @Override
    public boolean rPush(String name, Object object) {
        return doAction("rPush", () -> {
            ListOperations<String, String> list = redisTemplate.opsForList();
            if (object != null) {
                list.rightPush(getString(name), JSON.toJSONString(object));
            }
        }, name, object);
    }

    @Override
    public boolean lRem(String key, String val) {
        return doAction("lRem", () -> {
            ListOperations<String, String> list = redisTemplate.opsForList();
            list.remove(key, -1, val);
        }, key, val);
    }

    @Override
    public <T> T lIndex(String key, Long index, Class<T> tClass) {
        String objStr = redisTemplate.opsForList().index(key, index);
        if (!StringUtils.isEmpty(objStr)) {
            return JSON.parseObject(objStr, tClass);
        }
        return null;
    }

    @Override
    public <T> List<T> getList(String name, Class<T> tClass) {
        ListOperations<String, String> list = redisTemplate.opsForList();
        List<String> range = list.range(getString(name), 0, -1);
        if (null != range) {
            return range.stream().map(val -> JSON.parseObject(val, tClass)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public boolean sAdd(String key, Object object) {
        return doAction("sAdd", () -> {
            if (object != null) {
                redisTemplate.opsForSet().add(getString(key), JSON.toJSONString(object));
            }
        });
    }

    @Override
    public <T> List<T> sMembers(String key, Class<T> tClass) {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        Set<String> members = set.members(getString(key));
        if (members != null) {
            return members.stream().map(m -> JSON.parseObject(m, tClass)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Set<String> sMembers(String key) {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        return set.members(getString(key));
    }

    @Override
    public boolean del(String var1) {
        return doAction("del", () -> redisTemplate.delete(getString(var1)), var1);
    }

    @Override
    public boolean sRem(String key, String... vals) {
        return doAction("sRem", () -> {
            redisTemplate.multi();
            SetOperations<String, String> set = redisTemplate.opsForSet();
            for (String val : vals) {
                set.remove(getString(key), val);
            }
            redisTemplate.exec();
        }, key, vals);
    }

    @Override
    public boolean hSet(String key, String filed, String val) {
        return doAction("hSet", () -> {
            HashOperations<String, String, String> hash = redisTemplate.opsForHash();
            hash.put(getString(key), getString(filed), getString(val));
        }, key, filed, val);
    }

    @Override
    public String hGet(String key, String filed) {
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        return hash.get(getString(key), getString(filed));
    }

    @Override
    public boolean hDel(String prefix, String key) {
        return doAction("hDel", () -> {
            HashOperations<String, String, String> hash = redisTemplate.opsForHash();
            hash.delete(getString(prefix), getString(key));
        }, prefix, key);
    }

    @Override
    public Set<String> hKeys(String key) {
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        return hash.keys(getString(key));
    }

    @Override
    public boolean hIncrBy(String key, String filed, long score) {
        return doAction("hIncrBy", () -> {
            HashOperations<String, String, String> hash = redisTemplate.opsForHash();
            hash.increment(getString(key), getString(filed), score);
        }, key, filed, score);
    }

    @Override
    public Long lLen(String key) {
        return redisTemplate.boundListOps(key).size();
    }

    @Override
    public <T> T lPop(String key, Class<T> tClass) {
        String s = redisTemplate.boundListOps(key).leftPop();
        if (null != s) {
            return JSONObject.parseObject(s, tClass);
        }
        return null;
    }

    @Override
    public <T> List<T> hVals(String key, Class<T> tClass) {
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        List<String> values = hash.values(getString(key));
        return values.stream().map(x -> JSON.parseObject(x, tClass)).collect(Collectors.toList());
    }

    @Override
    public Boolean setByte(String key, byte[] bytes) {
        return redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> redisConnection.set(getString(key).getBytes(StandardCharsets.UTF_8), bytes));
    }

    @Override
    public byte[] getByte(String key) {
        return redisTemplate.execute((RedisCallback<byte[]>) redisConnection -> redisConnection.get(getString(key).getBytes(StandardCharsets.UTF_8)));
    }

    private String getString(String val) {
        return val == null ? "" : val;
    }

    private boolean doAction(String reason, Action action, Object... params) {
        try {
            action.call();
            return true;
        } catch (Exception e) {
            log.error("doAction error:{}, {}", reason, Arrays.toString(params), e);
        }
        return false;
    }

    interface Action {
        void call();
    }
}
