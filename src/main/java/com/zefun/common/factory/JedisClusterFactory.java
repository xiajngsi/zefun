package com.zefun.common.factory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * redis工厂类  
* @author 王大爷
* @date 2015年8月14日 下午4:32:51
 */
public class JedisClusterFactory implements FactoryBean<JedisCluster>, InitializingBean {
    /** 地址属性 */
    private Resource addressConfig;
    /** 地址前缀 */
    private String addressKeyPrefix;
    /** 地址列表 */
    private List<String> addressList;
    /** 链接时间 */
    private Integer timeout;
    /** 最大链接数 */
    private Integer maxRedirections;
    /** 连接池属性 */
    private GenericObjectPoolConfig genericObjectPoolConfig;
    /** redis客户端 */
    private JedisCluster jedisCluster;

    @Override
    public JedisCluster getObject() throws Exception {
        return jedisCluster;
    }

    @Override
    public Class<? extends JedisCluster> getObjectType() {
        return (this.jedisCluster != null ? this.jedisCluster.getClass() : JedisCluster.class);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    /**
     * 
    * @author 王大爷
    * @date 2015年8月14日 下午4:35:59
    * @return  return
    * @throws Exception 异常
     */
    private Set<HostAndPort> parseHostAndPort() throws Exception {
        try {
            Set<HostAndPort> haps = new HashSet<HostAndPort>();
            for (String val : addressList) {
                String[] ipAndPort = val.split(":");

                HostAndPort hap = new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
                haps.add(hap);
            }

            return haps;
        }
        catch (IllegalArgumentException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new Exception("解析 jedis 配置文件失败", ex);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set<HostAndPort> haps = this.parseHostAndPort();
        jedisCluster = new JedisCluster(haps, timeout, maxRedirections, genericObjectPoolConfig);
    }

    public Resource getAddressConfig() {
        return addressConfig;
    }

    public void setAddressConfig(Resource addressConfig) {
        this.addressConfig = addressConfig;
    }

    public String getAddressKeyPrefix() {
        return addressKeyPrefix;
    }

    public void setAddressKeyPrefix(String addressKeyPrefix) {
        this.addressKeyPrefix = addressKeyPrefix;
    }

    public List<String> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<String> addressList) {
        this.addressList = addressList;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getMaxRedirections() {
        return maxRedirections;
    }

    public void setMaxRedirections(Integer maxRedirections) {
        this.maxRedirections = maxRedirections;
    }

    public GenericObjectPoolConfig getGenericObjectPoolConfig() {
        return genericObjectPoolConfig;
    }

    public void setGenericObjectPoolConfig(GenericObjectPoolConfig genericObjectPoolConfig) {
        this.genericObjectPoolConfig = genericObjectPoolConfig;
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }
}  
