package com.demo.service;

import com.demo.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p> @Title UserService
 * <p> @Description 用户Service
 *
 * @author zhj
 * @date 2021/6/9 11:46
 */
public interface UserService {

    /**
     * 导入Excel
     *
     * @param file Excel 文件
     * @return 用户信息
     */
    List<User> importExcel(MultipartFile file) throws Exception;

    /**
     * 导出Excel
     *
     * @param response 响应
     */
    void exportExcel(HttpServletResponse response) throws IOException;

    /**
     *
     * 模板导出Excel
     *
     * @param response 响应
     * @throws IOException IO异常
     */
    void exportByTemplate(HttpServletResponse response) throws IOException;
}
