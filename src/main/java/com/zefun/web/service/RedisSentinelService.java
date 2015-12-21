package com.zefun.web.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

/**
 * redis操作服务类
* @author 老王
* @date 2015年6月16日 下午5:05:15
 */
public class RedisSentinelService {

    /**
     * redis链接池
     */
    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 将key和value对应。如果key已经存在了，它会被覆盖，而不管它是什么类型。
    * @author 老王
    * @date 2015年6月16日 下午5:05:15
    * @param key 键 
    * @param value 值
    * @return 总是OK，因为SET不会失败。
     */
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    /**
     * 你妹！看属性提示。
    * @author 老王
    * @date 2015年6月16日 下午5:12:33
    * @param key 键 
    * @param value 值
    * @param nxxx 值只能取NX或者XX，如果取NX，则只有当key不存在是才进行set，如果取XX，则只有当key已经存在时才进行set
    * @param expx 值只能取EX或者PX，代表数据过期时间的单位，EX代表秒，PX代表毫秒。
    * @param time 过期时间，单位是expx所代表的单位。
    * @return 总是OK，因为SET不会失败。
     */
    public String set(String key, String value, String nxxx, String expx, long time) {
        return jedisCluster.set(key, value, nxxx, expx, time);
    }

    /**
     * 返回key的value。如果key不存在，返回特殊值nil。如果key的value不是string，就返回错误，因为GET只处理string类型的values。
    * @author 老王
    * @date 2015年6月16日 下午5:29:49
    * @param key 键 
    * @return key对应的value，或者nil（key不存在时）
     */
    public String get(String key) {
        return jedisCluster.get(key);
    }

    /**
     * 返回key是否存在。
    * @author 老王
    * @date 2015年6月16日 下午5:35:01
    * @param key 键 
    * @return 1 如果key存在  0 如果key不存在
     */
    public Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    /**
     * 移除给定key的生存时间，将这个 key从带生存时间 key转换成一个不带生存时间、永不过期的 key。
    * @author 老王
    * @date 2015年6月16日 下午5:40:25
    * @param key 键 
    * @return  当生存时间移除成功时，返回 1 。如果 key 不存在或 key 没有设置生存时间，返回 0.
     */
    public Long persist(String key) {
        return jedisCluster.persist(key);
    }

    /**
     * 返回 key 所储存的值的类型。
    * @author 老王
    * @date 2015年6月16日 下午5:47:06
    * @param key 键 
    * @return none (key不存在)
    *         string (字符串)
    *         list (列表)
    *         set (集合)
    *         zset (有序集)
    *         hash (哈希表)
     */
    public String type(String key) {
        return jedisCluster.type(key);
    }

    /**
     * 为给定 key 设置生存时间（以秒为单位）
    * @author 老王
    * @date 2015年6月16日 下午5:49:24
    * @param key 键
    * @param seconds 以秒为单位
    * @return 1  设置成功返回 1
     *        0 当 key 不存在或者不能为 key 设置生存时间时(比如在低于 2.1.3 版本的 Redis 中你尝试更新 key 的生存时间)，返回 0
     */
    public Long expire(String key, int seconds) {
        return jedisCluster.expire(key, seconds);
    }

    /**
     * 为给定 key 设置生存时间（以毫秒为单位）
    * @author 老王
    * @date 2015年6月17日 上午9:38:10
    * @param key 键
    * @param milliseconds 毫秒为单位
    * @return 1  设置成功返回 1
     *        0 当 key 不存在或者不能为 key 设置生存时间时(比如在低于 2.1.3 版本的 Redis 中你尝试更新 key 的生存时间)，返回 0
     */
    public Long pexpire(String key, long milliseconds) {
        return jedisCluster.pexpire(key, milliseconds);
    }

    /**
     * 为给定 key 设置生存时间（以秒计算）,接受的时间参数是 UNIX 时间戳(不知道的百度)
    * @author 老王
    * @date 2015年6月17日 上午9:45:49
    * @param key 键
    * @param unixTime 时间参数
    * @return 如果生存时间设置成功，返回 1 。
    *         当 key 不存在或没办法设置生存时间，返回 0 。
     */
    public Long expireAt(String key, long unixTime) {
        return jedisCluster.expireAt(key, unixTime);
    }

    /**
     * 为给定 key 设置生存时间（以毫秒计算）,接受的时间参数是 UNIX 时间戳(不知道的百度)
    * @author 老王
    * @date 2015年6月17日 上午9:49:35
    * @param key 键
    * @param millisecondsTimestamp 时间参数
    * @return 如果生存时间设置成功，返回 1 。
    *         当 key 不存在或没办法设置生存时间，返回 0 。
     */
    public Long pexpireAt(String key, long millisecondsTimestamp) {
        return jedisCluster.pexpireAt(key, millisecondsTimestamp);
    }

    /**
     * 以秒为单位，返回给定 key 的剩余生存时间
    * @author 老王
    * @date 2015年6月17日 上午9:49:59
    * @param key 键
    * @return 当 key 不存在时，返回 -2 。
    *         当 key 存在但没有设置剩余生存时间时，返回 -1 。
    *         否则，以秒为单位，返回 key 的剩余生存时间。
     */
    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    /**
     * 设置或者清除指定key的value上的某个位置的比特位，如果该key原先不存在，则新创建一个key，其value将会自动分配内存，
     * 直到可以放下指定位置的bit值。
    * @author 老王
    * @date 2015年6月17日 上午10:08:38
    * @param key 键
    * @param offset 偏移量
    * @param value 值 true代表1，false代表0
    * @return 返回原来位置的bit值是否是1，如果是1，则返回true，否则返回false。
     */
    public Boolean setbit(String key, long offset, boolean value) {
        return jedisCluster.setbit(key, offset, value);
    }

    /**
     * 设置或者清除指定key的value上的某个位置的比特位，如果该key原先不存在，则新创建一个key，其value将会自动分配内存，
     * 直到可以放下指定位置的bit值。
    * @author 老王
    * @date 2015年6月17日 上午10:25:18
    * @param key 键
    * @param offset 偏移量
    * @param value 值  只能是"1"或者"0"
    * @return  返回原来位置的bit值是否是1，如果是1，则返回true，否则返回false。
     */
    public Boolean setbit(String key, long offset, String value) {
        return jedisCluster.setbit(key, offset, value);
    }

    /**
     * 取得偏移量为offset的bit值。
    * @author 老王
    * @date 2015年6月17日 上午10:45:13
    * @param key 键
    * @param offset 偏移量
    * @return true代表1，false代表0
     */
    public Boolean getbit(String key, long offset) {
        return jedisCluster.getbit(key, offset);
    }

