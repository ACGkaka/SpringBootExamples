package com.demo.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p> @Title ApiLog
 * <p> @Description 接口日志类
 *
 * @author ACGkaka
 * @date 2021/4/1 13:41
 */
@Data
@AllArgsConstructor
public class ApiLog {

    /**
     * UUID
     */
    private String uuid;

    /**
     * 访问日期
     */
    private LocalDate visitDate;

    /**
     * 模块
     */
    private String module;

    /**
     * 请求url
     */
    private String url;

    /**
     * 访问次数
     */
    private AtomicInteger visitTimes;

    /**
     * 访问成功次数
     */
    private AtomicInteger successTimes;

    /**
     * 访问失败次数
     */
    private AtomicInteger failTimes;

}
