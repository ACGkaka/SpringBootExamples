package com.demo.module.utils;

import com.demo.module.entity.ApiLog;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数器，统计当前执行的任务数
 *
 * @author ACGkaka
 */
@Component
public class AtomicCounter {

    private static final AtomicCounter atomicCounter = new AtomicCounter();

    /**
     * 单例，不允许外界主动实例化
     */
    private AtomicCounter() {}

    public static AtomicCounter getInstance() {
        return atomicCounter;
    }

    private static Map<String, ApiLog> map = new HashMap<>();

    public static AtomicCounter init(String module, String url) {

        if (Strings.isBlank(url)) {
            return atomicCounter;
        }

        ApiLog apiLog = map.get(url);
        if (apiLog == null) {
            apiLog = new ApiLog(UUID.randomUUID().toString().replace("-", ""),
                    LocalDate.now(), module, url, new AtomicInteger(), new AtomicInteger(), new AtomicInteger());
            map.put(url, apiLog);
        }

        return atomicCounter;
    }

    public ApiLog getValue(String url) {
        return map.get(url);
    }

    public int increaseVisit(String url) {
        return map.get(url).getVisitTimes().incrementAndGet();
    }

    public int increaseSuccess(String url) {
        return map.get(url).getSuccessTimes().incrementAndGet();
    }

    public int increaseFail(String url) {
        return map.get(url).getFailTimes().incrementAndGet();
    }

}
