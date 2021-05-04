package com.demo.swagger.controller;

import com.demo.swagger.query.DemoQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p> @Title DemoController
 * <p> @Description Controller 接口
 *
 * @author ACGkaka
 * @date 2021/4/24 23:35
 */
@Api(tags = "接口类")
@RestController
@RequestMapping("/demo")
public class DemoController {

    @ApiOperation("get接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "String", paramType = "json", required = true)
    })
    @GetMapping("/get")
    public String get(String id) {
        return id;
    }

    @ApiOperation("post接口")
    @PostMapping("/post")
    public DemoQueryParam post(@RequestBody DemoQueryParam param) {
        return param;
    }
}