    /**
     * 用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始。
     * 原字符和偏移量之间的空白将用零字节(zerobytes, "\x00" )来填充
    * @author 老王
    * @date 2015年6月17日 上午10:47:37
    * @param key 键
    * @param offset 偏移量
    * @param value 值
    * @return 被 SETRANGE 修改之后，字符串的长度。
     */
    public Long setrange(String key, long offset, String value) {
        return jedisCluster.setrange(key, offset, value);
    }

    /**
     *  key 中字符串值的子字符串，字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。
     *  负数偏移量表示从字符串最后开始计数， -1 表示最后一个字符， -2 表示倒数第二个，以此类推。
     *  不支持回绕操作（-1 到 -5）
     *  值域范围不超过实际字符串，超过部分自动被符略
    * @author 老王
    * @date 2015年6月17日 上午10:49:58
    * @param key 键
    * @param startOffset 开始偏移量
    * @param endOffset 结束偏移量
    * @return 截取得出的子字符串。
     */
    public String getrange(String key, long startOffset, long endOffset) {
        return jedisCluster.getrange(key, startOffset, endOffset);
    }

    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
     * 当 key 存在但不是字符串类型时，返回一个错误。
    * @author 老王
    * @date 2015年6月17日 上午11:04:57
    * @param key 键
    * @param value 值
    * @return 返回给定 key 的旧值。
    *         当 key 没有旧值时，也即是， key 不存在时，返回 nil 。
     */
    public String getSet(String key, String value) {
        return jedisCluster.getSet(key, value);
    }

    /**
     * 将 key 的值设为 value ，当且仅当 key 不存在。
     * 若给定的 key 已经存在，则 SETNX 不做任何动作
    * @author 老王
    * @date 2015年6月17日 上午11:28:09
    * @param key 键
    * @param value 值
    * @return 设置成功，返回 1 。
    *         设置失败，返回 0 。
     */
    public Long setnx(String key, String value) {
        return jedisCluster.setnx(key, value);
    }

    /**
     * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)。
     * 如果 key 已经存在， SETEX 命令将覆写旧值。
    * @author 老王
    * @date 2015年6月17日 上午11:30:15
    * @param key 键
    * @param seconds 以秒为单位
    * @param value 值
    * @return 设置成功时返回 OK。
    *         当 seconds 参数不合法时，返回一个错误。
     */
    public String setex(String key, int seconds, String value) {
        return jedisCluster.setex(key, seconds, value);
    }

    /**
     * 将 key 所储存的值减去减量 decrement。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECRBY 操作。
    * @author 老王
    * @date 2015年6月17日 上午11:35:26
    * @param key 键
    * @param integer decrement
    * @return 减去 decrement 之后， key 的值。
     */
    public Long decrBy(String key, long integer) {
        return jedisCluster.decrBy(key, integer);
    }

    /**
     * 将 key 中储存的数字值减一。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作。
    * @author 老王
    * @date 2015年6月17日 上午11:51:57
    * @param key 键
    * @return 执行 DECR 命令之后 key 的值。
     */
    public Long decr(String key) {
        return jedisCluster.decr(key);
    }

    /**
     * 将 key 所储存的值加上增量 increment 。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。
    * @author 老王
    * @date 2015年6月17日 上午11:53:33
    * @param key 键
    * @param integer increment
    * @return 加上 increment 之后， key 的值。
     */
    public Long incrBy(String key, long integer) {
        return jedisCluster.incrBy(key, integer);
    }

    /**
     * 为 key 中所储存的值加上浮点数增量 increment 。
     * 如果 key 不存在，那么 INCRBYFLOAT 会先将 key 的值设为 0 ，再执行加法操作。
     * 如果命令执行成功，那么 key 的值会被更新为（执行加法之后的）新值，并且新值会以字符串的形式返回给调用者。
    * @author 老王
    * @date 2015年6月17日 上午11:54:43
    * @param key 键
    * @param value 值
    * @return 执行命令之后 key 的值。
     */
    public Double incrByFloat(String key, double value) {
        return jedisCluster.incrByFloat(key, value);
    }

    /**
     * 将 key 中储存的数字值增一。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
    * @author 老王
    * @date 2015年6月17日 上午11:59:07
    * @param key 键
    * @return 执行 INCR 命令之后 key 的值。
     */
    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    /**
     * key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。
     * key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
    * @author 老王
    * @date 2015年6月17日 下午12:00:33
    * @param key 键
    * @param value 值
    * @return 追加 value 之后， key 中字符串的长度。
     */
    public Long append(String key, String value) {
        return jedisCluster.append(key, value);
    }

    /**
     * 返回start - end 之间的子字符串(start 和 end处的字符也包括在内)
    * @author 老王
    * @date 2015年6月17日 下午12:01:48
    * @param key 键
    * @param start 开始
    * @param end 结束
    * @return 返回子字符串
     */
    public String substr(String key, int start, int end) {
        return jedisCluster.substr(key, start, end);
    }

    /**
     * 将哈希表 key 中的域 field 的值设为 value 。
     * 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。
    * @author 老王
    * @date 2015年6月17日 下午12:03:52
    * @param key 键
    * @param field 域
    * @param value 值
    * @return 如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。
    *         如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回 0 
     */
    public Long hset(String key, Object field, Object value) {
        return jedisCluster.hset(key, field.toString(), value.toString());
    }

    /**
     * key对应的值是一个Hash表，则返回对应字段的值。 
     * 如果不存在该字段，或者key不存在，则返回一个"nil"值。
    * @author 老王
    * @date 2015年6月17日 下午12:12:27
    * @param key 键
    * @param field 字段名
    * @return 字段值
     */
    public String hget(String key, Object field) {
        return jedisCluster.hget(key, field.toString());
    }

    /**
     * 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。
     * 若域 field 已经存在，该操作无效。
    * @author 老王
    * @date 2015年6月17日 下午2:27:23
    * @param key 键
    * @param field 字段名
    * @param value 值
    * @return 设置成功，返回 1 。
    *         如果给定域已经存在且没有操作被执行，返回 0 。
     */
    public Long hsetnx(String key, String field, String value) {
        return jedisCluster.hsetnx(key, field, value);
    }

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * 此命令会覆盖哈希表中已存在的域。
    * @author 老王
    * @date 2015年6月17日 下午2:29:43
    * @param key 键
    * @param hash  field-value (域-值)
    * @return 如果命令执行成功，返回 OK 。
    *         当 key 不是哈希表(hash)类型时，返回一个错误。
     */
    public String hmset(String key, Map<String, String> hash) {
        return jedisCluster.hmset(key, hash);
    }

