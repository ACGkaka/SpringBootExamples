package com.demo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.demo.entity.User;
import com.demo.listener.UserListener;
import com.demo.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p> @Title UserServiceImpl
 * <p> @Description 用户Service 实现类
 *
 * @author zhj
 * @date 2021/6/9 11:46
 */
@Service
public class UserServiceImpl implements UserService {

    private final List<User> list = Arrays.asList(
            new User("ACGkaka", "aaa", LocalDateTime.now()),
            new User("张三", "bbb", LocalDateTime.now()),
            new User("李四", "ccc", LocalDateTime.now()),
            new User("王五", "ddd", LocalDateTime.now()));

    private final List<User> list1 = Arrays.asList(list.get(0), list.get(1));

    private final List<User> list2 = Arrays.asList(list.get(2), list.get(3));

    @Override
    public List<User> importExcel(MultipartFile file) throws IOException {
        // 写法1：
        byte[] bytes = file.getBytes();
        InputStream inputStream = file.getInputStream();
        /// 不获取读取到的数据
//        EasyExcel.read(inputStream, User.class, new UserListener()).sheet().doRead();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        List<User> users = EasyExcel.read(inputStream, User.class, new UserListener()).sheet().doReadSync();

        // 写法2：
        inputStream = new ByteArrayInputStream(bytes);
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(inputStream, User.class, new UserListener()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
        return users;
    }

    @Override
    public void exportExcel(HttpServletResponse response) throws IOException {
        // 写法1
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode("用户-导出.xlsx", "utf-8"));
        EasyExcel.write(response.getOutputStream(), User.class).sheet("用户").doWrite(list);

        // 写法2
//        ExcelWriter excelWriter = null;
//        try {
//            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
//            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode("用户-导出.xlsx", "utf-8"));
//            excelWriter = EasyExcel.write(response.getOutputStream(), User.class).build();
//            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//            excelWriter.write(list, writeSheet);
//        } finally {
//            // 千万别忘记finish 会帮忙关闭流
//            if (excelWriter != null) {
//                excelWriter.finish();
//            }
//        }
    }

    @Override
    public void exportByTemplate(HttpServletResponse response) throws IOException {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // 填充list 的时候还要注意 模板中{.} 多了个点 表示list
        String templatePath = "/excel/export-template.xlsx";
        InputStream templateInputStream = this.getClass().getResourceAsStream(templatePath);

        // 方案1 一下子全部放到内存里面 并填充
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode("用户-模板导出.xlsx", "utf-8"));
        EasyExcel.write(response.getOutputStream(), User.class).withTemplate(templateInputStream).sheet().doFill(list);

        // 方案2 分多次 填充 会使用文件缓存（省内存）
//        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
//        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode("用户-模板导出.xlsx", "utf-8"));
//        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), User.class).withTemplate(templateInputStream).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet().build();
//        excelWriter.fill(list1, writeSheet);
//        excelWriter.fill(list2, writeSheet);
//        // 千万别忘记关闭流
//        excelWriter.finish();
    }
}
