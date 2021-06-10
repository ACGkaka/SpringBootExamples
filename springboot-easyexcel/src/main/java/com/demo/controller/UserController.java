package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p> @Title UserController
 * <p> @Description 用户Controller
 *
 * @author ACGkaka
 * @date 2021/6/9 11:47
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 导入Excel
     *
     * @param file Excel 文件
     * @return 用户信息
     */
    @PostMapping("importExcel")
    public List<User> importExcel(MultipartFile file) throws IOException {
        return userService.importExcel(file);
    }

    /**
     * 导出Excel
     *
     * @param response 响应
     */
    @PostMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        userService.exportExcel(response);
    }

    /**
     * 模板导出Excel
     *
     * @param response 响应
     */
    @PostMapping("/exportByTemplate")
    public void exportByTemplate(HttpServletResponse response) throws IOException {
        userService.exportByTemplate(response);
    }
}