    /**
     * 返回哈希表 key 中，一个或多个给定域的值。
     * 如果给定的域不存在于哈希表，那么返回一个 nil 值。
     * 
    * @author 老王
    * @date 2015年6月17日 下午2:33:00
    * @param key 键
    * @param fields 字段名
    * @return 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样。
     */
    public List<String> hmget(String key, String... fields) {
        return jedisCluster.hmget(key, fields);
    }

    /**
     * 为哈希表 key 中的域 field 的值加上增量 increment 。
     * 增量也可以为负数，相当于对给定域进行减法操作。
    * @author 老王
    * @date 2015年6月17日 下午2:35:52
    * @param key 键
    * @param field 字段名
    * @param value 值
    * @return 执行 HINCRBY 命令之后，哈希表 key 中域 field 的值。
     */
    public Long hincrBy(String key, String field, long value) {
        return jedisCluster.hincrBy(key, field, value);
    }

    /**
     * 返回字段是否是 key 指定的哈希集中存在的字段。
    * @author 老王
    * @date 2015年6月17日 下午2:40:31
    * @param key 键
    * @param field 字段名
    * @return 1 哈希集中含有该字段。
    *         0 哈希集中不含有该存在字段，或者key不存在。
     */
    public Boolean hexists(String key, String field) {
        return jedisCluster.hexists(key, field);
    }

    /**
     * 从 key 指定的哈希集中移除指定的域。
     * 在哈希集中不存在的域将被忽略。
     * 如果 key 指定的哈希集不存在，
     * 它将被认为是一个空的哈希集，该命令将返回0。
    * @author 老王
    * @date 2015年6月17日 下午2:43:10
    * @param key 键
    * @param field 字段名
    * @return 返回从哈希集中成功移除的域的数量，不包括指出但不存在的那些域
     */
    public Long hdel(String key, String... field) {
        return jedisCluster.hdel(key, field);
    }

    /**
     * 返回哈希表 key 中域的数量。
    * @author 老王
    * @date 2015年6月17日 下午2:45:47
    * @param key 键
    * @return 哈希表中域的数量
     */
    public Long hlen(String key) {
        return jedisCluster.hlen(key);
    }

    /**
     * 返回哈希表 key 中的所有域。（名称）
    * @author 老王
    * @date 2015年6月17日 下午2:47:01
    * @param key 键
    * @return 一个包含哈希表中所有域的表。
     */
    public Set<String> hkeys(String key) {
        return jedisCluster.hkeys(key);
    }

    /**
     * 返回哈希表 key 中所有域的值。（值）
    * @author 老王
    * @date 2015年6月17日 下午2:48:20
    * @param key 键
    * @return 一个包含哈希表中所有值的表。
     */
    public List<String> hvals(String key) {
        return jedisCluster.hvals(key);
    }

    /**
     * 返回 key 指定的哈希集中所有的字段和值
    * @author 老王
    * @date 2015年6月17日 下午2:52:36
    * @param key 键
    * @return 以列表形式返回哈希表的域和域的值。
     */
    public Map<String, String> hgetAll(String key) {
        return jedisCluster.hgetAll(key);
    }

    /**
     * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     * 从左到右的顺序依次插入到表尾
     * 一个空列表 mylist 执行 RPUSH mylist a b c 
     * 等同于执行命令 RPUSH mylist a 、 RPUSH mylist b 、 RPUSH mylist c 
     * 列表允许重复元素
    * @author 老王
    * @date 2015年6月17日 下午2:54:13
    * @param key 键
    * @param string 值
    * @return 执行 RPUSH 操作后，表的长度。
     */
    public Long rpush(String key, String... string) {
        return jedisCluster.rpush(key, string);
    }

    /**
     * 将一个或多个值 value 插入到列表 key 的表头
     * 从左到右的顺序依次插入到表头
     * 列表允许重复元素
    * @author 老王
    * @date 2015年6月17日 下午2:57:17
    * @param key 键
    * @param string 值
    * @return 执行 LPUSH 命令后，列表的长度。
     */
    public Long lpush(String key, String... string) {
        return jedisCluster.lpush(key, string);
    }

    /**
     * 返回列表 key 的长度。
    * @author 老王
    * @date 2015年6月17日 下午3:01:51
    * @param key 键
    * @return  key 不存在，则 key 被解释为一个空列表，返回 0 .
    *           key 不是列表类型，返回一个错误。
     */
    public Long llen(String key) {
        return jedisCluster.llen(key);
    }

    /**
     *  返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
     *  0 表示列表的第一个元素，-1 表示列表的最后一个元素
    * @author 老王
    * @date 2015年6月17日 下午3:03:50
    * @param key 键
    * @param start 开始
    * @param end 结束
    * @return 指定范围里的列表元素。
     */
    public List<String> lrange(String key, long start, long end) {
        return jedisCluster.lrange(key, start, end);
    }

    /**
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，
     * 不在指定区间之内的元素都将被删除。
    * @author 老王
    * @date 2015年6月17日 下午3:11:21
    * @param key 键
    * @param start 开始
    * @param end 结束
    * @return 命令执行成功时，返回 ok 。
     */
    public String ltrim(String key, long start, long end) {
        return jedisCluster.ltrim(key, start, end);
    }

    /**
     * 返回列表 key 中，下标为 index 的元素。
    * @author 老王
    * @date 2015年6月17日 下午3:15:41
    * @param key 键
    * @param index 下标量
    * @return 列表中下标为 index 的元素。
     */
    public String lindex(String key, long index) {
        return jedisCluster.lindex(key, index);
    }

    /**
     * 将列表 key 下标为 index 的元素的值设置为 value 。
     * index 参数超出范围，或对一个空列表( key 不存在)进行 LSET 时，返回一个错误。
    * @author 老王
    * @date 2015年6月17日 下午3:17:10
    * @param key 键 
    * @param index 下标量
    * @param value 值
    * @return 操作成功返回 ok ，否则返回错误信息。
     */
    public String lset(String key, long index, String value) {
        return jedisCluster.lset(key, index, value);
    }

    /**
     * 根据参数 count 的值，移除列表中与参数 value 相等的元素。
    * @author 老王
    * @date 2015年6月17日 下午3:18:54
    * @param key 键
    * @param count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
    *        count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
    *        count = 0 : 移除表中所有与 value 相等的值。
    * @param value 值
    * @return 被移除元素的数量。
     */
    public Long lrem(String key, long count, String value) {
        return jedisCluster.lrem(key, count, value);
    }

    /**
     * 移除并且返回 key 对应的 list 的第一个元素。
    * @author 老王
    * @date 2015年6月17日 下午3:31:07
    * @param key 键
    * @return 返回第一个元素的值，或者当 key 不存在时返回 nil。
     */
    public String lpop(String key) {
        return jedisCluster.lpop(key);
    }

