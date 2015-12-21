package com.zefun.web.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.Pool;

import com.zefun.common.consts.App;
import com.zefun.common.utils.DateUtil;

/**
 * 封装Jedis操作
 * @author sfit0254
 * @date 2014-4-15
 */
@Service
public class RedisService {
    /** redis连接池对象 */
    @Autowired 
    private Pool<Jedis> jedisPool;
    
    /** 日志记录对象 */
    private Logger logger = Logger.getLogger(RedisService.class);
    
    
    /**
     * 获取redis连接
    * @author 张进军
    * @date Sep 24, 2015 3:20:43 PM
    * @return                   连接对象
    * @throws JedisException    获取连接发生的异常
     */
    private Jedis getJedis() throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        }
        catch (JedisException e) {
            logger.error("getJedis error : jedisPool getResource.", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            throw e;
        }
        return jedis;
    }

    
    /**
     * 释放连接
    * @author 张进军
    * @date Sep 24, 2015 3:22:06 PM
    * @param jedis      redis连接
    * @param isBroken   是否强制中断
     */
    protected void release(Jedis jedis, boolean isBroken) {
        if (jedis != null) {
            if (isBroken) {
                jedisPool.returnBrokenResource(jedis);
            }
            else {
                jedisPool.returnResource(jedis);
            }
        }
    }
    
    
    /**
     * 将一个key重命名，将key重命名为newkey，如果key与newkey相同，将返回一个错误。如果newkey已经存在，则值将被覆盖。
    * @author 张进军
    * @date Sep 24, 2015 4:07:40 PM
    * @param oldkey     需要重命名的键
    * @param newkey     重命名后的键
     */
    public void rename(String oldkey, String newkey) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.rename(oldkey, newkey);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
    }
    
    
    /**
     * 将key和value对应。如果key已经存在了，它会被覆盖，而不管它是什么类型。
    * @author 张进军
    * @date Sep 24, 2015 3:22:48 PM
    * @param key        键  
    * @param value      值
     */
    public void set(Object key, Object value) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.set(key.toString(), value.toString());
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
    }
    
    
    /**
     * 乐观锁
    * @author 王大爷
    * @date 2015年9月18日 下午7:58:52
    * @param key 贱
    * @param storeId 门店标识
    * @param field field
    * @return String
     */
    public String watchSet(String key, Integer storeId, String field) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.watch(App.Redis.GET_ORDER_CODE + String.valueOf(storeId));
            String numString = jedis.hget(App.Redis.GET_ORDER_CODE + String.valueOf(storeId), field);
            String values = null;
            int a = 0;
            if ("order_info".equals(field)) {
                if (!numString.substring(0, 8).equals(DateUtil.getCurDateString())) {
                    values = DateUtil.getCurDateString() + "0001";
                    a = 1;
                }
                else {
                    values = String.valueOf(Long.parseLong(numString) + 1);
                }
            }
            else {
                values = String.valueOf(Long.parseLong(numString) + 1);
            }
            Transaction tx = jedis.multi();
            Response<Long> retObj = tx.hset(key, field, values);
            tx.exec();
            retObj.get();
            if (a == 1) {
                //初始化订单明细编码
                jedis.hset(App.Redis.GET_ORDER_CODE + String.valueOf(storeId), "order_detail", "0");
            }
            return values;
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return null;
    }
    
    /**
     * 设置key-value并设置过期时间（单位：秒），设置key对应字符串value，并且设置key在给定的seconds时间之后超时过期
    * @author 张进军
    * @date Sep 24, 2015 4:05:37 PM
    * @param key        键
    * @param value      值
    * @param seconds    过期秒数
     */
    public void setex(Object key, Object value, int seconds) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.setex(key.toString(), seconds, value.toString());
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
    }

    
    /**
     * 设置的一个关键的价值，只有当该键不存在
    * @author 张进军
    * @date Sep 24, 2015 4:06:40 PM
    * @param key        键
    * @param value      值
    * @return           如果不存在，插入成功返回true；如果存在，插入失败，返回false
     */
    public boolean setnx(String key, String value) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.setnx(key, value) == 1;
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return false;
    }
    
    
    /**
     * 执行原子增加一个整数
    * @author 张进军
    * @date Sep 24, 2015 4:19:51 PM
    * @param key        键
    * @param increment  增加的数值
    * @return           增加之后的数值
     */
    public long incrBy(Object key, int increment) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.incrBy(key.toString(), increment);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return 0;
    }

    
    /**
     * 返回key的value。如果key不存在，返回特殊值nil。如果key的value不是string，就返回错误，因为GET只处理string类型的values。
    * @author 张进军
    * @date Sep 24, 2015 3:25:19 PM
    * @param key    键
    * @return       对应值
     */
    public String get(Object key) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.get(key.toString());
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return null;
    }

    
    /**
     * 删除一个key，如果删除的key不存在，则直接忽略。
    * @author 张进军
    * @date Sep 24, 2015 3:26:52 PM
    * @param key    键
     */
    public void del(String key) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.del(key);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
    }
    
    
    /**
     * 查询一个key是否存在
    * @author 张进军
    * @date Sep 24, 2015 3:27:45 PM
    * @param key    键
    * @return       如果key存在返回true;如果key不存在返回false
     */
    public boolean exists(Object key) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.exists(key.toString());
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return false;
    }

    
    /**
     * 设置一个key的过期的秒数
    * @author 张进军
    * @date Sep 24, 2015 3:29:11 PM
    * @param key        键
    * @param seconds    过期的秒数
     */
    public void expire(Object key, int seconds) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.expire(key.toString(), seconds);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
    }

    
    /**
     * 设置一个UNIX时间戳的过期时间.
     * EXPIREAT 的作用和 EXPIRE类似，都用于为 key 设置生存时间。不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳 Unix timestamp 
    * @author 张进军
    * @date Sep 24, 2015 3:30:28 PM
    * @param key        键
    * @param unixTime   UNIX 时间戳，如：1293840000
     */
    public void expireAt(Object key, long unixTime) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.expireAt(key.toString(), unixTime);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
    }

    
    /**
     * 设置hash里面一个字段的值。设置 key 指定的哈希集中指定字段的值。如果 key 指定的哈希集不存在，会创建一个新的哈希集并与 key 关联。如果字段在哈希集中存在，它将被重写。
    * @author 张进军
    * @date Sep 24, 2015 3:33:00 PM
    * @param key        hash键
    * @param field      字段键
    * @param value      字段值
     */
    public void hset(Object key, Object field, Object value) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.hset(key.toString(), field.toString(), value.toString());
        }
        catch (Exception e) {
            isBroken = true;
            logger.error("hset " + key + " " + field + " " + value, e);
        }
        finally {
            release(jedis, isBroken);
        }
    }
    
    
    /**
     * 设置 key 指定的哈希集中指定字段的值。该命令将重写所有在哈希集中存在的字段。
     * 如果 key 指定的哈希集不存在，会创建一个新的哈希集并与 key 关联
    * @author 张进军
    * @date Sep 24, 2015 4:10:49 PM
    * @param key        hash键
    * @param hash       多个对应的hash
     */
    public void hmset(Object key, Map<String, String> hash) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.hmset(key.toString(), hash);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
    }
    
    
    /**
     * 设置hash的一个字段，只有当这个字段不存在时有效。
     * 只在 key 指定的哈希集中不存在指定的字段时，设置字段的值。
     * 如果 key 指定的哈希集不存在，会创建一个新的哈希集并与 key 关联。如果字段已存在，该操作无效果。
    * @author 张进军
    * @date Sep 24, 2015 3:56:04 PM
    * @param key        hash键
    * @param field      字段键
    * @param value      字段值
    * @return           如果字段不存在，插入成功返回true；如果字段存在，插入失败返回false
     */
    public boolean hsetnx(Object key, Object field, Object value) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.hsetnx(key.toString(), field.toString(), value.toString()) == 1;
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return false;
    }
    
    
    /**
     * 将哈希集中指定域的值增加给定的数字。
     * 增加 key 指定的哈希集中指定字段的数值。
     * 如果 key 不存在，会创建一个新的哈希集并与 key 关联。
     * 如果字段不存在，则字段的值在该操作执行前被设置为 0
    * @author 张进军
    * @date Sep 24, 2015 4:12:04 PM
    * @param key        hash键
    * @param field      字段键
    * @param increment  需要增加的数值    
    * @return           增值操作执行后的该字段的值
     */
    public long hincrBy(Object key, Object field, long increment) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.hincrBy(key.toString(), field.toString(), increment);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return 0l;
    }
    
    
    /**
     * 检查字段键是否是指定的哈希集中存在的字段。
    * @author 张进军
    * @date Sep 24, 2015 3:58:54 PM
    * @param key        hash键
    * @param field      字段键
    * @return           存在返回true；不存在返回false
     */
    public boolean hexists(String key, String field) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.hexists(key, field);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return false;
    }

    
    /**
     * 读取哈希域的的值
    * @author 张进军
    * @date Sep 24, 2015 3:34:29 PM
    * @param key        hash键
    * @param field      字段键
    * @return           指定的哈希集中该字段所关联的值
     */
    public String hget(Object key, Object field) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.hget(key.toString(), field.toString());
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return null;
    }
    
    
    /**
     * 获取hash键下多个字段键的值。
     * 对于哈希集中不存在的每个字段，返回 nil 值。
     * 因为不存在的keys被认为是一个空的哈希集，对一个不存在的 key 执行 HMGET 将返回一个只含有 nil 值的列表
    * @author 张进军
    * @date Sep 24, 2015 4:15:41 PM
    * @param key        hash键
    * @param fields     字段值
    * @return           含有给定字段及其值的列表，并保持与请求相同的顺序
     */
    public List<String> hmget(String key, String... fields) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.hmget(key, fields);
        }
        catch (Exception e) {
            isBroken = true;
            return null;
        }
        finally {
            release(jedis, isBroken);
        }
    }
    
    
    /**
     * 获取指定的哈希集中所有字段的名字
    * @author 张进军
    * @date Sep 24, 2015 4:16:40 PM
    * @param key        hash键
    * @return           所有hash下的字段键的集合
     */
    public Set<String> hkeys(String key) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.hkeys(key);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return null;
    }


    /**
     * 删除一个哈希域。从 key 指定的哈希集中移除指定的域。在哈希集中不存在的域将被忽略。如果 key 指定的哈希集不存在，它将被认为是一个空的哈希集，该命令将返回0。
    * @author 张进军
    * @date Sep 24, 2015 3:35:27 PM
    * @param key        hash键
    * @param field      字段键
     */
    public void hdel(String key, Object field) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.hdel(key, field.toString());
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
    }

    /**
     * 返回哈希表 key 中域的数量。
    * @author 老王
    * @date 2015年6月17日 下午2:45:47
    * @param key 键
    * @return 哈希表中域的数量
     */
    public Long hlen(String key) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.hlen(key);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return null;
    }
    
    
    
    /**
     * 读取哈希集中所有的字段和值
    * @author 张进军
    * @date Sep 24, 2015 3:36:31 PM
    * @param key        hash键
    * @return           哈希集中字段和值的列表，当指定的哈希集不存在时返回空列表
     */
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.hgetAll(key);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return null;
    }

    
    /**
     * 添加一个或多个元素到集合(set)里
    * @author 张进军
    * @date Sep 24, 2015 3:37:28 PM
    * @param key        set键    
    * @param member     需要添加的成员
     */
    public void sadd(Object key, Object... member) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.sadd(key.toString(), member.toString());
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
    }
  
    /**
     * 添加一个或多个元素到集合(set)里
    * @author 高国藩
    * @date Sep 24, 2015 3:37:28 PM
    * @param key        set键    
    * @param member     需要添加的成员
     */
    public void sadd(Object key, String... member) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.sadd(key.toString(), member);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
    }
    
    /**
     * 在set集合中移除指定的元素. 如果指定的元素不是key集合中的元素则忽略 如果key集合不存在则被视为一个空的集合，该命令返回0
    * @author 张进军
    * @date Sep 24, 2015 3:42:32 PM
    * @param key        set键
    * @param member     需要移除的成员
     */
    public void srem(String key, Object... member) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.srem(key, String.valueOf(member));
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
    }
    
    
    /**
     * 确定一个给定的值是一个集合的成员
    * @author 张进军
    * @date Sep 24, 2015 3:54:14 PM
    * @param key        set键
    * @param member     检查的成员
    * @return           存在返回true；不存在返回false
     */
    public boolean sismember(Object key, Object member) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.sismember(key.toString(), member.toString());
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return false;
    }
    
    
    /**
     * 获取集合中的所有成员
    * @author 张进军
    * @date Sep 24, 2015 4:33:24 PM
    * @param key        set键
    * @return           成员集合
     */
    public Set<String> smembers(Object key) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.smembers(key.toString());
        }
        catch (Exception e) {
            isBroken = true;
            return null;
        }
        finally {
            release(jedis, isBroken);
        }
    }
    
    
    /**
     * 从集合里面随机获取一个成员
    * @author 张进军
    * @date Sep 24, 2015 4:02:05 PM
    * @param key        set键
    * @return           随机一个成员
     */ 
    public String srandmember(Object key) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.srandmember(key.toString());
        }
        catch (Exception e) {
            isBroken = true;
            return null;
        }
        finally {
            release(jedis, isBroken);
        }
    }

    
    /**
     * 获取集合里面的元素数量
    * @author 张进军
    * @date Sep 24, 2015 4:02:45 PM
    * @param key        set键
    * @return           集合中的成员数量
     */
    public long scard(Object key) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.scard(key.toString());
        }
        catch (Exception e) {
            isBroken = true;
            return -1;
        }
        finally {
            release(jedis, isBroken);
        }
    }
    
    
    /**
     * 添加到有序set的一个成员，或更新的分数，如果它已经存在。<br/>
     * 该命令添加指定的成员到key对应的有序集合中，每个成员都有一个分数。<br/>
     * 你可以指定多个分数/成员组合。如果一个指定的成员已经在对应的有序集合中了，那么其分数就会被更新成最新的，并且该成员会重新调整到正确的位置，以确保集合有序。<br/>
     * 如果key不存在，就会创建一个含有这些成员的有序集合，就好像往一个空的集合中添加一样。<br/>
     * 如果key存在，但是它并不是一个有序集合，那么就返回一个错误。<br/>
     * 分数的值必须是一个表示数字的字符串，并且可以是double类型的浮点数。<br/>
    * @author 张进军
    * @date Sep 24, 2015 3:43:08 PM
    * @param key        有序set键
    * @param score      分数值
    * @param member     需要添加的成员
     */
    public void zadd(String key, Double score, Object member) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.zadd(key, score, member.toString());
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
    }

    
    /**
     * 添加到有序set的多个成员，或更新的分数，如果它已经存在
    * @author 张进军
    * @date Sep 24, 2015 3:46:09 PM
    * @param key            有序set键
    * @param scoreMembers   需要添加的多个成员集
     */
    public void zadd(Object key, Map<String, Double> scoreMembers) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.zadd(key.toString(), scoreMembers);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
    }
    
    
    /**
     * 为有序集key的成员member的score值加上增量increment。
     * 如果key中不存在member，就在key中添加一个member，score是increment（就好像它之前的score是0.0）。
     * 如果key不存在，就创建一个只含有指定member成员的有序集合
    * @author 张进军
    * @date Sep 24, 2015 4:21:48 PM
    * @param key        有序set键
    * @param member     成员
    * @param score      增加的分数值
    * @return           成员的新分数值
     */
    public double zincrby(Object key, Object member, double score) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.zincrby(key.toString(), score, member.toString());
        }
        catch (Exception e) {
            isBroken = true;
            return -1d;
        }
        finally {
            release(jedis, isBroken);
        }
    }

    /**
     *  返回有序集key中，指定区间内的成员。其中成员按score值递增(从小到大)来排序。具有相同score值的成员按字典序来排列。
     * 如果你需要成员按score值递减(score相等时按字典序递减)来排列，请使用ZREVRANGE命令。
     * 下标参数start和stop都以0为底，也就是说，以0表示有序集第一个成员，以1表示有序集第二个成员，以此类推。
     * 你也可以使用负数下标，以-1表示最后一个成员，-2表示倒数第二个成员，以此类推。
     * 超出范围的下标并不会引起错误。如果start的值比有序集的最大下标还要大，或是start >
     * stop时，ZRANGE命令只是简单地返回一个空列表。
     * 另一方面，假如stop参数的值比有序集的最大下标还要大，那么Redis将stop当作最大下标来处理。
     * 使用WITHSCORES选项，来让成员和它的score值一并返回，返回列表以value1,score1, ...,
     * valueN,scoreN的格式表示，而不是value1,...,valueN。客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
    * @author 老王
    * @date 2015年6月17日 下午4:48:25
    * @param key 键
    * @param start 开始
    * @param end 结束
    * @return 指定范围的元素列表(以元组集合的形式)。
     */
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.zrangeWithScores(key, start, end);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return null;
    }
    
    /**
     * 从有序set中移除一个成员
    * @author 张进军
    * @date Sep 24, 2015 3:47:56 PM
    * @param key        有序set键
    * @param member     需要移除的成员
     */
    public void zrem(Object key, Object member) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.zrem(key.toString(), member.toString());
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
    }
    
    
    /**
     * 获取有序集key中成员的排名
    * @author 张进军
    * @date Sep 24, 2015 4:24:14 PM
    * @param key        有序set键
    * @param member     成员
    * @return           成员的排名
     */
    public Long zrank(Object key, String member) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.zrank(key.toString(), member);
        }
        catch (Exception e) {
            isBroken = true;
            return -1L;
        }
        finally {
            release(jedis, isBroken);
        }
    }

    /**
     * 返回有序集 key 中，指定区间内的成员。
     * 其中成员的位置按 score 值递增(从小到大)来排序。
     * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
     * 0 表示有序集第一个成员
     * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，
     * 返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
    * @author 老王
    * @date 2015年6月17日 下午4:27:16
    * @param key 键
    * @param start 开始
    * @param end 结束
    * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
     */
    public Set<String> zrange(String key, long start, long end) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.zrange(key, start, end);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return null;
    }
    
    /**
     * 获取有序集key中成员的排名(倒序)，其中有序集成员按score值从大到小排列
    * @author 张进军
    * @date Sep 24, 2015 4:26:07 PM
    * @param key        有序set键
    * @param member     成员
    * @return           成员的排名(倒序)
     */
    public long zrevrank(Object key, Object member) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.zrevrank(key.toString(), member.toString());
        }
        catch (Exception e) {
            isBroken = true;
            return -1l;
        }
        finally {
            release(jedis, isBroken);
        }
    }

    
    /**
     * 在排序的设置返回的成员范围，通过索引，下令从分数高到低
     * 其中成员的位置按score值递减(从大到小)来排列。具有相同score值的成员按字典序的反序排列。 
     * 除了成员按score值递减的次序排列这一点外，ZREVRANGE命令的其他方面和ZRANGE命令一样。
    * @author 张进军
    * @date Sep 24, 2015 3:48:38 PM
    * @param key        有序set键
    * @param start      开始的分数值
    * @param end        结束的分数值
    * @return           返回有序集key中，指定区间内的成员
     */
    public Set<String> zrevrange(String key, long start, long end) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.zrevrange(key, start, end);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return null;
    }

    
    /**
     * 返回的成员在排序设置的范围，由得分，下令从分数高到低。
     * 返回key的有序集合中的分数在max和min之间的所有元素（包括分数等于max或者min的元素）。
     * 与有序集合的默认排序相反，对于这个命令，元素被认为是从高分到低具有相同分数的元素按字典反序。
     * 除了反序之外， ng, ZREVRANGEBYSCORE 和ZRANGEBYSCORE类似。
     * min和max可以是-inf和+inf，这样一来，你就可以在不知道有序集的最低和最高score值的情况下
    * @author 张进军
    * @date Sep 24, 2015 3:50:31 PM
    * @param key        有序set键
    * @param max        最大分数值
    * @param min        最小分数值
    * @param offset     结果集合中开始的索引值
    * @param count      一共需要返回的结果数
    * @return           指定分数范围的元素列表
     */
    public Set<String> zrevrangebyscore(String key, String max, String min, int offset, int count) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return null;
    }

    
    /**
     * 获取有序集合的成员个数
    * @author 张进军
    * @date Sep 24, 2015 4:27:33 PM
    * @param key        有序set键
    * @return           成员个数
     */
    public long zcard(Object key) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.zcard(key.toString());
        }
        catch (Exception e) {
            isBroken = true;
            return -1;
        }
        finally {
            release(jedis, isBroken);
        }
    }


    /**
     * 获取有序集合中某个成员的分数值
    * @author 张进军
    * @date Sep 24, 2015 4:28:09 PM
    * @param key        有序set键
    * @param member     成员
    * @return           成员对应的分数值
     */
    public Double zscore(Object key, Object member) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.zscore(key.toString(), member.toString());
        }
        catch (Exception e) {
            isBroken = true;
            return -1d;
        }
        finally {
            release(jedis, isBroken);
        }
    }

    
    /**
     * 获取有序集合指定分数值区间的成员个数
    * @author 张进军
    * @date Sep 24, 2015 4:29:02 PM
    * @param key        有序set键
    * @param min        最小分数值
    * @param max        最大分数值
    * @return           成员个数
     */
    public Long zcount(Object key, double min, double max) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.zcount(key.toString(), min, max);
        }
        catch (Exception e) {
            isBroken = true;
            return 0l;
        }
        finally {
            release(jedis, isBroken);
        }
    }
    
    
    /**
     * 
    * @author 王大爷
    * @date 2015年9月18日 下午7:18:22
    * @param keys keys
    * @return String
     */
    public String watch (final String keys) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.watch(keys);
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return null;
    }
    
    
    /**
     * 乐观锁
    * @author 王大爷
    * @date 2015年9月18日 下午7:58:52
    * @param key 贱
    * @param storeId 门店标识
    * @return String
     */
    public String watchSet(String key, Integer storeId) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.watch(App.Redis.GET_ORDER_CODE + String.valueOf(storeId));
            String numString = jedis.get(App.Redis.GET_ORDER_CODE + String.valueOf(storeId));
            Response<String> retObj = null;
            String values = null;
            if (!numString.substring(0, 8).equals(DateUtil.getCurDateString())) {
                Transaction tx = jedis.multi();
                retObj = tx.set(key, numString);
                tx.exec();
                values = numString;
            } 
            else {
                long valueString =  Long.parseLong(numString) + 1;
                Transaction tx = jedis.multi();
                retObj = tx.set(key, String.valueOf(valueString));
                tx.exec();
                values = String.valueOf(valueString);
            }
            String ret = retObj.get();
            if ("OK".equals(ret)) {
                return values;
            }
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
        return null;
    }
    
    
    /**
     * 存储用户的访问口令、设备类型、设备标识
    * @author 张进军
    * @date Sep 24, 2015 4:18:17 PM
    * @param userId         用户标识
    * @param token          访问口令
    * @param deviceType     设备类型
    * @param deviceToken    设备标识
     */
    public void setMultiToken(String userId, String token, String deviceType, String deviceToken) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            Transaction transaction = jedis.multi();
            transaction.hdel(App.Redis.APP_USER_TOKEN_KEY_HASH, userId);
            transaction.hset(App.Redis.APP_TOKEN_USER_KEY_HASH, token, userId);
            transaction.hset(App.Redis.APP_TOKEN_DEVICE_TYPE_KEY_HASH, token, deviceType);
            transaction.hset(App.Redis.APP_TOKEN_DEVICE_TOKEN_KEY_HASH, token, deviceToken);
            transaction.hset(App.Redis.APP_USER_TOKEN_KEY_HASH, userId, token);
            transaction.exec();
        }
        catch (Exception e) {
            isBroken = true;
        }
        finally {
            release(jedis, isBroken);
        }
    }
    
}