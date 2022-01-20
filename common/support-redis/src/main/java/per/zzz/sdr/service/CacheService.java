package per.zzz.sdr.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author 阿忠 2669918628@qq.com
 * 2021/11/4 14:41
 */
public interface CacheService {

    boolean set(String key,String value);

    boolean setWithExpire(String key,String value,Integer second);

    boolean setWithPrefix(String prefix,String key,String value);

    boolean setUseJson(String key,Object object);

    String get(String key);

    String getWithPrefix(String prefix,String key);

    <T> T getFromJson(String key,Class<T> tClass);

    Set<String> getKeys(String key);

    boolean lPush(String name, Object object);

    boolean rPush(String name, Object object);

    <T> T lIndex(String key,Long index,Class<T> tClass);

    boolean lRem(String key,String val);

    <T> List<T> getList(String name, Class<T> tClass);

    boolean sAdd(String key, Object object);

    <T> List<T> sMembers(String key, Class<T> tClass);

    Set<String> sMembers(String key);

    boolean del(String var1);

    boolean sRem(String key,String... vals);

    boolean hSet(String key, String filed, String val);

    String hGet(String key, String filed);

    boolean hDel(String prefix, String key);

    Set<String> hKeys(String key);

    boolean hIncrBy(String key, String filed, long score);

    Long lLen(String key);

    <T> T lPop(String key, Class<T> tClass);

    <T> List<T> hVals(String key, Class<T> tClass);

    Boolean setByte(String key,byte[] bytes);

    byte[] getByte(String key);

    Boolean setNx(String key, String val, Long time, TimeUnit timeUnit);
}