    /**
     * 移除并返回列表 key 的尾元素。
    * @author 老王
    * @date 2015年6月17日 下午3:35:32
    * @param key 键
    * @return 列表的尾元素
     */
    public String rpop(String key) {
        return jedisCluster.rpop(key);
    }

    /**
     * 将一个或多个 member 元素加入到集合 key 当中，
     * 已经存在于集合的 member 元素将被忽略。
    * @author 老王
    * @date 2015年6月17日 下午3:36:16
    * @param key 键
    * @param member 元素
    * @return 被添加到集合中的新元素的数量，不包括被忽略的元素。
     */
    public Long sadd(String key, String... member) {
        return jedisCluster.sadd(key, member);
    }

    /**
     * 返回集合 key 中的所有成员。
    * @author 老王
    * @date 2015年6月17日 下午3:38:47
    * @param key 键
    * @return 集合中的所有成员。
     */
    public Set<String> smembers(String key) {
        return jedisCluster.smembers(key);
    }

    /**
     * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
    * @author 老王
    * @date 2015年6月17日 下午3:48:20
    * @param key 键
    * @param member 元素
    * @return 被成功移除的元素的数量，不包括被忽略的元素。
     */
    public Long srem(String key, String... member) {
        return jedisCluster.srem(key, member);
    }

    /**
     * 移除并返回集合中的一个随机元素。
    * @author 老王
    * @date 2015年6月17日 下午3:54:01
    * @param key 键
    * @return 被移除的随机元素。
     */
    public String spop(String key) {
        return jedisCluster.spop(key);
    }

    /**
     * 移除并返回多个集合中的随机元素
    * @author 老王
    * @date 2015年6月17日 下午3:55:02
    * @param key 键
    * @param count 个数
    * @return 被移除的元素, 当key不存在的时候值为 nil .
     */
    public Set<String> spop(String key, long count) {
        return jedisCluster.spop(key, count);
    }

    /**
     * 返回集合 key 的基数(集合中元素的数量)。
    * @author 老王
    * @date 2015年6月17日 下午3:59:15
    * @param key 键
    * @return 集合的基数。
     */
    public Long scard(String key) {
        return jedisCluster.scard(key);
    }

    /**
     * 判断 member 元素是否集合 key 的成员。
    * @author 老王
    * @date 2015年6月17日 下午4:01:08
    * @param key 键
    * @param member 元素
    * @return 如果 member 元素是集合的成员，返回 1 
    *         如果 member 元素不是集合的成员，或 key 不存在，返回 0 。
     */
    public Boolean sismember(String key, String member) {
        return jedisCluster.sismember(key, member);
    }

    /**
     * 仅提供key参数,那么随机返回key集合中的一个元素.该命令作用类似于SPOP命令, 
     * 不同的是SPOP命令会将被选择的随机元素从集合中移除,
     * 而SRANDMEMBER仅仅是返回该随记元素,而不做任何操作.
    * @author 老王
    * @date 2015年6月17日 下午4:02:10
    * @param key 键
    * @return 返回一个元素；如果集合为空，返回 nil 。
     */
    public String srandmember(String key) {
        return jedisCluster.srandmember(key);
    }

    /**
     * 如果count是整数且小于元素的个数，返回含有 count
     * 个不同的元素的数组,如果count是个整数且大于集合中元素的个数时,仅返回整个集合的所有元素
     * ,当count是负数,则会返回一个包含count的绝对值的个数元素的数组
     * ，如果count的绝对值大于元素的个数,则返回的结果集里会出现一个元素出现多次的情况.
    * @author 老王
    * @date 2015年6月17日 下午4:05:51
    * @param key 键
    * @param count 数量
    * @return 么返回一个数组；如果集合为空，返回空数组。
     */
    public List<String> srandmember(String key, int count) {
        return jedisCluster.srandmember(key, count);
    }

    /**
     * 回key的string类型value的长度。如果key对应的非string类型，就返回错误。
    * @author 老王
    * @date 2015年6月17日 下午4:09:29
    * @param key 键
    * @return key对应的字符串value的长度，或者0（key不存在）
     */
    public Long strlen(String key) {
        return jedisCluster.strlen(key);
    }

    /**
     * 该命令添加指定的成员到key对应的有序集合中，每个成员都有一个分数。
     * 你可以指定多个分数/成员组合。如果一个指定的成员已经在对应的有序集合中了，
     * 那么其分数就会被更新成最新的
     * ，并且该成员会重新调整到正确的位置，以确保集合有序。如果key不存在，
     * 就会创建一个含有这些成员的有序集合，就好像往一个空的集合中添加一样
     * 。如果key存在，但是它并不是一个有序集合，那么就返回一个错误。
     * 分数的值必须是一个表示数字的字符串，并且可以是double类型的浮点数。
    * @author 老王
    * @date 2015年6月17日 下午4:11:20
    * @param key 键
    * @param score 分数
    * @param member 值
    * @return  返回添加到有序集合中元素的个数，不包括那种已经存在只是更新分数的元素。
     */
    public Long zadd(String key, double score, String member) {
        return jedisCluster.zadd(key, score, member);
    }

