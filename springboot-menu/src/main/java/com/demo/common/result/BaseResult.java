package com.demo.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p> @Title BaseResult
 * <p> @Description 返回封装类
 *
 * @author zhj
 * @date 2021/6/21 10:50
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class BaseResult {

    /**
     * 返回状态码
     */
    private int code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 默认成功
     */
    private BaseResult() {
        code = 200;
        message = "操作成功";
    }

    /**
     * 定制构造方法
     *
     * @param code 状态码
     * @param message 返回信息
     */
    public BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 操作成功的基础方法
     *
     * @return 成功的BaseResult对象
     */
    public static BaseResult success() {
        return new BaseResult();
    }

    /**
     * 自定义成功的返回消息
     *
     * @param message 自定义消息提示
     * @return 成功的BaseResult对象
     */
    public static BaseResult success(String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.code = 200;
        baseResult.message = message;
        return baseResult;
    }

    /**
     * 自定义失败的返回code和message
     *
     * @param code    错误码
     * @param message 错误信息
     * @return 失败的BaseResult对象
     */
    public static BaseResult failed(int code, String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.code = code;
        baseResult.message = message;
        return baseResult;
    }

    /**
     * 自定义失败的返回信息
     *
     * @param message 错误消息
     * @return 失败的BaseResult对象
     */
    public static BaseResult failed(String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.code = 500;
        baseResult.message = message;
        return baseResult;
    }
}
