package com.demo.swagger.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p> @Title DemoQueryParam
 * <p> @Description Demo 请求体
 *
 * @author ACGkaka
 * @date 2021/4/24 23:41
 */
@Data
@ApiModel(description = "请求入参")
public class DemoQueryParam {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private String id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;
}