    /**
     * 该命令添加指定的成员到key对应的有序集合中，每个成员都有一个分数。
     * 你可以指定多个分数/成员组合。如果一个指定的成员已经在对应的有序集合中了，
     * 那么其分数就会被更新成最新的
     * ，并且该成员会重新调整到正确的位置，以确保集合有序。如果key不存在，
     * 就会创建一个含有这些成员的有序集合，就好像往一个空的集合中添加一样
     * 。如果key存在，但是它并不是一个有序集合，那么就返回一个错误。
     * 分数的值必须是一个表示数字的字符串，并且可以是double类型的浮点数。 
    * @author 老王
    * @date 2015年6月17日 下午4:24:48
    * @param key 键
    * @param scoreMembers 分数 对应值的map集合
    * @return 返回添加到有序集合中元素的个数，不包括那种已经存在只是更新分数的元素。
     */
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        return jedisCluster.zadd(key, scoreMembers);
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
        return jedisCluster.zrange(key, start, end);
    }

    /**
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
    * @author 老王
    * @date 2015年6月17日 下午4:30:03
    * @param key 键
    * @param member 成员字段
    * @return 被成功移除的成员的数量，不包括被忽略的成员。
     */
    public Long zrem(String key, String... member) {
        return jedisCluster.zrem(key, member);
    }

    /**
     * 为有序集 key 的成员 member 的 score 值加上增量 increment 
     * 可以通过传递一个负数值 increment ，让 score 减去相应的值，
    * @author 老王
    * @date 2015年6月17日 下午4:31:37
    * @param key 键
    * @param score increment
    * @param member member
    * @return member 成员的新 score 值，以字符串形式表示。
     */
    public Double zincrby(String key, double score, String member) {
        return jedisCluster.zincrby(key, score, member);
    }

    /**
     * 返回有序集 key 中成员 member 的排名。
     * 其中有序集成员按 score 值递增(从小到大)顺序排列。
     * （无法解决并列排名）
    * @author 老王
    * @date 2015年6月17日 下午4:35:10
    * @param key 键
    * @param member member
    * @return 如果 member 是有序集 key 的成员，返回 member 的排名。
    *         如果 member 不是有序集 key 的成员，返回 nil 。
     */
    public Long zrank(String key, String member) {
        return jedisCluster.zrank(key, member);
    }

    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。
    * @author 老王
    * @date 2015年6月17日 下午4:43:36
    * @param key 键
    * @param member member
    * @return 如果 member 是有序集 key 的成员，返回 member 的排名。
    *         如果 member 不是有序集 key 的成员，返回 nil 。
     */
    public Long zrevrank(String key, String member) {
        return jedisCluster.zrevrank(key, member);
    }

    /**
     * 返回有序集 key 中，指定区间内的成员。
     * 其中成员的位置按 score 值递减(从大到小)来排列。
    * @author 老王
    * @date 2015年6月17日 下午4:44:53
    * @param key 键
    * @param start 开始
    * @param end 结束
    * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
     */
    public Set<String> zrevrange(String key, long start, long end) {
        return jedisCluster.zrevrange(key, start, end);
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
        return jedisCluster.zrangeWithScores(key, start, end);
    }

    /**
     * 返回有序集key中，指定区间内的成员。其中成员的位置按score值递减(从大到小)来排列。
     * 具有相同score值的成员按字典序的反序排列。
     * 除了成员按score值递减的次序排列这一点外，ZREVRANGE命令的其他方面和ZRANGE命令一样。
    * @author 老王
    * @date 2015年6月17日 下午4:57:49
    * @param key 键
    * @param start 开始
    * @param end 结束
    * @return 区间内的成员。其中成员的位置按score值递减(从大到小)来排列。
     */
    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        return jedisCluster.zrevrangeWithScores(key, start, end);
    }

    /**
     * 返回有序集 key 的基数。
    * @author 老王
    * @date 2015年6月17日 下午5:01:25
    * @param key 键
    * @return 当 key 存在且是有序集类型时，返回有序集的基数。当 key 不存在时，返回 0 。
     */
    public Long zcard(String key) {
        return jedisCluster.zcard(key);
    }

    /**
     * 返回有序集 key 中，成员 member 的 score 值。
    * @author 老王
    * @date 2015年6月17日 下午5:02:58
    * @param key 键
    * @param member member
    * @return member 成员的 score 值，以字符串形式表示。
     */
    public Double zscore(String key, String member) {
        return jedisCluster.zscore(key, member);
    }

    /**
     * 返回或保存给定列表、集合、有序集合 key 中经过排序的元素。
     *  对集合，有序集合，或者列表的value进行排序。默认情况下排序只对数字排序，双精度浮点数。
    * @author 老王
    * @date 2015年6月17日 下午5:06:07
    * @param key 键
    * @return   假设集合或列表包含的是数字元素，那么返回的将会是从小到大排列的一个列表。
     */
    public List<String> sort(String key) {
        return jedisCluster.sort(key);
    }

    /**
     * 返回或保存给定列表、集合、有序集合 key 中经过排序的元素。
     *  对集合，有序集合，或者列表的value进行排序。默认情况下排序只对数字排序，双精度浮点数。
     *  不会的查命令，参照下面例子。还不会就二了
    * @author 老王
    * @date 2015年6月17日 下午5:21:40
    * @param key 键
    * @param sortingParameters 例子：SortingParams sortingParameters = new SortingParams();
    *                                  sortingParameters.desc();
    *                                  sortingParameters.alpha();
    *                                  当数据集中保存的是字符串值时，你可以用 ALPHA
    *                                  修饰符(modifier)进行排序。
    *                              sortingParameters.limit(0, 2); 可用于分页查询
    *                               List<String> list = redis.sort("mylist", sortingParameters);默认是升序
    * @return 假设集合或列表包含的是数字元素，那么返回的将会是从小到大排列的一个列表。
     */
    public List<String> sort(String key, SortingParams sortingParameters) {
        return jedisCluster.sort(key, sortingParameters);
    }

    /**
     * 返回有序集key中，score值在min和max之间(默认包括score值等于min或max)的成员。
    * @author 老王
    * @date 2015年6月17日 下午5:44:19
    * @param key 键
    * @param min 最小
    * @param max 最大
    * @return 指定分数范围的元素个数。
     */
    public Long zcount(String key, double min, double max) {
        return jedisCluster.zcount(key, min, max);
    }

    /**
     * 返回有序集key中，score值在min和max之间(默认包括score值等于min或max)的成员。
    * @author 老王
    * @date 2015年6月17日 下午5:51:07
    * @param key 键
    * @param min 最小
    * @param max 最大
    * @return 指定分数范围的元素个数。
     */
    public Long zcount(String key, String min, String max) {
        return jedisCluster.zcount(key, min, max);
    }

    /**
     * 返回key的有序集合中的分数在min和max之间的所有元素（包括分数等于max或者min的元素）。
     * 元素被认为是从低分到高分排序的。
     * 具有相同分数的元素按字典序排列
    * @author 老王
    * @date 2015年6月17日 下午5:57:04
    * @param key 键
    * @param min 最小
    * @param max 最大
    * @return 指定分数范围的元素列表
     */
    public Set<String> zrangeByScore(String key, double min, double max) {
        return jedisCluster.zrangeByScore(key, min, max);
    }

    /**
     * 返回key的有序集合中的分数在min和max之间的所有元素（包括分数等于max或者min的元素）。
     * 元素被认为是从低分到高分排序的。
     * 具有相同分数的元素按字典序排列
    * @author 老王
    * @date 2015年6月17日 下午5:58:45
    * @param key 键
    * @param min 最小
    * @param max 最大
    * @return 指定分数范围的元素列表
     */
    public Set<String> zrangeByScore(String key, String min, String max) {
        return jedisCluster.zrangeByScore(key, min, max);
    }

    /**
     * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。
     * 有序集成员按 score 值递减(从大到小)的次序排列。
    * @author 老王
    * @date 2015年6月17日 下午5:59:24
    * @param key 键
    * @param max 最小
    * @param min 最大
    * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
     */
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        return jedisCluster.zrevrangeByScore(key, max, min);
    }

    /**
     * 返回key的有序集合中的分数在min和max之间的所有元素（包括分数等于max或者min的元素）。元素被认为是从低分到高分排序的。
     * 具有相同分数的元素按字典序排列, 指定返回结果的数量及区间。
    * @author 老王
    * @date 2015年6月17日 下午6:03:45
    * @param key 键
    * @param min 最小
    * @param max 最大
    * @param offset 分页开始数
    * @param count  分页结束数
    * @return 指定分数范围的元素列表(也可以返回他们的分数)。
     */
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return jedisCluster.zrangeByScore(key, min, max, offset, count);
    }

    /**
     * 返回key的有序集合中的分数在max和min之间的所有元素（包括分数等于max或者min的元素）。
     * 与有序集合的默认排序相反，对于这个命令，元素被认为是从高分到低具有相同分数的元素按字典反序。
    * @author 老王
    * @date 2015年6月17日 下午6:10:09
    * @param key 键
    * @param max 最大
    * @param min 最小
    * @return 指定分数范围的元素列表(也可以返回他们的分数)。
     */
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        return jedisCluster.zrevrangeByScore(key, max, min);
    }

    /**
     * 返回key的有序集合中的分数在min和max之间的所有元素（包括分数等于max或者min的元素）。元素被认为是从低分到高分排序的。
     * 具有相同分数的元素按字典序排列, 指定返回结果的数量及区间。
    * @author 老王
    * @date 2015年6月17日 下午6:10:15
    * @param key 键
    * @param min 最小
    * @param max 最大
    * @param offset 分页开始数
    * @param count  分页结束数
    * @return 指定分数范围的元素列表(也可以返回他们的分数)。
     */
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        return jedisCluster.zrangeByScore(key, min, max, offset, count);
    }

    /**
     * 返回key的有序集合中的分数在max和min之间的所有元素（包括分数等于max或者min的元素）。
     * 与有序集合的默认排序相反，对于这个命令，元素被认为是从高分到低具有相同分数的元素按字典反序。
     * 除了反序之外， ng, ZREVRANGEBYSCORE 和ZRANGEBYSCORE类似。
    * @author 老王
    * @date 2015年6月17日 下午6:10:21
    * @param key 键
    * @param max 最大
    * @param min 最小
    * @param offset 分页开始数
    * @param count  分页结束数
    * @return 指定分数范围的元素列表(也可以返回他们的分数)。
     */
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        return jedisCluster.zrevrangeByScore(key, max, min, offset, count);
    }

    /**
     * 返回key的有序集合中的分数在min和max之间的所有元素（包括分数等于max或者min的元素）。元素被认为是从低分到高分排序的。
     * 具有相同分数的元素按字典序排列。返回元素和其分数，而不只是元素。
    * @author 老王
    * @date 2015年6月17日 下午6:10:32
    * @param key 键
    * @param min 最小
    * @param max 最大
    * @return 指定分数范围的元素列表(也可以返回他们的分数)。
     */
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        return jedisCluster.zrangeByScoreWithScores(key, min, max);
    }

    /**
     * 返回key的有序集合中的分数在max和min之间的所有元素（包括分数等于max或者min的元素）。
     * 与有序集合的默认排序相反，对于这个命令，元素被认为是从高分到低具有相同分数的元素按字典反序。
     * 返回元素和其分数，而不只是元素。
    * @author 老王
    * @date 2015年6月17日 下午6:10:39
    * @param key 键
    * @param max 最大 
    * @param min 最小
    * @return 指定分数范围的元素列表(也可以返回他们的分数)。
     */
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        return jedisCluster.zrevrangeByScoreWithScores(key, max, min);
    }

    /**
     * 返回key的有序集合中的分数在min和max之间的所有元素（包括分数等于max或者min的元素）。元素被认为是从低分到高分排序的。
     * 具有相同分数的元素按字典序排列。返回元素和其分数，而不只是元素。
    * @author 老王
    * @date 2015年6月17日 下午6:10:52
    * @param key 键
    * @param min 最小
    * @param max 最大
    * @param offset 分页开始数
    * @param count  分页结束数
    * @return 指定分数范围的元素列表(也可以返回他们的分数)。
     */
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    /**
     * 返回key的有序集合中的分数在max和min之间的所有元素（包括分数等于max或者min的元素）。
     * 与有序集合的默认排序相反，对于这个命令，元素被认为是从高分到低具有相同分数的元素按字典反序。
     * 除了反序之外， ng, ZREVRANGEBYSCORE 和ZRANGEBYSCORE类似。
    * @author 老王
    * @date 2015年6月17日 下午6:11:05
    * @param key 键
    * @param max 最大
    * @param min 最小
    * @param offset 分页开始数
    * @param count  分页结束数
    * @return 指定分数范围的元素列表(也可以返回他们的分数)。
     */
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        return jedisCluster.zrevrangeByScore(key, max, min, offset, count);
    }

    /**
     * 返回key的有序集合中的分数在min和max之间的所有元素（包括分数等于max或者min的元素）。元素被认为是从低分到高分排序的。
     * 具有相同分数的元素按字典序排列。返回元素和其分数，而不只是元素。
    * @author 老王
    * @date 2015年6月17日 下午6:11:14
    * @param key 键
    * @param min 最小
    * @param max 最大
    * @return 指定分数范围的元素列表(也可以返回他们的分数)。
     */
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        return jedisCluster.zrangeByScoreWithScores(key, min, max);
    }

    /**
     * 返回key的有序集合中的分数在max和min之间的所有元素（包括分数等于max或者min的元素）。
     * 与有序集合的默认排序相反，对于这个命令，元素被认为是从高分到低具有相同分数的元素按字典反序。
     * 返回元素和其分数，而不只是元素。
    * @author 老王
    * @date 2015年6月17日 下午6:11:19
    * @param key 键
    * @param max 最大
    * @param min 最小
    * @return 指定分数范围的元素列表(也可以返回他们的分数)。
     */
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        return jedisCluster.zrevrangeByScoreWithScores(key, max, min);
    }

    /**
     * 返回key的有序集合中的分数在min和max之间的所有元素（包括分数等于max或者min的元素）。元素被认为是从低分到高分排序的。
     * 具有相同分数的元素按字典序排列。返回元素和其分数，而不只是元素。
    * @author 老王
    * @date 2015年6月17日 下午6:11:29
    * @param key 键
    * @param min 最小
    * @param max 最大
    * @param offset 分页开始数
    * @param count  分页结束数
    * @return 指定分数范围的元素列表(也可以返回他们的分数)。
     */
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        return jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    /**
     * 返回key的有序集合中的分数在max和min之间的所有元素（包括分数等于max或者min的元素）。
     * 与有序集合的默认排序相反，对于这个命令，元素被认为是从高分到低具有相同分数的元素按字典反序。
     * 返回元素和其分数，而不只是元素。
    * @author 老王
    * @date 2015年6月17日 下午6:11:59
    * @param key 键
    * @param max 最大
    * @param min 最大
    * @param offset 分页开始数
    * @param count 分页结束数
    * @return 指定分数范围的元素列表(也可以返回他们的分数)。
     */
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        return jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    /**
     * 返回key的有序集合中的分数在max和min之间的所有元素（包括分数等于max或者min的元素）。
     * 与有序集合的默认排序相反，对于这个命令，元素被认为是从高分到低具有相同分数的元素按字典反序。
     * 返回元素和其分数，而不只是元素。
    * @author 老王
    * @date 2015年6月17日 下午6:12:14
    * @param key 键
    * @param max 最大
    * @param min 最小
    * @param offset 分页开始数
    * @param count 分页结束数
    * @return 指定分数范围的元素列表(也可以返回他们的分数)。
     */
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        return jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    /**
     * 移除有序集key中，指定排名(rank)区间内的所有成员。下标参数start和stop都以0为底，
     * 0处是分数最小的那个元素。这些索引也可是负数，
    * 表示位移从最高分处开始数。例如，-1是分数最高的元素，-2是分数第二高的，依次类推。
    * @author 老王
    * @date 2015年6月17日 下午6:18:11
    * @param key 键
    * @param start 开始
    * @param end 结束
    * @return 被移除成员的数量。
     */
    public Long zremrangeByRank(String key, long start, long end) {
        return jedisCluster.zremrangeByRank(key, start, end);
    }

    /**
     * 移除有序集key中，所有score值介于min和max之间(包括等于min或max)的成员。
     * 自版本2.1.6开始，score值等于min或max的成员也可以不包括在内，语法请参见ZRANGEBYSCORE命令。
    * @author 老王
    * @date 2015年6月17日 下午6:20:06
    * @param key 键
    * @param start 开始
    * @param end 结束
    * @return 被移除成员的数量。
     */
    public Long zremrangeByScore(String key, double start, double end) {
        return jedisCluster.zremrangeByScore(key, start, end);
    }

    /**
     * 移除有序集key中，所有score值介于min和max之间(包括等于min或max)的成员。
     * 自版本2.1.6开始，score值等于min或max的成员也可以不包括在内，语法请参见ZRANGEBYSCORE命令。
    * @author 老王
    * @date 2015年6月17日 下午7:14:43
    * @param key 键
    * @param start 开始
    * @param end 结束
    * @return 被移除成员的数量。
     */
    public Long zremrangeByScore(String key, String start, String end) {
        return jedisCluster.zremrangeByScore(key, start, end);
    }

    /**
     * 当插入到有序集合中的元素都具有相同的分数时，这个命令可以返回min和max指定范围内的元素的数量。
    * @author 老王
    * @date 2015年6月17日 下午7:16:48
    * @param key 键
    * @param min 最小
    * @param max 最大 
    * @return  min和max指定范围内的元素的数量。
     */
    public Long zlexcount(String key, String min, String max) {
        return jedisCluster.zlexcount(key, min, max);
    }

    /**
     * 该元素被认为是从最低到最高的分值进行排序。字典顺序用于以相等的分数的元素。
     * 两个开始和结束是从零开始的索引，其中0是第一个元素，1是下一个元素等等。
     * 它们也可以是表示偏移量从有序集的结尾，以-1作为排序的集合的最后一个元素，-2倒数第二元素等负数。
    * @author 老王
    * @date 2015年6月17日 下午7:26:25
    * @param key 键
    * @param min 最小
    * @param max 最大
    * @return 返回数组，在规定的分数范围内的元素列表。
     */
    public Set<String> zrangeByLex(String key, String min, String max) {
        return jedisCluster.zrangeByLex(key, min, max);
    }

    /**
     * 该元素被认为是从最低到最高的分值进行排序。字典顺序用于以相等的分数的元素。
     * 两个开始和结束是从零开始的索引，其中0是第一个元素，1是下一个元素等等。
     * 它们也可以是表示偏移量从有序集的结尾，以-1作为排序的集合的最后一个元素，-2倒数第二元素等负数。
    * @author 老王
    * @date 2015年6月17日 下午7:28:39
    * @param key 键
    * @param min 最小
    * @param max 最大
    * @param offset 分页开始数
    * @param count  分页结束数
    * @return 返回数组，在规定的分数范围内的元素列表。
     */
    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        return jedisCluster.zrangeByLex(key, min, max, offset, count);
    }

    /**
     * 该元素被认为是从最高到最低的分值进行排序。字典顺序用于以相等的分数的元素。
     * 两个开始和结束是从零开始的索引，其中0是第一个元素，1是下一个元素等等。
     * 它们也可以是表示偏移量从有序集的结尾，以-1作为排序的集合的最后一个元素，-2倒数第二元素等负数。
    * @author 老王
    * @date 2015年6月17日 下午7:30:49
    * @param key 键
    * @param max 最大
    * @param min 最小
    * @return 返回数组，在规定的分数范围内的元素列表。
     */
    public Set<String> zrevrangeByLex(String key, String max, String min) {
        return jedisCluster.zrevrangeByLex(key, max, min);
    }

    /**
     * 该元素被认为是从最高到最低的分值进行排序。字典顺序用于以相等的分数的元素。
     * 两个开始和结束是从零开始的索引，其中0是第一个元素，1是下一个元素等等。
     * 它们也可以是表示偏移量从有序集的结尾，以-1作为排序的集合的最后一个元素，-2倒数第二元素等负数。
    * @author 老王
    * @date 2015年6月17日 下午7:32:05
    * @param key 键
    * @param max 最大
    * @param min 最小
    * @param offset 分页开始数
    * @param count  分页结束数
    * @return 返回数组，在规定的分数范围内的元素列表。
     */
    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        return jedisCluster.zrevrangeByLex(key, max, min, offset, count);
    }

    /**
     * 删除有序集合存储在由最小和最大指定的字典范围之间的所有键元素。
    * @author 老王
    * @date 2015年6月17日 下午7:33:28
    * @param key 键
    * @param min 最小
    * @param max 最大
    * @return  返回整数，删除的元素数量。
     */
    public Long zremrangeByLex(String key, String min, String max) {
        return jedisCluster.zremrangeByLex(key, min, max);
    }

    /**
     * 把 value 插入存于 key 的列表中在基准值 pivot 的前面或后面。
     * 当 key 不存在时，这个list会被看作是空list，任何操作都不会发生。
     * 当 key 存在，但保存的不是一个list的时候，会返回error。
    * @author 老王
    * @date 2015年6月17日 下午7:34:52
    * @param key 键
    * @param where 如：where.BEFORE
    * @param pivot 前或后
    * @param value 值
    * @return 在 insert 操作后的 list 长度。
     */
    public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
        return jedisCluster.linsert(key, where, pivot, value);
    }

    /**
     * 只有当 key 已经存在并且存着一个 list 的时候，在这个 key 下面的 list 的头部插入 value。
     * (重点)与 LPUSH 相反，当 key 不存在的时候不会进行任何操作。
    * @author 老王
    * @date 2015年6月17日 下午7:39:29
    * @param key 键
    * @param string 值
    * @return 在 push 操作后的 list 长度。
     */
    public Long lpushx(String key, String... string) {
        return jedisCluster.lpushx(key, string);
    }

    /**
     * 将值 value 插入到列表 key 的表尾, 当且仅当 key 存在并且是一个列表。 
     * (重点)和 RPUSH 命令相反, 当 key 不存在时，RPUSHX 命令什么也不做。
    * @author 老王
    * @date 2015年6月17日 下午7:45:48
    * @param key 键
    * @param string 值
    * @return RPUSHX 命令执行之后，表的长度。
     */
    public Long rpushx(String key, String... string) {
        return jedisCluster.rpushx(key, string);
    }

    /**
     * 如果删除的key不存在，则直接忽略。
    * @author 老王
    * @date 2015年6月17日 下午7:51:21
    * @param key 键
    * @return 被删除的keys的数量
     */
    public Long del(String key) {
        return jedisCluster.del(key);
    }

    /**
     * 返回消息
    * @author 老王
    * @date 2015年6月17日 下午7:55:55
    * @param string 
    * @return Bulk reply
     */
    public String echo(String string) {
        return jedisCluster.echo(string);
    }

    /**
     * 如果当前数据库(源数据库)和给定数据库(目标数据库)有相同名字的给定 key ，或者 key 不存在于当前数据库，那么 MOVE 没有任何效果。
     * 因此，也可以利用这一特性，将 MOVE 当作锁(locking)原语(primitive)。
    * @author 老王
    * @date 2015年6月17日 下午7:56:39
    * @param key 键
    * @param dbIndex 给定数据库
    * @return 移动成功返回 1  失败则返回 0
     */
    public Long move(String key, int dbIndex) {
        return jedisCluster.move(key, dbIndex);
    }

    /**
     * 统计字符串的字节数
    * @author 老王
    * @date 2015年6月17日 下午7:59:36
    * @param key 键
    * @return 字节数
     */
    public Long bitcount(String key) {
        return jedisCluster.bitcount(key);
    }

    /**
     * 统计字符串的字节数
    * @author 老王
    * @date 2015年6月17日 下午8:02:01
    * @param key 键
    * @param start 开始
    * @param end 结束
    * @return 字节数
     */
    public Long bitcount(String key, long start, long end) {
        return jedisCluster.bitcount(key, start, end);
    }

    /**
     * 获取当前客户端连接节点（暂时是这么猜的）
    * @author 老王
    * @date 2015年6月17日 下午8:07:10
    * @return  节点信息
     */
    public Map<String, JedisPool> getClusterNodes() {
        return jedisCluster.getClusterNodes();
    }

    /**
     * 迭代hash里面的元素
    * @author 老王
    * @date 2015年6月17日 下午8:08:05
    * @param key 键
    * @param cursor 元素
    * @return 自己找
     */
    public ScanResult<Entry<String, String>> hscan(String key, String cursor) {
        return jedisCluster.hscan(key, cursor);
    }

    /**
     * 迭代set里面的元素
    * @author 老王
    * @date 2015年6月17日 下午8:09:30
    * @param key 键
    * @param cursor  元素
    * @return 自己找
     */
    public ScanResult<String> sscan(final String key, final String cursor) {
        return jedisCluster.sscan(key, cursor);
    }

    /**
     * 迭代zset里面的元素
    * @author 老王
    * @date 2015年6月17日 下午8:09:45
    * @param key 键
    * @param cursor 元素
    * @return 自己找
     */
    public ScanResult<Tuple> zscan(final String key, final String cursor) {
        return jedisCluster.zscan(key, cursor);
    }

    /**
     * 将除了第一个参数以外的参数存储到以第一个参数为变量名的HyperLogLog结构中.
     *这个命令的一个副作用是它可能会更改这个HyperLogLog的内部来反映在每添加一个唯一的对象时估计的基数(集合的基数).
     *如果一个HyperLogLog的估计的近似基数在执行命令过程中发了变化， PFADD 返回1，否则返回0，
     *如果指定的key不存在，这个命令会自动创建一个空的HyperLogLog结构（指定长度和编码的字符串）.
     *如果在调用该命令时仅提供变量名而不指定元素也是可以的，如果这个变量名存在，则不会有任何操作，如果不存在，则会创建一个数据结构（返回1）.
     *了解更多HyperLogLog数据结构，请查阅PFCOUNT 命令页面.
    * @author 老王
    * @date 2015年6月17日 下午8:10:27
    * @param key 键
    * @param elements 
    * @return HyperLogLog 的内部被修改了,那么返回 1,否则返回 0 .
     */
    public Long pfadd(String key, String... elements) {
        return jedisCluster.pfadd(key, elements);
    }

    /**
     * 一个带有 0.81% 标准错误（standard error）的近似值.
    * @author 老王
    * @date 2015年6月16日 下午4:16:33
    * @param key 键
    * @return 近似值
     */
    public long pfcount(String key) {
        return jedisCluster.pfcount(key);
    }

    /**
     *  LPOP 的阻塞版本，当给定列表内没有任何元素可供弹出的时候阻塞连接。
     *  弹出头元素
    * @author 老王
    * @date 2015年6月16日 下午4:06:36
    * @param timeout 参数表示的是一个指定阻塞的最大秒数的整型值。 为 0 是表示阻塞时间无限制
    * @param key 键 
    * @return list中第一个元素是弹出元素的 key，第二个元素是 value
     */
    public List<String> blpop(int timeout, String key) {
        return jedisCluster.blpop(timeout, key);
    }

    /**
     * RPOP阻塞版本，在list无法弹出任何元素的时候阻塞连接。
     * 从尾部弹出元素
    * @author 老王
    * @date 2015年6月16日 下午3:18:25
    * @param timeout 参数表示的是一个指定阻塞的最大秒数的整型值。 为 0 是表示阻塞时间无限制
    * @param key 键
    * @return list中第一个元素是弹出元素的 key，第二个元素是 value
     */
    public List<String> brpop(int timeout, String key) {
        return jedisCluster.brpop(timeout, key);
    }

}
