package com.zefun.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.beans.BeanInfo;  
import java.beans.Introspector;  
import java.beans.PropertyDescriptor;  
import java.lang.reflect.Method;  

/**
 * 集合处理类
* @author 高国藩
* @date 2015年9月9日 下午7:18:29
 */
public class ListUtil {

    /**
     * 将list中重复率最高的String取出
    * @author 高国藩
    * @date 2015年9月9日 下午7:19:27
    * @param arc 集合
    * @return 返回重复率最高的String
     */
    public static String getBigCountFormList(List<String> arc){
        Set<String> set = new HashSet<String>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String s : arc) {
            if (!set.add(s)){
                map.put(s, map.get(s) + 1);
            } 
            else {
                map.put(s, 1); 
            }
        }
        List<Integer> ls = new ArrayList<Integer>();
        for (String k : map.keySet()) {
            ls.add(map.get(k));
        }
        Integer max = (Integer)ls.get(0);
        for (int i = 0; i < ls.size(); i++) {          
            if (max < (Integer) ls.get(i)){
                max = (Integer) ls.get(i);   
            }
        } 
        for (String k : map.keySet()) {
            if (map.get(k) == max){
                return k;
            }
        }
        return "";
    }

    /**
     * javaBean转化为map
    * @author 高国藩
    * @date 2015年9月28日 上午11:46:14
    * @param obj javaBean
    * @return 返回map
     */
    public static Map<String, Object> transBean2Map(Object obj) {  
        if (obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
                    map.put(key, value);  
                }  
  
            }  
        } 
        catch (Exception e) {  
            e.printStackTrace();
        }  
        return map;  
    }  
}