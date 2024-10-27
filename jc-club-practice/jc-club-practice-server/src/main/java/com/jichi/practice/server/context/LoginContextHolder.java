package com.jichi.practice.server.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录上下文对象
 *
 * @author ZhuFan
 * @data 2024/10/13/013 12:07
 */
public class LoginContextHolder {

    private static final InheritableThreadLocal<Map<String, Object>> THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void set(String key, Object value){
        Map<String, Object> threadLocalMap = getThreadLocalMap();
        threadLocalMap.put(key, value);
    }

    public static Object get(String key){
        Map<String, Object> threadLocalMap = getThreadLocalMap();
        return threadLocalMap.get(key);
    }

    public static Map<String, Object> getThreadLocalMap(){
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null){
            map = new ConcurrentHashMap<>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static void remove(){
        THREAD_LOCAL.remove();
    }

    public static String getLoginId(){
        return (String) getThreadLocalMap().get("loginId");
    }
}
