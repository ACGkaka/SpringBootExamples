package com.demo.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.demo.entity.User;
import com.demo.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p> @Title UserServiceImpl
 * <p> @Description 用户Service 实现类
 *
 * @author zhj
 * @date 2021/6/9 11:46
 */
@Service
public class UserServiceImpl implements UserService {

    /** 【注意】：不能直接用Arrays.asList()，会报错。 */
    private final List<User> list = new ArrayList<>(Arrays.asList(
            new User("ACGkaka", "aaa", 1, LocalDateTime.now()),
            new User("张三", "bbb", 2, LocalDateTime.now()),
            new User("李四", "ccc", 1, LocalDateTime.now()),
            new User("王五", "ddd", 1, LocalDateTime.now())));

    @Override
    public List<User> importExcel(MultipartFile file) throws Exception {
        // 导入
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        return ExcelImportUtil.importExcel(file.getInputStream(), User.class, params);
    }

    @Override
    public void exportExcel(HttpServletResponse response) throws IOException {
        // 导出
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode("用户-导出.xls", "utf-8"));
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户数据", "sheet1"), User.class, list);
        workbook.write(response.getOutputStream());
    }

    @Override
    public void exportByTemplate(HttpServletResponse response) throws IOException {
        // 模板导出
        String templatePath = "/excel/export-template.xlsx";
        InputStream templateInputStream = this.getClass().getResourceAsStream(templatePath);
        // HSSFWorkbook 扩展名xls XSSFWorkbook 扩展名xlsx
        Workbook templateWorkbook = new XSSFWorkbook(templateInputStream);
        TemplateExportParams params = new TemplateExportParams("用户数据", 1);
        params.setTemplateWb(templateWorkbook);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);

        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode("用户-模板导出.xls", "utf-8"));
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        workbook.write(response.getOutputStream());
    }
}
